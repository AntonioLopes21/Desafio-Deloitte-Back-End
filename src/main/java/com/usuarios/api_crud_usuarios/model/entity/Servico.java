package com.usuarios.api_crud_usuarios.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@RequiredArgsConstructor
@Getter
@Setter
public class Servico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String descricao;
    private int duracaoEmMinutos;

    @ManyToOne
    @JoinColumn(name = "profissional_id", nullable = false)
    private Usuario profissional;
}
