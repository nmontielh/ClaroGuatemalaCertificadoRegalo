<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/permisos.tld" prefix="securityCa" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%--
Multicotizador II JAPA 28/03/2012  Folio 84048 y ZZZZZZ : 
Se elimina el div que muestra campos para TarjetaRedencionesCon y TarjetaCareg
--%>
<link rel="stylesheet" href='<c:url value="/commons/js/sc_textsheet.css"/>' type="text/css">

<script type="text/javascript" src='<c:url value="/commons/js/jquery-1.2.3.pack.js"/>'></script>
<script type='text/javascript' src='<c:url value="/commons/js/jquery.form.js"/>' ></script>
<script type="text/javascript" src='<c:url value="/commons/js/jquery-impromptu.1.3.js"/>' ></script>


<!--<script src='<c:url value="/commons/js/validacionesRedencion.js"/>' type="text/javascript"></script>-->
<script src='<c:url value="/commons/js/validacionesRedencionMulticotizador.js"/>' type="text/javascript"></script>
<script type="text/javascript">

function asignaValor(){

	var ptosDisp = document.parentWindow.parent.document.getElementById("ptosDisp").value;	
	var msg = "<font color='red'>&nbsp;"+ ptosDisp +"</font>"
	document.getElementById("puntosDisponibles").innerHTML						= msg;
	document.getElementById("ptsExactos").value									= ptosDisp;
	document.parentWindow.parent.document.getElementById("ptsTotales").value	= ptosDisp;
	document.getElementById("nMesActual").value									= '${nMesActual}';
	document.parentWindow.parent.document.getElementById("gpoPromo").value		= '${idGrupoPromocion}';	
	document.parentWindow.parent.document.getElementById("porcentajeIva").value = '${porcentajeIva}';
	document.parentWindow.parent.document.getElementById("defineIva").value		= '${defineIva}';
	document.parentWindow.parent.document.getElementById("formaRedencion").value= '${formaRedencion}';
	document.parentWindow.parent.document.getElementById("tipoRedencion").value = '${tipoRedencion}';
	document.parentWindow.parent.document.getElementById("bContinuar").value	= '${btnContinuar}';	
	document.getElementById("TarjetaTipoRed").style.visibility					= "visible";
	document.getElementById("TarjetaTipoRed").style.display						= "block";
	/* Ocultar o mostrar boton de beneficios solo cuando se tengan JAPA 30/10/2012  Folio 105592 */
	document.getElementById("tdBuscaBeneficio").style.visibility	= "hidden";
	document.getElementById("tdBuscaBeneficio").style.display		= "none";
	
/*
var a = document.parentWindow.parent.document.getElementById("gpoPromo").value;
//var b = document.parentWindow.parent.document.getElementById("adendumNvo").value;
var c = document.parentWindow.parent.document.getElementById("porcentajeIva").value;
var d = document.parentWindow.parent.document.getElementById("defineIva").value;
var e = document.parentWindow.parent.document.getElementById("ptsTotales").value;
var f = document.parentWindow.parent.document.getElementById("formaRedencion").value;
var g = document.parentWindow.parent.document.getElementById("tipoRedencion").value;
var h = document.parentWindow.parent.document.getElementById("bContinuar").value;
alert(a+","+c+","+d+","+e+","+f+","+g+","+h);
*/

	if(document.parentWindow.parent.document.getElementById("bContinuar").value=="1"){
		var tipoReden = document.parentWindow.parent.document.getElementById("tipoRedencion").value;
		
		if(tipoReden=='CAREG'){
			document.getElementById("AdendumCAREG").style.visibility="visible";
			document.getElementById("AdendumCAREG").style.display="block";
		}			
		if(tipoReden=='SIN' || tipoReden=='ACA' || tipoReden=='T3G'){
			consultaMarcas();
		}
	}
}


