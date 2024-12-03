package com.example.models;
import com.example.controls.tda.list.LinkedList;
import com.example.controls.dao.Implement.AdapterDao;
public class Detalle {
    private Integer id;
    private Integer tiempoVida;
    private float energiaGenerada;
    private LinkedList<Inversionista> inversionista;
    private LinkedList<Proyecto> proyecto;
    private float montoInversionista;

 
    public Detalle(){
        this.id = 0;
        this.tiempoVida = 0;
        this.energiaGenerada = 0.0F;
        this.montoInversionista= 0.0F;
        this.inversionista = new LinkedList<>();
        this.proyecto = new LinkedList<>();


    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public  LinkedList<Inversionista> getInversionista() {
        return this.inversionista;
    }

    public void setInversionista(LinkedList<Inversionista> inversionista) {
        this.inversionista = inversionista;
    }

    public float getMontoInversionista() {
        return this.montoInversionista;
    }

    public void setMontoInversionista(float montoInversionista) {
        this.montoInversionista = montoInversionista;
    }

    public LinkedList<Proyecto> getProyecto() {
        return this.proyecto;
    }

    public void setProyecto(LinkedList<Proyecto> proyecto) {
        this.proyecto = proyecto;
    }



}
