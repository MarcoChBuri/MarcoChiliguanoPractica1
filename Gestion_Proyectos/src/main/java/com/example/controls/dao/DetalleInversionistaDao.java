package com.example.controls.dao;

import com.example.controls.dao.Implement.AdapterDao;
import com.example.controls.exception.ListEmptyException;
import com.example.models.DetalleInversionista;
import com.example.controls.tda.list.LinkedList;

public class DetalleInversionistaDao extends AdapterDao<DetalleInversionista> {
    private DetalleInversionista detalleInversionista;
    private LinkedList<DetalleInversionista> listAll;

    public DetalleInversionistaDao() {
        super(DetalleInversionista.class);
    }

    public DetalleInversionista getDetalleInversionista() {
        if (detalleInversionista == null) {
            detalleInversionista = new DetalleInversionista();
        }
        return this.detalleInversionista;
    }

    public void setDetalleInversionista(DetalleInversionista detalleInversionista) {
        this.detalleInversionista = detalleInversionista;
    }

    public LinkedList<DetalleInversionista> getListAll() {
        if (listAll == null) {
            this.listAll = new LinkedList<>(); // Inicializa la lista
            this.listAll = listAll(); // Carga la lista desde la persistencia
        }
        return listAll;
    }

    public Boolean save(DetalleInversionista detalleInversionista) throws Exception {
        Integer id = getListAll().getSize() + 1;
        detalleInversionista.setIdDetaleInversionista(id); // Establece el ID
        this.persist(detalleInversionista); // Persiste el nuevo detalle
        return true;
    }

    // Método de actualización por índice
    public Boolean updateByIndex(Integer index, DetalleInversionista detalleInversionista) throws Exception {
        LinkedList<DetalleInversionista> detalleInversionistas = getListAll();
        if (index >= 0 && index < detalleInversionistas.getSize()) {
            detalleInversionista.setIdDetaleInversionista(index + 1); // Mantener el ID por índice (si se requiere)
            detalleInversionistas.set(index, detalleInversionista); // Reemplaza el detalle en el índice
            this.merge(detalleInversionista, index); // Sincroniza el cambio en persistencia
            return true;
        }
        return false; // Retorna false si el índice es inválido
    }

    // Método de eliminación por índice
    public Boolean deleteByIndex(Integer index) throws Exception {
        LinkedList<DetalleInversionista> detalleInversionistas = getListAll();
        try {
            if (index >= 0 && index < detalleInversionistas.getSize()) {
                detalleInversionistas.delete(index); // Elimina en el índice
                this.delete(index + 1); // Sincroniza con la persistencia
                return true;
            }
        } catch (ListEmptyException e) {
            System.err.println("La lista de detalles de inversionistas está vacía: " + e.getMessage());
        }
        return false; // Retorna false si el índice es inválido o la lista está vacía
    }
}
