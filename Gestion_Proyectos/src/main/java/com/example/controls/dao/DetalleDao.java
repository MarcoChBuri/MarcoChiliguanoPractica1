package com.example.controls.dao;
import com.example.controls.dao.Implement.AdapterDao;
import com.example.controls.dao.DetalleDao;
import com.example.models.Detalle;
import com.example.controls.tda.list.LinkedList;

public class DetalleDao extends AdapterDao<Detalle> {
    private Detalle detalle;
    private LinkedList<Detalle> listAll;

    public DetalleDao() {
        super(Detalle.class);
    }

    public Detalle getDetalle() {
        if (detalle == null) {
            detalle = new Detalle();
        }
        return this.detalle;
    }

    public void setDetalle(Detalle detalle) {
        this.detalle = detalle;
    }

    public LinkedList<Detalle> getListAll() {
        if (listAll == null) {
            this.listAll = listAll();
        }
        return listAll;
    }

    public Boolean save() throws Exception {
        Integer id = getListAll().getSize() + 1;
        detalle.setIdDetalle(id);
        this.persist(this.detalle);
        return true;
    }
}
