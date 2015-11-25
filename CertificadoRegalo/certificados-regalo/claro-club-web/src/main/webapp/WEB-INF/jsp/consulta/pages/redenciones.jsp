<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/permisos.tld" prefix="securityCa" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Redenciones</title>
<link rel="stylesheet" href='<c:url value="/commons/js/sc_textsheet.css"/>' type="text/css">
<script src='<c:url value="/commons/js/validacionesRedencion.js"/>' type="text/javascript"></script>

<script>
	var loadDatos=false;
	var banderaRedencion=false;
	var tipoReden;
	var tipoRedenS;
	
	function inicializa(){
		//Multicotizador II JAPA 15/03/2012  Folio 84048 y ZZZZZZ : Menu de selección para el tipo de redencion	
		<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="164">
			document.getElementById("MenuMulticotizador").style.visibility="visible";
			document.getElementById("MenuMulticotizador").style.display="block";
		</securityCa:validaPerfil>

		<c:choose>
		<c:when test="${aplicaMulticotizador}">
			frmAplicaRedencion.modoReden[1].checked = true;
		</c:when>
		<c:otherwise>
			frmAplicaRedencion.modoReden[0].checked = true;	
		</c:otherwise>
		</c:choose>
	
		var btnHistorico = document.getElementById("B01");
		var btnNueva = document.getElementById("B02");
		var btnCancelar = document.getElementById("B03");
		
		if(btnHistorico!=null){
			cambioOpcion(1);
			return;
		}
		if(btnNueva!=null){
			cambioOpcion(2);
			return;
		}
		if(btnCancelar!=null){
			cambioOpcion(3);
			return;
		}
	}	
	
	function cambioOpcion(opcion){
		if(opcion==1){
			document.getElementById("Tarjeta1").style.visibility="visible";
			document.getElementById("Tarjeta1").style.display="block";
			document.getElementById("Tarjeta2").style.visibility="hidden";			
			document.getElementById("Tarjeta2").style.display="none";	
			document.getElementById("Tarjeta3").style.visibility="hidden";
			document.getElementById("Tarjeta3").style.display="none";
			document.getElementById("TarjetaTA").style.visibility="hidden";
			document.getElementById("TarjetaTA").style.display="none";
						
		}else if(opcion==2){
			document.getElementById("TarjetaMulticotizador").style.visibility="hidden";
			document.getElementById("TarjetaMulticotizador").style.display="none";
			document.getElementById("Tarjeta3").style.visibility="hidden";
			document.getElementById("Tarjeta3").style.display="none";
			document.getElementById("Tarjeta1").style.visibility="hidden";
			document.getElementById("Tarjeta1").style.display="none";
			document.getElementById("Tarjeta2").style.visibility="visible";
			document.getElementById("Tarjeta2").style.display="block";	
			document.getElementById("TarjetaRedencionesCon").style.visibility="hidden";
			document.getElementById("TarjetaRedencionesCon").style.display="none";
			document.getElementById("TarjetaCareg").style.visibility="hidden";
			document.getElementById("TarjetaCareg").style.display="none";
			document.getElementById("TarjetaRedencionesS").style.visibility="hidden";
			document.getElementById("TarjetaRedencionesS").style.display="none";
			document.getElementById("TarjetaPromociones").style.visibility="hidden";
			document.getElementById("TarjetaPromociones").style.display="none";	
			document.getElementById("CampoObligatorio").style.visibility="hidden";
			document.getElementById("CampoObligatorio").style.display="none";
			document.getElementById("TarjetaInformacion").style.visibility="hidden";
			document.getElementById("TarjetaInformacion").style.display="none";		
			document.getElementById("btnContinuarCon").style.visibility="hidden";
			document.getElementById("btnContinuarCon").style.display="none";	
			document.getElementById("btnContinuarCareg").style.visibility="hidden";
			document.getElementById("btnContinuarCareg").style.display="none";
			document.getElementById("btnContinuarSin").style.visibility="hidden";
			document.getElementById("btnContinuarSin").style.display="none";
			document.getElementById("btnContinuarAmigoChip").style.visibility="hidden";
			document.getElementById("btnContinuarAmigoChip").style.display="none";
			document.getElementById("btnContinuarT3G").style.visibility="hidden";
			document.getElementById("btnContinuarT3G").style.display="none";
			document.getElementById("TarjetaFolios").style.visibility="hidden";
			document.getElementById("TarjetaFolios").style.display="none";
			document.getElementById("TarjetaFoliosSap").style.visibility="hidden";
			document.getElementById("TarjetaFoliosSap").style.display="none";			
			document.getElementById("TarjetaTA").style.visibility="hidden";
			document.getElementById("TarjetaTA").style.display="none";
			activaMenu();	
			habilitaCampos();	
			limpiaCampos();
			limpiaCamposCareg();
			getDatos();
		}else if(opcion==3){
			document.getElementById("Tarjeta1").style.visibility="hidden";			
			document.getElementById("Tarjeta1").style.display="none";	
			document.getElementById("Tarjeta2").style.visibility="hidden";
			document.getElementById("Tarjeta2").style.display="none";
			document.getElementById("Tarjeta3").style.visibility="visible";
			document.getElementById("Tarjeta3").style.display="block";
			document.getElementById("TarjetaTA").style.visibility="hidden";
			document.getElementById("TarjetaTA").style.display="none";
			var cuenta = document.parentWindow.parent.document.getElementById("cuenta").innerText;
			var secuencia = document.parentWindow.parent.document.getElementById("secuencia").value;
			var telefono = document.parentWindow.parent.document.getElementById("telefono").innerText;
			var region = document.parentWindow.parent.document.getElementById("nRegion").value;
			Frame3.location ="./cancelaRedenciones.do?secuencia="+secuencia+"&cuenta="+cuenta+"&region="+region+"&telefono="+telefono;		  				  				  				
		}			
	}

	function cambioSubMenu(opcion){		
		if(opcion==3){
			document.getElementById("TarjetaRedencionesCon").style.visibility="visible";
			document.getElementById("TarjetaRedencionesCon").style.display="block";
			document.getElementById("Link1").style.visibility="visible";
			document.getElementById("Link1").style.display="block";
			document.getElementById("TarjetaCareg").style.visibility="hidden";
			document.getElementById("TarjetaCareg").style.display="none";
			document.getElementById("TarjetaRedencionesS").style.visibility="hidden";
			document.getElementById("TarjetaRedencionesS").style.display="none";
			document.getElementById("TarjetaPromociones").style.visibility="hidden";
			document.getElementById("TarjetaPromociones").style.display="none";	
			document.getElementById("CampoObligatorio").style.visibility="visible";
			document.getElementById("CampoObligatorio").style.display="inline";
			document.getElementById("TarjetaInformacion").style.visibility="hidden";
			document.getElementById("TarjetaInformacion").style.display="none";
			document.getElementById("TarjetaFoliosSap").style.visibility="hidden";
			document.getElementById("TarjetaFoliosSap").style.display="none";	
			document.getElementById("TarjetaTA").style.visibility="hidden";
			document.getElementById("TarjetaTA").style.display="none";		
			limpiaCamposCareg();
			cambioModoReden(opcion);
					
		}else if(opcion==4) {
			document.getElementById("TarjetaRedencionesCon").style.visibility="hidden";
			document.getElementById("TarjetaRedencionesCon").style.display="none";
			document.getElementById("TarjetaCareg").style.visibility="hidden";
			document.getElementById("TarjetaCareg").style.display="none";
			document.getElementById("TarjetaRedencionesS").style.visibility="visible";
			document.getElementById("TarjetaRedencionesS").style.display="block";
			document.getElementById("TarjetaPromociones").style.visibility="hidden";
			document.getElementById("TarjetaPromociones").style.display="none";	
			document.getElementById("CampoObligatorio").style.visibility="hidden";
			document.getElementById("CampoObligatorio").style.display="none";
			document.getElementById("TarjetaInformacion").style.visibility="hidden";
			document.getElementById("TarjetaInformacion").style.display="none";
			document.getElementById("TarjetaFoliosSap").style.visibility="hidden";
			document.getElementById("TarjetaFoliosSap").style.display="none";
			document.getElementById("TarjetaTA").style.visibility="hidden";
			document.getElementById("TarjetaTA").style.display="none";
		}else if(opcion==5) {
			document.getElementById("TarjetaRedencionesCon").style.visibility="hidden";
			document.getElementById("TarjetaRedencionesCon").style.display="none";
			document.getElementById("TarjetaCareg").style.visibility="visible";
			document.getElementById("TarjetaCareg").style.display="block";
			document.getElementById("TarjetaRedencionesS").style.visibility="hidden";
			document.getElementById("TarjetaRedencionesS").style.display="none";
			document.getElementById("TarjetaPromociones").style.visibility="hidden";
			document.getElementById("TarjetaPromociones").style.display="none";	
			document.getElementById("CampoObligatorio").style.visibility="visible";
			document.getElementById("CampoObligatorio").style.display="inline";
			document.getElementById("TarjetaInformacion").style.visibility="hidden";
			document.getElementById("TarjetaInformacion").style.display="none";
			document.getElementById("TarjetaFoliosSap").style.visibility="hidden";
			document.getElementById("TarjetaFoliosSap").style.display="none";
			document.getElementById("TarjetaTA").style.visibility="hidden";
			document.getElementById("TarjetaTA").style.display="none";
			limpiaCampos();
			cambioModoReden(opcion);
		}else if(opcion==6) {
			document.getElementById("TarjetaRedencionesCon").style.visibility="hidden";
			document.getElementById("TarjetaRedencionesCon").style.display="none";
			document.getElementById("TarjetaCareg").style.visibility="hidden";
			document.getElementById("TarjetaCareg").style.display="none";
			document.getElementById("TarjetaRedencionesS").style.visibility="hidden";
			document.getElementById("TarjetaRedencionesS").style.display="none";
			document.getElementById("TarjetaPromociones").style.visibility="hidden";
			document.getElementById("TarjetaPromociones").style.display="none";	
			document.getElementById("CampoObligatorio").style.visibility="hidden";
			document.getElementById("CampoObligatorio").style.display="none";
			document.getElementById("TarjetaInformacion").style.visibility="hidden";			
			document.getElementById("TarjetaInformacion").style.display="none";
			document.getElementById("TarjetaFoliosSap").style.visibility="visible";
			document.getElementById("TarjetaFoliosSap").style.display="block";
			document.getElementById("TarjetaTA").style.visibility="hidden";
			document.getElementById("TarjetaTA").style.display="none";
		}				
	}
	
	function getDatos(){	
		if(!loadDatos){							
			document.getElementById("nRegion").value=document.parentWindow.parent.document.getElementById("nRegion").value;
			document.getElementById("adendum").value=document.parentWindow.parent.document.getElementById("adendum").innerText;
			document.getElementById("fecAddM2K").value=document.parentWindow.parent.document.getElementById("fecAddM2K").innerText;
			document.getElementById("cuenta").value=document.parentWindow.parent.document.getElementById("cuenta").innerText;
			document.getElementById("secuencia").value=document.parentWindow.parent.document.getElementById("secuencia").value;			
			document.getElementById("telefono").value=document.parentWindow.parent.document.getElementById("telefono").innerText;
			document.getElementById("estatusTel").value=document.parentWindow.parent.document.getElementById("estatusTel").innerText;
			document.getElementById("tecnologia").value=document.parentWindow.parent.document.getElementById("tecnologia").innerText;
			document.getElementById("bonoEquipo").value=document.parentWindow.parent.document.getElementById("bonoEquipo").value;
			document.getElementById("sPromFacturaAV").value=document.parentWindow.parent.document.getElementById("sPromFacturaAV").value;
			document.getElementById("motivo").value=document.parentWindow.parent.document.getElementById("motivo").value;
			document.getElementById("fecSuspension").value=document.parentWindow.parent.document.getElementById("fecSuspension").value;
			document.getElementById("planM2K").value=document.parentWindow.parent.document.getElementById("planM2K").value;
			document.getElementById("bAplicaRedencion").value=document.parentWindow.parent.document.getElementById("bAplicaRedencion").value;			
		
			/*Multicotizador II JAPA 28/03/2012  Folio 84048 y ZZZZZZ : Se elimina el div que muestra campos para TarjetaRedencionesCon y TarjetaCareg */
			document.getElementById("ptosDisp").value=document.parentWindow.parent.document.getElementById("ptosDisponibles").value;
		
			loadDatos=true;
		}				
	}

