<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<html>
<head>
	<title>Busca Promociones</title>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
		<link rel="stylesheet" href='<c:url value="/commons/js/sc_textsheet.css"/>' type="text/css">
		<SCRIPT language="javascript">
		
			function acepta(id, valor, div){
			
				document.getElementById("comentario").value=valor;
				var motivo = document.getElementById("idMotivo");
				motivo.value = id;						
				cierraDiv(div);
			}
			
			function cierra(){
				var comentario = document.getElementById("comentario");
				if(comentario.value ==""){
					alert("Debe seleccionar un Motivo para las promociones.");	
					return false;
				}
				
				var idModelo = document.getElementById("idModelo");
				var idBeneficio = document.getElementById("idBeneficio");
				var idGpoBeneficio = document.getElementById("idGpoBeneficio");
				var idMotivo = document.getElementById("idMotivo");
				var tipoMotivo = document.getElementById("tipoMotivo");
				var resultado = idModelo.value + "|" + idBeneficio.value + "|" + idGpoBeneficio.value + "|" + idMotivo.value + "|" + tipoMotivo.value + "|" + comentario.value;   
								
				window.returnValue = resultado;
			  	window.close();
			}
			
			function muestraMotivos(objeto){
				var valor = objeto.value;
				var arreglo =  valor.split("|");
								
				var idModelo = document.getElementById("idModelo");
				var idBeneficio = document.getElementById("idBeneficio");
				var idGpoBeneficio = document.getElementById("idGpoBeneficio");
				var tipoMotivo = document.getElementById("tipoMotivo");
				
				tipoMotivo.value = arreglo[0];
				idModelo.value = arreglo[1];
				idBeneficio.value = arreglo[2];
				idGpoBeneficio.value = arreglo[3];
				
							
				if(arreglo[0]=="1"){				
					document.getElementById("aceptados").style.visibility="visible";
					document.getElementById("aceptados").style.display="block";
				}else if(arreglo[0]=="0"){
					document.getElementById("rechazos").style.visibility="visible";
					document.getElementById("rechazos").style.display="block";		
				}
				document.getElementById("comentario").value = "";
			}
			
			function cierraDiv(div){
					document.getElementById(div).style.visibility="hidden";
					document.getElementById(div).style.display="none";
			}
		
			function verificaCierre(valor){
				if(valor==0){
					window.close();
				}
			}			
		</SCRIPT>
	</head>
	<body class="menu" marginwidth="0" marginheight="0" style="MARGIN: 0px">
	<form name="frmPromociones" id="frmPromociones">
		
		<input type="hidden" name="idModelo" id="idModelo">
		<input type="hidden" name="idBeneficio" id="idBeneficio">
		<input type="hidden" name="idGpoBeneficio" id="idGpoBeneficio">
		<input type="hidden" name="idMotivo" id="idMotivo">
		<input type="hidden" name="tipoMotivo" id="tipoMotivo">
		
		<center>
			<SCRIPT>
				verificaCierre(${beneficiosSize});
			</SCRIPT>
			<c:if test="${beneficios!=null && beneficiosSize>0}">
				<br>
				<table align="center" width="770px" height="auto" style="BORDER:solid 2px #4d7097;" cellpadding="1" cellspacing="0">				
				<tr>
					<td valign="top" width="740" align="center"><br>
						<table width="98%" border="0" cellspacing="0" cellpadding="0" align="center">
							<tr align="left">
								<td class="titulo" height="42">&nbsp;Seleccione una Promoción</td>
							</tr>
							<tr>
								<td>&nbsp;</td>
							</tr>
						</table>
						
						<DIV style="OVERFLOW: auto; WIDTH: 740px; POSITION: relative; HEIGHT:auto;">
							<table width="100%" border="1" cellspacing="0" cellpadding="0" align="center">
								<tr>
									<td bgcolor="#ECF0DB" class="healineblue1"  align="center" width="150">Modelo</td>
									<td bgcolor="#ECF0DB" class="healineblue1"  align="center" width="350">Promoción</td>
									<td bgcolor="#ECF0DB" class="healineblue1"  align="center" width="100">No.Material</td>
									<td bgcolor="#ECF0DB" class="healineblue1"  align="center" width="70">Acepta</td>
									<td bgcolor="#ECF0DB" class="healineblue1"  align="center" width="70">Rechaza</td>							
								</tr>
								<c:forEach items="${beneficios}" var="beneficiosTO" varStatus="total">
									<c:set var="contador" value="${total.count}"/>
										<tr class="X3" bgcolor="<c:if test="${contador %2 !=0 }">#D9EBF2</c:if>">
											<td align="center" width="150">
												<c:out value="${beneficiosTO.modelo}"></c:out>
											</td>
											<td align="center" width="350">
												<table>
													<c:forEach items="${beneficiosTO.beneficios}" var="benefTO">
														<tr>
															<td>
																<c:out value="${benefTO.descBeneficio}"></c:out>
															</td>
														</tr>														
													</c:forEach>
												</table>
											</td>
											<td align="center" width="100">
												<table>
													<c:forEach items="${beneficiosTO.beneficios}" var="benefTO">
														<tr>
															<td>
																<c:out value="${benefTO.idBeneficio}"></c:out>
															</td>
														</tr>														
													</c:forEach>
												</table>												
											</td>																						
											<td align="center" width="70">
												<INPUT type="radio" name="promo" value="1|${beneficiosTO.modelo}|${beneficiosTO.idbeneficio}|${beneficiosTO.idGpoBeneficio}" onclick="muestraMotivos(this);">
											</td>
											<td align="center" width="70">
												<INPUT type="radio" name="promo" value="0|${beneficiosTO.modelo}|${beneficiosTO.idbeneficio}|${beneficiosTO.idGpoBeneficio}" onclick="muestraMotivos(this);">
											</td>							
										</tr>									
								</c:forEach>
						</table>
						</DIV>
					</td>
				</tr>
				<tr>
					<td>
						<table width="740" border="0" cellspacing="0" cellpadding="0" align="center">
							<tr>
								<td bgcolor="#ECF0DB" class="healineblue1" align="center" width="81">Motivo</td>
								<TD width="307">
									<INPUT type="text" id="comentario" name="comentario" size="50" disabled>
								</TD>
							</tr>
						</table>
						<DIV style="OVERFLOW: auto; WIDTH: 740px; POSITION: relative; HEIGHT: 200px; color: red; font-size: 14px; font-style: bold;">
							<SPAN id="existencias"> 
								Favor de verificar previamente en SAP las <a href="http://sapits.telcel.com/scripts/wgate/ztcsd086/!">
								existencias </a>del beneficio por CAC.
							</SPAN>
							<BR><br>
							<INPUT type="button" name="btnPromocion" value="Aceptar" onclick="cierra()">
						</DIV>
					</td>
				</tr>
			</table>			
		</c:if>		

		<!-- Aceptados -->
		
		<DIV id="aceptados" style="visibility: hidden;display: none;top: 50px;left: 250px;position: absolute;" class="BloqueBlanco">
			<TABLE>
				<TR bgcolor="#ECF0DB" class="healineblue1">
					<TD>Aceptados</TD>
				</TR>
			</TABLE>
			<DIV style="OVERFLOW: auto; WIDTH: 250px; POSITION: relative; HEIGHT: 200px;background-color: white;" >
				<TABLE width="100%" border="1" cellspacing="0" cellpadding="0" align="center">
					<c:set var="contador" value="0"></c:set>
					<c:forEach items="${aceptados}" var="motivoAceptacion" varStatus="totales">
						<c:set var="contador" value="${totales.count}"></c:set>						
						<tr class="X3" bgcolor="<c:if test="${contador %2 != 0}">#D9EBF2</c:if>"  onclick="acepta(${motivoAceptacion.idMotivo},'${motivoAceptacion.descripcion}','aceptados')">
							<td class="textonegroBlod" valign="middle">
								<c:out value="${motivoAceptacion.descripcion}"></c:out>
							</td>		
						</tr>
					</c:forEach>
				</TABLE>
			</DIV>
			<TABLE>
				<TR bgcolor="#ECF0DB" class="healineblue1">
					<TD>Existe(n) ${motivoAceptacionSize} motivo(s) de Aceptaci&oacute;n.</TD>
				</TR>
			</TABLE>
			<INPUT type="button" name="cerrar" value="cerrar" onclick="cierraDiv('aceptados')">
		</DIV>

		<!-- Rechazos -->
		<DIV id="rechazos" style="visibility: hidden;display: none;top: 50px;left: 250px;position: absolute;" class="BloqueBlanco">
			<TABLE>
				<TR bgcolor="#ECF0DB" class="healineblue1">
					<TD>Rechazos</TD>
				</TR>
			</TABLE>
			<DIV style="OVERFLOW: auto; WIDTH: 250px; POSITION: relative; HEIGHT: 200px;background-color: white;" >
				<TABLE width="100%" border="1" cellspacing="0" cellpadding="0" align="center">
					<c:set var="contador" value="0"></c:set>
					<c:forEach items="${rechazos}" var="motivoRechazo" varStatus="totales">
						<c:set var="contador" value="${totales.count}"></c:set>
						<tr class="X3" bgcolor="<c:if test="${contador %2 != 0}">#D9EBF2</c:if>"  onclick="acepta(${motivoRechazo.idMotivo},'${motivoRechazo.descripcion}','rechazos')">
							<td class="textonegroBlod" valign="middle">
								<c:out value="${motivoRechazo.descripcion}"></c:out>
							</td>			
						</tr>						
					</c:forEach>				   
				</TABLE>
			</DIV>
			<TABLE>
				<TR bgcolor="#ECF0DB" class="healineblue1">
					<TD>Existe(n) ${motivoRechazoSize} motivo(s) de Rechazo.</TD>
				</TR>
			</TABLE>
			<INPUT type="button" name="cerrar" value="cerrar" onclick="cierraDiv('rechazos')">
		</DIV>
	</center>
</FORM>
</body>
</html>
