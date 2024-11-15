package br.com.mvc.energymi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tb_aparelho")
public class Aparelho {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "templates/aparelho")
    @SequenceGenerator(name = "templates/aparelho", sequenceName = "seq_mi_aparelho", allocationSize = 1)
    @Column(name = "cdAparelho", length = 10)
    private Long id;

    @NotNull(message = "O nome do aparelho não pode ser nulo")
    @Size(max = 100, message = "O nome do aparelho não pode ter mais de 100 caracteres")
    @Column(name = "dsNomeAparelho", nullable = false, length = 100)
    private String nome;

    @NotNull(message = "O tipo do aparelho não pode ser nulo")
    @Size(max = 250, message = "O tipo do aparelho não pode ter mais de 250 caracteres")
    @Column(name = "dsTipoAparelho", nullable = false, length = 250)
    private String tipo;

    @NotNull(message = "A potência em watts não pode ser nula")
    @Min(value = 1, message = "A potência em watts deve ser maior ou igual a 1")
    @Column(name = "nrWatts", nullable = false, precision = 5)
    private Integer watts;

    @OneToMany(mappedBy = "aparelho", cascade = CascadeType.ALL)
    private List<Alerta> alertas;

    @ManyToOne
    @JoinColumn(name = "cdInstalacao", referencedColumnName = "cdInstalacao", nullable = false)
    private Instalacao instalacao;

    @OneToMany(mappedBy = "aparelho", cascade = CascadeType.ALL)
    private List<Consumo> consumos;

    @OneToMany(mappedBy = "aparelho", cascade = CascadeType.ALL)
    private List<Recomendacao> recomendacoes;
}
