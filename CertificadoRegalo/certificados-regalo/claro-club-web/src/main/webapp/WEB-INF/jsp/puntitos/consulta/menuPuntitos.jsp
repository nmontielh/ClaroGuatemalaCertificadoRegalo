<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/permisos.tld" prefix="securityCa"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Menu Puntitos... 2.0</title>

<c:set var="perfil" value="${sessionScope.usuarioTO.perfilTO.idPerfilN}" />

<link rel="stylesheet" href='<c:url value="/commons/js/sc_textsheet.css"/>' type="text/css">
<script type="text/javascript" src='<c:url value="/commons/js/jquery-1.2.3.pack.js"/>'></script>
<script type='text/javascript' src='<c:url value="/commons/js/jquery.form.js"/>' ></script>
<script type="text/javascript" src='<c:url value="/commons/js/jquery-impromptu.1.3.js"/>' ></script>
<script src='<c:url value="/commons/js/puntitos.js"/>' type="text/javascript"></script>
</head>
<script type="text/javascript">
var oIdx = new Array(0,0,0,0,0,0,0);
var oDiv = null;
var oTar = null;

function muestradDiv(num){
	document.getElementById("FramePuntitos"+num).style.display = "block";
	document.getElementById("FramePuntitos"+num).style.visibility="visible";
	document.getElementById("TarjetaPuntitos"+num).style.display = "block";
  	document.getElementById("TarjetaPuntitos"+num).style.visibility="visible";
}

function ocultaDiv(){
	for(i=1;i<=4;i++){
		document.getElementById("FramePuntitos"+i).style.display = "none";
		document.getElementById("FramePuntitos"+i).style.visibility="hidden";
		document.getElementById("TarjetaPuntitos"+i).style.display = "none";
		document.getElementById("TarjetaPuntitos"+i).style.visibility="hidden";
	}
}

function inicializa(){
	var opcionInicial = document.getElementById("B01");
	if(opcionInicial==null){
		opcionInicial = document.getElementById("B02");
		if(opcionInicial==null){
			setConsultaSubmenusPuntitos(5);
		}else{
			setConsultaSubmenusPuntitos(2);
		}		
	}else{
		setConsultaSubmenusPuntitos(1);
	}
}

function setConsultaSubmenusPuntitos(_metodo){
  	if(oDiv != null) oDiv.className = "botonInactivo";
  	if(oTar != null) {
  		oTar.style.display = "none";
  		oTar.style.visibility="hidden";	  	  			
  	}
  	oDiv = document.getElementById("B0"+_metodo);
  	oTar = document.getElementById("TarjetaPuntitos" + _metodo);
  	oDiv.className= "botonActivo";
  	oTar.style.display= "block";
  	oTar.style.visibility="visible";
  	//Llama a Datos;
  	ocultaDiv();
  	muestradDiv(_metodo);
  	if(_metodo==1){
  		FramePuntitos1.location = "./consultaPuntitos.do";
  	}else if(_metodo==2){
  		FramePuntitos2.location = "./consultaUsuario.do";
  	}else if(_metodo==3){
  		FramePuntitos3.location = "./framePuntVenta.do";
  	}else if(_metodo==4){
  		FramePuntitos4.location = "./menuPromociones.do";
  	}else if(_metodo==5){
  		FramePuntitos5.location = "./lineasRestringidas.do";
  	}
}
</script>
<body onload="inicializa()">	
 		<TABLE border=0 cellspacing=0>
			<TR valign="middle" align="center" height="32px">
				<TD>
					<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="13">
						<SPAN id="B01" class="botonInactivo" onclick="setConsultaSubmenusPuntitos(1,null)">ConsultaPuntitos</SPAN>
					</securityCa:validaPerfil>
				</TD>	 			
	 			<TD>
	 				<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="14">
	    				<SPAN id="B02" class="botonInactivo" onclick="setConsultaSubmenusPuntitos(2,null)">Usuarios</SPAN>
	    			</securityCa:validaPerfil>
 				</TD>
 				<TD>
	 				<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="172">
	    				<SPAN id="B05" class="botonInactivo" onclick="setConsultaSubmenusPuntitos(5,null)">Lineas Restringidas</SPAN>
	    			</securityCa:validaPerfil>
 				</TD>
 			</TR>
       	</TABLE>       	
       <DIV id="TarjetaPuntitos1" class="TarjetaDetalle" style="top:43px;height: 450px;width: 100%;visibility: visible;display: block;left: 10px;position:absolute;BORDER:solid 2px #4d7097;">
     		<IFRAME name="FramePuntitos1" id="FramePuntitos1" scrolling="auto" WIDTH="100%" HEIGHT="100%" style="border: hidden;visibility: hidden;display: none;" frameborder="0" AllowTransparency></IFRAME>
  		</DIV>
	  	<DIV id="TarjetaPuntitos2" class="TarjetaDetalle" style=" top:43px;height: 450px;width: 100%;visibility: hidden;display: none;left: 10px;position:absolute;BORDER:solid 2px #4d7097;">
	     	<IFRAME name="FramePuntitos2" id="FramePuntitos2" scrolling="auto" WIDTH="100%" HEIGHT="100%" style="border: hidden;visibility: hidden;display: none;" frameborder="0" AllowTransparency></IFRAME>
	  	</DIV>
	  	<DIV id="TarjetaPuntitos3" class="TarjetaDetalle" style=" top:43px;height: 450px;width: 100%;visibility: hidden;display: none;left: 10px;position:absolute;BORDER:solid 2px #4d7097;">
	     	<IFRAME name="FramePuntitos3" id="FramePuntitos3" scrolling="auto" WIDTH="100%" HEIGHT="100%" style="border: hidden;visibility: hidden;display: none;" frameborder="0" AllowTransparency></IFRAME>
	  	</DIV>
	  	<DIV id="TarjetaPuntitos4" class="TarjetaDetalle" style=" top:43px;height: 450px;width: 100%;visibility: hidden;display: none;left: 10px;position:absolute;BORDER:solid 2px #4d7097;">
	     	<IFRAME name="FramePuntitos4" id="FramePuntitos4" scrolling="auto" WIDTH="100%" HEIGHT="100%" style="border: hidden;visibility: hidden;display: none;" frameborder="0" AllowTransparency></IFRAME>
	  	</DIV>
	  	<DIV id="TarjetaPuntitos5" class="TarjetaDetalle" style=" top:43px;height: 450px;width: 100%;visibility: hidden;display: none;left: 10px;position:absolute;BORDER:solid 2px #4d7097;">
	     	<IFRAME name="FramePuntitos5" id="FramePuntitos5" scrolling="auto" WIDTH="100%" HEIGHT="100%" style="border: hidden;visibility: hidden;display: none;" frameborder="0" AllowTransparency></IFRAME>
	  	</DIV>
</body>
</html>