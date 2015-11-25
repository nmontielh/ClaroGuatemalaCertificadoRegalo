function deshabilita(){	
	if (frmAplicaRedencion.planNuevo.value != ""){		
	 frmAplicaRedencion.planNuevo.disabled = "disable";		 
	 }		
	 if (frmAplicaRedencion.planNuevoCR.value != ""){		
	 frmAplicaRedencion.planNuevoCR.disabled = "disable";		 
	 }
	 if (frmAplicaRedencion.AdendumCR.value != ""){		
	 frmAplicaRedencion.AdendumCR.disabled = "disable";		 
	 }
	 if (frmAplicaRedencion.MesesCR.value != ""){		
	 frmAplicaRedencion.MesesCR.disabled = "disable";		 
	 }
}

function habilitaCampos(){	
	frmAplicaRedencion.planNuevo.disabled = false;
	frmAplicaRedencion.planNuevoCR.disabled = false;
	frmAplicaRedencion.AdendumCR.disabled = false;
	frmAplicaRedencion.MesesCR.disabled = false;
	/*
	document.getElementById("TarjetaRedencionesCon").style.visibility="visible";
	document.getElementById("TarjetaRedencionesCon").style.display="block";
	
	document.getElementById("TarjetaCareg").style.visibility="visible";
	document.getElementById("TarjetaCareg").style.display="block";
	*/
	
}

/*Multicotizador II JAPA 28/03/2012  Folio 84048 y ZZZZZZ : Se elimina el div que muestra campos para TarjetaRedencionesCon y TarjetaCareg */
function inhabilitaCampos(){	
	frmAplicaRedencion.planNuevo.disabled = true;
	frmAplicaRedencion.planNuevoCR.disabled = true;
	frmAplicaRedencion.AdendumCR.disabled = true;
	frmAplicaRedencion.MesesCR.disabled = true;
	/*
	document.getElementById("TarjetaRedencionesCon").style.visibility="hidden";
	document.getElementById("TarjetaRedencionesCon").style.display="none";
	
	document.getElementById("TarjetaCareg").style.visibility="hidden";
	document.getElementById("TarjetaCareg").style.display="none";
	*/
}

function limpiaCampos(){
	if(document.getElementById("planNuevo").value =! ""){
		document.getElementById("planNuevo").value = "";
	}
}

function limpiaCamposCareg(){
	if(document.getElementById("planNuevoCR").value =! ""){
		document.getElementById("planNuevoCR").value = "";
	}
	if(document.getElementById("AdendumCR").value =! ""){
		document.getElementById("AdendumCR").value = "";
	}
	if(document.getElementById("MesesCR").value =! ""){
		document.getElementById("MesesCR").value = "";
	}
}
	
		
function activaMenu(){
	document.getElementById("inputCon").style.visibility="visible";
	document.getElementById("inputCon").style.display="inline";
	document.getElementById("inputCareg").style.visibility="visible";
	document.getElementById("inputCareg").style.display="inline";
	document.getElementById("inputSap").style.visibility="visible";
	document.getElementById("inputSap").style.display="inline";
	document.getElementById("inputSin").style.visibility="visible";
	document.getElementById("inputSin").style.display="inline";	
	/*Multicotizador II JAPA 28/03/2012  Folio 84048 y ZZZZZZ : Se elimina el div que muestra campos para TarjetaRedencionesCon y TarjetaCareg */
	document.getElementById("selecRedencion").style.visibility="visible";
	document.getElementById("selecRedencion").style.display="inline";
}


