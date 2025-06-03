package com.usuarios.api_crud_usuarios.service;

import com.usuarios.api_crud_usuarios.enums.StatusAgendamento;
import com.usuarios.api_crud_usuarios.enums.TipoUsuario;
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
        Usuario cliente = usuarioRepository.findById(dto.getCliente()).orElseThrow();
        Usuario profissional = usuarioRepository.findById(dto.getProfissional()).orElseThrow();
        Servico servico = servicoRepository.findById(dto.getServico()).orElseThrow();

        boolean conflito = agendamentoRepository.existsByProfissional_IdAndDataHoraInicioLessThanAndDataHoraFimGreaterThan(
                dto.getProfissional(), dto.getDataHoraFim(), dto.getDataHoraInicio());

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
        return agendamentoRepository.findByCliente_Id(clienteId)
                .stream().map(AgendamentoDTO::new).collect(Collectors.toList());
    }

    public List<AgendamentoDTO> listarPorProfissional(Long profissionalId) {
        return agendamentoRepository.findByProfissional_Id(profissionalId)
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
