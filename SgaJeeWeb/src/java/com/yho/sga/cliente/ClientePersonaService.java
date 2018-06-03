/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yho.sga.cliente;

import com.yho.sga.domain.Persona;
import com.yho.sga.servicio.PersonaServiceRemote;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author yhomarth
 */
public class ClientePersonaService {
    
    public static void main(String[] args) {
        System.out.println("Iniciando la llamada al EJB Cliente!!");
        
        try{
            Context jndi = new InitialContext();
            PersonaServiceRemote personaService = 
                    (PersonaServiceRemote) jndi.lookup("java:global/sga-jee/PersonaServiceImpl!com.yho.sga.servicio.PersonaServiceRemote");
            
            List<Persona> personas = personaService.listarPersonas();
            
            for(Persona persona : personas){
                System.out.println(persona);
            }//end of for statement
            
            System.out.println("Fin de la llamada del EJB");
            
        }//end of try
        
        catch(NamingException nex){
            nex.printStackTrace();
        }
        
    }//end of main method
    
}//end of ClientePersonaService class
