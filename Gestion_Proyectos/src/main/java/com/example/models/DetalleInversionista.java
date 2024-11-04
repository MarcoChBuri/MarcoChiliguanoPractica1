package com.example.models;

import java.util.List;

public class DetalleInversionista {
    private Integer idDetalleInversionista;



    private Inversionista inversionista;
    private List<Proyecto> proyectos; 
    private List<Double> montosInversion; 
    public DetalleInversionista(){

    }
    
    public DetalleInversionista(Integer idDetalleInversionista,Inversionista inversionista, List<Proyecto> proyectos, List<Double> montosInversion) {
        this.idDetalleInversionista = idDetalleInversionista;
        this.inversionista = inversionista;
        this.proyectos = proyectos;
        this.montosInversion = montosInversion;
    }

    public Integer getIdDetalleInversionista() {
        return this.idDetalleInversionista;
    }

    public void setIdDetaleInversionista(Integer idDetaleInversionista) {
        this.idDetalleInversionista = idDetaleInversionista;
    }
    public Inversionista getInversionista() {
        return inversionista;
    }

    public void setInversionista(Inversionista inversionista) {
        this.inversionista = inversionista;
    }

    public List<Proyecto> getProyectos() {
        return proyectos;
    }

    public void setProyectos(List<Proyecto> proyectos) {
        this.proyectos = proyectos;
    }

    public List<Double> getMontosInversion() {
        return montosInversion;
    }

    public void setMontosInversion(List<Double> montosInversion) {
        this.montosInversion = montosInversion;
    }
}
