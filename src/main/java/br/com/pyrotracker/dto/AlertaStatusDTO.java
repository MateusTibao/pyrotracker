package br.com.pyrotracker.dto;

import br.com.pyrotracker.domain.enums.StatusAlerta;
import lombok.*;

@Getter
@Setter
public class AlertaStatusDTO {
    private StatusAlerta status;
}
