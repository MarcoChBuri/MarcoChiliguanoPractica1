package com.example.rest;

import java.util.HashMap;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.example.controls.dao.services.DetalleService;

@Path("detalle")
public class DetalleApi {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllDetalle() {
        HashMap map = new HashMap<>();
        DetalleService ps = new DetalleService();
        map.put("msg", "Ok");
        map.put("data", ps.listAll().toArray());
        return Response.ok(map).build();
    }

}
