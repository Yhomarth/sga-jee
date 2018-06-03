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
public class TestPersonaServiceRS {
    
    private static final String URL_BASE = "http://localhost:8080/SgaJeeWeb/resources/";
    private static Client cliente;
    private static WebTarget webTarget;
    private static Persona persona;
    private static List<Persona> personas;
    private static Invocation.Builder invocationBuilder;
    private static Response response;
    
    
    public static void main(String[] args) {

        cliente = ClientBuilder.newClient();
        
                
        //leer una persona (metodo get)
        webTarget = cliente.target(URL_BASE).path("/personas");
        
        //proporcionamos un idPersona valido
        persona = webTarget.path("/3").request(MediaType.APPLICATION_XML).get(Persona.class);
        
        System.out.println("Persona recuperada : " + persona);
         
        //Leer todas las personas (metodo get con readEntity de tipo List<>)
        //personas = webTarget.request(MediaType.APPLICATION_XML).get(Response.class).readEntity(new GenericType<List<Persona>>() {});
       
        System.out.println("Personas recuperadas :");
        //imprimirPersonas(personas);                
          
        //agregar una persona metodo Post
        Persona nuevaPersona = new Persona();
        nuevaPersona.setNombre("Juan");
        nuevaPersona.setApellidoPaterno("Perez");
        nuevaPersona.setApellidoMaterno("D'Oleo");
        nuevaPersona.setEmail("pruebaa@gmail.com");
        nuevaPersona.setTelefono("8293333333");
        
        System.out.println("Esta es la persona : "+nuevaPersona);
        
                
        response = webTarget.request(MediaType.APPLICATION_XML  ).post(Entity.entity(nuevaPersona, MediaType.APPLICATION_XML_TYPE));                  
        
        System.out.println("");
        System.out.println("Estatus respuesta agregar:" + response.getStatus());        
        System.out.println("MediaType :" + response.getMediaType());        
        
        
        
        //recuperamos a la persona recien agregada para despues modificarla y eliminarla
        Persona personaRecuperada = response.readEntity(Persona.class);
        System.out.println("Persona agregada : " + personaRecuperada);
         
    
        //modificar una persona (metodo put)
        //persona recuperada anteriormente 
        Persona personaModificar = personaRecuperada;
        personaModificar.setApellidoMaterno("Cambio Apellido Mat.");
        String pathId = "/" + personaModificar.getIdPersona();
        invocationBuilder = webTarget.path(pathId).request(MediaType.APPLICATION_XML);
        response = invocationBuilder.put(Entity.entity(personaModificar, MediaType.APPLICATION_XML));
        
        System.out.println("");
        System.out.println(response.getStatus());
        System.out.println("Persona modificada : " + response.readEntity(Persona.class));
        
        //Eliminar una Persona 
        //persona recuperada anteriormente 
        Persona personaEliminar = personaRecuperada;
        String pathEliminar = "/"+personaEliminar.getIdPersona();
        invocationBuilder = webTarget.path(pathEliminar).request(MediaType.APPLICATION_XML);
        response = invocationBuilder.delete();       
        
        System.out.println("");        
        System.out.println(response.getStatus());
        System.out.println("Persona Eliminada");
        

    }//fin del metodo main
    
    private static void imprimirPersonas(List<Persona> personas){
        for (Persona persona : personas) {
                    System.out.println(persona);
        }
    }//fin del metodo imprimirPersonas
    
}//fin de la clase TestPersonaServiceRS
