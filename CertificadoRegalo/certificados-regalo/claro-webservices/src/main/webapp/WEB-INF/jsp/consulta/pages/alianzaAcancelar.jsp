<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/permisos.tld" prefix="securityCa" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Alianzas a Cancelar</title>
<link rel="stylesheet" href='<c:url value="/commons/js/sc_textsheet.css"/>' type="text/css">
<script >
	function fnMuestraComentario(){
		document.getElementById("divComentario").style.visibility="visible";
		document.getElementById("divComentario").style.display="block";
	}
	function fnAceptar(){		
		document.getElementById("divComentario").style.visibility="hidden";
		document.getElementById("divComentario").style.display="none";
		if(document.getElementById("idTransferencia")!=null){
			var valor = document.getElementById("idTransferencia").value;		
			var arreglo = valor.split("|");		
			document.getElementById("folio").value=arreglo[0];
			document.getElementById("estatusTrans").value=arreglo[1];		
			document.getElementById("frmCancela").action = "./aplicaAlianzaCancela.do";		
			document.getElementById("frmCancela").submit();
		}			
	}	
	function cerrar(){
		document.getElementById("divComentario").style.visibility="hidden";
		document.getElementById("divComentario").style.display="none";
	}	
	
</script>
</head>
<body>
<DIV style="position: static;top: 10px;height: 200px;MARGIN-TOP: 0px; OVERFLOW-Y: scroll; OVERFLOW-X: hidden; BORDER:solid 1px silver;">
<table border="1" cellspacing="0" cellpadding="0" align="center">           
           <tr bgcolor="#ECF0DB" class="healineblue1"  align="center" > 
               <td width="10%">Folio</td>
               <td width="10%">Fecha Operación </td>
               <c:if test="${opcion==1}">
               		<td width="10%">Cuenta Alianza </td>
               	</c:if>
               <td width="10%">Puntos Canjeados</td>
               
           </tr>             	
           <c:set var="contador" value="0"/>		             
           <c:forEach var="alianzaTO" items="${alianzasTO}" varStatus="total">
            <c:set var="contador" value="${total.count}"/>             
	           <tr class="X3" bgcolor="<c:if test="${contador %2 !=0 }">#D9EBF2</c:if>">
	           	<td>
	           	<c:if test="${contador==1}">
	           		<input type="radio" name="idTransferencia" value="${alianzaTO.folio}|${alianzaTO.statusTrans}">
	           	</c:if>
	           	&nbsp;${alianzaTO.folio}</td>
	           	<td>&nbsp;${alianzaTO.fechaOperacion}</td>
	           	<c:if test="${opcion==1}">
	           		<td>&nbsp;${alianzaTO.cuentaAlianza}</td>
	           	</c:if>
	           	<td>&nbsp;${alianzaTO.ptsTransferidosCF}</td>	           		           	
	           </tr>           
           </c:forEach>           
</table>
</DIV>
<DIV id="divTotal" style="height:20px;position: static;top: 200px;">
       <table style="width: 98%">
       		<tr bgcolor="#ECF0DB" class="healineblue1">
       			<td>&nbsp;<c:out value="${contador}"></c:out> Registro(s) Encontrado(s).</td>
       		</tr>
      	</table>
</DIV>
<DIV id="boton" style="height:20px;position: static;top: 223px;">
	<table>
		<tr>
			<td>
				<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="134">
					<input type="button" name="btnAceptar" value="Aceptar" onclick="fnMuestraComentario()" class="submit">
				</securityCa:validaPerfil>
			</td>
		</tr>
	</table>
</DIV>
<div id="divComentario" style="visibility: hidden;display: none;position: absolute;top:30px;left: 150px;width: 250px;height: 150px;" class="BloqueBlanco">
	<form name="frmCancela" id="frmCancela" method="post">
	<input type="hidden" name="estatusTrans" id="estatusTrans">
	<input type="hidden" name="folio" id="folio">
	<input type="hidden" name="telefono" id="telefono" 		value="${telefono}">	
	<input type="hidden" name="region" id="region" 			value="${region}">	
	<input type="hidden" name="secuencia" id="secuencia" 	value="${secuencia}">	
	<input type="hidden" name="opcion" id="opcion" 			value="${opcion}">
	<input type="hidden" name="cuenta" id="cuenta" 			value="${cuenta}">		
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
      				<input type="button" name="aceptar" value="Aceptar" onclick="fnAceptar()" class="submit">
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