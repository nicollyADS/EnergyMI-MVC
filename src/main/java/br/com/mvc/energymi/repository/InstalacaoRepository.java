package br.com.mvc.energymi.repository;

import br.com.mvc.energymi.model.Instalacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstalacaoRepository extends JpaRepository<Instalacao, Long> {
}
