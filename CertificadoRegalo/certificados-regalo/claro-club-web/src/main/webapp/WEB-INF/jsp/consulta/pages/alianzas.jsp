<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/permisos.tld" prefix="securityCa" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Alianzas</title>
<link rel="stylesheet" href='<c:url value="/commons/js/sc_textsheet.css"/>' type="text/css">
<script>
	var oDiv = null;
	var oTar = null; 
	var oFrame = null;
	function muestradDiv(){
		document.getElementById("opcion3").style.display = "block";
	  	document.getElementById("opcion3").style.visibility="visible";
	}
	function activaCargando(propiedad1,propiedad2){			
		document.getElementById("opcion3").style.visibility=propiedad1;
		document.getElementById("opcion3").style.display = propiedad2;	  	
	  	if(propiedad1=="hidden" && oFrame!=null){	  			
	  			oFrame.style.display = "block";
	  			oFrame.style.visibility="visible";	  		
	  	}
	}
	
	function inicializa(){
		var B01 = document.getElementById("B01");
		var B02 = document.getElementById("B02");
		var B04 = document.getElementById("B04");
		var B05 = document.getElementById("B05");
		var B06 = document.getElementById("B06");
		
		if(B01!=null){
			cambioOpcion(1);
			return;
		}
		if(B02!=null){
			cambioOpcion(2);
			return;
		}
		if(B04!=null){
			cambioOpcion(4);
			return;
		}
		if(B05!=null){
			cambioOpcion(5);
			return;
		}
		if(B06!=null){
			cambioOpcion(6);
			return;
		}
	}
	
	function cambioOpcion(opcion){		
		
		if(oDiv != null) oDiv.className = "botonInactivo";
	  	if(oTar != null) {
	  		oTar.style.display = "none";
	  		oTar.style.visibility="hidden";	  	  			
	  	}
	  	
	  			
	  	oDiv = document.getElementById("B0" + opcion);
	  	oDiv.className= "botonActivo";
	  	oTar = document.getElementById("opcion" + opcion);			
		oTar.style.visibility = "visible";
		oTar.style.display = "block";
		
		if(opcion==2){	
			muestradDiv();		
			document.getElementById("Frame2").style.display = "none";
	  		document.getElementById("Frame2").style.visibility="hidden";	  	
			oFrame = document.getElementById("Frame" + opcion);	  
			Frame2.location = "./alianzasCancela.do?cuenta=${cuenta}&secuencia=${secuencia}&region=${region}&telefono=${telefono}";
		}else if(opcion==4){
			muestradDiv();		
			document.getElementById("Frame4").style.display = "none";
	  		document.getElementById("Frame4").style.visibility="hidden";
			oFrame = document.getElementById("Frame" + opcion);	  
			Frame4.location = "./alianzasAltaCambio.do?cuenta=${cuenta}&secuencia=${secuencia}&region=${region}&telefono=${telefono}&millas=${millas}&ptsDisponibles=${ptsDisponibles}&factor=${factor}&millaMin=${millaMin}&estatusPuntos=${estatusPuntos}";
		}else if(opcion==5){			
			muestradDiv();			
			document.getElementById("Frame5").style.display = "none";
	  		document.getElementById("Frame5").style.visibility="hidden";	  			
			oFrame = document.getElementById("Frame" + opcion);	  
			Frame5.location = "./alianzaCanje.do?cuenta=${cuenta}&secuencia=${secuencia}&region=${region}&telefono=${telefono}&millas=${millas}&ptsDisponibles=${ptsDisponibles}&factor=${factor}&millaMin=${millaMin}";
		}else if(opcion==6){
			muestradDiv();		
			document.getElementById("Frame6").style.display = "none";
	  		document.getElementById("Frame6").style.visibility="hidden";
			oFrame = document.getElementById("Frame" + opcion);	  
			Frame6.location = "./muestraLiberar.do?cuenta=${cuenta}&secuencia=${secuencia}&region=${region}&telefono=${telefono}&millas=${millas}&ptsDisponibles=${ptsDisponibles}&factor=${factor}&millaMin=${millaMin}";
		}
	}
	
