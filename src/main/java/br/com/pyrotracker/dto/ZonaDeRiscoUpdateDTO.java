package br.com.pyrotracker.dto;

import br.com.pyrotracker.domain.NivelRisco;
import lombok.*;

import java.util.List;

@Getter
@Setter
public class ZonaDeRiscoUpdateDTO {
    private NivelRisco nivelRisco;
    private List<Long> idsAlertasRelacionados;
}
