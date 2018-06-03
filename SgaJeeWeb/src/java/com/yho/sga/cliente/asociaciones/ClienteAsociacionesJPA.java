/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yho.sga.cliente.asociaciones;

import com.yho.sga.domain.Persona;
import com.yho.sga.domain.Usuario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author y.reyes
 */
public class ClienteAsociacionesJPA {
    
     static Logger log = LogManager.getRootLogger();
    
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ConsolaJpqlPU");
        EntityManager em = emf.createEntityManager();
        
        @SuppressWarnings("unchecked")
		List<Persona> personas = em.createNamedQuery("Persona.findAll").getResultList();
        
        //cerramos la conexion
        em.close();
        
        imprimirPersonas(personas);
        
    }

    private static void imprimirPersonas(List<Persona> personas) {
        
        for (Persona persona : personas) {
            log.debug("Persona : "+persona);
            
            for(Usuario usuario: persona.getUsuarioList()){
                log.debug("Usuario : "+ usuario);
            }//fin del for usuario
            
        }//fin del for persona
        
    }
   
    
}//fin de la clase ClienteAsociacionesJPA
