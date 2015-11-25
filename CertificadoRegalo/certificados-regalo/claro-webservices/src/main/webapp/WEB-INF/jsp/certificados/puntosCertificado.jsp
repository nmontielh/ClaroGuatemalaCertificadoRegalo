<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/permisos.tld" prefix="securityCa" %>
<html>
    <head>
        <title>Puntos Certificados</title>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
        <link rel="stylesheet" href='<c:url value="/commons/js/sc_textsheet.css"/>' type="text/css">                
        <script type="text/javascript">
        	function consultaDetalle(){        		      
        			if(frmconsulta.tarjeta.value==""){
        				window.alert("Debe Capturar un Número de Tarjeta");
        				return false;
        			}if(isNaN(frmconsulta.tarjeta.value) || frmconsulta.tarjeta.value.length != 16){
        					window.alert ("El Número de Certificado no es valido");
							frmconsulta.tarjeta.value="";
							return false;
					}  	        		        	
        		var url = "./consultaDetalleCertificado.do?tarjeta="+document.getElementById("tarjeta").value;        		        		
        		document.parentWindow.parent.setConsultaSubmenus(2,url);        		
        	}     	
        </script>
    </head>
    
    <body  marginwidth="0" marginheight="0" style="MARGIN: 0px;background-color: transparent;" >
    	<script>
    		document.parentWindow.parent.activaCargando('hidden','none');    		
    	</script>
       <form id="frmconsulta" name="frmconsulta">
      	<div style="height: 350px;top: 10px;position: absolute;padding-left: 50px;" >
            <table width="100%"  cellspacing="0" cellpadding="0" align="center">
                <tr> 
                    <td colspan="3" class="titulo" height="42" width="100%" >&nbsp;&nbsp;Consulta de Certificados</td>
                </tr>               
                <tr> 
                    <td width="15%" class="healineblue1" align="left" height="20"> 
                        Número de Tarjeta
                    </td>
                    <td width="30%" class="textonegroBlodTrs" height="20" align="left"> 
                        <input type="text" id="tarjeta" name="tarjeta" maxlength="16" onKeyDown="if(event.keyCode==13)consultaDetalle();">                        
                    </td>
					<td width="" height="20">
	                    
	                    	<a class="LinkOut" style="width:100px" onmouseover='this.className="LinkIn";' onmouseout='this.className="LinkOut";'
	                    		id="Link1" onClick="consultaDetalle();">&nbsp;Continuar&nbsp;&nbsp;</a>
	                                                                                                                       
                    </td>                   
                </tr>
			</table>
         </div>
         </form>                         
    </body>
</html>
