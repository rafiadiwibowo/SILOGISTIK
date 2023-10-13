package apap.ti.silogistik2106653855.repository;

import apap.ti.silogistik2106653855.model.Gudang;
import apap.ti.silogistik2106653855.model.Karyawan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KaryawanDb extends JpaRepository<Karyawan, Long> {

}