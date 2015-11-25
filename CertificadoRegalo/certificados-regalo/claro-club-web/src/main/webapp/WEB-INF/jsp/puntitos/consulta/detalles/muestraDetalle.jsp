<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<c:forEach  var="telefonoTO" items="${lineas}">
	<tr id="${telefonoTO.telefono}|${telefonoTO.cuenta}" class="InputB" bgcolor="#ECF0DB" align="center">
		<td class="txtSumNormal">
		<input type="submit" class="botonInactivo" name="selector" value="${telefonoTO.telefono}" onclick="seleccion('${telefonoTO.telefono}|${telefonoTO.cuenta}|${telefonoTO.region}|${telefonoTO.puntosTO.estatusPuntos}')"></td>
		<td class="txtSumNormal">&nbsp;${telefonoTO.puntosTO.estatusPuntos}&nbsp;</td>
		<td class="txtSumNormal">&nbsp;${telefonoTO.cuenta}&nbsp;<input type="hidden" name="cuenta" id="cuenta" value="${telefonoTO.cuenta}"></td> 
		<td class="txtSumNormal">&nbsp;${telefonoTO.region}&nbsp;</td>
		<td class="txtSumNormal">&nbsp;${telefonoTO.secuencia}&nbsp;</td>
		<td class="txtSumNormal">&nbsp;${telefonoTO.plan}&nbsp;</td>
		<td class="txtSumNormal">&nbsp;${telefonoTO.ciclo}&nbsp;</td>
		<td class="txtSumNormal">&nbsp;${telefonoTO.nickName}&nbsp;</td>
		<td class="txtSumNormal">&nbsp;${telefonoTO.addendum}&nbsp;</td>
		<td class="txtSumNormal">&nbsp;<fmt:formatDate value="${telefonoTO.fechaExpira}" pattern="dd/MM/yyyy" />&nbsp;</td>
		<td class="txtSumNormal">&nbsp;<fmt:formatDate value="${telefonoTO.fechaAltaTime}" pattern="dd/MM/yyyy" />&nbsp;</td>
	</tr>
</c:forEach>