package apap.ti.silogistik2106653855.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "permintaan_pengiriman")
public class PermintaanPengiriman {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nomor_pengiriman")
    @NotNull
    @Size(max = 16)
    private String nomorPengiriman;

    @Column(name = "is_cancelled")
    private Boolean isCancelled;

    @Column(name = "nama_penerima")
    private String namaPenerima;

    @Column(name = "alamat_penerima")
    private String alamatPenerima;

    @Column(name = "tanggal_pengiriman")
    private Date tanggalPengiriman;

    @Column(name = "biaya_pengiriman")
    private Integer biayaPengiriman;

    @Column(name = "jenis_layanan")
    private Integer jenisLayanan;

    @Column(name = "waktu_permintaan")
    private LocalDateTime waktuPermintaan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_karyawan")
    private Karyawan karyawan;

    @OneToMany(mappedBy = "permintaanPengiriman", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PermintaanPengirimanBarang> listPermintaanPengirimanBarang;
}
