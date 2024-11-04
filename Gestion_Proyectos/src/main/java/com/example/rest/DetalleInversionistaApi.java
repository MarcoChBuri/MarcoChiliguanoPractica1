package com.example.rest;

import java.util.HashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.example.controls.dao.services.DetalleInversionistaService;
import com.example.models.DetalleInversionista;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Path("/detalleInversionistas")
public class DetalleInversionistaApi {
    private static final Gson gson = new GsonBuilder().create();
    private DetalleInversionistaService dis = new DetalleInversionistaService(); // Inicializa el servicio una vez

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllDetalleInversionistas() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("msg", "Ok");
        map.put("data", dis.listAll().toArray());
        return Response.ok(map).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createDetalleInversionista(DetalleInversionista detalleInversionista) {
        HashMap<String, Object> map = new HashMap<>();
        try {
            dis.save(detalleInversionista); // Simplificado, ya no necesitas verificar el retorno
            map.put("msg", "Detalle de inversionista creado exitosamente");
            map.put("data", detalleInversionista);
            return Response.ok(map).build();
        } catch (Exception e) {
            map.put("msg", "Error al crear el detalle de inversionista.");
            System.err.println("Error guardando detalle de inversionista: " + e.getMessage());
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(map).build();
        }
    }

    @DELETE
    @Path("/{idDetalleInversionista}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteDetalleInversionista(@PathParam("idDetalleInversionista") Integer idDetalleInversionista) {
        HashMap<String, Object> map = new HashMap<>();
        try {
            dis.deleteByIndex(idDetalleInversionista); // Usa el método deleteByIndex
            map.put("msg", "Detalle de inversionista eliminado exitosamente");
            return Response.ok(map).build();
        } catch (Exception e) {
            map.put("msg", "Error al eliminar el detalle de inversionista.");
            System.err.println("Error eliminando detalle de inversionista: " + e.getMessage());
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(map).build();
        }
    }

    @GET
    @Path("/{idDetalleInversionista}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDetalleInversionista(@PathParam("idDetalleInversionista") Integer idDetalleInversionista) {
        HashMap<String, Object> map = new HashMap<>();
        try {
            DetalleInversionista detalleInversionista = dis.get(idDetalleInversionista); // Cambiado a dis.get
            if (detalleInversionista == null) {
                map.put("msg", "No se encontró el detalle de inversionista");
                return Response.status(Response.Status.NOT_FOUND).entity(map).build();
            }
            map.put("msg", "Detalle de inversionista encontrado");
            map.put("data", detalleInversionista);
            return Response.ok(map).build();
        } catch (Exception e) {
            map.put("msg", "Error al obtener el detalle de inversionista.");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(map).build();
        }
    }

    @PUT
    @Path("/{idDetalleInversionista}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateDetalleInversionista(@PathParam("idDetalleInversionista") Integer idDetalleInversionista, DetalleInversionista detalleInversionista) {
        HashMap<String, Object> map = new HashMap<>();
        try {
            DetalleInversionista existingDetalleInversionista = dis.get(idDetalleInversionista);
            if (existingDetalleInversionista == null) {
                map.put("msg", "No se pudo encontrar el detalle de inversionista para actualizar");
                return Response.status(Response.Status.NOT_FOUND).entity(map).build();
            }

            detalleInversionista.setIdDetaleInversionista(idDetalleInversionista); // Asegúrate de que el ID se establezca
            dis.updateByIndex(idDetalleInversionista, detalleInversionista); // Actualiza el detalle de inversionista por índice
            map.put("msg", "Detalle de inversionista actualizado exitosamente");
            map.put("data", detalleInversionista);
            return Response.ok(map).build();
        } catch (Exception e) {
            map.put("msg", "Error al actualizar el detalle de inversionista.");
            System.err.println("Error actualizando detalle de inversionista: " + e.getMessage());
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(map).build();
        }
    }
}
