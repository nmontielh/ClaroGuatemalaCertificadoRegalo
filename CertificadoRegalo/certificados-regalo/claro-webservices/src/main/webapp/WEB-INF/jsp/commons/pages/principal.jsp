<%-- 
    Document   : principal
    Created on : 6/03/2008, 11:39:38 AM
    Author     : vibx958
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Circulo Azul</title>
    </head>
<FRAMESET rows="39,37,*" frameborder="NO" border="0" framespacing="0">
  <FRAMESET cols="50%,50%" frameborder="NO" border="1" framespacing="0" rows="*">
    <FRAME name="upperFrame1" src='<c:url value="/commons/pages/anuncio.jsp"/>' scrolling ="no" noresize="noresize">
  </FRAMESET>
  
 <FRAME name="menuFrame" src='<c:url value="/commons/pages/menu.html"/>' scrolling ="no" noresize="noresize">
  
  <FRAMESET cols="125,*" frameborder="NO" border="1" framespacing="0" rows="*">
    <FRAME name="leftFrame" src='<c:url value="/commons/pages/blankleft.jsp"/>' scrolling ="no" noresize="noresize" >
	<FRAME name="mainFrame"  src='<c:url value="/Consulta/pages/puntos.jsp"/>' scrolling ="yes">
  </FRAMESET>
</FRAMESET>
<noframes>
<body bgcolor="#FFFFFF" text="#000000">
</body>
</noframes>
</html>
