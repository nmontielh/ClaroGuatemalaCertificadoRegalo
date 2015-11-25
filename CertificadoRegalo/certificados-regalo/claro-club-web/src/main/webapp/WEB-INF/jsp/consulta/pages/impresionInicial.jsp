<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/permisos.tld" prefix="securityCa"%>

<html>
<head>
<script>
		var opcion = 1;
		
		function eligeOpcion(opt){
			if(opt==1){
				opcion = 1;
				document.getElementById("radioImprime").value = "redencion";
				var alianzatr = document.getElementById("alianzatr");
				if(alianzatr!=null){
					alianzatr.style.visibility="hidden";
					alianzatr.style.display="none";
				}
				var certificadoRadio = document.getElementById("certificadoRadio");
				if(certificadoRadio!=null){
					certificadoRadio.style.visibility="hidden";
					certificadoRadio.style.display="none";
				}
				var fecha = document.getElementById("fecha");
				if(fecha!=null){
					fecha.style.visibility="visible";
					fecha.style.display="block";
				}
				var fechatd = document.getElementById("fechatd");
				if(fechatd!=null){
					fechatd.style.visibility="visible";
					fechatd.style.display="block";
				}
			}
			if(opt==2){
				opcion = 2;
				document.getElementById("radioImprime").value = "redencion";
				var alianzatr = document.getElementById("alianzatr");
				if(alianzatr!=null){
					alianzatr.style.visibility="visible";
					alianzatr.style.display="block";
				}
				var alianza = document.getElementById("alianza");
				if(alianza!=null){
					if(alianza.value == "amex"){
						var certificadoRadio = document.getElementById("certificadoRadio");
						if(certificadoRadio!=null){
							certificadoRadio.style.visibility="visible";
							certificadoRadio.style.display="block";
						}
					}
				}
				var fecha = document.getElementById("fecha");
				if(fecha!=null){
					fecha.style.visibility="visible";
					fecha.style.display="block";
				}
				var fechatd = document.getElementById("fechatd");
				if(fechatd!=null){
					fechatd.style.visibility="visible";
					fechatd.style.display="block";
				}
			}
			if(opt==3){
				opcion = 3;
				document.getElementById("radioImprime").value = "radioInbursa";
				var alianzatr = document.getElementById("alianzatr");
				if(alianzatr!=null){
					alianzatr.style.visibility="hidden";
					alianzatr.style.display="none";
				}
				var certificadoRadio = document.getElementById("certificadoRadio");
				if(certificadoRadio!=null){
					certificadoRadio.style.visibility="hidden";
					certificadoRadio.style.display="none";
				}
				var fecha = document.getElementById("fecha");
				if(fecha!=null){
					fecha.style.visibility="hidden";
					fecha.style.display="none";
				}
				var fechatd = document.getElementById("fechatd");
				if(fechatd!=null){
					fechatd.style.visibility="hidden";
					fechatd.style.display="none";
				}
			}
		}
		
		function cambio2(){
			var alianza = document.getElementById("alianza");
			if(alianza!=null){			
				if(alianza.value=='amex'){
					//alert("es amex");
					var certificadoRadio = document.getElementById("certificadoRadio");
					if(certificadoRadio!=null){
						certificadoRadio.style.visibility="visible";
						certificadoRadio.style.display="block";
					}
				}else{
					//alert("no es amex");
					var certificadoRadio = document.getElementById("certificadoRadio");
					if(certificadoRadio!=null){
						certificadoRadio.style.visibility="hidden";
						certificadoRadio.style.display="none";
					}
				}			
			}						
		}

		function activaCargando(){
			//alert("activa cargando en impresion Inicial");
			document.parentWindow.parent.act_cargando();
			//alert("activa cargando en impresion Inicial FIN");
		}

		function desactivaCargando(){
			//alert("desactiva cargando en impresion Inicial");
			document.parentWindow.parent.des_cargando();
			//alert("desactiva cargando en impresion Inicial FIN");
		}
		
		function validaDatos(){
			var radioImprime = document.getElementsByName("radioImprime");
			if(radioImprime==null || radioImprime.length==0){
				window.alert("No tiene los privilegios necesarios para realizar esta operación.");
				return false;
			}
							
			var tel = document.getElementById("telefono").value;
			var cta = document.getElementById("cuenta").value;
			var fech = document.getElementById("fecha").value;
			var radioValor = document.getElementById("radioImprime").value;

			if(tel.length>0){
				if(isNaN(tel)){
					window.alert("Error en el formato de la linea");
					return false;
				}
				if(tel == "" || (fech == "" && radioValor != "radioInbursa")){
					window.alert("No pueden ir datos vacios");
					return false;
				}
				if(tel.length < 10){
					window.alert("El telefono debe ser de 10 digitos");
					return false;
				}
			}else{			
				if(isNaN(cta)){
					window.alert("Error en el formato de la cuenta");
					return false;
				}
				if(cta == "" || (fech == "" && radioValor != "radioInbursa")){
					window.alert("No pueden ir datos vacios");
					return false;
				}
			}
			
			document.parentWindow.parent.visualizaResultado();
			document.parentWindow.parent.frmContenedor.tel.value = document.getElementById("telefono").value;
			document.parentWindow.parent.frmContenedor.cta.value = document.getElementById("cuenta").value;
			
			//si es redencion
						
			if(opcion==1){
				activaCargando();
			
				frmconsultaImpresion.action="./impresionGeneral.do?telefono="+document.getElementById("telefono").value+
				"&fecha="+document.getElementById("fecha").value;
			}
			else
				if(opcion==3){
					activaCargando();
				
					frmconsultaImpresion.action="./impresionBonoInbursa.do?telefono="+document.getElementById("telefono").value+
					"&fecha="+document.getElementById("fecha").value;
				}else{//si es alianza
				
					if(document.getElementById("alianza").value == "amex"){
						if(frmconsultaImpresion.certificado[0].checked == false && frmconsultaImpresion.certificado[1].checked == false){
							alert("Debe seleccionar una opcion de impresion...");
							return false;
						}
						
						activaCargando();
						
						if(frmconsultaImpresion.certificado[0].checked == true){
							document.getElementById("certificado").value = 1;
							frmconsultaImpresion.action="./impresion.do?telefono="+document.getElementById("telefono").value+
								"&fecha="+document.getElementById("fecha").value+"&certificado="+document.getElementById("certificado").value;
						}else{
							frmconsultaImpresion.action="./impresionAlianzas.do?telefono="+document.getElementById("telefono").value+
								"&fecha="+document.getElementById("fecha").value+"&alianza="+document.getElementById("alianza").value;
						}
					}else {alert("Tipo de alianza no valida....."); return false;}
				}
				frmconsultaImpresion.submit();
			//	desactivaCargando();
		}
	
	function resetField(fieldName){
		document.getElementById(fieldName).value=""; 
	}	
	

