package com.example.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.HashMap;


import com.example.controls.dao.services.InversionistaService;
import com.example.models.Inversionista;
import com.example.controls.tda.list.*;

@Path("inversionistas")
public class InversionistaApi {
    @Path("/all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllOperations() throws Exception {
        HashMap map = new HashMap<>();
            InversionistaService ps = new    InversionistaService();

        try {
            map.put("msg", "OK");
            map.put("data", ps.listAll().toArray());

            if (ps.listAll().isEmpty()) {
                map.put("data", new Object[]{});
            }

            return Response.ok(map).build();
        } catch (Exception e) {
            HashMap<String, String> error = new HashMap<>();
            error.put("msg", "Error");
            error.put("data", e.toString());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(error).build();
        }
    }

    @Path("/save")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(HashMap map) {
        HashMap res = new HashMap<>();
        
        try {
                InversionistaService ps = new    InversionistaService();

            ps.getInversionista().setNombre(map.get("nombre").toString());
            ps.getInversionista().setApellido(map.get("apellido").toString());
            ps.getInversionista().setEmail(map.get("email").toString());
            ps.getInversionista().setIdentification(map.get("Identification").toString());

            ps.save();

            res.put("msg", "OK");
            res.put("data", "Persona registrada correctamente");

            return Response.ok(res).build();
        } catch (Exception e) {
            res.put("msg", "Error");
            res.put("data", e.toString());

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(res).build();
        }
    }

    @Path("/get/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getInversionista(@PathParam("id") Integer id) throws Exception {
        HashMap map = new HashMap<>();
            InversionistaService ps = new  InversionistaService();
        Inversionista inversionista = ps.get(id);

        if (inversionista == null || inversionista.getId() == null) {
            map.put("msg", "Error");
            map.put("data", "No existe esa Inversionista");
            return Response.status(Response.Status.BAD_REQUEST).entity(map).build();
        }

        map.put("msg", "OK");
        map.put("data", inversionista);

        return Response.ok(map).build();
    }

    @Path("/update")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(HashMap map) {
        HashMap res = new HashMap<>();

        try {
                InversionistaService ps = new    InversionistaService();

            ps.setInversionista(ps.get(Integer.parseInt(map.get("id").toString())));
            ps.getInversionista().setNombre(map.get("nombre").toString());
            ps.getInversionista().setApellido(map.get("apellido").toString());
            ps.getInversionista().setEmail(map.get("email").toString());
            ps.getInversionista().setIdentification(map.get("Identification").toString());
            ps.update();

            res.put("msg", "OK");
            res.put("data", "Persona actualizada correctamente");

            return Response.ok(res).build();
        } catch (Exception e) {
            res.put("msg", "Error");
            res.put("data", e.toString());

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(res).build();
        }
    }

    @Path("/delete")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(HashMap map) {
        HashMap res = new HashMap<>();

        try {
                InversionistaService ps = new    InversionistaService();
            
            Inversionista Inversionista = ps.get(Integer.parseInt(map.get("id").toString()));

            if (Inversionista == null || Inversionista.getId() == null) {
                res.put("msg", "Error");
                res.put("data", "No existe esa Inversionista");
                return Response.status(Response.Status.BAD_REQUEST).entity(res).build();
            }

            ps.setInversionista(Inversionista);
            ps.delete();

            res.put("msg", "OK");
            res.put("data", "Persona eliminada correctamente");

            return Response.ok(res).build();
        } catch (Exception e) {
            res.put("msg", "Error");
            res.put("data", e.toString());

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(res).build();
        }
    }
@Path("/order/{algorithm}/{attribute}/{type}")
@GET
@Produces(MediaType.APPLICATION_JSON)
public Response EscogerOrdenamiento(
    @PathParam("algorithm") String algorithm,
    @PathParam("attribute") String attribute,
    @PathParam("type") Integer type
) {
    HashMap<String, Object> map = new HashMap<>();
    if (algorithm == null || attribute == null || type == null) {
        map.put("msg", "Error: Parámetros inválidos o faltantes.");
        return Response.status(Response.Status.BAD_REQUEST).entity(map).build();
    }

    try {
        InversionistaService ps = new InversionistaService();
        LinkedList data = ps.EscogerOrdenamiento(algorithm, attribute, type);
        map.put("data", data.toArray());

        if (data.isEmpty()) {
            map.put("msg", "No data found");
        }
    } catch (Exception e) {
        map.put("msg", e.toString());
        return Response.status(Response.Status.BAD_REQUEST).entity(map).build();
    }
    return Response.ok(map).build();
}

@Path("/buscar/LinearSearch/{attribute}/{value}")

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response BuscarLinear(
        @PathParam("attribute") String attribute,
        @PathParam("value") String value
    ) {
        HashMap<String, Object> map = new HashMap<>();
        if ( attribute == null || value == null) {
            map.put("msg", "Error: Parámetros inválidos o faltantes.");
            return Response.status(Response.Status.BAD_REQUEST).entity(map).build();
        }

        try {
            InversionistaService ps = new InversionistaService();
            LinkedList data = ps.SearchLinear(attribute, value);
            map.put("data", data.toArray());

            if (data.isEmpty()) {
                map.put("msg", "No data found");
            }
        } catch (Exception e) {
            map.put("msg", e.toString());
            return Response.status(Response.Status.BAD_REQUEST).entity(map).build();
        }
        return Response.ok(map).build();
    }
    @Path("/buscar/binarySearch/{attribute}/{value}")

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response BuscarBynary(
        @PathParam("attribute") String attribute,
        @PathParam("value") String value
    ) {
        HashMap<String, Object> map = new HashMap<>();
        if ( attribute == null || value == null) {
            map.put("msg", "Error: Parámetros inválidos o faltantes.");
            return Response.status(Response.Status.BAD_REQUEST).entity(map).build();
        }

        try {
            InversionistaService ps = new InversionistaService();
            LinkedList<Inversionista> data = ps.EscogerOrdenamiento("mergesort", attribute, 1);
            data = data.binarySearch(attribute, value);
            map.put("data", data.toArray());

            if (data.isEmpty()) {
                map.put("data", "No data found");
            }
        } catch (Exception e) {
            map.put("msg", e.toString());
            return Response.status(Response.Status.BAD_REQUEST).entity(map).build();
        }
        return Response.ok(map).build();
    }
}