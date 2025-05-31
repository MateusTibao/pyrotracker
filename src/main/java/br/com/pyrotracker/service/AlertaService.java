package br.com.pyrotracker.service;

import br.com.pyrotracker.domain.Alerta;
import br.com.pyrotracker.domain.PontoDeFoco;
import br.com.pyrotracker.dto.AlertaCreateDTO;
import br.com.pyrotracker.dto.AlertaDTO;
import br.com.pyrotracker.repository.AlertaRepository;
import br.com.pyrotracker.repository.PontoDeFocoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        List<PontoDeFoco> pontos = pontoDeFocoRepository.findAllById(dto.getIdsPontosRelacionados());

        if (pontos.size() != dto.getIdsPontosRelacionados().size()) {
            throw new RuntimeException("Alguns pontos informados não foram encontrados.");
        }

        Alerta alerta = new Alerta();
        alerta.setLatitude(dto.getLatitude());
        alerta.setLongitude(dto.getLongitude());
        alerta.setCriticidade(dto.getCriticidade());
        alerta.setPontosRelacionados(pontos);

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

