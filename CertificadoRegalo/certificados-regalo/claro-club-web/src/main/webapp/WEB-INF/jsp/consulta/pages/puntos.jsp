	<%-- 
    Document   : puntos
    Created on : 6/03/2008, 06:16:29 PM
    Author     : vibx958
--%>

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
        		}
        		        		        		  	        		        	
        		var url = "./consultaDetallePuntos.do?telefono="+document.getElementById("telefono").value+"&cuenta="+document.getElementById("cuenta").value;        		        		
        		document.parentWindow.parent.setConsultaSubmenus(2,url);        		
        	}
        	function seleccionaOpcion(_opcion){
        		if(_opcion=="cuenta")        		
        			frmconsulta.operacion[1].checked=true;
        		else if(_opcion=="telefono")
        			frmconsulta.operacion[0].checked=true;
        	}        	
        </script>
    </head>
    
    <body  marginwidth="0" marginheight="0" style="MARGIN: 0px;background-color: transparent;" >
    	<script>
    		document.parentWindow.parent.activaCargando('hidden','none');    		
    	</script>
       <form id="frmconsulta" name="frmconsulta">
      	<div style="height: 350px;top: 10px;position: absolute;padding-left: 50px;" >
            <table width="100%"  cellspacing="0" cellpadding="0" align="center"  >
                <tr > 
                    <td colspan="3" class="titulo" height="42" width="100%" >&nbsp;&nbsp;Consulta de Puntos</td>
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
                        <input type="text" id="cuenta" name="cuenta" maxlength="10" onKeyDown="if(event.keyCode==13) consultaDetalle();" onfocus="seleccionaOpcion('cuenta');">
                    </td>
                    <td align="center" width="50%">
                    <securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="20">
                    	<a class="LinkOut" style="width:100px" onmouseover='this.className="LinkIn";' onmouseout='this.className="LinkOut";'
                    		id="Link1" onClick="consultaDetalle();">&nbsp;Continuar&nbsp;&nbsp;</a>
                    </securityCa:validaPerfil>                                                                                                   
                    </td>                                           
                </tr>
                </table>
         </div>
         </form>                         
    </body>
</html>
