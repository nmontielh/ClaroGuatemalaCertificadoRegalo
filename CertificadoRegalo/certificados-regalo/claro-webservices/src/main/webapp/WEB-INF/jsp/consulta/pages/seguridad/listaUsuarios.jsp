
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<html>
<c:if test="${exporta == 1}">
	<% response.setContentType("application/vnd.ms-excel"); %>
	<meta http-equiv="Content-Type" content="application/vnd.ms-excel; charset=ISO-8859-1">
</c:if>
<c:if test="${exporta != 1}">
	<link rel="stylesheet" href='/ClaroClubWeb/commons/js/sc_textsheet.css' type="text/css">
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</c:if>
<head>
<title>Lista usuarios
</title>
</head>
<body style="background-color: transparent;"> 
 	<DIV id="divMovimiento" style="MARGIN-TOP: 0px; OVERFLOW-Y: scroll; OVERFLOW-X: hidden; BORDER:solid 1px silver; width:98%; HEIGHT: 90%;position: absolute;top: 10px;"> 	   	                    
       <table border="1" cellspacing="0" cellpadding="0" align="center" >       
              <tr bgcolor="#ECF0DB" class="healineblue1" align="center"> 
                  <th width="10%">Numero Empleado</th>
                  <th width="10%">Clave</th>
                  <th width="30%">Nombre</th>
                  <th width="10%">Perfil</th>
                  <th width="4%">Estatus</th>
                  <th width="13%">Fecha Actualizacion</th>
                  <th width="13%">Fecha Alta</th>
                  <th width="10%">Usuario Captura</th>
              </tr>
              <c:set var="contador" value="0"/>
              <c:forEach var="usuarioTO" items="${usuarios}" varStatus="total">
              <c:set var="contador" value="${total.count}"/> 
              <tr class="X3" bgcolor="<c:if test="${contador %2 !=0 }">#D9EBF2</c:if>">
                 <td >&nbsp;${usuarioTO.numEmpleado}</td>
                 <td >&nbsp;${usuarioTO.idUsuario}</td>
                 <td >&nbsp;${usuarioTO.nombre}</td>
                 <td >&nbsp;${usuarioTO.perfil}</td>
                 <td >&nbsp;${usuarioTO.status}</td>
                 <td >&nbsp;${usuarioTO.fechaUpdate}</td>
                 <td >&nbsp;${usuarioTO.fechaAdmin}</td>
                 <td >&nbsp;${usuarioTO.idUsuarioCaptura}</td>                                  
              </tr>
              </c:forEach>
              <tr bgcolor="#ECF0DB" class="healineblue1" >
       			<td colspan="8">&nbsp;<c:out value="${contador}"></c:out> Registro(s) Encontrado(s).</td>
       		  </tr>	
        </table>
      </DIV>
</body>
</html>
