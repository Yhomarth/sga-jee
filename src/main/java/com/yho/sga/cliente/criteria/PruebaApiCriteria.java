package com.yho.sga.cliente.criteria;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.yho.sga.domain.Persona;

public class PruebaApiCriteria {
	
	 static Logger log = LogManager.getRootLogger();
	 
	 public static void main(String[] args) {
		 EntityManagerFactory emf = Persistence.createEntityManagerFactory("ConsolaJpqlPU");
		 EntityManager em = emf.createEntityManager();
		 
		 CriteriaBuilder cb = em.getCriteriaBuilder();
		 CriteriaQuery<Persona> criteriaQuery = null;
		 Root<Persona> fromPersona = null;
		 TypedQuery<Persona> query = null;
		 Persona persona = null;
		 
		 
		 //Query utilizando el API de criteria 
		 log.debug("N1 --> Consulta de todas las personas");
		 
		 //1 el objeto EntityManager crea instancia de criteriabuilder
		 
		 cb = em.getCriteriaBuilder();
		 
		 //2 se crea un objeto criteriaQuery
		 criteriaQuery = cb.createQuery(Persona.class);
		 
		 
		 //3 creamos el objeto raiz del query
		 fromPersona = criteriaQuery.from(Persona.class);
		 
		 
		 //4 seleccionamos lo necesario del from
		 criteriaQuery.select(fromPersona);
		 
		 //5 creamos el query typesafe
		 query = em.createQuery(criteriaQuery);
		 
		 //6 ejecutar la consulta
		 List<Persona> personas = query.getResultList();
		 mostrarPersonas(personas);
		 
		 
		 
		 // 2-a) Consulta de la persona con id = 6
		 log.debug("N2-a --> Consulta de la persona con id = 6");
		 cb = em.getCriteriaBuilder();
		 criteriaQuery = cb.createQuery(Persona.class);
		 fromPersona = criteriaQuery.from(Persona.class);
		 criteriaQuery.select(fromPersona).where(
				 cb.equal(fromPersona.get("idPersona"), 6));
		 persona = (Persona) em.createQuery(criteriaQuery).getSingleResult();
		 log.debug(persona);
		 
		 // 2-b) Consulta de la persona con id = 3
		 // jpql = "select p from Persona p where p.personaId = 3";
		 log.debug("N2-b --> Consulta de la persona con id = 3, forma dinamica");
		 cb = em.getCriteriaBuilder();
		 criteriaQuery = cb.createQuery(Persona.class);
		 fromPersona = criteriaQuery.from(Persona.class);
		 criteriaQuery.select(fromPersona);
		 
		 
		 //La clase Predicate permite agregar varios criterios dinamicamiente
		 List<Predicate> criterios = new ArrayList<Predicate>();
		 
		 
		 //Verificamos si tenemos criterios que agregar
		 Integer idPersonaParam = null;
		 idPersonaParam = 3;
		 
		 if(idPersonaParam != null) {
			 ParameterExpression<Integer> p = cb.parameter(Integer.class, "idPersona");
			 criterios.add(cb.equal(fromPersona.get("idPersona"),p));
		 }//fin del if idPersonaParam!= null
		 
		 //se agregaron los criterios 
		 if(criterios.isEmpty()) {
			 	throw new RuntimeException("Sin criteriaQuery");			 
		 }
		 else if(criterios.size() == 1) {
			 criteriaQuery.where(criterios.get(0));
		 }
		 else {			 
			 criteriaQuery.where( cb.and( criterios.toArray(new Predicate[0] ) ) );
		 }
		 
		 
		 //se crea el query con los criterios 
		 query = em.createQuery(criteriaQuery);
		 if(idPersonaParam != null) {
			 query.setParameter("idPersona", idPersonaParam);
		 }
		 
		 //se ejecuta el query
		 persona = query.getSingleResult();
		 log.debug(persona);
		 
		 
		 		
	}//fin del metodo main
	 

	    private static void mostrarPersonas(List<Persona> personas) {
	        
	        for (Persona persona : personas) {
	                    System.out.println(persona);
	        }//fin del for Personas
	    }//fin del metodo mostrarPersonas	 
	 
	 
	 
	
	
	

}//fin de la clase PruebaApiCriteria 
