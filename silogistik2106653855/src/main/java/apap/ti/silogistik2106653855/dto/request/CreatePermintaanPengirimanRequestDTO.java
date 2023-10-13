package apap.ti.silogistik2106653855.dto.request;

import apap.ti.silogistik2106653855.model.Karyawan;
import apap.ti.silogistik2106653855.model.PermintaanPengirimanBarang;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreatePermintaanPengirimanRequestDTO {
    private Long idPermintaanPengirimanBarangSekarang;
    private String nomorPengiriman;
    private String namaPenerima;
    private String alamatPenerima;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date tanggalPengiriman;
    private int biayaPengiriman;
    private int jenisLayanan;
    private Long karyawanId;
    private Karyawan karyawan;
    private List<PermintaanPengirimanBarang> listPermintaanPengirimanBarang;

}

