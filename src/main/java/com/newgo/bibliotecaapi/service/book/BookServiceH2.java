package com.newgo.bibliotecaapi.service.book;

import com.newgo.bibliotecaapi.dto.BookDTO;
import com.newgo.bibliotecaapi.model.author.Author;
import com.newgo.bibliotecaapi.model.baseentity.BaseEntity;
import com.newgo.bibliotecaapi.model.book.Book;
import com.newgo.bibliotecaapi.repository.BookRepository;
import com.newgo.bibliotecaapi.service.author.AuthorService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Profile("dev")
@Service
public class BookServiceH2 implements BookService{

    private final BookRepository bookRepository;
    private final AuthorService authorService;

    public BookServiceH2(BookRepository bookRepository, AuthorService authorService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
    }
    @Override
    public Book save(Book book) {
        if(book.getAuthorSet().stream().allMatch(author ->  this.authorService.existsById(author.getId())) &&
                book.getAuthorSet().size() > 0){
            System.out.println(book.getAuthorSet());
          return this.bookRepository.save(book);
        }
        return null;
    }

    @Override
    public Book findById(UUID id) {
        return this.bookRepository.findBooksById(id);
    }

    @Override
    public Set<Book> findAll() {
        Set<Book> books = new HashSet<>();
        this.bookRepository.findAll().forEach(books::add);
        return books;
    }

    @Override
    public void deleteById(UUID id) {
        this.bookRepository.deleteById(id);
    }

    @Override
    public Set<Book> findBooksByAuthorId(UUID id) {
        Author author = this.authorService.findById(id);
        Set<Book> books = new HashSet<>(author.getBookSet());
        return books;
    }
}
