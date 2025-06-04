package com.usuarios.api_crud_usuarios.model.dto;

import com.usuarios.api_crud_usuarios.model.entity.Usuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class ProfissionaisEServicosDisponiveisDTO {
    @NotNull(message = "Id do profissional não pode ser nulo")
    Long profissional_Id;
    @NotBlank(message = "Nome do profissional é obrigatório")
    String profissional_nome;
    @Email(message = "Email inválido")
    @NotBlank(message = "Email do profissional é obrigatório")
    String profissional_email;
    Long servico_Id;
    @NotBlank(message = "Nome do serviço é obrigatório")
    String  servico_nome;
    String servico_descricao;

}
