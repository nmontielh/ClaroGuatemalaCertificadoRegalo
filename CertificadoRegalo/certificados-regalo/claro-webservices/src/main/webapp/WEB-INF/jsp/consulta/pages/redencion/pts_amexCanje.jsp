<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<html>
	<head>
		<title>Puntos Telcel</title>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
		
		<link rel="stylesheet" href='<c:url value="/commons/js/sc_textsheet.css"/>' type="text/css">
		<script language="javascript" type="text/javascript">
		<!--
			//American Express
		
		function canje(){
			var comentario = frmMexicana.comentario.value;			
			if(trim(comentario) == ""){
				window.alert("Debe ingresar un comentario para poder hacer el canje de puntos")
				return false;
		 	}
		}
		
		function trim(str) {
			while(str.charAt(str.length - 1) == " ")
				str = str.substring(0,str.length - 1);
			while(str.charAt(0) == " ")
				str = str.substring(1,str.length);
			return str;
		}
						
		// -->
		</script>
	</head>
	
	<body bgcolor="#ffffff" marginwidth="0" marginheight="0" style="background-color: transparent;MARGIN-TOP: 0px; 
			OVERFLOW-Y: hidden; OVERFLOW-X: hidden;WIDTH:926px; HEIGHT: 378px;position: absolute;top: 0px;visibility: visible;display: block;">
		
		<form name="frmMexicana" id="frmMexicana">
			<table width="98%" border="0" cellspacing="0" cellpadding="0" align="center">
                <tr>                  
                   <td colspan="2" class="textonegroBlodTrs">&nbsp;</td>                 
                </tr>
                <tr>                  
                   <td colspan="2" class="textonegroBlodTrs">&nbsp;</td>                 
                </tr>
                <tr>                  
                <td class="textonegroBlodTrs" width="28%"><font color='red'>Paquetes de Viaje</font></td>                 
                <td class="textonegroBlodTrs" width="72%">&nbsp;<font color=red>Se completará valor del certificado en PUNTOS exclusivamente</font></td>
                </tr>
                <tr>                   
                <td class="textonegroBlodTrs" width="28%">&nbsp;</td>                  
                <td class="textonegroBlodTrs" width="72%">&nbsp;</td>
                </tr>
              </table> 			  
              <table width="800" border="1" cellspacing="0" cellpadding="0" align="center" bordercolor="#cccccc">
                <tr bgcolor="#eff0f1"> 
				  <td width="60%" class="healineblue1"  height="13">
                    <div align="center">Descripción</div>
                  </td>                  
                  <td width="20%" class="healineblue1" height="13">
                    <div align="center">Valor que ampara el certificado</div>
                  </td>
                  <td width="20%" class="healineblue1"  height="13">
                    <div align="center">Puntos a canjear</div>
                  </td>                  
                </tr>
                <c:forEach items="${alianzasTipoTO.productosArray}" var="producto">
                	<tr> 
                  		<td  class="textonegroBlod">
                  			<input type="radio" name="idViaje" value="${producto.material}">
                  				<c:if test="${producto.descripcion==null || producto.descripcion==''}">Viaje Amex</c:if>
                  				<c:if test="${producto.descripcion!=null || producto.descripcion!=''}">
                  					<c:out value="${producto.descripcion}"></c:out>                  					
                  				</c:if>
                  		</td>
                  		<td class="textonegroBlod">
                  			<div align="right">$<c:out value="${producto.precioActivacion}"></c:out></div>
                  		</td>
				  		<td  class="textonegroBlod">
                    		<div align="right">
                    			<c:out value="${producto.valorPuntos}"></c:out>
                    		</div>
                    	</td>
                	</tr>
                </c:forEach>
                
              </table>
			
			<table width="98%" border="0" cellspacing="0" cellpadding="0" align="center">
				<tr>
					<td colspan="2" class="textonegroBlodTrs">&nbsp;</td>
        		</tr>
        		<tr> 
        			<td colspan="2" class="textonegroBlodTrs">&nbsp;</td>
        		</tr>
        		<tr>
        			<td width="20%" class="textonegroBlodTrs">Comentario:</td>
        			<td><input id="comentario" name="comentario" maxlength="100" size=80 style="TEXT-TRANSFORM:UPPERCASE"></td>
        		</tr>
        		<tr>
        			<td class="textonegroBlodTrs">&nbsp;</td>
        			<td class="textonegroBlodTrs"><div align="left"><font color="red">&nbsp;* No capturar más de 70 caracteres</font></div></td>
        		</tr>
        		<tr>
        			<td class="textonegroBlodTrs">&nbsp;</td>
        			<td class="textonegroBlodTrs">&nbsp;</td>
        		</tr>
     		</table>
			<input type="button" name="btnAceptar" value="Canjear" onclick="canje()">
			
			<input type="hidden" name="telefono" value="${telefono}">
			<input type="hidden" name="cuenta" value="${cuenta}">
			<input type="hidden" name="region" value="${region}">
			<input type="hidden" name="secuencia" value="${secuencia}">
			<input type="hidden" name="millas" value="${millas}">
			<input type="hidden" name="ptsDisponibles" value="${ptsDisponibles}">
			<input type="hidden" name="factor" value="${factor}">
			<input type="hidden" name="millaMin" value="${millaMin}">
		</form>	
			
			
	</body>
</html>

