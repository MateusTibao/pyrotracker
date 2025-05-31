package br.com.pyrotracker.controller;

import br.com.pyrotracker.domain.Alerta;
import br.com.pyrotracker.dto.AlertaCreateDTO;
import br.com.pyrotracker.dto.AlertaDTO;
import br.com.pyrotracker.service.AlertaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/alertas")
public class AlertaController {

    @Autowired
    private AlertaService alertaService;

    @GetMapping
    public ResponseEntity<List<AlertaDTO>> listarTodos() {
        List<Alerta> alertas = alertaService.listarTodos();
        List<AlertaDTO> dtos = alertas.stream()
                .map(alertaService::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @PostMapping
    public ResponseEntity<AlertaDTO> cadastrar(@RequestBody AlertaCreateDTO dto) {
        Alerta novo = alertaService.cadastrar(dto);
        return ResponseEntity.status(201).body(alertaService.toDTO(novo));
    }
}
