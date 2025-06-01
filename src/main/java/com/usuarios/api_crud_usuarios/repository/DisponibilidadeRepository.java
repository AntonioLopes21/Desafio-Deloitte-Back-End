package com.usuarios.api_crud_usuarios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisponibilidadeRepository extends JpaRepository<DisponibilidadeRepository, Long> {
}
