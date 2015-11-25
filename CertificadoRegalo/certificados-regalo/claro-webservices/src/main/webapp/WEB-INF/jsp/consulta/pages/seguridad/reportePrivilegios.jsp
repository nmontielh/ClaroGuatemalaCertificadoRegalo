
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<html>
<% response.setContentType("application/vnd.ms-excel"); %>
<meta http-equiv="Content-Type" content="application/vnd.ms-excel; charset=ISO-8859-1">

<head>
	<title>Lista Perfiles</title>
	<style type="text/css">
		.titulo{
			font-size: 20px;
			background-color: blue;
			color: white;
			font: bold;
			font-family: sans-serif;			   			 
		}
		.menuPrincipal{
			background-color: #33FF00;
			font-family: sans-serif;			
		}
		.nivel1{
			background-color: #F6F62A;
			font-family: sans-serif;			 
		}
		.nivel2{
			background-color: #F48AD0;
			font-family: sans-serif;			 
		}
		.nivel3{
			background-color: #9999FF;
			font-family: sans-serif;			 
		}
		.nivel4{
			background-color: #3FC4F2;
			font-family: sans-serif;			 
		}
		.nivel5{
			background-color: #FFD033;
			font-family: sans-serif;			 
		}
		.nivel6{
			background-color: #FABEFA;
			font-family: sans-serif;			 
		}
		.nivel7{
			background-color: #F24A0C;
			font-family: sans-serif;			 
		}
		.general{
			font-family: sans-serif;
		}
		
	</style>
