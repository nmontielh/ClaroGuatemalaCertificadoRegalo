<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/permisos.tld" prefix="securityCa"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Asignar Puntos</title>
<link rel="stylesheet" href='<c:url value="/commons/js/sc_textsheet.css"/>' type="text/css">
<script type="text/javascript" src='<c:url value="/commons/js/jquery-1.2.3.js"/>'></script>
<script type="text/javascript" src='<c:url value="/commons/js/jquery-validate.js"/>'></script>


</head>
<script language="javascript" type="text/javascript">

function cambioDiv(opcion){
		if(opcion==1){
			document.getElementById("divAsignar").style.visibility="visible";
			document.getElementById("divAsignar").style.display="block";
			document.getElementById("divEliminar").style.visibility="hidden";
			document.getElementById("divEliminar").style.display="none";
			document.getElementById("divAsignarAntiguedad").style.visibility="hidden";
			document.getElementById("divAsignarAntiguedad").style.display="none";
			document.getElementById("divaisgnaCancePorError").style.visibility="hidden";
			document.getElementById("divaisgnaCancePorError").style.display="none";
		}else if(opcion==2){
			document.getElementById("divEliminar").style.visibility="visible";
			document.getElementById("divEliminar").style.display="block";
			document.getElementById("divAsignar").style.visibility="hidden";
			document.getElementById("divAsignar").style.display="none";
			document.getElementById("divAsignarAntiguedad").style.visibility="hidden";
			document.getElementById("divAsignarAntiguedad").style.display="none";
			document.getElementById("divaisgnaCancePorError").style.visibility="hidden";
			document.getElementById("divaisgnaCancePorError").style.display="none";			
		}else if(opcion==3){
			document.getElementById("divAsignarAntiguedad").style.visibility="visible";
			document.getElementById("divAsignarAntiguedad").style.display="block";
			document.getElementById("divAsignar").style.visibility="hidden";
			document.getElementById("divAsignar").style.display="none";	
			document.getElementById("divEliminar").style.visibility="hidden";
			document.getElementById("divEliminar").style.display="none";
			document.getElementById("divaisgnaCancePorError").style.visibility="hidden";
			document.getElementById("divaisgnaCancePorError").style.display="none";
		}else if(opcion==4){
			document.getElementById("divaisgnaCancePorError").style.visibility="visible";
			document.getElementById("divaisgnaCancePorError").style.display="block";
			document.getElementById("divAsignarAntiguedad").style.visibility="hidden";
			document.getElementById("divAsignarAntiguedad").style.display="none";
			document.getElementById("divAsignar").style.visibility="hidden";
			document.getElementById("divAsignar").style.display="none";	
			document.getElementById("divEliminar").style.visibility="hidden";
			document.getElementById("divEliminar").style.display="none";
			
		}
	}

function validaCampo(campo, tipo){
		if (tipo == 'numerico') {
			keycode = window.event.keyCode;
			if(keycode < 48 || keycode > 57) {
				event.returnValue = false;
			}
		}
		else  {
			if(tipo == 'cadena') {                             
				event.keyCode = event.keyCode                       
				if(event.keyCode > 96 && event.keyCode < 123) {
					event.keyCode -= 32;
				}
				else {
					if(event.keyCode < 65 || event.keyCode >90) {
						event.returnValue = false;                     
					}
				}
			}
			else if( tipo == 'alfanumerico') {                     
				if(event.keyCode > 96 && event.keyCode < 123){
					event.keyCode -= 32;
				}
				else 
					if((event.keyCode >= 48 && event.keyCode < 58) || event.keyCode == 32) {
						event.returnValue = true;
					}
					else {
						if(event.keyCode < 65 || event.keyCode >90) {
							event.returnValue = false;                     
					}
				}
			}
		} 
	}	
