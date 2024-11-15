package br.com.mvc.energymi.repository;

import br.com.mvc.energymi.model.Aparelho;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AparelhoRepository extends JpaRepository<Aparelho, Long> {
    List<Aparelho> findByNomeContainingIgnoreCase(String nome);

}
