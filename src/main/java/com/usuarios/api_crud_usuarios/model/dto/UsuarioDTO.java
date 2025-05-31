package com.usuarios.api_crud_usuarios.model.dto;

import com.usuarios.api_crud_usuarios.enums.TipoUsuario;
import com.usuarios.api_crud_usuarios.model.entity.Usuario;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class UsuarioDTO {

    private Long id;
    private String nome;
    private String email;
    private String senha;
    private TipoUsuario tipoUsuario;

    public UsuarioDTO(Usuario usuario) {
        this.setId(usuario.getId());
        this.setNome(usuario.getNome());
        this.setEmail(usuario.getEmail());
        this.setSenha(usuario.getSenha());
        this.setTipoUsuario(usuario.getTipoUsuario());
    }

    public static Usuario toEntity(UsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario();
        usuario.setNome(usuarioDTO.getNome());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setSenha(usuarioDTO.getSenha());
        usuario.setTipoUsuario(usuarioDTO.getTipoUsuario());
        return usuario;
    }
}
