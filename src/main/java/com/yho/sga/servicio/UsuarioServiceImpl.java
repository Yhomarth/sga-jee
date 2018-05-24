/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yho.sga.servicio;

import com.yho.sga.domain.Usuario;
import com.yho.sga.eis.UsuarioDao;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author y.reyes
 */
@Stateless
public class UsuarioServiceImpl implements UsuarioServiceRemote, UsuarioService {
    
    @EJB
    private UsuarioDao usuarioDao;

    public List<Usuario> listarUsuarios() {
        return usuarioDao.findAllUsuarios();
    }

    public Usuario encontrarUsuarioPorId(Usuario usuario) {
        return usuarioDao.findUsuarioById(usuario);
    }

    public Usuario encontrarUsuarioPorUserName(Usuario usuario) {
        return usuarioDao.findPersonaByUserName(usuario);
    }

    public void registrarUsuario(Usuario usuario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void modificarUsuario(Usuario usuario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void eliminarUsuario(Usuario usuario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
