<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
 <%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<table width="98%" border="1" cellspacing="0" cellpadding="0" align="center">
	            <tr> 
	                <td width="19%" class="healineblue1" bgcolor="#ECF0DB" >&nbsp;Por vencer 2 años</td>
	                <td width="15%" class="textonegroBlod"  >&nbsp;<c:out value="${telefonoTO.puntosTO.ptsPorVencer2}"/></td>
	                <td width="19%" class="healineblue1" bgcolor="#ECF0DB">&nbsp;Fecha de Vencimiento</td>
	                <td width="14%" class="textonegroBlod" >&nbsp;<c:out value="${telefonoTO.puntosTO.fecVencer2}"/></td>
	                <td width="19%" class="healineblue1" bgcolor="#ECF0DB" >&nbsp;Fecha facturación</td>
	                <td width="12%" class="textonegroBlod" >
	                    &nbsp;<c:out value="${telefonoTO.fecFactura}"/>
	                </td>
	            </tr>
	             <tr>                 
	                <td class="healineblue1" bgcolor="#ECF0DB" >&nbsp;Por vencer 1 año</td>
	                <td class="textonegroBlod"  >&nbsp;<c:out value="${telefonoTO.puntosTO.ptsPorVencer1}"/></td>
	                <td class="healineblue1" bgcolor="#ECF0DB"  >&nbsp;Fecha de Vencimiento</td>
	                <td class="textonegroBlod" >&nbsp;<c:out value="${telefonoTO.puntosTO.fecVencer1}"/></td>
	                <td class="healineblue1" bgcolor="#ECF0DB">&nbsp;Alianzas</td>
	                <td class="textonegroBlod" >
	                    &nbsp;<c:out value="${telefonoTO.puntosTO.ptsTransferidos}"/>
	                </td>
	            </tr>
	            <tr>  
	                <td class="healineblue1" bgcolor="#ECF0DB"  >&nbsp;Por vencer</td>
	                <td class="textonegroBlod"  >&nbsp;<c:out value="${telefonoTO.puntosTO.ptsPorVencer}"/></td>
	                <td class="healineblue1" bgcolor="#ECF0DB">&nbsp;Fecha de Vencimiento</td>
	                <td class="textonegroBlod" width="14%" >&nbsp;<c:out value="${telefonoTO.puntosTO.fecVencer}"/></td>
	                <td class="healineblue1" bgcolor="#ECF0DB"  >&nbsp;Redimidos</td>
	                <td class="textonegroBlod"  >&nbsp;<c:out value="${telefonoTO.puntosTO.ptsRedimidos}"/></td>
	            </tr>
	            <tr> 
	                <td class="healineblue1" bgcolor="#ECF0DB" >&nbsp;Renta</td>
	                <td class="textonegroBlod" >&nbsp;<c:out value="${telefonoTO.puntosTO.ptsRenta}"/></td>
	                <td class="healineblue1" bgcolor="#ECF0DB"  >&nbsp;Antigüedad</td>
	                <td class="textonegroBlod" >&nbsp;<c:out value="${telefonoTO.puntosTO.ptsAntiguedad}"/></td>
	                <td class="healineblue1" bgcolor="#ECF0DB">&nbsp;Puntos Vencidos</td>
	                <td class="textonegroBlod"  >&nbsp;<c:out value="${telefonoTO.puntosTO.ptsVencidos}"/></td>
	            </tr>
	            <tr> 
	                <td class="healineblue1" bgcolor="#ECF0DB" >&nbsp;Excedentes</td>
	                <td class="textonegroBlod" >&nbsp;<c:out value="${telefonoTO.puntosTO.ptsExcedentes}"/></td>
	                <td class="healineblue1" bgcolor="#ECF0DB" >&nbsp;Promoción</td>
	                <td class="textonegroBlod" >&nbsp;<c:out value="${telefonoTO.puntosTO.ptsPromocion}"/></td>
	                <td class="healineblue1" bgcolor="#ECF0DB" >&nbsp;Fecha de Expiración</td>
	                <td class="textonegroBlod" >&nbsp;<c:out value="${telefonoTO.puntosTO.fecVencidos}"/></td>
	            </tr>
	            <tr> 
	            <c:if test="${telefonoTO.puntosTO.bonoEquipo!=0}">
	                <td class="healineblue1" bgcolor="#ECF0DB" >&nbsp;Bono Equipo</td>
	                <td class="textonegroBlod"  >&nbsp;<c:out value="${telefonoTO.puntosTO.bonoEquipo}"/></td>
	            </c:if>
	            </tr>	
	        </table>           