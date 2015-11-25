<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<html>
	<head>
		<title>Circulo Azul</title>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
		<link rel="stylesheet" href='<c:url value="/commons/js/sc_textsheet.css"/>' type="text/css">
		<script LANGUAGE=javascript>
<!--
	function agrega() {
		if(form1.nombreAlianza.value == ""){
    		window.alert("Debe capturar información en el campo Nombre.")
    		return false;
 		}
 		if(form1.appPaterno.value == ""){
    		window.alert("Debe capturar información en el campo Apellido Paterno.")
    		return false;
 		}
 		if(form1.appMaterno.value == ""){
    		window.alert("Debe capturar información en el campo Apellido Materno.")
    		return false;
 		}
 
 		if(isPositiveInteger(trim(removeZeros(form1.cuentaAlianza.value))) == false){
    		window.alert("Debe capturar un número positivo en el campo Cuenta Alianza.")
    		return false;
 		}
 		
 		var form = document.getElementById('form1');
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
		// -->
		</script>
	</head>

	<body bgcolor="#ffffff" marginwidth="0" marginheight="0" style="background-color: transparent;MARGIN-TOP: 0px; OVERFLOW-Y: scroll; OVERFLOW-X: hidden; 
			WIDTH:926px; HEIGHT: 378px;position: absolute;top: 0px;visibility: visible;display: block;">
	
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
                    						<input id="nombreAlianza" name="nombreAlianza" maxlength="30" size=30 style="TEXT-TRANSFORM:UPPERCASE"></td>
                    					<td width="18%" class="healineblue1" align="center" height="20">Cuenta Alianza:</td>
                  						<td width="25%"> 
                    						<input id="cuentaAlianza" name="cuentaAlianza" maxlength="12" size=13></td>
				
        							</tr>  
        							<tr> 
                  						<td class="healineblue1" width="18%">Apellido Paterno: </td>
                  						<td width="23%"> 
                    						<input id="appPaterno" name="appPaterno" maxlength="30" size=30 style="TEXT-TRANSFORM:UPPERCASE"></td>
		          						<td class="healineblue1" width="26%" align ="center">Apellido 
                    						Materno:</td> 
                  						<td width="33%"> 
                    						<input id="appMaterno" name="appMaterno" maxlength="30" size=30 align=left style="TEXT-TRANSFORM:UPPERCASE"></td>
                					</tr>
                					<tr> 
          								<td colspan="4" align="center" height="63"><div align="center"><font color="red" face="arial,helvetica" size="4">
		  									Los datos deben ser capturados igual que como están
		   									en la tarjeta Frecuenta del usuario.</font></div></td>
		   							</tr>		   							
		  							<tr> 
          								<td class="healineblue1" width="18%">Teléfono</td>
          								<td width="23%"><input name="telefono" id="telefono" maxlength="10" value="${telefono}" readonly></td>
		  								<td class="healineblue1" width="26%" align="right">Cuenta:&nbsp;&nbsp;&nbsp;&nbsp;</td>
          								<td width="33%">
											<input name="cuenta" id="cuenta" maxlength="10" value="${cuenta}" readonly></td>
        							</tr>
        							<tr> 
          								<td class="textonegroBlodTrs" width="18%">&nbsp;</td>
        							</tr>
      							</table>
      							<table width="98%" border="0" cellspacing="0" cellpadding="0" align="center">
        							<tr> 
          								<td align="center" width="20%">
             								<a class="LinkOut" style="width:20%" onmouseover='this.className="LinkIn";' onmouseout='this.className="LinkOut";'
                								id="Link1" onClick="agrega();">&nbsp;Agregar&nbsp;&nbsp;</a>
          								</td>
        							</tr>
        						</table>
								
          					</td>
        				</tr>
      				</table>
      				
      				<input type="hidden" name="secuencia" id="secuencia" value="${secuencia}">
      				<input type="hidden" name="region" id="region" value="${region}">
					<input type="hidden" name="millasDisponibles" id="millasDisponibles" value="${millasDisponibles}"/>
					<input type="hidden" name="ptosDisponibles" id="ptosDisponibles" value="${ptosDisponibles}"/>
					<input type="hidden" name="factor" id="factor" value="${factor}">
					<input type="hidden" name="millaMin" id="millaMin" value="${millaMin}">
					<input type="hidden" name="estatusPuntos" id="estatusPuntos" value="${estatusPuntos}">
					<input type="hidden" name="cveAlianza" id="cveAlianza" value="${cveAlianza}">
					<input type="hidden" name="alianza" id="alianza" value="${alianza}">					
					
					<input type="hidden" name="folio" id="folio" value="${alianzasTO.folio}">					
										
					      				
     			</form>
    		</td>
  		</tr>
	</table>
</body>
</html>

