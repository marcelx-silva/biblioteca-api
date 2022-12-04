package com.newgo.bibliotecaapi.controller;

import com.newgo.bibliotecaapi.dto.BookDTO;
import com.newgo.bibliotecaapi.model.author.Author;
import com.newgo.bibliotecaapi.model.book.Book;
import com.newgo.bibliotecaapi.service.author.AuthorService;
import com.newgo.bibliotecaapi.service.book.BookService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;

    public BookController(BookService bookService, AuthorService authorService){
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @GetMapping
    ResponseEntity<Object> getAllBooks(){
        return ResponseEntity.status(HttpStatus.OK).body(this.bookService.findAll());
    }

    @GetMapping("/id/{id}")
    ResponseEntity<Object> getBookById(@PathVariable("id") UUID bookID){
        Optional<Book> bookOptional = this.bookService.findById(bookID);
        if (bookOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.FOUND).body(bookOptional);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book does not exist!");
    }

    @GetMapping("/author/{id}")
    ResponseEntity<Object> getBooksByAuthorId(@PathVariable("id") UUID authorID){
        Optional<Author> authorOptional = this.authorService.findById(authorID);
        if (authorOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Author does not exist!");
        }
        Set<Book> bookSet = new HashSet<>(this.bookService.findBooksByAuthorId(authorOptional.get().getId()));
        return ResponseEntity.status(HttpStatus.FOUND).body(bookSet);
    }

    @PostMapping
    ResponseEntity<Object> saveBook(@RequestBody @Valid BookDTO bookDTO){
        Book book = new Book();
        BeanUtils.copyProperties(bookDTO,book);
        this.bookService.save(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(this.bookService.findById(book.getId()));
    }

    @PutMapping("/id/{id}")
    ResponseEntity<Object> updateBook(@RequestBody @Valid BookDTO bookDTO,@PathVariable("id") UUID bookId){
        Optional<Book> bookOptional = this.bookService.findById(bookId);
        if (bookOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book does not exist!");
        }
        Book book = new Book();
        BeanUtils.copyProperties(bookDTO,book);
        book.setId(bookOptional.get().getId());
        book.setAuthorSet(bookOptional.get().getAuthorSet());
        return ResponseEntity.status(HttpStatus.OK).body(this.bookService.save(book));
    }


}
