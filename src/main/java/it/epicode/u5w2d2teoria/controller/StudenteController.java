package it.epicode.u5w2d2teoria.controller;

import it.epicode.u5w2d2teoria.exception.StudenteNonTrovatoException;
import it.epicode.u5w2d2teoria.model.Studente;
import it.epicode.u5w2d2teoria.service.StudenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class StudenteController {
    @Autowired
    private StudenteService studenteService;

    @PostMapping("/api/studenti")
    @ResponseStatus(HttpStatus.CREATED)
    public String saveStudente(@RequestBody Studente studente){
        return studenteService.saveStudente(studente);
    }
    @GetMapping("/api/studenti")
    public List<Studente> getAllStudenti(){
        return studenteService.getAllStudenti();
    }
    @GetMapping("/api/studenti/{matricola}")
    public Studente getStudenteByMatricola(@PathVariable int matricola) throws StudenteNonTrovatoException{
        Optional<Studente> studenteOpt = studenteService.getStudenteByMatricola(matricola);

        if(studenteOpt.isPresent()){
            return studenteOpt.get();
        }
        else{
            throw new StudenteNonTrovatoException("Studente con matricola " + matricola + " non trovato");
        }
    }
    @PutMapping(path = "/api/studenti/{matricola}",produces = MediaType.APPLICATION_XML_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Studente updateStudente(@PathVariable int matricola,@RequestBody Studente studente) throws StudenteNonTrovatoException{
        return studenteService.updateStudente(matricola, studente);
    }
    @DeleteMapping("/api/studenti/{matricola}")
    public String deleteStudente(@PathVariable int matricola) throws StudenteNonTrovatoException{
        return studenteService.deleteStudente(matricola);
    }





//    @GetMapping("/api")
//    public String benvenuto(){
//        return "benvenuto";
//    }
//
//    @GetMapping("/api/nome_cognome") //gestisce le richieste con i query params (?nome=Sara&cognome=De Sio)
//    public String benvenuto2(@RequestParam String nome, String cognome){
//        return "benvenuto " + nome + " " + cognome;
//    }
//    @GetMapping("/api/{nome}/{cognome}") //tra parentesi graffe si mette la parte del path che sarà variabile
//    public String benvenuto3(@PathVariable String nome, @PathVariable String cognome){
//        return "benvenuto " + nome + " " + cognome;
//    }
//
//    @GetMapping("/api/body")//@RequestBody fa capire a Spring che i dati deve recuperarli dal body della richiesta
//    public String benvenuto4(@RequestBody String nomeCognome){
//        return "benvenuto " + nomeCognome;
//    }

}
