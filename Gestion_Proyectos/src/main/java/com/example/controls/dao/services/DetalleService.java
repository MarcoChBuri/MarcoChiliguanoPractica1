package com.example.controls.dao.services;

import com.example.controls.dao.DetalleDao;
import com.example.models.Detalle;
import com.example.controls.tda.list.LinkedList;

public class DetalleService {
    private DetalleDao obj;
    public DetalleService() {
        obj = new DetalleDao(); // Inicializa obj con una instancia de DetalleDao
    }
    

    public DetalleService(DetalleDao detalleDao) {
        obj = new DetalleDao();
    }

    public Boolean save() throws Exception {
        return obj.save();
    }

    public LinkedList<Detalle> listAll() {
        return obj.getListAll();
    }

    public Detalle getDetalle() {
        return obj.getDetalle();
    }

    public void setDetalle(Detalle detalle) {
        obj.setDetalle(detalle);
    }
}
