<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt'%>     
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix='f' uri="http://java.sun.com/jstl/fmt_rt"%>
<%@ taglib prefix="spring" uri="/spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Manuales y Promociones</title>
<link rel="stylesheet" type="text/css" href='<c:url value="/commons/css/impromptus.css"/>' media='screen' >
        
<script type="text/javascript" src='<c:url value="/commons/js/jquery-1.2.3.pack.js"/>'></script>
<script type='text/javascript' src='<c:url value="/commons/js/jquery.form.js"/>' ></script>
<script type="text/javascript" src='<c:url value="/commons/js/jquery-impromptu.1.3.js"/>' ></script>
<script type="text/javascript" src='<c:url value="/commons/js/puntitos.js"/>' ></script>
</head>
<body marginwidth="0" marginheight="0" style="MARGIN: 0px;background-color: transparent;">
   	<script>
   		document.parentWindow.parent.activaCargando('hidden','none');    		
   	</script>    	
<div id="Manuales">
    <table>
		<tr><td class="titulo">Manuales y Procedimientos</td></tr>
	</table>
<table id="tablaManuales" border="0" cellspacing="0" cellpadding="0" width="90%">
  <c:set var="count" value="0" />
  <c:forEach  var="documentoTO" items="${listaManualesTO}">
  	<c:if test="${count==0}"><c:out value="<tr id='linea' class='healineblue1' align='left'>" escapeXml="false"></c:out></c:if>
	  <td style="text-transform: uppercase; width:50%"  valign="middle">
	    <img src='<c:url value="/commons/images/load.gif"/>'/><a href="${ruta}${documentoTO.nombre}">${documentoTO.descripcion}</a>
	  </td> 
	<c:choose><c:when test="${count==1}"><c:out value="</tr>" escapeXml="false"></c:out><c:set var="count" value="0" /></c:when>
	<c:otherwise><c:set var="count" value="1" /></c:otherwise></c:choose>
  </c:forEach>
  <c:if test="${count==0}"><td></td><c:out value="</tr>" escapeXml="false"></c:out></c:if>
</table>
</div>
<div id="Promociones">
    <table>
    	<tr><td class="titulo" height="50pts">&nbsp;</td></tr>
		<tr><td class="titulo">Promociones</td></tr>
	</table>
<table id="tablaLista" border="0" cellspacing="0" cellpadding="0" width="90%">
  <c:set var="count" value="0" />
  <c:forEach  var="documentoTO" items="${listaPromocionesTO}">
  	<c:if test="${count==0}"><c:out value="<tr id='linea' class='healineblue1' align='left'>" escapeXml="false"></c:out></c:if>
	  <td style="text-transform: uppercase; width:50%" valign="middle">
	    <img src='<c:url value="/commons/images/load.gif"/>'/><a href="${ruta}${documentoTO.nombre}">${documentoTO.descripcion}</a>
	  </td> 
	<c:choose><c:when test="${count==1}"><c:out value="</tr>" escapeXml="false"></c:out><c:set var="count" value="0" /></c:when>
	<c:otherwise><c:set var="count" value="1" /></c:otherwise></c:choose>
  </c:forEach>
  <c:if test="${count==0}"><td></td><c:out value="</tr>" escapeXml="false"></c:out></c:if>
	</table>
</div>
<div id="Logo">
    <table border="0" cellspacing="0" cellpadding="0" width="90%">
		<tr><td align="right"><img src='<c:url value="/commons/images/logo_circulo_azul_1.gif"/>'/></td></tr>
	</table>
</div>
</body>
</html>