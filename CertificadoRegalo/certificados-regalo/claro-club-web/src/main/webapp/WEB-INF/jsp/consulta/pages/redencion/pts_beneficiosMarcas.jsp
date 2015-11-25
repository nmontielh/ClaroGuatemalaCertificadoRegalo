<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Busca Promociones</title>
		<link rel="stylesheet" href='<c:url value="/commons/js/sc_textsheet.css"/>' type="text/css">
		<script type="text/javascript">
		</script>
	</head>
	<body class="menu">
		<form name="frmPromociones" id="frmPromociones" action="">
			<br>
			<table align="center" width="770px" height="auto" style="BORDER:solid 2px #4d7097;" cellpadding="1" cellspacing="0">
				<tr>
					<td>
						<table border="0" cellpadding="1" cellspacing="0">
							<tr>
								<td valign="top" width="740"><br>
									<table width="98%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td class="titulo" height="42">&nbsp;&nbsp;Listado de Marcas con Beneficios</td>
										</tr>
										<tr>
											<td>&nbsp;</td>
										</tr>
									</table>				
								</td>
							</tr>
						</table>
						<br>
						
						<table width="100%" border="1" cellspacing="0" cellpadding="0" align="center">
								<tr>
									<td bgcolor="#ECF0DB" class="healineblue1"  align="center" width="150">Marca</td>
									<td bgcolor="#ECF0DB" class="healineblue1"  align="center" width="95">Modelo</td>
									<td bgcolor="#ECF0DB" class="healineblue1"  align="center" width="350">Beneficio(s)</td>
									<td bgcolor="#ECF0DB" class="healineblue1"  align="center" width="100">No.Material</td>
								</tr>
							<c:set var="contador" value="0"></c:set>
							<c:forEach items="${beneficios}" var="beneficiosTO" varStatus="total">
								<c:set var="contador" value="${total.count}"></c:set>
									<tr class="X3" bgcolor="<c:if test="${contador %2 !=0 }">#D9EBF2</c:if>">										
										<td align="center" width="150">
											<c:out value="${beneficiosTO.marca}"></c:out>
										</td>
										<td align="center" width="95">
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
									</tr>								
							</c:forEach>	
							
						</table>
						<table align="center">
							<tr>
								<td height="10"></td>
							</tr>
							<tr>
								<td>
									Favor de verificar previamente en SAP las <a href="http://sapits.telcel.com/scripts/wgate/ztcsd086/!">
									existencias </a>del beneficio por CAC.
								</td>				
							</tr>
							<tr>
								<td height="10"></td>
							</tr>
							<tr>
								<td align="center">
									<INPUT type="button" name="btnPromocion" value="Aceptar" onclick="window.close();">
								</td>
							</tr>			
						</table>
					</td>
				</tr>
			</table>		
		</form>
	</body>
</html>