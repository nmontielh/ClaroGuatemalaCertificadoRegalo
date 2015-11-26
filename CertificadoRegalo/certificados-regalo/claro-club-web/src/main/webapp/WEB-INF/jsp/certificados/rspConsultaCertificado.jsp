<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/permisos.tld" prefix="securityCa" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Datos Tarjeta</title>
        <link rel="stylesheet" href='<c:url value="/commons/js/sc_textsheet.css"/>' type="text/css">
    	<c:if test="${telefonoTO.puntosTO.bandVencer60Dias==true}">
        <script>
        window.open('./commons/help/puntosavencerpopup.jsp?puntos=${telefonoTO.puntosTO.ptsPorVencer}&fecha=${telefonoTO.puntosTO.fecVencer}&region=${telefonoTO.region}','_newven','width=450,height=350,status,toolbar=no,scrollbars=no,resizable,left=400,top=300');
        </script>        
        </c:if>
    	<script>
    		
    		function cargaDatos(){    			
    			window.location.reload();
    		}
    				
    		var oIdx = new Array(0,0,0,0,0);
			var oDiv = null;
			var oTar = null;
			var oSubconsulta = null;		
			var oBtnSubconsulta = null;	
									
			function inicializa(){
				var oDetalle = document.getElementById("BloqueDetalle");
    			oDetalle.style.visibility="visible";
				oDetalle.style.display   ="block";
				
				var btnPuntos = document.getElementById("B04");
				var btnMovimientos = document.getElementById("B01");
				var btnRedencion = document.getElementById("B02");
				var btnAlianzas = document.getElementById("B03");
				var btnAsignacion = document.getElementById("B06");
				
				if(btnPuntos!=null){
					setConsultaSubmenus(4);
					return;
				}
				if(btnMovimientos!=null){
					setConsultaSubmenus(1);
					return;
				}
				if(btnRedencion!=null){
					setConsultaSubmenus(2);
					return;
				}
				if(btnAlianzas!=null){
					setConsultaSubmenus(3);
					return;
				}
				if(btnAsignacion!=null){
					setConsultaSubmenus(6);
					return;
				}				
			}
			
			function activaCargando(propiedad1,propiedad2){		
				if(oTar != null && propiedad1=="visible") {
		  			oTar.style.display = "none";
		  			oTar.style.visibility="hidden";
		  		}										
				document.getElementById("Tarjeta5").style.visibility=propiedad1;
				document.getElementById("Tarjeta5").style.display=propiedad2;
				if (oTar != null && propiedad1=="hidden"){
		  			oTar.style.display= "block";
		  			oTar.style.visibility="visible";
		  		} 
			}
			
    		function setConsultaSubmenus(_metodo){
    		
    			
		  		if(oDiv != null) oDiv.className = "botonInactivo";
		  		if(oTar != null) {
		  			oTar.style.display = "none";
		  			oTar.style.visibility="hidden";
		  		}
		  			
		  		oDiv = document.getElementById("B0" + _metodo);
		  		oTar = document.getElementById("Tarjeta" + _metodo);
		  		oDiv.className= "botonActivo";
		  		oTar.style.display= "block";
		  		oTar.style.visibility="visible";
		  		
		  		//Llama a Datos;		  		
		  		if(_metodo==1 && oIdx[0]==0 ){	
		  			activaCargando("visible","block");	  				  			
					oIdx[0] = 1;		  	  		
		  	  		Frame1.location = "./movimientosCertificado.do?numeroTarjeta=${tarjetaCertificado.numeroTarjeta}";
		  		}else if(_metodo==2 && oIdx[1]==0){		
		  			activaCargando("visible","block");  					  			 
		  	  		oIdx[1] = 1;			
		  	  		Frame2.location = "./redenciones.do?cuenta=${telefonoTO.mobileTO.cuenta}&secuencia=${telefonoTO.mobileTO.secuencia}&idmensaje=${telefonoTO.idMensaje}&mensaje=${telefonoTO.mensaje}";
		  		}else if(_metodo==3 && oIdx[2]==0){		  					  		
		  	  		activaCargando("visible","block");
		  	  		oIdx[2] = 1;	  	  		
					Frame3.location = "./alianzas.do?cuenta=${telefonoTO.mobileTO.cuenta}&secuencia=${telefonoTO.mobileTO.secuencia}&telefono=${telefonoTO.mobileTO.telefono}&region=${telefonoTO.region}&ptsDisponibles=${telefonoTO.puntosTO.ptosDisponibles}&millas=${telefonoTO.millas}&factor=${factor}&millaMin=${millaMin}&estatusPuntos=${telefonoTO.puntosTO.ptosStatus}&idmensaje=${telefonoTO.idMensaje}&mensaje=${telefonoTO.mensaje}";
		  		}else if(_metodo==6 && oIdx[3]==0){		  					  		
		  	  		activaCargando("visible","block");
		  	  		oIdx[3] = 1;
		  	  		Frame6.location = "./accionPuntos.do?cuenta=${telefonoTO.mobileTO.cuenta}&secuencia=${telefonoTO.mobileTO.secuencia}&telefono=${telefonoTO.mobileTO.telefono}&region=${telefonoTO.region}&fechaAlta=${telefonoTO.mobileTO.fecAltaUser}&ptsDisponibles=${telefonoTO.puntosTO.ptosDisponibles}&estatusPuntos=${telefonoTO.puntosTO.ptosStatus}&nombre=${telefonoTO.mobileTO.nombre}&segmento=${telefonoTO.segmento}";		  	  				  	  				  	  		
		  		}		  	
		  	}		  	
    		 
    	</script>
    </head>
			    
    <body  onload="inicializa()" style="background-color: transparent;">    
    <script>    		
    		document.parentWindow.parent.activaCargando("hidden","none");
    </script>
     <DIV id="BloqueDetalle" style="VISIBILITY:hidden; WIDTH: 100%; DISPLAY:none;padding-left: 5px;" >
     	<table>     	
            <tr>            	
            	
            	<td class="titulo" height="42" width="30%" align="center">&nbsp;&nbsp;Consulta de Certificados</td>
            	
            	<td width="70%" align="center" height="42">
            		<table width="100%">
            			<tr>            			            		
            				<TD>
            					
	    							<SPAN id="B04" class="botonInactivo" onclick="setConsultaSubmenus(4)" >Saldo</SPAN>
	    						
	    						
		    						<SPAN id="B01" class="botonInactivo" onclick="setConsultaSubmenus(1)" >Movimientos</SPAN>
		    							 				
		 					</TD>
            			</tr>
            		</table>
            	</td>
            	</tr>            	              	 		 												       	
	    	</TABLE>
        
        <DIV id="Tarjeta4"  style="BORDER:solid 1px silver; height: 380px;visibility: hidden;display: none;background-color: transparent;" >
        <br>                  
        <table width="98%" cellspacing="0" cellpadding="0" align="center">
            <tr><td class="textonegroBlodTrs">&nbsp;&nbsp;Datos de la Tarjeta</td></tr>
        </table>   
        
        <table width="98%" border="1" cellspacing="0" cellpadding="0" align="center">
	        <tr>
	            <td width="25%" class="healineblue1" bgcolor="#ECF0DB"  align="left" >&nbsp;Número de Tarjeta</td>
	            <td width="25%" class="textonegroBlod" align="left" id="Nombre">&nbsp;<font size="3">${tarjetaCertificado.numeroTarjeta}</font></td>
	            <td class="healineblue1" bgcolor="#ECF0DB" align="left" >&nbsp;Saldo</td>
              	<td class="textonegroBlod" id="millasDisp">&nbsp;<font color="red" size="3">${tarjetaCertificado.saldo}</font></td>               	            
	        </tr>
	         <tr> 
              	<td class="healineblue1" bgcolor="#ECF0DB" align="left">&nbsp;Número de Certificado</td>
              	<td class="textonegroBlod" align="left" id="telefono">&nbsp;${tarjetaCertificado.numeroCertificado}</td>
              	<td class="healineblue1" bgcolor="#ECF0DB" align="left">&nbsp;Estatus</td> 
              <td  class="textonegroBlod" >&nbsp;${tarjetaCertificado.estatus}</td>
          	</tr>
          <tr> 
              <td class="healineblue1" bgcolor="#ECF0DB" align="left">&nbsp;Fecha de Activación</td>
              <td class="textonegroBlod" id="cuenta">&nbsp;${tarjetaCertificado.fechaActivacion}</td>
              <td class="healineblue1" bgcolor="#ECF0DB" align="left">&nbsp;País</td>
              <td class="textonegroBlod" >&nbsp;GUATEMALA</td>
          </tr>
           <tr> 
              <td class="healineblue1" bgcolor="#ECF0DB" align="left">&nbsp;Fecha de Expiración</td>
              <td class="textonegroBlod" id="tecnologia" >&nbsp;${tarjetaCertificado.fechaExpiracion}</td>
              <td width="25%" class="healineblue1" bgcolor="#ECF0DB" align="left">&nbsp;Valor del Certificado</td>
	          <td width="25%" class="textonegroBlod" id="ptsDisponibles" >&nbsp;${500}</td>	     
          </tr>
        </table>
        
        <c:if test="${telefonoTO.mensaje!=null}">
        
        <div>
        	<center>
        	<table>        		
        		<tr><td  align="center" width="60%"><font color="red"><b><c:out value="${telefonoTO.mensaje}"/></b> </font> </td></tr>
        	</table>
        	</center>
        </div>
        </c:if>
	    	
      	</DIV>	       	
	       <DIV id="Tarjeta1"  style="BORDER:solid 1px silver; height: 380px;visibility: hidden;display: none;position: absolute;top: 60px;">
	     		<IFRAME name="Frame1" id="Frame1" scrolling="no" WIDTH="100%" HEIGHT="380px" style="border: hidden;" frameborder="0" AllowTransparency ></IFRAME>
	  		</DIV>
		  	<DIV id="Tarjeta2" style="BORDER:solid 1px silver; height: 380px;visibility: hidden;display: none;position: absolute;top: 60px;">
		     	<IFRAME name="Frame2" id="Frame2" scrolling="no" WIDTH="100%" HEIGHT="380px" style="border: hidden;" frameborder="0" AllowTransparency></IFRAME>
		  	</DIV>
	  		<DIV id="Tarjeta3"  style="BORDER:solid 1px silver; height: 380px;visibility: hidden;display: none;position: absolute;top: 60px;" >
	     		<IFRAME name="Frame3" id="Frame3" scrolling="no" WIDTH="100%" HEIGHT="380px" style="border: hidden;" frameborder="0" AllowTransparency></IFRAME>
	  		</DIV>
	  		
	  		<DIV id="Tarjeta6" style="BORDER:solid 1px silver; height: 380px;visibility: hidden;display: none;position: absolute;top: 60px;">
		     	<IFRAME name="Frame6" id="Frame6" scrolling="no" WIDTH="100%" HEIGHT="380px" style="border: hidden;" frameborder="0" AllowTransparency></IFRAME>
		  	</DIV>
	  		<DIV id="Tarjeta5" class="TarjetaDetalle" style="BORDER:solid 1px silver; height: 380px;visibility: hidden;display: none;position: absolute;top: 60px;">	     
	     		<IFRAME src="./commons/ProcesandoInfo.html" name="Frame5" id="Frame5" scrolling="no" WIDTH="100%" HEIGHT="100%" style="border: hidden;" frameborder="0" AllowTransparency></IFRAME>
	  		</DIV>
	  		
       </DIV>             
    </body>
</html>
