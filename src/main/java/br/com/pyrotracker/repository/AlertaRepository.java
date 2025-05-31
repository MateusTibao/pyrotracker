package br.com.pyrotracker.repository;

import br.com.pyrotracker.domain.Alerta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlertaRepository extends JpaRepository<Alerta, Long> {
    //List<Alerta> findByCriticidade(Criticidade criticidade);
}
