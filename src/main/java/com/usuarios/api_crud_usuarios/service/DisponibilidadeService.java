package com.usuarios.api_crud_usuarios.service;

import com.usuarios.api_crud_usuarios.enums.TipoUsuario;
import com.usuarios.api_crud_usuarios.model.dto.DisponibilidadeDTO;
import com.usuarios.api_crud_usuarios.model.entity.Disponibilidade;
import com.usuarios.api_crud_usuarios.model.entity.Usuario;
import com.usuarios.api_crud_usuarios.repository.DisponibilidadeRepository;
import com.usuarios.api_crud_usuarios.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DisponibilidadeService {
    private final DisponibilidadeRepository disponibilidadeRepository;
    private final UsuarioRepository usuarioRepository;

    //Get
    public List<DisponibilidadeDTO> listarDisponibilidade() {
        List<Disponibilidade> lista = disponibilidadeRepository.findAll();
        List<DisponibilidadeDTO> listaDTO = lista.stream()
                .map(DisponibilidadeDTO::new)
                .collect(Collectors.toList());
        return listaDTO;
    }

    //GetById
    public DisponibilidadeDTO verificarDisponibilidade(Long id) {
        Optional<Disponibilidade> disponibilidade = disponibilidadeRepository.findById(id);
        if (disponibilidade.isEmpty()) {
            throw new RuntimeException("Disponibilidade não encontrada");
        }
        return new DisponibilidadeDTO(disponibilidade.get());
    }

    //Post
    public DisponibilidadeDTO definirDisponibilidade(DisponibilidadeDTO disponibilidadeDTO) {
        Usuario profissional = usuarioRepository.findById(disponibilidadeDTO.getProfissional().getId())
                .orElseThrow(() -> new RuntimeException("Profissional não encontrado"));

        validarSeProfissional(profissional);

        Disponibilidade novaDisponibilidade = DisponibilidadeDTO.toEntity(disponibilidadeDTO);
        novaDisponibilidade.setProfissional(profissional);

        Disponibilidade salvarDisponibilidade = disponibilidadeRepository.save(novaDisponibilidade);
        return new DisponibilidadeDTO(salvarDisponibilidade);
    }

    //Put
    public DisponibilidadeDTO atualizarDisponibilidade (DisponibilidadeDTO disponibilidadeDTO, Long id) {
        Disponibilidade disponibilidade = disponibilidadeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Disponibilidade não encontrada."));
        validarSeProfissional(disponibilidade.getProfissional());

        disponibilidade.setDiaDaSemana(disponibilidadeDTO.getDiaDaSemana());
        disponibilidade.setHoraInicio(disponibilidadeDTO.getHoraInicio());
        disponibilidade.setHoraFim(disponibilidadeDTO.getHoraFim());

        Disponibilidade salvarDisponibilidade = disponibilidadeRepository.save(disponibilidade);
        return new DisponibilidadeDTO(salvarDisponibilidade);
    }

    //Delete
    public void deletarDisponibilidade ( Long id) {
        Disponibilidade disponibilidade = disponibilidadeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Disponibilidade não encontrada."));
        validarSeProfissional(disponibilidade.getProfissional());
        disponibilidadeRepository.delete(disponibilidade);
    }

    private void validarSeProfissional(Usuario usuario) {
        if (usuario == null || usuario.getTipoUsuario() != TipoUsuario.ROLE_PROFISSIONAL) {
            throw new RuntimeException("Usuário não é um profissional.");
        }
    }

}
