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

import com.example.controls.dao.services.ProyectoService;
import com.example.models.Proyecto;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Path("proyecto")
public class ProyectoApi {
    private static final Gson gson = new GsonBuilder().create();
    private ProyectoService ps = new ProyectoService(); // Inicializa el servicio una vez

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllProyecto() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("msg", "Ok");
        map.put("data", ps.listAll().toArray());
        return Response.ok(map).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createProyecto(Proyecto proyecto) {
        HashMap<String, Object> map = new HashMap<>();
        try {
            ps.save(proyecto); // Simplificado, ya no necesitas verificar el retorno
            map.put("msg", "Proyecto creado exitosamente");
            map.put("data", proyecto);
            return Response.ok(map).build();
        } catch (Exception e) {
            map.put("msg", "Error al crear el proyecto.");
            System.err.println("Error guardando proyecto: " + e.getMessage());
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(map).build();
        }
    }

    @DELETE
    @Path("/{idProyecto}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteProyecto(@PathParam("idProyecto") Integer idProyecto) {
        HashMap<String, Object> map = new HashMap<>();
        try {
            ps.deleteByIndex(idProyecto); // Usa el método deleteByIndex
            map.put("msg", "Proyecto eliminado exitosamente");
            return Response.ok(map).build();
        } catch (Exception e) {
            map.put("msg", "Error al eliminar el proyecto.");
            System.err.println("Error eliminando proyecto: " + e.getMessage());
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(map).build();
        }
    }

    @GET
    @Path("/{idProyecto}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProyecto(@PathParam("idProyecto") Integer idProyecto) {
        HashMap<String, Object> map = new HashMap<>();
        try {
            Proyecto proyecto = ps.get(idProyecto); // Cambiado a ps.get
            if (proyecto == null) {
                map.put("msg", "No se encontró el proyecto");
                return Response.status(Response.Status.NOT_FOUND).entity(map).build();
            }
            map.put("msg", "Proyecto encontrado");
            map.put("data", proyecto);
            return Response.ok(map).build();
        } catch (Exception e) {
            map.put("msg", "Error al obtener el proyecto.");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(map).build();
        }
    }

    @PUT
    @Path("/{idProyecto}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateProyecto(@PathParam("idProyecto") Integer idProyecto, Proyecto proyecto) {
        HashMap<String, Object> map = new HashMap<>();
        try {
            Proyecto existingProyecto = ps.get(idProyecto);
            if (existingProyecto == null) {
                map.put("msg", "No se pudo encontrar el proyecto para actualizar");
                return Response.status(Response.Status.NOT_FOUND).entity(map).build();
            }

            proyecto.setIdProyecto(idProyecto); // Asegúrate de que el ID se establezca
            ps.updateByIndex(idProyecto, proyecto); // Actualiza el proyecto por índice
            map.put("msg", "Proyecto actualizado exitosamente");
            map.put("data", proyecto);
            return Response.ok(map).build();
        } catch (Exception e) {
            map.put("msg", "Error al actualizar el proyecto.");
            System.err.println("Error actualizando proyecto: " + e.getMessage());
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(map).build();
        }
    }
}
