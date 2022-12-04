package com.newgo.bibliotecaapi.service.author;

import com.newgo.bibliotecaapi.model.author.Author;
import com.newgo.bibliotecaapi.repository.AuthorRepository;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
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
        return this.authorRepository.save(author);
    }

    @Override
    public Optional<Author> findById(UUID id) {
        return this.authorRepository.findById(id);
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
}
