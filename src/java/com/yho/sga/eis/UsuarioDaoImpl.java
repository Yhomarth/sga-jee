/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yho.sga.eis;

import com.yho.sga.domain.Usuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author y.reyes
 */

@Stateless
public class UsuarioDaoImpl implements UsuarioDao {
    
    @PersistenceContext(unitName = "UsuarioPU")
    EntityManager em;

    @SuppressWarnings("unchecked")
	public List<Usuario> findAllUsuarios() {
        return em.createNamedQuery("Usuario.findAll").getResultList(); 
    }

    public Usuario findUsuarioById(Usuario usuario) {
        return em.find(Usuario.class, usuario.getIdUsuario());
    }

    public Usuario findPersonaByUserName(Usuario usuario) {
        return em.find(Usuario.class, usuario.getUsername());
    }

    public void insertPersona(Usuario usuario) {
        em.persist(usuario);
    }

    public void updatePersona(Usuario usuario) {
        em.merge(usuario);
    }

    public void deletePersona(Usuario usuario) {
        em.merge(usuario);
        em.remove(usuario);
    }
    
}//fin de la clase UsuarioDaoImpl
