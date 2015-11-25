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
		<!--
			function form1_onsubmit() {
				if(form1.nombreAlianza.value == ""){
    				window.alert("Debe capturar información en el campo Nombre.");
    				return false;
 				}
 				if(form1.appPaterno.value == ""){
    				window.alert("Debe capturar información en el campo Apellido Paterno.");
    				return false;
	 			}
 				if(form1.appMaterno.value == ""){
    				window.alert("Debe capturar información en el campo Apellido Materno.");
    				return false;
	 			}
	 			muestraProcesando();
 				form1.submit();
			}
			function actualizaAlianza(){
				var form = document.getElementById('form1');
				form.action='./actualizaAlianza.do';
				form1_onsubmit();
			}
			
			function cancelaAlianza(){
				var form = document.getElementById('form1');
				form.action='./cancelaAlianza.do';
				form.submit();
			}
			
			function muestraProcesando(){
  				document.getElementById("Tarjeta1").style.display = "none";
				document.getElementById("Tarjeta1").style.visibility="hidden";
				document.getElementById("TarjetaProcesando").style.display = "block";
				document.getElementById("TarjetaProcesando").style.visibility="visible";
			}
						
		// -->
		</script>
	</head>
	
	<body bgcolor="#ffffff" marginwidth="0" marginheight="0" style="background-color: transparent;MARGIN-TOP: 0px; 
			OVERFLOW-Y: hidden; OVERFLOW-X: hidden;WIDTH:926px; HEIGHT: 378px;position: absolute;top: 0px;visibility: visible;display: block;">
			
			<form name="form1" method="post" action="pts_controller.jsp">
				<DIV id="Tarjeta1"  style="visibility: visible;display: block;">
      				<table width="98%" border="0" cellpadding="0" cellspacing="0" class='main'>
      					<tr>
      						<td width="10">&nbsp;</td>
      						<td valign="top">
      							<input type="hidden" name="form_name">
								<input type="hidden" name="operacion">
      							<table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
        							<tr valign="top"> 
          								<td colspan="3" height="150"> 
											
            								<c:if test="${alianza == '1'}">
            									<table width="98%" border="0" cellspacing="0" cellpadding="0" align="center" bordercolor="#ffffff">
              										<tr> 
                										<td width="25%" class="healineblue1" valign="center" height="20">&nbsp;</td>
                										<td class="textonegroBlodTrs" colspan="2" height="20" valign="center">&nbsp;</td>
                  										<td class="healineblue1" width="11%" height="20" valign="center">&nbsp;</td>
              										</tr>
              										<tr> 
                										<td colspan=3 class="healineblue1" valign="center" height="34"> 
                  											<div align="left">Cuenta Alianza: <c:out value="${alianzasTO.cuentaAlianza}"></c:out></div>
                										</td>
                  										<td class="healineblue1" width="11%"></td>
                  										<td width="38%"></td>
              										</tr>
            									</table>
            								</c:if>
            								<table width="98%" border="0" cellspacing="0" cellpadding="0" align="center">
            									<tr> 
				                  					<td class="textonegroBlodTrs">&nbsp;</td>
                								</tr>
                								<tr> 
                  									<td class="healineblue1">Nombre(s): </td>
                  									<td><input id="nombreAlianza" name="nombreAlianza" maxlength="30" size=50 value="<c:out value="${alianzasTO.nombre}"></c:out>" style="TEXT-TRANSFORM:UPPERCASE"></td>
                								<tr> 
                  									<td class="healineblue1">Apellido Paterno:</td>
                  									<td><input id="appPaterno" name="appPaterno" maxlength="30" size=50 value="<c:out value="${alianzasTO.APaterno}"></c:out>" style="TEXT-TRANSFORM:UPPERCASE"></td>
                								</tr>
                								<tr> 
                  									<td class="healineblue1">Apellido Materno:</td>
                  									<td><input id="appMaterno" name="appMaterno" maxlength="30" size=50 value="<c:out value="${alianzasTO.AMaterno}"></c:out>" style="TEXT-TRANSFORM:UPPERCASE"></td>
                								</tr>
                								<c:if test="${alianza == '2'}">
                									<tr> 
    	              									<td class="healineblue1">Alianza:</td>
	                  									<td><input id="cveAlianza" name="cveAlianza" maxlength="25" size=24 value="${alianzasTO.cveAlianza }" style="TEXT-TRANSFORM:UPPERCASE" readonly></td>
                									</tr>
                								</c:if>
                								<c:if test="${alianza == '1'}">
                									<input type="hidden" name="cveAlianza" value="<c:out value="${alianzasTO.cveAlianza }"></c:out>" />
												</c:if>
                								<tr> 
                  									<td class="healineblue1">Teléfono </td>
                  									<td><input name="telefono" id="telefono" maxlength="10" value="${telefono}" readonly></td>
                								</tr>
                								<tr> 
			                  						<td class="healineblue1">Cuenta </td>
			                  						<td><input name="cuenta" maxlength="10" value="<c:out value="${alianzasTO.cuenta}"></c:out>" readonly></td>
	                							</tr>
                								<tr> 
                  									<td class="textonegroBlodTrs">&nbsp;</td>
                								</tr>
                							</table>
                							<c:if test="${alianza == '1'}">                		
                								<table width="98%" border="0" cellspacing="0" cellpadding="0" align="center">
                									<tr> 
                  										<td class="textonegroBlodTrs" width="51%">&nbsp;</td>
				          								<td class="textonegroBlodTrs" width="49%">&nbsp;</td>
                									</tr>              
                									<tr> 
                 										<td align="center" width="20%">
                   											<a class="LinkOut" style="width:50%" onmouseover='this.className="LinkIn";' onmouseout='this.className="LinkOut";'
                      											id="Link1" onClick="cancelaAlianza();">&nbsp;Cancelar Alianza&nbsp;&nbsp;</a>
                 										</td>
                 										<td align="center" width="20%">
                   											<a class="LinkOut" style="width:50%" onmouseover='this.className="LinkIn";' onmouseout='this.className="LinkOut";'
                      											id="Link1" onClick="actualizaAlianza();">&nbsp;Actualizar Alianza&nbsp;&nbsp;</a>
                 										</td>
               										</tr>         
              									</table>
              								</c:if>
              								<c:if test="${alianza == '2'}">
              									<table width="98%" border="0" cellspacing="0" cellpadding="0" align="center">
                									<tr> 
                  										<td class="textonegroBlodTrs">&nbsp;</td>
                									</tr>
               										<tr> 
                  										<td align="center" width="20%"> 
                  											<a class="LinkOut" style="width:20%" onmouseover='this.className="LinkIn";' onmouseout='this.className="LinkOut";'
                      											id="Link1" onClick="actualizaAlianza();">&nbsp;Actualizar&nbsp;&nbsp;</a>
                      									</td>
                      								</tr>
              									</table>
              								</c:if>
              							</td>
              						</tr>
              					</table>
              				</td>
			 			</tr>
					</table>
					<input type="hidden" name="secuencia" id="secuencia" value="${secuencia}">
					<input type="hidden" name="region" id="nRegion" value="${region}"/>
					<input type="hidden" name="millasDisponibles" id="millasDisponibles" value="${millasDisponibles}"/>
					<input type="hidden" name="ptosDisponibles" id="ptosDisponibles" value="${ptosDisponibles}"/>
					<input type="hidden" name="factor" id="factor" value="${factor}">
					<input type="hidden" name="millaMin" id="millaMin" value="${millaMin}">
					<input type="hidden" name="estatusPuntos" id="estatusPuntos" value="${estatusPuntos}">
					<input type="hidden" name="folio" id="folio" value="${alianzasTO.folio}">
					<input type="hidden" name="cuentaAlianza" id="cuentaAlianza" value="${alianzasTO.cuentaAlianza}">
					<input type="hidden" name="alianza" id="alianza" value="${alianza}">
									
				</DIV>
				<DIV id="TarjetaProcesando" class="TarjetaDetalleSinBorde" style=" top:0px;height: 460px;width: 100%;visibility: hidden;display: none;position:absolute">
					<IFRAME src="./commons/ProcesandoInfo.html" name="Frame3" id="Frame3" scrolling="no" WIDTH="100%" HEIGHT="100%" style="border: hidden;" frameborder="0" AllowTransparency></IFRAME>
				</DIV>
			</form>
	</body>
</html>
