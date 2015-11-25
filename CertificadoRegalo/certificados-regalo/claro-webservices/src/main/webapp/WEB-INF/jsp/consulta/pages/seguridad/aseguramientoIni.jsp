<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/permisos.tld" prefix="securityCa" %>
<html>
<head>
<script>
		function act_tabla(){
			document.getElementById("tabla").style.visibility="visible";
			document.getElementById("tabla").style.display="block";
		}

		function validaDatos(exp){
			document.getElementById("exporta").value = exp;
			var Link2 = document.getElementById("Link2");
			
			if(Link2!=null){
				if(exp ==0){
					document.getElementById("Link2").style.visibility="visible";
					document.getElementById("Link2").style.display="block";
				}else{
					document.getElementById("Link2").style.visibility="hidden";
					document.getElementById("Link2").style.display="none";
				}
			}
			
			if(isNaN(frmSeguridad.max.value)){
				alert ("El dato de registros debe ser numerico");
				frmconsulta.max.value="";
				return false;
        	}
	
			var numEmpleado = document.getElementById("numEmpleado").value;
			var clave = document.getElementById("clave").value;
			var perfil_ = document.getElementById("perfil_").value;
			var opcEstatus = document.getElementById("opcEstatus").value;
			
			act_tabla();
			
			frmSeguridad.action="./aseguramientoIni.do?modalidad="+document.getElementById("modalidad").value+
			"&numEmpleado="+document.getElementById("numEmpleado").value+"&clave="+document.getElementById("clave").value+
			"&perfil_="+document.getElementById("perfil_").value+"&opcEstatus="+document.getElementById("opcEstatus").value+
			"&max="+document.getElementById("max").value+"&exporta="+document.getElementById("exporta").value;
			
			frmSeguridad.submit();
	}
	
</script>
<title>Seguridad</title>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
    <link rel="stylesheet" href='<c:url value="/commons/js/sc_textsheet.css"/>' type="text/css">
    <link rel="stylesheet" href='<c:url value="/commons/css/calendar-blue.css"/>' type="text/css">
    <script src='<c:url value="/commons/js/calendar.js"/>' type="text/javascript"></script>
    <script src='<c:url value="/commons/js/calendar-es.js"/>' type="text/javascript"></script>
    <script src='<c:url value="/commons/js/calendar-setup.js"/>' type="text/javascript"></script>
</head>

<body  background="<c:url value='/commons/images/backgroundlight.gif'/>">
	 
	 <form id="frmSeguridad" name="frmSeguridad" method="get" target="Frame1">
     <input type="hidden" name="modalidad"  id="modalidad" value="1">
     <input type="hidden" name="exporta"  id="exporta" value="0">
     <input type="hidden" name="flag"  id="flag" value="0">
      	<div>
      	
      		<table width="98%">
				<tr>
					<td height="45" class="titulo">Reporte de Usuarios</td>
				</tr>
			</table>
			<DIV style="width: 98%; FILTER: progid:DXImageTransform.Microsoft.Shadow(color='#aaaacc', Direction=135, Strength=2); 
				border-bottom-width: 2px; border-bottom-style: solid; border-bottom-color: #4d7097; font-size: 1px;">
			</DIV>
			<p></p>
      	            
             <table width="100%"  cellspacing="0" cellpadding="0" align="left">
                <tr> 
                    <td class="healineblue1" width="165">&nbsp;Numero de Empleado:&nbsp;</td>
                    <td class="healineblue1" align="left" width="327"><input type="text" name="numEmpleado" id="numEmpleado" maxlength="7" /></td>
                    <td class="healineblue1" width="139">&nbsp;Clave del Usuario:&nbsp;</td>
                    <td class="healineblue1" align="left" width="587"><input type="text" name="clave" id="clave" maxlength="7" /></td>
                </tr>
                <tr> 
                    <td class="healineblue1" width="165">&nbsp;Perfil:&nbsp;</td>
                    <td class="healineblue1" align="left" width="327">
                    	<select name="perfil_" id="perfil_" class="InputB" style="text-transform: uppercase; width:90%">
                    		<option value=""  selected ></option>
      						<c:forEach var="perfil" items="${perfil}">
         					<option value="${perfil.valor}">${perfil.valor}&nbsp;-&nbsp;${perfil.descripcion}&nbsp;</option>
      						</c:forEach>
    					</select>
                    </td>
                    <td class="healineblue1" width="139">&nbsp;Estatus:&nbsp;</td>
                    <td class="healineblue1" align="left" width="587">
                    	<select id="opcEstatus" name="opcEstatus" >                        
                        	<option value="I"  selected >I  (Inactivo)</option>
                        	<option value="A"  selected >A  (Activo)</option>
                        	<option value=""  selected ></option>
                        </select>
                    </td>           
                </tr>
                <tr>
                    <td class="healineblue1" width="165">&nbsp;Numero de registros:&nbsp;</td>
                	<td  class="healineblue1" width="327"><input type="text" name="max" id="max" maxlength="5" value="10" /></td>
                	<td  class="healineblue1" width="139">&nbsp;&nbsp;</td>
                	<td  class="healineblue1" width="587">&nbsp;&nbsp;</td>
                </tr>
                <tr align="center">
                	<td align="center" width="165"> 
                	</td>
					 	
                   	<td align="center" colspan="2">
                   		<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="56">
	                   		<a class="LinkOut" style="width:100px" onmouseover='this.className="LinkIn";' onmouseout='this.className="LinkOut";'
	                          id="Link1" onClick="validaDatos(0);">&nbsp;Consultar&nbsp;&nbsp;</a>
	                    </securityCa:validaPerfil>
                    </td>
                    <td align="center" width="587">
                    	<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="57">
	                    	<a class="LinkOut" style="width:100px; visibility: hidden;" onmouseover='this.className="LinkIn";' onmouseout='this.className="LinkOut";'
	                          id="Link2" onClick="validaDatos(1);">&nbsp;Exportar&nbsp;&nbsp;</a> 
	                    </securityCa:validaPerfil>
                	</td> 	
                </tr>
                <tr>
                <td width="165">
	                <DIV id="tabla"  style=" position: fixed;top: 190px;height: 280px;width:90%; background-attachment:fixed; BORDER:solid 1px silver; visibility: hidden" >
						<IFRAME name="Frame1" id="Frame1" scrolling="no" WIDTH="100%" HEIGHT="100%" style="border: hidden;" frameborder="0" AllowTransparency ></IFRAME>
	         		</DIV>
                </td>
                </tr>
             </table>
         </div>
    </form>         
      
</body>
</html>