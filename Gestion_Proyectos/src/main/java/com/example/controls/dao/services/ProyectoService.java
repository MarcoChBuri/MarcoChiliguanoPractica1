package com.example.controls.dao.services;

import com.example.controls.dao.ProyectoDao;
import com.example.models.Proyecto;
import com.example.models.Proyecto;
import com.example.controls.tda.list.LinkedList;

public class ProyectoService {
    private ProyectoDao proyecto;

    public  ProyectoService() {
        this.proyecto = new ProyectoDao();
    }

    public Boolean save() throws Exception {
        return this.proyecto.save();
    }

    public Boolean update() throws Exception {
        return this.proyecto.update();
    }

    public Boolean delete() throws Exception {
        return this.proyecto.delete();
    }

    public Proyecto get(Integer id) throws Exception{
        return this.proyecto.get(id);
    }

    public LinkedList listAll() {
        return this.proyecto.getListAll();
    }

    public Proyecto getProyecto() {
        return this.proyecto.getProyecto();
    }

     public void setProyecto(Proyecto proyecto) {
         this.proyecto.setProyecto(proyecto);
     }

     public LinkedList<Proyecto> ordenar(String criterio, boolean ascendente){
        return this.ordenar(criterio, ascendente);
     }

         public LinkedList<Proyecto> EscogerOrdenamiento(String algorithm, String attribute, Integer type) throws Exception {
        return this.proyecto.getListAll().tipo(algorithm, attribute, type); // Llama al método sort de tu lista enlazada.
    }
    public LinkedList<Proyecto>  SearchByBinary(String attribute, String value)throws Exception{
        return this.proyecto.getListAll().binarySearch(attribute, value);
    }
    public LinkedList<Proyecto> SearchLinear(String attribute, String value)throws Exception{
        return this.proyecto.getListAll().linearSearch(attribute, value);

    }
}

    

