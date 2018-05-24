/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yho.sga.eis;

import com.yho.sga.domain.Persona;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author yhomarth
 */

@Stateless
public class PersonaDaoImpl implements PersonaDao {
    
    @PersistenceContext(unitName = "PersonaPU")
    EntityManager em;
    
    @SuppressWarnings("unchecked")
	public List<Persona> findAllPersonas(){
        return em.createNamedQuery("Persona.findAll").getResultList();
    }//fin del metodo findAllPersonas
    
    public Persona findPersonaById(Persona persona){
        return em.find(Persona.class, persona.getIdPersona());
    }//fin del metodo findPersonaById
    
     public Persona findPersonaByEmail(Persona persona) {
        Query query = em.createQuery("from Persona p where p.email =: email");
        query.setParameter("email", persona.getEmail());
        return (Persona) query.getSingleResult();
    }

    public void insertPersona(Persona persona) {
        em.persist(persona);
    }

    public void updatePersona(Persona persona) {
        em.merge(persona);
    }

    public void deletePersona(Persona persona) {
        em.merge(persona);
        em.remove(persona);
    }

    
}//fin de la clase PersonaDaoImpl
