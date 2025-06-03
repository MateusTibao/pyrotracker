package br.com.pyrotracker.service;

import br.com.pyrotracker.domain.Alerta;
import br.com.pyrotracker.domain.Intervencao;
import br.com.pyrotracker.domain.enums.StatusAlerta;
import br.com.pyrotracker.domain.enums.StatusIntervencao;
import br.com.pyrotracker.dto.IntervencaoCreateDTO;
import br.com.pyrotracker.dto.IntervencaoDTO;
import br.com.pyrotracker.exception.RecursoNaoEncontradoException;
import br.com.pyrotracker.repository.AlertaRepository;
import br.com.pyrotracker.repository.IntervencaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class IntervencaoService {

    @Autowired
    private IntervencaoRepository intervencaoRepository;

    @Autowired
    private AlertaRepository alertaRepository;

    public List<Intervencao> listarTodas() {
        return intervencaoRepository.findAll();
    }

    public Intervencao atualizarStatus(Long id, StatusIntervencao novoStatus) {
        Intervencao intervencao = intervencaoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Intervenção não encontrado com ID: " + id));

        intervencao.setStatus(novoStatus);

        if (novoStatus.name().startsWith("FINALIZADA")) {
            intervencao.setDataFim(LocalDateTime.now());

            if (intervencao.getAlertaRelacionado() != null && novoStatus == StatusIntervencao.FINALIZADA_COM_SUCESSO) {
                Long alertaId = intervencao.getAlertaRelacionado().getId();
                List<Intervencao> relacionadas = intervencaoRepository.findByAlertaRelacionadoId(alertaId);

                boolean todasComSucesso = relacionadas.stream()
                        .allMatch(i -> i.getStatus() == StatusIntervencao.FINALIZADA_COM_SUCESSO);

                if (todasComSucesso) {
                    alertaRepository.findById(alertaId).ifPresent(alerta -> {
                        alerta.setStatus(StatusAlerta.CONTIDO);
                        alerta.setDataEncerramento(LocalDateTime.now());
                        alertaRepository.save(alerta);
                    });
                }
            }
        }

        return intervencaoRepository.save(intervencao);
    }



    public Intervencao cadastrar(IntervencaoCreateDTO dto) {
        Alerta alerta = alertaRepository.findById(dto.getAlertaId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Alerta não encontrado com ID: " + dto.getAlertaId()));

        Intervencao intervencao = new Intervencao();
        intervencao.setEquipeResponsavel(dto.getEquipeResponsavel());
        intervencao.setDescricaoOperacao(dto.getDescricaoOperacao());
        intervencao.setStatus(StatusIntervencao.AGUARDANDO);
        intervencao.setDataInicio(LocalDateTime.now());
        intervencao.setAlertaRelacionado(alerta);

        return intervencaoRepository.save(intervencao);
    }

    public IntervencaoDTO toDTO(Intervencao intervencao) {
        return new IntervencaoDTO(
                intervencao.getId(),
                intervencao.getEquipeResponsavel(),
                intervencao.getDescricaoOperacao(),
                intervencao.getDataInicio(),
                intervencao.getDataFim(),
                intervencao.getStatus(),
                intervencao.getAlertaRelacionado().getId()
        );
    }
}
