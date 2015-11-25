<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/permisos.tld" prefix="securityCa" %>

<html>
    <head>
        <title>Transferencia puntos</title>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
        <link rel="stylesheet" href='<c:url value="/commons/js/sc_textsheet.css"/>' type="text/css">
                        
        <script type="text/javascript">
        	var tipoTransf = "ANEXO";
        	/*JSC - Folio: 96556*/        	
        	var tipoTransfEx = "EXCELENTE";
        	function validaDetalleInfo(){        		    		    
	        	var ctaDestino 	= frmDetalleTransferencia.cuentaDestino.value;
	        	ctaDestino 		= ctaDestino.replace(/^\s*/, "").replace(/\s*$/, "");
	        	frmDetalleTransferencia.cuentaDestino.value = ctaDestino;
        	
        		if(frmDetalleTransferencia.telefonoDestino.value == ""){
        			window.alert("Debe capturar el teléfono destino.");
        			return false;
        		} else if(isNaN(frmDetalleTransferencia.telefonoDestino.value) || 
        					frmDetalleTransferencia.telefonoDestino.value.length != 10){
        			window.alert ("El teléfono destino no es válido");
					frmDetalleTransferencia.telefonoDestino.value="";
					frmDetalleTransferencia.telefonoDestino.focus();
					return false;
				} else if(frmDetalleTransferencia.cuentaDestino.value == ""){
	        		window.alert("Debe capturar una cuenta destino.");
        			return false;
        		} else if(isNaN(frmDetalleTransferencia.cuentaDestino.value) || 
        					frmDetalleTransferencia.cuentaDestino.value.length < 2){
	        		window.alert("La cuenta destino no es valida");
	        		frmDetalleTransferencia.cuentaDestino.value = "";
	        		frmDetalleTransferencia.cuentaDestino.focus();
        			return false;
        		} else if(frmDetalleTransferencia.regionDestino.value == 0){
        			window.alert("Debe seleccionar la región destino.");
        			return false;
        		/*JSC - Folio: 96556*/ 	
        		} else if(frmDetalleTransferencia.tipoTransferencia.value == tipoTransf  || frmDetalleTransferencia.tipoTransferencia.value ==  tipoTransfEx){        		
        			if(frmDetalleTransferencia.puntosTrans.value == ""){
		        		window.alert("Debe capturar la cantidad de puntos a transferir.");		        		
	        			return false;        				
        			} else if(isNaN(frmDetalleTransferencia.puntosTrans.value) ||
        						frmDetalleTransferencia.puntosTrans.value <= 0){
		        		window.alert("La cantidad de puntos a transferir debe ser un número mayor a cero.");
		        		frmDetalleTransferencia.puntosTrans.value = "";
		        		frmDetalleTransferencia.puntosTrans.focus();		        		
	        			return false;
        			}
        		} 
				
				if(frmDetalleTransferencia.comentario.value == ""){
        			window.alert("Debe capturar un comentario.");
        			return false;
        		}
				
        		//envia solicitud       		        		        		  	        		        	
        		frmDetalleTransferencia.method = "POST";
        		frmDetalleTransferencia.action = "./aplicaTransferencia.do";        		
        		frmDetalleTransferencia.submit();
        	}        
        </script>
    </head>
    
	<body marginwidth="0" marginheight="0" style="MARGIN:0px; background-color:transparent;" >
    	<script>
    		document.parentWindow.parent.activaCargando('hidden','none'); 
    	</script>
    	<form id="frmDetalleTransferencia" name="frmDetalleTransferencia">
    		<!-- Datos linea origen -->			
			<input type="hidden" name="nombre" value="${telefonoTO.mobileTO.nombre}">
			<input type="hidden" name="ptosDisponibles" value="${telefonoTO.puntosTO.ptosDisponibles}">
			<input type="hidden" name="telefono" value="${telefonoTO.telefono}">
			<input type="hidden" name="cuenta" value="${telefonoTO.mobileTO.cuenta}">
			<input type="hidden" name="tecnologia" value="${telefonoTO.tecnologia}">
			<input type="hidden" name="region" value="${telefonoTO.region}">
			<input type="hidden" name="fecFactura" value="${telefonoTO.fecFactura}">
			<input type="hidden" name="fechaAlta" value="${telefonoTO.fechaAlta}">
			
			<!-- Datos detalle -->
			<input type="hidden" name="tipoTransferencia" value="${tipoTransferencia}">
			<input type="hidden" name="tipoPlan" value="">
			<input type="hidden" name="vista" value="D">
			<!-- Datos para validaciones en proceso de transferencia -->
			<input type="hidden" name="cuentaPadreOrigen" value="${telefonoTO.mobileTO.cuentaPadre}">
			<input type="hidden" name="cuentaLineaOrigen" value="${telefonoTO.cuenta}">
			<input type="hidden" name="estatus" value="${telefonoTO.mobileTO.status}">
			<input type="hidden" name="secuencia" value="${telefonoTO.mobileTO.secuencia}">
			<!--JSC - Folio: 96556 --> 
			<input type="hidden" name="rfcOrigen" value="${telefonoTO.mobileTO.rfc}">
			<table width="98%" border="0" cellspacing="0" cellpadding="0" align="center">
				<tr>
					<td class="titulo" height="42" width="30%">&nbsp;&nbsp;Transferencia de Puntos</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
			</table>
			
			<table width="100%"  cellspacing="0" cellpadding="0" align="center"  >
				<tr valign="top">
					<td colspan="3">
						<table width="98%" border="1" cellspacing="0" cellpadding="0" align="center">
							<tr>
								<td width="12%" class="healineblue1" valign="middle" height="20" bgcolor="#eff0f1">
									<div align="center">Nombre</div>
								</td>
								<td class="textonegroBlod" width="34%" height="20" valign="middle">
									<div align="left">${telefonoTO.mobileTO.nombre }</div>
         						</td>
         						<td width="28%" class="healineblue1" valign="middle" height="19" bgcolor="#eff0f1">
         							<div align="center">Puntos Disponibles</div>
         						</td>
         						<td class="textonegroBlod" width="26%" height="19" valign="middle">
         							${telefonoTO.puntosTO.ptosDisponibles }
         						</td>
         					</tr>
         					<tr>
         						<td width="12%" class="healineblue1" valign="middle" height="20" bgcolor="#eff0f1">
         							<div align="center">Tel&eacute;fono</div>
         						</td>
         						<td class="textonegroBlod" width="34%" height="20" valign="middle">
         							<div align="left"></div>${telefonoTO.telefono}
         						</td>
         						<td width="28%" class="healineblue1" valign="middle" height="20" bgcolor="#eff0f1">
         							<div align="center">Estatus Puntos</div>
         						</td>
         						<td class="textonegroBlod" width="26%" height="20" valign="middle">
         							${telefonoTO.puntosTO.ptosStatus}
         						</td>
         					</tr>
         					<tr>
         						<td width="12%" class="healineblue1" valign="middle" height="20" bgcolor="#eff0f1">
         							<div align="center">Cuenta</div>
         						</td>
         						<td class="textonegroBlod" width="34%" height="20" valign="middle">
         							<div align="left"></div>${telefonoTO.mobileTO.cuenta}
         						</td>
         						<td width="28%" class="healineblue1" valign="middle" height="20" bgcolor="#eff0f1">
         							<div align="center">Tecnolog&iacute;a</div>
         						</td>
         						<td class="textonegroBlod" width="26%" height="20" valign="middle">
         							${telefonoTO.tecnologia}
         						</td>
         					</tr>
         					</table>
         					<table>
         						<tr>
         							<td>&nbsp;</td>
         						</tr>
         						<tr>
         							<td class="healineblue1">
         								<font color='red'>  Totales de la L&iacute;nea Origen</font>
         							</td>
         						</tr>
         						<tr>
         							<td>&nbsp;</td>
         						</tr>
         					</table>
         					<table width="98%" border="1" cellspacing="0" cellpadding="0" align="center" bordercolor="#cccccc">
         						<tr>
         							<td class="healineblue1" bgcolor="#eff0f1" width="19%">Fecha facturación</td>
         							<td class="textonegroBlod" width="15%">&nbsp;${telefonoTO.fecFactura}</td>
         							<td class="healineblue1" bgcolor="#eff0f1" width="19%" >Puntos Renta</td>
         							<td class="textonegroBlod" width="14%">&nbsp;${telefonoTO.puntosTO.ptsRenta }</td>
         							<td class="healineblue1" bgcolor="#eff0f1" width="19%">Alianzas</td>
         							<td class="textonegroBlod" width="15%">&nbsp;${telefonoTO.puntosTO.ptsTransferidos}</td>
         						</tr>
         						<tr>
         							<td class="healineblue1" bgcolor="#eff0f1" width="19%" >Fecha vencimiento</td>
         							<td class="textonegroBlod" width="14%">&nbsp;${telefonoTO.puntosTO.fecVencer}</td>
         							<td class="healineblue1" bgcolor="#eff0f1" width="19%" >Por vencer</td>
         							<td class="textonegroBlod" width="14%">&nbsp;${telefonoTO.puntosTO.ptsPorVencer}</td>
         							<td class="healineblue1" bgcolor="#eff0f1" width="19%" >Redimidos</td>
         							<td class="textonegroBlod" width="14%">&nbsp;${telefonoTO.puntosTO.ptsRedimidos}</td>
         						</tr>
								<tr>
									<td class="healineblue1" bgcolor="#eff0f1" width="19%">Fecha vencimiento 1</td>
									<td class="textonegroBlod" width="15%">&nbsp;${telefonoTO.puntosTO.fecVencer1}</td>
									<td class="healineblue1" bgcolor="#eff0f1" width="19%" >Por vencer 1</td>
									<td class="textonegroBlod" width="14%">&nbsp;${telefonoTO.puntosTO.ptsPorVencer1}</td>
									<td class="healineblue1" bgcolor="#eff0f1" width="19%" >Vencidos</td>
									<td class="textonegroBlod" width="14%">&nbsp;${telefonoTO.puntosTO.ptsVencidos}</td>
								</tr>
								<tr>
									<td class="healineblue1" bgcolor="#eff0f1" width="19%" >Fecha vencimiento 2</td>
									<td class="textonegroBlod" width="14%">&nbsp;${telefonoTO.puntosTO.fecVencer2}</td>
									<td class="healineblue1" bgcolor="#eff0f1" width="19%">Por vencer 2 </td>
									<td class="textonegroBlod" width="15%">&nbsp;${telefonoTO.puntosTO.ptsPorVencer2}</td>
									<td class="healineblue1" bgcolor="#eff0f1" width="19%">&nbsp;</td>
									<td class="textonegroBlod" width="15%">&nbsp;</td>
								</tr>
							</table>
							<table width="98%" border="0" cellspacing="0" cellpadding="0" align="center">
								<tr>
									<td colspan="4">&nbsp;</td>
								</tr>
								<tr>
									<td colspan="4" class="healineblue1"> <font color=red> Datos de la Línea Destino</font></td>
								</tr>
         						<tr>
         							<td>&nbsp;</td>
         						</tr>								
								<tr>
									<td width="15%" class="healineblue1" valign="middle" height="20">Telefono Destino</td>
									<td class="textonegroBlod" width="10%" height="20" valign="middle">
										<input id="telefonoDestino" name="telefonoDestino" maxlength="10" size="11">
									</td>
								</tr>
								<tr>
									<td width="15%" class="healineblue1" valign="middle" height="20">Cuenta Destino</td>
									<td class="textonegroBlod" width="10%" height="20" valign="middle">
										<input id="cuentaDestino" name="cuentaDestino" maxlength="10" size="11">
									</td>
									<td width="11%" class="healineblue1" valign="middle" height="20" align="right">Región</td>
									<td width="50%" height="20" valign="middle">&nbsp;&nbsp;
			                    		<select size=1 name="regionDestino">
			                    			<option value=1 selected>1</option>
			                    			<option value=2>2</option>
			                    			<option value=3>3</option>
			                    			<option value=4>4</option>
			                    			<option value=5>5</option>
			                    			<option value=6>6</option>
			                    			<option value=7>7</option>
			                    			<option value=8>8</option>
			                    			<option value=9>9</option>
			                    		</select>
			                    	</td>
			                    </tr>
			                    <!--JSC - Folio: 96556 --> 
			                    <c:if test='${tipoTransferencia=="ANEXO" || tipoTransferencia == "EXCELENTE"}'>
									<tr>
										<td width="15%" class="healineblue1" valign="middle" height="20">Cantidad de Puntos</td>
										<td colspan="3" width="10%" height="20" valign="middle">
											<input id="puntosTrans" name="puntosTrans" maxlength="10" align="right" size="11" 
												onKeyDown="if(event.keyCode==13) document.getElementById('Link1').click()">
										</td>
									</tr>
								</c:if>
								<tr>
									<td width="15%" class="healineblue1" valign="middle" height="20">Comentario</td>
									<td colspan="3" width="10%" height="20" valign="middle">
										<input id="comentario" name="comentario" maxlength="40" align="right" size="41" 
											onKeyDown="if(event.keyCode==13) document.getElementById('Link1').click()">
									</td>
								</tr>
							</table>
							<table width="98%">
								<tr>
									<td align="center" width="30%">
										<!--JSC - Folio: 96556 --> 
										<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="79,80,161">
											<a class="LinkOut" style="width:30; text-align:center" 
												onmouseover='this.className="LinkIn";'
												onmouseout='this.className="LinkOut";'id="Link1"
												onClick="validaDetalleInfo();">
												&nbsp;Transferir&nbsp;&nbsp;
											</a>
										</securityCa:validaPerfil>										
									</td>
								</tr>
							</table>
                    	</td>
                    </tr>
            </table>            
       </form>
    </body>
</html>
