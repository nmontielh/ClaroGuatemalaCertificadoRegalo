<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<html>
<head>
<title>Impresion Alianzas</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href='<c:url value="/commons/js/sc_textsheet.css"/>' type="text/css">
<script>
	function imprimeIndividual(telefono,fecha,fechaFolio,tipoAlianza,actual){

			document.getElementById("general").value = "detalle";
			document.getElementById("alianza").value = tipoAlianza;
			document.parentWindow.parent.act_cargando();
			valor = window.showModalDialog("./impresionAlianzas.do?telefono="+telefono+
					"&fecha="+fecha+"&alianza="+document.getElementById("alianza").value+
					"&general="+document.getElementById("general").value+
					"&fechaFolio="+fechaFolio+"&actual="+actual,
					"","dialogHeight: 750px;dialogWidth: 750px; center: Yes; help: No; resizable: Yes; status: No;");
			document.parentWindow.parent.des_cargando();
		}

	</script>
</head>
<body style="background-color: transparent;">
<script>	
    document.parentWindow.parent.des_cargando(); 
</script>
<form name="frmdetalleRed" id="frmdetalleRed"
	action="./jsp-content/ConstanciaRedencion.pdf"><input
	type="hidden" id="alianza" name="alianza" value="amex"> <input
	type="hidden" id="telefono" name="telefono"
	value="${telefonoTO.telefono}"> <input type="hidden" id="fecha"
	name="fecha" value="${impresionTO.fechaFAlianza}"> <input
	type="hidden" id="general" name="general" value="general">






<DIV id="divAmex"
	style="visibility: visible MARGIN-TOP :       0px; OVERFLOW-Y: scroll; OVERFLOW-X: hidden; BORDER: ; width: : inherit; height: inherit; absolute; top: 10px; display: block;">
<table width="970" border="1" cellspacing="0" cellpadding="1"
	align="center" class='main'>
	<tr>
		<td class="titulo" height="42">&nbsp;&nbsp;Impresión de
		Constancia de Canje</td>
	</tr>
</table>
<table width="98%" border="1" cellspacing="0" cellpadding="0"
	align="center">
	<tr>
		<td width="16%" class="healineblue1" height="20" bgcolor="#eff0f1">
		<div align="center">Nombre</div>
		</td>
		<td class="textonegroBlod" width="44%" height="20">
		<div align="left"></div>
		&nbsp;${telefonoTO.mobileTO.nombre}</td>
		<td width="12%" class="healineblue1" height="19" bgcolor="#eff0f1">
		<div align="center">Alianza</div>
		</td>
		<td class="textonegroBlod" width="28%" height="19">
		<div align="left"></div>
		&nbsp;AMERICAN EXPRESS</td>
	</tr>
	<tr>
		<td width="16%" class="healineblue1" height="19" bgcolor="#eff0f1">
		<div align="center">Teléfono</div>
		</td>
		<td class="textonegroBlod" width="44%" height="19">
		&nbsp;${telefonoTO.telefono}</td>
		<td colspan=2 class="textonegroBlod">&nbsp;</td>
	</tr>
	<tr>
		<td width="16%" class="healineblue1" height="19" bgcolor="#eff0f1">
		<div align="center">Cuenta</div>
		</td>
		<td colspan="2" class="textonegroBlod" width="44%" height="19">
		&nbsp;${telefonoTO.cuenta}</td>
	</tr>
</table>

<table width="98%" border="0" cellspacing="0" cellpadding="0"
	align="center">
	<tr>
		<td class="textonegroBlodTrs">&nbsp;</td>
	</tr>
	<tr>
		<td class="textonegroBlodTrs"><font color='red'>Canje</font></td>
	</tr>
</table>
<table width="98%" border="1" cellspacing="0" cellpadding="0"
	align="center" bordercolor="#cccccc">
	<tr bgcolor="#ECF0DB" class="healineblue1" align="center">
		<th width="10%">Folio</th>
		<th width="13%">Fecha de expedición</th>
		<th width="13%">Valor en puntos</th>
		<th width="10%">Valor del cupón en pesos</th>
	</tr>
	<tr>
		<c:set var="contador" value="0" />
		<c:forEach var="alianzasTO" items="${impresionTO.alianzas}"
			varStatus="total">
			<c:set var="contador" value="${total.count}" />
			<tr class="X3"
				bgcolor="<c:if test="${contador %2 !=0 }">#D9EBF2</c:if>">
				<td align="left"><input type="radio" id="row" name="row"
					onclick="imprimeIndividual('${telefonoTO.telefono}','${impresionTO.fechaFAlianza}','${alianzasTO.fechaFolio}','${tipoAlianza}','')">
				&nbsp;${alianzasTO.folio}</td>
				<td align="left">&nbsp;${alianzasTO.fechaOperacion}</td>
				<td align="left">&nbsp;${alianzasTO.ptsTransferidos}</td>
				<td align="left">&nbsp;$${alianzasTO.valorCuponOrig}</td>
			</tr>
		</c:forEach>
	<tr bgcolor="#ECF0DB" class="healineblue1">
		<td colspan="4">&nbsp;<c:out value="${contador}"></c:out>
		Registro(s) Encontrado(s).</td>
	</tr>
</table>
<table width="98%" border="0" cellspacing="0" cellpadding="0"
	align="center">
	<tr>
		<td class="textonegroBlodTrs">&nbsp;</td>
	</tr>
</table>
<!-- <p align="right"><img height=63 src="http://191.9.4.168:38080/circuloazul/web-content/pts_images/logo_circulo_azul.gif" width=197></p>  -->
</DIV>

</form>
</body>
</html>