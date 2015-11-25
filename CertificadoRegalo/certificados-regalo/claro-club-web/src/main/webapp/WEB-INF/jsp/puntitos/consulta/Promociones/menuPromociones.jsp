<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/permisos.tld" prefix="securityCa" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Menu Promociones... 2.0</title>
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
	document.getElementById("FramePromo"+num).style.display = "block";
	document.getElementById("FramePromo"+num).style.visibility="visible";
	document.getElementById("TarjetaPromo"+num).style.display = "block";
  	document.getElementById("TarjetaPromo"+num).style.visibility="visible";
}

function ocultaDiv(){
	for(i=1;i<=3;i++){
		document.getElementById("FramePromo"+i).style.display = "none";
		document.getElementById("FramePromo"+i).style.visibility="hidden";
		document.getElementById("TarjetaPromo"+i).style.display = "none";
		document.getElementById("TarjetaPromo"+i).style.visibility="hidden";
	}
}

function inicializa(){
	var B01 = document.getElementById("B01");
	if(B01!=null){
		setConsultaSubmenusPromos(1);
	}else{
		var B02 = document.getElementById("B02");
		if(B02!=null){
			setConsultaSubmenusPromos(2);
		}else{ /*JSC Folio:115460*/
			var B03 = document.getElementById("B03");
			if(B03!=null){			
				setConsultaSubmenusPromos(3);
			}else{			
				var B04 = document.getElementById("B04");
				if(B04!=null){
					setConsultaSubmenusPromos(4);
				}else{
					var B05 = document.getElementById("B05");
					if(B05!=null){
						setConsultaSubmenusPromos(5);
					}else{
						setConsultaSubmenusPromos(6);
					}
				}	
			}
		}
	}	
}

function setConsultaSubmenusPromos(_metodo){
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
  		FramePromo1.location = "./consultaArchivos.do";
  	}else if(_metodo==2){
  		FramePromo2.location = "./consultaPlanes.do";
  	}else if(_metodo==3){
  		FramePromo3.location = "./consultaProcesaArchivos.do";
  	/* Pantalla Consulta Distribuidores JAPA 20/07/2011 Folio 93379 */
	}else if(_metodo==4){
		FramePromo4.location = "./consultaPromocionesDistribuidores.do";
		/* Pantalla procesa Membresías JSC Folio:115460 */
	}else if(_metodo==5){
		FramePromo5.location = "./consultaProcesaMembresias.do";	
	}else if(_metodo==6){
		FramePromo6.location = "./consultaProcesaInbursa.do";	
	}
}
</script>
<body onload="inicializa()" marginwidth="0" marginheight="0" style="margin: 0px" background="<c:url value="/commons/images/backgroundlight.gif"/>">
	
 		<TABLE border=0 cellspacing=0>
			<TR valign="middle" align="center" height="32px">			
				<TD>
					<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="53">
						<SPAN id="B01" class="botonInactivo" onclick="setConsultaSubmenusPromos(1,null)">Alta de Documentos</SPAN>
					</securityCa:validaPerfil>
 				</TD>
 				<TD>
 					<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="54">
    					<SPAN id="B02" class="botonInactivo" onclick="setConsultaSubmenusPromos(2,null)">Consultas</SPAN>
    				</securityCa:validaPerfil>
 				</TD>
 				<TD>
 					<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="55">
    					<SPAN id="B03" class="botonInactivo" onclick="setConsultaSubmenusPromos(3,null)">Procesa Cat&aacute;logos</SPAN>
    				</securityCa:validaPerfil>
 				</TD>
				<TD>
					<!-- Pantalla Consulta Distribuidores JAPA 20/07/2011 Folio 93379 -->
					<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="157">
    					<SPAN id="B04" class="botonInactivo" onclick="setConsultaSubmenusPromos(4,null)">Distribuidores</SPAN>
    				</securityCa:validaPerfil>
 				</TD>
 				<TD>
					<!-- Reporte de Membresias JSC Folio:115460 -->
					<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="169">
    					<SPAN id="B05" class="botonInactivo" onclick="setConsultaSubmenusPromos(5,null)">Membresias</SPAN>
    				</securityCa:validaPerfil>
 				</TD>
 			<!--<TD>
 					<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="55">
    					<SPAN id="B06" class="botonInactivo" onclick="setConsultaSubmenusPromos(6,null)">Cat&aacute;logos Inbursa</SPAN>
    				</securityCa:validaPerfil>
 				</TD>-->
 			</TR>
       	</TABLE>       	
       <DIV id="TarjetaPromo1" class="TarjetaDetalle" style="top:43px;height: 380px;width: 95%;visibility: visible;display: block;left: 10px;position:absolute;BORDER:solid 2px #4d7097;">
     		<IFRAME name="FramePromo1" id="FramePromo1" scrolling="auto" WIDTH="100%" HEIGHT="100%" style="border: hidden;visibility: hidden;display: none;" frameborder="0" AllowTransparency></IFRAME>
  		</DIV>
	  	<DIV id="TarjetaPromo2" class="TarjetaDetalle" style=" top:43px;height: 380px;width: 95%;visibility: hidden;display: none;left: 10px;position:absolute;BORDER:solid 2px #4d7097;">
	     	<IFRAME name="FramePromo2" id="FramePromo2" scrolling="auto" WIDTH="100%" HEIGHT="100%" style="border: hidden;visibility: hidden;display: none;" frameborder="0" AllowTransparency></IFRAME>
	  	</DIV>	  	
	  	<DIV id="TarjetaPromo3" class="TarjetaDetalle" style=" top:43px;height: 380px;width: 95%;visibility: hidden;display: none;left: 10px;position:absolute;BORDER:solid 2px #4d7097;">
	     	<IFRAME name="FramePromo3" id="FramePromo3" scrolling="auto" WIDTH="100%" HEIGHT="100%" style="border: hidden;visibility: hidden;display: none;" frameborder="0" AllowTransparency></IFRAME>
	  	</DIV>
