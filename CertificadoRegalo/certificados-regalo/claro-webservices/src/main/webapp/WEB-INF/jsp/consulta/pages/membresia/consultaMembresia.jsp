<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/permisos.tld" prefix="securityCa" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Consulta Membresia</title>
<link rel="stylesheet" href='<c:url value="/commons/js/sc_textsheet.css"/>' type="text/css">
<script type="text/javascript">
        	function consultaDetalle(){        		      
        		if(frmconsulta.operacion[0].checked==true){
        			if(frmconsulta.telefono.value==""){
        				window.alert("Debe Capturar un Teléfono");
        				return false;
        			}if(isNaN(frmconsulta.telefono.value) || frmconsulta.telefono.value.length != 10){
        					window.alert ("El telefono no es valido");
							frmconsulta.telefono.value="";
							return false;
					}
        		}else if(frmconsulta.operacion[1].checked==true){
        			if(frmconsulta.cuenta.value==""){
	        			window.alert("Debe Capturar una cuenta.");
        				return false;
        			}if(isNaN(frmconsulta.cuenta.value)){
	        			window.alert("La cuenta no es valida");
	        			frmconsulta.cuenta.value="";
        				return false;
        			}
        		}else if(frmconsulta.region.value==0){
        			window.alert("Debe Seleccionar la región del Teléfono ó Cuenta.");
        			return false;
        		}
        		        		        		  	        		        	
        		var url = "./consultaPtsMembresia.do?telefono="+document.getElementById("telefono").value+"&cuenta="+document.getElementById("cuenta").value+"&region="+document.getElementById("region").value;        		
        		document.parentWindow.parent.setConsultaSubmenus(5,url);        		
        	}
        	function seleccionaOpcion(_opcion){
        		if(_opcion=="cuenta")        		
        			frmconsulta.operacion[1].checked=true;
        		else if(_opcion=="telefono")
        			frmconsulta.operacion[0].checked=true;
        	}		
        </script>
</head>
<body marginwidth="0" marginheight="0" style="MARGIN: 0px;background-color: transparent;">
<script>	
    document.parentWindow.parent.activaCargando('hidden','none');     
</script>
<form id="frmconsulta" name="frmconsulta" action="">
      	<div style="height: 350px;top: 10px;position: absolute;padding-left: 50px;" >
            <table width="100%"  cellspacing="0" cellpadding="0" align="center"  >
                <tr > 
                    <td colspan="3" class="titulo" height="42" width="100%" >&nbsp;&nbsp;Reposición de tarjeta</td>
                </tr>               
                <tr> 
                    <td width="15%" class="healineblue1" align="left" height="20"> 
                        <input type="radio" name="operacion" value="0" checked>Teléfono 
                    </td>
                    <td class="textonegroBlodTrs" colspan="2" width="85%" height="20" align="left"> 
                        <input type="text" id="telefono" name="telefono" maxlength="10" onKeyDown="if(event.keyCode==13)consultaDetalle();" onfocus="seleccionaOpcion('telefono');">                        
                    </td>                  
                </tr>
                <tr> 
                    <td width="15%" class="healineblue1" align="left" height="20">
                    	<input type="radio" name="operacion" value="1">Cuenta 
                    </td>
                    <td class="textonegroBlodTrs" width="35%" height="20" align="left"> 
                        <input type="text" id="cuenta" name="cuenta" maxlength="10"  LANGUAGE=javascript onKeyDown="if(event.keyCode==13) consultaDetalle();" onfocus="seleccionaOpcion('cuenta');">
                    </td>
                    <td align="center" width="50%"> 
                    	<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="26">
                    		<a class="LinkOut" style="width:100px" onmouseover='this.className="LinkIn";' onmouseout='this.className="LinkOut";'
                                                           id="Link1" onClick="consultaDetalle();">&nbsp;Continuar&nbsp;&nbsp;</a>
                    	</securityCa:validaPerfil> 
                    </td>                                           
                </tr>
                <tr>                     
                    <td  class="healineblue1" align="left" height="20">&nbsp;&nbsp;&nbsp;&nbsp;Región</td>
                    
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
         </div>
         </form>     
</body>
</html>