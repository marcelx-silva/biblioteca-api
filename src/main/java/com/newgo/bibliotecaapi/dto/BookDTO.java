package com.newgo.bibliotecaapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Set;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {
    @NotBlank
    @Min(value = 10)
    private String title;
    @NotBlank
    private String genre;
    @NotBlank
    private String publisher;
    @NotBlank
    private String language;
    @NotBlank
    private Integer pages;
    @NotBlank
    private String isbn13;
    private Set<UUID> authors;


}
