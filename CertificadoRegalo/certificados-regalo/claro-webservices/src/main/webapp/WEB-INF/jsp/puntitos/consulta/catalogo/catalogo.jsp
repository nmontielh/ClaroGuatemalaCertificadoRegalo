<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt'%>
<table id="tablaCatalogo">
	<thead>								
	</thead>
	<tbody>
		<c:forEach var="registro" items="${listaRegistros}">
		<tr id="${registro.descripcion}|${registro.valor}">
			<td>
				${registro.descripcion}
			</td>
		</tr>
		</c:forEach>
	</tbody>	
</table>
