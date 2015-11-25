<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/permisos.tld" prefix="securityCa" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Consulta y Actualizacion de Folios</title>
<script type="text/javascript" src='<c:url value="/commons/js/validacionesRedencion.js"/>'></script>
<script src='<c:url value="/commons/js/actualizaFolioSap.js"/>' type="text/javascript"></script>
<link rel="stylesheet" href='<c:url value="/commons/js/sc_textsheet.css"/>' type="text/css">
<script type="text/javascript">
var loadDatos=false;
var nTipoRed=0;
var idFolio;
var folio;
var esnImeiTNvo;
var esnImeiPNvo;
var iccidNvo;
var tecnologia;
var cuenta;

	function getDatos(){		
		if(!loadDatos){
			nTipoRed=${nTipoRed};			
			document.parentWindow.parent.document.getElementById("TarjetaFoliosSap").style.visibility="hidden";
			document.parentWindow.parent.document.getElementById("TarjetaFoliosSap").style.display="none";
			document.parentWindow.parent.document.getElementById("TarjetaFolios").style.visibility="visible";
			document.parentWindow.parent.document.getElementById("TarjetaFolios").style.display="block";	
			document.parentWindow.parent.document.getElementById("inputSap").style.visibility="hidden";
			document.parentWindow.parent.document.getElementById("inputSap").style.display="none";
			document.parentWindow.parent.document.getElementById("inputSin").style.visibility="hidden";
			document.parentWindow.parent.document.getElementById("inputSin").style.display="none";
			document.parentWindow.parent.document.getElementById("inputCareg").style.visibility="hidden";
			document.parentWindow.parent.document.getElementById("inputCareg").style.display="none";
			document.parentWindow.parent.document.getElementById("inputCon").style.visibility="hidden";
			document.parentWindow.parent.document.getElementById("inputCon").style.display="none";	
				
			if(nTipoRed==1){
				document.getElementById("divPlanTarifario").style.visibility="visible";
				document.getElementById("divPlanTarifario").style.display="block";
				document.getElementById("encabezados").style.visibility="visible";
				document.getElementById("encabezados").style.display="block";
				document.getElementById("rCon").style.visibility="visible";
				document.getElementById("rCon").style.display="block";
			}
			if(nTipoRed==2){
				document.getElementById("divAmigoChip").style.visibility="visible";
				document.getElementById("divAmigoChip").style.display="block";
				document.getElementById("encabezados").style.visibility="visible";
				document.getElementById("encabezados").style.display="block";
				document.getElementById("rAca").style.visibility="visible";
				document.getElementById("rAca").style.display="block";	
			}
			if(nTipoRed==3){
				document.getElementById("divAmigoKit").style.visibility="visible";
				document.getElementById("divAmigoKit").style.display="block";
				document.getElementById("encabezados").style.visibility="visible";
				document.getElementById("encabezados").style.display="block";
				document.getElementById("rSin").style.visibility="visible";
				document.getElementById("rSin").style.display="block";
			}
			if(nTipoRed==4){
				document.getElementById("divT3G").style.visibility="visible";
				document.getElementById("divT3G").style.display="block";
				document.getElementById("encabezados").style.visibility="visible";
				document.getElementById("encabezados").style.display="block";
				document.getElementById("rT3G").style.visibility="visible";
				document.getElementById("rT3G").style.display="block";
			}										
			loadDatos=true;
		}
	}
	
		
</script>

</head>

<body onload="getDatos();">
<form name="frmFolios" id="frmFolios" method="post">
<DIV id="encabezados" style="visibility: hidden;display: none;background-color: transparent; position: absolute;top: 3px;left: 3px;height: 15px;width: 820px;">
	<table width="98%" border="0" cellspacing="0" cellpadding="0" align="center" style="top: 1px">
		<tr style="visibility: hidden; display: none;" id="rCon">
		<td align="left" class="textonegroBlodTrs" style="color: red;">&nbsp;Consulta de Folio para Equipos en Plan Tarifario</td>
		<tr style="visibility: hidden; display: none;" id="rAca">
		<td align="left" class="textonegroBlodTrs" style="color: red;">&nbsp;Consulta de Folio para Amigo Chip</td>
		<tr style="visibility: hidden; display: none;" id="rSin">
		<td align="left" class="textonegroBlodTrs" style="color: red;">&nbsp;Consulta de Folio para Equipos en Amigo Kit</td>
		<tr style="visibility: hidden; display: none;" id="rT3G">
		<td align="left" class="textonegroBlodTrs" style="color: red;">&nbsp;Consulta de Folio para Tarjetas 3G</td>
	</table>