<%--
/* Pantalla Consulta Distribuidores JAPA 20/07/2011 Folio 93379 */
--%>
		<DIV id="TarjetaPromo4" class="TarjetaDetalle" style=" top:43px;height: 380px;width: 95%;visibility: hidden;display: none;left: 10px;position:absolute;BORDER:solid 2px #4d7097;">
	     	<IFRAME name="FramePromo4" id="FramePromo4" scrolling="auto" WIDTH="100%" HEIGHT="100%" style="border: hidden;visibility: hidden;display: none;" frameborder="0" AllowTransparency></IFRAME>
	  	</DIV>
	  	<%--
/* Pantalla Procesa Membresías JSC Folio:115460 */
--%>
		<DIV id="TarjetaPromo5" class="TarjetaDetalle" style=" top:43px;height: 380px;width: 95%;visibility: hidden;display: none;left: 10px;position:absolute;BORDER:solid 2px #4d7097;">
	     	<IFRAME name="FramePromo5" id="FramePromo5" scrolling="auto" WIDTH="100%" HEIGHT="100%" style="border: hidden;visibility: hidden;display: none;" frameborder="0" AllowTransparency></IFRAME>
	  	</DIV>
	  	<%--
	  	/* Pantalla Procesa Membresías JSC Folio:115460 */
--%>
		<DIV id="TarjetaPromo6" class="TarjetaDetalle" style=" top:43px;height: 380px;width: 95%;visibility: hidden;display: none;left: 10px;position:absolute;BORDER:solid 2px #4d7097;">
	     	<IFRAME name="FramePromo6" id="FramePromo6" scrolling="auto" WIDTH="100%" HEIGHT="100%" style="border: hidden;visibility: hidden;display: none;" frameborder="0" AllowTransparency></IFRAME>
	  	</DIV>	 	
</body>
</html>