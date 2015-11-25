<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<html>
<head>
<script type="text/javascript">
	function carga(){
		superior.location="./impresion.do?opcion="+document.getElementById("opcionS").value;
	}
	function activaCargando(valor1,valor2){
		document.parentWindow.parent.activaCargando(valor1,valor2);
	} 
	
	function visualizaResultado(){
		document.getElementById("inferior").style.visibility="visible";
	}

	function act_cargando(){
		//alert("en act_cargando");
		document.getElementById("divLoaded").style.visibility="visible";
		document.getElementById("divLoaded").style.display="block";
		//alert("termina act_cargando");
	}

	function des_cargando(){
		//alert("en des_cargando");
		document.getElementById("divLoaded").style.visibility="hidden";
		document.getElementById("divLoaded").style.display="none";
		//alert("termina des_cargando");
	}
	
</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Menu Impresion</title>
   <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
   <link rel="stylesheet" href='<c:url value="/commons/js/sc_textsheet.css"/>' type="text/css">
</head>
<body  marginheight="0" marginwidth = "0" style="MARGIN: 0px;background-color: transparent; border: none;"  >
<form id="frmContenedor" name="frmContenedor">
<input type="hidden" name="tel"  id="tel" value="0">
<input type="hidden" name="cta"  id="cta" value="0">
<input type="hidden" name="telefono"  id="telefono" value="0">
<input type="hidden" name="cuenta"  id="cuenta" value="0">
<input type="hidden" name="fecha"  id="fecha" value="0">
<input type="hidden" name="folio"  id="folio" value="0">
<input type="hidden" name="individual"  id="individual" value="0">
<input type="hidden" name="opcionS" id="opcionS" value="super">
<iframe id="superior"  width="1000" height="200" scrolling="no" style="border: none;" 
	marginwidth="0" marginheight="0" name="superior" AllowTransparency src="./impresion.do?opcionS=super">
</iframe>
<div id="divLoaded"  class='Titulo' style="position: absolute; top:160px; left:300px; visibility: hidden">Procesando Informaci&oacute;n.....
	<img src='<c:url value="/commons/images/await.gif"/>'>
</div>
<iframe id="inferior"  width="999" height="260" style="border: none; visibility: hidden"
	marginwidth="0" marginheight="0" name="inferior"  AllowTransparency>
</iframe>
</form>
</body>
</html>