function getTipoRedencion(){

	if(frmAplicaRedencion.tipoReden.length == undefined) {
		tipoReden = frmAplicaRedencion.tipoReden.value;
	} else {
	
		var radios = document.getElementsByName('tipoReden');
		for (var i = 0; i < radios.length; i++) {
			if (radios[i].checked) {
				tipoReden = radios[i].value;
			
				if(tipoReden == 'SIN') {
					var radiosS = document.getElementsByName('tipoRedenS');
					for (var i = 0; i < radiosS.length; i++) {
						if (radiosS[i].checked) {
							tipoRedenS=radiosS[i].value;
						}
					}
				}
			}     
		}
	} 
	
	/*
	if(frmAplicaRedencion.tipoReden.length == undefined) {
		tipoReden = frmAplicaRedencion.tipoReden.value;
	} else {
		if(frmAplicaRedencion.tipoReden[0].checked){		
			tipoReden = frmAplicaRedencion.tipoReden[0].value;		
		}else if(frmAplicaRedencion.tipoReden[1].checked){		
				tipoReden = frmAplicaRedencion.tipoReden[1].value;		
		}else if(frmAplicaRedencion.tipoReden[2].checked){
				tipoReden = frmAplicaRedencion.tipoReden[2].value;
			if(frmAplicaRedencion.tipoRedenS[0].checked){
				tipoRedenS=frmAplicaRedencion.tipoRedenS[0].value;
			}else if(frmAplicaRedencion.tipoRedenS[1].checked){
				tipoRedenS=frmAplicaRedencion.tipoRedenS[1].value;
			}else if(frmAplicaRedencion.tipoRedenS[2].checked){
				tipoRedenS=frmAplicaRedencion.tipoRedenS[2].value;
			}else if(frmAplicaRedencion.tipoRedenS[3].checked){
				tipoRedenS=frmAplicaRedencion.tipoRedenS[3].value;
			}
		}
	}
	*/

		
			
			
	if(tipoReden=="CON"){
		if(document.getElementById("planNuevo").value!=""){
			deshabilita();
		}else{
			window.alert("Debes escribir el nuevo plan");
			return false;	
		}
	}   
	
	if(tipoReden=="CAREG"){
		if(!document.getElementById("planNuevoCR").value!=""){			
			window.alert("Debes escribir el nuevo plan");
			return false;
		}
		if(!document.getElementById("AdendumCR").value!=""){			
			window.alert("Debes escribir el addendum");
			return false;
		}
		if(!document.getElementById("MesesCR").value!=""){			
			window.alert("Debes escribir los meses realizados");
			return false;
		}	
	}
	
	return true;
}

function validaMarcaModelo(){	
	if(document.getElementById("ptsExactos").value==""){
		window.alert("Es necesario capturar la cantidad de puntos a redimir");
		return false;
	}else{
		if(isNaN(frmMarcas.ptsExactos.value)){
			window.alert("El formato del valor de puntos no es valido");
			frmMarcas.ptsExactos.focus();
			return false;
		}
		if(tipoRedencion!="ACA"){
			if (frmMarcas.marca.value=="0" || frmMarcas.marca.value==""){
				window.alert("Es necesario seleccionar una marca");
	  			return false;
			}else if (frmMarcas.modelo.value==""){
				window.alert("Es necesario seleccionar un modelo");
	  			return false;
			}
		}
	}
	return true;
}

