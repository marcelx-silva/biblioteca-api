package com.newgo.bibliotecaapi.model.language;

import com.newgo.bibliotecaapi.model.baseentity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "languages")
@Getter
@Setter
@NoArgsConstructor
public class Language extends BaseEntity {

    public Language(UUID id, String name){
        super(id);
        this.name = name;
    }

    public Language(String name){
        this.name = name;
    }

    @Column(name = "name",unique = true,nullable = false)
    private String name;
}