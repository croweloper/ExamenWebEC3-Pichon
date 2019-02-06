<%-- 
    Document   : nuevacita
    Created on : 06-feb-2019, 12:13:48
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
        String codmed=request.getParameter("codmed");
        String ultimacita="";
        
        
        try{
                
                List list=SQL.sqlConsulta("call sp_ObtenerUltimaCita();"); 
                    if (list!=null) {
                        for (int i = 0; i < list.size(); i++) {
                            Object[] cita=(Object[])list.get(i); 
                            out.print("<tr>");
                            ultimacita=cita[0].toString();
                            out.print("<td>"+cita[0]+"</td>");
                            out.print("</tr>");
                        }
                    }
                }catch(Exception e){
                    System.out.println(e.getMessage().toString());
                }
            %>
        <h1>Registro de Pacientes</h1>
        <form>
            <table border="1">
                <tr>
                    <td>Codigo</td>
                    <td><input type="text" name="txtcita" value="<%=ultimacita%>"></td>                    
                </tr>
                <tr>
                    <td>Nombre</td>
                    <td><input type="text" name="txtnombre"></td>                    
                </tr>
                <tr>
                    <td>Tipo de Paciente</td>
                    <td>
                        <input type="radio" value="1" name="rbtipo">Asegurado
                        <input type="radio" value="2" name="rbtipo">Empresa
                        <input type="radio" value="2" name="rbtipo">Ambulatorio
                        
                    </td>                    
                </tr>
                <tr>
                    <td>Pago</td>
                    <td><input type="text" name="txtpago"></td>                    
                </tr>
                <tr>
                <input type="hidden" name="codmed" value="<%=codmed%>" >
                    <td><input type="submit" value="Enviar"></td>                    
                </tr>
                
            </table>
        </form>
    </body>
</html>
