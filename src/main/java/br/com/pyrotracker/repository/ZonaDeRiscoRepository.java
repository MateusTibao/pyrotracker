package br.com.pyrotracker.repository;

import br.com.pyrotracker.domain.NivelRisco;
import br.com.pyrotracker.domain.ZonaDeRisco;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ZonaDeRiscoRepository extends JpaRepository<ZonaDeRisco, Long> {
    List<ZonaDeRisco> findByNivelRisco(NivelRisco nivel);
}
