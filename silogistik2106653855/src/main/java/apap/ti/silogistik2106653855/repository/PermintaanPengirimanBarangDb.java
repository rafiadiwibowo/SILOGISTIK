package apap.ti.silogistik2106653855.repository;

import apap.ti.silogistik2106653855.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermintaanPengirimanBarangDb extends JpaRepository<PermintaanPengirimanBarang, Long> {
    List<PermintaanPengirimanBarang> findPermintaanPengirimanBarangByPermintaanPengiriman(PermintaanPengiriman permintaanPengiriman);
}
