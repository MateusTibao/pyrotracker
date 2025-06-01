package br.com.pyrotracker.repository;

import br.com.pyrotracker.domain.PontoDeFoco;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PontoDeFocoRepository extends JpaRepository<PontoDeFoco, Long> {

    List<PontoDeFoco> findByUsuarioId(Long usuarioId);

    List<PontoDeFoco> findByValido(boolean valido);

}
