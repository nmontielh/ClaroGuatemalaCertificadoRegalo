<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/permisos.tld" prefix="securityCa" %>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>alianzaAltaCambio</title>
<link rel="stylesheet" href='<c:url value="/commons/js/sc_textsheet.css"/>' type="text/css">
<script >
	function Consulta(){
		var form = document.getElementById('frmAltaCambio');
		var op = document.getElementById('opcion');
		form.action="./validaAlianza.do";
		form.submit();				
	}
	function muestraProcesando(){
		document.getElementById("Tarjeta1").style.display = "none";
		document.getElementById("Tarjeta1").style.visibility="hidden";
		document.getElementById("nuevaAlianzaAmex").style.display = "none";
		document.getElementById("nuevaAlianzaAmex").style.visibility="hidden";
		
		document.getElementById("actualizaAlianzaAmex").style.display = "none";
		document.getElementById("actualizaAlianzaAmex").style.visibility="hidden";		
		
		
		document.getElementById("TarjetaProcesando").style.display = "block";
		document.getElementById("TarjetaProcesando").style.visibility="visible";
	}
	function agrega() {
		
		var form;
		form = document.getElementById('agregaAlianzaAmex');
	
		if(form.nombreAlianza.value == ""){
    		window.alert("Debe capturar información en el campo Nombre.")
    		return false;
 		}
 		if(form.appPaterno.value == ""){
    		window.alert("Debe capturar información en el campo Apellido Paterno.")
    		return false;
 		}
 		if(form.appMaterno.value == ""){
    		window.alert("Debe capturar información en el campo Apellido Materno.")
    		return false;
 		}
 
 		if(isPositiveInteger(trim(removeZeros(form.cuentaAlianza.value))) == false){
    		window.alert("Debe capturar un número positivo en el campo Cuenta Alianza.")
    		return false;
 		}
 		
 		form.action='./agregaAlianza.do';
		form.submit();
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
	function muestraBloque(alianza,opcion){
		
		//amex
		if(alianza==2){
			if(opcion=='M'){
				document.getElementById("nuevaAlianzaAmex").style.display = "none";
				document.getElementById("nuevaAlianzaAmex").style.visibility="hidden";
				document.getElementById("actualizaAlianzaAmex").style.display = "block";
				document.getElementById("actualizaAlianzaAmex").style.visibility="visible";
			
			}else{
				document.getElementById("nuevaAlianzaAmex").style.display = "block";
				document.getElementById("nuevaAlianzaAmex").style.visibility="visible";
				document.getElementById("actualizaAlianzaAmex").style.display = "none";
				document.getElementById("actualizaAlianzaAmex").style.visibility="hidden";							
			}
		}
	}	
	function form1_onsubmit() {
		
		var form;
		form = document.getElementById('updateAlianzaAmex');
	
		if(form.nombreAlianza.value == ""){
    		window.alert("Debe capturar información en el campo Nombre.");
    		return false;
 		}
 		if(form.appPaterno.value == ""){
    		window.alert("Debe capturar información en el campo Apellido Paterno.");
    		return false;
		}
 		if(form.appMaterno.value == ""){
    		window.alert("Debe capturar información en el campo Apellido Materno.");
    		return false;
		}
		muestraProcesando();
 		form.submit();
	}
	function actualizaAlianza(){
		var form;
		form = document.getElementById('updateAlianzaAmex');		
		form.action='./actualizaAlianza.do';
		form1_onsubmit();
	}
	
</script>
</head>
<body>

<script >
	document.parentWindow.parent.activaCargando("hidden","none");
</script>

<form id="frmAltaCambio" name="frmAltaCambio" method="post" target="iResultado">
	<DIV id="Tarjeta1" style="position: static;top: 20px;height: 280px;MARGIN-TOP: 0px; OVERFLOW-Y: hidden; OVERFLOW-X: hidden;">	
		<input type="hidden" name="cuenta" value="${cuenta}">
		<input type="hidden" name="secuencia" value="${secuencia}">
		<input type="hidden" name="telefono" value="${telefono}">		
		<input type="hidden" name="region" value="${region}">
		<input type="hidden" id="millasDisponibles" name="millasDisponibles" value="${millas}">
		<input type="hidden" id="ptsDisponibles" name="ptsDisponibles" value="${ptsDisponibles}"/>
		<input type="hidden" id="factor" name="factor" value="${factor}"/>
		<input type="hidden" id="millaMin" name="millaMin" value="${millaMin}"/>
		<input type="hidden" id="estatusPuntos" name="estatusPuntos" value="${estatusPuntos}">
		<table>
			<tr class="textRadio">
				
				<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="91">
					<td><input type="radio" name="opcion" value="2" onclick="muestraBloque(this.value, '${banderaAmex}');"></td>			
					<td>American Express</td>
				</securityCa:validaPerfil>					
			</tr>
		</table>				
	</DIV>
	
	<DIV id="TarjetaProcesando" class="TarjetaDetalleSinBorde" style=" top:0px;height: 460px;width: 100%;visibility: hidden;display: none;position:absolute">
		<IFRAME src="./commons/ProcesandoInfo.html" name="Frame3" id="Frame3" scrolling="no" WIDTH="100%" HEIGHT="100%" style="border: hidden;" frameborder="0" AllowTransparency></IFRAME>
	</DIV>		
</form>


	
	
	<div id="nuevaAlianzaAmex" style="width:250;top: 50px;height: 265px;position: absolute;MARGIN-TOP: 0px; OVERFLOW-Y: hidden; OVERFLOW-X: hidden;visibility: hidden;display: none;">

		<table width="98%" border="0" cellpadding="1" cellspacing="0" class='main'>
  			<tr> 
    			<td height="20" width="10"></td>
    			<td height="20"></td>
  			</tr>
  			<tr> 
    			<td width="10">&nbsp;</td>
    			<td valign="top"> 
      			<form name="agregaAlianzaAmex" method="post" action="">
      				<input type=hidden name=form_name>
      				<input type="hidden" name="operacion">   
      				<table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
        				<tr valign="top">
        					<td colspan="2" height="150"><BR>
        						<table width="98%" border="0" cellspacing="0" cellpadding="0" align="center">
        							<tr>
        								<td colspan="4" class="healineblue1" valign="middle" height="30"> 
            								<div align="left"></div>
            								<div align="left"></div>
            								<div align="center"><c:out value="${texto}"></c:out></div>
            							</td>
            						</tr>		
      							</table>
          						<table width="98%" border="0" cellspacing="0" cellpadding="0" align="center" bordercolor="#ffffff">
        							<tr> 
                  						<td class="textonegroBlodTrs" width="18%">&nbsp;</td>
        							</tr>
        							<tr> 
                  						<td class="healineblue1" width="18%">Nombre(s): </td>
                  						<td width="23%"> 
                    						<input class="InputText" id="nombreAlianza" name="nombreAlianza" maxlength="30" size=30 style="TEXT-TRANSFORM:UPPERCASE"></td>
                    					<td width="18%" class="healineblue1" align="center" height="20">Cuenta Alianza:</td>
                  						<td width="25%"> 
                    						<input class="InputText" id="cuentaAlianza" name="cuentaAlianza" maxlength="12" size=13></td>
				
        							</tr>  
        							<tr> 
                  						<td class="healineblue1" width="18%">Apellido Paterno: </td>
                  						<td width="23%"> 
                    						<input class="InputText" id="appPaterno" name="appPaterno" maxlength="30" size=30 style="TEXT-TRANSFORM:UPPERCASE"></td>
		          						<td class="healineblue1" width="26%" align ="center">Apellido 
                    						Materno:</td> 
                  						<td width="33%"> 
                    						<input class="InputText" id="appMaterno" name="appMaterno" maxlength="30" size=30 align=left style="TEXT-TRANSFORM:UPPERCASE"></td>
                					</tr>
                					<tr> 
          								<td colspan="4" align="center" height="63">
          									<div id="mensajeInfoAmex" align="center">
          									<font color="red" face="arial,helvetica" size="4">
		  									La cuenta de la Alianza no se encuentra dada de alta en el sistema. Capture los datos tal como 
		  									aparecen en la Credencial de Elector u otra identificación oficial.</font>
		   								</div>
		   								</td>
		   									
		   							</tr>		   							
		  							<tr> 
          								<td class="healineblue1" width="18%">Teléfono</td>
          								<td width="23%"><input class="InputText" name="telefono" id="telefono" maxlength="10" value="${telefono}" readonly></td>
		  								<td class="healineblue1" width="26%" align="right">Cuenta:&nbsp;&nbsp;&nbsp;&nbsp;</td>
          								<td width="33%">
											<input class="InputText" name="cuenta" id="cuenta" maxlength="10" value="${cuenta}" readonly></td>
        							</tr>
        							<tr> 
          								<td class="textonegroBlodTrs" width="18%">&nbsp;</td>
        							</tr>
      							</table>
      							<table width="98%" border="0" cellspacing="0" cellpadding="0" align="center">
        							<tr> 
          								<td align="center" width="20%">
          									<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="112">
	             								<a class="LinkOut" style="width:20%" onmouseover='this.className="LinkIn";' onmouseout='this.className="LinkOut";'
	                								id="Link1" onClick="agrega();">&nbsp;Agregar&nbsp;&nbsp;</a>
	                						</securityCa:validaPerfil>
          								</td>
        							</tr>
        						</table>
								
          					</td>
        				</tr>
      				</table>
      				
      				<input type="hidden" name="secuencia" id="secuencia" value="${secuencia}">
      				<input type="hidden" name="region" id="region" value="${region}">
					<input type="hidden" name="millasDisponibles" id="millasDisponibles" value="${millasDisponibles}"/>
					<input type="hidden" name="ptsDisponibles" id="ptsDisponibles" value="${ptsDisponibles}"/>
					<input type="hidden" name="factor" id="factor" value="${factor}">
					<input type="hidden" name="millaMin" id="millaMin" value="${millaMin}">
					<input type="hidden" name="estatusPuntos" id="estatusPuntos" value="${estatusPuntos}">
					<input type="hidden" name="cveAlianza" id="cveAlianza" value="${cveAlianza}">
					<input type="hidden" name="alianza" id="alianza" value="2">					
					
					<input type="hidden" name="folio" id="folio" value="${alianzasTO.folio}">					
										
					      				
     			</form>
    		</td>
  		</tr>
	</table>
	</div>
	
	
	
	
	<div id="actualizaAlianzaAmex" style="width:250;top: 50px;height: 265px;position: absolute;MARGIN-TOP: 0px; OVERFLOW-Y: hidden; OVERFLOW-X: hidden;visibility: hidden;display: none;">
		<form name="updateAlianzaAmex" method="post" action="pts_controller.jsp">
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
											
            								<table width="98%" border="0" cellspacing="0" cellpadding="0" align="center">
            									<tr> 
				                  					<td class="textonegroBlodTrs">&nbsp;</td>
                								</tr>
                								<tr> 
                  									<td class="healineblue1">Nombre(s): </td>
                  									<td><input class="InputText" id="nombreAlianza" name="nombreAlianza" maxlength="30" size=50 value="<c:out value="${alianzaAmexTO.nombre}"></c:out>" style="TEXT-TRANSFORM:UPPERCASE"></td>
                								<tr> 
                  									<td class="healineblue1">Apellido Paterno:</td>
                  									<td><input class="InputText" id="appPaterno" name="appPaterno" maxlength="30" size=50 value="<c:out value="${alianzaAmexTO.APaterno}"></c:out>" style="TEXT-TRANSFORM:UPPERCASE"></td>
                								</tr>
                								<tr> 
                  									<td class="healineblue1">Apellido Materno:</td>
                  									<td><input  class="InputText" id="appMaterno" name="appMaterno" maxlength="30" size=50 value="<c:out value="${alianzaAmexTO.AMaterno}"></c:out>" style="TEXT-TRANSFORM:UPPERCASE"></td>
                								</tr>
                								
                									<tr> 
    	              									<td class="healineblue1">Alianza:</td>
	                  									<td><input class="InputText" id="cveAlianza" name="cveAlianza" maxlength="25" size=24 value="${alianzaAmexTO.cveAlianza }" style="TEXT-TRANSFORM:UPPERCASE" readonly></td>
                									</tr>
                								
                								
                								<tr> 
                  									<td class="healineblue1">Teléfono </td>
                  									<td><input class="InputText" name="telefono" id="telefono" maxlength="10" value="${telefono}" readonly></td>
                								</tr>
                								<tr> 
			                  						<td class="healineblue1">Cuenta </td>
			                  						<td><input class="InputText" name="cuenta" maxlength="10" value="<c:out value="${alianzaAmexTO.cuenta}"></c:out>" readonly></td>
	                							</tr>
                								<tr> 
                  									<td class="textonegroBlodTrs">&nbsp;</td>
                								</tr>
                							</table>
                							
              									<table width="98%" border="0" cellspacing="0" cellpadding="0" align="center">
                									<tr> 
                  										<td class="textonegroBlodTrs">&nbsp;</td>
                									</tr>
               										<tr> 
                  										<td align="center" width="20%"> 
                  											<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="111">
                  												<a class="LinkOut" style="width:20%" onmouseover='this.className="LinkIn";' onmouseout='this.className="LinkOut";'
                      												id="Link1" onClick="actualizaAlianza();">&nbsp;Actualizar&nbsp;&nbsp;</a>
                  											</securityCa:validaPerfil>
                      									</td>
                      								</tr>
              									</table>
              								
              							</td>
              						</tr>
              					</table>
              				</td>
			 			</tr>
					</table>
					<input type="hidden" name="secuencia" id="secuencia" value="${secuencia}">
					<input type="hidden" name="region" id="nRegion" value="${region}"/>
					<input type="hidden" name="millasDisponibles" id="millasDisponibles" value="${millasDisponibles}"/>
					<input type="hidden" name="ptsDisponibles" id="ptsDisponibles" value="${ptsDisponibles}"/>
					<input type="hidden" name="factor" id="factor" value="${factor}">
					<input type="hidden" name="millaMin" id="millaMin" value="${millaMin}">
					<input type="hidden" name="estatusPuntos" id="estatusPuntos" value="${estatusPuntos}">
					<input type="hidden" name="folio" id="folio" value="${alianzaAmexTO.folio}">
					<input type="hidden" name="cuentaAlianza" id="cuentaAlianza" value="${alianzaAmexTO.cuentaAlianza}">
					<input type="hidden" name="alianza" id="alianza" value="2">
									
				</DIV>				
			</form>
	</div>		

</body>
</html>