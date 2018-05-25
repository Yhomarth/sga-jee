<%-- 
    Document   : listarPersonas
    Created on : May 24, 2018, 6:36:57 PM
    Author     : y.reyes
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Listado de Personas</h1>        
        
        <a href="agregarPersonas.jsp">Agregar Personas</a>
        <br />
        <br />
        
        <table border="1">
                <tr>
                    <th>Nombre</th>
                    <th>Apellido Paterno</th>
                    <th>Email</th>
                </tr>
                <c:forEach var="persona" items="${personas}">
                <tr>
                    <td><a href="ServletControlador?accion=editar&idPersona=${persona.idPersona}"> ${persona.nombre} </a> </td>
                    <td>${persona.apellidoPaterno}</td>
                    <td>${persona.email}</td>
                </tr>    
                </c:forEach>                
        </table>
        <br/>
        <a href="index.jsp">Regresar al inicio</a>
    </body>
</html>
