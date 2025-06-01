package com.usuarios.api_crud_usuarios.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class NotFoundItemException extends ResponseStatusException {

    public NotFoundItemException(Long id) {
        super(HttpStatus.NOT_FOUND, "ID " + id + " não encontrado.");
    }
}
