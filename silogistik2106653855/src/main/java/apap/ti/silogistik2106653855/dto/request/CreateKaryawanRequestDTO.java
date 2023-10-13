package apap.ti.silogistik2106653855.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateKaryawanRequestDTO {
    private String nama;
    private Integer jenisKelamin;
    private Date tanggalLahir;
}
