package apap.ti.silogistik2106653855.service;

import apap.ti.silogistik2106653855.model.Barang;
import apap.ti.silogistik2106653855.model.Gudang;

import java.util.List;

public interface BarangService {

    List<Barang> getAllBarang();
    List<String> findAllMerkBarang();
    String findSkuByMerk(String merk);
    void saveBarang(Barang barang);
    String generateSKU(Barang barang);
    Barang getBarangById(String id);
    Barang updateBarang(Barang buku);
    String getTipeBarang(Barang barang);

    Barang findBarangBySku(String sku);

    int findStok(Barang barang);

}
