<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
<c:if test="${idReporte=='1' }">
	<c:choose>
		<c:when test="${redenciones!=null && fn:length(redenciones)>0 }">
			<% response.setContentType("application/vnd.ms-excel"); %>
			<meta http-equiv="Content-Type" content="application/vnd.ms-excel; charset=ISO-8859-1">
	 	</c:when>
	 	<c:otherwise>
	 		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	 	</c:otherwise>
	 </c:choose>
</c:if>
<c:if test="${idReporte=='2' }">
	<c:choose>
		<c:when test="${acumulados!=null && fn:length(acumulados)>0 }">
			<% response.setContentType("application/vnd.ms-excel"); %>
			<meta http-equiv="Content-Type" content="application/vnd.ms-excel; charset=ISO-8859-1">
	 	</c:when>
	 	<c:otherwise>
	 		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	 	</c:otherwise>
	 </c:choose>
</c:if>
<c:if test="${idReporte=='3' }">
	<c:choose>
		<c:when test="${certificados!=null && fn:length(certificados)>0 }">
			<% response.setContentType("application/vnd.ms-excel"); %>
			<meta http-equiv="Content-Type" content="application/vnd.ms-excel; charset=ISO-8859-1">
	 	</c:when>
	 	<c:otherwise>
	 		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	 	</c:otherwise>
	 </c:choose>
</c:if>
<!-- 

<c:if test="${idReporte=='4' }">
	<c:choose>
		<c:when test="${retenciones!=null && fn:length(retenciones)>0 }">
			<% response.setContentType("application/vnd.ms-excel"); %>
			<meta http-equiv="Content-Type" content="application/vnd.ms-excel; charset=ISO-8859-1">
	 	</c:when>
	 	<c:otherwise>
	 		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	 	</c:otherwise>
	 </c:choose>
</c:if>


 -->
<c:if test="${idReporte=='5' }">
	<c:choose>
		<c:when test="${roext!=null && fn:length(roext)>0 }">
			<% response.setContentType("application/vnd.ms-excel"); %>
			<meta http-equiv="Content-Type" content="application/vnd.ms-excel; charset=ISO-8859-1">
	 	</c:when>
	 	<c:otherwise>
	 		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	 	</c:otherwise>
	 </c:choose>
</c:if>
<c:if test="${idReporte=='6' }">
	<c:choose>
		<c:when test="${redencionesLineas!=null && fn:length(redencionesLineas)>0 }">
			<% response.setContentType("application/vnd.ms-excel"); %>
			<meta http-equiv="Content-Type" content="application/vnd.ms-excel; charset=ISO-8859-1">
	 	</c:when>
	 	<c:otherwise>
	 		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	 	</c:otherwise>
	 </c:choose>
</c:if>

<c:if test="${idReporte=='7' }">
	<c:choose>
		<c:when test="${(alianzasMexicana!=null && fn:length(alianzasMexicana)>0) ||
				 (alianzasAmex!=null && fn:length(alianzasAmex)>0) ||
				 (redencionesEntrega!=null && fn:length(redencionesEntrega)>0) ||
				 (redencionesAmigoKit!=null && fn:length(redencionesAmigoKit)>0) ||
				 (redencionesTiempoAire!=null && fn:length(redencionesTiempoAire)>0)||
				 (redencionesActivaciones!=null && fn:length(redencionesActivaciones)>0)||
				 (redencionesTarjetas3G!=null && fn:length(redencionesTarjetas3G)>0)}">
			<% response.setContentType("application/vnd.ms-excel"); %>
			<meta http-equiv="Content-Type" content="application/vnd.ms-excel; charset=ISO-8859-1">
	 	</c:when>
	 	<c:otherwise>
	 		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	 	</c:otherwise>
	 </c:choose>
</c:if>
<c:if test="${idReporte=='8' }">
	<c:choose>
		<c:when test="${altoValor!=null && fn:length(altoValor)>0 }">
			<% response.setContentType("application/vnd.ms-excel"); %>
			<meta http-equiv="Content-Type" content="application/vnd.ms-excel; charset=ISO-8859-1">
	 	</c:when>
	 	<c:otherwise>
	 		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	 	</c:otherwise>
	 </c:choose>
</c:if>
<c:if test="${idReporte=='9' }">
	<c:choose>
		<c:when test="${comisiones!=null && fn:length(comisiones)>0 }">
			<% response.setContentType("application/vnd.ms-excel"); %>
			<meta http-equiv="Content-Type" content="application/vnd.ms-excel; charset=ISO-8859-1">
	 	</c:when>
	 	<c:otherwise>
	 		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	 	</c:otherwise>
	 </c:choose>
</c:if>
<c:if test="${idReporte=='10' }">
	<c:choose>
		<c:when test="${puntosPorVencer!=null && fn:length(puntosPorVencer)>0 }">
			<% response.setContentType("application/vnd.ms-excel"); %>
			<meta http-equiv="Content-Type" content="application/vnd.ms-excel; charset=ISO-8859-1">
	 	</c:when>
	 	<c:otherwise>
	 		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	 	</c:otherwise>
	 </c:choose>
</c:if>
<c:if test="${idReporte=='11' }">
	<c:choose>
		<c:when test="${redencionesDetalle!=null && fn:length(redencionesDetalle)>0 }">
			<% response.setContentType("application/vnd.ms-excel"); %>
			<meta http-equiv="Content-Type" content="application/vnd.ms-excel; charset=ISO-8859-1">
	 	</c:when>
	 	<c:otherwise>
	 		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	 	</c:otherwise>
	 </c:choose>
</c:if>
<c:if test="${idReporte=='12' }">
	<c:choose>
		<c:when test="${redencionesOnline!=null && fn:length(redencionesOnline)>0 }">
			<% response.setContentType("application/vnd.ms-excel"); %>
			<meta http-equiv="Content-Type" content="application/vnd.ms-excel; charset=ISO-8859-1">
	 	</c:when>
	 	<c:otherwise>
	 		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	 	</c:otherwise>
	 </c:choose>
</c:if>	
<c:if test="${idReporte=='13' }">
	<c:choose>
		<c:when test="${productosInbursa!=null && fn:length(productosInbursa)>0 }">
			<% response.setContentType("application/vnd.ms-excel"); %>
			<meta http-equiv="Content-Type" content="application/vnd.ms-excel; charset=ISO-8859-1">
	 	</c:when>
	 	<c:otherwise>
	 		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	 	</c:otherwise>
	 </c:choose>
