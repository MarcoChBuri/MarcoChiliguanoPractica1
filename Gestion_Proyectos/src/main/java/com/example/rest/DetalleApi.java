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
import java.util.List;
import com.example.controls.tda.list.LinkedList;
import com.example.controls.dao.services.DetalleService;
import com.example.models.Detalle;

@Path("detalles")
public class DetalleApi {
    @Path("/all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllOperations() throws Exception {
        HashMap map = new HashMap<>();
            DetalleService ps = new    DetalleService();

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
                DetalleService ps = new    DetalleService();

            ps.getDetalle().setTiempoVida(0);
            ps.getDetalle().setEnergiaGenerada(0);
            ps.getDetalle().setMontoInversionista(0);;
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
    public Response getDetalle(@PathParam("id") Integer id) throws Exception {
        HashMap map = new HashMap<>();
            DetalleService ps = new  DetalleService();
        Detalle Detalle = ps.get(id);

        if (Detalle == null || Detalle.getId() == null) {
            map.put("msg", "Error");
            map.put("data", "No existe esa Detalle");
            return Response.status(Response.Status.BAD_REQUEST).entity(map).build();
        }

        map.put("msg", "OK");
        map.put("data", Detalle);

        return Response.ok(map).build();
    }

    @Path("/update")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(HashMap map) {
        HashMap res = new HashMap<>();

        try {
                DetalleService ps = new    DetalleService();

            ps.setDetalle(ps.get(Integer.parseInt(map.get("id").toString())));
            ps.getDetalle().setTiempoVida(0);
            ps.getDetalle().setEnergiaGenerada(0);
            ps.getDetalle().setMontoInversionista(0);;
            ps.save();

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
                DetalleService ps = new    DetalleService();
            
            Detalle Detalle = ps.get(Integer.parseInt(map.get("id").toString()));

            if (Detalle == null || Detalle.getId() == null) {
                res.put("msg", "Error");
                res.put("data", "No existe esa Detalle");
                return Response.status(Response.Status.BAD_REQUEST).entity(res).build();
            }

            ps.setDetalle(Detalle);
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
    // @Path("/list/search/{texto}")
    // @GET
    // @Produces(MediaType.APPLICATION_JSON)
    // public Response getPersonsLastName(@PathParam("texto") String texto) {
    //     HashMap map = new HashMap<>();
    //     DetalleService ps = new DetalleService();
    //     map.put("msg", "OK");
    //     LinkedList lsita = ps.buscar_apellidos(texto);
    //     map.put("data", lsita.toArray());
    //     if (lsita.isEmpty()) {
    //         map.put("data", new Object[] {});
    //     }

    //     return Response.ok(map).build();
    // }

    // @Path("/list/order/{attribute}/{type}")
    // @GET
    // @Produces(MediaType.APPLICATION_JSON)
    // public Response getPersonsLastName(@PathParam("attribute") String attribute, @PathParam("type") Integer type) {
    //     HashMap map = new HashMap<>();
    //     DetalleService ps = new DetalleService();
    //     map.put("msg", "OK");
    //     // pd.order_object(LinkedList.ASC, "apellidos")
    //     try {
    //         LinkedList lsita = ps.order_object(type, attribute);
    //         map.put("data", lsita.toArray());
    //         if (lsita.isEmpty()) {
    //             map.put("data", new Object[] {});
    //         }
    //     } catch (Exception e) {
    //         // TODO: handle exception
    //     }

    //     return Response.ok(map).build();
    // }

    // @Path("/list/search/ident/{texto}")
    // @GET
    // @Produces(MediaType.APPLICATION_JSON)
    // public Response getPersonsIdent(@PathParam("texto") String texto) {
    //     HashMap map = new HashMap<>();
    //     DetalleService ps = new DetalleService();
    //     map.put("msg", "OK");
    //     ps.setPersona(ps.buscar_identificacion(texto));
    //     map.put("data", ps.getPersona());
    //     if (ps.getPersona().getId() == null) {
    //         map.put("data", "No existe la persona");
    //         return Response.status(Status.BAD_REQUEST).header("Access-Control-Allow-Origin", "*").entity(map).build();
    //     }
    //     return Response.ok(map).header("Access-Control-Allow-Origin", "*").build();

    // }
}


