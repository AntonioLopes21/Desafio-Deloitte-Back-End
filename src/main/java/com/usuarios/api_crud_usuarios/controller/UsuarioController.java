package com.usuarios.api_crud_usuarios.controller;

import com.usuarios.api_crud_usuarios.model.dto.UsuarioDTO;
import com.usuarios.api_crud_usuarios.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> listar() {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.listarUsuarios());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<UsuarioDTO>> listarPorId(@PathVariable Long id) {
        if(usuarioService.listarUsuarioPorId(id).isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(usuarioService.listarUsuarioPorId(id));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> criar(@RequestBody UsuarioDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.criarUsuario(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> editar(@PathVariable Long id,@RequestBody UsuarioDTO dto) {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.editarUsuario(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        usuarioService.excluirUsuario(id);
        return ResponseEntity.noContent().build();
    }
}
