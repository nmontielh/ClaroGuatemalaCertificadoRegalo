<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/permisos.tld" prefix="securityCa" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Consulta de Marcas</title>
<script src='<c:url value="/commons/js/validacionesRedencion.js"/>' type="text/javascript"></script>
<link rel="stylesheet" href='<c:url value="/commons/js/sc_textsheet.css"/>' type="text/css">
<script type="text/javascript">
var loadDatos=false;
var tipoRedencion;

	function getDatos(){
		if(!loadDatos){
			document.getElementById("nGrupo").value=document.parentWindow.parent.document.getElementById("gpoPromo").value;			
			document.getElementById("nRegion").value=document.parentWindow.parent.document.getElementById("nRegion").value;
			document.getElementById("nAdendumNuevo").value=document.parentWindow.parent.document.getElementById("adendumNvo").value;
			document.getElementById("tipoRedencion").value="${tipoRed}";
			document.getElementById("ptsExactos").value=document.parentWindow.parent.document.getElementById("ptsTotales").value;
			document.getElementById("cuenta").value=document.parentWindow.parent.document.getElementById("cuenta").value;
			document.getElementById("secuencia").value=document.parentWindow.parent.document.getElementById("secuencia").value;
			document.getElementById("telefono").value=document.parentWindow.parent.document.getElementById("telefono").value;
			document.getElementById("fecAddM2K").value=document.parentWindow.parent.document.getElementById("fecAddM2K").value;
			document.getElementById("bAplicaRedencion").value=document.parentWindow.parent.document.getElementById("bAplicaRedencion").value;									
			var defineIva=document.parentWindow.parent.document.getElementById("defineIva").value;			
			var porcentajeIva=document.parentWindow.parent.document.getElementById("porcentajeIva").value;			
			tipoRedencion = document.getElementById("tipoRedencion").value;
					
			if(tipoRedencion=="CON" || tipoRedencion=="CAREG"){
				document.getElementById("addendum").style.visibility = "visible";
				document.getElementById("addendumTxt").style.visibility = "visible";
			}
			if(tipoRedencion!="ACA"){
				document.getElementById("TarjetaMarcas").style.visibility = "visible";
				document.getElementById("TarjetaMarcas").style.display = "block";
			}
			
			if(porcentajeIva=="1.16"){				
				document.getElementById("iva16").checked =true;
			}else if(porcentajeIva=="1.11"){				
				document.getElementById("iva11").checked =true;
			}else{				
				document.getElementById("iva16").checked =false;
				document.getElementById("iva11").checked =false;			
			}			
			if(defineIva=="false"){
				document.getElementById("iva16").disabled =true;
				document.getElementById("iva11").disabled =true;
			}						
			
			document.getElementById("TarjetaTipoConsulta").style.visibility="visible";
			document.getElementById("TarjetaTipoConsulta").style.display="block";
			
			document.getElementById("TarjetaPuntos").style.visibility="visible";
			document.getElementById("TarjetaPuntos").style.display="block";
			
			document.getElementById("TarjetaIva").style.visibility="visible";
			document.getElementById("TarjetaIva").style.display="block";
			
			document.getElementById("TarjetaProductos").style.visibility="visible";			
			document.getElementById("TarjetaProductos").style.display="block";
			
			document.parentWindow.parent.document.getElementById("TarjetaPromociones").style.visibility="visible";
			document.parentWindow.parent.document.getElementById("TarjetaPromociones").style.display="block";
			
			var aplicaRedencion = document.getElementById("bAplicaRedencion").value;
			
			if(tipoRedencion=='CON'){
				var divCon = document.getElementById("divCon");
				if(divCon!=null){
					if(aplicaRedencion=='true' || aplicaRedencion=='TRUE'){
						divCon.style.visibility="visible";
					}else{
						divCon.style.visibility="hidden";
					}
				}				
			}else if(tipoRedencion=='CAREG'){
				var divCareg = document.getElementById("divCareg");
				if(divCareg!=null){
					if(aplicaRedencion=='true' || aplicaRedencion=='TRUE'){
						divCareg.style.visibility="visible";
					}else{
						divCareg.style.visibility="hidden";
					}
				}				
			}else if(tipoRedencion=='SIN'){
				var divSin = document.getElementById("divSin");
				if(divSin!=null){
					if(aplicaRedencion=='true' || aplicaRedencion=='TRUE'){
						divSin.style.visibility="visible";
					}else{
						divSin.style.visibility="hidden";
					}
				}				
			}else if(tipoRedencion=='ACA'){
				var divAca = document.getElementById("divAca");
				if(divAca!=null){
					if(aplicaRedencion=='true' || aplicaRedencion=='TRUE'){
						divAca.style.visibility="visible";
					}else{
						divAca.style.visibility="hidden";
					}
				}
				
			}else if(tipoRedencion=='T3G'){
				var divT3G = document.getElementById("divT3G");
				if(divT3G!=null){
					if(aplicaRedencion=='true' || aplicaRedencion=='TRUE'){
						divT3G.style.visibility="visible";
					}else{
						divT3G.style.visibility="hidden";
					}
				}				
			}			
			loadDatos=true;			
		}
	}
	
	function modelos(){
	var valor;
		if (document.getElementById("marca").value=="0"){
		window.alert("Debe seleccionar una marca");
    	}else{
	    	valor = window.showModalDialog("./consultaModelos.do?sMarca="+document.getElementById("marca").value+
	    	"&nRegion="+document.getElementById("nRegion").value+
	    	"&nGrupo="+document.getElementById("nGrupo").value,"",
	    	"dialogHeight: 350px;dialogWidth: 400px; center: Yes; help: No; resizable: Yes; status: No;");	    	
	    	if(valor!= null && valor != "undefined" && valor != " "){
	    	document.getElementById("modelo").value=valor;
	    	}
	    }	    
	    
	}	
	
	function realizaRedencion(){
		if(validaRedencion()){
			if(confirm("Esta apunto de redimir los puntos de la línea ¿Desea continuar?")){
				if(document.getElementById("idPromocionGap").value !=0 && document.getElementById("productoM2K").value == 'SI'){
					if(confirm("La promoción  -" + document.getElementById("nombrePromoGap").value + "-  se aplicará a la línea."+ "\n"+
					"Favor de Verificar en Mobile 2000")){
						aplicaRedencion();									
					}
				}else{
						aplicaRedencion();				
				}
			}
		}
	}
	
	function productos(){	
		if(validaMarcaModelo()){
			getProductos();
		}
	}
	
	function buscaBeneficios(valor){
		if(valor==1){
			var region = document.parentWindow.parent.document.getElementById("nRegion");
			var url = "./consultaBeneficios.do?region=" + region.value;
			var beneficio=window.showModalDialog(url,"","dialogHeight: 450px; " +		
				"dialogWidth: 800px; center: Yes; help: No; resizable: Yes; status: No;");
		}
		if(valor==2){
			var region = document.parentWindow.parent.document.getElementById("nRegion");
			var marca = document.getElementById("marca");
			var url = "./consultaBeneficiosMarca.do?marca=" + marca.value + "&region=" + region.value; 
			var beneficio = window.showModalDialog(url,"","dialogHeight: 450px; " +		
				"dialogWidth: 800px; center: Yes; help: No; resizable: Yes; status: No;");
				
			if(beneficio != null && beneficio != "undefined" && beneficio != " " && beneficio!=""){
				var valores = beneficio.split("|");
				var promocionSeleccionada = document.getElementById("promocionSeleccionada");
				
				var idBeneficio = document.getElementById("idBeneficio");
				var idGpoBeneficio = document.getElementById("idGpoBeneficio");
				var idMotivo = document.getElementById("idMotivo");
				var tipoMotivo = document.getElementById("tipoMotivo");
				
				idBeneficio.value = valores[1];
				idGpoBeneficio.value = valores[2];
				idMotivo.value = valores[3]; 
				tipoMotivo.value = valores[4];
								
				
				if(valores[4]=="1"){
					document.getElementById("modelo").value = valores[0];					
					promocionSeleccionada.innerHTML = "<font style='font: bold' color='BLUE'>Se acepto el Beneficio</font>";
				}else{
					document.getElementById("modelo").value = "";					
					promocionSeleccionada.innerHTML = "<font style='font: bold' color='red'>No se acept&oacute; ning&uacute;n Beneficio</font>";
				}
			}									
		}	
	}
	function marca_onchange(){
		var modelo = document.getElementById("modelo");
		modelo.value = "";
		var frmMarcas = document.getElementById("promocionSeleccionada");
		frmMarcas.innerHTML = "";
		
		buscaBeneficios(2);		
		//limpia();		 
	}		
		