function getProductos(){
	var iva;
	
	if (frmMarcas.porcentajeIVA(0).checked == true){
		iva = frmMarcas.porcentajeIVA(0).value;
	}else if (frmMarcas.porcentajeIVA(1).checked == true){
		iva = frmMarcas.porcentajeIVA(1).value;
	}else{
		iva ="16";
	}
	var puntosTotDisp=document.parentWindow.parent.document.getElementById("ptsTotales").value;
	var region=document.parentWindow.parent.document.getElementById("nRegion").value;
	var estatusTel=document.parentWindow.parent.document.getElementById("estatusTel").value;
	var motivo=document.parentWindow.parent.document.getElementById("motivo").value;
	var fecSuspension=document.parentWindow.parent.document.getElementById("fecSuspension").value;
	var planM2K=document.parentWindow.parent.document.getElementById("planM2K").value;
	var formaRedencion=document.parentWindow.parent.document.getElementById("formaRedencion").value;			
	var sPromFacturaAV=document.parentWindow.parent.document.getElementById("sPromFacturaAV").value;			
	var tipoRedencion =document.getElementById("tipoRedencion").value;
	var planNuevo=document.parentWindow.parent.document.getElementById("planNuevo").value;
	var nAdendumNuevo=document.getElementById("nAdendumNuevo").value;			
	var tecnologia=document.parentWindow.parent.document.getElementById("tecnologia").value;			
	var cuenta=document.parentWindow.parent.document.getElementById("cuenta").value;
	var telefono=document.getElementById("telefono").value;
	var secuencia=document.parentWindow.parent.document.getElementById("secuencia").value;
	var bonoEquipo=document.parentWindow.parent.document.getElementById("bonoEquipo").value;
	var fecAddM2K=document.parentWindow.parent.document.getElementById("fecAddM2K").value;
	var adendum=document.parentWindow.parent.document.getElementById("adendum").value;
	var sPromFacturaAV=document.parentWindow.parent.document.getElementById("sPromFacturaAV").value;			
	var planNvoCR=document.parentWindow.parent.document.getElementById("planNuevoCR").value;
	var adendumCR=document.parentWindow.parent.document.getElementById("AdendumCR").value;
	var mesesCR=document.parentWindow.parent.document.getElementById("MesesCR").value;
	
	var promociones=window.showModalDialog("./consultaProductos.do?nPtosDisp="+puntosTotDisp+
	"&marca="+document.getElementById("marca").value+"&modelo="+document.getElementById("modelo").value+
	"&ptsExactos="+document.getElementById("ptsExactos").value+"&region="+region+"&estatusTel="+estatusTel+
	"&motivo="+motivo+"&fecSuspension="+fecSuspension+"&planM2K="+planM2K+"&sPromFactura="+sPromFacturaAV+
	"&tipoReden="+tipoRedencion+"&planNuevo="+planNuevo+"&tecnologia="+tecnologia+"&cuenta="+cuenta+
	"&telefono="+telefono+
	"&secuencia="+secuencia+"&gpoPromo="+document.getElementById("nGrupo").value+
	"&bonoEquipo="+	bonoEquipo+"&fecAddM2K="+fecAddM2K+"&adendum="+adendum+
	"&planNvoCareg="+planNvoCR+"&adendumCR="+adendumCR+"&mesesCR="+mesesCR+
	"&nGrupo="+document.getElementById("nGrupo").value+"&porcentajeIva="+iva+
	"&tipoconsulta="+document.getElementById("tipoconsulta").value+"&formaRedencion="+formaRedencion+
	"&nAdendumNuevo="+nAdendumNuevo
	,"","dialogHeight: 350px; dialogWidth: 775px; center: Yes; help: No; resizable: No; status: No;");	
			
	if( promociones != null && promociones != "undefined" && promociones != "") {
		var resul_array = promociones.split(",");
		document.getElementById("idProd").value=resul_array[0];
		document.getElementById("descripcion").value=resul_array[1];
		document.getElementById("marcaR").value=resul_array[2];
		document.getElementById("modeloR").value=resul_array[3];
		document.getElementById("valorptos").value=resul_array[4]; 
		document.getElementById("precio").value=resul_array[5];
		document.getElementById("precioIVA").value=resul_array[7];
		document.getElementById("descuento").value=resul_array[11];
		document.getElementById("ptsExactos").value=resul_array[4];
		document.getElementById("tipoPromocion").value=resul_array[6];
		document.getElementById("sobrantes").value=resul_array[10];
		document.getElementById("bonoRoext").value=resul_array[12];
		document.getElementById("bonoAltoValor").value=resul_array[13];
		document.getElementById("descuento").value=resul_array[11];
		document.getElementById("aplicaPaqSms").value=resul_array[14];
		document.getElementById("aplicaPromoGap").value=resul_array[15];
		document.getElementById("idPromocionGap").value=resul_array[16];
		document.getElementById("idPromocionCA").value=resul_array[17];
		document.getElementById("versionPromoGap").value=resul_array[18];
		document.getElementById("bonosGap").value=resul_array[19];
		document.getElementById("bonoDescuento").value=resul_array[20];	
		document.getElementById("productoM2K").value=resul_array[21];
		document.getElementById("nombrePromoGap").value=resul_array[22];
		document.getElementById("aplicaEP").value=resul_array[23];
	}
}	
	
