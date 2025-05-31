package br.com.pyrotracker.service;

import br.com.pyrotracker.domain.PontoDeFoco;
import br.com.pyrotracker.domain.Usuario;
import br.com.pyrotracker.dto.PontoDeFocoCreateDTO;
import br.com.pyrotracker.dto.PontoDeFocoDTO;
import br.com.pyrotracker.repository.PontoDeFocoRepository;
import br.com.pyrotracker.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com ID: " + dto.getUsuarioId()));

        PontoDeFoco ponto = new PontoDeFoco();
        ponto.setLatitude(dto.getLatitude());
        ponto.setLongitude(dto.getLongitude());
        ponto.setNivelFumaca(dto.getNivelFumaca());
        ponto.setComentario(dto.getComentario());
        ponto.setFotoUrl(dto.getFotoUrl());
        ponto.setValido(false);  // começa como inválido
        ponto.setUsuario(usuario);

        return pontoDeFocoRepository.save(ponto);
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
