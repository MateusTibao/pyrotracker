package br.com.pyrotracker.controller;

import br.com.pyrotracker.domain.Intervencao;
import br.com.pyrotracker.dto.IntervencaoCreateDTO;
import br.com.pyrotracker.dto.IntervencaoDTO;
import br.com.pyrotracker.dto.IntervencaoStatusDTO;
import br.com.pyrotracker.service.IntervencaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/intervencoes")
public class IntervencaoController {

    @Autowired
    private IntervencaoService intervencaoService;

    @GetMapping
    public ResponseEntity<List<IntervencaoDTO>> listarTodas() {
        List<Intervencao> intervencoes = intervencaoService.listarTodas();
        List<IntervencaoDTO> dtos = intervencoes.stream()
                .map(intervencaoService::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @PostMapping
    public ResponseEntity<IntervencaoDTO> cadastrar(@RequestBody IntervencaoCreateDTO dto) {
        Intervencao nova = intervencaoService.cadastrar(dto);
        return ResponseEntity.status(201).body(intervencaoService.toDTO(nova));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<IntervencaoDTO> atualizarStatus(
            @PathVariable Long id,
            @RequestBody IntervencaoStatusDTO dto) {

        Intervencao atualizada = intervencaoService.atualizarStatus(id, dto.getStatus());
        return ResponseEntity.ok(intervencaoService.toDTO(atualizada));
    }
}
