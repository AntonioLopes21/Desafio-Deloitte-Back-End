package com.usuarios.api_crud_usuarios.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.usuarios.api_crud_usuarios.enums.TipoUsuario;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;



@Entity
@RequiredArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties({"password", "enabled", "accountNonExpired", "credentialsNonExpired", "accountNonLocked", "authorities", "username"})
public class Usuario  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    @JsonIgnore
    private String senha;

    @Enumerated(EnumType.STRING)
    private TipoUsuario tipoUsuario;

    public Usuario(Long id,String nome, String email, String senha, TipoUsuario role) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.tipoUsuario = role;
    }
}
