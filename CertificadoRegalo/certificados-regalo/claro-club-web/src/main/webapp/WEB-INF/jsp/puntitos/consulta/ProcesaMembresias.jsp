<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page	language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt'%>     
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix='f' uri="http://java.sun.com/jstl/fmt_rt"%>
<%@ taglib prefix="spring" uri="/spring"%>
<%@ taglib uri="/WEB-INF/permisos.tld" prefix="securityCa" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Procesa Membresías</title>
<link rel="stylesheet" type="text/css" href='<c:url value="/commons/css/impromptus.css"/>' media='screen' >
<link rel="stylesheet" href='<c:url value="/commons/js/sc_textsheet.css"/>' type="text/css">

<script type="text/javascript" src='<c:url value="/commons/js/jquery-1.2.3.pack.js"/>'></script>
<script type='text/javascript' src='<c:url value="/commons/js/jquery.form.js"/>' ></script>
<script type="text/javascript" src='<c:url value="/commons/js/jquery-impromptu.1.3.js"/>' ></script>
<script type="text/javascript" src='<c:url value="/commons/js/puntitos.js"/>' ></script>
</head>
<body>
<table width="100%">
	<tr><td	class="subtitulo">Procesa Membres&iacute;as</td>
		<td align="right" >
			<div class="txtSumNormal" id="divLoading" style="display: none;">
				<img src="<c:url value="/commons/images/load.gif"/>" height="25" width="25"/> Cargando infomaci&oacute;n &nbsp;&nbsp; </div> </td>	
	</tr>
</table>
<DIV style="width: 100%; FILTER: progid:DXImageTransform.Microsoft.Shadow(color='#aaaacc', Direction=135, Strength=2); 
	border-bottom-width: 2px; border-bottom-style: solid; border-bottom-color: #4d7097; font-size: 1px;">
</DIV>
<form action="./procesaArchivoMembresia.do" method="post" id="formProcesa" enctype="multipart/form-data" name="formProcesa">
<table width="80%">
 <tr>
  	<td class="subtitulo" colspan="2">Datos Generales</td>
  </tr>
   <tr>
  	<td class="healineblue1" width="25%">&nbsp;Region&nbsp;</td>
    <td width="70%">
    	<select name="idRegion" id="idRegion">
    		<option></option>
    		<option value="1">1</option>    		
    		<option value="2">2</option>
    		<option value="3">3</option>
    		<option value="4">4</option>
    		<option value="5">5</option>
    		<option value="6">6</option>
    		<option value="7">7</option>
    		<option value="8">8</option>
    		<option value="9">9</option>
    	</select>    	
	</td>
  </tr>
  
  <tr>
  	<td class="healineblue1" width="25%">&nbsp;Archivo&nbsp;</td>
    <td width="70%">
    	<input type="file" name="archivo" id="archivo" style="width:95%"/>
  	</td>
  </tr>
  
  <tr>
    <td colspan="2" align="right">
    	<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="170">
	    	<a class="LinkOut" style="width:30; text-align:center" onmouseover='this.className="LinkIn";'
					onmouseout='this.className="LinkOut";' id="btnArchivo" onClick="procesaArchivoMembresia('formProcesa');">&nbsp;PROCESAR&nbsp;&nbsp;</a>
		</securityCa:validaPerfil>
    </td>
  </tr>
  <tr><td colspan="2" align="justify">${mensaje}</td></tr>
  </table>
</form>
</body>
</html>