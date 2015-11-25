<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt'%>     
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix='f' uri="http://java.sun.com/jstl/fmt_rt"%>
<%@ taglib prefix="spring" uri="/spring"%>
<%@ taglib uri="/WEB-INF/permisos.tld" prefix="securityCa"  %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Administración de Promociones</title>
<link rel="stylesheet" type="text/css" href='<c:url value="/commons/css/impromptus.css"/>' media='screen' >
<link rel="stylesheet" href='<c:url value="/commons/js/sc_textsheet.css"/>' type="text/css">
        
<script type="text/javascript" src='<c:url value="/commons/js/jquery-1.2.3.pack.js"/>'></script>
<script type='text/javascript' src='<c:url value="/commons/js/jquery.form.js"/>' ></script>
<script type="text/javascript" src='<c:url value="/commons/js/jquery-impromptu.1.3.js"/>' ></script>
<script type="text/javascript" src='<c:url value="/commons/js/puntitos.js"/>' ></script>
<script type="text/javascript">
	function consulta(tipoAccion){
		var grupo = document.getElementById('Grupo');
		var planes = document.getElementById('Planes');
		var promociones = document.getElementById('Promociones');
		var fuerzasVentas = document.getElementById("FuerzasVentas");
		var promocionSms = document.getElementById('PromocionSms');
		var clienteExcelente = document.getElementById('ClienteExcelente');
			
		if(grupo.checked==false && planes.checked==false && promociones.checked==false
			&& fuerzasVentas.checked==false && promocionSms.checked==false  && clienteExcelente.checked==false){
			alert("Debe eligir una opción para consultar.");
			return false;
		}
		var accion = document.getElementById("accion");
		var reporte = document.getElementById("reporte");
		if(tipoAccion == 1) {
			accion.value = "Consulta";
		} else {
			/* Agregar tipos de producto JAPA 27/09/2011 Folio 96353 */
			accion.value = "Exporta";

			var idProducto = document.getElementById('idProducto').value;
			var idGrupoPromoc = document.getElementById('idGrupoPromoc').value;
			var idtipoproducto = document.getElementById('idtipoproducto').value;
			var idestatus = document.getElementById('idestatus').value;
			var idregion = document.getElementById('idregion').value;
			var idAreaPromoc = document.getElementById('idAreaPromoc').value;
			window.open('./consultaPlanes.do?idProducto='+idProducto+'&idGrupoPromoc='+idGrupoPromoc+'&idtipoproducto='+idtipoproducto+'&idestatus='+idestatus+'&idregion='+idregion+'&idAreaPromoc='+idAreaPromoc+'&accion='+accion.value+'&reporte=Promociones','Reporte_Promociones','status=1,toolbar=0,location=0,directories=0,menubar=0,titlebar=1,width=800,height=350');

			return false;
		}
		
		if(grupo.checked==true){
			reporte.value="Grupos";
		}
		
		if(planes.checked==true){
			/*
			var idPlan = document.getElementById("idPlan");
			if(idPlan==null || idPlan.value==""){
				alert("Debe introducir un idPlan");
				return false;
			}
			*/
			reporte.value="Planes";
		}
		
		if(promociones.checked==true){
			/*
			var idProducto = document.getElementById("idProducto");
			if(idProducto==null || idProducto.value==""){
				alert("Debe introducir un idProducto");
				return false;
			}*/
			
			reporte.value="Promociones";
		}
		if(fuerzasVentas.checked==true){
			reporte.value="FuerzasVentas";
		}		
		
		if(promocionSms.checked==true){
			reporte.value="PromocionesSms";
		}
		
		if(clienteExcelente.checked==true){			
			reporte.value="ClienteExcelentes";
		}
		
		var form = document.getElementById('frmConsulta');
		form.submit();
	}
</script>
</head>
<body onload="lectura()">
<form action="./consultaPlanes.do" method="post" name="frmConsulta" id="frmConsulta" AUTOCOMPLETE="OFF">
<input type="hidden" name="accion" id="accion" />
<input type="hidden" name="reporte" id="reporte" />
<input type="hidden" name="tipo" id="tipo" />
<table width="100%">
	<tr>
		<td height="30" class="subtitulo">Puntitos: Consulta de Planes y Promociones</td>
		<td align="right" >
			<div class="txtSumNormal" id="divLoading" style="display: none;">
				<img src="./imagenes/loading.gif"/> Buscando infomaci&oacute;n &nbsp;&nbsp; </div> </td>	
	</tr>
