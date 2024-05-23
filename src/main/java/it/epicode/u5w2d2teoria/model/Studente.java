package it.epicode.u5w2d2teoria.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class Studente {
    @Id
    @GeneratedValue
    private int matricola;
    //variabile statica per mantenere un valore comune tra tutti gli studenti
    //private static int cont;
    private String nome;
    private String cognome;
    private LocalDate dataNascita;

    private String foto;
    private String email;

    @ManyToOne
    @JoinColumn(name = "aula_id")
    @JsonIgnore
    private Aula aula;

//    public Studente(String nome, String cognome, LocalDate dataNascita) {
//        this.nome = nome;
//        this.cognome = cognome;
//        this.dataNascita = dataNascita;
//        cont++;//incremento cont che essendo statica viene incrementata in ogni oggetto della classe
//        matricola=cont; //matricola prenderà il valore di cont, il passaggio è per valore.
//        //matricola non è statica e quindi è indipendente da oggetto ad oggetto. Il suo valore
//        //viene mantenuto uguale a cont nel momento in cui viene assegnato e non cambia se il valore
//        //di cont cambia successivamente
//    }
}
