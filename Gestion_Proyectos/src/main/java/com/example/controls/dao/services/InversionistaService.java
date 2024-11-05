package com.example.controls.dao.services;

import com.example.controls.dao.InversionistaDao;
import com.example.models.Inversionista;
import com.example.controls.tda.list.LinkedList;

public class InversionistaService {
    private InversionistaDao obj;

    public InversionistaService() {
        obj = new InversionistaDao();
    }

    public InversionistaService(InversionistaDao inversionistaDao) {
        obj = inversionistaDao;
    }
    
    public void save(Inversionista inversionista) throws Exception {
        obj.persist(inversionista);
    }

    public LinkedList<Inversionista> listAll() {
        return obj.listAll();
    }

    public Inversionista get(Integer id) throws Exception {
        return obj.get(id);
    }

    public void updateByIndex(Integer index, Inversionista inversionista) throws Exception {
        obj.merge(inversionista, index);
    }

    public void deleteByIndex(Integer index) throws Exception {
        obj.delete(index);
    }
}
