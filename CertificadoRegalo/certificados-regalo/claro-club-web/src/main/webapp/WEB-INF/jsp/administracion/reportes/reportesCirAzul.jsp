<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/permisos.tld" prefix="securityCa" %>
<html>
<head>
	<title>Reportes Circulo Azul</title>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	
	<link rel="stylesheet" href='<c:url value="/commons/js/sc_textsheet.css"/>' type="text/css">
	<link rel="stylesheet" href='<c:url value="/commons/js/sc_textsheet.css"/>' type="text/css">
    <link rel="stylesheet" href='<c:url value="/commons/css/calendar-blue.css"/>' type="text/css">
    <script src='<c:url value="/commons/js/calendar.js"/>' type="text/javascript"></script>
    <script src='<c:url value="/commons/js/calendar-es.js"/>' type="text/javascript"></script>
    <script src='<c:url value="/commons/js/calendar-setup.js"/>' type="text/javascript"></script>
    
    <script type="text/javascript">
    	window.onload = function() {	
			Calendar.setup({
		    	inputField: "fechaIni",
		    	ifFormat:   "%d/%m/%Y",
		    	button:     "fechaIni"
		  		});
			
			Calendar.setup({
		    	inputField: "fechaFin",
		    	ifFormat:   "%d/%m/%Y",
		    	button:     "fechaFin"
		  		});			
			};
			
			function exportar(opcion){
			
				var idReporte = document.getElementById("idReporte");
				var idRegion = document.getElementById("idRegion");
				var fechaIni = document.getElementById("fechaIni");
				var fechaFin = document.getElementById("fechaFin");
				
				if(idReporte.value==0){
					alert("Debe elegir un tipo de reporte.");
					return false;
				}
				if(idRegion.value==-1){
					alert("Debe elegir una región.");
					return false;
				}
				if(fechaIni.value==null || fechaIni.value==""){
					alert("Debe elegir una Fecha inicial.");
					return false;
				}
				if(fechaFin.value==null || fechaFin.value==""){
					alert("Debe elegir una Fecha final.");
					return false;
				}
				
				if(opcion!=6){					
					FrameDetalle.location = './generaReporteCa.do?idReporte=' + idReporte.value + '&idRegion=' + idRegion.value + '&fechaIni=' + fechaIni.value + '&fechaFin=' + fechaFin.value;			
				
					var detalle = document.getElementById("TarjetaDetalle");
					detalle.style.visibility="visible";
					detalle.style.display="block";
				}else{
					var archivo = document.getElementById("archivoReden");
					var extencion = archivo.value.substring(archivo.value.length-3, archivo.value.length).toUpperCase();
					
					if(archivo.value==null || archivo.value==""){
						alert("Debe específicar el archivo de texto a procesar.");
						return false;
					}
		
					if(extencion != "TXT"){
						alert("Formato de archivo no valido, solo se aceptan archivos txt");
						return false;
					}				
				
					var form = document.getElementById("frmReportes");
					form.action = './generaReporteCa.do?idReporte=' + idReporte.value + '&idRegion=' + idRegion.value + '&fechaIni=' + fechaIni.value + '&fechaFin=' + fechaFin.value;
					form.submit();
				}
			}
			function selectTipoReporte(opcion){
				var tdArchivo = document.getElementById("tdArchivo");
				var archivoReden = document.getElementById("archivoReden");
				if(opcion==6){
					tdArchivo.style.visibility = "visible";
					archivoReden.style.visibility = "visible";
				}else{
					tdArchivo.style.visibility = "hidden";
					archivoReden.style.visibility = "hidden";
				}
				var tarjetaDetalle = document.getElementById("TarjetaDetalle");
				tarjetaDetalle.style.visibility = "hidden";
				tarjetaDetalle.style.display = "none";
			}
    </script>

