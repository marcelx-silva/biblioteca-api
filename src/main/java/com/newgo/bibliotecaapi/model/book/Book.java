package com.newgo.bibliotecaapi.model.book;

import com.newgo.bibliotecaapi.model.author.Author;
import com.newgo.bibliotecaapi.model.baseentity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Entity(name = "books")
@NoArgsConstructor
@AllArgsConstructor
public class Book extends BaseEntity {
    @Column(name = "title")
    private String title;
    @Column(name = "genre")
    private String genre;
    @Column(name = "publisher")
    private String publisher;
    @Column(name = "language")
    private String language;
    @Column(name = "pages")
    private Integer pages;
    @Column(name = "isbn_13")
    private String isbn13;

}
