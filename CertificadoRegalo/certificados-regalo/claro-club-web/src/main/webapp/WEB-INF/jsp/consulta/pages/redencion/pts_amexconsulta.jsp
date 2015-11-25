<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<html>
	<head>
		<title>Circulo Azul</title>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
		<link rel="stylesheet" href='<c:url value="/commons/js/sc_textsheet.css"/>' type="text/css">
		<script LANGUAGE=javascript>
		<!--
		function trim(str) {
			while(str.charAt(str.length - 1) == " ")
			str = str.substring(0,str.length - 1);
			while(str.charAt(0) == " ")
			str = str.substring(1,str.length);
			return str;
		}
		
		function canjear_onClick() {
			if(trim(form1.comentario.value) == ""){
				window.alert("Debe ingresar un comentario para poder hacer el canje de puntos")
				return false;
	 		}
	 		form1.form_name.value='pts_alianzaprocesa.jsp';
	 		form1.operacion.value='CANJE';
	 		form1.submit();
		}
		// -->
		</script>
	</head>
	<body marginwidth="0" marginheight="0" style="MARGIN-TOP: 0px; OVERFLOW-Y: scroll; OVERFLOW-X: hidden; 
			BORDER:solid 1px silver; HEIGHT: 280px;position: absolute;top: 55px;visibility: visible;display: block;">
	
		<table align="center" width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td height="10" width="10"></td>
				<td height="10"></td>
			</tr>
			<tr>
				<td width="10">&nbsp;</td>
				<td valign="top">
					<form name="form1" method="post" action="pts_controller.jsp">
						<input type=hidden name=form_name>
						<input type="hidden" name="operacion">
						<table width="100%" border="0" cellspacing="0" cellpadding="0" align="center" height="296" class='main'>
							<tr valign="top">
								<td colspan="3"><BR>
									<table width="98%" border="0" cellspacing="0" cellpadding="0" align="center" >
										<tr>
											<td colspan="4" class="titulo" height="42">&nbsp;&nbsp;Alianzas</td>
										</tr>
									</table>
									<BR>
									<table width="98%" border="1" cellspacing="0" cellpadding="0" align="center" height="115">
										<tr>
											<td width="15%" class="healineblue1" valign="middle" height="20" bgcolor="#eff0f1">
												<div align="center">Nombre</div>
											</td>
											<td colspan=3 class="textonegroBlod" width="39%" height="20" valign="middle">
												<div align="left"></div>ANGELINA CHAVEZ HERNANDEZ
											</td>
										</tr>
										<tr>
											<td width="15%" class="healineblue1" valign="middle" height="19" bgcolor="#eff0f1">
												<div align="center">Teléfono</div>
											</td>
											<td class="textonegroBlod" width="39%" height="19" valign="middle">5510101728</td>
											<td width="21%" class="healineblue1" valign="middle" height="20" bgcolor="#eff0f1">
												<div align="center">Alianza</div>
											</td>
											<td class="textonegroBlod" width="25%" height="20" valign="middle">AMERICAN EXPRESS</td>
										</tr>
										<tr>
											<td width="15%" class="healineblue1" valign="middle" height="19" bgcolor="#eff0f1">
												<div align="center">Cuenta</div></td>
											<td class="textonegroBlod" width="39%" height="19" valign="middle">1517929</td>
											<td width="21%" class="healineblue1" valign="middle" height="20" bgcolor="#eff0f1">
												<div align="center">Puntos Disponibles</div></td>
											<td class="textonegroBlod" width="25%" height="20" valign="middle">91,385</td>
										</tr>
										<tr>
											<td width="15%" class="healineblue1" valign="middle" height="20" bgcolor="#eff0f1">
												<div align="center">Estatus Puntos</div></td>
											<td class="textonegroBlod" width="39%" height="20" valign="middle"><font color=red>0</font></td>
											<td colspan=2 class="textonegroBlod">&nbsp;</td>
										</tr>
									</table>
									<table width="98%" border="0" cellspacing="0" cellpadding="0" align="center">
										<tr>
											<td>&nbsp;</td>
										</tr>
										<tr>
											<td class="healineblue1"><font color='red'>Totales</font></td>
										</tr>
										<tr>
											<td>&nbsp;</td>
										</tr>
									</table>
									<table width="98%" border="1" cellspacing="0" cellpadding="0" align="center" bordercolor="#cccccc">
										<tr>
											<td class="healineblue1" bgcolor="#eff0f1" width="19%">Por vencer 2 años</td>
											<td class="textonegroBlod" width="15%" align="right">0</td>
											<td class="healineblue1" bgcolor="#eff0f1" width="19%" >Fecha de Vencimiento</td>
											<td class="textonegroBlod" width="14%"></td>
											<td class="healineblue1" bgcolor="#eff0f1" width="19%">Fecha facturación</td>
											<td class="textonegroBlod" width="15%" align="right">2009-01-18</td>
										</tr>
										<tr>
											<td class="healineblue1" bgcolor="#eff0f1" width="19%" >Por vencer 1 año</td>
											<td class="textonegroBlod" width="14%" align="right">0</td>
											<td class="healineblue1" bgcolor="#eff0f1" width="19%" >Fecha de Vencimiento</td>
											<td class="textonegroBlod" width="14%"></td>
											<td class="healineblue1" bgcolor="#eff0f1" width="19%" >Alianzas</td>
											<td class="textonegroBlod" width="14%" align="right">0</td>
										</tr>
										<tr>
											<td class="healineblue1" bgcolor="#eff0f1" width="19%" >Por vencer</td>
											<td class="textonegroBlod" width="14%" align="right">0</td>
											<td class="healineblue1" bgcolor="#eff0f1" width="19%" >Fecha de Vencimiento</td>
											<td class="textonegroBlod" width="14%"></td>
											<td class="healineblue1" bgcolor="#eff0f1" width="19%" >Redimidos</td>
											<td class="textonegroBlod" width="14%" align="right">0</td>
										</tr>
										<tr>
											<td class="healineblue1" bgcolor="#eff0f1" width="19%">Renta</td>
											<td class="textonegroBlod" width="15%" align="right">8,372</td>
											<td class="healineblue1" bgcolor="#eff0f1" width="19%" >Antigüedad</td>
											<td class="textonegroBlod" width="14%" align="right">12,000</td>
											<td class="healineblue1" bgcolor="#eff0f1" width="19%" >Puntos Vencidos</td>
											<td class="textonegroBlod" width="14%" align="right">3,737</td>
										</tr>
										<tr>
											<td class="healineblue1" bgcolor="#eff0f1" width="19%" >Excedentes</td>
											<td class="textonegroBlod" width="14%" align="right">8,372</td>
								<td class="healineblue1" bgcolor="#eff0f1" width="19%" >Promoción</td>
								<td class="textonegroBlod" width="14%" align="right">62,641</td>
								<td class="healineblue1" bgcolor="#eff0f1" width="19%" >Fecha de Expiración</td>
								<td class="textonegroBlod" width="14%">2007-10-12</td>
							</tr>
						</table>
						<table width="98%" border="0" cellspacing="0" cellpadding="0" align="center">
							<tr>
								<td colspan="2" class="textonegroBlodTrs">&nbsp;</td>
							</tr>
							<tr>
								<td colspan="2" class="textonegroBlodTrs">&nbsp;</td>
							</tr>
							<tr>
								<td class="textonegroBlodTrs" width="28%"><font color='red'>Paquetes de Viaje</font></td>
								<td class="textonegroBlodTrs" width="72%">&nbsp;<font color=red>Se completará valor del certificado en PUNTOS exclusivamente</font></td>
							</tr>
							<tr>
								<td class="textonegroBlodTrs" width="28%">&nbsp;</td>
								<td class="textonegroBlodTrs" width="72%">&nbsp;</td>
							</tr>
						</table>
						<table width="98%" border="1" cellspacing="0" cellpadding="0" align="center" bordercolor="#cccccc">
							<tr bgcolor="#eff0f1">
								<td class="healineblue1" width="37%" height="13">
									<div align="center">Descripción</div>
								</td>
								<td class="healineblue1" width="13%" height="13">
									<div align="center">Valor que ampara el certificado</div>
								</td>
								<td class="healineblue1" width="14%" height="13">
									<div align="center">Puntos a canjear</div>
								</td>
							</tr>
							<tr>
								<td width="19%" class="textonegroBlod">
									<input type="radio" name="idViaje" value="AMEX1">Viaje Amex</td>
								<td width="13%" class="textonegroBlod"><div align="right">$ 1000.00</div></td>
								<td width="14%" class="textonegroBlod">
									<div align="right">50,000</div>
								</td>
							</tr>
							<tr>
								<td width="19%" class="textonegroBlod">
									<input type="radio" name="idViaje" value="AMEX2">Viaje Amex</td>
								<td width="13%" class="textonegroBlod"><div align="right">$ 1500.00</div></td>
								<td width="14%" class="textonegroBlod">
									<div align="right">75,000</div></td>
							</tr>
						</table>
						<table width="98%" border="0" cellspacing="0" cellpadding="0" align="center">
							<tr>
								<td colspan="2" class="textonegroBlodTrs">&nbsp;</td>
							</tr>
							<tr>
								<td colspan="2" class="textonegroBlodTrs">&nbsp;</td>
							</tr>
							<tr>
								<td width="20%" class="textonegroBlodTrs">Comentario:</td>
								<td><input id="comentario" name="comentario" maxlength="100" size=80 style="TEXT-TRANSFORM:UPPERCASE"></td>
							</tr>
							<tr>
								<td class="textonegroBlodTrs">&nbsp;</td>
								<td class="textonegroBlodTrs"><div align="left"><font color="red">&nbsp;* No capturar más de 70 caracteres</font></div></td>
							</tr>
							<tr>
								<td class="textonegroBlodTrs">&nbsp;</td>
								<td class="textonegroBlodTrs">&nbsp;</td>
							</tr>
						</table>
						<table width="100%">
							<tr>
								<td align="center" width="33%" height="24"> <a class="LinkOut" style="width:90%" onmouseover='this.className="LinkIn";' onmouseout='this.className="LinkOut";'
									id="Link1" onClick="form1.form_name.value='pts_alianzacancelaconsulta.jsp'; form1.submit();">&nbsp;Cancelar
									Canje&nbsp;&nbsp;</a>
								</td>
								<td align="center" width="33%" height="24"> <a class="LinkOut" style="width:90%" onmouseover='this.className="LinkIn";' onmouseout='this.className="LinkOut";'
									id="Link2" onClick="form1.form_name.value='pts_alianzaactualiza.jsp'; form1.submit();">&nbsp;Modificar&nbsp;&nbsp;</a>
								</td>
								<td align="center" width="33%" height="24"> <a class="LinkOut" style="width:90%" onmouseover='this.className="LinkIn";' onmouseout='this.className="LinkOut";'
									id="Link3" onClick="canjear_onClick();">&nbsp;Canjear&nbsp;&nbsp;</a>
								</td>
							</tr>
						</table>
						<table width="70%" align="center">
							<tr>
								<td align="center" width="40%">
									<a class="LinkOut" style="width:60%" onmouseover='this.className="LinkIn";' onmouseout='this.className="LinkOut";'
										id="Link2" onClick="form1.form_name.value='pts_amexverifica.jsp'; form1.submit();">&nbsp;Liberar&nbsp;&nbsp;</a>
								</td>
								<td align="center" width="40%">
									<a class="LinkOut" style="width:60%" onmouseover='this.className="LinkIn";' onmouseout='this.className="LinkOut";'
										id="Link1" onClick="form1.form_name.value='pts_amexcertificados.jsp'; form1.submit();">&nbsp;Imprimir&nbsp;&nbsp;</a>
								</td>
							</tr>
						</table>
						<p align="right">
							<img height=63 src='<c:url value="/commons/images/logo_circulo_azul.gif"/>' width=197>
						</p>
					</td>
				</tr>
			</table>
	  </form>
	</td>
  </tr>
</table>
</body>
</html>