/*
* Método que realiza la validación del plan y 
* obtiene las marcas asociadas al grupo de promoción
*/
function consultaMarcas(){	
	document.getElementById("modelo").value								= "";

	if(document.getElementById('ctlPlanes1') != null && 
		document.getElementById("ctlPlanes1").value == 0) {

		document.getElementById("TarjetaMarcas").style.visibility		= "hidden";
		document.getElementById("TarjetaMarcas").style.display			= "none";	
		document.getElementById("TarjetaTipoConsulta").style.visibility = "hidden";
		document.getElementById("TarjetaTipoConsulta").style.display	= "none";
		document.getElementById("TarjetaPuntos").style.visibility		= "hidden";
		document.getElementById("TarjetaPuntos").style.display			= "none";
		document.getElementById("TarjetaIva").style.visibility			= "hidden";
		document.getElementById("TarjetaIva").style.display				= "none";
		document.getElementById("TarjetaProductos").style.visibility	= "hidden";			
		document.getElementById("TarjetaProductos").style.display		= "none";

		alert("Debe eligir un Plan para consultar.");
		return false;
	} else {
		var idPlan		= "";
		var gpoPromo	= document.parentWindow.parent.document.getElementById("gpoPromo").value;
		var adendumNvo	= document.parentWindow.parent.document.getElementById("adendumNvo").value;
		var banRedAct	= document.getElementById("banRedencionAnct").value;
		var mesesCR		= document.getElementById("MesesCR").value;
		var adendumCR	= document.getElementById("AdendumCR").value;
		var adendum		= document.parentWindow.parent.document.getElementById("adendum").value;
		var fecAddM2K	= document.parentWindow.parent.document.getElementById("fecAddM2K").value;
		var tipoReden	= document.parentWindow.parent.document.getElementById("tipoRedencion").value;
		var tipoRedenS	= '${tipoRedencionS}';
		var tipoPromocion= "";
		var nRegionTel 	= document.parentWindow.parent.document.getElementById("nRegion").value;

		//Redencion de tipo diferente de SIN
		if(document.getElementById('ctlPlanes1') != null) {
			//document.parentWindow.parent.document.getElementById("planNuevo").value	= "";
			//document.parentWindow.parent.document.getElementById("gpoPromo").value	= 0;

			var planes		= document.getElementById("ctlPlanes1").value;
			idPlan			= planes.split("|")[0];
			gpoPromo		= planes.split("|")[1];
			adendumNvo		= planes.split("|")[2];
			banRedAct		= planes.split("|")[4];
			tipoPromocion	= planes.split("|")[5];
				
			document.parentWindow.parent.document.getElementById("planNuevo").value = idPlan;
			document.getElementById("buscaPlan").value								= idPlan;
			document.parentWindow.parent.document.getElementById("gpoPromo").value	= gpoPromo;
			document.parentWindow.parent.document.getElementById("adendumNvo").value = adendumNvo;
			document.getElementById("banRedencionAnct").value						= banRedAct;

			//Realiza las validaciones para cuando es una redención de tipo CAREG
			if(!validaCAREG()) {
				document.getElementById("ctlPlanes1").options[0].selected = true;	
				return false;
			} else {
				document.parentWindow.parent.document.getElementById("planNuevoCR").value	= "";	
				document.parentWindow.parent.document.getElementById("AdendumCR").value		= adendumCR;
				document.parentWindow.parent.document.getElementById("MesesCR").value		= mesesCR;
			}
		}
		//llama al servlet con el parametro seleccionado
		//$('#marca').load("./consultaCatalogo.do", {idOperacion:"cargaMarcas",idPlan:idPlan},
		$('#marca').load("./validaPlanRedencion.do", {sPlan:idPlan,adendum:adendum,fecAddM2K:fecAddM2K,tipoReden:tipoReden,tipoRedenS:tipoRedenS,MesesCR:mesesCR,AdendumCR:adendumCR,BanRedenAnct:banRedAct,tipoPromocion:tipoPromocion,idGpoPromocion:gpoPromo,nRegion:nRegionTel},
			function(data, status, xhr) { 
				if(status=="success") {

					//Obtiene el listado dentro de catalogo.jsp para incluirlos en el combo de seleccion
					agregaResultadoCatalogo(data,document.getElementById("marca"));

					var idMensaje = document.getElementById("idmensaje").value;
					//Si el plan es válido
					if(idMensaje == 0) {
						//Obtiene los datos resultantes de la validación
						getDatos();

						//Crea un elemento de tipo opcion y se lo agrega al inicio del combo de marcas una opción de 'seleccione'
						var elOptNew = document.createElement("option");
						elOptNew.text = 'Seleccione';
						elOptNew.value = '';

						var elSel = document.getElementById("marca");
						elSel.add(elOptNew, 0); 
						elSel.options[0].selected = true;

					} else {
						//Si hubo un error de validación lo despliega como mensaje de alerta
						var mensaje = document.getElementById("mensaje").value;
						alert(mensaje);
					}
				} else {
					alert("Ocurrio un error en la consulta");
				}
			}
		); 
	}
}

