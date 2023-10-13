package apap.ti.silogistik2106653855.dto.request;

import apap.ti.silogistik2106653855.model.Barang;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateBarangRequestDTO {
    private String sku;
    private Integer tipeBarang;
    private String merk;
    private Long hargaBarang;
}
