package apap.ti.silogistik2106653855.dto.response;

import apap.ti.silogistik2106653855.dto.request.CreateKaryawanRequestDTO;
import apap.ti.silogistik2106653855.model.Karyawan;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface KaryawanMapper {
    Karyawan createKaryawanRequestDTOToKaryawan (CreateKaryawanRequestDTO karyawanDTO);
}
