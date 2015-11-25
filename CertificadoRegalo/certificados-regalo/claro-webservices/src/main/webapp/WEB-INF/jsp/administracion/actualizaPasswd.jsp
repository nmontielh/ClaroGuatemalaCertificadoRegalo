<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href='<c:url value="/commons/js/sc_textsheet.css"/>' type="text/css">
<title>Cambiar password</title>
</head>
<body marginwidth="0" marginheight="0" style="margin: 0px" background="<c:url value="/commons/images/backgroundlight.gif"/>">

	<script>
		function validaPasswd() {
		
		if(document.getElementById('password').value == ""){
			alert("Debe ingresar su password ACTUAL.")
			return false;
		}
		
		if(document.getElementById('passwordNuevo').value == ""){
			alert("Debe ingresar su NUEVO password.")
		    return false;
		}
		 
		if(document.getElementById('passwordNuevo').value.length != 8){
			alert("El NUEVO password debe ser de 8 caracteres.")
		    return false;
		}
		 
		if(document.getElementById('confirmacionPassword').value == ""){
			alert("Debe ingresar la CONFIRMACIÓN de su password.")
		    return false;
		}
		 
		if(document.getElementById('confirmacionPassword').value.length != 8){
			alert("La CONFIRMACIÓN de su password debe ser de 8 caracteres.")
		    return false;
		}
		 
		if(document.getElementById('passwordNuevo').value != document.getElementById('confirmacionPassword').value){
			alert("La CONFIRMACIÓN de su password debe ser igual al NUEVO password.")
		    return false;
		}
		 
		if(document.getElementById('password').value == document.getElementById('passwordNuevo').value ||
			document.getElementById('password').value == document.getElementById('confirmacionPassword').value){
		 	alert("El NUEVO password no debe ser igual al ACTUAL.");
		 	return false;
		}
	
	
    cambiaPass.method = "POST";
    cambiaPass.action = "./actualizaPassword.do";
    cambiaPass.submit();	 
}
	</script>
	
	<table width="98%" border="0" cellpadding="1" cellspacing="0">
		<tr>
			<td height="10" width="11"></td>
			<td height="10" width="862"></td>
		</tr>
		<tr>
			<td width="10">&nbsp;</td>
			<td valign="top">
				<form name="cambiaPass">
					<table width="100%" class="main" border="0" cellspacing="0" cellpadding="1" align="left">
						<tr valign="top">
							<td><BR>
								<table width="98%" border="0" cellspacing="0" cellpadding="0" align="center">
									<tr>
										<td class="titulo" height="42">&nbsp;&nbsp;Es necesario cambiar tu password de usuario</td>
									</tr>
								</table>
								<!-- Texto de reglas -->
								<table width="98%" border="0" cellspacing="0" cellpadding="0" align="center">
									<tr>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td class="healineblue1">Reglas para asignación de PASSWORD:</td>
									</tr>
									<tr>
										<td class="textonegroBlodTrs">&nbsp;&nbsp;&nbsp;</td>
									</tr>
									<tr>
										<td class="textonegroBlodTrs">&nbsp;&nbsp;&nbsp;&nbsp;1. Debe ser alfanum&eacute;rico</td>
									</tr>
									<tr>
										<td class="textonegroBlodTrs">&nbsp;&nbsp;&nbsp;</td>
									</tr>
									<tr>
										<td class="textonegroBlodTrs">&nbsp;&nbsp;&nbsp;&nbsp;2. Debe ser de 8 caracteres</td>
									</tr>
									<tr>
										<td class="textonegroBlodTrs">&nbsp;&nbsp;&nbsp;</td>
									</tr>
									<tr>
										<td class="textonegroBlodTrs">&nbsp;&nbsp;&nbsp;&nbsp;3. Debe ser sensible a may&uacute;sculas y min&uacute;sculas</td>
									</tr>
									<tr>
										<td class="textonegroBlodTrs">&nbsp;&nbsp;&nbsp;</td>
									</tr>
									<tr>
										
									</tr>
									<tr>
										<td class="textonegroBlodTrs">&nbsp;</td>
									</tr>									
								</table>
								<!-- Fin texto de reglas -->
								<HR>
								<!-- Campos para cambio de passwd -->
								<table width="98%" border="0" cellspacing="0" cellpadding="0" align="center">
									<tr>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td width="15%" class="healineblue1" valign="middle" bgcolor="#eff0f1">
											<div align="right">Usuario: </div>
										</td>
										<td class="textonegroBlodTrs" width="40%" valign="middle" bgcolor="#eff0f1">
											<div align="left"></div>&nbsp;&nbsp;${sessionScope.usuarioTO.nombre}
										</td>
										<td width="15%" class="healineblue1" valign="middle" height="20"></td>
										<td class="textonegroBlodTrs" width="30%" valign="middle"></td>
									</tr>
									<tr>
										<td class="textonegroBlodTrs">&nbsp;</td>
									</tr>
									<tr>
										<td width="20%" class="healineblue1" align="right" height="20">
											<div align="right"> Password Actual: </div>
										</td>
										<td colspan=3 class="healineblue1">
											&nbsp;<input type="password" name="password" maxlength="8" size=10>
										</td>
									</tr>
									<tr>
										<td width="20%" class="healineblue1" align="right" height="20">
											<div align="right">Nuevo Password: </div>
										</td>
										<td colspan=3 class="healineblue1">
											&nbsp;<input type="password" name="passwordNuevo" maxlength="8" size=10>
											<font color=red>*Escribir ocho caracteres alfanum&eacute;ricos</font>
										</td>
									</tr>
									<tr>
										<td width="20%" class="healineblue1" align="center" height="20">
											<div align="right">Confirmar Password: </div>
										</td>
										<td colspan=3>
											&nbsp;<input type="password" name="confirmacionPassword" maxlength="8" size=10>
											<input type="hidden" name="idUsuario" value="${sessionScope.usuarioTO.idUsuario}">											
										</td>
									</tr>
									<tr>
										<td class="textonegroBlodTrs">&nbsp;</td>
									</tr>
									<tr>
										<td colspan=4 align="center" width="100%">
											<a class="LinkOut" name="Link1" id="Link1" style="width:20%" 
												onmouseover='this.className = "LinkIn";' 
												onmouseout='this.className = "LinkOut";'												 
												onClick="validaPasswd();">&nbsp;Continuar&nbsp;&nbsp;</a>
										</td>
									</tr>
									<tr>
										<td class="healineblue1"height="28">&nbsp;</td>
									</tr>
									<tr>
											<td class="textonegroBlodTrs"  colspan=4 align="center" width="100%">&nbsp; Cuando es modificado exitosamente, el sistema te regresa al inico para ingresar.</td>
									</tr>
								</table>
								<p>&nbsp;</p>
								<p>&nbsp;</p>
							</td>
						</tr>
					</table>
				</form>
			</td>
		</tr>
	</table>
</body>
</html>
