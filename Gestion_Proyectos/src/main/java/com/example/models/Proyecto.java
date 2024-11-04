package com.example.models;

import java.util.Date;
import java.util.List;

public class Proyecto {
    private Integer idProyecto;
    private String nombre;
    private int tiempoVida;
    private Date fechaInicio;
    private Date fechaFin;
    private List<Inversionista> inversionistas;

    public Proyecto (){
        
    }

    public Proyecto(Integer idProyecto, String nombre, int tiempoVida, Date fechaInicio, Date fechaFin, List<Inversionista> inversionistas) {
        this.idProyecto = idProyecto;
        this.nombre = nombre;
        this.tiempoVida = tiempoVida;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.inversionistas = inversionistas;
    }


    public Integer getIdProyecto() {
        return this.idProyecto;
    }

    public void setIdProyecto(Integer idProyecto) {
        this.idProyecto = idProyecto;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTiempoVida() {
        return this.tiempoVida;
    }

    public void setTiempoVida(int tiempoVida) {
        this.tiempoVida = tiempoVida;
    }

    public Date getFechaInicio() {
        return this.fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return this.fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public List<Inversionista> getInversionistas() {
        return this.inversionistas;
    }

    public void setInversionistas(List<Inversionista> inversionistas) {
        this.inversionistas = inversionistas;
    }

    public void addInversionista(Inversionista inversionista) {
        this.inversionistas.add(inversionista);
    }


}

