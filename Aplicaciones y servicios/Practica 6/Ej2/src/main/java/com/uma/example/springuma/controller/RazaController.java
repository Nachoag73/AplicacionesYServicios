package com.uma.example.springuma.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.uma.example.springuma.model.Raza;
import com.uma.example.springuma.model.RazaService;

@RestController
public class RazaController {
    
    @Autowired
    private RazaService razaService;

    @GetMapping("/razas")//consultar todos los gatos
    public List<Raza> getRaza(){
        return razaService.getAllRazas();
    }

    @GetMapping("/raza/{id}")//consultar gato por id
    public Raza getGato(@PathVariable("id") Long id) {
        return razaService.getRaza(id);
    }

    @PostMapping(value = "/razas",  consumes = "application/json")//añadir gato
	public ResponseEntity<?> save(@RequestBody Raza raza) {
        if (razaService.addRaza(raza)==null)
            return ResponseEntity.internalServerError().body("La raza ya existe");
        else{
            return ResponseEntity.ok().body("Una nueva raza se ha anyadido");
        }
	}

    @DeleteMapping("/raza/{id}")//eliminar gato por id
    public ResponseEntity<?> deleteGato(@PathVariable("id") Long id){
        Raza raza = razaService.getRaza(id);
        razaService.removeRaza(raza);
        return ResponseEntity.ok().body("Raza eliminada con exito");
    }

    @PutMapping(value = "/raza/{id}",  consumes = "application/json")
    public ResponseEntity<?> update(@RequestBody Raza raza) {
        razaService.updateRaza(raza);
        return ResponseEntity.ok().body("Raza actualizada");
        
	}
    

}