function frmAsignar_submit() {
	var puntosMaximos = parseInt(document.getElementById("puntosMaximos").value * 1);
	var puntosAsignar = parseInt(document.getElementById("cantidadpts").value * 1);

	if(puntosMaximos == 0) {
		alert("El perfil no tiene especificado la cantidad máxima de puntos a asignar.");
		return false;
	}
	
	if(puntosAsignar > puntosMaximos) {
		alert("El límite máximo de asignación para su perfil es de "+ puntosMaximos +" puntos.");
		return false;
	}

	

	if(isNaN(frmAsignar.cantidadpts.value) || frmAsignar.cantidadpts.value == "" || frmAsignar.cantidadpts.value == "0"){
		window.alert("El valor de los puntos a asignar no es valido"); 
		return false;
 	}
 	var idMotivoAsignacion = document.getElementById("idMotivoAsig");
 	if(idMotivoAsignacion.value == -1){
 		window.alert("Seleccione un motivo de asignación."); 
    	return false;
 	}
 	
	if(frmAsignar.comentario.value == ""){
   		window.alert("Debe especificar el comentario del movimiento."); 
    	return false;	
	}
	frmAsignar.method="post";
	frmAsignar.action="./aplicaAsignarPuntos.do?ptsAsignar="+document.getElementById("cantidadpts").value + "&comentario=" + document.getElementById("comentario").value + "&telefono=" + document.getElementById("telefono").value + "&cuenta=" + document.getElementById("cuenta").value + "&region=" + document.getElementById("region").value + "&secuencia=" + document.getElementById("secuencia").value + "&tipoAsig=1&fechaAlta=${fechaAlta}";				
	frmAsignar.submit();
}

function frmAsignar_ptsAntiguedad() {
  if(frmAsignarAntiguedad.comentarioAnt.value == ""){
    window.alert("Debe especificar el comentario del movimiento."); 
    return false;
  }
  if(frmAsignarAntiguedad.ctaAnt.value == ""){
    window.alert("Debe especificar la cuenta del movimiento."); 
    return false;
  }
  frmAsignarAntiguedad.method="post";
  frmAsignarAntiguedad.action="./AsignacionPorErrorController.do";		
  frmAsignarAntiguedad.submit();
}

function frmEliminar_submit() {
		if(isNaN(frmEliminar.cantidadptsEli.value) || frmEliminar.cantidadptsEli.value == ""){
			window.alert("El valor de los puntos a asignar no es valido"); 
		return false;
  		}
		if(frmEliminar.comentarioEli.value == ""){
    		window.alert("Debe especificar el comentario del movimiento."); 
	    	return false;	
		}
		frmEliminar.method="post";
		frmEliminar.action="./eliminarPuntos.do?ptsAsignar="+document.getElementById("cantidadptsEli").value + "&comentario=" + document.getElementById("comentarioEli").value + "&telefono=" + document.getElementById("telefono").value + "&cuenta=" + document.getElementById("cuenta").value + "&region=" + document.getElementById("region").value + "&secuencia=" + document.getElementById("secuencia").value;		
		frmEliminar.submit();
	}
	
function frmAsignarporError_submit(){
	frmAsignarporError.method = "POST";
	 if (confirm("Se asignaran "+frmAsignarporError.cantidadptsErr.value+ " puntos a la cuenta "+document.getElementById("cuenta").value+"\n¿Estas seguro de que deseas continuar?")){ 
       frmAsignarporError.action = "./AsignacionPorError.do?telefono=" + document.getElementById("telefono").value + "&cuenta=" + document.getElementById("cuenta").value;        		
    frmAsignarporError.submit();  
    }      
}
</script>
 <body  marginwidth="0" marginheight="0" style="MARGIN: 0px;background-color: transparent;" >
<script >
	document.parentWindow.parent.activaCargando("hidden","none");
</script>

<form id="frmOpcion" name="frmOpcion" method="post" target="iResultado">
<DIV id="Tarjeta1" style="position: static;top: 20px;height: 110px;MARGIN-TOP: 0px; OVERFLOW-Y: hidden; OVERFLOW-X: hidden;">
<input  type="hidden" name="telefono" value="${telefono}">
<input  type="hidden" name="cuenta" value="${cuenta}">
<input  type="hidden" name="region" value="${region}">
<input  type="hidden" name="secuencia" value="${secuencia}">
<input  type="hidden" name="ptsDisponibles" value="${ptsDisponibles}">
<input  type="hidden" name="estatusPuntos" value="${estatusPuntos}">
<input  type="hidden" name="nombre" value="${nombre}">
<input  type="hidden" name="segmento" value="${segmento}">
<input  type="hidden" name="fechaAlta" value="${fechaAlta}">
<!-- MÓDULO ASIGNACIÓN DE PUNTOS - JAPA 08/02/2013 Folio 121733 --> 
<input  type="hidden" name="puntosMaximos" value="${puntosMaximos}">



