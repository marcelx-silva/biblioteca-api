package com.newgo.bibliotecaapi.model.book;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.newgo.bibliotecaapi.model.author.Author;
import com.newgo.bibliotecaapi.model.baseentity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;


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
    @JsonBackReference(value = "authors_id")
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "authors_books",
            joinColumns = {@JoinColumn(name="fk_book_id")},
            inverseJoinColumns = {@JoinColumn(name = "fk_author_id")})
    private Set<Author> authorSet = new HashSet<>();
}
