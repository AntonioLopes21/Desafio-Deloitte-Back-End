package com.usuarios.api_crud_usuarios.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
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

    @NotBlank(message = "O nome não pode ser vazio.")
    private String nome;

    @Size(min = 10, max = 100, message = "A descrição deve ter entre 10 e 100 caracteres.")
    private String descricao;
    @Positive(message = "A duração precisa ser um valor positivo.")
    private int duracaoEmMinutos;

    @ManyToOne
    @JoinColumn(name = "profissional_id", nullable = false)
    @JsonProperty("profissional")
    private Usuario profissional;
}