function configuraRedencion(){	
	if(frmAplicaRedencion.modoReden[0].checked){
		//Normal
		validaPlan();

	} else if(frmAplicaRedencion.modoReden[1].checked){
		//Multicotizador
		validaPlanMult('ConsultaPlanes');

	}
}



	function validaPlan(){	
		if(getTipoRedencion()){
			FrameInformacion.location="./validaPlan.do?nRegion="+document.getElementById("nRegion").value+
			"&sPlan="+document.getElementById("planNuevo").value+"&tipoReden="+tipoReden +
			"&tipoRedenS="+tipoRedenS+"&MesesCR="+document.getElementById("MesesCR").value+
			"&AdendumCR="+document.getElementById("AdendumCR").value+"&fecAddM2K="+document.getElementById("fecAddM2K").value+
			"&adendum="+document.getElementById("adendum").value+"&cuenta="+document.getElementById("cuenta").value+
			"&secuencia="+document.getElementById("secuencia").value+"&planNuevoCR="+document.getElementById("planNuevoCR").value;	 	       
			document.getElementById("TarjetaInformacion").style.visibility="visible";
			document.getElementById("TarjetaInformacion").style.display="block";	
			document.getElementById("CampoObligatorio").style.visibility="hidden";
			document.getElementById("CampoObligatorio").style.display="none";
			document.getElementById("inputSap").style.visibility="hidden";
			document.getElementById("inputSap").style.display="none";
			document.getElementById("inputSin").style.visibility="hidden";
			document.getElementById("inputSin").style.display="none";
			document.getElementById("inputCareg").style.visibility="hidden";
			document.getElementById("inputCareg").style.display="none";		
			document.getElementById("inputCon").style.visibility="hidden";
			document.getElementById("inputCon").style.display="none";
			document.getElementById("TarjetaRedencionesCon").style.display="none";
			document.getElementById("TarjetaRedencionesCon").style.visibility="hidden";
			document.getElementById("TarjetaCareg").style.visibility="hidden";
			document.getElementById("TarjetaCareg").style.display="none";	
			document.getElementById("TarjetaRedencionesS").style.visibility="hidden";
			document.getElementById("TarjetaRedencionesS").style.display="none";
			/*Multicotizador II JAPA 28/03/2012  Folio 84048 y ZZZZZZ : Se elimina el div que muestra campos para TarjetaRedencionesCon y TarjetaCareg */
			document.getElementById("selecRedencion").style.visibility="hidden";
			document.getElementById("selecRedencion").style.display="none";

			
		}
}

