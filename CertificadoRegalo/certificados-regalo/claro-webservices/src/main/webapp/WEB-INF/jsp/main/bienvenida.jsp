<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="/permisos" prefix="fn"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
Bienvenido : ${sessionScope.usuarioTO.nombre} 

<!-- Tabla de Aviso de Reportes Folio-78902 JSC-->
	<table width="70%" align ="center" >
		<tr height="75">
		</tr>
		<tr>
			<td bgcolor="black" align="center" >
				<FONT face="Arial" SIZE ="-1" COLOR="#FFFFFF">
					<B>Para poder visualizar una l�nea en Claro Club es importante que cumpla,
					las siguientes condiciones:</B>
				</FONT>
			</td>
		</tr>		
		<tr>
			<td align="left"> 
				<FONT SIZE ="-1" COLOR="#380474">	
					<br>				
					- La l�nea no debe tener ciclo corporativo, 20, 31, 32, 60 y 61.<br>
					- Debe tener un plan v�lido en Claro club, cons�ltalos en el m�dulo de manuales y promociones.<br>
					- El plan que tenga la l�nea debe ser v�lido en la regi�n.<br>					
					- Debe de haber transcurrido un ciclo completo de facturaci�n (no corte) a partir de la fecha que se di� de alta la l�nea.<br>
					- Si se realiz� cambio de ciclo, debe transcurrir un ciclo completo de facturaci�n (no corte), a partir de la fecha en que se hizo el cambio.					
					<br><br>
					<FONT face="Arial" SIZE ="-1" COLOR="red"><B>Ejemplo de ciclo completo:</B></FONT> <br><br>					
					<B>Si se realiza el cambio el 26 de abril y la l�nea tiene ciclo 10.</B><br>
					<B>10 de Mayo - 10 de Junio, despu�s de �sta fecha se podr� visualizar la l�nea.</B>					
					<br><br> 
					Si la linea que consultas cumple con las condiciones para que se visualice
					y al ingresar indica que la cuenta no est� cargada, se deb�ra consultar por 
					n�mero de cuenta. Si el sistema contin�a sin mostrar datos, favor de reportarlo:
					<UL type = disk >
					<LI>Por folio SISAP.
					<LI>En la Ext. 2497, 8717, 1611 y 1981  
					<LI>Por correo: <FONT SIZE ="-1" COLOR="#0000FF"><B> alfredo.marquez@mail.telcel.com, veronica.diaz@mail.telcel.com, jackeline.contreras@telcel.com, jenny.reyes@telcel.com </B></FONT>	 
					</UL>								
					<br><br>
				</FONT>
	        </td>
	    </tr>
	    	<td bgcolor="#E31D1A" align="center" ></td>
	</table>	
</body>
</html>