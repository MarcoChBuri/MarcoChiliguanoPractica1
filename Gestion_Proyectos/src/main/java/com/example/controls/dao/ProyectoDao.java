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

    // public LinkedList order(Integer typeOrder, String atributo) {
    //     LinkedList list = listAll();
    //     if (!listAll().isEmpty()) {
    //         Proyecto[] listPerson = (Proyecto[])list.toArray();
    //         list.reset();
    //         switch (typeOrder) {
    //             case 1: //ASCENDENTE
    //                 for (int i = 0; i < listPerson.length; i++) {
    //                     Proyecto aux = listPerson[i];
    //                     int j = i - 1;
    //                     while (j >= 0 && verify(listPerson[j], aux, typeOrder, atributo)) {
    //                         listPerson[j + 1] = listPerson[j--];
    //                     }
    //                     listPerson[j + 1] = aux;
    //                 }
    //                 list.toList(listPerson);
    //                 break;
            
    //             default: //DESCENDENTE
    //                 for (int i = 0; i < listPerson.length; i++) {
    //                     Proyecto aux = listPerson[i];
    //                     int j = i - 1;
    //                     while (j >= 0 && verify(listPerson[j], aux, typeOrder, atributo)) {
    //                         listPerson[j + 1] = listPerson[j--];
    //                     }
    //                     listPerson[j + 1] = aux;
    //                 }
    //                 list.toList(listPerson);
    //                 break;
    //         }
            
    //     }
    //     return list;
    // }

    // private Boolean verify(Proyecto a, Proyecto b, Integer typeOrder, String atributo) {
    //     if (typeOrder == 1) {
    //         if (atributo.equalsIgnoreCase("apellido")) {
    //             return a.getApellido().compareTo(b.getApellido()) > 0;
    //         } else if (atributo.equalsIgnoreCase("nombre")) {
    //             return a.getNombre().compareTo(b.getNombre()) > 0;
    //         }
    //     } else {
    //         if (atributo.equalsIgnoreCase("apellido")) {
    //             return a.getApellido().compareTo(b.getApellido()) < 0;
    //         } else if (atributo.equalsIgnoreCase("nombre")) {
    //             return a.getNombre().compareTo(b.getNombre()) < 0;
    //         }
    //     }
    //     return false;
    // }
}
