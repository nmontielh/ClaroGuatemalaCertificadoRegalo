<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href='<c:url value="/commons/js/sc_textsheet.css"/>' type="text/css">
<title>Claro Club: Sistema de Recompensas</title>
<script type="text/javascript">
 
function sendRequest(){
	if(document.getElementById('numEmpleado').value == ""){
 		alert('Debe capturar su número de empleado.');
 		return false;
 	}

 	if(document.getElementById('password').value == ""){
 		alert('Debe capturar el password.');
 		return false;
 	}

 	frmLogin.method="post";
 	frmLogin.action="./menu.do";
 	frmLogin.submit();
}

</script>
</head>
<body class="menu">
	<table align="center" border="0" width="100%">
			<tr>
				<td width="7%"></td>
				<td width="1%" align="right">
					<img src='<c:url value="/commons/images/claro/logo_claro.png"/>'>
				</td>
				<td width="93%" align="right">
					<font face="Arial" size="6" color="red"><b>Claro</b></font>	
					<font face="Arial" size="6" color="black"><b>Club
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b></font>
					
				</td>		
			</tr>
			
		</table>
	<br>
	<div style="height:350px; top:90px; position:absolute; padding-left:50px;" >
		<table width="100%" border="0" cellpadding="0" cellspacing="0" align="center">
			<tr>
				<td>
					<table width="100%" cellspacing="0" cellpadding="0" align="center">
						<tr>
							<td colspan="3" class="titulo" height="42" width="100%" >
								&nbsp;&nbsp;Login
							</td>
						</tr>
						<tr align="center">
							<td valign="top" width="98%" align="center">
								<form name="frmLogin">
									<table width="98%" border="0" cellpadding="0" align="center">
										<tr>
											<td width="25%" class="healineblue1" align="right" height="20">N&uacute;mero de Empleado:</td>
											<td class="textonegroBlodTrs" width="23%" height="20" align="left">
												<input name="numEmpleado" maxlength="10"
													onKeyDown="if(event.keyCode==13) document.getElementById('Link1').click()">
											</td>
											<td class="textonegroBlodTrs" width="52%" height="20" align="center">
												&nbsp;
											</td>
										</tr>
										<tr>
											<td width="25%">&nbsp;</td>
										</tr>
										<tr>
											<td width="25%" class="healineblue1" align="right" height="20">Password:</td>
											<td class="textonegroBlodTrs" width="23%" height="20" align="left">
												<input type="password" name="password" maxlength="8"
												onKeyDown="if(event.keyCode==13) document.getElementById('Link1').click()">
												<!-- <input type="hidden" name="ip" value="<%=request.getHeader("CLIENT_IP_ADDRESS")%>"> --> 
												<input type="hidden" name="ip" value="<%=request.getRemoteAddr()%>">
											</td>
											<td align="left" width="52%">
												<a class="LinkOut" id="Link1" style="width:100px" 
													onmouseover='this.className="LinkIn";' 
													onmouseout='this.className="LinkOut";'
													onClick="sendRequest();">
														&nbsp;&nbsp;&nbsp;Continuar&nbsp;&nbsp;
												</a>
											</td>
										</tr>
									</table>
								</form>
								<p>&nbsp;</p>								
								
								<!-- Tabla de mensaje -->
								<table width="75%" border="0" align ="center">
									<tr>
										<td bgcolor="black" align="center" height="16">
											<FONT face="Arial" SIZE ="-1" COLOR="#FFFFFF">
												<b>ATENCIÓN:</b>
											</FONT>
										</td>
									</tr>
									<tr>
										<td align="left"> 
											<FONT SIZE ="-1" COLOR="#666666">
												Se les informa que a partir del del <B>13 de Marzo del 2007</B>,
												el soporte al Sistema de Puntos se realizara como sigue:<br><br>
												1. El asesor, supervisor o jefe de CAC o Telefónico, podrá levantar
												el folio SISAP desde <B>Sisap.Soporte</B>, colocando en tipo
												APLICACIONES MOBILE 2000, seleccionando ASA -ACTIVACIONES Y SWITCH MANAGER.
												<br><br>
												2. Solo después de haber levantado un folio en sisap.soporte,
												podrá comunicarse a la ext. <B>6318, opción 5, opción 7 Servicios de Soporte</B><B></B> para que
												le den seguimiento, <B><U>si no han levantado el folio sisap
												el Grupo de Soporte no atenderá su llamada.</U></B>
												<br><br>
												3. El horario de soporte será de <b>lunes a viernes</b> de 7 a 10 p.m. 
												y fuera de este horario incluyendo <b>sábados y domingos</b> 
												se pueden comunicar al celular de guardia 5554001729.
												A excepción de los casos de puntos reservados los cuáles se atenderán
												en la ext. 6318.												
												<br><br>
												4. A partir del <B>13 de marzo</B>, help desk solo dará seguimiento a las 
												solicitudes de alta/baja de usuarios en el Sistema de Puntos a través de SUA.
												<br><br>
											</FONT>
											<FONT SIZE ="-2" COLOR ="black" FACE="Helvetica, Geneva">
												<u>
													<B>NOTA:</B> 
	                  								SOLO EN CASO DE CAIDA DEL SISTEMA NO SE LEVANTARA FOLIO Y PODRAN 
	                  								COMUNICARSE DIRECTAMENTE A LA EXT. 6318
	                  							</u>
	                  						</FONT>
	                  					</td>
	                  				</tr>
	                  				<tr>
	                  					<td bgcolor="black" align ="center" height="16"></td>
	                  				</tr>
	                  			</table>	                  			
		                 		<table>
									<tr>					
										<td class="textonegroBlodTrs">Acceso desde la dirección IP:</td>
										<!-- <td class="healineblue1"><%=request.getHeader("CLIENT_IP_ADDRESS")%></td> -->
										<td class="healineblue1"><%=request.getRemoteAddr()%></td>
									</tr>					
								</table>	                  			
	                  			
	                  		</td>
	                  	</tr>
					</table>
				</td>
			</tr>
		</table>
	</div>
	<p style="top: 3px;position: absolute;left: 770px;"></p>
	
	<!--Seccion de codigo para el anuncio de versiones-->
	<div id="w_n_update" 
		style="position:absolute;margin-left:100px;margin-top:-22px;top:0;width:500px;height:400px;border:1px solid #000000;background:#FFFFFF;font-size:12px;visibility:hidden"
		onclick="hide_whatsnew()">
		
		<div style="border:1px;font-size:13px;background-color:#FFFFFF;text-align:center">
			<table style="color:#0033CC; border-bottom-color:#ffff00;font-weight:normal">
				<tr>
					<td height="10"></td>
				</tr>
				<tr>
					<td class="titulo" height="40">
						<font size="+1">
							&nbsp;¿Que hay de nuevo en Circulo Azul?
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</font>
						<font size ="-2" align ="right" color="blue">
							Clic para cerrar
						</font>
					</td>
				</tr>
			</table>
		</div>
		<HR COLOR ="RED">
		<div style="border:1px;text-align:left;height:432px;overflow:auto;padding-right:10px">
			<br>
			<ul>
				<div id="update_list"></div>
			</ul>					
		</div>
	</div>
	<!--Fin de Seccion de codigo para el anuncio de versiones-->
</body>
</html>