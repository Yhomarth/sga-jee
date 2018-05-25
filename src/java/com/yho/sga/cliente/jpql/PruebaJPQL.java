/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yho.sga.cliente.jpql;

import com.yho.sga.domain.Persona;
import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author y.reyes
 */
public class PruebaJPQL {
    
    static Logger log = LogManager.getRootLogger();
    
    @SuppressWarnings("unchecked")
	public static void main(String[] args) {
        String jpql = null;
        Query qry = null;
        List<Persona> personas = null;
        Persona persona = null;
        Iterator<?> iter = null;
        Object[] tupla = null;
        List<String> nombres = null;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ConsolaJpqlPU");
        EntityManager em = emf.createEntityManager();
        
        //paso 1
        log.debug("N1 --> Consulta de todas las personas");
        jpql = "select p from Persona p";
        personas = em.createQuery(jpql).getResultList();
        mostrarPersonas(personas);
        
        //2 Consulta de la persona con el id = 1
        log.debug("N2 --> Consulta de la persona con el id = 1");
        jpql = "select p from Persona p where p.idPersona = 1";
        persona = (Persona) em.createQuery(jpql).getSingleResult();
        log.debug(persona);
        
    
        //3 Consulta de la Persona por nombre 
        log.debug("N3 --> Consulta de la persona por nombre :");
        jpql = "select p from Persona p where p.nombre = 'Rosa Julia'";
        personas = em.createQuery(jpql).getResultList();
        mostrarPersonas(personas);
        
        
        //4) Consulta de datos individuales, se crea un arreglo (tupla) de tipo object de tres columnas:
        log.debug("N4 --> Consulta de datos individuales, se crea un arreglo (tupla) de tipo object de tres columnas ");
        jpql = "select p.nombre as Nombre, p.apellidoPaterno as Paterno, p.apellidoMaterno as Materno from Persona p";
        iter = em.createQuery(jpql).getResultList().iterator();
        
        while (iter.hasNext()) {
            tupla = (Object[]) iter.next();
            String nombre = (String) tupla[0];
            String apePat = (String) tupla[1];
            String apeMat = (String) tupla[2];            
            log.debug(nombre + " " + apePat + " " + apeMat);            
        }//fin del while            
        
        //5) Obtiene el objeto Persona y el id, se crea un arreglo de tipo Object con 2 columnas:
        log.debug("N5 --> Obtiene el objeto Persona y el id, se crea un arreglo (tupla) de tipo Object con 2 columnas");
        jpql ="select p, p.idPersona from Persona p";
        iter = em.createQuery(jpql).getResultList().iterator();
        
        while (iter.hasNext()) {
            tupla  = (Object[]) iter.next();           
            persona = (Persona) tupla[0];
            int idPersona = (Integer) tupla[1];            
            log.debug("Objeto persona : " + persona);
            log.debug("id persona : " + idPersona);            
        }//fin del while
        
        //6) Consulta de todas las personas: 
        log.debug("N6 --> Consulta de todas las personas");
        jpql = "select new com.yho.sga.domain.Persona( p.idPersona) from Persona p";
        personas = em.createQuery(jpql).getResultList();
        mostrarPersonas(personas);
        
        //7) Regresa el valor minimo y maximo de idPersona (Scalar Results)
        log.debug("N7 --> Regresa el valor minimo y maximo de idPersona (Scalar Results)");
        jpql = "select min(p.idPersona) as MinId, max(p.idPersona) as MaxId, count(p.idPersona) as contador from Persona p";
        iter = em.createQuery(jpql).getResultList().iterator();
        while (iter.hasNext()) {
			 tupla = (Object[]) iter.next();
			 Integer idMin = (Integer) tupla[0];
			 Integer idMax = (Integer) tupla[1];
			 Long count = (Long) tupla[2];
			 log.debug("idMin = " +idMin + ", idMax = " + idMax + ", count = "+ count  );
			
		}//fin del while
        
        //8) Cuenta los nombres de las personas que son distintos 
        log.debug("N8 --> Extrae el numero de personas que son distintos");
        jpql = "select count(distinct p.nombre) from Persona p";
        Long contador = (Long) em.createQuery(jpql).getSingleResult();
        log.debug("no. de personas con nombre distinto: " +contador);
        
        
        //9) Concatena y convierte a mayusculas el nombre y apellido (depende de la base de datos)
        log.debug("N9 --> Concatena y convierte a mayusculas el nombre y apellido (depende de la base de datos)");
        jpql = "select CONCAT (p.nombre, ' ', p.apellidoPaterno) as Nombre FROM Persona p";
        nombres = em.createQuery(jpql).getResultList();
        for (String nombreCompleto : nombres) {
			log.debug(nombreCompleto);
		}
        
        
        //10) Obtiene el objeto alumno con id igual al parametro 1 
        log.debug("N10 --> Obtiene el objeto alumno con id igual al parametro");
        int idPersona = 3;
        jpql = "select p from Persona p where p.idPersona = :id";
        qry = em.createQuery(jpql);
        qry.setParameter("id", idPersona);
        persona = (Persona) qry.getSingleResult();
        log.debug(persona);
        
        
        //11) Obtiene todas las personas que contengan la letra a, sin importar mayusculas/minusculas
        log.debug("N11 --> Obtiene todas las personas que contengan la letra a, sin importar mayusculas/minusculas");
        jpql = "select p from Persona p where upper(p.nombre) like upper(:param1)";
        String cadena = "%a%";
        qry = em.createQuery(jpql);
        qry.setParameter("param1", cadena);
        personas = qry.getResultList();
        mostrarPersonas(personas);
        
    }//fin del metodo main 

    private static void mostrarPersonas(List<Persona> personas) {
        
        for (Persona persona : personas) {
                    System.out.println(persona);
        }//fin del for Personas
    }//fin del metodo mostrarPersonas
    
}//fin de la clase PruebaJPQL
