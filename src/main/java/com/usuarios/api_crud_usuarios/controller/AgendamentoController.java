package com.usuarios.api_crud_usuarios.controller;

import com.usuarios.api_crud_usuarios.enums.StatusAgendamento;
import com.usuarios.api_crud_usuarios.model.dto.AgendamentoDTO;
import com.usuarios.api_crud_usuarios.service.AgendamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/agendamentos")
public class AgendamentoController {

    @Autowired
    private AgendamentoService service;

    @PostMapping
    public ResponseEntity<AgendamentoDTO> criar(@RequestBody AgendamentoDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criarAgendamento(dto));
    }

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<AgendamentoDTO>> listarPorCliente(@PathVariable Long clienteId) {
        return ResponseEntity.ok(service.listarPorCliente(clienteId));
    }

    @GetMapping("/profissional/{profissionalId}")
    public ResponseEntity<List<AgendamentoDTO>> listarPorProfissional(@PathVariable Long profissionalId) {
        return ResponseEntity.ok(service.listarPorProfissional(profissionalId));
    }

    @GetMapping("/agenda/{profissionalId}")
    public List<AgendamentoDTO> getAgenda(
            @PathVariable Long profissionalId,
            @RequestParam("inicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
            @RequestParam("fim") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fim) {
        return service.listarAgendaPorPeriodo(profissionalId, inicio, fim);
    }

    @PutMapping("/{id}/cancelar")
    public ResponseEntity<Void> cancelar(@PathVariable Long id, @RequestParam StatusAgendamento motivo) {
        service.cancelarAgendamento(id, motivo);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/concluir")
    public ResponseEntity<Void> concluir(@PathVariable Long id) {
        service.concluirAgendamento(id);
        return ResponseEntity.noContent().build();
    }
}