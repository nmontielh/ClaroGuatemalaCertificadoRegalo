<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<html>
<head>
<title>Error</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href='<c:url value="/commons/js/sc_textsheet.css"/>' type="text/css">
</head>

<body style="background-color: white" marginwidth="0" marginheight="0" style="MARGIN: 0px" class="body">

<script>			
	if(document.parentWindow.parent.activaCargando!=null)
		document.parentWindow.parent.activaCargando('hidden','none');		
</script>

<center>	
	<div align="left" style="position: absolute; top: 2%; left: 10px; background-color: transparent; " AllowTransparency >		
		
		<table border="0" width="100%" align="center" class="BloqueErrorEspera" style="background-color: transparent;">
			<tr align="center">	<td valign="middle" width="100%">Ha ocurrido un Error</td></tr>
			<tr align="center">	<td valign="middle" height="32" width="100%" bgcolor="#ECF0DB">${ex}</td></tr>	
		</table>	
	</div>	
</center> 
	

</body>
</html>