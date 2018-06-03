package com.yho.sga.cliente.transacciones;

import javax.naming.Context;
import javax.naming.InitialContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.yho.sga.domain.Persona;
import com.yho.sga.servicio.PersonaServiceRemote;

public class PruebaManejoTransacciones {
	
	static Logger log = LogManager.getRootLogger();
	
	public static void main(String[] args) throws Exception {
		Context jndi = new InitialContext();
		PersonaServiceRemote personaService = (PersonaServiceRemote) jndi.lookup(""
				+ "java:global/sga-jee/PersonaServiceImpl!com.yho.sga.servicio.PersonaServiceRemote");
		log.debug("Iniciando prueba manejo transaccional PersonaServiceRemote");
		
		//Buscamos un objeto persona
		Persona persona1 = personaService.encontrarPersonaPorId(new Persona(1));
		
		//cambiamos la persona 
		//persona1.setApellidoMaterno("Cambio con error ..................................................................");
		persona1.setApellidoMaterno("Lizardo");
		
		personaService.modificarPersona(persona1);
		log.debug("objeto modificado :"+ persona1);
		log.debug("fin de prueba EJB PersonaService");				
		
	}//fin del metodo main 

}//fin de la clase PruebaManejoTransacciones
