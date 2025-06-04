package com.usuarios.api_crud_usuarios.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.usuarios.api_crud_usuarios.enums.StatusAgendamento;
import com.usuarios.api_crud_usuarios.model.entity.Agendamento;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Getter
@Setter
public class AgendamentoDTO {
    private Long id;
    private Long cliente;
    private Long profissional;
    private Long servico;
    private LocalDateTime dataHoraInicio;
    private LocalDateTime dataHoraFim;
    private StatusAgendamento status;

    public AgendamentoDTO(@JsonProperty("clienteId") Long cliente,@JsonProperty("profissionalId") Long profissional,@JsonProperty("servicoId") Long servico,@JsonProperty("dataHoraInicio") LocalDateTime dataHoraInicio,@JsonProperty("dataHoraFim") LocalDateTime dataHoraFim) {
        this.cliente = cliente;
        this.profissional = profissional;
        this.servico = servico;
        this.dataHoraInicio = dataHoraInicio;
        this.dataHoraFim = dataHoraFim;
    }

    public AgendamentoDTO(Agendamento agendamento) {
        this.id = agendamento.getId();
        this.cliente = agendamento.getCliente().getId();
        this.profissional = agendamento.getProfissional().getId();
        this.servico = agendamento.getServico().getId();
        this.dataHoraInicio = agendamento.getDataHoraInicio();
        this.dataHoraFim = agendamento.getDataHoraFim();
        this.status = agendamento.getStatus();

    }
}
