<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt'%>     
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix='f' uri="http://java.sun.com/jstl/fmt_rt"%>
<%@ taglib prefix="spring" uri="/spring"%>
<%@ taglib uri="/WEB-INF/permisos.tld" prefix="securityCa" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Puntitos Telcel</title>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
    	<link rel="stylesheet" type="text/css" href='<c:url value="/commons/css/impromptus.css"/>' media='screen' >
        
		<script type="text/javascript" src='<c:url value="/commons/js/jquery-1.2.3.pack.js"/>'></script>
		<script type='text/javascript' src='<c:url value="/commons/js/jquery.form.js"/>' ></script>
		<script type="text/javascript" src='<c:url value="/commons/js/jquery-impromptu.1.3.js"/>' ></script>
		<script type="text/javascript" src='<c:url value="/commons/js/puntitos.js"/>' ></script>
		
</head>
    <body>
       <form action="./consultaLinea.do" method="post" id="frmconsulta" name="frmconsulta" AUTOCOMPLETE="OFF">
       <DIV style="width: 100%; FILTER: progid:DXImageTransform.Microsoft.Shadow(color='#aaaacc', Direction=135, Strength=2); 
	border-bottom-width: 2px; border-bottom-style: solid; border-bottom-color: #4d7097; font-size: 1px;">
</DIV>
            <table width="100%"  cellspacing="0" cellpadding="0" align="center"  >
                <tr> 
                    <td colspan="3" class="titulo">&nbsp;&nbsp;Consulta Puntitos</td>
                </tr>
                <tr>
                    <td width="15%" class="healineblue1" align="left" height="20"> 
                        <input type="radio" name="operacion" id="operacion1" value="0" checked>Tel&eacute;fono 
                    </td>
                    <td class="textonegroBlodTrs" colspan="2" width="85%" height="20" align="left"> 
                        <input type="text" id="telefono" name="telefono" maxlength="10" onfocus="seleccionaOpcion('telefono');">                        
                    </td>                  
                </tr>
                <tr> 
                    <td width="15%" class="healineblue1" align="left" height="20">
                    	<input type="radio" name="operacion" id="operacion2" value="1">Cuenta</td>
                    <td class="textonegroBlodTrs" width="35%" height="20" align="left"> 
                        <input type="text" id="cuenta" name="cuenta" maxlength="10" onfocus="seleccionaOpcion('cuenta')">
                    </td>
                    <td align="center" width="50%">
                    	<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="38">
                    		<input type="button" id="Buscar" value="BUSCAR" onclick="agregar()"	class="botonInactivo">
                    	</securityCa:validaPerfil>
                    </td>
                </tr>
                </table>
<DIV style="width: 100%; FILTER: progid:DXImageTransform.Microsoft.Shadow(color='#aaaacc', Direction=135, Strength=2); 
	border-bottom-width: 2px; border-bottom-style: solid; border-bottom-color: #4d7097; font-size: 1px;">
</DIV>
<input type="hidden" name="region" id="region">
<input type="hidden" name="estatusPuntos" id="estatusPuntos">
<input type="hidden" name="cuentaAlt" id="cuentaAlt">
         <div id="listados">
         <table>
				<tr><td class="titulo">Datos de la Línea</td></tr>
			</table>
			<table id="tablaLista" border="1" cellspacing="0" cellpadding="0">
				<thead>
					<tr class="healineblue1" bgcolor="#ECF0DB" align="center">
						<td>&nbsp;Tel&eacute;fono&nbsp; </td>
						<td>&nbsp;Estatus Puntos&nbsp;</td>
						<td>&nbsp;Cuenta&nbsp;</td>
						<td>&nbsp;Regi&oacute;n&nbsp; </td>
						<td>&nbsp;Secuencia&nbsp; </td>
						<td>&nbsp;Plan&nbsp; </td>
						<td>&nbsp;Ciclo&nbsp; </td>
						<td>&nbsp;Estatus Tel&eacute;fono&nbsp; </td>
						<td>&nbsp;Addendum&nbsp; </td>
						<td>&nbsp;Fecha Addendum&nbsp; </td>
						<td>&nbsp;Fecha Alta&nbsp; </td>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
			</div>
         </form>                         
    </body>
</html>