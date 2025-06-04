package com.usuarios.api_crud_usuarios.service;

import com.usuarios.api_crud_usuarios.exceptions.NotFoundItemException;
import com.usuarios.api_crud_usuarios.model.dto.ProfissionaisEServicosDisponiveisDTO;
import com.usuarios.api_crud_usuarios.model.dto.UsuarioDTO;
import com.usuarios.api_crud_usuarios.model.entity.Servico;
import com.usuarios.api_crud_usuarios.model.entity.Usuario;
import com.usuarios.api_crud_usuarios.repository.AgendamentoRepository;
import com.usuarios.api_crud_usuarios.repository.ServicoRepository;
import com.usuarios.api_crud_usuarios.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UsuarioService {
    private final PasswordEncoder passwordEncoder;

    private final ServicoRepository servicoRepository;

    private final AgendamentoRepository agendamentoRepository;

    private final UsuarioRepository usuarioRepository;

    //GET Servicos
    public List<ProfissionaisEServicosDisponiveisDTO> listarServicosEProfissionais() {
        return agendamentoRepository.findAll()
                .stream()
                .map(agendamento -> {
                    ProfissionaisEServicosDisponiveisDTO dto = new ProfissionaisEServicosDisponiveisDTO();

                    Usuario profissional = agendamento.getProfissional();
                    Servico servico = agendamento.getServico();

                    dto.setProfissional_Id(profissional.getId());
                    dto.setProfissional_nome(profissional.getNome());
                    dto.setProfissional_email(profissional.getEmail());

                    dto.setServico_Id(servico.getId());
                    dto.setServico_nome(servico.getNome());
                    dto.setServico_descricao(servico.getDescricao());

                    return dto;
                }).toList();
    }
    //GET PROFISSIONAIS
    //public List<Usuario> listarProfissionais() {
        //return usuarioRepository.findByTipoUsuario(TipoUsuario.ROLE_PROFISSIONAL);

    //}

    //GET SERVICOS
    public List<Servico> listarServicos() {
        return servicoRepository.findAll();
    }

    //GET
    public List<UsuarioDTO> listarUsuarios() {

        return usuarioRepository.findAll()
                .stream()
                .map(UsuarioDTO ::new)
                .toList();
    }

    //GetById
    public Optional<UsuarioDTO> listarUsuarioPorId(Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        return usuario.map(UsuarioDTO::new);
    }

    //POST
    public UsuarioDTO criarUsuario(UsuarioDTO usuarioDTO) {
        Usuario novoUser = UsuarioDTO.toEntity(usuarioDTO);
        novoUser.setSenha(passwordEncoder.encode(usuarioDTO.getSenha()));
        Usuario usuarioSalvo = usuarioRepository.save(novoUser);
        return UsuarioDTO.toDTO(usuarioSalvo);
    }

    //PUT
    public UsuarioDTO editarUsuario (Long id ,UsuarioDTO dto) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);

        if(optionalUsuario.isPresent()) {
            Usuario usuario = optionalUsuario.get();

            usuario.setNome(dto.getNome());
            usuario.setEmail(dto.getEmail());
            usuario.setSenha(passwordEncoder.encode(dto.getSenha()));
            usuario.setTipoUsuario(dto.getTipoUsuario());

            usuarioRepository.save(usuario);

            return UsuarioDTO.toDTO(usuario);
        } else throw new NotFoundItemException(id);
    }


    //DELETE
    public void excluirUsuario (Long id) {
        if(usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
        } else {
            throw new NotFoundItemException(id);
        }
    }
}
