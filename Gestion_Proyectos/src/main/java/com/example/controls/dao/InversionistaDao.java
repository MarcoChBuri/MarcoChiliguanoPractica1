package com.example.controls.dao;

import com.example.controls.dao.Implement.AdapterDao;
import com.example.models.Inversionista;
import com.example.controls.dao.Implement.InterfazDao;
import com.example.controls.tda.list.LinkedList;
import com.example.controls.exception.ListEmptyException;

public class InversionistaDao implements InterfazDao<Inversionista> {
    private LinkedList<Inversionista> inversionistas;
    private AdapterDao<Inversionista> adapterDao;

    public InversionistaDao() {
         this.inversionistas = new LinkedList<>();
        this.adapterDao = new AdapterDao<>(Inversionista.class);
        this.inversionistas = adapterDao.listAll();
        System.out.println("inversionista cargados en el constructor: " + inversionistas);

    }

    @Override
    public LinkedList<Inversionista> listAll() {
        return inversionistas;
    }

    @Override
    public void persist(Inversionista inversionista) throws Exception {
        inversionistas.add(inversionista);
        adapterDao.persist(inversionista); // Persistir el nuevo inversionista
    }

    @Override
    public void merge(Inversionista inversionista, Integer index) throws Exception {
        inversionistas.update(inversionista, index);
        adapterDao.merge(inversionista, index); // Sincronizar el cambio en persistencia
    }

    @Override
    public Inversionista get(Integer id) throws Exception {
        try {
            return inversionistas.get(id - 1); // Obtener inversionista por índice ajustado
        } catch (IndexOutOfBoundsException | ListEmptyException e) {
            System.err.println("Error al obtener el inversionista: " + e.getMessage());
            return null;
        }
    }

    @Override
    public void delete(Integer id) throws Exception {
        try {
            inversionistas.delete(id);
            adapterDao.delete(id); // Eliminar de la persistencia
        } catch (ListEmptyException e) {
            System.err.println("La lista de inversionistas está vacía: " + e.getMessage());
        }
    }
}
