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
<title>Administración de Puntos de Venta</title>
<link rel="stylesheet" type="text/css" href='<c:url value="/commons/css/impromptus.css"/>' media='screen' >
<script type="text/javascript" src='<c:url value="/commons/js/jquery-1.2.3.pack.js"/>'></script>
<script type='text/javascript' src='<c:url value="/commons/js/jquery.form.js"/>' ></script>
<script type="text/javascript" src='<c:url value="/commons/js/jquery-impromptu.1.3.js"/>' ></script>
<script type="text/javascript" src='<c:url value="/commons/js/puntitos.js"/>' ></script>
</head>
<body onLoad="lectura()" background="<c:url value="/commons/images/backgroundlight.gif"/>">
<form name="frmCapPtosVta" id="frmCapPtosVta" action="./PtoVtaController.do" onSubmit="return PtosVta()" method="post" AUTOCOMPLETE="OFF">
<table width="98%">
	<tr><td	class="titulo">Administración de Puntos de Venta</td></tr>
</table>
<DIV style="width: 98%; FILTER: progid:DXImageTransform.Microsoft.Shadow(color='#aaaacc', Direction=135, Strength=2); 
	border-bottom-width: 2px; border-bottom-style: solid; border-bottom-color: #4d7097; font-size: 1px;">
</DIV>
<table width="80%">
  <tr><td class="titulo" colspan="6">Datos Generales</td></tr>
  <tr>
  	<td class="healineblue1" width="25%">
			&nbsp;Punto de Venta&nbsp;</td>
    <td width="20%">
    <input name="idPuntoVta" id="idPuntoVta" type="text" value="${puntoVentaTO.idPuntoVta}" class="InputB" style="text-transform: uppercase; width:95%" maxlength="35"/></td>
    <td class="healineblue1" width="25%">
			&nbsp;Segmento IP&nbsp;</td>
    <td width="20%"><input name="segmentoIP" id="segmentoIP" type="text" value="${puntoVentaTO.segmentoIP}" class="InputB" style="text-transform: uppercase; width:95%" maxlength="11"/></td>
  </tr>
  <tr>
    <td class="healineblue1">&nbsp;Rango Inferior</td>
    <td><input name="rangoInf" id="rangoInf" type="text" class="InputB" style="text-transform: uppercase; width:50%" maxlength="3" /></td>
    <td class="healineblue1"> Rango Superior</td>
    <td colspan="5"><input name="rangoSup" id="rangoSup" type="text" class="InputB" style="text-transform: uppercase; width:50%" maxlength="3" /></td>
  </tr>
  <tr>
  	<td class="healineblue1">&nbsp;Regi&oacute;n</td>
  	<td><select name="idRegion" id="idRegion" class="InputB" style="text-transform: uppercase; width:95%" >
      <option value="0" selected="selected">Selecciona</option>
      <c:forEach var="count" begin="1" end="9" step="1">
      	<option value="${count}">REGIÓN 0${count}</option>	
      </c:forEach>
     </select></td>
  	<td class="healineblue1">Impuesto al Valor Agregado </td>
  	<td><select name="ivaProcentaje" id="ivaProcentaje" class="InputB" style="text-transform: uppercase; width:99%" >
      <option value="0" selected="selected">Selecciona</option>
      <option value="16">Zona No Fronteriza 16 %&nbsp;&nbsp;&nbsp;</option>
      <option value="11">Zona Fronteriza 11 %&nbsp;</option>
    </select></td>
  </tr>
</table>
<DIV style="width: 98%; FILTER: progid:DXImageTransform.Microsoft.Shadow(color='#aaaacc', Direction=135, Strength=2); 
	border-bottom-width: 2px; border-bottom-style: solid; border-bottom-color: #4d7097; font-size: 1px;">
</DIV>
<table width="98%">
 	<tr align="right">
 		<td>
 		    <input type="hidden" name="accion" id="accion" value="" />
 		    <securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="49">
 		    	<input type="button" name="Consulta" id="Consulta" value="CONSULTAR" class="botonInactivo" onClick="pantallas()"/>
 		    </securityCa:validaPerfil>
 		    <securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="50">
		    	<input type="submit" name="Agregar" id="Agregar" value="AGREGAR" class="botonInactivo" onClick="document.getElementById('accion').value='Agregar'"/>
		    </securityCa:validaPerfil>
	        <securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="51">
	        	<input type="submit" name="Actualizar" id="Actualizar" value="ACTUALIZAR" disabled="disabled" class="botonInactivo" onClick="document.getElementById('accion').value='Actualizar'"/>
	        </securityCa:validaPerfil>
	        <securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="52">
				<input type="submit" name="Eliminar" id="Eliminar" value="ELIMINAR" disabled="disabled" class="botonInactivo" onClick="document.getElementById('accion').value='Eliminar'" />
			</securityCa:validaPerfil>
		</td>
	</tr>
</table>
<DIV style="width: 98%; FILTER: progid:DXImageTransform.Microsoft.Shadow(color='#aaaacc', Direction=135, Strength=2); 
	border-bottom-width: 2px; border-bottom-style: solid; border-bottom-color: #4d7097; font-size: 1px;">
</DIV>
<table>
	<tr><td class="titulo">Datos de los Puntos de Venta</td></tr>
</table>
	<table id="tablaLista" border="1" cellspacing="0" cellpadding="0" width="600px">
		<tr class="healineblue1" bgcolor="#ECF0DB" align="center">
			<td width="200px">&nbsp;Punto de Venta&nbsp;</td>
			<td width="120px">&nbsp;Segmento IP&nbsp;</td>
			<td width="60px">&nbsp;Inferior</td>
			<td width="60px">&nbsp;Superior</td>
			<td width="30px">&nbsp;Regi&oacute;n</td>
			<td width="30px">&nbsp;IVA&nbsp;</td>
		</tr>
	</table>
<script type="text/javascript" language="javascript">
function lectura(){
	<c:if test="${puntoVentaTO.idRegion!=null && puntoVentaTO.ivaProcentaje!=null}">
		document.getElementById("idRegion").value='${puntoVentaTO.idRegion}';
		document.getElementById("ivaProcentaje").value='${puntoVentaTO.ivaProcentaje}';
	</c:if>
	<c:if test="${fn:containsIgnoreCase('false',accion)}">
		mensaje("Ya existe este Punto de Venta");
	</c:if>
}
</script>
</form>
<center><h3><font color="white" class="InputB"></font>${error}</h3></center>
<spring:hasBindErrors name="puntoVentaTO">
      <div align="center"  class="BloqueErrorEspera">
            Hay ${errors.errorCount} error(es):
            <ul>
                  <c:forEach var="errMsgObj" items="${errors.allErrors}" >
                        <li><spring:message code="${errMsgObj.code}"
                             text="${errMsgObj.defaultMessage}" /></li>
                  </c:forEach>
            </ul>
      </div>
</spring:hasBindErrors>
<c:if test="${errors.errorCount>0}">
<c:forEach var="error" items="${errors.allErrors}" >
                        <li><spring:message code="${errMsgObj.code}"
                             text="${errMsgObj.defaultMessage}" /></li>
</c:forEach>
</c:if>
</body>
</html>