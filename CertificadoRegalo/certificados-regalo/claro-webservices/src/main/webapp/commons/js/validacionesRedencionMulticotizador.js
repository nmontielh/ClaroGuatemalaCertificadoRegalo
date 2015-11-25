/*
Multicotizador II JAPA 28/03/2012  Folio 84048 y ZZZZZZ : 
Se elimina el div que muestra campos para TarjetaRedencionesCon y TarjetaCareg
*/
//Variable que determina el número de productos cotizador
var numRegistros	= 1;

function fnBusqueda(pobEvento) {
	var plan		= document.getElementById("buscaPlan")
	var lstLetras	= plan.value.toUpperCase();
	
	if(lstLetras == "") {
		document.getElementById("ctlPlanes1").selectedIndex = 0;
		return;
	}
	if(plan.value.length > 1) {
		var lnuSelected		= binarySearch(document.getElementById("ctlPlanes1"), lstLetras);
		document.getElementById("ctlPlanes1").selectedIndex = lnuSelected;
	}
		
	//Validacion CAREG para busqueda de plan solo con meses y adendum
	if(!validaCAREG()) {
		document.getElementById("ctlPlanes1").selectedIndex = 0;
		plan.value = "";
		return false;
	}
	noEnter(pobEvento);
}

function binarySearch(items, value){
	var startIndex = 0,
	stopIndex = items.options.length - 1;
	var len		= value.length;

	while(startIndex <= stopIndex){
		middle = Math.round((stopIndex + startIndex)/2);
		//alert(items.options[middle].value.substring(0,len) +" == "+ value);
		//alert(value +" < "+ items.options[middle].value.substring(0,len) +" = "+ (value < items.options[middle].value.substring(0,len)));
		if ( items.options[middle].value.substring(0,len) == value ) {
			//&& (value >= items.options[middle-1].value.substring(0,len)) ) {
			return middle;
		}
		
		//Selecciona la primera mitad
		else if (value < items.options[middle].value.substring(0,len)) {
			stopIndex = middle - 1;
		} else {
			startIndex = middle + 1;
		}
	}
	return 0;
}

function noEnter(event) {
	if (window.event) {	// funciona para I.Explorer
		if (window.event.keyCode == 13)	{	
		 	consultaMarcas();
			return false;
		} else {
			return true;
		}
	} else {
		if (event) { // funciona para Netscape
			if (event.which == 13) {		
				consultaMarcas();
				return false;
			} else {
				return true
			}
		} else 
			return true;
	}
}//fin function noEnter(event)


function validaCAREG() {
	var tipoRedencion	= document.parentWindow.parent.document.getElementById("tipoRedencion").value;
	var adendumCR		= document.getElementById("AdendumCR").value;
	var mesesCR			= document.getElementById("MesesCR").value;
	var region			= document.parentWindow.parent.document.getElementById("nRegion").value;

	if(tipoRedencion=="CAREG") {
		if(!document.getElementById("ctlPlanes1").options[0].selected &&(adendumCR=="" || mesesCR=="") ) {
			window.alert("Para realizar una redención por CAREG es necesario capturar el addendum y el número de meses que lleva dentro de el mismo.");
			document.getElementById("buscaPlan").value = "";
			return false;
		}
		if(parseInt(mesesCR) > parseInt(adendumCR)) {
			window.alert("El No. de Meses para Prorrateo CAREG debe ser Menor que el Addendum");
			document.getElementById("MesesCR").value = "";
			document.getElementById("MesesCR").focus();
			document.getElementById("buscaPlan").value = "";
			return false;
		}
		
		var plazo = ( parseInt(document.getElementById("nMesActual").value)+parseInt(mesesCR) );
		if(region != 9 && ( plazo <= 6 )) {
			window.alert("No ha concluido el tiempo estimado en el plazo forzoso para realizar una redención. Para R1 a R8 a partir del 6to. mes.");
			document.getElementById("MesesCR").value = "";
			document.getElementById("MesesCR").focus();
			document.getElementById("buscaPlan").value = "";
			return false;
		}
	}
	return true;
}

function validaMarcaModeloMult(){
	if(document.getElementById("ptsExactos").value==""){
			window.alert("Es necesario capturar la cantidad de puntos a redimir");
			return false;
	}else{
		if(isNaN(document.getElementById("ptsExactos").value)){
			window.alert("El formato del valor de puntos no es valido");
			document.getElementById("ptsExactos").focus();
			return false;
		}
		if(document.parentWindow.parent.document.getElementById("tipoRedencion").value!="ACA"){
			if (document.getElementById("marca").value=="0" || document.getElementById("marca").value==""){
				window.alert("Es necesario seleccionar una marca");
				return false;
			}else if (document.getElementById("modelo").value==""){
				window.alert("Es necesario seleccionar un modelo");
				return false;
			}
		}
	}
	return true;
}

function marca_onchange(){
	document.getElementById("modelo").value = "";
	document.getElementById("promocionSeleccionada").innerHTML = "";
	buscaBeneficios(2);		
}

function productos(){
	if(numRegistros  >= 11) {
		window.alert("Solo se pueden comparar hasta 10 productos.");
		return false;
	} else {
		if(validaMarcaModeloMult()){
			getProductosMult();
		}
	}
}

