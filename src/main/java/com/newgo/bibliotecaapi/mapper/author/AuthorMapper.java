package com.newgo.bibliotecaapi.mapper.author;

import com.newgo.bibliotecaapi.dto.AuthorDTO;
import com.newgo.bibliotecaapi.model.author.Author;

public interface AuthorMapper {
    AuthorDTO authorToAuthorDto(Author author);

}
