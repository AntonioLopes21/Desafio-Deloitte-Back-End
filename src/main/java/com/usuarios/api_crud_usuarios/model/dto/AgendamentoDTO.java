package com.usuarios.api_crud_usuarios.model.dto;

import com.usuarios.api_crud_usuarios.enums.StatusAgendamento;
import com.usuarios.api_crud_usuarios.model.entity.Agendamento;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class AgendamentoDTO {
    private StatusAgendamento statusAgendamento;
    private Long id;

    public AgendamentoDTO(Agendamento agendamento) {
        this.setId(agendamento.getId());
        this.setStatusAgendamento(agendamento.getStatusDoAgendamento());
    }
}
