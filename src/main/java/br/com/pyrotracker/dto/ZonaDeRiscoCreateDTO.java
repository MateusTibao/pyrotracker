package br.com.pyrotracker.dto;

import br.com.pyrotracker.domain.NivelRisco;
import lombok.*;

import java.util.List;

@Getter
@Setter
public class ZonaDeRiscoCreateDTO {

    private String regiao;
    private NivelRisco nivelRisco;
    private String comentario;
    private List<Long> idsAlertasRelacionados;
}
