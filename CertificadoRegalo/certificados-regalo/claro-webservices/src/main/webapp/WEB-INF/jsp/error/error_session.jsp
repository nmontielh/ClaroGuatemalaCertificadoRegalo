<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href='<c:url value="/commons/js/sc_textsheet.css"/>' type="text/css">
<title>Puntos Telcel</title>
</head>
<body style="background-color: white" marginwidth="0" marginheight="0"
	style="MARGIN: 0px" class="body">
<script>			
	if(document.parentWindow.parent.activaCargando!=null)
		document.parentWindow.parent.activaCargando('hidden','none'); 
</script>

<center>
<div align="left" style="position: absolute; top: 2%; left: 10px">
<table border="0">   
 <tr><td colspan="1" class="titulo" height="42" width="100%">Consulta de Puntos</td></tr>	
</table>
</div>

<div align="center" style="position: absolute; top: 30%; left: 4%">
<table border="0" width="98%" align="center" class="BloqueErrorEspera">
	<tr align="center">
		<td valign="middle" height="42" width="100%" bgcolor="#ECF0DB">
		La sesión ha terminado.</td>
	</tr>
</table>
</div>

<div>
<p align="right" style="position: absolute; left: 700px; top: 520px;">
<IMG height=63
	src='<c:url value="/commons/images/logo_circulo_azul.gif"/>' width=197></p>
</div>

</center>
</body>
</html>