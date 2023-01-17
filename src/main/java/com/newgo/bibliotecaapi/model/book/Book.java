package com.newgo.bibliotecaapi.model.book;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.newgo.bibliotecaapi.model.author.Author;


import com.newgo.bibliotecaapi.model.genre.Genre;
import com.newgo.bibliotecaapi.model.language.Language;
import com.newgo.bibliotecaapi.model.publisher.Publisher;
import com.newgo.bibliotecaapi.model.valueObjects.ISBN;
import com.newgo.bibliotecaapi.model.BaseEntity;
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

    @Column(name = "title", nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;

    @ManyToOne
    @JoinColumn(name = "language", nullable = false)
    private Language language;

    @Column(name = "pages", nullable = false)
    private Integer pages;

    @Embedded
    @Column(name = "isbn_13", unique = true)
    private ISBN isbn;

    @JsonBackReference(value = "authors_id")
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "books_authors",
            joinColumns = {@JoinColumn(name="book_id")},
            inverseJoinColumns = {@JoinColumn(name = "author_id")})
    private Set<Author> authorSet = new HashSet<>();

    @ManyToMany(targetEntity = Genre.class)
    @JoinTable(name = "books_genres",
            joinColumns = {@JoinColumn(name = "book_id")},
            inverseJoinColumns = {@JoinColumn(name = "genre_id")})
    private Set<Genre> genre;
}
