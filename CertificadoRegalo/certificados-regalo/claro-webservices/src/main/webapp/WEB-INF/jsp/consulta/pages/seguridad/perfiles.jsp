<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/permisos.tld" prefix="securityCa"%>
<html>
<head>
<script>
			
</script>
<title>Perfiles</title>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
    <link rel="stylesheet" href='<c:url value="/commons/js/sc_textsheet.css"/>' type="text/css">
    
    <script type="text/javascript">
    
    	function consultaPrivilegios(){
    		var idPerfil = document.getElementById("idPerfil");
    		FramePrivilegios.location = './privilegiosPerfiles.do?idPerfil=' + idPerfil.value;
    	}
    	function exportar(opcion){
    		var form = document.getElementById("frmPerfiles");
    		var idPerfil = document.getElementById("idPerfil");
    		var descripcion = document.getElementById("perfilDescripcion");
    		
    		if(opcion==1){
    			form.action = './jsp-content/Perfiles.pdf?idPerfil=' + idPerfil.value + "&perfilDescripcion=" + descripcion.value;
    			form.submit();
    		}
    		if(opcion==2){
    			FramePrivilegios.location = './reportePerfiles.do?idPerfil=' + idPerfil.value + "&perfilDescripcion=" + descripcion.value;
    		}    		    		
    	}
    	function getDescripcion(){
    		var opcion = document.getElementById("idPerfil");
    		var descripcion = document.getElementById("perfilDescripcion");
    		descripcion.value = document.getElementById("opt" + opcion.value).text;    		    	
    	}
    
    </script>
        
</head>

<body  background="<c:url value='/commons/images/backgroundlight.gif'/>">
	 
	 <form id="frmPerfiles" name="frmPerfiles" method="post" target="Frame1">     
      	<div>
      	
      		<table width="98%">
				<tr>
					<td height="45" class="titulo">Reporte de Perfiles</td>
				</tr>
			</table>
			<DIV style="width: 98%; FILTER: progid:DXImageTransform.Microsoft.Shadow(color='#aaaacc', Direction=135, Strength=2); 
				border-bottom-width: 2px; border-bottom-style: solid; border-bottom-color: #4d7097; font-size: 1px;">
			</DIV>
			<p></p>
      	            
             <table width="100%"  cellspacing="0" cellpadding="0" align="left">
                <tr> 
                    <td class="healineblue1" width="67">&nbsp;Perfil:&nbsp;</td>
                    <td class="healineblue1" align="left" width="2004">
                    	<select name="idPerfil" id="idPerfil" class="InputB" style="text-transform: uppercase; width:90%" onchange="getDescripcion();">
                    		<option value=""  selected ></option>
      						<c:forEach var="perfil" items="${perfil}">
         						<option id="opt${perfil.idVariable}" value="${perfil.idVariable}">${perfil.valor}&nbsp;-&nbsp;${perfil.descripcion}&nbsp;</option>
      						</c:forEach>
    					</select>
                    </td>
                    <td class="healineblue1" width="88">
                    	<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="58">
	                		<a class="LinkOut" style="width:100px" onmouseover='this.className="LinkIn";' onmouseout='this.className="LinkOut";'
	                          id="Link1" onClick="consultaPrivilegios();">&nbsp;Consultar&nbsp;&nbsp;</a>
	                    </securityCa:validaPerfil>
                	</td>
                	<!-- 
                	
                	<td class="healineblue1" width="100">
                		<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="59">
                			<a class="LinkOut" style="width:100px; visibility: hidden;" onmouseover='this.className="LinkIn";' onmouseout='this.className="LinkOut";'
                          		id="btnExportPdf" onClick="exportar(1);">&nbsp;Exportar PDF&nbsp;&nbsp;</a>
                		</securityCa:validaPerfil>                                          		
                	</td>
                	
                	 -->
                	<td class="healineblue1" width="556">
                		<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="150">
                			<a class="LinkOut" style="width:100px; visibility: hidden;" onmouseover='this.className="LinkIn";' onmouseout='this.className="LinkOut";'
                          		id="btnExportExcel" onClick="exportar(2);">&nbsp;Exportar Excel&nbsp;&nbsp;</a>
                		</securityCa:validaPerfil>                                          		
                	</td>
                </tr>
              </table>              
         </div>
         <DIV id="TarjetaPrivilegios" class="TarjetaDetalle" style="top:140px;height: 300px;width: 98%;visibility: visible;display: block;left: 10px;position:absolute;BORDER:solid 2px #4d7097;background: transparent;">
			<IFRAME name="FramePrivilegios" id="FramePrivilegios" scrolling="yes" WIDTH="100%" HEIGHT="100%" style="border: hidden;background: transparent;" frameborder="0"></IFRAME>
		</DIV>
		<input type="hidden" id="perfilDescripcion" name="perfilDescripcion">
    </form>         
      
</body>
</html>