package br.com.pyrotracker.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class PontoDeFocoDTO {

    private Long id;
    private Double latitude;
    private Double longitude;
    private Integer nivelFumaca;
    private String comentario;
    private String fotoUrl;
    private Boolean valido;
    private String nomeUsuario;
}
