<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/permisos.tld"  prefix="securityCa"%>
<html>
<head>
<title>Impresion Alianzas</title>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
    <link rel="stylesheet" href='<c:url value="/commons/js/sc_textsheet.css"/>' type="text/css">

</head>
<body style="background-color: transparent;">
 <form name="frmdetalleRed" id="frmdetalleRed" action="./jsp-content/ConstanciaRedencion.pdf">
 <input type="hidden" id="alianza" name="alianza" value="amex">
 <input type="hidden" id="telefono" name="telefono" value="${telefonoTO.telefono}">
 <input type="hidden" id="fecha" name="fecha" value="${impresionTO.fechaFAlianza}">
 <input type="hidden" id="tipoTM" name="tipoTM" value="${impresionTO.opcion}">
 <input type="hidden" id="fechaFolio" name="fechaFolio" value="${fechaFolio}">
 	
    
    
    
        <DIV id="divAmex" style="visibility: visible MARGIN-TOP: 0px; OVERFLOW-Y: scroll; OVERFLOW-X: hidden; BORDER:; width::inherit; height:inherit; absolute;top: 10px;display: block;"> 	   	                    
	        <table width="97%" border="1" cellspacing="0" cellpadding="1" align="center" class='main'>
	         	<tr> 
	               <td class="titulo" height="42">&nbsp;&nbsp;Impresión de Constancia de Canje</td>
	            </tr> 
	        </table>
	        <table width="97%" border="1" cellspacing="0" cellpadding="0" align="center">   				
	           <tr> 
	             <td width="16%" class="healineblue1"  height="20" bgcolor="#eff0f1"> 
	                <div align="center">Nombre</div>
	             </td>
	             <td colspan="2" class="textonegroBlod" width="44%" height="20" > 
	                 <div align="left"></div>&nbsp;${telefonoTO.mobileTO.nombre}</td>
	           </tr>
	           <tr> 
	             <td width="16%" class="healineblue1"  height="19" bgcolor="#eff0f1"> 
	               <div align="center">Teléfono</div></td>
	             <td class="textonegroBlod" width="44%" height="19" > 
	                 &nbsp;${telefonoTO.telefono}</td>
				 <td width="16%" class="healineblue1"  height="19" bgcolor="#eff0f1"> 
	                <div align="center">Puntos Disponibles:</div></td>
	              <td class="textonegroBlod" width="44%" height="19" > 
	                 &nbsp;${telefonoTO.puntosTO.ptosDisponibles}</td>                 				
	            </tr>
	           	<tr> 
	              <td width="16%" class="healineblue1"  height="19" bgcolor="#eff0f1"> 
	                <div align="center">Cuenta</div></td>
	              <td class="textonegroBlod" width="44%" height="19" > 
	                 &nbsp;${telefonoTO.cuenta}</td>
	              <td width="16%" class="healineblue1"  height="19" bgcolor="#eff0f1"> 
	                <div align="center">Puntos Canjeados:</div></td>
	              <td class="textonegroBlod" width="44%" height="19" > 
	                 &nbsp;${impresionTO.ptsCanjeadoCF}</td>               				 
	             </tr>
	             <tr> 
	              <td width="16%" class="healineblue1"  height="19" bgcolor="#eff0f1"> 
	                <div align="center">Fecha de Canje:</div></td>
	              <td class="textonegroBlod" width="44%" height="19" > 
	                 &nbsp;${impresionTO.fechaOperacion}</td>
	              <td width="16%" class="healineblue1"  height="19" bgcolor="#eff0f1"> 
	                <div align="center"></div></td>
	              <td class="textonegroBlod" width="44%" height="19" > 
	                 &nbsp;</td>               				 
	             </tr>
	           </table>
	              
	              <table width="97%" border="0" cellspacing="0" cellpadding="0" align="center">
	                <tr> 
	                  <td class="textonegroBlodTrs">&nbsp;</td>
	                </tr>
	                <tr> 
	                  <td class="textonegroBlodTrs">Detalle de canje</td>
	                </tr>
	              </table>
	              <table width="97%" border="1" cellspacing="0" cellpadding="0" align="center" bordercolor="#cccccc">
	                <tr bgcolor="#ECF0DB" class="healineblue1" align="center" >
		             <th width="10%" >Cuenta</th>     
		             <th width="13%">Fecha Operación</th>
		             <th width="13%">Puntos Canjeados</th>
		             <th width="10%">Valor del cupón en pesos</th>
		             <th width="15%">Comentario</th>
            		</tr>
            		<tr>
		             <c:set var="contador" value="0"/>
	           		 <c:forEach var="alianzasTO" items="${impresionTO.alianzas}" varStatus="total">
	            	 <c:set var="contador" value="${total.count}"/>   
	            	<tr class="X3"  bgcolor="<c:if test="${contador %2 !=0 }">#D9EBF2</c:if>" >
						<td align="left">&nbsp;${alianzasTO.cuenta}</td>
						<td align="left">&nbsp;${alianzasTO.fechaOperacion}</td>
						<td align="left">&nbsp;${alianzasTO.ptsTransferidos}</td>
						<td align="left">&nbsp;${alianzasTO.valorCuponOrig}</td>
						<td align="left">&nbsp;${alianzasTO.comentario}</td>
	              </tr>
	              </c:forEach>
	              <tr bgcolor="#ECF0DB" class="healineblue1" >
	       			<td colspan="5">&nbsp;<c:out value="${contador}"></c:out> Registro(s) Encontrado(s).</td>
	       		  </tr>	   
	              </table>
				  <table width="97%" border="0" cellspacing="0" cellpadding="0" align="center">
	                <tr> 
	                  <td class="textonegroBlodTrs">&nbsp;</td>
	                </tr>
	              </table>   
	             <!-- <p align="right"><img height=63 src="http://191.9.4.168:38080/circuloazul/web-content/pts_images/logo_circulo_azul.gif" width=197></p>  -->
	        <table width="97%" border="0" cellspacing="0" cellpadding="0" align="center">
				<tr>
					<td colspan=2 class="textonegroBlodTrs">
			 			<P>&nbsp;</P>
						<table cellSpacing=0 cellPadding=0 width="98%" align=center
								border=0>
							<tr>
				 				<td class=healineblue1 width="40%" height="15" align="center"><font
									style="font-size:6pt">${usuarioTO.nombre} ( ${usuarioTO.idUsuario} )</font>
									<HR id=HR1 size=1 align=left width=280>
									<div align=center><font style="font-size:6pt">Nombre y Firma del
									Asesor</font></div>
								</td>
								<td width="10%"></td>
								<td class=healineblue1 width="40%" height="15" align="center"><font
										style="font-size:6pt">${telefonoTO.mobileTO.lastName} ${telefonoTO.mobileTO.firstName}</font>
								<HR id=HR1 size=1 align=left width=280>
								<div align=center><font style="font-size:6pt">Nombre y Firma del
								Cliente</font></div>
								</td>
						</tr>
						</table>
							</td>
							</tr>
						<tr>
							<td colspan=2 class="textonegroBlodTrs">
								<table cellSpacing=0 cellPadding=0 width="251" align=center
									border=0>
									<tr>
										<td colspan=2>&nbsp;</td>
									</tr>
									<tr>
										<td colspan=2>&nbsp;</td>
									</tr>
									<tr>
										<td class=healineblue1 width="40%">
										<HR id=HR1 size=1 align=left width=300>
										<div align=center><font style="font-size:6pt">Visto Bueno
										(Nombre y Firma)</font></div>
										</td>
									</tr>
									<tr>
										<td colspan=2>&nbsp;</td>
									</tr>
									<tr>
										<td colspan=2>&nbsp;</td>
									</tr>
								</table>
								</td>
					</tr>
					<tr>
								<td width="50%">
									<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="77">
										<input class="LinkOut" onmouseover='this.className="LinkIn";'
											onmouseout='this.className="LinkOut";' type="submit"
											name="Submit" value="Generar archivo PDF">
									</securityCa:validaPerfil>
								</td>
								<td width="40%">
									<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="78">
										<a class="LinkOut" style="width:10%"
											onmouseover='this.className="LinkIn";'
											onmouseout='this.className="LinkOut";' id="Link1"
											onClick="window.print();">&nbsp;&nbsp;Imprimir&nbsp;&nbsp;</a>
									</securityCa:validaPerfil>
								</td>
					</tr>
			</table> 
    </DIV>
    
    </form>
</body>
</html>