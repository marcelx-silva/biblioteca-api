package com.newgo.bibliotecaapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BookAuthorDTO {
    @NotBlank
    @Min(value = 10)
    private String title;
    @NotBlank
    private String genre;
    @NotBlank
    private String isbn13;
}
