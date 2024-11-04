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

import com.example.controls.dao.services.DetalleProyectoService;
import com.example.models.DetalleProyecto;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Path("/detalleProyectos")
public class DetalleProyectoApi {
    private static final Gson gson = new GsonBuilder().create();
    private DetalleProyectoService dps = new DetalleProyectoService(); // Inicializa el servicio una vez

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllDetalleProyectos() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("msg", "Ok");
        map.put("data", dps.listAll().toArray());
        return Response.ok(map).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createDetalleProyecto(DetalleProyecto detalleProyecto) {
        HashMap<String, Object> map = new HashMap<>();
        try {
            dps.save(detalleProyecto); // Simplificado, ya no necesitas verificar el retorno
            map.put("msg", "Detalle de proyecto creado exitosamente");
            map.put("data", detalleProyecto);
            return Response.ok(map).build();
        } catch (Exception e) {
            map.put("msg", "Error al crear el detalle de proyecto.");
            System.err.println("Error guardando detalle de proyecto: " + e.getMessage());
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(map).build();
        }
    }

    @DELETE
    @Path("/{idDetalleProyecto}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteDetalleProyecto(@PathParam("idDetalleProyecto") Integer idDetalleProyecto) {
        HashMap<String, Object> map = new HashMap<>();
        try {
            dps.deleteByIndex(idDetalleProyecto); // Usa el método deleteByIndex
            map.put("msg", "Detalle de proyecto eliminado exitosamente");
            return Response.ok(map).build();
        } catch (Exception e) {
            map.put("msg", "Error al eliminar el detalle de proyecto.");
            System.err.println("Error eliminando detalle de proyecto: " + e.getMessage());
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(map).build();
        }
    }

    @GET
    @Path("/{idDetalleProyecto}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDetalleProyecto(@PathParam("idDetalleProyecto") Integer idDetalleProyecto) {
        HashMap<String, Object> map = new HashMap<>();
        try {
            DetalleProyecto detalleProyecto = dps.get(idDetalleProyecto); // Cambiado a dps.get
            if (detalleProyecto == null) {
                map.put("msg", "No se encontró el detalle de proyecto");
                return Response.status(Response.Status.NOT_FOUND).entity(map).build();
            }
            map.put("msg", "Detalle de proyecto encontrado");
            map.put("data", detalleProyecto);
            return Response.ok(map).build();
        } catch (Exception e) {
            map.put("msg", "Error al obtener el detalle de proyecto.");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(map).build();
        }
    }

    @PUT
    @Path("/{idDetalleProyecto}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateDetalleProyecto(@PathParam("idDetalleProyecto") Integer idDetalleProyecto, DetalleProyecto detalleProyecto) {
        HashMap<String, Object> map = new HashMap<>();
        try {
            DetalleProyecto existingDetalleProyecto = dps.get(idDetalleProyecto);
            if (existingDetalleProyecto == null) {
                map.put("msg", "No se pudo encontrar el detalle de proyecto para actualizar");
                return Response.status(Response.Status.NOT_FOUND).entity(map).build();
            }

            detalleProyecto.setIdDetalle(idDetalleProyecto); // Asegúrate de que el ID se establezca
            dps.updateByIndex(idDetalleProyecto, detalleProyecto); // Actualiza el detalle de proyecto por índice
            map.put("msg", "Detalle de proyecto actualizado exitosamente");
            map.put("data", detalleProyecto);
            return Response.ok(map).build();
        } catch (Exception e) {
            map.put("msg", "Error al actualizar el detalle de proyecto.");
            System.err.println("Error actualizando detalle de proyecto: " + e.getMessage());
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(map).build();
        }
    }
}