<table width="90%" border="0" cellspacing="0" cellpadding="0" align="center">
	<tr class="textRadio">
		<td width="3%">&nbsp;</td>
		<td width="97%">&nbsp;</td>
	</tr>	
	<tr class="textRadio">
	<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="71">
		<td> <input type="radio" name="accion" value="1" id="accion" onclick="cambioDiv(1)"> </td>			 
		<td> Asignar </td>

	</securityCa:validaPerfil>
	</tr>
	<tr class="textRadio">
	<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="72">
		<td> <input type="radio" name="accion" value="2" id="accion" onclick="cambioDiv(2)"> </td> 
		<td> Eliminar </td>
	</securityCa:validaPerfil>
	</tr>
	<tr class="textRadio">
	<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="73">
		<td> <input type="radio" name="accion" value="3" id="accion" onclick="cambioDiv(3)"> </td> 
		<td> Puntos por Antigüedad </td>
	</securityCa:validaPerfil>
	</tr>
	<tr class="textRadio">
	<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="168">
		<td> <input type="radio" name="accion" value="4" id="accion" onclick="cambioDiv(4)"> </td> 
		<td> Asignación de Puntos por Cancelación de Cuenta Errónea</td>
	</securityCa:validaPerfil>
	</tr>
	<tr class="textRadio">
	<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="168">
		<td> <input type="radio" name="accion" value="4" id="accion" onclick="cambioDiv(4)"> </td> 
		<td> Asignación por Migración de Prepago Errónea</td>
	</securityCa:validaPerfil>
	</tr>	
</table>
</DIV>
</form>
<div id="divAsignar" style="position:static;top: 50px;height: 265px;MARGIN-TOP: 0px; OVERFLOW-Y: hidden; OVERFLOW-X: hidden;visibility: hidden;display: none;">
<form id="frmAsignar" name="frmAsignar"  >
<br>
<table width="90%" border="1" cellspacing="0" cellpadding="0" align="center">
	<tr>
   				<td width="15%" class="healineblue1" bgcolor="#C0E5F8"  align="left" >&nbsp;Nombre</td>
				<td width="45%" class="textonegroBlod" align="left" id="Nombre">&nbsp;${nombre}</td>
				<td width="20%" class="healineblue1" bgcolor="#C0E5F8" align="left">&nbsp;Teléfono</td>
				<td width="18%" class="textonegroBlod" id="ptsDisponibles" >&nbsp;${telefono}</td>	                    	            
	</tr> 
	<tr> 
   				<td class="healineblue1" bgcolor="#C0E5F8" align="left">&nbsp;Segmento</td>              
   				<c:if test="${segmento=='Azul'}">
   	  			<td class="textonegroBlod" >&nbsp;
      				<span style="width: 20px;background-color: #00BFFF">
          				${segmento}
        			</span>
      			</td>                                            
     			</c:if> 
     			<c:if test="${segmento=='Oro'}">
      			<td class="textonegroBlod"  >&nbsp;
      				<span style="width: 20px;background-color: #FEE000">
             			${segmento}
        			</span>
       			</td>
      			</c:if>
      			<c:if test="${segmento=='Platino'}">
        		<td class="textonegroBlod"   >&nbsp;
          			<span style="width: 20px;background-color: #CCCCCC">
             			${segmento}
          			</span>
         		</td>
       			</c:if>                                       
          		<td class="healineblue1" bgcolor="#C0E5F8" align="left">&nbsp;Cuenta</td>
          		<td class="textonegroBlod" id="cuenta">&nbsp;${cuenta}</td> 
      		</tr>
</table>
<br> 
<br>  	
<table width="90%" border="0" cellspacing="0" cellpadding="0" align="center">    	
	<tr>
		<td width="15%" class="healineblue1" valign="middle" height="20" >Cantidad de Puntos:</td>
		<td width="2%"></td>					
		<td width="43%"><input class="InputText" name="cantidadpts" id="cantidadpts"  maxlength="10" onkeypress="validaCampo(this,'numerico');" value=0> </td> 			
	</tr>
	<!-- MÓDULO ASIGNACIÓN DE PUNTOS - JAPA 08/02/2013 Folio 121733 --> 
	<tr>
		<td width="15%" class="healineblue1" valign="middle" height="20" >Motivo de Asignación:</td>
		<td width="2%"></td>					
		<td width="43%">
			<select name="idMotivoAsig" id="idMotivoAsig" class="textonegroBlod" style="width:280px">
				<option value="-1">Seleccione</option>
				<c:forEach var="motivoTO" items="${motivosLst}" >
					<option value="${motivoTO.idMotivo}">${motivoTO.descripcion}</option>	
		    	</c:forEach>
	     	</select>    
		</td> 			
	</tr>
	<tr>
		<td width="15%" class="healineblue1" valign="middle" height="20"> Comentario: </td>
		<td width="2%"></td>			
		<td width="43%" ><input width="20%" class="InputTextC" name="comentario" id="comentario" maxlength="60" onkeypress="validaCampo(this,'alfanumerico');" > </td>			
	</tr>	
	<tr>
		<td width="15%"></td>
		<td width="2%"></td>
		<td width="43%" class="healinered" valign="middle" height="20" >&nbsp;</td>
	</tr>
	<tr height="20"></tr>
	<tr>
		<td>&nbsp</td>
		<td>&nbsp</td>
		<td>
			<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="96">
				<input class="buttonC" type="button"  id="buttonC" name="Continuar" value="  Continuar  " onClick="frmAsignar_submit();"><br>&nbsp;
			</securityCa:validaPerfil>				     			
		</td>
	</tr>		
