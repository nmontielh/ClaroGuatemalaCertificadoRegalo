<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
 <%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
 
 <table width="98%" border="1" cellspacing="0" cellpadding="0" align="center" >
        <tr>          
          <td bgcolor="#ECF0DB" class="healineblue1" width="16%">&nbsp;Sistema</td>
          <td class="textonegroBlod" width="17%">&nbsp;${telefonoTO.sistema}</td>
          <td bgcolor="#ECF0DB" class="healineblue1" width="17%">&nbsp;Fecha alta</td>
          <td class="textonegroBlod" width="17%">&nbsp;${telefonoTO.fechaAlta}</td>
          <td bgcolor="#ECF0DB" class="healineblue1" width="16%">&nbsp;Ciclo</td>
          <td class="textonegroBlod" width="17%">&nbsp;${telefonoTO.ciclo}</td>
        </tr>        
        <tr> 
          <td bgcolor="#ECF0DB" class="healineblue1" width="16%">&nbsp;Fecha adendum</td>          
          <td class="textonegroBlod" width="17%" id="fecAddM2K">&nbsp;${telefonoTO.mobileTO.fecAddM2K}</td>
          <td bgcolor="#ECF0DB" class="healineblue1" width="17%">&nbsp;Fecha alta M2K</td>
          <td class="textonegroBlod" width="17%">&nbsp;${telefonoTO.mobileTO.fecAltaUser}</td>
          <td bgcolor="#ECF0DB" class="healineblue1" width="16%">&nbsp;Estatus</td>
          <td class="textonegroBlod" width="17%" id="estatusTel">&nbsp;${telefonoTO.mobileTO.status}</td>
        </tr>
        <tr>                 
          <td bgcolor="#ECF0DB" class="healineblue1" width="17%">&nbsp;Plan</td>
          <td class="textonegroBlod" width="17%">&nbsp;${telefonoTO.mobileTO.planM2K}</td>
          <td bgcolor="#ECF0DB" class="healineblue1" width="16%">&nbsp;Adendum</td>
          <td class="textonegroBlod" width="17%" colspan="3" id="adendum">&nbsp;${telefonoTO.mobileTO.addM2K}</td>
        </tr>
 </table>
 
 