package br.com.pyrotracker.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class UsuarioDTO {

    private Long id;
    private String nome;
    private String email;
    private int reputacao;
}
