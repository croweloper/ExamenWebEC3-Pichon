<%-- 
    Document   : medicoespecialidad
    Created on : 06-feb-2019, 10:33:21
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
                    <td>Nombre</td>
                    <td>Foto</td>
                    <td>Experiencia</td>
                    <td>Citas</td>
                </tr>
            </thead>
            <tbody>
            <%
                try{
                
                    String codesp=request.getParameter("codesp");

                    List list=SQL.sqlConsulta("call sp_ObtenerMedicoEspecialidad('"+codesp+"');"); 
                    if (list!=null) {
                        for (int i = 0; i < list.size(); i++) {
                            Object[] fila=(Object[])list.get(i);
                            out.print("<tr>");
                            out.print("<td>"+fila[0]+"</td>");
                            out.print("<td>"+fila[1]+"</td>");
                            out.print("<td><img src='http://portafolio.deiviherrera.com/fotosp/"+fila[0]+".jpg'></td>");
                            out.print("<td>"+fila[2]+"</td>");
                            out.print("<td>");
                            out.print("<a href='/EC03-Web/pacientesmedico.jsp?codmed="+fila[0]+"&nommed="+fila[1]+"'>");
                            out.print("Ver");
                            out.print("</td>");
                            out.print("</tr>");
                        }
                    }
                }catch(Exception e){
                    System.out.println(e.getMessage().toString());
                }
            %>
            </tbody>
        </table>
            
        <h2>....... <a href="index.html">Retornar</a></h2>
    </body>
</html>
