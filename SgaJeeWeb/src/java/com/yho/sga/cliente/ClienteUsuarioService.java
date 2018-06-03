/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yho.sga.cliente;

import com.yho.sga.domain.Usuario;
import com.yho.sga.servicio.UsuarioServiceRemote;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author y.reyes
 */
public class ClienteUsuarioService {
    
    
    public static void main(String[] args) {
        System.out.println("Iniciando la llamada al EJB Cliente!!");
        
        try{
            Context jndi = new InitialContext();
            UsuarioServiceRemote usuarioService = 
                    (UsuarioServiceRemote) jndi.lookup("java:global/sga-jee/UsuarioServiceImpl!com.yho.sga.servicio.UsuarioServiceRemote");
            
            List<Usuario> usuarios = usuarioService.listarUsuarios();
            
            for(Usuario usuario : usuarios){
                System.out.println(usuario);
            }//end of for statement
            
            System.out.println("Fin de la llamada del EJB");
            
        }//end of try
        
        catch(NamingException nex){
            nex.printStackTrace();
        }
        
    }//end of main method
    
}
