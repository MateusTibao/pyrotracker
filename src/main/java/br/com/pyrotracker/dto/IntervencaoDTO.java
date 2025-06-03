package br.com.pyrotracker.dto;

import br.com.pyrotracker.domain.enums.StatusIntervencao;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class IntervencaoDTO {

    private Long id;
    private String equipeResponsavel;
    private String descricaoOperacao;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
    private StatusIntervencao status;
    private Long alertaId;
}
