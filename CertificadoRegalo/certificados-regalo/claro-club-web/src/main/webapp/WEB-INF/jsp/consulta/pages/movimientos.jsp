<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href='<c:url value="/commons/js/sc_textsheet.css"/>' type="text/css">
<title>Movimientos</title>
</head>
<body style="background-color: transparent;"> 
	<script>
		document.parentWindow.parent.activaCargando("hidden","none");
	</script>
 	<DIV id="divMovimiento" style="MARGIN-TOP: 0px; OVERFLOW-Y: scroll; OVERFLOW-X: hidden; BORDER:solid 1px silver; HEIGHT: 280px;position: absolute;top: 10px;"> 	   	                    
       <table border="1" cellspacing="0" cellpadding="0" align="center" width="96%">       
              <tr bgcolor="#ECF0DB" class="healineblue1" align="center"> 
                  <th width="13%">Fecha Operación</th>
                  <th width="8%">Movimiento </th>
                  <th width="5%">Puntos </th>
                  <th width="8%">Excedentes </th>
                  <th width="10%">Total Ajustes </th>
                  <th width="56%">Referencia </th>
              </tr>
              <c:set var="contador" value="0"/>
              <c:forEach var="movimientoTO" items="${movimientosTO}" varStatus="total">
              
            	<c:set var="contador" value="${total.count}"/>   
              <tr class="X3" bgcolor="<c:if test="${contador %2 !=0 }">#D9EBF2</c:if>">
                 <td >&nbsp;${movimientoTO.facturacion}</td>                 
                 <td>
                 	<c:choose>
                 		<c:when test="${movimientoTO.movimiento=='REDEN'}">
                 		<b><FONT color="RED">&nbsp;${movimientoTO.movimiento}</FONT></b>
                 		</c:when>
                 		<c:when test="${movimientoTO.movimiento=='CANCE'}">
                 		<b><FONT color="BLUE">&nbsp;${movimientoTO.movimiento}</FONT></b>
                 		</c:when>
                 		<c:when test="${movimientoTO.movimiento=='BLEAL'}">
                 		<b><FONT color="#698B22">&nbsp;${movimientoTO.movimiento}</FONT></b>
                 		</c:when>
                 		<c:otherwise>
                 		<b><FONT color="BLACK" style="" face="">&nbsp;${movimientoTO.movimiento}</FONT></b>
                 		</c:otherwise>
                    </c:choose> 
                 </td>
                 <td >&nbsp;${movimientoTO.numPuntos}</td>
                 <td >&nbsp;${movimientoTO.numPuntosExc}</td>
                 <td >&nbsp;${movimientoTO.totalAjustes}</td>
                 <td >&nbsp;${movimientoTO.referencia}</td>                 
              </tr>
              
              </c:forEach>
          </table>
      </DIV>
      <DIV id="divTotal" style="height:50px;position: absolute;top: 290px;">
      <table style="width: 98%">
       	<tr bgcolor="#ECF0DB" class="healineblue1">
       		<td>&nbsp;<c:out value="${contador}"></c:out> Registro(s) Encontrado(s).</td>
       	</tr>
      </table>
      </DIV>
</body>
</html>