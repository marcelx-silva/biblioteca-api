package com.newgo.bibliotecaapi.mapper.book;

import com.newgo.bibliotecaapi.dto.BookDTO;
import com.newgo.bibliotecaapi.model.author.Author;
import com.newgo.bibliotecaapi.model.baseentity.BaseEntity;
import com.newgo.bibliotecaapi.model.book.Book;
import com.newgo.bibliotecaapi.service.author.AuthorService;
import org.springframework.context.annotation.Configuration;

import java.util.stream.Collectors;

@Configuration
public class BookMapperImpl implements BookMapper{

    private final AuthorService authorService;

    public BookMapperImpl(AuthorService authorService) {
        this.authorService = authorService;
    }

    @Override
    public BookDTO bookToBookDTO(Book book) {
        if (book == null)
            return null;

        BookDTO bookDTO = new BookDTO();
        bookDTO.setTitle(book.getTitle());
        bookDTO.setGenre(book.getGenre());
        bookDTO.setLanguage(book.getLanguage());
        bookDTO.setPublisher(book.getPublisher());
        bookDTO.setPages(book.getPages());
        bookDTO.setIsbn13(book.getIsbn13());
        bookDTO.setAuthors(book.getAuthorSet()
                .stream()
                .map(Author::getName)
                .collect(Collectors.toSet()));

        return bookDTO;
    }

    @Override
    public Book bookDtoToBook(BookDTO bookDTO) {
        if (bookDTO == null)
            return null;

        Book book = new Book();
        book.setTitle(bookDTO.getTitle());
        book.setGenre(bookDTO.getGenre());
        book.setPublisher(bookDTO.getPublisher());
        book.setLanguage(bookDTO.getLanguage());
        book.setPages(bookDTO.getPages());
        book.setIsbn13(bookDTO.getIsbn13());
        book.setAuthorSet(bookDTO.getAuthors()
                .stream()
                .map(authorService::findAuthorByName)
                .collect(Collectors.toSet()));

        return book;
    }
}
