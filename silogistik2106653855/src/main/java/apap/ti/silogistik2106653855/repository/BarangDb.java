package apap.ti.silogistik2106653855.repository;

import apap.ti.silogistik2106653855.model.Barang;
import apap.ti.silogistik2106653855.model.Gudang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BarangDb extends JpaRepository<Barang, String> {
    String findSkuByMerk(String merk);
    @Query("SELECT MAX(b.sku) FROM Barang b WHERE b.sku LIKE ?1%")
    String findMaxSkuByTypeCode(String typeCode);
}
