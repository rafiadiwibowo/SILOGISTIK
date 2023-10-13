package apap.ti.silogistik2106653855.dto.request;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class RestockBarangRequestDTO extends CreateBarangRequestDTO{
    private List<RestockBarangItem> listRestockBarang;
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class RestockBarangItem {
        private String sku;
        private int jumlahStok;
    }
}

