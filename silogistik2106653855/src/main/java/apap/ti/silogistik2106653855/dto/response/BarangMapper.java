package apap.ti.silogistik2106653855.dto.response;

import apap.ti.silogistik2106653855.dto.request.CreateBarangRequestDTO;
import apap.ti.silogistik2106653855.dto.request.ReadBarangResponseDTO;
import apap.ti.silogistik2106653855.dto.request.UpdateBarangRequestDTO;
import apap.ti.silogistik2106653855.model.Barang;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BarangMapper {
    Barang createBarangRequestDTOToBarang(CreateBarangRequestDTO createBarangRequestDTO);
    Barang updateBarangRequestDTOToBarang(UpdateBarangRequestDTO updateBarangRequestDTO);
    UpdateBarangRequestDTO barangToUpdateBarangRequestDTO(Barang barang);
    ReadBarangResponseDTO barangToReadBarangDTO(Barang barang);


}
