package com.usuarios.api_crud_usuarios.service;

import com.usuarios.api_crud_usuarios.enums.TipoUsuario;
import com.usuarios.api_crud_usuarios.model.dto.ServicoDTO;
import com.usuarios.api_crud_usuarios.model.dto.UsuarioDTO;
import com.usuarios.api_crud_usuarios.model.entity.Servico;
import com.usuarios.api_crud_usuarios.model.entity.Usuario;
import com.usuarios.api_crud_usuarios.repository.ServicoRepository;
import com.usuarios.api_crud_usuarios.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ServicoService {

    private final ServicoRepository servicoRepository;
    private final UsuarioRepository usuarioRepository;

    //Get
    public List<ServicoDTO> listarServico() {
        List<Servico> servicos = servicoRepository.findByProfissionalTipoUsuario(TipoUsuario.ROLE_PROFISSIONAL);
            List<ServicoDTO> servicoDTO = servicos.stream()
                    .map(ServicoDTO::new)
                    .collect(Collectors.toList());
            return servicoDTO;
    }

    //Get by id
    public ServicoDTO verificarServico( Long id) {
            Optional<Servico> servico = servicoRepository.findByIdAndProfissionalTipoUsuario(id, TipoUsuario.ROLE_PROFISSIONAL);
            if (servico.isEmpty()) {
                throw new RuntimeException("Serviço não encontrado ou não pertence a um profissional.");
            }
            return  new ServicoDTO(servico.get());
        }

    //Post
    public ServicoDTO cadastrarServico(ServicoDTO servicoDTO) {
        Usuario profissional = usuarioRepository.findById(servicoDTO.getProfissional().getId())
                .orElseThrow(() -> new RuntimeException("Profissional não encontrado"));

        validarSeProfissional(profissional);

        Servico novoServico = ServicoDTO.toEntity(servicoDTO);
        novoServico.setProfissional(profissional);

        Servico servicoSalvo = servicoRepository.save(novoServico);
        return new ServicoDTO(servicoSalvo);

    }

    //Put
    public ServicoDTO atualizarServico(ServicoDTO servicoDTO, Long id) {
        Servico servico = servicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Serviço não encontrado"));

        validarSeProfissional(servico.getProfissional());

        servico.setNome(servicoDTO.getNome());
        servico.setDescricao(servicoDTO.getDescricao());
        servico.setDuracaoEmMinutos(servicoDTO.getDuracaoEmMinutos());
        Long idProfissional = servicoDTO.getProfissional().getId();
        Usuario profissional = usuarioRepository.findById(idProfissional)
                .orElseThrow(() -> new RuntimeException("Profissional não encontrado"));
        servico.setProfissional(profissional);

        Servico servicoSalvo = servicoRepository.save(servico);
        return new ServicoDTO(servicoSalvo);
    }

    //delete
    public void deletarServico(Long id){
        Servico servico = servicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Serviço não encontrado."));

        validarSeProfissional(servico.getProfissional());
        servicoRepository.delete(servico);
    }

    private void validarSeProfissional(Usuario usuario) {
        if (usuario == null || usuario.getTipoUsuario() != TipoUsuario.ROLE_PROFISSIONAL) {
            throw new RuntimeException("Usuário não é um profissional.");
        }
    }

}
