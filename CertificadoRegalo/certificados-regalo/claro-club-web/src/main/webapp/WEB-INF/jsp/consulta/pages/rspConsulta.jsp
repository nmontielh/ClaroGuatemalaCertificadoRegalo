<%-- 
    Document   : rspConsulta
    Created on : 6/03/2008, 06:34:25 PM
    Author     : vibx958
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/permisos.tld" prefix="securityCa" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Datos Linea</title>
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
		  	  		Frame1.location = "./movimientos.do?cuenta=${telefonoTO.mobileTO.cuenta}&secuencia=${telefonoTO.mobileTO.secuencia}";
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
            	
            	<td class="titulo" height="42" width="30%" align="left">&nbsp;&nbsp;Consulta de puntos</td>
            	
            	<td width="70%" align="right" height="42">
            		<table width="100%">
            			<tr>            			            		
            				<TD>
            					<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="21">
	    							<SPAN id="B04" class="botonInactivo" onclick="setConsultaSubmenus(4)" >Puntos</SPAN>
	    						</securityCa:validaPerfil>
	    						<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="22">
		    						<SPAN id="B01" class="botonInactivo" onclick="setConsultaSubmenus(1)" >Movimientos</SPAN>
		    					</securityCa:validaPerfil>		 				
		    					<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="23">
		    						<SPAN id="B02" class="botonInactivo" onclick="setConsultaSubmenus(2)" >Redención</SPAN>
		    					</securityCa:validaPerfil>		 				
		    					<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="24">
		    						<SPAN id="B03" class="botonInactivo" onclick="setConsultaSubmenus(3)" >Alianzas</SPAN>
		    					</securityCa:validaPerfil>
		    					<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="25">
		    						<SPAN id="B06" class="botonInactivo" onclick="setConsultaSubmenus(6)" >Asignación</SPAN>
		    					</securityCa:validaPerfil>
		 					</TD>
            			</tr>
            		</table>
            	</td>
            	</tr>            	              	 		 												       	
	    	</TABLE>
        
        <DIV id="Tarjeta4"  style="BORDER:solid 1px silver; height: 380px;visibility: hidden;display: none;background-color: transparent;" >                  
        <table width="98%" cellspacing="0" cellpadding="0" align="center">
            <tr><td class="textonegroBlodTrs">&nbsp;&nbsp;Datos de la Línea</td></tr>
        </table>   
        
        <input type="hidden" value="${telefonoTO.region}" id="nRegion"/>
        <input type="hidden" value="${telefonoTO.secuencia}" id="secuencia"/>	
        <input type="hidden" value="${telefonoTO.mobileTO.motivo}" id="motivo"/>
        <input type="hidden" value="${telefonoTO.mobileTO.fechaSuspension}" id="fecSuspension"/>
        <input type="hidden" value="${telefonoTO.mobileTO.planM2K}" id="planM2K"/>        
        <input type="hidden" value="${telefonoTO.bonoEquipo}" id="bonoEquipo"/>
        <input type="hidden" value="${telefonoTO.mobileTO.promedio}" id="sPromFacturaAV"/>
        <input type="hidden" value="${telefonoTO.aplicaRedencion}" id="bAplicaRedencion"/>
        <input type="hidden" value="${telefonoTO.puntosTO.ptosStatus}" id="estatusPuntos">        

        <input type="hidden" value="${telefonoTO.puntosTO.ptosDisponibles}" id="ptosDisponibles">        
        
         
        <!-- BLOQUE ANTERIOR QUE MUESTRA MILLAS DISPONIBLES
        
         <table width="98%" border="1" cellspacing="0" cellpadding="0" align="center">
	        <tr>
	            <td width="15%" class="healineblue1" bgcolor="#ECF0DB"  align="left" >&nbsp;Nombre</td>
	            <td width="45%" class="textonegroBlod" align="left" id="Nombre">&nbsp;${telefonoTO.mobileTO.nombre}</td>
	            <td width="20%" class="healineblue1" bgcolor="#ECF0DB" align="left">&nbsp;Puntos Disponibles</td>
	            <td width="18%" class="textonegroBlod" id="ptsDisponibles" >&nbsp;${telefonoTO.puntosTO.ptosDisponibles}</td>	                    	            
	        </tr>
	         <tr> 
              	<td class="healineblue1" bgcolor="#ECF0DB" align="left">&nbsp;Teléfono</td>
              	<td class="textonegroBlod" align="left" id="telefono">&nbsp;${telefonoTO.mobileTO.telefono}</td>
              	<td class="healineblue1" bgcolor="#ECF0DB" align="left" >&nbsp;Millas Disponibles</td>
              	<td class="textonegroBlod" id="millasDisp">&nbsp;${telefonoTO.millas}</td>
          	</tr>
          <tr> 
              <td class="healineblue1" bgcolor="#ECF0DB" align="left">&nbsp;Cuenta</td>
              <td class="textonegroBlod" id="cuenta">&nbsp;${telefonoTO.mobileTO.cuenta}</td>
              <td class="healineblue1" bgcolor="#ECF0DB" align="left">&nbsp;Estatus Puntos</td> 
              <td  class="textonegroBlod" >&nbsp;<font color="red">${telefonoTO.puntosTO.ptosStatus}</font>
              </td> 
          </tr>
           <tr> 
              <td class="healineblue1" bgcolor="#ECF0DB" align="left">&nbsp;Tecnología</td>
              <td class="textonegroBlod" id="tecnologia" >&nbsp;${telefonoTO.mobileTO.tecnologia}</td>
              <td class="healineblue1" bgcolor="#ECF0DB" align="left">&nbsp;Cuenta Padre</td>
              <td class="textonegroBlod" >&nbsp;${telefonoTO.mobileTO.cuentaPadre}</td>
          </tr>
          <tr> 
              <td class="healineblue1" bgcolor="#ECF0DB" align="left">&nbsp;Segmento</td>              
              	<c:if test="${telefonoTO.segmento=='Azul'}">
                	  <td class="textonegroBlod" >&nbsp;
                    	  <span style="width: 20px;background-color: #00BFFF">
                    	  	${telefonoTO.segmento}
                    	  </span>
                  	</td>                                            
              	</c:if> 
              <c:if test="${telefonoTO.segmento=='Oro'}">
                  <td class="textonegroBlod"  >&nbsp;<span style="width: 20px;background-color: #FEE000">
                      ${telefonoTO.segmento}
                      </span>
                      </td>
              </c:if>
              <c:if test="${telefonoTO.segmento=='Platino'}">
                  <td class="textonegroBlod"   >&nbsp;
                      <span style="width: 20px;background-color: #CCCCCC">
                      ${telefonoTO.segmento}
                      </span>
                  </td>
              </c:if>                                       
              <td class="healineblue1" bgcolor="#ECF0DB" align="left">&nbsp;Region</td>
              <td class="textonegroBlod" >&nbsp;${telefonoTO.regionTxt}</td> 
          </tr>          
          <tr> 
              <td class="healineblue1" bgcolor="#ECF0DB" align="left">&nbsp;Bandera Subasta</td> 
              <td class="textonegroBlod" >&nbsp;
                  <c:if test="${telefonoTO.banSubasta==0}">
                  Inactiva
                  </c:if>
                  <c:if test="${telefonoTO.banSubasta==1}">
                  Activa
                  </c:if>
              </td>
              <td class="healineblue1" bgcolor="#ECF0DB" align="left">&nbsp;Puntos en Subasta</td> 
              <td class="textonegroBlod" >&nbsp;${telefonoTO.banSubasta}</td> 
          </tr>
        </table>	        
       -->               
        
        <table width="98%" border="1" cellspacing="0" cellpadding="0" align="center">
	        <tr>
	            <td width="15%" class="healineblue1" bgcolor="#ECF0DB"  align="left" >&nbsp;Nombre</td>
	            <td width="45%" class="textonegroBlod" align="left" id="Nombre">&nbsp;${telefonoTO.mobileTO.nombre}</td>
	            <td width="20%" class="healineblue1" bgcolor="#ECF0DB" align="left">&nbsp;Puntos Disponibles</td>
	            <td width="18%" class="textonegroBlod" id="ptsDisponibles" >&nbsp;${telefonoTO.puntosTO.ptosDisponibles}</td>	                    	            
	        </tr>
	         <tr> 
              	<td class="healineblue1" bgcolor="#ECF0DB" align="left">&nbsp;Teléfono</td>
              	<td class="textonegroBlod" align="left" id="telefono">&nbsp;${telefonoTO.mobileTO.telefono}</td>
              	<td class="healineblue1" bgcolor="#ECF0DB" align="left" >&nbsp;Estatus Puntos</td>
              	<td class="textonegroBlod" id="millasDisp">&nbsp;<font color="red">${telefonoTO.puntosTO.ptosStatus}</font>
              	</td>
          	</tr>
          <tr> 
              <td class="healineblue1" bgcolor="#ECF0DB" align="left">&nbsp;Cuenta</td>
              <td class="textonegroBlod" id="cuenta">&nbsp;${telefonoTO.mobileTO.cuenta}</td>
              <td class="healineblue1" bgcolor="#ECF0DB" align="left">&nbsp;Cuenta Padre</td> 
              <td  class="textonegroBlod" >&nbsp;${telefonoTO.mobileTO.cuentaPadre}</td> 
          </tr>
           <tr> 
              <td class="healineblue1" bgcolor="#ECF0DB" align="left">&nbsp;Tecnología</td>
              <td class="textonegroBlod" id="tecnologia" >&nbsp;${telefonoTO.mobileTO.tecnologia}</td>
              <td class="healineblue1" bgcolor="#ECF0DB" align="left">&nbsp;Region</td>
              <td class="textonegroBlod" >&nbsp;${telefonoTO.regionTxt}</td>
          </tr>
          <tr> 
              <td class="healineblue1" bgcolor="#ECF0DB" align="left">&nbsp;Segmento</td>              
              	<c:if test="${telefonoTO.segmento=='Azul'}">
                	  <td class="textonegroBlod" >&nbsp;
                    	  <span style="width: 20px;background-color: #00BFFF">
                    	  	${telefonoTO.segmento}
                    	  </span>
                  	</td>                                            
              	</c:if> 
              <c:if test="${telefonoTO.segmento=='Oro'}">
                  <td class="textonegroBlod"  >&nbsp;<span style="width: 20px;background-color: #FEE000">
                      ${telefonoTO.segmento}
                      </span>
                      </td>
              </c:if>
              <c:if test="${telefonoTO.segmento=='Platino'}">
                  <td class="textonegroBlod"   >&nbsp;
                      <span style="width: 20px;background-color: #CCCCCC">
                      ${telefonoTO.segmento}
                      </span>
                  </td>
              </c:if>                                       
              <td class="healineblue1" bgcolor="#ECF0DB" align="left">&nbsp;Puntos en Subasta</td>
              <td class="textonegroBlod" >&nbsp;${telefonoTO.banSubasta}</td> 
          </tr>          
          <tr> 
              <td class="healineblue1" bgcolor="#ECF0DB" align="left">&nbsp;Bandera Subasta</td> 
              <td class="textonegroBlod" >&nbsp;
                  <c:if test="${telefonoTO.banSubasta==0}">
                  Inactiva
                  </c:if>
                  <c:if test="${telefonoTO.banSubasta==1}">
                  Activa
                  </c:if>
              </td>
              <td class="healineblue1" bgcolor="#ECF0DB" align="left">&nbsp;</td> 
              <td class="textonegroBlod" >&nbsp;</td> 
          </tr>
        </table>		      	
        <br>
	    	
         <DIV id="subTarjeta1"  style="BORDER:solid 1px silver; height: 120px;visibility: visible;display: block;margin-left: 5px;margin-right: 5px;">             
	        <table width="98%" cellspacing="0" cellpadding="0" align="center">
            	<tr><td class="textonegroBlodTrs">&nbsp;&nbsp;Totales</td></tr>
        	</table>	        
	        <jsp:include page="totales.jsp"/>
        </div>
        
         <DIV id="subTarjeta12  style="BORDER:solid 1px silver; height: 150px;visibility: visible;display: block;margin-left: 5px;margin-right: 5px;">
         	<table width="98%" cellspacing="0" cellpadding="0" align="center">
            	<tr><td class="textonegroBlodTrs">&nbsp;&nbsp;Líneas</td></tr>
        	</table>
         	<jsp:include page="lineas.jsp"/>
         </div>
        
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
