package br.com.mvc.energymi.model;

import jakarta.persistence.*;
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

    @Column(name = "dsObservacao", nullable = false, length = 100)
    private String observacao;

    @Column(name = "stNivelPrioridade", nullable = false, length = 5)
    private String prioridade;

    @Column(name = "dtAlerta", nullable = false)
    private LocalDate dataCriacao;

    @ManyToOne
    @JoinColumn(name = "cdAparelho", referencedColumnName = "cdAparelho", insertable = false, updatable = false)
    private Aparelho aparelho;


}