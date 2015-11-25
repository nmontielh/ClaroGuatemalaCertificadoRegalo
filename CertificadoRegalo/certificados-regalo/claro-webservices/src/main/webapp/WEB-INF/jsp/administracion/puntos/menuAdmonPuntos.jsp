<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/permisos.tld" prefix="securityCa" %>
 <!-- MÓDULO ASIGNACIÓN DE PUNTOS - JAPA 08/02/2013 Folio 121733 -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Administración de Puntos</title>
<link rel="stylesheet" href='<c:url value="/commons/js/sc_textsheet.css"/>' type="text/css">
<script type="text/javascript" src='<c:url value="/commons/js/jquery-1.2.3.pack.js"/>'></script>
<script type='text/javascript' src='<c:url value="/commons/js/jquery.form.js"/>' ></script>
<script type="text/javascript" src='<c:url value="/commons/js/jquery-impromptu.1.3.js"/>' ></script>
<!-- <script src='<c:url value="/commons/js/puntitos.js"/>' type="text/javascript"></script> -->
</head>
<script type="text/javascript">
var oIdx = new Array(0,0,0,0,0,0,0);
var oDiv = null;
var oTar = null;

function muestradDiv(num){
	document.getElementById("FramePromo"+num).style.display = "block";
	document.getElementById("FramePromo"+num).style.visibility="visible";
	document.getElementById("TarjetaPromo"+num).style.display = "block";
  	document.getElementById("TarjetaPromo"+num).style.visibility="visible";
}

function ocultaDiv(){
	for(i=1;i<=1;i++){
		document.getElementById("FramePromo"+i).style.display = "none";
		document.getElementById("FramePromo"+i).style.visibility="hidden";
		document.getElementById("TarjetaPromo"+i).style.display = "none";
		document.getElementById("TarjetaPromo"+i).style.visibility="hidden";
	}
}

function inicializa(){

	var B01 = document.getElementById("B01");
	if(B01!=null){
		setConsultaSubMenu(1);
	}
}

function setConsultaSubMenu(_metodo){
  	if(oDiv != null) oDiv.className = "botonInactivo";
  	if(oTar != null) {
  		oTar.style.display = "none";
  		oTar.style.visibility="hidden";	  	  			
  	}
  	oDiv = document.getElementById("B0"+_metodo);
  	oTar = document.getElementById("TarjetaPromo" + _metodo);
  	oDiv.className= "botonActivo";
  	oTar.style.display= "block";
  	oTar.style.visibility="visible";
  	//Llama a Datos;
  	ocultaDiv();
  	muestradDiv(_metodo);
  	
  	if(_metodo==1){
  		FramePromo1.location = "./menuAsignacionPuntos.do";
  		
  	}
}
</script>
<body onload="inicializa()" marginwidth="0" marginheight="0" style="margin: 0px" background="<c:url value="/commons/images/backgroundlight.gif"/>">
	
<TABLE border=0 cellspacing=0>
<TR valign="middle" align="center" height="32px">			
	<TD>
		<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="175">
			<SPAN id="B01" class="botonInactivo" onclick="setConsultaSubMenu(1,null)">Asignaci&oacute;n</SPAN>
		</securityCa:validaPerfil>
	</TD>
</TR>
</TABLE>       	
<DIV id="TarjetaPromo1" class="TarjetaDetalle" style="top:43px;height: 380px;width: 95%;visibility: visible;display: block;left: 10px;position:absolute;BORDER:solid 2px #4d7097;">
	<IFRAME name="FramePromo1" id="FramePromo1" scrolling="auto" WIDTH="100%" HEIGHT="100%" style="border: hidden;visibility: hidden;display: none;" frameborder="0" AllowTransparency></IFRAME>
</DIV>
</body>
</html>