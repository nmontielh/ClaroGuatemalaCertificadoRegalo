<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/permisos.tld" prefix="securityCa"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href='<c:url value="/commons/js/sc_textsheet.css"/>' type="text/css">
<title>Claro Club: Sistema de Recompensas</title>
<c:set var="perfil" value="${sessionScope.usuarioTO.perfilTO.idPerfilN}" />
<script>
	
	var oIdx = new Array(0,0,0,0,0,0,0,0);
	var oDiv = null;
	var oTar = null;
		
	function setConsultaPrincipal(_metodo){
		
		FrameAviso.location = "./marquesinaAvisos.do";				
	  	if(oDiv != null) oDiv.className = "menuInactivo";
	  	if(oTar != null) {
	  		oTar.style.display = "none";
	  		oTar.style.visibility="hidden";	  	  			
	  	}
	  		  			
	  	oDiv = document.getElementById("B0" + _metodo);
	  	oTar = document.getElementById("Tarjeta" + _metodo);	  	
	  	oDiv.className= "menuActivo";	  	
	  	oTar.style.display= "block";	  	
	  	oTar.style.visibility="visible";
	  	  		  		
	  	//Llama a Datos;		  		
	  	if(_metodo==2 && oIdx[1]==0){
	  		oIdx[1]=1;	  				  			
	  		Frame2.location ="./consultaPrincipal.do";	  			  						  			  	
	  	}	  		  	
	  	else if(_metodo==3 && oIdx[2]==0){  			
  			oIdx[2]=1;  			  			
  			Frame3.location ="./menuPuntitos.do";
  		}	  	
	  	else if(_metodo==4 && oIdx[3]==0){
	  		oIdx[3]=1;
	  		Frame4.location ="./menuAdministracion.do";
	  	}
	  	else if(_metodo==5 && oIdx[4]==0){
	  		oIdx[4]=1;
  			Frame5.location = "./seguridad.do";
  		}
  		else if(_metodo==6 && oIdx[5]==0){
	  		oIdx[5]=1;
	  		parent.location ="./salir.do";	  		
	  	}else if(_metodo==7 && oIdx[6]==0){
	  		oIdx[6]=1;
	  		Frame7.location ="./muestraInbursa.do";	  		
	  	}else if(_metodo==8 && oIdx[7]==0){
	  		oIdx[7]=1;
	  		Frame8.location ="./menuCertificados.do";	  		
	  	}
	}
			
