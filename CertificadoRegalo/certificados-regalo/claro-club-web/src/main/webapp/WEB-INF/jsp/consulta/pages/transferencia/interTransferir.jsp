<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<html>
    <head>
        <title>Intermedio Transferencia</title>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
        <link rel="stylesheet" href='<c:url value="/commons/js/sc_textsheet.css"/>' type="text/css">
        <script type="text/javascript">
        	function envia(){        		
        		frmTransfiere.submit();
        	}
        </script>
    </head>
    <body onload="envia();">
    	<div class='Titulo' style="position: absolute; top:170px; left:180px;">
    		Procesando Informaci&oacute;n.....
			<img src='<c:url value="/commons/images/await.gif"/>'>
		</div>
        
        <!-- Valores para detalle de consulta -->
   		<c:if test='${transferenciaIN.vista=="I"}'>    
	    	<form name="frmTransfiere" method="post" action="./transferenciaDetalle.do">
		    	<input type="hidden" name="telefono" value="${transferenciaIN.telefono}">
		    	<input type="hidden" name="cuenta" value="${transferenciaIN.cuenta}">
		    	<input type="hidden" name="region" value="${transferenciaIN.region}">
		    	<input type="hidden" name="tipoTransferencia" value="${transferenciaIN.tipoTransferencia}">	
		    	<input type="hidden" name="vista" value="C">
		    </form>
	    </c:if>
	    	
	    <!-- Valores para realizar transferencia -->
	    <c:if test='${transferenciaIN.vista=="D"}'>
	    	<form name="frmTransfiere" method="post" action="./aplicaTransferencia.do">
		    	<input type="hidden" name="telefono" value="${transferenciaIN.telefono}">
		    	<input type="hidden" name="cuenta" value="${transferenciaIN.cuenta}">
		    	<input type="hidden" name="region" value="${transferenciaIN.region}">		    	
		    	<input type="hidden" name="tipoTransferencia" value="${transferenciaIN.tipoTransferencia}">
		    	<input type="hidden" name="fecFactura" value="${transferenciaIN.fecFactura}">
		    	<input type="hidden" name="fechaAlta" value="${transferenciaIN.fechaAlta}">	
	
		    	<input type="hidden" name="telefonoDestino" value="${transferenciaIN.telefonoDestino}">
			    <input type="hidden" name="cuentaDestino" value="${transferenciaIN.cuentaDestino}">
			    <input type="hidden" name="regionDestino" value="${transferenciaIN.regionDestino}">
			    <input type="hidden" name="puntosTrans" value="${transferenciaIN.puntosTrans}">
			    <input type="hidden" name="comentario" value="${transferenciaIN.comentario}">
			    <input type="hidden" name="cuentaPadreOrigen" value="${transferenciaIN.cuentaPadreOrigen}">
			    <input type="hidden" name="cuentaLineaOrigen" value="${transferenciaIN.cuentaLineaOrigen}">
			    <input type="hidden" name="estatus" value="${transferenciaIN.estatus}">
			    <input type="hidden" name="tecnologia" value="${transferenciaIN.tecnologia}">
			    <input type="hidden" name="nombre" value="${transferenciaIN.nombre}">
			    <input type="hidden" name="secuencia" value="${transferenciaIN.secuencia}">			    
			    <input type="hidden" name="vista" value="A">
			    <!-- JSC - Folio: 96556 -->
			    <input type="hidden" name="rfcOrigen" value="${transferenciaIN.rfcOrigen}">
			</form>
	    </c:if>
    	
    </body>
</html>