</head>
<body marginwidth="0" marginheight="0" style="margin: 0px" background="<c:url value="/commons/images/backgroundlight.gif"/>">

	<form action="./generaReporteCa.do" method="post" id="frmReportes" enctype="multipart/form-data" name="frmReportes">     
		<div>
			<table width="99%" align="center">
				<tr>
					<td height="45" class="titulo">Reportes Círculo Azul</td>
				</tr>
			</table>
			<DIV style="width: 98%; FILTER: progid:DXImageTransform.Microsoft.Shadow(color='#aaaacc', Direction=135, Strength=2); 
				border-bottom-width: 2px; border-bottom-style: solid; border-bottom-color: #4d7097; font-size: 1px;">
			</DIV>
			<p></p>
			
			<table width="98%" align="center" cellspacing="0" cellpadding="0" align="left">
				<tr>
					<td class="healineblue1" height="36" width="96" align="right">Tipo Reporte:</td>
					<td class="healineblue1" height="36" width="197">
						<select class="selectC" id="idReporte" style="width: 185px" onchange="selectTipoReporte(this.value);">
							<option value="0">Seleccione</option>
							<option value="1">Reporte Redenciones</option>
							<option value="2">Reporte Acumulados</option>
							<option value="3">Reporte Certificados Lealtad</option>
							<!-- <option value="4">Reporte Retención</option> -->
							<option value="5">Reporte Roext</option>
							<option value="6">Reporte Redenciones Linea</option>
							<option value="7">Reporte Alianzas</option>
							<option value="8">Reporte Alto Valor</option>
							<option value="9">Reporte Comisiones</option>
							<option value="10">Reporte Puntos por Vencer</option>
							<option value="11">Reporte Redenciones Detalle</option>
							<option value="12">Reporte Redenciones Online</option>
							<option value="13">Reporte Inbursa renta gratis</option>
							<option value="14">Reporte Inbursa 100 minutos</option>
							<option value="15">Reporte Inbursa Desc. 1000</option>
							<option value="16">Reporte Inbursa Pqts. min.</option>
						</select>
					</td>
					<td class="healineblue1" height="36" width="82" align="right">Región</td>
					<td class="healineblue1" height="36" width="324">
						<select class="selectC" id="idRegion" style="width: 90px">
							<option value="-1">Seleccione</option>
							<option value="1">1</option>
							<option value="2">2</option>
							<option value="3">3</option>
							<option value="4">4</option>
							<option value="5">5</option>
							<option value="6">6</option>
							<option value="7">7</option>
							<option value="8">8</option>
							<option value="9">9</option>
							<option value="0">Todas</option>							
						</select>
					</td>
					<td width="469"></td>
				</tr>
				<tr>
					<td class="healineblue1" height="11" width="96" align="right">Fecha Inicial:</td>
					<td class="healineblue1" height="11" width="197">
						<input class="InputText" type="text" name="fechaIni" id="fechaIni" readonly="readonly" align="middle">
					</td>
					<td class="healineblue1" height="11" width="82" align="right">Fecha Final:</td>
					<td class="healineblue1" height="11" width="324">
						<input class="InputText" type="text" name="fechaFin" id="fechaFin" readonly="readonly" align="middle" />
					</td>
					<td width="469">
						<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="167">
						
							<a class="LinkOut" style="width:100px" onmouseover='this.className="LinkIn";' onmouseout='this.className="LinkOut";'
		                    	id="Link1" onClick="exportar(idReporte.value);">&nbsp;Exportar&nbsp;&nbsp;</a>
						
						</securityCa:validaPerfil>
					</td>
									
				</tr>
				<tr>
					<td id="tdArchivo" class="healineblue1" height="11" width="96" align="right" style="visibility: hidden">Archivo:</td>
					<td class="healineblue1" height="11" colspan="3">
						<input class="InputText"  type="file" name="archivoReden" id="archivoReden" align="middle" style="width: 95%;visibility: hidden;">
					</td>
					<td width="469">						
					</td>				
				</tr>
								
			</table>			
		</div>
		<DIV id="TarjetaDetalle" class="TarjetaDetalle" style="top:140px;height: 300px;width: 97%;visibility: hidden;display: none;left: 10px;position:absolute;BORDER:solid 2px #4d7097;background: transparent;">
			<IFRAME name="FrameDetalle" id="FrameDetalle" scrolling="yes" WIDTH="100%" HEIGHT="100%" style="border:solid; background: transparent;  " frameborder="0"></IFRAME>
		</DIV>
</form>

</body>
</html>