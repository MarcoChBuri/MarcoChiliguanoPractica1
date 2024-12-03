package com.example.controls.dao.services;


import com.example.controls.dao.DetalleDao;
import com.example.models.Detalle;
import com.example.controls.tda.list.LinkedList;

public class DetalleService {
    private DetalleDao detalle;

    public  DetalleService() {
        this.detalle = new DetalleDao();
    }

    public Boolean save() throws Exception {
        return this.detalle.save();
    }

    public Boolean update() throws Exception {
        return this.detalle.update();
    }

    public Boolean delete() throws Exception {
        return this.detalle.delete();
    }

    public Detalle get(Integer id) throws Exception{
        return this.detalle.get(id);
    }

    public LinkedList listAll() {
        return this.detalle.getListAll();
    }

    public Detalle getDetalle() {
        return this.detalle.getDetalle();
    }

    public void setDetalle(Detalle detalle) {
        this.detalle.setDetalle(detalle);
    }
    
    // public LinkedList order(Integer typeOrder, String atributo) {
    //     return this.detalle.order(typeOrder, atributo);
    // }
}

    

