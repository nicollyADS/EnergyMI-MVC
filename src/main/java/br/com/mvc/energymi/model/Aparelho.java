package br.com.mvc.energymi.model;

import jakarta.persistence.*;
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

    @Column(name = "dsNomeAparelho", nullable = false, length = 100)
    private String nome;

    @Column(name = "dsTipoAparelho", nullable = false, length = 250)
    private String tipo;

    @Column(name = "nrWatts", nullable = false, precision = 5)
    private Integer watts;

    @OneToMany(mappedBy = "aparelho", cascade = CascadeType.ALL)
    private List<Alerta> alertas;

    @ManyToOne
    @JoinColumn(name = "cdInstalacao", referencedColumnName = "cdInstalacao",nullable = false)
    private Instalacao instalacao;

    @OneToMany(mappedBy = "aparelho", cascade = CascadeType.ALL)
    private List<Consumo> consumos;

    @OneToMany(mappedBy = "aparelho", cascade = CascadeType.ALL)
    private List<Recomendacao> recomendacoes;
}