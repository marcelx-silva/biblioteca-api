package com.newgo.bibliotecaapi.model.author;

import com.newgo.bibliotecaapi.model.baseentity.BaseEntity;
import com.newgo.bibliotecaapi.model.book.Book;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "authors")
@Getter
@Setter
@NoArgsConstructor
public class Author extends BaseEntity {
    public Author(UUID id, String name, LocalDate birthDate){
        super(id);
        this.name = name;
        this.birthDate = birthDate;
    }

    public Author(String name, LocalDate birthDate){
        this.name = name;
        this.birthDate = birthDate;
    }

    @Column
    private String name;
    @Column(name = "birth_date")
    private LocalDate birthDate;
    @ManyToMany(mappedBy="authorSet")
    private Set<Book> bookSet = new HashSet<>();
}
