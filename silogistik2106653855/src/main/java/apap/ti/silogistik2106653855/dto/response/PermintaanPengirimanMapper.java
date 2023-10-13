package apap.ti.silogistik2106653855.dto.response;

import apap.ti.silogistik2106653855.dto.request.CreatePermintaanPengirimanRequestDTO;
import apap.ti.silogistik2106653855.model.Gudang;
import apap.ti.silogistik2106653855.model.Karyawan;
import apap.ti.silogistik2106653855.model.PermintaanPengiriman;
import apap.ti.silogistik2106653855.service.BarangService;
import apap.ti.silogistik2106653855.service.KaryawanService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public interface PermintaanPengirimanMapper {
    PermintaanPengiriman createPermintaanPengirimanRequestDTOToPermintaanPengiriman(CreatePermintaanPengirimanRequestDTO permintaanPengirimanDTO);
}
