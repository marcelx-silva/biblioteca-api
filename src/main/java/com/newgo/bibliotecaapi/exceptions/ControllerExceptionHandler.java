package com.newgo.bibliotecaapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(value = {AuthorNotFoundException.class,BookNotFoundException.class})
    public ResponseEntity<ErrorMessage> resourceNotFound(Exception exception){
            ErrorMessage errorMessage = new ErrorMessage(HttpStatus.NOT_FOUND,exception.getMessage(),new Date());
            return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {AuthorAlreadyExistsException.class, BookAlreadyExistsException.class})
    public ResponseEntity<ErrorMessage> resourceAlreadyExists(Exception exception){
        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.CONFLICT,exception.getMessage(),new Date());
        return new ResponseEntity<>(errorMessage, HttpStatus.CONFLICT);
    }

}
