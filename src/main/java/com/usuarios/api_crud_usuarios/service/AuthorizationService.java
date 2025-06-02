package com.usuarios.api_crud_usuarios.service;

import com.usuarios.api_crud_usuarios.exceptions.UserMailNotFound;
import com.usuarios.api_crud_usuarios.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthorizationService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UserMailNotFound {
        return usuarioRepository.findByEmail(email);
    }
}