</DIV>

<c:choose>
<c:when test="${nTipoRed=='1'}">
<DIV id="divPlanTarifario" style="visibility: hidden;display: none;background-color: transparent; top: 20px;left: 3px;width: 920px; height: 240px; position: absolute; OVERFLOW-Y:scroll; OVERFLOW-X:;">
	<table width="98%" border="1" cellpadding="0" cellspacing="0" align="center">
		<tr bgcolor="#ECF0DB" class="healineblue1" align="center">
			<td width="7%" rowspan="2">Folio</td>
			<td width="10%" rowspan="2">Fecha</td>
			<td width="12%" rowspan="2">Descripción</td>
			<td width="8%" rowspan="2">Marca</td>
			<td width="8%" rowspan="2">Modelo</td>				
			<td width="23%" colspan="2">IMEI</td>	
			<td width="45%" style="visibility: hidden; display: none;">ICCID</td>		
			<td width="8%" rowspan="2">Puntos</td>
			<td width="12%" rowspan="2">Valor Pesos</td>			
		</tr>
		<tr bgcolor="#ECF0DB" class="healineblue1">			
			<td align="center" style="font-size: x-small">Eq. Tarifario</td>
			<td align="center" style="font-size: x-small">Eq. Promoción</td>
		</tr>
        <c:forEach var="folios" items="${folios}" varStatus="total" >
        <c:set var="contador" value="${total.count}"/>   
           <tr class="textonegroBlod">
           		<td align="left">
           			<input type="radio" id="idfolio" name="idfolio" value="${folios.folio}" 
           			onclick="returnFolio(${contador});" disabled="disabled">${folios.folio}
           		</td>
           		<td align="center">${folios.fechaOperacion}</td>
           		<td align="center">${folios.productosTO.descripcion}</td>
           		<td align="center">${folios.productosTO.marca}</td>
           		<td align="center">${folios.productosTO.modelo}</td>
           		<td ><input type="text" size="16" maxlength="15" value="${folios.productosTO.numeroSerieT}" onclick="returnFolio(${contador});" onmousedown="returnFolio(${contador});" onmouseup="returnFolio(${contador});"
           			id="${contador}_serieT" name="${contador}_serieT" style="color: blue; border: thin; font: bolder;" >
           		</td>
           		<td ><input type="text" size="16" maxlength="15" value="${folios.productosTO.numeroSerieP}" onclick="returnFolio(${contador});" onmousedown="returnFolio(${contador});" onmouseup="returnFolio(${contador});"
           			id="${contador}_serieP" name="${contador}_serieP" style="color: blue; border: thin; font: bolder;" onclick="returnFolio(${contador});">
           		</td>           	
           		<td align="center" style="visibility: hidden; display: none">
           			<input type="text" size="20" maxlength="18" value="${folios.productosTO.iccid}" 
           			id="${contador}_iccid" name="${contador}_iccid"></td>	 
           		<td align="center">${folios.productosTO.valorPuntos}</td>
           		<td align="center">$${folios.productosTO.difPesos}</td>
           		<td align="center" style="visibility: hidden; display: none">
           		<input type="hidden" name="${contador}_tecnologia" id="${contador}_tecnologia" value="${folios.productosTO.tecnologia}">           		
           		</td>
           </tr> 
        </c:forEach>
	</table>	
</DIV>
</c:when>
</c:choose>


