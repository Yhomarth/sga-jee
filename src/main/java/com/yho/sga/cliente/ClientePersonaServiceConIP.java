/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yho.sga.cliente;

import com.yho.sga.domain.Persona;
import java.util.Properties;
import javax.naming.Context;
import javax.naming.InitialContext;
import com.yho.sga.servicio.PersonaServiceRemote;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author yhomarth
 */
public class ClientePersonaServiceConIP {
    
    public static void main(String[] args) {
        
        System.out.println("Starting EJB call from client!");
        
        try{
            Properties props = new Properties();
            props.setProperty("java.naming.factory.initial", "com.sun.enterprise.naming.SerialInitContextFactory");
            props.setProperty("java.naming.factory.url.pkgs", "com.sun.enterprise.naming");
            props.setProperty("java.naming.factory.state", "com.sun.corba.ee.presentation.rmi.JNDIStateFactoryImpl");
            props.setProperty("org.omg.CORBA.ORBInitialHost", "127.0.0.1");
            
            Context jndi = new InitialContext(props);
            PersonaServiceRemote personaService = 
                    (PersonaServiceRemote) jndi.lookup("java:global/sga-jee/PersonaServiceImpl!com.yho.sga.servicio.PersonaServiceRemote");
            
            List<Persona> personas = personaService.listarPersonas();
            
            for (Persona persona : personas) {
                System.out.println(persona);
            }
            
            System.out.println("Fin de la llamada EJB Cliente");
            
        }//end of try
        
        catch(NamingException e){
            e.getStackTrace();
        }
        
    }//end of main method
    
}//end of ClientePersonaServiceConIP class
