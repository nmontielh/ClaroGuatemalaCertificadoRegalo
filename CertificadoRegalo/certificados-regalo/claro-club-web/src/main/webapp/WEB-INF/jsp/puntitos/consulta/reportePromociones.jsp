<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
<c:choose>
	<c:when test="${promociones!=null && fn:length(promociones)>0 }">
		<% response.setContentType("application/vnd.ms-excel"); %>
		<meta http-equiv="Content-Type" content="application/vnd.ms-excel; charset=ISO-8859-1">
	</c:when>
	<c:otherwise>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	</c:otherwise>
 </c:choose>
	 	
<head>
	<title>Reporte Promociones Circulo Azul</title>
</head>

<body style="background: transparent;"> 
 	<DIV id="divMovimiento" style="background-color:transparent; MARGIN-TOP: 0px; OVERFLOW-Y: scroll; OVERFLOW-X: hidden; BORDER:solid 1px silver; width:98%; HEIGHT: 90%;position: absolute;top: 10px;">
	 	
	<!-- Reporte Promociones -->
	<c:choose>
		<c:when test="${fn:length(promociones)>0 }">
			<table border="1" class="general">
				<tr>
					<td>IDPRODUCTO</td>
					<td>REGION</td>
					<td>GRUPO PROMOCION</td>
					<td>DESCRIPCION</td>
					<td>TIPO PRODUCTO</td>
					<td>PRECIO LISTA</td>
					<td>PRECIO ACTIVACION</td>
					<td>MARCA</td>
					<td>MODELO</td>
					<td>URL</td>
					<td>TECNOLOGIA</td>
					<td>ESTATUS</td>
					<td>BANSISACT</td>
					<td>ADDENDUM</td>
					<td>FUERZA VENTAS</td>
					<td>VALOR PUNTOS</td>
					<td>INDICADOR</td>
					<td>AREA PROMOCION</td>
				</tr>
				<c:forEach items="${promociones}" var="promocionTO">
					<tr>
						<td>${promocionTO.idProducto}</td>
						<td>${promocionTO.idRegion}</td>
						<td>${promocionTO.gpoPromocion}</td>
						<td>${promocionTO.descripcion}</td>
						<td>${promocionTO.tipoProducto}</td>
						<td>${promocionTO.precioLista}</td>
						<td>${promocionTO.precioActiva}</td>
						<td>${promocionTO.marca}</td>
						<td>${promocionTO.modelo}</td>
						<td>${promocionTO.URL}</td>
						<td>${promocionTO.tecnologia}</td>
						<td>${promocionTO.estatus}</td>
						<td>${promocionTO.banSISACT}</td>
						<td>${promocionTO.addendum}</td>
						<td>${promocionTO.fzaVta}</td>
						<td>${promocionTO.valorPtos}</td>
						<td>${promocionTO.indicador}</td>
						<td>${promocionTO.areaPromocion.descAreaPromocion}</td>
					</tr>	 		
				</c:forEach>
			</table>
		</c:when>
		<c:otherwise>
			<center>	
				<div align="left" style="position: absolute; top: 20%; left: 30px; background: transparent; " AllowTransparency >		
					<table border="0" width="100%" align="center" class="BloqueErrorEspera" style="background: transparent;">
						<tr align="center">	<td valign="middle" width="100%">No se encontro información</td></tr>								
					</table>	
				</div>	
			</center>
		</c:otherwise>
	</c:choose>	 		
    </DIV>
</body>
</html>


