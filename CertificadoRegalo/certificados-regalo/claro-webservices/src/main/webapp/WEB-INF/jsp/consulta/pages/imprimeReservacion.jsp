<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/permisos.tld" prefix="securityCa"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Imprime Reservaciones</title>
<link rel="stylesheet" href='<c:url value="/commons/js/sc_textsheet.css"/>' type="text/css">
<script >
	function muestraDiv(opc){	
		if(!opc == "C" || !opc=="S" || !opc=="G" || !opc=="T" || !opc=="A"){
			window.alert("El tipo de redención no es válido!!");
		}
	}
</script>

</head>
<body  style="background-color: transparent;" onload="muestraDiv('${impresionTO.tipoReden}')">
<input type="hidden" name="individual" id="individual" value="1">
<form name="frmImprimir" id="frmImprimir" action="./jsp-content/ConstanciaRedencion.pdf">
       
<input type="hidden" name="telefono" id="telefono" value="${telefonoTO.telefono}">
<input type="hidden" name="folio" id="folio" value="${folio}">
<input type="hidden" name="fecha" id="fecha" value="${fecha}">
<input type="hidden" name="cuenta" id="cuenta" value="${telefonoTO.cuenta}">
       
<DIV id="divImpresion"  style=" visibility:visible;display:block; OVERFLOW-Y: hidden; OVERFLOW-X: hidden; BORDER:hidden;width: 800px;height:1020px;position:absolute;top: 5px; left: 25px;" >
	<table width="98%" border="0" cellpadding="1" cellspacing="0">
		<tr>
			<td height="20" width="10"></td><td height="20"></td><td height="20"></td>
		</tr>
		<tr>
			<td width="10">&nbsp;</td>
			<td valign="top">
				<table width="98%" class="main" border="1" cellspacing="0" cellpadding="2" align="center">
					<tr valign="top">
						<td colspan="3">
							<table width="98%" border="0" cellspacing="0" cellpadding="0" align="center" bordercolor="#ffffff">
								<tr>
									<td colspan=3" class="healineblue1" valign="middle" height="60" align="center">
										<img src='<c:url value="/commons/images/logo-telcel.jpg"/>'>
									</td>
									<td>
										<img style="background-color: transparent" height=60 src='<c:url value="/commons/images/logo_circulo_azul_1.gif"/>' width=194>
									</td>
								</tr>
							</table>
							<table width="98%" border="0" cellspacing="0" cellpadding="0" align="center" bordercolor="#ffffff">
								<tr>
									<td class="healineblue1" valign="middle" height="20"></td>
								</tr>
							</table>
							<table width="98%" border="0" cellspacing="0" cellpadding="0" align="center">
								<tr>
									<td>
										<table width="98%" border="0" cellspacing="0" cellpadding="0" align="center">
											<tr>
												<td class="titulo" colspan="4" height="42">&nbsp;&nbsp;Constancia de Canje</td>
											</tr>
											<tr><td>&nbsp;</td></tr>											
										</table>
									</td>
								</tr>								
							</table>
							
							<table width="98%" border="1" cellspacing="0" cellpadding="0" align="center" style="font-size: 8pt;">
								<tr>
									<td class="healineblue1" valign="middle" bgcolor="#eff0f1" colspan="1">Nombre:</td>
									<td colspan=3 class="textonegroBlod" valign="middle">${telefonoTO.mobileTO.lastName} ${telefonoTO.mobileTO.firstName}</td>
								</tr>
								<tr>
									<td class="healineblue1" valign="middle" bgcolor="#eff0f1" width="25%">	Teléfono:</td>
									<td class="textonegroBlod" valign="middle" width="28%">${telefonoTO.telefono}</td>
									<td class="healineblue1" valign="middle" bgcolor="#eff0f1" width="28%">Fecha de Canje:</td>
									<td class="textonegroBlod" valign="middle" width="19%" align="right">${impresionTO.fechaOperacion}</td>
								</tr>
								<tr>
									<td class="healineblue1" valign="middle" bgcolor="#eff0f1">Cuenta:</td>
									<td class="textonegroBlod" valign="middle">${telefonoTO.cuenta}</td>
									<td class="healineblue1" valign="middle" bgcolor="#eff0f1">Tecnología:</td>
									<td class="textonegroBlod" valign="middle" align="right">${telefonoTO.tecnologia}</td>
								<tr>
									<td class="healineblue1" valign="middle" bgcolor="#eff0f1">Segmento:</td>
									<td class="textonegroBlod" valign="middle">${telefonoTO.segmento}</td>
									<td class="healineblue1" valign="middle" bgcolor="#eff0f1">Puntos Disponibles:</td>
									<td class="textonegroBlod" valign="middle" align="right">${impresionTO.ptosDispCF}</td>
								</tr>								
								<tr>
									<td class="healineblue1" valign="middle" bgcolor="#eff0f1">Centro de Atención:</td>
									<td class="textonegroBlod" valign="middle">${impresionTO.idPuntoVenta}</td>
									<td class="healineblue1" valign="middle" bgcolor="#eff0f1">Puntos Redimidos:</td>
									<td class="textoredBlod" valign="middle" align="right">- ${impresionTO.valorPuntosCF}</td>
								</tr>
								<tr>
									<td class="healineblue1" valign="middle" bgcolor="#eff0f1">Forma de Redención:</td>
									<td class="textonegroBlod" valign="middle">${impresionTO.formaReden}</td>
									<td class="healineblue1" valign="middle" bgcolor="#eff0f1">Puntos Disponibles Restantes:</td>
									<td class="textoredBlod" valign="middle" align="right">${impresionTO.ptsDispRestantesCF}</td>
								</tr>
								<tr>
									<td class="healineblue1" valign="middle" bgcolor="#eff0f1">Tipo de Redención:</td>
									<td class="textonegroBlod" valign="middle" colspan="3">${impresionTO.tipoRedenDB}</td>
								</tr>
								<c:if test="${impresionTO.tipoReden=='C'}">
									<tr>
										<td class="healineblue1" valign="middle" bgcolor="#eff0f1">Plan Tarifario Anterior:</td>
										<td class="textonegroBlod" valign="middle">${impresionTO.planAnt}</td>
										<td class="healineblue1" valign="middle" bgcolor="#eff0f1">Plan Tarifario Nuevo:</td>
										<td class="textonegroBlod" valign="middle" align="right">${impresionTO.planNuevo}</td>
									</tr>
									<tr>
										<td class="healineblue1" valign="middle" bgcolor="#eff0f1">Addendum Anterior:</td>
										<td class="textonegroBlod" valign="middle">${impresionTO.fechaPlazoAnt}</td>
										<td class="healineblue1" valign="middle" bgcolor="#eff0f1">Addendum Sugerido:</td>
										<td class="textonegroBlod" valign="middle" align="right">${impresionTO.fechaPlazoSeg}</td>
									</tr>
									<tr>
										<td class="healineblue1" valign="middle" bgcolor="#eff0f1">	Plazo Forzoso Anterior:</td>
										<td class="textonegroBlod" valign="middle">${impresionTO.addAnt}</td>
										<td class="healineblue1" valign="middle" bgcolor="#eff0f1">Plazo Forzoso Nuevo:</td>
										<td class="textonegroBlod" valign="middle" align="right">${impresionTO.addNuevo}</td>
									</tr>
								</c:if>
								<tr>
									<td class="healineblue1" valign="middle" bgcolor="#eff0f1" colspan="1">Folio:</td>
									<td colspan="3" class="textonegroBlod" valign="middle">${impresionTO.folio}</td>
								</tr>								
							</table>
							<table width="98%" border="0" cellspacing="0" cellpadding="0" align="center">
								<tr><td>&nbsp;</td></tr>
								<tr><td>&nbsp;</td></tr>								
								<tr><td class="textonegroBlodTrs">DETALLE DE REDENCION/CAMBIO DE EQUIPO</td></tr>
							</table>
							
							<c:if test="${(impresionTO.tipoReden=='C') or (impresionTO.tipoReden=='S')}">							
								<table width="98%" border="1" cellspacing="0" cellpadding="0" align="center" style="max-width: 98%;position: static;" >								
									<tr bgcolor="#eff0f1" align="center">
										<td class="healineblue1" width="20%">Descripción</td>
										<td class="healineblue1" width="8%">Marca</td>
										<td class="healineblue1" width="8%">Modelo</td>
										<td class="healineblue1" width="10%">IMEI</td>
										<td class="healineblue1" width="8%">Puntos</td>
										<td class="healineblue1" width="8%">Precio</td>
										<c:if test="${impresionTO.inbursaFormato != ''}">
											<td class="healineblue1" width="8%">Descuento Inbursa</td>
											<td class="healineblue1" width="8%">Descuento Marca</td>
										</c:if>
										<td class="healineblue1" width="8%">
											<table>
												<c:choose>
													<c:when test="${impresionTO.bonosAltoValor != 0}">
														<td class="healineblue1" bgcolor="#eff0f1" width="35%" align="center">Descuento Alto Valor</td>
													</c:when>
													<c:when test="${impresionTO.bonosRoext != 0}">
														<td class="healineblue1" bgcolor="#eff0f1" width="35%" align="center">Descuento Roext</td>
													</c:when>
													<c:when test="${impresionTO.bonosGap != 0}">
														<td class="healineblue1" bgcolor="#eff0f1" width="35%" align="center">Descuento Especial</td>
													</c:when>
													<c:otherwise>
														<td class="healineblue1" bgcolor="#eff0f1" width="35%" align="center">Descuento</td>
													</c:otherwise>
												</c:choose>											
												<!--<tr><td>Descuento</td></tr>-->
												<!--<tr><td style="font-size: 6pt">ROEXT/AltoValor</td></tr>-->
											</table>										
										</td>
										<td class="healineblue1" width="8%">Precio IVA</td>
										<td class="healineblue1" width="22%">Comentario</td>
									</tr>
									<tr>
										<td class="textonegroBlod" align="left">${impresionTO.descripcion}</td>
										<td class="textonegroBlod" align="center">${impresionTO.marca}</td>
										<td class="textonegroBlod" align="center">${impresionTO.modelo}</td>
										<td class="textonegroBlod" align="center">
											<table>
												<tr><td>${impresionTO.esnImeiR}</td></tr>
												<tr><td>${impresionTO.esnImeiP}</td></tr>
											</table>											
										</td>
										<td class="textonegroBlod" align="center">${impresionTO.valorPuntosCF}</td>
										<td class="textonegroBlod" align="center">$${impresionTO.precioFormato}</td>
										<c:if test="${impresionTO.inbursaFormato != ''}">
											<td class="textonegroBlod" align="center">$${impresionTO.inbursaFormato}</td>
											<td class="textonegroBlod" align="center">$${impresionTO.marcaFormato}</td>
										</c:if>	
										<td class="textonegroBlod" align="center">$${impresionTO.descuentoFormato}</td>
										<td class="textonegroBlod" align="center">$${impresionTO.precioIvaFormato}</td>										
										<td class="textonegroBlod" align="left">${impresionTO.comentario}</td>										
									</tr>	
								</table>							
							</c:if>							
							<c:if test="${(impresionTO.tipoReden=='G')}">							
								<table width="98%" border="1" cellspacing="0" cellpadding="0" align="center">								
									<tr bgcolor="#eff0f1" align="center">
										<td class="healineblue1" width="24%">Descripción</td>
										<td class="healineblue1" width="8%">Marca</td>
										<td class="healineblue1" width="8%">Modelo</td>
										<td class="healineblue1" width="10%">IMEI</td>
										<td class="healineblue1" width="8%">Puntos</td>
										<td class="healineblue1" width="8%">Precio</td>										
										<td class="healineblue1" width="8%">Precio IVA</td>
										<td class="healineblue1" width="26%">Comentario</td>
									</tr>
									<tr>
										<td class="textonegroBlod" align="left">${impresionTO.descripcion}</td>
										<td class="textonegroBlod" align="center">${impresionTO.marca}</td>
										<td class="textonegroBlod" align="center">${impresionTO.modelo}</td>
										<td class="textonegroBlod" align="center">${impresionTO.esnImeiR}</td>
										<td class="textonegroBlod" align="center">${impresionTO.valorPuntosCF}</td>
										<td class="textonegroBlod" align="center">$${impresionTO.precioFormato}</td>										
										<td class="textonegroBlod" align="center">$${impresionTO.precioIvaFormato}</td>										
										<td class="textonegroBlod" align="left">${impresionTO.comentario}</td>										
									</tr>	
								</table>							
							</c:if>
							<c:if test="${(impresionTO.tipoReden=='T')}">							
								<table width="98%" border="1" cellspacing="0" cellpadding="0" align="center">								
									<tr bgcolor="#eff0f1" align="center">
										<td class="healineblue1" width="30%">Descripción</td>
										<td class="healineblue1" width="15%">Clave</td>
										<td class="healineblue1" width="12%">Valor Puntos</td>
										<td class="healineblue1" width="12%">Diferencia Pesos</td>
										<td class="healineblue1" width="30%">Comentario</td>
									</tr>
									<tr>
										<td class="textonegroBlod" align="left">${impresionTO.descripcion}</td>										
										<td class="textonegroBlod" align="center">${impresionTO.marca}</td>
										<td class="textonegroBlod" align="center">${impresionTO.valorPuntos}</td>																				
										<td class="textonegroBlod" align="center">$${impresionTO.precioFormato}</td>										
										<td class="textonegroBlod" align="left">${impresionTO.comentario}</td>										
									</tr>	
								</table>							
							</c:if>
							<c:if test="${(impresionTO.tipoReden=='A')}">							
								<table width="98%" border="1" cellspacing="0" cellpadding="0" align="center">								
									<tr bgcolor="#eff0f1" align="center">
										<td class="healineblue1" width="30%">Descripción</td>
										<td class="healineblue1" width="15%">ICCID</td>
										<td class="healineblue1" width="12%">Valor Puntos</td>
										<td class="healineblue1" width="12%">Diferencia Pesos</td>
										<td class="healineblue1" width="30%">Comentario</td>
									</tr>
									<tr>
										<td class="textonegroBlod" align="left">${impresionTO.descripcion}</td>										
										<td class="textonegroBlod" align="center">${impresionTO.iccid}</td>
										<td class="textonegroBlod" align="center">${impresionTO.valorPuntosCF}</td>																				
										<td class="textonegroBlod" align="center">$${impresionTO.precioIvaFormato}</td>										
										<td class="textonegroBlod" align="left">${impresionTO.comentario}</td>										
									</tr>	
								</table>							
							</c:if>
							<table width="98%" border="0" cellspacing="0" cellpadding="0" align="center">								
								<tr>
									<td colspan=2 class="textorojoBlodTrs">*Nota: La diferencia en pesos mostrada en esta constancia incluye I.V.A</td>
								</tr>
								<tr><td>&nbsp;</td></tr>
							</table>
							<c:if test="${impresionTO.tipoReden=='T'}">
								<table width="98%" border="0" cellspacing="0" cellpadding="0" align="center">
									<tr>
										<td colspan=2 class="textoazulBlodTrs">Al
										cancelar la línea, el usuario perderá la promoción de minutos
										adicionales y no se le bonificarán. En caso de cambio de plan, los
										minutos adicionales se pierden automáticamente. Los minutos
										adicionales pueden ser utilizados únicamente en el periodo en que
										se aplican, en caso de que no se utilicen en ese periodo, se
										perderán.</td>
									</tr>
									<tr><td>&nbsp;</td></tr>
								</table>
							</c:if>
							<table width="98%" border="0" cellspacing="0" cellpadding="0" align="center">
								<tr><td>&nbsp;</td></tr>
								<tr><td>&nbsp;</td></tr>
								<tr><td>&nbsp;</td></tr>
								<tr><td>&nbsp;</td></tr>								
								<tr>
									<td colspan="2" class="textonegroBlodTrs">
										<P>&nbsp;</P>
										<table cellSpacing="0" cellPadding="0" width="98%" align="center" border="0">
											<tr>
												<td class="healineblue1" width="45%" height="15" align="center" style="font-size: 7pt;">
													${usuarioTO.nombre}( ${usuarioTO.idUsuario})
													<HR id=HR1 size=1 align=left width=295>	Nombre y Firma del Asesor													
												</td>
												<td width="5%"></td>
												<td class=healineblue1 width="45%" height="15" align="center" style="font-size: 7pt;">
													${telefonoTO.mobileTO.lastName} ${telefonoTO.mobileTO.firstName}
													<HR id=HR1 size=1 align=left width=295>Nombre y Firma del Cliente
												</td>												
											</tr>
										</table>									
									</td>
								</tr>
								<tr>
									<td colspan="2" class="textonegroBlodTrs">
										<table cellSpacing="0" cellPadding="0" width="251" align="center" border="0">
											<tr><td colspan="2">&nbsp;</td></tr>
											<tr><td colspan="2">&nbsp;</td></tr>
											<tr><td colspan="2">&nbsp;</td></tr>
											<tr><td colspan="2">&nbsp;</td></tr>
											<tr><td colspan="2">&nbsp;</td></tr>
											<tr><td colspan="2">&nbsp;</td></tr>
											<tr><td colspan="2">&nbsp;</td></tr>
											<tr>
												<td class=healineblue1 style="font-size: 7pt;" align="center">
													<HR id="HR1" size="1" align="center" width="335">Vo Bo Nombre y Firma Radiomovil DIPSA SA de CV
												</td>
											</tr>
											<tr><td colspan="2">&nbsp;</td></tr>
											<tr><td colspan="2">&nbsp;</td></tr>
										</table>
									</td>
								</tr>
							</table>											
							<table width="98%" border="0" cellspacing="0" cellpadding="0" align="center">
								<tr><td colspan="2">&nbsp;</td></tr>
								<tr><td colspan="2">&nbsp;</td></tr>
								<tr><td colspan="2">&nbsp;</td></tr>
								<tr><td colspan="2">&nbsp;</td></tr>								
								<tr>
									<td width="50%">
										<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="75">
											<input class="LinkOut" onmouseover='this.className="LinkIn";'onmouseout='this.className="LinkOut";'
												type="submit" name="Submit" value="Generar archivo PDF">
										</securityCa:validaPerfil>										
									</td>					
									<td width="40%">
										<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="76">
											<a class="LinkOut" style="width:10%" onmouseover='this.className="LinkIn";'
												onmouseout='this.className="LinkOut";' id="Link1"
												onClick="window.print();">&nbsp;&nbsp;Imprimir&nbsp;&nbsp;
											</a>
										</securityCa:validaPerfil>
									</td>
								</tr>
								<tr><td colspan="2">&nbsp;</td></tr>								
							</table>						
						</td>
					</tr>					
				</table>
			</td>
		</tr>		
	</table>
</DIV>
</form>
</body>
</html>