</script>
</head>
<body  style="background-color: transparent;" onload="inicializa();">
		<script>
			document.parentWindow.parent.activaCargando("hidden","none");
		</script>			
       <DIV id="BloqueRedencion" style="VISIBILITY:visible; WIDTH: 100%; DISPLAY:block;padding-left: 5px; top: 0px;" >
	<table>     	
            <tr>            	            	            	            
            	<td width="70%" align="left" height="42">
            		<table width="100%">
            			<tr>            			            		
            				<TD>
	    						<c:if test="${menuTodos}">
	    							<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="66">
	    								<SPAN id="B01" class="botonInactivo" onclick="cambioOpcion(1)" >Histórico</SPAN>
	    							</securityCa:validaPerfil>
			 						<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="68">
			 							<SPAN id="B02" class="botonInactivo" onclick="cambioOpcion(2)" >Cancelación</SPAN>
			 						</securityCa:validaPerfil>
			 						<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="67">
			 							<SPAN id="B04" class="botonInactivo" onclick="cambioOpcion(4)" >Alta/Cambio</SPAN>
			 						</securityCa:validaPerfil>
			 						<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="69">
			 							<SPAN id="B05" class="botonInactivo" onclick="cambioOpcion(5)" >Canjear</SPAN>
			 						</securityCa:validaPerfil>			 						
			 						<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="70">
			 							<SPAN id="B06" class="botonInactivo" onclick="cambioOpcion(6)" >Liberar Cupones AMEX</SPAN>
			 						</securityCa:validaPerfil>
		 						</c:if>						
		 					</TD>
            			</tr>
            		</table>
            	</td>
            	</tr>            	              	 		 												       	
	   </TABLE>
	   </DIV>
       
       <DIV id="opcion1" style="MARGIN-TOP: 35px; OVERFLOW-Y: hidden; OVERFLOW-X: hidden; HEIGHT: 320px;position: absolute;top: 16px;visibility: hidden;display: none;">
       		<jsp:include page="historicoAlianzas.jsp"/>
       </DIV>
       <DIV id="opcion2" style="MARGIN-TOP: 35px; OVERFLOW-Y: hidden; OVERFLOW-X: hidden; BORDER:solid 1px silver; HEIGHT: 320px;position: absolute;top: 16px;visibility: hidden;display: none;">
       		<IFRAME name="Frame2" id="Frame2" scrolling="no" WIDTH="100%" HEIGHT="100%" style="border: hidden;" frameborder="0" AllowTransparency></IFRAME>
       </DIV>
       <DIV id="opcion4" style="MARGIN-TOP: 35px; OVERFLOW-Y: hidden; OVERFLOW-X: hidden; BORDER:solid 1px silver; HEIGHT: 320px;position: absolute;top: 16px;visibility: hidden;display: none;">
       		<IFRAME name="Frame4" id="Frame4" scrolling="no" WIDTH="100%" HEIGHT="100%" style="border: hidden;" frameborder="0" AllowTransparency></IFRAME>
       </DIV>
       <DIV id="opcion5" style="MARGIN-TOP: 35px; OVERFLOW-Y: hidden; OVERFLOW-X: hidden; BORDER:solid 1px silver; HEIGHT: 320px;position: absolute;top: 16px;visibility: hidden;display: none;">
       		<IFRAME name="Frame5" id="Frame5" scrolling="auto" WIDTH="100%" HEIGHT="100%" style="border: hidden;" frameborder="0" AllowTransparency></IFRAME>
       </DIV>
       <DIV id="opcion6" style="MARGIN-TOP: 35px; OVERFLOW-Y: hidden; OVERFLOW-X: hidden; BORDER:solid 1px silver; HEIGHT: 320px;position: absolute;top: 16px;visibility: hidden;display: none;">
       		<IFRAME name="Frame6" id="Frame6" scrolling="auto" WIDTH="100%" HEIGHT="100%" style="border: hidden;" frameborder="0" AllowTransparency></IFRAME>
       </DIV>       
       <DIV id="opcion3" class="TarjetaDetalle" style=" top:50px;height: 300px;width: 100%;visibility: hidden;display: none;left: 10px;position:absolute;BORDER:solid 2px #4d7097;">	     
	     	<IFRAME src="./commons/ProcesandoInfo.html" name="Frame3" id="Frame3" scrolling="no" WIDTH="100%" HEIGHT="100%" style="border: hidden;" frameborder="0" AllowTransparency></IFRAME>
	  	</DIV>
       
</body>
</html>