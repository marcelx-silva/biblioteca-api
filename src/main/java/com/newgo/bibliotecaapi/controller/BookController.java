package com.newgo.bibliotecaapi.controller;

import com.newgo.bibliotecaapi.dto.BookDTO;
import com.newgo.bibliotecaapi.mapper.book.BookMapper;
import com.newgo.bibliotecaapi.model.author.Author;
import com.newgo.bibliotecaapi.model.book.Book;
import com.newgo.bibliotecaapi.service.author.AuthorService;
import com.newgo.bibliotecaapi.service.book.BookService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;
    private final BookMapper bookMapper;

    public BookController(BookService bookService, AuthorService authorService, BookMapper bookMapper){
        this.bookService = bookService;
        this.authorService = authorService;
        this.bookMapper = bookMapper;
    }

    @GetMapping
    ResponseEntity<Object> getAllBooks(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(this.bookService.findAll().stream()
                        .map(bookMapper::bookToBookDTO));
    }

    @GetMapping("/id/{id}")
    ResponseEntity<Object> getBookById(@PathVariable("id") UUID bookID){
        Book book = this.bookService.findById(bookID);
        BookDTO bookDTO = this.bookMapper.bookToBookDTO(book);
        if (bookDTO == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book does not exist!");
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(bookDTO);

    }

    @GetMapping("/author/{id}")
    ResponseEntity<Object> getBooksByAuthorId(@PathVariable("id") UUID authorID){
       Author author = this.authorService.findById(authorID);
        if (author == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Author does not exist!");
        }
        Set<BookDTO> bookSet = new HashSet<>();
        this.bookService.findBooksByAuthorId(author.getId())
                .forEach(book -> bookSet.add(bookMapper.bookToBookDTO(book)));
        return ResponseEntity.status(HttpStatus.FOUND).body(bookSet);
    }

    @PostMapping
    ResponseEntity<Object> saveBook(@RequestBody @Valid BookDTO bookDTO){
        Book book = this.bookMapper.bookDtoToBook(bookDTO);
        this.bookService.save(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(this.bookService.findById(book.getId()));
    }

    @PutMapping("/id/{id}")
    ResponseEntity<Object> updateBook(@RequestBody @Valid BookDTO bookDTO,@PathVariable("id") UUID bookId){
        Book bookFromDatabase = this.bookService.findById(bookId);
        if ( bookFromDatabase == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book does not exist!");
        }
        Book book = bookMapper.bookDtoToBook(bookDTO);
        book.setId(bookFromDatabase.getId());
        bookService.save(book);
        return ResponseEntity.status(HttpStatus.OK).body(bookMapper.bookToBookDTO(book));
    }


}
