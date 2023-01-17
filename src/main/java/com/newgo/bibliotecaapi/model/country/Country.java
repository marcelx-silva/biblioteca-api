package com.newgo.bibliotecaapi.model.country;

import com.newgo.bibliotecaapi.model.baseentity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "countries")
@NoArgsConstructor
public class Country extends BaseEntity {

    @Column(name = "name",unique = true,nullable = false)
    private String name;

    public Country(String name) {
        this.name = name;
    }
}