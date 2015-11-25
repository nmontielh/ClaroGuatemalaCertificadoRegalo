<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt'%>     
<%@ taglib prefix="spring" uri="/spring"%>
<%@ taglib uri="/WEB-INF/permisos.tld" prefix="securityCa"  %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- MÓDULO ASIGNACIÓN DE PUNTOS - JAPA 11/02/2013 Folio 121733 -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Administración de Asignación de Puntos</title>
<link rel="stylesheet" type="text/css" href='<c:url value="/commons/css/impromptus.css"/>' media='screen' >
<link rel="stylesheet" href='<c:url value="/commons/js/sc_textsheet.css"/>' type="text/css">
        
<script type="text/javascript" src='<c:url value="/commons/js/jquery-1.2.3.pack.js"/>'></script>
<script type='text/javascript' src='<c:url value="/commons/js/jquery.form.js"/>' ></script>
<script type="text/javascript" src='<c:url value="/commons/js/jquery-impromptu.1.3.js"/>' ></script>
<script type="text/javascript" src='<c:url value="/commons/js/puntitos.js"/>' ></script>
<script type="text/javascript">

	function Selecciona(accion) {
		
		if(accion == 1) {
			document.frmConsulta.action 				= "./menuAsignacionPuntos.do";
			document.getElementById("accion").value 	= "PEFILES";
		} else if(accion == 2) {
			document.frmConsulta.action 				= "./menuAsignacionPuntos.do";
			document.getElementById("accion").value 	= "MOTIVOS";
		} else if(accion == 3){
			document.frmConsulta.action 				= "./lineasPruebaAsignacion.do";
		}

		document.frmConsulta.submit();
	}






	function consulta(){
		var perfiles 	= document.getElementById('Perfiles');
		var motivos 	= document.getElementById('Motivos');
			
		if(perfiles.checked==false && motivos.checked==false) {
			alert("Debe eligir una opción para consultar.");
			return false;
		}
		
		document.frmConsulta.action = "./menuAsignacionPuntos.do";
		document.frmConsulta.submit();
	}
</script>
</head>
<body>
<form action="./motivosAsignacion.do" method="post" name="frmConsulta" id="frmConsulta">
<input type="hidden" name="accion" id="accion" value=""/>
<table width="100%">
	<tr>
		<td height="30" class="subtitulo">Asignaci&oacute;n de Puntos</td>
		<td align="right" >
			<div class="txtSumNormal" id="divLoading" style="display: none;">
				<img src="./imagenes/loading.gif"/>Buscando infomaci&oacute;n&nbsp;&nbsp;</div> 
		</td>	
	</tr>
</table>
<table width="100%">
	<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="176">
	<tr>
		<td width="10"><input type="radio" name="seleccion" id="Grupo" onclick="Selecciona(1);" /></td>
		<td class="healineblue1">Perfiles V&aacute;lidos</td>
	</tr>
	</securityCa:validaPerfil>
	<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="181">
	<tr>
		<td><input type="radio" name="seleccion" id="Planes" onclick="Selecciona(2);"/></td>
		<td class="healineblue1">Motivos de Asignaci&oacute;n</td>
	</tr>
	</securityCa:validaPerfil>
	<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="187">
		<tr>
			<td><input type="radio" name="seleccion" id="lineasPrueba" onclick="Selecciona(3);"/></td>
			<td class="healineblue1">Lineas de Prueba</td>		
		</tr>
	</securityCa:validaPerfil>
	
	
	<!-- 
	<tr>
		<td>&nbsp;</td>
		<td align="right">
			<table align="right">
			<tr>
				<td>&nbsp;</td>
				<td>
				 	<a class="LinkOut" style="width:30; text-align:center" onmouseover='this.className="LinkIn";'
					onmouseout='this.className="LinkOut";' id="btnArchivo" onClick="consulta();">&nbsp;CONSULTA&nbsp;&nbsp;</a>	
				</td>
			</tr>
			</table>
		</td>
	</tr>
	 -->
</table>	
<DIV style="width: 100%; FILTER: progid:DXImageTransform.Microsoft.Shadow(color='#aaaacc', Direction=135, Strength=2); 
	border-bottom-width: 2px; border-bottom-style: solid; border-bottom-color: #4d7097; font-size: 1px;">
</DIV>
</form>
</body>
</html>