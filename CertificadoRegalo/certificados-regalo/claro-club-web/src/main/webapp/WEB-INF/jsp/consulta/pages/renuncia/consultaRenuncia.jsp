<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/permisos.tld" prefix="securityCa" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Consulta Renuncia</title>
<link rel="stylesheet" type="text/css" href='<c:url value="/commons/css/impromptus.css"/>' media='screen' >
<link rel="stylesheet" href='<c:url value="/commons/js/sc_textsheet.css"/>' type="text/css">
<script type="text/javascript" src='<c:url value="/commons/js/jquery-1.2.3.pack.js"/>'></script>
<script type='text/javascript' src='<c:url value="/commons/js/jquery.form.js"/>' ></script>
<script type="text/javascript" src='<c:url value="/commons/js/jquery-impromptu.1.3.js"/>' ></script>
<script type="text/javascript" src='<c:url value="/commons/js/puntos.js"/>' ></script>
</head>
<body marginwidth="0" marginheight="0" style="MARGIN: 0px;background-color: transparent;">
<script>	
    document.parentWindow.parent.activaCargando('hidden','none');     
</script>
<form id="frmconsulta" name="frmconsulta" action="">
      	<div style="height: 350px;top: 10px;position: absolute;padding-left: 50px;" >
            <table width="100%"  cellspacing="0" cellpadding="0" align="center"  >
                <tr > 
                    <td colspan="3" class="titulo" height="42" width="100%" >&nbsp;&nbsp;Renuncia de Puntos</td>
                </tr>               
                <tr> 
                    <td width="15%" class="healineblue1" align="left" height="20"> 
                        <input type="radio" name="operacion" id="operacion1" checked="checked" onclick="seleccionaOpcion('telefono')">Teléfono 
                    </td>
                    <td colspan="2" width="85%" > 
                        <input type="text" id="telefono" name="telefono" maxlength="10" onKeyDown="if(event.keyCode==13)consultaDetalle();" onfocus="seleccionaOpcion('telefono');" class="InputB">                        
                    </td>                  
                </tr>
                <tr> 
                    <td width="15%" class="healineblue1" align="left" height="20">
                    	<input type="radio" name="operacion"  id="operacion2" onclick="seleccionaOpcion('cuenta')">Cuenta 
                    </td>
                    <td class="textonegroBlodTrs" width="35%" height="20" align="left"> 
                        <input type="text" id="cuenta" name="cuenta" maxlength="10" onKeyDown="if(event.keyCode==13) consultaDetalle();" onfocus="seleccionaOpcion('cuenta');" class="InputB">
                    </td>
                    <td align="center" width="50%"> 
                    <a class="LinkOut" style="width:100px" onmouseover='this.className="LinkIn";' 
                    	onmouseout='this.className="LinkOut";' id="Link1" onClick="consultaDetalle();">&nbsp;Continuar&nbsp;&nbsp;</a>
                    </td>
                </tr>
                <tr>                     
                    <td class="healineblue1" align="left" height="20">&nbsp;&nbsp;&nbsp;&nbsp;Región</td>
                    <td colspan="2"> <input type="text" id="region" name="region" value="9" readonly="readonly" class="InputB"></td>
                </tr> 
                <tr>
                    <td class="healineblue1" height="20">&nbsp;&nbsp;&nbsp;&nbsp;Acción</td>
                    <td colspan="2">
                    <select name="accion" id="accion" onKeyDown="if(event.keyCode==13) document.getElementById('Link1').click()" class="InputB">                        
                        <option></option>
						<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="30">
							<option value="congelar">Constancia de Renuncia</option>
						</securityCa:validaPerfil>
						<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="29">
							<option value="reactivar">Reactivar Puntos</option>
						</securityCa:validaPerfil>                      
                    </select>
                    </td>
               </tr>
                </table>
         </div>
         </form>     
</body>
</html>