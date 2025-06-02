package com.usuarios.api_crud_usuarios.model.dto;

import com.usuarios.api_crud_usuarios.model.entity.Servico;
import com.usuarios.api_crud_usuarios.model.entity.Usuario;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class ServicoDTO {
    private Long id;
    private String nome;
    private String descricao;
    private int duracaoEmMinutos;
    private Usuario profissional;

    public ServicoDTO(Servico servico) {
        this.setId(servico.getId());
        this.setNome(servico.getNome());
        this.setDescricao(servico.getDescricao());
        this.setDuracaoEmMinutos(servico.getDuracaoEmMinutos());
        this.setProfissional(servico.getProfissional());
    }

    public static Servico toEntity(ServicoDTO servicoDTO) {
        Servico servico = new Servico();
        Usuario usuario = new Usuario();

        usuario.setId(servicoDTO.getProfissional().getId());
        servico.setNome(servicoDTO.getNome());
        servico.setDuracaoEmMinutos(servicoDTO.getDuracaoEmMinutos());
        servico.setProfissional(servicoDTO.getProfissional());
        return servico;
    }



}



