<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/permisos.tld" prefix="securityCa"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Alianza Canje</title>
<link rel="stylesheet"
	href='<c:url value="/commons/js/sc_textsheet.css"/>' type="text/css">
</head>
<script language="javascript" type="text/javascript">

	function muestraComentarioAmex(total){
		frmAmex.total.value=total;
		var activa=validaViajes();		
		if(!activa){			
			alert("Debe Seleccionar un viaje");
			return false;
		}
		document.getElementById("divComentarioAmex").style.visibility="visible";		
		document.getElementById("divComentarioAmex").style.display="block";		
	}	
	
	function cierraComentario(){
		document.getElementById("divComentarioAmex").style.visibility="hidden";
		document.getElementById("divComentarioAmex").style.display="none";
	}
	function cierraComentarioAmex(){
		var coment = document.getElementById("comentarioDiv").value;				
		if(coment=="" || coment.length==0){
			alert("Debe ingresar un comentario.");
			return false;
		}else if(coment.length>=50){
			/* Envío de Mensaje SMS  - Reducir tamañano de comentario para incluir NumEmpleado y direccion IP - JAPA 22/10/2012 - Folio: 117384 */
			alert("El comentario no debe exceder de 50 caracteres");
			return false;
		}				
		cierraComentario();
		frmAmex.comentario.value=document.getElementById("comentarioDiv").value;
		canjeAmex();
	}
	function validaViajes(){		
		var activo = false;
		var contador=0;		
		var total = frmAmex.total.value;		
		if(total==1){			
			activo = eval("frmAmex.viaje.checked");
			if(activo){
				var valores=eval("frmAmex.viaje.value");				
				var resul_array = valores.split("|");			        		 
				frmAmex.puntosTrans.value = resul_array[1];
				frmAmex.producto.value = resul_array[0];
				var entero = parseInt(resul_array[2]);				
				frmAmex.valorCertificado.value=entero;
			}
		}else{
			while(contador<total && !activo){						
				activo = eval("frmAmex.viaje["+contador+"].checked")==true;			
				if(activo){
					var valores = eval("frmAmex.viaje["+contador+"].value");
					var resul_array = valores.split("|");        		  
			  		frmAmex.puntosTrans.value = resul_array[1];
					frmAmex.producto.value = resul_array[0];
					var entero = parseInt(resul_array[2]);				
					frmAmex.valorCertificado.value=entero;					
				}			
				contador++;					
			}		
		}		
		return activo;		
	}
	function canjeAmex(){
		var activa=validaViajes();		
		if(!activa){			
			alert("Debe Seleccionar un viaje");
			return false;
		}else if(frmAmex.comentario.value==""){
			alert("Debe Ingresar un comentario.");
			return false;
		}else if(frmAmex.comentario.length>=50){
			/* Envío de Mensaje SMS  - Reducir tamañano de comentario para incluir NumEmpleado y direccion IP - JAPA 22/10/2012 - Folio: 117384 */
			alert("El comentario no debe exceder de 50 caracteres");
			return false;
		}
		frmAmex.method="post";
		frmAmex.action="./aplicaCanje.do";
		frmAmex.submit();
	}
	
	function cambioDiv(){
		document.getElementById("divAmex").style.visibility="visible";
		document.getElementById("divAmex").style.display="block";
	}
	
	
</script>
<body>
<script>
	document.parentWindow.parent.activaCargando("hidden","none");
</script>
<DIV
	style="position: absolute; top: 20px; height: 50px; MARGIN-TOP: 0px; OVERFLOW-Y: hidden; OVERFLOW-X: hidden;">
<table>

	<tr class="textRadio">		
		<c:if test="${!empty amexTO}">
			<securityCa:validaPerfil
				perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="93">
				<td class="textRadio"><input type="radio" name="opcion"
					value="2" onclick="cambioDiv()"></td>
				<td class="textRadio">American Express</td>
			</securityCa:validaPerfil>
		</c:if>		
	</tr>
</table>
</DIV>

