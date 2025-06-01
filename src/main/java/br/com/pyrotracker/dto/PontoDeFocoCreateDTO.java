package br.com.pyrotracker.dto;

import lombok.*;

@Getter
@Setter
public class PontoDeFocoCreateDTO {

    private Double latitude;
    private Double longitude;
    private Integer nivelFumaca;
    private String comentario;
    private String fotoUrl;
    private Long usuarioId;
}
