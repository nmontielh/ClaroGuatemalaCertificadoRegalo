<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Asignación/Cancelación de Puntos</title>
<link rel="stylesheet" href='<c:url value="/commons/js/sc_textsheet.css"/>' type="text/css">
<script type="text/javascript">
var oDiv = null;
var oTar = null; 
var oFrame = null;
function muestradDiv(){
alert("HOLA muestradDiv");
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
	}		
</script>
</head>
<body style="background-color: transparent;" onload="cambioOpcion(1)">
		<script>
			document.parentWindow.parent.activaCargando("hidden","none");			
		</script>					
       <DIV id="BloqueAsignacion" style="VISIBILITY:visible; WIDTH: 100%; DISPLAY:block;padding-left: 5px; top: 0px;" >       
	<table>     	
            <tr>            	            	            	            
            	<td width="70%" align="left" height="42">
            		<table width="100%">
            			<tr>            			            		
            				<TD>
	    						<SPAN id="B01" class="botonInactivo" onclick="cambioOpcion(1)" >Puntos</SPAN>		 						
		 					</TD>
            			</tr>
            		</table>
            	</td>
            	</tr>            	              	 		 												       	
	   </TABLE>
	   </DIV>
     <DIV id="opcion1" style="MARGIN-TOP: 35px; OVERFLOW-Y: hidden; OVERFLOW-X: hidden; HEIGHT: 320px;position: absolute;top: 16px;visibility: hidden;display: none;">
       		<jsp:include page="asignarPuntos.jsp"/>
     </DIV>  	
</body>
</html>