</c:if>
<c:if test="${idReporte=='14' }">
	<c:choose>
		<c:when test="${productosInbursa!=null && fn:length(productosInbursa)>0 }">
			<% response.setContentType("application/vnd.ms-excel"); %>
			<meta http-equiv="Content-Type" content="application/vnd.ms-excel; charset=ISO-8859-1">
	 	</c:when>
	 	<c:otherwise>
	 		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	 	</c:otherwise>
	 </c:choose>
</c:if>
<c:if test="${idReporte=='15' }">
	<c:choose>
		<c:when test="${descuentoInbursa!=null && fn:length(descuentoInbursa)>0 }">
			<% response.setContentType("application/vnd.ms-excel"); %>
			<meta http-equiv="Content-Type" content="application/vnd.ms-excel; ">
	 	</c:when>
	 	<c:otherwise>
	 		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	 	</c:otherwise>
	 </c:choose>
</c:if>
<c:if test="${idReporte=='16' }">
	<c:choose>
		<c:when test="${paquetesInbursa!=null && fn:length(paquetesInbursa)>0 }">
			<% response.setContentType("application/vnd.ms-excel"); %>
			<meta http-equiv="Content-Type" content="application/vnd.ms-excel; ">
	 	</c:when>
	 	<c:otherwise>
	 		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	 	</c:otherwise>
	 </c:choose>
