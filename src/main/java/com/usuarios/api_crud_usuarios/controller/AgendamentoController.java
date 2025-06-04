package com.usuarios.api_crud_usuarios.controller;

import com.usuarios.api_crud_usuarios.enums.StatusAgendamento;
import com.usuarios.api_crud_usuarios.model.dto.AgendamentoDTO;
import com.usuarios.api_crud_usuarios.service.AgendamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/auth/agendamentos")
public class AgendamentoController {

    @Autowired
    private AgendamentoService service;

    @PostMapping
    public AgendamentoDTO criar(@RequestBody AgendamentoDTO dto) {
        return service.criarAgendamento(dto);
    }

    @GetMapping("/cliente/{clienteId}")
    public List<AgendamentoDTO> listarPorCliente(@PathVariable Long clienteId) {
        return service.listarPorCliente(clienteId);
    }

    @GetMapping("/profissional/{profissionalId}")
    public List<AgendamentoDTO> listarPorProfissional(@PathVariable Long profissionalId) {
        return service.listarPorProfissional(profissionalId);
    }
    @GetMapping("/agenda/{profissionalId}")
    public List<AgendamentoDTO> getAgenda(
            @PathVariable Long profissionalId,
            @RequestParam("inicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
            @RequestParam("fim") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fim) {
        return service.listarAgendaPorPeriodo(profissionalId, inicio, fim);
    }


    @PutMapping("/{id}/cancelar")
    public void cancelar(@PathVariable Long id, @RequestParam StatusAgendamento motivo) {
        service.cancelarAgendamento(id, motivo);
    }

    @PutMapping("/{id}/concluir")
    public void concluir(@PathVariable Long id) {
        service.concluirAgendamento(id);
    }
}
