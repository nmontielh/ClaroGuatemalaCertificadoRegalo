<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/permisos.tld" prefix="securityCa"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Avisos</title>
	<link rel="stylesheet" href='<c:url value="/commons/js/sc_textsheet.css"/>' type="text/css">
    <link rel="stylesheet" href='<c:url value="/commons/css/calendar-blue.css"/>' type="text/css">
    <script src='<c:url value="/commons/js/calendar.js"/>' type="text/javascript"></script>
    <script src='<c:url value="/commons/js/calendar-es.js"/>' type="text/javascript"></script>
    <script src='<c:url value="/commons/js/calendar-setup.js"/>' type="text/javascript"></script>
    <script src='<c:url value="/commons/js/administracion.js"/>' type="text/javascript"></script>
</head>

<body marginwidth="0" marginheight="0" style="margin: 0px" background="<c:url value="/commons/images/backgroundlight.gif"/>">
 	
<script type="text/javascript">
	document.parentWindow.parent.activaCargando('hidden','none');
	 
	window.onload = function() {	
	Calendar.setup({
    	inputField: "idfechaConIni",
    	ifFormat:   "%m/%d/%Y",
    	button:     "idfechaConIni"
  		});
	
	Calendar.setup({
    	inputField: "idfechaConFin",
    	ifFormat:   "%m/%d/%Y",
    	button:     "idfechaConFin"
  		});
	
	Calendar.setup({
    	inputField: "idfechainiA",
    	ifFormat:   "%m/%d/%Y",
    	button:     "idfechainiA"
  		});
	
	Calendar.setup({
    	inputField: "idfechafinA",
    	ifFormat:   "%m/%d/%Y",
    	button:     "idfechafinA"
  		});
  		
	}
	
	
	function returnFolio(){
		var elegidos = new Array();
		var opciones = document.getElementsByName('idaviso');
		var cadena='';
		
		for(i=0;i<opciones.length;i++){
			if(opciones[i].checked==true){
				elegidos[i]= opciones[i].value;					
			}else{
				elegidos[i]= null;					
			}
		}
		for(i=0;i<elegidos.length;i++){
			if(elegidos[i]!=null){
				cadena = cadena + elegidos[i] + ',';
			}
		}

		if(cadena!=null && cadena!='' && cadena.length>0){
			var sIdsAvisos = document.getElementById("sIdsAvisos");
			sIdsAvisos.value = cadena;
			frmAvisos.action="./avisosAccion.do?valor=2";
			frmAvisos.method="post";						
			frmAvisos.submit();
		}		
			
	}
	
	function compara_fechas(fechaAct, fechaExp)   
  {   
    var xDay=fechaAct.substring(3, 5);   
    var xMonth=fechaAct.substring(0, 2);   
    var xYear=fechaAct.substring(6,10);   
    var yDay=fechaExp.substring(3, 5);   
    var yMonth=fechaExp.substring(0, 2);   
    var yYear=fechaExp.substring(6,10);   
    
    if (xYear > yYear)   
    {   
        return(true)   
    }   
    else  
    {   
      if (xYear == yYear)   
      {
        if (xMonth > yMonth)   
        {   
            return(true)   
        }   
        else  
        {    
          if (xMonth == yMonth)   
          {  
            if (xDay > yDay)   
              return(true);   
            else  
              return(false);  
          }   
          else  
            return(false);   
        }   
      }   
      else  
        return(false);   
    }   
}  
	
	function validaDatos(opcion,fechaIni, fechaFin,descripcion){
		if(descripcion == "" || fechaIni == ""  || fechaFin == ""){
			window.alert("No pueden ir datos vacios");
			return false;
		} 
		if (compara_fechas(fechaIni, fechaFin))   {
			alert("-> La Fecha de expiración no puede ser menor a la Fecha de Activación");
			return false;
		}	
			frmAltaAvisos.action="./avisosAccion.do?valor=1" +"&fechaAct="+fechaIni+"&fechaExp="+fechaFin+"&descripcion="+descripcion+"&tipoAviso="+document.getElementById("tipoavisocte").value ;		
			frmAltaAvisos.method="post";						
			frmAltaAvisos.submit();
	}	
	
	
	function consulta() {	
	var sFechaIni, sFechaFin;
		
	 sFechaIni = document.getElementById("idfechaConIni").value;
	 sFechaFin = document.getElementById("idfechaConFin").value;
	 
	if( sFechaIni == ""  || sFechaFin == ""){
			window.alert("No pueden ir datos vacios");
			return false;
	} 
	
	if (compara_fechas(sFechaIni, sFechaFin))   {
			alert("-> La Fecha de expiración no puede ser menor a la Fecha de Activación");
			return false;
	}	
	 				
	frmAvisos.action="./avisos.do?valor=2" + "&fechaIni=" + sFechaIni + "&fechaFin=" + sFechaFin + "&tipoAviso=" + document.getElementById("idtipoAvisoCon").value +
	"&estatus=" + document.getElementById("idEstatusCon").value;
	frmAvisos.method="post";
	frmAvisos.submit();		
	}
	
	</script>

