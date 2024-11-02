package com.example.controls.dao;
import com.example.controls.dao.Implement.AdapterDao;
import com.example.controls.dao.InversionistaDao;
import com.example.models.Inversionista;
import com.example.controls.tda.list.LinkedList;

public class InversionistaDao extends AdapterDao<Inversionista> {
    private Inversionista inversionista;
    private LinkedList <Inversionista> listAll;
    public InversionistaDao() {
        super(Inversionista.class);
    }

    public Inversionista getInversionista() {
        if (inversionista == null) {
            inversionista = new Inversionista();
        }
        return this.inversionista;
    }

    public void setInversionista(Inversionista inversionista) {
        this.inversionista = inversionista;
    }
    
    public LinkedList <Inversionista> getListAll() {
        if(listAll == null){
            this.listAll = listAll();
        }
        return listAll;
    }

    public Boolean save() throws Exception {
        Integer id = getListAll().getSize()+1;
        inversionista.setId(id);
        this.persist(this.inversionista);
        return true;
    }
}
