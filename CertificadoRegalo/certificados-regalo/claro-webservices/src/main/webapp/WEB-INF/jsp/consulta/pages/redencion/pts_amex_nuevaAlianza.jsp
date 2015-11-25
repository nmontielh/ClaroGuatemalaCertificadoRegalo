
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<html>
<head>
<title>Circulo Azul</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href='<c:url value="/commons/js/sc_textsheet.css"/>' type="text/css">
<script type="text/javascript">
        	function form1_onsubmit() {
        		if(form1.nombreAlianza.value == ""){
    				window.alert("Debe capturar información en el campo Nombre.")
    				return false;
 				}
 				if(form1.apellidoPat.value == ""){
 					window.alert("Debe capturar información en el campo Apellido Paterno.")
 					return false;
 				}
 				if(form1.apellidoMat.value == ""){
	    			window.alert("Debe capturar información en el campo Apellido Materno.")
    				return false;
 				}
 				if(isPositiveInteger(trim(removeZeros(form1.ctaAlianza.value))) == false){
	    			window.alert("Debe capturar un número positivo en el campo Cuenta Alianza.")
    				return false;
 				}
 
 			form1.submit();
			}
			function trim(str) {
				while(str.charAt(str.length - 1) == " ")
				str = str.substring(0,str.length - 1);
				while(str.charAt(0) == " ")
					str = str.substring(1,str.length);
				return str;
			}
			function removeZeros(str) {
				var cantidad = "";
				if(str.length>0) {
					for(var n=0; n<str.length; n++) {
						if(str.charAt(n)!="0") {
							var p=n;
							do {
								cantidad = cantidad + str.charAt(p);
								p++;
							} while(p<str.length)
							break;
						}
					}		
				}
				return cantidad;
			}
			function isPositiveInteger(str) { 
				var pattern = "0123456789";
				var i = 0;
				do { 
					var pos = 0;
					for(var j=0; j<pattern.length; j++)
						if(str.charAt(i)==pattern.charAt(j)) { 
							pos = 1;
							break;
						}
						i++;
				} while(pos==1 &&  i<str.length)
				if(pos == 0 )
					return false;
				return true;
			}
        </script>
</head>

<body marginwidth="0" marginheight="0"
	style="MARGIN: 0px; background-color: transparent;">
<script>
    		document.parentWindow.parent.activaCargando('hidden','none'); 
    	</script>
<body bgcolor="#ffffff" marginwidth="0" marginheight="0" style="MARGIN: 0px">
	<table width="98%" border="0" cellpadding="1" cellspacing="0" class='main'>
		<tr>
			<td height="20" width="10"></td>
			<td height="20"></td>
		</tr>
		<tr>
			<td width="10">&nbsp;</td>
			<td valign="top">
			<form name="form1" method="post" action="pts_controller.jsp">
				<input type=hidden name=form_name>
				<input type="hidden" name="operacion">
				<table width="100%" border="1" cellspacing="0" cellpadding="1" align="center">
					<tr valign="top">
						<td colspan="3" height="150"><BR>
							<table width="98%" border="0" cellspacing="0" cellpadding="0" align="center">
								<tr>
									<td colspan="4" class="titulo" height="42">&nbsp;&nbsp;Totales</td>
								</tr>
							</table><BR>
							
							<table width="98%" border="1" cellspacing="0" cellpadding="0" align="center">
								<tr>
									<td width="35%" class="healineblue1" valign="middle" height="20" bgcolor="#eff0f1">
										<div align="center">Puntos Disponibles</div>
									</td>
									<td colspan=3 class="textonegroBlod" width="65%" height="20" valign="middle">
										<div align="left"></div>${telefonoTO.puntosTO.ptosDisponibles}
									</td>
								</tr>
								<tr>
									<td width="35%" class="healineblue1" valign="middle" height="20" bgcolor="#eff0f1">
										<div align="center">Estatus Puntos</div>
									</td>
									<td colspan=3 class="textonegroBlod" width="65%" height="20" valign="middle">
										<font color="red">${telefonoTO.puntosTO.ptosStatus}</font>
									</td>
								</tr>
							</table>
							<table width="98%" border="0" cellspacing="0" cellpadding="0" align="center">
								<tr>
									<td height="18">&nbsp;</td>
								</tr>
								<tr>
									<td colspan="4" class="titulo" height="42">
										&nbsp;&nbsp;Captura Datos de Alianza
									</td>
								</tr>
								<tr>
									<td colspan="4" class="healineblue1" valign="middle" height="30">
										<div align="left"></div>
										<div align="left"></div>
										<div align="center">La cuenta de la Alianza no se encuentra
											dada de alta en el sistema. Capture los datos tal como aparecen en
											la Credencial de Elector u otra identificación oficial.
										</div>
									</td>
								</tr>
							</table>
							<table width="98%" border="0" cellspacing="0" cellpadding="0" align="center" bordercolor="#ffffff">
								<tr>
									<td class="textonegroBlodTrs" width="18%">&nbsp;</td>
								</tr>
								<tr>
									<td class="healineblue1" width="18%">Nombre(s):</td>
									<td width="23%"><input id="nombreAlianza" name="nombreAlianza" maxlength="30" size=30 style="TEXT-TRANSFORM: UPPERCASE"></td>
									<td width="18%" class="healineblue1" align="center" height="20">&nbsp;</td>
									<td width="25%">&nbsp; 
										<input type="hidden" id="ctaAlianza" name="ctaAlianza" value="1">
									</td>
								</tr>
								<tr>
									<td class="healineblue1" width="18%">Apellido Paterno:</td>
									<td width="23%">
										<input id="apellidoPat" name="apellidoPat" maxlength="30" size=30 style="TEXT-TRANSFORM: UPPERCASE">
									</td>
									<td class="healineblue1" width="26%" align="center">Apellido Materno:</td>
									<td width="33%">
										<input id="apellidoMat" name="apellidoMat" maxlength="30" size=30 align=left style="TEXT-TRANSFORM: UPPERCASE">
										</td>
									</tr>
									<tr>
										<td class="healineblue1" width="18%">Alianza:</td>
										<td width="23%">
											<input name="descAl" value="${alianza}" readonly>
										</td>
									</tr>
									<tr>
										<td class="healineblue1" width="18%">Teléfono</td>
										<td width="23%">
											<input name="telefono" maxlength="10" value="${telefonoTO.mobileTO.telefono}" readonly>
										</td>
										<td class="healineblue1" width="26%" align="right">Cuenta:&nbsp;&nbsp;&nbsp;&nbsp;</td>
										<td width="33%">
											<input name="cuenta" maxlength="10" value="${telefonoTO.mobileTO.cuenta}" readonly>
										</td>
									</tr>
									<tr>
										<td class="textonegroBlodTrs" width="18%">&nbsp;</td>
									</tr>
								</table>
								<table width="98%" border="0" cellspacing="0" cellpadding="0" align="center">
									<tr>
										<td class="textonegroBlodTrs">
											<p>&nbsp;</p>
										</td>
									</tr>
									<tr>
										<td class="textonegroBlodTrs">&nbsp;</td>
									</tr>
									<tr>
										<td align="center" width="20%">
											<a class="LinkOut" style="width: 20%" onmouseover='this.className="LinkIn";' 
												onmouseout='this.className="LinkOut";' id="Link1"
												onClick="form1.form_name.value='pts_alianzaprocesa.jsp'; form1.operacion.value='ALTA'; form1_onsubmit();">&nbsp;Agregar&nbsp;&nbsp;</a>
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
