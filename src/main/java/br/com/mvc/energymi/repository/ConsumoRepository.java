package br.com.mvc.energymi.repository;

import br.com.mvc.energymi.model.Consumo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsumoRepository extends JpaRepository<Consumo, Long> {
}
