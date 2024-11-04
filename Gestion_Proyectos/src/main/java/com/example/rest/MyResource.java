package com.example.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("myresource")
public class MyResource {
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSampleResponse() {
        // Este método ahora puede servir para otras funcionalidades o simplemente dejarlo vacío
        return Response.ok("Este endpoint ya no gestiona proyectos. Usa el endpoint /proyecto para eso.").build();
    }
}
