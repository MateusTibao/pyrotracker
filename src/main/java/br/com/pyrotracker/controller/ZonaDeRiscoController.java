package br.com.pyrotracker.controller;

import br.com.pyrotracker.domain.ZonaDeRisco;
import br.com.pyrotracker.dto.ZonaDeRiscoCreateDTO;
import br.com.pyrotracker.dto.ZonaDeRiscoDTO;
import br.com.pyrotracker.dto.ZonaDeRiscoUpdateDTO;
import br.com.pyrotracker.service.ZonaDeRiscoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/zonas")
public class ZonaDeRiscoController {

    @Autowired
    private ZonaDeRiscoService zonaDeRiscoService;

    @GetMapping
    public ResponseEntity<List<ZonaDeRiscoDTO>> listarTodos() {
        List<ZonaDeRisco> zonas = zonaDeRiscoService.listarTodos();
        List<ZonaDeRiscoDTO> dtos = zonas.stream()
                .map(zonaDeRiscoService::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @PostMapping
    public ResponseEntity<ZonaDeRiscoDTO> cadastrar(@RequestBody ZonaDeRiscoCreateDTO dto) {
        ZonaDeRisco nova = zonaDeRiscoService.cadastrar(dto);
        return ResponseEntity.status(201).body(zonaDeRiscoService.toDTO(nova));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ZonaDeRiscoDTO> atualizarZona(@PathVariable Long id, @RequestBody ZonaDeRiscoUpdateDTO dto) {
        ZonaDeRisco zonaAtualizada = zonaDeRiscoService.atualizarZona(id, dto);
        return ResponseEntity.ok(zonaDeRiscoService.toDTO(zonaAtualizada));
    }
}