</script>
<title>Valida Plan</title>
</head>
<body onload="asignaValor();" style="background-color: transparent" >
<form name="frmMarcas" id="frmMarcas" method="post">
<input type="hidden" id="mensaje" name="mensaje">
<input type="hidden" id="idmensaje" name="idmensaje">

<input type="hidden" id="banRedencionAnct" name="banRedencionAnct">
<input type="hidden" id="nMesActual" name="nMesActual">

<input type="hidden" name="idProd" id="idProd">
<input type="hidden" name="descripcion" id="descripcion">
<input type="hidden" name="descuento" id="descuento">
<input type="hidden" name="marcaR" id="marcaR">
<input type="hidden" name="modeloR" id="modeloR">
<input type="hidden" name="valorptos" id="valorptos">
<input type="hidden" name="precio" id="precio">
<input type="hidden" name="precioIVA" id="precioIVA">

<input type="hidden" name="tipoPromocion" id="tipoPromocion">
<input type="hidden" name="sobrantes" id="sobrantes">	
<input type="hidden" name="bonoRoext" id="bonoRoext">
<input type="hidden" name="bonoAltoValor" id="bonoAltoValor">	
<input type="hidden" name="aplicaPaqSms" id="aplicaPaqSms">
<input type="hidden" name="aplicaPromoGap" id="aplicaPromoGap"/>
<input type="hidden" name="idPromocionGap" id="idPromocionGap"/>
<input type="hidden" name="idPromocionCA" id="idPromocionCA"/>
<input type="hidden" name="versionPromoGap" id="versionPromoGap"/>
<input type="hidden" name="bonosGap" id="bonosGap"/>
<input type="hidden" name="bonoDescuento" id="bonoDescuento"/>
<input type="hidden" name="productoM2K"	id="productoM2K"/>
<input type="hidden" name="nombrePromoGap" id="nombrePromoGap"/>
<input type="hidden" name="aplicaEP" id="aplicaEP" value=""/>
<input type="hidden" name="bonoInbursa" id="bonoInbursa">	
<input type="hidden" name="bonoMarca" id="bonoMarca">
<input type="hidden" name="bonoInbursaRestante" id="bonoInbursaRestante">	
<input type="hidden" name="bonoMarcaRestante" id="bonoMarcaRestante">
<input type="hidden" name="planesInbursa" id="planesInbursa" value="${planesInbursa}">
<input type="hidden" name="marcasInbursa" id="marcasInbursa" value="${marcasInbursa}">
<input type="hidden" name="modelosInbursa" id="modelosInbursa" value="${modelosInbursa}">
<input type="hidden" name="lineasInbursa" id="lineasInbursa" value="${lineasInbursa}">

<!-- Inputs para Ventana de Beneficios "buscaBeneficio()" -->
<input type="hidden" name="idBeneficio" id="idBeneficio">
<input type="hidden" name="idGpoBeneficio" id="idGpoBeneficio">
<input type="hidden" name="idMotivo" id="idMotivo">
<input type="hidden" name="tipoMotivo" id="tipoMotivo">

