<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<DIV style="position: static;top: 20px;height: 280px;MARGIN-TOP: 0px; OVERFLOW-Y: scroll; OVERFLOW-X: hidden; BORDER:solid 1px silver;">
<table border="1" cellspacing="0" cellpadding="0" align="center">           
           <tr bgcolor="#ECF0DB" class="healineblue1"  align="center" > 
               <td width="10%">Fecha Operación </td>
               <td width="10%">Cuenta Alianza </td>
               <td width="10%">Puntos Canjeados</td>
               <td width="10%">Millas/Valor cup&oacute;n</td>
               <td width="10%">Usuario </td>
               <td width="50%">Comentario </td>
           </tr>             	
           <c:set var="contador" value="0"/>		             
           <c:forEach var="alianzaTO" items="${alianzasTO}" varStatus="total">
            <c:set var="contador" value="${total.count}"/>             
	           <tr class="X3" bgcolor="<c:if test="${contador %2 !=0 }">#D9EBF2</c:if>">
	           	<td>&nbsp;${alianzaTO.fechaOperacion}</td>
	           	<td>&nbsp;${alianzaTO.cuentaAlianza}</td>
	           	<td align="center">&nbsp;${alianzaTO.puntosRedimidosTO.ptsCanjeados}</td>
	           	
	           	<c:choose>
                	<c:when test="${alianzaTO.millas==0}"><td align="center">&nbsp;${alianzaTO.valorCuponOrig}</td></c:when>
                	<c:otherwise>
                	<td align="center">&nbsp;${alianzaTO.millas}</td>
                	</c:otherwise>                	
				</c:choose>		
	           	
	           	<!-- <td>&nbsp;${alianzaTO.valorCuponOrig}</td> -->
	           	
	           	<td align="center">&nbsp;${alianzaTO.usuario}</td>
	           	<td>&nbsp;${alianzaTO.comentario}</td>
	           </tr>           
           </c:forEach>           
</table>
</DIV>
<DIV id="divTotal" style="height:20px;position: absolute;top: 280px;">
       <table style="width: 98%">
       		<tr bgcolor="#ECF0DB" class="healineblue1">
       			<td>&nbsp;<c:out value="${contador}"></c:out> Registro(s) Encontrado(s).</td>
       		</tr>
       		<tr>
       			 <td align="center"><font color="red"><b>&nbsp;${mensaje}</b></font></td>
       		</tr>
      	</table>

</DIV>