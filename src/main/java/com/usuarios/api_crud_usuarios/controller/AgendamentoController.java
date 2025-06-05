package com.usuarios.api_crud_usuarios.controller;

import com.usuarios.api_crud_usuarios.enums.StatusAgendamento;
import com.usuarios.api_crud_usuarios.model.dto.AgendamentoDTO;
import com.usuarios.api_crud_usuarios.model.entity.Usuario;
import com.usuarios.api_crud_usuarios.service.AgendamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth/agendamentos")
public class AgendamentoController {

    @Autowired
    private AgendamentoService service;

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<AgendamentoDTO>> listarPorCliente(@PathVariable Long clienteId, @AuthenticationPrincipal Usuario usuario) {
        if(usuario == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(service.listarPorCliente(clienteId));
    }

    @GetMapping("/profissional/{profissionalId}")
    public ResponseEntity<List<AgendamentoDTO>> listarPorProfissional(@PathVariable Long profissionalId, @AuthenticationPrincipal Usuario usuario) {
        if(usuario == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(service.listarPorProfissional(profissionalId));
    }

    @PostMapping
    public ResponseEntity<AgendamentoDTO> criar(@RequestBody AgendamentoDTO dto, @AuthenticationPrincipal Usuario usuario) {
        if(usuario == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(service.criarAgendamento(dto));
    }



    @PutMapping("/{id}/cancelar")
    public ResponseEntity<Void> cancelar(@PathVariable Long id, @RequestParam StatusAgendamento motivo, @AuthenticationPrincipal Usuario usuario) {
        if(usuario == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        service.cancelarAgendamento(id, motivo);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/concluir")
    public ResponseEntity<Void> concluir(@PathVariable Long id, @AuthenticationPrincipal Usuario usuario) {
        if(usuario == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        service.concluirAgendamento(id);
        return ResponseEntity.noContent().build();
    }
}