package com.newgo.bibliotecaapi.mapper.author;

import com.newgo.bibliotecaapi.dto.AuthorDTO;
import com.newgo.bibliotecaapi.dto.BookAuthorDTO;
import com.newgo.bibliotecaapi.model.author.Author;
import org.springframework.context.annotation.Configuration;

import java.util.stream.Collectors;

@Configuration
public class AuthorMapperImpl implements AuthorMapper {

    public AuthorDTO authorToAuthorDto(Author author) {
        if (author==null)
            return null;

        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setName(author.getName());
        authorDTO.setBirthDate(author.getBirthDate());
        authorDTO.setBooks(author.getBookSet()
                .stream()
                .map(book -> new BookAuthorDTO(book.getTitle(), book.getGenre(), book.getIsbn13()))
                .collect(Collectors.toSet()));
        return authorDTO;
    }
}
