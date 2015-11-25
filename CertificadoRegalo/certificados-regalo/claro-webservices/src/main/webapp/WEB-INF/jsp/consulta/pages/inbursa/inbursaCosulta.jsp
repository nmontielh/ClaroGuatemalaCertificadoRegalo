<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt'%>     
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix='f' uri="http://java.sun.com/jstl/fmt_rt"%>
<%@ taglib prefix="spring" uri="/spring"%>
<%@ taglib uri="/WEB-INF/permisos.tld" prefix="securityCa" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Consulta Inbursa</title>
    <link rel="stylesheet" type="text/css" href='<c:url value="/commons/css/impromptus.css"/>' media='screen' >
    <link rel="stylesheet" type="text/css" href='<c:url value="/commons/js/sc_textsheet.css"/>' media='screen' >
	<script type="text/javascript" src='<c:url value="/commons/js/jquery-1.2.3.pack.js"/>'></script>
	<script type='text/javascript' src='<c:url value="/commons/js/jquery.form.js"/>' ></script>
	<script type="text/javascript" src='<c:url value="/commons/js/jquery-impromptu.1.3.js"/>' ></script>
		
     <script type="text/javascript">
          
     	function mensaje(informacion){
     		$.prompt(informacion, {prefix:'cleanblue', buttons:{Aceptar:true}});
     		return true;
     	}
     	
     	function consultaDetalle(){
     		var numero= $("#telefono").val();
			var expresion = /\d{10}/;
			var existe=false;
			
			if($("#telefono").val()==""){
				return mensaje("Debe Capturar un Tel&eacute;fono ");
			}else if(!expresion.test(numero)){
				return mensaje("Número no valido");
			}else {
		   		var telefonoInput = new String($("#telefono").val());
				
				$("#tablaLista>tbody>tr>td:nth-child(1)").each(function(i){
					var telefonoIterate = $(this).text();
					if(telefonoInput == telefonoIterate){
						mensaje("La linea ya se encuentra en la lista");
						existe=true;
						return false;
					}
				});
       			if(!existe){
				var queryString = "telefono=" + numero;
				$.ajax(	{
					url:"./detalleInbursa.do",
					type:"POST",
					data: queryString,
					cache: false,
					success: agregaRespuesta
				});
				}
			}       		         		
        }
        
        function agregaRespuesta(data){
        	var tagExito = "<tr id=\"" + $("#telefono").val();
        	if(data.indexOf(tagExito) != -1){
				$("#tablaLista tbody").append(data);
				$("#spTotal").html($("#tablaLista [@name=inbursa]").length);
				

				
			}else{
				mensaje("El n&uacute;mero no tiene promociones Inbursa");
			}
		}
	
</script>
</head>
  <body>
      
  <div id="listados">
            <TABLE width="100%"  cellspacing="0" cellpadding="0" align="center" >
                <tr > 
                    <td colspan="3" class="titulo" height="40">&nbsp;&nbsp;Consulta de Inbursa</td>
                </tr>               
                <tr> 
                <td class="healineblue1" align="left" width="5%" >Teléfono: </td>
                    <td class="textonegroBlodTrs" colspan="2" width="5%" height="23" align="left"> 
                        <input type="text" id="telefono" name="telefono" maxlength="10" onKeyDown="if(event.keyCode==13) document.getElementById('Link1').click()">                        
                    </td>
                    <td align="center" width="50%" height="23px">
                    <securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="192">
                    	<a class=botonInactivo onmouseover='this.className="botonActivo";' onmouseout='this.className="botonInactivo";'
                    		id="Link1" onClick="consultaDetalle();">&nbsp;Consultar&nbsp;</a>
                    </securityCa:validaPerfil>                                                                                                   
                    </td>                          
                </tr>
                </table>
                <DIV style="width: 100%; FILTER: progid:DXImageTransform.Microsoft.Shadow(color='#aaaacc', Direction=135, Strength=2); 
	border-bottom-width: 2px; border-bottom-style: solid; border-bottom-color: #4d7097; font-size: 1px;" ></DIV >
  
         <table >
				<tr><td class="titulo" height="40">Datos de la Línea</td></tr>
			</table>
         <div style="width: 98%; height: 68%; overflow-y:scroll; position:fixed;" >
         <table id="tablaLista" border="1" cellspacing="0" cellpadding="0" >
				<thead>
					<tr align="center">
						<td class="tablaHead">&nbsp;Tel&eacute;fono&nbsp; </td>
						<td class="tablaHead">&nbsp;C&oacute;digo Carta&nbsp; </td>
						<td class="tablaHead">&nbsp;Regi&oacute;n&nbsp;</td>
						<td class="tablaHead">&nbsp;Fecha Operaci&oacute;n&nbsp;</td>
						<td class="tablaHead">&nbsp;Producto&nbsp; </td>
						<td class="tablaHead">&nbsp;Descripci&oacute;n&nbsp; </td>
						<td class="tablaHead">&nbsp;Modalidad&nbsp; </td>
						<td class="tablaHead">&nbsp;Tipo de Movimiento&nbsp; </td>
						<td class="tablaHead">&nbsp;Estatus&nbsp; </td>
						<td class="tablaHead">&nbsp;Descuento Inbursa&nbsp;</td>
						<td class="tablaHead">&nbsp;Descuento Marca&nbsp;</td>
						<td class="tablaHead">&nbsp;Descuento Roext&nbsp;</td>
						<td class="tablaHead">&nbsp;Descuento AltoValor&nbsp;</td>
						<td class="tablaHead">&nbsp;Descuento Certificado&nbsp;</td>
						<td class="tablaHead">&nbsp;Folio Certificado&nbsp;</td>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>  
         </div>
       </div>                 
    </body>
</html>