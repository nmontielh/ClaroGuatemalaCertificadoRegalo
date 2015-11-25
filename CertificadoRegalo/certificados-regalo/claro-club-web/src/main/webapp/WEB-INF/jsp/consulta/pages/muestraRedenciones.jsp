<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>

<html>
<head>
<script>
	function imprimeIndividual(cont, fecha,folio ){
		var tel = document.parentWindow.parent.frmContenedor.tel.value;
		var cta = document.parentWindow.parent.frmContenedor.cta.value;
		document.parentWindow.parent.frmContenedor.telefono.value = tel;
		document.parentWindow.parent.frmContenedor.cuenta.value = cta;
		document.parentWindow.parent.frmContenedor.fecha.value = fecha;
		document.parentWindow.parent.frmContenedor.folio.value = folio;
		document.parentWindow.parent.act_cargando(); 
		valor = window.showModalDialog(
		   "./impresionGeneral.do?telefono="+tel+"&cuenta=" + cta + "&fecha="+fecha+"&folio="+folio+"&individual=1",
		   "","dialogHeight: 750px;dialogWidth: 870px; center: Yes; help: No; resizable: Yes; status: No;");
		document.parentWindow.parent.des_cargando();
		
	}
	

</script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href='/ClaroClubWeb/commons/js/sc_textsheet.css' type="text/css">
<title>Resultado Impresiones</title>
</head>
<body style="background-color: transparent;" >
<script>	
    document.parentWindow.parent.des_cargando(); 
</script>
 <form name="frmdetalleRed" id="frmdetalleRed">
  <input type="hidden" name="opcionS" id="opcionS" value="super">
  <input type="hidden" name="telefono" id="telefono" value="">
  <input type="hidden" name="fecha" id="fecha" value="">
  <input type="hidden" name="folio" id="folio" value="">
  <input type="hidden" name="individual" id="individual" value="1">
 	<DIV id="divDetalle" style="visibility: hidden MARGIN-TOP: 0px; OVERFLOW-Y: scroll; OVERFLOW-X: hidden; BORDER:solid 1px silver; width:980px; 280px;position: absolute;top: 10px;display: block;"> 	   	                    
       <table border="1" cellspacing="0" cellpadding="0" align="center" >       
              <tr bgcolor="#ECF0DB" class="healineblue1" align="center" >
                  <th width="17%" >Fecha Operación</th>
                  <th width="8%">Tipo Redencion </th>
                  <th width="8%">Producto </th>
                  <th width="10%">Marca </th>
                  <th width="9%">Modelo </th>
                  <th width="9%">Valor Puntos </th>
                  <th width="10%">Dif. Pesos</th>
                  <th width="10%">Usuario </th>
              </tr>
              <c:set var="contador" value="0"/>
              <c:forEach var="impresionTO" items="${impresion}" varStatus="total">
              <c:set var="contador" value="${total.count}"/>   
              <tr class="X3" bgcolor="<c:if test="${contador %2 !=0 }">#D9EBF2</c:if>" >
              	<td align="left"><input type="radio" id="row" name="row" 
              			onclick="imprimeIndividual('${total.count}','${impresionTO.fechaF}','${impresionTO.folio}')">
              			
              			<fmt:parseDate value="${impresionTO.fechaFolio }" type="DATE" pattern="yyyy-MM-dd" var="formatedDate"></fmt:parseDate>
              			<fmt:formatDate value="${formatedDate }" pattern="dd-MM-yyyy" type="DATE"/>
              	</td>
              	
				<td align="left">&nbsp;${impresionTO.tipoReden}</td>
				<td align="left">&nbsp;${impresionTO.idProducto}</td>
				<td align="left">&nbsp;${impresionTO.marca}</td>
				<td align="left">&nbsp;${impresionTO.modelo}</td>
				<td align="left">&nbsp;${impresionTO.valorPuntosCF}</td>
				<td align="left">&nbsp;${impresionTO.precioIvaFormato}</td>
				<td align="left">&nbsp;${impresionTO.idUsuario}</td>
              </tr>
            </c:forEach>
              <tr bgcolor="#ECF0DB" class="healineblue1" >
       			<td colspan="8">&nbsp;<c:out value="${contador}"></c:out> Registro(s) Encontrado(s).</td>
       		  </tr>
          </table>
          <c:if test="${contador==0}">
          <table>
              <tr class="healineblue1" >
              	<td width="10%">&nbsp;</td>
       			<td colspan="1">
       					<c:choose>
       						<c:when test="${telefono!=null }">
       							<font color="red" >&nbsp;&nbsp; No hay registros para la fecha solicitada. Realice la consulta por Cuenta.</font>
       						</c:when>
       						<c:otherwise>
       							<font color="red" >&nbsp;&nbsp; No hay registros para la fecha solicitada. </font>
       						</c:otherwise>
       					</c:choose>		
       				</td>
       			<td width="10%">&nbsp;</td>
       		  </tr>
          </table>          	
          </c:if>
          
      </DIV> 
    </form>
</body>
</html>