function limpia(){
	document.getElementById("modelo").value="";
	document.getElementById("idProd").value="";
	document.getElementById("marcaR").value="";
	document.getElementById("modeloR").value="";
	document.getElementById("valorptos").value="";
	document.getElementById("descripcion").value="";
	document.getElementById("precio").value="";
	document.getElementById("esnimeiT").value="";
	document.getElementById("esnimeiP").value="";
	document.getElementById("descuento").value="";
	document.getElementById("precioIVA").value="";	
	document.getElementById("tipoPromocion").value="";
}

function aplicaRedencion(){
	var planM2K=document.parentWindow.parent.document.getElementById("planM2K").value;
	var planNuevo=document.parentWindow.parent.document.getElementById("planNuevo").value;
	var modelo=document.getElementById("modeloR").value;
	var marca=document.getElementById("marcaR").value;
	var descripcion=document.getElementById("descripcion").value;
	var tipoPromo=document.getElementById("tipoPromocion").value;
	var bonoRoext=document.getElementById("bonoRoext").value;
	var sobrantes=document.getElementById("sobrantes").value;
	var bonoAltoValor=document.getElementById("bonoAltoValor").value;
	var tipoRed=document.getElementById("tipoRedencion").value;		
	var cuenta=document.getElementById("cuenta").value;
	var secuencia=document.getElementById("secuencia").value;
	var valorPtos=document.getElementById("valorptos").value;
	var telefono=document.getElementById("telefono").value;
	var idProd=document.getElementById("idProd").value;
	var difPesos=document.getElementById("precioIVA").value;
	var region=document.getElementById("nRegion").value;
	var coment=document.getElementById("coment").value;		
	var fecAddM2K=document.getElementById("fecAddM2K").value;
	var precio=document.getElementById("precio").value;
	var precioIva=document.getElementById("precioIVA").value;
	var descuento=document.getElementById("descuento").value;
	var adendum=document.parentWindow.parent.document.getElementById("adendum").value;		
	var ptosDispTmp=document.parentWindow.parent.document.getElementById("ptsTotales").value;
	var formaRed=document.parentWindow.parent.document.getElementById("formaRedencion").value;		
	var esnimeiT=document.getElementById("esnimeiT").value;
	var esnimeiP=document.getElementById("esnimeiP").value;
	var iccid=document.getElementById("iccid").value;		
	var aplicaSms=document.getElementById("aplicaPaqSms").value;
	var nAdendumNuevo=document.getElementById("nAdendumNuevo").value;
	
	var idBeneficio = document.getElementById("idBeneficio").value;
	var idGpoBeneficio = document.getElementById("idGpoBeneficio").value;
	var idMotivo = document.getElementById("idMotivo").value;			

	var aplicaPromoGap=document.getElementById("aplicaPromoGap").value;
	var nombrePromoGap=document.getElementById("nombrePromoGap").value;
	var idPromocionGap=document.getElementById("idPromocionGap").value;
	var idPromocionCA=document.getElementById("idPromocionCA").value;
	var versionPromoGap=document.getElementById("versionPromoGap").value;
	var bonosGap=document.getElementById("bonosGap").value;
	var bonoDescuento=document.getElementById("bonoDescuento").value;
	var productoM2K=document.getElementById("productoM2K").value;
	var dirIP=	document.getElementById("dirIP").value;	
	var aplicaEP=document.getElementById("aplicaEP").value;
			
var alertas = "";
alertas += "planM2K: "+ planM2K+"\n";
alertas += "planNuevo: "+ planNuevo+"\n";
alertas += "modelo: "+ modelo+"\n";
alertas += "marca: "+ marca+"\n";
alertas += "descripcion: "+ descripcion+"\n";
alertas += "tipoPromo: "+ tipoPromo+"\n";
alertas += "bonoRoext: "+ bonoRoext+"\n";
alertas += "sobrantes: "+ sobrantes+"\n";
alertas += "bonoAltoValor: "+ bonoAltoValor+"\n";
alertas += "tipoRed: "+ tipoRed+"\n";
alertas += "cuenta: "+ cuenta+"\n";
alertas += "secuencia: "+ secuencia+"\n";
alertas += "valorPtos: "+ valorPtos+"\n";
alertas += "telefono: "+ telefono+"\n";
alertas += "idProd: "+ idProd+"\n";
alertas += "difPesos: "+ difPesos+"\n";
alertas += "region: "+ region+"\n";
alertas += "coment: "+ coment+"\n";
alertas += "fecAddM2K: "+ fecAddM2K+"\n";
alertas += "precio: "+ precio+"\n";
alertas += "precioIva: "+ precioIva+"\n";
alertas += "descuento: "+ descuento+"\n";
alertas += "adendum: "+ adendum+"\n";
alertas += "ptosDispTmp: "+ ptosDispTmp+"\n";
alertas += "formaRed: "+ formaRed+"\n";
alertas += "esnimeiT: "+ esnimeiT+"\n";
alertas += "esnimeiP: "+ esnimeiP+"\n";
alertas += "iccid: "+ iccid+"\n";
alertas += "aplicaSms: "+ aplicaSms+"\n";
alertas += "nAdendumNuevo: "+ nAdendumNuevo+"\n";
alertas += "idBeneficio: "+ idBeneficio+"\n";
alertas += "idGpoBeneficio: "+ idGpoBeneficio+"\n";
alertas += "idMotivo: "+ idMotivo+"\n";
alertas += "aplicaPromoGap: "+ aplicaPromoGap+"\n";
alertas += "nombrePromoGap: "+ nombrePromoGap+"\n";
alertas += "idPromocionGap: "+ idPromocionGap+"\n";
alertas += "idPromocionCA: "+ idPromocionCA+"\n";
alertas += "versionPromoGap: "+ versionPromoGap+"\n";
alertas += "bonosGap: "+ bonosGap+"\n";
alertas += "bonoDescuento: "+ bonoDescuento+"\n";
alertas += "productoM2K: "+ productoM2K+"\n";
alertas += "dirIP: "+ dirIP+"\n";
alertas += "aplicaEP: "+ aplicaEP+"\n";
//alert("NORMAL: "+ alertas);

	frmMarcas.action="./realizaRedencion.do?tipoRed="+tipoRed+"&cuenta="+cuenta+"&secuencia="+secuencia+
				"&valorptos="+valorPtos+"&telefono="+telefono+"&idProd="+idProd+"&difPesos="+difPesos+"&region="+region+
				"&comentario="+coment+"&fecAddM2K="+fecAddM2K+"&tipoPromo="+tipoPromo+"&precio="+precio+
				"&precioIva="+precioIva+"&sobrantes="+sobrantes+"&bonoRoext="+bonoRoext+"&marcaR="+marca+
				"&modeloR="+modelo+"&bonoAltoValor="+bonoAltoValor+"&descuento="+descuento+"&planM2K="+planM2K+
				"&planNuevo="+planNuevo+"&addNvo="+nAdendumNuevo+"&adendum="+adendum+"&ptosDispTmp="+ptosDispTmp+
				"&formaRed="+formaRed+"&esnimeiT="+esnimeiT+"&esnimeiP="+esnimeiP+"&iccid="+iccid+"&aplicaPaqSms="+aplicaSms+
				"&descripcion="+descripcion+"&idBeneficio="+idBeneficio+"&idMotivo="+idMotivo+"&idGpoBeneficio="+idGpoBeneficio+
				"&aplicaPromoGap="+aplicaPromoGap+"&idPromocionGap="+idPromocionGap+"&idPromocionCA="+idPromocionCA+
				"&versionPromoGap="+versionPromoGap+"&bonosGap="+bonosGap+"&dirIP="+dirIP+"&bonoDescuento="+bonoDescuento+
				"&productoM2K="+productoM2K+"&nombrePromoGap="+nombrePromoGap+"&aplicaEP="+aplicaEP;
	frmMarcas.submit();
}
	
