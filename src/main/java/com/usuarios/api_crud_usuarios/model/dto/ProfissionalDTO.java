package com.usuarios.api_crud_usuarios.model.dto;

import com.usuarios.api_crud_usuarios.enums.EspecialidadeProfissional;
import com.usuarios.api_crud_usuarios.model.entity.Profissional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class ProfissionalDTO {
    private Long id;
    private String nome;
    private String email;
    private String descricao;
    private EspecialidadeProfissional especialidade;

    public Profissional toEntity() {
        Profissional profissional = new Profissional();
        profissional.setId(this.id);
        profissional.setNome(this.nome);
        profissional.setEmail(this.email);
        profissional.setDescricao(this.descricao);
        profissional.setEspecialidadeProfissional(this.especialidade);

        return profissional;
    }
}
