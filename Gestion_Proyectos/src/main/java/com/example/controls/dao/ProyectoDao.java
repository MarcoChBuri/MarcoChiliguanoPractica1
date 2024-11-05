package com.example.controls.dao;

import java.util.Arrays;

import com.example.controls.dao.Implement.AdapterDao;
import com.example.models.Proyecto;
import com.example.controls.dao.Implement.InterfazDao;
import com.example.controls.tda.list.LinkedList;


public class ProyectoDao implements InterfazDao<Proyecto> {
    private LinkedList<Proyecto> proyectos;
    private AdapterDao<Proyecto> adapterDao;

    public ProyectoDao() {
        this.proyectos = new LinkedList<>();
        this.adapterDao = new AdapterDao<>(Proyecto.class);
        this.proyectos = adapterDao.listAll();
        System.out.println("Proyectos cargados en el constructor: " + proyectos);
    }
    
    public void setProyecto(Proyecto proyecto) {
        proyectos.add(proyecto);
    }

    @Override
    public LinkedList<Proyecto> listAll() {
        if (proyectos.isEmpty()) {
            this.proyectos = adapterDao.listAll();
        }

        return proyectos;
    }
    

    @Override
    public void persist(Proyecto proyecto) throws Exception {
        proyectos.add(proyecto);
        adapterDao.persist(proyecto); 
    }

    @Override
    public void merge(Proyecto proyecto, Integer index) throws Exception {
        proyectos.update(proyecto, index);
        adapterDao.merge(proyecto, index); 
    }

    @Override
    public Proyecto get(Integer id) throws Exception {
        return proyectos.get(id - 1); 
    }

    @Override
    public void delete(Integer id) throws Exception {
        proyectos.delete(id);
        adapterDao.delete(id); 
    }
}
