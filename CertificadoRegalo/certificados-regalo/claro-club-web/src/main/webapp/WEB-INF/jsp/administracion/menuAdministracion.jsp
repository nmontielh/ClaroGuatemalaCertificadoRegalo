<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/permisos.tld" prefix="securityCa" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Menu Admin</title>

<c:set var="perfil" value="${sessionScope.usuarioTO.perfilTO.idPerfilN}" />

<link rel="stylesheet" href='<c:url value="/commons/js/sc_textsheet.css"/>' type="text/css">
<script src='<c:url value="/commons/js/administracion.js"/>' type="text/javascript"></script>
</head>
<body onload="inicializaMenuadmin();" >

	<TABLE border=0 cellspacing=0>
		<TR valign="middle" align="center" height="32px">
			<!--<TD >
				<SPAN id="B01" class="botonInactivo" onclick="eligeOpcionSubMenu(1)">Password</SPAN>
 			</TD>-->
 			<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="15">
				<TD>
	    			<SPAN id="B04" class="botonInactivo" onclick="eligeOpcionSubMenu(4)">Avisos</SPAN>
	    		</TD>
	    	</securityCa:validaPerfil>
    		<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="16">
    			<TD>
    				<SPAN id="B02" class="botonInactivo" onclick="eligeOpcionSubMenu(2)">Puntos Venta</SPAN>
    			</TD>
    		</securityCa:validaPerfil>
    		<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="17">
    			<TD>
    				<SPAN id="B03" class="botonInactivo" onclick="eligeOpcionSubMenu(3)">Promociones</SPAN>
    			</TD>
    		</securityCa:validaPerfil>
    		<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="166">
    			<td>
    				<SPAN id="B05" class="botonInactivo" onclick="eligeOpcionSubMenu(5)">Reportes</SPAN>
    			</td>
    		</securityCa:validaPerfil>
    		
    		<!-- MÓDULO ASIGNACIÓN DE PUNTOS - JAPA 08/02/2013 Folio 121733 -->
    		<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="174"> 
    		<td>
    			<SPAN id="B06" class="botonInactivo" onclick="eligeOpcionSubMenu(6)">Puntos</SPAN>
    		</td>
    		</securityCa:validaPerfil>
    	</TR>
	</TABLE> 
	<DIV id="TarjetaAdmin3" class="TarjetaDetalle" style=" top:43px;height: 460px;width: 100%;visibility: hidden;display: none;left: 10px;position:absolute;BORDER:solid 2px #4d7097;">
		<IFRAME src="./commons/ProcesandoInfo.html" name="FrameAdmin3" id="FrameAdmin3" scrolling="no" WIDTH="100%" HEIGHT="100%" style="border: hidden;" frameborder="0" AllowTransparency></IFRAME>
	</DIV>
	<DIV id="TarjetaAdmin" class="TarjetaDetalle" style="top:43px;height: 460px;width: 100%;visibility: visible;display: block;left: 10px;position:absolute;BORDER:solid 2px #4d7097;background: transparent;">
		<IFRAME name="FrameAdmin" id="FrameAdmin" scrolling="no" WIDTH="100%" HEIGHT="100%" style="border: hidden;background: transparent;" frameborder="0"></IFRAME>
	</DIV>	      	
</body>
</html>