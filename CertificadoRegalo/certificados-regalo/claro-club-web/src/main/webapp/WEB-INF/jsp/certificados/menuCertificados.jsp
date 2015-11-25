<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/permisos.tld" prefix="securityCa"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Menu Puntos</title>

<link rel="stylesheet" href='<c:url value="/commons/js/sc_textsheet.css"/>' type="text/css">

<script>
	var oIdx = new Array(0,0,0,0,0,0,0,0,0,0,0);
	var oDiv = null;
	var oTar = null;
	var oFrame = null;
			
	function inicializa(){
		for(var i=1;i<11;i++){
			var menu = document.getElementById("B0" + i);
			if(menu!=null){
				setConsultaSubmenus(i);
				break;
			}
		}		   									
	}
	
	function activaCargando(propiedad1,propiedad2){			
		document.getElementById("Tarjeta3").style.visibility=propiedad1;
		document.getElementById("Tarjeta3").style.display = propiedad2;	  	
	  	if(propiedad1=="hidden" && oFrame!=null){	  			
	  			oFrame.style.display = "block";
	  			oFrame.style.visibility="visible";	  		
	  	}
	}
	
	function cargando(){
		document.getElementById("Tarjeta3").style.display = "block";
	  	document.getElementById("Tarjeta3").style.visibility="visible";
	}

	function ocultaDivs(){
		for(a=1;a<=9;a++){
			document.getElementById("Tarjeta"+a+"").style.display = "none";
		  	document.getElementById("Tarjeta"+a+"").style.visibility="hidden";
		  	document.getElementById("Frame"+a+"").style.display = "none";
		  	document.getElementById("Frame"+a+"").style.visibility="hidden";
		}
	}

	function muestraDiv(num){
		document.getElementById("Frame"+num).style.display = "block";
		document.getElementById("Frame"+num).style.visibility="visible";
		document.getElementById("Tarjeta"+num).style.display = "block";
	  	document.getElementById("Tarjeta"+num).style.visibility="visible";
	}

	function setConsultaSubmenus(_metodo,url){
	  	//Llama a Datos;
	  	if(oDiv != null) oDiv.className = "botonInactivo";
	  	if(oTar != null) {
	  		oTar.style.display = "none";
	  		oTar.style.visibility="hidden";	  	  			
	  	}
	  	oDiv = document.getElementById("B0"+_metodo);
	  	if(oDiv!=null){
	  		oDiv.className= "botonActivo";
	  	}
	  	oTar = document.getElementById("Tarjeta" + _metodo);
	  	
	  	ocultaDivs();
	  	muestraDiv(_metodo);
	  	//Llama a Datos;
	  	if(_metodo==1){
	  		oIdx[0] = 1;
	  		cargando();
	  		Frame1.location = "./consultaPuntosCertificado.do";	  		
	  	}else if(_metodo==2){
	  		cargando();
	  		oIdx[1] = 1;
	  		if(url!=null ){
	  			Frame2.location = url;
	  		}else if(url==null){
	  			Frame2.location = "./commons/DetalleVacio.html";
	  		 }
	  	}else if(_metodo ==  4){
	  			cargando();
	  			oIdx[2] = 1;
	  			Frame4.location = "./impresion.do";	  	
	  	}else if(_metodo ==  5){
	  			oIdx[3] = 1;
	  			cargando();
	  			if(url==null)
	  				Frame5.location = "./consultaMembresia.do";
	  			else
	  				Frame5.location = url;
	  	}else if(_metodo == 6){
	  		oIdx[5] = 1;
	  		if(url==null)
	  			Frame6.location = "./consultaRenuncia.do";
	  		else
	  			Frame6.location = url;
	  	}else if(_metodo ==  7){
	  		oIdx[6] = 1;
	  		cargando();
	  			if(url==null)
	  				Frame7.location = "./transferencia.do";
	  			else
	  				Frame7.location = url;
	  	}else if(_metodo ==  8){
	  		oIdx[7] = 1;
	  		cargando();
	  			if(url==null)
	  				Frame8.location = "./retencion.do";
	  			else
	  				Frame8.location = url;
	  	}else if(_metodo == 9){
	  		oIdx[8] = 1;
	  		if(url==null)
	  			Frame9.location = "./consultaManuales.do";
	  		else
	  			Frame9.location = url;
	  	}else if(_metodo == 10){
	  		oIdx[9] = 1;
	  		if(url==null)
	  			Frame10.location = "./consultaManuales.do";
	  		else
	  			Frame10.location = url;
	  	}else if(_metodo == 11){
	  		oIdx[10] = 1;
	  		if(url==null)
	  			Frame11.location = "./consultaManuales.do";
	  		else
	  			Frame11.location = url;
	  	}
	}
