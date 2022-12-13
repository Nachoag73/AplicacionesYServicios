package com.uma.example.springuma.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // para ignorar el serializador al devolver un objeto cuenta
public class Gato
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) 
    private long id;

    @Column(name = "nombre")
    private String nombre;

    private String color;
    private long edad;

    @OneToOne
    private Raza raza;

    public Raza getRaza() {
        return raza;
    }

    public void setRaza(Raza raza) {
        this.raza = raza;
    }

    public long getEdad() {
        return edad;
    }

    public void setEdad(long edad) {
        this.edad = edad;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean equals(Object obj)
    {
	    return (obj instanceof Gato) && ((Gato) obj).getNombre()==this.nombre && ((Gato) obj).getEdad()==this.edad && ((Gato) obj).getColor()==this.color;
    }

    public String toString()
    {
	    return "Nombre: " + this.nombre + " color: " + this.color + " edad: " + this.edad;
    }
}
