package br.com.mvc.energymi.repository;

import br.com.mvc.energymi.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {

    Role findByName(String nome);

}

