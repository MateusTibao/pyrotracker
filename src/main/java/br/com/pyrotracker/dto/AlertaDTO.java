package br.com.pyrotracker.dto;

import br.com.pyrotracker.domain.enums.Criticidade;
import br.com.pyrotracker.domain.enums.StatusAlerta;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class AlertaDTO {

    private Long id;
    private Double latitude;
    private Double longitude;
    private Criticidade criticidade;
    private LocalDateTime dataCriacao;
    private List<Long> idsPontosRelacionados;
    private StatusAlerta status;
    private LocalDateTime dataEncerramento;
}
