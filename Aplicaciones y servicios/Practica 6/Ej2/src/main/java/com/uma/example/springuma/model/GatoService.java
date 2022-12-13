package com.uma.example.springuma.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GatoService {

    @Autowired
    RepositoryGato repositorygato;

    public List<Gato> getAllGatos(){
        return repositorygato.findAll();
    }

    public Gato getGato(Long id){
        return repositorygato.getReferenceById(id);
    }

    public Gato addGato(Gato c){
        return repositorygato.saveAndFlush(c);
    }

    public void updateGato(Gato c){
        Gato gato = repositorygato.getReferenceById(c.getId());
		gato.setNombre(c.getNombre());
		gato.setColor(c.getColor());
		gato.setEdad(c.getEdad());
        gato.setRaza(c.getRaza());
        repositorygato.saveAndFlush(gato);
    }

    public void removeGato(Gato c){
        repositorygato.delete(c);
    }
}
