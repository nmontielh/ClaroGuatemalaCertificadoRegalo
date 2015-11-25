<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<html>
	<head>
		<title>Retencion</title>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
		<link rel="stylesheet" href='<c:url value="/commons/js/sc_textsheet.css"/>' type="text/css">
		<script language="javascript" type="text/javascript">
			function puntos(){
				var tipoBusqueda = document.getElementById('tipoBusqueda');
				if(tipoBusqueda.value=="t"){
					var url = "./consultaDetallePuntos.do?telefono="+document.getElementById("telefono").value + "&region="+document.getElementById("region").value;
				}else{
					var url = "./consultaDetallePuntos.do?cuenta="+document.getElementById("cuenta").value+"&region="+document.getElementById("region").value;
				}				        		        		
        		document.parentWindow.parent.setConsultaSubmenus(2,url);
			}
		</script>
	</head>

	<body marginwidth="0" marginheight="0" style="background-color:transparent; MARGIN: 0px">
	<script>
		document.parentWindow.parent.activaCargando('hidden','none');     
	</script>
	
		<form name="form1" id="form1" method="post">
		<table width="98%" border="0" cellpadding="0" cellspacing="0" class='main' >
		  	<tr><td height="10" width="10"></td></tr>
		    <tr> 
		    	<td width="10">&nbsp;</td>
		      <td valign="top">
		      	<table width="98%" border="0" cellspacing="0" cellpadding="0" align="center" >
		        	<tr>
		        		<td>
									<table width="98%" border="0" cellspacing="0" cellpadding="0" align="center" >
		        		    <tr>
					   					<td class="titulo" height="42">&nbsp;&nbsp;Retención - Certificado de Descuento</td>
		           			</tr>
		          		</table>
		              <table width="98%" border="0" cellspacing="0" cellpadding="0" align="center" height="115">
		         				<tr> 
		                	<td width="25%" class="healineblue1" valign="middle" height="20">&nbsp;</td>
		                  <td class="textonegroBlodTrs" colspan="2" height="20" valign="middle">&nbsp;</td>
		                  <td class="healineblue1" width="25%" height="20" valign="middle">&nbsp;</td>
		                </tr>
		                <tr bgcolor="#eff0f1"> 
		                	<td colspan="4" class="healineblue1" valign="middle" height="34"> 
		                    <div align="center"><font size="2">El Certificado se ha generado exitosamente. Siga con la redención con renovación de adéndum para poder imprimir este Certificado.</font></div>
		                  </td>
		                </tr>
		                <tr> 
		                  <td class="textonegroBlodTrs"> 
		                    <p>&nbsp;</p>
		                    <p>&nbsp;</p>
		                  </td>
		                </tr>
		              </table>
				  				<table width="98%" border="0" cellspacing="0" cellpadding="0" align="center" height="115">  
										<tr>
											<td class="healineblue1"><font size="2">Sugerencias para uso del Certificado de Lealtad:</font>
		                    <p>&nbsp;</p>
											</td>
										</tr>
		            		<tr> 
		              		<td colspan="4" class="textonegroBlodTrs" valign="middle" height="34"> 
		                	<div align="left"></div>
		                	<div align="left"></div>
		                	<c:if test="${sMotivo=='Cambio a Amigo'}">
		                			<div><font class="healineblue2">Con costo:</font></div>
									<div><font class="textodocumentacion">&nbsp;&nbsp;* Descuento en equipo al renovar Contrato</font></div>
									<div><font class="textodocumentacion">&nbsp;&nbsp;* Descuento en Amigo Kit</font></div>
									<div><font class="textodocumentacion">&nbsp;&nbsp;* Pago de última factura</font></div>
							</c:if>
							<c:if test="${sMotivo=='Cambio a competencia por equipo'}">
									<div><font class="healineblue2">Con costo:</font></div>
									<div><font class="textodocumentacion">&nbsp;&nbsp;* Descuento en equipo al renovar Contrato</font></div>
									<div><font class="textodocumentacion">&nbsp;&nbsp;* Descuento en equipo a Precio de Lista</font></div>
									<div><font class="textodocumentacion">&nbsp;&nbsp;* Descuento en Amigo Kit</font></div>
									<div><font class="textodocumentacion">&nbsp;&nbsp;* Accesorios</font></div>
									<div><font class="textodocumentacion">&nbsp;&nbsp;* Pago de última factura</font></div>
							</c:if>
							<c:if test="${sMotivo=='Cambio a telefonía fija'}">
									<div><font class="healineblue2">Con costo:</font></div>
									<div><font class="textodocumentacion">&nbsp;&nbsp;* Descuento en equipo al renovar Contrato</font></div>
									<div><font class="textodocumentacion">&nbsp;&nbsp;* Pago de Fianza</font></div>
									<div><font class="textodocumentacion">&nbsp;&nbsp;* Descuento en Amigo Kit</font></div>
									<div><font class="textodocumentacion">&nbsp;&nbsp;* Pago de última factura</font></div>
							</c:if>
							<c:if test="${sMotivo=='Cargos incorrectos en la facturación'}">
									<div><font class="healineblue2">Con costo:</font></div>
									<div><font class="textodocumentacion">&nbsp;&nbsp;* Descuento en equipo al renovar Contrato</font></div>
									<div><font class="textodocumentacion">&nbsp;&nbsp;* Pago de Fianza</font></div>
									<div><font class="textodocumentacion">&nbsp;&nbsp;* Descuento en Amigo Kit</font></div>
									<div><font class="textodocumentacion">&nbsp;&nbsp;* Pago de última factura</font></div>
							</c:if>
							<c:if test="${sMotivo=='Mala calidad del servicio'}">
									<div><font class="healineblue2">Con costo:</font></div>
									<div><font class="textodocumentacion">&nbsp;&nbsp;* Descuento en equipo al renovar Contrato</font></div>
									<div><font class="textodocumentacion">&nbsp;&nbsp;* Descuento en Amigo Kit</font></div>
						</c:if>
						<c:if test="${sMotivo=='Precio'}">
									<div><font class="healineblue2">Con costo:</font></div>
									<div><font class="textodocumentacion">&nbsp;&nbsp;* Descuento en equipo al renovar Contrato</font></div>
									<div><font class="textodocumentacion">&nbsp;&nbsp;* Pago de Fianza</font></div>
									<div><font class="textodocumentacion">&nbsp;&nbsp;* Pago de última factura</font></div>
									<div><font class="healineblue2">Sin costo:</font></div>
									<div><font class="textodocumentacion">&nbsp;&nbsp;* Activación de equipo anterior en sistema Amigo</font></div>
									<div><font class="textodocumentacion">&nbsp;&nbsp;* Baja plan tarifario</font></div>
									<div><font class="textodocumentacion">&nbsp;&nbsp;* Ajuste de límite de consumo</font></div>
						</c:if>
						<c:if test="${sMotivo=='Problemas con cobertura'}">
									<div><font class="healineblue2">Con costo:</font></div>
									<div><font class="textodocumentacion">&nbsp;&nbsp;* Descuento en equipo al renovar Contrato</font></div>
									<div><font class="textodocumentacion">&nbsp;&nbsp;* Pago de Fianza</font></div>
									<div><font class="textodocumentacion">&nbsp;&nbsp;* Descuento en Amigo Kit</font></div>
									<div><font class="healineblue2">Sin costo:</font></div>
									<div><font class="textodocumentacion">&nbsp;&nbsp;* Activación de Amigo anterior</font></div>
									<div><font class="textodocumentacion">&nbsp;&nbsp;* Cambio de número y switcheo sin costo</font></div>
						</c:if>
						<c:if test="${sMotivo=='Problemas con el equipo'}">
									<div><font class="healineblue2">Con costo:</font></div>
									<div><font class="textodocumentacion">&nbsp;&nbsp;* Descuento en equipo al renovar Contrato</font></div>
									<div><font class="textodocumentacion">&nbsp;&nbsp;* Descuento de equipo a precio de lista</font></div>
									<div><font class="textodocumentacion">&nbsp;&nbsp;* Descuento en Amigo Kit</font></div>
						</c:if>
						<c:if test="${sMotivo=='Otra Línea Telcel'}">
									<div><font class="healineblue2">Con costo:</font></div>
									<div><font class="textodocumentacion">&nbsp;&nbsp;* Descuento en equipo al renovar Contrato</font></div>
									<div><font class="textodocumentacion">&nbsp;&nbsp;* Pago de Fianza</font></div>
									<div><font class="textodocumentacion">&nbsp;&nbsp;* Descuento en Amigo Kit</font></div>
									<div><font class="healineblue2">Sin costo:</font></div>
									<div><font class="textodocumentacion">&nbsp;&nbsp;* Activación de Amigo anterior</font></div>
									<div><font class="textodocumentacion">&nbsp;&nbsp;* Bajar plan tarifario</font></div>
						</c:if>
						<c:if test="${sMotivo!='Cambio a Amigo' && sMotivo!='Cambio a competencia por equipo' 
							&& sMotivo!='Cambio a telefonía fija' && sMotivo!='Cargos incorrectos en la facturación'
							&& sMotivo!='Mala calidad del servicio' && sMotivo!='Precio'
							&& sMotivo!='Problemas con cobertura' && sMotivo!='Problemas con el equipo'
							&& sMotivo!='Otra Línea Telcel'}">
								<div align="center"></div>
		                 		<div align="center"><font color="red" size="2">No se tienen sugerencias de uso para el motivo seleccionado.</font></div>
						</c:if>
					</td>
		           </tr>
		          <tr>
		          	<td class="textonegroBlodTrs">
		          		<P>&nbsp;</P>
		          		<P>&nbsp;</P>
		          	</td>
		          </tr>
		         </table>
		         <table width="50%" border="0" cellspacing="0" cellpadding="0" align="center">
            		<tr> 
          	    		<td align="center" width="25%">
            		      	<a class="LinkOut" style="width:90%" onmouseover='this.className="LinkIn";' onmouseout='this.className="LinkOut";'
                   				id="Link1" onClick="puntos();">&nbsp;Puntos&nbsp;&nbsp;</a>
              			</td>
		            </tr>        
          		</table>
          	</td>
          </tr>
         </table>
        </td>
       </tr>
      </table>
       <input type="hidden" id="telefono" name="telefono" value="${telefono}">
       <input type="hidden" id="cuenta" name="cuenta" value="${cuenta}">
       <input type="hidden" id="region" name="region" value="${region}">
       <input type="hidden" id="tipoBusqueda" name="tipoBusqueda" value="${tipoBusqueda}">
	</form>
</body>
</html>