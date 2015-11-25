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
				var form = document.getElementById('form1');
				form.action="./retencion.do";
				ocultaContenido();
				//document.parentWindow.parent.muestradDiv();
				form.submit(); 
			}
			function ocultaContenido(){
				var contenido = document.getElementById("contenido");
				contenido.style.visibility = "hidden";
				contenido.style.display = "none";
			}
		</script>
	</head>
	<body marginwidth="0" marginheight="0" style="background-color:transparent; MARGIN: 0px">
		<script>	
    		document.parentWindow.parent.activaCargando('hidden','none');     
		</script>
		<form name="form1" method="post" action="pts_controller.jsp">
		<div id="contenido" style="visibility: visible;display: block;">
		<input type="hidden" name="form_name">
			<table width="98%" border="0" cellpadding="0" cellspacing="0" class='main' >
		  		<tr> 
		    		<td height="10" width="10"></td>
		    		<td height="10" width="740"></td>
		  		</tr>
		  		<tr> 
		    		<td width="10">&nbsp;</td>
		    		<td valign="top">
						<table width="98%" border="0" cellspacing="0" cellpadding="0" align="center" >
		        			<tr> 
				  				<td><br>
				  					<table width="98%" border="0" cellspacing="0" cellpadding="0" align="center" >
										<tr> 
									  		<td class="titulo" height="42">&nbsp;&nbsp;Datos del Certificado de Lealtad</td>
									  	</tr>
								  	</table>
		      						<BR>
		      						<table width="98%" border="1" cellspacing="0" cellpadding="0" align="center" height="115">
					            	<tr> 
						              	<td width="15%" class="healineblue1" height="20" bgcolor="#ECF0DB" align="left">Nombre</td>
						                <td class="textonegroBlod" width="50%" height="20" valign="middle"> 
						                	<div align="left"></div>${nombre}</td>
						                <td width="15%" class="healineblue1" height="20" bgcolor="#ECF0DB" align="left">
						                	Cuenta padre</td>
						                <td class="textonegroBlod" width="20%" height="20" valign="middle">
						                	<div align="left"></div>${mobileTOCtaPadre}</td>
					              	</tr>
						            <tr> 
						                <td width="15%" class="healineblue1" height="20" bgcolor="#ECF0DB" align="left"> 
						            	  	Teléfono</td>
						                <td class="textonegroBlod" width="50%" height="20" valign="middle"> 
							                <div align="left"></div>${mobileTOTelefono}</td>
						                <td width="15%" class="healineblue1" height="20" bgcolor="#ECF0DB" align="left"> 
							                Estatus Puntos</td>
						                <td class="textonegroBlod" width="20%" height="20" valign="middle" style="COLOR: crimson"> 
							                <div align="left"></div>${mobileTOEstatusPtos}</td>
						            </tr>
						            <tr> 
						                <td width="15%" class="healineblue1" height="20" bgcolor="#ECF0DB" align="left"> 
						        	        Cuenta</td>
						                <td class="textonegroBlod" width="50%" height="20" valign="middle"> 
						    	            <div align="left"></div>${cuenta}</td>
								        <td colspan=2 width="15%" class="textonegroBlod" valign="middle" height="20">${mobileTOPuntosDistReser}&nbsp;</td>
						            </tr>
					                <tr> 
					                	<td width="15%" class="healineblue1" height="20" bgcolor="#ECF0DB" align="left"> 
					                		Tecnología</td>
					                	<td class="textonegroBlod" width="50%" height="20" valign="middle">${mobileTOTecnologia}</td>
					                	<td colspan=2 width="15%" class="textonegroBlod" valign="middle" height="20">${mobileTOPuntosFecReser}&nbsp;</td>
					              </tr>
					              <tr> 
					              	<td width="15%" class="healineblue1" height="20" bgcolor="#ECF0DB" align="left"> 
					                	Segmento</td>
					                <td class="textonegroBlod" width="50%" height="20" valign="middle">${mobileTOSegmento}</td>
					                <td colspan=2 width="15%" class="textonegroBlod" valign="middle" height="20">&nbsp;</td>
					              </tr>
					             </table>
					             <BR>
					             <BR>
					             <table width="98%" border="1" cellspacing="0" cellpadding="0" align="center">
					            	<tr> 
					              	<td bgcolor="#ECF0DB" align="left" class="healineblue1" width="16%">Sistema</td>
					                <td class="textonegroBlod" width="17%">${mobileTOSistema}</td>
					                <td bgcolor="#ECF0DB" align="left" class="healineblue1" width="17%">Fecha alta</td>
					                <td class="textonegroBlod" width="17%">${telefonoTOFechaAlta}</td>
					                <td bgcolor="#ECF0DB" align="left" class="healineblue1" width="16%">Ciclo</td>
					                <td class="textonegroBlod" width="17%">${mobileTOCiclo}</td>
					              </tr>
					              <tr> 
					                <td bgcolor="#ECF0DB" align="left" class="healineblue1" width="16%">Región</td>
					                <td class="textonegroBlod" width="17%">R0${mobileTORegion}</td>
					                <td bgcolor="#ECF0DB" align="left" class="healineblue1" width="17%">Plan</td>
					                <td class="textonegroBlod" width="17%">${mobileTOPlan}</td>
					                <td bgcolor="#ECF0DB" align="left" class="healineblue1" width="16%">Adendum</td>
					                <td class="textonegroBlod" width="17%">${mobileTOAdendum}</td>
					              </tr>
					              <tr> 
					                <td bgcolor="#ECF0DB" align="left" class="healineblue1" width="16%">Fecha adendum</td>
					                <td class="textonegroBlod" width="17%">${mobileTOFecAdendum}</td>
					                <td bgcolor="#ECF0DB" align="left" class="healineblue1" width="17%">Fecha alta M2K</td>
					                <td class="textonegroBlod" width="17%">${mobileTOFecAltaUser}</td>
					                <td bgcolor="#ECF0DB" align="left" class="healineblue1" width="16%">Estatus</td>
					                <td class="textonegroBlod" width="17%">${mobileTOStatus}</td>
					              </tr>
					            </table>
            					<BR> 
								<table width="98%" border="0" cellspacing="0" cellpadding="0" align="center">
									<tr><td class="textonegroBlodTrs">&nbsp;&nbsp;Certificados</td></tr>
									<tr><td height="5"></td></tr>
		  						</table>
								<table width="98%" border="1" cellspacing="0" cellpadding="0" align="center">
						  			<tr> 
			      						<td class="healineblue1"  bgcolor="#ECF0DB" width="20" align="center">FechaOper</td>
								  		<td class="healineblue1"  bgcolor="#ECF0DB" width="8%" align="center">Antigüedad </td>
										<td class="healineblue1"  bgcolor="#ECF0DB" width="8%" align="center">ARPU </td>
										<td class="healineblue1"  bgcolor="#ECF0DB" width="8%" align="center">Cobranza</td>
										<td class="healineblue1"  bgcolor="#ECF0DB" width="8%" align="center">Valor</td>
										<td class="healineblue1"  bgcolor="#ECF0DB" width="10%" align="center">Valor Extra</td>
										<td class="healineblue1"  bgcolor="#ECF0DB" width="10%" align="center">Fecha Vence</td>
										<td class="healineblue1"  bgcolor="#ECF0DB" width="5%" align="center">Estatus</td>
									</tr>
									<c:if test="${alianzasTO != null && alianzasTOSize>0}">
										<c:forEach items="${alianzasTO}" var="certificado">
												<tr> 
			        	    						<td class="textonegroBlod" width="20%" align ="center">${certificado.fechaToper}</td>
												    <td class="textonegroBlod" width="8%" align="center">${certificado.porcAntig}</td>
												    <td class="textonegroBlod" width="8%" align="center">${certificado.porcArpu}</td>
												    <td class="textonegroBlod" width="8%" align="center">${certificado.porcCob}</td>
												    <td class="textonegroBlod" width="8%" align="right">${certificado.VCertif}</td>
												    <td class="textonegroBlod" width="8%" align="right">${certificado.VCentifExtra}</td>
												    <td class="textonegroBlod" width="10%" align="center">${certificado.vigenciaMax}</td>
												    <td class="textonegroBlod" width="5%" align="center">${certificado.estatus}</td>
												</tr>
											</c:forEach>
									</c:if>
									<c:if test="${alianzasTO == null || alianzasTOSize==0}">
										<tr>
											<td class="textonegroBlod" colspan="8" align="center">Esta cuenta no tiene Certificados de Lealtad
											</td>
										</tr>										
				  					</c:if>
		  						</table>
		  						<BR>
		  						<BR>
								<table width="98%" border="0" cellspacing="0" cellpadding="0" align="center">
					  				<tr>
							  			<td align="center" width="24%"> <a class="LinkOut"
										style="width:100px" onmouseover='this.className="LinkIn";'
										onmouseout='this.className="LinkOut";' id="Link1"
										onClick="salir();">&nbsp;&nbsp;Salir&nbsp;&nbsp;</a> 
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
		</form>
	</body>
		</html>