package apap.ti.silogistik2106653855.service;

import apap.ti.silogistik2106653855.model.Barang;
import apap.ti.silogistik2106653855.model.Gudang;
import apap.ti.silogistik2106653855.model.GudangBarang;
import apap.ti.silogistik2106653855.repository.GudangBarangDb;
import apap.ti.silogistik2106653855.repository.GudangDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class GudangServiceImpl implements GudangService{

    @Autowired
    GudangDb gudangDb;

    @Autowired
    GudangBarangDb gudangBarangDb;

    @Override
    public Gudang createGudang(Gudang gudang) { return gudangDb.save(gudang); }

    @Override
    public List<Gudang> getAllGudang() { return gudangDb.findAll(); }

    @Override
    public List<GudangBarang> getAllGudangBarang() {
        return gudangBarangDb.findAll();
    }



    @Override
    public Gudang getGudangById(Long id) {
        for (Gudang gudang : getAllGudang()) {
            if (gudang.getId().equals(id)) {
                return gudang;
            }
        }
        return null;
    }

    @Override
    public Gudang updateStok(Gudang gudangFromDTO) {
        Gudang gudang = getGudangById(gudangFromDTO.getId());

        if (gudang != null) {
            gudang.setListGudangBarang(new ArrayList<>());
            for (GudangBarang gudangBarang : gudangFromDTO.getListGudangBarang()) {
                gudangBarang.setGudang(gudang);
                gudang.getListGudangBarang().add(gudangBarang);
            }
        }
        gudangDb.save(gudang);
        return gudang;
    }

    @Override
    public GudangBarang getGudangBarangByGudangAndBarang(Gudang gudang, Barang barang) {
        for (GudangBarang gudangBarang : gudang.getListGudangBarang()) {
            if (gudangBarang.getBarang().getSku().equals(barang.getSku())) {
                return gudangBarang;
            }
        }
        return null;
    }

    @Override
    public void updateStockGudangBarang(GudangBarang gudangBarang, Gudang gudang, Barang barang,  int stok) {

        if (gudangBarang != null) {
            gudangBarangDb.save(gudangBarang);
        }
        else {
            GudangBarang gudangBarangBaru = new GudangBarang();
            gudangBarangBaru.setGudang(gudang);
            gudangBarangBaru.setBarang(barang);
            gudangBarangBaru.setStok(stok);
            gudangBarangBaru.setId((long) (getAllGudangBarang().size() + 1 + 1));

            gudangBarangDb.save(gudangBarangBaru);
        }
    }
}
