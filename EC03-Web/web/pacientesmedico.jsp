<%-- 
    Document   : pacientesmedico
    Created on : 06-feb-2019, 11:54:16
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
        <%
        String nommed=request.getParameter("nommed");
        String codmed=request.getParameter("codmed");
        
        %>
        <h1>Lista de Pacientes del Medico <%=nommed%> </h1>
        <h2><a href="/EC03-Web/nuevacita.jsp?codmed=<%=codmed%>">Nuevo Paciente</a></h2>
        
        <table border="1">
            <thead>
                <tr>
                    <td>Codigo</td>
                    <td>Nombre</td>
                    <td>Tipo</td>
                    <td>Pago</td>
                    <td>Anular</td>
                </tr>
            </thead>
            <tbody>
            <%
                try{
                
                    List list=SQL.sqlConsulta("call sp_ObtenerCitasMedico('"+codmed+"');"); 
                    if (list!=null) {
                        for (int i = 0; i < list.size(); i++) {
                            Object[] fila=(Object[])list.get(i);
                            out.print("<tr>");
                            out.print("<td>"+fila[0]+"</td>");
                            out.print("<td>"+fila[1]+"</td>");
                            out.print("<td>"+fila[2]+"</td>");
                            out.print("<td>"+fila[3]+"</td>");
                            out.print("<td>");
                            out.print("<a href='/EC03-Web/SCitas?codcita="+fila[0]+"'>");
                            out.print("Borrar");
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
            
        <h2>....... <a href="/EC03-Web/listaespecialidades.jsp">Retornar</a></h2>
    </body>
</html>
