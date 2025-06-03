package br.com.pyrotracker.dto;

import br.com.pyrotracker.domain.enums.Role;
import lombok.*;

@Getter
@Setter
public class UsuarioCreateDTO {
    private String nome;
    private String email;
    private String senha;
    private Role role;
}
