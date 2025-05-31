package br.com.pyrotracker.service;

import br.com.pyrotracker.domain.Alerta;
import br.com.pyrotracker.domain.ZonaDeRisco;
import br.com.pyrotracker.dto.ZonaDeRiscoCreateDTO;
import br.com.pyrotracker.dto.ZonaDeRiscoDTO;
import br.com.pyrotracker.repository.AlertaRepository;
import br.com.pyrotracker.repository.ZonaDeRiscoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZonaDeRiscoService {

    @Autowired
    private ZonaDeRiscoRepository zonaDeRiscoRepository;

    @Autowired
    private AlertaRepository alertaRepository;

    public List<ZonaDeRisco> listarTodos() {
        return zonaDeRiscoRepository.findAll();
    }

    public ZonaDeRisco cadastrar(ZonaDeRiscoCreateDTO dto) {
        List<Alerta> alertas = alertaRepository.findAllById(dto.getIdsAlertasRelacionados());

        if (alertas.size() != dto.getIdsAlertasRelacionados().size()) {
            throw new RuntimeException("Alguns alertas informados não foram encontrados.");
        }

        ZonaDeRisco zona = new ZonaDeRisco();
        zona.setRegiao(dto.getRegiao());
        zona.setNivelRisco(dto.getNivelRisco());
        zona.setComentario(dto.getComentario());
        zona.setAlertas(alertas);

        return zonaDeRiscoRepository.save(zona);
    }

    public ZonaDeRiscoDTO toDTO(ZonaDeRisco zona) {
        List<Long> idsAlertas = zona.getAlertas().stream()
                .map(Alerta::getId)
                .toList();

        return new ZonaDeRiscoDTO(
                zona.getId(),
                zona.getRegiao(),
                zona.getNivelRisco(),
                zona.getComentario(),
                zona.getDataAtualizacao(),
                idsAlertas
        );
    }
}
