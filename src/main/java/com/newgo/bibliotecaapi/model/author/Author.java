package com.newgo.bibliotecaapi.model.author;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.newgo.bibliotecaapi.model.BaseEntity;
import com.newgo.bibliotecaapi.model.book.Book;
import com.newgo.bibliotecaapi.model.country.Country;
import com.newgo.bibliotecaapi.model.valueObjects.Name;
import jakarta.persistence.*;
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
    public Author(UUID id, Name name, LocalDate birthDate){
        super(id);
        this.name = name;
        this.birthDate = birthDate;
    }

    public Author(Name name, LocalDate birthDate){
        this.name = name;
        this.birthDate = birthDate;
    }

    public Author(Name name, LocalDate birthDate, Country country){
        this.name = name;
        this.birthDate = birthDate;
        this.country = country;
    }

    /** Atributo composto. Composto pelas variáveis
     * <code>firstName</code> e <code>lastName</code> presente
     * na classe <strong>Name</strong>.
        @see Name
     */
    @Embedded
    //Anotação para sobreescrever o nome dos atributos gerado pelo
    //JPA.
    @AttributeOverrides({
            @AttributeOverride(name="fistName",column = @Column(name = "first_name", nullable = false)),
            @AttributeOverride(name="lastName",column = @Column(name = "last_name"))
    })
    private Name name;
    @Column(name = "birth_date")
    private LocalDate birthDate;

    @JsonManagedReference(value = "authors")
    @ManyToMany(mappedBy="authorSet")
    private Set<Book> bookSet = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "country_id",referencedColumnName = "id")
    private Country country;
}
