<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt'%>     
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix='f' uri="http://java.sun.com/jstl/fmt_rt"%>
<%@ taglib prefix="spring" uri="/spring"%>
<%@ taglib uri="/WEB-INF/permisos.tld" prefix="securityCa" %>
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
</head>
<body>
<table width="100%">
	<tr>
		<td	class="subtitulo" height="40">Puntitos: Administración de Promociones</td>
		<td align="right" >
			<div class="txtSumNormal" id="divLoading" style="display: none;">
				<img src="<c:url value="/commons/images/load.gif"/>"/> Buscando infomaci&oacute;n &nbsp;&nbsp; </div> </td>	
	</tr>
</table>
<DIV style="width: 100%; FILTER: progid:DXImageTransform.Microsoft.Shadow(color='#aaaacc', Direction=135, Strength=2); 
	border-bottom-width: 2px; border-bottom-style: solid; border-bottom-color: #4d7097; font-size: 1px;">
</DIV>
<form action="./agregaDocumentos.do" method="post" id="formAgregar" enctype="multipart/form-data" name="formAgregar">
	<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="81">
		<table width="80%">
		  <tr><td class="subtitulo" height="40" colspan="2">Datos Generales</td></tr>
		  <tr>
		  	<td class="healineblue1" width="25%">&nbsp;Descripci&oacute;n&nbsp;</td>
		    <td width="70%">
		    	<input name="descripcion" id="descripcion" type="text" value="" style="text-transform: uppercase; width:95%" maxlength="150"/>
		    </td>
		  </tr>
		  <tr>
		  	<td class="healineblue1" width="25%">&nbsp;Tipo de Documento&nbsp;</td>
		    <td width="70%">
		    	<select name="estatus" id="estatus">
		    		<option></option>
					<option value="M">MANUAL</option>
					<option value="A">PROMOCION</option>
		 		</select>
		 	</td>
		  </tr>
		  <tr>
		  	<td class="healineblue1">&nbsp;Archivo&nbsp;</td>
		  	<td>
		  		<input type="file" name="archivo" id="archivo" style="width:95%"/>
		  	</td>
		  </tr>
		  <tr>
		    <td colspan="2" align="right">
		    	<a class="LinkOut" style="width:30; text-align:center" onmouseover='this.className="LinkIn";'
					onmouseout='this.className="LinkOut";' id="btnArchivo" onClick="sendFile('formAgregar');">&nbsp;Adjuntar&nbsp;&nbsp;</a>
			</td>
		  </tr>
		</table>
	</securityCa:validaPerfil>
</form>
<form action="./eliminaDocumentos.do" method="post" id="formEliminar" name="formEliminar" style="visibility:hidden; display:none">
	<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="82">
		<table width="80%">
		  <tr><td class="subtitulo" colspan="2">Datos Generales</td></tr>
		  <tr>
		  	<td class="healineblue1" width="25%">&nbsp;Descripci&oacute;n&nbsp;</td>
		    <td width="70%">
		    	<input name="descripcionElimina" id="descripcionElimina" type="text" value="" readonly="readonly" style="text-transform: uppercase; width:95%" maxlength="150"/>
		    	<input name="idDocumento" id="idDocumento" type="hidden" value="" />
		    	<input name="nombre" id="nombre" type="hidden" value="" />
		    </td>
		  </tr>
		  <tr>
		    <td colspan="2" align="right">
		    	<input type="submit" value="Eliminar" name="btnEliminar" id="btnEliminar" class="botonInactivo" />
		    </td>
		  </tr>
		</table>
	</securityCa:validaPerfil>
</form>
	<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="82">
		<table>
			<tr><td height="40" class="subtitulo">Datos de las Promociones</td></tr>
		</table>
		<div id="listados" class="scroll">
			<table id="tablaLista" border="1" cellspacing="0" cellpadding="0" width="90%">
					<tr class="healineblue1" bgcolor="#ECF0DB" align="center">
						<td colspan="2">&nbsp;Nombre Promociones&nbsp; </td>
					</tr>
				  	<c:forEach  var="documentoTO" items="${listaDocumentoTO}" varStatus="total"><c:set var="contador" value="${total.count}"/>
						<tr id="linea" class="InputC" <c:if test="${contador %2 !=0 }">bgcolor="#D9EBF2"</c:if> align="center">
								<c:set var="texto" value="${documentoTO.descripcion}" /><c:set var="comita" value="\"" /><c:set var="espacio" value=" " />
								<c:set var="texto" value="${fn:replace(texto,comita,espacio)}" />
							<td><input type="radio" name="rdbdocumentoTO" id="${documentoTO.idDocumento}" 
								onClick="documentoTO('${documentoTO.idDocumento}|${documentoTO.nombre}|${texto}')"> 
							</td>
							<td style="text-transform: uppercase; width:95%">${documentoTO.descripcion}</td>
						</tr>
					</c:forEach>
					<tr class="healineblue1" bgcolor="#ECF0DB" align="center">
						<td colspan="2">&nbsp;Nombre Manuales&nbsp; </td>
					</tr>
					<c:forEach  var="documentoTO" items="${listaManualesTO}" varStatus="total"><c:set var="contador" value="${total.count}"/>
						<tr id="linea" class="InputC" <c:if test="${contador %2 !=0 }">bgcolor="#D9EBF2"</c:if> align="center">
								<c:set var="texto" value="${documentoTO.descripcion}" /><c:set var="comita" value="\"" /><c:set var="espacio" value=" " />
								<c:set var="texto" value="${fn:replace(texto,comita,espacio)}" />
							<td><input type="radio" name="rdbdocumentoTO" id="${documentoTO.idDocumento}" 
								onClick="documentoTO('${documentoTO.idDocumento}|${documentoTO.nombre}|${texto}')"> 
							</td>
							<td style="text-transform: uppercase; width:95%">${documentoTO.descripcion}</td>
						</tr>
					</c:forEach>
			</table>
		</div>
	</securityCa:validaPerfil>
</body>
</html>