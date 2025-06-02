package com.usuarios.api_crud_usuarios.controller;

import com.usuarios.api_crud_usuarios.model.dto.DisponibilidadeDTO;
import com.usuarios.api_crud_usuarios.model.entity.Disponibilidade;
import com.usuarios.api_crud_usuarios.service.DisponibilidadeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth/disponibilidades")
@RequiredArgsConstructor
public class DisponibilidadeController {

    private final DisponibilidadeService disponibilidadeService;

    @GetMapping
    public ResponseEntity<List<DisponibilidadeDTO>> disponibilidadesDisponivei () {
        return ResponseEntity.status(HttpStatus.OK).body(disponibilidadeService.listarDisponibilidade());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DisponibilidadeDTO> buscarDisponibilidade(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(disponibilidadeService.verificarDisponibilidade(id));
    }

    @PostMapping
    public ResponseEntity<DisponibilidadeDTO> definirDisponibilidade (@RequestBody DisponibilidadeDTO disponibilidadeDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(disponibilidadeService.definirDisponibilidade(disponibilidadeDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DisponibilidadeDTO> editarDisponibilidade (@PathVariable Long id, @RequestBody DisponibilidadeDTO disponibilidadeDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(disponibilidadeService.atualizarDisponibilidade(disponibilidadeDTO, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirDisponibilidade (@PathVariable Long id) {
        disponibilidadeService.deletarDisponibilidade(id);
        return ResponseEntity.noContent().build();
    }
}
