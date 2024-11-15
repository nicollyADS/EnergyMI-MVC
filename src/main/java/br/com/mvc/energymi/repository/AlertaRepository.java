package br.com.mvc.energymi.repository;

import br.com.mvc.energymi.model.Alerta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlertaRepository extends JpaRepository<Alerta, Long> {
}
