<%-- 
    Document   : listaespecialidades
    Created on : 06-feb-2019, 9:42:42
    Author     : crowe
--%>

<%@page import="com.idat.SQL.SQL"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Lista de Especialidades</h1>
        
        
        <table border="1">
            <thead>
                <tr>
                    <td>Codigo</td>
                    <td>Especialidad</td>
                    <td>Costo</td>
                    <td>Medicos</td>
                </tr>
            </thead>
            <tbody>
            <%
                List list=SQL.sqlConsulta("call sp_ObtenerEspecialidades();"); 
                if (list!=null) {
                    for (int i = 0; i < list.size(); i++) {
                        Object[] fila=(Object[])list.get(i);
                        out.print("<tr>");
                        out.print("<td>"+fila[0]+"</td>");
                        out.print("<td>"+fila[1]+"</td>");
                        out.print("<td>"+fila[2]+"</td>");
                        out.print("<td>");
                        out.print("<a href='/EC03-Web/medicoespecialidad.jsp?codesp="+fila[0]+"'>");
                        out.print("Ver");
                        out.print("</td>");
                        out.print("</tr>");
                    }
                }
            %>
            </tbody>
        </table>
            
        
            
    </body>
</html>
