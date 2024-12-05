package com.example.models;
import com.example.controls.tda.list.*;

public class Proyecto {
    private Integer id;
    private String nombre;
    private String fechaInicio;
    private String fechaFin;
    private float inversion;
    private Integer tiempoVida;
    private float energiaGenerada;

    

    // Constructor
    public Proyecto( ) {
        this.id = 0;
        this.nombre = "";
        this.fechaInicio = "";
        this.fechaFin = "";
        this.inversion = 0.0F;
        this.tiempoVida = 0;
        this.energiaGenerada = 0.0F;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }



    public double getInversion() {
        return inversion;
    }

    public void setInversion(float inversion) {
        this.inversion = inversion;
    }
    public Integer getTiempoVida() {
        return this.tiempoVida;
    }

    public void setTiempoVida(Integer tiempoVida) {
        this.tiempoVida = tiempoVida;
    }

    public float getEnergiaGenerada() {
        return this.energiaGenerada;
    }

    public void setEnergiaGenerada(float energiaGenerada) {
        this.energiaGenerada = energiaGenerada;
    }


}