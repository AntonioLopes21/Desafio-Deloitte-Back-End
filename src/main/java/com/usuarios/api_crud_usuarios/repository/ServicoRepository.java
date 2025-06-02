package com.usuarios.api_crud_usuarios.repository;

import com.usuarios.api_crud_usuarios.enums.TipoUsuario;
import com.usuarios.api_crud_usuarios.model.entity.Servico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Long> {
    Optional<Servico> findById(Long id);

    List<Servico> findByProfissionalTipoUsuario(TipoUsuario tipoUsuario);

    Optional<Servico> findByIdAndProfissionalTipoUsuario(Long id, TipoUsuario tipoUsuario);


}
