<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt'%>
<table id="tablaCatalogo">
	<thead>								
	</thead>
	<tbody>
		<c:forEach var="registro" items="${listaRegistros}">
		<tr id="${registro.descripcion}">
			<td>
				${registro.descripcion}
			</td>
		</tr>
		</c:forEach>
	</tbody>	
</table>
<input type="hidden" name="idmensaje" id="idmensaje" value="${idmensaje}">
<input type="hidden" name="mensaje" id="mensaje" value="${mensaje}">
<input type="hidden" name="descFormaRedencion" id="descFormaRedencion" value="${descFormaRedencion}">
<input type="hidden" name="descTipoRedencion" id="descTipoRedencion" value="${descTipoRedencion}">
<input type="hidden" name="formaRedencion" id="formaRedencion" value="${formaRedencion}">