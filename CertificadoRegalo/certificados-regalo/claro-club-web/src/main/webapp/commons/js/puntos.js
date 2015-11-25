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
	
	function validaCampo(campo){
		var user = /[(\*\(\)\[\]\+\,\/\?\:\;\'\"\`\~\\#\$\%\^\&\<\>)+]/;
        if(campo.match(user)){
		  return mensaje("No se permiten caracteres especiales");
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
	
	function seleccionaOpcion(_opcion){
 		if(_opcion=="cuenta"){
 			$("#operacion1").attr("checked",false);
 			$("#operacion2").attr("checked",true);
 			$("#telefono").val("");
 		}else if(_opcion=="telefono"){
 			$("#operacion2").attr("checked",false);
 			$("#operacion1").attr("checked",true);
 			$("#cuenta").val("");
 		}
	}
	
	function consultaDetalle(){        		      
		if($("#operacion1").is(":checked")){
			if($("#telefono").val()==""){
				return mensaje("Debe Capturar un Teléfono");
			}if(isNaN($("#telefono").val()) || $("#telefono").val().length != 10){
				$("#telefono").val("");
				return mensaje("El telefono no es valido");
			}
		}else if($("#operacion2").is(":checked")){
			if($("#cuenta").val()==""){
				return mensaje("Debe Capturar una cuenta.");
			}if(isNaN($("#cuenta").val())){
				$("#cuenta").val("");
				return mensaje("La cuenta no es valida");
			}
		}if($("#accion").val() == "congelar") {
				if(!confirm('Los puntos de esta línea se congelarán por lo que no podrán ser utilizados, \n ¿está seguro que desea continuar?')){
					return false;		
				}
		}
		
		var queryString = "telefono="+$("#telefono").val()+"&cuenta="+$("#cuenta").val()+"&region="+$("#region").val()+"&accion="+$("#accion").val();
		var url = "./consultaPtsRenuncia.do?"+queryString;
		
		if($("#accion").val()!=""){
			document.parentWindow.parent.setConsultaSubmenus(6,url);
		}else{
			return mensaje("Debe seleccionar una Acción.");
		}
		
	}