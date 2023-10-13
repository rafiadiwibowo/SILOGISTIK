package apap.ti.silogistik2106653855.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "barang")
public class Barang {
    @Id
    @Column(name = "sku", length = 7)
    private String sku;

    @Column(name = "tipe_barang", nullable = false)
    @NotNull
    private Integer tipeBarang;

    @Column(name = "merk", nullable = false)
    @NotNull
    private String merk;

    @Column(name = "harga_barang", nullable = false)
    @NotNull
    private Long hargaBarang;

    @OneToMany(mappedBy = "barang", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<GudangBarang> listGudangBarang;
}
