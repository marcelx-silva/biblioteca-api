package com.newgo.bibliotecaapi.repository;

import com.newgo.bibliotecaapi.model.author.Author;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AuthorRepository extends CrudRepository<Author, UUID> {
}