function validaRedencion(){
	var tipoReden=document.getElementById("tipoRedencion").value;
	var comentario=document.getElementById("coment").value;
	
		if(tipoReden!="ACA") {
			// Validaciones 
		  	var esnimei1 = document.getElementById("esnimeiT").value;
		  	var esnimei2 = document.getElementById("esnimeiP").value;		  	
		  	
		  	if (document.getElementById("marcaR").value=="" || document.getElementById("modeloR").value=="" || document.getElementById("precio").value=="" || 
		  	document.getElementById("valorptos").value==""	|| document.getElementById("marca").value== "0" || document.getElementById("modelo").value=="" || document.getElementById("idProd").value==""){
				window.alert("Los Campos no pueden ir vacios");
				return false ;
			}			
			if(tipoReden=="T3G") {
	           if (esnimei1!= null && esnimei1!=""){
	            if (esnimei1.length < 15 ){
	                window.alert("El número de IMEI capturado para la tarjeta no puede ser menor a 15 dígitos.");
	                return false ;
	             }
	           }else{
	           window.alert("Por favor capture el IMEI de la tarjeta inalámbrica.");
	            return false ;                
	          }
           }else{
        		if (esnimei1!= null && esnimei1!=""){
            		if (esnimei1.length < 11 ){
                		window.alert("El número de IMEI capturado para el equipo tarifario no puede ser menor a 15 dígitos.");
                		return false ;
            		}
        		}else{
        			window.alert("Por favor capture el IMEI de los equipos: tarifario y promocional en caso de que aplique.");
        			return false ;
    			}
    		if (esnimei2!= null && esnimei2!=""){
        		if (esnimei2.length < 11 ){
            		window.alert("El número de IMEI capturado para el equipo en promoción no puede ser menor a 15 dígitos.");
            		return false ;					
        		}
    		}    
    	}
		} else {
			var noIccid= document.getElementById("iccid").value;
			if (noIccid.length < 18 ){
				window.alert("El número de ICCID capturado no puede ser menor a 18 dígitos.");
				return false ;
			}
			if (document.getElementById("precioIVA").value=="" || 
			document.getElementById("valorptos").value=="" || document.getElementById("idProd").value=="") {
				window.alert("Los Campos no pueden ir vacios");
				return false ;
			}
		}
		
		if (comentario.length <= 0 ){
				window.alert("Debe ingresar un comentario.");
				return false ;
		}
		
	 	return true;
}

