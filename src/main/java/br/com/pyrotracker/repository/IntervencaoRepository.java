package br.com.pyrotracker.repository;

import br.com.pyrotracker.domain.Intervencao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IntervencaoRepository extends JpaRepository<Intervencao, Long> {

    List<Intervencao> findByAlertaRelacionadoId(Long alertaId);
}
