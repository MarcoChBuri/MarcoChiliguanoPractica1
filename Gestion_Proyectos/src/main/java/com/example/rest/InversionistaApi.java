package com.example.rest;

import java.util.HashMap;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.example.controls.dao.services.InversionistaService;

@Path("inversionista")
public class InversionistaApi {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPersons() {
        HashMap map = new HashMap<>();
        InversionistaService ps = new InversionistaService();

        map.put("msg", "Ok");
        map.put("data", ps.listAll().toArray());
        return Response.ok(map).build();
    }
}

