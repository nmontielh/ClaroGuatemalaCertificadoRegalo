<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<html>
<head>
<script>

</script>
<title>Impresion de Certificados Amex</title>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
    <link rel="stylesheet" href='<c:url value="/commons/js/sc_textsheet.css"/>' type="text/css">
</head>
<body  marginwidth="0" marginheight="0" style="MARGIN: 0px;background-color: transparent; border: none;">
     <form id="frmCertificadoViaje" name="frmCertificadoViaje" >
      	<div id="certificado" style="visibility: hidden MARGIN-TOP: 0px; OVERFLOW-Y: hidden; OVERFLOW-X: hidden; width:735px; height:inherit; position:absolute;top: 10px;display: block;" >
            <table width="98%"  cellspacing="0" cellpadding="0" align="center"  >
                <tr> 
                    <td colspan="3" class="titulo" height="42" width="100%" >&nbsp;&nbsp;Impresión de Certificado de Viaje</td>
                </tr>
            </table>
            <table width="98%" border="1" cellspacing="0" cellpadding="0" align="center">
		        <tr>
		           <td width="20%" class="healineblue1" bgcolor="#ECF0DB" >&nbsp;Otorgado a:</td>
		           <td class="textonegroBlod"  >&nbsp;${alianzaTO.nombre} ${alianzaTO.apMaterno} ${alianzaTO.apPaterno}</td>
		           <td width="20%" class="healineblue1" bgcolor="#ECF0DB" >&nbsp;Centro de atención:</td>
		           <td class="textonegroBlod"  >&nbsp;${usuarioTO.puntoVentaTO.ptoVenta}</td>
		        </tr>
		        <tr> 
		           <td width="20%" class="healineblue1" bgcolor="#ECF0DB" >&nbsp;Teléfono:</td>
		           <td class="textonegroBlod"  >&nbsp;${telefonoTO.telefono}</td>
		           <td width="20%" class="healineblue1" bgcolor="#ECF0DB" >&nbsp;Fecha (año-mes-día):</td>
		           <td class="textonegroBlod"  >&nbsp;${alianzaTO.fechaOperacion}</td>
		        </tr>
		        <tr> 
		           <td width="20%" class="healineblue1" bgcolor="#ECF0DB" >&nbsp;Cuenta:</td>
		           <td class="textonegroBlod"  >&nbsp;${telefonoTO.cuenta}</td>
		           <td width="20%" class="healineblue1" bgcolor="#ECF0DB" >&nbsp;Vigencia máxima:</td>
		           <td class="textonegroBlod"  >&nbsp;${alianzaTO.vigenciaMax}</td>
		        </tr>
		        <tr> 
		           <td width="20%" class="healineblue1" bgcolor="#ECF0DB" >&nbsp;No. Folio:</td>
		           <td class="textonegroBlod"  >&nbsp;${alianzaTO.folio}</td>
		           <td width="20%" class="healineblue1" bgcolor="#ECF0DB" >&nbsp;Puntos canjeados:</td>
		           <td class="textonegroBlod"  >&nbsp;${alianzaTO.ptsTransferidos}</td>
		        </tr>
            </table>   
			<table width="98%" border="0" cellspacing="0" cellpadding="0" align="center">
                <tr><td class="textonegroBlodTrs">&nbsp;</td></tr>
                <tr><td class="textonegroBlodTrs">&nbsp;</td></tr>
                <tr><td class="textonegroBlodTrs"><div align="center"><font color="black" face="arial,helvetica" size="5">${alianzaTO.valorCuponOrig}&nbsp;pesos</font></div></td></tr>
             </table>
             	  <table width="98%" border="0" cellspacing="0" cellpadding="0" align="center">	
				<tr> 
                  <td colspan="2" class="textonegroBlodTrs">
				  <P>&nbsp;</P>
                  <P>Válido para intercambiarse por un paquete de viaje de los que se le indiquen al cliente 
                  al momento de hacerlo efectivo y de acuerdo al monto del Certificado. 
                  Sujeto a disponibilidad en fechas, vuelos, hospedajes y/o recorridos fijados por 
                  el prestador de los servicios de viaje. No Reembolsable. Vigencia máxima de un año a 
                  partir de su fecha de expedición. El Certificado es Personal e Intransferible y no tiene 
                  valor en monetario. Se puede acumular con otros Certificados para el intercambio de paquetes 
                  de viaje dentro del plazo de vigencia de los mismos. En caso de que el costo del paquete 
                  del viaje sea menor al valor del certificado no se entregará el monto de la diferencia al 
                  cliente ni se le expedirá un documento que ampare la misma. En caso de que el costo del 
                  paquete de viaje sea mayor al valor del certificado el cliente deberá de pagar el monto 
                  excedente. </P>
                  <P>&nbsp;</P>
				  </td>
                </tr>
                <tr> 
                  <td class="textonegroBlodTrs">
                  <td colspan="4" class="healineblue1" valign="middle" height="50" align="center">
                  	<img src='<c:url value="/commons/images/logoAlianzaAmex.gif"/>'>
                  </td>		  
                </tr>
			  </table>        
			  <table align="center">  
      			<tr>
					<td width="40%"><a class="LinkOut" style="width:10%"
						onmouseover='this.className="LinkIn";'
						onmouseout='this.className="LinkOut";' id="Link1"
						onClick="window.print();">&nbsp;&nbsp;Imprimir&nbsp;&nbsp;</a></td>
	  			</tr>
			  </table>     
         </div>
    </form>          
</body>
</html>