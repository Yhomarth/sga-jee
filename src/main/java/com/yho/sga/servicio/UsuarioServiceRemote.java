/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yho.sga.servicio;

import com.yho.sga.domain.Usuario;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author y.reyes
 */
@Remote
public interface UsuarioServiceRemote {
    
    public List<Usuario> listarUsuarios();
    public Usuario encontrarUsuarioPorId(Usuario usuario);
    public Usuario encontrarUsuarioPorUserName(Usuario usuario);
    public void registrarUsuario(Usuario usuario);
    public void modificarUsuario(Usuario usuario);
    public void eliminarUsuario(Usuario usuario);
    
    
}
