package br.com.mvc.energymi.repository;

import br.com.mvc.energymi.model.Aparelho;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AparelhoRepository extends JpaRepository<Aparelho, Long> {
}