</table>
<table width="100%">
	<tr>
		<td width="10">
			<input type="radio" name="seleccion" id="Grupo" onclick="selec('Grupos')" />
		</td>
		<td class="healineblue1">Consulta de Grupos de Promoción</td>
	</tr>
	<tr id="grupo1" style="visibility:hidden; display:none">
		<td width="10"></td>
		<td>
			<table>
				<tr>
					<td class="txtSumNormal">Id Grupo</td>
					<td>
						<input type="text" name="idGrupoPromo" id="idGrupoPromo" class="InputB" maxlength="10" size="11">
					</td>
					<td width="10"></td>
					<td class="txtSumNormal">Tipo Promoci&oacute;n</td>
					<td>
						<input type="text" name="tipoPromo" id="tipoPromo" class="InputB" maxlength="2" size="3">
					</td>
				</tr>
				<tr id="grupo2" style="visibility:hidden; display:none">
					<td class="txtSumNormal">Estatus</td>
					<td>
						<select name="estatusGpoPromo" id="estatusGpoPromo" class="InputB" disabled="disabled">
							<option value="A">Activo</option>
							<option value="I">Inactivo</option>							
						</select>
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td width="10">
			<input type="radio" name="seleccion" id="Planes" onclick="selec('Planes')"/>
		</td>
		<td class="healineblue1">Consulta de Planes</td>
	</tr>
	<tr id="plan1" style="visibility:hidden; display:none">
		<td width="10"></td>
		<td>
			<table>
				<tr>
					<td class="txtSumNormal">Estatus</td>
					<td>
						<select name="estatus" id="estatus" class="InputB" disabled="disabled">
							<option value="A">Activo</option>
							<option value="I">Inactivo</option>
							<option value="T">Todos</option>
						</select>
					</td>
					<td width="10"></td>
					<td class="txtSumNormal">Id Plan</td>
					<td>
						<input type="text" name="idPlan" id="idPlan" class="InputB" maxlength="10">
					</td>
				</tr>
				<tr id="plan2" style="visibility:hidden; display:none">
					<td class="txtSumNormal">Region</td>
					<td>
						<select name="region" id="region" class="InputB" disabled="disabled">
							<option value="1">1</option>
							<option value="2">2</option>
							<option value="3">3</option>
							<option value="4">4</option>
							<option value="5">5</option>
							<option value="6">6</option>
							<option value="7">7</option>
							<option value="8">8</option>
							<option value="9">9</option>
						</select>
					</td>
				</tr>
			</table>
		</td>
	</tr>
	
	<tr>
		<td width="10">
			<input type="radio" name="seleccion" id="Promociones" onclick="selec('Promociones')"/>
		</td>		
		<td class="healineblue1">Consulta de Promociones</td>
	</tr>
	<tr id="prom1" style="visibility:hidden; display:none">
		<td width="10"></td>
		<td>
			<table>
				<tr>
					<td class="txtSumNormal">Estatus</td>
					<td>
						<select name="idestatus" id="idestatus" class="InputB" disabled="disabled">
							<option value="A">Activo</option>
							<option value="I">Inactivo</option>
						</select>
					</td>
					<td width="10"></td>
					<td class="txtSumNormal">Id Producto</td>
					<td>
						<input type="text" name="idProducto" id="idProducto" maxlength="20" width="10" value="" class="InputB">
					</td>
				</tr>
				<tr id="prom2" style="visibility:hidden; display:none">
					<td class="txtSumNormal">Regi&oacute;n</td>
					<td colspan="2">
						<select name="idregion" id="idregion" class="InputB" disabled="disabled">
							<option value="1">1</option>
							<option value="2">2</option>
							<option value="3">3</option>
							<option value="4">4</option>
							<option value="5">5</option>
							<option value="6">6</option>
							<option value="7">7</option>
							<option value="8">8</option>
							<option value="9">9</option>
						</select>
					</td>
					<td class="txtSumNormal">idGrupoPromoci&oacute;n</td>
					<td>
						<input type="text" name="idGrupoPromoc" id="idGrupoPromoc" maxlength="10" width="10" value="" class="InputB">
					</td>
				</tr>
				<tr  id="prom3" style="visibility:hidden; display:none">
					<td class="txtSumNormal">Tipo de Producto</td>
					<td colspan="2">
						<select name="idtipoproducto" id="idtipoproducto" class="InputB" disabled="disabled">
							<option value="D">D</option>
							<option value="E">E</option>
							<option value="G">G</option>
							<option value="M">M</option>
							<option value="S">S</option>
							<option value="T">T</option>
							<option value="X">X</option>
						</select>
					</td>
					<td class="txtSumNormal">idAreaPromoci&oacute;n</td>
					<td>
						<select name="idAreaPromoc" id="idAreaPromoc" class="InputB" disabled="disabled">
							<option value="0">Seleccione</option>
							<c:forEach  var="catalogoTO" items="${areasPromocion}"  varStatus="total">
								<option value="${catalogoTO.idVariable}">${catalogoTO.descripcion}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
			</table>
		</td>		
	</tr>
	<tr>
		<td width="10">
			<input type="radio" name="seleccion" id="FuerzasVentas" onclick="selec('FuerzasVentas')"/>
		</td>		
		<td class="healineblue1">Consulta de Fuerza de Ventas</td>
	</tr>
	<tr id="fza1" style="visibility:hidden; display:none">
		<td width="10"></td>
		<td>
			<table>
				<tr>
					<td class="txtSumNormal">FzaVta</td>
					<td>
						<input type="text" name="fzaVta" id="fzaVta" maxlength="10" width="15" value="" class="InputB">
					</td>
					<td width="10"></td>
					<td class="txtSumNormal">Estatus</td>
					<td>
						<select name="fzaVtaEstatus" id="fzaVtaEstatus" class="InputB" disabled="disabled">
							<option value="A">Activo</option>
							<option value="I">Inactivo</option>
						</select>
					</td>
				</tr>				
			</table>
		</td>
	</tr>
	<tr>
		<td width="10">
			<input type="radio" name="seleccion" id="promocionSms" onclick="selec('PromocionesSms')"/>
		</td>		
		<td class="healineblue1">Consulta de Promociones Sms</td>
	</tr>
	<tr id="promoSms" style="visibility:hidden; display:none">
		<td width="10"></td>
		<td>
			<table>
				<tr>
					<td class="txtSumNormal">Clave Sms</td>
					<td>
						<input type="text" name=clave_sms id="clave_sms" maxlength="10" width="12" value="" class="InputB">
					</td>
					<td class="txtSumNormal">Tipo Promoci&oacute;n</td>
					<td>						
						<input type="text" name="tipoPromoSms" id="tipoPromoSms" class="InputB" maxlength="2" size="3" value="">
					</td>
				</tr>	
			</table>
		</td>
	</tr>
	<!-- JSC Folio:111775 -->
	<tr>
		<td width="10">
			<input type="radio" name="seleccion" id="ClienteExcelente" onclick="selec('ClienteExcelentes')"/>
		</td>
		<td class="healineblue1">Consulta Cliente Excelente</td>
	</tr>
	<tr id="cteExcelentes" style="visibility:hidden; display:none">
		<td width="10"></td>
		<td>
			<table>
				<tr>
					<td class="txtSumNormal">Cuenta</td>
					<td>
						<input type="text" name="cuenta" id="cuenta" class="InputB" maxlength="10">
					</td>
					<td width="10"></td>
					<td class="txtSumNormal">Estatus</td>					
					<td>
						<select name="estatusCE" id="estatusCE" class="InputB" disabled="disabled">
							<option value="A">Activo</option>
							<option value="I">Inactivo</option>							
						</select>
					</td>
				</tr>
				<tr id="cteExcelentes2" style="visibility:hidden; display:none">
					<td class="txtSumNormal">Region</td>
					<td>
						<select name="regionCE" id="regionCE" class="InputB" disabled="disabled">
							<option value="1">1</option>
							<option value="2">2</option>
							<option value="3">3</option>
							<option value="4">4</option>
							<option value="5">5</option>
							<option value="6">6</option>
							<option value="7">7</option>
							<option value="8">8</option>
							<option value="9">9</option>
						</select>
					</td>
				</tr>
			</table>
		</td>
	</tr>
	
	<tr>
		<td width="10"></td>
		<td align="right">
			<table align="right"><tr>
				<td>
				<!-- Agregar tipos de producto JAPA 27/09/2011 Folio 96353 -->
				<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="159">					
					<a id="aExporta" class="LinkOut" style="width:30; text-align:center" onmouseover='this.className="LinkIn";' onmouseout='this.className="LinkOut";' id="btnArchivo" onClick="consulta(2);">&nbsp;EXPORTA&nbsp;&nbsp;</a>
				</securityCa:validaPerfil>

				</td>
				<td>

				<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="83">
				 	<a class="LinkOut" style="width:30; text-align:center" 		onmouseover='this.className="LinkIn";'
					onmouseout='this.className="LinkOut";' id="btnArchivo" onClick="consulta(1);">&nbsp;CONSULTA&nbsp;&nbsp;</a>	
				</securityCa:validaPerfil>

					</td>
				</tr>
			</table>
		<!-- 
			<a class="LinkOut" style="width:30; text-align:center" onmouseover='this.className="LinkIn";'
				onmouseout='this.className="LinkOut";' id="btnArchivo" onClick="exporta();">&nbsp;EXPORTAR&nbsp;&nbsp;</a>		
		 -->

		</td>
	</tr>
