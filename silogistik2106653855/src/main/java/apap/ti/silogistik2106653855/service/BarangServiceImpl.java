package apap.ti.silogistik2106653855.service;

import apap.ti.silogistik2106653855.model.Barang;
import apap.ti.silogistik2106653855.model.Gudang;
import apap.ti.silogistik2106653855.model.GudangBarang;
import apap.ti.silogistik2106653855.repository.BarangDb;
import apap.ti.silogistik2106653855.repository.GudangBarangDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BarangServiceImpl implements BarangService{

    @Autowired
    BarangDb barangDb;

    @Autowired
    GudangBarangDb gudangBarangDb;

    @Override
    public void saveBarang(Barang barang) {
        barangDb.save(barang);
    }

    @Override
    public List<Barang> getAllBarang() {
        return barangDb.findAll();
    }
    @Override
    public List<String> findAllMerkBarang() {
        List<Barang> listBarang = getAllBarang();
        List<String> listMerk = new ArrayList<>();
        for (Barang b: listBarang) {
            if(listMerk.isEmpty()) {
                listMerk.add(b.getMerk());
            } else if (!listMerk.contains(b.getMerk())) {
                listMerk.add(b.getMerk());
            }
        }
        return listMerk;
    }

    @Override
    public String findSkuByMerk(String merk) {
        return barangDb.findSkuByMerk(merk);
    }

    @Override
    public String generateSKU(Barang barang) {
        String typeCode = getTypeCode(barang.getTipeBarang());
        String autoIncrement = String.format("%03d", getAutoIncrement(typeCode));
        return typeCode + autoIncrement;
    }

    @Override
    public int findStok(Barang barang) {
        int stok = 0;

        if (barang.getListGudangBarang() != null || barang.getListGudangBarang().size() != 0) {
            for (GudangBarang gudangBarangX : barang.getListGudangBarang()) {
                if (gudangBarangX.getBarang().getSku().equals(barang.getSku())) {
                    stok += gudangBarangX.getStok();
                }
            }
        }

        return stok;

    }

    private String getTypeCode(Integer tipe_barang) {
        switch (tipe_barang) {
            case 1:
                return "ELEC";
            case 2:
                return "CLOT";
            case 3:
                return "FOOD";
            case 4:
                return "COSM";
            case 5:
                return "TOOL";
            default:
                return "UNKWN"; // Unknown type
        }
    }

    private int getAutoIncrement(String typeCode) {
        int autoIncrement = 1;
        String maxSku = barangDb.findMaxSkuByTypeCode(typeCode); // Implement this method in your repository
        if (maxSku != null) {
            String autoIncrementStr = maxSku.substring(4);
            autoIncrement = Integer.parseInt(autoIncrementStr) + 1;
        }
        return autoIncrement;
    }

    @Override
    public Barang getBarangById(String id) {
        for (Barang barang : getAllBarang()) {
            if (barang.getSku().equals(id)) {
                return barang;
            }
        }
        return null;
    }

    @Override
    public String getTipeBarang(Barang barang) {

        String prefix;
        switch (barang.getTipeBarang()) {
            case 1:
                prefix = "Elektronik";
                break;
            case 2:
                prefix = "Baju / Fashion";
                break;
            case 3:
                prefix = "Makanan";
                break;
            case 4:
                prefix = "Kosmetik";
                break;
            case 5:
                prefix = "Peralatan";
                break;
            default:
                prefix = "";
        }

        return prefix;
    }

    @Override
    public Barang updateBarang (Barang barangFromDto) {
        Barang barang = getBarangById(barangFromDto.getSku());
        System.out.println(barang);
        if (barang != null) {
            barang.setSku(barangFromDto.getSku());
            barang.setTipeBarang(barangFromDto.getTipeBarang());
            barang.setMerk(barangFromDto.getMerk());
            barang.setHargaBarang(barangFromDto.getHargaBarang());
            barangDb.save(barang);
        }
        return barang;
    }

    @Override
    public Barang findBarangBySku(String sku) {
        for (Barang barang : getAllBarang()) {
            if (sku.equals(barang.getSku())) {
                return barang;
            }
        }
        return null;
    }
}