<c:choose>
<c:when test="${nTipoRed=='2'}">
<DIV id="divAmigoChip" style="visibility: hidden;display: none;background-color: transparent; position: absolute;top: 15px;left: 3px;width: 920px; height: 240px; OVERFLOW-Y:scroll; OVERFLOW-X: hidden;">
	<table width="95%" border="1" cellpadding="0" cellspacing="0" align="center">
		<tr bgcolor="#ECF0DB" class="healineblue1" align="center">
			<td width="10%">Folio</td>
			<td width="10%">Fecha</td>
			<td width="22%">Descripción</td>	
			<td width="2%" style="visibility: hidden; display: none;">IMEI</td>						
			<td width="22%">ICCID</td>
			<td width="10%">Valor Puntos</td>
			<td width="10%">Valor Pesos</td>			
		</tr>		
        <c:forEach var="folios" items="${folios}" varStatus="total">
        <c:set var="contador" value="${total.count}"/>   
           <tr class="textonegroBlod">
           		<td align="left">
           		<input type="radio" id="idfolio" name="idfolio" disabled="disabled" value="${folios.folio}" onclick="returnFolio(${contador});" >${folios.folio}
           		</td>
           		<td align="center">${folios.fechaOperacion}</td>
           		<td align="center">${folios.productosTO.descripcion}</td> 
           		<td style="visibility: hidden; display: none">
           			Eq.Tarifario:<input type="text" size="16" maxlength="15" value="${folios.productosTO.numeroSerieT}" 
           			id="${contador}_serieT" name="${contador}_serieT" style="color: blue; border: thin; font: bolder;" onclick="returnFolio(${contador});">
           			Eq.Promoción:<input type="text" size="16" maxlength="15" value="${folios.productosTO.numeroSerieP}" 
           			id="${contador}_serieP" name="${contador}_serieP" style="color: blue; border: thin; font: bolder;" onclick="returnFolio(${contador});">
           		</td>    
           		<td align="center"><input type="text" size="20" maxlength="18" value="${folios.productosTO.iccid}" onmousedown="returnFolio(${contador});" onmouseup="returnFolio(${contador});"
           		id="${contador}_iccid" name="${contador}_iccid" onclick="returnFolio(${contador});" style="color: blue; border: thin; font: bolder;"></td>         		
           		<td align="center">${folios.productosTO.valorPuntos}</td>
           		<td align="center">$${folios.productosTO.difPesos}</td>
           		<td align="center" style="visibility: hidden; display: none">
           		<input type="hidden" name="${contador}_tecnologia" id="${contador}_tecnologia" value="${folios.productosTO.tecnologia}">           		
           		</td>
           </tr>
        </c:forEach>
	</table>
</DIV>
</c:when>
</c:choose>


