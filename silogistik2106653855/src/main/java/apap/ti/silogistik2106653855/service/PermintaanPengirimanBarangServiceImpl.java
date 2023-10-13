package apap.ti.silogistik2106653855.service;


import apap.ti.silogistik2106653855.model.Barang;
import apap.ti.silogistik2106653855.model.PermintaanPengiriman;
import apap.ti.silogistik2106653855.model.PermintaanPengirimanBarang;
import apap.ti.silogistik2106653855.repository.PermintaanPengirimanBarangDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PermintaanPengirimanBarangServiceImpl implements PermintaanPengirimanBarangService {

    @Autowired
    PermintaanPengirimanBarangDb permintaanPengirimanBarangDb;

    @Override
    public List<PermintaanPengirimanBarang> findBarangByPermintaan(PermintaanPengiriman permintaanPengiriman) {
        return permintaanPengirimanBarangDb.findPermintaanPengirimanBarangByPermintaanPengiriman(permintaanPengiriman);
    }
}
