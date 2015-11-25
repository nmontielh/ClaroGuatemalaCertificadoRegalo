<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Consulta de Productos</title>
<link rel="stylesheet" href='<c:url value="/commons/js/sc_textsheet.css"/>' type="text/css">
<script type="text/javascript">

	function returnProductos(r){
	  	var producto = r.value.split(",");	  	
  		var alerta = producto[14];
  		var costo=producto[5];  		
		if (alerta=="1"){	
			if(costo="0.00"){
				window.alert("Usuario acreedor a Paquete de SMS");				
			}	
	  	}	  	
	  	window.returnValue=r.value;
	  	window.close();
	}
</script>
</head>

<body bgcolor="#ffffff" marginwidth="0" marginheight="0" style="MARGIN: 0px" class="body">
<table border="0" cellpadding="1" cellspacing="0" width="98%">
<tr><td>
	<table width="98%" border="0" cellspacing="0" cellpadding="0" align="center" >
   		<tr> 
    		<td class="healineblue1" valign="middle" height="20"><font color="red" size="3">&gt;Seleccione un Producto</font></td>    		
    	</tr>   		
	</table>
</td></tr>
<tr><td>
	<table width='98%' border="1" cellspacing="0" cellpadding="0" align="center" >
			<tr bgcolor="#eff0f1" class="healineblue1" align="center">
				<td class="healineblue1" bgcolor="#eff0f1" width="40%" align="center">Descripci&oacute;n</td>
				<td class="healineblue1" bgcolor="#eff0f1" width="20%" align="center">Marca</td>
				<td class="healineblue1" bgcolor="#eff0f1" width="20%" align="center">Modelo</td>			
				<td class="healineblue1" bgcolor="#eff0f1" width="20%" align="center">Puntos</td>
				<td class="healineblue1" bgcolor="#eff0f1" width="50%" align="center">Precio</td>
				<td class="healineblue1" bgcolor="#eff0f1" width="35%" align="center">Descuento Inbursa</td>
				<td class="healineblue1" bgcolor="#eff0f1" width="35%" align="center">Descuento Marca</td>
				
				<c:choose>
					<c:when test="${productosTO[0].bonosAltoValor != 0}">
						<td class="healineblue1" bgcolor="#eff0f1" width="35%" align="center">Descuento Alto Valor</td>
					</c:when>
					<c:when test="${productosTO[0].bonosRoext != 0}">
						<td class="healineblue1" bgcolor="#eff0f1" width="35%" align="center">Descuento Roext</td>
					</c:when>
					<c:when test="${productosTO[0].bonosGap != 0}">
						<td class="healineblue1" bgcolor="#eff0f1" width="35%" align="center">Desc. Promoci&oacute;n</td>
					</c:when>
					<c:otherwise>
						<td class="healineblue1" bgcolor="#eff0f1" width="35%" align="center">Descuento</td>
					</c:otherwise>
				</c:choose>
				
				<td class="healineblue1" bgcolor="#eff0f1" width="50%" align="center">&nbsp;Precio IVA &nbsp;</td>
				<td class="healineblue1" bgcolor="#eff0f1" width="25%" align="center">URL</td>
			</tr>
			 <c:set var="contador" value="0"/>
	            <c:forEach var="productosTO" items="${productosTO}" varStatus="total">
	            <c:set var="contador" value="${total.count}"/>
	            <tr class="X3" bgcolor="<c:if test="${contador %2 !=0 }">#D9EBF2</c:if>">                
	                <td class="textonegroBlod" width="35%">
	                <input type="radio" id="idProd" name="idProd" value='${productosTO.material},${productosTO.descripcion},${productosTO.marca},${productosTO.modelo},${productosTO.ptosARedimir},${productosTO.precioBD},${productosTO.tipoPromocion},${productosTO.precioIva},${productosTO.url},${productosTO.tecnologia},${productosTO.puntosSobrantes},${productosTO.descuento},${productosTO.bonosRoext},${productosTO.bonosAltoValor},${productosTO.aplicaPaqueteSMS},${productosTO.aplicaPromocionGap},${productosTO.idPromocionGap},${productosTO.idPromocionGapCA},${productosTO.verPromocionGap},${productosTO.bonosGap},${productosTO.bonoDescuentoGap},${productosTO.productoM2KGap},${productosTO.nombrePromocionGap},${productosTO.aplicaEP},${productosTO.descuentoInbursa},${productosTO.descuentoMarca},${productosTO.descuento+productosTO.descuentoInbursa+productosTO.descuentoMarca},${productosTO.descuentoInbursaRestante},${productosTO.descuentoMarcaRestante}'
	                       onclick="returnProductos(this);">${productosTO.descripcion}</td>                
	                <td class="textonegroBlod" width="10%">&nbsp;${productosTO.marca}</td>
	                <td class="textonegroBlod" width="10%">&nbsp;${productosTO.modelo}</td>     
	                <td class="textonegroBlod" width="10%">&nbsp;${productosTO.ptosARedimir}</td>
	                <td class="textonegroBlod" width="19%" align="right">$&nbsp;${productosTO.precioBD}</td>
	                <td class="textonegroBlod" width="13%" align="right">$&nbsp;${productosTO.descuentoInbursa}</td>
					<td class="textonegroBlod" width="13%" align="right">$&nbsp;${productosTO.descuentoMarca}</td>
	                <td class="textonegroBlod" width="13%" align="right">$&nbsp;${productosTO.descuento}</td>
	                <td class="textonegroBlod" width="19%" align="right">$&nbsp;${productosTO.precioIva}</td>
	                <td class="textonegroBlod" width="20%">
	                	<a id="detalle" name="detalle" target="_blank" href="${productosTO.url}">Ver Detalle</a>
	                </td>	                          
	            </tr>
	            </c:forEach>
	</table>
</td></tr>
	</table>
</body>
</html>