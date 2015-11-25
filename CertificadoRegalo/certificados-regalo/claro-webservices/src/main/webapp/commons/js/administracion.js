/**
 * Para el manejo de las pestañas de administración
 */
var oIdx = new Array(0,0);
var oDiv = null;
var oTar = null;
		
function inicializa(){
	setConsultaSubmenus(1);
	//setConsultaSubmenus(4);
}

function inicializaMenuadmin(){
	var spanElement = document.getElementById('B04');
	var opc=0;
	if(spanElement==null){
		spanElement=document.getElementById('B03');
		if(spanElement==null){
			spanElement=document.getElementById('B02');
			if(spanElement==null){
				opc=5;
			}else{
				opc=2;
			}			
		}else{
			opc=3;
		}
	}else{
		opc=4;
	}		
	eligeOpcionSubMenu(opc);
}

function activaCargando(propiedad1,propiedad2){		
	if(oTar != null && propiedad1=="visible") {
		oTar.style.display = "none";
		oTar.style.visibility="hidden";
	}										
		document.getElementById("TarjetaAdmin3").style.visibility=propiedad1;
		document.getElementById("TarjetaAdmin3").style.display=propiedad2;
			if (oTar != null && propiedad1=="hidden"){
				oTar.style.display= "block";
				oTar.style.visibility="visible";
			} 
	}


function setConsultaSubmenus(_metodo){
	if(oDiv != null) oDiv.className = "botonInactivo";
	if(oTar != null) {
		oTar.style.display = "none";
		oTar.style.visibility="hidden";
	}
		
	oDiv = document.getElementById("B0" + _metodo);
	oTar = document.getElementById("TarjetaAdmin" + _metodo);
	oDiv.className= "botonActivo";
	oTar.style.display= "block";
	oTar.style.visibility="visible";
	
	//Llama a Datos;
	
	if(_metodo==1 && oIdx[0]==0 ){	
		activaCargando("visible","block");	  				  			
		oIdx[0] = 1;					
		FrameAdmin1.location = "./administracion.do";  		
	}else if(_metodo==4 && oIdx[1]==0){	
		activaCargando("visible","block");  					  			 
  		oIdx[1] = 1;			
  		FrameAdmin4.location = "./avisos.do?valor=1"; 	  		
	}
}

	function eligeOpcionSubMenu(_metodo){
		if(oDiv != null) oDiv.className = "botonInactivo";
		oDiv = document.getElementById("B0" + _metodo);
		oDiv.className= "botonActivo";		  		
				
		//Llama a Datos;		  		
		if(_metodo==1){
			FrameAdmin.location = "./administracion.do";  		
		}
		if(_metodo==2){
			FrameAdmin.location = "./framePuntVenta.do";  		
		}
		if(_metodo==3){
			FrameAdmin.location = "./menuPromociones.do";  		
		}
		if(_metodo==4){
			FrameAdmin.location = "./avisos.do?valor=1"; 	  		
	 	}		  	
		if(_metodo==5){
			FrameAdmin.location = "./reportesCa.do"; 	  		
	 	}
		if(_metodo==6){
			FrameAdmin.location = "./menuAdmonPuntos.do"; 	  		
	 	}
	}			  	
    		 
  	

/**
 * Para la validación de los campos para el 
 * cambio de password.
 */
	function validaPasswd() {
		if(document.getElementById('password').value == ""){
			alert("Debe ingresar su password ACTUAL.")
			return false;
		}
		
		/*if(document.getElementById('password').value.length != 7){
			alert("Su password ACTUAL debe ser de 7 caracteres.")
		    return false;
		}*/
		
		if(document.getElementById('passwordNuevo').value == ""){
			alert("Debe ingresar su NUEVO password.")
		    return false;
		}
		 
		if(document.getElementById('passwordNuevo').value.length != 8){
			alert("El NUEVO password debe ser de 8 caracteres.")
		    return false;
		}
		 
		if(document.getElementById('confirmacionPassword').value == ""){
			alert("Debe ingresar la CONFIRMACIÓN de su password.")
		    return false;
		}
		 
		if(document.getElementById('confirmacionPassword').value.length != 8){
			alert("La CONFIRMACIÓN de su password debe ser de 8 caracteres.")
		    return false;
		}
		 
		if(document.getElementById('passwordNuevo').value != document.getElementById('confirmacionPassword').value){
			alert("La CONFIRMACIÓN de su password debe ser igual al NUEVO password.")
		    return false;
		}
		 
		if(document.getElementById('password').value == document.getElementById('passwordNuevo').value ||
			document.getElementById('password').value == document.getElementById('confirmacionPassword').value){
		 	alert("El NUEVO password no debe ser igual al ACTUAL.");
		 	return false;
		}
	
    cambiaPass.method = "POST";
    cambiaPass.action = "./actualizaPassword.do";
    cambiaPass.submit();	 
}
