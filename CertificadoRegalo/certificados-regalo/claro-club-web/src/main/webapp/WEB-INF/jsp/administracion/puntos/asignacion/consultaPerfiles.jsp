<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt'%>     
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix='f' uri="http://java.sun.com/jstl/fmt_rt"%>
<%@ taglib prefix="spring" uri="/spring"%>
<%@ taglib uri="/WEB-INF/permisos.tld" prefix="securityCa" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- MÓDULO ASIGNACIÓN DE PUNTOS - JAPA 20/02/2013 Folio 121733 -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Administración de Perfiles de Asignacion</title>
<link rel="stylesheet" type="text/css" href='<c:url value="/commons/css/impromptus.css"/>' media='screen' >
<script type="text/javascript" src='<c:url value="/commons/js/jquery-1.2.3.pack.js"/>'></script>
<script type='text/javascript' src='<c:url value="/commons/js/jquery.form.js"/>' ></script>
<script type="text/javascript" src='<c:url value="/commons/js/jquery-impromptu.1.3.js"/>' ></script>
<!-- <script type="text/javascript" src='<c:url value="/commons/js/puntitos.js"/>' ></script> -->
<script type="text/javascript">

	function selecPuesto() {
		var idPerfilN = document.getElementById("idPerfilN").value;
		document.getElementById("idPerfilN").value = idPerfilN;
	}
	
	function consultaPerfil(){
		var idPerfilN 	= document.getElementById("idPerfilN").value;
		var ptosMaximos = document.getElementById("ptosMaximos").value;
		document.getElementById("Agregar").disabled = false;
		
		if(isNaN(ptosMaximos)) {
			mensaje("Seleccione un número máximo de puntos a asignar válido.");
			return false;
		}
		
		parent["detallePerfilAsig"].document.forms[0].idPerfilN.value=idPerfilN;
		parent["detallePerfilAsig"].document.forms[0].ptosMaximos.value=ptosMaximos;
		parent["detallePerfilAsig"].document.forms[0].action = "./detallePerfiles.do";
		parent["detallePerfilAsig"].document.forms[0].submit();
	}
	
	function mensaje(informacion){        		      
		$.prompt(informacion, {prefix:'cleanblue', buttons:{Aceptar:true}});
		return false;
	}
	
	function agregarPerfil() {
		var idPerfilN 		= document.getElementById("idPerfilN").value;
		var ptosMaximos 	= document.getElementById("ptosMaximos").value;
		
		if(idPerfilN == -1) {
			mensaje("Seleccione un perfil a agregar");
			return false;
		}
		
		//parent["detallePerfilAsig"].document.body.innerHTML = "";
		document.getElementById("accion").value 			= "AGREGAR";
		document.frmConsultaPerfiles.action 				= "./consultaPerfiles.do";
		document.frmConsultaPerfiles.submit();
	}
	
	function actualizarPerfil() {
		var idPerfilN 		= document.getElementById("idPerfilN").value;
		var ptosMaximos 	= document.getElementById("ptosMaximos").value;
		
		if(idPerfilN == -1) {
			mensaje("Seleccione un perfil a actualizar.");
			return false;
		} 
		
		if(idPerfilN == "") {
			mensaje("Seleccione la cantidad de puntos que puede asignar.");
			return false;
		}
	
		document.getElementById("accion").value = "ACTUALIZAR";
		document.frmConsultaPerfiles.action = "./consultaPerfiles.do";
		document.frmConsultaPerfiles.submit();
		
	}
	
	function eliminarPerfil() {
		var idPerfilN 		= document.getElementById("idPerfilN").value;

		if(idPerfilN == -1) {
			mensaje("Seleccione un perfil a eliminar.");
			return false;
		} 

		document.getElementById("accion").value = "ELIMINAR";
		document.frmConsultaPerfiles.action = "./consultaPerfiles.do";
		document.frmConsultaPerfiles.submit();
	}
	
</script>
</head>
<body onload="lectura();" background="<c:url value="/commons/images/backgroundlight.gif"/>">
<form name="frmConsultaPerfiles" id="frmConsultaPerfiles" action="" method="post">
<input type="hidden" name="accion" id="accion" value="CONSULTA"/>

<table width="98%">
	<tr><td	class="titulo">Administración de Perfiles de Asignacion</td></tr>
</table>
<DIV style="width: 98%; FILTER: progid:DXImageTransform.Microsoft.Shadow(color='#aaaacc', Direction=135, Strength=2); 
	border-bottom-width: 2px; border-bottom-style: solid; border-bottom-color: #4d7097; font-size: 1px;">
</DIV>
<table width="90%">
   <tr>
  	<td class="healineblue1" width="40%">
			&nbsp;Perfil de Asignacion&nbsp;</td>
    <td>
		<select name="idPerfilN" id="idPerfilN" class="InputB" style="text-transform: uppercase; width:95%" onChange="selecPuesto();">
			<option value="-1">Seleccione</option>
			<c:forEach var="perfilTO" items="${perfilesLst}" >
				<option value="${perfilTO.idPerfilN}">${perfilTO.idPuesto} - ${perfilTO.descripcion}</option>	
	    	</c:forEach>
     	</select>    

	</td>
  </tr>
  <tr>
  	<td class="healineblue1">&nbsp;Cantidad máxima de puntos a asignar</td>
  	<td><input name="ptosMaximos" id="ptosMaximos" type="text" value="" class="InputB" style="text-transform: uppercase; width:45%" maxlength="15"/></td>
  </tr>
</table>
<table width="98%">
 	<tr align="right">
 		<td>
 		    <input type="hidden" name="accion" id="accion" value="" />
 		    <securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="177">
 		    	<input type="button" name="Consulta" id="Consulta" value="CONSULTAR" class="botonInactivo" onClick="consultaPerfil();"/>
 		    </securityCa:validaPerfil>
 		    <securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="178">
		    	<input type="button" name="Agregar" id="Agregar" value="AGREGAR" class="botonInactivo" onClick="agregarPerfil();"/>
		    </securityCa:validaPerfil>
	        <securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="179">
	        	<input disabled type="button" name="Actualizar" id="Actualizar" value="ACTUALIZAR" class="botonInactivo" onClick="actualizarPerfil();"/>
	        </securityCa:validaPerfil>
	        <securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="180">
				<input disabled type="button" name="Eliminar" id="Eliminar" value="ELIMINAR" class="botonInactivo" onClick="eliminarPerfil();" />
			</securityCa:validaPerfil>
		</td>
	</tr>
</table>
<DIV style="width: 98%; FILTER: progid:DXImageTransform.Microsoft.Shadow(color='#aaaacc', Direction=135, Strength=2); 
	border-bottom-width: 2px; border-bottom-style: solid; border-bottom-color: #4d7097; font-size: 1px;">
</DIV>
<table>
	<tr><td class="titulo">Datos del Perfil de Asignación</td></tr>
</table>
	<table id="tablaLista" border="1" cellspacing="0" cellpadding="0" width="600px">
		<tr class="healineblue1" bgcolor="#ECF0DB" align="center">
			<td width="60px">&nbsp;</td>
			<td width="120px">Puesto</td>
			<td width="300px">Descripcion</td>
			<td width="120px">Puntos a Asignar</td>
		</tr>
	</table>
<script type="text/javascript" language="javascript">
function lectura(){
	<c:if test="${mensaje != null}">
		mensaje("${mensaje}");
	</c:if>
}
</script>
</form>
</body>
</html>