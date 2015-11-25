<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/permisos.tld" prefix="securityCa" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href='<c:url value="/commons/js/sc_textsheet.css"/>' type="text/css">
<title>alianzasCancela</title>
<script >
	function buscaCancela(){
		document.getElementById("divAlianzas").style.visibility="visible";
		document.getElementById("divAlianzas").style.display="block";		
		document.getElementById("iResultado").style.visibility="visible";
		document.getElementById("iResultado").style.display="block";
		frmCancelacion.action="./alianzasBuscaCancela.do";		
		frmCancelacion.submit();
	}
	function ocultaDiv(){
		document.getElementById("divAlianzas").style.visibility="hidden";
		document.getElementById("divAlianzas").style.display="none";
		document.getElementById("iResultado").src="./commons/blanck.html";
	}

</script>
</head>
<body>

<script >
	document.parentWindow.parent.activaCargando("hidden","none");
</script>


<form id="frmCancelacion" name="frmCancelacion" method="post" target="iResultado">
<DIV id="opciones" style="position: static;top: 20px;height: 30px;MARGIN-TOP: 0px; OVERFLOW-Y: hidden; OVERFLOW-X: hidden;">	
	<input type="hidden" name="telefono" value="${telefono}">
	<input type="hidden" name="cuenta" value="${cuenta}">
	<input type="hidden" name="region" value="${region}">
	<input type="hidden" name="secuencia" value="${secuencia}">
	<table>
		<tr class="textRadio">
			<!-- 
			<td>
				<input type="radio" name="opcion" value="1">
			</td>			
			<td>Mexicana</td>			
			 -->
			<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="92">
				<td><input type="radio" name="opcion" value="2"></td>
				<td>American Express</td>
			</securityCa:validaPerfil>
			<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="113">
				<td><input type="button" name="buscar" value="Buscar" onclick="buscaCancela()" class="submit"> </td>
			</securityCa:validaPerfil>		
		</tr>
	</table>	
</DIV>	
</form>

<DIV id="divAlianzas" style="visibility: hidden;display: none;background-color: transparent; position: absolute;top: 30px;left: 3px;height: 350px;width: 960px;">
<IFRAME  name="iResultado" id="iResultado" style="border: hidden; visibility: hidden; display: none; top: 50px" frameborder="0" width="960px" height="320px" align="left" AllowTransparency></IFRAME>
</DIV>

</body>
</html>