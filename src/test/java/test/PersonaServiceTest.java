/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import com.yho.sga.domain.Persona;
import com.yho.sga.domain.Usuario;
import com.yho.sga.servicio.PersonaService;
import com.yho.sga.servicio.UsuarioService;
import java.util.List;
import javax.ejb.embeddable.EJBContainer;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author yhomarth
 */
public class PersonaServiceTest {

    private PersonaService personaService;
    private UsuarioService usuarioService;

    @Before
    public void setUp() throws Exception {
        EJBContainer contenedor = EJBContainer.createEJBContainer();
        personaService = (PersonaService) contenedor.getContext().lookup("java:global/classes/PersonaServiceImpl!com.yho.sga.servicio.PersonaService");
        usuarioService = (UsuarioService) contenedor.getContext().lookup("java:global/classes/UsuarioServiceImpl!com.yho.sga.servicio.UsuarioService");
    }

    
    public void testEJBPersonaService() {
        System.out.println("Iniciando test EJB PersonaService");
        assertTrue(personaService != null);

        //assertEquals(2, personaService.listarPersonas().size());

        System.out.println("El no. de personas es igual a:" + personaService.listarPersonas().size());

        this.desplegarPersonas(personaService.listarPersonas());
        System.out.println("Fin test EJB PersonaService");
    }

    public void testEJBUsuarioService() {
        System.out.println("Iniciando test EJB UsuarioService");
        assertTrue(usuarioService != null);

        //assertEquals(1, usuarioService.listarUsuarios().size());

        System.out.println("El no. de personas es igual a:" + usuarioService.listarUsuarios().size());

        this.desplegarUsuarios(usuarioService.listarUsuarios());
        System.out.println("Fin test EJB UsuarioService");
    }
    
    @Test
    public void testEJB(){
        this.testEJBPersonaService();
        this.testEJBUsuarioService();
    }
    
  
    
    
    private void desplegarPersonas(List<Persona> personas) {
        for (Persona persona : personas) {
            System.out.println(persona);
        }
    }
    
        private void desplegarUsuarios(List<Usuario> usuarios) {
        for (Usuario usuario : usuarios) {
            System.out.println(usuario);
        }
    }
}