function modelos(){
	var marca		= document.getElementById("marca").value;
	var gpoPlan		= document.parentWindow.parent.document.getElementById('gpoPromo').value;
	var valor;

	if (marca==""){
		window.alert("Debe seleccionar una marca");
	}else{
		valor = window.showModalDialog("./consultaModelos.do?sMarca="+marca+
		"&nRegion="+document.parentWindow.parent.document.getElementById("nRegion").value+
		"&nGrupo="+gpoPlan,"",
		"dialogHeight: 350px;dialogWidth: 400px; center: Yes; help: No; resizable: Yes; status: No;");	    	
		if(valor!= null && valor != "undefined" && valor != " "){
		document.getElementById("modelo").value=valor;
		}
	}
	
	document.getElementById("descuentoInbursa").checked = "";
	var planesInbursa = document.getElementById("planesInbursa").value;
	var marcasInbursa = document.getElementById("marcasInbursa").value;
	var modelosInbursa = document.getElementById("modelosInbursa").value;
	var lineasInbursa = document.getElementById("lineasInbursa").value;
	var plan = "-1";
	if(document.getElementById("buscaPlan") != null)
		plan = document.getElementById("buscaPlan").value;
	var telefono = $.trim(document.parentWindow.parent.document.getElementById("telefono").value);
	
	if(lineasInbursa.indexOf(telefono+",") >= 0 && planesInbursa.indexOf(plan+",") >= 0)
		document.getElementById("trInbursa").style.visibility = "visible";
	else
		document.getElementById("trInbursa").style.visibility = "hidden";
}


function buscaBeneficios(valor){
	if(valor==1){
		var region		= document.parentWindow.parent.document.getElementById("nRegion");
		var url			= "./consultaBeneficios.do?region=" + region.value;
		var beneficio	= window.showModalDialog(url,"","dialogHeight: 450px; " +		
						"dialogWidth: 800px; center: Yes; help: No; resizable: Yes; status: No;");
	}
	if(valor==2){
		/* Ocultar o mostrar boton de beneficios solo cuando se tengan JAPA 30/10/2012  Folio 105592 */
		document.getElementById("tdBuscaBeneficio").style.visibility	= "hidden";
		document.getElementById("tdBuscaBeneficio").style.display		= "none";
		
		var region		= document.parentWindow.parent.document.getElementById("nRegion");
		var marca		= document.getElementById("marca").value;
		var marcaTMP	= marca.split("|")[0];
		var url			= "./consultaBeneficiosMarca.do?marca=" + marcaTMP + "&region=" + region.value; 
		var beneficio	= window.showModalDialog(url,"","dialogHeight: 450px; " +		
						"dialogWidth: 800px; center: Yes; help: No; resizable: Yes; status: No;");
			
		if(beneficio != null && beneficio != "undefined" && beneficio != " " && beneficio!=""){
			var valores					= beneficio.split("|");
			var promocionSeleccionada	= document.getElementById("promocionSeleccionada");
			
			var idBeneficio				= document.getElementById("idBeneficio");
			var idGpoBeneficio			= document.getElementById("idGpoBeneficio");
			var idMotivo				= document.getElementById("idMotivo");
			var tipoMotivo				= document.getElementById("tipoMotivo");
			
			idBeneficio.value			= valores[1];
			idGpoBeneficio.value		= valores[2];
			idMotivo.value				= valores[3]; 
			tipoMotivo.value			= valores[4];

			if(valores[4]=="1"){
				document.getElementById("modelo").value = valores[0];					
				promocionSeleccionada.innerHTML = "<font style='font: bold' color='BLUE'>Se acepto el Beneficio</font>";
				/* Ocultar o mostrar boton de beneficios solo cuando se tengan JAPA 30/10/2012  Folio 105592 */
				document.getElementById("tdBuscaBeneficio").style.visibility	= "visible";
				document.getElementById("tdBuscaBeneficio").style.display		= "block";
			}else{
				document.getElementById("modelo").value = "";					
				promocionSeleccionada.innerHTML = "<font style='font: bold' color='red'>No se acept&oacute; ning&uacute;n Beneficio</font>";
				/* Ocultar o mostrar boton de beneficios solo cuando se tengan JAPA 30/10/2012  Folio 105592 */
				document.getElementById("tdBuscaBeneficio").style.visibility	= "visible";
				document.getElementById("tdBuscaBeneficio").style.display		= "block";
			}
		}									
	}	
}


