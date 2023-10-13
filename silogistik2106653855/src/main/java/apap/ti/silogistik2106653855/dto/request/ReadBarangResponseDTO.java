package apap.ti.silogistik2106653855.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReadBarangResponseDTO {
    private String sku;
    private String tipeBarang;
    private String merk;
    private Long hargaBarang;
    private int stok;
}
