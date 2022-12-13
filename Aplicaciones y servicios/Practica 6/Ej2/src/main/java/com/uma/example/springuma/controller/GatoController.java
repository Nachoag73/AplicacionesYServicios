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

import com.uma.example.springuma.model.Gato;
import com.uma.example.springuma.model.GatoService;

@RestController
public class GatoController {
    
    @Autowired
    private GatoService gatoService;

    @GetMapping("/gatos")//consultar todos los gatos
    public List<Gato> getGatos(){
        return gatoService.getAllGatos();
    }

    @GetMapping("/gato/{id}")//consultar gato por id
    public Gato getGato(@PathVariable("id") Long id) {
        return gatoService.getGato(id);
    }

    @PostMapping(value = "/gatos",  consumes = "application/json")//añadir gato
	public ResponseEntity<?> save(@RequestBody Gato gato) {
        if (gatoService.addGato(gato)==null)
            return ResponseEntity.internalServerError().body("El gato ya existe");
        else{
            return ResponseEntity.ok().body("Un nuevo gato se ha anyadido");
        }
	}

    @DeleteMapping("/gato/{id}")//eliminar gato por id
    public ResponseEntity<?> deleteGato(@PathVariable("id") Long id){
        Gato gato = gatoService.getGato(id);
        gatoService.removeGato(gato);
        return ResponseEntity.ok().body("Gato eliminado con exito");
    }

    @PutMapping(value = "/gato/{id}",  consumes = "application/json") //modificar gato por id
    public ResponseEntity<?> update(@RequestBody Gato gato) {
        gatoService.updateGato(gato);
        return ResponseEntity.ok().body("Gato actualizado");
        
	}
    


}
