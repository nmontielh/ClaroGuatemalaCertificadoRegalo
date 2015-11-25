<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/permisos.tld" prefix="securityCa" %>

<html>
	<head>
		<title>Puntos Telcel</title>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
		<link rel="stylesheet" href='<c:url value="/commons/js/sc_textsheet.css"/>' type="text/css">
		<script language="javascript" type="text/javascript">
			
			var divVisible = 0;
			
			function muestraDiv(folio){
				if(divVisible==0){
					document.getElementById("divCertificados").style.visibility="visible";
					document.getElementById("divCertificados").style.display="block";
				}
				divVisible=1;				
				var idTransf = document.getElementById('idTransf');
				idTransf.value = folio; 			
			}
			
			function ocultaDiv(){
				divVisible=0;
				document.getElementById("principal").style.visibility="hidden";
				document.getElementById("principal").style.display="none";
			}
			
			function confirmaCanje(){
			
				var valorPesos = document.getElementById('valorPesos');
				var numAcompa = document.getElementById('numAcompa');
								
				if(divVisible==0){
					alert("Debe elegir un certificado para su liberacion.");
					return false;
				}
				if(trim(valorPesos.value)==0){
					alert("Debe capturar el valor del viaje.");
					return false;
				}
				if(trim(numAcompa.value)==0){
					alert("Debe capturar el número de acompañantes.");
					return false;
				}
				
				var form = document.getElementById('form1');
				form.action = './confirmaCanje.do';
				form.submit();
			}
			
			function trim(str) {
				while(str.charAt(str.length - 1) == " ")
				str = str.substring(0,str.length - 1);
				while(str.charAt(0) == " ")
					str = str.substring(1,str.length);
				return str;
			}
			
			
		</script>
	</head>
<body onunload="ocultaDiv();"  marginwidth="0" marginheight="0" style="MARGIN: 0px" >

<script >
	document.parentWindow.parent.activaCargando("hidden","none");
</script>