</table>
</form>
</div>	
<div id="divAsignarAntiguedad" style="position: static;top:50px;height: 265px;MARGIN-TOP: 0px; OVERFLOW-Y: hidden; OVERFLOW-X: hidden;visibility: hidden;display: none;">
	<form id="frmAsignarAntiguedad" name="frmAsignarAntiguedad" method="post">
	<br>	
		<table width="90%" border="1" cellspacing="0" cellpadding="0" align="center">
	        <tr>
	            <td width="15%" class="healineblue1" bgcolor="#C0E5F8"  align="left" >&nbsp;Nombre</td>
	            <td width="45%" class="textonegroBlod" align="left" id="Nombre">&nbsp;${nombre}</td>
	            <td width="20%" class="healineblue1" bgcolor="#C0E5F8" align="left">&nbsp;Puntos Disponibles</td>
	            <td width="18%" class="textonegroBlod" id="ptsDisponibles" >&nbsp;${ptsDisponibles}</td>	                    	            
	        </tr>
	        <tr> 
              	<td class="healineblue1" bgcolor="#C0E5F8" align="left">&nbsp;Teléfono</td>
              	<td class="textonegroBlod" align="left" id="telefono">&nbsp;${telefono}</td>
              	<td class="healineblue1" bgcolor="#C0E5F8" align="left">&nbsp;Estatus Puntos</td> 
              	<td  class="textonegroBlod" >&nbsp;<font color="red">${estatusPuntos}</font></td>
          	</tr>
            <tr> 
              <td class="healineblue1" bgcolor="#C0E5F8" align="left">&nbsp;Cuenta</td>
              <td class="textonegroBlod" id="cuenta">&nbsp;${cuenta}</td>
              <td class="healineblue1" bgcolor="#C0E5F8" align="left">&nbsp;Fecha de Alta M2K</td> 
              <td  class="textonegroBlod" >&nbsp;${fechaAlta}</td> 
          	</tr>
            <tr> 
              <td class="healineblue1" bgcolor="#C0E5F8" align="left">&nbsp;Región</td>
              <td class="textonegroBlod" >&nbsp;${region}</td> 
              <td colspan="2"  class="healineblue1" align="left">&nbsp;</td>              
          	</tr>         
        </table>	
		<br>
		<table width="90%" border="0" cellspacing="0" cellpadding="0" align="center">
			<tr>
				<td width="20%" class="healineblue1" valign="middle" height="20"> Puntos a asignar: </td>
				<td colspan=2 height="20" valign="middle">
				<input class="InputText" type="text" name="ptsAsignar" size=24 value="CÁLCULO PENDIENTE" disabled />
				</td>	
			</tr>
			<tr>
				<td class="healineblue1" width="20%" valign="middle" height="20">Región origen:</td>	
				<td colspan=2 height="20" valign="middle"> 
              	<select class="selectC name="regionAnt" id="regionAnt" size=1>
			  		<option value="1">R01</option>
			  		<option value="2">R02</option>
			  		<option value="3">R03</option>				
			  		<option value="4">R04</option>			
			  		<option value="5">R05</option>													
			  		<option value="6">R06</option>												
			  		<option value="7">R07</option>
			  		<option value="8">R08</option>
			  		<option value="9">R09</option>								
			  	</select>
   		   		</td>
   	 		</tr>
			<tr>
				<td class="healineblue1" width="20%"  valign="middle" height="20">Cuenta origen: </td>
				<td colspan=2 height="20" valign="middle"> 
					<input class="InputText" type="text" id="ctaAnt" name="ctaAnt" maxlength="10" size=11 onkeypress="validaCampo(this,'numerico');"/>
				</td>			
			</tr>
			<tr>
				<td class="healineblue1" width="22%"  valign="middle" height="20">Comentario: </td>
				<td><input class="InputTextC" type="text" id="comentarioAnt" name="comentarioAnt" maxlength="68" size=65 onkeypress="validaCampo(this,'alfanumerico');"/></td>
			</tr>
			<tr height="20"></tr>
			<tr>
				<td> </td>
				<td colspan="2">
					<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="98">
						<input class="buttonC" type="button"  id="buttonC" name="Continuar" value="  Continuar  " onClick="frmAsignar_ptsAntiguedad();"><br>&nbsp;
					</securityCa:validaPerfil>			
				</td>
			</tr>			
		</table>
	</form>
