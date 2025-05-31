package br.com.pyrotracker.dto;

import br.com.pyrotracker.domain.StatusIntervencao;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IntervencaoStatusDTO {
    private StatusIntervencao status;
}
