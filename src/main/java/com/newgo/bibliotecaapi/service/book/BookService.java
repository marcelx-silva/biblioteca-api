package com.newgo.bibliotecaapi.service.book;

import com.newgo.bibliotecaapi.dto.BookDTO;
import com.newgo.bibliotecaapi.model.book.Book;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface BookService {
    Book save(Book book);
    Book findById(UUID id);
    Set<Book> findAll();
    void deleteById(UUID id);
    Set<Book> findBooksByAuthorId(UUID id);
}
