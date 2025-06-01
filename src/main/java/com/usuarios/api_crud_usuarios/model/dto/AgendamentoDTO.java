package com.usuarios.api_crud_usuarios.model.dto;

import com.usuarios.api_crud_usuarios.enums.StatusAgendamento;
import com.usuarios.api_crud_usuarios.model.entity.Agendamento;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AgendamentoDTO {
    private Long id;
    private Long clienteId;
    private Long profissionalId;
    private Long servicoId;
    private LocalDateTime dataHoraInicio;
    private LocalDateTime dataHoraFim;
    private StatusAgendamento status;

    public AgendamentoDTO(Agendamento agendamento) {
        this.id = agendamento.getId();
        this.clienteId = agendamento.getCliente().getId();
        this.profissionalId = agendamento.getProfissional().getId();
        this.servicoId = agendamento.getServico().getId();
        this.dataHoraInicio = agendamento.getDataHoraInicio();
        this.dataHoraFim = agendamento.getDataHoraFim();
        this.status = agendamento.getStatus();

    }
}