function getProductosMult(){
	var iva;
	iva ="16";

	var puntosTotDisp	= document.parentWindow.parent.document.getElementById("ptsTotales").value;
	var region			= document.parentWindow.parent.document.getElementById("nRegion").value;
	var estatusTel		= document.parentWindow.parent.document.getElementById("estatusTel").value;
	var motivo			= document.parentWindow.parent.document.getElementById("motivo").value;
	var fecSuspension	= document.parentWindow.parent.document.getElementById("fecSuspension").value;
	var planM2K			= document.parentWindow.parent.document.getElementById("planM2K").value;
	var formaRedencion	= document.parentWindow.parent.document.getElementById("formaRedencion").value;			
	var sPromFacturaAV	= document.parentWindow.parent.document.getElementById("sPromFacturaAV").value;			
	var tipoRedencion	= document.parentWindow.parent.document.getElementById("tipoRedencion").value;
	var planNuevo		= document.parentWindow.parent.document.getElementById("planNuevo").value;
	var nAdendumNuevo	= document.getElementById("nAdendumNuevo").value;			
	var tecnologia		= document.parentWindow.parent.document.getElementById("tecnologia").value;			
	var cuenta			= document.parentWindow.parent.document.getElementById("cuenta").value;
	var telefono		= document.parentWindow.parent.document.getElementById("telefono").value;
	var secuencia		= document.parentWindow.parent.document.getElementById("secuencia").value;
	var bonoEquipo		= document.parentWindow.parent.document.getElementById("bonoEquipo").value;
	var fecAddM2K		= document.parentWindow.parent.document.getElementById("fecAddM2K").value;
	var adendum			= document.parentWindow.parent.document.getElementById("adendum").value;
	var sPromFacturaAV	= document.parentWindow.parent.document.getElementById("sPromFacturaAV").value;			
	var planNvoCR		= document.parentWindow.parent.document.getElementById("planNuevoCR").value;
	var adendumCR		= document.parentWindow.parent.document.getElementById("AdendumCR").value;
	var mesesCR			= document.parentWindow.parent.document.getElementById("MesesCR").value;
	var adendumNvo		= document.parentWindow.parent.document.getElementById("adendumNvo").value;	
	var marca			= document.getElementById("marca").value;
	marca				= marca.split("|")[0];
	var modelo			= document.getElementById("modelo").value;
	var ptsExactos		= document.getElementById("ptsExactos").value;
	//var nGrupo		= document.getElementById("nGrupo").value;
	var nGrupo			= document.parentWindow.parent.document.getElementById("gpoPromo").value;
	var tipoconsulta	= document.getElementById("tipoconsulta").value;
	var descuentoInbursa= document.getElementById("descuentoInbursa").checked;

	
	/*
	alert("puntosTotDisp: " + puntosTotDisp + ", region: " + region + ", estatusTel: " + estatusTel + "\n" + 
			"motivo: " + motivo + ", fecSuspension: " + fecSuspension + ", planM2K: " + planM2K + "\n" + 
			"formaRedencion: " + formaRedencion + ", sPromFacturaAV: " + sPromFacturaAV + ", tipoRedencion: " + tipoRedencion + "\n" +
			"planNuevo: " + planNuevo + ", nAdendumNuevo: " + nAdendumNuevo + ", tecnologia: " + tecnologia + "\n" + 
			"cuenta: " + cuenta + ", telefono: " + telefono + ", secuencia: " + secuencia + ", bonoEquipo: " +  bonoEquipo + "\n" + 
			"fecAddM2K: " + fecAddM2K + ", adendum: " + adendum + ", sPromFacturaAV: " + sPromFacturaAV + "\n" + 
			"planNvoCR: " + planNvoCR + ", adendumCR: " + adendumCR + ", mesesCR: " + mesesCR + ", adendumNvo: " + adendumNvo + "\n" + 
			"marca: " + marca + ", modelo: " + modelo + ", ptsExactos: " + ptsExactos + ", nGrupo: " + nGrupo + ", tipoconsulta: " + tipoconsulta);
	*/
	

	var promociones=window.showModalDialog("./consultaProductos.do?nPtosDisp="+puntosTotDisp+
	"&marca="+marca+"&modelo="+modelo+
	"&ptsExactos="+ptsExactos+"&region="+region+"&estatusTel="+estatusTel+
	"&motivo="+motivo+"&fecSuspension="+fecSuspension+"&planM2K="+planM2K+"&sPromFactura="+sPromFacturaAV+
	"&tipoReden="+tipoRedencion+"&planNuevo="+planNuevo+"&tecnologia="+tecnologia+"&cuenta="+cuenta+
	"&telefono="+telefono+
	"&secuencia="+secuencia+"&gpoPromo="+nGrupo.value+
	"&bonoEquipo="+	bonoEquipo+"&fecAddM2K="+fecAddM2K+"&adendum="+adendum+
	"&planNvoCareg="+planNvoCR+"&adendumCR="+adendumCR+"&mesesCR="+mesesCR+
	"&nGrupo="+nGrupo+"&porcentajeIva="+iva+
	"&tipoconsulta="+tipoconsulta+"&formaRedencion="+formaRedencion+
	"&nAdendumNuevo="+nAdendumNuevo+"&descuentoInbursa="+descuentoInbursa
	,"","dialogHeight: 350px; dialogWidth: 775px; center: Yes; help: No; resizable: No; status: No;");	
			
	if( promociones != null && promociones != "undefined" && promociones != "") {
		creaRangoHtml(numRegistros, tipoRedencion, planNuevo, adendumNvo);
		cargaInfoPromo(numRegistros, promociones);
		numRegistros++;
	}
}