<form name="form1" method="post" action="">
<div id="principal">
	<table width="98%" border="0" cellpadding="1" cellspacing="0">
	<tr>
		<td width="10">&nbsp;</td>
		<td valign="top">
			
	  			<table width="98%" border="0" cellspacing="0" cellpadding="0" align="center">
                	<tr>                  
                  		<td class="textonegroBlodTrs" width="28%"><font color="red">Certificados</font></td>                 
                  		<td class="textonegroBlodTrs" width="72%">&nbsp;</td>
                	</tr>
                	<tr>                   
                		<td class="textonegroBlodTrs" width="28%">&nbsp;</td>                  
                		<td class="textonegroBlodTrs" width="72%">&nbsp;</td>
                	</tr>
              	</table> 			  
              	<table width="98%" border="1" cellspacing="0" cellpadding="0" align="center" bordercolor="#cccccc">
              		<tr bgcolor="#eff0f1"> 
				  		<td class="healineblue1" width="19%" height="13"> 
                    		<div align="center">Folio</div>
                  		</td>                  
                  		<td class="healineblue1" width="15%" height="13"> 
                    		<div align="center">Valor que ampara el certificado</div>
                  		</td>
                  		<td class="healineblue1" width="14%" height="13"> 
                    		<div align="center">Puntos canjeados</div>
                  		</td>
                  		<td class="healineblue1" width="16%" height="13"> 
                    		<div align="center">Fecha de Expedición</div>
                  		</td>
                  		<td class="healineblue1" width="16%" height="13"> 
                    		<div align="center">Fecha de caducidad</div>
                  		</td>
                  		<td class="healineblue1" width="36%" height="13"> 
                    		<div align="center">Estatus</div>
                    	</td>                  
                	</tr>
                	<c:forEach items="${alianzasTipoTO.cupones}" var="cupon">
                		<tr>
                			<td width="6%" class="textonegroBlod">
                				<c:if test="${cupon.statusTrans == 4}">
                					<input type="radio" name="opcion" value="${cupon.folio}" onclick="muestraDiv(this.value);" >
                					<c:out value="${cupon.folio}"></c:out>
                				</c:if>
                  				<c:if test="${cupon.statusTrans != 4}">
                  					<div align="right">
                  						<c:out value="${cupon.folio}"></c:out>
                  					</div>
                  				</c:if>
                  			</td>  
                			<td width="15%" class="textonegroBlod"> 
                    			<div align="right">$ <c:out value="${cupon.valorCuponOrig}"></c:out></div>
                    		</td>                    
				  			<td width="14%" class="textonegroBlod">				  
                    			<div align="right"><c:out value="${cupon.ptsTransferidos}"></c:out></div>
                    		</td>
                    		<td width="16%" class="textonegroBlod">
				  				<div align="right"><c:out value="${cupon.fechaOperacion}"></c:out></div>
				  			</td>
				  			<td width="16%" class="textonegroBlod">
				  				<div align="right"><c:out value="${cupon.vigenciaMax}"></c:out></div>
				  			</td>
				  			<td width="30%" class="textonegroBlod">
				  				<div align="center">
				  					<c:if test="${cupon.statusTrans == 0}">CUPON LIBERADO</c:if>
				  					<c:if test="${cupon.statusTrans == 1}">CANJE CANCELADO</c:if>
				  					<c:if test="${cupon.statusTrans == 2}">CUPON CADUCADO</c:if>
				  					<c:if test="${cupon.statusTrans == 4}">LIBERACION PENDIENTE</c:if>
				  				</div>
				  			</td>
				 		</tr>				  	    
                </c:forEach>
             </table>             
             <div id="divCertificados" style="width:250;height: 265px;position: relative;MARGIN-TOP: 0px; OVERFLOW-Y: hidden; OVERFLOW-X: hidden;visibility: hidden;display: none;">
               	<table width="100%" border="0" cellspacing="0" cellpadding="0" align="center" class='main'>
          						<tr valign="top"> 
            						<td colspan="3"><br>
            							<table width="98%" border="0" cellspacing="0" cellpadding="0" align="center">
                    						<tr>
                    						<tr>
                    							<td class="healineblue1" valign="center" height="20">&nbsp;</td>
                    							<td class="textonegroBlodTrs" height="20" valign="center">&nbsp;</td>
                    						</tr>
                    						<tr>
                    							<td class="healineblue1" valign="center" height="20">&nbsp;</td>
                    							<td class="textonegroBlodTrs" height="20" valign="center"><font color='red'>&nbsp;Valor
                    								del Viaje en Pesos es el valor total de los cupones</font></td>
                    						</tr>
                    						<tr>
                    							<td width="25%" class="healineblue1" valign="center" height="20">&nbsp;Valor
                    								del Viaje en Pesos:&nbsp;&nbsp;</td>
                    							<td width="75%" class="healineblue1" valign="center" height="20">
                    								<input name="valorPesos" id="valorPesos" maxlength="12" size=25>
                    							</td>
                    						</tr>
                    						<tr>
                    							<td width="25%" class="healineblue1" valign="center" height="20">&nbsp;Número de acompañantes
                    						</td>
                    						<td width="75%" class="healineblue1" valign="center" height="20">
                    							<input name="numAcompa" id="numAcompa" maxlength="2" size=7>
                    						</td>
                    					</tr>                    					
                    				</table>
                    				<table width="25%" align="center">
                    					<td align="center" width="40%">
                    						<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="95">
	                    						<a class="LinkOut" style="width:90%" onmouseover='this.className="LinkIn";' onmouseout='this.className="LinkOut";'
	                    							id="Link3" onClick="confirmaCanje();">&nbsp;Continuar&nbsp;&nbsp;</a>
	                    					</securityCa:validaPerfil>
                    					</td>
                    					<p>&nbsp;</p>
                    				</table>                    				
                    			</td>
                    		</tr>
                    	</table>
             </div>
             
			 
			 <DIV id="opcion1" style="HEIGHT: 220px; width: 880px;BORDER:solid 1px silver;position: relative;visibility: hidden;display: none;">
			 	<IFRAME name="Frame1" id="Frame1" scrolling="auto" WIDTH="100%" HEIGHT="100%" style="border: hidden;" frameborder="0" AllowTransparency></IFRAME>
			 </DIV>
			 
			 <input type="hidden" name="idTransf" id="idTransf">
			 <input type="hidden" name="cuenta" id="cuenta" value="${cuenta}">
			 <input type="hidden" name="secuencia" id="secuencia" value="${secuencia}">
			 <input type="hidden" name="region" id="region" value="${region}">
			 <input type="hidden" name="telefono" id="telefono" value="${telefono}">
			 <input type="hidden" name="millas" id="millas" value="${millas}">
			 <input type="hidden" name="ptsDisponibles" id="ptsDisponibles" value="${ptsDisponibles}">
			 <input type="hidden" name="factor" id="factor" value="${factor}">
			 <input type="hidden" name="millaMin" id="millaMin" value="${millaMin}">
	
		</td>
  		</tr>
	</table>
		</div>
		</form>
	
</body>
</html>