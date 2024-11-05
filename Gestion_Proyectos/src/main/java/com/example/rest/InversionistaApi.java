package com.example.rest;

import java.util.Arrays;
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

import com.example.controls.dao.services.InversionistaService;
import com.example.models.Inversionista;
import com.example.models.ENUM.Dni;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Path("/inversionistas")
public class InversionistaApi {
    private static final Gson gson = new GsonBuilder().create();
    private InversionistaService is = new InversionistaService(); // Inicializa el servicio una vez

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllInversionistas() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("msg", "Ok");
        map.put("data", is.listAll().toArray());
        return Response.ok(map).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createInversionista(Inversionista inversionista) {
        HashMap<String, Object> map = new HashMap<>();
        try {
            if (inversionista.getDni() == null) {
                map.put("msg", "El campo dni es obligatorio.");
                return Response.status(Response.Status.BAD_REQUEST).entity(map).build();
            }
            is.save(inversionista);
            map.put("msg", "Inversionista creado exitosamente");
            map.put("data", inversionista);
            return Response.ok(map).build();
        } catch (Exception e) {
            map.put("msg", "Error al crear el inversionista.");
            System.err.println("Error guardando inversionista: " + e.getMessage());
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(map).build();
        }
    }
    

    @DELETE
    @Path("/{idInversionista}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteInversionista(@PathParam("idInversionista") Integer idInversionista) {
        HashMap<String, Object> map = new HashMap<>();
        try {
            is.deleteByIndex(idInversionista); // Usa el método deleteByIndex
            map.put("msg", "Inversionista eliminado exitosamente");
            return Response.ok(map).build();
        } catch (Exception e) {
            map.put("msg", "Error al eliminar el inversionista.");
            System.err.println("Error eliminando inversionista: " + e.getMessage());
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(map).build();
        }
    }
    
    @Path("/dni")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDniOptions() {
        HashMap<String, Object> map = new HashMap<>();
        String[] dniOptions = Arrays.stream(Dni.values())
                                    .map(Dni::name)
                                    .toArray(String[]::new);
        map.put("msg", "Opciones de DNI");
        map.put("data", dniOptions);
        return Response.ok(map).build();
    }
    

    @GET
    @Path("/{idInversionista}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getInversionista(@PathParam("idInversionista") Integer idInversionista) {
        HashMap<String, Object> map = new HashMap<>();
        try {
            Inversionista inversionista = is.get(idInversionista); // Cambiado a is.get
            if (inversionista == null) {
                map.put("msg", "No se encontró el inversionista");
                return Response.status(Response.Status.NOT_FOUND).entity(map).build();
            }
            map.put("msg", "Inversionista encontrado");
            map.put("data", inversionista);
            return Response.ok(map).build();
        } catch (Exception e) {
            map.put("msg", "Error al obtener el inversionista.");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(map).build();
        }
    }

    @PUT
    @Path("/{idInversionista}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateInversionista(@PathParam("idInversionista") Integer idInversionista, Inversionista inversionista) {
        HashMap<String, Object> map = new HashMap<>();
        try {
            Inversionista existingInversionista = is.get(idInversionista);
            if (existingInversionista == null) {
                map.put("msg", "No se pudo encontrar el inversionista para actualizar");
                return Response.status(Response.Status.NOT_FOUND).entity(map).build();
            }

            inversionista.setId(idInversionista); // Asegúrate de que el ID se establezca
            is.updateByIndex(idInversionista, inversionista); // Actualiza el inversionista por índice
            map.put("msg", "Inversionista actualizado exitosamente");
            map.put("data", inversionista);
            return Response.ok(map).build();
        } catch (Exception e) {
            map.put("msg", "Error al actualizar el inversionista.");
            System.err.println("Error actualizando inversionista: " + e.getMessage());
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(map).build();
        }
    }
}
