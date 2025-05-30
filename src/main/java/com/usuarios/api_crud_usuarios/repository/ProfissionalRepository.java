package com.usuarios.api_crud_usuarios.repository;

import com.usuarios.api_crud_usuarios.model.dto.ProfissionalDTO;
import com.usuarios.api_crud_usuarios.model.entity.Profissional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfissionalRepository extends JpaRepository<Profissional, Long> {
}