</table>	
	
	
 
 <DIV style="width: 100%; FILTER: progid:DXImageTransform.Microsoft.Shadow(color='#aaaacc', Direction=135, Strength=2); 
	border-bottom-width: 2px; border-bottom-style: solid; border-bottom-color: #4d7097; font-size: 1px;">
</DIV>

<div id="listadoGrupos" style="visibility:hidden; display:none" class="scroll">
	<table>
		<tr><td class="Cabecera2">Datos de los Grupos</td></tr>
	</table>
	<table id="tablaGrupos" border="1" cellspacing="0" cellpadding="0" width="90%">
		  <thead>
			<tr class="healineblue1" bgcolor="#ECF0DB" align="center">
				<td>&nbsp;Id Grupo&nbsp; </td>
				<td>&nbsp;Tipo Promoci&oacute;n&nbsp;</td>
				<td>&nbsp;Estatus&nbsp;</td>
				<td>&nbsp;Grupo Promoci&oacute;n&nbsp;</td>
				<td>&nbsp;Descuento&nbsp;</td>
				<td>&nbsp;Descuento Alto Valor&nbsp;<input type="hidden" name="idgrupo" id="idgrupo"></td>
			</tr>
		  </thead>
		  <tbody>
		  	<c:set var="contador" value="0"/>
		  	<c:forEach  var="gpopromoTO" items="${grupos}" varStatus="total">
		  		<c:set var="contador" value="${total.count}"/>
				<tr id="linea" align="center" class="X3" bgcolor="<c:if test="${contador %2 !=0 }">#D9EBF2</c:if>">
					<td>
						<a href="./actualizaCatalogo.do?idgrupo=${gpopromoTO.lineaLog}&accion=CONSULTA&tipo=GRUPO">${gpopromoTO.idGrupoPromocion}</a>
					</td>
					<td>${gpopromoTO.tipoPromocion}</td>
					<td>${gpopromoTO.estatus}</td>
					<td>${gpopromoTO.grupoPromocion}</td>
					<td>${gpopromoTO.descuento}</td>
					<td>${gpopromoTO.descuentoValorAlto}</td>
				</tr>
			</c:forEach>
		  </tbody>
	</table>
