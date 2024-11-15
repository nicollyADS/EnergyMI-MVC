package br.com.mvc.energymi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tb_instalacao")
public class Instalacao {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "templates/instalacao")
    @SequenceGenerator(name = "templates/instalacao", sequenceName = "seq_emi_instalacao", allocationSize = 1)
    @Column(name = "cdInstalacao", length = 10)
    private Long id;

    @Column(name = "nmEstado", nullable = false, length = 50)
    private String estado;

    @Column(name = "nmCidade", nullable = false, length = 50)
    private String cidade;

    @Column(name = "nmBairro", nullable = false, length = 50)
    private String bairro;

    @Column(name = "nmRua", nullable = false, length = 50)
    private String rua;

    @Column(name = "nrNumeroEndereco", nullable = false)
    private Integer numeroEndereco;

    @Column(name = "dsObservacoes")
    private String observacoes;

    @OneToMany(mappedBy = "instalacao")
    private List<Aparelho> aparelhos;

}