</c:if> 	
<head>
	<style type="text/css">
		.tablaHead			{  font-family: Verdana, Arial, Helvetica, sans-serif; font-size: 12px; font-style: normal; font-weight: bold; font-variant: normal; color: #FFFFFF; background-color: #147FB5}
		.tablaDetalle		{  font-family: Verdana, Arial, Helvetica, sans-serif; font-size: 12px; font-style: normal; font-variant: normal; }
.tablaDetalleCebra  {  font-family: Verdana, Arial, Helvetica, sans-serif; font-size: 12px; font-style: normal; font-variant: normal; background-color: #C0E5F8}
	</style>
	<title>Reporte Circulo Azul</title>
</head>

<body style="background: transparent;"> 
 	<DIV id="divMovimiento" style="background-color:transparent; MARGIN-TOP: 0px; OVERFLOW-Y: scroll; OVERFLOW-X: hidden; BORDER:solid 1px silver; width:98%; HEIGHT: 90%;position: absolute;top: 10px;">
	 	
	 	<!-- Reporte Redenciones -->
	 	<c:if test="${idReporte=='1' }">	 	
	 		<c:choose>
	 			<c:when test="${redenciones!=null && fn:length(redenciones)>0 }">
	 				<table border="1" class="general">
						<tr>
							<td>MARCA</td>
				 			<td>MODELO</td>				 			
				 			<td>NO MATERIAL</td>
				 			<td>TIPOREDEN</td>
				 			<td>ESTATUS</td>
				 			<td>TOTAL PUNTOS</td>
				 			<td>TOTALPESOS</td>
				 			<td>TOTALEQUIPOS</td>				 			
				 		</tr>
				 		<c:forEach items="${redenciones }" var="redencionTO">
				 			<tr>
								<td>${redencionTO.marca }</td>
					 			<td>${redencionTO.modelo }</td>
					 			<td>${redencionTO.idProducto }</td>
				 				<td>${redencionTO.tipoRedencion }</td>
				 				<td>${redencionTO.estatus }</td>
				 				<td>${redencionTO.totalPuntos }</td>
					 			<td>${redencionTO.totalPesos }</td>
					 			<td>${redencionTO.totalEquipos }</td>				 			
					 			
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
	 	</c:if>
	 	<!-- Reporte Acumulados -->
	 	<c:if test="${idReporte=='2' }">	 	
	 		<c:choose>
	 			<c:when test="${acumulados!=null && fn:length(acumulados)>0 }">
	 				<table border="1" class="general">
						<tr>
							<td>REGION</td>
							<td>CICLO</td>
							<td>PORVENCER</td>
							<td>PORVENCER1</td>
							<td>PORVENCER2</td>
							<td>RENTA</td>
							<td>EXCEDENTES</td>
							<td>ANTIGUEDAD</td>			 			
				 		</tr>
				 		<c:forEach items="${acumulados }" var="acumuladoTO">
				 			<tr>
								<td>${acumuladoTO.region}</td>
								<td>${acumuladoTO.ciclo}</td>
								<td>${acumuladoTO.porVencer}</td>
								<td>${acumuladoTO.porVencer1}</td>
								<td>${acumuladoTO.porVencer2}</td>
								<td>${acumuladoTO.renta}</td>
								<td>${acumuladoTO.excedentes}</td>
								<td>${acumuladoTO.antiguedad}</td>					 			
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
	 	</c:if>
	 	<!-- Reporte Certificados de Lealtad -->
	 	<c:if test="${idReporte=='3' }">	 	
	 		<c:choose>
	 			<c:when test="${certificados!=null && fn:length(certificados)>0 }">
	 				<table border="1" class="general">
						<tr>
							<td>FOLIO</td>
				 			<td>CUENTA</td>
				 			<td>LINEA</td>
				 			<td>IDUSUARIO</td>
				 			<td>FECHAOPER</td>
				 			<td>FECHAVENCIMIENTO</td>
				 			<td>IDREGION</td>
				 			<td>IDPUNTOVTA</td>
				 			<td>SEGMENTO</td>
				 			<td>IDPLAN</td>
				 			<td>MESESANTIG</td>
				 			<td>PORCANTIG</td>
				 			<td>VCERTIF</td>
				 			<td>VCERTIFEXTRA</td>
				 			<td>TOTAL</td>
				 			<td>MOTIVO</td>
				 			<td>ESTATUS</td>
				 		</tr>
				 		<c:forEach items="${certificados }" var="certificadoTO">
				 			<tr>
				 				<td>${certificadoTO.folio}</td>
					 			<td><c:out value="${certificadoTO.cuenta }"></c:out></td>		 			
					 			<td>${certificadoTO.linea}</td>
					 			<td>${certificadoTO.idUsuario}</td>
					 			<td>${certificadoTO.fechaOperacion}</td>
					 			<td>${certificadoTO.fechaVencimiento}</td>
					 			<td>${certificadoTO.idRegion}</td>
					 			<td>${certificadoTO.idPuntoVta}</td>
					 			<td>${certificadoTO.segmento}</td>
					 			<td>${certificadoTO.idPlan}</td>
					 			<td>${certificadoTO.mesesAntig}</td>
					 			<td>${certificadoTO.porcAntig}</td>
					 			<td>${certificadoTO.certif}</td>
					 			<td>${certificadoTO.certifExtra}</td>
					 			<td>${certificadoTO.certif + certificadoTO.certifExtra}</td>
					 			<td>${certificadoTO.motivo}</td>
					 			<td>${certificadoTO.estatus}</td>
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
	 	</c:if>
	 	
	 	<!-- Reporte Retenciones 
	 	<c:if test="${idReporte=='4' }">	 	
	 		<c:choose>
	 			<c:when test="${retenciones!=null && fn:length(retenciones)>0 }">
	 				<table border="1" class="general">
						<tr>
							<td>Folio</td>
							<td>Valor General</td>
							<td>Valor Extra</td>
							<td>Total</td>
							<td>Fecha Operacion</td>
							<td>Fecha Vencimiento</td>
							<td>Usuario</td>
							<td>Punto de Venta</td>
							<td>Estatus</td>
						</tr>
				 		<c:forEach items="${retenciones }" var="retencionTO">
				 			<tr>
								<td>${retencionTO.folio}</td>
								<td>${retencionTO.valorGeneral}</td>
								<td>${retencionTO.valorExtra}</td>
								<td>${retencionTO.total}</td>
								<td>${retencionTO.fechaOperacion}</td>
								<td>${retencionTO.fechaVencimiento}</td>
								<td>${retencionTO.usuario}</td>
								<td>${retencionTO.puntoVenta}</td>
								<td>${retencionTO.estatus}</td>
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
	 	</c:if>
	 	
	 	-->
	 	
	 	<!-- Reporte ROEXT -->
	 	<c:if test="${idReporte=='5' }">	 	
	 		<c:choose>
	 			<c:when test="${roext!=null && fn:length(roext)>0 }">
	 				<table border="1" class="general">
						<tr>
							<td>FOLIO</td>
							<td>REGION</td>
							<td>DESCUENTO</td>
							<td>PRECIO</td>
							<td>PRECIOIVA</td>
							<td>FECHA OPERACION</td>
							<td>MATERIAL</td>
							<td>MARCA</td>
							<td>MODELO</td>
							<td>PUNTOS</td>
							<td>BONOROEXT</td>
							<td>PUNTO DE VENTA</td>
						</tr>
				 		<c:forEach items="${roext }" var="roextTO">
				 			<tr>
								<td>${roextTO.folio }</td>
								<td>${roextTO.region }</td>
								<td>${roextTO.descuento }</td>
								<td>${roextTO.precio }</td>
								<td>${roextTO.precioIva }</td>
								<td>${roextTO.fechaOperacion }</td>
								<td>${roextTO.material }</td>
								<td>${roextTO.marca }</td>
								<td>${roextTO.modelo }</td>
								<td>${roextTO.puntos }</td>
								<td>${roextTO.bonoRoext }</td>
								<td>${roextTO.puntoVenta }</td>								
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
	 	</c:if>
	 	
	 	<!-- Reporte Redenciones Linea -->
	 	<c:if test="${idReporte=='6' }">	 	
	 		<c:choose>
	 			<c:when test="${redencionesLineas!=null && fn:length(redencionesLineas)>0 }">
	 				<table border="1" class="general">
						<tr>
							<td>LINEA</td>
							<td>CUENTA</td>
							<td>MARCA</td>
							<td>MODELO</td>
							<td>PTOSDISPONIBLES</td>
							<td>DIFPESOS</td>
							<td>ESTATUS</td>
							<td>TIPOREDEN</td>
							<td>FECHAOPERACION</td>
						</tr>
				 		<c:forEach items="${redencionesLineas }" var="redencionLineaTO">
				 			<tr>
				 				<td>${redencionLineaTO.linea}</td>
				 				<td>${redencionLineaTO.cuenta}</td>
				 				<td>${redencionLineaTO.marca}</td>
				 				<td>${redencionLineaTO.modelo}</td>
				 				<td>${redencionLineaTO.puntosDisponibles}</td>
				 				<td>${redencionLineaTO.difPesos}</td>
				 				<td>${redencionLineaTO.estatus}</td>
				 				<td>${redencionLineaTO.tipoReden}</td>
				 				<td>${redencionLineaTO.fechaOperacion}</td>
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
	 	</c:if>
	 	
	 	<!-- Reporte ALIANZAS -->
	 	<c:if test="${idReporte=='7' }">
	 		<c:choose>
	 			<c:when test="${(alianzasMexicana!=null && fn:length(alianzasMexicana)>0) || 
	 						(alianzasAmex!=null && fn:length(alianzasAmex)>0) || 
	 						(redencionesEntrega!=null && fn:length(redencionesEntrega)>0) ||
	 						(redencionesAmigoKit!=null && fn:length(redencionesAmigoKit)>0)||
	 						(redencionesTiempoAire!=null && fn:length(redencionesTiempoAire)>0)||
	 						(redencionesActivaciones!=null && fn:length(redencionesActivaciones)>0)||
	 						(redencionesTarjetas3G!=null && fn:length(redencionesTarjetas3G)>0)}">
	 					 						
	 				<c:if test="${alianzasMexicana!=null && fn:length(alianzasMexicana)>0 }">
	 					<table border="1" class="general">
			 				<tr>
			 					<td colspan="5">1. Mexicana</td>
			 				</tr>
			 				<tr>
			 					<td>Región</td>
			 					<td>Num Canjes</td>
			 					<td>Millas</td>
			 					<td>Total de Puntos</td>
			 					<td>Promedio de Canjes</td>
			 				</tr>
			 				<c:forEach items="${alianzasMexicana }" var="alianzaMexTO">
						 		<tr>
						 			<td>${alianzaMexTO.region }</td>
				 					<td>${alianzaMexTO.numCanjes }</td>
				 					<td>${alianzaMexTO.millas }</td>
				 					<td>${alianzaMexTO.totalPuntos }</td>
				 					<td>${alianzaMexTO.promedio }</td>
						 		</tr>
						 	</c:forEach>
			 			</table>
	 				</c:if>
	 				
	 				<c:if test="${alianzasAmex!=null && fn:length(alianzasAmex)>0 }">
	 				
	 					<table border="0" class="general">
	 						<tr>
	 							<td colspan="4"></td>
	 						</tr>
	 					</table>
	 				
	 					<table border="1" class="general">
	 						<tr>
			 					<td colspan="4">2. American Express</td>
			 				</tr>
	 						<tr>
	 							<td>Región</td>
	 							<td>Num canjes</td>
	 							<td>Total de Puntos</td>
	 							<td>Estatus</td>
	 						</tr>
	 						<c:forEach items="${alianzasAmex }" var="alianzaAmexTO">
						 		<tr>
						 			<td>${alianzaAmexTO.region }</td>
				 					<td>${alianzaAmexTO.numCanjes }</td>
				 					<td>${alianzaAmexTO.totalPuntos }</td>
				 					<td>${alianzaAmexTO.estatus }</td>
						 		</tr>
						 	</c:forEach>
	 					</table>
	 				</c:if>
	 				<c:if test="${redencionesEntrega!=null && fn:length(redencionesEntrega)>0 }">
	 				
	 					<table border="0" class="general">
	 						<tr>
	 							<td colspan="4"></td>
	 						</tr>
	 					</table>
	 				
	 					<table border="1" class="general">
	 						<tr>
	 							<td>Medio Entrega</td>
	 							<td>Total</td>	 							
	 						</tr>
	 						<c:forEach items="${redencionesEntrega }" var="redencionEntregaTO">
						 		<tr>
						 			<td>${redencionEntregaTO.medioEntrega }</td>
				 					<td>${redencionEntregaTO.total }</td>				 									
						 		</tr>
						 	</c:forEach>
	 					</table>
	 				</c:if>
	 				
	 				<c:if test="${redencionesAmigoKit!=null && fn:length(redencionesAmigoKit)>0 }">
	 				
	 					<table border="0" class="general">
	 						<tr>
	 							<td colspan="4"></td>
	 						</tr>
	 					</table>
	 				
	 					<table border="1" class="general">
	 						<tr>
			 					<td colspan="6">3. Amigo Kit</td>
			 				</tr>
	 						<tr>
	 							<td>Región</td>
	 							<td>Num canjes</td>
	 							<td>Total de Puntos</td>
	 							<td>Diferencia en Pesos</td>
	 							<td>Promedio de Puntos</td>
	 							<td>Promedio de Pesos</td>	 							
	 						</tr>
	 						<c:forEach items="${redencionesAmigoKit }" var="redencionAmigoKitTO">
						 		<tr>
						 			<td>${redencionAmigoKitTO.region}</td>
		 							<td>${redencionAmigoKitTO.numCanjes}</td>
		 							<td>${redencionAmigoKitTO.totalPuntos}</td>
		 							<td>${redencionAmigoKitTO.diferenciaPesos}</td>
		 							<td>${redencionAmigoKitTO.promedioPuntos}</td>
		 							<td>${redencionAmigoKitTO.promedioPesos}</td>				
						 		</tr>
						 	</c:forEach>
	 					</table>
	 				</c:if>
	 				<c:if test="${redencionesTiempoAire!=null && fn:length(redencionesTiempoAire)>0 }">
	 				
	 					<table border="0" class="general">
	 						<tr>
	 							<td colspan="4"></td>
	 						</tr>
	 					</table>
	 				
	 					<table border="1" class="general">
	 						<tr>
			 					<td colspan="4">4. Tiempo Aire</td>
			 				</tr>
	 						<tr>
	 							<td>Región</td>
	 							<td>Paquete Tiempo Aire</td>
	 							<td>Total Región</td>
	 							<td>Total Puntos</td>	 								 							
	 						</tr>
	 						<c:forEach items="${redencionesTiempoAire }" var="redencionTiempoAireTO">
						 		<tr>
						 			<td>${redencionTiempoAireTO.region}</td>
	 								<td>${redencionTiempoAireTO.paqueteTiempoAire}</td>
	 								<td>${redencionTiempoAireTO.totalRegion}</td>
	 								<td>${redencionTiempoAireTO.totalPuntos}</td>				
						 		</tr>
						 	</c:forEach>
	 					</table>
	 				</c:if>
	 				<c:if test="${redencionesActivaciones!=null && fn:length(redencionesActivaciones)>0 }">
	 				
	 					<table border="0" class="general">
	 						<tr>
	 							<td colspan="4"></td>
	 						</tr>
	 					</table>
	 				
	 					<table border="1" class="general">
	 						<tr>
			 					<td colspan="4">5. Activaciones</td>
			 				</tr>
	 						<tr>
	 							<td>Región</td>
	 							<td>Tecnología</td>
	 							<td>Total Región</td>
	 							<td>Total Puntos</td>	 							
	 						</tr>
	 						<c:forEach items="${redencionesActivaciones }" var="redencionActivacionesTO">
						 		<tr>
						 			<td>${redencionActivacionesTO.region}</td>
	 								<td>${redencionActivacionesTO.tecnologia}</td>
	 								<td>${redencionActivacionesTO.totalRegion}</td>
	 								<td>${redencionActivacionesTO.totalPuntos}</td>				
						 		</tr>
						 	</c:forEach>
	 					</table>
	 				</c:if>
	 				<c:if test="${redencionesTarjetas3G!=null && fn:length(redencionesTarjetas3G)>0 }">
	 				
	 					<table border="0" class="general">
	 						<tr>
	 							<td colspan="4"></td>
	 						</tr>
	 					</table>
	 				
	 					<table border="1" class="general">
	 						<tr>
			 					<td colspan="7">6. Tarjetas inalámbricas 3G</td>
			 				</tr>
	 						<tr>
	 							<td>Región</td>
	 							<td>IdProducto</td>
	 							<td>Num canjes</td>
	 							<td>Total de Puntos</td>
	 							<td>Diferencia en Pesos</td>
	 							<td>Promedio de Puntos</td>
	 							<td>Promedio de Pesos</td>
	 						</tr>
	 						<c:forEach items="${redencionesTarjetas3G }" var="redencionTarjetasTO">
						 		<tr>
						 			<td>${redencionTarjetasTO.region}</td>
		 							<td>${redencionTarjetasTO.idProducto}</td>
		 							<td>${redencionTarjetasTO.numCanjes}</td>
		 							<td>${redencionTarjetasTO.totalPuntos}</td>
		 							<td>${redencionTarjetasTO.diferenciaPesos}</td>
		 							<td>${redencionTarjetasTO.promedioPuntos}</td>
		 							<td>${redencionTarjetasTO.promedioPesos}</td>				
						 		</tr>
						 	</c:forEach>
	 					</table>
	 				</c:if>
	 				
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
	 	</c:if>
	 	<!-- Reporte AltoValor -->
	 	<c:if test="${idReporte=='8' }">	 	
	 		<c:choose>
	 			<c:when test="${altoValor!=null && fn:length(altoValor)>0 }">
	 				<table border="1" class="general">
						<tr>
							<td>FOLIO</td>
							<td>REGION</td>
							<td>LINEA</td>
							<td>CUENTA</td>
							<td>DESCUENTO</td>
							<td>PRECIO</td>
							<td>PRECIOIVA</td>
							<td>FECHA OPERACION</td>
							<td>MATERIAL</td>
							<td>MARCA</td>
							<td>MODELO</td>
							<td>PUNTOS</td>
							<td>BONOALTOVALOR</td>
							<td>PUNTO DE VENTA</td>
						</tr>
				 		<c:forEach items="${altoValor }" var="altoValorTO">
				 			<tr>
								<td>${altoValorTO.folio}</td>
								<td>${altoValorTO.region}</td>
								<td>${altoValorTO.linea}</td>
								<td>${altoValorTO.cuenta}</td>
								<td>${altoValorTO.descuento}</td>
								<td>${altoValorTO.precio}</td>
								<td>${altoValorTO.precioIva}</td>
								<td>${altoValorTO.fechaOperacion}</td>
								<td>${altoValorTO.material}</td>
								<td>${altoValorTO.marca}</td>
								<td>${altoValorTO.modelo}</td>
								<td>${altoValorTO.puntos}</td>
								<td>${altoValorTO.bonoAltoValor}</td>
								<td>${altoValorTO.puntoVenta}</td>
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
	 	</c:if>	 	
	 	<!-- Reporte Comisiones -->
	 	<c:if test="${idReporte=='9' }">	 	
	 		<c:choose>
	 			<c:when test="${comisiones!=null && fn:length(comisiones)>0 }">
	 				<table border="1" class="general">
						<tr>
							<td>Estatus</td>
							<td>FuerzaVentas</td>
							<td>Linea</td>
							<td>Cuenta</td>
							<td>Plan</td>
							<td>PlanNuevo</td>
							<td>Segmento</td>
							<td>Folio</td>
							<td>FechaAdendum</td>
							<td>Marca</td>
							<td>Modelo</td>
							<td>Puntos</td>
							<td>TipoPromocion</td>
							<td>FechaReservacion</td>
							<td>Precio</td>
							<td>PrecioIVA</td>
						</tr>
				 		<c:forEach items="${comisiones }" var="comisionTO">
				 			<tr>
								<td>${comisionTO.estatus }</td>
								<td>${comisionTO.fuerzaVentas}</td>
								<td>${comisionTO.linea}</td>
								<td>${comisionTO.cuenta}</td>
								<td>${comisionTO.plan}</td>
								<td>${comisionTO.planNuevo}</td>
								<td>${comisionTO.segmento}</td>
								<td>${comisionTO.folio}</td>
								<td>${comisionTO.fechaAdendum}</td>
								<td>${comisionTO.marca}</td>
								<td>${comisionTO.modelo}</td>
								<td>${comisionTO.puntos}</td>
								<td>${comisionTO.tipoPromocion}</td>
								<td>${comisionTO.fechaReservacion}</td>
								<td>${comisionTO.precio}</td>
								<td>${comisionTO.precioIVA}</td>
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
	 	</c:if>
	 	<!-- Reporte Puntos por Vencer -->
	 	<c:if test="${idReporte=='10' }">	 	
	 		<c:choose>
	 			<c:when test="${puntosPorVencer!=null && fn:length(puntosPorVencer)>0 }">
	 				<table border="1" class="general">
						<tr>
							<td>CUENTA</td>
							<td>SECUENCIA</td>
							<td>REGION</td>
							<td>LINEA</td>
							<td>FECHA VENCIMIENTO</td>
							<td>PUNTOS A VENCER</td>
							<td>PUNTOS ACUMULADOS</td>							
						</tr>
				 		<c:forEach items="${puntosPorVencer }" var="puntosPorVencerTO">
				 			<tr>
								<td>${puntosPorVencerTO.cuenta}</td>
								<td>${puntosPorVencerTO.secuencia}</td>
								<td>${puntosPorVencerTO.region}</td>
								<td>${puntosPorVencerTO.linea}</td>
								<td>${puntosPorVencerTO.fechaCad}</td>
								<td>${puntosPorVencerTO.puntosaCad}</td>
								<td>${puntosPorVencerTO.totales}</td>				
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
	 	</c:if>
	 	
	 	<!-- Reporte Redenciones Detalle -->
	 	<c:if test="${idReporte=='11' }">	 	
	 		<c:choose>
	 			<c:when test="${redencionesDetalle!=null && fn:length(redencionesDetalle)>0 }">
	 				<table border="1" class="general">
						<tr>
							<td>LINEA</td>
							<td>CUENTA</td>
							<td>PLAN</td>
							<td>NO MATERIAL</td>
							<td>MARCA</td>
							<td>MODELO</td>
							<td>VALORPTOS</td>
							<td>TIPOREDEN</td>
							<td>IDREGION</td>
							<td>IDPUNTOVTA</td>
							<td>FECHAOPER</td>
							<td>ESTATUS</td>														
						</tr>
				 		<c:forEach items="${redencionesDetalle }" var="redencionDetalleTO">
				 			<tr>
				 				<td>${redencionDetalleTO.linea }</td>
				 				<td>${redencionDetalleTO.cuenta }</td>
				 				<td>${redencionDetalleTO.plan }</td>
				 				<td>${redencionDetalleTO.idProducto }</td>
				 				<td>${redencionDetalleTO.marca }</td>
				 				<td>${redencionDetalleTO.modelo }</td>
				 				<td>${redencionDetalleTO.valorPuntos }</td>
				 				<td>${redencionDetalleTO.tipoRedencion }</td>
				 				<td>${redencionDetalleTO.idRegion }</td>
				 				<td>${redencionDetalleTO.idPuntoVenta }</td>
				 				<td>${redencionDetalleTO.fechaOperacion }</td>
				 				<td>${redencionDetalleTO.estatus }</td>
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
	 	</c:if>
	 
	 <!-- Reporte Redenciones Online -->
	 	<c:if test="${idReporte=='12' }">	 	
	 		<c:choose>
	 			<c:when test="${redencionesOnline!=null && fn:length(redencionesOnline)>0 }">
	 				<table border="1" class="general">
						<tr>
							<td>ORIGEN</td>
				 			<td>REGION</td>
				 			<td>PAQUETE</td>
				 			<td>TOTAL REGION</td>
				 			<td>TOTAL PUNTOS</td>
				 		</tr>
				 		<c:forEach items="${redencionesOnline }" var="redencionesOnlineTO">
				 			<tr>
								<td>${redencionesOnlineTO.origen}</td>
								<td>${redencionesOnlineTO.region}</td>
								<td>${redencionesOnlineTO.paquete}</td>
								<td>${redencionesOnlineTO.totalRegion}</td>
								<td>${redencionesOnlineTO.totalPuntos}</td>
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
	 	</c:if>	
	 	
	 		 		 	<!-- Reporte Inbursa Rentas gratis -->
	 	<c:if test="${idReporte=='13' }">	 	
	 		<c:choose>
	 			<c:when test="${productosInbursa!=null && fn:length(productosInbursa)>0 }">
	 				<table border="1" class="general">
						<tr>
							<td class="tablaHead">&nbsp;FOLIO&nbsp;</td>
							<td class="tablaHead">&nbsp;TELEFONO&nbsp;</td>
							<td class="tablaHead">&nbsp;CUENTA&nbsp;</td>
							<td class="tablaHead">&nbsp;REGION&nbsp;</td>
							<td class="tablaHead">&nbsp;MEDIO&nbsp;</td>
							<td class="tablaHead">&nbsp;PRODUCTO&nbsp;</td>
							<td class="tablaHead">&nbsp;DESCRIPCION&nbsp;</td>
							<td class="tablaHead">&nbsp;FECHA OPERACION&nbsp;</td>
							<td class="tablaHead">&nbsp;TIPO DE MOVIMIENTO&nbsp;</td>
							<td class="tablaHead">&nbsp;ESTATUS&nbsp;</td>							
						</tr>
						<c:forEach items="${productosInbursa }" var="inbursaTO" varStatus="count">
			 				<c:choose>
								<c:when test="${count.index % 2 == 0 }">
						 			<tr>
						 				<td class="tablaDetalle">&nbsp;${inbursaTO.folioInbursa }&nbsp;</td>
						 				<td class="tablaDetalle">&nbsp;${inbursaTO.telefono }&nbsp;</td>
						 				<td class="tablaDetalle">&nbsp;${inbursaTO.ctaTelcel }&nbsp;</td>
						 				<td class="tablaDetalle">&nbsp;${inbursaTO.region }&nbsp;</td>
						 				<td class="tablaDetalle">&nbsp;${inbursaTO.medio }&nbsp;</td>	
						 				<td class="tablaDetalle">&nbsp;${inbursaTO.producto }&nbsp;</td>
						 				<td class="tablaDetalle">&nbsp;${inbursaTO.descripcion }&nbsp;</td>
						 				<td class="tablaDetalle">&nbsp;${inbursaTO.fechaOper }&nbsp;</td>
						 				<td class="tablaDetalle">&nbsp;${inbursaTO.tipoMovimiento }&nbsp;</td>
						 				<td class="tablaDetalle">&nbsp;${inbursaTO.estatus }&nbsp;</td>				
									</tr>	 
								</c:when>
								<c:otherwise>
									<tr>
						 				<td class="tablaDetalleCebra">&nbsp;${inbursaTO.folioInbursa }&nbsp;</td>
						 				<td class="tablaDetalleCebra">&nbsp;${inbursaTO.telefono }&nbsp;</td>
						 				<td class="tablaDetalleCebra">&nbsp;${inbursaTO.ctaTelcel }&nbsp;</td>
						 				<td class="tablaDetalleCebra">&nbsp;${inbursaTO.region }&nbsp;</td>
						 				<td class="tablaDetalleCebra">&nbsp;${inbursaTO.medio }&nbsp;</td>	
						 				<td class="tablaDetalleCebra">&nbsp;${inbursaTO.producto }&nbsp;</td>
						 				<td class="tablaDetalleCebra">&nbsp;${inbursaTO.descripcion }&nbsp;</td>
						 				<td class="tablaDetalleCebra">&nbsp;${inbursaTO.fechaOper }&nbsp;</td>
						 				<td class="tablaDetalleCebra">&nbsp;${inbursaTO.tipoMovimiento }&nbsp;</td>
						 				<td class="tablaDetalleCebra">&nbsp;${inbursaTO.estatus }&nbsp;</td>				
									</tr>
								</c:otherwise>
							</c:choose>		
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
	 	</c:if>
	 	
	 	<!-- Reporte Inbursa 100 minutos gratis -->
	 	<c:if test="${idReporte=='14' }">	 	
	 		<c:choose>
	 			<c:when test="${productosInbursa!=null && fn:length(productosInbursa)>0 }">
	 				<table border="1" class="general">
						<tr>
							<td class="tablaHead">&nbsp;FOLIO&nbsp;</td>
							<td class="tablaHead">&nbsp;TELEFONO&nbsp;</td>
							<td class="tablaHead">&nbsp;CUENTA&nbsp;</td>
							<td class="tablaHead">&nbsp;REGION&nbsp;</td>
							<td class="tablaHead">&nbsp;MEDIO&nbsp;</td>
							<td class="tablaHead">&nbsp;PRODUCTO&nbsp;</td>
							<td class="tablaHead">&nbsp;DESCRIPCION&nbsp;</td>
							<td class="tablaHead">&nbsp;FECHA OPERACION&nbsp;</td>
							<td class="tablaHead">&nbsp;TIPO DE MOVIMIENTO&nbsp;</td>
							<td class="tablaHead">&nbsp;ESTATUS&nbsp;</td>
						</tr>
				 		<c:forEach items="${productosInbursa }" var="inbursaTO"varStatus="count">
			 				<c:choose>
								<c:when test="${count.index % 2 == 0 }">
						 			<tr>
						 				<td class="tablaDetalle">&nbsp;${inbursaTO.folioInbursa }&nbsp;</td>
						 				<td class="tablaDetalle">&nbsp;${inbursaTO.telefono }&nbsp;</td>
						 				<td class="tablaDetalle">&nbsp;${inbursaTO.ctaTelcel }&nbsp;</td>
						 				<td class="tablaDetalle">&nbsp;${inbursaTO.region }&nbsp;</td>
						 				<td class="tablaDetalle">&nbsp;${inbursaTO.medio }&nbsp;</td>	
						 				<td class="tablaDetalle">&nbsp;${inbursaTO.producto }&nbsp;</td>
						 				<td class="tablaDetalle">&nbsp;${inbursaTO.descripcion }&nbsp;</td>
						 				<td class="tablaDetalle">&nbsp;${inbursaTO.fechaOper }&nbsp;</td>
						 				<td class="tablaDetalle">&nbsp;${inbursaTO.tipoMovimiento }&nbsp;</td>
						 				<td class="tablaDetalle">&nbsp;${inbursaTO.estatus }&nbsp;</td>	
						 			</tr>	 
				 			</c:when>
							<c:otherwise>	
									<tr>
						 				<td class="tablaDetalleCebra">&nbsp;${inbursaTO.folioInbursa }&nbsp;</td>
						 				<td class="tablaDetalleCebra">&nbsp;${inbursaTO.telefono }&nbsp;</td>
						 				<td class="tablaDetalleCebra">&nbsp;${inbursaTO.ctaTelcel }&nbsp;</td>
						 				<td class="tablaDetalleCebra">&nbsp;${inbursaTO.region }&nbsp;</td>
						 				<td class="tablaDetalleCebra">&nbsp;${inbursaTO.medio }&nbsp;</td>	
						 				<td class="tablaDetalleCebra">&nbsp;${inbursaTO.producto }&nbsp;</td>
						 				<td class="tablaDetalleCebra">&nbsp;${inbursaTO.descripcion }&nbsp;</td>
						 				<td class="tablaDetalleCebra">&nbsp;${inbursaTO.fechaOper }&nbsp;</td>
						 				<td class="tablaDetalleCebra">&nbsp;${inbursaTO.tipoMovimiento }&nbsp;</td>
						 				<td class="tablaDetalleCebra">&nbsp;${inbursaTO.estatus }&nbsp;</td>	
				 					</tr>
				 			</c:otherwise>
						</c:choose>			
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
	 	</c:if>
	 	
	 		 	<!-- Reporte Inbursa descuento de 1000 pesos -->
	 	<c:if test="${idReporte=='15' }">	 	
	 		<c:choose>
	 			<c:when test="${descuentoInbursa!=null && fn:length(descuentoInbursa)>0 }">
	 				<table border="1" class="general">
						<tr>
								<td class="tablaHead">&nbsp;FOLIO CA&nbsp;</td>
								<td class="tablaHead">&nbsp;LINEA&nbsp;</td>
								<td class="tablaHead">&nbsp;CUENTA&nbsp;</td>
								<td class="tablaHead">&nbsp;REGION&nbsp;</td>
								<td class="tablaHead">&nbsp;COMENTARIO&nbsp;</td>
								<td class="tablaHead">&nbsp;NO.MATERIAL&nbsp;</td>
								<td class="tablaHead">&nbsp;MARCA&nbsp;</td>
								<td class="tablaHead">&nbsp;MODELO&nbsp;</td>
								<td class="tablaHead">&nbsp;VALOR PUNTOS&nbsp;</td>
								<td class="tablaHead">&nbsp;DIF.PESOS&nbsp;</td>
								<td class="tablaHead">&nbsp;TIPO REDENCION&nbsp;</td>
								<td class="tablaHead">&nbsp;BONO INBURSA&nbsp;</td>
								<td class="tablaHead">&nbsp;BONO MARCA&nbsp;</td>
								<td class="tablaHead">&nbsp;BONO ROEXT&nbsp;</td>
								<td class="tablaHead">&nbsp;BONO ALTOVALOR&nbsp;</td>
								<td class="tablaHead">&nbsp;BONO CERTIFICADO&nbsp;</td>
								<td class="tablaHead">&nbsp;FECHA OPERACION&nbsp;</td>
								<td class="tablaHead">&nbsp;ESTATUS&nbsp;</td>
								<td class="tablaHead">&nbsp;CAC&nbsp;</td>
								<td class="tablaHead">&nbsp;FECHA EXPIRA CERTIFICADO&nbsp;</td>
								<td class="tablaHead">&nbsp;FOLIO CARTA&nbsp;</td>
								<td class="tablaHead">&nbsp;FOLIO CERTIFICADO&nbsp;</td>								
						</tr>
		 		<c:forEach items="${descuentoInbursa }" var="inbursaTO" varStatus="count">
		 				<c:choose>
							<c:when test="${count.index % 2 == 0 }">
				 				<tr>
					 				<td class="tablaDetalle">&nbsp;${inbursaTO.folioCA }&nbsp;</td>
									<td class="tablaDetalle">&nbsp;${inbursaTO.telefono }&nbsp;</td>
									<td class="tablaDetalle">&nbsp;${inbursaTO.ctaTelcel }&nbsp;</td>
									<td class="tablaDetalle">&nbsp;${inbursaTO.region }&nbsp;</td>
									<td class="tablaDetalle">&nbsp;${inbursaTO.comentario }&nbsp;</td>	
									<td class="tablaDetalle">&nbsp;${inbursaTO.producto }&nbsp;</td>
									<td class="tablaDetalle">&nbsp;${inbursaTO.marca }&nbsp;</td>
									<td class="tablaDetalle">&nbsp;${inbursaTO.modelo }&nbsp;</td>
									<td class="tablaDetalle">&nbsp;${inbursaTO.valorPuntos }&nbsp;</td>
									<td class="tablaDetalle">&nbsp;${inbursaTO.difPesos }&nbsp;</td>	
									<td class="tablaDetalle">&nbsp;${inbursaTO.tipoRedencion }&nbsp;</td>
									<td class="tablaDetalle">&nbsp;${inbursaTO.bonoInbursa }&nbsp;</td>
									<td class="tablaDetalle">&nbsp;${inbursaTO.bonoMarca }&nbsp;</td>
									<td class="tablaDetalle">&nbsp;${inbursaTO.bonoRoext }&nbsp;</td>
									<td class="tablaDetalle">&nbsp;${inbursaTO.bonoAltoValor }&nbsp;</td>	
									<td class="tablaDetalle">&nbsp;${inbursaTO.bonoDescInbursa }&nbsp;</td>
									<td class="tablaDetalle">&nbsp;${inbursaTO.fechaOper }&nbsp;</td>
									<td class="tablaDetalle">&nbsp;${inbursaTO.estatus }&nbsp;</td>		
									<td class="tablaDetalle">&nbsp;${inbursaTO.puntoVenta }&nbsp;</td>
									<td class="tablaDetalle">&nbsp;${inbursaTO.fechaExpira }&nbsp;</td>
									<td class="tablaDetalle">&nbsp;${inbursaTO.folioInbursa }&nbsp;</td>
									<td class="tablaDetalle">&nbsp;${inbursaTO.folioFinanzas }&nbsp;</td>
		 						</tr>
		 					</c:when>
							<c:otherwise>	 
								<tr>
					 				<td class="tablaDetalleCebra">&nbsp;${inbursaTO.folioCA }&nbsp;</td>
									<td class="tablaDetalleCebra">&nbsp;${inbursaTO.telefono }&nbsp;</td>
									<td class="tablaDetalleCebra">&nbsp;${inbursaTO.ctaTelcel }&nbsp;</td>
									<td class="tablaDetalleCebra">&nbsp;${inbursaTO.region }&nbsp;</td>
									<td class="tablaDetalleCebra">&nbsp;${inbursaTO.comentario }&nbsp;</td>	
									<td class="tablaDetalleCebra">&nbsp;${inbursaTO.producto }&nbsp;</td>
									<td class="tablaDetalleCebra">&nbsp;${inbursaTO.marca }&nbsp;</td>
									<td class="tablaDetalleCebra">&nbsp;${inbursaTO.modelo }&nbsp;</td>
									<td class="tablaDetalleCebra">&nbsp;${inbursaTO.valorPuntos }&nbsp;</td>
									<td class="tablaDetalleCebra">&nbsp;${inbursaTO.difPesos }&nbsp;</td>	
									<td class="tablaDetalleCebra">&nbsp;${inbursaTO.tipoRedencion }&nbsp;</td>
									<td class="tablaDetalleCebra">&nbsp;${inbursaTO.bonoInbursa }&nbsp;</td>
									<td class="tablaDetalleCebra">&nbsp;${inbursaTO.bonoMarca }&nbsp;</td>
									<td class="tablaDetalleCebra">&nbsp;${inbursaTO.bonoRoext }&nbsp;</td>
									<td class="tablaDetalleCebra">&nbsp;${inbursaTO.bonoAltoValor }&nbsp;</td>	
									<td class="tablaDetalleCebra">&nbsp;${inbursaTO.bonoDescInbursa }&nbsp;</td>
									<td class="tablaDetalleCebra">&nbsp;${inbursaTO.fechaOper }&nbsp;</td>
									<td class="tablaDetalleCebra">&nbsp;${inbursaTO.estatus }&nbsp;</td>		
									<td class="tablaDetalleCebra">&nbsp;${inbursaTO.puntoVenta }&nbsp;</td>
									<td class="tablaDetalleCebra">&nbsp;${inbursaTO.fechaExpira }&nbsp;</td>
									<td class="tablaDetalleCebra">&nbsp;${inbursaTO.folioInbursa }&nbsp;</td>
									<td class="tablaDetalleCebra">&nbsp;${inbursaTO.folioFinanzas }&nbsp;</td>
		 						</tr>
		 				</c:otherwise>
					</c:choose>		
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
	 	</c:if>
	 	
	 	<!-- Reporte Inbursa paquetes-->
	 	<c:if test="${idReporte=='16' }">	 	
	 		<c:choose>
	 			<c:when test="${paquetesInbursa!=null && fn:length(paquetesInbursa)>0 }">
	 				       <table border="1" class="general">
						<tr>
							<td class="tablaHead">&nbsp;TELEFONO&nbsp;</td>
							<td class="tablaHead">&nbsp;CUENTA&nbsp;</td>
							<td class="tablaHead">&nbsp;REGION&nbsp;</td>
							<td class="tablaHead">&nbsp;PRODUCTO&nbsp;</td>
							<td class="tablaHead">&nbsp;DESCRIPCION&nbsp;</td>
							<td class="tablaHead">&nbsp;FECHA OPERACION&nbsp;</td>
							<td class="tablaHead">&nbsp;ESTATUS&nbsp;</td>
							<td class="tablaHead">&nbsp;MOTIVO ESTATUS&nbsp;</td>
						</tr>
				 		<c:forEach items="${paquetesInbursa }" var="inbursaTO"varStatus="count">
			 				<c:choose>
								<c:when test="${count.index % 2 == 0 }">
						 			<tr>
						 				<td class="tablaDetalle">&nbsp;${inbursaTO.telefono }&nbsp;</td>
						 				<td class="tablaDetalle">&nbsp;${inbursaTO.ctaTelcel }&nbsp;</td>
						 				<td class="tablaDetalle">&nbsp;${inbursaTO.region }&nbsp;</td>
						 				<td class="tablaDetalle">&nbsp;${inbursaTO.producto }&nbsp;</td>
						 				<td class="tablaDetalle">&nbsp;${inbursaTO.descripcion }&nbsp;</td>
						 				<td class="tablaDetalle">&nbsp;${inbursaTO.fechaOper }&nbsp;</td>
						 				<td class="tablaDetalle">&nbsp;${inbursaTO.codigoRespuesta }&nbsp;</td>
						 				<td class="tablaDetalle">&nbsp;${inbursaTO.estatus }&nbsp;</td>	
						 			</tr>	 
				 			</c:when>
							<c:otherwise>	
									<tr>
						 				<td class="tablaDetalleCebra">&nbsp;${inbursaTO.telefono }&nbsp;</td>
						 				<td class="tablaDetalleCebra">&nbsp;${inbursaTO.ctaTelcel }&nbsp;</td>
						 				<td class="tablaDetalleCebra">&nbsp;${inbursaTO.region }&nbsp;</td>	
						 				<td class="tablaDetalleCebra">&nbsp;${inbursaTO.producto }&nbsp;</td>
						 				<td class="tablaDetalleCebra">&nbsp;${inbursaTO.descripcion }&nbsp;</td>
						 				<td class="tablaDetalleCebra">&nbsp;${inbursaTO.fechaOper }&nbsp;</td>
						 				<td class="tablaDetalleCebra">&nbsp;${inbursaTO.codigoRespuesta }&nbsp;</td>
						 				<td class="tablaDetalleCebra">&nbsp;${inbursaTO.estatus }&nbsp;</td>	
				 					</tr>
				 			</c:otherwise>
						</c:choose>			
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
	 	</c:if>
	 	
    </DIV>
</body>
</html>


