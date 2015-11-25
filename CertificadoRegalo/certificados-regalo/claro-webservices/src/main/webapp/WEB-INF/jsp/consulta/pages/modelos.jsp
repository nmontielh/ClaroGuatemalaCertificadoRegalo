<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Consulta de Modelos</title>
<link rel="stylesheet" href='<c:url value="/commons/js/sc_textsheet.css"/>' type="text/css">
<script type="text/javascript">	
function radio_onclick(r){
	var modelo= r.value;
	window.returnValue=modelo;
  	window.close();
}
</script>
</head>

<body class="body" bgcolor="#ffffff">
<table border="0" cellpadding="1" cellspacing="0">
	<tr>
		<td valign="top" width="740" align="center"><br>
			<table width="98%" border="0" cellspacing="0" cellpadding="0" align="center">
				<tr><td class="titulo" height="42" align="left">&nbsp;&nbsp;Seleccione un Modelo</td></tr>
				<tr><td>&nbsp;</td></tr>	
			</table>
			
			<DIV id="modelos" style="visibility: visible;display: block;">			
				<table width="260" border="0" cellspacing="0" cellpadding="0" align="center">
					<tr bgcolor="#ECF0DB" class="healineblue1">
						<td align="center">Modelos</td>
					</tr>					
				</table>
				
				<DIV style="overflow:auto; WIDTH: 260px; POSITION: relative; HEIGHT: 200px">
				<table width="98%" border="1" cellspacing="0" cellpadding="0" align="center">
					<tr>
						<td class="textonegroBlod" width='15' valign="middle" style="WIDTH: 3px" align="left">
							<input type='radio' name="modelo" value='TODOS'	onclick="radio_onclick(this);"></td>
						<td class="textonegroBlod" width='250' valign="middle" align="left">TODOS</td>
					</tr>	
					<c:set var="contador" value="0"/>
		    		<c:forEach var="catalogoTO" items="${catalogoTO}" varStatus="total">
			        	<c:set var="contador" value="${total.count}"/>   
				            <tr class="X3" bgcolor="<c:if test="${contador %2 !=0 }">#D9EBF2</c:if>">
			    	            <td class="textonegroBlod" valign="middle" width='15' align="left">
			        	        <input type="radio" name="modelo" value="${catalogoTO.descripcion}" onclick="radio_onclick(this);"></td>
			            	    <td class="textonegroBlod" valign="middle" width='250' align="left">${catalogoTO.descripcion}</td>
			            	</tr>
	       			</c:forEach>
				</table>			
			</DIV>
		</DIV>	
		</td>
	</tr>
</table>
</body>
</html>

