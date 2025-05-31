package br.com.pyrotracker.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Intervencao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String equipeResponsavel;

    private String descricaoOperacao;

    private LocalDateTime dataInicio = LocalDateTime.now();

    private LocalDateTime dataFim;

    @Enumerated(EnumType.STRING)
    private StatusIntervencao status = StatusIntervencao.AGUARDANDO;

    @ManyToOne
    private Alerta alertaRelacionado;
}
