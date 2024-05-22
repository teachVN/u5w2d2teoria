package it.epicode.u5w2d2teoria.Dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.time.LocalDate;

@Data
public class StudenteDto {
    private String nome;
    private String cognome;
    private LocalDate dataNascita;


    private int aulaId;
}
