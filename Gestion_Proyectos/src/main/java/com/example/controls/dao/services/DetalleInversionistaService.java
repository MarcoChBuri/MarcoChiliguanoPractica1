package com.example.controls.dao.services;

import com.example.controls.dao.DetalleInversionistaDao;
import com.example.models.DetalleInversionista;
import com.example.controls.tda.list.LinkedList;

public class DetalleInversionistaService {
    private DetalleInversionistaDao obj;

    public DetalleInversionistaService() {
        obj = new DetalleInversionistaDao();
    }

    public DetalleInversionistaService(DetalleInversionistaDao detalleInversionistaDao) {
        obj = detalleInversionistaDao;
    }

    public Boolean save(DetalleInversionista detalleInversionista) throws Exception {
        return obj.save(detalleInversionista);
    }

    public LinkedList<DetalleInversionista> listAll() {
        return obj.getListAll();
    }

    public DetalleInversionista get(Integer id) {
        return obj.getDetalleInversionista();
    }

    public void setDetalleInversionista(DetalleInversionista detalleInversionista) {
        obj.setDetalleInversionista(detalleInversionista);
    }

    // Métodos de actualización y eliminación por índice
    public Boolean updateByIndex(Integer index, DetalleInversionista detalleInversionista) throws Exception {
        return obj.updateByIndex(index, detalleInversionista);
    }

    public Boolean deleteByIndex(Integer index) throws Exception {
        return obj.deleteByIndex(index);
    }
}
