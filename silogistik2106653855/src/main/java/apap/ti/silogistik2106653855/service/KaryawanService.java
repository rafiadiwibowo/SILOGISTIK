package apap.ti.silogistik2106653855.service;

import apap.ti.silogistik2106653855.model.Karyawan;

import java.util.List;

public interface KaryawanService {
    List<Karyawan> getAllKaryawan();

    Karyawan getKaryawanById(Long id);

    void saveKaryawan(Karyawan karyawan);


}
