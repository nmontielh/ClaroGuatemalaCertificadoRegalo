<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/permisos.tld" prefix="securityCa" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Redención Tiempo Aire</title>
<link rel="stylesheet" href='<c:url value="/commons/js/sc_textsheet.css"/>' type="text/css">
<script>
	
	function realizaRedencion(){
		var capturaComent = false;
		if(frmRedencionTA.bmixto.value=="S")
			capturaComent = confirm("Los minutos incluidos en este paquete, aplicarán para el SIGUIENTE PERIODO debido a que es una línea mixta,\r recuerda que no hay cancelación de este movimiento. Deseas continuar con el trámite?");
		else 
			capturaComent = confirm("Este Canje no podrá ser Cancelado. \r ¿Esta seguro de Redimir los Puntos?");
		
		if(capturaComent){					
			document.getElementById("divComentario").style.visibility="visible";
			document.getElementById("divComentario").style.display="block";
		}					
	}
	function cerrar(){
		document.getElementById("divComentario").style.visibility="hidden";
		document.getElementById("divComentario").style.display="none";
	}
	
	function aplicaRedencion(){
					
		if(document.getElementById("comentarioDiv").value==""|| document.getElementById("comentarioDiv").value.legth==0){
			alert("Debe Capturar un comentario.");
			return false;
		}else if(document.getElementById("comentarioDiv").value.legth>=30){
			alert("El comentario no debe exceder de 30 caracteres.");
			return false;			
		}
		document.getElementById("divComentario").style.visibility="hidden";
		document.getElementById("divComentario").style.display="none";
		frmRedencionTA.comentario.value = document.getElementById("comentarioDiv").value;
		frmRedencionTA.submit();
	}
	
	var elimina = false;
	
	function validaProductos(){		
		var activo = false;
		var contador=0;		
		var total = frmPromociones.total.value; 		
		var objeto = null;		
		if(total==1){			
			activo =  frmPromociones.rdProducto.checked;
			if(activo)
				objeto = frmPromociones.rdProducto;
		}else{
			while(contador<total && !activo){						
				activo = eval("frmPromociones.rdProducto["+contador+"].checked"); 				 					
				if(activo)
					objeto =eval("frmPromociones.rdProducto["+contador+"]");
							
				contador++;					
			}		
		}	
		if(!activo){
			alert("Debe Seleccionar un producto.");
			return false;
		}else if(objeto!=null){	
			regresaProducto(objeto);
		}		
	}
	
	function regresaProducto(objeto){			
		var valor = objeto.value;
		cierraDiv();
		agregaRenglon(valor);
	}
	
	function eliminaRenglon(){
		elimina = false;		  
		document.getElementById('tblProductos').deleteRow(1);
		document.getElementById("tblProductos").style.display="none";
		document.getElementById("tblProductos").style.visibility="hidden";
		document.getElementById("btnRealizaRedencion").style.display="none";
		document.getElementById("btnRealizaRedencion").style.visibility="hidden";
		
	}
	
	function agregaRenglon(valores){
		if(elimina){		
		 	eliminaRenglon();		
		 	limpiaDatos(); 			
		}
		document.getElementById("tblProductos").style.display="block";
		document.getElementById("tblProductos").style.visibility="visible";
		
		var tabla = document.getElementById('tblProductos').insertRow(1);  
		var cell0 = tabla.insertCell(0);		
		var cell1 = tabla.insertCell(1);  
		var cell2 = tabla.insertCell(2); 		 
		var cell3 = tabla.insertCell(3);
		var cell4 = tabla.insertCell(4);
		cell0.className="InputText";
		cell1.className="InputText";
		cell2.className="InputText";
		cell3.className="InputText";
		cell4.className="InputText";
		
		var resul_array = valores.split("|");     		
		cell0.innerHTML = resul_array[0];  
		cell1.innerHTML = resul_array[1];
		cell2.innerHTML = resul_array[2];  		
		cell3.innerHTML = resul_array[4];  
		cell4.innerHTML = resul_array[7];
		elimina=true;
		document.getElementById("btnRealizaRedencion").style.display="block";
		document.getElementById("btnRealizaRedencion").style.visibility="visible";
		frmRedencionTA.idProd.value=resul_array[0];
		frmRedencionTA.tipoPromo.value=	resul_array[4];
		frmRedencionTA.valorptos.value=resul_array[2];
		frmRedencionTA.planDescuento.value=resul_array[7];
		frmRedencionTA.marcaR.value=resul_array[7];
		frmRedencionTA.bmixto.value=resul_array[8];
		frmRedencionTA.descripcion.value=resul_array[1];
		if(resul_array[3]==".00")
			frmRedencionTA.difPesos.value="0"+resul_array[3];
		else 
			frmRedencionTA.difPesos.value=resul_array[3];
	}
	
	function limpiaDatos (){
		frmRedencionTA.idProd.value="";
		frmRedencionTA.tipoPromo.value=	"";
		frmRedencionTA.valorptos.value="";
		frmRedencionTA.planDescuento.value="";
		frmRedencionTA.difPesos.value="";
		frmRedencionTA.marcaR.value="";
		frmRedencionTA.descripcion.value="";
		frmRedencionTA.bmixto.value="";
	}
	
	function cierraDiv(){
		document.getElementById("divPromociones").style.visibility="hidden";
		document.getElementById("divPromociones").style.display="none";
	}
	
	function buscaPromociones(){
		document.getElementById("divPromociones").style.visibility="visible";
		document.getElementById("divPromociones").style.display="block";		
	}
	
	function incializa(){	
		//document.parentWindow.parent.document.getElementById("divCargando").style.visibility="hidden";
		//document.parentWindow.parent.document.getElementById("divCargando").style.display="none";	
		document.parentWindow.parent.document.getElementById("CampoObligatorio").style.visibility="hidden";
		document.parentWindow.parent.document.getElementById("CampoObligatorio").style.display="none";
		document.parentWindow.parent.document.getElementById("inputSap").style.visibility="hidden";
		document.parentWindow.parent.document.getElementById("inputSap").style.display="none";
		document.parentWindow.parent.document.getElementById("inputSin").style.visibility="hidden";
		document.parentWindow.parent.document.getElementById("inputSin").style.display="none";
		document.parentWindow.parent.document.getElementById("inputCareg").style.visibility="hidden";
		document.parentWindow.parent.document.getElementById("inputCareg").style.display="none";		
		document.parentWindow.parent.document.getElementById("inputCon").style.visibility="hidden";
		document.parentWindow.parent.document.getElementById("inputCon").style.display="none";
		document.parentWindow.parent.document.getElementById("TarjetaRedencionesCon").style.display="none";
		document.parentWindow.parent.document.getElementById("TarjetaCareg").style.visibility="hidden";
		document.parentWindow.parent.document.getElementById("TarjetaCareg").style.display="none";	
		document.parentWindow.parent.document.getElementById("TarjetaRedencionesS").style.visibility="hidden";
		document.parentWindow.parent.document.getElementById("TarjetaRedencionesS").style.display="none";	
		document.parentWindow.parent.document.getElementById("TarjetaTA").style.visibility="visible";
		document.parentWindow.parent.document.getElementById("TarjetaTA").style.display="block";	
		frmRedencionTA.fecAddM2K.value=document.parentWindow.parent.document.getElementById("fecAddM2K").value;
		var bAplicaRedencion = document.parentWindow.parent.document.getElementById("bAplicaRedencion").value;		
		if(bAplicaRedencion){
			document.getElementById("btnRealizaRedencion").style.display="none";
			document.getElementById("btnRealizaRedencion").style.visibility="hidden";
		}		
	}
	
	/*function activaCargando(sVisible,sDisplay){	
		window.alert("redencionTA.jsp");
		document.parentWindow.parent.document.getElementById("divCargando").style.visibility=sVisible;
		document.parentWindow.parent.document.getElementById("divCargando").style.display=sDisplay;					
	}*/
