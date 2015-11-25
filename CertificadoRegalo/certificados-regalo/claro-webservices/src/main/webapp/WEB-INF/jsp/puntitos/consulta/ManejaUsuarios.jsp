<%@ page language="java" contentType="text/html;"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt'%>     
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix='f' uri="http://java.sun.com/jstl/fmt_rt"%>
<%@ taglib prefix="spring" uri="/spring"%>
<%@ taglib uri="/WEB-INF/permisos.tld" prefix="securityCa" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="tipo_contenido"  content="text/html; charset=utf-8" http-equiv="content-type; charset=utf-8">

<title>Administración de Usuarios</title>
<link rel="stylesheet" type="text/css" href='<c:url value="/commons/css/impromptus.css"/>' media='screen' >
        
<script type="text/javascript" src='<c:url value="/commons/js/jquery-1.2.3.pack.js"/>'></script>
<script type='text/javascript' src='<c:url value="/commons/js/jquery.form.js"/>' ></script>
<script type="text/javascript" src='<c:url value="/commons/js/jquery-impromptu.1.3.js"/>' ></script>
<script type="text/javascript" src='<c:url value="/commons/js/puntitos.js"/>' ></script>

<script type="text/javascript">
	function enviar(){
		var form = document.getElementById('frmCapUsuario');
		var validaciones = AdminUsers();
		if(validaciones==true){			
			form.submit();
		}else{
			return false;
		}		
	}
	function lectura(){
		document.getElementById("idPerfilActual").value = '${UsuarioTO.perfilTO.idPerfilN}';
		document.getElementById("idUsuario").value='${UsuarioTO.idUsuario}';
		document.getElementById("nombre").value='${UsuarioTO.nombre}';
		document.getElementById("status").value='${UsuarioTO.status}';
		document.getElementById("perfil").value='${UsuarioTO.perfilTO.idPerfilN}-${UsuarioTO.perfilTO.idPuesto}';
		<c:set var="accion" value="${UsuarioTO.accion}" />
		<c:if test="${fn:containsIgnoreCase('Agregar', accion)}">
		
			var agregar = document.getElementById("Agregar");
			if(agregar!=null){
				agregar.disabled=false;
			}
		</c:if>
		<c:if test="${fn:containsIgnoreCase('Actualizar', accion)}">
			var actualizar = document.getElementById("Actualizar");
			if(actualizar!=null){
				actualizar.disabled=false;
			} 
			var reseteo = document.getElementById("Reseteo");
			if(reseteo!=null){
				reseteo.disabled=false;
			} 		
	    	var eliminar = document.getElementById("Eliminar");
	    	if(eliminar!=null){
	    		eliminar.disabled=false;
	    	}
		    
		</c:if>
	}
	function elimina(){
		document.getElementById('accion').value='Eliminar';
		var form = document.getElementById('frmCapUsuario');
		form.submit();
	}
	function limpiar(){
		document.getElementById('idUsuario').value='';
		document.getElementById('nombre').value='';
		document.getElementById('password').value='';
		document.getElementById('confirmacionPassword').value='';
		document.getElementById('status').value='Selecciona';
		document.getElementById('perfil').value='Selecciona';
	}
	function resetea(){
		var password = document.getElementById('password');
		var confirmacionPassword = document.getElementById('confirmacionPassword');
		password.value='';
		confirmacionPassword.value='';
	}
	
		function Activo(){
	if(document.getElementById('miCheck').checked==false){
	document.getElementById('password').disabled=true;
	document.getElementById('confirmacionPassword').disabled=true;
	}
	if(document.getElementById('miCheck').checked==true){
	document.getElementById('password').disabled=false;
	document.getElementById('confirmacionPassword').disabled=false;
	}
	}
</script>

</head>

