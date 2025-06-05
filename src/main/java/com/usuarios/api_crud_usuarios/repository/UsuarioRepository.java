package com.usuarios.api_crud_usuarios.repository;

import com.usuarios.api_crud_usuarios.enums.TipoUsuario;
import com.usuarios.api_crud_usuarios.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    //UserDetails findByEmail(String email);
    List<Usuario> findByTipoUsuario(TipoUsuario tipoUsuario);

    boolean existsByEmail(String email);

    Optional<Usuario> findByEmail(String email);
}

