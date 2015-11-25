<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt'%>     
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix='f' uri="http://java.sun.com/jstl/fmt_rt"%>
<%@ taglib prefix="spring" uri="/spring"%>
<%@ taglib uri="/WEB-INF/permisos.tld" prefix="securityCa"%>

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
	
	function actualizaPlan(){
		var idPlan = document.getElementById("idPlan");
		var descripcion = document.getElementById("descripcion");
		var idRegion = document.getElementById("idRegion");
		var tecnologia = document.getElementById("tecnologia");
		var bMixto = document.getElementById("bMixto");
		var status = document.getElementById("status");
		var modalidad = document.getElementById("modalidad");
		var bSisact = document.getElementById("bSisact");
		var renta = document.getElementById("renta");
		var adendum = document.getElementById("adendum");
		var segmento = document.getElementById("segmento");
		var idGpoPromo = document.getElementById("idGpoPromo");
		var bRedencion = document.getElementById("bRedencion");
		var bRedenAnt = document.getElementById("bRedenAnt");
		var tipoPlan = document.getElementById("tipoPlan");
		
		if(idPlan == null || idPlan.value==""){
			alert("Introduzca un idPlan.");
			return false;
		}
		if(descripcion == null || descripcion.value==""){
			alert("Introduzca una descripción.");
			return false;
		}
		if(idRegion == null || idRegion.value==""){
			alert("Seleccione una región.");
			return false;
		}
		if(tecnologia == null || tecnologia.value==""){
			alert("Introduzca una tecnología.");
			return false;
		}
		if(bMixto == null || bMixto.value==""){
			alert("Introduzca un bMixto.");
			return false;
		}
		if(status == null || status.value==""){
			alert("Introduzca una estatus.");
			return false;
		}
		if(modalidad == null || modalidad.value==""){
			alert("Introduzca una modalidad.");
			return false;
		}
		if(bSisact == null || bSisact.value==""){
			alert("Introduzca el campo bSisact.");
			return false;
		}
		if(renta == null || renta.value==""){
			alert("Introduzca una renta.");
			return false;
		}
		if(adendum == null || adendum.value==""){
			alert("Seleccione un adendum");
			return false;
		}
		if(segmento == null || segmento.value==""){
			alert("Introduzca un idSegmento");
			return false;
		}
		if(idGpoPromo == null || idGpoPromo.value==""){
			alert("Seleccione un idGrupo de Promoción");
			return false;
		}
		if(bRedencion == null || bRedencion.value==""){
			alert("Introduzca una bRedención");
			return false;
		}
		if(bRedenAnt == null || bRedenAnt.value==""){
			alert("Introduzca una bRedención Anterior");
			return false;
		}
		if(tipoPlan == null || tipoPlan.value==""){
			alert("Introduzca un tipoPlan");
			return false;
		}
		
		var form = document.getElementById('frmCatalogo');
		form.action = './actualizaCatalogo.do';
		form.submit();
	}

	function actualizaGposPromocion(){
	
		var idGrupo = document.getElementById("idGrupo");
		var tipoPromo = document.getElementById("tipoPromo");
		var estatus = document.getElementById("estatus");
		var gpoPromo = document.getElementById("gpoPromo");
		var descuento = document.getElementById("descuento");
		var descAlto = document.getElementById("descAlto");
		
		if(idGrupo == null || idGrupo.value==""){
			alert("Introduzca un id de Grupo");
			return false;
		}
		if(tipoPromo == null || tipoPromo.value==""){
			alert("Introduzca un tipo de promoción");
			return false;
		}
		if(estatus == null || estatus.value==""){
			alert("Introduzca un estatus");
			return false;
		}
		if(gpoPromo == null || gpoPromo.value==""){
			alert("Introduzca un grupo de promoción");
			return false;
		}
		if(descuento == null || descuento.value==""){
			alert("Introduzca un descuento");
			return false;
		}
		if(descAlto == null || descAlto.value==""){
			alert("Introduzca un descuento Alto Valor");
			return false;
		}
	
		var form = document.getElementById('frmCatalogo');
		form.action = './actualizaCatalogo.do';
		form.submit();
	}
	function actualizaPromocion(){
		
		var idProd = document.getElementById("idProd");
		var descripcion = document.getElementById("descripcion");
		var idGrupo = document.getElementById("idGrupo");
      	var idRegion = document.getElementById("idRegion");
      	var tecnologia = document.getElementById("tecnologia");
      	var tipoprod = document.getElementById("tipoprod");
      	var estatus = document.getElementById("estatus");
      	var plista = document.getElementById("plista");
      	var bSisact = document.getElementById("bSisact");
      	var pActiva = document.getElementById("pActiva");
      	var adendum = document.getElementById("adendum");
      	var marca = document.getElementById("marca");
      	var modelo = document.getElementById("modelo");
      	var url = document.getElementById("url");
      	var fzavta = document.getElementById("fzavta");
      	var valorPts = document.getElementById("valorPts");
      	var indicador = document.getElementById("indicador");
      	
		var form = document.getElementById('frmCatalogo');
		form.action = './actualizaCatalogo.do';
		form.submit();
	}
	
	function actualizaFzaVentas(){
		var form = document.getElementById('frmCatalogo');
		form.action = './actualizaCatalogo.do';
		form.submit(); 
	}
	
	function actualizapromocionSms(){
		var form = document.getElementById('frmCatalogo');
		form.action = './actualizaCatalogo.do';
		form.submit(); 
	}
	function actualizaCteExcelente(){	
		var form = document.getElementById('frmCatalogo');
		form.action = './actualizaCatalogo.do';
		form.submit(); 
	}
