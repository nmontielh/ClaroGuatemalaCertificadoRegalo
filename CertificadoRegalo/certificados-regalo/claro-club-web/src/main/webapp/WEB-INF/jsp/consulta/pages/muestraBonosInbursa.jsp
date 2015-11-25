<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>

<html>
<head>
<script>
	function imprimeIndividual(telefono, cuenta){
		document.parentWindow.parent.act_cargando(); 
		valor = window.showModalDialog(
		   "./impresionBonoInbursa.do?telefono="+telefono+"&cuenta=" + cuenta + "&individual=1",
		   "","dialogHeight: 750px;dialogWidth: 830px; center: Yes; help: No; resizable: NO; status: No;");
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
                  <th width="10%" >Folio</th>
                  <th width="10%" >Fecha de Impresión</th>
                  <th width="10%">Fecha de Expiracion</th>
                  <th width="10%">Descuento Inbursa </th>
                  <th width="10%">Descuento Marca </th>
              </tr>
              <c:set var="contador" value="0"/>
              <c:forEach var="impresionTO" items="${impresion}" varStatus="total">
              <c:set var="contador" value="${total.count}"/>   
              <tr class="X3" bgcolor="<c:if test="${contador %2 !=0 }">#D9EBF2</c:if>" >
              	<td align="left" height="20">
              		<jsp:useBean id="fechaActual" class="java.util.Date" scope="request" />
              		<fmt:formatDate value="${fechaActual}" pattern="yyyy-MM-dd" var="fechaActualFormato"/>
              		<c:if test="${impresionTO.fechaOperacion==fechaActualFormato}">
              			<input type="radio" id="row" name="row" onclick="imprimeIndividual('${impresionTO.telefono}','${impresionTO.cuenta}')">
              		</c:if><fmt:formatNumber groupingUsed="false" minIntegerDigits="8" value="${impresionTO.folio}"/>
              	</td>
              	<td align="left">&nbsp;
              			${impresionTO.fechaOperacion}
              	</td>
				<td align="left">&nbsp;
              			${impresionTO.fechaPlazoSeg}
              	</td>
				<td align="left">&nbsp;${impresionTO.inbursaFormato}</td>
				<td align="left">&nbsp;${impresionTO.marcaFormato}</td>
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
       					<font color="red" >&nbsp;&nbsp; La linea no cuenta con Bono de Descuento.</font>
       				</td>
       			<td width="10%">&nbsp;</td>
       		  </tr>
          </table>          	
          </c:if>
          
      </DIV> 
    </form>
</body>
</html>