</div>
<div id="listadoPlanes" style="visibility:hidden; display:none" class="scroll">
<table>
	<tr><td class="Cabecera2">Datos de los Planes</td></tr>
</table>
<table id="tablaPlanes" border="1" cellspacing="0" cellpadding="0" width="90%">
	  <thead>
		<tr class="healineblue1" bgcolor="#ECF0DB" align="center">
			<td>&nbsp;Id Plan&nbsp; </td>
			<td>&nbsp;Segmento&nbsp; </td>
			<td>&nbsp;Regi&oacute;n&nbsp;</td>
			<td>&nbsp;Grupo Promoci&oacute;n&nbsp;</td>
			<td>&nbsp;Descripci&oacute;n&nbsp;</td>
			<td>&nbsp;Tecnologia&nbsp;</td>
			<td>&nbsp;BanMixto&nbsp;</td>
			<td>&nbsp;Modalidad&nbsp;</td>
			<td>&nbsp;BanSisact&nbsp;</td>
			<td>&nbsp;Addendum&nbsp;</td>
			<td>&nbsp;Renta&nbsp;</td>
			<td>&nbsp;BanRedencion&nbsp;</td>
			<td>&nbsp;Estatus&nbsp;</td>
			<td>&nbsp;BanRedencionAnct&nbsp;<input type="hidden" name="idplan" id="idplan"></td>
			<td>&nbsp;TipoPlan&nbsp;</td>
		</tr>
	  </thead>
	  <tbody>
	  	<c:set var="contador" value="0"/>
	  	<c:forEach  var="planTO" items="${planes}"  varStatus="total">
	  		<c:set var="contador" value="${total.count}"/>
			<tr id="linea" align="center" class="X3" bgcolor="<c:if test="${contador %2 !=0 }">#D9EBF2</c:if>">	  	
				<td>
					<a href="./actualizaCatalogo.do?idplan=${planTO.lineaLog}&accion=CONSULTA&tipo=PLAN">${planTO.idPlanNuevo}</a>
				</td>
				
				
				<td>${planTO.segmento}</td>
				<td>${planTO.idRegion}</td>
				<td>${planTO.idGrupoPromocion}</td>
				<td>${planTO.descripcion}</td>
				<td>${planTO.tecnologia}</td>
				<td>${planTO.banMixto}</td>
				<td>${planTO.modalidad}</td>
				<td>${planTO.banSisact}</td>
				<td>${planTO.adendum}</td>
				<td>${planTO.renta}</td>
				<td>${planTO.banRedencion}</td>
				<td>${planTO.estatus}</td>
				<td>${planTO.banRedencionAnct}</td>				
				<td>${planTO.tipoPlan}</td>
			</tr>
		</c:forEach>
	  </tbody>
