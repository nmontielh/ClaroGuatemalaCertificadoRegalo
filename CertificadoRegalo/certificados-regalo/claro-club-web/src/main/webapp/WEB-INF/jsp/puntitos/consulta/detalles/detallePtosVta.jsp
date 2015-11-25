<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="/spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Administración de Puntos de Venta</title>
<link rel="stylesheet" type="text/css" href='<c:url value="/commons/css/impromptus.css"/>' media='screen' >
<script type="text/javascript" src='<c:url value="/commons/js/jquery-1.2.3.pack.js"/>'></script>
<script type='text/javascript' src='<c:url value="/commons/js/jquery.form.js"/>' ></script>
<script type="text/javascript" src='<c:url value="/commons/js/jquery-impromptu.1.3.js"/>' ></script>
<script type="text/javascript" src='<c:url value="/commons/js/puntitos.js"/>' ></script>
</head>
<body background="<c:url value="/commons/images/backgroundlight.gif"/>">
<form name="frmDetPtosVta" id="frmDetPtosVta" action="./PtoVtaController.do" method="post" AUTOCOMPLETE="OFF">
<div id="listados" align="left">
<table id="tablaLista" border="1" cellspacing="0" cellpadding="0" width="600px">
	  <tbody>
	  <c:if test="${listaPuntoVentaTO!=null}">
	  	<c:forEach  var="IDpuntoVentaTO" items="${listaPuntoVentaTO}">
			<tr id="linea" class="InputC" bgcolor="#ECF0DB" align="center">
				<td width="20px"><input type="radio" name="rdbPuntoVta" id="${IDpuntoVentaTO.idPuntoVta}" 
					onClick="puntoVta('${IDpuntoVentaTO.idPuntoVta}|${IDpuntoVentaTO.segmentoIP}|${IDpuntoVentaTO.rangoInf}|${IDpuntoVentaTO.rangoSup}|${IDpuntoVentaTO.idRegion}|${IDpuntoVentaTO.ivaProcentaje}')"> 
				</td>
				<td width="170px">&nbsp;${IDpuntoVentaTO.idPuntoVta}&nbsp;</td> 
				<td width="115px">&nbsp;${IDpuntoVentaTO.segmentoIP}&nbsp;</td>
				<td width="60px">&nbsp;${IDpuntoVentaTO.rangoInf}&nbsp;</td>
				<td width="60px">&nbsp;${IDpuntoVentaTO.rangoSup}&nbsp;</td>
				<td width="45px">&nbsp;${IDpuntoVentaTO.idRegion}&nbsp;</td>
				<td width="30px">&nbsp;${IDpuntoVentaTO.ivaProcentaje}&nbsp;</td>
			</tr>
		</c:forEach>
	  </c:if>
	  </tbody>
	</table>
</div>
<input type="hidden" name="idPuntoVta" id="idPuntoVta" />
<input type="hidden" name="segmentoIP" id="segmentoIP" />
<input type="hidden" name="rangoInf" id="rangoInf" />
<input type="hidden" name="rangoSup" id="rangoSup" />
<input type="hidden" name="idRegion" id="idRegion" />
<input type="hidden" name="ivaProcentaje" id="ivaProcentaje" />
<input type="hidden" name="accion" id="accion" value="Consultar" />
<center><h3><font color="white" class="InputB"></font>${error}</h3></center>
<spring:hasBindErrors name="puntoVentaTO">
      <div align="center"  class="BloqueErrorEspera">
            Hay ${errors.errorCount} error(es):
            <ul>
                  <c:forEach var="errMsgObj" items="${errors.allErrors}" >
                        <li><spring:message code="${errMsgObj.code}"
                             text="${errMsgObj.defaultMessage}" /></li>
                  </c:forEach>
            </ul>
      </div>
</spring:hasBindErrors>
<c:if test="${errors.errorCount>0}">
<c:forEach var="error" items="${errors.allErrors}" >
                        <li><spring:message code="${errMsgObj.code}"
                             text="${errMsgObj.defaultMessage}" /></li>
</c:forEach>
</c:if>
</form>
</body>
</html>