package br.com.pyrotracker.domain;

import br.com.pyrotracker.domain.enums.Criticidade;
import br.com.pyrotracker.domain.enums.StatusAlerta;
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
public class Alerta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double latitude;

    private Double longitude;

    @Enumerated(EnumType.STRING)
    private Criticidade criticidade;

    @Enumerated(EnumType.STRING)
    private StatusAlerta status = StatusAlerta.ATIVO;

    private LocalDateTime dataCriacao = LocalDateTime.now();

    private LocalDateTime dataEncerramento;

    @OneToMany
    private List<PontoDeFoco> pontosRelacionados;
}
