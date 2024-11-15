package br.com.mvc.energymi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @NotNull(message = "O estado não pode ser nulo")
    @Size(min = 2, max = 50, message = "O estado deve ter entre 2 e 50 caracteres")
    @Column(name = "nmEstado", nullable = false, length = 50)
    private String estado;

    @NotNull(message = "A cidade não pode ser nula")
    @Size(min = 2, max = 50, message = "A cidade deve ter entre 2 e 50 caracteres")
    @Column(name = "nmCidade", nullable = false, length = 50)
    private String cidade;

    @NotNull(message = "O bairro não pode ser nulo")
    @Size(min = 2, max = 50, message = "O bairro deve ter entre 2 e 50 caracteres")
    @Column(name = "nmBairro", nullable = false, length = 50)
    private String bairro;

    @NotNull(message = "A rua não pode ser nula")
    @Size(min = 2, max = 50, message = "A rua deve ter entre 2 e 50 caracteres")
    @Column(name = "nmRua", nullable = false, length = 50)
    private String rua;

    @NotNull(message = "O número do endereço não pode ser nulo")
    @Min(value = 1, message = "O número do endereço deve ser maior que 0")
    @Column(name = "nrNumeroEndereco", nullable = false)
    private Integer numeroEndereco;

    @Size(max = 250, message = "As observações não podem ter mais de 250 caracteres")
    @Column(name = "dsObservacoes")
    private String observacoes;

    @OneToMany(mappedBy = "instalacao")
    private List<Aparelho> aparelhos;
}
