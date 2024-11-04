package com.example.controls.dao.services;

import com.example.controls.dao.DetalleProyectoDao;
import com.example.models.DetalleProyecto;
import com.example.controls.tda.list.LinkedList;

public class DetalleProyectoService {
    private DetalleProyectoDao obj;

    public DetalleProyectoService() {
        obj = new DetalleProyectoDao();
    }

    public DetalleProyectoService(DetalleProyectoDao detalleProyectoDao) {
        obj = detalleProyectoDao;
    }
    
    public Boolean save(DetalleProyecto detalleProyecto) throws Exception {
        return obj.save(detalleProyecto);
    }

    public LinkedList<DetalleProyecto> listAll() {
        return obj.getListAll();
    }

    public DetalleProyecto get(Integer id) {
        return obj.getDetalleProyecto();
    }

    public void setDetalleProyecto(DetalleProyecto detalleProyecto) {
        obj.setDetalleProyecto(detalleProyecto);
    }

    public Boolean updateByIndex(Integer index, DetalleProyecto detalleProyecto) throws Exception {
        return obj.updateByIndex(index, detalleProyecto);
    }

    public Boolean deleteByIndex(Integer index) throws Exception {
        return obj.deleteByIndex(index);
    }
}
