package br.com.pyrotracker.service;

import br.com.pyrotracker.domain.PontoDeFoco;
import br.com.pyrotracker.domain.Usuario;
import br.com.pyrotracker.dto.PontoDeFocoCreateDTO;
import br.com.pyrotracker.dto.PontoDeFocoDTO;
import br.com.pyrotracker.exception.RecursoNaoEncontradoException;
import br.com.pyrotracker.repository.PontoDeFocoRepository;
import br.com.pyrotracker.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PontoDeFocoService {

    @Autowired
    private PontoDeFocoRepository pontoDeFocoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<PontoDeFoco> listarTodos() {
        return pontoDeFocoRepository.findAll();
    }

    public PontoDeFoco cadastrar(PontoDeFocoCreateDTO dto) {
        String email = ((UserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal()).getUsername();

        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado com e-mail: " + email));

        PontoDeFoco ponto = new PontoDeFoco();
        ponto.setLatitude(dto.getLatitude());
        ponto.setLongitude(dto.getLongitude());
        ponto.setNivelFumaca(dto.getNivelFumaca());
        ponto.setComentario(dto.getComentario());
        ponto.setFotoUrl(dto.getFotoUrl());
        ponto.setValido(false);
        ponto.setUsuario(usuario);

        return pontoDeFocoRepository.save(ponto);
    }

    public void atualizarValidade(Long id, boolean novoValor) {
        PontoDeFoco foco = pontoDeFocoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Ponto de foco não encontrado com ID: " + id));

        foco.setValido(novoValor);
        pontoDeFocoRepository.save(foco);
    }

    public List<PontoDeFocoDTO> listarPorValidade(Boolean valido) {
        List<PontoDeFoco> focos;

        if (valido == null) {
            focos = pontoDeFocoRepository.findAll();
        } else {
            focos = pontoDeFocoRepository.findByValido(valido);
        }

        return focos.stream()
                .map(this::toDTO)
                .toList();
    }


    public PontoDeFocoDTO toDTO(PontoDeFoco ponto) {
        return new PontoDeFocoDTO(
                ponto.getId(),
                ponto.getLatitude(),
                ponto.getLongitude(),
                ponto.getNivelFumaca(),
                ponto.getComentario(),
                ponto.getFotoUrl(),
                ponto.getValido(),
                ponto.getUsuario().getNome()
        );
    }

}
