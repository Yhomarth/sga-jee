/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yho.sga.servicio;

import com.yho.sga.domain.Persona;
import com.yho.sga.eis.PersonaDao;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

/**
 *
 * @author yhomarth
 */
@Stateless
public class PersonaServiceImpl implements PersonaServiceRemote, PersonaService{
	
    @Resource
    private SessionContext contexto;
    
    @EJB
    private PersonaDao personaDao;
    
    @Override
    public List<Persona> listarPersonas(){
        return personaDao.findAllPersonas();       
    }
    
    @Override
    public Persona encontrarPersonaPorId(Persona persona) {
        return personaDao.findPersonaById(persona);
    }
    
    @Override
    public Persona encontrarPersonaPorEmail(Persona persona) {
        return personaDao.findPersonaByEmail(persona);
    }

    @Override
    public void registrarPersona(Persona persona) {
        personaDao.insertPersona(persona);
    }

    @Override
    public void modificarPersona(Persona persona) {
    	try {
    		personaDao.updatePersona(persona);
    	} catch (Throwable e) {
			contexto.setRollbackOnly();
			e.printStackTrace(System.out);
		}
    }

    @Override
    public void eliminarPersona(Persona persona) {
    	personaDao.deletePersona(persona);
    }
    
}
