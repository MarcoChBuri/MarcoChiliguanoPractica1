package com.example.controls.dao;


import com.example.controls.dao.Implement.AdapterDao;
import com.example.controls.tda.list.LinkedList;
import com.example.models.Inversionista;
import com.example.models.ENUM.Dni;


public class InversionistaDao extends AdapterDao<Inversionista> {
    private Inversionista inversionista;
    private LinkedList listAll;

    public InversionistaDao() {
        super(Inversionista.class);
    }
    
    public Inversionista getInversionista() {
        if(this.inversionista == null) {
            this.inversionista = new Inversionista();
        }

        return this.inversionista;
    }

    public void setInversionista(Inversionista inversionista) {
        this.inversionista = inversionista;
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
            inversionista.setId(id);
            this.persist(this.inversionista);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Boolean update() throws Exception {
        try {
            this.merge(this.inversionista, this.inversionista.getId()-1);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Boolean delete() throws Exception {
        try {
            this.delete(this.inversionista.getId()-1);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public Dni getDni (String tipo){
        return Dni.valueOf(tipo);
    }
    public Dni[] getTipos(){
        return Dni.values();

    }
    public boolean correoUnico  (String email) throws Exception{
        LinkedList inversionistas = getListAll();
        
        for (int i = 0; i < inversionistas.getSize(); i++) {
            Inversionista inversionista = (Inversionista) inversionistas.get(i);  
            if (inversionista.getEmail().equals(email)) {
                return false; 
            }
        }
        return true; 
    }
    
    public LinkedList EscogerOrdenamiento(String algorithm, String attribute, Integer type) throws Exception {
        LinkedList inversionistas = getListAll();
        if (inversionistas == null || inversionistas.getSize() == 0) {
            throw new Exception("La lista de inversionistas está vacía.");
        }
        return inversionistas.tipo(algorithm, attribute, type);
    }
    public LinkedList  buscaPorBynario(String attribute, String value)throws Exception{
        LinkedList inversionistas = getListAll().tipo("shellsort",attribute,1);

        if (inversionistas == null || inversionistas.getSize() == 0) {
            throw new Exception("La lista de inversionistas está vacía.");
        }
            return inversionistas.binarySearch(attribute, value);
        
        }
          
    }

