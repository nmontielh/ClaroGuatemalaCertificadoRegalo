<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href='<c:url value="/commons/js/sc_textsheet.css"/>' type="text/css">
<script type="text/javascript">
	function cerrar(){
		<c:if test="${planTO.idGrupoPromocion!=null}">
		window.returnValue ="${planTO.idGrupoPromocion}";
		</c:if>
		window.close();
	}
	
	function asignaValor(){
		document.parentWindow.parent.document.getElementById("gpoPromo").value=${planTO.idGrupoPromocion};
		document.parentWindow.parent.document.getElementById("adendumNvo").value=${planTO.adendum};
		document.parentWindow.parent.document.getElementById("porcentajeIva").value=${porcentajeIva};
		document.parentWindow.parent.document.getElementById("defineIva").value=${defineIva};
		document.parentWindow.parent.document.getElementById("ptsTotales").value=${puntosDisponibles};
		document.parentWindow.parent.document.getElementById("formaRedencion").value="${formaRedencion}";
		document.parentWindow.parent.document.getElementById("tipoRedencion").value="${tipoRedencion}";
		document.parentWindow.parent.document.getElementById("bContinuar").value="${btnContinuar}";		
		document.getElementById("TarjetaTipoRed").style.visibility = "visible";
		document.getElementById("TarjetaTipoRed").style.display = "block";
		
		if(document.parentWindow.parent.document.getElementById("bContinuar").value=="1"){
			var tipoReden = document.parentWindow.parent.document.getElementById("tipoRedencion").value;
			if(tipoReden=='CON'){
				document.parentWindow.parent.document.getElementById("btnContinuarCon").style.visibility="visible";
				document.parentWindow.parent.document.getElementById("btnContinuarCon").style.display="block";	
			}
			if(tipoReden=='CAREG'){
				document.parentWindow.parent.document.getElementById("btnContinuarCareg").style.visibility="visible";
				document.parentWindow.parent.document.getElementById("btnContinuarCareg").style.display="block";	
			}			
			if(tipoReden=='SIN'){
				document.parentWindow.parent.document.getElementById("btnContinuarSin").style.visibility="visible";
				document.parentWindow.parent.document.getElementById("btnContinuarSin").style.display="block";	
			}
			if(tipoReden=='ACA'){
				document.parentWindow.parent.document.getElementById("btnContinuarAmigoChip").style.visibility="visible";
				document.parentWindow.parent.document.getElementById("btnContinuarAmigoChip").style.display="block";	
			}
			if(tipoReden=='T3G'){
				document.parentWindow.parent.document.getElementById("btnContinuarT3G").style.visibility="visible";
				document.parentWindow.parent.document.getElementById("btnContinuarT3G").style.display="block";	
			}			
		}
	}
	
</script>
<title>Valida Plan</title>
</head>
<body onload="asignaValor()" style="background-color: transparent" >
<c:if test="${planTO.idGrupoPromocion!=null}">
<!--
	<script>
		cerrar();
	</script>
-->
</c:if>
<div id="TarjetaTipoRed" style="height: 30px;visibility: hidden;display: none;background-color: transparent;">
<table width="98%" border="1" cellspacing="0" cellpadding="0" align="center" style="background-color: transparent;">
        <tr>          
          <td width="17%" class="healineblue1" bgcolor="#ECF0DB"  align="left">&nbsp;Aplica Redención:</td>
          <td width="17%" class="textonegroBlod" align="left" id="descFormaRedencion">&nbsp;${descFormaRedencion}</td>
          <td width="17%" class="healineblue1" bgcolor="#ECF0DB" align="left">&nbsp;Tipo de Redención:</td>
          <td width="17%" class="textonegroBlod" id="descTipoRedencion">&nbsp;${descTipoRedencion}</td>
        </tr>           
        <tr>                  
          <td width="17%" class="healineblue1" bgcolor="#ECF0DB" align="left">&nbsp;Puntos Disponibles:</td>
          <td width="17%" class="textonegroBlod" id="puntosDisponibles"><font color=red>&nbsp;${puntosDisponibles}</font></td>   
          <td width="17%" class="healineblue1" bgcolor="#ECF0DB" align="left">&nbsp;Plan para Renovar:</td>
          <td width="17%" class="textonegroBlod" id="planNuevo">&nbsp;${planNuevo}</td>
        </tr> 
 </table>
 </div>
</body>
</html>