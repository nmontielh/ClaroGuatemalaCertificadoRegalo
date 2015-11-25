<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="/spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- MÓDULO ASIGNACIÓN DE PUNTOS - JAPA 20/02/2013 Folio 121733 -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Administración de Perfiles para Asignación de Puntos</title>
<link rel="stylesheet" type="text/css" href='<c:url value="/commons/css/impromptus.css"/>' media='screen' >
<script type="text/javascript" src='<c:url value="/commons/js/jquery-1.2.3.pack.js"/>'></script>
<script type='text/javascript' src='<c:url value="/commons/js/jquery.form.js"/>' ></script>
<script type="text/javascript" src='<c:url value="/commons/js/jquery-impromptu.1.3.js"/>' ></script>

<script type="text/javascript">

	function selecPerfil(id, ptosMaximos) {
				
		if(parent["consultaPerfilAsig"].document.forms[0].Actualizar != null)
			parent["consultaPerfilAsig"].document.forms[0].Actualizar.disabled 	= false;
			
		if(parent["consultaPerfilAsig"].document.forms[0].Eliminar != null)
			parent["consultaPerfilAsig"].document.forms[0].Eliminar.disabled 	= false;
			
		if(parent["consultaPerfilAsig"].document.forms[0].Agregar != null)
			parent["consultaPerfilAsig"].document.forms[0].Agregar.disabled 	= true;
			
		parent["consultaPerfilAsig"].document.forms[0].ptosMaximos.value 	= ptosMaximos;
		
		var numPerfiles = parent["consultaPerfilAsig"].document.forms[0].idPerfilN.length;
		var idPerfil 	= null; 
		for (i = 0; i < numPerfiles; i++) {
			idPerfil 		= parent["consultaPerfilAsig"].document.forms[0].idPerfilN[i].value;
			if(idPerfil == id) {
			
				parent["consultaPerfilAsig"].document.forms[0].idPerfilN[i].selected = true;
			}
		}

		
		
		//document.getElementById("Actualizar").disabled = false;
	
	/*
		var actualizar  = parent["consulta"].document.forms[0].Actualizar;
		if(actualizar!=null){
			actualizar.disabled=false;
		}
		*/
	}
</script>
</head>
<body background="<c:url value="/commons/images/backgroundlight.gif"/>">
<form name="frmDetPerfiles" id="frmDetPerfiles" action="./menuAsignacionPuntos.do" method="post">
<input type="hidden" name="idPerfilN" id="idPerfilN" value=""/>
<input type="hidden" name="ptosMaximos" id="ptosMaximos" value=""/>
<input type="hidden" name="accion" id="accion" value="CONSULTA_DETALLE" />

			
<div id="listados" align="left">
<table id="tablaLista" border="1" cellspacing="0" cellpadding="0" width="600px">
	  <tbody>
	  <c:if test="${perfilesAsigLst != null}">
	  	<c:forEach  var="perfilTO" items="${perfilesAsigLst}">
			<tr id="linea" class="InputC" bgcolor="#ECF0DB" align="center">
				<td width="60px"><input type="radio" name="rdbPuntoVta" id="${perfilTO.idPerfilN}" 
					onClick="selecPerfil('${perfilTO.idPerfilN}','${perfilTO.nivelAutorizacion}')"> 
				</td>
				<td width="120px">&nbsp;${perfilTO.idPuesto}&nbsp;</td> 
				<td width="300px">&nbsp;${perfilTO.descripcion}&nbsp;</td>
				<td width="120px">&nbsp;${perfilTO.nivelAutorizacion}&nbsp;</td>
			</tr>
		</c:forEach>
	  </c:if>
	  </tbody>
	</table>
</div>
</form>
</body>
</html>