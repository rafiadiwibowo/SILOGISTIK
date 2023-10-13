package apap.ti.silogistik2106653855.service;

import apap.ti.silogistik2106653855.model.Barang;
import apap.ti.silogistik2106653855.model.Gudang;
import apap.ti.silogistik2106653855.model.GudangBarang;

import java.util.List;
import java.util.UUID;

public interface GudangBarangService {
    List<GudangBarang> findBarangByGudang(Gudang gudang);
    List<GudangBarang> findGudangByBarang(Barang barang);
    List<GudangBarang> findAll();
    List<GudangBarang> findGudangBySku(String barang);
}
