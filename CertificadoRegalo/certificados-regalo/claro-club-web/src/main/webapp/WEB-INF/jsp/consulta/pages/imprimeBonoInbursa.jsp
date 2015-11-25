<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/permisos.tld" prefix="securityCa"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Imprime Bonos Inbursa</title>
<link rel="stylesheet" href='<c:url value="/commons/js/sc_textsheet.css"/>' type="text/css">


</head>
<body  style="background-color: transparent;">
<input type="hidden" name="individual" id="individual" value="1">
<form name="frmImprimir" id="frmImprimir" action="./jsp-content/ConstanciaRedencion.pdf">
       
<input type="hidden" name="telefono" id="telefono" value="${telefonoTO.telefono}">
<input type="hidden" name="folio" id="folio" value="${folio}">
<input type="hidden" name="fecha" id="fecha" value="${fecha}">
<input type="hidden" name="cuenta" id="cuenta" value="${telefonoTO.cuenta}">
<input type="hidden" name="inbursa" id="inbursa" value="imprime">
       
<DIV id="divImpresion"  style=" visibility:visible;display:block; OVERFLOW-Y: hidden; OVERFLOW-X: hidden; BORDER:hidden;width: 800px;height:740px;position:absolute;top: 5px; left: 25px;" >
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
												<td class="titulo" colspan="4" height="42">&nbsp;&nbsp;Certificado de Descuento</td>
											</tr>
											<tr><td>&nbsp;</td></tr>											
										</table>
									</td>
								</tr>								
							</table>
							
							<table width="98%" border="1" cellspacing="0" cellpadding="0" align="center" style="font-size: 8pt;">
								<tr>
									<td width="40%" class="healineblue1" valign="middle" bgcolor="#eff0f1" colspan="1">Folio:</td>
									<td width="40%" class="textonegroBlod" valign="middle"><fmt:formatNumber groupingUsed="false" minIntegerDigits="8" value="${impresionTO.folio}"/></td>
									<td width="20%" rowspan="10" valign="middle">
										<table height="76" border="1" cellspacing="0" cellpadding="0" align="center" width="84">
         									<tr>
         										<td width="77" class="textonegroBlod" align=center>Holograma</td>
         									</tr>
         								</table>
         							</td>
								</tr>
								<tr>
									<td class="healineblue1" valign="middle" bgcolor="#eff0f1" >Nombre:</td>
									<td class="textonegroBlod" valign="middle">${telefonoTO.mobileTO.lastName} ${telefonoTO.mobileTO.firstName}</td>
								</tr>
								<tr>
									<td class="healineblue1" valign="middle" bgcolor="#eff0f1" width="25%">	Teléfono:</td>
									<td class="textonegroBlod" valign="middle" width="28%">${telefonoTO.telefono}</td>
								</tr>
								<tr>
									<td class="healineblue1" valign="middle" bgcolor="#eff0f1">Cuenta:</td>
									<td class="textonegroBlod" valign="middle">${telefonoTO.cuenta}</td>
								</tr>
								<tr>
									<td class="healineblue1" valign="middle" bgcolor="#eff0f1">Región:</td>
									<td class="textonegroBlod" valign="middle">${telefonoTO.region}</td>
								</tr>
								<tr>
									<td class="healineblue1" valign="middle" bgcolor="#eff0f1">Importe del Descuento por Aplicar:</td>
									<td class="textonegroBlod" valign="middle"><fmt:formatNumber value="${impresionTO.inbursaFormato+impresionTO.marcaFormato}" pattern="0.00"/></td>
								</tr>
								<tr>
									<td class="healineblue1" valign="middle" bgcolor="#eff0f1">Fecha de Impresión:</td>
									<td class="textonegroBlod" valign="middle">${impresionTO.fechaOperacion}</td>
								</tr>
								<tr>
									<td class="healineblue1" valign="middle" bgcolor="#eff0f1">Fecha de Expiración:</td>
									<td class="textonegroBlod" valign="middle">${impresionTO.fechaPlazoSeg}</td>
								</tr>							
								<tr>
									<td class="healineblue1" valign="middle" bgcolor="#eff0f1">Centro de Atención:</td>
									<td class="textonegroBlod" valign="middle">${impresionTO.idPuntoVenta}</td>
								</tr>
								<tr>
									<td class="healineblue1" valign="middle" bgcolor="#eff0f1" colspan="1">Folio Redencion:</td>
									<td class="textonegroBlod" valign="middle">${impresionTO.folioRedencion}</td>
								</tr>								
							</table>

							<table width="98%" border="0" cellspacing="0" cellpadding="0">
								<tr><td>&nbsp;</td></tr>
								<tr>
									<td class="textoazulBlodTrs">
									El importe que se indica en este certificado aplica para la compra de Amigo Kit o 
									Accesorios en Centros de Atención a Clientes Telcel.<br><br> 
									Para hacer válido este certificado es indispensable presentar una identificación oficial.<br><br>
									En caso de robo o extravío, Telcel no se hace responsable y no se obliga a la reexpedición del mismo.<br><br> 
									En caso de que el costo del Amigo Kit o Accesorio sea menor al importe del certificado, 
									no se entregará el monto de la diferencia en efectivo ni se expedirá un nuevo documento 
									que ampare la misma.<br><br>
									En caso de que el costo de Amigo Kit o Accesorio sea mayor al importe del certificado, 
									se deberá de pagar el monto excedente.<br><br>
									El certificado es personal e intransferible, no es acumulable ni reembolsable y aplica para la región a la que pertenece la línea.<br><br>
									En caso de devolución del equipo que amparó la expedición de este certificado, Telcel no se obliga a la expedición de uno nuevo por cambio de equipo.<br><br>
									El certificado solo será válido al presentarlo con el holograma.
									</td>
								</tr>
								<tr><td>&nbsp;</td></tr>
							</table>
					
							<table width="98%" border="0" cellspacing="0" cellpadding="0" align="center">
								<tr><td colspan="2">&nbsp;</td></tr>
								<tr><td colspan="2">&nbsp;</td></tr>
								<tr>
									<td width="50%" align="center">
									<!--
										<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="75">
											<input class="LinkOut" onmouseover='this.className="LinkIn";'onmouseout='this.className="LinkOut";'
												type="submit" name="Submit" value="Generar archivo PDF">
										</securityCa:validaPerfil>
									-->
										<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="76">
											<a class="LinkOut" style="width:10%" onmouseover='this.className="LinkIn";'
												onmouseout='this.className="LinkOut";' id="Link1"
												onClick="window.print();">&nbsp;&nbsp;Imprimir&nbsp;&nbsp;
											</a>
										</securityCa:validaPerfil>									
									</td>					
									<td width="40%">
										<img src="../barcode4j/genbc?type=code128&msg=<fmt:formatNumber groupingUsed="false" minIntegerDigits="8" value="${impresionTO.folio}"/>&hrp=bottom&fmt=png" width="156" height="58">
									</td>
								</tr>							
							</table>
							<br><br>										
				</table>
			</td>
		</tr>		
	</table>
</DIV>
</form>
</body>
</html>
