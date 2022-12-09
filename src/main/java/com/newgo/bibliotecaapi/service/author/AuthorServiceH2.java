package com.newgo.bibliotecaapi.service.author;

import com.newgo.bibliotecaapi.exceptions.AuthorAlreadyExistsException;
import com.newgo.bibliotecaapi.model.author.Author;
import com.newgo.bibliotecaapi.repository.AuthorRepository;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Profile("dev")
@Service
public class AuthorServiceH2 implements AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorServiceH2(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    @Transactional
    public Author save(Author author) {
        if (existsAuthorByName(author.getName())){
            throw new AuthorAlreadyExistsException();
        }
        return this.authorRepository.save(author);
    }

    @Override
    public Author findById(UUID id) {
        return this.authorRepository.findAuthorById(id);
    }

    @Override
    public Set<Author> findAll() {
        Set<Author> authors = new HashSet<>();
        this.authorRepository.findAll().forEach(authors::add);
        return authors;
    }

    @Override
    public boolean existsById(UUID id) {
        return this.authorRepository.existsById(id);
    }

    @Override
    @Transactional
    public void deleteById(UUID id) {
        this.authorRepository.deleteById(id);
    }

    @Override
    public Author findAuthorByName(String name) {
      return this.authorRepository.findAuthorByName(name);
    }

    @Override
    public boolean existsAuthorByName(String name) {
        return this.authorRepository.existsAuthorByName(name);
    }
}
