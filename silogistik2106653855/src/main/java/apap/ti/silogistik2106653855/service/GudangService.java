package apap.ti.silogistik2106653855.service;

import apap.ti.silogistik2106653855.model.Barang;
import apap.ti.silogistik2106653855.model.Gudang;
import apap.ti.silogistik2106653855.model.GudangBarang;

import java.math.BigInteger;
import java.util.List;
import java.util.UUID;

public interface GudangService {

    List<GudangBarang> getAllGudangBarang();
    List<Gudang> getAllGudang();
    Gudang getGudangById(Long id);

    Gudang updateStok(Gudang gudangFromDTO);

    Gudang createGudang(Gudang gudang);

    GudangBarang getGudangBarangByGudangAndBarang(Gudang gudang, Barang barang);
    void updateStockGudangBarang(GudangBarang gudangBarang, Gudang gudang, Barang barang, int stok);


}
