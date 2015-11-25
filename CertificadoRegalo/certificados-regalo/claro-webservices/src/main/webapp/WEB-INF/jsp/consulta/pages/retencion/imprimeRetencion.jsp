<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<html>
	<head>
		<title>Puntos Telcel</title>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
		
		<link rel="stylesheet" href='<c:url value="/commons/js/sc_textsheet.css"/>' type="text/css">
		<script language="javascript" type="text/javascript">
		</script>
	</head>
	
<body bgcolor="#ffffff" marginwidth="0" marginheight="0" style="MARGIN: 0px">
<form name="form1" method="post" action="">
<input type="hidden" name="form_name">
	<table width="98%" border="0" cellpadding="1" cellspacing="0">
  	<tr><td height="20" width="10"></td><td height="20"></td><td height="20"></td></tr>
  	<tr>
  		<td width="10"></td>
			<td valign="top"> 
    		<table width="98%"  class="main" border="1" cellspacing="0" cellpadding="1" align="center" >
      		<tr valign="top"> 
         		<td colspan="3">
         			<table width="98%" border="0" cellspacing="0" cellpadding="0" align="center" bordercolor="#ffffff">
         				<tr>
         					<td colspan="3" class="healineblue1" align="center" height="67">
         						<p align="center"><IMG height=50 src="<c:url value="/commons/images/logotelcel.gif"/>" width=163></p></td>
         					<td colspan="3" class="healineblue1" align="center" height="67">
         						<p align="center">
         							<img height=50 src='<c:url value="/commons/images/logo_circulo_azul.gif"/>' width=156>
         						</p></td>
         				</tr>
         			</table>
         			<table width="98%" border="0" cellspacing="0" cellpadding="0" align="center">
         				<tr>
         					<td colspan="4" class="titulo" height="42">&nbsp;&nbsp;Certificado de Lealtad
         						<table height="76" border="1" cellspacing="0" cellpadding="0" align="right" width="84">
         							<tr><td width="77" class="textonegroBlod" align=center >Holograma</td></tr>
         						</table>
         					</td>
         				</tr>
         			</table>
         			<br>
         			<table width="98%" border="1" cellspacing="0" cellpadding="0" align="center">
         				<tr>
         					<td class="healineblue1" bgcolor="#eff0f1"><div align="left">Nombre del Titular:</div></td>
         					<td colspan=3 class="textonegroBlod"  valign="middle">
         						<div align="left">
         							<c:out value="${mobileTONombre}"></c:out>
         						</div>
         					</td>
         				</tr>
         				<tr>
         					<td class="healineblue1" bgcolor="#eff0f1"><div align="left">Numero de Teléfono:</div></td>
         					<td class="textonegroBlod"><div align="right"><c:out value="${mobileTOTelefono}"></c:out></div></td>
         					<td class="healineblue1" bgcolor="#eff0f1"><div align="left">Fecha de Expedición:</div></td>
         					<td class="textonegroBlod"><div align="right">
         						<c:out value="${retencionTO.fechaOperacion}"></c:out></div>
         					</td>
         				</tr>
         				<tr>
         					<td class="healineblue1" bgcolor="#eff0f1"><div align="left">Cuenta:</div></td>
         					<td class="textonegroBlod"><div align="right"><c:out value="${mobileTOCuenta}"></c:out></div></td>
         					<td class="healineblue1" bgcolor="#eff0f1"><div align="left">Fecha de Caducidad:</div></td>
         					<td class="textonegroBlod"><div align="center"><c:out value="${retencionTO.fechaCaduca}"></c:out></div></td></tr>
         				<tr>
         					<td class="healineblue1" bgcolor="#eff0f1"><div align="left">Valor del Certificado:</div></td>
         					<td class="textonegroBlod"><div align="right"><font color=red>$<c:out value="${nValorTotal}"></c:out>.00</font></div></td>
							<td class="healineblue1" bgcolor="#eff0f1"><div align="left">Estatus del Folio:</div></td>
			                <td class="textonegroBlod"><div align="center"><c:out value="${retencionTO.estatus}"></c:out></div></td>
            		    </tr>
              		</table>
              		<BR>	
              		<BR>
              		<BR>
					<UL>
						<LI class="textonegroBlod">Este Certificado de Lealtad es personal e intransferible y no tiene valor monetario. Se requiere identificación oficial para su uso.</LI>
						<LI class="textonegroBlod">No reembolsable.</LI>
						<LI class="textonegroBlod">No es acumulable con otros Certificados.</LI>
						<LI class="textonegroBlod">En caso de que el monto del Certificado sea mayor al pago del cliente, no se  entregará el monto de la diferencia al cliente, ni se expedirá un documento que ampare la misma.</LI>
						<LI class="textonegroBlod">En caso de que el monto del Certificado sea menor al pago del cliente, el cliente podrá pagar el monto excedente.</LI>
						<LI class="textonegroBlod">El  Certificado podrá ser canjeado en los Centros de Atención a Clientes de Telcel en el DF, Estado de México, Hidalgo y Morelos.</LI>
						<LI class="textonegroBlod">En caso de que el uso del Certificado de Lealtad genere una factura, deberá ser emitida al mismo nombre contenido en este Certificado.</LI>
						<LI class="textonegroBlod">El Certificado de Lealtad tiene 30 días de vigencia, pasado este tiempo, no se podrá aceptar ni se podrá ampliar el plazo.</LI>
						<LI class="textonegroBlod">En caso de pérdida, Telcel no expedrá una reimpresión del Certificado de Lealtad.</LI>
						<LI class="textonegroBlod">En caso de que el Certificado de Lealtad presente alteraciones en el holograma o pérdida total de éste, no será aceptado en Telcel.</LI>
					</UL>
					<BR>
					<BR>
					<BR>
					<table width="98%" border="0" cellspacing="0" cellpadding="0" align="center">
						<tr> 
              				<td colspan=2 class="textonegroBlodTrs">
                				<table cellSpacing=0 cellPadding=0 width="98%" align=center border=0>
                		<tr>
							<td class=healineblue1  width="40%" height="15">
                    			<center><font style="font-size:6pt">
                    				<c:out value="${usuarioTO.nombre}"></c:out> (<c:out value="${usuarioTO.idUsuario}"></c:out>)</font>
                      				<HR id=HR1 size=1 align=left width=280><font style="font-size:6pt">Nombre y Firma del Asesor</font></Center></td>
                    		<td width="10%"><div align=center></div></td>
                    		<td class=healineblue1  width="40%" height="15">                        
                       			<center>
                       				<HR id=HR1 size=1 align=left width=280><font style="font-size:6pt">Nombre y Firma del Cliente</font></center></td>
						</tr>
              		</table>
				</td>
			</tr>             
			<tr> 
      			<td colspan=2 class="textonegroBlodTrs">
               		<table cellSpacing=0 cellPadding=0 width="251" align=center border=0 >
              			<tr><td colspan=2>&nbsp;</td></tr>
						<tr>
							<td class=healineblue1 width="40%">
								<HR id=HR1 size=1 align=left width=300>
									<div align=center><font style="font-size:6pt">Vo Bo Nombre y Firma Radiomovil DIPSA SA de CV</font></div>
							</td>
						</tr> 
              		</table>
				</td> 
			</tr>
		</table>
		<BR>
		<BR>
		<table width="98%" border="0" cellspacing="0" cellpadding="0" align="center">
			<tr>
				<td align="center" width="10%"> <a class="LinkOut" style="width:10%" onmouseover='this.className="LinkIn";' 
					onmouseout='this.className="LinkOut";' id="Link1" onClick="window.print();">&nbsp;&nbsp;Imprimir&nbsp;&nbsp;</a>
				</td>
				<td align="center" width="10%">
					<img src="../barcode4j/genbc?type=ean-13&msg=<c:out value="${folioRetencion}"></c:out>&fmt=png" width="156" height="58"></td>
			</tr>
		</table>
		<BR>
		<BR>
</table>
</td>
</tr>
</table>
</form>
</body>
</html>