<c:choose>
<c:when test="${nTipoRed=='3'}">	
<DIV id="divAmigoKit" style="visibility: hidden;display: none;background-color: transparent; position: absolute;top: 15px;left: 3px;width: 920px; height: 240px; OVERFLOW-Y:scroll; OVERFLOW-X: hidden;">
	<table width="98%" border="1" cellpadding="0" cellspacing="0" align="center">
		<tr bgcolor="#ECF0DB" class="healineblue1" align="center">
			<td width="7%" rowspan="2">Folio</td>
			<td width="10%" rowspan="2">Fecha</td>
			<td width="15%" rowspan="2">Descripción</td>
			<td width="8%" rowspan="2">Marca</td>
			<td width="8%" rowspan="2">Modelo</td>			
			<td width="23%" colspan="2">IMEI</td>	
			<td width="45%" style="visibility: hidden; display: none;">ICCID</td>		
			<td width="8%" rowspan="2">Puntos</td>
			<td width="8%" rowspan="2">Valor Pesos</td>			
		</tr>
		
		<tr bgcolor="#ECF0DB" class="healineblue1">			
			<td align="center" style="font-size: x-small">Eq. Tarifario</td>
			<td align="center" style="font-size: x-small">Eq. Promoción</td>
		</tr>
		
        <c:forEach var="folios" items="${folios}" varStatus="total" >
        <c:set var="contador" value="${total.count}"/>   
           <tr class="textonegroBlod">
           		<td align="left">
           			<input type="radio" id="idfolio" name="idfolio" value="${folios.folio}" 
           			onclick="returnFolio(${contador});" disabled="disabled">${folios.folio}
           		</td>
           		<td align="center">${folios.fechaOperacion}</td>
           		<td align="center">${folios.productosTO.descripcion}</td>
           		<td align="center">${folios.productosTO.marca}</td>
           		<td align="center">${folios.productosTO.modelo}</td>
           		<td>
	           		<input type="text" size="16" maxlength="15" value="${folios.productosTO.numeroSerieT}" onmousedown="returnFolio(${contador});" onmouseup="returnFolio(${contador});"
	           			id="${contador}_serieT" name="${contador}_serieT" style="color: blue; border: thin; font: bolder;" onclick="returnFolio(${contador});">
           		</td>
           		<td>
           			<input type="text" size="16" maxlength="15" value="${folios.productosTO.numeroSerieP}" onmousedown="returnFolio(${contador});" onmouseup="returnFolio(${contador});" 
           			id="${contador}_serieP" name="${contador}_serieP" style="color: blue; border: thin; font: bolder;" onclick="returnFolio(${contador});">
           		</td>           	
           		<td align="center" style="visibility: hidden; display: none">
           			<input type="text" size="20" maxlength="18" value="${folios.productosTO.iccid}" 
           			id="${contador}_iccid" name="${contador}_iccid"></td>	 
           		<td align="center">${folios.productosTO.valorPuntos}</td>
           		<td align="center">$${folios.productosTO.difPesos}</td>
           		<td align="center" style="visibility: hidden; display: none">
           		<input type="hidden" name="${contador}_tecnologia" id="${contador}_tecnologia" value="${folios.productosTO.tecnologia}">           		
           		</td>
           </tr> 
        </c:forEach>        
	</table>
</DIV>
</c:when>
</c:choose>

<c:choose>
<c:when test="${nTipoRed=='4'}">
<DIV id="divT3G" style="visibility: hidden;display: none;background-color: transparent; position: absolute;top: 15px;left: 3px;width: 920px; height: 240px; OVERFLOW-Y:scroll; OVERFLOW-X: hidden;">
	<table width="98%" border="1" cellpadding="0" cellspacing="0" align="center">
		<tr bgcolor="#ECF0DB" class="healineblue1" align="center">
			<td width="8%">Folio</td>
			<td width="12%">Fecha</td>
			<td width="12%">Descripción</td>
			<td width="8%">Marca</td>
			<td width="8%">Modelo</td>
			<td width="23%">IMEI</td>	
			<td width="47%" style="visibility: hidden; display: none;">ICCID</td>		
			<td width="10%">Valor Puntos</td>
			<td width="10%">Valor Pesos</td>			
		</tr>
        <c:forEach var="folios" items="${folios}" varStatus="total" >
        <c:set var="contador" value="${total.count}"/>   
           <tr class="textonegroBlod">
           		<td align="left">
           			<input type="radio" id="idfolio" name="idfolio" value="${folios.folio}" 
           			onclick="returnFolio(${contador});" disabled="disabled">${folios.folio}
           		</td>
           		<td align="center">${folios.fechaOperacion}</td>
           		<td align="center">${folios.productosTO.descripcion}</td>
           		<td align="center">${folios.productosTO.marca}</td>
           		<td align="center">${folios.productosTO.modelo}</td>
           		<td align="center" ><input type="text" size="16" maxlength="15" value="${folios.productosTO.numeroSerieT}" onmousedown="returnFolio(${contador});" onmouseup="returnFolio(${contador});"
           			id="${contador}_serieT" name="${contador}_serieT" style="color: blue; border: thin; font: bolder;" onclick="returnFolio(${contador});">
           			<input type="text" size="16" maxlength="15" value="${folios.productosTO.numeroSerieP}" 
           			id="${contador}_serieP" name="${contador}_serieP" style="color: blue; border: thin; font: bolder; visibility: visible; display: none;" onclick="returnFolio(${contador});">
           		</td>           	
           		<td align="center" style="visibility: hidden; display: none">
           			<input type="text" size="20" maxlength="18" value="${folios.productosTO.iccid}" 
           			id="${contador}_iccid" name="${contador}_iccid"></td>	 
           		<td align="center">${folios.productosTO.valorPuntos}</td>
           		<td align="center">$${folios.productosTO.difPesos}</td>
           		<td align="center" style="visibility: hidden; display: none">
           		<input type="hidden" name="${contador}_tecnologia" id="${contador}_tecnologia" value="${folios.productosTO.tecnologia}">           		
           		</td>
           </tr> 
        </c:forEach>        
	</table>
