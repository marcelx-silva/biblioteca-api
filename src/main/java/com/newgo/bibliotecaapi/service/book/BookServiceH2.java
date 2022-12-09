package com.newgo.bibliotecaapi.service.book;

import com.newgo.bibliotecaapi.exceptions.AuthorNotFoundException;
import com.newgo.bibliotecaapi.exceptions.BookAlreadyExistsException;
import com.newgo.bibliotecaapi.model.author.Author;
import com.newgo.bibliotecaapi.model.book.Book;
import com.newgo.bibliotecaapi.repository.BookRepository;
import com.newgo.bibliotecaapi.service.author.AuthorService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.*;

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

        if (existsBookByTitle(book.getTitle()) || existsBookByIsbn13(book.getIsbn13()))
            throw new BookAlreadyExistsException();

        if(book.getAuthorSet().stream().allMatch(author ->  this.authorService.existsAuthorByName(author.getName())) &&
                book.getAuthorSet().size() > 0){
          return this.bookRepository.save(book);
        }
        throw new AuthorNotFoundException();
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

    @Override
    public Book findBooksByTitle(String title) {
        return this.bookRepository.findBooksByTitle(title);
    }

    @Override
    public boolean existsBookByTitle(String title) {
        return this.bookRepository.existsBookByTitle(title);
    }

    @Override
    public boolean existsBookByIsbn13(String isbn13) {
        return this.bookRepository.existsBookByIsbn13(isbn13);
    }
}