</head>
<body style="background-color: transparent;"> 
 	<DIV id="divMovimiento" style="MARGIN-TOP: 0px; OVERFLOW-Y: scroll; OVERFLOW-X: hidden; BORDER:solid 1px silver; width:98%; HEIGHT: 90%;position: absolute;top: 10px;">
	 	<table border="0" cellspacing="0" cellpadding="0" width="1000">
	 		
	 	</table> 	   	                    
		<table border="1" class="general">
			<tr>
	 			<td colspan="9" class="titulo">
	 				<table>
	 					<tr class="titulo">
	 						<td width="200"></td>
	 						<td colspan="2" class="titulo">MATRIZ DE PRIVILEGIOS</td>
	 						<td colspan="2" width="100"></td>	 						
	 						<td class="titulo">CÍRCULO AZUL</td>
	 						<td colspan="3" class="titulo"></td>
	 					</tr>	 					
	 				</table>
	 			</td> 				 			
	 		</tr>		
		<tr>
			<td>Menu Principal</td>
			<td>Nivel 1</td>
			<td>Nivel 2</td>
			<td>Nivel 3</td>
			<td>Nivel 4</td>
			<td>Nivel 5</td>
			<td>Nivel 6</td>
			<td>Nivel 7</td>
			<td style="font: bold">${perfilDescripcion}</td>
		</tr>
		<tr>
			<td class="menuPrincipal">Puntos</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td>${id1}</td>
		</tr>
		<tr>
			<td></td>
			<td class="nivel1">Consulta</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td>${id5}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td class="nivel2">Continuar</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td>${id20}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td class="nivel3">ValidaCrédito AR</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td>${id60}</td>
		</tr>
		<!-- 
		
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td class="nivel3">Consulta Puntos</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td>${id61}</td>
		</tr>
		
		 -->
		<tr>
			<td></td>
			<td class="nivel1">Detalles</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td>${id6}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td class="nivel2">Puntos</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td>${id21}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td class="nivel2">Movimientos</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td>${id22}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td class="nivel2">Redención</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td>${id23}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td class="nivel3">Histórico</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td>${id62}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td class="nivel3">Nueva</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td>${id63}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td class="nivel4">Red.Renova Addendum</td>
			<td></td>
			<td></td>
			<td></td>
			<td>${id86}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td class="nivel5">Busca Promociones</td>
			<td ></td>
			<td></td>
			<td>${id118}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td class="nivel5">Busca Beneficios</td>
			<td></td>			
			<td></td>
			<td>${id119}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td class="nivel5">Realiza Redención</td>
			<td></td>
			<td></td>
			<td>${id120}</td>
		</tr>
		<tr>
			  <td></td>
			  <td></td>
			  <td></td>
			  <td></td>
			  <td></td>
			  <td class="nivel5">Define IVA</td>
			  <td></td>
			  <td></td>
			  <td>${id121}</td>
		</tr>
		
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td class="nivel4">Red.sin Renovación de<br>Addendum</td>
			<td></td>
			<td></td>
			<td></td>
			<td>${id88}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td class="nivel5">Amigo Kit</td>
			<td></td>
			<td></td>
			<td>${id101}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td class="nivel6">Busca Promociones</td>
			<td></td>
			<td>${id135}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td class="nivel6">Busca Beneficios</td>
			<td></td>
			<td>${id136}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td class="nivel6">Realiza Redención</td>
			<td></td>
			<td>${id137}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td class="nivel6">Define IVA</td>
			<td></td>
			<td>${id138}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td class="nivel5">Venta Tiempo Aire</td>
			<td></td>
			<td></td>
			<td>${id102}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td class="nivel6">Busca Promociones</td>
			<td></td>
			<td>${id127}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td class="nivel7">Redimir</td>
			<td>${id139}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td class="nivel5">Amigo Chip</td>
			<td></td>
			<td></td>
			<td>${id103}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td class="nivel6">Busca Promociones</td>
			<td></td>
			<td>${id140}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td class="nivel6">Busca Beneficios</td>
			<td></td>
			<td>${id141}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td class="nivel6">Realiza Redención</td>
			<td></td>
			<td>${id142}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td class="nivel6">Define IVA</td>
			<td></td>
			<td>${id143}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td class="nivel5">Tarjetas Inalam.3G</td>
			<td></td>
			<td></td>
			<td>${id104}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td class="nivel6">Busca Promociones</td>
			<td></td>
			<td>${id144}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td class="nivel6">Busca Beneficios</td>
			<td></td>
			<td>${id145}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td class="nivel6">Realiza Redención</td>
			<td></td>
			<td>${id146}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td class="nivel6">Define IVA</td>
			<td></td>
			<td>${id147}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td class="nivel4">Consulta y Actualización<br>de Folios SAP</td>
			<td></td>
			<td></td>
			<td></td>
			<td>${id89}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td class="nivel5">Equipos en Plan<br>Tarifario</td>
			<td></td>
			<td></td>
			<td>${id105}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td class="nivel6">Actualizar</td>
			<td></td>
			<td>${id130}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td class="nivel5">Amigo Chip</td>
			<td></td>
			<td></td>
			<td>${id106}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td class="nivel6">Actualizar</td>
			<td></td>
			<td>${id131}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td class="nivel5">Equipos Amigo Kit</td>
			<td></td>
			<td></td>
			<td>${id107}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td class="nivel6">Actualizar</td>
			<td></td>
			<td>${id132}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td class="nivel5">Tarjetas 3G</td>
			<td></td>
			<td></td>
			<td>${id108}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td class="nivel6">Actualizar</td>
			<td></td>
			<td>${id133}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td class="nivel3">Cancelación</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td>${id64}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td class="nivel4">Aceptar</td>
			<td></td>
			<td></td>
			<td></td>
			<td>${id90}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td class="nivel5">Realiza Cancelación</td>
			<td></td>
			<td></td>
			<td>${id148}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td class="nivel5">180 días</td>
			<td></td>
			<td></td>
			<td>${id109}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td class="nivel5">60 días</td>
			<td></td>
			<td></td>
			<td>${id110}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td class="nivel2">Alianzas</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td>${id24}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td class="nivel3">Todas las opciones<br>del Menú</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td>${id65}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td class="nivel3">Histórico</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td>${id66}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td class="nivel3">Alta/Cambio</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td>${id67}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td class="nivel4">American Express</td>
			<td></td>
			<td></td>
			<td></td>
			<td>${id91}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td class="nivel5">Actualizar</td>
			<td></td>
			<td></td>
			<td>${id111}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td class="nivel5">Agregar</td>
			<td></td>
			<td></td>
			<td>${id112}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td class="nivel3">Cancelación</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td>${id68}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td class="nivel4">American Express</td>
			<td></td>
			<td></td>
			<td></td>
			<td>${id92}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td class="nivel5">Buscar</td>
			<td></td>
			<td></td>
			<td>${id113}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td class="nivel6">Aceptar</td>
			<td></td>
			<td>${id134}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td class="nivel3">Canjear</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td>${id69}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td class="nivel4">American Express</td>
			<td></td>
			<td></td>
			<td></td>
			<td>${id93}</td>
		</tr>
		
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td class="nivel3">Liberar Cupones<br>AMEX</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td>${id70}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td class="nivel4">Continuar</td>
			<td></td>
			<td></td>
			<td></td>
			<td>${id95}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td class="nivel2">Asignación</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td>${id25}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td class="nivel3">Asignar</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td>${id71}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td class="nivel4">Continuar</td>
			<td></td>
			<td></td>
			<td></td>
			<td>${id96}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td class="nivel3">Eliminar</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td>${id72}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td class="nivel4">Continuar</td>
			<td></td>
			<td></td>
			<td></td>
			<td>${id97}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td class="nivel3">Puntos por<br>Antiguedad</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td>${id73}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td class="nivel4">Continuar</td>
			<td></td>
			<td></td>
			<td></td>
			<td>${id98}</td>
		</tr>
		<tr>
			<td></td>
			<td class="nivel1">Imprimir</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td>${id8}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td class="nivel2">Redención</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td>${id27}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td class="nivel3">Generar Archivo</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td>${id75}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td class="nivel3">Imprimir</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td>${id76}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td class="nivel2">Alianza</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td>${id28}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td class="nivel3">Generar Archivo</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td>${id77}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td class="nivel3">Imprimir</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td>${id78}</td>
		</tr>
		<tr>
			<td></td>
			<td class="nivel1">Renuncia</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td>${id9}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td class="nivel2">Reactivar Puntos</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td>${id29}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td class="nivel2">Constancia de Renuncia</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td>${id30}</td>
		</tr>
		<tr>
			<td></td>
			<td class="nivel1">Transferencia</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td>${id10}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td class="nivel2">Transferencia para cambio de<br>Región
		</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td>${id31}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td class="nivel3">Transferir</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td>${id79}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td class="nivel2">Transferencia para plan<br>anexo</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td>${id32}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td class="nivel3">Transferir</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td>${id80}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td class="nivel2">Transferencia Cliente Excelente</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td>${id160}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td class="nivel3">Transferir</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td>${id161}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td class="nivel4">Más de 90 días, regionales</td>
			<td></td>
			<td></td>
			<td></td>
			<td>${id162}</td>
		</tr>
		<tr>
			<td></td>
			<td class="nivel1">Retención</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td>${id11}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td class="nivel2">Captura cuenta anterior</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td>${id33}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td class="nivel2">Generar Certi. de Lealtad</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td>${id34}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td class="nivel2">Consultar Certi. de Lealtad</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td>${id35}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td class="nivel2">Imprimir Certi. de Lealtad</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td>${id36}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td class="nivel2">Cancelar Certi. de Lealtad</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td>${id37}</td>
		</tr>
		<tr>
			<td></td>
			<td class="nivel1">Manuales y<br>Promociones
		</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td>${id12}</td>
		</tr>
		<tr>
			<td class="menuPrincipal">Menu<br>Inbursa</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td>${id190}</td>
		</tr>
		<tr>
			<td></td>
			<td class="nivel1">Consulta<br>Inbursa</td>
			<td></td>
            <td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td>${id191}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td class="nivel2">Consulta</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td>${id192}</td>
		</tr>
		<tr>
			<td class="menuPrincipal">Soporte</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td>${id2}</td>
		</tr>
		<tr>
			<td></td>
			<td class="nivel1">Consulta<br>Puntitos</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td>${id13}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td class="nivel2">Buscar</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td>${id38}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td class="nivel2">Cancelar</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td>${id39}</td>
		</tr>
		<tr>
			<td></td>
			<td class="nivel1">Usuarios</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td>${id14}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td class="nivel2">Buscar</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td>${id40}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td class="nivel2">Agregar</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td>${id41}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td class="nivel2">Resetea</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td>${id42}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td class="nivel2">Actualizar</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td>${id43}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td class="nivel2">Eliminar</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td>${id44}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td class="nivel2">Limpiar</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td>${id45}</td>
		</tr>
		<tr>
			<td></td>
			<td class="nivel1">Lineas<br>Restringidas</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td>${id172}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td class="nivel2">Procesar</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td>${id173}</td>
		</tr>
		<tr>
                  <td></td>
                  <td></td>
                  <td class="nivel2">Descargar</td>
                  <td></td>
                  <td></td>
                  <td></td>
                  <td></td>
                  <td></td>
                  <td>${id186}</td>
            </tr>
		<tr>
			<td class="menuPrincipal">Administración</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td>${id3}</td>
		</tr>
		<tr>
			<td></td>
			<td class="nivel1">Avisos</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td>${id15}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td class="nivel2">Consultar</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td>${id46}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td class="nivel2">Eliminar</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td>${id47}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td class="nivel2">Agregar</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td>${id48}</td>
		</tr>
		<tr>
			<td></td>
			<td class="nivel1">Puntos de<br>Venta
		</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td>${id16}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td class="nivel2">Consultar</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td>${id49}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td class="nivel2">Agregar</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td>${id50}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td class="nivel2">Actualizar</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td>${id51}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td class="nivel2">Eliminar</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td>${id52}</td>
		</tr>
		<tr>
			<td></td>
			<td class="nivel1">Promociones</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td>${id17}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td class="nivel2">Alta de Documentos</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td>${id53}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td class="nivel3">Adjuntar</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td>${id81}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td class="nivel3">Eliminar</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td>${id82}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td class="nivel2">Consultas</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td>${id54}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td class="nivel3">Consulta</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td>${id83}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td class="nivel3">Actualizar</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td>${id159}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td class="nivel3">Exportar</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td>${id84}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td class="nivel2">Procesa Catálogos</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td>${id55}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td class="nivel3">Procesar</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td>${id85}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td class="nivel2">Carga Masiva FTP</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td>${id193}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td class="nivel3">Enviar</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td>${id194}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td class="nivel2">Distribuidores</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td>${id157}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td class="nivel3">Consultar</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td>${id158}</td>
		</tr>
		<tr>
			<td></td>
			<td class="nivel1">Reportes</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td>${id166}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td class="nivel2">Exportar</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td>${id167}</td>
		</tr>
		
		<tr>
			<td></td>
			<td class="nivel1">Puntos</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td>${id174}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td class="nivel2">Asignación</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td>${id175}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td class="nivel3">Perfiles Válidos</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td>${id176}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td class="nivel4">Consultar</td>
			<td></td>
			<td></td>
			<td></td>
			<td>${id177}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td class="nivel4">Agregar</td>
			<td></td>
			<td></td>
			<td></td>
			<td>${id178}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td class="nivel4">Actualizar</td>
			<td></td>
			<td></td>
			<td></td>
			<td>${id179}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td class="nivel4">Eliminar</td>
			<td></td>
			<td></td>
			<td></td>
			<td>${id180}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td class="nivel3">Motivos de Asignación</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td>${id181}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td class="nivel4">Consultar</td>
			<td></td>
			<td></td>
			<td></td>
			<td>${id182}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td class="nivel4">Agregar</td>
			<td></td>
			<td></td>
			<td></td>
			<td>${id183}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td class="nivel4">Actualizar</td>
			<td></td>
			<td></td>
			<td></td>
			<td>${id184}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td class="nivel4">Eliminar</td>
			<td></td>
			<td></td>
			<td></td>
			<td>${id185}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td class="nivel3">Líneas de Prueba</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td>${id187}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td class="nivel4">Agregar</td>
			<td></td>
			<td></td>
			<td></td>
			<td>${id188}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td class="nivel4">Eliminar</td>
			<td></td>
			<td></td>
			<td></td>
			<td>${id189}</td>
		</tr>
		
		
		<tr>
			<td></td>
			<td class="nivel1">Reportes Finanzas</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td>${id171}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td class="nivel2">Generar</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td>${id195}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td class="nivel2">Exportar</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td>${id196}</td>
		</tr>
		
		<tr>
			<td class="menuPrincipal">Seguridad</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td>${id4}</td>
		</tr>
		<tr>
			<td></td>
			<td class="nivel1">Reporte<br>Usuarios
		</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td>${id18}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td class="nivel2">Consultar</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td>${id56}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td class="nivel2">Exportar</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td>${id57}</td>
		</tr>
		<tr>
			<td></td>
			<td class="nivel1">Reporte<br>Perfiles</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td>${id19}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td class="nivel2">Consultar</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td>${id58}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td class="nivel2">Exportar PDF</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td>${id59}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td class="nivel2">Exportar Excel</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td>${id150}</td>
		</tr>
		<tr>
			<td></td>
			<td class="nivel1">Privilegios</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td>${id149}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td class="nivel2">Consultar</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td>${id151}</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td class="nivel2">Actualizar</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td>${id152}</td>
		</tr>		
	</table>       
    </DIV>
</body>
</html>


