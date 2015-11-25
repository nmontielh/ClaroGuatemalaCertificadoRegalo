<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/permisos.tld" prefix="securityCa" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Circulo Azul</title>
		<link rel="stylesheet" type="text/css" href='<c:url value="/commons/css/impromptus.css"/>' media='screen' >
		<link rel="stylesheet" type="text/css" href='<c:url value="/commons/js/sc_textsheet.css"/>'>
		
        
		<script type="text/javascript" src='<c:url value="/commons/js/jquery-1.2.3.pack.js"/>'></script>
		<script type='text/javascript' src='<c:url value="/commons/js/jquery.form.js"/>' ></script>
		<script type="text/javascript" src='<c:url value="/commons/js/jquery-impromptu.1.3.js"/>' ></script>
		
		<script type="text/javascript">
			
			function procesaLineas(){
			
				var form = document.getElementById("formLineasRestringidas");
				
				var archivo = document.getElementById("archivo");
				if(archivo==null || archivo.value==""){
					alert("Seleccione un archivo");
					return false;
				}			
				var extencion = archivo.value.substring(archivo.value.length-3, archivo.value.length).toUpperCase();
		
				if(extencion != "CSV"){
					alert("Formato de archivo no valido, solo se aceptan archivos CSV");
					return false;
				}
				
				form.action="./cargaLineasRestringidas.do";
				form.submit();
			}
			
			function exportaLineas(){
			var form = document.getElementById("formLineasRestringidas");
			form.action="./reporteLineasRestringidas.do";
				form.submit();
			}
			
		</script>
		
</head>
<body>

<form action="cargaLineasRestringidas.do" method="post" name="formLineasRestringidas" id="formLineasRestringidas" enctype="multipart/form-data">

	<table width="100%"  cellspacing="0" cellpadding="0" align="center"  >
		<tr>
			<td	class="subtitulo">Lineas Restringidas</td>
		</tr>
	</table>
	<DIV style="width: 100%; FILTER: progid:DXImageTransform.Microsoft.Shadow(color='#aaaacc', Direction=135, Strength=2); 
		border-bottom-width: 2px; border-bottom-style: solid; border-bottom-color: #4d7097; font-size: 1px;">
	</DIV>
    <table width="100%"  cellspacing="0" cellpadding="0" align="center"  >
         <tr>
         	<td class="healineblue1" width="20%">&nbsp;Archivo:&nbsp;</td>
			<td width="39%">
			   <input type="file" name="archivo" id="archivo" style="width:95%"/>
			</td>
			<td width="483">			
		</tr>
		<tr>
         	<td class="healineblue1" width="20%"></td>
			<td width="39%" align="right">
			<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="173">	
			 	<a class="LinkOut" style="width:30; text-align:center" onmouseover='this.className="LinkIn";'
						onmouseout='this.className="LinkOut";' id="btnArchivo" onClick="procesaLineas();">&nbsp;PROCESAR&nbsp;&nbsp;</a>  
						</securityCa:validaPerfil>	
			</td>
			<td width="483" align="left">
				<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="186">	
					<a class="LinkOut" style="width:30; text-align:center" onmouseover='this.className="LinkIn";'
						onmouseout='this.className="LinkOut";' id="btnArchivo" onClick="exportaLineas();">&nbsp;DESCARGAR&nbsp;&nbsp;</a>
				</securityCa:validaPerfil>	
			</td>	
		</tr>
	</table>
	<DIV style="width: 100%; FILTER: progid:DXImageTransform.Microsoft.Shadow(color='#aaaacc', Direction=135, Strength=2); 
		border-bottom-width: 2px; border-bottom-style: solid; border-bottom-color: #4d7097; font-size: 1px;">
	</DIV>  
</form>
</body>
</html>