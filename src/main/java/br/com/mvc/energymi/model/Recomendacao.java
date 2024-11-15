package br.com.mvc.energymi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.FutureOrPresent;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tb_recomendacao")
public class Recomendacao {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "templates/recomendacao")
    @SequenceGenerator(name = "templates/recomendacao", sequenceName = "seq_mi_recomendacao", allocationSize = 1)
    @Column(name = "cdRecomendacao", length = 10)
    private Long id;

    @Size(max = 250, message = "A observação não pode ter mais de 250 caracteres")
    @Column(name = "dsObservacao", length = 250)
    private String observacao;

    @NotNull(message = "A data da recomendação não pode ser nula")
    @FutureOrPresent(message = "A data da recomendação deve ser no presente ou no futuro")
    @Column(name = "dtRecomendacao", nullable = false)
    private LocalDate data;

    @Size(max = 50, message = "O status não pode ter mais de 50 caracteres")
    @Column(name = "stRecomendacao", length = 50)
    private String status;

    @NotNull(message = "O aparelho não pode ser nulo")
    @ManyToOne
    @JoinColumn(name = "cdAparelho", nullable = false)
    private Aparelho aparelho;
}