</script>
</head>
<body  style="background-color: transparent;" onload="incializa();" >
<div id="divRedencionTA" style="width: 800px;height: 280px;">
	<form name="frmRedencionTA" id="frmRedencionTA" method="get" action="./realizaRedencion.do">
		<input type="hidden" name="cuenta" id="cuenta" value="${cuenta}" >
		<input type="hidden" name="secuencia" id="secuencia" value="${secuencia}">
		<input type="hidden" name="telefono" id="telefono" value="${telefono}">
		<input type="hidden" name="region" id="region" value="${region}">
		<input type="hidden" name="planM2K" id="planM2K" value="${planM2K}">
		<input type="hidden" name="idProd" id="idProd" >
		<input type="hidden" name="descripcion" id="descripcion">
		<input type="hidden" name="tipoPromo" id="tipoPromo" >		
		<input type="hidden" name="valorptos" id="valorptos">
		<input type="hidden" name="bmixto" id="bmixto" >
		<input type="hidden" name="planDescuento" id="planDescuento" value="${planDescuento}" >
		<input type="hidden" name="difPesos" id="difPesos">
		<input type="hidden" name="comentario" id="comentario" >
		<input type="hidden" name="tipoRed" id="tipoRed" value="TAIR">		
		<input type="hidden" name="marcaR" id="marcaR">
		<input type="hidden" name="modeloR" id="modeloR" value="">
		<input type="hidden" name="ptosDispTmp" id="ptosDispTmp" value="${puntosDispo}">
		<input type="hidden" name="formaRed" id="formaRed" value="PD">
		<input type="hidden" name="sobrantes" id="sobrantes" value="0">
		<input type="hidden" name="fecAddM2K" value="fecAddM2K" >
		<input type="hidden" name="descuento" id="descuento" value="0.0">
		<!-- Envío de Mensaje SMS JAPA 22/10/2012 - Folio: 117384 -->
		<input type="hidden" name=dirIP value="<%=request.getHeader("CLIENT_IP_ADDRESS")%>"> 
		<!--<input type="hidden" name="dirIP" value="<%=request.getRemoteAddr()%>">-->
		
		<center>
		<table border="1" width="100%">
			<tr >
				<td bgcolor="#ECF0DB" class="healineblue1"  align="left">&nbsp;Aplica Redeción:</td>
				<td class="textonegroBlod">PUNTOS DISPONIBLES</td>
				<td bgcolor="#ECF0DB" class="healineblue1"  align="left">&nbsp;Tipo de Redención</td>
				<td class="textonegroBlod">Tiempo Aire</td>
			</tr>
			<tr>
				<td bgcolor="#ECF0DB" class="healineblue1"  align="left">&nbsp;Puntos Disponibles:</td>
				<td class="textonegroBlod">${puntosDispoCF}</td>
				<td bgcolor="#ECF0DB" class="healineblue1"  align="left">&nbsp;Plan</td>
				<td class="textonegroBlod">${planM2K}</td>
			</tr>	
		</table>
		<br>
		<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="127">	
			<input type="button" name="btnPromociones" value="Busca Promociones" onclick="buscaPromociones()" class="submit">
		</securityCa:validaPerfil>		
		<table id="tblProductos" border="1" style="visibility: hidden;display: none;" width="100%">
			<tr>
				<td class="healineblue1" bgcolor="#ECF0DB" align="left">&nbsp;Id</td>
				<td class="healineblue1" bgcolor="#ECF0DB" align="left">&nbsp;Descripción</td>
				<td class="healineblue1" bgcolor="#ECF0DB" align="left">&nbsp;Valor Puntos</td>				
				<td class="healineblue1" bgcolor="#ECF0DB" align="left">&nbsp;Tipo Prom</td>
				<td class="healineblue1" bgcolor="#ECF0DB" align="left">&nbsp;Plan Descuento</td>
			</tr>
		</table>
		<br>
			<span id="btnRealizaRedencion" style="visibility: hidden;display: none;">
				<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="139">
					<input type="button" name="btnRedimir" value="Redimir" onclick="realizaRedencion()" class="submit">
				</securityCa:validaPerfil>
			</span>			
		</center>
	</form>
	
	
	<div id="divPromociones" style="display: none;visibility: visible;position: absolute;left: 130px;top: 15px;width: 700px;height: 80px;" class="BloqueBlanco">
		<form action="frmPromociones" id="frmPromociones">
		<c:if test="${empty productosTO}">
			<span>
				<font>
					<b>No Se encontraron productos.</b>
				</font>
			</span>
		</c:if>
		<c:set var="contador" value="0"/>
		
		
		
		<c:if test="${!empty productosTO}">
		<div id="divProductos" style="height: 200px;MARGIN-TOP: 0px; OVERFLOW-Y: scroll; OVERFLOW-X: hidden;">			
			<table width="100%" border="1">
			<tr>
				<td class="healineblue1" bgcolor="#ECF0DB" align="left">&nbsp;Descripción</td>				
				<td class="healineblue1" bgcolor="#ECF0DB" align="left">&nbsp;Valor Puntos</td>				
				<td class="healineblue1" bgcolor="#ECF0DB" align="left">&nbsp;Tipo Prom</td>
				<td class="healineblue1" bgcolor="#ECF0DB" align="left">&nbsp;Fuerza Vent</td>
				<td class="healineblue1" bgcolor="#ECF0DB" align="left">&nbsp;Plan Descuento</td>				
			</tr>
			<c:forEach var="productoTO" items="${productosTO}" varStatus="total">			
			<c:set var="contador" value="${total.count}"/>
			<tr class="X3" bgcolor="<c:if test="${contador %2 !=0 }">#D9EBF2</c:if>">	           	
	           	<td>&nbsp;<input type="radio" name="rdProducto" id="rdProducto" value="${productoTO.material}|${productoTO.descripcion}|${productoTO.valorPuntos}|${productoTO.precioActivacionCF}|${productoTO.tipoPromocion}|${productoTO.descripcion}|${productoTO.fzaVentas}|${productoTO.marca}|${productoTO.modelo}" >&nbsp;${productoTO.descripcion}</td>
	           	<td>&nbsp;${productoTO.valorPuntosF}</td>
	           	<td>&nbsp;${productoTO.tipoPromocion}</td>
	           	<td >&nbsp;${productoTO.fzaVentas}</td>
	           	<td>&nbsp;${productoTO.marca}</td>
	          </tr>
			</c:forEach>
			</table>
		</div>
		</c:if>	
		<table width="100%">
			<tr class="healineblue1" bgcolor="#ECF0DB" align="left">
				<td colspan="2">${contador}&nbsp;Registro(s) Encontrado(s).</td>
			</tr>
			<tr align="center">
				<td><input type="button" name="btnCerrar" value="Cerrar" onclick="cierraDiv()" class="submit">&nbsp;&nbsp;&nbsp;<input class="submit" type="button" name="btnAceptar" value="Aceptar" onclick="validaProductos()"> </td>
			</tr>
		</table>		
		<input type="hidden" name="total" value="${contador}" id="total">		
		</form>			
	</div>	
</div>
<div id="divComentario" style="visibility: hidden;display: none;position: absolute;top:30px;left: 150px;width: 250px;height: 150px;" class="BloqueBlanco">
	
	<table width="100%">
      		<tr bgcolor="#ECF0DB" class="healineblue1">
      			<td colspan="2" width="100%">
      				Comentario
      			</td>      			
      		</tr>
      		<tr>
      			<td colspan="2" width="100%">	
      				<textarea rows="5" cols="300" id="comentarioDiv" name="comentarioDiv" style="width: 100%;" ></textarea>
      			</td>
      		</tr>
      		<tr>
      			<td width="50%">
      				<input type="button" name="aceptar" value="Aceptar" onclick="aplicaRedencion()" class="submit">
      			</td>
      			<td width="50%">
      				<input type="button" name="aceptar" value="Cerrar" onclick="cerrar()" class="submit">
      			</td>
      		</tr>
      	</table>
</div>

	

</body>
</html>
