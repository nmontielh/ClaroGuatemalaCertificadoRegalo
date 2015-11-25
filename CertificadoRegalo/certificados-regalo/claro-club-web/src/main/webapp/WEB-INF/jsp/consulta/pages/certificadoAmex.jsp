<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
<script>

function imprimeIndividual(folio,cuenta,secuencia,telefono){
	document.parentWindow.parent.act_cargando(); 
	valor = window.showModalDialog("./impresion.do?folio="+folio+
			"&cuenta="+cuenta+"&secuencia="+secuencia+"&certificado="+document.getElementById("certificado").value
			+"&telefono="+telefono,
			"","dialogHeight: 750px;dialogWidth: 750px; center: Yes; help: No; resizable: Yes; status: No;");
	document.parentWindow.parent.des_cargando();
}
</script>
<title>Impresion de Certificados Amex</title>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
    <link rel="stylesheet" href='<c:url value="/commons/js/sc_textsheet.css"/>' type="text/css">
</head>
<body  marginwidth="0" marginheight="0" style="MARGIN: 0px;background-color: transparent; border: none;">
<script>	
    document.parentWindow.parent.des_cargando(); 
</script>
     <form id="frmCertificado" name="frmCertificado" >
      <input type="hidden" id="certificado" name="certificado" value="1">
      <input type="hidden" id="folio" name="folio" value="">
      <input type="hidden" id="cuenta" name="cuenta" value="">
      <input type="hidden" id="secuencia" name="secuencia" value="">
      <input type="hidden" id="telefono" name="telefono" value="">
      	<div id="resumen" style="visibility: hidden MARGIN-TOP: 0px; OVERFLOW-Y: hidden; OVERFLOW-X: hidden; width:735px; height:inherit; position:absolute;top: 10px;display: block;" >
            <table width="98%"  cellspacing="0" cellpadding="0" align="center"  >
                <tr> 
                    <td colspan="3" class="titulo" height="42" width="100%" >&nbsp;&nbsp;Impresión de Certificado de Viaje</td>
                </tr>
            </table>
            <table width="98%" border="1" cellspacing="0" cellpadding="0" align="center">
		        <tr> 
		           <td width="20%" class="healineblue1" bgcolor="#ECF0DB" >&nbsp;Nombre:</td>
		           <td class="textonegroBlod"  >&nbsp;${telefonoTO.mobileTO.nombre}</td>
		           <td width="20%" class="healineblue1" bgcolor="#ECF0DB" >&nbsp;Alianza:</td>
		           <td class="textonegroBlod"  >&nbsp;AMERICAN EXPRESS</td>
		        </tr>
		        <tr> 
		           <td width="20%" class="healineblue1" bgcolor="#ECF0DB" >&nbsp;Teléfono:</td>
		           <td class="textonegroBlod"  >&nbsp;${telefonoTO.mobileTO.telefono}</td>
		           <td width="20%" class="healineblue1" bgcolor="#ECF0DB" >&nbsp;Cuenta:</td>
		           <td class="textonegroBlod"  >&nbsp;${telefonoTO.mobileTO.cuenta}</td>
		        </tr>
            </table>   
         	<br>
 		 	<br>
            <table width="98%" border="1" cellspacing="0" cellpadding="0" align="center" >       
		          <tr bgcolor="#ECF0DB" class="healineblue1" align="center" >		          
		             <th width="10%" >Folio</th>
		             <th width="13%">Fecha de expedición</th>
		             <th width="13%">Puntos canjeados</th>
		             <th width="10%">Valor que ampara el certificado</th>   
		            </tr>
		            <c:set var="contador" value="0"/>
		            <c:forEach var="alianzasTO" items="${impresionTO.alianzas}" varStatus="total">
		            <c:set var="contador" value="${total.count}"/>   
		            <tr class="X3"  bgcolor="<c:if test="${contador %2 !=0 }">#D9EBF2</c:if>" >
						<td align="left"><input type="radio" id="row" name="row" 
						onclick="imprimeIndividual('${alianzasTO.folio}','${telefonoTO.mobileTO.cuenta}','${telefonoTO.mobileTO.secuencia}','${telefonoTO.mobileTO.telefono}')">&nbsp;${alianzasTO.folio}</td>
						<td align="left">
							<!-- &nbsp;${alianzasTO.fechaOperacion} -->
							
							<fmt:parseDate value="${alianzasTO.fechaOperacion }" type="DATE" pattern="yyyy-MM-dd" var="formatedDate"></fmt:parseDate>
              				<fmt:formatDate value="${formatedDate }" pattern="dd-MM-yyyy" type="DATE"/>
							
						</td>
						<td align="left">&nbsp;${alianzasTO.ptsTransferidos}</td>
						<td align="left">&nbsp;${alianzasTO.valorCuponOrig}</td>
		              </tr>
		              </c:forEach>
		              <tr bgcolor="#ECF0DB" class="healineblue1" >
		       			<td colspan="4">&nbsp;<c:out value="${contador}"></c:out> Registro(s) Encontrado(s).</td>
		       		  </tr>	   
		    </table>
		  <br>  
		  <c:if test="${contador==0}">
          <table>
              <tr class="healineblue1" >
              	<td width="10%">&nbsp;</td>
       			<td colspan="1">
       			
       				<c:set var="telefonoTrim" value="${fn:trim(telefono) }"></c:set>
       				<c:choose>       					
       					<c:when test="${telefono!=null && fn:length(telefonoTrim)>0 }">
       						<font color="red" >&nbsp;&nbsp; No hay registros para la fecha solicitada. Realice la busqueda con la Cuenta.</font>
       					</c:when>
       					<c:otherwise>
       						<font color="red" >&nbsp;&nbsp; No hay registros para la fecha solicitada.</font>
       					</c:otherwise>
       				</c:choose>
       			</td>
       			<td width="10%">&nbsp;</td>
       		  </tr>
          </table>          	
    	</c:if>
         </div>
    </form>          
</body>
</html>