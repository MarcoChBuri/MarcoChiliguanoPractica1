package com.example.models;
import java.util.Date;
import com.example.controls.tda.list.*;

public class Proyecto {
    private Integer id;
    private String nombre;
    private Date fechaInicio;
    private Date fechaFin;
    private double inversion;

    public Proyecto (){
        
    }
    // Constructor
    public Proyecto(Integer id, String nombre, int tiempoVida, Date fechaInicio, Date fechaFin, double inversion, double energiaGenerada) {
        this.id = 0;
        this.nombre = "";
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.inversion = inversion;
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


    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }



    public double getInversion() {
        return inversion;
    }

    public void setInversion(double inversion) {
        this.inversion = inversion;
    }


}