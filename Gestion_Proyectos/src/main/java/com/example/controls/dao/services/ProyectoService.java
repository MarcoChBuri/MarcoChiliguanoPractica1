package com.example.controls.dao.services;

import com.example.controls.dao.ProyectoDao;
import com.example.models.Proyecto;
import com.example.controls.tda.list.LinkedList;

public class ProyectoService {
    private ProyectoDao obj;
    
    public ProyectoService() {
        obj = new ProyectoDao(); 
    }
    
    public ProyectoService(ProyectoDao proyectoDao) {
        obj = new ProyectoDao();
    }

    public Boolean save() throws Exception {
        return obj.save();
    }

    public LinkedList<Proyecto> listAll() {
        return obj.getListAll();
    }

    public Proyecto getProyecto() {
        return obj.getProyecto();
    }

    public void setProyecto(Proyecto proyecto) {
        obj.setProyecto(proyecto);
    }
}
