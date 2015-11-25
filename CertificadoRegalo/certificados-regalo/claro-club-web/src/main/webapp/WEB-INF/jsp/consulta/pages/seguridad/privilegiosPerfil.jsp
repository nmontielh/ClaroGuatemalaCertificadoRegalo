<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"><%@page
	language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<html>
<head>
<title>privilegiosPerfil</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

	
	<link rel="stylesheet" href="/ClaroClubWeb/commons/js/sc_textsheet.css" style="text/css">
	<script type="text/javascript">
		function obtieneTotalRegistros(){
			var rowCount = document.getElementById("count");
			var rowCount2 = document.getElementById("count2");
			
			var btnExportarPdf = document.parentWindow.parent.document.getElementById('btnExportPdf');
			if(btnExportarPdf!=null){
				if(rowCount.value!=0 || rowCount2.value!=0){
					btnExportarPdf.style.visibility = "visible";
				} else{
					btnExportarPdf.style.visibility = "hidden";
				}
			} 					
			var btnExportarExcel = document.parentWindow.parent.document.getElementById('btnExportExcel');
			if(btnExportarExcel!=null){
				if(rowCount.value!=0 || rowCount2.value!=0){
					btnExportarExcel.style.visibility = "visible";
				} else{
					btnExportarExcel.style.visibility = "hidden";
				}
			}
		}
	</script>

</head>
<body onload="obtieneTotalRegistros();" background="<c:url value='/commons/images/backgroundlight.gif'/>">


	<DIV id="divMovimiento" style="MARGIN-TOP: 0px; OVERFLOW-Y: scroll; OVERFLOW-X: hidden; BORDER:solid 1px silver; width:98%; HEIGHT: 90%;position: absolute;top: 10px;"> 	   	                    
       <table>
       	<tr>
       		<td class="subtitulo">Privilegios Círculo Azul</td>       		
       	</tr>
       	<tr>
       		<td valign="top">
       			<table border="1" cellspacing="0" cellpadding="0" align="left" >       
		              <tr bgcolor="#ECF0DB" class="healineblue1" align="center"> 
		                  <th width="300">Nombre</th>
		                  <th width="750">Descripción</th>
		                  <th width="25">Tipo</th>
		              </tr>
		              <c:set var="counter" value="0"/>
		              	<c:forEach var="privilegioCa" items="${privilegiosCa}" varStatus="total">
		              		<c:set var="counter" value="${total.count}"/> 
		              		<tr class="X3" bgcolor="<c:if test="${counter %2 !=0 }">#D9EBF2</c:if>">
		                 		<td width="300" align="left">&nbsp;${privilegioCa.value.nombre}</td>
		                 		<td width="750" align="left">&nbsp;${privilegioCa.value.descripcion}</td>
		                 		<td width="25" align="left">&nbsp;${privilegioCa.value.tipo}</td>
		              	</tr>
		              </c:forEach>
		              <tr bgcolor="#ECF0DB" class="healineblue1" >
		       			
		       			<td colspan="8">&nbsp;<c:out value="${fn:length(privilegiosCa) }"></c:out> Registro(s) Encontrado(s).</td>
		       			
		       		  </tr>	
       			 </table>       			 
       			 <input type="hidden" id="count" value="<c:out value="${fn:length(privilegiosCa) }"></c:out>">
       		</td>       		
       	</tr>
       </table>
      </DIV>
	
</body>
</html>