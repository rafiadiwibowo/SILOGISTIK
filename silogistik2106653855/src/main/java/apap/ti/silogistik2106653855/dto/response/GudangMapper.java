package apap.ti.silogistik2106653855.dto.response;

import apap.ti.silogistik2106653855.dto.request.CreateGudangRequestDTO;
import apap.ti.silogistik2106653855.dto.request.ReadGudangResponseDTO;
import apap.ti.silogistik2106653855.dto.request.UpdateGudangRequestDTO;
import apap.ti.silogistik2106653855.model.Gudang;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GudangMapper {
    ReadGudangResponseDTO bukuToReadBukuResponseDTO(Gudang gudang);
    Gudang updateGudangRequestDTOToGudang(UpdateGudangRequestDTO updateGudangRequestDTO);
    Gudang gudangToUpdateGudangRequestDTO(Gudang gudang);
    Gudang createGudangRequestDTOToGudang(CreateGudangRequestDTO createGudangRequestDTO);
}