<!-- Inputs para selección de producto -->
<input type="hidden" name="esnimeiT" id="esnimeiT">
<input type="hidden" name="esnimeiP" id="esnimeiP">
<input type="hidden" name="iccid" id="iccid">
<input type="hidden" name="dirIP" id="dirIP" value="<%=request.getHeader("CLIENT_IP_ADDRESS")%>"/>
<%--<input type="hidden" name="dirIP" id="dirIP" value="<%=request.getRemoteAddr()%>"/>--%>

<div id="TarjetaTipoRed" style="height: 35px;visibility: hidden;display: none;background-color: transparent;"></div>

<div id="divLoaded"  class='Titulo' style="position: absolute; top:160px; left:300px; visibility: hidden">Procesando Informaci&oacute;n.....
	<img src='<c:url value="/commons/images/await.gif"/>'>
</div>

	<table width="98%" border="1" cellspacing="0" cellpadding="0" align="center" style="background-color: transparent;">
        <tr>          
          <td width="20%" class="healineblue1" bgcolor="#ECF0DB" align="left">&nbsp;Aplica Redención:</td>
          <td width="25%" class="textonegroBlod" align="left" id="descFormaRedencion">&nbsp;&nbsp;${descFormaRedencion}</td>
          <td width="20%" class="healineblue1" bgcolor="#ECF0DB" align="left">&nbsp;Tipo de Redención:</td>
          <td width="35%" class="textonegroBlod" id="descTipoRedencion">&nbsp;&nbsp;${descTipoRedencion}</td>
        </tr>   
		<tr>          
         <td class="healineblue1" bgcolor="#ECF0DB" align="left">&nbsp;Puntos Disponibles:</td>
          <td class="textonegroBlod" id="puntosDisponibles"></td>
          <td class="healineblue1" bgcolor="#ECF0DB" align="left">&nbsp;Plan para Renovar:</td>
          <td class="textonegroBlod">&nbsp;
		  <c:if test="${muestraCatalogo}">
		  <input type="text" align="left" class="textonegroBlod" name="buscaPlan" id="buscaPlan" style="text-transform: uppercase" size="8" maxlength="5" onKeyUp="fnBusqueda(this);">
		  </c:if>
		  </td>
        </tr>   
        <tr>                  
          <td class="healineblue1" bgcolor="#ECF0DB" align="left">&nbsp;</td>
          <td class="textonegroBlod">&nbsp;</td>
		  <td class="healineblue1" bgcolor="#ECF0DB" align="left">&nbsp;Plan para Renovar 
			<c:choose>
				<c:when test="${esRegion9}">R9:</c:when>
				<c:otherwise>Deur:</c:otherwise>
			</c:choose>
		  </td>
          <td class="textonegroBlod" id="planNuevo" valign="middle">
		  <c:choose>
		  <c:when test="${muestraCatalogo}">
		  <c:set var="desc" value=""/>
		  <c:set var="habilitar" value=""/>&nbsp;
			<select id="ctlPlanes1" name="ctlPlanes1" class="textonegroBlod" onchange="consultaMarcas();" style="width:280px" ${habilitar}>
				<option value="0">Seleccione</option>
				<c:forEach items="${catPlanes}" var="lstPlanes">
				<c:if test="${lstPlanes.idPlanNuevo == planNuevo}">
				<c:set var="desc" value="selected"/>
				</c:if>
					<option ${desc} value="${lstPlanes.idPlanNuevo}|${lstPlanes.idGrupoPromocion}|${lstPlanes.adendumNvo}|${lstPlanes.idRegion}|${lstPlanes.banRedencionAnct}|${lstPlanes.tipoPromocion}">${lstPlanes.descripcion}</option>
				<c:set var="desc" value=""/>
				</c:forEach>	
			</select>
			&nbsp;
			<img src='<c:url value="/commons/images/buscar.gif"/>' width="14" height="14" align="middle" alt="Validar Plan" onClick="consultaMarcas();"/>
		  </c:when>
		  <c:otherwise>&nbsp;<c:if test="${esRegion9 && tipoRedencion != 'SIN' && tipoRedencion != 'T3G'&& tipoRedencion != 'ACA'}">${planNuevo}</c:if></c:otherwise>
		  </c:choose>
			
		  </td>
        </tr>
	</table>

	<div id="AdendumCAREG" style="visibility:hidden">
	<table width="98%" border="0" cellspacing="0" cellpadding="0" align="center" style="background-color: transparent;">
		<tr><td colspan="4" height="2px"></td></tr>
		<tr>
		  <td width="20%" class="healineblue1" bgcolor="" align="left">
		  &nbsp;Addendum&nbsp;<font color="red">*</font>:</td>
		  <td width="25%" class="textonegroBlod" align="left">
		  &nbsp;<input class="textonegroBlod" type="text" name="AdendumCR" size="5" maxlength="5" id="AdendumCR" value="${adendumCR}"></td>
		  <td width="20%" class="healineblue1" bgcolor="" align="left">
		  &nbsp;Meses&nbsp;<font color="red">*</font>:</td>
		  <td width="35%" class="textonegroBlod">
		  &nbsp;<input class="textonegroBlod" type="text" name="MesesCR" size="5" maxlength="5" id="MesesCR" value="${mesesCR}"></td>
		</tr> 
	</table>
	</div>