<body onload="lectura();">
<form action="./adminUsuarios.do" method="POST" name="frmCapUsuario" id="frmCapUsuario" accept-charset="UTF-8">
	<table width="100%">
		<tr><td	class="titulo">Puntitos: Administración de Usuarios</td>
			<td align="right" >
				<div class="txtSumNormal" id="divLoading" style="display: none;">
					<img src="./imagenes/loading.gif"/> Buscando infomaci&oacute;n &nbsp;&nbsp; </div> </td>	
		</tr>
	</table>
	<DIV style="width: 100%; FILTER: progid:DXImageTransform.Microsoft.Shadow(color='#aaaacc', Direction=135, Strength=2); 
		border-bottom-width: 2px; border-bottom-style: solid; border-bottom-color: #4d7097; font-size: 1px;">
	</DIV>
	<table width="80%">
	  <tr><td class="titulo" colspan="6">Datos Generales</td></tr>
	  <tr>
	  	<td class="healineblue1" width="25%">
				&nbsp;N&uacute;mero de Empleado&nbsp;</td>
	    <td width="20%">
	    <input name="numEmpleado" id="numEmpleado" type="text" value="${UsuarioTO.numEmpleado}" readOnly="readonly" class="InputB" style="text-transform: uppercase; width:95%" maxlength="35"/>
	    </td>
	    <td class="healineblue1" width="25%">
				&nbsp;Identificaci&oacute;n de Usuario	</td>
	    <td width="20%"><input name="idUsuario" id="idUsuario" type="text" value="${UsuarioTO.idUsuario}" class="InputB" style="text-transform: uppercase; width:95%" maxlength="35"/></td>
	  </tr>
	  <tr>
	    <td class="healineblue1">&nbsp;Nombre(s)&nbsp;</td>
	    <td colspan="4">
	    	<input name="nombre" id="nombre" type="text" value="${UsuarioTO.nombre}" class="InputB" style="width:90%" maxlength="60"/>
	    </td>
	  </tr>
	  <tr>
	    <td class="healineblue1">&nbsp;Password</td>
	    <td><input name="password" id="password" type="password" disabled="disabled" class="InputB" value="${UsuarioTO.password}" style="text-transform: uppercase; width:95%" maxlength="8" /></td>
	    <td class="healineblue1">Confirma Password</td>
	    <td colspan="5"><input name="confirmacionPassword" id="confirmacionPassword" type="password" disabled="disabled" value="${UsuarioTO.password}" class="InputB" style="text-transform: uppercase; width:95%" maxlength="8" /></td>
	    <td><input type="checkbox" name="miCheck" id="miCheck" onclick="Activo();"></td>
	    <td class="healineblue1">Password</td>
	  </tr>
	  <tr>
	  	<td class="healineblue1">&nbsp;Estatus</td>
	  	<td><select name="status" id="status" class="InputB" style="text-transform: uppercase; width:95%" >
	      <option>Selecciona</option>
	  	  <option value="A">Activo</option>
	  	  <option value="I">Inactivo</option>
	  	  <option value="P">ProcesoBaja</option>
	        </select></td>
	  	<td class="healineblue1">Perfil</td>
	  	<td colspan="4"><select name="perfil" id="perfil" class="InputB" style="text-transform: uppercase; width:99%" >
	      <option>Selecciona</option>
	      <c:forEach var="perfil" items="${perfil}">
	         <option value="${perfil.idVariable}-${perfil.valor}">${perfil.valor}&nbsp;-&nbsp;${perfil.descripcion}&nbsp;&nbsp;</option>
	      </c:forEach>
	    </select></td>
	  </tr>
	</table>
	<DIV style="width: 100%; FILTER: progid:DXImageTransform.Microsoft.Shadow(color='#aaaacc', Direction=135, Strength=2); 
		border-bottom-width: 2px; border-bottom-style: solid; border-bottom-color: #4d7097; font-size: 1px;">
	</DIV>
	<table width="100%">
	 	<tr align="right">
	 		<td>
	 		    <input type="hidden" name="accion" id="accion" value="${UsuarioTO.accion}" />
	 		    <input type="hidden" name="reset" id="reset"/>
	 		    <input type="hidden" id="idPerfilActual" name="idPerfilActual" value=""/>
				
				<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="41">
					<input type="button" name="Agregar" id="Agregar" value="AGREGAR" disabled="disabled" class="botonInactivo" onclick="enviar();"/>
				</securityCa:validaPerfil>
				<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="42">
					<input type="button" name="Reseteo" id="Reseteo" value="RESETEA" disabled="disabled" class="botonInactivo" onClick="resetea();"/>
				</securityCa:validaPerfil>
				
				<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="43">
					<input type="button" name="Actualizar" id="Actualizar" value="ACTUALIZAR" disabled="disabled" class="botonInactivo" onclick="enviar();"/>
				</securityCa:validaPerfil>				
							
				<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="44">
					<input type="button" name="Eliminar" id="Eliminar" value="ELIMINAR" disabled="disabled" class="botonInactivo" onClick="elimina();" />
				</securityCa:validaPerfil>
				<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="45">
					<input type="button" name="Limpiar" value="LIMPIAR" class="botonInactivo" onclick="limpiar();" />
				</securityCa:validaPerfil>				
				
			</td>
		</tr>
	</table>
	</form>

<center><h3><font color="white"></font>${error}</h3></center>
<spring:hasBindErrors name="cliente">
      <div align="center">
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
</body>
</html>