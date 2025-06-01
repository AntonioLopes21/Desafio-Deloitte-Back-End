package com.usuarios.api_crud_usuarios.repository;

import com.usuarios.api_crud_usuarios.model.entity.Servico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicoRepository extends JpaRepository<Servico, Long> {
}
