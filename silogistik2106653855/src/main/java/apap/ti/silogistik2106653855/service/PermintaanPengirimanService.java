package apap.ti.silogistik2106653855.service;

import apap.ti.silogistik2106653855.model.Karyawan;
import apap.ti.silogistik2106653855.model.PermintaanPengiriman;

import java.time.LocalDateTime;
import java.util.List;


public interface PermintaanPengirimanService {
    List<PermintaanPengiriman> getAllPermintaanPengiriman();
    PermintaanPengiriman findPermintaanPengirimanById(Long id);
    void tambahPermintaanPengiriman(PermintaanPengiriman permintaanPengiriman, Karyawan karyawan);

    String generateNomorPengirimanDua(int jumlahBarang, LocalDateTime waktuPermintaan, int jenisLayanan);

    String getJenisLayananString(int jenisLayanan);

    boolean isCancelable(PermintaanPengiriman permintaanPengiriman);

}
