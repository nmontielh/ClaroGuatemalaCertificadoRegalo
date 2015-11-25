<%@page contentType="text/html" pageEncoding="ISO-8859-1" language="java"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<!-- ver: 2.1.1 -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Puntos Telcel</title>
<link rel="stylesheet" href='<c:url value="/commons/js/sc_textsheet.css"/>' type="text/css">
<script type="text/javascript">
        	function consultaDetalle(cuenta){        		        	
        		if(confirm("¿Desea consultar la cuenta:"+cuenta)){        		
        			var url = "./consultaDetallePuntos.do?cuenta="+cuenta+"&region=${region}";
        			document.parentWindow.parent.setConsultaSubmenus(2,url);        		
        		}
        	}        	
        </script>
</head>

<body class="body">	
		<script>
    		document.parentWindow.parent.activaCargando('hidden','none'); 
    	</script>
    	
		<table width="98%" border="0" cellspacing="0" cellpadding="0" align="center">
            <tr><td class="textonegroBlodTrs">&nbsp;&nbsp;Consulta de Puntos</td></tr>
            <tr><td height="5"></td></tr>
        </table>        
        <div style="OVERFLOW-Y: scroll; OVERFLOW-X: hidden; HEIGHT: 155px;">
	        <table width="100%" border="1" cellspacing="0" cellpadding="0" align="center">
	           <thead>     
	           <tr bgcolor="#ECF0DB" class="healineblue1"  align="center"> 
	               <td width="10%">Cuentas Hijas </td>               
	           </tr>             			  
	           </thead>
	           
	           <tbody>
	           <c:forEach var="cuentaPadreTO" items="${cuentasHijas}" >           
		           <tr class="X3">
		           	<td onclick="consultaDetalle(${cuentaPadreTO.cuenta});" style="cursor: pointer;">${cuentaPadreTO.cuenta}</td>
		           </tr>           
	           </c:forEach>                                                       
	           </tbody>
	       </table>       
       </div>                           
       </body>
</html>
       
   
       