</DIV>
</c:when>
</c:choose>

<c:if test="${nTipoRed=='1' }">
	<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="130">
		<DIV id="divActualiza" style="visibility: visible;display: block;background-color: transparent; position: absolute;top: 250px;left: 3px;width: 920px; height: 40px; ">
			<table width="25%" border="0" cellspacing="0" cellpadding="0" align="center">
				<tr height="10%" style="height: 15px"><td width="15%" class="healineblue1" align="left"/></tr>
				<tr><td align="center" width="50%">
					<a class="LinkOut" style="font-size: 12px" onmouseover='this.className="LinkIn";' onmouseout='this.className="LinkOut";' id="linkActualizar" onclick="actualizaFolio()">&nbsp;Actualizar&nbsp;&nbsp;</a>
					</td>
				</tr>
			</table>	
		</DIV>
	</securityCa:validaPerfil>
</c:if>
<c:if test="${nTipoRed=='2' }">
	<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="131">
		<DIV id="divActualiza" style="visibility: visible;display: block;background-color: transparent; position: absolute;top: 250px;left: 3px;width: 920px; height: 40px; ">
			<table width="25%" border="0" cellspacing="0" cellpadding="0" align="center">
				<tr height="10%" style="height: 15px"><td width="15%" class="healineblue1" align="left"/></tr>
				<tr><td align="center" width="50%">
					<a class="LinkOut" style="font-size: 12px" onmouseover='this.className="LinkIn";' onmouseout='this.className="LinkOut";' id="linkActualizar" onclick="actualizaFolio()">&nbsp;Actualizar&nbsp;&nbsp;</a>
					</td>
				</tr>
			</table>	
		</DIV>
	</securityCa:validaPerfil>
</c:if>
<c:if test="${nTipoRed=='3' }">
	<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="132">
		<DIV id="divActualiza" style="visibility: visible;display: block;background-color: transparent; position: absolute;top: 250px;left: 3px;width: 920px; height: 40px; ">
			<table width="25%" border="0" cellspacing="0" cellpadding="0" align="center">
				<tr height="10%" style="height: 15px"><td width="15%" class="healineblue1" align="left"/></tr>
				<tr><td align="center" width="50%">
					<a class="LinkOut" style="font-size: 12px" onmouseover='this.className="LinkIn";' onmouseout='this.className="LinkOut";' id="linkActualizar" onclick="actualizaFolio()">&nbsp;Actualizar&nbsp;&nbsp;</a>
					</td>
				</tr>
			</table>	
		</DIV>
	</securityCa:validaPerfil>
</c:if>
<c:if test="${nTipoRed=='4' }">
	<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="133">
		<DIV id="divActualiza" style="visibility: visible;display: block;background-color: transparent; position: absolute;top: 250px;left: 3px;width: 920px; height: 40px; ">
			<table width="25%" border="0" cellspacing="0" cellpadding="0" align="center">
				<tr height="10%" style="height: 15px"><td width="15%" class="healineblue1" align="left"/></tr>
				<tr><td align="center" width="50%">
					<a class="LinkOut" style="font-size: 12px" onmouseover='this.className="LinkIn";' onmouseout='this.className="LinkOut";' id="linkActualizar" onclick="actualizaFolio()">&nbsp;Actualizar&nbsp;&nbsp;</a>
					</td>
				</tr>
			</table>	
		</DIV>
	</securityCa:validaPerfil>
</c:if>

</form>
</body>
</html>