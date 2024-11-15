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
@Table(name = "tb_recomendacao")
public class Recomendacao {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "templates/recomendacao")
    @SequenceGenerator(name = "templates/recomendacao", sequenceName = "seq_mi_recomendacao", allocationSize = 1)
    @Column(name = "cdRecomendacao", length = 10)
    private Long id;

    @Column(name = "dsObservacao", length = 250)
    private String observacao;

    @Column(name = "dtRecomendacao", nullable = false)
    private LocalDate data;

    @Column(name = "stRecomendacao", length = 50)
    private String status;

    @ManyToOne
    @JoinColumn(name = "cdAparelho", referencedColumnName = "cdAparelho", insertable = false, updatable = false)
    private Aparelho aparelho;
}