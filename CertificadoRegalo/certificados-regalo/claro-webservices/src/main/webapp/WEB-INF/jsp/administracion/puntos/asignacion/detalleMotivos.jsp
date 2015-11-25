<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="/spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- MÓDULO ASIGNACIÓN DE PUNTOS - JAPA 20/02/2013 Folio 121733 -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Administración de Motivos de Asignación de Puntos</title>
<link rel="stylesheet" type="text/css" href='<c:url value="/commons/css/impromptus.css"/>' media='screen' >
<script type="text/javascript" src='<c:url value="/commons/js/jquery-1.2.3.pack.js"/>'></script>
<script type='text/javascript' src='<c:url value="/commons/js/jquery.form.js"/>' ></script>
<script type="text/javascript" src='<c:url value="/commons/js/jquery-impromptu.1.3.js"/>' ></script>
<script type="text/javascript">

	function selecMotivo(id, descripcion, estatus) {
				
		if(parent["consultaMotivosAsig"].document.forms[0].Actualizar != null)
			parent["consultaMotivosAsig"].document.forms[0].Actualizar.disabled = false;
			
		if(parent["consultaMotivosAsig"].document.forms[0].Eliminar != null)
			parent["consultaMotivosAsig"].document.forms[0].Eliminar.disabled 	= false;
		
		parent["consultaMotivosAsig"].document.forms[0].idMotivo.value 		= id;
		parent["consultaMotivosAsig"].document.forms[0].descripcion.value 	= descripcion;
		
		var numEstatus 	= parent["consultaMotivosAsig"].document.forms[0].estatus.length;
		var idEstatus 	= null; 
		for (i = 0; i < numEstatus; i++) {
			idEstatus 	= parent["consultaMotivosAsig"].document.forms[0].estatus[i].value;
			if(idEstatus == estatus) {
				parent["consultaMotivosAsig"].document.forms[0].estatus[i].selected = true;
			}
		}


	}
</script>

</head>
<body background="<c:url value="/commons/images/backgroundlight.gif"/>">
<form name="frmDetMotivos" id="frmDetMotivos" action="./menuAsignacionPuntos.do" method="post">
<input type="hidden" name="idMotivo" id="idMotivo" value=""/>
<input type="hidden" name="descripcion" id="descripcion" value=""/>
<input type="hidden" name="estatus" id="estatus" value=""/>
<input type="hidden" name="accion" id="accion" value="CONSULTA_DETALLE" />


<div id="listados" align="left">
<table id="tablaLista" border="1" cellspacing="0" cellpadding="0" width="600px">
	  <tbody>
	  <c:if test="${motivosAsigLst != null}">
	  	<c:forEach  var="motivoTO" items="${motivosAsigLst}">
			<tr id="linea" class="InputC" bgcolor="#ECF0DB" align="center">
				<td width="60px"><input type="radio" name="rdbMotivo" id="${motivoTO.idMotivo}" 
					onClick="selecMotivo('${motivoTO.idMotivo}','${motivoTO.descripcion}','${motivoTO.estatus}')"> 
				</td>
				<td width="120px">&nbsp;${motivoTO.idMotivo}&nbsp;</td>
				<td width="300px">&nbsp;${motivoTO.descripcion}&nbsp;</td>
				<td width="120px">&nbsp;${motivoTO.estatus}&nbsp;</td>
			</tr>
		</c:forEach>
	  </c:if>
	  </tbody>
	</table>
</div>
</form>
</body>
</html>