<DIV id="idBusqueda"   style="top:0px;height: 80px;width: 98%;visibility: visible;display: block;left: 10px;position:static;BORDER:solid 1px #99ccff; bgcolor=#F2F2F2" ;>		
<table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
	<tr><td colspan="4" height="20"></td> </tr>
 	<tr height="20">
     <td  class="healineblue1" align="left"  height="20">&nbsp;Tipo Aviso:</td>
	 <td>
	  	<select class="selectC" id="idtipoAvisoCon" size=1>
			<option value="FP">Falla en Puntos</option>
			<option value="GR">Aviso General</option>
		</select>
	 </td>
	 <td  class="healineblue1"  align="left">&nbsp;Estatus:</td>
	 <td>
	 	<select class="selectC" id="idEstatusCon" size=1>
			<option value="A">Activo</option>
			<option value="I">Inactivo</option>
		</select>
	 </td>   	            
 	</tr> 
 	<tr >
		<td class="healineblue1" width="15%" valign="middle" height="20">&nbsp;Fecha Inicial:</td>	
		<td align="left">
			<input class="InputText" type="text" name="idfechaConIni" id="idfechaConIni" readonly="readonly" align="middle">			
        </td>
        <td class="healineblue1" width="15%" valign="middle" height="20">&nbsp;Fecha Final:</td>
		<td align="left">
			<input class="InputText" type="text" name="idfechaConFin" id="idfechaConFin" readonly="readonly" align="middle" />
        </td>
   	 </tr>
   	 <tr><td colspan="4" height="15"></td> </tr>
 </table>
</DIV>
<div id="idBotones" style="top:90px;height:23px;width: 98%;visibility: visible;display: block;position:static;">
      	<table width="95%" align="center">
	   		 <tr>
	   	 		<td align="left"  height="20" style="width: 119px; height: 41px">
	   	 			<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="46">
			   	 		<a class="LinkOut" style="width:100px" onmouseover='this.className="LinkIn";' onmouseout='this.className="LinkOut";'
		                    	id="Link1" onClick="consulta();">&nbsp;Consultar&nbsp;&nbsp;</a>
		            </securityCa:validaPerfil>
		        </td>  	 		 																										
	   	 		<td align="left" style="width: 112px">
	   	 			<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="47">
	   	 				<a class="LinkOut" style="width:100px" onmouseover='this.className="LinkIn";' onmouseout='this.className="LinkOut";'
                    	id="Link1" onClick="returnFolio();">&nbsp;Eliminar&nbsp;&nbsp;</a>
	   	 			</securityCa:validaPerfil>	   	 		
	   	 		</td>
	   	 		<td align="center" colspan="2" style="width: 80%">
	   	 		<c:if test='${objMensaje.idMensaje == "0"}'>
					<font face="Tahoma,sans-serif" size="2" color=red >
						${objMensaje.mensaje}
					</font>
				</c:if>	 
	   	 		</td>   
	   	 			 			   	 		
	   	 	</tr>
		</table>
