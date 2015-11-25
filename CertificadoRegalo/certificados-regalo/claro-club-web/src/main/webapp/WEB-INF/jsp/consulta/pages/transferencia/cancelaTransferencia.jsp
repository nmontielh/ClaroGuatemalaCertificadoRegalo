<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/permisos.tld" prefix="securityCa" %>
<!-- CANCELACION DE TRANSFERENCIA - JAPA 17/12/2012 Folio 120213 / -->
<html>
    <head>
        <title>Transferencia puntos</title>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
        <link rel="stylesheet" href='<c:url value="/commons/js/sc_textsheet.css"/>' type="text/css">
    </head>
    
	<body marginwidth="0" marginheight="0" style="MARGIN:0px; background-color:transparent;" >
    	<script type="text/javascript">
    		document.parentWindow.parent.activaCargando('hidden','none');
    		
        	function validaDetalleInfo(){
        	        		    		    
	        	//var ctaDestino 	= frmDetalleTransferencia.cuentaDestino.value;
	        	//ctaDestino 		= ctaDestino.replace(/^\s*/, "").replace(/\s*$/, "");
	        	//frmDetalleTransferencia.cuentaDestino.value = ctaDestino;
        	/*
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
        		// JSC - Folio: 96556/ 	
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
        		*/
				
				if(frmCancelaTransferencia.comentario.value == ""){
        			window.alert("Debe capturar un comentario.");
        			return false;
        		}
				
        		//envia solicitud       		        		        		  	        		        	
        		frmCancelaTransferencia.method = "POST";
        		frmCancelaTransferencia.action = "./cancelaTransferencia.do";
        		frmCancelaTransferencia.submit();
        	}        
         
    	</script>
    	<form id="frmCancelaTransferencia" name="frmCancelaTransferencia" method="POST">
    		<!-- Datos linea origen -->			
			<input type="hidden" name="telefonoOrigen" value="${lineaOrigen.telefono}">
			<input type="hidden" name="cuentaOrigen" value="${lineaOrigen.mobileTO.cuenta}">
			<input type="hidden" name="regionOrigen" value="${lineaOrigen.region}">
			<input type="hidden" name="secuenciaOrigen" value="${lineaOrigen.mobileTO.secuencia}">
			
			<!-- Datos linea destino -->			
			<input type="hidden" name="telefonoDestino" value="${lineaDestino.telefono}">
			<input type="hidden" name="cuentaDestino" value="${lineaDestino.cuenta}">
			<input type="hidden" name="regionDestino" value="${lineaDestino.region}">
			<input type="hidden" name="secuenciaDestino" value="${lineaDestino.secuencia}">
			
			<!-- Datos detalle -->
			<input type="hidden" name="vista" value="C"><!-- Realizar cancelacion -->



			
			<!-- Datos para validaciones en proceso de transferencia -->
			<input type="hidden" name="cuentaLineaOrigen" value="${lineaOrigen.mobileTO.cuenta}">
			<input type="hidden" name="nombre" value="${telefonoTO.mobileTO.nombre}">
			<input type="hidden" name="ptosDisponibles" value="${telefonoTO.puntosTO.ptosDisponibles}">
			<input type="hidden" name="tecnologia" value="${telefonoTO.tecnologia}">
			<input type="hidden" name="fecFactura" value="${telefonoTO.fecFactura}">
			<input type="hidden" name="fechaAlta" value="${telefonoTO.fechaAlta}">
			
			
			<input type="hidden" name="tipoTransferencia" value="${tipoTransferencia}">
			<input type="hidden" name="tipoPlan" value="">
			<input type="hidden" name="cuentaPadreOrigen" value="${telefonoTO.mobileTO.cuentaPadre}">
			<input type="hidden" name="estatus" value="${telefonoTO.mobileTO.status}">
			<input type="hidden" name="rfcOrigen" value="${telefonoTO.mobileTO.rfc}">
			
			
			
			
			<table width="98%" border="0" cellspacing="0" cellpadding="0" align="center">
				<tr>
					<td class="titulo" height="42" width="30%">&nbsp;&nbsp;Cancelaci&oacute;n de Transferencia de Puntos</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
			</table>
			<table width="98%"  cellspacing="0" cellpadding="0" align="center"  >
				<tr valign="top">
					<td colspan="3">
						<table width="98%" border="1" cellspacing="0" cellpadding="0" align="center">
							<tr>
								<td width="12%" class="healineblue1" valign="middle" height="20" bgcolor="#eff0f1">
									<div align="center">Nombre</div>
								</td>
								<td class="textonegroBlod" width="34%" height="20" valign="middle">
									<div align="left">&nbsp;${lineaOrigen.mobileTO.nombre }&nbsp;</div>
         						</td>
         						<td width="28%" class="healineblue1" valign="middle" height="19" bgcolor="#eff0f1">
         							<div align="center">Puntos Disponibles</div>
         						</td>
         						<td class="textonegroBlod" width="26%" height="19" valign="middle">
         							&nbsp;${lineaOrigen.puntosTO.ptosDisponibles }&nbsp;
         						</td>
         					</tr>
         					<tr>
         						<td width="12%" class="healineblue1" valign="middle" height="20" bgcolor="#eff0f1">
         							<div align="center">Tel&eacute;fono</div>
         						</td>
         						<td class="textonegroBlod" width="34%" height="20" valign="middle">
         							<div align="left">&nbsp;${lineaOrigen.mobileTO.telefono}&nbsp;</div>
         						</td>
         						<td width="28%" class="healineblue1" valign="middle" height="20" bgcolor="#eff0f1">
         							<div align="center">Estatus Puntos</div>
         						</td>
         						<td class="textonegroBlod" width="26%" height="20" valign="middle">
         							&nbsp;${lineaOrigen.puntosTO.ptosStatus}&nbsp;
         						</td>
         					</tr>
         					<tr>
         						<td width="12%" class="healineblue1" valign="middle" height="20" bgcolor="#eff0f1">
         							<div align="center">Cuenta</div>
         						</td>
         						<td class="textonegroBlod" width="34%" height="20" valign="middle">
         							<div align="left">&nbsp;${lineaOrigen.mobileTO.cuenta}&nbsp;</div>
         						</td>
         						<td width="28%" class="healineblue1" valign="middle" height="20" bgcolor="#eff0f1">
         							<div align="center">Tecnolog&iacute;a</div>
         						</td>
         						<td class="textonegroBlod" width="26%" height="20" valign="middle">
         							&nbsp;${lineaOrigen.tecnologia}&nbsp;
         						</td>
         					</tr>
         					</table>
         					<table>
         						<tr>
         							<td>&nbsp;</td>
         						</tr>
         						<tr>
         							<td class="healineblue1">
         								<font color='red'>  Datos de la Línea Destino</font>
         							</td>
         						</tr>
         					</table>
         					
         					<table width="98%" border="1" cellspacing="0" cellpadding="0" align="center">
								<tr>
								<td width="12%" class="healineblue1" valign="middle" height="20" bgcolor="#eff0f1">
         							<div align="center">Tel&eacute;fono</div>
         						</td>
         						<td class="textonegroBlod" width="34%" height="20" valign="middle">
         							<div align="left">&nbsp;${lineaDestino.telefono}&nbsp;</div>
         						</td>
         						<td width="28%" class="healineblue1" valign="middle" height="19" bgcolor="#eff0f1">
         							<div align="center">Puntos Disponibles Actualmente</div>
         						</td>
         						<td class="textonegroBlod" width="26%" height="19" valign="middle">
         							&nbsp;${lineaDestino.puntosTO.ptosDisponiblesTmp }&nbsp;
         						</td>
         					</tr>
         					<tr>
         						<td width="12%" class="healineblue1" valign="middle" height="20" bgcolor="#eff0f1">
         							<div align="center">Cuenta</div>
         						</td>
         						<td class="textonegroBlod" width="34%" height="20" valign="middle">
         							<div align="left">&nbsp;${lineaDestino.cuenta}&nbsp;</div>
         						</td>
         						<td width="28%" class="healineblue1" valign="middle" height="19" bgcolor="#eff0f1">
         							<div align="center">Total de Puntos Transferidos</div>
         						</td>
         						<td class="textonegroBlod" width="26%" height="19" valign="middle">
         							&nbsp;${lineaDestino.puntosTO.ptosDisponibles}&nbsp;
         						</td>
         					</tr>
							</table>
         					<table>
         						<tr>
         							<td>&nbsp;</td>
         						</tr>
         						<tr>
         							<td class="healineblue1">
         								<font color='red'>  Detalle de Puntos Transferidos</font>
         							</td>
         						</tr>
         					</table>
         					<table width="98%" border="1" cellspacing="0" cellpadding="0" align="center" bordercolor="#cccccc">
         						<tr>
         							<td class="healineblue1" bgcolor="#eff0f1" width="19%">Fecha facturación</td>
         							<td class="textonegroBlod" width="15%">&nbsp;${lineaDestino.puntosTO.fecFactura}</td>
         							<td class="healineblue1" bgcolor="#eff0f1" width="19%" >Puntos Renta</td>
         							<td class="textonegroBlod" width="14%">&nbsp;${lineaDestino.puntosTO.ptsRenta }</td>
         							<td class="healineblue1" bgcolor="#eff0f1" width="19%">Alianzas</td>
         							<td class="textonegroBlod" width="15%">&nbsp;${lineaDestino.puntosTO.ptsTransferidos}</td>
         						</tr>
         						<tr>
         							<td class="healineblue1" bgcolor="#eff0f1" width="19%" >Fecha vencimiento</td>
         							<td class="textonegroBlod" width="14%">&nbsp;${lineaDestino.puntosTO.fecVencer}</td>
         							<td class="healineblue1" bgcolor="#eff0f1" width="19%" >Por vencer</td>
         							<td class="textonegroBlod" width="14%">&nbsp;${lineaDestino.puntosTO.ptsPorVencer}</td>
         							<td class="healineblue1" bgcolor="#eff0f1" width="19%" >Redimidos</td>
         							<td class="textonegroBlod" width="14%">&nbsp;${lineaDestino.puntosTO.ptsRedimidos}</td>
         						</tr>
								<tr>
									<td class="healineblue1" bgcolor="#eff0f1" width="19%">Fecha vencimiento 1</td>
									<td class="textonegroBlod" width="15%">&nbsp;${lineaDestino.puntosTO.fecVencer1}</td>
									<td class="healineblue1" bgcolor="#eff0f1" width="19%" >Por vencer 1</td>
									<td class="textonegroBlod" width="14%">&nbsp;${lineaDestino.puntosTO.ptsPorVencer1}</td>
									<td class="healineblue1" bgcolor="#eff0f1" width="19%" >Vencidos</td>
									<td class="textonegroBlod" width="14%">&nbsp;${lineaDestino.puntosTO.ptsVencidos}</td>
								</tr>
								<tr>
									<td class="healineblue1" bgcolor="#eff0f1" width="19%" >Fecha vencimiento 2</td>
									<td class="textonegroBlod" width="14%">&nbsp;${lineaDestino.puntosTO.fecVencer2}</td>
									<td class="healineblue1" bgcolor="#eff0f1" width="19%">Por vencer 2 </td>
									<td class="textonegroBlod" width="15%">&nbsp;${lineaDestino.puntosTO.ptsPorVencer2}</td>
									<td class="healineblue1" bgcolor="#eff0f1" width="19%">Promoci&oacute;n</td>
									<td class="textonegroBlod" width="15%">&nbsp;${lineaDestino.puntosTO.ptsPromocion}</td>
								</tr>
								<tr>
									<td class="healineblue1" bgcolor="#eff0f1" width="19%" >Antigüedad</td>
									<td class="textonegroBlod" width="14%">&nbsp;${lineaDestino.puntosTO.ptsAntiguedad}</td>
									<td class="healineblue1" bgcolor="#eff0f1" width="19%">Excedentes</td>
									<td class="textonegroBlod" width="15%">&nbsp;${lineaDestino.puntosTO.ptsExcedentes}</td>
									<td class="healineblue1" bgcolor="#eff0f1" width="19%">&nbsp;</td>
									<td class="textonegroBlod" width="15%">&nbsp;</td>
								</tr>
							</table>
							<table width="40%" border="0" cellspacing="0" cellpadding="0" align="left">
								<tr>
         							<td colspan="2">&nbsp;</td>
         						</tr>
								<tr>
									<td width="15%" class="healineblue1" valign="middle" height="20">Comentario:&nbsp;</td>
									<td colspan="3" width="10%" height="20" valign="middle">
										<input id="comentario" name="comentario" maxlength="35" align="right" size="45" 
											onKeyDown="if(event.keyCode==13) document.getElementById('Link1').click()">
									</td>
								</tr>
							</table>
							</td>
                    </tr>
            </table>     
							<table width="98%">
								<tr>
         							<td>&nbsp;</td>
         						</tr>
								<tr>
									<td align="center" width="30%">
											<a class="LinkOut" style="width:30; text-align:center" 
												onmouseover='this.className="LinkIn";'
												onmouseout='this.className="LinkOut";'id="Link1"
												onClick="validaDetalleInfo();">
												&nbsp;Cancela Transferencia&nbsp;&nbsp;
											</a>
									</td>
								</tr>
							</table>
                    	       
       </form>
    </body>
</html>