</script>
</head>

<body style="background-color: transparent;" onload="getDatos();" > 
<DIV id="resMarcas" style="visibility: visible;display: block;background-color: transparent; position: absolute;top:2px;left: 5px;height: 300px;width: 990px;" >

	<form name="frmMarcas" id="frmMarcas" method="post">
	<input type="hidden" name="nRegion" id="nRegion">
	<input type="hidden" name="nGrupo" id="nGrupo">
	<input type="hidden" name="tipoRedencion" id="tipoRedencion">	
	<input type="hidden" name="cuenta" id="cuenta">	
	<input type="hidden" name="secuencia" id="secuencia">
	<input type="hidden" name="telefono" id="telefono">
	<input type="hidden" name="fecAddM2K" id="fecAddM2K">
	<input type="hidden" name="tipoPromocion" id="tipoPromocion">
	<input type="hidden" name="sobrantes" id="sobrantes">	
	<input type="hidden" name="bonoRoext" id="bonoRoext">
	<input type="hidden" name="bonoAltoValor" id="bonoAltoValor">	
	<input type="hidden" name="aplicaPaqSms" id="aplicaPaqSms">
	<input type="hidden" name="bAplicaRedencion" id="bAplicaRedencion"/>
	
	<input type="hidden" name="idBeneficio" id="idBeneficio">
	<input type="hidden" name="idGpoBeneficio" id="idGpoBeneficio">
	<input type="hidden" name="idMotivo" id="idMotivo">
	<input type="hidden" name="tipoMotivo" id="tipoMotivo">
	
	<input type="hidden" name="aplicaPromoGap"  id="aplicaPromoGap"/>
	<input type="hidden" name="nombrePromoGap"		  id="nombrePromoGap"/>
	<input type="hidden" name="bonoDescuento"	  id="bonoDescuento"/>
	<input type="hidden" name="productoM2K"	  id="productoM2K"/>
	<input type="hidden" name="idPromocionGap"  id="idPromocionGap"/>
	<input type="hidden" name="idPromocionCA"   id="idPromocionCA"/>
	<input type="hidden" name="versionPromoGap" id="versionPromoGap"/>
	<input type="hidden" name="bonosGap"		  id="bonosGap"/>
	<!-- <input type="hidden" name="dirIP" id="dirIP" value="<%=request.getHeader("CLIENT_IP_ADDRESS")%>"/> -->
	<input type="hidden" name="dirIP" id="dirIP" value="<%=request.getRemoteAddr()%>"/> 
		
	<input type="hidden" name="aplicaEP" id="aplicaEP" value=""/>
			
	<div id="TarjetaMarcas" style="height: 30px; left 5px: hidden;display: none;background-color: transparent; position: static; top: 2px">
	<table border="0" cellspacing="0" cellpadding="0" align="center">		
		<tr>
			<td colspan="5" class="healineblue1" valign="middle" width="15%">Marca: &nbsp;&nbsp;&nbsp;</td>
			<td colspan="5" class="healineblue1" valign="middle" width="15%">
				<select name="marca" class="textonegroBlod" size=1 id="marca" onchange="marca_onchange();">
					<option value="0"></option>
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
	
	<DIV id="promocionSeleccionada" align="center"></DIV>
	
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
	<table border="0" cellspacing="0" cellpadding="0" align="left">	
	<tr>
		<td class="healineblue1" valign="middle" align="left">Aplicar IVA:</td>
		<td class="healineblue1" valign="middle" align="right">
			<c:if test="${tipoRed=='CON' }">
				<input type="radio" name="porcentajeIVA" value="16" id="iva16">16%
				<input type="radio" name="porcentajeIVA" value="11" id="iva11">11%
			</c:if>
			<c:if test="${tipoRed=='CAREG' }">
				<input type="radio" name="porcentajeIVA" value="16" id="iva16">16%
				<input type="radio" name="porcentajeIVA" value="11" id="iva11">11%
			</c:if>
			<c:if test="${tipoRed=='SIN' }">
				<input type="radio" name="porcentajeIVA" value="16" id="iva16">16%
				<input type="radio" name="porcentajeIVA" value="11" id="iva11">11%
			</c:if>
			<c:if test="${tipoRed=='ACA' }">
				<input type="radio" name="porcentajeIVA" value="16" id="iva16">16%
				<input type="radio" name="porcentajeIVA" value="11" id="iva11">11%
			</c:if>
			<c:if test="${tipoRed=='T3G' }">
				<input type="radio" name="porcentajeIVA" value="16" id="iva16">16%
				<input type="radio" name="porcentajeIVA" value="11" id="iva11">11%
			</c:if>
		
		</td>
	</tr>	
	<tr>
		<td align=left>
			<c:if test="${tipoRed=='CON' }">			
				<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="118">
					<a class="LinkOut" style="width:20%" onmouseover='this.className="LinkIn";'
						onmouseout='this.className="LinkOut";' id="Link2" onclick="productos()" >&nbsp;	Busca Promociones&nbsp;</a>
				</securityCa:validaPerfil>
			</c:if>
			<c:if test="${tipoRed=='CAREG' }">
				<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="122">
					<a class="LinkOut" style="width:20%" onmouseover='this.className="LinkIn";'
						onmouseout='this.className="LinkOut";' id="Link2" onclick="productos()" >&nbsp;	Busca Promociones&nbsp;</a>
				</securityCa:validaPerfil>				
			</c:if>
			<c:if test="${tipoRed=='SIN' }">
				<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="135">
					<a class="LinkOut" style="width:20%" onmouseover='this.className="LinkIn";'
						onmouseout='this.className="LinkOut";' id="Link2" onclick="productos()" >&nbsp;	Busca Promociones&nbsp;</a>
				</securityCa:validaPerfil>				
			</c:if>
			<c:if test="${tipoRed=='ACA' }">
				<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="140">
					<a class="LinkOut" style="width:20%" onmouseover='this.className="LinkIn";'
						onmouseout='this.className="LinkOut";' id="Link2" onclick="productos()" >&nbsp;	Busca Promociones&nbsp;</a>
				</securityCa:validaPerfil>
			</c:if>
			<c:if test="${tipoRed=='T3G' }">
				<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="144">
					<a class="LinkOut" style="width:20%" onmouseover='this.className="LinkIn";'
						onmouseout='this.className="LinkOut";' id="Link2" onclick="productos()" >&nbsp;	Busca Promociones&nbsp;</a>
				</securityCa:validaPerfil>
			</c:if>
		</td>
		<td align=left>
		
			<c:if test="${tipoRed=='CON' }">			
				<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="119">
					<a class="LinkOut" style="width:20%" onmouseover='this.className="LinkIn";'
				onmouseout='this.className="LinkOut";' id="Link2" onclick="buscaBeneficios(1);" >&nbsp;	Busca Beneficios&nbsp;</a>
				</securityCa:validaPerfil>
			</c:if>
			<c:if test="${tipoRed=='CAREG' }">			
				<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="123">
					<a class="LinkOut" style="width:20%" onmouseover='this.className="LinkIn";'
				onmouseout='this.className="LinkOut";' id="Link2" onclick="buscaBeneficios(1);" >&nbsp;	Busca Beneficios&nbsp;</a>
				</securityCa:validaPerfil>
			</c:if>
			<c:if test="${tipoRed=='SIN' }">			
				<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="136">
					<a class="LinkOut" style="width:20%" onmouseover='this.className="LinkIn";'
				onmouseout='this.className="LinkOut";' id="Link2" onclick="buscaBeneficios(1);" >&nbsp;	Busca Beneficios&nbsp;</a>
				</securityCa:validaPerfil>
			</c:if>
			<c:if test="${tipoRed=='ACA' }">			
				<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="141">
					<a class="LinkOut" style="width:20%" onmouseover='this.className="LinkIn";'
				onmouseout='this.className="LinkOut";' id="Link2" onclick="buscaBeneficios(1);" >&nbsp;	Busca Beneficios&nbsp;</a>
				</securityCa:validaPerfil>
			</c:if>
			<c:if test="${tipoRed=='T3G' }">			
				<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="145">
					<a class="LinkOut" style="width:20%" onmouseover='this.className="LinkIn";'
				onmouseout='this.className="LinkOut";' id="Link2" onclick="buscaBeneficios(1);" >&nbsp;	Busca Beneficios&nbsp;</a>
				</securityCa:validaPerfil>
			</c:if>
			
			
			
		</td>
	</tr>	
	</table>
	</div>
	<div id="TarjetaProductos" style="height: 80px;visibility: hidden;display: none;background-color: transparent;position: static;">
	<table border="1" cellspacing="0" cellpadding="0" align="left">		
	<tr bgcolor="#eff0f1" class="healineblue1" align="center">
			<td>Material</td>
			<td>Descripcion</td>			
			<c:if test="${tipoRed!='ACA'}">
			<td>Marca</td>
			<td>Modelo</td>
			 	<c:choose>
                	<c:when test="${tipoRed!='T3G'}"><td>IMEI</td></c:when>
                	<c:when test="${tipoRed=='T3G'}"><td>IMEI</td></c:when>
				</c:choose>			
			</c:if>			
			<td>Puntos</td>			
			<c:if test="${tipoRed!='ACA'}">
			<td>Precio</td>
				<c:if test="${tipoRed!='T3G'}">
					<td>Descuento</td>	
				</c:if>
			<td>Precio IVA</td>
			</c:if>			
			<c:if test="${tipoRed=='ACA'}">
			<td>Total a Pagar</td>
			<td>No.Iccid</td>			
			</c:if>
						
			

	</tr>	
	<tr class="textonegroBlod">
			<td><input class="textonegroBlod" type='text' name="idProd" size=10 style="border:0" align="middle" readonly></td>
			<td><input class="textonegroBlod" type='text' name="descripcion" id = "descripcion" size=30 style="border:0" align="middle" readonly></td>
			
			<c:choose>
                	<c:when test="${tipoRed=='ACA'}">
                		<td style="visibility: hidden; display: none"><input class="textonegroBlod" type='hidden' name="marcaR" id="marcaR" size=10 style="border:0" align="middle" readonly></td>
						<td style="visibility: hidden; display: none"><input class="textonegroBlod" type='hidden' name="modeloR" id="modeloR" size=10 style="border:0" align="middle" readonly></td>
						<td style="visibility: hidden; display: none"><input class="textonegroBlod" type='hidden' name="esnimeiT" id="esnimeiT" size=10 style="border:0" align="middle" readonly></td>
						<td style="visibility: hidden; display: none"><input class="textonegroBlod" type='hidden' name="esnimeiP" id="esnimeiP" size=10 style="border:0" align="middle" readonly></td>
                	</c:when>
                	 
                	 <c:when test="${tipoRed=='T3G'}">
                	 	<td><input class="textonegroBlod" type='text' name="marcaR" id="marcaR" size=10 style="border:0" align="middle" readonly></td>
					 	<td><input class="textonegroBlod" type='text' name="modeloR" id="modeloR" size=10 style="border:0" align="middle" readonly></td>
           	    		<td width="10 px"><input class="textonegroBlod" type='text'	name='esnimeiT' id="esnimeiT" size="16"	style="border:0; color: blue; font:bold;" maxlength="15">
			     	    <input class="textonegroBlod" type='hidden'	name='esnimeiP' id="esnimeiP" size="16"	style="border:0; color: blue; font:bold;" maxlength="15"></td>
                	</c:when>
                	
                	<c:otherwise>
                		<td><input class="textonegroBlod" type='text' name="marcaR" id="marcaR" size=10 style="border:0" align="middle" readonly></td>
						<td><input class="textonegroBlod" type='text' name="modeloR" id="modeloR" size=10 style="border:0" align="middle" readonly></td>
						<td width="10 px">Eq.Tarifario&nbsp;&nbsp;&nbsp;&nbsp;: <input class="textonegroBlod" type='text'	name='esnimeiT' id="esnimeiT" size="16"	style="border:0; color: blue; font:bold;" maxlength="15">
										  Eq.Promoción: <input class="textonegroBlod" type='text'	name='esnimeiP' id="esnimeiP" size="16"	style="border:0; color: blue; font:bold;" maxlength="15">
						</td>
                	</c:otherwise>                	
			</c:choose>	
			
			<td><input class="textonegroBlod" type='text' name="valorptos" size=8 style="border:0" align="middle" readonly></td>
			
			
			<c:choose>
                	<c:when test="${tipoRed=='ACA'}">
                		<td style="visibility: hidden; display: none">$<input class="textonegroBlod" type='hidden' name="precio" size=6 style="border:0" align="middle" readonly></td>
						<td style="visibility: hidden; display: none">$<input class="textonegroBlod" type='hidden' name="descuento" size=6 style="border:0" align="middle" readonly></td>			
                	</c:when>
                	 
                	 <c:when test="${tipoRed=='T3G'}">
                	 	<td>$<input class="textonegroBlod" type='text' name="precio" size=6 style="border:0" align="middle" readonly></td>
						<td style="visibility: hidden; display: none">$<input class="textonegroBlod" type='hidden' name="descuento" size=6 style="border:0" align="middle" readonly></td>
                	</c:when>
                	
                	<c:otherwise>
                		<td>$<input class="textonegroBlod" type='text' name="precio" size="6" style="border:0" align="middle" readonly></td>
						<td>$<input class="textonegroBlod" type='text' name="descuento" size="6" style="border:0" align="middle" readonly></td>
                	</c:otherwise>                	
			</c:choose>	
			
			<td>$<input class="textonegroBlod" type='text' name="precioIVA" id="precioIVA" size="6" style="border:0" align="middle" readonly></td>
			
			
			<c:choose>
                	<c:when test="${tipoRed=='ACA'}">
                		<td><input class="textonegroBlod" type='text' name="iccid" id="iccid" size="20" style="border:0; color: blue; font:bold;" maxlength="18"></td>									
                	</c:when>
                	
                	<c:otherwise>
                		<td style="visibility: hidden; display: none"><input class="textonegroBlod" type='hidden' name="iccid" id="iccid" size="20" style="border:0; color: blue; font:bold;" maxlength="18"></td>
                	</c:otherwise>                	
			</c:choose>	
	</tr>	
	</table>
	</div>
	
	
	<c:if test="${tipoRed=='CON' }">			
		<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="120">
			<div id="divCon">
				<table border="0" cellspacing="0" cellpadding="0" align="left" id="redencion">		
					<tr>
						<td class="textonegroBlodTrs" align="left" width="80%">Comentarios:<input type='text' name="coment" id="coment" size="100" maxlength="99" maxonKeyDown="if(event.keyCode==13) document.getElementById('btnRedencion').click()"></td>
						<td align="right" width="20%"><a class="LinkOut" style="width:20%" onmouseover='this.className="LinkIn";' onmouseout='this.className="LinkOut";' id="btnRedencion" onclick="realizaRedencion()">&nbsp;Realiza Redención&nbsp;</a></td>
					</tr>
				</table>
			</div>
		</securityCa:validaPerfil>
	</c:if>
	<c:if test="${tipoRed=='CAREG' }">			
		<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="124">
			<div id="divCareg">
				<table border="0" cellspacing="0" cellpadding="0" align="left" id="redencion">		
					<tr>
						<td class="textonegroBlodTrs" align="left" width="80%">Comentarios:<input type='text' name="coment" id="coment" size="100" maxlength="99" maxonKeyDown="if(event.keyCode==13) document.getElementById('btnRedencion').click()"></td>
						<td align="right" width="20%"><a class="LinkOut" style="width:20%" onmouseover='this.className="LinkIn";' onmouseout='this.className="LinkOut";' id="btnRedencion" onclick="realizaRedencion()">&nbsp;Realiza Redención&nbsp;</a></td>
					</tr>
				</table>
			</div>
		</securityCa:validaPerfil>
	</c:if>
	<c:if test="${tipoRed=='SIN' }">			
		<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="137">
			<div id="divSin">
				<table border="0" cellspacing="0" cellpadding="0" align="left" id="redencion">		
					<tr>
						<td class="textonegroBlodTrs" align="left" width="80%">Comentarios:<input type='text' name="coment" id="coment" size="100" maxlength="99" maxonKeyDown="if(event.keyCode==13) document.getElementById('btnRedencion').click()"></td>
						<td align="right" width="20%"><a class="LinkOut" style="width:20%" onmouseover='this.className="LinkIn";' onmouseout='this.className="LinkOut";' id="btnRedencion" onclick="realizaRedencion()">&nbsp;Realiza Redención&nbsp;</a></td>
					</tr>
				</table>
			</div>
		</securityCa:validaPerfil>
	</c:if>
	<c:if test="${tipoRed=='ACA' }">			
		<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="142">
			<div id="divAca">
				<table border="0" cellspacing="0" cellpadding="0" align="left" id="redencion">		
					<tr>
						<td class="textonegroBlodTrs" align="left" width="80%">Comentarios:<input type='text' name="coment" id="coment" size="100" maxlength="99" maxonKeyDown="if(event.keyCode==13) document.getElementById('btnRedencion').click()"></td>
						<td align="right" width="20%"><a class="LinkOut" style="width:20%" onmouseover='this.className="LinkIn";' onmouseout='this.className="LinkOut";' id="btnRedencion" onclick="realizaRedencion()">&nbsp;Realiza Redención&nbsp;</a></td>
					</tr>
				</table>
			</div>
		</securityCa:validaPerfil>
	</c:if>
	<c:if test="${tipoRed=='T3G' }">			
		<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="146">
			<div id="divT3G">
				<table border="0" cellspacing="0" cellpadding="0" align="left" id="redencion">		
					<tr>
						<td class="textonegroBlodTrs" align="left" width="80%">Comentarios:<input type='text' name="coment" id="coment" size="100" maxlength="99" maxonKeyDown="if(event.keyCode==13) document.getElementById('btnRedencion').click()"></td>
						<td align="right" width="20%"><a class="LinkOut" style="width:20%" onmouseover='this.className="LinkIn";' onmouseout='this.className="LinkOut";' id="btnRedencion" onclick="realizaRedencion()">&nbsp;Realiza Redención&nbsp;</a></td>
					</tr>
				</table>
			</div>
		</securityCa:validaPerfil>
	</c:if>
	
	
</form>
	
</DIV>

</body>
</html>