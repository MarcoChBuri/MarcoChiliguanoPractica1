package com.example.models;

import java.util.Date;
import com.example.list.ListArray; // Asegúrate de que esta importación sea correcta.

public class Proyecto {
    private Integer idProyecto;
    private String nombre;
    private int tiempoVida;
    private Date fechaInicio;
    private Date fechaFin;
    private ListArray<Inversionista> inversionistas; // Cambiar a ListArray
    private double inversion;
    private double energiaGenerada;

    public Proyecto(Integer idProyecto, String nombre, int tiempoVida, Date fechaInicio, Date fechaFin, ListArray<Inversionista> inversionistas, double inversion, double energiaGenerada) {
        this.idProyecto = idProyecto;
        this.nombre = nombre;
        this.tiempoVida = tiempoVida;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.inversionistas = inversionistas;
        this.inversion = inversion;
        this.energiaGenerada = energiaGenerada;
    }

    public Integer getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(Integer idProyecto) {
        this.idProyecto = idProyecto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTiempoVida() {
        return tiempoVida;
    }

    public void setTiempoVida(int tiempoVida) {
        this.tiempoVida = tiempoVida;
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

    public ListArray<Inversionista> getInversionistas() {
        return inversionistas;
    }

    public void setInversionistas(ListArray<Inversionista> inversionistas) {
        this.inversionistas = inversionistas;
    }

    public double getInversion() {
        return inversion;
    }

    public void setInversion(double inversion) {
        this.inversion = inversion;
    }

    public double getEnergiaGenerada() {
        return energiaGenerada;
    }

    public void setEnergiaGenerada(double energiaGenerada) {
        this.energiaGenerada = energiaGenerada;
    }
}
