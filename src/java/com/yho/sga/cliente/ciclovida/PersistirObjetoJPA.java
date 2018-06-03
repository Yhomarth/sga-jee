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
public class PersistirObjetoJPA {
    
    static Logger log = LogManager.getRootLogger();
    
    
    public static void main(String[] args) {
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ConsolaJpqlPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        //inicia la transaccion 
        
        
        
        //paso 1. Creamos el nuevo Objeto
        //objeto en estado transitivo
        Persona persona1 = new Persona("Juan", "Perez", "aa", "prueba11@gmail.com", "1234567890");
        
        //paso 2 inicia la transaccion 
        tx.begin();
        
        //paso 3 ejecuta el sql 
        em.persist(persona1);
        
        //paso 4 commit /rollback
        tx.commit();
        
        log.debug("Objeto persistido: "+persona1);
        
        em.close();
        
        
        
        
        
        
    }//fin del metodo main 
    
    
}//fin de la clase PersistirObjetoJPA
