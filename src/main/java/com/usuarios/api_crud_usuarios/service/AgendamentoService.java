package com.usuarios.api_crud_usuarios.service;

import com.usuarios.api_crud_usuarios.enums.StatusAgendamento;
import com.usuarios.api_crud_usuarios.model.dto.AgendamentoDTO;
import com.usuarios.api_crud_usuarios.model.entity.Agendamento;
import com.usuarios.api_crud_usuarios.model.entity.Servico;
import com.usuarios.api_crud_usuarios.model.entity.Usuario;
import com.usuarios.api_crud_usuarios.repository.AgendamentoRepository;
import com.usuarios.api_crud_usuarios.repository.ServicoRepository;
import com.usuarios.api_crud_usuarios.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AgendamentoService {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ServicoRepository servicoRepository;

    public AgendamentoDTO criarAgendamento(AgendamentoDTO dto) {
        Usuario cliente = usuarioRepository.findById(dto.getClienteId()).orElseThrow();
        Usuario profissional = usuarioRepository.findById(dto.getProfissionalId()).orElseThrow();
        Servico servico = servicoRepository.findById(dto.getServicoId()).orElseThrow();

        boolean conflito = agendamentoRepository.existsByProfissionalIdAndDataHoraInicioLessThanAndDataHoraFimGreaterThan(
                dto.getProfissionalId(), dto.getDataHoraFim(), dto.getDataHoraInicio());

        if (conflito) {
            throw new RuntimeException("Horário já ocupado para o profissional.");
        }

        Agendamento agendamento = new Agendamento();
        agendamento.setCliente(cliente);
        agendamento.setProfissional(profissional);
        agendamento.setServico(servico);
        agendamento.setDataHoraInicio(dto.getDataHoraInicio());
        agendamento.setDataHoraFim(dto.getDataHoraFim());
        agendamento.setStatus(StatusAgendamento.AGENDADO);

        return new AgendamentoDTO(agendamentoRepository.save(agendamento));
    }

    public List<AgendamentoDTO> listarPorCliente(Long clienteId) {
        return agendamentoRepository.findByClienteId(clienteId)
                .stream().map(AgendamentoDTO::new).collect(Collectors.toList());
    }

    public List<AgendamentoDTO> listarPorProfissional(Long profissionalId) {
        return agendamentoRepository.findByProfissionalId(profissionalId)
                .stream().map(AgendamentoDTO::new).collect(Collectors.toList());
    }

    public void cancelarAgendamento(Long id, StatusAgendamento motivoCancelamento) {
        Agendamento agendamento = agendamentoRepository.findById(id).orElseThrow();
        agendamento.setStatus(motivoCancelamento);
        agendamentoRepository.save(agendamento);
    }

    public void concluirAgendamento(Long id) {
        Agendamento agendamento = agendamentoRepository.findById(id).orElseThrow();
        agendamento.setStatus(StatusAgendamento.CONCLUIDO);
        agendamentoRepository.save(agendamento);
    }
}
