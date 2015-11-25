<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<html>
	<head>
		<title>Retencion</title>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
		
		<link rel="stylesheet" href='<c:url value="/commons/js/sc_textsheet.css"/>' type="text/css">
		<script language="javascript" type="text/javascript">

			function motivo_onchange() {
				form1.otro.value="";							  
			}
			
			function otro_onchange() {
			  form1.motivo.value="";
			}
			
			function Valida_onclick(sCuponGral, sCuponExtra, sTotal) {
				
  				if(form1.CuponGral.checked == false && form1.cuponExtra.checked == false){
    				form1.vCuponTotal.value="";
    			}else if(form1.CuponGral.checked == true && form1.cuponExtra.checked == false){
	   				form1.vCuponTotal.value=sCuponGral;
				}else if(form1.CuponGral.checked == false && form1.cuponExtra.checked == true){
	  				form1.vCuponTotal.value=sCuponExtra;
				}else if(form1.CuponGral.checked == true && form1.cuponExtra.checked == true){
	  				form1.vCuponTotal.value=sTotal;
				}
			}
			function Extra_onclick(sCuponTotal) {
 				if(form1.CuponGral.checked == false && form1.cuponExtra.checked == false){
  					form1.vCuponTotal.value="";
    			}else{
	  				form1.vCuponTotal.value=sCuponTotal;
				}
			}		
			function continuar() {
			
				if(form1.CuponGral.checked == false && form1.cuponExtra.checked == false){
	    			window.alert("Debe seleccionar el cupon de Descuento a Utilizar."); 
    				return false;
  				}
				if(form1.comenta.value == "" && form1.CuponGral.checked == true){
					window.alert("Debe especificar el comentario para justificar el Certificado de Descuento."); 
			    	return false;
			  	}
				if(form1.comenta2.value == "" && form1.cuponExtra.checked == true){
    				window.alert("Es necesario capturar un comentario para justificar el uso del Bono Extra."); 
    				return false;
  				}
  				if(form1.motivo.value == "0" && form1.otro.value == "" ){
			    	window.alert("Debe especificar el motivo de cancelación."); 
			    	return false;
			  	}
			  	
			  	var form = document.getElementById('form1');
			  	form.action = "./almacenaCertificadoLealtad.do";
			  	ocultaContenido();
			  	//document.parentWindow.parent.muestradDiv();			  	
			  	form.submit();
			}
			function salir(){
				var form = document.getElementById('form1');
				form.action="./retencion.do";
				form.submit();
			}
			function ocultaContenido(){
				var bloqueContenido = document.getElementById('bloqueContenido');
				bloqueContenido.style.visibility = "hidden";
				bloqueContenido.style.display = "none";
			}
			
			
		</SCRIPT>
	</head>
	<body  marginwidth="0" marginheight="0" style="MARGIN: 0px; background-color: transparent;">
		<script>	
    		document.parentWindow.parent.activaCargando('hidden','none');     
		</script>
		
		<form name="form1" method="post" action="" >
		
		<div id="bloqueContenido" style="visibility: visible;display: block;">
		
		<table width="98%" border="0" cellpadding="0" cellspacing="0" class='main' >
			<tr> 
		  	<td height="10" width="10"></td>
		    <td height="10" width="740"></td>
		  </tr>
		  <tr> 
		    <td width="10">&nbsp;</td>
		  	<td valign="top">
					<table width="98%" border="0" cellspacing="0" cellpadding="0" align="center" >
		      	<tr> 
				  		<td>
				  			<table width="98%" border="0" cellspacing="0" cellpadding="0" align="center" >
									<tr> 
					  				<td class="titulo" height="42">&nbsp;&nbsp;Certificado de Lealtad</td>
									</tr>
				  			</table>
					  <table width="98%" border="1" cellspacing="0" cellpadding="0" align="center" height="115">
		            	<tr> 
			              	<td width="15%" class="healineblue1" bgcolor="#ECF0DB" align="left" height="20">Nombre</td>
			                <td class="textonegroBlod" width="50%" height="20" valign="middle"> 
			                	<div align="left"></div>${nombre}</td>
			                <td width="15%" class="healineblue1" bgcolor="#ECF0DB" align="left" height="20">Cuenta padre</td>
			                <td class="textonegroBlod" width="20%" height="20" valign="middle">
			                	<div align="left"></div>${mobileTOCtaPadre}</td>
		              </tr>
		              <tr> 
			                <td width="15%" class="healineblue1" bgcolor="#ECF0DB" align="left" height="20">Teléfono</td>
			                <td class="textonegroBlod" width="50%" height="20" valign="middle"> 
			                <div align="left"></div>${mobileTOTelefono}</td>
			                <td width="15%" class="healineblue1" bgcolor="#ECF0DB" align="left" height="20"> 
			                	Estatus Puntos</td>
			                <td class="textonegroBlod" width="20%" height="20" valign="middle" style="COLOR: crimson"> 
			                <div align="left"></div>${mobileTOEstatusPtos}</td>
		              </tr>
		              <tr> 
			                <td width="15%" class="healineblue1" bgcolor="#ECF0DB" align="left" height="20">Cuenta</td>
			                <td class="textonegroBlod" width="50%" height="20" valign="middle"> 
			                <div align="left"></div>${cuenta}</td>
							        <td colspan=2 width="15%" class="textonegroBlod" valign="middle" height="20">${mobileTOPuntosDistReser}&nbsp;</td>
		              </tr>
		              <tr> 
			                <td width="15%" class="healineblue1" bgcolor="#ECF0DB" align="left" height="20">Tecnología</td>
			                <td class="textonegroBlod" width="50%" height="20" valign="middle">${mobileTOTecnologia}</td>
			                <td colspan=2 width="15%" class="textonegroBlod" valign="middle" height="20">${mobileTOPuntosFecReser}&nbsp;</td>
		              </tr>
		              <tr> 
			              	<td width="15%" class="healineblue1" bgcolor="#ECF0DB" align="left" height="20">Segmento</td>
			                <td class="textonegroBlod" width="50%" height="20" valign="middle">${mobileTOSegmento}</td>
			                <td colspan=2 width="15%" class="textonegroBlod" valign="middle" height="20">&nbsp;</td>
		              </tr>
				</table>
		            <BR>
		            <table width="98%" border="1" cellspacing="0" cellpadding="0" align="center">
		            	<tr> 
		              	<td bgcolor="#ECF0DB" align="left" class="healineblue1" width="16%">Sistema</td>
		                <td class="textonegroBlod" width="17%">${mobileTOSistema}</td>
		                <td bgcolor="#ECF0DB" align="left" class="healineblue1" width="17%">Fecha alta</td>
		                <td class="textonegroBlod" width="17%">${telefonoTOFechaAlta}</td>
		                <td bgcolor="#ECF0DB" align="left" class="healineblue1" width="16%">Ciclo</td>
		                <td class="textonegroBlod" width="17%">${mobileTOCiclo}</td>
		              </tr>
		              <tr> 
		                <td bgcolor="#ECF0DB" align="left" class="healineblue1" width="16%">Región</td>
		                <td class="textonegroBlod" width="17%">R0${mobileTORegion}</td>
		                <td bgcolor="#ECF0DB" align="left" class="healineblue1" width="17%">Plan</td>
		                <td class="textonegroBlod" width="17%">${mobileTOPlan}</td>
		                <td bgcolor="#ECF0DB" align="left" class="healineblue1" width="16%">Adendum</td>
		                <td class="textonegroBlod" width="17%">${mobileTOAdendum}</td>
		              </tr>
		              <tr> 
		                <td bgcolor="#ECF0DB" align="left" class="healineblue1" width="16%">Fecha adendum</td>
		                <td class="textonegroBlod" width="17%">${mobileTOFecAdendum}</td>
		                <td bgcolor="#ECF0DB" align="left" class="healineblue1" width="17%">Fecha alta M2K</td>
		                <td class="textonegroBlod" width="17%">${mobileTOFecAltaUser}</td>
		                <td bgcolor="#ECF0DB" align="left" class="healineblue1" width="16%">Estatus</td>
		                <td class="textonegroBlod" width="17%">${mobileTOStatus}</td>
		              </tr>
		            </table>
		            <BR>
					<table width="98%" border="0" cellspacing="0" cellpadding="0" align="center">
						<tr><td class="textonegroBlodTrs">&nbsp;&nbsp;ARPU (Facturación antes de IVA y sin Ajustes)</td></tr>
			    	</table>
					<table width="98%" border="1" cellspacing="0" cellpadding="0" align="center">
		            	<tr> 
		              	<td bgcolor="#ECF0DB" align="left" class="healineblue1" width="20%">Facturación 1</td>
		                <td class="textonegroBlod" width="10%" align="right">$<c:out value="${mobileTOMontoFact0}"></c:out></td>
		                <td bgcolor="#ECF0DB" align="left" class="healineblue1" width="20%">Facturación 3</td>
		                <td class="textonegroBlod" width="10%" align="right">$<c:out value="${mobileTOMontoFact2}"></c:out></td>
		                <td bgcolor="#ECF0DB" align="left" class="healineblue1" width="25%">Promedio</td>
		                <td class="textonegroBlod" width="10%" align="right">$<c:out value="${promedio}"></c:out></td>
		              </tr>
		              <tr> 
		                <td bgcolor="#ECF0DB" align="left" class="healineblue1" width="20%">Facturación 2</td>
		                <td class="textonegroBlod" width="10%" align="right">$<c:out value="${mobileTOMontoFact1}"></c:out></td>
		                <td bgcolor="#ECF0DB" align="left" class="healineblue1" width="20%">Facturación 4</td>
		                <td class="textonegroBlod" width="10%" align="right">$<c:out value="${mobileTOMontoFact3}"></c:out></td>
		                <td bgcolor="#ECF0DB" align="left" class="healineblue1" width="25%">Puntos por Indicador</td>
		                <td class="textonegroBlod" width="10%" align="right"><font color="blue"><B>${retencionTO.porcARPU}</b></font></td>
		              </tr>
		            </table>
					<BR>
						<table width="98%" border="0" cellspacing="0" cellpadding="0" align="center">
							<tr><td class="textonegroBlodTrs">&nbsp;&nbsp;ANTIGÜEDAD</td></tr>
					    </table>
						<table width="98%" border="1" cellspacing="0" cellpadding="0" align="center">
			            	<tr> 
				                <td bgcolor="#ECF0DB" align="left" class="healineblue1" width="20%">Fecha Alta  M2K</td>
				                <td class="textonegroBlod" width="10%">${mobileTOFecAltaUser}</td>
				                <td bgcolor="#ECF0DB" align="left" class="healineblue1" width="20%">No. de Meses</td>
				                <td class="textonegroBlod" width="10%" align="right">${nMeses}</td>
				                <td bgcolor="#ECF0DB" align="left" class="healineblue1" width="25%">Puntos por Indicador</td>
				                <td class="textonegroBlod" width="10%" align="right"><font color="blue"><b>${retencionTO.porcAntig}</B></FONT></td>
		            	  </tr>
		            	</table>
						<BR>
						<table width="98%" border="0" cellspacing="0" cellpadding="0" align="center">
							<tr><td class="textonegroBlodTrs">&nbsp;&nbsp;CANCELACIONES POR COBRANZA</td></tr>
					    </table>
						<div align="left">
						<table width="59%" border="1" cellspacing="0" cellpadding="0">
							<tr>
								<td bgcolor="#ECF0DB" align="left" class="healineblue1" width="20%">No. de	Cancelaciones</td>
								<td class="textonegroBlod" width="10%" align="right">${mobileTONoBajas}</td>
								<td bgcolor="#ECF0DB" align="left" class="healineblue1" width="20%">Puntos por
									Indicador</td>
								<td class="textonegroBlod" width="10%" align="right"><FONT
									color="blue"><B>${retencionTO.porcCob}</B></FONT></td>
							</tr>
						</table>
					</div>
					<BR>
					<BR>
					<c:if test="${nMeses <12 || promedio < 200}">
						<table width="98%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td class="textonegroBlodTrs" width="20%" align="center">
								<FONT size="+1">"El cliente no cumple con los requisitos de ARPU y/o Antigüedad para la generación de un Certificado de Lealtad".</FONT></td>
							</tr>						
						</table>	
						<BR>
						<BR>
						<table width="98%" border="0" cellspacing="0" cellpadding="0" align="center">
	  						<tr>
					  			<td align="center" width="24%"> <a class="LinkOut"
									style="width:100px" onmouseover='this.className="LinkIn";'
									onmouseout='this.className="LinkOut";' id="Link1"
									onClick="salir();">&nbsp;&nbsp;Salir&nbsp;&nbsp;</a> 
		             			</td>                      
							</tr>
			  			</table>
		  			</c:if>
					<c:if test="${nMeses >=12 && promedio >= 200}">
						<table width="98%" border="0" cellspacing="0" cellpadding="0" align="center">
							<tr><td class="textonegroBlodTrs">&nbsp;&nbsp;VALOR DEL CERTIFICADO</td></tr>
				  		</table>
				  		<div align="left">
							<table width="98%" border="1" cellspacing="0" cellpadding="0">
								<tr>
									<td bgcolor="#ECF0DB" align="left" class="healineblue1">
		 								<c:if test="${retencionTO.VCentifextra == null || retencionTO.VCentifextra == ''}">
				        					<input type="checkbox" name="CuponGral" id="CuponGral" value="CG" onClick="Valida_onclick('${retencionTO.valorCupon}','0','${iVTotal}');">Valor General
				        				</c:if>
				        				<c:if test="${retencionTO.VCentifextra != null && retencionTO.VCentifextra != ''}">
				        					<input type="checkbox" name="CuponGral" id="CuponGral" value="CG" onClick="Valida_onclick('${retencionTO.valorCupon}','${retencionTO.VCentifextra}','${iVTotal}');">Valor General 
				        				</c:if>
			    					</td>
			    					<td class="textonegroBlod" align="right">
			    						<FONT color="maroon">$<input type="TEXT" style="font:bold"  name="vCuponGral" size="4" maxlength="10" value="${retencionTO.valorCupon}" readonly>.00
			    						</FONT>
			    					</td>
									<td class="textonegroBlodTrs">Comentarios1:<input type='text' name='comenta' size=100 maxlength="200"></td>		            					
								</tr>
								<tr>
									<td bgcolor="#ECF0DB" align="left" class="healineblue1">
		 								<c:if test="${retencionTO.VCentifextra == null || retencionTO.VCentifextra == ''}">
											<input type="checkbox" name="cuponExtra" id="cuponExtra" value="CE" onClick="Valida_onclick('${retencionTO.valorCupon}','0','${iVTotal}');">Valor Extra
										</c:if> 
										<c:if test="${retencionTO.VCentifextra != null && retencionTO.VCentifextra != ''}">
											<input type="checkbox" name="cuponExtra" id="cuponExtra" value="CE" onClick="Valida_onclick('${retencionTO.valorCupon}','${retencionTO.VCentifextra}','${iVTotal}');">Valor Extra
										</c:if>
			    					</td>
			    					<td class="textonegroBlod" align="right">
										<c:if test="${retencionTO.VCentifextra == null || retencionTO.VCentifextra == ''}">
											<font color="maroon">$<input type="TEXT" style="font:bold"  name="vCuponExtra" size="4" maxlength="10" value="0" readonly>.00</font>
										</c:if> 
										<c:if test="${retencionTO.VCentifextra != null && retencionTO.VCentifextra != ''}">
											<font color="maroon">$<input type="TEXT" style="font:bold"  name="vCuponExtra" size="4" maxlength="10" value="${retencionTO.VCentifextra}" readonly>.00</font>
										</c:if>
									</td>
									<td class="textonegroBlodTrs">Comentarios2:<input type='text' name='comenta2' size=100 MAXLENGTH=200></td>
								</tr>
								<tr>
									<td colspan="2" bgcolor="#ECF0DB" align="left" class="healineblue1">VALOR TOTAL :</td>
									<td class="textonegroBlod" align="right">
										<font color="maroon">$ 
												<input type="TEXT" style="color:maroon; font:bold" name="vCuponTotal" id="vCuponTotal" size="4" maxlength="10" readonly>.00</font>
									</td>
								</tr>
							</table>
								</div>
								<br>
								<div align="left">
									<table width="60%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td width="10%" class="healineblue1" height="20" bgcolor="#ECF0DB" align="right">Motivo de Cancelación :</td>
											<td width="50%" class="textonegroBlod">
												<select name="motivo" id="motivo" size=1 language=javascript onClick="return motivo_onchange()">
								               		<option value='0'>Seleccione una opción...</option>
								               		<c:if test="${retencionTO.motivos != null}">
								               			<c:forEach items="${retencionTO.motivos}" var="mtvo">
								               				<option value="${mtvo.descripcion}"><c:out value="${mtvo.descripcion}"></c:out></option>
								               			</c:forEach>
													</c:if>
												</select>
											</td>
										</tr>
										<tr>
										<td width="30%" class="textonegroBlod" height="20" align="right">Otro. Cuál:</td>
										<td width="30%" class="textonegroBlod"><input type='text' name="otro" id="otro" size="36" maxlength="30" onClick="return otro_onchange()"></td>																	
										</tr>
									</table>
								</div>
								<BR>
								<table width="98%" border="0" cellspacing="0" cellpadding="0" align="center">
		            				<tr> 
		            					<td colspan=3 align="center"> <a class="LinkOut" style="width:20%" onmouseover='this.className="LinkIn";' onmouseout='this.className="LinkOut";'
		                					id="Link1" onClick="continuar();">&nbsp;Continuar&nbsp;&nbsp;</a></td>                 
		              				</tr>
								</table>
					 			<BR>
					 		</c:if>		            		
		          		</td>
		        	</tr>
		      	</table>
			</td>
		</tr>
		</table>
		<input type="hidden" id="telefonoTOFechaAlta" name="telefonoTOFechaAlta" value="${telefonoTOFechaAlta}">
		<input type="hidden" id="secuencia" name="secuencia" value="${secuencia}">
		<input type="hidden" id="cuenta" name="cuenta" value="${cuenta}">
		<input type="hidden" id="mobileTOTelefono" name="mobileTOTelefono" value="${mobileTOTelefono}">
		<input type="hidden" id="mobileTORegion" name="mobileTORegion" value="${mobileTORegion}">
		<input type="hidden" id="mobileTOFecAltaUser" name="mobileTOFecAltaUser" value="${mobileTOFecAltaUser}">
		<input type="hidden" id="mobileTOSegmento" name="mobileTOSegmento" value="${mobileTOSegmento}">
		<input type="hidden" id="mobileTOPlan" name="mobileTOPlan" value="${mobileTOPlan}">
		<input type="hidden" id="mobileTOMontoFact0" name="mobileTOMontoFact0" value="${mobileTOMontoFact0}">
		<input type="hidden" id="mobileTOMontoFact1" name="mobileTOMontoFact1" value="${mobileTOMontoFact1}">
		<input type="hidden" id="mobileTOMontoFact2" name="mobileTOMontoFact2" value="${mobileTOMontoFact2}">
		<input type="hidden" id="mobileTOMontoFact3" name="mobileTOMontoFact3" value="${mobileTOMontoFact3}">
		<input type="hidden" id="promedio" name="promedio" value="${promedio}">
		<input type="hidden" id="mobileTONoBajas" name="mobileTONoBajas" value="${mobileTONoBajas}">
		
		<input type="hidden" id="retencionTOValorCupon" name="retencionTOValorCupon" value="${retencionTO.valorCupon}">
		<input type="hidden" id="retencionTOVCentifextra" name="retencionTOVCentifextra" value="${retencionTO.VCentifextra}">
		<input type="hidden" id="retencionTOPorcAntig" name="retencionTOPorcAntig" value="${retencionTO.porcAntig}">
		<input type="hidden" id="retencionTOPorcARPU" name="retencionTOPorcARPU" value="${retencionTO.porcARPU}">
		<input type="hidden" id="retencionTOPorcCob" name="retencionTOPorcCob" value="${retencionTO.porcCob}">
		
		<input type="hidden" id="cuentaAnterior" name="cuentaAnterior" value="${cuentaAnterior}">
		<input type="hidden" id="sRegionAnt" name="sRegionAnt" value="${sRegionAnt}">
		<input type="hidden" id="nMesesCuentaAnt" name="nMesesCuentaAnt" value="${nMesesCuentaAnt}">
		<input type="hidden" id="fechaM2K" name="fechaM2K" value="${fechaM2K}">
		<input type="hidden" id="nMeses" name="nMeses" value="${nMeses}">
		<input type="hidden" id="tipoBusqueda" name="tipoBusqueda" value="${tipoBusqueda}">		
		
		</div>
		</form>
</body>
</html>