package com.usuarios.api_crud_usuarios.model.dto;

import com.usuarios.api_crud_usuarios.enums.TipoUsuario;

public record RegisterDTO(String email, String senha, TipoUsuario role) {
}
