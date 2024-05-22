package it.epicode.u5w2d2teoria.service;

import it.epicode.u5w2d2teoria.Dto.AulaDto;
import it.epicode.u5w2d2teoria.exception.AulaNonTrovataException;
import it.epicode.u5w2d2teoria.model.Aula;
import it.epicode.u5w2d2teoria.repository.AulaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AulaService {
    @Autowired
    private AulaRepository aulaRepository;

    public String saveAula(AulaDto aulaDto){
        Aula aula = new Aula();
        aula.setNome(aulaDto.getNome());
        aula.setPiano(aulaDto.getPiano());

        aulaRepository.save(aula);
        return "Aula con id=" + aula.getId() + " creata con successo";
    }

    public List<Aula> getAule(){
        return aulaRepository.findAll();
    }

    public Optional<Aula> getAulaById(int id){
        return aulaRepository.findById(id);
    }

    public Aula updateAula(int id, AulaDto aulaDto){
        Optional<Aula> aulaOptional = getAulaById(id);

        if(aulaOptional.isPresent()){
            Aula aula = aulaOptional.get();
            aula.setNome(aulaDto.getNome());
            aula.setPiano(aulaDto.getPiano());

            return aulaRepository.save(aula);
        }
        else{
            throw new AulaNonTrovataException("Aula con id=" + id + " non trovata");
        }
    }

    public String deleteAula(int id){
        Optional<Aula> aulaOptional = getAulaById(id);

        if(aulaOptional.isPresent()){
            aulaRepository.delete(aulaOptional.get());
            return "Aula con id=" + id + " cancellata con successo";
        }
        else{
            throw new AulaNonTrovataException("Aula con id=" + id + " non trovata");
        }
    }

}
