package com.example.rest;

import java.util.HashMap;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.example.controls.dao.services.ProyectoService;

@Path("proyecto")
public class ProyectoApi {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllProyecto() {
        HashMap<String, Object> map = new HashMap<>();
        ProyectoService ps = new ProyectoService();
        map.put("msg", "Ok");
        map.put("data", ps.listAll().toArray());
        return Response.ok(map).build();
    }
}
