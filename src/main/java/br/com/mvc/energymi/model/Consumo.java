package br.com.mvc.energymi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tb_consumo")
public class Consumo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "templates/consumo")
    @SequenceGenerator(name = "templates/consumo", sequenceName = "seq_mi_consumo", allocationSize = 1)
    @Column(name = "cdConsumo", length = 10)
    private Long id;

    @Column(name = "dtConsumo", nullable = false)
    private LocalDate data;

    @Column(name = "nrConsumoEnergia", nullable = false)
    private Integer consumoEnergia;

    @Column(name = "nrCusto", nullable = false)
    private Integer custo;

    @Column(name = "dsObservacoes", length = 250)
    private String observacoes;

    @ManyToOne
    @JoinColumn(name = "cdAparelho", referencedColumnName = "cdAparelho", nullable = false)
    private Aparelho aparelho;
}