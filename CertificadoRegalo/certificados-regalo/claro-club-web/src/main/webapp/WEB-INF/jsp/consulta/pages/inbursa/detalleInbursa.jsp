<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<c:forEach  var="inbursaTO" items="${inbursa}" varStatus="count">
	<c:choose>
		<c:when test="${count.index % 2 == 0 }">
			<tr id="${inbursaTO.telefono}" align="center">
				<td class="tablaDetalle" id="tdTelefono" >${inbursaTO.telefono}</td>
				<td class="tablaDetalle">&nbsp;${inbursaTO.folioInbursa}&nbsp;</td>
				<td class="tablaDetalle">&nbsp;${inbursaTO.region}&nbsp;</td>
				<td class="tablaDetalle">&nbsp;${inbursaTO.fechaOper}&nbsp;</td>
				<td class="tablaDetalle">&nbsp;${inbursaTO.producto}&nbsp;</td>
				<td class="tablaDetalle">&nbsp;${inbursaTO.descripcion}&nbsp;</td>
				<td class="tablaDetalle">&nbsp;${inbursaTO.modalidad}&nbsp;</td>
				<td class="tablaDetalle">&nbsp;${inbursaTO.tipoMovimiento}&nbsp;</td>
				<td class="tablaDetalle">&nbsp;${inbursaTO.estatus}&nbsp;</td>
				<td class="tablaDetalle">&nbsp;${inbursaTO.bonoInbursa }&nbsp;</td>
				<td class="tablaDetalle">&nbsp;${inbursaTO.bonoMarca }&nbsp;</td>
				<td class="tablaDetalle">&nbsp;${inbursaTO.bonoRoext }&nbsp;</td>
				<td class="tablaDetalle">&nbsp;${inbursaTO.bonoAltoValor }&nbsp;</td>	
				<td class="tablaDetalle">&nbsp;${inbursaTO.bonoDescInbursa }&nbsp;</td>
				<td class="tablaDetalle">&nbsp;${inbursaTO.folioFinanzas }&nbsp;</td>
			</tr>
		</c:when>
		<c:otherwise>
			<tr id="${inbursaTO.telefono}" align="center">
				<td class="tablaDetalleCebra" id="tdTelefono">${inbursaTO.telefono}</td>
				<td class="tablaDetalleCebra">&nbsp;${inbursaTO.folioInbursa}&nbsp;</td>
				<td class="tablaDetalleCebra">&nbsp;${inbursaTO.region}&nbsp;</td>
				<td class="tablaDetalleCebra">&nbsp;${inbursaTO.fechaOper}&nbsp;</td>
				<td class="tablaDetalleCebra">&nbsp;${inbursaTO.producto}&nbsp;</td>
				<td class="tablaDetalleCebra">&nbsp;${inbursaTO.descripcion}&nbsp;</td>
				<td class="tablaDetalleCebra">&nbsp;${inbursaTO.modalidad}&nbsp;</td>
				<td class="tablaDetalleCebra">&nbsp;${inbursaTO.tipoMovimiento}&nbsp;</td>
				<td class="tablaDetalleCebra">&nbsp;${inbursaTO.estatus}&nbsp;</td>
				<td class="tablaDetalleCebra">&nbsp;${inbursaTO.bonoInbursa }&nbsp;</td>
				<td class="tablaDetalleCebra">&nbsp;${inbursaTO.bonoMarca }&nbsp;</td>
				<td class="tablaDetalleCebra">&nbsp;${inbursaTO.bonoRoext }&nbsp;</td>
				<td class="tablaDetalleCebra">&nbsp;${inbursaTO.bonoAltoValor }&nbsp;</td>	
				<td class="tablaDetalleCebra">&nbsp;${inbursaTO.bonoDescInbursa }&nbsp;</td>
				<td class="tablaDetalleCebra">&nbsp;${inbursaTO.folioFinanzas }&nbsp;</td>
			</tr>
		</c:otherwise>
	</c:choose>
</c:forEach>