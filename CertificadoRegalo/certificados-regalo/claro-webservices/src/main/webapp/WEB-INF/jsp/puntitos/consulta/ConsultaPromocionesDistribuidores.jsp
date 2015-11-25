<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt'%>     
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="/spring"%>
<%@ taglib uri="/WEB-INF/permisos.tld" prefix="securityCa"  %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Administración de Promociones</title>
<link rel="stylesheet" type="text/css" href='<c:url value="/commons/css/impromptus.css"/>' media='screen' >
<link rel="stylesheet" href='<c:url value="/commons/js/sc_textsheet.css"/>' type="text/css">
        
<script type="text/javascript" src='<c:url value="/commons/js/jquery-1.2.3.pack.js"/>'></script>
<script type='text/javascript' src='<c:url value="/commons/js/jquery.form.js"/>' ></script>
<script type="text/javascript" src='<c:url value="/commons/js/jquery-impromptu.1.3.js"/>' ></script>
<script type="text/javascript" src='<c:url value="/commons/js/puntitos.js"/>' ></script>
<script type="text/javascript">
	function consultaMarcas(){										
		var idPlan = document.getElementById("idPlan").value;
		var fzaVenta = document.getElementById("fzaVenta").value;
		if(idPlan == '' || idPlan.length < 5) {
			alert("Debe eligir un Plan para consultar.");
			return false;
		} else {
			//llama al servlet con el parametro seleccionado
			$('#marcas').load("./consultaCatalogo.do", {idOperacion:"cargaMarcas",idPlan:idPlan,fzaVenta:fzaVenta},
				function(data) { 
					var elOptNew = document.createElement('option');
					elOptNew.text = 'Seleccione';
					elOptNew.value = '';

					agregaResultadoCatalogo(data,document.getElementById('marcas'));
					if(document.getElementById('marcas').length > 0) {
						var idMarcaS		= document.getElementById('marcas')[0].value;
						var idGpoPromo		= idMarcaS.split("|")[1];
						document.getElementById('gpoPromo').value = idGpoPromo;
					} else {
						alert("El Plan seleccionado no tiene marcas asociadas.");
						return false;
					}
					

					var elSel = document.getElementById('marcas');
					elSel.add(elOptNew, 0); 
					elSel.options[0].selected = true;
					document.getElementById("marcas").disabled=false;
				}
			); 
		}
	}

	function agregaResultadoCatalogo(data,objectDestino){		
		if($(data)[0].id == "tablaCatalogo"){				             
			var total = $(data)[0].rows.length;	
			for(var i=0;i<total;i++){
				var idRenglon =  $(data)[0].rows[i].id;	
				var texto = $(data)[0].rows[i].innerText;						
				var no = new Option();
	          		 no.value = idRenglon;
	          		 no.text = texto;	          
	                 objectDestino[i] = no;	          											
			}
		}
	}


	function consulta(){
		var grupo		= document.getElementById('Grupo');
				
		if(grupo.checked==false){
			alert("Debe eligir una opción para consultar.");
			return false;
		}
		if(document.getElementById("marcas").value=='') {
			alert("Debe eligir una Marca para consultar.");
			return false;
		} else {
			var idMarcaS		= document.getElementById('marcas').value;
			document.getElementById('idMarca').value = idMarcaS.split("|")[0];
		}
		if(document.getElementById("modelos").value=='') {
//			alert("Debe eligir un Modelo para consultar.");
//			return false;
		} else {
			var idModeloS		= document.getElementById('modelos').value;
			document.getElementById('idModelo').value = idModeloS.split("|")[0];
		}
		if(document.getElementById("idPlan").value=='') {
			alert("Debe eligir un Plan para consultar.");
			return false;
		}

		var accion = document.getElementById("accion");
		accion.value = "Consulta";
		var reporte = document.getElementById("reporte");
		
		if(grupo.checked==true){
			reporte.value="Promociones";
		}
		
		var form = document.getElementById('frmConsultaD');
		form.submit();
	}


function cargaModelos() {
	var idMarcaS		= document.getElementById('marcas').value;
	var infoMarca		= idMarcaS.split("|");
	var idMarca			= infoMarca[0];

	//llama al servlet con el parametro seleccionado
	$('#modelos').load("./consultaCatalogo.do", {idOperacion:"cargaModelos",idMarca:idMarca,idGpoPromocion:document.getElementById('gpoPromo').value},
		function(data) { 
			var elOptNew = document.createElement('option');
			elOptNew.text = 'Seleccione';
			elOptNew.value = '';

			agregaResultadoCatalogo(data,document.getElementById('modelos'));

			if(document.getElementById('modelos').length <= 0) {
				alert("La Marca seleccionada no tiene modelos asociados.");
				return false;
			}

			var elSel = document.getElementById('modelos');
		    elSel.add(elOptNew, 0); 
			elSel.options[0].selected = true;
			document.getElementById("modelos").disabled=false;

		}
	); 
}
</script>
</head>
<body onload="lectura()">
<form action="./consultaPromocionesDistribuidores.do" method="post" name="frmConsultaD" id="frmConsultaD" AUTOCOMPLETE="OFF">
<input type="hidden" name="accion" id="accion" />
<input type="hidden" name="reporte" id="reporte" />

