package apap.ti.silogistik2106653855.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.math.BigInteger;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "gudang")
public class Gudang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nama", nullable = false)
    @Size(max = 255)
    @NotNull
    private String nama;

    @Column(name = "alamat_gudang", nullable = false)
    @Size(max = 255)
    @NotNull
    private String alamatGudang;

    @OneToMany(mappedBy = "gudang", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<GudangBarang> listGudangBarang;
}
