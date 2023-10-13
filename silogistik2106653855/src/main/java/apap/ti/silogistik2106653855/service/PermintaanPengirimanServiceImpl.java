package apap.ti.silogistik2106653855.service;

import apap.ti.silogistik2106653855.model.Barang;
import apap.ti.silogistik2106653855.model.Karyawan;
import apap.ti.silogistik2106653855.model.PermintaanPengiriman;
import apap.ti.silogistik2106653855.model.PermintaanPengirimanBarang;
import apap.ti.silogistik2106653855.repository.PermintaanPengirimanBarangDb;
import apap.ti.silogistik2106653855.repository.PermintaanPengirimanDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class PermintaanPengirimanServiceImpl implements PermintaanPengirimanService{

    @Autowired
    PermintaanPengirimanDb permintaanPengirimanDb;

    @Autowired
    PermintaanPengirimanBarangDb permintaanPengirimanBarangDb;

    @Override
    public List<PermintaanPengiriman> getAllPermintaanPengiriman() { return permintaanPengirimanDb.findAll(); }

    @Override
    public String generateNomorPengirimanDua(int jumlahBarang, LocalDateTime waktuPermintaan, int jenisLayanan) {
        String jenisLayananString = getJenisLayananString(jenisLayanan);
        String jumlahBarangFormatted = String.format("%02d", jumlahBarang);
        LocalTime localTime = waktuPermintaan.toLocalTime();
        String waktuPembuatan = localTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        String nomorPengiriman = String.format("REQ%s%s%s", jumlahBarangFormatted, jenisLayananString, waktuPembuatan);

        return nomorPengiriman;
    }

    @Override
    public String getJenisLayananString(int jenisLayanan) {
        switch (jenisLayanan) {
            case 1:
                return "SAM";
            case 2:
                return "KIL";
            case 3:
                return "REG";
            case 4:
                return "HEM";
            default:
                throw new IllegalArgumentException("Jenis Layanan tidak valid");
        }
    }

    @Override
    public PermintaanPengiriman findPermintaanPengirimanById(Long id) {
        for (PermintaanPengiriman permintaanPengiriman : getAllPermintaanPengiriman()) {
            if (id.equals(permintaanPengiriman.getId())) {
                return permintaanPengiriman;
            }
        }
        return null;
    }

    @Override
    public void tambahPermintaanPengiriman(PermintaanPengiriman permintaanPengiriman, Karyawan karyawan) {
        permintaanPengiriman.setIsCancelled(false);
        permintaanPengiriman.setWaktuPermintaan(LocalDateTime.now());

        int jumlahBarang = 0;
        if (permintaanPengiriman.getListPermintaanPengirimanBarang() != null) {
            for (PermintaanPengirimanBarang permintaanPengirimanBarang : permintaanPengiriman.getListPermintaanPengirimanBarang()) {
                jumlahBarang += permintaanPengirimanBarang.getKuantitasPesanan();

            }
        }

        permintaanPengiriman.setNomorPengiriman(generateNomorPengirimanDua(jumlahBarang, permintaanPengiriman.getWaktuPermintaan(), permintaanPengiriman.getJenisLayanan()));
        permintaanPengirimanDb.save(permintaanPengiriman);

        for (PermintaanPengirimanBarang permintaanPengirimanBarang : permintaanPengiriman.getListPermintaanPengirimanBarang()) {
            permintaanPengirimanBarang.setPermintaanPengiriman(permintaanPengiriman);
            permintaanPengirimanBarangDb.save(permintaanPengirimanBarang);
        }
        permintaanPengiriman.setKaryawan(karyawan);

        permintaanPengirimanDb.save(permintaanPengiriman);
    }

    @Override
    public boolean isCancelable(PermintaanPengiriman permintaanPengiriman) {
        LocalDateTime waktuSekarang = LocalDateTime.now();
        LocalDateTime waktuPermintaan = permintaanPengiriman.getWaktuPermintaan();
        long hoursBetween = Duration.between(waktuPermintaan, waktuSekarang).toHours();
        return hoursBetween <= 24;
    }

}
