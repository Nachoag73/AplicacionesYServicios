package com.uma.example.springuma.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // para ignorar el serializador al devolver un objeto cuenta
public class Raza
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;    

    @Column(name = "nombre")
    private String nombre;

    private String tamanyo;

    private int amabilidad;

    private int pelosidad; 
    

    public int getPelosidad() {
        return pelosidad;
    }

    public void setPelosidad(int pelosidad) {
        this.pelosidad = pelosidad;
    }

    public int getAmabilidad() {
        return amabilidad;
    }

    public void setAmabilidad(int amabilidad) {
        this.amabilidad = amabilidad;
    }

    public String getTamanyo() {
        return tamanyo;
    }

    public void setTamanyo(String tamanyo) {
        this.tamanyo = tamanyo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean equals(Object obj)
    {
	    return (obj instanceof Gato) && ((Gato) obj).getNombre()==this.nombre;
    }

    public String toString()
    {
	    return "Nombre: " + this.nombre + " tamanyo: " + this.tamanyo + " amabilidad: " + this.amabilidad + " pelosidad: " + this.pelosidad;
    }
}
