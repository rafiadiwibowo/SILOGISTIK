package apap.ti.silogistik2106653855.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "permintaan_pengiriman_barang")
public class PermintaanPengirimanBarang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_permintaan_pengiriman")
    private PermintaanPengiriman permintaanPengiriman;

    @ManyToOne
    @JoinColumn(name = "sku_barang", referencedColumnName = "sku")
    private Barang barang;

    @Column(name = "kuantitas_pesanan", nullable = false)
    private Integer kuantitasPesanan;
}
