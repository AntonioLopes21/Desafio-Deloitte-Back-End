package com.usuarios.api_crud_usuarios.service;

import com.usuarios.api_crud_usuarios.enums.TipoUsuario;
import com.usuarios.api_crud_usuarios.exceptions.NotFoundItemException;
import com.usuarios.api_crud_usuarios.model.dto.ProfissionaisEServicosDisponiveisDTO;
import com.usuarios.api_crud_usuarios.model.dto.UsuarioDTO;
import com.usuarios.api_crud_usuarios.model.entity.Servico;
import com.usuarios.api_crud_usuarios.model.entity.Usuario;
import com.usuarios.api_crud_usuarios.repository.AgendamentoRepository;
import com.usuarios.api_crud_usuarios.repository.ServicoRepository;
import com.usuarios.api_crud_usuarios.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UsuarioService {

    private final ServicoRepository servicoRepository;

    private final AgendamentoRepository agendamentoRepository;

    private final UsuarioRepository usuarioRepository;

    public UsuarioDTO registrarUsuario(UsuarioDTO dto) {
        if (usuarioRepository.existsByEmail((dto.getEmail()))) {
            throw new RuntimeException("Usuário já existe");
        }
        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(dto.getSenha());
        usuario.setTipoUsuario(dto.getTipoUsuario());
        usuarioRepository.save(usuario);
        return UsuarioDTO.toDTO(usuario);
    }

    public Optional<UsuarioDTO> login(String email, String senha) {
        Optional<Usuario> usuario = usuarioRepository.findByEmail(email);
        if (usuario.isPresent()) {
            Usuario usuarioSenha = usuario.get();
            if (senha.equals(usuarioSenha.getSenha())) {
                return Optional.of(UsuarioDTO.toDTO(usuarioSenha));
            }
        }
        return Optional.empty();
    }


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
    public List<Usuario> listarProfissionais() {
        return usuarioRepository.findByTipoUsuario(TipoUsuario.PROFISSIONAL);

    }

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
        novoUser.setSenha(usuarioDTO.getSenha());
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
            usuario.setSenha(dto.getSenha());
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
