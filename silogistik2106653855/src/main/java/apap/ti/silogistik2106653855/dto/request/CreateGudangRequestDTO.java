package apap.ti.silogistik2106653855.dto.request;

import apap.ti.silogistik2106653855.model.GudangBarang;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateGudangRequestDTO {
    private String nama;
    private String alamatGudang;
}
