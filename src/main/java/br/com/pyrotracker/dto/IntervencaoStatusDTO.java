package br.com.pyrotracker.dto;

import br.com.pyrotracker.domain.enums.StatusIntervencao;
import lombok.*;

@Getter
@Setter
public class IntervencaoStatusDTO {
    private StatusIntervencao status;
}
