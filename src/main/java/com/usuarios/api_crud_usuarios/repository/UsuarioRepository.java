package com.usuarios.api_crud_usuarios.repository;

import com.usuarios.api_crud_usuarios.enums.TipoUsuario;
import com.usuarios.api_crud_usuarios.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    UserDetails findByEmail(String email);
    List<Usuario> findByTipoUsuario(TipoUsuario tipoUsuario);

}

