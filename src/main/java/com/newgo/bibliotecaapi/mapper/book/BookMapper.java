package com.newgo.bibliotecaapi.mapper.book;

import com.newgo.bibliotecaapi.dto.BookDTO;
import com.newgo.bibliotecaapi.model.book.Book;

import java.util.Optional;

public interface BookMapper {
    BookDTO bookToBookDTO(Book book);
    Book bookDtoToBook(BookDTO bookDTO);
}