</table>
</div>
<div id="listadoPromociones" style="visibility:hidden; display:none" class="scroll">
<table>
	<tr><td class="Cabecera2">Datos de las Promociones</td></tr>
</table>
<table id="tablaPromociones" border="1" cellspacing="0" cellpadding="0" width="90%">
	  <thead>
		<tr class="healineblue1" bgcolor="#ECF0DB" align="center">
			<td>&nbsp;Id Producto&nbsp; </td>
			<td>&nbsp;Regi&oacute;n&nbsp;</td>
			<td>&nbsp;Grupo Promoci&oacute;n&nbsp;</td>
			<td>&nbsp;Descripci&oacute;n&nbsp;</td>
			<td>&nbsp;Tipo Producto&nbsp;</td>
			<td>&nbsp;Precio Lista&nbsp;</td>
			<td>&nbsp;Precio Activaci&oacute;n&nbsp;</td>
			<td>&nbsp;Marca&nbsp;</td>
			<td>&nbsp;Modelo&nbsp;</td>
			<td>&nbsp;URL&nbsp;</td>
			<td>&nbsp;Tecnolog&iacute;a&nbsp;</td>
			<td>&nbsp;Estatus&nbsp;</td>
			<td>&nbsp;BanSISACT&nbsp;</td>
			<td>&nbsp;Addendum&nbsp;</td>
			<td>&nbsp;Fuerza Ventas&nbsp;</td>
			<td>&nbsp;Valor Puntos&nbsp;</td>
			<td>&nbsp;Indicador&nbsp;<input type="hidden" name="idpromo" id="idpromo"></td>
		</tr>
	  </thead>
	  <tbody>
	  <c:set var="contador" value="0"/>
	  	<c:forEach  var="promocionTO" items="${promociones}" varStatus="total">
	  		<c:set var="contador" value="${total.count}"/>
			<tr id="linea" align="center" class="X3" bgcolor="<c:if test="${contador %2 !=0 }">#D9EBF2</c:if>">
				<td>
					<a href="./actualizaCatalogo.do?idpromo=${promocionTO.lineaLog}&accion=CONSULTA&tipo=PROMO">${promocionTO.idProducto}</a>
				</td>
				<td>${promocionTO.idRegion}</td>
				<td>${promocionTO.idGrupoPromocion}</td>
				<td>${promocionTO.descripcion}</td>
				<td>${promocionTO.tipoProducto}</td>
				<td>${promocionTO.precioLista}</td>
				<td>${promocionTO.precioActiva}</td>
				<td>${promocionTO.marca}</td>
				<td>${promocionTO.modelo}</td>
				<td>${promocionTO.URL}</td>
				<td>${promocionTO.tecnologia}</td>
				<td>${promocionTO.estatus}</td>
				<td>${promocionTO.banSISACT}</td>
				<td>${promocionTO.addendum}</td>
				<td>${promocionTO.fzaVta}</td>
				<td>${promocionTO.valorPtos}</td>
				<td>${promocionTO.indicador}</td>
			</tr>
		</c:forEach>
	  </tbody>
