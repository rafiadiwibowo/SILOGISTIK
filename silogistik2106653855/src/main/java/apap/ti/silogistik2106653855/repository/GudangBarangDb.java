package apap.ti.silogistik2106653855.repository;

import apap.ti.silogistik2106653855.model.Barang;
import apap.ti.silogistik2106653855.model.Gudang;
import apap.ti.silogistik2106653855.model.GudangBarang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GudangBarangDb extends JpaRepository<GudangBarang, Long> {
    List<GudangBarang> findGudangBarangByGudang(Gudang gudang);
    List<GudangBarang> findGudangBarangByBarang(Barang barang);

    List<GudangBarang> findGudangBarangByBarangSku(String sku);
}
