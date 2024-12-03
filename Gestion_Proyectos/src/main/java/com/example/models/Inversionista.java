package com.example.models;
import com.example.models.ENUM.*;
public class Inversionista {
    private Integer id;
    private String nombre;
    private String apellido;
    private String identification;
    private String email;
    private Dni tipo;

  

    public Inversionista() {
        this.id = 0;
        this.nombre = "";
        this.apellido = "";
        this.identification = "";
        this.email = "";

    }
    public Integer getId() {
        return this.id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return this.apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }



    public String getIdentification() {
        return this.identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Dni getTipo() {
        return this.tipo;
    }

    public void setTipo(Dni tipo) {
        this.tipo = tipo;
    }
}
