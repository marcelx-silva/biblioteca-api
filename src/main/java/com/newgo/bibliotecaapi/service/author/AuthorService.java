package com.newgo.bibliotecaapi.service.author;

import com.newgo.bibliotecaapi.model.author.Author;

import java.util.Set;
import java.util.UUID;

public interface AuthorService {
    Author save(Author author);
    Author findById(UUID id);
    Set<Author> findAll();
    boolean existsById(UUID id);
    void deleteById(UUID id);
    Author findAuthorByName(String name);
    boolean existsAuthorByName(String name);
}
