package br.com.pyrotracker.dto;

import lombok.*;

@Getter
@Setter
public class AuthRequestDTO {
    private String email;
    private String senha;
}
