package com.example.models;
import com.example.models.ENUM.Dni;

public class Inversionista {
    private Integer idInversionista;
    private String name;
    private String lastName;
    private Dni dni;
    private String identification;
    private String direction;
    public Inversionista(){

    }

    public Inversionista(Integer idInversionista, String name, String lastName, Dni dni, String identification, String direction) {
        this.idInversionista = idInversionista;
        this.name = name;
        this.lastName = lastName;
        this.dni = dni;
        this.identification = identification;
        this.direction = direction;
    }
    public Integer getId() {
        return idInversionista;
    }
    public void setId(Integer idInversionista) {
        this.idInversionista = idInversionista;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Dni getDni() {
        return this.dni;
    }

    public void setDni(Dni dni) {
        this.dni = dni;
    }

    public String getIdentification() {
        return this.identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public String getDirection() {
        return this.direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

}
