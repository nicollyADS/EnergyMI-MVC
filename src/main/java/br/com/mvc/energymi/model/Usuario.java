package br.com.mvc.energymi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="t_usuario")
@EntityListeners(AuditingEntityListener.class)
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "templates/usuario")
    @SequenceGenerator(name = "templates/usuario", sequenceName = "seq_mi_usuario", allocationSize = 1)
    @Column(name = "cdUsuario", length = 9)
    private Long id;

    @NotBlank
    @Column(name = "nmUsuario", length = 20, nullable = false, unique = true)
    private String username;

    @NotBlank
    @Size(min = 5)
    @Column(name = "dsSenha", length = 100, nullable = false)
    private String password;

    public Usuario(String username, String password) {
        this.username = username;
        this.password = password;
    }


}