<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<html>
	<head>
		<title>Resultado Transferencia</title>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
		<link rel="stylesheet" href='<c:url value="/commons/js/sc_textsheet.css"/>' type="text/css">
		
		<script type="text/javascript">
			var oSubconsulta = null;		
			var oBtnSubconsulta = null;	
		
			function setSubConsulta(opcion){
				if(oBtnSubconsulta != null) oBtnSubconsulta.className = "botonInactivo";
				
				if(oSubconsulta!=null){
					oSubconsulta.style.display = "none";
		  			oSubconsulta.style.visibility ="hidden";
				}
				
				oSubconsulta = document.getElementById("subTarjeta"+opcion);
				oBtnSubconsulta = document.getElementById("BS0" + opcion);
				oBtnSubconsulta.className = "botonActivo";
				oSubconsulta.style.display = "block";
		  		oSubconsulta.style.visibility ="visible";	
			}		
		</script>
	</head>
		
	<body onload="setSubConsulta(1);" marginwidth="0" marginheight="0" style="MARGIN:0px; background-color:transparent;">
		<table>
			<tr>
				<td width="100%" align="right" height="42">
					<table width="100%">
						<tr>
							<td>
	    						<SPAN id="BS01" class="botonInactivo" onclick="setSubConsulta(1)" style="width: 350px;">L&iacute;nea destino</SPAN>		 				
		    					<SPAN id="BS02" class="botonInactivo" onclick="setSubConsulta(2)" style="width: 350px;">L&iacute;nea origen</SPAN>		 						    					
		 					</td>
            			</tr>
            		</table>
            	</td>
            </tr>            	              	 		 												       	
	    </table>
	    
	    <!-- Info linea destino -->
	    <div id="subTarjeta1" style="BORDER:solid 1px silver; height:150px; visibility:visible; display:block; margin-left:5px; margin-right:5px;">             
	        <br>
         	<table width="98%" border="0" bordercolor=red cellspacing="0" cellpadding="0" align="center">
         		<tr>
         			<td class="titulo" height="42">&nbsp;&nbsp;Transferencia de puntos</td>
         		</tr>
         		<tr>
         			<td valign="middle" height="20" class="healineblue1" style="COLOR: maroon">
         				<P>Datos destino</P>
         			</td>
				</tr>
				<tr>
					<td colspan="3" class="healineblue1" valign="middle" height="20"></td>
				</tr>
				<tr>
					<td>
						<table width="98%" border="1" cellspacing="0" cellpadding="0" align="center" bordercolor="#cccccc">						
							<tr>
								<td class="healineblue1" valign="middle" width="15%" bgColor="#eff0f1" height="20">
									<DIV align=center>Nombre</DIV>
								</td>
								<td class="textonegroBlod" valign="middle" height="20" width="37%">
									${transfTO.telefonoTO.mobileTO.nombre}
								</td>
								<td class="healineblue1" valign="middle" bgColor="#eff0f1" height="19" width="18%">
									<div align=center>Puntos Disponibles</div>
								</td>
								<td class="textonegroBlod" valign="middle" width="30%" height="19">
									${transfTO.telefonoTO.puntosTO.ptosDisponibles}
								</td>
							</tr>
							<tr>
								<td class="healineblue1" valign="middle" width="15%" bgColor="#eff0f1" height="20">
									<div align=center>Tel&eacute;fono</div>
								</td>
								<td class="textonegroBlod" valign="middle" height="20" width="37%">
									${transfTO.telefonoTO.mobileTO.telefono}
								</td>
								<td class="healineblue1" valign="middle" bgColor="#eff0f1" height="20" width="18%">
									<DIV align=center>Tecnología</DIV>
								</td>
								<td class="textonegroBlod" valign="middle" width="30%" height="20">
									${transfTO.telefonoTO.tecnologia}
								</td>
							</tr>
							<tr>
								<td class="healineblue1" valign="middle" width="15%" bgColor="#eff0f1" height="20">
									<DIV align=center>Cuenta</DIV>
								</td>
								<td class="textonegroBlod" valign="middle" height="20" width="37%">
									${transfTO.telefonoTO.mobileTO.cuenta}
								</td>
								<td class="healineblue1" valign="middle" bgColor="#eff0f1" height="20" width="18%">
									<DIV align=center>Estatus Puntos</DIV>
								</td>
								<td class="textonegroBlod" valign="middle" width="30%" height="20">
									${transfTO.telefonoTO.puntosTO.ptosStatus}
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td>
						<table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
							<tr>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td class="healineblue1" style="COLOR: maroon">Totales</td>
							</tr>
							<tr>
								<td>&nbsp;</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td>
						<table width="98%" border="1" cellspacing="0" cellpadding="0" align="center" bordercolor="#cccccc">
							<tr>
								<td class="healineblue1" bgcolor="#eff0f1" width="19%">Por vencer 2 años</td>
								<td class="textonegroBlod" width="15%" align="left">&nbsp;${transfTO.telefonoTO.puntosTO.ptsPorVencer2}</td>
								<td class="healineblue1" bgcolor="#eff0f1" width="19%" >Fecha de Vencimiento</td>
								<td class="textonegroBlod" width="14%">&nbsp;${transfTO.telefonoTO.puntosTO.fecVencer2}</td>
								<td class="healineblue1" bgcolor="#eff0f1" width="19%">Fecha facturación</td>
								<td class="textonegroBlod" width="15%">&nbsp;${transfTO.telefonoTO.fecFactura}</td>
							</tr>
							<tr>
								<td class="healineblue1" bgcolor="#eff0f1" width="19%" >Por vencer 1 año</td>
								<td class="textonegroBlod" width="14%" align="left">&nbsp;${transfTO.telefonoTO.puntosTO.ptsPorVencer1}</td>
								<td class="healineblue1" bgcolor="#eff0f1" width="19%" >Fecha de Vencimiento</td>
								<td class="textonegroBlod" width="14%">&nbsp;${transfTO.telefonoTO.puntosTO.fecVencer1}</td>
								<td class="healineblue1" bgcolor="#eff0f1" width="19%" >Alianzas</td>
								<td class="textonegroBlod" width="14%" align="left">&nbsp;${transfTO.telefonoTO.puntosTO.ptsTransferidos}</td>
							</tr>
							<tr>
								<td class="healineblue1" bgcolor="#eff0f1" width="19%" >Por vencer</td>
								<td class="textonegroBlod" width="14%" align="left">&nbsp;${transfTO.telefonoTO.puntosTO.ptsPorVencer}</td>
								<td class="healineblue1" bgcolor="#eff0f1" width="19%" >Fecha de Vencimiento</td>
								<td class="textonegroBlod" width="14%">&nbsp;${transfTO.telefonoTO.puntosTO.fecVencer}</td>
								<td class="healineblue1" bgcolor="#eff0f1" width="19%" >Redimidos</td>
								<td class="textonegroBlod" width="14%" align="left">&nbsp;${transfTO.telefonoTO.puntosTO.ptsRedimidos}</td>
							</tr>
							<tr>
								<td class="healineblue1" bgcolor="#eff0f1" width="19%">Renta</td>
								<td class="textonegroBlod" width="15%" align="left">&nbsp;${transfTO.telefonoTO.puntosTO.ptsRenta}</td>
								<td class="healineblue1" bgcolor="#eff0f1" width="19%" >Antigüedad</td>
								<td class="textonegroBlod" width="14%" align="left">&nbsp;${transfTO.telefonoTO.puntosTO.ptsAntiguedad}</td>
								<td class="healineblue1" bgcolor="#eff0f1" width="19%">Vencidos</td>
								<td class="textonegroBlod" width="14%" align="left">&nbsp;${transfTO.telefonoTO.puntosTO.ptsVencidos}</td>
							</tr>
							<tr>
								<td class="healineblue1" bgcolor="#eff0f1" width="19%" >Excedentes</td>
								<td class="textonegroBlod" width="14%" align="left">&nbsp;${transfTO.telefonoTO.puntosTO.ptsExcedentes}</td>
								<td class="healineblue1" bgcolor="#eff0f1" width="19%">Promoci&oacute;n</td>
								<td class="textonegroBlod" width="14%" align="left">&nbsp;${transfTO.telefonoTO.puntosTO.ptsPromocion}</td>
								<td class="healineblue1" bgcolor="#eff0f1" width="19%">Fecha de Expiración</td>
								<td class="textonegroBlod" width="14%">&nbsp;${transfTO.telefonoTO.puntosTO.fecVencidos}</td>
							</tr>
						</table>					
					</td>
				</tr>
			</table>
			<br>&nbsp;&nbsp;&nbsp;<br>
        </div>

		<!-- Info linea Origen -->
        <div id="subTarjeta2" style="BORDER:solid 1px silver; height:150px; visibility:hidden; display:none; margin-left:5px; margin-right:5px;">
         	<br>
			<table width="98%" border="0" bordercolor=red cellspacing="0" cellpadding="0" align="center">
				<tr>
					<td class="titulo" height="42">&nbsp;&nbsp;Transferencia de puntos</td>
				</tr>
				<tr>
					<td valign="middle" height="20" class="healineblue1" style="COLOR: maroon">
						<P>Datos origen</P>
					</td>
				</tr>
				<tr>
					<td colspan="3" class="healineblue1" valign="middle" height="20"></td>
				</tr>
				<tr>
					<td colspan=4>
						<table width="98%" border="1" cellspacing="0" cellpadding="0" align="center" bordercolor="#cccccc">						
							<tr>
								<td class="healineblue1" valign="middle" width="15%" bgColor="#eff0f1" height="20">
									<DIV align=center>Nombre</DIV>
								</td>
								<td class="textonegroBlod" valign="middle" height="20" width="37%">
									${transfTO.nombreClienteOrigen}
								</td>
								<td class="healineblue1" valign="middle" bgColor="#eff0f1" height="19" width="18%">
									<div align=center>Puntos Disponibles</div>
								</td>
								<td class="textonegroBlod" valign="middle" width="30%" height="19">
									${transfTO.puntosOrigenTO.ptosDisponibles}
								</td>
							</tr>
							<tr>
								<td class="healineblue1" valign="middle" width="15%" bgColor="#eff0f1" height="20">
									<div align=center>Tel&eacute;fono</div>
								</td>
								<td class="textonegroBlod" valign="middle" height="20" width="37%">
									${transfTO.telefonoOrigen}
								</td>
								<td class="healineblue1" valign="middle" bgColor="#eff0f1" height="20" width="18%">
									<DIV align=center>Tecnología</DIV>
								</td>
								<td class="textonegroBlod" valign="middle" width="30%" height="20">
									${transfTO.tecnologiaOrigen}
								</td>
							</tr>
							<tr>
								<td class="healineblue1" valign="middle" width="15%" bgColor="#eff0f1" height="20">
									<DIV align=center>Cuenta</DIV>
								</td>
								<td class="textonegroBlod" valign="middle" height="20" width="37%">
									${transfTO.cuentaOrigen}
								</td>
								<td class="healineblue1" valign="middle" bgColor="#eff0f1" height="20" width="18%">
									<DIV align=center>Estatus Puntos</DIV>
								</td>
								<td class="textonegroBlod" valign="middle" width="30%" height="20">
									0
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td width="100%">
						<table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
							<tr>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td class="healineblue1" style="COLOR: maroon">Totales</td>
							</tr>
							<tr>
								<td>&nbsp;</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>				
					<td>
						<table width="98%" border="1" cellspacing="0" cellpadding="0" align="center" bordercolor="#cccccc">
							<tr>
								<td class="healineblue1" bgcolor="#eff0f1" width="19%">Por vencer 2 años</td>
								<td class="textonegroBlod" width="15%" align="left">&nbsp;${transfTO.puntosOrigenTO.ptsPorVencer2}</td>
								<td class="healineblue1" bgcolor="#eff0f1" width="19%" >Fecha de Vencimiento</td>
								<td class="textonegroBlod" width="14%">&nbsp;${transfTO.puntosOrigenTO.fecVencer2}</td>
								<td class="healineblue1" bgcolor="#eff0f1" width="19%">Fecha facturación</td>
								<td class="textonegroBlod" width="15%">&nbsp;${transfTO.puntosOrigenTO.fecFactura}</td>
							</tr>
							<tr>
								<td class="healineblue1" bgcolor="#eff0f1" width="19%" >Por vencer 1 año</td>
								<td class="textonegroBlod" width="14%" align="left">&nbsp;${transfTO.puntosOrigenTO.ptsPorVencer1}</td>
								<td class="healineblue1" bgcolor="#eff0f1" width="19%" >Fecha de Vencimiento</td>
								<td class="textonegroBlod" width="14%">&nbsp;${transfTO.puntosOrigenTO.fecVencer1}</td>
								<td class="healineblue1" bgcolor="#eff0f1" width="19%" >Alianzas</td>
								<td class="textonegroBlod" width="14%" align="left">&nbsp;${transfTO.puntosOrigenTO.ptsTransferidos}</td>
							</tr>
							<tr>
								<td class="healineblue1" bgcolor="#eff0f1" width="19%" >Por vencer</td>
								<td class="textonegroBlod" width="14%" align="left">&nbsp;${transfTO.puntosOrigenTO.ptsPorVencer}</td>
								<td class="healineblue1" bgcolor="#eff0f1" width="19%" >Fecha de Vencimiento</td>
								<td class="textonegroBlod" width="14%">&nbsp;${transfTO.puntosOrigenTO.fecVencer}</td>
								<td class="healineblue1" bgcolor="#eff0f1" width="19%" >Redimidos</td>
								<td class="textonegroBlod" width="14%" align="left">&nbsp;${transfTO.puntosOrigenTO.ptsRedimidos}</td>
							</tr>
							<tr>
								<td class="healineblue1" bgcolor="#eff0f1" width="19%">Renta</td>
								<td class="textonegroBlod" width="15%" align="left">&nbsp;${transfTO.puntosOrigenTO.ptsRenta}</td>
								<td class="healineblue1" bgcolor="#eff0f1" width="19%" >Antigüedad</td>
								<td class="textonegroBlod" width="14%" align="left">&nbsp;${transfTO.puntosOrigenTO.ptsAntiguedad}</td>
								<td class="healineblue1" bgcolor="#eff0f1" width="19%">Vencidos</td>
								<td class="textonegroBlod" width="14%" align="left">&nbsp;${transfTO.puntosOrigenTO.ptsVencidos}</td>
							</tr>
							<tr>
								<td class="healineblue1" bgcolor="#eff0f1" width="19%" >Excedentes</td>
								<td class="textonegroBlod" width="14%" align="left">&nbsp;${transfTO.puntosOrigenTO.ptsExcedentes}</td>
								<td class="healineblue1" bgcolor="#eff0f1" width="19%">Promoci&oacute;n</td>
								<td class="textonegroBlod" width="14%" align="left">&nbsp;${transfTO.puntosOrigenTO.ptsPromocion}</td>
								<td class="healineblue1" bgcolor="#eff0f1" width="19%">Fecha de Expiración</td>
								<td class="textonegroBlod" width="14%">&nbsp;${transfTO.puntosOrigenTO.fecVencidos}</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			<br>&nbsp;&nbsp;&nbsp;<br>         	
		</div>	
	</body>
</html>