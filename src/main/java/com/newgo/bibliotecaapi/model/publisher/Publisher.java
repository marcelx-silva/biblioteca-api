package com.newgo.bibliotecaapi.model.publisher;

import com.newgo.bibliotecaapi.model.baseentity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "publishers")
@Getter
@Setter
@NoArgsConstructor
public class Publisher extends BaseEntity {

    public Publisher(UUID id, String name){
        super(id);
        this.name = name;
    }

    @Column(name = "name",unique = true,nullable = false)
    private String name;
}