<%--
<DIV id="resMarcas" style="OVERFLOW-Y: scroll; OVERFLOW-X: hidden; height: 225px;background-color: transparent;">
--%>

	<!--<form name="frmMarcas" id="frmMarcas" method="post">-->
	<div id="TarjetaMarcas" style="height: 20px; left 5px: hidden;display: none;background-color: transparent; position: static; top: 2px">

	<table width="98%" border="0" cellspacing="0" cellpadding="0" align="center">		
		<tr>
			<td colspan="5" class="healineblue1" valign="middle" width="15%">Marca: &nbsp;&nbsp;&nbsp;</td>
			<td colspan="5" class="healineblue1" valign="middle" width="15%">
				<select name="marca" class="textonegroBlod" size=1 id="marca" onchange="marca_onchange();">
					<option value="0">Seleccione</option>
					<c:set var="contador" value="0"/>
					<c:forEach var="catalogoTO" items="${catalogoTO}" varStatus="total">
						<c:set var="contador" value="${total.count}"/>
						<option value="${catalogoTO.descripcion}">${catalogoTO.descripcion}</option>
					</c:forEach>
         		</select>
	         </td>
			<td colspan="5" class="healineblue1" valign="middle" width="15%">Modelo:</td>
			<td colspan="10" class="healineblue1" valign="middle" width="15%">
				<input type="text" class="textonegroBlod" name="modelo" id="modelo" style="CURSOR:hand" onclick="modelos()"	onKeyDown="if(event.keyCode==13){document.getElementById('Link2').click(); }">				
			</td>
			<td colspan="5" class="healineblue1" valign="middle" width="15%" style="visibility: hidden;" id="addendum" align="left">Addendum del Plan:</td>			
			<td colspan="5" class="healineblue1" valign="middle" width="15%" style="visibility: hidden;" id="addendumTxt">
				<input type="text" align="left" class="textonegroBlod" name="nAdendumNuevo" id="nAdendumNuevo" disabled="disabled" size="2">
			</td>
		</tr>
	</table>
	</div>

	<DIV id="promocionSeleccionada" align="center">&nbsp;</DIV>

