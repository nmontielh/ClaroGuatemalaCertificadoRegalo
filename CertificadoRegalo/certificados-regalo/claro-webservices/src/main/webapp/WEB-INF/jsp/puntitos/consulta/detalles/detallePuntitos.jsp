<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/WEB-INF/permisos.tld" prefix="securityCa" %>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Detalle puntitos</title>
<link rel="stylesheet" type="text/css" href='<c:url value="/commons/css/impromptus.css"/>' media='screen' >
        
<script type="text/javascript" src='<c:url value="/commons/js/jquery-1.2.3.pack.js"/>'></script>
<script type='text/javascript' src='<c:url value="/commons/js/jquery.form.js"/>' ></script>
<script type="text/javascript" src='<c:url value="/commons/js/jquery-impromptu.1.3.js"/>' ></script>
<script type="text/javascript" src='<c:url value="/commons/js/puntitos.js"/>' ></script>
</head>
 <body>
<form action="./cancelaPuntitos.do" method="post" id="frmReserva" name="frmReserva" AUTOCOMPLETE="OFF">
<DIV style="width: 100%; FILTER: progid:DXImageTransform.Microsoft.Shadow(color='#aaaacc', Direction=135, Strength=2); 
	border-bottom-width: 2px; border-bottom-style: solid; border-bottom-color: #4d7097; font-size: 1px;">
</DIV>
<table width="100%" class="healineblue1" align="left" border="0" cellspacing="0" cellpadding="0">
   <tr> 
   	<td colspan="6" class="titulo">Datos de la Línea</td>
   </tr>
   <tr style="background-color:transparent;">
   	 <td>Telefono</td>
   	 <td class="txtSumNormal">${telefono.telefono}
   	 	<input type="hidden" name="telefono" id="telefono" value="${telefono.telefono}" />
   	 </td>
   	 <td>Cuenta</td>
   	 <td class="txtSumNormal">${telefono.cuenta}
   	 	<input type="hidden" name="cuenta" id="cuenta" value="${telefono.cuenta}" />
   	 </td>
   	 <td width="15%"></td>
   	 <td width="15%"></td>
   </tr>
   <tr style="background-color:transparent;">
     <td>Regi&oacute;n</td>
     <td class="txtSumNormal">${telefono.region}
     	<input type="hidden" name="region" id="region" value="${telefono.region}" />
     </td>
     <td>Estatus Puntos</td>
     <td class="txtSumNormal">${telefono.estatusPuntos}
     	<input type="hidden" name="estatusPuntos" id="estatusPuntos" value="${telefono.estatusPuntos}" />
     </td>
     <td></td>
   	 <td></td>
   </tr>
   <%--
	Seguridad para Cancelacion de Redencion JAPA 13/02/2011
   <tr>
     <td colspan="4" align="right">
        <c:if test="${fn:containsIgnoreCase('R', telefono.folio)}">
        	<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="39">
				<input type="submit" class="botonInactivo" value="Cancelar" class="botonCancelar">
			</securityCa:validaPerfil>
		</c:if>
		<input type="hidden" id="folio" name="folio" value="${telefonoTO.folio}">
     </td>
     <td></td>
     <td></td>
   </tr>
   --%>
   <input type="hidden" id="folio" name="folio" value="${telefonoTO.folio}">
</table><br /><br /><br /><br />
<DIV style="width: 100%; FILTER: progid:DXImageTransform.Microsoft.Shadow(color='#aaaacc', Direction=135, Strength=2); 
	border-bottom-width: 2px; border-bottom-style: solid; border-bottom-color: #4d7097; font-size: 1px;">
</DIV>
<input type="hidden" id="confirma" name="confirma" value="" />
<table id="tablaListado" width="98%" border="1" cellspacing="0" cellpadding="0" style="visibility:visible; display:block">
	<thead>
	<tr class="titulo"><td colspan="9">Datos de Reservaciones</td></tr>
	<tr class="healineblue1" bgcolor="#ECF0DB" align="center">
		<td>&nbsp;Folio&nbsp;</td>
		<td>&nbsp;Estatus Reservaci&oacute;n&nbsp;</td>
		<td>&nbsp;Secuencia&nbsp;</td>
		<td>&nbsp;Plan&nbsp;</td>
		<td>&nbsp;Descripci&oacute;n&nbsp;</td>
		<td>&nbsp;Marca&nbsp;</td>
		<td>&nbsp;Modelo&nbsp;</td>
		<td>&nbsp;Fecha Expiraci&oacute;n&nbsp;</td>
		<td>&nbsp;Redenci&oacute;n&nbsp;</td>
	</tr>
	</thead>
	<tbody style="visibility:visible; display:block">
<c:forEach  var="telefonoTO" items="${lineas}">
	<tr id="linea" class="InputC" bgcolor="#ECF0DB" align="center">
    	<td><c:set var="accion" value="${telefonoTO.puntosTO.estatusPuntos}" />
    	<c:choose>
			<c:when test="${fn:containsIgnoreCase('A', accion) or fn:containsIgnoreCase('P', accion)}">&nbsp;${telefonoTO.folio}&nbsp;
				<%-- INICIO: Seguridad para Cancelacion de Redencion JAPA 13/02/2011 Folio: 103010 --%>
				<%-- Perfil que permite cancelar las reservaciones provenientes de ECAC y CAC's --%>
				<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="39">
					<c:if test="${telefonoTO.fzaVta == 'E-CAC' || telefonoTO.fzaVta == 'TELCEL'}">
        				<br/><input type="button" id="Reservacion" value="Cancelar" class="botonCancelar" onClick="folioValidar('${telefonoTO.folio}')">
					</c:if>
				</securityCa:validaPerfil>
				<%-- Perfil que permite cancelar las reservaciones provenientes de Distribuidores --%>
				<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="165">
					<c:if test="${telefonoTO.fzaVta != 'E-CAC' && telefonoTO.fzaVta != 'TELCEL'}">
        				<br/><input type="button" id="Reservacion" value="Cancelar" class="botonCancelar" onClick="folioValidar('${telefonoTO.folio}')">
					</c:if>
				</securityCa:validaPerfil>
				<%-- FIN: Seguridad para Cancelacion de Redencion JAPA 13/02/2011 --%>
				
			</c:when>
		<c:otherwise>
			&nbsp;${telefonoTO.folio}&nbsp;
		</c:otherwise>
		</c:choose></td>
		<td>&nbsp;${telefonoTO.puntosTO.estatusPuntos}&nbsp;</td> 
		<td>&nbsp;${telefonoTO.secuencia}&nbsp;</td>
		<td>&nbsp;${telefonoTO.plan}&nbsp;</td>
		<td>&nbsp;${telefonoTO.descripcionPlan}&nbsp;</td>
		<td>&nbsp;${telefonoTO.marca}&nbsp;</td>
		<td>&nbsp;${telefonoTO.modelo}&nbsp;</td>
		<td>&nbsp;<fmt:formatDate value="${telefonoTO.fechaExpira}" pattern="dd/MM/yyyy" />&nbsp;</td>
		<td>&nbsp;${telefonoTO.tipoRedencion}&nbsp;</td>
	</tr>
</c:forEach>
</tbody>
</table>
</form>
</body>
</html>