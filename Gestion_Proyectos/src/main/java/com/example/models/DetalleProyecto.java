package com.example.models;

import java.util.List;

public class DetalleProyecto {
    private Integer idDetalle;
    private Proyecto proyecto;
    private double inversionTotal; // Monto total de la inversión en el proyecto
    private List<Inversionista> inversionistas; // Lista de inversionistas

    public DetalleProyecto(){
        
    }

    public DetalleProyecto(Integer idDetalle,Proyecto proyecto, double inversionTotal, List<Inversionista> inversionistas) {
        this.idDetalle = idDetalle;
        this.proyecto = proyecto;
        this.inversionTotal = inversionTotal;
        this.inversionistas = inversionistas;
    }

    // Getters y Setters

    public Integer getIdDetalle() {
        return this.idDetalle;
    }

    public void setIdDetalle(Integer idDetalle) {
        this.idDetalle = idDetalle;
    }
    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    public double getInversionTotal() {
        return inversionTotal;
    }

    public void setInversionTotal(double inversionTotal) {
        this.inversionTotal = inversionTotal;
    }

    public List<Inversionista> getInversionistas() {
        return inversionistas;
    }

    public void setInversionistas(List<Inversionista> inversionistas) {
        this.inversionistas = inversionistas;
    }
}
