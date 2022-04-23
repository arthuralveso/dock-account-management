package com.pi.accountmanagement.dtos;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Date;

@Data
public class PeopleDTO {

    @NotBlank
    @Size(max = 40)
    private String nome;

    @NotBlank
    @Size(max = 11)
    private String cpf;

    @NotNull
    private LocalDate dataNascimento;

}
