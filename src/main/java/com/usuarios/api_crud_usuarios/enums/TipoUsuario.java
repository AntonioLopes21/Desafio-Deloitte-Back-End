package com.usuarios.api_crud_usuarios.enums;

public enum TipoUsuario {
    CLIENTE ("CLIENTE"),
    PROFISSIONAL ("PROFISSIONAL");

    private String role;

     TipoUsuario(String role) {
        this.role = role;
    }

    public String getRole() {
         return role;
    }
}
