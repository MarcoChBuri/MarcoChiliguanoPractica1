package com.example.controls.dao;

import com.example.controls.dao.Implement.AdapterDao;
import com.example.models.DetalleProyecto;
import com.example.controls.tda.list.LinkedList;
import com.example.controls.exception.ListEmptyException;

public class DetalleProyectoDao extends AdapterDao<DetalleProyecto> {
    private DetalleProyecto detalleProyecto;
    private LinkedList<DetalleProyecto> listAll;

    public DetalleProyectoDao() {
        super(DetalleProyecto.class);
    }

    public DetalleProyecto getDetalleProyecto() {
        if (detalleProyecto == null) {
            detalleProyecto = new DetalleProyecto();
        }
        return this.detalleProyecto;
    }

    public void setDetalleProyecto(DetalleProyecto detalleProyecto) {
        this.detalleProyecto = detalleProyecto;
    }

    public LinkedList<DetalleProyecto> getListAll() {
        if (listAll == null) {
            this.listAll = listAll(); // Inicializa la lista desde la persistencia
        }
        return listAll;
    }

    public Boolean save(DetalleProyecto detalleProyecto) throws Exception {
        Integer id = getListAll().getSize() + 1;
        detalleProyecto.setIdDetalle(id); // Asigna el ID
        this.persist(detalleProyecto); // Persistir el nuevo detalle del proyecto
        return true;
    }

    // Método de actualización basado en índice
    public Boolean updateByIndex(Integer index, DetalleProyecto detalleProyecto) throws Exception {
        LinkedList<DetalleProyecto> detalles = getListAll();
        if (index >= 0 && index < detalles.getSize()) {
            detalleProyecto.setIdDetalle(index + 1); // Mantener el mismo índice ID (si se requiere)
            detalles.set(index, detalleProyecto); // Reemplaza el detalle del proyecto en el índice
            this.merge(detalleProyecto, index); // Sincroniza el cambio en persistencia
            return true;
        }
        return false; // Retorna false si el índice es inválido
    }

    public Boolean deleteByIndex(Integer index) throws Exception {
        LinkedList<DetalleProyecto> detalles = getListAll();
        try {
            if (index >= 0 && index < detalles.getSize()) {
                detalles.delete(index); // Elimina en el índice específico
                this.delete(index + 1); // Sincroniza con la persistencia
                return true;
            }
        } catch (ListEmptyException e) {
            System.err.println("La lista de detalles de proyectos está vacía: " + e.getMessage());
        }
        return false; // Retorna false si el índice es inválido o la lista está vacía
    }
}
