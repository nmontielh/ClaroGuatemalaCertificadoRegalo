<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/permisos.tld" prefix="securityCa" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Puntitos: Consulta Usuario</title>
		<link rel="stylesheet" type="text/css" href='<c:url value="/commons/css/impromptus.css"/>' media='screen' >
		<link rel="stylesheet" type="text/css" href='<c:url value="/commons/js/sc_textsheet.css"/>'>
		
        
		<script type="text/javascript" src='<c:url value="/commons/js/jquery-1.2.3.pack.js"/>'></script>
		<script type='text/javascript' src='<c:url value="/commons/js/jquery.form.js"/>' ></script>
		<script type="text/javascript" src='<c:url value="/commons/js/jquery-impromptu.1.3.js"/>' ></script>
		<script type="text/javascript" src='<c:url value="/commons/js/puntitos.js"/>' ></script>
		<script type="text/javascript">
			function buscar(){
				
				var numEmpleado = document.getElementById("numEmpleado");
				if(numEmpleado==null||numEmpleado.value==""){
					alert("Introduzca un número de empleado.");
					return false;
				}
				var form = document.getElementById("formBuscarusuario");
				form.action="./buscaUsuario.do";
				form.submit();			
			}
		</script>
		
</head>
<body>

<form action="./buscaUsuario.do" method="post" name="formBuscarusuario" id="formBuscarusuario" onSubmit="return validaConsulta()" AUTOCOMPLETE="OFF">

	<table width="100%"  cellspacing="0" cellpadding="0" align="center"  >
		<tr>
			<td	class="subtitulo">Usuario</td>
		</tr>
	</table>
	<DIV style="width: 100%; FILTER: progid:DXImageTransform.Microsoft.Shadow(color='#aaaacc', Direction=135, Strength=2); 
		border-bottom-width: 2px; border-bottom-style: solid; border-bottom-color: #4d7097; font-size: 1px;">
	</DIV>
    <table width="100%"  cellspacing="0" cellpadding="0" align="center"  >
         <tr><td class="healineblue1" width="20%">&nbsp;N&uacute;mero de Empleado&nbsp;</td>
			<td width="15%">
			   <input type="text" id="numEmpleado" name="numEmpleado" style="text-transform: uppercase; width:95%" maxlength="10" onblur="this.value=this.value.toUpperCase()">
			</td>
			<td>
			   <securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="40">
			   		<a class="LinkOut" style="width:30; text-align:center" onmouseover='this.className="LinkIn";'
						onmouseout='this.className="LinkOut";' id="btnBuscar" onClick="buscar();">&nbsp;Buscar&nbsp;&nbsp;</a>
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