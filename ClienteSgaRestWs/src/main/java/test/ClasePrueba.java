/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import domain.Persona;
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author y.reyes
 */
public class ClasePrueba {
    
private static final String URL_BASE = "http://localhost:8080/SgaJeeWeb/resources/";
    private static Client cliente;
    private static WebTarget webTarget;
    private static Persona persona;
    private static List<Persona> personas;
    private static Invocation.Builder invocationBuilder;
    private static Response response;
    
    
    public static void main(String[] args) {

        //agregar una persona metodo Post
        Persona nuevaPersona = new Persona();
        nuevaPersona.setNombre("Juan");
        nuevaPersona.setApellidoPaterno("Prueba POST");
        nuevaPersona.setApellidoMaterno("Mercedes");
        nuevaPersona.setEmail("pruebaa@gmail.com");
       // nuevaPersona.setTelefono("8098888888");
        
      
        //recuperamos a la persona recien agregada para despues modificarla y eliminarla
        
        try{
      
          Client client = ClientBuilder.newClient();
        client.target("http://localhost:8080/SgaJeeWeb/resources/personas/").
                request().post(Entity.entity(nuevaPersona, MediaType.APPLICATION_XML), Persona.class);

        }
        catch(Exception e){
            System.out.println("Error " + e.getMessage());
            System.out.println("Causa " + e.getCause());
            System.out.println("Causa " + e.getClass());
        }
        
    }//fin del metodo main
    

}//fin de la clasePrueba