function validaPlanMult(accion){
	if(frmAplicaRedencion.tipoReden.length == undefined) {
		tipoReden = frmAplicaRedencion.tipoReden.value;
	} else {
	
		var radios = document.getElementsByName('tipoReden');
		for (var i = 0; i < radios.length; i++) {
			if (radios[i].checked) {
				tipoReden = radios[i].value;
			
				if(tipoReden == 'SIN') {
					var radiosS = document.getElementsByName('tipoRedenS');
					for (var i = 0; i < radiosS.length; i++) {
						if (radiosS[i].checked) {
							tipoRedenS=radiosS[i].value;
						}
					}
				}
			}     
		}
	} 

	     
	/*
	if(frmAplicaRedencion.tipoReden.length == undefined) {
		tipoReden = frmAplicaRedencion.tipoReden.value;
	} else {
		if(frmAplicaRedencion.tipoReden[0].checked){		
			tipoReden = frmAplicaRedencion.tipoReden[0].value;
		}else if(frmAplicaRedencion.tipoReden[1].checked){		
				tipoReden = frmAplicaRedencion.tipoReden[1].value;
		}else if(frmAplicaRedencion.tipoReden[2].checked){
			tipoReden = frmAplicaRedencion.tipoReden[2].value;
			if(frmAplicaRedencion.tipoRedenS[0].checked){
				tipoRedenS=frmAplicaRedencion.tipoRedenS[0].value;
			}else if(frmAplicaRedencion.tipoRedenS[1].checked){
				tipoRedenS=frmAplicaRedencion.tipoRedenS[1].value;
			}else if(frmAplicaRedencion.tipoRedenS[2].checked){
				tipoRedenS=frmAplicaRedencion.tipoRedenS[2].value;
			}else if(frmAplicaRedencion.tipoRedenS[3].checked){
				tipoRedenS=frmAplicaRedencion.tipoRedenS[3].value;
			}
		} else {
			return;
		}	
	}
	*/
	
			
	if(accion == 'ConsultaPlanes') {
		//var aRed1 = document.getElementById("bAplicaRedencion").value;
		//var aRed2 = document.parentWindow.parent.document.getElementById("bAplicaRedencion").value;	
		//var ptosDisp = document.getElementById("ptosDisp").value;
		
		var nRegion		= document.getElementById("nRegion").value;
		var fecAddM2K	= document.parentWindow.parent.document.getElementById("fecAddM2K").innerText;
		var adendum		= document.parentWindow.parent.document.getElementById("adendum").innerText;
		var cuenta		= document.getElementById("cuenta").value;
		FrameMulticotizador.location="./consultaPlanesRedencion.do?nRegion="+nRegion+"&tipoReden="+tipoReden+"&tipoRedenS="+tipoRedenS+"&fecAddM2K="+fecAddM2K+"&adendum="+adendum+"&cuenta="+cuenta;	 	
		
	} else if(accion = 'sinRenovacion') {
		var planNuevo = document.parentWindow.parent.document.getElementById("planM2K").value;
		FrameInformacion.location="./validaPlan.do?nRegion="+document.getElementById("nRegion").value+"&sPlan="+planNuevo+"&tipoReden="+tipoReden +"&tipoRedenS="+tipoRedenS+"&MesesCR="+document.getElementById("MesesCR").value+"&AdendumCR="+document.getElementById("AdendumCR").value+"&fecAddM2K="+document.getElementById("fecAddM2K").value+"&adendum="+document.getElementById("adendum").value+"&cuenta="+document.getElementById("cuenta").value+"&secuencia="+document.getElementById("secuencia").value+"&planNuevoCR="+planNuevoCR;	
	}

	document.getElementById("TarjetaRedencionesCon").style.display="none";
	document.getElementById("TarjetaRedencionesCon").style.visibility="hidden";
	document.getElementById("TarjetaCareg").style.visibility="hidden";
	document.getElementById("TarjetaCareg").style.display="none";	
	document.getElementById("TarjetaMulticotizador").style.visibility="visible";
	document.getElementById("TarjetaMulticotizador").style.display="block";	
	document.getElementById("TarjetaInformacion").style.visibility="hidden";
	document.getElementById("TarjetaInformacion").style.display="none";	
	document.getElementById("CampoObligatorio").style.visibility="hidden";
	document.getElementById("CampoObligatorio").style.display="none";
	document.getElementById("inputSap").style.visibility="hidden";
	document.getElementById("inputSap").style.display="none";
	document.getElementById("inputSin").style.visibility="hidden";
	document.getElementById("inputSin").style.display="none";
	document.getElementById("inputCareg").style.visibility="hidden";
	document.getElementById("inputCareg").style.display="none";		
	document.getElementById("inputCon").style.visibility="hidden";
	document.getElementById("inputCon").style.display="none";
	document.getElementById("TarjetaRedencionesS").style.visibility="hidden";
	document.getElementById("TarjetaRedencionesS").style.display="none";
	document.getElementById("selecRedencion").style.visibility="hidden";
	document.getElementById("selecRedencion").style.display="none";
}

	
function showCombos(){
	var valor=document.getElementById("gpoPromo").value;		
	if (valor != null && valor != "undefined" && valor != ""){
		FrameMarcas.location="./consultaMarcas.do?nRegion="+document.getElementById("nRegion").value+
		"&nGrupo="+valor+"&sPlan="+document.getElementById("planNuevo").value+
		"&tipoRed="+document.getElementById("tipoRedencion").value;

		document.getElementById("btnContinuarCon").style.visibility="hidden";
		document.getElementById("btnContinuarCon").style.display="none";
		document.getElementById("btnContinuarCareg").style.visibility="hidden";
		document.getElementById("btnContinuarCareg").style.display="none";
		document.getElementById("btnContinuarSin").style.visibility="hidden";
		document.getElementById("btnContinuarSin").style.display="none";
		document.getElementById("btnContinuarAmigoChip").style.visibility="hidden";
		document.getElementById("btnContinuarAmigoChip").style.display="none";
		document.getElementById("btnContinuarT3G").style.visibility="hidden";
		document.getElementById("btnContinuarT3G").style.display="none";			
	}
}
	
	
function actualizaFolios(opcion){	
		FrameFolios.location="./actualizaFolioSap.do?cuenta="+document.getElementById("cuenta").value+
		"&secuencia="+document.getElementById("secuencia").value+
		"&tipoRed="+opcion;
}		