<input type="hidden" name="idMarca" id="idMarca" />
<input type="hidden" name="idModelo" id="idModelo" />
<input type="hidden" name="gpoPromo" id="gpoPromo" />
<table width="100%">
	<tr>
		<td height="30" class="subtitulo">Puntitos: Consulta de Promociones de Distribuidores</td>
		<td align="right" >
			<div class="txtSumNormal" id="divLoading" name="divLoading" style="display: none;">
				<img src='<c:url value="/commons/images/load.gif"/>'/> Buscando infomaci&oacute;n &nbsp;&nbsp; </div> </td>	
	</tr>
</table>
<table width="100%">
	<tr>
		<td width="10">
			<input type="radio" name="seleccion" id="Grupo" onclick="selecDistribuidor('Distribuidores')" />
		</td>
		<td class="healineblue1">Consulta de Promociones</td>
	</tr>
	<tr id="consulta1" style="visibility:hidden; display:none">
		<td width="10"></td>
		<td>
			<table>
				<tr>
					<td class="txtSumNormal">Id Plan</td>
					<td>
						<input type="text" name="idPlan" id="idPlan" class="InputB" maxlength="5" size="8" onKeyDown="if(event.keyCode==13) consultaMarcas(); ">
					</td>
					<td width="10" colspan="3"></td>
				</tr>
				<tr>
					<td class="txtSumNormal">Fuerza de Venta</td>
					<td>
						<input type="text" name="fzaVenta" id="fzaVenta" class="InputB" maxlength="10" size="11">
						<a target="_blank" style="cursor:hand;" onClick="consultaMarcas();" title="Consultar Marcas">
						<img width="13" height="13" src='<c:url value="/commons/images/buscar.gif"/>' border="0"></a>
					</td>
					<td width="10" colspan="3"></td>
				</tr>
				<tr>
					<td class="txtSumNormal">Marca</td>
					<td>
					<select disabled name="marcas" class="textonegroBlod" size=1 id="marcas" onchange="cargaModelos();">
						<option value="">Seleccione</option>
						<c:forEach var="catalogoTO" items="${marcas}" varStatus="total">
							<option value="${catalogoTO.descripcion}">${catalogoTO.descripcion}</option>
						</c:forEach>
	         		</select>
					</td>
					<td class="txtSumNormal">Modelo</td>
					<td>
						<select name="modelos" id="modelos" class="textonegroBlod" size=1>
							<option value="">Seleccione</option>
							<c:forEach var="catalogoTO" items="${modelos}" varStatus="total">
							<option value="${catalogoTO.descripcion}">${catalogoTO.descripcion}</option>
						</c:forEach>						
						</select>
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td width="10"></td>
		<td align="right">
			<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="158">
			 	<a class="LinkOut" style="width:30; text-align:center" onmouseover='this.className="LinkIn";'
					onmouseout='this.className="LinkOut";' id="btnArchivo" onClick="consulta();">&nbsp;CONSULTA&nbsp;&nbsp;</a>	
			</securityCa:validaPerfil>
		</td>
	</tr>
</table>
 
 <DIV style="width: 100%; FILTER: progid:DXImageTransform.Microsoft.Shadow(color='#aaaacc', Direction=135, Strength=2); 
	border-bottom-width: 2px; border-bottom-style: solid; border-bottom-color: #4d7097; font-size: 1px;">
</DIV>

<div id="listadoPromociones" style="visibility:hidden; display:none" class="scroll">
<table>
	<tr><td class="Cabecera2">Datos de las Promociones</td></tr>
</table>
<table id="tablaPromociones" border="1" cellspacing="0" cellpadding="0" width="95%">
	  <thead>
		<tr class="healineblue1" bgcolor="#ECF0DB" align="center">
			<td>&nbsp;Id Producto&nbsp; </td>
			<td>&nbsp;Descripci&oacute;n&nbsp;</td>
			<td>&nbsp;Tipo Promoci&oacute;n&nbsp;</td>
			<td>&nbsp;Precio Lista&nbsp;</td>
			<td>&nbsp;Precio Activaci&oacute;n&nbsp;</td>
			<td>&nbsp;Marca&nbsp;</td>
			<td>&nbsp;Modelo&nbsp;</td>
			<td>&nbsp;Indicador&nbsp;</td>
			<td>&nbsp;Fuerza Venta&nbsp;</td>
			<td>&nbsp;Id Plan&nbsp;</td>
			<input type="hidden" name="idpromo" id="idpromo">
	
		</tr>
	  </thead>
	  <tbody>
	  <c:set var="contador" value="0"/>
	  	<c:forEach  var="promocionTO" items="${promociones}" varStatus="total">
	  		<c:set var="contador" value="${total.count}"/>
			<tr id="linea" align="center" class="X3" bgcolor="<c:if test="${contador %2 !=0 }">#D9EBF2</c:if>">
				<td>${promocionTO.idProducto}</td>
				<td>${promocionTO.descripcion}</td>
				<td>${promocionTO.tipoPromocion}</td>
				<td>${promocionTO.precioLista}</td>
				<td>${promocionTO.precioActiva}</td>
				<td>${promocionTO.marca}</td>
				<td>${promocionTO.modelo}</td>
				<td>${promocionTO.indicador}</td>
				<td>${promocionTO.fzaVta}</td>
				<td>${promocionTO.tipoProducto}</td>
			</tr>
		</c:forEach>
	  </tbody>
</table>
</div>



<script type="text/javascript" language="javascript">
 function lectura(){
 	<c:set var="accion" value="${accion}" /> 	
 	<c:if test="${promociones!=null}">	
		document.getElementById("listadoPromociones").style.display = "block";
		document.getElementById("listadoPromociones").style.visibility="visible";
	</c:if>	

 }
</script>
</form>
</body>
</html>