</div>
<form name="frmAvisos" id="frmAvisos" method="post" style=" top:110px; height: 170px ; ">

	<input type="hidden" name="sIdsAvisos" id="sIdsAvisos">
 	
	<DIV id="idEncabezado"  style="top:110px; height: 20px;width: 98%; visibility: visible;display: block;position:static;  OVERFLOW-Y: scroll;">		 
		 <table border="1" cellspacing="0" cellpadding="0" align="center" width="95%" >
			<tr bgcolor="#ECF0DB" class="healineblue1" align="center" >
				<td width="7%"  height="15" >ID</td>
				<td width="20%" height="15" >FECHA</td>
				<td width="20%" height="15" >DESCRIPCIÓN</td>
				<td width="10%" height="15" >CREADO POR</td>			
				<td width="15%" height="15" >CATEGORÍA</td>
				<td width="10%" height="15" >ESTATUS</td>			
			</tr>
		</table>
 	</DIV> 	
	<DIV id="idHistorico"   style="top:130px; OVERFLOW-Y: scroll; OVERFLOW-X: hidden; height: 120px;width: 98%;visibility: visible;display: block;position:static;">		 
		<table  border="1" width="95%" cellpadding="0" cellspacing="0" align="center" >
	        <c:forEach var="avisosTO" items="${avisosTO}" varStatus="total" >
    	    <c:set var="contador" value="${total.count}"/>
				 <tr class="X3" bgcolor="<c:if test="${contador %2 !=0 }">#D9EBF2</c:if>">
					<td width="7%"  align="left"  >
						<input type="checkbox"	id="idaviso" name="idaviso" value="${avisosTO.idAviso}">
						<c:out value="${avisosTO.idAviso}"></c:out>
					</td>
					<td width="20%" align="center" >${avisosTO.fechaAlta}</td>
					<td width="20%" align="left" >${avisosTO.descripcion}</td>
					<td width="10%" align="center">${avisosTO.idUsuario}</td>
					<td width="15%" align="center" >${avisosTO.tipoAviso}</td>
					<td width="10%" align="center">${avisosTO.estatus}</td>
				</tr>
			</c:forEach>
		</table>
 	</DIV>
 	<DIV id="divTotal" style="width:90% height:40px;position: static;">
      <table style="width: 94%" align="center" >
       	<tr bgcolor="#ECF0DB" class="healineblue1">
       		<td><c:out value="${contador}"></c:out> Registro(s) Encontrado(s).</td>
       	</tr>
      </table>
      </DIV>	
 </form>
 
<form name="frmAltaAvisos" id="frmAltaAvisos" method="post" action="" style="height: 120">
<DIV id="idAlta"   style="   top:200px height: 140px;width: 98%;visibility: visible;display: block;left: 10px;position:static;BORDER:solid 1px #99ccff;">
	<table width="96%" border="0" cellspacing="0" cellpadding="0" align="center" bordercolor="#ffffff" > 
    	<tr> 
           <td width="16%" class="healinenavy" valign="middle" height="20" align="left" bordercolor="#74C7F0" bgcolor ="#C0E5F8">Alta de Avisos</td>
           <td width="16%" class="healineblue1" valign="middle" height="20">&nbsp;</td>
           <td width="16%" class="healineblue1" valign="middle" height="20">&nbsp;</td>
           <td width="16%" class="healineblue1" valign="middle" height="20">&nbsp;</td>
           <td width="16%" class="healineblue1" valign="middle" height="20">&nbsp;</td>
           <td width="16%" class="healineblue1" valign="middle" height="20">&nbsp;</td>
      </tr>
  	<tr> 
          <td width="16%" class="healineblue1" valign="middle" height="20">Descripción</td>
          <td colspan="5"  class="healineblue1" valign="middle" height="20">
		  <TEXTAREA class="textArea" name="adescripcioncteA" rows="2" cols="80" style="width: 563px"></TEXTAREA>
          </td>
       </tr>
        <tr>
			<td width="16%" class="healineblue1"  valign="middle" height="20">Fecha Activación:</td>	
			<td width="16%" align="left">
			<input class="InputText"  type="text" name="idfechainiA" id="idfechainiA" readonly="readonly" />			
        	</td>
        	<td width="16%" class="healineblue1" align="center" valign="middle" height="20">Fecha Expiración:</td>
			<td width="16%" align="left">
			<input class="InputText"  type="text" name="idfechafinA" id="idfechafinA" readonly="readonly" />
			
        	</td>
        	<td width="16%" class="healineblue1" align="center"  valign="middle" height="20">Categoría:</td>
         	<td  width="16%" class="healineblue1" valign="middle" height="26">
         	<select class="selectC" size="1"  name="tipoavisocte" onClick="">
			<option selected value="FP">Falla en Puntos</option>
			<option value="GR">Aviso General</option>
			</select></td>
   	  	</tr>
       <tr >
        <td colspan="6" valign="middle" height="15">&nbsp;</td>   	 	
   	   </tr>
	   <tr > 
        <td colspan="6" align="center">
        	<securityCa:validaPerfil perfilTO="${sessionScope.usuarioTO.perfilTO}" idProcesoCA="48">
        		<a class="LinkOut" style="width:100px" onmouseover='this.className="LinkIn";' onmouseout='this.className="LinkOut";'
        			id="Link1" onClick="validaDatos(1,idfechainiA.value,idfechafinA.value,adescripcioncteA.value);">&nbsp;Agregar&nbsp;&nbsp;</a>
        	</securityCa:validaPerfil>        
        </td>            	        
       </tr>
     </table>
</DIV>
</form> 
</body>
</html>