package com.usuarios.api_crud_usuarios.exceptions;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserMailNotFound extends UsernameNotFoundException {

    public UserMailNotFound(String email) {
      super("Usuário não encontrado pelo email: " + email);

    }

}
