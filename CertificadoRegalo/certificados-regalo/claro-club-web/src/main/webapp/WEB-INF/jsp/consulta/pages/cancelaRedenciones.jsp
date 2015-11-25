<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/permisos.tld" prefix="securityCa" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href='<c:url value="/commons/js/sc_textsheet.css"/>' type="text/css">
<title>Cancela Redenciones</title>
<script >
	
	function fnCancelar(fechafoliolong,folio){
		document.getElementById("divComentario").style.visibility="visible";
		document.getElementById("divComentario").style.display="block";
		document.getElementById("fechafolio").value=fechafoliolong;	
		document.getElementById("folio").value=folio;
	}
	function fnAceptar(){		
		document.getElementById("divComentario").style.visibility="hidden";
		document.getElementById("divComentario").style.display="none";							
		//document.getElementById("fechafolio").value=document.getElementById("rFechaFolio").value;		
		document.getElementById("frmCancela").submit();			
	}	
	function cerrar(){
		document.getElementById("divComentario").style.visibility="hidden";
		document.getElementById("divComentario").style.display="none";
	}	
</script>
</head>
<body style="background-color: transparent;" >
			
	<DIV id="divRedencion"  style="MARGIN-TOP: 0px; OVERFLOW-Y: scroll; OVERFLOW-X: hidden; BORDER:solid 1px silver; HEIGHT: 280px;position: absolute;top: 0px;visibility: visible;display: block;">
        <table border="1" cellspacing="0" cellpadding="0" align="center" > 
            <tr bgcolor="#ECF0DB" class="healineblue1" align="center"> 
                <td width="11%">Fecha Operación </td>
                <td width="10%">Producto </td>
                <td width="8%">Marca </td>
                <td width="8%">Modelo </td>
                <td width="9%">Valor Puntos </td>
                <td width="8%">Diferencia Pesos</td>
                <td width="9%">Usuario </td>                
            </tr>
            <c:set var="contador" value="0"/>
            <c:forEach var="redencionTO" items="${redencionesTO}" varStatus="total">
            <c:if test="${redencionTO.tipoRedencion!='T'}">
           	 <c:set var="contador" value="${1+contador}"/>   
            	<tr class="X3" bgcolor="<c:if test="${contador %2 !=0 }">#D9EBF2</c:if>">
                <td align="left">
	            
	                	<input type="radio" name="rFechafolio" id="rFechafolio" onclick="fnCancelar('${redencionTO.fechaFoliolong}','${redencionTO.folio}')" value="${redencionTO.fechaFoliolong}">
	             
	                &nbsp;${redencionTO.fechaOperacion}</td>
	                
	                <c:if test="${redencionTO.tipoRedencion!='A'}">
	                	<td align="left">M-&nbsp;${redencionTO.productosTO.material}</td>
	                </c:if>
	                 
	                <c:if test="${redencionTO.tipoRedencion=='A'}">
	                	<td align="left">&nbsp;${redencionTO.productosTO.descripcion}</td>
	                </c:if>
	                <td align="left">&nbsp;${redencionTO.productosTO.marca}</td>
	                <td align="left">&nbsp;${redencionTO.productosTO.modelo}</td>
	                <td align="right">&nbsp;${redencionTO.puntosRedimidosTO.ptsTotaltesconFormato}</td>
	                <td align="right">&nbsp;${redencionTO.productosTO.precioIvaConFormato}</td>
	                <td align="center">&nbsp;${redencionTO.usuarioTO.idUsuario}</td>                    
            	</tr>
            </c:if>
            </c:forEach>
        </table>       
     </DIV>
     <DIV id="divTotal" style="height:50px;position: absolute;top: 280px;">
      <table style="width: 98%">
       	<tr bgcolor="#ECF0DB" class="healineblue1">
       		<td>&nbsp;<c:out value="${contador}"></c:out> Registro(s) Encontrado(s).</td>
       	</tr>
      </table>
      </DIV>
      <div id="divComentario" style="visibility: hidden;display: none;position: absolute;top:30px;left: 150px;width: 250px;height: 150px;" class="BloqueBlanco">
      	<form name="frmCancela" id="frmCancela" action="./redencionCancela.do" method="post">
      	<input type="hidden" name="telefono" id="telefono" value="${telefono}">
      	<input type="hidden" name="cuenta" 	 id="cuenta" value="${cuenta}">
      	<input type="hidden" name="region" 		id="region" value="${region}">
		<input type="hidden" name="secuencia" 	id="secuencia" value="${secuencia}">
		<input type="hidden" name="fechafolio" id="fechafolio" value="">
		<input type="hidden" name="folio" id="folio" value="">
      	<table width="100%">
      		<tr bgcolor="#ECF0DB" class="healineblue1">
      			<td colspan="2" width="100%">
      				Comentario
      			</td>      			
      		</tr>
      		<tr>
      			<td colspan="2" width="100%">	
      				<textarea rows="5" cols="300" id="comentario" name="comentario" style="width: 100%;" ></textarea>
      			</td>
      		</tr>
      		<tr>
      			<td width="50%">
      				<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="90">
      					<input type="button" name="aceptar" value="Aceptar" onclick="fnAceptar()" class="submit">
      				</securityCa:validaPerfil>
      			</td>
      			<td width="50%">
      				<input type="button" name="aceptar" value="Cerrar" onclick="cerrar()" class="submit">
      			</td>
      		</tr>
      	</table>
      	</form>
      </div>

</body>
</html>