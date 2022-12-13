package com.uma.example.springuma.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RazaService {

    @Autowired
    RepositoryRaza repositoryraza;

    public List<Raza> getAllRazas(){
        return repositoryraza.findAll();
    }

    public Raza getRaza(Long id){
        return repositoryraza.getReferenceById(id);
    }

    public Raza addRaza(Raza c){
        return repositoryraza.saveAndFlush(c);
    }

    public void updateRaza(Raza c){
        Raza raza = repositoryraza.getReferenceById(c.getId());
		raza.setNombre(c.getNombre());
		raza.setAmabilidad(c.getAmabilidad());
		raza.setPelosidad(c.getPelosidad());
		raza.setTamanyo(c.getTamanyo());
        repositoryraza.saveAndFlush(raza);
    }

    public void removeRaza(Raza c){
        repositoryraza.delete(c);
    }
}
