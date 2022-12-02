package com.newgo.bibliotecaapi.service.author;

import com.newgo.bibliotecaapi.model.author.Author;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface AuthorService {
    Author save(Author author);
    Optional<Author> findById(UUID id);
    Set<Author> findAll();
    void deleteById(UUID id);
}
