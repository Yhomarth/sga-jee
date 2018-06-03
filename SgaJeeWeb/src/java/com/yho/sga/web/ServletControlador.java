/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yho.sga.web;

import com.yho.sga.domain.Persona;
import com.yho.sga.servicio.PersonaService;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author y.reyes
 */

@WebServlet({"/ServletControlador","/ListarPersonas"})
public class ServletControlador extends HttpServlet {
    
    private static final long serialVersionUID = 1L;

    @EJB
    private PersonaService personaService;
    
    @Override
    protected void doGet(HttpServletRequest request, 
            HttpServletResponse response) throws ServletException, IOException {
        
        String accion = request.getParameter("accion");
        
        if(accion != null && accion.equals("editar")){
            //Recuperamos el idPersona seleccionado
            String idPersonaString = request.getParameter("idPersona");
            int idPersona = 0;
            
            if(idPersonaString != null){
                
                //creamos el objeto persona para recuperarlo
                idPersona = Integer.parseInt(idPersonaString);
                Persona persona = new Persona(idPersona);                
                persona = this.personaService.encontrarPersonaPorId(persona);
                
                //compartimos el objeto persona con el request
                request.setAttribute("persona", persona);
                
                //reedireccionamos la pagina
                request.getRequestDispatcher("editarPersona.jsp").
                        forward(request, response);
            }
        }
        
        else{
            this.listarPersonas(request, response);
        }
        
    } //fin del metodo doGet
    
    @Override
    protected void doPost(HttpServletRequest request, 
            HttpServletResponse response) throws ServletException, IOException {
        
        String accion = request.getParameter("accion");
        
        if (accion != null && accion.equals("agregar")){
        
            //Recuperamos los parametros
            String nombre = request.getParameter("nombrePersona");
            String apellidoPaterno = request.getParameter("apellidoPaterno");
            String apellidoMaterno = request.getParameter("apellidoMaterno");
            String email = request.getParameter("email");
            String telefono = request.getParameter("telefono");
            
            //Creamos el objeto Persona
            Persona persona = new Persona(nombre, apellidoPaterno, 
                    apellidoMaterno, email, telefono);
            
            try{
                this.personaService.registrarPersona(persona);
            }
            catch(Exception e){
                e.printStackTrace(System.out);
            }
            
            //listar las personas 
            this.listarPersonas(request, response);
            
        }
        else if (accion != null && accion.equals("modificar")){ 
            String botonGuardar = request.getParameter("guardar");
            String botonEliminar = request.getParameter("eliminar");
            
            
            if(botonGuardar != null){
                //Recuperamos los parametros
            String idPersonaString = request.getParameter("idPersona");    
            String nombre = request.getParameter("nombrePersona");
            String apellidoPaterno = request.getParameter("apellidoPaterno");
            String apellidoMaterno = request.getParameter("apellidoMaterno");
            String email = request.getParameter("email");
            String telefono = request.getParameter("telefono");
            
            //creamos el objeto persona 
            Persona persona = new Persona();
            int idPersona = Integer.parseInt(idPersonaString);
            persona.setIdPersona(idPersona);
            persona.setNombre(nombre);
            persona.setApellidoPaterno(apellidoPaterno);
            persona.setApellidoMaterno(apellidoMaterno);
            persona.setEmail(email);
            persona.setTelefono(telefono);
                
            try{
                this.personaService.modificarPersona(persona);
            }
            catch(Exception e){
                e.printStackTrace(System.out);
            }
            
            //listamos la persona
            this.listarPersonas(request, response);
            
            
            } else if(botonEliminar != null){
                //Recuperamos el parametro
                String idPersonaString = request.getParameter("idPersona");
                
                //creamos el objeto persona
                int idPersona = Integer.parseInt(idPersonaString);
                Persona persona = new Persona(idPersona);
                
                try{
                    this.personaService.eliminarPersona(persona);
                }
                catch(Exception e) {
                    e.printStackTrace(System.out);
                }
                
                this.listarPersonas(request, response);
                
            }
            
        } else {
        //accion por defecto listamos la persona
        this.listarPersonas(request, response);
        
        }
        
    } //fin del metodo doGet
        
    
    private void listarPersonas(HttpServletRequest request, 
            HttpServletResponse response) throws ServletException, IOException {
        List<Persona> personas = personaService.listarPersonas();
        request.setAttribute("personas", personas);
        request.getRequestDispatcher("listarPersonas.jsp").
                forward(request, response);
    }
    
    
    
}//fin de la clase ServletControlador