function creaRangoHtml(numPaso, tipoRedencion, planNuevo, adendumNvo){
	var tr			= document.createElement("tr");
	tr.id			= "TR_rango_"+ numPaso;

	//Radio
	var td			= document.createElement("td");
	td.innerHTML	= "<input align='middle' type='radio' id='selecProd"+numPaso+"' name='selecProd"+numPaso+"' value='selecProd"+numPaso+"' onClick='selecProducto("+numPaso+");'>";
	tr.appendChild(td); 

	//Plan
	var td			= document.createElement("td");
	td.innerHTML	= "<input type='text' id='idPlan"+ numPaso +"' name='idPlan"+ numPaso +"' value='"+ planNuevo +"' size='5' class='textonegroBlod' style='border:0' align='middle' readonly>";
	tr.appendChild(td); 

	//Material
	var td			= document.createElement("td");
	td.innerHTML	= "<input type='text' id='idProd"+ numPaso +"' name='idProd"+ numPaso +"' value='' size='10' class='textonegroBlod' style='border:0' align='middle' readonly>";
	tr.appendChild(td); 
	
	//Descripcion
	var td			= document.createElement("td");
	td.innerHTML	= "<input type='text' id='descripcion"+ numPaso +"' name='descripcion"+ numPaso +"' value='' size='30' class='textonegroBlod' style='border:0' align='middle' readonly>";
	tr.appendChild(td); 
		
	if(tipoRedencion=='ACA') {
		//Valor Puntos
		var td			= document.createElement("td");
		td.innerHTML	= "<input class='textonegroBlod' type='text' name='valorptos"+ numPaso +"' id='valorptos"+ numPaso +"' size='8' style='border:0' align='middle' readonly>";
		tr.appendChild(td); 

		//Precio IVA
		var td			= document.createElement("td");
		td.className	= "textonegroBlod"; 
		td.innerHTML	= "$<input class='textonegroBlod' type='text' name='precioIVA"+ numPaso +"' id='precioIVA"+ numPaso +"' size='6' style='border:0' align='middle' readonly>";
		tr.appendChild(td); 

		//ICCID
		var td			= document.createElement("td");
		td.innerHTML	= "<input class='textonegroBlod' type='text' name='iccid"+ numPaso +"' id='iccid"+ numPaso +"' size='20' style='border:0; color: blue; font:bold;' maxlength='18'>";
		tr.appendChild(td); 

	} else if(tipoRedencion=='T3G') {
		//Marca
		var td			= document.createElement("td");
		td.innerHTML	= "<input class='textonegroBlod' type='text' name='marcaR"+ numPaso +"' id='marcaR"+ numPaso +"' size='10' style='border:0' align='middle' readonly>";
		tr.appendChild(td); 

		//Modelo
		var td			= document.createElement("td");
		td.innerHTML	= "<input class='textonegroBlod' type='text' name='modeloR"+ numPaso +"' id='modeloR"+ numPaso +"' size='10' style='border:0' align='middle' readonly>";
		tr.appendChild(td); 

		//EsnimeiT y //EsnimeiP
		var td			= document.createElement("td");
		td.width		= "10px";
		td.innerHTML	= "<input class='textonegroBlod' type='text' name='esnimeiT"+ numPaso +"' id='esnimeiT"+ numPaso +"' size='16' style='border:0; color: blue; font:bold;' maxlength='15'>"+
						  "<input class='textonegroBlod' type='hidden'	name='esnimeiP"+ numPaso +"' id='esnimeiP"+ numPaso +"' size='16' style='border:0; color: blue; font:bold;' maxlength='15'></td>";
		tr.appendChild(td); 

		//Valor Puntos
		var td			= document.createElement("td");
		td.innerHTML	= "<input class='textonegroBlod' type='text' name='valorptos"+ numPaso +"' id='valorptos"+ numPaso +"' size='8' style='border:0' align='middle' readonly>";
		tr.appendChild(td); 

		//Precio
		var td			= document.createElement("td");
		td.className	= "textonegroBlod"; 
		td.innerHTML	= "$<input class='textonegroBlod' type='text' name='precio"+ numPaso +"' id='precio"+ numPaso +"' size='6' style='border:0' align='middle' readonly>";
		tr.appendChild(td); 
		
		//Precio IVA
		var td			= document.createElement("td");
		td.className	= "textonegroBlod"; 
		td.innerHTML	= "$<input class='textonegroBlod' type='text' name='precioIVA"+ numPaso +"' id='precioIVA"+ numPaso +"' size='6' style='border:0' align='middle' readonly>";
		tr.appendChild(td);
		
	} else {
		//Marca
		var td			= document.createElement("td");
		td.innerHTML	= "<input class='textonegroBlod' type='text' name='marcaR"+ numPaso +"' id='marcaR"+ numPaso +"' size='10' style='border:0' align='middle' readonly>";
		tr.appendChild(td); 

		//Modelo
		var td			= document.createElement("td");
		td.innerHTML	= "<input class='textonegroBlod' type='text' name='modeloR"+ numPaso +"' id='modeloR"+ numPaso +"' size='10' style='border:0' align='middle' readonly>";
		tr.appendChild(td); 

		//EsnimeiT y EsnimeiP
		var td			= document.createElement("td");
		td.width		= "10px";
		td.className	= "textonegroBlod"; 
		td.innerHTML	= "Eq.Tarifario&nbsp;&nbsp;&nbsp;&nbsp;: <input class='textonegroBlod' type='text'	name='esnimeiT"+ numPaso +"' id='esnimeiT"+ numPaso +"' size='16'	style='border:0; color: blue; font:bold;' maxlength='15'><br>"+
						  "Eq.Promoción: <input class='textonegroBlod' type='text'	name='esnimeiP"+ numPaso +"' id='esnimeiP"+ numPaso +"' size='16'	style='border:0; color: blue; font:bold;' maxlength='15'>";
		tr.appendChild(td); 

		//Valor Puntos
		var td			= document.createElement("td");
		td.innerHTML	= "<input class='textonegroBlod' type='text' name='valorptos"+ numPaso +"' id='valorptos"+ numPaso +"' size='8' style='border:0' align='middle' readonly>";
		tr.appendChild(td); 

		//Precio
		var td			= document.createElement("td");
		td.className	= "textonegroBlod"; 
		td.innerHTML	= "$<input class='textonegroBlod' type='text' name='precio"+ numPaso +"' id='precio"+ numPaso +"' size='8' style='border:0' align='middle' readonly>";
		tr.appendChild(td);
		
		//Descuento
		var td			= document.createElement("td");
		td.className	= "textonegroBlod"; 
		td.innerHTML	= "$<input class='textonegroBlod' type='text' name='descuentoTotal"+ numPaso +"' id='descuentoTotal"+ numPaso +"' size='8' style='border:0' align='middle' readonly>"+
						  "<input type='hidden' name='descuento"+ numPaso +"' id='descuento"+ numPaso +"'>";
						  
		tr.appendChild(td);

		//Precio IVA
		var td			= document.createElement("td");
		td.className	= "textonegroBlod"; 
		td.innerHTML	= "$<input class='textonegroBlod' type='text' name='precioIVA"+ numPaso +"' id='precioIVA"+ numPaso +"' size='8' style='border:0' align='middle' readonly>";
		tr.appendChild(td); 
	}

	//Marca
	var td			= document.createElement("td");
	td.setAttribute("style","visibility: hidden; display: none");
	//"<input type='hidden' name='ptsExactos"+ numPaso +"' id='ptsExactos"+ numPaso +"'>"+
	td.innerHTML	=	"<input type='hidden' name='adendumNvo"+ numPaso +"' id='adendumNvo"+ numPaso +"' value='"+ adendumNvo +"'>"+
						"<input type='hidden' name='tipoPromocion"+ numPaso +"' id='tipoPromocion"+ numPaso +"'>"+
						"<input type='hidden' name='sobrantes"+ numPaso +"' id='sobrantes"+ numPaso +"'>"+
						"<input type='hidden' name='bonoRoext"+ numPaso +"' id='bonoRoext"+ numPaso +"'>"+
						"<input type='hidden' name='bonoAltoValor"+ numPaso +"' id='bonoAltoValor"+ numPaso +"'>"+
						"<input type='hidden' name='aplicaPaqSms"+ numPaso +"' id='aplicaPaqSms"+ numPaso +"'>"+
						"<input type='hidden' name='aplicaPromoGap"+ numPaso +"' id='aplicaPromoGap"+ numPaso +"'>"+
						"<input type='hidden' name='idPromocionGap"+ numPaso +"' id='idPromocionGap"+ numPaso +"'>"+
						"<input type='hidden' name='idPromocionCA"+ numPaso +"' id='idPromocionCA"+ numPaso +"'>"+
						"<input type='hidden' name='versionPromoGap"+ numPaso +"' id='versionPromoGap"+ numPaso +"'>"+
						"<input type='hidden' name='bonosGap"+ numPaso +"' id='bonosGap"+ numPaso +"'>"+
						"<input type='hidden' name='bonoDescuento"+ numPaso +"' id='bonoDescuento"+ numPaso +"'>"+
						"<input type='hidden' name='productoM2K"+ numPaso +"' id='productoM2K"+ numPaso +"'>"+
						"<input type='hidden' name='nombrePromoGap"+ numPaso +"' id='nombrePromoGap"+ numPaso +"'>"+
						"<input type='hidden' name='aplicaEP"+ numPaso +"' id='aplicaEP"+ numPaso +"'>"+
						"<input type='hidden' name='bonoInbursa"+ numPaso +"' id='bonoInbursa"+ numPaso +"'>"+
						"<input type='hidden' name='bonoMarca"+ numPaso +"' id='bonoMarca"+ numPaso +"'>"+
						"<input type='hidden' name='bonoInbursaRestante"+ numPaso +"' id='bonoInbursaRestante"+ numPaso +"'>"+
						"<input type='hidden' name='bonoMarcaRestante"+ numPaso +"' id='bonoMarcaRestante"+ numPaso +"'>";
	
	if(tipoRedencion=='ACA') {
		td.innerHTML	+= "<input type='hidden' name='marcaR"+ numPaso +"' id='marcaR"+ numPaso +"'>";
		td.innerHTML	+= "<input type='hidden' name='modeloR"+ numPaso +"' id='modeloR"+ numPaso +"'>";
		td.innerHTML	+= "<input type='hidden' name='esnimeiT"+ numPaso +"' id='esnimeiT"+ numPaso +"'>";
		td.innerHTML	+= "<input type='hidden' name='esnimeiP"+ numPaso +"' id='esnimeiP"+ numPaso +"'>";
		td.innerHTML	+= "<input type='hidden' name='precio"+ numPaso +"' id='precio"+ numPaso +"'>";
		td.innerHTML	+= "<input type='hidden' id='descuento"+ numPaso +"' name='descuento"+ numPaso +"'>";
	} else if(tipoRedencion=='T3G') {
		td.innerHTML	+= "<input type='hidden' id='descuento"+ numPaso +"' name='descuento"+ numPaso +"'>";
		td.innerHTML	+= "<input type='hidden' name='iccid"+ numPaso +"' id='iccid"+ numPaso +"'>";
	} else {
		if(tipoRedencion=='CAREG') {
			var adeCR		= document.getElementById("AdendumCR").value;
			var mesCR		= document.getElementById("MesesCR").value;

			td.innerHTML	+= "<input type='hidden' id='AdendumCR"+ numPaso +"' name='AdendumCR"+ numPaso +"' value='"+adeCR+"'>";
			td.innerHTML	+= "<input type='hidden' id='MesesCR"+ numPaso +"' name='MesesCR"+ numPaso +"' value='"+mesCR+"'>";
		}
		td.innerHTML	+= "<input type='hidden' name='iccid"+ numPaso +"' id='iccid"+ numPaso +"'>";
	}

	tr.appendChild(td); 

	var tbo			= document.createElement('tbody');
	tbo.appendChild(tr);
	
	table			= document.getElementById("RangosMultiplesTBL"); 
	table.lastChild.appendChild(tbo);
}


