package com.newgo.bibliotecaapi.controller;

import com.newgo.bibliotecaapi.dto.AuthorDTO;
import com.newgo.bibliotecaapi.model.author.Author;
import com.newgo.bibliotecaapi.service.author.AuthorService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/author")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping
    ResponseEntity<Object> saveAuthor(@RequestBody AuthorDTO authorDTO){
        Author author = new Author();
        BeanUtils.copyProperties(authorDTO,author);
        return ResponseEntity.status(HttpStatus.CREATED).body(this.authorService.save(author));
    }

    @GetMapping
    ResponseEntity<Object> getAllAuthors(){
        return ResponseEntity.status(HttpStatus.OK).body(authorService.findAll());
    }

    @GetMapping("id/{id}")
    ResponseEntity<Object> getAuthorById(@PathVariable("id") UUID authorID){
        Optional<Author> authorOptional = authorService.findById(authorID);
        if (authorOptional.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(authorOptional);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Author does not exist!");
    }

    @DeleteMapping("id/{id}")
    ResponseEntity<Object> deleteAuthorById(@PathVariable("id") UUID authorID){
        Optional<Author> authorOptional = authorService.findById(authorID);
        if (authorOptional.isPresent()){
            authorService.deleteById(authorID);
            return ResponseEntity.status(HttpStatus.OK).body(authorOptional);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Author does not exist!");
    }
}
