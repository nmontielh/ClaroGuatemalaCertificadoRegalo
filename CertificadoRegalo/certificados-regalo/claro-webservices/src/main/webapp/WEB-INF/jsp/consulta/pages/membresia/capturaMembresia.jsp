<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/permisos.tld"  prefix="securityCa"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Captura Membresia</title>
<link rel="stylesheet" href='<c:url value="/commons/js/sc_textsheet.css"/>' type="text/css">
<script type="text/javascript">

function guardaPromocion(){
	if(trim(frmMembresia.nombre.value) == ""){
    	window.alert("Debe capturar información en el campo Nombre(s).")
    	return false;
 	}
 	if(trim(frmMembresia.apPaterno.value) == ""){
    	window.alert("Debe capturar información en el campo Apellido Paterno.")
		frmMembresia.apePaterno.focus();
    	return false;
 	}
 	if(trim(frmMembresia.apMaterno.value) == ""){
    	window.alert("Debe capturar información en el campo Apellido Materno.")
    	return false;
 	}
 	if(trim(frmMembresia.tipoCalle.value) == ""){
    	window.alert("Debe seleccionar el Tipo de Calle.")
    	return false;
 	}
 	if(trim(frmMembresia.calle.value) == ""){
    	window.alert("Debe capturar información en el campo Calle.")
    	return false;
 	}
 	if(trim(frmMembresia.numExterior.value) == ""){
    	window.alert("Debe capturar información en el campo No. exterior.")
    	return false;
 	}
 	if(trim(frmMembresia.colonia.value) == ""){
    	window.alert("Debe capturar información en el campo Colonia.")
    	return false;
 	}
 	if(trim(frmMembresia.codigoPostal.value) == ""){
    	window.alert("Debe capturar información en el C.P.")
    	return false;
 	}
 	if(trim(frmMembresia.ciudad.value) == ""){
    	window.alert("Debe capturar información en el campo Ciudad.")
    	return false;
 	}
 	if(trim(frmMembresia.estado.value) == ""){
    	window.alert("Debe seleccionar el Estado.")
    	return false;
 	}
 	if(trim(frmMembresia.motivo.value) == ""){
    	window.alert("Debe seleccionar el motivo de la reposición.")
    	return false;
 	}
 	if(frmMembresia.costo(0).checked == false && frmMembresia.costo(1).checked == false){
    	window.alert("Debe indicar si el motivo de la solicitud tiene costo.")
    	return false;
 	}
 	if(frmMembresia.motivo.value == "Otros"){
		if(trim(frmMembresia.otroMotivo.value) == ""){
			alert("Debe capturar el motivo de la reposición.");
			frmMembresia.motivo.focus();
			return false;
		}
 	}
 	if(validaCodigoPostal(trim(document.getElementById('codigoPostal').value))){
 		var form = document.getElementById('frmMembresia');
 		form.submit();
 	}else{
		return false;
	}
}

function on_motivo_select(){
	if(frmMembresia.motivo.value == "Nunca recibió su tarjeta" || frmMembresia.motivo.value == "Le llegó la tarjeta errónea"){
		alert("Revise si está correcta la información del cliente en M2K. Esta es una solicitud sin costo para el cliente.");
		frmMembresia.otroMotivo.disabled=true;
		frmMembresia.otroMotivo.value="";
		frmMembresia.costo(1).disabled=false;
		frmMembresia.costo(1).checked=true;
		frmMembresia.costo(0).disabled=true;
		return true;
	}
	if(frmMembresia.motivo.value == "Cambió de plan" || frmMembresia.motivo.value == "A petición del usuario"){
		alert("Revise si está correcta la información del cliente en M2K. Esta es una solicitud con costo para el cliente.\nAsegúrese de generar el cargo de $57.50 IVA incluido.");
		frmMembresia.otroMotivo.value="";
		frmMembresia.otroMotivo.disabled=true;
		frmMembresia.costo(0).disabled=false;
		frmMembresia.costo(0).checked=true;
		frmMembresia.costo(1).disabled=true;
		return true;
	}
	if(frmMembresia.motivo.value == "Otros"){		
		frmMembresia.otroMotivo.disabled=false;
		frmMembresia.costo(0).disabled=false;
		frmMembresia.costo(1).disabled=false;
		frmMembresia.costo(0).checked=false;
		frmMembresia.costo(1).checked=false;
		return true;
	}
}

function trim(str) {
	while(str.charAt(str.length - 1) == " ")
		str = str.substring(0,str.length - 1);
	while(str.charAt(0) == " ")
		str = str.substring(1,str.length);
	return str;
}

