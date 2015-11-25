<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/permisos.tld" prefix="securityCa"%>
<html>
	<head>
		<title>Retencion</title>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
		
		<link rel="stylesheet" href='<c:url value="/commons/js/sc_textsheet.css"/>' type="text/css">
		<script language="javascript" type="text/javascript">
			function telefono_onfocus() {
				var form = document.getElementById('frmConsulta');
				form.opcion(0).checked = true;
				resetCampo('cuentaActual');
			}

			function cuenta_onfocus() {
				var form = document.getElementById('frmConsulta');
				form.opcion(1).checked = true;
				resetCampo('telefono');
			}

			function validaCampos() {
				var form = document.getElementById('frmConsulta');
    			if(form.telefono.value == "" && form.cuentaActual.value == ""){
        			window.alert ("Debe introducir un número telefónico o una cuenta.");
        			return false;
    			}
    			if(form.opcion(0).checked){
	      			if(isNaN(form.telefono.value) || form.telefono.value.length != 10 ){
    	    			window.alert ("El número de teléfono no es válido.");
						form.telefono.focus();
		    	    	return false;
      				}
    			}
	    		if(form.opcion(1).checked){
    	  			if(isNaN(form.cuentaActual.value)){
        				window.alert ("El número de cuenta no es válido.");
						form.cuentaActual.focus();
        				return false;
	      			}
    			}    			
				return true;
			}
			
			function validaLinea(){
				var validaCorrecto = validaCampos();
				if(validaCorrecto==true){
					ocultaCtaAnterior();
					//document.parentWindow.parent.muestradDiv();
					var cuentaActual = document.getElementById('cuentaActual').value;
					var telefono = document.getElementById('telefono').value;
					var region = document.getElementById('region').value;
					var form = document.getElementById('frmConsulta');
					form.action="./validaLineaCertificado.do";
					form.submit();
					//Frame1.location = "./validaLineaCertificado.do?cuentaActual=" + cuentaActual + "&telefono=" + telefono + "&region=" + region;
				}else{
					return false;
				}
				
					  			
	  		}
	  		function ocultaCtaAnterior(){
	  			//document.parentWindow.parent.muestradDiv();
	  			document.getElementById("ctaAnterior").style.visibility = 'hidden';
				document.getElementById("ctaAnterior").style.display = 'none';
			}
	  		
	  		function muestraCertificados(){
				var certificados = document.getElementById("certificados");
				certificados.style.visibility = 'visible';
				certificados.style.display = 'block';
			}
			
			function eligeCertificado(opcion,descripcion){
				var operacion = document.getElementById('operacion');
				var desc = descripcion + ' Certificado de Lealtad'
				var certiLealtad = document.getElementById('certiLealtad');
				operacion.value=opcion;
				ocultaCertificados();
				certiLealtad.value = desc;				
			}
			
			function ocultaCertificados(){
				var certificados = document.getElementById("certificados");
				certificados.style.visibility = 'hidden';
				certificados.style.display = 'none';
			}
			
			function ocultarCtaAnterior(){
				var ctaAnterior =  document.getElementById("ctaAnterior");
				ctaAnterior.style.visibility = "hidden";
				ctaAnterior.style.display = "none";
			}
			
			function continuar(){
				var validaCorrecto = validaCampos();
				var operacion = document.getElementById('operacion').value;
				var form = document.getElementById('frmConsulta')
				
				//continua validaciones
				if(validaCorrecto==true){
					if(operacion=="N"){
    					alert('Debe elegir una acción para el Certificado de lealtad.');
    					return false;
    				}
    				if(operacion=="A"){
	    				form.action="./generarCertificadoLealtad.do?cuenta=${telefonoTO.cuenta}&secuencia=${telefonoTO.secuencia}&fechaM2K=${telefonoTO.mobileTO.fecAddM2K}&mobileTONombre=${telefonoTO.mobileTO.nombre}&mobileTOTelefono=${telefonoTO.mobileTO.telefono}&mobileTOCuenta=${telefonoTO.mobileTO.cuenta}&mobileTOFecAltaUser=${telefonoTO.mobileTO.fecAltaUser}&mobileTONoBajas=${telefonoTO.mobileTO.noBajas}&mobileTOPromedio=${telefonoTO.mobileTO.promedio}&mobileTOTecnologia=${telefonoTO.tecnologia}&mobileTOSegmento=${telefonoTO.segmento}&mobileTOCtaPadre=${telefonoTO.ctaPadre}&mobileTOEstatusPtos=${telefonoTO.puntosTO.ptosStatus}&mobileTOPuntosDistReser=${telefonoTO.puntosTO.distribuidorReserva}&mobileTOPuntosFecReser =${telefonoTO.puntosTO.fecReservacion}&mobileTOSistema=${telefonoTO.sistema}&telefonoTOFechaAlta=${telefonoTO.fechaAlta}&mobileTOCiclo=${telefonoTO.mobileTO.ciclo}&mobileTORegion=${telefonoTO.region}&mobileTOPlan=${telefonoTO.mobileTO.planM2K}&mobileTOAdendum=${telefonoTO.mobileTO.addM2K}&mobileTOFecAdendum=${telefonoTO.mobileTO.fecAddM2K}&mobileTOStatus=${telefonoTO.mobileTO.status}&mobileTOMontoFact0=${mobileTOMontoFact0}&mobileTOMontoFact1=${mobileTOMontoFact1}&mobileTOMontoFact2=${mobileTOMontoFact2}&mobileTOMontoFact3=${mobileTOMontoFact3}";
					}	
					if(operacion=="B"){
						form.action="./consultarCertificadoLealtad.do?cuenta=${telefonoTO.cuenta}&secuencia=${telefonoTO.secuencia}&fechaM2K=${telefonoTO.mobileTO.fecAddM2K}&mobileTONombre=${telefonoTO.mobileTO.nombre}&mobileTOTelefono=${telefonoTO.mobileTO.telefono}&mobileTOCuenta=${telefonoTO.mobileTO.cuenta}&mobileTOFecAltaUser=${telefonoTO.mobileTO.fecAltaUser}&mobileTONoBajas=${telefonoTO.mobileTO.noBajas}&mobileTOPromedio=${telefonoTO.mobileTO.promedio}&mobileTOTecnologia=${telefonoTO.tecnologia}&mobileTOSegmento=${telefonoTO.segmento}&mobileTOCtaPadre=${telefonoTO.ctaPadre}&mobileTOEstatusPtos=${telefonoTO.puntosTO.ptosStatus}&mobileTOPuntosDistReser=${telefonoTO.puntosTO.distribuidorReserva}&mobileTOPuntosFecReser =${telefonoTO.puntosTO.fecReservacion}&mobileTOSistema=${telefonoTO.sistema}&telefonoTOFechaAlta=${telefonoTO.fechaAlta}&mobileTOCiclo=${telefonoTO.mobileTO.ciclo}&mobileTORegion=${telefonoTO.region}&mobileTOPlan=${telefonoTO.mobileTO.planM2K}&mobileTOAdendum=${telefonoTO.mobileTO.addM2K}&mobileTOFecAdendum=${telefonoTO.mobileTO.fecAddM2K}&mobileTOStatus=${telefonoTO.mobileTO.status}&mobileTOMontoFact0=${mobileTOMontoFact0}&mobileTOMontoFact1=${mobileTOMontoFact1}&mobileTOMontoFact2=${mobileTOMontoFact2}&mobileTOMontoFact3=${mobileTOMontoFact3}";
					}
					if(operacion=="C"){						
						window.open('./imprimirCertificadoLealtad.do?cuenta=${telefonoTO.cuenta}&secuencia=${telefonoTO.secuencia}&fechaM2K=${telefonoTO.mobileTO.fecAddM2K}&mobileTONombre=${telefonoTO.mobileTO.nombre}&mobileTOTelefono=${telefonoTO.mobileTO.telefono}&mobileTOCuenta=${telefonoTO.mobileTO.cuenta}','', '');
						return false; 
					}	
					if(operacion=="D"){
						form.action='./cancelarCertificadoLealtad.do?cuenta=${telefonoTO.cuenta}&secuencia=${telefonoTO.secuencia}&fechaM2K=${telefonoTO.mobileTO.fecAddM2K}&mobileTONombre=${telefonoTO.mobileTO.nombre}&mobileTOTelefono=${telefonoTO.mobileTO.telefono}&mobileTOCuenta=${telefonoTO.mobileTO.cuenta}';
					}
					
					if(operacion!="C"){
						ocultaContenido();
						//document.parentWindow.parent.muestradDiv();
					}
					form.submit();
				}else{
					return false;
				}
			}
			
			function resetCampo(idCampo){
				var campo = document.getElementById(idCampo);
				campo.value="";				
			}
			function ocultaContenido(){
				var bloqueContenido = document.getElementById('bloqueContenido');
				bloqueContenido.style.visibility = "hidden";
				bloqueContenido.style.display = "none";
			}
			
		</script>
	</head>
	<body bgcolor="#ffffff" marginwidth="0" marginheight="0" style="background-color: transparent;MARGIN-TOP: 0px; 
			OVERFLOW-Y: hidden; OVERFLOW-X: hidden;WIDTH:926px; HEIGHT: 378px;position: absolute;top: 0px;visibility: visible;display: block;">
	<script>	
    	document.parentWindow.parent.activaCargando('hidden','none');     
	</script>
	<form name="frmConsulta" method="post" action="">
	
	<div id="bloqueContenido" style="visibility: visible;display: block;">
				<c:if test="${errorValidaLinea==null}">	
		<table width="98%" border="0" cellspacing="0" cellpadding="0" align="center" bordercolor="#ffffff" height="62">
			<tr>
				<td colspan="4" class="titulo" height="42">&nbsp;&nbsp;Certificado de Lealtad</td>
			</tr>
			<tr height="20"><td></td></tr>
			<tr>
				<td colspan="4" class="healineblue1">Informaci&oacute;n de Cuenta Actual</td>
			</tr>
			<tr>
				<td class="healineblue1">
						<input type="radio" name="opcion" value="telefono" onclick="resetCampo('cuentaActual');" checked>
				</td>
            	<td class="healineblue1">Teléfono:</td>
            	<td class="healineblue1">
            		<c:if test="${telefonoTO==null || telefonoActual==null}">
              			<input type="text" id="telefono" name="telefono" maxlength="10" onFocus="return telefono_onfocus();" onKeyDown="if(event.keyCode==13) document.getElementById('btnContinuar').click()">
              		</c:if>
              		<c:if test="${telefonoTO!=null && telefonoActual!=null}">
              			<input type="text" id="telefono" name="telefono" value="${telefonoActual}" maxlength="10" onFocus="return telefono_onfocus();" onKeyDown="if(event.keyCode==13) document.getElementById('btnContinuar').click()">
              		</c:if> 
              	</td>
              	<td class="healineblue1"></td>
			</tr>
			<tr>
				<td class="healineblue1">
					<input type="radio" name="opcion" value="cuenta" onclick="resetCampo('telefono');">            		
            	</td>
            	<td class="healineblue1">Cuenta</td>
          		<td class="textonegroBlodTrs">
          			<c:if test="${telefonoTO==null || cuentaActual==null}"> 
        				<input type="text" id="cuentaActual" name="cuentaActual" maxlength="9" onfocus="return cuenta_onfocus();" onKeyDown="if(event.keyCode==13) document.getElementById('btnContinuar').click()">
        			</c:if>
        			<c:if test="${telefonoTO!=null && cuentaActual!=null}">
        				<input type="text" id="cuentaActual" name="cuentaActual" value="${cuentaActual}" maxlength="9" onfocus="return cuenta_onfocus();" onKeyDown="if(event.keyCode==13) document.getElementById('btnContinuar').click()">
        			</c:if>
        			 
        		</td>
        		<td class="textonegroBlodTrs">
        			<a class="LinkOut" style="width:30; text-align:center" onmouseover='this.className="LinkIn";'
						onmouseout='this.className="LinkOut";' id="btnContinuar" onClick="validaLinea();">&nbsp;Continuar&nbsp;&nbsp;</a>
        		</td>
			</tr>
			<tr>
				<td class="healineblue1"></td>            		
            	<td class="healineblue1">Región:</td>
            	<td class="healineblue1">
              		 <select name="region" id="region" size=1>
						<option value="9">9</option>
					</select>
              	</td>
              	<td class="healineblue1"></td>
			</tr>
		</table>
		<br>
		<br>
		
		<div id="ctaAnterior" style="visibility: visible;display=block;">
			<c:if test="${telefonoTO!=null}">
				<!--<table width="98%" border="1" cellspacing="0" cellpadding="0" align="center" bordercolor="#ffffff" height="62">  -->
				<table width="98%" border="0" cellspacing="0" cellpadding="0" align="center"  height="62">
						
						<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="33">
							<tr>
								<td colspan="3" class="healineblue1">
									<font color=blue> &nbsp;&nbsp;  ** En caso de que la línea haya tenido un cambio de región, agregar los datos de la cuenta anterior y la región a 
										la que pertenecía. </font>
								</td>
							</tr>
							<tr>
								<td colspan="3" height="10"></td>
							</tr>								
							<tr>
								<td colspan="3" class="healineblue1">Informaci&oacute;n de Cuenta Anterior</td>
							</tr>
							
							<tr>
								<td class="healineblue1">
										<input type="radio" name="opcion" value="telefono" checked>
								</td>							
				            	<td class="healineblue1">Cuenta:</td>
				            	<td class="healineblue1">
				            		<input type="text" id="cuentaAnterior" name="cuentaAnterior" maxlength="9"  onKeyDown="if(event.keyCode==13) document.getElementById('bContinuar').click()">
				              	</td>
				              	<td class="healineblue1"></td>
							</tr>						
							
							<tr>
								<td class="healineblue1" width="10"></td>            		
			            		<td class="healineblue1" width="30">Región:</td>
			        	    	<td class="healineblue1">
			    	          		 <select name="regionOrig" size=1>
											<option value="1">1</option>
											<option value="2">2</option>
											<option value="3">3</option>
											<option value="4">4</option>
											<option value="5">5</option>
											<option value="6">6</option>
											<option value="7">7</option>
											<option value="8">8</option>
										</select>
			              		</td>
			            	  	<td class="healineblue1"></td>
							</tr>
						</securityCa:validaPerfil>							
					</table>
					<br>
					
					<table width="98%" border="0" cellspacing="0" cellpadding="0" align="center" bordercolor="#ffffff" height="62">
						<tr>
							<td class="healineblue1"></td>            		
		            		<td class="healineblue1" width="250">Certificados de Lealtad:</td>
		        	    	<td class="healineblue1" width="350">
		    	          		 <input type="text" id="certiLealtad" name="certiLealtad" size="35" readonly="readonly" onclick="muestraCertificados();">
		              		</td>
		            	  	<td class="healineblue1">
		            	  		<a class="LinkOut" style="width:30; text-align:center" onmouseover='this.className="LinkIn";'
								onmouseout='this.className="LinkOut";' id="bContinuar"  onClick="continuar();">&nbsp;Continuar&nbsp;&nbsp;</a>
		            	  	</td>
						</tr>						
					</table>		
			</c:if>
		</div>
		</c:if>
		
		<c:if test="${errorValidaLinea!=null}">
				<br>
				<br>
				<center>
					<div align="left" style="position: absolute; top: 2%; left: 10px; background-color: transparent; "AllowTransparency>
						<table border="0" width="100%" align="center" class="BloqueErrorEspera" style="background-color: transparent;">	
							<tr align="center">	<td valign="middle" height="42" width="100%" bgcolor="#ECF0DB">${idmensaje},${mensaje}</td></tr>
						</table>
					</div>
				</center>
			</c:if>
		
  		<input type="hidden" name="operacion" id="operacion" value="N">  	
  		<div id="certificados" style="visibility: hidden;display: none;position: absolute;top: 230px;left: 300px;" class="BloqueBlanco">
						<table border="0" cellspacing="0" cellpadding="0">
							<tr>
								<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="34">
									<td class="healineblue1">
				            		<input type="radio" name="tipoReten" value="Genera"   onKeyDown="if(event.keyCode==13) document.getElementById('bContinuar').click()" onClick="eligeCertificado('A',this.value);">Generar Certificado de Lealtad</td>
								</securityCa:validaPerfil>
    	  			    	</tr>
    	  			    	<tr>
    	  			    		<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="35">
				            		<td class="healineblue1">
										<input type="radio" name="tipoReten" value="Consulta"   onKeyDown="if(event.keyCode==13) document.getElementById('bContinuar').click()" onClick="eligeCertificado('B',this.value);">Consultar Certificados de Lealtad</td>
								</securityCa:validaPerfil>
      			    		</tr>
      			    		<tr>
      			    			<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="36">
	      			      			<td class="healineblue1">
		      			      			<input type="radio" name="tipoReten" value="Imprime" onKeyDown="if(event.keyCode==13) document.getElementById('bContinuar').click()" onClick="eligeCertificado('C',this.value);">Imprimir Certificado de Lealtad</td>
		      			      	</securityCa:validaPerfil>
				          	</tr>
				          	<tr>
          			  			<td class="healineblue1">
	          			  			<input type="radio" name="tipoReten" value="Cancela" onKeyDown="if(event.keyCode==13) document.getElementById('bContinuar').click()" onClick="eligeCertificado('D',this.value);">Cancelar  Certificado de Lealtad</td>
				          	</tr>
				         </table>
					</div>
	</div>
    
	</form>	
    	 
</body>
</html>