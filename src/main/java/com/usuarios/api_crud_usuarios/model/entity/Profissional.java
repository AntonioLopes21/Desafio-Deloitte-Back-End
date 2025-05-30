package com.usuarios.api_crud_usuarios.model.entity;

import com.usuarios.api_crud_usuarios.enums.EspecialidadeProfissional;
import com.usuarios.api_crud_usuarios.enums.StatusAgendamento;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

public class Profissional {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String nome;

    @NotBlank
    private EspecialidadeProfissional especialidadeProfissional;

}
