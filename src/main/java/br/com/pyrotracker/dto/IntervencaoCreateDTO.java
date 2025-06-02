package br.com.pyrotracker.dto;

import lombok.*;

@Getter
@Setter
public class IntervencaoCreateDTO {

    private String equipeResponsavel;
    private String descricaoOperacao;
    private Long alertaId;
}
