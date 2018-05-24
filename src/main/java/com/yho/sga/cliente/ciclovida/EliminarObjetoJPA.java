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
public class EliminarObjetoJPA {
    
        static Logger log = LogManager.getRootLogger();
        
    public static void main(String[] args) {
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ConsolaJpqlPU");
        EntityManager em = emf.createEntityManager();
                
        //paso 1 inicia la transaccion
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        
        //Paso 2 Ejecuta el Select con el id del objeto
        Persona persona1 = em.find(Persona.class, 4);
        
        //Paso 3 termina la transaccion
        tx.commit();
        
        log.debug("Objeto encontrado : " + persona1);
        
        //Paso 4 inicia la transaccion 2 
        EntityTransaction tx2 = em.getTransaction();
        tx2.begin();
        
        //Paso 5 Ejecutamaos el delete 
        em.remove(persona1);
        
        //Paso 6 Termina la transaccion, momento de hacer commit
        tx2.commit();
        
        //objeto en estado de detached
        log.debug("Objeto eliminado : " + persona1);
        
    }
    
} //fin de la clase EliminarObjetoJPA
