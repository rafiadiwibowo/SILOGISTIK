package apap.ti.silogistik2106653855.service;

import apap.ti.silogistik2106653855.model.Karyawan;
import apap.ti.silogistik2106653855.repository.KaryawanDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KaryawanServiceImpl implements KaryawanService{

    @Autowired
    KaryawanDb karyawanDb;

    @Override
    public List<Karyawan> getAllKaryawan() {
        return karyawanDb.findAll();
    }

    @Override
    public Karyawan getKaryawanById(Long id) {
        for (Karyawan karyawan : getAllKaryawan()) {
            if (karyawan.getId().equals(id)) {
                return karyawan;
            }
        }
        return null;
    }

    @Override
    public void saveKaryawan(Karyawan karyawan) {
        karyawanDb.save(karyawan);
    }
}
