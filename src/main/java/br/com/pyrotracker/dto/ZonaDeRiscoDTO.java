package br.com.pyrotracker.dto;

import br.com.pyrotracker.domain.enums.NivelRisco;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ZonaDeRiscoDTO {

    private Long id;
    private String regiao;
    private NivelRisco nivelRisco;
    private String comentario;
    private LocalDateTime dataAtualizacao;
    private List<Long> idsAlertasRelacionados;
}
