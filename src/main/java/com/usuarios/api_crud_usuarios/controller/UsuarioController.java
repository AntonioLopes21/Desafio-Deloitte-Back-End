package com.usuarios.api_crud_usuarios.controller;

import com.usuarios.api_crud_usuarios.model.dto.ProfissionaisEServicosDisponiveisDTO;
import com.usuarios.api_crud_usuarios.model.dto.ServicoDTO;
import com.usuarios.api_crud_usuarios.model.dto.UsuarioDTO;
import com.usuarios.api_crud_usuarios.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth/usuarios")
public class UsuarioController {
    private final UsuarioService usuarioService;

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

    @GetMapping("/cliente/listarServicos")
    public ResponseEntity<List<ServicoDTO>> listarServicos() {
        List<ServicoDTO> servicos = usuarioService.listarServicos()
                .stream()
                .map(servico -> {
                    ServicoDTO dto = new ServicoDTO();

                    dto.setId(servico.getId());
                    dto.setNome(servico.getNome());
                    dto.setDescricao(servico.getDescricao());
                    dto.setDuracaoEmMinutos(servico.getDuracaoEmMinutos());
                    dto.setProfissional(servico.getProfissional());
                    return dto;
                }).toList();


        return ResponseEntity.status(HttpStatus.OK).body(servicos);
    }

    @GetMapping("/cliente/listarProfissionais")
    public ResponseEntity<List<UsuarioDTO>> listarProfissionais() {

        List<UsuarioDTO> profissionais = usuarioService.listarProfissionais()
                .stream()
                .map(profissional -> {
                            UsuarioDTO dto = new UsuarioDTO();
                            dto.setId(profissional.getId());
                            dto.setNome(profissional.getNome());
                            dto.setEmail(profissional.getEmail());
                            dto.setTipoUsuario(profissional.getTipoUsuario());
                            dto = UsuarioDTO.toDTO(profissional);
                            return dto;
                        })
                .toList();
        return ResponseEntity.status(HttpStatus.OK).body(profissionais);
    }

    @GetMapping("/cliente/listarServicosEProfissionais")
    public ResponseEntity<List<ProfissionaisEServicosDisponiveisDTO>> listarServicosEProfissionais() {
        List<ProfissionaisEServicosDisponiveisDTO> profissionaisEServicos = usuarioService.listarServicosEProfissionais();
        return ResponseEntity.status(HttpStatus.OK).body(profissionaisEServicos);
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
