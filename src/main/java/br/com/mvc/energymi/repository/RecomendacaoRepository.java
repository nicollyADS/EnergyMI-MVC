package br.com.mvc.energymi.repository;

import br.com.mvc.energymi.model.Recomendacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecomendacaoRepository extends JpaRepository<Recomendacao, Long> {
}
