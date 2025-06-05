package com.usuarios.api_crud_usuarios.model.dto;

import com.usuarios.api_crud_usuarios.enums.DiaDaSemana;
import com.usuarios.api_crud_usuarios.model.entity.Disponibilidade;
import com.usuarios.api_crud_usuarios.model.entity.Usuario;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.util.List;

@RequiredArgsConstructor
@Getter
@Setter
public class DisponibilidadeDTO {
    private Long id;
    private Usuario profissional;
    private List<DiaDaSemana> diaDaSemana;
    private LocalTime horaInicio;
    private LocalTime horaFim;

    public  DisponibilidadeDTO(Disponibilidade disponibilidade) {
        this.setId(disponibilidade.getId());
        this.setProfissional(disponibilidade.getProfissional());
        this.setDiaDaSemana(disponibilidade.getDiaDaSemana());
        this.setHoraInicio(disponibilidade.getHoraInicio());
        this.setHoraFim(disponibilidade.getHoraFim());
    }

    public static Disponibilidade toEntity(DisponibilidadeDTO disponibilidadeDTO) {
        Disponibilidade disponibilidade = new Disponibilidade();
        Usuario profissional = new Usuario();
        profissional.setId(disponibilidadeDTO.getProfissional().getId());

        disponibilidade.setDiaDaSemana(disponibilidadeDTO.getDiaDaSemana());
        disponibilidade.setHoraInicio(disponibilidadeDTO.getHoraInicio());
        disponibilidade.setHoraFim(disponibilidadeDTO.getHoraFim());
        return disponibilidade;

    }
}