function cargaInfoPromo(numPaso, promociones){
	var resul_array = promociones.split(",");
	document.getElementById("idProd"+numPaso).value			= resul_array[0];
	document.getElementById("descripcion"+numPaso).value	= resul_array[1];
	document.getElementById("marcaR"+numPaso).value			= resul_array[2];
	document.getElementById("modeloR"+numPaso).value		= resul_array[3];
	document.getElementById("valorptos"+numPaso).value		= resul_array[4]; 
	document.getElementById("precio"+numPaso).value			= resul_array[5];
	document.getElementById("precioIVA"+numPaso).value		= resul_array[7];
	//document.getElementById("ptsExactos"+numPaso).value		= resul_array[4];
	document.getElementById("tipoPromocion"+numPaso).value	= resul_array[6];
	document.getElementById("sobrantes"+numPaso).value		= resul_array[10];
	document.getElementById("bonoRoext"+numPaso).value		= resul_array[12];
	document.getElementById("bonoAltoValor"+numPaso).value	= resul_array[13];
	document.getElementById("descuento"+numPaso).value		= resul_array[11];
	document.getElementById("aplicaPaqSms"+numPaso).value	= resul_array[14];
	document.getElementById("aplicaPromoGap"+numPaso).value	= resul_array[15];
	document.getElementById("idPromocionGap"+numPaso).value	= resul_array[16];
	document.getElementById("idPromocionCA"+numPaso).value	= resul_array[17];
	document.getElementById("versionPromoGap"+numPaso).value= resul_array[18];
	document.getElementById("bonosGap"+numPaso).value		= resul_array[19];
	document.getElementById("bonoDescuento"+numPaso).value	= resul_array[20];	
	document.getElementById("productoM2K"+numPaso).value	= resul_array[21];
	document.getElementById("nombrePromoGap"+numPaso).value	= resul_array[22];
	document.getElementById("aplicaEP"+numPaso).value		= resul_array[23];
	document.getElementById("bonoInbursa"+numPaso).value	= resul_array[24];
	document.getElementById("bonoMarca"+numPaso).value		= resul_array[25];
	if(document.getElementById("descuentoTotal"+numPaso) != null)
		document.getElementById("descuentoTotal"+numPaso).value	= resul_array[26];
	document.getElementById("bonoInbursaRestante"+numPaso).value	= resul_array[27];
	document.getElementById("bonoMarcaRestante"+numPaso).value		= resul_array[28];
}

