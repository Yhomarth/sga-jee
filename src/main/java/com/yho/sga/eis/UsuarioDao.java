/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yho.sga.eis;

import com.yho.sga.domain.Usuario;
import java.util.List;

/**
 *
 * @author y.reyes
 */
public interface UsuarioDao {
    public List<Usuario> findAllUsuarios();
    public Usuario findUsuarioById(Usuario usuario);
    public Usuario findPersonaByUserName (Usuario usuario);
    public void insertPersona(Usuario usuario);
    public void updatePersona(Usuario usuario);
    public void deletePersona(Usuario usuario);    
}//fin de la interfaz ClienteDao
