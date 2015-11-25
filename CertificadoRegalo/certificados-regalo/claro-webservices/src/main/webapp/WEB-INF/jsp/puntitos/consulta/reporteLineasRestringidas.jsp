<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<c:choose>
		<c:when test="${telefono!=null && fn:length(telefono)>0 }">
			<% response.setContentType("application/vnd.ms-excel"); %>
			<meta http-equiv="Content-Type" content="application/vnd.ms-excel; charset=ISO-8859-1">
	 	</c:when>
	 	<c:otherwise>
	 		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	 	</c:otherwise>
	 </c:choose>

<head>
	<title>Reporte Lineas Restringidas Circulo Azul</title>
		
</head>

<body style="background: transparent;"> 
 	<DIV id="divMovimiento" style="background-color:transparent; MARGIN-TOP: 0px; OVERFLOW-Y: scroll; OVERFLOW-X: hidden; BORDER:solid 1px silver; width:98%; HEIGHT: 90%;position: absolute;top: 10px;">
	 	<!-- Reporte Redenciones -->
	 		<c:choose>
	 			<c:when test="${telefono!=null && fn:length(telefono)>0 }">
	 				<table border="1" class="general">
						<tr>
							<td>CUENTA</td>
				 			<td>LINEA</td>				 			
				 			<td>REGION</td>	 			
				 			<td>PLAN</td>
				 		</tr>
				 		<c:forEach items="${telefono }" var="telefonoTO">
				 			<tr>
								<td>${telefonoTO.cuenta }</td>
					 			<td>${telefonoTO.telefono }</td>
					 			<td>${telefonoTO.region }</td>
 								<td>${telefonoTO.plan }</td>
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