</script>
</head>
<body class="menu" onload="setConsultaPrincipal(1);"> 
<input type="hidden" name="modalidad"  id="modalidad" value="0">
	 <table align="center" border="0" width="100%">
			<tr>
			<td width="7%"></td>
				<td width="1%" align="right">
					<img src='<c:url value="/commons/images/claro/logo_claro.png"/>'>
				</td>
				<td width="1%" align="center" width="10%" style="vertical-align: middle;">
					<font face="Arial" size="5" color="black"><b>Club</b></font>
				</td>
				<td width="68%" >
       					<IFRAME scrolling="no" name="FrameAviso" id="FrameAviso" WIDTH="1px" HEIGHT="1px" style="visibility:hidden; background-color:#E31D1A border: hidden;" frameborder="0" AllowTransparency></IFRAME>
				</td>
				<td width="30%" align="center">
					<span style="text-align: right;">
					<font  size="2" face="Times New Roman" color="black">
					<script>
						d = new Date();
					    d = d.toLocaleString();
					    d = d.substring(0,d.indexOf(":")-2);
					    document.write(d);
				    </script></font>
					</span>				
				</td>
			</tr>
		</table>
			<!--<table align=center border=0 cellpadding="0" cellspacing="0" width="100%">
		<tr >
			<td align="center" bgcolor="#E31D1A" width="133px">
				<img src='<c:url value="/commons/images/claro/logo_claro.png"/>'>
			</td>			
			<td bgcolor="#E31D1A" align="right">
				<font class="tituloNegro">Claro </font>
				<font class="tituloBlanco">Club</font>				
			</td>
			<td width="20px" bgcolor="#E31D1A">
			<IFRAME scrolling="no" name="FrameAviso" id="FrameAviso" WIDTH="1px" HEIGHT="1px" style="visibility:hidden; background-color:#E31D1A border: hidden;" frameborder="0" AllowTransparency></IFRAME>
			</td>
		</tr>
		<tr>
		</tr>
	</table>-->
	<br>	
	 <TABLE border=0 cellspacing=0 style="width: 600px;">
			
			<TR valign="bottom">
				<!-- if(!PerfilTO.valida(Constanate.PANTALLA_CP)) -->
				<TD style="padding-left: 0px;width: 150px;">				
    				<SPAN id="B01" class="menuInactivo" onclick="setConsultaPrincipal(1)"><font class="filtro">&nbsp;Bienvenida&nbsp;</font> </SPAN>
 				</TD>
 				 <TD  style="padding-left: 0px;width: 150px;">
 					<SPAN id="B08" class="menuInactivo" onclick="setConsultaPrincipal(8)"><font class="filtro">&nbsp;Certificados&nbsp;</font> </SPAN>
 				</TD>
 				<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="1">
 					<TD  style="padding-left: 0px;width: 150px;">
 						<SPAN id="B02" class="menuInactivo" onclick="setConsultaPrincipal(2)"><font class="filtro">&nbsp;Puntos&nbsp;</font> </SPAN>
 					</TD>
 				</securityCa:validaPerfil>
 				<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="190">
 					<TD  style="padding-left: 0px;width: 150px;">
 						<SPAN id="B07" class="menuInactivo" onclick="setConsultaPrincipal(7)"><font class="filtro">&nbsp;Inbursa&nbsp;</font> </SPAN>
 					</TD>
 				</securityCa:validaPerfil>
 				<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="2">
	 				<TD  style="padding-left: 0px;width: 200px;">
	 					<SPAN id="B03" class="menuInactivo" onclick="setConsultaPrincipal(3);"><font class="filtro">&nbsp;Soporte&nbsp;</font> </SPAN>
	 				</TD>
	 			</securityCa:validaPerfil>
	 			<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="3">
	 				<TD  style="padding-left: 0px;width: 200px;">
	    				<SPAN id="B04" class="menuInactivo" onclick="setConsultaPrincipal(4)"><font class="filtro">&nbsp;Administraci&oacute;n&nbsp;</font> </SPAN>
	 				</TD>
	 			</securityCa:validaPerfil>
 				<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="4">
 					<TD  style="padding-left: 0px;width: 200px;">
	    				<SPAN id="B05" class="menuInactivo" onclick="setConsultaPrincipal(5)"><font class="filtro">&nbsp;Seguridad&nbsp;</font> </SPAN>
	 				</TD>
	 			</securityCa:validaPerfil>
 				<TD  style="padding-left: 0px;width: 200px;">
    				<SPAN id="B06" class="menuInactivo" onclick="setConsultaPrincipal(6)"><font class="filtro">&nbsp;Salir&nbsp;</font> </SPAN>
 				</TD> 			 				 				
       		</TR>
       	</TABLE>
       	<DIV id="Tarjeta1" class="TarjetaDetalle" style="padding-left:10px; height: 530px;width: 98%;visibility: hidden;display: none;position:absolute;BORDER:solid 2px #4d7097;">
     		<jsp:include page="bienvenida.jsp" />
  		</DIV>
       	<DIV id="Tarjeta2" class="TarjetaDetalle" style="padding-left:10px;height: 530px;width: 98%;visibility: hidden;display: none;position:absolute;BORDER:solid 2px #4d7097;">
     		<IFRAME name="Frame2" id="Frame2" scrolling="no" WIDTH="100%" HEIGHT="100%" frameborder="0" AllowTransparency></IFRAME>
  		</DIV>
  		
  		
	  	<DIV id="Tarjeta3" class="TarjetaDetalle" style="padding-left:10px;height: 530px;width:98%;visibility: hidden;display: none;position:absolute;BORDER:solid 2px #4d7097;">
	     	<IFRAME name="Frame3" id="Frame3" scrolling="no" WIDTH="100%" HEIGHT="100%"  frameborder="0" AllowTransparency></IFRAME>
	  	</DIV>
	  	
	  	
	  	<DIV id="Tarjeta4" class="TarjetaDetalle" style="padding-left:10px;height: 530px;width:98%;visibility: hidden;display: none;position:absolute;BORDER:solid 2px #4d7097;">
	     	<IFRAME name="Frame4" id="Frame4" scrolling="no" WIDTH="100%" HEIGHT="100%"  frameborder="0" AllowTransparency></IFRAME>
	  	</DIV>
	  	<DIV id="Tarjeta5" class="TarjetaDetalle" style="padding-left:10px;height: 530px;width:98%;visibility: hidden;display: none;position:absolute;BORDER:solid 2px #4d7097;">
	     	<IFRAME name="Frame5" id="Frame5" scrolling="no" WIDTH="100%" HEIGHT="100%"  frameborder="0" AllowTransparency></IFRAME>
	  	</DIV>
	    <DIV id="Tarjeta6" class="TarjetaDetalle" style="padding-left:10px;height: 530px;width:98%;visibility: hidden;display: none;position:absolute;BORDER:solid 2px #4d7097;">
	     	<IFRAME name="Frame6" id="Frame6" scrolling="no" WIDTH="100%" HEIGHT="100%"  frameborder="0" AllowTransparency></IFRAME>
	  	</DIV>
	  	<DIV id="Tarjeta7" class="TarjetaDetalle" style="padding-left:10px;height: 530px;width:98%;visibility: hidden;display: none;position:absolute;BORDER:solid 2px #4d7097;">
	     	<IFRAME name="Frame7" id="Frame7" scrolling="no" WIDTH="100%" HEIGHT="100%"  frameborder="0" AllowTransparency></IFRAME>
	  	</DIV>
	  	<DIV id="Tarjeta8" class="TarjetaDetalle" style="padding-left:10px;height: 530px;width:98%;visibility: hidden;display: none;position:absolute;BORDER:solid 2px #4d7097;">
	     	<IFRAME name="Frame8" id="Frame8" scrolling="no" WIDTH="100%" HEIGHT="100%"  frameborder="0" AllowTransparency></IFRAME>
	  	</DIV>			  	
</body>
</html>