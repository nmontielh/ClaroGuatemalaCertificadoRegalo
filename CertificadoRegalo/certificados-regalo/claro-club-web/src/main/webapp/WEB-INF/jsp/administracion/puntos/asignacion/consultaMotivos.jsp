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
<title>Administración de Motivos de Asignacion</title>
<link rel="stylesheet" type="text/css" href='<c:url value="/commons/css/impromptus.css"/>' media='screen' >
<script type="text/javascript" src='<c:url value="/commons/js/jquery-1.2.3.pack.js"/>'></script>
<script type='text/javascript' src='<c:url value="/commons/js/jquery.form.js"/>' ></script>
<script type="text/javascript" src='<c:url value="/commons/js/jquery-impromptu.1.3.js"/>' ></script>
<!-- <script type="text/javascript" src='<c:url value="/commons/js/puntitos.js"/>' ></script> -->

<script type="text/javascript">
function mensaje(informacion){        		      
	$.prompt(informacion, {prefix:'cleanblue', buttons:{Aceptar:true}});
	return false;
}
	
function selecMotivo() {
	var idMotivo = document.getElementById("idMotivo").value;
	document.getElementById("idMotivo").value = idMotivo;
}

function consultaMotivo(){
		
	var idMotivo 	= document.getElementById("idMotivo").value;
	var descripcion = document.getElementById("descripcion").value;
	var estatus 	= document.getElementById("estatus").value;
	
	parent["detalleMotivosAsig"].document.forms[0].idMotivo.value 	= idMotivo;
	parent["detalleMotivosAsig"].document.forms[0].descripcion.value = descripcion;
	parent["detalleMotivosAsig"].document.forms[0].estatus.value 	= estatus;
	parent["detalleMotivosAsig"].document.forms[0].action 			= "./detalleMotivos.do";
	parent["detalleMotivosAsig"].document.forms[0].submit();
}

function agregarMotivo() {
	var idMotivo 	= document.getElementById("idMotivo").value;
	var descripcion = document.getElementById("descripcion").value;
	var estatus 	= document.getElementById("estatus").value;	
	
	if(idMotivo == "") {
		mensaje("Seleccione un Id del motivo");
		return false;
	}
	
	if(descripcion == "") {
		mensaje("Seleccione una descripción del motivo");
		return false;
	}
	
	if(estatus == -1) {
		mensaje("Seleccione un estatus válido del motivo");
		return false;
	}
	
	//parent["detalleMotivosAsig"].document.body.innerHTML 	= "";
	document.getElementById("accion").value 				= "AGREGAR";
	document.frmConsultaMotivos.action 						= "./consultaMotivos.do";
	document.frmConsultaMotivos.submit();
}

function actualizarMotivo() {
	var idMotivo 	= document.getElementById("idMotivo").value;
	var descripcion = document.getElementById("descripcion").value;
	var estatus 	= document.getElementById("estatus").value;	
	
	if(idMotivo == "") {
		mensaje("Seleccione un Id del motivo");
		return false;
	}
	
	if(descripcion == "") {
		mensaje("Seleccione una descripción del motivo");
		return false;
	}
	
	if(estatus == -1) {
		mensaje("Seleccione un estatus válido del motivo");
		return false;
	}

	document.getElementById("accion").value = "ACTUALIZAR";
	document.frmConsultaMotivos.action = "./consultaMotivos.do";
	document.frmConsultaMotivos.submit();
	
}
	
function eliminarMotivo() {
	var idMotivo 	= document.getElementById("idMotivo").value;

	if(idMotivo == "") {
		mensaje("Seleccione un Id del motivo");
		return false;
	}

	document.getElementById("accion").value = "ELIMINAR";
	document.frmConsultaMotivos.action = "./consultaMotivos.do";
	document.frmConsultaMotivos.submit();
}
</script>
</head>
<body onLoad="lectura()" background="<c:url value="/commons/images/backgroundlight.gif"/>">
<form name="frmConsultaMotivos" id="frmConsultaMotivos" action="./menuAsignacionPuntos.do" method="post">
<table width="98%">
	<tr><td	class="titulo">Administración de Motivos de Asignacion</td></tr>
</table>
<DIV style="width: 98%; FILTER: progid:DXImageTransform.Microsoft.Shadow(color='#aaaacc', Direction=135, Strength=2); 
	border-bottom-width: 2px; border-bottom-style: solid; border-bottom-color: #4d7097; font-size: 1px;">
</DIV>
<table width="80%">

  <tr>
  	<td class="healineblue1" width="25%">&nbsp;Id Motivo</td>
  	<td><input name="idMotivo" id="idMotivo" type="text" value="" class="InputB" style="text-transform: uppercase; width:25%" maxlength="5"/></td>
  </tr>
  <tr>
  	<td class="healineblue1">&nbsp;Descripcion</td>
  	<td><input name="descripcion" id="descripcion" type="text" value="" class="InputB" style="text-transform: uppercase; width:95%" maxlength="70"/></td>
  </tr>
  <tr>
  	<td class="healineblue1">&nbsp;Estatus</td>
  	<td><select name="estatus" id="estatus" class="InputB" style="text-transform: uppercase; width:50%" >
  		<option value="-1">Seleccione</option>
      	<option value="0">Inactivo</option>
      	<option value="1">Activo</option>	
     </select></td>
  </tr>
</table>
<table width="98%">
 	<tr align="right">
 		<td>
 		    <input type="hidden" name="accion" id="accion" value="" />
 		    <securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="182">
 		    	<input type="button" name="Consulta" id="Consulta" value="CONSULTAR" class="botonInactivo" onClick="consultaMotivo()"/>
 		    </securityCa:validaPerfil>
 		    <securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="183">
		    	<input type="button" name="Agregar" id="Agregar" value="AGREGAR" class="botonInactivo" onClick="agregarMotivo();"/>
		    </securityCa:validaPerfil>
	        <securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="184">
	        	<input type="button" name="Actualizar" id="Actualizar" value="ACTUALIZAR" disabled="disabled" class="botonInactivo" onClick="actualizarMotivo();"/>
	        </securityCa:validaPerfil>
	        <securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="185">
				<input type="button" name="Eliminar" id="Eliminar" value="ELIMINAR" disabled="disabled" class="botonInactivo" onClick="eliminarMotivo();" />
			</securityCa:validaPerfil>
		</td>
	</tr>
</table>
<DIV style="width: 98%; FILTER: progid:DXImageTransform.Microsoft.Shadow(color='#aaaacc', Direction=135, Strength=2); 
	border-bottom-width: 2px; border-bottom-style: solid; border-bottom-color: #4d7097; font-size: 1px;">
</DIV>
<table>
	<tr><td class="titulo">Datos del Motivo de Asignación</td></tr>
</table>
	<table id="tablaLista" border="1" cellspacing="0" cellpadding="0" width="600px">
		<tr class="healineblue1" bgcolor="#ECF0DB" align="center">
			<td width="60px">&nbsp;&nbsp;</td>
			<td width="120px">&nbsp;Id Motivo</td>
			<td width="300px">&nbsp;Descripción&nbsp;</td>
			<td width="120px">&nbsp;Estatus</td>
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