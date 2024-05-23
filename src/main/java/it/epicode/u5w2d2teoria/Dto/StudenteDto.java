package it.epicode.u5w2d2teoria.Dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class StudenteDto {

    @Size(max = 30, min = 1)
    @NotBlank
    private String nome;
    @NotNull
    @Size(max = 30)
    private String cognome;
    private LocalDate dataNascita;
    @Email
    @NotNull
    private String email;

    @NotNull
    private int aulaId;


}
