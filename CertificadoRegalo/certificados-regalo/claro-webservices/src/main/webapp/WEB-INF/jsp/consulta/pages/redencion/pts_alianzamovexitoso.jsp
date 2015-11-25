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
		
			function muestraProcesando(){
  				document.getElementById("Tarjeta1").style.display = "none";
				document.getElementById("Tarjeta1").style.visibility="hidden";
				document.getElementById("TarjetaProcesando").style.display = "block";
				document.getElementById("TarjetaProcesando").style.visibility="visible";
			}
			
			function consultaCupones(){
				document.parentWindow.parent.document.Frame6.location = "./muestraLiberar.do?cuenta=${cuenta}&secuencia=${secuencia}&region=${region}&telefono=${telefono}&millas=${millas}&ptsDisponibles=${ptosDisponibles}&factor=${factor}&millaMin=${millaMin}";
			}
			
			function validaAlianza(){
				muestraProcesando();
				var form = document.getElementById('form1');
				form.action="./validaAlianza.do";				
				form.submit();
			}
			
		</script>
		
	</head>
	
	<body bgcolor="#ffffff" marginwidth="0" marginheight="0" style="background-color: transparent;MARGIN-TOP: 0px; 
			WIDTH:926px; HEIGHT: 378px;position: absolute;top: 0px;visibility: visible;display: block;">		
			
		<form name="form1" method="post" action="">
			<DIV id="Tarjeta1"  style="visibility: visible;display: block;">			
			<table width="98%" border="0" cellpadding="0" cellspacing="0" class='main' >
    			<tr> 
  					<td height="10" width="10"></td>
  					<td height="10"></td>
  				</tr>
  				<tr> 
    				<td width="10">&nbsp;</td>
    				<td valign="top">
      					<table width="100%" border="0" cellspacing="0" cellpadding="0" align="center" >
        					<tr>
		  						<td>
		  							<table width="98%" border="0" cellspacing="0" cellpadding="0" align="center">  
        								<tr> 
          									<td width="25%" class="healineblue1" valign="center" height="20">&nbsp;</td>
        								</tr>
										<tr> 
          									<td width="25%" class="healineblue1" valign="center" height="20">&nbsp;</td>
        								</tr>
        								<c:if test="${sOperacion != 'Confirmacion de Canje'}">
        									<tr bgcolor="#eff0f1"> 
          										<td class="healineblue1" valign="center" height="34"> 
            										<div align="left"></div>
            										<div align="left"></div>
            										<div align="center">El movimiento fue exitoso.</div>
          										</td>
        									</tr>
        								</c:if>
        								<c:if test="${sOperacion == 'Confirmacion de Canje'}">
        									<tr bgcolor="#eff0f1"> 
          										<td class="healineblue1" valign="center" height="34"> 
            										<div align="left"></div>
        	    									<div align="left"></div>
    	        									<div align="center">El folio de la liberación es:&nbsp;&nbsp;
    	        										<c:out value="${folio}"></c:out>
    	        									</div>
    	        									
	          									</td>
        									</tr>
        								</c:if>		
      								</table>            						  
        							<table width="98%" border="0" cellspacing="0" cellpadding="0" align="center">
        								<tr>
        								<c:if test="${alianza == '2' && sOperacion == 'Confirmacion de Canje'}">
        									<td align="center" width="40%">
              										<a class="LinkOut" style="width:40%" onmouseover='this.className="LinkIn";' onmouseout='this.className="LinkOut";'
                 										id="Link1" onClick="consultaCupones();">&nbsp;Consulta cupones&nbsp;&nbsp;</a>
            									</td>
            							</c:if>							
          							</tr>
        						</table>        						      						       							
	  						</td>
	  					</tr>
	  				</table>
	  			</td>
	  		</tr>
	  	</table>
	<c:if test="${sOperacion == 'Alta Alianza' || sOperacion == 'Modificación Alianza'}">
	
	<table width="98%" border="0" cellpadding="0" cellspacing="0" class='main'>
	  	<tr> 
		    <td height="20" width="10"></td>
    		<td height="20"></td>
  		</tr>
  		<tr> 
    		<td width="10">&nbsp;</td>
    		<td valign="top"> 
        		<table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
          			<tr valign="top"> 
            			<td colspan="3"><BR>
            				<table width="98%" border="1" cellspacing="0" cellpadding="0" align="center">	
								<tr> 
				  					<td width="18%" class="healineblue1" valign="middle" height="20" bgcolor="#ECF0DB"> 
                    					<div align="center">Nombre(s)</div>
                    				</td>
				  					<td class="textonegroBlod" width="30%" height="20" valign="middle">
				  						<c:out value="${nombreAlianza}"></c:out>
				  					</td>
				  					<td width="23%" class="healineblue1" valign="middle" height="19" bgcolor="#ECF0DB"> 
                    					<div align="center">Alianza</div></td>
				  					<td class="textonegroBlod" width="29%" height="19" valign="middle">
				  						<c:out value="${cveAlianza}"></c:out>
				  					</td>
								</tr>
								<tr> 
				  					<td width="18%" class="healineblue1" valign="middle" height="20" bgcolor="#ECF0DB"> 
                    					<div align="center">Apellido Paterno</div></td>
				  					<td class="textonegroBlod" width="30%" height="20" valign="middle"><c:out value="${appPaterno}"></c:out></td>
				  					<td width="23%" class="healineblue1" valign="middle" height="20" bgcolor="#ECF0DB"> 
                    					<div align="center">Puntos Disponibles</div></td>
				  					<td class="textonegroBlod" width="29%" height="19" valign="middle"> 
                    					<c:out value="${ptsDisponibles}"></c:out>
                  					</td>
								</tr>
								<tr> 
				  					<td width="18%" class="healineblue1" valign="middle" height="20" bgcolor="#ECF0DB"> 
                    					<div align="center">Apellido Materno</div></td>
				  					<td class="textonegroBlod" width="30%" height="20" valign="middle">
				  						<c:out value="${appMaterno}"></c:out>
				  					</td>
				  					<td width="23%" class="healineblue1" valign="middle" height="20" bgcolor="#ECF0DB"> 
                    					<div align="center">Estatus Puntos</div></td>
				  					<td class="textonegroBlod" width="29%" height="20" valign="middle"><FONT
										color="red"><c:out value="${estatusPuntos}"></c:out></FONT></td>
								</tr>
								<tr> 
				  					<td width="18%" class="healineblue1" valign="middle" height="20" bgcolor="#ECF0DB"> 
                    					<div align="center">Teléfono</div></td>
				  					<td class="textonegroBlod" width="30%" height="19" valign="middle">
				  						<c:out value="${telefono}"></c:out>
				  					</td>
				  					<c:if test="${alianza==1}">
				  						<td width="23%" class="healineblue1" valign="middle" height="20" bgcolor="#ECF0DB"> 
	                    					<div align="center">Cuenta Alianza</div></td>
	                					<td class="textonegroBlod" width="29%" height="20" valign="middle"><font color=red></font>
				  							<c:out value="${cuentaAlianza}"></c:out>
				  						</td>
				  					</c:if>
								</tr>
								<tr>  
					  				<td width="18%" class="healineblue1" valign="middle" height="19" bgcolor="#ECF0DB"> 
    					                <div align="center">Cuenta</div></td> 
				  					<td class="textonegroBlod" width="30%" height="19" valign="middle">
				  						<c:out value="${cuenta}"></c:out>
				  					</td>
				  					<c:if test="${alianza==1}">
				  						<td width="23%" class="healineblue1" valign="middle" height="19" bgcolor="#ECF0DB"> 
	                    					<div align="center">Millas Disponibles</div>
                  						</td>
                  						<td class="textonegroBlod" width="29%" height="20" valign="middle">
					  						<c:out value="${millasDisponibles}"></c:out>
				  						</td>
                  					</c:if>                    
                				</tr>
              				</table>
             			</td>
            		</tr>
           		</table>
         	</td>
         </tr>
      </table>
    </c:if>

	  	<input type="hidden" name="cuenta" id="cuenta" value="${cuenta}">
	  	<input type="hidden" id="secuencia" name="secuencia" value="${secuencia}">
	  	<input type="hidden" name="telefono" id="telefono" value="${telefono}">
	  	<input type="hidden" name="region" id="region" value="${region}">
	  	<input type="hidden" id="millasDisponibles" name="millasDisponibles" value="${millasDisponibles}">
	  	<input type="hidden" id="ptsDisponibles" name="ptsDisponibles" value="${ptsDisponibles}">
	  	<input type="hidden" id="factor" name="factor" value="${factor}">
	  	<input type="hidden" id="millaMin" name="millaMin" value="${millaMin}">
	  	<input type="hidden" id="estatusPuntos" name="estatusPuntos" value="${estatusPuntos}">
	  	<input type="hidden" id="cuentaAlianza" name="cuentaAlianza" value="${cuentaAlianza}">
	  	<input type="hidden" id="nombreAlianza" name="nombreAlianza" value="${nombreAlianza}">
	  	<input type="hidden" id="cveAlianza" name="cveAlianza" value="${cveAlianza}">
	  	<input type="hidden" id="appPaterno" name="appPaterno" value="${appPaterno}">
		<input type="hidden" id="appMaterno" name="appMaterno" value="${appMaterno}">
	  	<input type="hidden" id="millas" name="millas" value="${millas}">
	  	<input type="hidden" id="opcion" name="opcion" value="${alianza}">
	  	<input type="hidden" name="alianza" value="${alianza}"> 
		
		
	  	</DIV>
	  	
	  	<DIV id="TarjetaProcesando" class="TarjetaDetalleSinBorde" style=" top:0px;height: 460px;width: 100%;visibility: hidden;display: none;position:absolute">
	  		<IFRAME src="./commons/ProcesandoInfo.html" name="Frame3" id="Frame3" scrolling="no" WIDTH="100%" HEIGHT="100%" style="border: hidden;" frameborder="0" AllowTransparency></IFRAME>
	  	</DIV>
	  </form>
	</body>
</html>
