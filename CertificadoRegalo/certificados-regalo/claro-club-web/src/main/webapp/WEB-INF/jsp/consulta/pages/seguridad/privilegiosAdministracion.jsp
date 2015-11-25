<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/permisos.tld" prefix="securityCa"%>

<html>
<head>
<script>
			
</script>
<title>Privilegios</title>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
    <link rel="stylesheet" href='<c:url value="/commons/js/sc_textsheet.css"/>' type="text/css">
    
    <script type="text/javascript">
    
    	function privilegiosDetalle(){
    		var idPerfil = document.getElementById("idPerfil");
    		if(idPerfil==null || idPerfil.value==""){
    			window.alert("Debe elegir un perfil.");
    			return false;
    		}
    		FramePrivilegios.location = './privilegiosDetalle.do?idPerfil=' + idPerfil.value;    		
    	}

    	function getDescripcion(){
    		var opcion = document.getElementById("idPerfil");
    		var descripcion = document.getElementById("perfilDescripcion");
    		descripcion.value = document.getElementById("opt" + opcion.value).text;    		    	
    	}
    	function actualizaPrivilegiosDetalle(){
    		var idPerfil = document.getElementById("idPerfil");
    		var privilegiosPerfil = document.getElementById("privilegiosPerfil");
    		FramePrivilegios.location = './actualizaPrivilegios.do?idPerfil=' + idPerfil.value + '&privilegiosPerfil=' + privilegiosPerfil.value;
    	}
    
    </script>
        
</head>

<body  background="<c:url value='/commons/images/backgroundlight.gif'/>">
	 
	 <form id="frmPerfiles" name="frmPerfiles" method="post" target="Frame1">     
      	<div>
      	
      		<table width="98%">
				<tr>
					<td height="45" class="titulo">Administración de Perfiles</td>
				</tr>
			</table>
			<DIV style="width: 98%; FILTER: progid:DXImageTransform.Microsoft.Shadow(color='#aaaacc', Direction=135, Strength=2); 
				border-bottom-width: 2px; border-bottom-style: solid; border-bottom-color: #4d7097; font-size: 1px;">
			</DIV>
			<p></p>
      	            
             <table width="100%"  cellspacing="0" cellpadding="0" align="left">
             	<tr>
             		<td colspan="4">
             			<input type="hidden" id="privilegiosPerfil" name="privilegiosPerfil" size="170">
             		</td>
             	</tr>
                <tr> 
                    <td class="healineblue1" width="53">&nbsp;Perfil:&nbsp;</td>
                    <td class="healineblue1" align="left" width="2000">
                    	<select name="idPerfil" id="idPerfil" class="InputB" style="text-transform: uppercase; width:90%" onchange="getDescripcion();">
                    		<option value=""  selected ></option>
      						<c:forEach var="perfil" items="${perfil}">
         						<option id="opt${perfil.idVariable}" value="${perfil.idVariable}">${perfil.valor}&nbsp;-&nbsp;${perfil.descripcion}&nbsp;</option>
      						</c:forEach>
    					</select>
                    </td>
                    <td class="healineblue1" width="70">
                    	<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="151">
	                    	<a class="LinkOut" style="width:100px" onmouseover='this.className="LinkIn";' onmouseout='this.className="LinkOut";'
		                         id="Link1" onClick="privilegiosDetalle();">&nbsp;Consultar&nbsp;&nbsp;</a>
		                </securityCa:validaPerfil>
                	</td>
                	<td width="706">
                		<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="152">
	                		<a class="LinkOut" style="width:100px;visibility: hidden;" onmouseover='this.className="LinkIn";' onmouseout='this.className="LinkOut";'
		                         id="btnActualizar" onClick="actualizaPrivilegiosDetalle();">&nbsp;Actualizar&nbsp;&nbsp;</a>
		                </securityCa:validaPerfil>
                	</td>
                </tr>
              </table>              
         </div>
         <DIV id="TarjetaPrivilegios" class="TarjetaDetalle" style="top:140px;height: 300px;width: 98%;visibility: visible;display: block;left: 10px;position:absolute;BORDER:solid 2px #4d7097;background: transparent;">
			<IFRAME name="FramePrivilegios" id="FramePrivilegios" scrolling="yes" WIDTH="100%" HEIGHT="100%" style="border: hidden;background: transparent;" frameborder="0"></IFRAME>
		</DIV>
		
		<input type="hidden" id="perfilDescripcion" name="perfilDescripcion">
		<input type="hidden" id="privilegiosPerfil" name="privilegiosPerfil">
    </form>         
      
</body>
</html>