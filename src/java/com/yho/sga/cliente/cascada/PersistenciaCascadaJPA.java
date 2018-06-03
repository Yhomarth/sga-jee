/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yho.sga.cliente.cascada;

import com.yho.sga.domain.Persona;
import com.yho.sga.domain.Usuario;
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
public class PersistenciaCascadaJPA {
    
    static Logger log = LogManager.getRootLogger();
    
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ConsolaJpqlPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        
        //paso 1 crear objeto tipo persona 
        Persona persona1 = new Persona("Jose Ernesto", "Reyes", null, "joseernestoreyes@hotmail.com", "8293474912");
        
        //creamos el objeto usuario que tiene dependencia con la persona 
        Usuario usuario1 = new Usuario("j.reyes", "jreyes123", persona1);
        
        //paso 2 iniciar la transaccion
        tx.begin();
        
        em.persist(usuario1);
        
        tx.commit();
        
        //objeto detached
        log.debug("Objeto persistido Usuario : " + usuario1);
        log.debug("Objeto persistido Persona : " + persona1);
        
        em.close();
        
    } //fin del metodo main
    
}
