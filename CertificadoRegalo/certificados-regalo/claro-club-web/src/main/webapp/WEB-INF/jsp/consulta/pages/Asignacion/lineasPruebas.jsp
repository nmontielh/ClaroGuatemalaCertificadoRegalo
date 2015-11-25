<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/permisos.tld" prefix="securityCa"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Asignar Puntos</title>
<link rel="stylesheet" href='<c:url value="/commons/js/sc_textsheet.css"/>' type="text/css">
<script type="text/javascript" src='<c:url value="/commons/js/jquery-1.2.3.js"/>'></script>
<script type="text/javascript" src='<c:url value="/commons/js/jquery-validate.js"/>'></script>


</head>
<script type="text/javascript">


	$(function(){
	
		$('#btnAgregar').click(function(){
		
			var telefono	= $('#telefonoId').val();
			var cuenta		= $('#cuentaId').val();
			
			var telRegExp = "^[0-9]{10}$";
			var cuentaRegExp = "^[0-9]";
			
			if(!telefono.match(telRegExp)){
				alert("Introduzca un teléfono válido (10 dígitos).");
				return false;	
			}
			
			if(!cuenta.match(cuentaRegExp)){
				alert("Introduzca una cuenta válida (únicamente números).");
				return false;
			}
			
			$.post('./agregaLineaPruebas.do', $('#frmAgregaLineaPrueba').serialize(), function(data){
				$( "#divLineasPruebaDetalle" ).empty().append( data );
				
				$('#telefonoSelected').val("");
				$('#cuentaSelected').val("");
			});
			
		});
		$('#btnEliminar').click(function(){
		
			if($("input[name='lineaRadio']:checked").val()){
				$.post('./eliminaLineaPruebas.do', $('#frmEliminaLineaPrueba').serialize(), function(data){
					$( "#divLineasPruebaDetalle" ).empty().append( data );
					$('#telefonoSelected').val("");
					$('#cuentaSelected').val("");
				});
			}else{
				alert("Debe eligir una linea.");
			}	
		});		
	});
	
	function eligeLineaPrueba(telefono,cuenta){
		$('#telefonoSelected').val(telefono);
		$('#cuentaSelected').val(cuenta);
	}

</script>

 <body  marginwidth="0" marginheight="0" style="MARGIN: 0px;background-color: transparent;" >

	
	<!-- SECCION LINEAS DE PRUEBA -->
	<br>
	<div id="divLineasPrueba" style="height: 265px;MARGIN-TOP: 0px; OVERFLOW-Y: hidden; OVERFLOW-X: hidden;visibility: visible;display: block;">
		
		<form id="frmLineasPrueba" name="frmLineasPrueba" method="post" >
			<div id="divLineasPruebaDetalle" style="overflow-y: scroll; overflow-x: hidden; width: 50%; height: 135px; margin-left: auto;margin-right: auto;">
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
			</div>
		</form>
				
			
		<form id="frmAgregaLineaPrueba" method="post">
			<table width="90%" border="0" cellspacing="0" cellpadding="0" align="center">
				<tr>
					<td class="healineblue1" valign="middle" height="20" width="8%"> Línea: </td>
					<td colspan=2 height="20" valign="middle" width="1006">
						<input class="InputText" type="text" id="telefonoId" name="telefonoId"/>
					</td>
				</tr>
				<tr>
					<td class="healineblue1" valign="middle" height="20" width="8%">Cuenta: </td>
					<td colspan=2 height="20" valign="middle" width="1006"> 
						<input class="InputText" type="text" id="cuentaId" name="cuentaId"/>
					</td>			
				</tr>
				<tr>
					<td class="healineblue1" valign="middle" height="20" width="8%">Región:</td>	
					<td colspan=2 height="20" valign="middle" width="1006"> 
		              	<select class="selectC" id="regionId" name="regionId">
					  		<option value="1">R01</option>
					  		<option value="2">R02</option>
					  		<option value="3">R03</option>				
					  		<option value="4">R04</option>			
					  		<option value="5">R05</option>													
					  		<option value="6">R06</option>												
					  		<option value="7">R07</option>
					  		<option value="8">R08</option>
					  		<option value="9">R09</option>								
					  	</select>
	   		   		</td>
	   	 		</tr>
	   	 		<tr height="20">
	   	 			<td colspan="2"></td>
	   	 		</tr>
	   	 		<tr>
	   	 			<td colspan="2">
	   	 				<table width="60%">
	   	 					<tr>
	   	 						<td align="left" width="94">
	   	 							<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="188">
	   	 								<input class="buttonC" type="button"  id="btnAgregar" name="btnAgregar" value="  Agregar  ">
	   	 							</securityCa:validaPerfil>
	   	 						</td>
	   	 						<td align="left" width="168">
	   	 							<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="189">
	   	 								<input class="buttonC" type="button"  id="btnEliminar" name="btnEliminar" value="  Eliminar  ">
	   	 							</securityCa:validaPerfil>
	   	 						</td>
	   	 					</tr>
	   	 				</table>
	   	 			</td>
	   	 		</tr>		
			</table>
		</form>
		<form id="frmEliminaLineaPrueba" method="POST">
			<input type="hidden" id="telefonoSelected" name="telefonoSelected">
			<input type="hidden" id="cuentaSelected" name="cuentaSelected">
		</form>
		
	</div>
	
	
</body>
</html>