function isPositiveInteger(str) {
	var pattern = "0123456789";
	var i = 0;
	do { 
		var pos = 0;
		for(var j=0; j<pattern.length; j++)
			if(str.charAt(i)==pattern.charAt(j)) { 
				pos = 1;
				break;
			}
		i++;
	} while(pos==1 &&  i<str.length)
	if(pos == 0 )
		return false;
	return true;
}

function validaCodigoPostal(lCodigo){
	var sCodigoPostal = trim(lCodigo);
	if(isPositiveInteger(sCodigoPostal) == false){
		alert("El código postal debe ser un número entero positivo.");
		return false;
	}
	return true;
}
</script>
</head>
<body>
	<script>
   		document.parentWindow.parent.activaCargando('hidden','none'); 
	</script>
	
	<table>     	
        <tr>            	          
          	<td class="titulo" height="42" width="30%" align="left">&nbsp;&nbsp;Captura Datos de Membresía</td>
       	</tr>
    </table>
    <center>    
    <form name="frmMembresia" id="frmMembresia" method="post" action="./guardaMembresia.do">
    <input type="hidden" name="secuencia" id="secuencia" value="${telefonoTO.mobileTO.secuencia}">
    <input type="hidden" name="nombreM2K" id="nombreM2K" value="${telefonoTO.mobileTO.nombre}">
    <input type="hidden" name="fechaAltaM2K" id="fechaAltaM2K" value="${telefonoTO.mobileTO.fecAltaUser}">
    <input type="hidden" name="segmento" id="segmento" value="${telefonoTO.segmento}">
    <input type="hidden" name="plan" id="plan" value="${telefonoTO.mobileTO.planM2K}">
    <input type="hidden" name="descPlan" id="descPlan" value="${telefonoTO.descripcionPlan}">    
    
	<table>
		<tr>
			<td class="healineblue1" 	align="left">&nbsp;Teléfono:</td>
			<td class="textonegroBlod" 	align="left"><input class="InputText" type="text" value="${telefonoTO.mobileTO.telefono}" name="telefono" id="telefono" readonly> </td>
			<td class="healineblue1"  	align="left">&nbsp;Cuenta:</td>
			<td class="textonegroBlod" 	align="left"><input class="InputText" type="text" value="${telefonoTO.mobileTO.cuenta}" name="cuenta" id="cuenta"  readonly></td>
			<td class="healineblue1" 	align="left">&nbsp;Región</td>
			<td class="textonegroBlod" 	align="left"><input class="InputText" type="text" value="${telefonoTO.region}" name="region" id="region" readonly></td>
		</tr>			
		<tr>
			<td class="healineblue1" 	align="left">&nbsp;Nombre(s):</td>
			<td class="textonegroBlod" 	align="left"><input class="InputText" type="text" name="nombre" id="nombre"></td>
			<td class="healineblue1" 	align="left">&nbsp;Apellido Paterno:</td>
			<td class="textonegroBlod" 	align="left"><input class="InputText" type="text" name="apPaterno" id="apPaterno"> </td>
			<td class="healineblue1" 	align="left">&nbsp;Apellido Materno:</td>
			<td class="textonegroBlod" 	align="left"><input class="InputText" type="text" name="apMaterno" id="apMaterno"></td>			
		</tr>
		<tr>
			<td class="healineblue1" 	align="left">&nbsp;Tipo de Calle:</td>
			<td class="textonegroBlod" 	align="left">	<select name="tipoCalle" size=1>
						<option selected value=""></option>
						<option value="AND">AND</option>
						<option value="AUTO">AUTO</option>
						<option value="AVE">AVE</option>
						<option value="BLVD">BLVD</option>						
						<option value="CALZ">CALZ</option>
						<option value="CAM">CAM</option>
						<option value="CARR">CARR</option>
						<option value="CDA">CDA</option>
						<option value="CIRC">CIRC</option>
						<option value="CJON">CJON</option>
						<option value="CLLE">CLLE</option>
						<option value="DIAG">DIAG</option>
						<option value="PRIV">PRIV</option>
						<option value="PROL">PROL</option>						
						<option value="PZA">PZA</option>						
						<option value="RCDA">RCDA</option>
						<option value="RCON">RCON</option>
						<option value="RTNO">RTNO</option>
						<option value="VIAD">VIAD</option>
					  </select></td>
			<td class="healineblue1" 	align="left">&nbsp;Calle:</td>
			<td class="textonegroBlod" 	align="left"><input class="InputText" type="text" name="calle" id="calle"> </td>
					  
			<td class="healineblue1" 	align="left">&nbsp;No. Exterior:</td>
			<td class="textonegroBlod" 	align="left"><input class="InputText" type="text" name="numExterior" id="numExterior"></td>
		</tr>
		<tr>
			<td class="healineblue1" 	align="left">&nbsp;No. Interior:</td>
			<td class="textonegroBlod" 	align="left"><input class="InputText" type="text" name="numInterior" id="numInterior"> </td>
			<td class="healineblue1" 	align="left">&nbsp;Colonia:</td>
			<td class="textonegroBlod" 	align="left"><input class="InputText" type="text" name="colonia" id="colonia"></td>
			<td class="healineblue1" 	align="left">&nbsp;C.P.</td>
			<td class="textonegroBlod" 	align="left"><input class="InputText" type="text" name="codigoPostal" id="codigoPostal"></td>
		</tr>
		<tr>
			<td class="healineblue1" 	align="left">&nbsp;Ciudad:</td>
			<td class="textonegroBlod" 	align="left"><input class="InputText" type="text" name="ciudad" id="ciudad"> </td>
			<td class="healineblue1" 	align="left">&nbsp;Estado:</td>
			<td class="textonegroBlod" 	align="left">
					<select name="estado" size=1>
						<option selected value=""></option>
						<option value="AGC">Aguascalientes</option>
						<option value="BCN">Baja California</option>
						<option value="BCS">Baja California Sur</option>
						<option value="CMP">Campeche</option>
						<option value="CHP">Chiapas</option>
						<option value="CHI">Chihuahua</option>						
						<option value="COA">Coahuila</option>
						<option value="COL">Colima</option>
						<option value="DIF">Distrito Federal</option>
						<option value="DGO">Durango</option>
						<option value="MEX">Estado de México</option>
						<option value="GTO">Guanajuato</option>
						<option value="GRO">Guerrero</option>
						<option value="HGO">Hidalgo</option>
						<option value="JCO">Jalisco</option>
						<option value="MCN">Michoacán</option>
						<option value="MOR">Morelos</option>
						<option value="NYT">Nayarit</option>
						<option value="NLN">Nuevo León</option>
						<option value="OXC">Oaxaca</option>
						<option value="PBL">Puebla</option>
						<option value="QRO">Querétaro</option>
						<option value="QNR">Quintana Roo</option>
						<option value="SLP">San Luis Potosí</option>
						<option value="SNL">Sinaloa</option>
						<option value="SON">Sonora</option>
						<option value="TBC">Tabasco</option>
						<option value="TMP">Tamaulipas</option>
						<option value="TCL">Tlaxcala</option>
						<option value="VCZ">Veracruz</option>
						<option value="YTN">Yucatán</option>
						<option value="ZCT">Zacatecas</option>
					  </select>
					  </td>
			<td class="healineblue1" 	align="left">&nbsp;País:</td>
			<td class="textonegroBlod" 	align="left"><input type="text" class="InputText" name="pais" id="pais"></td>
		</tr>
		<tr>
			<td class="healineblue1" 	align="left">&nbsp;Motivo:</td>
			<td class="textonegroBlod" 	align="left">
					<select name="motivo" size=1  onchange="return on_motivo_select();">
                      <option value="" selected>&nbsp;</option>
                      <option value="Nunca recibió su tarjeta">Nunca recibió su tarjeta</option>
                      <option value="Cambió de plan">Cambió de plan</option>
                      <option value="Le llegó la tarjeta errónea">Le llegó la tarjeta errónea</option>
                      <option value="A petición del usuario">A petición del usuario</option>
                      <option value="Otros">Otros</option>
                    </select>
              </td>
			<td align="left" colspan="2"><input class="InputText" type="text" name="otroMotivo" id="otroMotivo" disabled style="width: 100%;"></td>
			<td class="healineblue1" 	align="left">&nbsp;Costo:</td>
			<td class="healineblue1" 	align="left"><input type="radio" id="costo" name="costo" value="1">SI
                  									<input type="radio" id="costo" name="costo" value="0">NO</td>
		</tr>		
	</table>
	<span style="text-align: center">
		<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="74">
			<a  class="LinkOut" style="width:40%" onmouseover='this.className="LinkIn";' onmouseout='this.className="LinkOut";'
						id="Link1" onClick="guardaPromocion()">&nbsp;&nbsp;&nbsp;Agregar&nbsp;&nbsp;</a>
		</securityCa:validaPerfil>
	</span>
	</form>
	</center>
</body>
</html>