function validaProductoTA(){
	document.getElementById("TarjetaTA").style.visibility="hidden";
	document.getElementById("TarjetaTA").style.display="none";
	//document.getElementById("divCargando").style.visibility="visible";
	//document.getElementById("divCargando").style.display="block";
	document.getElementById("CampoObligatorio").style.visibility="hidden";
	document.getElementById("CampoObligatorio").style.display="none";
	document.getElementById("inputSap").style.visibility="hidden";
	document.getElementById("inputSap").style.display="none";
	document.getElementById("inputSin").style.visibility="hidden";
	document.getElementById("inputSin").style.display="none";
	document.getElementById("inputCareg").style.visibility="hidden";
	document.getElementById("inputCareg").style.display="none";		
	document.getElementById("inputCon").style.visibility="hidden";
	document.getElementById("inputCon").style.display="none";
	document.getElementById("TarjetaRedencionesCon").style.display="none";
	document.getElementById("TarjetaRedencionesCon").style.visibility="hidden";
	document.getElementById("TarjetaCareg").style.visibility="hidden";
	document.getElementById("TarjetaCareg").style.display="none";	
	document.getElementById("TarjetaRedencionesS").style.visibility="hidden";
	document.getElementById("TarjetaRedencionesS").style.display="none";
	/*Multicotizador II JAPA 28/03/2012  Folio 84048 y ZZZZZZ : Se elimina el div que muestra campos para TarjetaRedencionesCon y TarjetaCareg */
	document.getElementById("selecRedencion").style.visibility="hidden";
	document.getElementById("selecRedencion").style.display="none";
	
	var plan = document.parentWindow.parent.document.getElementById("planM2K").value;
	var secuencia = document.parentWindow.parent.document.getElementById("secuencia").value;
	var telefono = document.parentWindow.parent.document.getElementById("telefono").innerText;
	var region = document.parentWindow.parent.document.getElementById("nRegion").value;
	var cuenta = document.parentWindow.parent.document.getElementById("cuenta").innerText;
	FrameTA.location ="./validaProductosTA.do?cuenta="+cuenta+"&telefono="+telefono+"&region="+region+"&plan="+plan+"&secuencia="+secuencia;
	document.getElementById("TarjetaTA").style.visibility="visible";
	document.getElementById("TarjetaTA").style.display="block";
	
}
	
