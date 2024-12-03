package com.example.controls.dao.services;

import com.example.controls.dao.InversionistaDao;
import com.example.models.Inversionista;
import com.example.models.ENUM.Dni;
import com.example.controls.tda.list.LinkedList;

public class InversionistaService {
    private InversionistaDao inversionista;

    public  InversionistaService() {
        this.inversionista = new InversionistaDao();
    }

    public Boolean save() throws Exception {
        return this.inversionista.save();
    }

    public Boolean update() throws Exception {
        return this.inversionista.update();
    }

    public Boolean delete() throws Exception {
        return this.inversionista.delete();
    }


    public LinkedList listAll() {
        return this.inversionista.getListAll();
    }

    public Inversionista getInversionista() {
        return this.inversionista.getInversionista();
    }

    public void setInversionista(Inversionista inversionista) {
        this.inversionista.setInversionista(inversionista);
    }

    public Dni getDni (String tipo){
        return inversionista.getDni(tipo);
    }


    public Dni[] getTipos(String tipo){
        return getTipos(tipo);
    }
    public boolean correoUnico  (String email) throws Exception{
        return correoUnico(email);
    }
    public Inversionista get(Integer id) throws Exception{
        return this.inversionista.get(id);
    }
    // public LinkedList ordenarShellShort(String attribute, Integer type)throws Exception{
    //     return this.inversionista.listAll().ordenarShellShort(attribute, type);
    // }
    // public LinkedList ordenarMergeShort(String attribute, Integer type)throws Exception{
    //     return this.inversionista.listAll().ordenarMergeShort(attribute, type);
    // }
    // public LinkedList ordenarQuickShort(String attribute, Integer type)throws Exception{
    //     return this.inversionista.listAll().ordenarQuickShort(attribute, type);
    // }

    public LinkedList EscogerOrdenamiento(String algorithm, String attribute, Integer type) throws Exception {
        return inversionista.EscogerOrdenamiento(algorithm, attribute, type); // Llama al método sort de tu lista enlazada.
    }
    
    
}

    

