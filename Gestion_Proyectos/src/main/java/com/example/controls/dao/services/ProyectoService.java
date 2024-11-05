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
        obj = proyectoDao;
    }

    public void save(Proyecto proyecto) throws Exception {
        obj.persist(proyecto);
    }

    public LinkedList<Proyecto> listAll() {
        return obj.listAll();
    }

    public Proyecto get(Integer id) throws Exception {
        return obj.get(id);
    }

    public void updateByIndex(Integer index, Proyecto proyecto) throws Exception {
        obj.merge(proyecto, index);
    }

    public void deleteByIndex(Integer index) throws Exception {
        obj.delete(index);
    }
}