<DIV id="TablaProductos" style="OVERFLOW-Y: scroll; OVERFLOW-X: hidden; height: 170px;background-color: transparent;position: static; width:99%">

	<div id="TarjetaTipoConsulta" style="height: 20px;visibility: hidden;display: none;background-color: transparent;position: static;">
	<table border="0" cellspacing="0" cellpadding="0" align="center">
		<tr><td colspan="5" class="healineblue1" valign="middle" width="15%">
			<input	align="middle" type="radio" name="tipoconsulta" value="puntosEsp" checked="checked" disabled="disabled">
			Consulta de productos por cantidad espec&iacute;fica de Puntos</td>
		</tr>		
	</table>
	</div>
	
	<div id="TarjetaPuntos" style="height: 30px;visibility: hidden;display: none;background-color: transparent;position: static;">
	<table border="0" cellspacing="0" cellpadding="0" align="center">
		<tr><td colspan="5" class="healineblue1" valign="middle" width="15%">Valor en puntos</td>
			<td class="healineblue1Med" width="80%"><input id="ptsExactos"	name="ptsExactos" type="text" maxlength="7" size="8">
			<font color=red>*Capture el valor en puntos sin comas.</font></td>
		</tr>
	</table>
	</div>
	
	<div id="TarjetaIva" style="height: 45px;visibility: hidden;display: none;background-color: transparent;position: static;">
	<table border="0" cellspacing="0" cellpadding="0" align="left" id="ParamRedencionTBL">	
	<tr>
		<td class="healineblue1" valign="middle" align="left">Aplicar IVA:</td>
		<td class="healineblue1" valign="middle" align="right">
			<c:if test="${tipoRedencion=='CON' }">
				<input type="radio" name="porcentajeIVA" value="16" id="iva16">16%				
			</c:if>
			<c:if test="${tipoRedencion=='CAREG' }">
				<input type="radio" name="porcentajeIVA" value="16" id="iva16">16%
			</c:if>
			<c:if test="${tipoRedencion=='SIN' }">
				<input type="radio" name="porcentajeIVA" value="16" id="iva16">16%				
			</c:if>
			<c:if test="${tipoRedencion=='ACA' }">
				<input type="radio" name="porcentajeIVA" value="16" id="iva16">16%
			</c:if>
			<c:if test="${tipoRedencion=='T3G' }">
				<input type="radio" name="porcentajeIVA" value="16" id="iva16">16%				
			</c:if>
		</td>
	</tr>	
	<tr>
		<td align=left>
			<c:if test="${tipoRedencion=='CON' }">			
				<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="118">
					<a class="LinkOut" style="width:20%" onmouseover='this.className="LinkIn";'
						onmouseout='this.className="LinkOut";' id="Link2" onclick="productos()" >&nbsp;	Busca Promociones&nbsp;</a>
				</securityCa:validaPerfil>
			</c:if>
			<c:if test="${tipoRedencion=='CAREG' }">
				<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="122">
					<a class="LinkOut" style="width:20%" onmouseover='this.className="LinkIn";'
						onmouseout='this.className="LinkOut";' id="Link2" onclick="productos()" >&nbsp;	Busca Promociones&nbsp;</a>
				</securityCa:validaPerfil>				
			</c:if>
			<c:if test="${tipoRedencion=='SIN' }">
				<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="135">
					<a class="LinkOut" style="width:20%" onmouseover='this.className="LinkIn";'
						onmouseout='this.className="LinkOut";' id="Link2" onclick="productos()" >&nbsp;	Busca Promociones&nbsp;</a>
				</securityCa:validaPerfil>				
			</c:if>
			<c:if test="${tipoRedencion=='ACA' }">
				<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="140">
					<a class="LinkOut" style="width:20%" onmouseover='this.className="LinkIn";'
						onmouseout='this.className="LinkOut";' id="Link2" onclick="productos()" >&nbsp;	Busca Promociones&nbsp;</a>
				</securityCa:validaPerfil>
			</c:if>
			<c:if test="${tipoRedencion=='T3G' }">
				<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="144">
					<a class="LinkOut" style="width:20%" onmouseover='this.className="LinkIn";'
						onmouseout='this.className="LinkOut";' id="Link2" onclick="productos()" >&nbsp;	Busca Promociones&nbsp;</a>
				</securityCa:validaPerfil>
			</c:if>
		</td>
		<td align=left id="tdBuscaBeneficio">
			<c:if test="${tipoRedencion=='CON' }">			
				<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="119">
					<a class="LinkOut" style="width:20%" onmouseover='this.className="LinkIn";'
				onmouseout='this.className="LinkOut";' id="Link2" onclick="buscaBeneficios(1);" >&nbsp;	Busca Beneficios&nbsp;</a>
				</securityCa:validaPerfil>
			</c:if>
			<c:if test="${tipoRedencion=='CAREG' }">			
				<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="123">
					<a class="LinkOut" style="width:20%" onmouseover='this.className="LinkIn";'
				onmouseout='this.className="LinkOut";' id="Link2" onclick="buscaBeneficios(1);" >&nbsp;	Busca Beneficios&nbsp;</a>
				</securityCa:validaPerfil>
			</c:if>
			<c:if test="${tipoRedencion=='SIN' }">			
				<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="136">
					<a class="LinkOut" style="width:20%" onmouseover='this.className="LinkIn";'
				onmouseout='this.className="LinkOut";' id="Link2" onclick="buscaBeneficios(1);" >&nbsp;	Busca Beneficios&nbsp;</a>
				</securityCa:validaPerfil>
			</c:if>
			<c:if test="${tipoRedencion=='ACA' }">			
				<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="141">
					<a class="LinkOut" style="width:20%" onmouseover='this.className="LinkIn";'
				onmouseout='this.className="LinkOut";' id="Link2" onclick="buscaBeneficios(1);" >&nbsp;	Busca Beneficios&nbsp;</a>
				</securityCa:validaPerfil>
			</c:if>
			<c:if test="${tipoRedencion=='T3G' }">			
				<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="145">
					<a class="LinkOut" style="width:20%" onmouseover='this.className="LinkIn";'
				onmouseout='this.className="LinkOut";' id="Link2" onclick="buscaBeneficios(1);" >&nbsp;	Busca Beneficios&nbsp;</a>
				</securityCa:validaPerfil>
			</c:if>
		</td>
		<td id="trInbursa" class="healineblue1" style="visibility: hidden;">Aplica Descuento Inbursa <input type="checkbox" name="descuentoInbursa" id="descuentoInbursa"></td>
	</tr>
	</table>
	</div>

