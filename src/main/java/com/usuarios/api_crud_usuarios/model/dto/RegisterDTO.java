package com.usuarios.api_crud_usuarios.model.dto;

import com.usuarios.api_crud_usuarios.enums.TipoUsuario;

public record RegisterDTO(Long id, String nome, String email, String senha, TipoUsuario tipoUsuario) {
}