</script>
</head>

<body onload="inicializa();" >	
 		<TABLE border="0" cellspacing="0">
			<TR valign="middle" align="center" height="32px">
				
					<TD >
	    				<SPAN id="B04" class="botonInactivo" onclick="setConsultaSubmenus(1,null)">Consulta</SPAN>
	 				</TD>
								
					<TD>
	    				<SPAN id="B02" class="botonInactivo" onclick="setConsultaSubmenus(2,null)">Detalle</SPAN>
	 				</TD>
	 				
	 				<TD >
	    				<SPAN id="B10" class="botonInactivo" onclick="setConsultaSubmenus(10,null)">Activa Certificado</SPAN>
	 				</TD>
	 				<TD>
	    				<SPAN id="B11" class="botonInactivo" onclick="setConsultaSubmenus(11,null)">Realiza Compra</SPAN>
	 				</TD>
				 				 	
 				<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="8">
 					<TD>
	    				<SPAN id="B04" class="botonInactivo" onclick="setConsultaSubmenus(4,null)">Reportes</SPAN>
	 				</TD>
 				</securityCa:validaPerfil> 
 				
 				<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="7">
	 				<TD>
	    				<SPAN id="B05" class="botonInactivo" onclick="setConsultaSubmenus(5,null)">Membresia</SPAN>
	 				</TD>
 				</securityCa:validaPerfil>
 				<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="9">
	 				<TD>
	    				<SPAN id="B06" class="botonInactivo" onclick="setConsultaSubmenus(6,null)">Cancelacion</SPAN>
	 				</TD>
	 			</securityCa:validaPerfil> 				
 				<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="10">
 					<TD>
	    				<SPAN id="B07" class="botonInactivo" onclick="setConsultaSubmenus(7,null)">Transferencia</SPAN>
	 				</TD>
	 			</securityCa:validaPerfil>
 				<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="11">
 					<TD>
	    				<SPAN id="B08" class="botonInactivo" onclick="setConsultaSubmenus(8,null)">Retenci&oacute;n</SPAN>
	 				</TD>
	 			</securityCa:validaPerfil>
	 			<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="12">
	 				<TD>
	    				<SPAN id="B09" class="botonInactivo" onclick="setConsultaSubmenus(9,null)">Manuales y Promociones</SPAN>
	 				</TD>
	 			</securityCa:validaPerfil>
       		</TR>
       	</TABLE>       	
       <DIV id="Tarjeta1" class="TarjetaDetalle" style="top:43px;height: 460px;width: 100%;visibility: visible;display: block;left: 10px;position:absolute;BORDER:solid 2px #4d7097;">
     		<IFRAME name="Frame1" id="Frame1" scrolling="no" WIDTH="100%" HEIGHT="100%" style="border: hidden;" frameborder="0" AllowTransparency></IFRAME>
  		</DIV>
	  	<DIV id="Tarjeta2" class="TarjetaDetalle" style=" top:43px;height: 460px;width: 100%;visibility: hidden;display: none;left: 10px;position:absolute;BORDER:solid 2px #4d7097;">
	     	<IFRAME name="Frame2" id="Frame2" scrolling="no" WIDTH="100%" HEIGHT="100%" style="border: hidden;visibility: hidden;display: none;" frameborder="0" AllowTransparency></IFRAME>
	  	</DIV>	
	    <DIV id="Tarjeta4" class="TarjetaDetalle" style=" top:43px;height: 460px;width: 100%;visibility: hidden;display: none;left: 10px;position:absolute;BORDER:solid 2px #4d7097;">
	     	<IFRAME name="Frame4" id="Frame4" scrolling="no" WIDTH="100%" HEIGHT="100%" style="border: hidden;visibility: hidden;display: none; " frameborder="0" AllowTransparency></IFRAME>
	  	</DIV>		
	  	<DIV id="Tarjeta5" class="TarjetaDetalle" style=" top:43px;height: 460px;width: 100%;visibility: hidden;display: none;left: 10px;position:absolute;BORDER:solid 2px #4d7097;">
	     	<IFRAME name="Frame5" id="Frame5" scrolling="no" WIDTH="100%" HEIGHT="100%" style="border: hidden;visibility: hidden;display: none;" frameborder="0" AllowTransparency></IFRAME>
	  	</DIV>
	  	<DIV id="Tarjeta6" class="TarjetaDetalle" style=" top:43px;height: 460px;width: 100%;visibility: hidden;display: none;left: 10px;position:absolute;BORDER:solid 2px #4d7097;">
	     	<IFRAME name="Frame6" id="Frame6" scrolling="no" WIDTH="100%" HEIGHT="100%" style="border: hidden;visibility: hidden;display: none;" frameborder="0" AllowTransparency></IFRAME>
	  	</DIV>	  	  	  		  	  
	  	<DIV id="Tarjeta7" class="TarjetaDetalle" style=" top:43px;height: 460px;width: 100%;visibility: hidden;display: none;left: 10px;position:absolute;BORDER:solid 2px #4d7097;">
	     	<IFRAME name="Frame7" id="Frame7" scrolling="no" WIDTH="100%" HEIGHT="100%" style="border: hidden;visibility: hidden;display: none;" frameborder="0" AllowTransparency></IFRAME>
	  	</DIV>
	  	<DIV id="Tarjeta8" class="TarjetaDetalle" style=" top:43px;height: 460px;width: 100%;visibility: hidden;display: none;left: 10px;position:absolute;BORDER:solid 2px #4d7097;">
	     	<IFRAME name="Frame8" id="Frame8" scrolling="auto" WIDTH="100%" HEIGHT="100%" style="border: hidden;visibility: hidden;display: none;" frameborder="0" AllowTransparency></IFRAME>
	  	</DIV>
	  	<DIV id="Tarjeta9" class="TarjetaDetalle" style=" top:43px;height: 460px;width: 100%;visibility: hidden;display: none;left: 10px;position:absolute;BORDER:solid 2px #4d7097;">
	     	<IFRAME name="Frame9" id="Frame9" scrolling="auto" WIDTH="100%" HEIGHT="100%" style="border: hidden;visibility: hidden;display: none;" frameborder="0" AllowTransparency></IFRAME>
	  	</DIV>
	  	<DIV id="Tarjeta3" class="TarjetaDetalle" style=" top:43px;height: 460px;width: 100%;visibility: hidden;display: none;left: 10px;position:absolute;BORDER:solid 2px #4d7097;">	     
	     	<IFRAME src="./commons/ProcesandoInfo.html" name="Frame3" id="Frame3" scrolling="no" WIDTH="100%" HEIGHT="100%" style="border: hidden;" frameborder="0" AllowTransparency></IFRAME>
	  	</DIV>
	  	<DIV id="Tarjeta10" class="TarjetaDetalle" style=" top:43px;height: 460px;width: 100%;visibility: hidden;display: none;left: 10px;position:absolute;BORDER:solid 2px #4d7097;">
	     	<IFRAME name="Frame10" id="Frame10" scrolling="auto" WIDTH="100%" HEIGHT="100%" style="border: hidden;visibility: hidden;display: none;" frameborder="0" AllowTransparency></IFRAME>
	  	</DIV>
	  	<DIV id="Tarjeta11" class="TarjetaDetalle" style=" top:43px;height: 460px;width: 100%;visibility: hidden;display: none;left: 10px;position:absolute;BORDER:solid 2px #4d7097;">
	     	<IFRAME name="Frame11" id="Frame11" scrolling="auto" WIDTH="100%" HEIGHT="100%" style="border: hidden;visibility: hidden;display: none;" frameborder="0" AllowTransparency></IFRAME>
	  	</DIV>	      	
</body>
</html>