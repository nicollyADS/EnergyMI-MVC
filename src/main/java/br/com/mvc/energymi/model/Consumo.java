package br.com.mvc.energymi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
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

    @NotNull(message = "A data do consumo não pode ser nula")
    @Column(name = "dtConsumo", nullable = false)
    private LocalDate data;

    @NotNull(message = "O consumo de energia não pode ser nulo")
    @Min(value = 1, message = "O consumo de energia deve ser maior que 0")
    @Column(name = "nrConsumoEnergia", nullable = false)
    private Integer consumoEnergia;

    @NotNull(message = "O custo não pode ser nulo")
    @Min(value = 0, message = "O custo não pode ser negativo")
    @Column(name = "nrCusto", nullable = false)
    private Integer custo;

    @Size(max = 250, message = "As observações não podem ter mais de 250 caracteres")
    @Column(name = "dsObservacoes", length = 250)
    private String observacoes;

    @NotNull(message = "O aparelho não pode ser nulo")
    @ManyToOne
    @JoinColumn(name = "cdAparelho", referencedColumnName = "cdAparelho", nullable = false)
    private Aparelho aparelho;
}
