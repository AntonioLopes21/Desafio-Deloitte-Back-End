package com.usuarios.api_crud_usuarios.model.entity;

import com.usuarios.api_crud_usuarios.enums.DiaDaSemana;
import com.usuarios.api_crud_usuarios.enums.TipoUsuario;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Entity
@RequiredArgsConstructor
@Getter
@Setter
public class Disponibilidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name= "profissional_id", nullable = false)
    private Usuario profissional;

    @ElementCollection(targetClass = DiaDaSemana.class)
    @Enumerated(EnumType.STRING)
    private List<DiaDaSemana> diaDaSemana;

    private LocalTime horaInicio;
    private LocalTime horaFim;
}