/*function activaCargando(sVisible,sDisplay){	
	window.alert("redencion.jsp");	
	document.getElementById("divCargando").style.visibility=sVisible;
	document.getElementById("divCargando").style.display=sDisplay;							
}*/


function cambioModoReden(opcion) {
	limpiaCampos();
	limpiaCamposCareg();

	if(frmAplicaRedencion.modoReden[0].checked){
		//Normal
		habilitaCampos();
	} else if(frmAplicaRedencion.modoReden[1].checked){
		//Multicotizador
		inhabilitaCampos();
		
		configuraRedencion();
	}
	
}
	
</script>
</head>
<body style="background-color: transparent;"  onload="inicializa();">
     <script>
		document.parentWindow.parent.activaCargando("hidden","none");		
	</script> 
<form id="frmAplicaRedencion" name="frmAplicaRedencion"> 
<!-- Multicotizador II JAPA 15/03/2012  Folio 84048 y ZZZZZZ -->
<input type="hidden" name="ptosDisp" id="ptosDisp">


<DIV id="BloqueRedencion" style="VISIBILITY:visible; WIDTH: 100%; DISPLAY:block;padding-left: 5px; top: 0px;" >
<table>     	
	<tr>            	            	            	            
		<td width="70%" align="left" height="42">
			<table width="100%">
				<tr>            			            		
					<TD>
						<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="62">
							<SPAN id="B01" class="botonInactivo" onclick="cambioOpcion(1)" >Histórico</SPAN>
						</securityCa:validaPerfil>
						<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="63">	    								 					    					 							 					
							<SPAN id="B02" class="botonInactivo" onclick="cambioOpcion(2)" >Nueva</SPAN>
						</securityCa:validaPerfil>
						<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="64">		 					
							<SPAN id="B03" class="botonInactivo" onclick="cambioOpcion(3)" >Cancelación</SPAN>
						</securityCa:validaPerfil>
					</TD>		 					
				</tr>
			</table>
		</td>
	</tr>   	
</TABLE>
</DIV>
<DIV id="Tarjeta1"  style="height: 380px;visibility: hidden;display: none;background-color: transparent;" >	
   <DIV id="divRedencion"  style="MARGIN-TOP: 0px; OVERFLOW-Y: scroll; OVERFLOW-X: hidden; BORDER:solid 1px silver; HEIGHT: 280px;position: absolute;top: 55px;visibility: visible;display: block;">
        <table border="1" cellspacing="0" cellpadding="0" align="center" width="96%"> 
            <tr bgcolor="#ECF0DB" class="healineblue1" align="center"> 
                <td width="11%">Fecha Operación </td>
                <td width="10%">Producto </td>
                <td width="8%">Marca </td>
                <td width="8%">Modelo </td>
                <td width="9%">Valor Puntos </td>
                <td width="8%">Diferencia Pesos</td>
                <td width="9%">Usuario </td>
                <td width="4%">Tipo </td>
                <td width="29%">Comentario </td>
            </tr>
            <c:set var="contador" value="0"/>
            <c:forEach var="redencionTO" items="${redencionesTO}" varStatus="total">
            <c:set var="contador" value="${total.count}"/>   
            <tr class="X3" bgcolor="<c:if test="${contador %2 !=0 }">#D9EBF2</c:if>">
                <td align="left">&nbsp;${redencionTO.fechaOperacion}</td>
                <td align="left">&nbsp;${redencionTO.productosTO.material}</td>
                <td align="left">&nbsp;${redencionTO.productosTO.marca}</td>
                <td align="left">&nbsp;${redencionTO.productosTO.modelo}</td>
                <td align="right">&nbsp;${redencionTO.puntosRedimidosTO.ptsTotaltesconFormato}</td>
                <td align="right">&nbsp;${redencionTO.productosTO.precioIvaConFormato}</td>
                <td align="center">&nbsp;${redencionTO.usuarioTO.idUsuario}</td>
                <td align="center">&nbsp;${redencionTO.tipoRedencion}</td>
                <td align="left">&nbsp;${redencionTO.comentario}</td>                
            </tr>
            </c:forEach>
        </table>       
     </DIV>
     <DIV id="divTotal" style="height:50px;position: absolute;top: 335px;">
      <table style="width: 98%">
       	<tr bgcolor="#ECF0DB" class="healineblue1">
       		<td>&nbsp;<c:out value="${contador}"></c:out> Registro(s) Encontrado(s).</td>
       	</tr>
      </table>
      </DIV>
</DIV>


