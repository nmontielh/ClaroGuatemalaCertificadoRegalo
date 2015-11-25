	function returnFolio(r){	
		indice=r-1;
		radioArray = document.frmFolios.idfolio;					
		
		if(document.frmFolios.idfolio.length==undefined){				
			document.frmFolios.idfolio.checked=true;
			document.frmFolios.idfolio.disabled=false;
			folio=document.frmFolios.idfolio.value;
			cuenta=0;
		}else{
			for(i=0;i<radioArray.length;i++){
				if(indice==i){
					radioArray[indice].checked=true;
					radioArray[indice].disabled=false;
					folio=radioArray[indice].value;
					cuenta=indice;
				}else{
					radioArray[i].checked=false;
					radioArray[i].disabled=true;
				}
			}
		}
	}
	
	function actualizaFolio(){
		radioArray = document.frmFolios.idfolio;		
		if(cuenta!=0){
			for(i=0;i<radioArray.length;i++){			
				if(radioArray[i].checked==true){
					indice=i+1;
					esnImeiTNvo=document.getElementById(indice+"_serieT").value;
					esnImeiPNvo=document.getElementById(indice+"_serieP").value;
					iccidNvo=document.getElementById(indice+"_iccid").value;
					tecnologia=document.getElementById(indice+"_tecnologia").value;					
					break;
				}else{
					continue;
				}				
			}
		}else{
			cuenta=cuenta+1;
			esnImeiTNvo=document.getElementById(cuenta+"_serieT").value;
			esnImeiPNvo=document.getElementById(cuenta+"_serieP").value;
			iccidNvo=document.getElementById(cuenta+"_iccid").value;
			tecnologia=document.getElementById(cuenta+"_tecnologia").value;		
		}		
		
		if(folio!=null && folio !=""){
			if(confirm("¿Desea actualizar el folio seleccionado?")){
				if(validaSeries()){			
					frmFolios.action="./actualizaFolio.do?folio="+folio+"&cuenta="+document.parentWindow.parent.document.getElementById("cuenta").value+
					"&secuencia="+document.parentWindow.parent.document.getElementById("secuencia").value+
					"&esnImeiRNvo="+esnImeiTNvo+"&esnImeiPNvo="+esnImeiPNvo+"&iccidNvo="+iccidNvo;
					frmFolios.submit();
				}	
			}
		}else{
			window.alert("Debe seleccionar un folio válido para continuar con la actualización.");
		}
	}