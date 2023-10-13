package apap.ti.silogistik2106653855.service;

import apap.ti.silogistik2106653855.model.Barang;
import apap.ti.silogistik2106653855.model.Gudang;
import apap.ti.silogistik2106653855.model.GudangBarang;
import apap.ti.silogistik2106653855.repository.GudangBarangDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GudangBarangServiceImpl implements GudangBarangService{

    @Autowired
    GudangBarangDb gudangBarangDb;
    @Override
    public List<GudangBarang> findBarangByGudang(Gudang gudang) {
        return gudangBarangDb.findGudangBarangByGudang(gudang);
    }

    @Override
    public List<GudangBarang> findGudangByBarang(Barang barang) {
        return gudangBarangDb.findGudangBarangByBarang(barang);
    }

    @Override
    public List<GudangBarang> findAll() {
        return gudangBarangDb.findAll();
    }

    @Override
    public List<GudangBarang> findGudangBySku(String sku) {
        return gudangBarangDb.findGudangBarangByBarangSku(sku);
    }

}
