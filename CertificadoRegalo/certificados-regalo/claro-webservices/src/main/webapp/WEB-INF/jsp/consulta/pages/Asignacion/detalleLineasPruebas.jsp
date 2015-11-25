<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:choose>	
	<c:when test="${idmensaje == 0 }">
		<table border="1" cellspacing="0" cellpadding="0" align="right" width="100%">
			<thead>
				<tr>
					<td class="tablaHead" width="20"></td>
					<td class="tablaHead" width="130">&nbsp;Línea</td>
					<td class="tablaHead" width="130">&nbsp;Cuenta</td>
					<td class="tablaHead" width="70">&nbsp;Región</td>
				</tr>
			</thead>
			
			<tbody>
				<c:forEach items="${lineasPrueba}" var="lineaTO" varStatus="count">
				<c:choose>
					<c:when test="${count.index % 2 == 0 }">
						<tr>
							<td class="tablaDetalle">
								<input type="radio" name="lineaRadio" onclick="eligeLineaPrueba('${lineaTO.telefono }','${lineaTO.cuenta }');">
							</td>
							<td class="tablaDetalle">${lineaTO.telefono }</td>
							<td class="tablaDetalle">${lineaTO.cuenta }</td>
							<td class="tablaDetalle">${lineaTO.region}</td>
						</tr>
					</c:when>
					<c:otherwise>
						<tr>
							<td class="tablaDetalleCebra">
								<input type="radio" name="lineaRadio" onclick="eligeLineaPrueba('${lineaTO.telefono }','${lineaTO.cuenta }');">
							</td>
							<td class="tablaDetalleCebra">${lineaTO.telefono }</td>
							<td class="tablaDetalleCebra">${lineaTO.cuenta }</td>
							<td class="tablaDetalleCebra">${lineaTO.region}</td>
						</tr>
					</c:otherwise>
				</c:choose>
					
				</c:forEach>
			</tbody>
		</table>
	</c:when>
	<c:otherwise>
		<div align="left" style="position: absolute; top: 2%; left: 10px; background-color: transparent; height: 200px" AllowTransparency >
			<table border="0" width="100%" align="center" class="BloqueErrorEspera" style="background-color: transparent;">	
				<tr align="center">	<td valign="middle" height="145px" width="100%" bgcolor="#ECF0DB">${idmensaje},${mensaje}</td></tr>
			</table>
		</div>
	</c:otherwise>

</c:choose>