function validaSeries(){
	if(nTipoRed==2){
		if (iccidNvo!= null && iccidNvo!=""){
			if (iccidNvo.length < 18 ){
				window.alert("El número de ICCID capturado no puede ser menor a 18 dígitos.");
				return false ;
			}
		}else{
		    window.alert("Por favor capture el número de ICCID para continuar con la actualización.");
			return false ;
		}
	}else{
		if (esnImeiTNvo!= null && esnImeiTNvo!=""){
			if (tecnologia=="GSM"){
				if (esnImeiTNvo.length < 15 ){
					window.alert("El número de IMEI capturado para el equipo Tarifario no puede ser menor a 15 dígitos.");
					return false ;
				}
			}else if(tecnologia=="TDMA"){
				if (esnImeiTNvo.length < 11 ){
					window.alert("El número de ESN capturado para el equipo Tarifario no puede ser menor a 11 dígitos.");
				  return false ;
				}
			}
		}else{
			window.alert("Por favor capture el IMEI de los equipos: tarifario y promocional en caso de que aplique.");
			return false ;
		}
		
		if(nTipoRed!=4){
			if (esnImeiPNvo!= null && esnImeiPNvo.length > 0 && esnImeiPNvo!="0"){
				if (tecnologia=="GSM"){
					if (esnImeiPNvo.length < 15 ){
						window.alert("El número de IMEI capturado para el equipo en Promoción  no puede ser menor a 15 dígitos.");
						return false ;
				}
				}else if(tecnologia=="TDMA"){
					if (esnImeiPNvo.length < 11 ){
						window.alert("El número de ESN capturado para el equipo en Promoción  no puede ser menor a 11 dígitos.");
					  return false ;
					}
				}
			}
		}
	}
	return true;
}