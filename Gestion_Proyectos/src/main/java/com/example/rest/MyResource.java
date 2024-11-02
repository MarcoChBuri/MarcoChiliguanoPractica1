package com.example.rest;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Date;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.example.controls.dao.ProyectoDao;
import com.example.models.Proyecto;
import com.example.models.Inversionista;

@Path("myresource")
public class MyResource {
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProyecto() {
        HashMap<String, Object> mapa = new HashMap<>();
        ProyectoDao proyectoDao = new ProyectoDao();

        try {
            // Crear y configurar un nuevo Proyecto
            ArrayList<Inversionista> inversionistas = new ArrayList<>();  // lista de inversionistas vacía para el ejemplo
            Proyecto proyecto = new Proyecto(1, "Proyecto de Energía", 30, new Date(), new Date(), inversionistas);
            
            proyectoDao.setProyecto(proyecto);
            proyectoDao.save();

            mapa.put("msg", "Proyecto guardado correctamente");
            mapa.put("data", proyecto);
        } catch (Exception e) {
            mapa.put("msg", "Error al guardar el proyecto");
            mapa.put("error", e.getMessage());
            System.out.println("Error: " + e);
        }

        return Response.ok(mapa).build();
    }
}
