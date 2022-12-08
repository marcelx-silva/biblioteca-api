package com.newgo.bibliotecaapi.controller;

import com.newgo.bibliotecaapi.dto.AuthorDTO;
import com.newgo.bibliotecaapi.exceptions.BookNotFoundException;
import com.newgo.bibliotecaapi.mapper.author.AuthorMapper;
import com.newgo.bibliotecaapi.model.author.Author;
import com.newgo.bibliotecaapi.service.author.AuthorService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/author")
@CrossOrigin
public class AuthorController {

    private final AuthorService authorService;
    private final AuthorMapper authorMapper;

    public AuthorController(AuthorService authorService, AuthorMapper authorMapper) {
        this.authorService = authorService;
        this.authorMapper = authorMapper;
    }

    @PostMapping("signup/")
    ResponseEntity<Object> saveAuthor(@RequestBody AuthorDTO authorDTO){
        Author author = new Author();
        BeanUtils.copyProperties(authorDTO,author);
        return ResponseEntity.status(HttpStatus.CREATED).body(this.authorService.save(author));
    }

    @GetMapping("all/")
    ResponseEntity<Object> getAllAuthors(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(authorService.findAll().stream()
                        .map(this.authorMapper::authorToAuthorDto));
    }

    @GetMapping("id/{id}")
    ResponseEntity<Object> getAuthorById(@PathVariable("id") UUID authorID){
        Author author = authorService.findById(authorID);
        AuthorDTO authorDTO = authorMapper.authorToAuthorDto(author);

        if (authorDTO!=null)
            return ResponseEntity.status(HttpStatus.OK).body(authorDTO);

        throw new BookNotFoundException();
    }

    @DeleteMapping("id/{id}")
    ResponseEntity<Object> deleteAuthorById(@PathVariable("id") UUID authorID){
        Author author = authorService.findById(authorID);
        AuthorDTO authorDTO = authorMapper.authorToAuthorDto(author);

        if (authorDTO==null)
            throw new BookNotFoundException();


        authorService.deleteById(authorID);
        return ResponseEntity.status(HttpStatus.OK).body(authorDTO);

    }

    @PutMapping("/id/{id}")
    ResponseEntity<Object> updateAuthor(@PathVariable("id") UUID authorID,@RequestBody @Valid AuthorDTO authorDTO){
        Author author = authorService.findById(authorID);

        if (author == null)
            throw new BookNotFoundException();

        author = new Author();
        BeanUtils.copyProperties(authorDTO,author);
        author.setId(authorID);
        authorService.save(author);
        return ResponseEntity.status(HttpStatus.OK).body(authorMapper.authorToAuthorDto(author));
    }
}