function selecProducto(numPaso){
	document.getElementById("idProd").value			= document.getElementById("idProd"+numPaso).value;
	document.getElementById("descripcion").value	= document.getElementById("descripcion"+numPaso).value;
	document.getElementById("marcaR").value			= document.getElementById("marcaR"+numPaso).value;
	document.getElementById("modeloR").value		= document.getElementById("modeloR"+numPaso).value;
	document.getElementById("valorptos").value		= document.getElementById("valorptos"+numPaso).value; 
	document.getElementById("precio").value			= document.getElementById("precio"+numPaso).value;
	document.getElementById("precioIVA").value		= document.getElementById("precioIVA"+numPaso).value;
	//document.getElementById("ptsExactos").value	= document.getElementById("ptsExactos"+numPaso).value;
	document.getElementById("tipoPromocion").value	= document.getElementById("tipoPromocion"+numPaso).value;
	document.getElementById("sobrantes").value		= document.getElementById("sobrantes"+numPaso).value;
	document.getElementById("bonoRoext").value		= document.getElementById("bonoRoext"+numPaso).value;
	document.getElementById("bonoAltoValor").value	= document.getElementById("bonoAltoValor"+numPaso).value;
	document.getElementById("descuento").value		= document.getElementById("descuento"+numPaso).value;
	document.getElementById("aplicaPaqSms").value	= document.getElementById("aplicaPaqSms"+numPaso).value;
	document.getElementById("aplicaPromoGap").value	= document.getElementById("aplicaPromoGap"+numPaso).value;
	document.getElementById("idPromocionGap").value	= document.getElementById("idPromocionGap"+numPaso).value;
	document.getElementById("idPromocionCA").value	= document.getElementById("idPromocionCA"+numPaso).value;
	document.getElementById("versionPromoGap").value= document.getElementById("versionPromoGap"+numPaso).value;
	document.getElementById("bonosGap").value		= document.getElementById("bonosGap"+numPaso).value;
	document.getElementById("bonoDescuento").value	= document.getElementById("bonoDescuento"+numPaso).value;	
	document.getElementById("productoM2K").value	= document.getElementById("productoM2K"+numPaso).value;
	document.getElementById("nombrePromoGap").value	= document.getElementById("nombrePromoGap"+numPaso).value;
	document.getElementById("aplicaEP").value		= document.getElementById("aplicaEP"+numPaso).value;
	document.getElementById("esnimeiT").value		= document.getElementById("esnimeiT"+numPaso).value;
	document.getElementById("esnimeiP").value		= document.getElementById("esnimeiP"+numPaso).value;
	document.getElementById("iccid").value			= document.getElementById("iccid"+numPaso).value;
	document.getElementById("bonoInbursa").value	= document.getElementById("bonoInbursa"+numPaso).value;
	document.getElementById("bonoMarca").value		= document.getElementById("bonoMarca"+numPaso).value;
	document.getElementById("bonoInbursaRestante").value	= document.getElementById("bonoInbursaRestante"+numPaso).value;
	document.getElementById("bonoMarcaRestante").value		= document.getElementById("bonoMarcaRestante"+numPaso).value;

	var tipoRedencion	= document.parentWindow.parent.document.getElementById("tipoRedencion").value;
	if(tipoRedencion == 'CAREG') {
		document.getElementById("AdendumCR").value	= document.getElementById("AdendumCR"+numPaso).value;
		document.getElementById("MesesCR").value	= document.getElementById("MesesCR"+numPaso).value;
	}

	var planNuevo									= document.getElementById("idPlan"+numPaso).value;
	if(!validaSeleccionProducto()) {
		document.getElementById("selecProd"+numPaso).checked = false;
		return false;
	} else {
		for(i=1;i<numRegistros;i++){
			if(i!=numPaso){
				document.getElementById("selecProd"+i).checked = false;
			}
		}
	
		if(document.getElementById("buscaPlan") != null && document.getElementById("buscaPlan") != 'undefined') { 
			document.getElementById("buscaPlan").value								= planNuevo;
			fnBusqueda(document.getElementById("buscaPlan"));
			consultaMarcas();
		}

		document.parentWindow.parent.document.getElementById("planNuevo").value	= planNuevo;
		var adnvo = document.getElementById("adendumNvo"+numPaso).value;
		if(adnvo == "") adnvo = "0";
		document.getElementById("nAdendumNuevo").value							= adnvo;
	
		var aplicaredencion = document.getElementById("btnRedencion");
		if(aplicaredencion != null) {
			document.getElementById("coment").disabled			= false;
			document.getElementById("btnRedencion").disabled	= false;
		}
	}
}


