package com.example.controls.dao;

import com.example.controls.dao.Implement.AdapterDao;
import com.example.controls.tda.list.LinkedList;
import com.example.models.Detalle;


public class DetalleDao extends AdapterDao<Detalle> {
    private Detalle Detalle;
    private LinkedList listAll;

    public DetalleDao() {
        super(Detalle.class);
    }
    
    public Detalle getDetalle() {
        if(this.Detalle == null) {
            this.Detalle = new Detalle();
        }

        return this.Detalle;
    }

    public void setDetalle(Detalle Detalle) {
        this.Detalle = Detalle;
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
            Detalle.setId(id);
            this.persist(this.Detalle);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Boolean update() throws Exception {
        try {
            this.merge(this.Detalle, this.Detalle.getId()-1);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Boolean delete() throws Exception {
        try {
            this.delete(this.Detalle.getId()-1);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    

    // public LinkedList order(Integer typeOrder, String atributo) {
    //     LinkedList list = listAll();
    //     if (!listAll().isEmpty()) {
    //         Detalle[] listPerson = (Detalle[])list.toArray();
    //         list.reset();
    //         switch (typeOrder) {
    //             case 1: //ASCENDENTE
    //                 for (int i = 0; i < listPerson.length; i++) {
    //                     Detalle aux = listPerson[i];
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
    //                     Detalle aux = listPerson[i];
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

    // private Boolean verify(Detalle a, Detalle b, Integer typeOrder, String atributo) {
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

