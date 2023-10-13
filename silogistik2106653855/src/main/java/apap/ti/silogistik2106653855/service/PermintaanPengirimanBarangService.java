package apap.ti.silogistik2106653855.service;

import apap.ti.silogistik2106653855.model.Barang;
import apap.ti.silogistik2106653855.model.PermintaanPengiriman;
import apap.ti.silogistik2106653855.model.PermintaanPengirimanBarang;

import java.time.LocalDate;
import java.util.List;


public interface PermintaanPengirimanBarangService {
    List<PermintaanPengirimanBarang> findBarangByPermintaan(PermintaanPengiriman permintaanPengiriman);
}
