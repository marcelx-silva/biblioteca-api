package com.newgo.bibliotecaapi.repository;

import com.newgo.bibliotecaapi.dto.BookDTO;
import com.newgo.bibliotecaapi.model.book.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.UUID;

@Repository
public interface BookRepository extends CrudRepository<Book, UUID> {
    Book findBooksById(UUID uuid);
}
