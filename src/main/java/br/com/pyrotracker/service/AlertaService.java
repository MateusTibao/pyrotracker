package br.com.pyrotracker.service;

import br.com.pyrotracker.domain.Alerta;
import br.com.pyrotracker.domain.PontoDeFoco;
import br.com.pyrotracker.domain.StatusAlerta;
import br.com.pyrotracker.dto.AlertaCreateDTO;
import br.com.pyrotracker.dto.AlertaDTO;
import br.com.pyrotracker.exception.RecursoNaoEncontradoException;
import br.com.pyrotracker.repository.AlertaRepository;
import br.com.pyrotracker.repository.PontoDeFocoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AlertaService {

    @Autowired
    private AlertaRepository alertaRepository;

    @Autowired
    private PontoDeFocoRepository pontoDeFocoRepository;

    public List<Alerta> listarTodos() {
        return alertaRepository.findAll();
    }

    public Alerta cadastrar(AlertaCreateDTO dto) {
        List<PontoDeFoco> pontos = pontoDeFocoRepository.findAllById(dto.getIdsPontosRelacionados()).stream()
                .filter(PontoDeFoco::getValido)
                .toList();

        if (pontos.isEmpty()) {
            throw new RuntimeException("Não é possível criar um alerta sem pontos de foco válidos.");
        }

        Alerta alerta = new Alerta();
        alerta.setLatitude(dto.getLatitude());
        alerta.setLongitude(dto.getLongitude());
        alerta.setCriticidade(dto.getCriticidade());
        alerta.setPontosRelacionados(pontos);

        return alertaRepository.save(alerta);
    }

    public Alerta atualizarStatus(Long id, StatusAlerta novoStatus) {
        Alerta alerta = alertaRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Alerta não encontrado com ID: " + id));

        alerta.setStatus(novoStatus);

        if (novoStatus == StatusAlerta.ENCERRADO) {
            alerta.setDataEncerramento(LocalDateTime.now());
        }

        return alertaRepository.save(alerta);
    }


    public AlertaDTO toDTO(Alerta alerta) {
        List<Long> idsPontos = alerta.getPontosRelacionados().stream()
                .map(PontoDeFoco::getId)
                .toList();

        return new AlertaDTO(
                alerta.getId(),
                alerta.getLatitude(),
                alerta.getLongitude(),
                alerta.getCriticidade(),
                alerta.getDataCriacao(),
                idsPontos,
                alerta.getStatus(),
                alerta.getDataEncerramento()
        );
    }
}

