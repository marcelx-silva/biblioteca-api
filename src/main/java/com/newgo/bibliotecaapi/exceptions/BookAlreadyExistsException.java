package com.newgo.bibliotecaapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class BookAlreadyExistsException extends RuntimeException{
    public BookAlreadyExistsException(){
        super("Book already exists");
    }
}
