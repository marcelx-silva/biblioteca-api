package com.newgo.bibliotecaapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class AuthorDTO {
    @NotBlank
    @Min(value = 10)
    private String name;
    @Past
    private LocalDate birthDate;
}