</div>

<div id="divEliminar" style="top: 50px;height: 265px;position: static;MARGIN-TOP: 0px; OVERFLOW-Y: hidden; OVERFLOW-X: hidden;visibility: hidden;display: none;">
	<form id="frmEliminar" name="frmEliminar" method="post" >
	<br>
	<table width="90%" border="1" cellspacing="0" cellpadding="0" align="center">
 			<tr>
       			<td width="15%" class="healineblue1" bgcolor="#C0E5F8"  align="left" >&nbsp;Nombre</td>
	   			<td width="45%" class="textonegroBlod" align="left" id="Nombre">&nbsp;${nombre}</td>
	   			<td width="20%" class="healineblue1" bgcolor="#C0E5F8" align="left">&nbsp;Teléfono</td>
	   			<td width="18%" class="textonegroBlod" id="ptsDisponibles" >&nbsp;${telefono}</td>	                    	            
 			</tr> 
 			<tr> 
       			<td class="healineblue1" bgcolor="#C0E5F8" align="left">&nbsp;Segmento</td>              
       			<c:if test="${segmento=='Azul'}">
       	  		<td class="textonegroBlod" >&nbsp;
          			<span style="width: 20px;background-color: #00BFFF">
              			${segmento}
            		</span>
          		</td>                                            
         		</c:if> 
         		<c:if test="${segmento=='Oro'}">
          		<td class="textonegroBlod"  >&nbsp;
          			<span style="width: 20px;background-color: #FEE000">
                			${segmento}
            		</span>
           		</td>
          		</c:if>
          		<c:if test="${segmento=='Platino'}">
            	<td class="textonegroBlod"   >&nbsp;
              		<span style="width: 20px;background-color: #CCCCCC">
                		${segmento}
              		</span>
             	</td>
           		</c:if>                                       
              	<td class="healineblue1" bgcolor="#C0E5F8" align="left">&nbsp;Cuenta</td>
              	<td class="textonegroBlod" id="cuenta">&nbsp;${cuenta}</td> 
          	</tr>
 		</table>
 		<br> 	
		<br> 
		<table width="90%" border="0" cellspacing="0" cellpadding="0" align="center">
			<tr>
				<td width="15%" class="healineblue1" valign="middle" height="20" >Cantidad de Puntos:</td>
				<td width="2%"></td>	
				<td width="43%" valign="middle" height="20"><input class="InputText" name="cantidadptsEli" id="cantidadptsEli"  maxlength="10" onkeypress="validaCampo(this,'numerico');" value=0> </td>			
			</tr>
			<tr>
				<td width="15%" class="healineblue1" valign="middle" height="20"> Comentario: </td>
				<td width="2%"></td>
				<td width="43%" valign="middle" height="20"><input class="InputTextC" name="comentarioEli" id="comentarioEli" maxlength="70" onkeypress="validaCampo(this,'alfanumerico');"  size="55"> </td>			
			</tr>	
			<tr>
				<td width="15%"></td>
				<td width="2%"></td>
				<td width="43%" class="healinered" valign="middle" height="20">&nbsp;</td>
			</tr>
			<tr height="20"></tr>
			<tr>
				<td> &nbsp </td>
				<td> &nbsp </td>
				<td>
					<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="97">
						<input class="buttonC" type="button"  id="buttonC" name="Continuar" value="  Continuar  " onClick="frmEliminar_submit();"><br>&nbsp;
					</securityCa:validaPerfil>			
				</td>
			</tr>
		</table>
	</form>