/*
Agrega los registros de marcas al combo de selección
*/
function agregaResultadoCatalogo(data,objectDestino){		
	var longitud = $(data).length;

	for(var a=0; a<longitud; a++) {
		if($(data)[a].id == "tablaCatalogo"){				             
			var total = $(data)[a].rows.length;	
			for(var i=0;i<total;i++){
				var idRenglon =  $(data)[a].rows[i].id;	
				var texto = $(data)[a].rows[i].innerText;						
				var no = new Option();
					 no.value = idRenglon;
					 no.text = texto;	          
					 objectDestino[i] = no;	          											
			}
		}
		
		else if($(data)[a].id == "idmensaje"){
			document.getElementById("idmensaje").value=$(data)[a].value;
		} else if($(data)[a].id == "mensaje"){
			document.getElementById("mensaje").value=$(data)[a].value;
		} else if($(data)[a].id == "descFormaRedencion"){
			document.getElementById("descFormaRedencion").innerHTML="&nbsp;"+$(data)[a].value;
		} else if($(data)[a].id == "descTipoRedencion"){
			document.getElementById("descTipoRedencion").innerHTML="&nbsp;"+$(data)[a].value;
		} else if($(data)[a].id == "formaRedencion"){			
			document.parentWindow.parent.document.getElementById("formaRedencion").value=$(data)[a].value;	
		}
	}
}

function getDatos(){
	document.getElementById("nAdendumNuevo").value = document.parentWindow.parent.document.getElementById("adendumNvo").value;
	
	var defineIva		= document.parentWindow.parent.document.getElementById("defineIva").value;
	var porcentajeIva	= document.parentWindow.parent.document.getElementById("porcentajeIva").value;
	var tipoRedencion	= document.parentWindow.parent.document.getElementById("tipoRedencion").value;
			
	if(tipoRedencion=="CON" || tipoRedencion=="CAREG"){
		document.getElementById("addendum").style.visibility		= "visible";
		document.getElementById("addendumTxt").style.visibility		= "visible";
	}
	if(tipoRedencion!="ACA"){
		document.getElementById("TarjetaMarcas").style.visibility	= "visible";
		document.getElementById("TarjetaMarcas").style.display		= "block";
	}
	
	if(porcentajeIva=="1.16"){				
		document.getElementById("iva16").checked = true;
	}else if(porcentajeIva=="1.11"){				
		document.getElementById("iva11").checked = true;
	}else{				
		document.getElementById("iva16").checked = false;
		document.getElementById("iva11").checked = false;			
	}			
	if(defineIva=="false"){
		document.getElementById("iva16").disabled = true;
		document.getElementById("iva11").disabled = true;
	}						
	
	document.getElementById("TarjetaTipoConsulta").style.visibility		= "visible";
	document.getElementById("TarjetaTipoConsulta").style.display		= "block";
	
	document.getElementById("TarjetaPuntos").style.visibility			= "visible";
	document.getElementById("TarjetaPuntos").style.display				= "block";
	
	document.getElementById("TarjetaIva").style.visibility				= "visible";
	document.getElementById("TarjetaIva").style.display					= "block";
	
	document.getElementById("TarjetaProductos").style.visibility		= "visible";			
	document.getElementById("TarjetaProductos").style.display			= "block";

	document.getElementById("divComentarios").style.visibility			= "visible";			
	document.getElementById("divComentarios").style.display				= "block";
	
	var aplicaRedencion = document.parentWindow.parent.document.getElementById("bAplicaRedencion").value;
	
	if(tipoRedencion=='CON'){
		var divCon = document.getElementById("divCon");
		if(divCon!=null){
			
			if(aplicaRedencion=='true' || aplicaRedencion=='TRUE'){
				divCon.style.visibility="visible";
			}else{
				divCon.style.visibility="hidden";
			}
		}				
	}else if(tipoRedencion=='CAREG'){
		var divCareg = document.getElementById("divCareg");
		if(divCareg!=null){
			if(aplicaRedencion=='true' || aplicaRedencion=='TRUE'){
				divCareg.style.visibility="visible";
			}else{
				divCareg.style.visibility="hidden";
			}
		}				
	}else if(tipoRedencion=='SIN'){
		var divSin = document.getElementById("divSin");
		if(divSin!=null){
			if(aplicaRedencion=='true' || aplicaRedencion=='TRUE'){
				divSin.style.visibility="visible";
			}else{
				divSin.style.visibility="hidden";
			}
		}				
	}else if(tipoRedencion=='ACA'){
		var divAca = document.getElementById("divAca");
		if(divAca!=null){
			if(aplicaRedencion=='true' || aplicaRedencion=='TRUE'){
				divAca.style.visibility="visible";
			}else{
				divAca.style.visibility="hidden";
			}
		}
		
	}else if(tipoRedencion=='T3G'){
		var divT3G = document.getElementById("divT3G");
		if(divT3G!=null){
			if(aplicaRedencion=='true' || aplicaRedencion=='TRUE'){
				divT3G.style.visibility="visible";
			}else{
				divT3G.style.visibility="hidden";
			}
		}				
	}			
}

