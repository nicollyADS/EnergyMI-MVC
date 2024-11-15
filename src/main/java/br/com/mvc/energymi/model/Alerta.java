package br.com.mvc.energymi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tb_alerta")
@EntityListeners(AuditingEntityListener.class)
public class Alerta {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "templates/alerta")
    @SequenceGenerator(name = "templates/alerta", sequenceName = "seq_mi_alerta", allocationSize = 1)
    @Column(name = "cdAlerta", length = 10)
    private Long id;

    @NotNull(message = "A observação não pode ser nula")
    @Size(max = 100, message = "A observação não pode ter mais de 100 caracteres")
    @Column(name = "dsObservacao", nullable = false, length = 100)
    private String observacao;

    @NotNull(message = "O nível de prioridade não pode ser nulo")
    @Size(min = 3, max = 5, message = "O nível de prioridade deve ter entre 3 e 5 caracteres")
    @Column(name = "stNivelPrioridade", nullable = false, length = 5)
    private String prioridade;

    @NotNull(message = "A data de criação não pode ser nula")
    @PastOrPresent(message = "A data de criação não pode ser no futuro")
    @Column(name = "dtAlerta", nullable = false)
    private LocalDate dataCriacao;

    @ManyToOne
    @JoinColumn(name = "cdAparelho", referencedColumnName = "cdAparelho", nullable = false)
    private Aparelho aparelho;

}
