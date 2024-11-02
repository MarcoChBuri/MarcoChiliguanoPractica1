package com.example.controls.dao.services;

import com.example.controls.dao.InversionistaDao;
import com.example.models.Inversionista;
import com.example.controls.tda.list.LinkedList;

public class InversionistaService {
    private InversionistaDao obj;

    public InversionistaService() {
        obj = new InversionistaDao();
    }
    
    
    public Boolean save() throws Exception {
        return obj.save();
    }

    public LinkedList<Inversionista> listAll() {
        return obj.getListAll();
    }

    public Inversionista getInversionista() {
        return obj.getInversionista();
    }

    public void setInversionista (Inversionista inversionista) {
        obj.setInversionista(inversionista);
    }
}
