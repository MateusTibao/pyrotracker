package br.com.pyrotracker.repository;

import br.com.pyrotracker.domain.Alerta;
import br.com.pyrotracker.domain.Criticidade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlertaRepository extends JpaRepository<Alerta, Long> {
    List<Alerta> findByCriticidade(Criticidade criticidade);
}
