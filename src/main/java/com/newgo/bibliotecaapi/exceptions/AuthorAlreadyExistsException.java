package com.newgo.bibliotecaapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class AuthorAlreadyExistsException extends RuntimeException{
    public AuthorAlreadyExistsException(){
        super("Author already exists");
    }
}
