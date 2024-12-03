package com.example.models;

public class Inversionista {
    private Integer idInversionista;
    private String name;
    private String lastName;
    private String identification;
    private String direction;

    public Inversionista() {}

    public Inversionista(Integer idInversionista, String name, String lastName, String identification, String direction) {
        this.idInversionista = idInversionista;
        this.name = name;
        this.lastName = lastName;
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
