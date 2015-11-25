<%@ page import="java.text.DecimalFormat" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<%	String sFecVenc = request.getParameter("fecha").toString();
	sFecVenc = sFecVenc.substring(0,4)+"/"+sFecVenc.substring(5,7)+"/"+sFecVenc.substring(8,10);	
	String sPtsVenc = request.getParameter("puntos");
	DecimalFormat formatea = new DecimalFormat("###,###,###,##0");
	sPtsVenc = formatea.format(Long.parseLong(sPtsVenc));
%>

<html>
<head>
<title>Puntos a vencer</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href='<c:url value="/commons/js/sc_textsheet.css"/>' type="text/css">

<SCRIPT language="JavaScript" type="text/javascript">
  function NewWindow(link) {
  	var instructivo = window.open(link, 'manual_redencion', 'location=no,width=630,height=280,status,toolbar=yes,scrollbars=yes,resizable,menubar=no');
	instructivo.focus();
  }
</SCRIPT>

</head>
<body style="background-color: white" marginwidth="0" marginheight="0" style="MARGIN: 0px" class="body">
	<BASEFONT face="trebuchet,ms sans serif" size="+2" color="#6680CC" />
	<P><strong>Estimado usuario;</strong></P>
	<P>Te pedimos recordar al cliente que tiene <strong><%=sPtsVenc%></strong>
	 puntos por<br />vencer el día <strong><%=sFecVenc%></strong></P>	
	 <P>Las opciones para canje de puntos son las siguientes:</P>
	
	<ul>
		<li>Puntos Disponibles:
			<ul><br/>
				<li><a href="javascript:NewWindow('redencion.html');">Equipos de Amigo Kit</a></li>				
				<li><a href="javascript:NewWindow('redencion.html');">Viajes con American Express</a></li>
				<li><a href="javascript:NewWindow('redencion.html');">Paquetes de Tiempo Aire</a></li>
				<li><a href="javascript:NewWindow('redencion.html');">Activaciones de Amigo</a></li>
			</ul>
		</li>
	</ul>
</body>
</html>