</div>	
<div id="divaisgnaCancePorError" style="position: static;top:50px;height: 265px;MARGIN-TOP: 0px; OVERFLOW-Y: hidden; OVERFLOW-X: hidden;visibility: hidden;display: none;">
	<form id="frmAsignarporError" name="frmAsignarporError" method="post" >
		<br>
			<table width="90%" border="1" cellspacing="0" cellpadding="0" align="center">
 				<tr>
       				<td width="15%" class="healineblue1" bgcolor="#C0E5F8"  align="left" >&nbsp;Nombre</td>
	   				<td width="45%" class="textonegroBlod" align="left" id="Nombre">&nbsp;${nombre}</td>
	   				<td width="20%" class="healineblue1" bgcolor="#C0E5F8" align="left">&nbsp;Teléfono</td>
	   				<td width="18%" class="textonegroBlod" id="ptsDisponibles" >&nbsp;${telefono}</td>	                    	            
 				</tr> 
 				<tr> 
       				<td class="healineblue1" bgcolor="#C0E5F8" align="left">&nbsp;Segmento</td>              
       				<c:if test="${segmento=='Azul'}">
       	  			<td class="textonegroBlod" >&nbsp;
          				<span style="width: 20px;background-color: #00BFFF">
              				${segmento}
            			</span>
          			</td>                                            
         			</c:if> 
         			<c:if test="${segmento=='Oro'}">
          			<td class="textonegroBlod"  >&nbsp;
          				<span style="width: 20px;background-color: #FEE000">
                 			${segmento}
            			</span>
           			</td>
          			</c:if>
          			<c:if test="${segmento=='Platino'}">
            		<td class="textonegroBlod"   >&nbsp;
              			<span style="width: 20px;background-color: #CCCCCC">
                 			${segmento}
              			</span>
             		</td>
           			</c:if>                                       
              		<td class="healineblue1" bgcolor="#C0E5F8" align="left">&nbsp;Cuenta</td>
              		<td class="textonegroBlod" id="cuenta">&nbsp;${cuenta}</td> 
          		</tr>
 			</table>
 			<br> 
 			<br>  	
			<table width="90%" border="0" cellspacing="0" cellpadding="0" align="center">    	
				<tr>
					<td width="15%" class="healineblue1" valign="middle" height="20" >Cantidad de Puntos:</td>
					<td width="2%"></td>					
					<td width="43%"><input class="InputText" name="cantidadptsErr" id="cantidadptsErr"  value=${asignaPorErrorTO.puntos} disabled="disabled"> </td> 			
				</tr>
				<tr>
					<c:if test="${asignaPorErrorTO.SM=='SINMOVIMIENTO'}">
					<td width="15%" class="healineblue1" valign="middle" height="20" >Fecha de operacion:</td>
					<td width="2%"></td>
					<td width="43%"><input class="InputText" name="fechaop" id="fechaop"  value=${asignaPorErrorTO.SM} disabled="disabled"> </td>
					<table border="0" width="100%" align="center" class="BloqueErrorEspera" style="background-color: transparent;">
					<tr height="20"></tr>
					<tr align="center">	<td valign="middle" width="100%">Ha ocurrido un Error</td></tr>
					<tr align="center">	<td valign="middle" height="32" width="100%" bgcolor="#ECF0DB">La línea no tiene un movimiento (EPBAJ) ELIMINA PTO POR BAJA CUENTA.</td></tr>
					</table>	
					</c:if>	
					<c:if test="${asignaPorErrorTO.SM=='CONMOV'}">
					<td width="15%" class="healineblue1" valign="middle" height="20" >Fecha de operacion:</td>
					<td width="2%"></td>
					<td width="43%"><input class="InputText" name="fechaop" id="fechaop"  value=${asignaPorErrorTO.fechaOperacion} disabled="disabled"> </td>
					</c:if>				 			
				</tr>
				<tr height="20"></tr>
				
				<tr>
				    <c:if test="${asignaPorErrorTO.SM=='SINMOVIMIENTO'}">
					</c:if>
					<c:if test="${asignaPorErrorTO.SM=='CONMOV'}">
					<td>&nbsp</td>
					<td>&nbsp</td>
					<td>
						<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="96">
							<input class="buttonC" type="button"  id="buttonC" name="Continuar" value="  Continuar  " onClick="return frmAsignarporError_submit();">
						</securityCa:validaPerfil>		
					</td>
					</c:if>
				</tr>	     				
			</table>
		</form>
	</div>	
</body>
</html>