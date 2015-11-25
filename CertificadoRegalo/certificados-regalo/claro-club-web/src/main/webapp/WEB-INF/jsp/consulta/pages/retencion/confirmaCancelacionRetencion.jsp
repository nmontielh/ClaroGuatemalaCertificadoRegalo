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
			function salir(){
				muestraProcesando();
				var form = document.getElementById('form1');
				form.action = './retencion.do';
				form.submit();
			}
			function cancelar(){
				muestraProcesando();
				var form = document.getElementById('form1');
				form.action = "./cancelaRetencion.do?retencionTOFolio=${retencionTOFolio}";
				form.submit();								
			}
			
			function muestraProcesando(){							
				document.getElementById("contenido").style.display = "none";
				document.getElementById("procesando").style.display = "block";
				document.getElementById("procesando").style.visibility="visible";				
	  			document.getElementById("contenido").style.visibility="hidden";					  
			}
			function ocultaProcesando(){
				document.getElementById("procesando").style.display = "none";
				document.getElementById("procesando").style.visibility="hidden";
			}			
			
		</script>
	</head>

<body marginwidth="0" marginheight="0" style="background-color:transparent;  MARGIN: 0px;">
	
<form name="form1" method="post" action="">
	<script>	
    	document.parentWindow.parent.activaCargando('hidden','none');     
	</script>	
	<div id="contenido" style="visibility:visible;display:block;">
	<table width="98%" border="0" cellpadding="0" cellspacing="0">
		<tr><td height="10" width="11"></td><td height="10" width="862"></td></tr>
		<tr> 
  		<td width="10"></td>
    	<td valign="top"> 
	 			<table width="100%" class="main" border="0" cellspacing="0" cellpadding="1" align="left">
        	<tr valign="top"> 
          	<td><BR> 
            	<table width="98%" border="0" cellspacing="0" cellpadding="0" align="center">
              		<tr><td class="titulo" height="42">&nbsp;&nbsp;Cancelar  Certificado de Lealtad</td></tr>
            	</table>
            	<br>
            		<table width="60%" border="0" cellspacing="0" cellpadding="0" align="right">
	            		<tr>
	              			<td bgcolor="#ECF0DB" class="healineblue1" align="right">Usuario:</td>
	                 		<td class="textonegroBlodTrs" align="left"><c:out value="${usuarioTO.idUsuario}"></c:out></td>
	                 		<td class="healineblue1" width="20" height="20"></td>
	                 		<td bgcolor="#ECF0DB" class="healineblue1" align="right">Folio:</td>
	                		<td class="textonegroBlodTrs" align="left">
	                			<font color=red><c:out value="${retencionTOFolio}"></c:out></font>
							</td>
	          			</tr>                	
					</table>	
	              	<br>
	              	<br>
	              	<table width="60%" border="1" cellspacing="0" cellpadding="0" align="center">
	              		<tr>
	              			<td class="healineblue1" height="20" bgcolor="#ECF0DB" align="left"><div>Nombre del Cliente</div></td>
	              			
	          				<td colspan=3 class="textonegroBlod" height="20"><div align="left"><c:out value="${mobileTONombre}"></c:out></div></td>
	          			</tr>
	                	<tr>    
	                  		<td class="healineblue1" height="20" bgcolor="#ECF0DB" align="left"><div>Teléfono</div></td>
	                  		<td class="textonegroBlodTrs"><div align="right"><c:out value="${mobileTOTelefono}"></c:out></div></td>
	                  		<td class="healineblue1" height="20" bgcolor="#ECF0DB" align="left"><div>Estatus</div></td>
	                  		<td class="textonegroBlod"><div align="right"><c:out value="${retencionTOEstatus}"></c:out></div></td>
	                  	</tr>
	                  	<tr>
	                  		<td class="healineblue1" height="20" bgcolor="#ECF0DB" align="left"><div>Cuenta</div></td>
	          		 		<td class="textonegroBlod"><div align="right"><c:out value="${mobileTOCuenta}"></c:out></div></td>
	                  		<td class="healineblue1" height="20" bgcolor="#ECF0DB" align="left"><div>Valor del Certificado</div></td>
	                  		<td class="textonegroBlod"><div align="right"><font color=red>$<c:out value="${retencionTOVCertif}"></c:out>.00</font></div></td>
	                  	</tr>
	                  	<tr>    
	                		<td class="healineblue1" height="20" bgcolor="#ECF0DB" align="left"><div>Fecha de Caducidad</div></td>
	                		<td class="textonegroBlod"><div align="right"><c:out value="${retencionTOFechaCaduca}"></c:out></div></td>
	                  		<td class="healineblue1" height="20" bgcolor="#ECF0DB" align="left"><div>Certificado Extra</div></td>
							<td class="textonegroBlod"><div align="right">$<c:out value="${retencionTOVCentifextra}"></c:out>.00</div></td>
						</tr>
							<td class="healineblue1" height="20" bgcolor="#ECF0DB" align="left"><div>Comentarios:</div></td>
	                		<td colspan="3" class="textonegroBlod">
	                			<input type='text' name='comentario' id="comentario" size=100 onKeyDown="if(event.keyCode==13) document.getElementById('btnCancelar').click()" MAXLENGTH=200>
	                		</td>
					</table>
					<table width="98%" border="0" cellspacing="0" cellpadding="0" align="center">
              			<tr>
                			<td colspan=2 align="center" width="100%">
                				<a class="LinkOut" style="width:20%" onmouseover='this.className="LinkIn";' onmouseout='this.className="LinkOut";'
	                    				id="btnCancelar" onClick="cancelar();">&nbsp;Cancelar&nbsp;&nbsp;</a>
                    			
                    			<a class="LinkOut" style="width:20%" onmouseover='this.className="LinkIn";' onmouseout='this.className="LinkOut";'
                    				id="Link2" onClick="salir();">&nbsp;Salir&nbsp;&nbsp;</a>
                    		</td>
                    	</tr>
                    </table>
                  </td>
              </tr>
        	</table>
		</td>
	</tr>
	</table>
	</div>
	
	<DIV id="procesando" class="TarjetaDetalleSinBorde" style=" top:50px;height: 300px;width: 100%;visibility: hidden;display: none;left: 10px;position:absolute;">
		<IFRAME src="./commons/ProcesandoInfo.html" name="Frame2" id="Frame2" scrolling="no" WIDTH="100%" HEIGHT="100%" style="border: hidden;" frameborder="0" AllowTransparency></IFRAME>
	</DIV>
	
</form>
</body>
</html>