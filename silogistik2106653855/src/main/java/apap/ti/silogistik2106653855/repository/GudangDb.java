package apap.ti.silogistik2106653855.repository;

import apap.ti.silogistik2106653855.model.Gudang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GudangDb extends JpaRepository<Gudang, Long> {
}
