package com.example.controls.dao;

import com.example.controls.dao.Implement.AdapterDao;
import com.example.models.Proyecto;
import com.example.controls.tda.list.LinkedList;

public class ProyectoDao extends AdapterDao<Proyecto> {
    private Proyecto proyecto;
    private LinkedList<Proyecto> listAll;

    public ProyectoDao() {
        super(Proyecto.class);
    }

    public Proyecto getProyecto() {
        if (proyecto == null) {
            proyecto = new Proyecto();
        }
        return this.proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    public LinkedList<Proyecto> getListAll() {
        if (listAll == null) {
            this.listAll = listAll();
        }
        return listAll;
    }

    public Boolean save() throws Exception {
        Integer id = getListAll().getSize() + 1;
        proyecto.setIdProyecto(id);
        this.persist(this.proyecto);
        return true;
    }
}
