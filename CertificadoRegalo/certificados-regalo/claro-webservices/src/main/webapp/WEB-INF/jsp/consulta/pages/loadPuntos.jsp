<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<title>Load Puntos</title>
<script >
	function loadPuntos(){				
		<c:if test="${vista=='realizaCanje'}">
			document.parentWindow.parent.document.parentWindow.document.parentWindow.parent.cargaDatos();
		</c:if>
		<c:if test="${vista=='cancelaCanje'}">
			document.parentWindow.parent.document.parentWindow.document.parentWindow.parent.document.parentWindow.parent.cargaDatos()
		</c:if>	
		<c:if test="${vista=='aplicaRedencion'}">		
			document.parentWindow.parent.document.parentWindow.parent.cargaDatos();
		</c:if>
		<c:if test="${vista=='cancelaRedencion'}">
			document.parentWindow.parent.document.parentWindow.parent.cargaDatos();
		</c:if>	
		<c:if test="${vista=='asignacion'}">
			document.parentWindow.parent.cargaDatos();
		</c:if>						
	}
</script>
</head>

<body onload="loadPuntos()">
</body>
</html>