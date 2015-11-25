<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ciudades</title>
<link rel="stylesheet" href='<c:url value="/commons/js/sc_textsheet.css"/>' type="text/css">
<script type="text/javascript">

	function ciudades(r){
		window.returnValue=r;
	  	window.close();
	}	
</script>
</head>
<body marginwidth="0" marginheight="0" style="MARGIN: 0px" class="body">

<table width="100%">
	  <tr>             
          <td class="healineblue1" bgcolor="#ECF0DB" align="left">Ciudades</td>
		</tr>      
</table>

	<c:if test="${!empty estadosTO}">
		<div id="divCiudades" style="height:130px;OVERFLOW-Y: scroll; OVERFLOW-X: hidden">
		<table width="100%">
		 <c:set var="contador" value="0"/>
		<c:forEach var="estadoTO" items="${estadosTO}" varStatus="total">	
		<c:set var="contador" value="${total.count}"/>  	
		<tr class="X3" bgcolor="<c:if test="${contador %2 !=0 }">#D9EBF2</c:if>">
		
			<td>
				<input type="radio" name=ciudad onclick="ciudades('${estadoTO.ciudad}|${estadoTO.costo}|${estadoTO.opcion}')">&nbsp;${estadoTO.ciudad}
			</td>
		</tr>	
		</c:forEach>
		</table>
		</div>
	</c:if>	
	<table width="100%">	
	<tr bgcolor="#ECF0DB" class="healineblue1">
    		<td colspan="2">&nbsp;<c:out value="${contador}"></c:out> Registro(s) Encontrado(s).</td>
   	</tr> 
</table>	
</body>
</html>