</script>
<title>Impresion de Redenciones</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href='<c:url value="/commons/js/sc_textsheet.css"/>' type="text/css">
<link rel="stylesheet"
	href='<c:url value="/commons/css/calendar-blue.css"/>' type="text/css">
<script src='<c:url value="/commons/js/calendar.js"/>'
	type="text/javascript"></script>
<script src='<c:url value="/commons/js/calendar-es.js"/>'
	type="text/javascript"></script>
<script src='<c:url value="/commons/js/calendar-setup.js"/>'
	type="text/javascript"></script>
</head>
<body marginwidth="0" marginheight="0" 
	style="MARGIN: 0px; background-color: transparent; border: none;">
<script>	
    document.parentWindow.parent.activaCargando('hidden','none'); 
  </script>
<script type="text/javascript">
	window.onload = function() {
  	Calendar.setup({
    	inputField: "fecha",
    	ifFormat:   "%d/%m/%Y",
    	button:     "fecha"
  		});
	}
	</script>
<form id="frmconsultaImpresion" name="frmconsultaImpresion"
	target="inferior" method="get" AUTOCOMPLETE='OFF'><input
	type="hidden" name="opcionS" value="buscar" id="opcionS">
<div>
<table width="100%" cellspacing="0" cellpadding="0" align="center">
	<tr>
		<td colspan="3" class="titulo" height="42" width="100%">&nbsp;&nbsp;Impresión
		de redenciones</td>
	</tr>
	<tr>
	<td colspan="3">
		<table>
			<tr>
		<td><securityCa:validaPerfil
			perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="27">
			<input type="radio" name="radioImprime" id="radioImprime"
				value="redencion" checked="checked" onclick="eligeOpcion(1)">
			<font class="textonegroBlod">Redención</font>
		</securityCa:validaPerfil></td>

		<td><securityCa:validaPerfil
			perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="28">
			<input type="radio" name="radioImprime" id="radioImprime"
				value="alianza" onclick="eligeOpcion(2)">
			<font class="textonegroBlod">Alianza</font>
		</securityCa:validaPerfil></td>
		<td><securityCa:validaPerfil
			perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="27">
			<input type="radio" name="radioImprime" id="radioImprime"
				value="bonoInbursa" onclick="eligeOpcion(3)">
			<font class="textonegroBlod">Bono Inbursa</font>
		</securityCa:validaPerfil></td>
		</tr>
		</table>
		</td>
	</tr>
	<tr>
			<tr>
				<td width="10%" height="25" class="healineblue1" align="left">Teléfono</td>
				<td width="10%" height="25" class="textonegroBlodTrs" align="left"><input
					type="text" id="telefono" name="telefono" maxlength="10"
					onkeypress="resetField('cuenta');"></td>
				<td width="10%" height="25" class="healineblue1" align="center">Cuenta</td>
				<td width="70%" height="25" class="textonegroBlodTrs" align="left"><input type="text"
					id="cuenta" name="cuenta" maxlength="10"
					onkeypress="resetField('telefono');"></td>
	</tr>
	<tr>
		<td width="10%" height="25" class="healineblue1" align="left"><div id="fechatd">Fecha</div></td>
		<td width="10%" height="25" align="left"><input type="text" name="fecha" id="fecha"
			readonly="readonly" /></td>
		<td width="10%" height="25" ></td>
		<td width="70%" height="25" align="center"><a class="LinkOut"
			style="width: 100px" onmouseover='this.className="LinkIn";'
			onmouseout='this.className="LinkOut";' id="Link1"
			onClick="validaDatos();">&nbsp;Continuar&nbsp;&nbsp;</a></td>
			
	</tr>
	<tr id="alianzatr" style="visibility: hidden;">
		<td width="10%" height="25" class="healineblue1" align="left">Alianza</td>
		<td width="10%" height="25" align="left"><select id="alianza" name="alianza"
			onchange="cambio2();">
			<option value="amex" selected>American Express</option>
			<%-- Eliminar opcion de Ticketmaster JAPA 10/02/2011 
	                        <option value="tickedmaster"  selected >Ticketmaster</option>--%>
		</select></td>
		<td width="10%" height="25" ></td>
		<td width="70%" height="25" ></td>
	</tr>
	<tr id="certificadoRadio" style="visibility: hidden;">
		<td colspan="2" width="50%" height="25" ><input type="radio" name="certificado" value="certificado"><font
			class="textonegroBlod">Imprimir certificado de viaje</font></td>
		<td colspan="2" width="50%" height="25" ><input type="radio" name="certificado" value="impresion"><font
			class="textonegroBlod">Impresion Constancia de Canje</font></td>
	</tr>
</table>
</div>
</form>
</body>
</html>