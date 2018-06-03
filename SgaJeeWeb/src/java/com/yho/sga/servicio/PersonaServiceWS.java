/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yho.sga.servicio;

import com.yho.sga.domain.Persona;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 *
 * @author y.reyes
 */

@WebService
public interface PersonaServiceWS {
    
    @WebMethod
    public List<Persona> listarPersonas();
    
    @WebMethod
    public Persona encontrarPersonaPorId(Persona persona);
    
    @WebMethod
    public void registrarPersona(Persona persona);
    
    @WebMethod
    public void modificarPersona(Persona persona);
    
    @WebMethod
    public void eliminarPersona(Persona persona);
    
}//fin de la clase PersonaServiceWS