</table>
</div>

<div id="listadoFuerzas" style="visibility:hidden; display:none" class="scroll">
<table>
	<tr><td class="Cabecera2">Datos de las Fuerzas de Ventas</td></tr>
</table>
<table id="tablaPromociones" border="1" cellspacing="0" cellpadding="0" width="90%">
	  <thead>
		<tr class="healineblue1" bgcolor="#ECF0DB" align="center">
			<td>&nbsp;IdFuerzaVenta&nbsp; </td>
			<td>&nbsp;PlanVisible&nbsp; </td>
			<td>&nbsp;Estatus&nbsp; </td>
			<td>&nbsp;Descripci&oacute;n&nbsp;</td>
		</tr>
	  </thead>
	  <tbody>
	  <c:set var="contador" value="0"/>
	  	<c:forEach  var="fuerzaTO" items="${fuerzas}" varStatus="total">
	  		<c:set var="contador" value="${total.count}"/>
			<tr id="linea" align="center" class="X3" bgcolor="<c:if test="${contador %2 !=0 }">#D9EBF2</c:if>">
				<td>
					<a href="./actualizaCatalogo.do?idFza=${fuerzaTO.idFuerzaVenta}&planVisible=${fuerzaTO.planVisible}&estatusFza=${fuerzaTO.estatus}&descripcion=${fuerzaTO.descripcion}&accion=CONSULTA&tipo=FZAVTA">${fuerzaTO.idFuerzaVenta}</a>
					
					
				</td>
				<td>${fuerzaTO.planVisible}</td>
				<td>${fuerzaTO.estatus}</td>
				<td>${fuerzaTO.descripcion}</td>				
			</tr>
		</c:forEach>
	  </tbody>
</table>
</div>
<div id="listadoPromocionesSms" style="visibility:hidden; display:none" class="scroll">
	<table>
		<tr><td class="Cabecera2">Datos de las Promociones Sms</td></tr>
	</table>
	<table id="tablapromocionesSms" border="1" cellspacing="0" cellpadding="0" width="90%">
		  <thead>
			<tr class="healineblue1" bgcolor="#ECF0DB" align="center">
				<td>&nbsp;Clave M2K&nbsp; </td>
				<td>&nbsp;Clave SMS&nbsp; </td>
				<td>&nbsp;Clave Promoci&oacute;n&nbsp;</td>
				<td>&nbsp;Descripci&oacute;n&nbsp;</td>
				<td>&nbsp;Tipo&nbsp;</td>
				<td>&nbsp;Valor Puntos&nbsp;</td>
			</tr>
		  </thead>
		  <tbody>
	  <c:set var="contador" value="0"/>
	  	<c:forEach  var="productosSmsTO" items="${promocionesSms}" varStatus="total">
	  		<c:set var="contador" value="${total.count}"/>
			<tr id="linea" align="center" class="X3" bgcolor="<c:if test="${contador %2 !=0 }">#D9EBF2</c:if>">
				<td>
					<a href="./actualizaCatalogo.do?claveM2k=${productosSmsTO.claveM2k}&claveSms=${productosSmsTO.claveSms}&idProducto=${productosSmsTO.idProducto}&descripcion=${productosSmsTO.descripcion}&tipoProducto=${productosSmsTO.tipoProducto}&valorPuntos=${productosSmsTO.valorPuntos}&accion=CONSULTA&tipo=PROMOSMS">${productosSmsTO.claveM2k}</a>
				</td>
				<td>${productosSmsTO.claveSms}</td>
				<td>${productosSmsTO.idProducto}</td>
				<td>${productosSmsTO.descripcion}</td>				
				<td>${productosSmsTO.tipoProducto}</td>			
				<td>${productosSmsTO.valorPuntos}</td>			
			</tr>
		</c:forEach>
	  </tbody>
	</table>
