$().ajaxSend(function(r,s){
	$('input:text').each(function(){
		var val = jQuery.trim(this.value);
		this.value = escape(this.value.toUpperCase());	
	});
	$("input:button").each(
		function(){
			this.disabled=true;
		}
	);
	$("#divLoading").show();
});

$().ajaxStop(function(r,s){
	$("input:button").each(
		function(){
			this.disabled=false;
		}
	);
	$("#divLoading").fadeOut("fast");
});
$("#msg").ajaxError(function(event, request, settings){
   $(this).append("<li>Error en la pagina " + settings.url + "</li>");
 });
	
	function mensaje(informacion){        		      
		$.prompt(informacion, {prefix:'cleanblue', buttons:{Aceptar:true}});
		return false;
	}
	
	function confirma(mensaje){
		$.prompt(mensaje,{prefix:'cleanblue', buttons: { Si: true, No: false }, callback: regresa});
	}
	
	function regresa(v,m,f){
		if(v=="undefined"){
			$("#confirma").val("")
		}else{
			if(v){
				$("#confirma").val("Confirmado");
			}
		}
	}
	
	function validaConsulta(){
		var campo=$("#numEmpleado").val();
		if(campo!=""){
			return validaCampo(campo);
		}else if(campo==""){
			return mensaje("Debe Capturar un N&uacute;mero de Empleado ");
		}else{
			return true;
		}
	}
	
	function validaCampo(campo){
		var user = /[(\*\(\)\[\]\+\,\/\?\:\;\'\"\`\~\\#\$\%\^\&\<\>)+]/;
        if(campo.match(user)){
		  return mensaje("No se permiten caracteres especiales");
        }else{
          return true;
        }
	}
	
	function validaCaractEspeciales(valor){
		//Valida caracteres especiales
		var user = /[(\*\(\)\[\]\+\,\/\?\:\;\'\"\`\~\\#\$\%\^\&\<\>)+]/;
		if(valor.match(user)){
	  		alert("No se permiten caracteres especiales");
	  		return false;	  		
        }else{
        	return true;
        }
	}
	
	function validaNumero(campo){
		var user = /[1234567890]/;
        if(!campo.match(user)){
		  return mensaje("S&oacute;lo se permiten N&uacute;meros");
        }else{
          return true;
        }
	}
	
	function seleccion(linea){
		var segm=linea.split("|");
		//alert(linea);
		$("#telefono").val(segm[0]);
		$("#cuentaAlt").val(segm[1]);
		$("#region").val(segm[2]);
		$("#estatusPuntos").val(segm[3]);
	}
	
	function puntoVta(linea){
		var segm=linea.split("|");
		parent["consulta"].document.forms[0].idPuntoVta.value=segm[0];
		parent["consulta"].document.forms[0].segmentoIP.value=segm[1];
		parent["consulta"].document.forms[0].rangoInf.value=segm[2];
		parent["consulta"].document.forms[0].rangoSup.value=segm[3];
		parent["consulta"].document.forms[0].idRegion.value=segm[4];
		parent["consulta"].document.forms[0].ivaProcentaje.value=segm[5];
		
		var agregar = parent["consulta"].document.forms[0].Agregar;
		if(agregar!=null){
			agregar.disabled=true;
		}
		var actualizar  = parent["consulta"].document.forms[0].Actualizar;
		if(actualizar!=null){
			actualizar.disabled=false;
		}
		var eliminar = parent["consulta"].document.forms[0].Eliminar
		if(eliminar!=null){
			eliminar.disabled=false;
		}
	}
	
	function AdminUsers(){
		
		var numEmpleado = document.getElementById('numEmpleado');
		var idUsuario = document.getElementById('idUsuario');
		var nombre = document.getElementById('nombre');
		var password = document.getElementById('password');
		var confirmacionPassword = document.getElementById('confirmacionPassword');
		var status = document.getElementById('status');
		var perfil = document.getElementById('perfil');
		var check= document.getElementById('miCheck').checked;
		
		//validaciones para numero de empleado
		if(numEmpleado.value==''){
			alert('Debe Capturar un Número de Empleado ');
			return false;
		}
		
		//validaciones para identificación de usuario
		if(idUsuario.value==''){
			alert('Debe Capturar una Identificación de Usuario ');
			return false;
		}else{
			var res = validaCaractEspeciales(idUsuario.value);
			if(res==false){return false;}
        }
        
        //validaciones para nombre
        if(nombre.value==''){
        	alert('Debe Capturar un Nombre ');
        	return false;
        }else{
        	var res = validaCaractEspeciales(nombre.value);
        	if(res==false){return false;}
        }
        
        if (check==true){
        //validaciones para password
        if(password.value==''){
        	alert('Debe Capturar una Contraseña ');
        	return false;
        }else{
        	var res = validaCaractEspeciales(password.value);
        	if(res==false){return false;}
        }
        
        //validaciones para confirmación de password
        if(confirmacionPassword.value==''){
        	alert('Debe Capturar la verificación de la contraseña ');
        	return false;
        }else{
        	var res = validaCaractEspeciales(confirmacionPassword.value);
        	if(res==false){return false;}
        }
        if(password.value!=confirmacionPassword.value){
        	alert('La contraseña y la verificación de la contraseña deben ser identicas');
        	return false;
        }
        if(confirmacionPassword.value.length!=8 || password.value.length!=8){
			alert('La contraseña y/o la verificación de la contraseña deben ser de 8 caracteres');
			return false;
		}
		if(status.value==null || status.value=='' || status.value=='Selecciona'){
			alert('Debe Capturar un Estatus de Empleado ');
			return false;
		}
		if(perfil.value==null || perfil.value=='' || perfil.value=='Selecciona'){
			alert('Debe Capturar un Perfil de Empleado ');
			return false;
		}
        }
        
        if (check==true){
        }
        return true;
	}
	
	function PtosVta(){
		if($("#accion").val()==""){
			$("#accion").val("Consultar")
		}
		if($("#rangoInf").val()==""){
			$("#rangoInf").val(0);
		}
		if($("#rangoSup").val()==""){
			$("#rangoSup").val(0);
		}
		if($("#accion").val()=="Consultar"){
			if($("#idRegion").val()=="0"){
				return mensaje("El campo Regi&oacute;n es Requerido ");
			}
			$("#rangoSup").val(0);
			$("#rangoInf").val(0);
			if($("#idPuntoVta").val()!=""){
				var campo=$("#idPuntoVta").val();
				var res=validaCampo(campo);
				if(!res){return false;}
			}
			if($("#segmentoIP").val()!=""){
				var campo=$("#segmentoIP").val();
				var res=validaCampo(campo);
				if(!res){return false;}
			}
		}else if($("#accion").val()=="Agregar" || $("#accion").val()=="Actualizar"){
			if($("#idPuntoVta").val()==""){
				return mensaje("El campo Punto de Venta es Requerido ");
			}else{
				var campo=$("#idPuntoVta").val();
				var res=validaCampo(campo);
				if(!res){return false;}
			}
			if($("#segmentoIP").val()==""){
				return mensaje("El campo Segmento IP es Requerido ");
			}else{
				var campo=$("#segmentoIP").val();
				var res=validaCampo(campo);
				if(!res){return false;}
			}
			if($("#rangoInf").val()==""){
				return mensaje("El campo Rango Inferior es Requerido ");
			}else{
				var campo=$("#rangoInf").val();
				var res=validaCampo(campo);
				if(!res){return false;}
				else{
					res=validaNumero(campo);
					if(!res){return false;}
				}
			}
			if($("#rangoSup").val()==""){
				return mensaje("El campo Rango Superior es Requerido ");
			}else{
				var campo=$("#rangoSup").val();
				var res=validaCampo(campo);
				if(!res){return false;}
				else{
					res=validaNumero(campo);
					if(!res){return false;}
				}
			}
			if($("#idRegion").val()=="0"){
				return mensaje("El campo Regi&oacute;n es Requerido ");
			}
		}else if($("#accion").val()=="Eliminar"){
			if($("#idPuntoVta").val()==""){
				return mensaje("El campo Punto de Venta es Requerido ");
			}else{
				var campo=$("#idPuntoVta").val();
				var res=validaCampo(campo);
				if(!res){return false;}
			}
		}
		return true;
	}
	
	function agregar(){
		var numero= $("#telefono").val();
		var cuenta= $("#cuenta").val();
		var expresion = /\d{10}/;
				
		if($("#operacion1").is(":checked") && $("#telefono").val()==""){
 			return mensaje("Debe Capturar un Tel&eacute;fono ");
 		}else if($("#operacion2").is(":checked") && $("#cuenta").val()==""){
 			return mensaje("Debe Capturar una cuenta ");
 		}else if($("#operacion1").is(":checked") && !expresion.test(numero)){
			return mensaje("Número no valido");
		}else if($("#tablaLista input:submit[@value="+numero+"]").length>0){
			return mensaje("El Número ya existe en la lista.");
		}else if($("#tablaLista input:hidden[@value="+cuenta+"]").length>0){
			return mensaje("La cuenta ya existe en la lista.");
		}else{
			if(cuenta==""){
				cuenta="000";
			}
			var queryString = "telefono=" + numero + "&cuenta=" + cuenta;
			$.ajax(	{
					url:"./consultaDetallePuntitos.do",
					type:"POST",
					data: queryString,
					cache: false,
					success: agregaRespuesta
			});
		}
	}
	
	function pantallas(){
		if(PtosVta()){
			parent["detalle"].document.forms[0].idPuntoVta.value=parent["consulta"].document.forms[0].idPuntoVta.value;
			parent["detalle"].document.forms[0].segmentoIP.value=parent["consulta"].document.forms[0].segmentoIP.value;
			parent["detalle"].document.forms[0].rangoInf.value=parent["consulta"].document.forms[0].rangoInf.value;
			parent["detalle"].document.forms[0].rangoSup.value=parent["consulta"].document.forms[0].rangoSup.value;
			parent["detalle"].document.forms[0].idRegion.value=parent["consulta"].document.forms[0].idRegion.value;
			parent["detalle"].document.forms[0].ivaProcentaje.value=parent["consulta"].document.forms[0].ivaProcentaje.value;
			parent["detalle"].document.forms[0].submit();
		}
	}
	
	function agregaRespuesta(data){
		var tagExito = "<tr id=\"" + $("#telefono").val();
		if(data.indexOf(tagExito) != -1){
			$("#tablaLista tbody").append(data);
			$("#spTotal").html($("#tablaLista [@name=listaTelefonos]").length);
		}else{
			mensaje("El n&uacute;mero no se encuentra en Circulo Azul");
		}
	}
	
	function folioValidar(valor){
		$("#folio").val(""+valor+"");
		if(confirm("Estas seguro de querer cancelar")){
			document.frmReserva.submit();
		}
	}

	function seleccionaOpcion(_opcion){
 		if(_opcion=="cuenta"){
 			document.getElementById("operacion2").checked=true;
 			$("#telefono").val("");
 		}else if(_opcion=="telefono"){
 			document.getElementById("operacion1").checked=true;
 			$("#cuenta").val("");
 		}
	}
		
	function sendFile(forma){
	
		var form = document.getElementById(forma);
		form.action = './agregaPromociones.do';
		
		var descripcion = document.getElementById('descripcion');
		if(descripcion==null||descripcion.value==''){
			alert("Introduzca una descripcion.");
			return false;
		}
		
		var user = /[(\*\(\)\[\]\+\,\/\?\:\;\'\"\`\~\\#\$\%\^\&\<\>)+]/;
        if(descripcion.value.match(user)){
		  alert("No se permiten caracteres especiales");
		  return false;
        }
		
		var estatus = document.getElementById('estatus');
		if(estatus==null || estatus.value==''){
			alert("El tipo de Documento es Requerido");
			return false;
		}
		
		var archivo = document.getElementById('archivo');
		if(archivo==null || archivo.value==''){
			alert("Favor de seleccionar un archivo");
			return false;
		}
		
		form.submit();	
		
	}
	
	function sendPromocion(forma){
		try{
			document.getElementById("divLoading").style.display = "block";
			document.getElementById("divLoading").style.visibility="visible";
			forma = "#" + forma;
			var documento =  $(forma + " input[@type=file]").val();
			if($("#tipo").val()==""){
				return mensaje("El tipo de Documento es Requerido");
			}
			if(documento==""){
				return mensaje("Favor de seleccionar un archivo");
			}
			var extencion = documento.substring(documento.length-3, documento.length).toUpperCase();
			
			if(extencion != "CSV"){
				return mensaje("Formato de archivo no valido, solo se aceptan archivos CSV");
			}
			return true; // cancel conventional submit
		}catch(exception){
			mensaje("Hubo un error: " + exception+"");
		}
	}
	
	function procesaArchivos(forma){
		var form = document.getElementById(forma);
		var tipoDocumento = document.getElementById('tipo');
		var tipoProceso = document.getElementById('tipoProceso');
		var idRegion = document.getElementById('idRegion');
		
		if(tipoDocumento==null||tipoDocumento.value==""){
			alert("El tipo de Documento es Requerido");
			return false;
		}
		
		if(tipoProceso==null||tipoProceso.value==""){
			alert("Seleccione un tipo de Proceso");
			return false;
		}
		if(idRegion==null||idRegion.value==""){
			alert("Seleccione una región");
			return false;
		}
		
		var archivo = document.getElementById("archivo");
		if(archivo==null || archivo.value==""){
			alert("Seleccione un archivo");
			return false;
		}
		
		var extencion = archivo.value.substring(archivo.value.length-3, archivo.value.length).toUpperCase();
		
		if(extencion != "CSV"){
			alert("Formato de archivo no valido, solo se aceptan archivos CSV");
			return false;
		}
		
		form.action="./procesaArchivos.do?tipo=" + tipoDocumento.value + "&tipoProceso=" 
					+ tipoProceso.value + "&idRegion=" + idRegion.value + 
					"&tipoCarga=1";
		form.submit();
	}
	
	function procesaArchivoDeur(){
		var archivoDeur = document.getElementById("archivoDeur");
		if(archivoDeur==null || archivoDeur.value==''){
			alert("El nombre del archivo es requerido");
			return false;
		}
		if(archivoDeur.value.indexOf(".txt")==-1){
			alert("Formato de archivo no valido, solo se aceptan archivos txt");
			return false;
		}
		var form = document.getElementById("formProcesaDeur");
		form.action="./procesaArchivos.do?tipoCarga=2&archivoDeur=" + archivoDeur.value;
		form.submit();
	}
	
	function procesaArchivoMasivoFTP(){
		var archivoMasivoFTP = document.getElementById("archivoMasivoFTP");
		var tipoProcesoFTP = document.getElementById("tipoProcesoFTP");
		
		if(archivoMasivoFTP==null || archivoMasivoFTP.value==''){
			alert("El nombre del archivo es requerido");
			return false;
		}
		if(archivoMasivoFTP.value.indexOf(".csv")==-1){
			alert("Formato de archivo no valido, solo se aceptan archivos csv.");
			return false;
		}
		if(tipoProcesoFTP==null||tipoProcesoFTP.value==""){
			alert("Seleccione un tipo de Proceso");
			return false;
		}
		
		var form = document.getElementById("formProcesa");
		form.action = "./procesaArchivos.do?tipoCarga=3&archivoMasivoFTP=" + archivoMasivoFTP.value + "&tipoProcesoFTP=" + tipoProcesoFTP.value;
		form.submit();
	}
	
	function agregaPromo(data){
		var tagExito = "rror";
		if(data.indexOf(tagExito) != -1){
			tagExito="terminado";
			if(data.indexOf(tagExito) != -1){
				mensaje("La hora de ejecuci&oacute;n es antes de las 8hrs o despues de las 20 hrs");
			}else{
				mensaje("Ha ocurrido un error, favor de validar ");
			}
		}else{
			mensaje("Proceso Exitoso");
		}
	}
	
	function documentoTO(linea){
		document.getElementById("formAgregar").style.display = "none";
		document.getElementById("formAgregar").style.visibility="hidden";
		document.getElementById("formEliminar").style.display = "block";
		document.getElementById("formEliminar").style.visibility="visible";
		var segm=linea.split("|");
		$("#idDocumento").val(segm[0]);
		$("#nombre").val(segm[1]);
		$("#descripcionElimina").val(segm[2]);
	}
	
	function selec(accion){
		$("#accion").val(accion);
		oculta();
		if(accion=="Grupos"){
			document.getElementById("grupo1").style.display = "block";
			document.getElementById("grupo1").style.visibility="visible";
			document.getElementById("grupo2").style.display = "block";
			document.getElementById("grupo2").style.visibility="visible";
			document.getElementById("estatusGpoPromo").disabled=false;
		}
		if(accion=="Planes"){
			document.getElementById("plan1").style.display = "block";
			document.getElementById("plan1").style.visibility="visible";
			document.getElementById("plan2").style.display = "block";
			document.getElementById("plan2").style.visibility="visible";
			document.getElementById("estatus").disabled=false;
			document.getElementById("region").disabled=false;
			
		}else if(accion=="Promociones"){
			document.getElementById("prom1").style.display = "block";
			document.getElementById("prom1").style.visibility="visible";
			document.getElementById("prom2").style.display = "block";
			document.getElementById("prom2").style.visibility="visible";
			document.getElementById("prom3").style.display = "block";
			document.getElementById("prom3").style.visibility="visible";
			document.getElementById("idestatus").disabled=false;
			document.getElementById("idregion").disabled=false;			
			document.getElementById("idtipoproducto").disabled=false;
			/* Agregar tipos de producto JAPA 27/09/2011 Folio 96353 */
			document.getElementById("idAreaPromoc").disabled=false;
		}else if(accion=="FuerzasVentas"){
			document.getElementById("fza1").style.display = "block";
			document.getElementById("fza1").style.visibility = "visible";
			document.getElementById("fzaVtaEstatus").disabled=false;
			//JSC Folio: 98615
		} else if(accion=="PromocionesSms"){
			document.getElementById("promoSms").style.display = "block";
			document.getElementById("promoSms").style.visibility = "visible";
			//JSC Folio: 111775
		} else if(accion=="ClienteExcelentes"){
			document.getElementById("cteExcelentes").style.display = "block";
			document.getElementById("cteExcelentes").style.visibility = "visible";
			document.getElementById("cteExcelentes2").style.display = "block";
			document.getElementById("cteExcelentes2").style.visibility = "visible";
			document.getElementById("estatusCE").disabled=false;
			document.getElementById("regionCE").disabled=false;
		}
		
	}
	
	function oculta(){
		
		document.getElementById("grupo1").style.display = "none";
		document.getElementById("grupo1").style.visibility="hidden";
		document.getElementById("grupo2").style.display = "none";
		document.getElementById("grupo2").style.visibility="hidden";
		document.getElementById("prom1").style.display = "none";
		document.getElementById("prom1").style.visibility="hidden";
		document.getElementById("prom2").style.display = "none";
		document.getElementById("prom2").style.visibility="hidden";
		document.getElementById("prom3").style.display = "none";
		document.getElementById("prom3").style.visibility="hidden";
		document.getElementById("plan1").style.display = "none";
		document.getElementById("plan1").style.visibility="hidden";
		document.getElementById("plan2").style.display = "none";
		document.getElementById("plan2").style.visibility="hidden";
		document.getElementById("estatusGpoPromo").disabled=true;
		document.getElementById("idestatus").disabled=true;
		document.getElementById("estatus").disabled=true;
		document.getElementById("idregion").disabled=true;
		document.getElementById("region").disabled=true;
		document.getElementById("idtipoproducto").disabled=true;
		document.getElementById("fza1").style.display = "none";
		document.getElementById("fza1").style.visibility = "hidden";
		document.getElementById("fzaVtaEstatus").disabled=true;
		//JSC Folio: 98615
		document.getElementById("promoSms").style.display = "none";
		document.getElementById("promoSms").style.visibility = "hidden";
		//JSC Folio: 111775
		document.getElementById("cteExcelentes").style.display = "none";
		document.getElementById("cteExcelentes").style.visibility = "hidden";
		document.getElementById("cteExcelentes2").style.display = "none";
		document.getElementById("cteExcelentes2").style.visibility = "hidden";
		document.getElementById("estatusCE").disabled=true;
		document.getElementById("regionCE").disabled=true;
		/* Agregar tipos de producto JAPA 27/09/2011 Folio 96353 */
		document.getElementById("idAreaPromoc").disabled=true;
	}
	
	function exporta(){
		var form = document.getElementById("frmConsulta");
		
		var accion = document.getElementById("accion");
		accion.value = "Exporta";
		
		var reporte = document.getElementById("reporte");
				
		var grupo = document.getElementById("Grupo");
		var plan = document.getElementById("Planes");
		var promocion = document.getElementById("Promociones");
		var fuerzaVentas = document.getElementById("FuerzasVentas");
		
		if(grupo.checked==true){
			reporte.value="Grupos";
		}
		if(plan.checked==true){
			reporte.value="Planes";
		}
		if(promocion.checked==true){
			reporte.value="Promociones";
		}
		if(fuerzaVentas.checked==true){
			reporte.value="FuerzasVentas";
		}
		
		if(grupo.checked!=true && plan.checked!=true && promocion.checked!=true && 
			fuerzaVentas.checked!=true){
			alert("Debe seleccionar una opción para exportar.");
			return false;
		}		
				
		form.submit();
		
		/*
		var accion=$("#accion").val();
		$("#accion").val("Exporta");
		$("#reporte").val(accion);
		*/
	}
	
	function muestraProcesa(){
		
	}
	
	function consulta(){
		if($("#accion").val()==""){
			return mensaje("El campo accion es Requerido ");
		}
		if($("#accion").val()=="Planes" || $("#reporte").val()=="Planes"){
			if($("#estatus").val()==""){
				return mensaje("El campo Estatus es Requerido ");
			}
			if($("#segmento").val()==""){
				return mensaje("El campo Eegmento es Requerido ");
			}
			if($("#region").val()==""){
				return mensaje("El campo Region es Requerido ");
			}
			if($("#adendum").val()==""){
				return mensaje("El campo Addendum es Requerido ");
			}
		}
		if($("#accion").val()=="Promociones" || $("#reporte").val()=="Promociones"){
			if($("#idestatus").val()==""){
				return mensaje("El campo Estatus es Requerido ");
			}
			if($("#idregion").val()==""){
				return mensaje("El campo Region es Requerido ");
			}
			
		}
		muestraProcesa();
	}
	
	function grupo(idGrupo){
		
		try{
			$("#idgrupo").val(idGrupo);
			$("#accion").val("CONSULTA");
			$("#tipo").val("GRUPO");
			document.frmConsulta.action="./actualizaCatalogo.do";
			document.frmConsulta.submit();
			return true; // cancel conventional submit
		}catch(exception){
			mensaje("Hubo un error: " + exception+"");
		}
	}
	
	function plan(idPlan){
		try{
			$("#idplan").val(idPlan);
			$("#accion").val("CONSULTA");
			$("#tipo").val("PLAN");
			document.frmConsulta.action="./actualizaCatalogo.do";
			document.frmConsulta.submit();
			return true; // cancel conventional submit
		}catch(exception){
			mensaje("Hubo un error: " + exception+"");
		}
	}
	
	function promo(idPromo){
		try{
			$("#idpromo").val(idPromo);
			$("#accion").val("CONSULTA");
			$("#tipo").val("PROMO");
			document.frmConsulta.action="./actualizaCatalogo.do";
			document.frmConsulta.submit();
			return true; // cancel conventional submit
		}catch(exception){
			mensaje("Hubo un error: " + exception+"");
		}
	}

	/* Pantalla Consulta Distribuidores JAPA 20/07/2011 Folio XXXXX */
	function selecDistribuidor(accion) {
		if(accion=="Distribuidores"){
			document.getElementById("consulta1").style.visibility = "visible";
			document.getElementById("consulta1").style.display = "block";
			
			document.getElementById("modelos").disabled=true;
		}

	}
	//JSC Folio:115460
	function procesaArchivoMembresia(forma){
		var form = document.getElementById(forma);
		
		var archivo = document.getElementById("archivo");
		if(archivo==null || archivo.value==""){
			alert("Seleccione un archivo");
			return false;
		}
		
		var extencion = archivo.value.substring(archivo.value.length-3, archivo.value.length).toUpperCase();
		
		if(extencion != "CSV"){
			alert("Formato de archivo no valido, solo se aceptan archivos CSV");
			return false;
		}
		form.action="./procesaArchivoMembresia.do";
		form.submit();
	}