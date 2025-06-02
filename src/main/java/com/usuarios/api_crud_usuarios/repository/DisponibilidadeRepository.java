package com.usuarios.api_crud_usuarios.repository;

import com.usuarios.api_crud_usuarios.model.entity.Disponibilidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DisponibilidadeRepository extends JpaRepository<Disponibilidade, Long> {
    List<Disponibilidade> findByProfissionalId(Long profissionalId);
}