</div>
<!-- JSC Folio:111775 -->
<div id="listadoCteExcelente" style="visibility:hidden; display:none" class="scroll">
<table>
		<tr><td class="Cabecera2">Datos de las líneas Cliente Excelente</td></tr>
	</table>
	<table id="tablacteExcelente" border="1" cellspacing="0" cellpadding="0" width="90%">
		  <thead>
			<tr class="healineblue1" bgcolor="#ECF0DB" align="center">
				<td>&nbsp;Cuenta&nbsp; </td>
				<td>&nbsp;L&iacute;nea&nbsp; </td>
				<td>&nbsp;Regi&oacute;n&nbsp;</td>
				<td>&nbsp;Estatus&nbsp;</td>
			</tr>
		  </thead>
		  <tbody>
	  <c:set var="contador" value="0"/>
	  	<c:forEach  var="lineasCteExcTO" items="${cteExcelente}" varStatus="total">
	  		<c:set var="contador" value="${total.count}"/>
			<tr id="cuenta" align="center" class="X3" bgcolor="<c:if test="${contador %2 !=0 }">#D9EBF2</c:if>">
				<td>
					<a href="./actualizaCatalogo.do?cuenta=${lineasCteExcTO.cuenta}&linea=${lineasCteExcTO.linea}&idRegion=${lineasCteExcTO.idRegion}&estatus=${lineasCteExcTO.estatus}&accion=CONSULTA&tipo=CTEEXC">${lineasCteExcTO.cuenta}</a>
				</td>				
				<td>${lineasCteExcTO.linea}</td>
				<td>${lineasCteExcTO.idRegion}</td>				
				<td>${lineasCteExcTO.estatus}</td>			
			</tr>
		</c:forEach>
	  </tbody>
	</table>
</div>


<script type="text/javascript" language="javascript">
 function lectura(){
 	<c:set var="accion" value="${accion}" /> 	
 	<c:if test="${promociones!=null}">	
		document.getElementById("listadoPromociones").style.display = "block";
		document.getElementById("listadoPromociones").style.visibility="visible";
	</c:if>	
	<c:if test="${planes!=null}">	
		document.getElementById("listadoPlanes").style.display = "block";
		document.getElementById("listadoPlanes").style.visibility="visible";
	</c:if>
	<c:if test="${grupos!=null}">	
		document.getElementById("listadoGrupos").style.display = "block";
		document.getElementById("listadoGrupos").style.visibility="visible";
	</c:if>
	<c:if test="${fuerzas!=null}">	
		document.getElementById("listadoFuerzas").style.display = "block";
		document.getElementById("listadoFuerzas").style.visibility="visible";
	</c:if>
	<c:if test="${promocionesSms!=null}">
		document.getElementById("listadoPromocionesSms").style.display = "block";
		document.getElementById("listadoPromocionesSms").style.visibility="visible";
	</c:if>
	<c:if test="${cteExcelente!=null}">
		document.getElementById("listadoCteExcelente").style.display = "block";
		document.getElementById("listadoCteExcelente").style.visibility="visible";
	</c:if>		
 }
</script>
</form>
</body>
</html>