<!-- OVERFLOW-Y: scroll; OVERFLOW-X: hidden;  -->
<div id="TarjetaProductos" style="height: 80px;visibility: hidden;display: none;background-color: transparent;position: static;">

	<table border="1" cellspacing="0" cellpadding="0" align="left" id="RangosMultiplesTBL" width="90%">	
		<tr bgcolor="#eff0f1" class="healineblue1" align="center">
			<td width="3%">&nbsp;</td>
			<td>Plan</td>
			<td>Material</td>
			<td>Descripcion</td>			
			<c:if test="${tipoRedencion!='ACA'}">
			<td>Marca</td>
			<td>Modelo</td>
			 	<c:choose>
                	<c:when test="${tipoRedencion!='T3G'}"><td>IMEI</td></c:when>
                	<c:when test="${tipoRedencion=='T3G'}"><td>IMEI</td></c:when>
				</c:choose>			
			</c:if>			
			<td>Puntos</td>			
			<c:if test="${tipoRedencion!='ACA'}">
			<td>Precio</td>
				<c:if test="${tipoRedencion!='T3G'}">
					<td>Descuento</td>
				</c:if>
			<td>Precio IVA</td>
			</c:if>			
			<c:if test="${tipoRedencion=='ACA'}">
			<td>Total a Pagar</td>
			<td>No.Iccid</td>			
			</c:if>
	</tr>	
	</table>
	</div>
