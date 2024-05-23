package it.epicode.u5w2d2teoria.controller;

import it.epicode.u5w2d2teoria.Dto.StudenteDto;
import it.epicode.u5w2d2teoria.exception.BadRequestException;
import it.epicode.u5w2d2teoria.exception.StudenteNonTrovatoException;
import it.epicode.u5w2d2teoria.model.Studente;
import it.epicode.u5w2d2teoria.service.StudenteService;
import it.epicode.u5w2d2teoria.service.StudenteServiceList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
public class StudenteController {
    @Autowired
    private StudenteService studenteService;

    @PostMapping("/api/studenti")
    @ResponseStatus(HttpStatus.CREATED)
    public String saveStudente(@RequestBody @Validated StudenteDto studenteDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().stream().
                    map(objectError -> objectError.getDefaultMessage()).reduce("", ((s, s2) -> s+s2)));
        }

        return studenteService.saveStudente(studenteDto);
    }
    @GetMapping("/api/studenti")
    public Page<Studente> getAllStudenti(@RequestParam(defaultValue = "0") int page,
                                         @RequestParam(defaultValue = "15") int size,
                                         @RequestParam(defaultValue = "matricola") String sortBy){

        return studenteService.getStudenti(page, size, sortBy);
    }
    @GetMapping("/api/studenti/{matricola}")
    public Studente getStudenteByMatricola(@PathVariable int matricola) throws StudenteNonTrovatoException{
        Optional<Studente> studenteOpt = studenteService.getStudenteById(matricola);

        if(studenteOpt.isPresent()){
            return studenteOpt.get();
        }
        else{
            throw new StudenteNonTrovatoException("Studente con matricola " + matricola + " non trovato");
        }
    }
    @PutMapping(path = "/api/studenti/{matricola}")
    @ResponseStatus(HttpStatus.OK)
    public Studente updateStudente(@PathVariable int matricola,@RequestBody @Validated StudenteDto studenteDto, BindingResult bindingResult) throws StudenteNonTrovatoException{
        if(bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().stream().
                    map(objectError -> objectError.getDefaultMessage()).reduce("", ((s, s2) -> s+s2)));
        }

        return studenteService.updateStudente(matricola, studenteDto);
    }
    @DeleteMapping("/api/studenti/{matricola}")
    public String deleteStudente(@PathVariable int matricola) throws StudenteNonTrovatoException{
        return studenteService.deleteStudente(matricola);
    }
    @PatchMapping("/api/studenti/{matricola}")
    public String patchFotoStudente(@RequestBody MultipartFile foto, @PathVariable int matricola) throws IOException {
        return studenteService.patchFotoStudente(matricola, foto);
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