</script>
</head>
<body>
<form action="./actualizaCatalogo.do" method="post" name="frmCatalogo" id="frmCatalogo" AUTOCOMPLETE="OFF">
<input type="hidden" name="accion" id="accion" value="${tipo}"/>
<input type="hidden" name="tipo" id="tipo" />
<table width="100%">
	<tr>
		<td height="30" class="subtitulo">Puntitos: Actualizaci&oacute;n de Grupos, Planes y Promociones</td>
	</tr>
</table>
<table width="90%">
	<c:if test="${grupo!=null}">
		<tr>
			<td class="healineblue1" colspan="6">Grupos de Promoción</td>
		</tr>
		<tr class="healineblue1">
				<td>&nbsp;Id Grupo&nbsp; </td>
				<td>
					<input type="text" name="idGrupo" id="idGrupo" readonly="readonly" maxlength="38" size="20" value="${grupo.idGrupoPromocion}">
				</td>
				<td>&nbsp;Tipo Promoci&oacute;n&nbsp;</td>
				<td>
					<input type="text" name="tipoPromo" id="tipoPromo" maxlength="2" size="4" value="${grupo.tipoPromocion}">
				</td>
				<td>&nbsp;Estatus&nbsp;</td>
				<td>
					<select name="estatus" id="estatus">
						<c:if test="${grupo.estatus=='A'}">
							<option value="A" selected="selected">Activo</option>
							<option value="I">Inactivo</option>
						</c:if>
						<c:if test="${grupo.estatus=='I'}">
							<option value="A">Activo</option>
							<option value="I" selected="selected">Inactivo</option>
						</c:if>
					</select>
				</td>
		</tr>
		<tr class="healineblue1">
				<td>&nbsp;Grupo Promoci&oacute;n&nbsp;</td>
				<td>
					<input type="text" name="gpoPromo" id="gpoPromo" maxlength="30" size="20" value="${grupo.grupoPromocion}">
				</td>
				<td>&nbsp;Descuento ROEXT&nbsp;</td>
				<td>
					$<input type="text" name="descuento" id="descuento" maxlength="19" size="18" value="${grupo.descuento}">
				</td>
				<td>&nbsp;Descuento Alto Valor&nbsp;</td>
				<td>
					<select name="descAlto" id="descAlto">
						<c:if test="${grupo.descuentoValorAlto==0}">
							<option value="0" selected="selected">0</option>
							<option value="1">1</option>
						</c:if>
						<c:if test="${grupo.descuentoValorAlto==1}">
							<option value="0">0</option>
							<option value="1" selected="selected">1</option>
						</c:if>
					</select>
				</td>
		</tr>
		<tr class="healineblue1" align="center">
			<td colspan="6" align="right">
				<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="84">
					<a class="LinkOut" style="width:30; text-align:center" onmouseover='this.className="LinkIn";'
						onmouseout='this.className="LinkOut";' id="btnActual" onClick="actualizaGposPromocion();">&nbsp;ACTUALIZAR&nbsp;&nbsp;</a>
				</securityCa:validaPerfil>
			</td>
		</tr>
		
	</c:if>
	<c:if test="${plan!=null}">
		<tr>
			<td class="healineblue1" colspan="6">Planes</td>
		</tr>
		<tr class="healineblue1">
			<td>&nbsp;Id Plan&nbsp; </td>
			<td>
				<input type="text" name="idPlan" id="idPlan" readonly="readonly" maxlength="5" size="6" value="${plan.idPlanNuevo}">
			</td>
			<td>&nbsp;Descripci&oacute;n&nbsp;</td>
			<td colspan="3">
				<input type="text" name="descripcion" id="descripcion" maxlength="30" size="40" value="${plan.descripcion}">
			</td>
		</tr>
		<tr class="healineblue1">
			<td>&nbsp;Id Regi&oacute;n&nbsp;</td>
			<td>
				<input type="text" name="idRegion" id="idRegion" value="${plan.idRegion}" readonly="readonly">
			</td>
			<td>&nbsp;Tecnolog&iacute;a&nbsp;</td>
			<td>
				<input type="text" name="tecnologia" id="tecnologia" maxlength="10" size="10" value="${plan.tecnologia}">
			</td>
			<td>&nbsp;BMixto&nbsp;</td>
			<td>
				<select name="bMixto" id="bMixto">
					<c:if test="${plan.banMixto=='S'}">
						<option value="S" selected="selected">S</option>					
		      			<option value="N">N</option>
					</c:if>
					<c:if test="${plan.banMixto!='S'}">
						<option value="S" >S</option>
						<option value="N" selected="selected">N</option>						
					</c:if>
				</select>
			</td>
		</tr>
		<tr class="healineblue1">
			<td>&nbsp;Estatus&nbsp;</td>
			<td>
				<select name="status" id="status">
					<c:if test="${plan.estatus=='A'}">
						<option value="A" selected="selected">Activo</option>					
		      			<option value="I">Inactivo</option>
					</c:if>
					<c:if test="${plan.estatus!='A'}">
						<option value="A" >Activo</option>
						<option value="I" selected="selected">Inactivo</option>						
					</c:if>
				</select>
			</td>
			<td>&nbsp;Modalidad&nbsp;</td>
			<td>
				<input type="text" name="modalidad" id="modalidad" maxlength="10" size="10" value="${plan.modalidad}">
			</td>
			<td>&nbsp;BSisact&nbsp;</td>
			<td>
				<select name="bSisact" id="bSisact">
					<c:if test="${plan.banSisact=='S'}">
						<option value="S" selected="selected">S</option>					
		      			<option value="N">N</option>
					</c:if>
					<c:if test="${plan.banSisact!='S'}">
						<option value="S" >S</option>
						<option value="N" selected="selected">N</option>						
					</c:if>
				</select>
			</td>
		</tr>
		<tr class="healineblue1">
			<td>&nbsp;Renta&nbsp;</td>
			<td>
				<input type="text" name="renta" id="renta" maxlength="38" size="20" value="${plan.renta}">
			</td>
			<td>&nbsp;Adendum&nbsp;</td>
			<td>
				<input type="text" id="adendum" name="adendum" value="${plan.adendum}">				
			</td>
			<td>&nbsp;Id Segmento&nbsp;</td>
			<td>
				<input type="text" name="segmento" id="segmento" readonly="readonly" maxlength="38" size="10" value="${plan.segmento}">
			</td>
		</tr>
		<tr class="healineblue1">
		    <td>&nbsp;Id Grupo Promoci&oacute;n&nbsp;</td>
			<td colspan="4">
				<input type="text" name="idGpoPromo" id="idGpoPromo" maxlength="8" size="10" value="${plan.idGrupoPromocion}">				
			</td>
		</tr>
		<tr class="healineblue1">
			<td>&nbsp;BRedenci&oacute;n&nbsp;</td>
			<td>
				<select name="bRedencion" id="bRedencion">
					<c:if test="${plan.banRedencion!=1}">
						<option value="0" selected="selected">0</option>					
		      			<option value="1">1</option>
					</c:if>
					<c:if test="${plan.banRedencion==1}">
						<option value="0" >0</option>
						<option value="1" selected="selected">1</option>						
					</c:if>
				</select>
			</td>
			<td>&nbsp;B Redenci&oacute;n Ant&nbsp;</td>
			<td>
				<select name="bRedenAnt" id="bRedenAnt">
					<c:if test="${plan.banRedencionAnct!=1}">
						<option value="0" selected="selected">0</option>					
		      			<option value="1">1</option>
					</c:if>
					<c:if test="${plan.banRedencionAnct==1}">
						<option value="0" >0</option>
						<option value="1" selected="selected">1</option>						
					</c:if>		      		
				</select>
			</td>
			<td>&nbsp;TipoPlan&nbsp;</td>
			<td>
				<input type="text" name="tipoPlan" id="tipoPlan" maxlength="10" size="12" value="${plan.tipoPlan}">				
			</td>
		</tr>
		<tr class="healineblue1" align="center">
			<td colspan="6" align="right">
				<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="84">
					<a class="LinkOut" style="width:30; text-align:center" onmouseover='this.className="LinkIn";'
						onmouseout='this.className="LinkOut";' id="btnActual" onClick="actualizaPlan();">&nbsp;ACTUALIZAR&nbsp;&nbsp;</a>
				</securityCa:validaPerfil>
			</td>
		</tr>
		
	</c:if>
	<c:if test="${promocion!=null}">
		<tr>
			<td class="healineblue1" colspan="6">Promoci&oacute;n</td>
		</tr>
		<tr class="healineblue1">
			<td>&nbsp;Id Producto&nbsp; </td>
			<td>
				<input type="text" name="idProd" id="idProd" readonly="readonly" maxlength="20" size="20" value="${promocion.idProducto}">
			</td>
			<td>&nbsp;Descripci&oacute;n&nbsp;</td>
			<td colspan="3">
				<input type="text" name="descripcion" id="descripcion" maxlength="180" size="50" value="${promocion.descripcion}">
			</td>
		</tr>
		<tr class="healineblue1">
			<td>&nbsp;Id Grupo Promocion&nbsp;</td>
			<td colspan="4">
				<input type="text" name="idGrupo" id="idGrupo" value="${promocion.idGrupoPromocion}" readonly="readonly">
			</td>
		</tr>
		<tr class="healineblue1">
			<td>&nbsp;Id Regi&oacute;n&nbsp;</td>
			<td>
				<input type="text" name="idRegion" id="idRegion" value="${promocion.idRegion}" readonly="readonly">
			</td>
			<td>&nbsp;Tecnolog&iacute;a&nbsp;</td>
			<td>
				<input type="text" name="tecnologia" id="tecnologia" maxlength="30" size="20" value="${promocion.tecnologia}">
			</td>
			<td>&nbsp;Tipo Producto&nbsp;</td>
			<td>
				<select name="tipoprod" id="tipoprod">
					<option value="${promocion.tipoProducto}" selected="selected">${promocion.tipoProducto}</option>
					<option value="E">E</option>
					<option value="G">G</option>
					<option value="M">M</option>
					<option value="S">S</option>
					<option value="T">T</option>
					<option value="X">X</option>
				</select>
			</td>
		</tr>
		<tr class="healineblue1">
			<td>&nbsp;Estatus&nbsp;</td>
			<td>
				<select name="estatus" id="estatus">
					<c:if test="${promocion.estatus=='A'}">
						<option value="A" selected="selected">Activo</option>					
		      			<option value="I">Inactivo</option>
					</c:if>
					<c:if test="${promocion.estatus=='I'}">
						<option value="A" >Activo</option>
						<option value="I" selected="selected">Inactivo</option>						
					</c:if>		      		
				</select>
			</td>
			<td>&nbsp;Precio Lista&nbsp;</td>
			<td>
				<input type="text" name="plista" id="plista" maxlength="30" size="20" value="${promocion.precioLista}">
			</td>
			<td>&nbsp;BSisact&nbsp;</td>
			<td>
				<input type="text" name="bSisact" id="bSisact" maxlength="30" size="20" value="${promocion.banSISACT}">
			</td>
		</tr>
		<tr class="healineblue1">
			<td>&nbsp;Precio Activa&oacute;n&nbsp;</td>
			<td>
				<input type="text" name="pActiva" id="pActiva" maxlength="30" size="20" value="${promocion.precioActiva}">
			</td>
			<td>&nbsp;Adendum&nbsp;</td>
			<td>
				<input type="text" name="adendum" id="adendum" value="${promocion.addendum}" readonly="readonly">
			</td>
			<td>&nbsp;Marca&nbsp;</td>
			<td>
				<input type="text" name="marca" id="marca" maxlength="30" size="20" value="${promocion.marca}">
			</td>
		</tr>
		<tr class="healineblue1">
			<td>&nbsp;Modelo&nbsp;</td>
			<td>
				<input type="text" name="modelo" id="modelo" maxlength="30" size="20" value="${promocion.modelo}">
			</td>
			<td>&nbsp;URL&nbsp;</td>
			<td colspan="3">
				<input type="text" name="url" id="url" maxlength="40" size="60" value="${promocion.URL}">
			</td>
		</tr>
		<tr class="healineblue1">
			<td>&nbsp;Fuerza de Ventas&nbsp;</td>
			<td>
				<input type="text" name="fzavta" id="fzavta" maxlength="30" size="20" value="${promocion.fzaVta}" readonly="readonly">
			</td>
			<td>&nbsp;Valor Puntos&nbsp;</td>
			<td>
				<input type="text" name="valorPts" id="valorPts" maxlength="30" size="20" value="${promocion.valorPtos}">
			</td>
			<td>&nbsp;Indicador&nbsp;</td>
			<td>
				<input type="text" name="indicador" id="indicador" maxlength="30" size="20" value="${promocion.indicador}" readonly="readonly">
			</td>
		</tr>
		<tr class="healineblue1" align="center">
			<td colspan="6" align="right">
				<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="84">
					<a class="LinkOut" style="width:30; text-align:center" onmouseover='this.className="LinkIn";'
						onmouseout='this.className="LinkOut";' id="btnActual" onClick="actualizaPromocion();">&nbsp;ACTUALIZAR&nbsp;&nbsp;</a>
				</securityCa:validaPerfil>
			</td>
		</tr>
	</c:if>
	<c:if test="${fuerzaVentas!=null}">
		<tr>
			<td class="healineblue1" colspan="6">Fuerzas de Ventas</td>
		</tr>
		<tr class="healineblue1">
				<td>&nbsp;Id Fuerza Ventas&nbsp; </td>
				<td>
					<input type="text" name="idFzaVenta" id="idFzaVenta" readonly="readonly" maxlength="10" size="15" value="${fuerzaVentas.idFuerzaVenta}">
				</td>
				<td>&nbsp;Plan Visible&nbsp;</td>
				<td>
					<input type="text" name="planVisible" id="planVisible" readonly="readonly" maxlength="10" size="15" value="${fuerzaVentas.planVisible}">
				</td>
				<td>&nbsp;Estatus&nbsp;</td>
				<td>
					<select name="estatus" id="estatus">
						<c:if test="${fuerzaVentas.estatus=='A'}">
							<option value="A" selected="selected">Activo</option>
							<option value="I">Inactivo</option>
						</c:if>
						<c:if test="${fuerzaVentas.estatus=='I'}">
							<option value="A">Activo</option>
							<option value="I" selected="selected">Inactivo</option>
						</c:if>
					</select>
				</td>
		</tr>
		<tr class="healineblue1">
				<td>&nbsp;Descripci&oacute;n&nbsp;</td>
				<td colspan="5">
					<input type="text" name="fzaDescripcion" id="fzaDescripcion" maxlength="30" size="20" value="${fuerzaVentas.descripcion}">
				</td>				
		</tr>
		<tr class="healineblue1" align="center">
			<td colspan="6" align="right">
				<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="84">
					<a class="LinkOut" style="width:30; text-align:center" onmouseover='this.className="LinkIn";'
						onmouseout='this.className="LinkOut";' id="btnActual" onClick="actualizaFzaVentas();">&nbsp;ACTUALIZAR&nbsp;&nbsp;</a>
				</securityCa:validaPerfil>
			</td>
		</tr>
		
	</c:if>

	<c:if test="${promocionsms!=null}">
			<tr>
				<td class="healineblue1" colspan="6">Promociones SMS</td>
			</tr>
			<tr class="healineblue1">
					<td>&nbsp;Clave M2K&nbsp; </td>
					<td>
						<input type="text" name="claveM2k" id="claveM2k" readonly="readonly" maxlength="10" size="15" value="${promocionsms.claveM2k}">
					</td>
					<td>&nbsp;Clave Sms&nbsp;</td>
					<td>
						<input type="text" name="claveSms" id="claveSms" readonly="readonly" maxlength="10" size="15" value="${promocionsms.claveSms}">
					</td>
					<td>&nbsp;Clave Promoci&oacute;n&nbsp;</td>
					<td>
						<input type="text" name="claveIdProducto" id="claveIdProducto" maxlength="10" size="15" value="${promocionsms.idProducto}">
					</td>
			</tr>
			
			<tr class="healineblue1">
					<td>&nbsp;Tipo&nbsp; </td>
					<td>
						<input type="text" name="tipoProducto" id="tipoProducto"  maxlength="2" size="8" value="${promocionsms.tipoProducto}">
					</td>
					<td>&nbsp;Valor Puntos&nbsp;</td>
					<td>
						<input type="text" name="valorPuntos" id="valorPuntos" maxlength="10" size="15" value="${promocionsms.valorPuntos}">
					</td>
			</tr>
			<tr class="healineblue1">
					<td>&nbsp;Descripci&oacute;n&nbsp;</td>
					<td colspan="5">
						<input type="text" name="promoSmsDescripcion" id="promoSmsDescripcion" maxlength="30" size="30" value="${promocionsms.descripcion}">
					</td>				
			</tr>
			<tr class="healineblue1" align="center">
				<td colspan="6" align="right">
					<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="84">
						<a class="LinkOut" style="width:30; text-align:center" onmouseover='this.className="LinkIn";'
							onmouseout='this.className="LinkOut";' id="btnActual" onClick="actualizapromocionSms();">&nbsp;ACTUALIZAR&nbsp;&nbsp;</a>
					</securityCa:validaPerfil>
				</td>
			</tr>
			
		</c:if>	
		
		<c:if test="${ctesExcelentes!=null}">
			<tr>
				<td class="healineblue1" colspan="6">Cliente Excelente</td>
			</tr>
			<tr class="healineblue1">
				<td>&nbsp;Cuenta&nbsp; </td>
				<td>
					<input type="text" name="cuenta" id="cuenta" readonly="readonly" maxlength="10" size="15" value="${ctesExcelentes.cuenta}">
				</td>
				<td>&nbsp;L&iacute;nea&nbsp; </td>
				<td>
					<input type="text" name="linea" id="linea" maxlength="10" size="15" value="${ctesExcelentes.linea}">
				</td>
			</tr>
			<tr class="healineblue1">	
				<td>&nbsp;Regi&oacute;n&nbsp;</td>
				<td>
					<input type="text" name="region" id="region" value="${ctesExcelentes.idRegion}" readonly="readonly">
				</td>
				<td>&nbsp;Estatus&nbsp;</td>
				<td>
					<select name="estatus" id="estatus">
						<c:if test="${ctesExcelentes.estatus=='A'}">
							<option value="A" selected="selected">Activo</option>
							<option value="I">Inactivo</option>
						</c:if>
						<c:if test="${ctesExcelentes.estatus=='I'}">
							<option value="A">Activo</option>
							<option value="I" selected="selected">Inactivo</option>
						</c:if>
					</select>
				</td>
				<tr class="healineblue1" align="center">
				<td colspan="6" align="right">
					<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="84">
						<a class="LinkOut" style="width:30; text-align:center" onmouseover='this.className="LinkIn";'
							onmouseout='this.className="LinkOut";' id="btnActual" onClick="actualizaCteExcelente();">&nbsp;ACTUALIZAR&nbsp;&nbsp;</a>
					</securityCa:validaPerfil>
				</td>
				</tr>	
		</c:if>
		
</table>
</form>
</body>
</html>