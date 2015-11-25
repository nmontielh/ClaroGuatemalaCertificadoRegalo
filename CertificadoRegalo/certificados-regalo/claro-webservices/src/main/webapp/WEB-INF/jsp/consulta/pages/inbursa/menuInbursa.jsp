<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/permisos.tld" prefix="securityCa" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Menu Inbursa</title>

	<link rel="stylesheet" href='<c:url value="/commons/js/sc_textsheet.css"/>' type="text/css">
	
	<script type="text/javascript">

		function eligeOpcionSubMenu(opcion){

			if(opcion==1){
				/* var b02 = document.getElementById("B02");
				if(b02!=null){
					b02.className = "botonInactivo";
				}				
				var b03 = document.getElementById("B03");
				if(b03!=null){
					b03.className = "botonInactivo";
				}*/
				FrameInbursa.location = "./consultaInbursa.do";
			}
			/* 
			if(opcion==2){
				var b01 = document.getElementById("B01");
				if(b01!=null){
					b01.className = "botonInactivo";
				}
				var b03 = document.getElementById("B03");
				if(b03!=null){
					b03.className = "botonInactivo";
				}
				FrameInbursa.location = "./inbursa2.do";
			}
			if(opcion==3){
				var b01 = document.getElementById("B01");
				if(b01!=null){
					b01.className = "botonInactivo";
				}
				var b02 = document.getElementById("B02");
				if(b02!=null){
					b02.className = "botonInactivo";
				}
				FrameInbursa.location = "./inbursa3.do";
			}
			*/
			var optMenu = document.getElementById("B0" + opcion);
			optMenu.className = "botonActivo";
						
		}
		function opcionInicial(){
			var opcion = document.getElementById("B01");
			/*if(opcion==null){
				eligeOpcionSubMenu(2);
			}else{*/
				eligeOpcionSubMenu(1)
			//}
		}
	
	</script>
</head>

<body onload="opcionInicial();">
	<TABLE border=0 cellspacing=0>
		<TR valign="middle" align="center" height="32px">
			<TD>
				<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="191">
					<SPAN id="B01" class="botonInactivo" onclick="eligeOpcionSubMenu(1)">Consulta Inbursa</SPAN>
				</securityCa:validaPerfil>
			</TD>
			<!--<TD>
				<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="19">
					<SPAN id="B02" class="botonInactivo" onclick="eligeOpcionSubMenu(2)">inbursa2</SPAN>
				</securityCa:validaPerfil>
			</TD>
			<TD>
				<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="149">
					<SPAN id="B03" class="botonInactivo" onclick="eligeOpcionSubMenu(3)">Inbursa3</SPAN>
				</securityCa:validaPerfil>
			</TD> -->
    	</TR>
	</TABLE> 
	<DIV id="TarjetaInbursa" class="TarjetaDetalle" style="top:43px;height: 460px;width: 100%;visibility: visible;display: block;left: 10px;position:absolute;BORDER:solid 2px #4d7097;background: transparent;">
		<IFRAME name="FrameInbursa" id="FrameInbursa" scrolling="no" WIDTH="100%" HEIGHT="100%" frameborder="0" AllowTransparency></IFRAME>
	</DIV>
</body>
</html>