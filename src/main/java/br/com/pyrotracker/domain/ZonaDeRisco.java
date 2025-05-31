package br.com.pyrotracker.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ZonaDeRisco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String regiao;

    @Enumerated(EnumType.STRING)
    private NivelRisco nivelRisco;

    private String comentario;

    private LocalDateTime dataAtualizacao = LocalDateTime.now();

    @ManyToMany
    private List<Alerta> alertas;
}
