<%-- 
    Document   : agregarPersonas
    Created on : May 24, 2018, 7:12:17 PM
    Author     : y.reyes
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Agregar Personas</title>
    </head>
    <body>
        <h1>Formulario para agregar personas</h1>
        
        <form name="frmPersonas" action="ServletControlador" method="Post">
            <input type="hidden" name="accion" value="agregar" />
            <table>
                <tr>
                    <td> <strong>Nombre :</strong> </td>
                    <td> <input type="text" size="45" name="nombrePersona" /> </td>                    
                </tr>
                
                <tr>
                    <td> <strong>Apellido Paterno :</strong> </td>
                    <td> <input type="text" size="45" name="apellidoPaterno" /> </td>                    
                </tr>

                <tr>
                    <td> <strong>Apellido Materno :</strong> </td>
                    <td> <input type="text" size="45" name="apellidoMaterno" /> </td>                    
                </tr>      
                
                <tr>
                    <td> <strong>Email :</strong> </td>
                    <td> <input type="text" size="45" name="email" /> </td>                    
                </tr>  
                
                <tr>
                    <td> <strong>Telefono :</strong> </td>
                    <td> <input type="text" size="45" name="telefono" /> </td>                    
                </tr>
                
                <tr>
                    <td><input type="submit" value="Aceptar" /> </td>
                </tr>
                
            </table>
           
            
        </form>
        
        <br/>
        <br/>
        <a href="index.jsp">Regresar al Inicio</a>
        
    </body>
</html>