<c:if test="${!empty amexTO}">
	<div id="divAmex"
		style="width: 250; top: 50px; height: 265px; position: absolute; MARGIN-TOP: 0px; OVERFLOW-Y: hidden; OVERFLOW-X: hidden; visibility: hidden; display: none;">
	<form id="frmAmex" name="frmAmex"><input type="hidden"
		name="telefono" id="telefono" value="${telefono}"> <input
		type="hidden" name="cuenta" id="cuenta" value="${cuenta}"> <input
		type="hidden" name="region" id="region" value="${region}"> <input
		type="hidden" name="secuencia" id="secuencia" value="${secuencia}">
		<!-- Envío de Mensaje SMS JAPA 22/10/2012 - Folio: 117384 -->
		<input type="hidden" id="dirIP" name="dirIP" value="<%=request.getHeader("CLIENT_IP_ADDRESS")%>"> 
		<!--<input type="hidden" id="dirIP" name="dirIP" value="<%=request.getRemoteAddr()%>">-->
		
	<input type="hidden" name="ptsDisponibles" id="ptsDisponibles"
		value="${ptsDisponibles}"> <input type="hidden" name="factor"
		id="factor" value="${factor}"> <input type="hidden"
		name="cuentaAlianza" id="cuentaAlianza"
		value="${amexTO.cuentaAlianza}"> <input type="hidden"
		name="opcion" value="2"> <input type="hidden" name="producto"
		id="producto" value=""> <input type="hidden"
		name="puntosTrans" id="puntosTrans" value="0"> <input
		type="hidden" name="valorCertificado" id="valorCertificado" value="0">
	<input type="hidden" name="ptsDispo" value="${ptsDisponibles}">
	<input type="hidden" name="comentario" id="comentario" value="">
	<input type="hidden" name="total" id="total" value="0">
	<table border="1" cellspacing="0" cellpadding="0" width="95%">
		<tr>
			<td class="healineblue1" bgcolor="#ECF0DB" align="left">&nbsp;Nombre</td>
			<td class="textonegroBlod" align="left">&nbsp;${amexTO.nombre}</td>
			<td class="healineblue1" bgcolor="#ECF0DB" align="left">&nbsp;Apellido
			Paterno</td>
			<td class="textonegroBlod" align="left">&nbsp;${amexTO.APaterno}</td>
			<td class="healineblue1" bgcolor="#ECF0DB" align="left">&nbsp;Apellido
			Materno</td>
			<td class="textonegroBlod" align="left">&nbsp;${amexTO.AMaterno}</td>
			<td class="healineblue1" bgcolor="#ECF0DB" align="left">&nbsp;Puntos
			Disponibles</td>
			<td class="textonegroBlod" align="left">&nbsp;${ptsDisponibles}</td>
		</tr>
	</table>
	<br>
	<c:if test="${empty amexTO.productos}">
		<c:if test="${ptsDisponibles<50000}">
			<center><font color="red"><b>No cuenta con los
			puntos suficientes para realizar el canje.</b></font></center>
		</c:if>
	</c:if> <c:if test="${!empty amexTO.productos}">
		<table width="95%">
			<tr>
				<td class="healineblue1" bgcolor="#ECF0DB" align="left">&nbsp;Descripción</td>
				<td class="healineblue1" bgcolor="#ECF0DB" align="left">&nbsp;Valor
				que ampara el certificado</td>
				<td class="healineblue1" bgcolor="#ECF0DB" align="left">&nbsp;Puntos
				a canjear</td>
			</tr>
		</table>
		<div id="divProductos"
			style="height: 130px; OVERFLOW-Y: scroll; OVERFLOW-X: hidden">
		<table border="1" width="95%">
			<c:set var="contador" value="0" />
			<c:forEach var="productoTO" items="${amexTO.productos}"
				varStatus="total">
				<c:set var="contador" value="${total.count}" />
				<tr class="X3"
					bgcolor="<c:if test="${contador %2 !=0 }">#D9EBF2</c:if>">
					<td align="center" width="20%"><input type="radio" id="viaje"
						name="viaje"
						value="${productoTO.material}|${productoTO.valorPuntos}|${productoTO.precioActivacionCF}">
					&nbsp;Viaje Amex</td>
					<td align="left" width="53%">$&nbsp;${productoTO.precioActivacionCF}</td>
					<td align="left" width="27%">&nbsp;${productoTO.valorPuntosF}</td>
				</tr>
			</c:forEach>
		</table>
		</div>
		<table width="100%">
			<tr bgcolor="#ECF0DB" class="healineblue1">
				<td colspan="2">&nbsp;<c:out value="${contador}"></c:out>
				Registro(s) Encontrado(s).</td>
			</tr>
			<tr align="center">
				<td><input type="button" name="canjear" value="Canjear"
					class="submit" onclick='muestraComentarioAmex(${contador})'>
				</td>
			</tr>
		</table>
	</c:if></form>
	<div id="divComentarioAmex" class="BloqueBlanco"
		style="visibility: hidden; display: none; width: 700px; height: 70px; top: 0px; left: 50px; position: absolute;">
	<table cellspacing="0" cellpadding="0" align="center" width="100%">
		<tr>
			<td class="healineblue1">&nbsp;Comentario:</td>
		</tr>
		<tr>
			<td><input type='text' name="comentarioDiv" size="100"
				maxlength="49" class="InputText" style="width: 90%;" />
		</tr>
		<tr>
			<td align="center"><input type="button" name="btnAceptar"
				value="Canjear" onclick="cierraComentarioAmex()" class="submit">
			&nbsp;&nbsp; <input type="button" name="btnCierra" value="Cerrar"
				onclick="cierraComentario()" class="submit"></td>
		</tr>
	</table>
	</div>

	</div>
</c:if>



</body>
</html>