/* 
Función que valida que se haya ingreado la información necesaria del producto y del formulario
*/
function validaSeleccionProducto(){
	var tipoReden=document.parentWindow.parent.document.getElementById("tipoRedencion").value
	
	if(tipoReden!="ACA") {
		// Validaciones 
		var esnimei1 = document.getElementById("esnimeiT").value;
		var esnimei2 = document.getElementById("esnimeiP").value;		  	

		if (document.getElementById("marcaR").value=="" || 
			document.getElementById("modeloR").value=="" || 
			document.getElementById("precio").value=="" || 
			document.getElementById("valorptos").value==""	|| 
			//document.getElementById("marca").value== "0" || 
			//document.getElementById("modelo").value=="" || 
			document.getElementById("idProd").value==""){
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
	return true;
}


/*
function validaRedencionMult(){
	var tipoReden=document.parentWindow.parent.document.getElementById("tipoRedencion").value
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
		if (document.getElementById("precioIVA").value=="" || document.getElementById("valorptos").value=="" || document.getElementById("idProd").value=="") {
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
*/


function aplicaRedencionMult(){
			var planM2K		= document.parentWindow.parent.document.getElementById("planM2K").value;
			var planNuevo	= document.parentWindow.parent.document.getElementById("planNuevo").value;
			var modelo		= document.getElementById("modeloR").value;
			var marca		= document.getElementById("marcaR").value;
			var descripcion	= document.getElementById("descripcion").value;
			var tipoPromo	= document.getElementById("tipoPromocion").value;
			var bonoRoext	= document.getElementById("bonoRoext").value;
			var sobrantes	= document.getElementById("sobrantes").value;
			var bonoAltoValor= document.getElementById("bonoAltoValor").value;
			//var tipoRed	= document.getElementById("tipoRedencion").value;		
			var tipoRed		= document.parentWindow.parent.document.getElementById("tipoRedencion").value;
			//var cuenta	= document.getElementById("cuenta").value;
			var cuenta		= document.parentWindow.parent.document.getElementById("cuenta").value;
			//var secuencia = document.getElementById("secuencia").value;
			var secuencia	= document.parentWindow.parent.document.getElementById("secuencia").value;
			var valorPtos	= document.getElementById("valorptos").value;
			//var telefono	= document.getElementById("telefono").value;
			var telefono	= document.parentWindow.parent.document.getElementById("telefono").value;
			var idProd		= document.getElementById("idProd").value;
			var difPesos	= document.getElementById("precioIVA").value;
			//var region	= document.getElementById("nRegion").value;
			var region		= document.parentWindow.parent.document.getElementById("nRegion").value;
			var coment		= document.getElementById("coment").value;		
			//var fecAddM2K = document.getElementById("fecAddM2K").value;
			var fecAddM2K	= document.parentWindow.parent.document.getElementById("fecAddM2K").value;
			var precio		= document.getElementById("precio").value;
			var precioIva	= document.getElementById("precioIVA").value;
			var descuento	= document.getElementById("descuento").value;
			var adendum		= document.parentWindow.parent.document.getElementById("adendum").value;		
			var ptosDispTmp = document.parentWindow.parent.document.getElementById("ptsTotales").value;
			var formaRed	= document.parentWindow.parent.document.getElementById("formaRedencion").value;		
			var esnimeiT	= document.getElementById("esnimeiT").value;
			var esnimeiP	= document.getElementById("esnimeiP").value;
			var iccid		= document.getElementById("iccid").value;		
			var aplicaSms	= document.getElementById("aplicaPaqSms").value;
			var nAdendumNuevo= document.getElementById("nAdendumNuevo").value;
			var idBeneficio		= document.getElementById("idBeneficio").value;
			var idGpoBeneficio	= document.getElementById("idGpoBeneficio").value;
			var idMotivo		= document.getElementById("idMotivo").value;			
			var aplicaPromoGap	= document.getElementById("aplicaPromoGap").value;
			var nombrePromoGap	= document.getElementById("nombrePromoGap").value;
			var idPromocionGap	= document.getElementById("idPromocionGap").value;
			var idPromocionCA	= document.getElementById("idPromocionCA").value;
			var versionPromoGap	= document.getElementById("versionPromoGap").value;
			var bonosGap		= document.getElementById("bonosGap").value;
			var bonoDescuento	= document.getElementById("bonoDescuento").value;
			var productoM2K		= document.getElementById("productoM2K").value;
			var dirIP			= document.getElementById("dirIP").value;	
			var aplicaEP		= document.getElementById("aplicaEP").value;

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
//alert("MULTI: "+ alertas);

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



function realizaRedencion(){
	var aplicaRedencion = document.getElementById("btnRedencion").disabled;

	if(!aplicaRedencion) {
		var comentario=document.getElementById("coment").value;
		if (comentario.length <= 0 ){
			window.alert("Debe ingresar un comentario.");
			return false ;
		} else {
			if(confirm("Esta apunto de redimir los puntos de la línea ¿Desea continuar?")){
				if(document.getElementById("idPromocionGap").value !=0 && document.getElementById("productoM2K").value == 'SI'){
					if(confirm("La promoción  -" + document.getElementById("nombrePromoGap").value + "-  se aplicará a la línea."+ "\n"+
					"Favor de Verificar en Mobile 2000")){
						aplicaRedencionMult();									
					}
				}else{
					aplicaRedencionMult();
					var bonoInbursaRestante = document.getElementById("bonoInbursaRestante").value;
					var bonoMarcaRestante = document.getElementById("bonoMarcaRestante").value;

					for(var i=0;i<1500000;i++);
					
					if((bonoInbursaRestante > 0 || bonoMarcaRestante > 0) && 
							confirm("La línea cuenta con un certificado de descuento que aplica para Amigo Kit o Accesorios.\nSelecciona OK para imprimirlo ahora o puedes hacerlo desde el Módulo \"Imprimir\".\nSólo será posible imprimir el certificado de descuento el día en que se realice la renovación."))
					{
						window.showModalDialog(
								   "./impresionBonoInbursa.do?telefono="+$.trim(document.parentWindow.parent.document.getElementById("telefono").value)+"&cuenta="+$.trim(document.parentWindow.parent.document.getElementById("cuenta").value)+"&individual=1",
								   "","dialogHeight: 750px;dialogWidth: 830px; center: Yes; help: No; resizable: NO; status: No;");
					}
				}
			}
		}
	} else {
		alert("Debe eligir un Producto para redimir.");
		return false;
	}
}


function act_cargando(){
	//alert("en act_cargando");
	document.getElementById("divLoaded").style.visibility="visible";
	document.getElementById("divLoaded").style.display="block";
	//alert("termina act_cargando");
}

function des_cargando(){
	//alert("en des_cargando");
	document.getElementById("divLoaded").style.visibility="hidden";
	document.getElementById("divLoaded").style.display="none";
	//alert("termina des_cargando");
}