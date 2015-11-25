<!-- JSC - Folio: 117285 -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"><%@page
	language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<head>
<title>imei</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript">
function GuardaImei(){

	if (!document.parentWindow.parent.document.getElementById("btnValidaImei").disabled) {
		if('${banImei}'== '2'){
			window.alert("El IMEI ya se encuentra previamente registrado. Favor de validar.");
			document.parentWindow.parent.document.getElementById("btnValidaImei").disabled		= true;
			document.parentWindow.parent.document.getElementById("coment").disabled			= true;
			document.parentWindow.parent.document.getElementById("btnRedencion").disabled	= true;
			
			var registros = document.parentWindow.parent.document.getElementById("RangosMultiplesTBL").rows.length;
			
			for(i=1;i<registros  ;i++){
				document.parentWindow.parent.document.getElementById("selecProd"+i).checked=false;
				document.parentWindow.parent.document.getElementById("esnimeiT"+i).value="";
			}
			
		} else {
			document.parentWindow.parent.document.getElementById("btnValidaImei").disabled	= true;
    		document.parentWindow.parent.document.getElementById("coment").disabled			= false;
			document.parentWindow.parent.document.getElementById("btnRedencion").disabled	= false;
		}
	}
}
</script>
</head>
<body  onload="GuardaImei();"  style="background-color: transparent" >
</body>
</html>