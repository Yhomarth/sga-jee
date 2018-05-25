/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yho.sga.cliente.ciclovida;

import com.yho.sga.domain.Persona;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author y.reyes
 */
public class ActualizarObjetoSessionLarga {
    
    static Logger log = LogManager.getRootLogger();
    
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ConsolaJpqlPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        
        tx.begin();
        Persona persona1 = em.find(Persona.class, 1);
                
        log.debug("Objeto encontrado : " +persona1);
        
        persona1.setApellidoPaterno("Guzman");
        tx.commit();
        
        log.debug("Objeto actualizado : " +persona1);
        
    }
    
    
}