<DIV id="Tarjeta2"  style="height: 380px;visibility: hidden;display: none;background-color: transparent;" >	
	<input type="hidden" name="nRegion" id="nRegion">
	<input type="hidden" name="nGrupo" id="nGrupo">
	<input type="hidden" name="fecAddM2K" id="fecAddM2K">
	<input type="hidden" name="adendum" id="adendum">
	<input type="hidden" name="adendumNvo" id="adendumNvo">	
	<input type="hidden" name="gpoPromo" id="gpoPromo">
	<input type="hidden" name="porcentajeIva" id="porcentajeIva">
	<input type="hidden" name="defineIva" id="defineIva">
	<input type="hidden" name="ptsTotales" id="ptsTotales">
	<input type="hidden" name="cuenta" id="cuenta">
	<input type="hidden" name="secuencia" id="secuencia">
	<input type="hidden" name="telefono" id="telefono">
	<input type="hidden" name="estatusTel" id="estatusTel">
	<input type="hidden" name="tecnologia" id="tecnologia">
	<input type="hidden" name="bonoEquipo" id="bonoEquipo">
	<input type="hidden" name="sPromFacturaAV" id="sPromFacturaAV">
	<input type="hidden" name="motivo" id="motivo">
	<input type="hidden" name="fecSuspension" id="fecSuspension">
	<input type="hidden" name="planM2K" id="planM2K">
	<input type="hidden" name="formaRedencion" id="formaRedencion">			
	<input type="hidden" name="idMensaje" id="idMensaje">
	<input type="hidden" name="bAplicaRedencion" id="bAplicaRedencion"/>
	<input type="hidden" name="bContinuar" id="bContinuar" value="0"/>	
	<input type="hidden" name="tipoRedencion" id="tipoRedencion">
	
	
	<DIV id="TarjetaInformacion" style=" border:solid 1px silver hidden;display: none; visibility: hidden; transparent; position: absolute;top: 45px;left: 2px;height: 0px;width: 250px; background-color: transparent;">         		
		<IFRAME  name="FrameInformacion" id="FrameInformacion" style="border: hidden;" frameborder="0" width="970px" height="95px" AllowTransparency></IFRAME>
	</DIV>		

	<!-- Multicotizador II JAPA 15/03/2012  Folio 84048 y ZZZZZZ : Div para el despliegue de formulario para multicotizador -->
	<DIV id="TarjetaMulticotizador" style=" border:solid 1px silver hidden;display: none; visibility: hidden; transparent; position: absolute;top: 40px;left: 2px;height: 0px;width: 250px; background-color: transparent;">         		
		<IFRAME name="FrameMulticotizador" id="FrameMulticotizador" style="border: hidden;" frameborder="0" width="970px" height="350px" AllowTransparency></IFRAME>
	</DIV>		
	<br>
	
	
	<DIV id="MenuMulticotizador" style="display: none; visibility: hidden;">
	<table width ="98%" cellspacing="0" cellpadding="0" align="center">
	<!-- Multicotizador II JAPA 15/03/2012  Folio 84048 y ZZZZZZ : Menu de selección para el tipo de redencion -->
		<tr>
			<td width="15%" class="healineblue1" align="left" id="selecRedencion">
				&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="radio" name="modoReden" id="modoReden" value="0" onClick="cambioModoReden(0);"><font class="textonegroBlod">Normal&nbsp;&nbsp;&nbsp;</font>
				<input type="radio" name="modoReden" id="modoReden" value="1" onClick="cambioModoReden(0);"><font class="textonegroBlod">Multicotizador</font>
			</td>
		</tr>
	</table>
	</DIV>
	
	<table width ="98%" cellspacing="0" cellpadding="0" align="center">
		<tr>
			<td width="15%" class="healineblue1" align="left" id="inputCon" >
				<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="86">
					<input type="radio" name="tipoReden" id="tipoReden" value="CON" onClick="cambioSubMenu(3);">&nbsp;Redenci&oacute;n con Renovaci&oacute;n de Addendum
				</securityCa:validaPerfil>
			</td>
		</tr>
		<tr>
			<td><div id="TarjetaRedencionesCon" style="height: 30px;visibility: hidden;display:	none;background-color: transparent;">
	    	<table width ="90%" cellspacing="0" cellpadding="0" align="right">
	        	<tr>
	        		<td class="healineblue1" align="left">Plan Nuevo<font color="red">*</font>:</td>
	        		<td class="healineblue1" align="left"><input type="text" name="planNuevo" size="8" style="text-transform: uppercase" maxlength="5" id="planNuevo" onKeyDown="if(event.keyCode==13)configuraRedencion();"></td>
	        		<td class="healineblue1" align="left"><a class="LinkOut" style="width:100px" onmouseover='this.className="LinkIn";' onmouseout='this.className="LinkOut";'
					id="Link1" onclick="configuraRedencion();" >&nbsp;&nbsp;Continuar&nbsp;&nbsp;</a>
	        		</td> 
	        	</tr>
	        </table>
	        </div></td>
		</tr>		
		<tr>
			<td>
				<div id="btnContinuarCon" style="height: 35px;visibility: hidden;display:	none;background-color: transparent; bottom: 210px;position: absolute; ">
			    	<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="99">
			    		<table width ="90%" cellspacing="0" cellpadding="0" align="right">
				        	<tr>	 
				        		<td class="healineblue1" align="left" width="30%"/>      		
				        		<td class="healineblue1" align="left">
					        		<a class="LinkOut" style="width:100px" onmouseover='this.className="LinkIn";' onmouseout='this.className="LinkOut";'
									id="Continuar" onclick="showCombos()" >&nbsp;Continuar&nbsp;&nbsp;</a>
				        		<td> 
				        	</tr>
				        </table>
			    	</securityCa:validaPerfil>
		        </div>		       
		        <div id="btnContinuarCareg" style="height: 35px;visibility: hidden;display:	none;background-color: transparent; bottom: 210px;position: absolute; ">
			    	<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="100">
			    		<table width ="90%" cellspacing="0" cellpadding="0" align="right">
				        	<tr>	 
				        		<td class="healineblue1" align="left" width="30%"/>      		
				        		<td class="healineblue1" align="left">
					        		<a class="LinkOut" style="width:100px" onmouseover='this.className="LinkIn";' onmouseout='this.className="LinkOut";'
									id="Continuar" onclick="showCombos()" >&nbsp;Continuar&nbsp;&nbsp;</a>
				        		<td> 
				        	</tr>
				        </table>
			    	</securityCa:validaPerfil>
		        </div>
		        <div id="btnContinuarSin" style="height: 35px;visibility: hidden;display:	none;background-color: transparent; bottom: 210px;position: absolute; ">
			    	<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="126">
			    		<table width ="90%" cellspacing="0" cellpadding="0" align="right">
				        	<tr>	 
				        		<td class="healineblue1" align="left" width="30%"/>      		
				        		<td class="healineblue1" align="left">
					        		<a class="LinkOut" style="width:100px" onmouseover='this.className="LinkIn";' onmouseout='this.className="LinkOut";'
									id="Continuar" onclick="showCombos()" >&nbsp;Continuar&nbsp;&nbsp;</a>
				        		<td> 
				        	</tr>
				        </table>
			    	</securityCa:validaPerfil>
		        </div>
		        <div id="btnContinuarAmigoChip" style="height: 35px;visibility: hidden;display:	none;background-color: transparent; bottom: 210px;position: absolute; ">
			    	<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="128">
			    		<table width ="90%" cellspacing="0" cellpadding="0" align="right">
				        	<tr>	 
				        		<td class="healineblue1" align="left" width="30%"/>      		
				        		<td class="healineblue1" align="left">
					        		<a class="LinkOut" style="width:100px" onmouseover='this.className="LinkIn";' onmouseout='this.className="LinkOut";'
									id="Continuar" onclick="showCombos()" >&nbsp;Continuar&nbsp;&nbsp;</a>
				        		<td> 
				        	</tr>
				        </table>
			    	</securityCa:validaPerfil>
		        </div>
		        <div id="btnContinuarT3G" style="height: 35px;visibility: hidden;display:	none;background-color: transparent; bottom: 210px;position: absolute; ">
			    	<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="129">
			    		<table width ="90%" cellspacing="0" cellpadding="0" align="right">
				        	<tr>	 
				        		<td class="healineblue1" align="left" width="30%"/>      		
				        		<td class="healineblue1" align="left">
					        		<a class="LinkOut" style="width:100px" onmouseover='this.className="LinkIn";' onmouseout='this.className="LinkOut";'
									id="Continuar" onclick="showCombos()" >&nbsp;Continuar&nbsp;&nbsp;</a>
				        		<td> 
				        	</tr>
				        </table>
			    	</securityCa:validaPerfil>
		        </div>		        
	       </td>
		</tr>		
		<tr>
	    	<td width="15%" class="healineblue1" align="left" id="inputCareg" >
	    		<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="87">
	    			<input type="radio" name="tipoReden" id="tipoReden" value="CAREG" onClick="cambioSubMenu(5);">
	         			Redenci&oacute;n con Renovaci&oacute;n de Addendum por CAREG
	    		</securityCa:validaPerfil>
	        </td>
		</tr>
		<tr>
	    	<td><div id="TarjetaCareg" style="height: 30px;visibility: hidden;display:	none;background-color: transparent;">
	        <table width="90%" cellspacing="0" cellpadding="0" align="right">
	    		<tr>
	        		<td width="10%" class="healineblue1" align="left"> 
	        			Plan Nuevo<font color="red">*</font>:</td>
	        		<td><input type="text" name="planNuevoCR" size="8" style="text-transform: uppercase" maxlength="5" id="planNuevoCR" onKeyDown="if(event.keyCode==13)validaPlan()"></td>
					<td width="10%" class="healineblue1" align="left"> 
	        			Addendum<font color="red">*</font>:</td>
	        		<td><input type="text" name="AdendumCR" size="8" maxlength="5" id="AdendumCR" onKeyDown="if(event.keyCode==13)validaPlan()"></td>
	        		<td width="10%" class="healineblue1" align="left"> 
	        			Meses<font color="red">*</font>:</td>
	        		<td><input type="text" name="MesesCR" size="8" maxlength="5" id="MesesCR" onKeyDown="if(event.keyCode==13)validaPlan()"></td>
	        		<td class="healineblue1" align="left">
	        			<a class="LinkOut" style="width:100px" onmouseover='this.className="LinkIn";' onmouseout='this.className="LinkOut";'
						id="btnCareg" onclick="configuraRedencion()" >&nbsp;Continuar&nbsp;&nbsp;</a>
	        		<td> 
	        	</tr>
	        </table></div></td>
		</tr>
		<tr>
			<td width="15%" class="healineblue1" align="left" id="inputSin" >
				<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="88">
					<input type="radio" name="tipoReden" id="tipoReden" value="SIN" onClick="cambioSubMenu(4);">
	    	 			Redenci&oacute;n sin Renovaci&oacute;n de Addendum
				</securityCa:validaPerfil>
	    	</td>
	 	</tr>
	 	
	 	<tr>
	 		<td>
	 		<div id="TarjetaRedencionesS" style="height: 100px;visibility: hidden;display:	none;background-color: transparent;">
	        <table width="90%" cellspacing="0" cellpadding="0" align="right">
	        	<tr><td width="15%" class="healineblue1" align="left"/></tr>
	        	<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="101">	  
		        	<tr>
		       			<td width="15%" class="healineblue1" align="left" id="inputSIN">
		       				<input type="radio" name="tipoRedenS" id="tipoRedenS" value="SIN"  onclick="configuraRedencion();">Amigo Kit
		       			</td>
		        	</tr>
		        </securityCa:validaPerfil>
		        <securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="102">
		        	<tr>
		       			<td width="15%" class="healineblue1" align="left" id="inputTAIR">
		       				<input type="radio" name="tipoRedenS" id="tipoRedenS" value="TAIR" onclick="validaProductoTA()">Venta Tiempo Aire
		       			</td>
	        		</tr>
	        	</securityCa:validaPerfil>
	        	<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="103">	        	
		        	<tr>
		        		<td width="15%" class="healineblue1" align="left" id="inputACA">
		        			<input type="radio" name="tipoRedenS" id="tipoRedenS" value="ACA" onclick="configuraRedencion();">Amigo Chip
		        		</td>
		        	</tr>
		        </securityCa:validaPerfil>
		        <securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="104">
		        	<tr>
		        		<td width="15%" class="healineblue1" align="left" id="inputT3G">
		        			<input type="radio" name="tipoRedenS" id="tipoRedenS" value="T3G"  onclick="configuraRedencion();">Tarjetas Inal&aacute;mbricas 3G
		        		</td>
		        	</tr>
		        </securityCa:validaPerfil>	        	
	        </table>
	        </div>
	        </td>
	 	</tr>	 	
	 	
	 	<tr>
	 		<td width="15%" class="healineblue1" align="left" id="inputSap">
	 			<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="89">
	 				<input type="radio" name="tipoReden" id="tipoReden" value="RET" onClick="cambioSubMenu(6);">
	 					Consulta y Actualizaci&oacute;n de folios SAP
	 			</securityCa:validaPerfil>
	 		</td>
	 	</tr>
	
	 	<tr><td>
	 		<div id="TarjetaFoliosSap" style="height: 250px;visibility: hidden;display:	none;background-color: transparent;">
	        	<table width="90%" cellspacing="0" cellpadding="0" align="right">	        	
	        		<tr height="10%" style="height: 10px"><td width="15%" class="healineblue1" align="left"/></tr>	  
	        		<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="105">
	        			<tr>
		        			<td width="15%" class="healineblue1" align="left" id="folioCon">
		       					<input type="radio" name="tipoFolio" id="tipoFolio" onclick="actualizaFolios(1)">Equipos en Plan Tarifario
		       				</td>
	        			</tr>
	        		</securityCa:validaPerfil>
	        		<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="106">
		        		<tr><td width="15%" class="healineblue1" align="left" id="folioAca">
		       				<input type="radio" name="tipoFolio" id="tipoFolio" onclick="actualizaFolios(2)" >Amigo Chip</td>
		        		</tr>
		        	</securityCa:validaPerfil>
		        	<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="107">
		        		<tr><td width="15%" class="healineblue1" align="left" id="folioSin">
		        			<input type="radio" name="tipoFolio" id="tipoFolio" onclick="actualizaFolios(3)" >Equipos en Amigo Kit</td>
		        		</tr>
		        	</securityCa:validaPerfil>
		        	<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="108">
		        		<tr><td width="15%" class="healineblue1" align="left" id="folioT3G">
		        			<input type="radio" name="tipoFolio" id="tipoFolio" onclick="actualizaFolios(4)" >Tarjetas 3G</td>
		        		</tr>
		        	</securityCa:validaPerfil>
	        	</table>
	        </div>
	     </td></tr>			
	 	<tr align="center">
	    	<td width="20%" class="healineblue1" align="left">
	        <div id="CampoObligatorio"  style="height: 30px;visibility: hidden;display:	none;background-color: transparent;">
	       	<font color="red" size="1">&nbsp;&nbsp;&nbsp;*Campos Obligatorios</font>
	        </div></td>
	    </tr>	 	
	</table>
	
	<DIV id="TarjetaPromociones" style="visibility: hidden;display: none;background-color: transparent; position: absolute;top: 110px;left: 20px;height: 350px;width: 990px;OVERFLOW-Y: hidden; OVERFLOW-X: hidden;">		
		<IFRAME  name="FrameMarcas" id="FrameMarcas" style="border: hidden;" frameborder="0" width="990px" height="350px" AllowTransparency></IFRAME>
	</DIV>
	
	<DIV id="TarjetaFolios" style="visibility: hidden;display: none;background-color: transparent; position: absolute;top: 50px;left: 3px;height: 5px;width: 250px;">		
		<IFRAME  name="FrameFolios" id="FrameFolios" style="border: hidden;" frameborder="0" width="970px" height="320px" align="left" AllowTransparency></IFRAME>
	</DIV>
	<DIV id="TarjetaTA" style="visibility: hidden;display: none;background-color: transparent; position: absolute;top: 50px;left: 3px;height: 5px;width: 250px;">		
		<IFRAME  name="FrameTA" id="FrameTA" style="border: hidden;" frameborder="0" width="970px" height="320px" align="left" AllowTransparency></IFRAME>
	</DIV>
</DIV>

<DIV id="Tarjeta3"  style="height: 380px;visibility: hidden;display: none;background-color: transparent;" >
	<IFRAME  name="Frame3" id="Frame3" style="border: hidden;" frameborder="0" width="900px" height="350px" AllowTransparency></IFRAME>
</DIV>

<DIV id="divCargando" class="TarjetaDetalle" style="BORDER:solid 1px silver; height: 380px;visibility: hidden;display: none;position: absolute;top: 60px;">	     
	<IFRAME src="./commons/ProcesandoInfo.html" name="FrameCargando" id="FrameCargando" scrolling="no" WIDTH="100%" HEIGHT="100%" style="border: hidden;" frameborder="0" AllowTransparency></IFRAME>
</DIV>

</form>      
</body>
</html>
