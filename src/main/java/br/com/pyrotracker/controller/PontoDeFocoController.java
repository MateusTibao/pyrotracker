package br.com.pyrotracker.controller;

import br.com.pyrotracker.domain.PontoDeFoco;
import br.com.pyrotracker.dto.AtualizarValidadeDTO;
import br.com.pyrotracker.dto.PontoDeFocoCreateDTO;
import br.com.pyrotracker.dto.PontoDeFocoDTO;
import br.com.pyrotracker.service.PontoDeFocoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/focos")
public class PontoDeFocoController {

    @Autowired
    private PontoDeFocoService pontoDeFocoService;

    @GetMapping
    public ResponseEntity<List<PontoDeFocoDTO>> listarTodos(@RequestParam(required = false) Boolean valido) {
        List<PontoDeFocoDTO> dtos = pontoDeFocoService.listarPorValidade(valido);
        return ResponseEntity.ok(dtos);
    }


    @PreAuthorize("hasAnyRole('ADMIN', 'AGENTE')")
    @PutMapping("/{id}/validade")
    public ResponseEntity<Void> atualizarValidade(@PathVariable Long id,
                                                  @RequestBody AtualizarValidadeDTO dto) {
        pontoDeFocoService.atualizarValidade(id, dto.isValido());
        return ResponseEntity.noContent().build();
    }


    @PostMapping
    public ResponseEntity<PontoDeFocoDTO> cadastrar(@RequestBody PontoDeFocoCreateDTO dto) {
        PontoDeFoco novo = pontoDeFocoService.cadastrar(dto);
        return ResponseEntity.status(201).body(pontoDeFocoService.toDTO(novo));
    }
}
