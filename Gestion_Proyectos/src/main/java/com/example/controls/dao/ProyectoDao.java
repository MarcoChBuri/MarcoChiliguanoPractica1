package com.example.controls.dao;

import com.example.controls.dao.Implement.AdapterDao;
import com.example.controls.tda.list.LinkedList;
import com.example.models.Proyecto;


public class ProyectoDao extends AdapterDao<Proyecto> {
    private Proyecto proyecto;
    private LinkedList listAll;

    public ProyectoDao() {
        super(Proyecto.class);
    }
    
    public Proyecto getProyecto() {
        if(this.proyecto == null) {
            this.proyecto = new Proyecto();
        }

        return this.proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    public LinkedList getListAll() {
        if (listAll == null) {
            this.listAll = listAll();
        }
        return this.listAll;
    }

    public Boolean save() throws Exception {
        Integer id = getListAll().getSize() +1;
        try {
            proyecto.setId(id);
            this.persist(this.proyecto);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Boolean update() throws Exception {
        try {
            this.merge(this.proyecto, this.proyecto.getId()-1);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Boolean delete() throws Exception {
        try {
            this.delete(this.proyecto.getId()-1);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    
}
