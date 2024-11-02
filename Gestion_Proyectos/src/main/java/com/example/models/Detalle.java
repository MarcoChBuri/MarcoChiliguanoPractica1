package com.example.models;

public class Detalle {
    private int idDetalle;
    private int idProyecto;
    private double costos;
    private double electricidadGenerada;
    private double inversionTotal;
    // Constructor
    public Detalle() {
        // Constructor por defecto
    }
    public Detalle (int idDetalle, int idProyecto, double costos, double electricidadGenerada, double inversionTotal) {
        this.idDetalle = idDetalle;
        this.idProyecto = idProyecto;
        this.costos = costos;
        this.electricidadGenerada = electricidadGenerada;
        this.inversionTotal = inversionTotal;
    }

    public int getIdDetalle() {
        return this.idDetalle;
    }

    public void setIdDetalle(int idDetalle) {
        this.idDetalle = idDetalle;
    }

    public int getIdProyecto() {
        return this.idProyecto;
    }

    public void setIdProyecto(int idProyecto) {
        this.idProyecto = idProyecto;
    }

    public double getCostos() {
        return this.costos;
    }

    public void setCostos(double costos) {
        this.costos = costos;
    }

    public double getElectricidadGenerada() {
        return this.electricidadGenerada;
    }

    public void setElectricidadGenerada(double electricidadGenerada) {
        this.electricidadGenerada = electricidadGenerada;
    }

    public double getInversionTotal() {
        return this.inversionTotal;
    }

    public void setInversionTotal(double inversionTotal) {
        this.inversionTotal = inversionTotal;
    }

    

    // Getters and Setters
    // toString, equals, hashCode
}
