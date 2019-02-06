<%-- 
    Document   : nuevacita
    Created on : 06-feb-2019, 12:13:48
    Author     : crowe
--%>

<%@page import="com.idat.controller.CitasJpaController"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="com.idat.model.Citas"%>
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
                        ultimacita=cita[0].toString();
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
                        <input type="radio" value="3" name="rbtipo">Ambulatorio
                        
                    </td>                    
                </tr>
                <tr>
                    <td>Pago</td>
                    <td><input type="text" name="txtpago" ></td>                    
                </tr>
                <tr>
                <input type="hidden" name="codmed" value="<%=codmed%>" >
                <td><input type="submit" name="btnenviar" value="Enviar"></td>                    
                </tr>
                
            </table>
        </form>
            
        <%
         if(request.getParameter("btnenviar")!=null){ 
             
             String nombrepaciente=request.getParameter("txtnombre");
             Integer tipo=Integer.parseInt(request.getParameter("rbtipo"));
                          
             double preciocita=0.0;
             
             try{
                List listaprecio=SQL.sqlConsulta("call sp_ObtenerPrecioxEspecialidadMedico('"+codmed+"');"); 
                if (listaprecio!=null) {
                    for (int i = 0; i < listaprecio.size(); i++) {
                        Object[] precio=(Object[])listaprecio.get(i); 
                        preciocita=Double.parseDouble(precio[1].toString());
                    }
                }
            }catch(Exception e){
                System.out.println(e.getMessage().toString());
            }
            
            
            if(tipo==1){
                preciocita=preciocita-(preciocita*50/100);
            }else if(tipo==2){
                preciocita=preciocita-(preciocita*30/100);
            }else if(tipo==3){
                //no pasa nada :v
            }
             
            BigDecimal pago = new BigDecimal(preciocita);
            
            
            Citas nuevacita=new Citas();
            nuevacita.setNrocita(Integer.parseInt(ultimacita));
            nuevacita.setNompac(nombrepaciente);
            nuevacita.setTipo(tipo);
            nuevacita.setCodmed(request.getParameter("codmed"));
            nuevacita.setPago(pago);
            
            try {
                    CitasJpaController citasjps=new CitasJpaController();
                    citasjps.create(nuevacita);
            } catch (Exception e) {
            }
            
            
            System.out.println(nuevacita.toString());
            
            System.out.println(Integer.parseInt(ultimacita)+nombrepaciente+tipo+codmed+pago+preciocita);
            
            response.sendRedirect("/EC03-Web/listaespecialidades.jsp");

             
         
         } 
        %>        
                
                
                
    </body>
</html>
