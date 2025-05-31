package br.com.pyrotracker.dto;

import br.com.pyrotracker.domain.Criticidade;
import lombok.*;

import java.util.List;

@Getter
@Setter
public class AlertaCreateDTO {

    private Double latitude;
    private Double longitude;
    private Criticidade criticidade;
    private List<Long> idsPontosRelacionados;
}
