package com.usuarios.api_crud_usuarios.controller;

import com.usuarios.api_crud_usuarios.model.dto.ServicoDTO;
import com.usuarios.api_crud_usuarios.service.ServicoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth/servicos")
@RequiredArgsConstructor
public class ServicoController {

    private final ServicoService servicoService;

    @GetMapping
    public ResponseEntity<List<ServicoDTO>> servicosDisponiveis() {
        return ResponseEntity.status(HttpStatus.OK).body(servicoService.listarServico());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServicoDTO> buscarServico (@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(servicoService.verificarServico(id));
    }

    @PostMapping
    public ResponseEntity<ServicoDTO> cadastrarServico(@RequestBody @Valid ServicoDTO servicoDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(servicoService.cadastrarServico(servicoDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServicoDTO> editarServico(@PathVariable Long id, @RequestBody @Valid ServicoDTO servicoDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(servicoService.atualizarServico(servicoDTO, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirServico(@PathVariable Long id) {
         servicoService.deletarServico(id);
         return ResponseEntity.noContent().build();
    }




}