</DIV>
	<!-- height: 80px; -->
	<div id="divComentarios" style="visibility: hidden;display: none;background-color: transparent;">

	<!--  style="visibility: hidden;display: none;" -->
	<c:if test="${tipoRedencion=='CON' }">			
		<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="120">
			<div id="divCon">
				<table border="0" cellspacing="0" cellpadding="0" align="left" id="redencion">		
					<tr>
						<td class="textonegroBlodTrs" align="left" width="80%">Comentarios:<input type='text' name="coment" id="coment" size="100" maxlength="90" maxonKeyDown="if(event.keyCode==13) document.getElementById('btnRedencion').click()" disabled></td>
						<td align="right" width="20%"><a class="LinkOut" style="width:20%" onmouseover='this.className="LinkIn";' onmouseout='this.className="LinkOut";' id="btnRedencion" onclick="realizaRedencion()" disabled>&nbsp;Realiza Redención&nbsp;</a></td>
					</tr>
				</table>
			</div>
		</securityCa:validaPerfil>
	</c:if>
	<c:if test="${tipoRedencion=='CAREG' }">			
		<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="124">
			<div id="divCareg">
				<table border="0" cellspacing="0" cellpadding="0" align="left" id="redencion">		
					<tr>
						<td class="textonegroBlodTrs" align="left" width="80%">Comentarios:<input type='text' name="coment" id="coment" size="100" maxlength="90" maxonKeyDown="if(event.keyCode==13) document.getElementById('btnRedencion').click()" disabled></td>
						<td align="right" width="20%"><a class="LinkOut" style="width:20%" onmouseover='this.className="LinkIn";' onmouseout='this.className="LinkOut";' id="btnRedencion" onclick="realizaRedencion()" disabled>&nbsp;Realiza Redención&nbsp;</a></td>
					</tr>
				</table>
			</div>
		</securityCa:validaPerfil>
	</c:if>
	<c:if test="${tipoRedencion=='SIN' }">			
		<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="137">
			<div id="divSin">
				<table border="0" cellspacing="0" cellpadding="0" align="left" id="redencion">		
					<tr>
						<td class="textonegroBlodTrs" align="left" width="80%">Comentarios:<input type='text' name="coment" id="coment" size="100" maxlength="90" maxonKeyDown="if(event.keyCode==13) document.getElementById('btnRedencion').click()" disabled></td>
						<td align="right" width="20%"><a class="LinkOut" style="width:20%" onmouseover='this.className="LinkIn";' onmouseout='this.className="LinkOut";' id="btnRedencion" onclick="realizaRedencion()" disabled>&nbsp;Realiza Redención&nbsp;</a></td>
					</tr>
				</table>
			</div>
		</securityCa:validaPerfil>
	</c:if>
	<c:if test="${tipoRedencion=='ACA' }">			
		<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="142">
			<div id="divAca">
				<table border="0" cellspacing="0" cellpadding="0" align="left" id="redencion">		
					<tr>
						<td class="textonegroBlodTrs" align="left" width="80%">Comentarios:<input type='text' name="coment" id="coment" size="100" maxlength="90" maxonKeyDown="if(event.keyCode==13) document.getElementById('btnRedencion').click()" disabled></td>
						<td align="right" width="20%"><a class="LinkOut" style="width:20%" onmouseover='this.className="LinkIn";' onmouseout='this.className="LinkOut";' id="btnRedencion" onclick="realizaRedencion()" disabled>&nbsp;Realiza Redención&nbsp;</a></td>
					</tr>
				</table>
			</div>
		</securityCa:validaPerfil>
	</c:if>
	<c:if test="${tipoRedencion=='T3G' }">			
		<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="146">
			<div id="divT3G">
				<table border="0" cellspacing="0" cellpadding="0" align="left" id="redencion">		
					<tr>
						<td class="textonegroBlodTrs" align="left" width="80%">Comentarios:<input type='text' name="coment" id="coment" size="100" maxlength="90" maxonKeyDown="if(event.keyCode==13) document.getElementById('btnRedencion').click()" disabled></td>
						<td align="right" width="20%"><a class="LinkOut" style="width:20%" onmouseover='this.className="LinkIn";' onmouseout='this.className="LinkOut";' id="btnRedencion" onclick="realizaRedencion()" disabled>&nbsp;Realiza Redención&nbsp;</a></td>
					</tr>
				</table>
			</div>
		</securityCa:validaPerfil>
	</c:if>
	<!--</div>-->
	<!--</form>-->
<%--</DIV>--%>
 </div>
 </form>
</body>
</html>