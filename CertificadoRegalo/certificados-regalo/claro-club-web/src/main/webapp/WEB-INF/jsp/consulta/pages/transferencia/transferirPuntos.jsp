<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/permisos.tld" prefix="securityCa"%>

<html>
    <head>
        <title>Transferencia puntos</title>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
        <link rel="stylesheet" href='<c:url value="/commons/js/sc_textsheet.css"/>' type="text/css">
        
        <script type="text/javascript" src='<c:url value="/commons/js/jquery-1.2.3.js"/>'></script>
                        
        <script type="text/javascript">
        	function validaInfo(){
        	
        		var tipoTransferencia = document.getElementById("tipoTransferencia");
        		
        		if(frmTransferencia.telefono.value==""){
        			window.alert("Debe capturar un teléfono");
        			return false;
        		} else if(isNaN(frmTransferencia.telefono.value) || frmTransferencia.telefono.value.length != 10){
        			window.alert ("El teléfono no es válido");
					frmTransferencia.telefono.value="";
					return false;
				} else if(frmTransferencia.cuenta.value==""){
	        		window.alert("Debe capturar una cuenta.");
        			return false;
        		} else if(isNaN(frmTransferencia.cuenta.value) || frmTransferencia.cuenta.value.length < 2){
	        		window.alert("La cuenta no es valida");
	        		frmTransferencia.cuenta.value="";
        			return false;        		
        		} else if(frmTransferencia.region.value==0){
        			window.alert("Debe seleccionar la región.");
        			return false;
        		} else{
        			var opciones = document.getElementsByName("tipoTransferencia");
       				
       				if(opciones==null || opciones.length==0){
       					window.alert("No cuenta con Privilegios para realizar esta operación.");
       					return false;
       				}       				
       				var radioValue = "";
       				var eligeOpc = false;
       				for(var i=0;i<opciones.length;i++){
       					if(opciones[i].checked==true){
       						eligeOpc = true;
       						radioValue = opciones[i].value;
       						continue;
       					}      					
       				}
       				
       				if(eligeOpc){
       					//envia solicitud       		        		        		  	        		        	
        				frmTransferencia.method = "POST";
        				/* CANCELACION DE TRANSFERENCIA - JAPA 17/12/2012 Folio 120213 */
	       				if(radioValue == 'CANCELACION') {
	       					document.getElementById("vista").value = 'B';//Buscar Cancelacion
	       					//frmTransferencia.action = "./cancelaTransferencia.do";        		
	       					frmTransferencia.action = "./transferenciaDetalle.do";
        					frmTransferencia.submit();
	       				} else {
	       					frmTransferencia.action = "./transferenciaDetalle.do";        		
        					frmTransferencia.submit();
	       				}
       					
        				       				        					
       				}else{
       					window.alert("Debe seleccionar el tipo de transferencia.");
       					return false;
       				}
        		}
        	}
        	
        </script>
    </head>
    
    <body  marginwidth="0" marginheight="0" style="MARGIN: 0px;background-color: transparent;" >
    	<script>
    		document.parentWindow.parent.activaCargando('hidden','none'); 
    	</script>
       <form id="frmTransferencia" name="frmTransferencia" method="POST">
      	<div style="height: 350px;top: 10px;position: absolute;padding-left: 50px;" >
            <table width="100%"  cellspacing="0" cellpadding="0" align="center">
                <tr > 
                    <td colspan="3" class="titulo" height="42" width="100%" >&nbsp;&nbsp;Transferencia de Puntos</td>
                </tr>
                <tr> 
                    <td width="15%" class="healineblue1" align="left" height="20"> 
                        Tel&eacute;fono origen 
                    </td>
                    <td class="textonegroBlodTrs" colspan="2" width="85%" height="20" align="left"> 
                        <input type="text" id="telefono" name="telefono" maxlength="10" 
                        	onKeyDown="if(event.keyCode==13) validaInfo();">                        
                    </td>
                </tr>
                <tr> 
                    <td width="15%" class="healineblue1" align="left" height="20">
                    	Cuenta origen 
                    </td>
                    <td class="textonegroBlodTrs" width="35%" height="20" align="left"> 
                        <input type="text" id="cuenta" name="cuenta" maxlength="10" 
                        	onKeyDown="if(event.keyCode==13) validaInfo();">
                    </td>
                    <td align="center" width="50%"> 
                    <a class="LinkOut" style="width:100px" 
                    	onmouseover='this.className="LinkIn";' 
                    	onmouseout='this.className="LinkOut";'
                    	id="Link1" onClick="validaInfo();">&nbsp;Continuar&nbsp;&nbsp;</a> 
                    </td>                                           
                </tr>
                <tr>                     
                    <td  class="healineblue1" align="left" height="20">Regi&oacute;n</td>
                    
                    <td colspan="2"> 
                    <select name="region" size="1" onKeyDown="if(event.keyCode==13) document.getElementById('Link1').click()">                        
                        <option value="0"></option>
                        <option value="1"  <c:if test='${sessionScope.usuarioTO.region=="1"}'> selected </c:if>>1</option>
                        <option value="2"  <c:if test='${sessionScope.usuarioTO.region=="2"}'> selected </c:if>>2</option>
                        <option value="3"  <c:if test='${sessionScope.usuarioTO.region=="3"}'> selected </c:if>>3</option>
                        <option value="4"  <c:if test='${sessionScope.usuarioTO.region=="4"}'> selected </c:if>>4</option>
                        <option value="5"  <c:if test='${sessionScope.usuarioTO.region=="5"}'> selected </c:if>>5</option>
                        <option value="6"  <c:if test='${sessionScope.usuarioTO.region=="6"}'> selected </c:if>>6</option>
                        <option value="7"  <c:if test='${sessionScope.usuarioTO.region=="7"}'> selected </c:if>>7</option>
                        <option value="8"  <c:if test='${sessionScope.usuarioTO.region=="8"}'> selected </c:if>>8</option>
                        <option value="9"  <c:if test='${sessionScope.usuarioTO.region=="9" || sessionScope.usuarioTO.region=="0"}'> selected </c:if>>9</option>
                    </select>
                    </td>
                </tr>
            </table>
            <br>
            <table width="100%"  cellspacing="0" cellpadding="0" align="center">
                <tr>
                	<td class="healineblue1" align="left">
                		<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="31">
	                		<input type="radio" name="tipoTransferencia" id="tipoTransferencia" value="REGION"  
	                			onKeyDown="if(event.keyCode==13) document.getElementById('Link1').click()">
	                			Transferencia para Cambio de Regi&oacute;n
	                	</securityCa:validaPerfil>
                	</td>
                </tr>
                <tr>
                	<td class="healineblue1" align="left">
                		<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="32">
	                		<input type="radio" name="tipoTransferencia" id="tipoTransferencia" value="ANEXO" 
	                			onKeyDown="if(event.keyCode==13) document.getElementById('Link1').click()">
	                			Tranferencia para Plan "ANEXO"
	                		</securityCa:validaPerfil>
                	</td>
		          </tr>
		          <!-- JSC - Folio: 96556 -->
		          <tr>
                	<td class="healineblue1" align="left">
                		<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="160">
                			<input type="radio" name="tipoTransferencia" value="EXCELENTE" 
                				onKeyDown="if(event.keyCode==13) document.getElementById('Link1').click()">
                				Tranferencia por Cliente Excelente
                		</securityCa:validaPerfil>	
                	</td>
		          </tr>
		          <%--
		          <!-- CANCELACION DE TRANSFERENCIA - JAPA 17/12/2012 Folio 120213 / -- >
		          <tr>
                	<td class="healineblue1" align="left">&nbsp;</td>
		          </tr>
		          <tr>
                	<td class="healineblue1" align="left">
                		<%--<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="160">-- % >
                			<input type="radio" name="tipoTransferencia" value="CANCELACION" 
                				onKeyDown="if(event.keyCode==13) document.getElementById('Link1').click()">
                				Cancelación de Transferencia
                		<%--</securityCa:validaPerfil>-- % >
                	</td>
		          </tr>
		          --%>
		    </table>                
         </div>
         <input type="hidden" name="vista" value="I">
       </form>                     
    </body>
</html>
