<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
      pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>


<%@page import="java.util.Set"%>
<%@page import="com.claro.transfer.PrivilegioTO"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Privilegios</title>

<script src="<c:url value='/commons/js/jquery-1.2.3.js'></c:url>"
      type="text/javascript"></script>
<script src="<c:url value='/commons/js/jquery.ui.js'></c:url>"
      type="text/javascript"></script>
<script src="<c:url value='/commons/js/jquery.collapser.js'></c:url>"
      type="text/javascript"></script>
<link rel="stylesheet"
      href='<c:url value="/commons/js/sc_textsheet.css"/>' type="text/css">

<script type="text/javascript">
                  
                  var privilegiosArray = [];
                  
                  $(document).ready(function(){
                        
                        $('.panel').hide();
                        
                        $('.grupoPrivilegios').collapser({
                             target: 'next',
                             effect: 'slide',
                             changeText: 0,
                             expandClass: 'expIco',
                             collapseClass: 'collIco'
                        }, function(){
                             $('.panel').slideUp();
                        }           
                        );    
                  }
                        
                  );
                  
                  function inicializaPrivilegios(privilegios){
                        var privilegiosPerfil = document.parentWindow.parent.document.getElementById('privilegiosPerfil');
                        privilegiosPerfil.value = privilegios;
                        
                        privilegiosArray = privilegiosPerfil.value.split(",");
                                               
                        for(var i=0;i<privilegiosArray.length;i++){
                             var privilegioId = privilegiosArray[i]; 
                             var check = document.getElementById(privilegioId);
                             if(check!=null){
                                   check.checked = 'checked';                                 
                             }
                        }
                        var btnActualizar = document.parentWindow.parent.document.getElementById('btnActualizar');
                        
                        if(btnActualizar!=null){
                             btnActualizar.style.visibility="visible";
                        }                 
                  }
                  function seleccionaPrivilegio(privilegio){                 
                        //Elimina privilegio del arreglo
                        if(privilegio.checked==false){
                             var idx = -1;                      
                             for(var i=0;i<privilegiosArray.length;i++){
                                   var privilegioId = privilegiosArray[i];                          
                                   if(privilegio.id==privilegioId){
                                         idx = i;
                                         break;                                                                      
                                   }                            
                             }
                             if(idx!=-1){
                                   privilegiosArray.splice(idx,1);                                                         
                             }                                                          
                        }
                        //Agrega privilegio al arreglo
                        if(privilegio.checked){
                             var idx = -1;
                             for(var i=0;i<privilegiosArray.length;i++){
                                   var privilegioId = privilegiosArray[i];
                                   if(privilegio.id==privilegioId){
                                         idx = i;
                                         break;
                                   }
                             }
                            if(idx==-1){
                                   privilegiosArray.push(privilegio.id);
                             }
                        }
                        document.parentWindow.parent.document.getElementById('privilegiosPerfil').value = privilegiosArray;
                  }
                  
                  function selectAll(perfil,formId){
                  
                        var checkBoxes = document.getElementById(formId).elements;
                        
                        seleccionaPrivilegio(perfil);
                        
                        for(var i=0;i<checkBoxes.length;i++ ){
                             var checkBox = checkBoxes[i];
                             if(checkBox.type=='checkbox' && perfil.checked==false){
                                   checkBox.checked = false;                                  
                             }
                             if(checkBox.type=='checkbox' && perfil.checked){
                                   checkBox.checked = true;                                   
                             }
                             seleccionaPrivilegio(checkBox);
                        }
                  }
                  
                  
                  function selectAllFromTable(perfil,tableId){
                                   
                        var registros = document.getElementById(tableId).rows;
                        
                        seleccionaPrivilegio(perfil);
                        
                        for(var i=0;i<registros.length;i++){
                             var checkBox = registros[i].cells[0].children[0];
                             if(checkBox.type=='checkbox' && perfil.checked==false){
                                   checkBox.checked = false;                                  
                             }
                             if(checkBox.type=='checkbox' && perfil.checked){
                                   checkBox.checked = true;                                   
                             }
                             seleccionaPrivilegio(checkBox);
                        }                 
                  }
                  
                  
            </script>
<style>
h4 {
      font-weight: normal;
      clear: both;
}

.grupoPrivilegios {
      font-family: "Tahoma", "Verdana";
      font-weight: bold;
      font-size: 10pt;
      background-color: #e4e9ee;
      color: #147FB5;
      border-top-width: 1px;
      border-right-width: 1px;
      border-bottom-width: 1px;
      border-left-width: 1px;
      border-top-style: solid;
      border-right-style: solid;
      border-bottom-style: solid;
      border-left-style: solid;
      border-top-color: #6699ff;
      border-right-color: #6699ff;
      border-bottom-color: #6699ff;
      border-left-color: #6699ff;
      padding-top: 2px;
      padding-bottom: 2px;
      cursor: pointer;
}

.panel {
      border: 1px solid #6699ff;
      padding: 10px;
}
</style>
</head>

<body onload="inicializaPrivilegios('${privilegiosPerfil }')"
      background="<c:url value='/commons/images/backgroundlight.gif'/>">

<table>
      <tr>
            <td width="550px" valign="top">
            <h4 class="grupoPrivilegios">Menú Puntos</h4>
            <div class="panel">
            <table>
                  <tr class="X3">
                        <td><input id="1" type="checkbox"
                             onclick="seleccionaPrivilegio(this);"></td>
                        <td>Menú Puntos</td>
                  </tr>
                  <tr class="X3">
                        <td><input id="5" type="checkbox"
                             onclick="selectAll(this,'menuPtosConsulta');"></td>
                        <td>Consulta</td>
                  </tr>
                  <tr class="X3">
                        <td></td>
                        <td>
                        <form action="" id="menuPtosConsulta">
                        <table>
                             <tr class="X3">
                                   <td><input id="20" type="checkbox"
                                         onclick="selectAllFromTable(this,'menuPtosContinuar');"></td>
                                   <td>Continuar</td>
                             </tr>
                             <tr class="X3">
                                   <td></td>
                                   <td>
                                   <table id="menuPtosContinuar">
                                         <tr class="X3">
                                               <td><input id="60" type="checkbox"
                                                     onclick="seleccionaPrivilegio(this);"></td>
                                               <td>Valida Crédito AR</td>
                                         </tr>
                                   </table>
                                   </td>
                             </tr>
                        </table>
                        </form>
                        </td>
                  </tr>
                  <tr class="X3">
                        <td><input id="6" type="checkbox"
                             onclick="seleccionaPrivilegio(this);"></td>
                        <td>Detalle</td>
                  </tr>
                  <tr class="X3">
                        <td></td>
                        <td>
                        <table>
                             <tr class="X3">
                                   <td><input id="21" type="checkbox"
                                         onclick="seleccionaPrivilegio(this);"></td>
                                   <td>Puntos</td>
                             </tr>
                             <tr class="X3">
                                   <td><input id="22" type="checkbox"
                                         onclick="seleccionaPrivilegio(this);"></td>
                                   <td>Movimientos</td>
                             </tr>
                             <tr class="X3">
                                   <td><input id="23" type="checkbox"
                                         onclick="seleccionaPrivilegio(this);"></td>
                                   <td>Redención</td>
                             </tr>
                             <tr class="X3">
                                   <td></td>
                                   <td>
                                   <table>
                                         <tr class="X3">
                                               <td><input id="62" type="checkbox"
                                                     onclick="seleccionaPrivilegio(this);"></td>
                                               <td>Histórico</td>
                                         </tr>
                                         <tr class="X3">
                                               <td><input id="63" type="checkbox"
                                                     onclick="seleccionaPrivilegio(this);"></td>
                                               <td>Nueva</td>
                                         </tr>
                                         <tr class="X3">
                                               <td></td>
                                               <td>
                                               <table>
                                                     <tr class="X3">
                                                           <td><input id="164" type="checkbox"
                                                                 onclick="seleccionaPrivilegio(this);"></td>
                                                           <td>Redencion Normal</td>
                                                     </tr>
                                                     <tr class="X3">
                                                           <td><input id="86" type="checkbox"
                                                                 onclick="selectAll(this,'menuPtosRedeRenovaAden');"></td>
                                                           <td>Red.Renova Adendum</td>
                                                     </tr>
                                                     <tr class="X3">
                                                           <td></td>
                                                           <td>
                                                           <form action="" id="menuPtosRedeRenovaAden">
                                                           <table>
                                                                 <tr class="X3">
                                                                        <td><input id="118" type="checkbox"
                                                                              onclick="seleccionaPrivilegio(this);"></td>
                                                                        <td>Busca Promociones</td>
                                                                  </tr>
                                                                  <tr class="X3">
                                                                        <td><input id="119" type="checkbox"
                                                                              onclick="seleccionaPrivilegio(this);"></td>
                                                                        <td>Busca Beneficios</td>
                                                                  </tr>
                                                                  <tr class="X3">
                                                                        <td><input id="120" type="checkbox"
                                                                              onclick="seleccionaPrivilegio(this);"></td>
                                                                        <td>Realiza Redención</td>
                                                                  </tr>
                                                                  <tr class="X3">
                                                                        <td><input id="121" type="checkbox"
                                                                              onclick="seleccionaPrivilegio(this);"></td>
                                                                        <td>Define IVA</td>
                                                                  </tr>
                                                                 
                                                           </table>
                                                           </form>
                                                           </td>
                                                     </tr>
                                                     <tr class="X3">
                                                           <td><input id="88" type="checkbox"
                                                                 onclick="seleccionaPrivilegio(this);"></td>
                                                           <td>Red.Sin Renova Adendum</td>
                                                     </tr>
                                                     <tr class="X3">
                                                           <td></td>
                                                           <td>
                                                           <table>
                                                                 <tr class="X3">
                                                                       <td><input id="101" type="checkbox"
                                                                             onclick="selectAll(this,'menuPtosRedenSinRenovaAdenAmigoKit');"></td>
                                                                       <td>Amigo Kit</td>
                                                                 </tr>
                                                                 <tr>
                                                                       <td></td>
                                                                       <td>
                                                                       		<form id="menuPtosRedenSinRenovaAdenAmigoKit">
	                                                                       <table>
	                                                                       		
	                                                                            <tr class="X3">
	                                                                                <td></td>
	                                                                                <td>
	                                                                                <table id="menuPtosRedenSinRenovaAdenAmigoKitContin">
	                                                                                      <tr class="X3">
	                                                                                            <td><input id="135" type="checkbox"
	                                                                                                  onclick="seleccionaPrivilegio(this);"></td>
	                                                                                            <td>Busca Promociones</td>
	                                                                                      </tr>
	                                                                                      <tr class="X3">
	                                                                                            <td><input id="136" type="checkbox"
	                                                                                                  onclick="seleccionaPrivilegio(this);"></td>
	                                                                                            <td>Busca Beneficios</td>
	                                                                                      </tr>
	                                                                                      <tr class="X3">
	                                                                                            <td><input id="137" type="checkbox"
	                                                                                                  onclick="seleccionaPrivilegio(this);"></td>
	                                                                                            <td>Realiza Redención</td>
	                                                                                      </tr>
	                                                                                      <tr class="X3">
	                                                                                            <td><input id="138" type="checkbox"
	                                                                                                  onclick="seleccionaPrivilegio(this);"></td>
	                                                                                            <td>Define IVA</td>
	                                                                                      </tr>
	                                                                                </table>
	                                                                                </td>
	                                                                          </tr>
	                                                                       </table>
	                                                                       </form>
                                                                       </td>
                                                                 </tr>
                                                           </table>
                                                           </td>
                                                     </tr>
                                                     <tr class="X3">
                                                           <td></td>
                                                           <td>
                                                           <table>
                                                                 <tr class="X3">
                                                                       <td><input id="102" type="checkbox"
                                                                             onclick="selectAll(this,'menuPtosRedenSinRenovaAdenVtaTpoAire');"></td>
                                                                       <td>Venta Tiempo Aire</td>
                                                                 </tr>
                                                                 <tr class="X3">
                                                                       <td></td>
                                                                       <td>
                                                                       <form action="" id="menuPtosRedenSinRenovaAdenVtaTpoAire">
                                                                       <table>
                                                                            <tr class="X3">
                                                                                  <td><input id="127" type="checkbox"
                                                                                        onclick="selectAllFromTable(this,'menuPtosRedenSinRenovaAdenVtaTpoAireBuscaPromo');"></td>
                                                                                  <td>Busca Promociones</td>
                                                                             </tr>
                                                                            <tr class="X3">
                                                                                  <td></td>
                                                                                  <td>
                                                                                  <table id="menuPtosRedenSinRenovaAdenVtaTpoAireBuscaPromo">
                                                                                        <tr class="X3">
                                                                                              <td><input id="139" type="checkbox"
                                                                                                    onclick="seleccionaPrivilegio(this);"></td>
                                                                                              <td>Redimir</td>
                                                                                       </tr>
                                                                                  </table>
                                                                                  </td>
                                                                            </tr>
                                                                       </table>
                                                                       </form>
                                                                       </td>
                                                                 </tr>
                                                           </table>
                                                           </td>
                                                     </tr>
                                                     <tr class="X3">
                                                           <td></td>
                                                           <td>
                                                           <table>
                                                                 <tr class="X3">
                                                                       <td><input id="103" type="checkbox"
                                                                             onclick="selectAll(this,'menuPtosRedenSinRenovaAdenAmigoChip');"></td>
                                                                       <td>Amigo Chip</td>
                                                                 </tr>
                                                                 <tr class="X3">
                                                                       <td></td>
                                                                       <td>
                                                                       <form action="" id="menuPtosRedenSinRenovaAdenAmigoChip">
	                                                                       <table>
	                                                                            <tr class="X3">
	                                                                                <td></td>
	                                                                                <td>
	                                                                                <table id="menuPtosRedenSinRenovaAdenAmigoChipContin">
	                                                                                      <tr class="X3">
	                                                                                            <td><input id="140" type="checkbox"
	                                                                                                  onclick="seleccionaPrivilegio(this);"></td>
	                                                                                            <td>Busca Promociones</td>
	                                                                                      </tr>
	                                                                                      <tr class="X3">
	                                                                                           <td><input id="141" type="checkbox"
	                                                                                                  onclick="seleccionaPrivilegio(this);"></td>
	                                                                                            <td>Busca Beneficios</td>
	                                                                                      </tr>
	                                                                                      <tr class="X3">
	                                                                                            <td><input id="142" type="checkbox"
	                                                                                                  onclick="seleccionaPrivilegio(this);"></td>
	                                                                                            <td>Realiza Redención</td>
	                                                                                      </tr>
	                                                                                      <tr class="X3">
	                                                                                            <td><input id="143" type="checkbox"
	                                                                                                  onclick="seleccionaPrivilegio(this);"></td>
	                                                                                            <td>Define IVA</td>
	                                                                                      </tr>
	                                                                                </table>
	                                                                                </td>
	                                                                          </tr>                                                                            
	                                                                       </table>
	                                                                      </form>
                                                                       </td>
                                                                 </tr>

                                                           </table>
                                                           </td>
                                                     </tr>
                                                     <tr class="X3">
                                                           <td></td>
                                                           <td>
                                                           <table>
                                                                 <tr class="X3">
                                                                       <td><input id="104" type="checkbox"
                                                                             onclick="selectAll(this,'menuPtosRedenSinRenovaAdenT3G');"></td>
                                                                       <td>Tarjetas Inalámbricas 3G</td>
                                                                 </tr>
                                                                 <tr class="X3">
                                                                       <td></td>
                                                                       <td>
                                                                       <form action="" id="menuPtosRedenSinRenovaAdenT3G">
                                                                       <table>
                                                                           <tr class="X3">
                                                                           		<td>
		                                                                              <table id="menuPtosRedenSinRenovaAdenT3GContin">
		                                                                                    <tr class="X3">
		                                                                                          <td><input id="144" type="checkbox"
		                                                                                                onclick="seleccionaPrivilegio(this);"></td>
		                                                                                          <td>Busca Promociones</td>
		                                                                                    </tr>
		                                                                                    <tr class="X3">
		                                                                                         <td><input id="145" type="checkbox"
		                                                                                                onclick="seleccionaPrivilegio(this);"></td>
		                                                                                          <td>Busca Beneficios</td>
		                                                                                    </tr>
		                                                                                    <tr class="X3">
		                                                                                          <td><input id="146" type="checkbox"
		                                                                                                onclick="seleccionaPrivilegio(this);"></td>
		                                                                                          <td>Realiza Redención</td>
		                                                                                    </tr>
		                                                                                    <tr class="X3">
		                                                                                          <td><input id="147" type="checkbox"
		                                                                                                onclick="seleccionaPrivilegio(this);"></td>
		                                                                                          <td>Define IVA</td>
		                                                                                    </tr>
		                                                                              </table>
		                                                                              </td>
                                                                             </tr>                                                                 
                                                                       </table>

                                                                       </form>
                                                                       </td>
                                                                 </tr>
                                                           </table>
                                                           </td>
                                                     </tr>
                                                     <tr class="X3">
                                                           <td><input id="89" type="checkbox"
                                                                 onclick="selectAll(this,'menuPtosRedenSinRenovaAdenSap');"></td>
                                                           <td>Consul Actualiza Folios SAP</td>
                                                     </tr>
                                                     <tr class="X3">
                                                           <td></td>
                                                           <td>
                                                           <form action="" id="menuPtosRedenSinRenovaAdenSap">
                                                           <table>
                                                                 <tr class="X3">
                                                                       <td><input id="105" type="checkbox"
                                                                             onclick="selectAllFromTable(this,'menuPtosRedenSinRenovaAdenSapEqPlanTarfAct');"></td>
                                                                       <td>Equipos en Plan tarifario</td>
                                                                 </tr>
                                                                 <tr class="X3">
                                                                       <td></td>
                                                                       <td>
                                                                       <table id="menuPtosRedenSinRenovaAdenSapEqPlanTarfAct">
                                                                            <tr class="X3">
                                                                                  <td><input id="130" type="checkbox"
                                                                                        onclick="seleccionaPrivilegio(this);"></td>
                                                                                  <td>Actualizar</td>
                                                                            </tr>
                                                                       </table>
                                                                       </td>
                                                                 </tr>
                                                                 <tr class="X3">
                                                                       <td><input id="106" type="checkbox"
                                                                             onclick="selectAllFromTable(this,'menuPtosRedenSinRenovaAdenSapAmigoChipAct');"></td>
                                                                       <td>Amigo Chip</td>
                                                                 </tr>
                                                                 <tr class="X3">
                                                                       <td></td>
                                                                       <td>
                                                                       <table id="menuPtosRedenSinRenovaAdenSapAmigoChipAct">
                                                                            <tr class="X3">
                                                                                  <td><input id="131" type="checkbox"
                                                                                        onclick="seleccionaPrivilegio(this);"></td>
                                                                                  <td>Actualizar</td>
                                                                            </tr>
                                                                       </table>
                                                                       </td>
                                                                 </tr>
                                                                 <tr class="X3">
                                                                       <td><input id="107" type="checkbox"
                                                                             onclick="selectAllFromTable(this,'menuPtosRedenSinRenovaAdenSapAmigoKitAct');"></td>
                                                                       <td>Equipos Amigo Kit</td>
                                                                 </tr>
                                                                 <tr class="X3">
                                                                       <td></td>
                                                                       <td>
                                                                       <table id="menuPtosRedenSinRenovaAdenSapAmigoKitAct">
                                                                            <tr class="X3">
                                                                                  <td><input id="132" type="checkbox"
                                                                                        onclick="seleccionaPrivilegio(this);"></td>
                                                                                  <td>Actualizar</td>
                                                                            </tr>
                                                                       </table>
                                                                       </td>
                                                                 </tr>
                                                                 <tr class="X3">
                                                                       <td><input id="108" type="checkbox"
                                                                             onclick="selectAllFromTable(this,'menuPtosRedenSinRenovaAdenSap3GAct');"></td>
                                                                       <td>Tarjetas 3G</td>
                                                                 </tr>
                                                                 <tr class="X3">
                                                                       <td></td>
                                                                       <td>
                                                                       <table id="menuPtosRedenSinRenovaAdenSap3GAct">
                                                                            <tr class="X3">
                                                                                  <td><input id="133" type="checkbox"
                                                                                        onclick="seleccionaPrivilegio(this);"></td>
                                                                                  <td>Actualizar</td>
                                                                            </tr>
                                                                       </table>
                                                                       </td>
                                                                 </tr>
                                                           </table>
                                                           </form>
                                                           </td>
                                                     </tr>
                                               </table>
                                               </td>
                                         </tr>
                                         <tr class="X3">
                                               <td><input id="64" type="checkbox"
                                                     onclick="selectAll(this,'menuPtosRedenCancel');"></td>
                                               <td>Cancelación</td>
                                         </tr>
                                         <tr class="X3">
                                               <td></td>
                                               <td>
                                               <form action="" id="menuPtosRedenCancel">
                                               <table>
                                                     <tr class="X3">
                                                           <td><input id="90" type="checkbox"
                                                                 onclick="selectAllFromTable(this,'menuPtosRedenCancelAceptar');"></td>
                                                           <td>Aceptar</td>
                                                     </tr>
                                                     <tr class="X3">
                                                           <td></td>
                                                           <td>
                                                           <table id="menuPtosRedenCancelAceptar">
                                                                 <tr class="X3">
                                                                       <td><input id="148" type="checkbox"
                                                                             onclick="seleccionaPrivilegio(this);"></td>
                                                                       <td>Realiza Cancelación</td>
                                                                 </tr>
                                                                 <tr class="X3">
                                                                       <td><input id="109" type="checkbox"
                                                                             onclick="seleccionaPrivilegio(this);"></td>
                                                                       <td>Periodo 180 días para realizar cancelación</td>
                                                                 </tr>
                                                                 <tr class="X3">
                                                                       <td><input id="110" type="checkbox"
                                                                             onclick="seleccionaPrivilegio(this);"></td>
                                                                       <td>Periodo 60 días para realizar cancelación</td>
                                                                 </tr>
                                                           </table>
                                                           </td>
                                                     </tr>
                                               </table>
                                               </form>
                                               </td>
                                         </tr>
                                   </table>
                                   </td>
                             </tr>
                             <tr class="X3">
                                   <td><input id="24" type="checkbox"
                                         onclick="seleccionaPrivilegio(this);"></td>
                                   <td>Alianzas</td>
                             </tr>
                             <tr class="X3">
                                   <td></td>
                                   <td>
                                   <table>
                                         <tr class="X3">
                                               <td><input id="65" type="checkbox"
                                                     onclick="seleccionaPrivilegio(this);"></td>
                                               <td>Todas las opciones del menú</td>
                                         </tr>
                                         <tr class="X3">
                                               <td><input id="66" type="checkbox"
                                                     onclick="seleccionaPrivilegio(this);"></td>
                                               <td>Histórico</td>
                                         </tr>
                                         <tr class="X3">
                                               <td><input id="67" type="checkbox"
                                                     onclick="seleccionaPrivilegio(this);"></td>
                                               <td>Alta/Cambio</td>
                                         </tr>
                                         <tr class="X3">
                                               <td></td>
                                               <td>
                                               <table>
                                                     <tr class="X3">
                                                           <td><input id="91" type="checkbox"
                                                                 onclick="selectAllFromTable(this,'menuPtosAlianzaAltaCambioAmeExp');"></td>
                                                           <td>American Express</td>
                                                     </tr>
                                                     <tr class="X3">
                                                           <td></td>
                                                           <td>
                                                           <table id="menuPtosAlianzaAltaCambioAmeExp">
                                                                 <tr class="X3">
                                                                       <td><input id="111" type="checkbox"
                                                                             onclick="seleccionaPrivilegio(this);"></td>
                                                                       <td>Actualizar</td>
                                                                 </tr>
                                                                 <tr class="X3">
                                                                       <td><input id="112" type="checkbox"
                                                                             onclick="seleccionaPrivilegio(this);"></td>
                                                                       <td>Agregar</td>
                                                                 </tr>
                                                           </table>
                                                           </td>
                                                     </tr>
                                               </table>
                                               </td>
                                         </tr>
                                         <tr class="X3">
                                               <td><input id="68" type="checkbox"
                                                     onclick="seleccionaPrivilegio(this);"></td>
                                               <td>Cancelación</td>
                                         </tr>
                                         <tr class="X3">
                                               <td></td>
                                               <td>
                                               <table>
                                                     <tr class="X3">
                                                           <td><input id="92" type="checkbox"
                                                                 onclick="seleccionaPrivilegio(this);"></td>
                                                           <td>American Express</td>
                                                     </tr>
                                                     <tr class="X3">
                                                           <td></td>
                                                           <td>
                                                           <table>
                                                                 <tr class="X3">
                                                                       <td><input id="113" type="checkbox"
                                                                             onclick="seleccionaPrivilegio(this);"></td>
                                                                       <td>Buscar</td>
                                                                 </tr>
                                                                 <tr class="X3">
                                                                       <td></td>
                                                                       <td>
                                                                       <table>
                                                                            <tr class="X3">
                                                                                  <td><input id="134" type="checkbox"
                                                                                        onclick="seleccionaPrivilegio(this);"></td>
                                                                                  <td>Aceptar</td>
                                                                            </tr>
                                                                       </table>
                                                                      </td>
                                                                 </tr>
                                                           </table>
                                                           </td>
                                                     </tr>
                                               </table>
                                               </td>
                                         </tr>
                                         <tr class="X3">
                                               <td><input id="69" type="checkbox"
                                                     onclick="seleccionaPrivilegio(this);"></td>
                                               <td>Canjear</td>
                                         </tr>
                                         <tr class="X3">
                                               <td></td>
                                               <td>
                                               <table>
                                                     <tr class="X3">
                                                           <td><input id="93" type="checkbox"
                                                                 onclick="seleccionaPrivilegio(this);"></td>
                                                           <td>American Express</td>
                                                     </tr>
                                               </table>
                                               </td>
                                         </tr>
                                         <tr class="X3">
                                               <td><input id="70" type="checkbox"
                                                     onclick="seleccionaPrivilegio(this);"></td>
                                               <td>Liberar cupones Amex</td>
                                         </tr>
                                         <tr class="X3">
                                               <td></td>
                                               <td>
                                               <table>
                                                     <tr class="X3">
                                                           <td><input id="95" type="checkbox"
                                                                 onclick="seleccionaPrivilegio(this);"></td>
                                                           <td>Continuar</td>
                                                     </tr>
                                               </table>
                                               </td>
                                         </tr>
                                   </table>
                                   </td>
                             </tr>
                             <tr class="X3">
                                   <td><input id="25" type="checkbox"
                                         onclick="seleccionaPrivilegio(this);"></td>
                                   <td>Asignación</td>
                             </tr>
                             <tr class="X3">
                                   <td></td>
                                   <td>
                                   <table>
                                         <tr class="X3">
                                               <td><input id="71" type="checkbox"
                                                     onclick="seleccionaPrivilegio(this);"></td>
                                               <td>Asignar</td>
                                        </tr>
                                         <tr class="X3">
                                               <td></td>
                                               <td>
                                               <table>
                                                     <tr class="X3">
                                                           <td><input id="96" type="checkbox"
                                                                 onclick="seleccionaPrivilegio(this);"></td>
                                                           <td>Continuar</td>
                                                     </tr>
                                               </table>
                                               </td>
                                         </tr>
                                         <tr class="X3">
                                               <td><input id="72" type="checkbox"
                                                     onclick="seleccionaPrivilegio(this);"></td>
                                               <td>Eliminar</td>
                                         </tr>
                                         <tr class="X3">
                                               <td></td>
                                               <td>
                                               <table>
                                                     <tr class="X3">
                                                           <td><input id="97" type="checkbox"
                                                                 onclick="seleccionaPrivilegio(this);"></td>
                                                           <td>Continuar</td>
                                                     </tr>
                                               </table>
                                               </td>
                                         </tr>
                                         <tr class="X3">
                                               <td><input id="73" type="checkbox"
                                                     onclick="seleccionaPrivilegio(this);"></td>
                                               <td>Puntos por Antiguedad</td>
                                         </tr>
                                         <tr class="X3">
                                               <td></td>
                                               <td>
                                               <table>
                                                     <tr class="X3">
                                                           <td><input id="98" type="checkbox"
                                                                 onclick="seleccionaPrivilegio(this);"></td>
                                                           <td>Continuar</td>
                                                     </tr>
                                               </table>
                                               </td>
                                         </tr>
                                   </table>
                                   </td>
                             </tr>
                        </table>
                        </td>
                  </tr>             
                  <tr class="X3">
                        <td><input id="8" type="checkbox"
                             onclick="seleccionaPrivilegio(this);"></td>
                        <td>Imprimir</td>
                  </tr>
                  <tr class="X3">
                        <td></td>
                        <td>
                        <table>
                             <tr class="X3">
                                   <td><input id="27" type="checkbox"
                                         onclick="seleccionaPrivilegio(this);"></td>
                                   <td>Redención</td>
                             </tr>
                             <tr class="X3">
                                   <td></td>
                                   <td>
                                   <table>
                                         <tr class="X3">
                                               <td><input id="75" type="checkbox"
                                                     onclick="seleccionaPrivilegio(this);"></td>
                                               <td>Generar Archivo</td>
                                         </tr>
                                         <tr class="X3">
                                               <td><input id="76" type="checkbox"
                                                     onclick="seleccionaPrivilegio(this);"></td>
                                               <td>Imprimir</td>
                                         </tr>
                                   </table>
                                   </td>
                             </tr>
                             <tr class="X3">
                                   <td><input id="28" type="checkbox"
                                         onclick="seleccionaPrivilegio(this);"></td>
                                   <td>Alianza</td>
                             </tr>
                             <tr class="X3">
                                   <td></td>
                                   <td>
                                   <table>
                                         <tr class="X3">
                                               <td><input id="77" type="checkbox"
                                                     onclick="seleccionaPrivilegio(this);"></td>
                                               <td>Generar Archivo</td>
                                         </tr>
                                         <tr class="X3">
                                               <td><input id="78" type="checkbox"
                                                     onclick="seleccionaPrivilegio(this);"></td>
                                               <td>Imprimir</td>
                                         </tr>
                                   </table>
                                   </td>
                             </tr>
                        </table>
                        </td>
                  </tr>
                  <tr class="X3">
                        <td><input id="9" type="checkbox"
                             onclick="seleccionaPrivilegio(this);"></td>
                        <td>Renuncia</td>
                  </tr>
                  <tr class="X3">
                        <td></td>
                        <td>
                        <table>
                             <tr class="X3">
                                   <td><input id="29" type="checkbox"
                                         onclick="seleccionaPrivilegio(this);"></td>
                                   <td>Reactivar Puntos</td>
                             </tr>
                             <tr class="X3">
                                   <td><input id="30" type="checkbox"
                                         onclick="seleccionaPrivilegio(this);"></td>
                                   <td>Constancia de Renuncia</td>
                             </tr>
                        </table>
                        </td>
                  </tr>
                  <tr class="X3">
                        <td><input id="10" type="checkbox"
                             onclick="seleccionaPrivilegio(this);"></td>
                        <td>Transferencia</td>
                  </tr>
                  <tr class="X3">
                        <td></td>
                        <td>
                        <table width="318">
                             <tr class="X3">
                                   <td><input id="31" type="checkbox"
                                         onclick="seleccionaPrivilegio(this);"></td>
                                   <td>Transferencia para cambio de Región</td>
                             </tr>
                             <tr class="X3">
                                   <td></td>
                                   <td>
                                   <table>
                                         <tr class="X3">
                                               <td><input id="79" type="checkbox"
                                                     onclick="seleccionaPrivilegio(this);"></td>
                                               <td>Transferir</td>
                                         </tr>
                                   </table>
                                   </td>
                             </tr>
                             <tr class="X3">
                                   <td><input id="32" type="checkbox"
                                         onclick="seleccionaPrivilegio(this);"></td>
                                   <td>Transferencia para Plan Anexo</td>
                             </tr>
                             <tr class="X3">
                                   <td></td>
                                   <td>
                                   <table>
                                         <tr class="X3">
                                               <td><input id="80" type="checkbox"
                                                     onclick="seleccionaPrivilegio(this);"></td>
                                               <td>Transferir</td>
                                         </tr>
                                   </table>
                                   </td>
                             </tr>
                             <tr class="X3">
                                   <td><input id="160" type="checkbox"
                                         onclick="seleccionaPrivilegio(this);"></td>
                                   <td>Transferencia Cliente Excelente</td>
                             </tr>
                             <tr class="X3">
                                   <td></td>
                                   <td>
                                   <table>
                                         <tr class="X3">
                                               <td><input id="161" type="checkbox"
                                                     onclick="seleccionaPrivilegio(this);"></td>
                                               <td>Transferir</td>
                                         </tr>
                                   </table>
                                   </td>
                             </tr>
                             <tr class="X3">
                                   <td></td>
                                   <td>
                                   <table width="271">
                                         <tr class="X3">
                                               <td width="21"></td>
                                               <td width="64">
                                                     <table width="195">
                                                           <tr class="X3"">
                                                                 <td width="22"><input id="162" type="checkbox"
                                                                             onclick="seleccionaPrivilegio(this);"></td>
                                                                 <td width="158">
                                                                       Más de 90 días, regionales</td>
                                                           </tr>
                                                     </table>
                                               </td>
                                         </tr>
                                   </table>
                                   </td>
                             </tr>
                        </table>
                        </td>
                  </tr>
                  <tr class="X3">
                        <td><input id="11" type="checkbox"
                             onclick="seleccionaPrivilegio(this);"></td>
                        <td>Retención</td>
                  </tr>
                  <tr class="X3">
                        <td></td>
                        <td>
                        <table>
                             <tr>
                                   <td><input id="33" type="checkbox"
                                         onclick="seleccionaPrivilegio(this);"></td>
                                   <td>Captura Cuenta Anterior</td>
                             </tr>
                             <tr>
                                   <td><input id="34" type="checkbox"
                                         onclick="seleccionaPrivilegio(this);"></td>
                                   <td>Generar Certificado de Lealtad</td>
                             </tr>
                             <tr>
                                   <td><input id="35" type="checkbox"
                                         onclick="seleccionaPrivilegio(this);"></td>
                                   <td>Consultar Certificado de Lealtad</td>
                             </tr>
                             <tr>
                                   <td><input id="36" type="checkbox"
                                         onclick="seleccionaPrivilegio(this);"></td>
                                   <td>Imprimir Certificado de Lealtad</td>
                             </tr>
                             <tr>
                                   <td><input id="37" type="checkbox"
                                         onclick="seleccionaPrivilegio(this);"></td>
                                   <td>Cancelar Certificado de Lealtad</td>
                             </tr>
                        </table>
                        </td>
                  </tr>
                  <tr class="X3">
                        <td><input id="12" type="checkbox"
                             onclick="seleccionaPrivilegio(this);"></td>
                        <td>Manuales y Promociones</td>
                  </tr>
            </table>
            </div>
            </td>
            <td width="550px" valign="top">
            <h4 class="grupoPrivilegios">Menú Inbursa</h4>
            <div class="panel">

            <table>
                  <tr class="X3">
                        <td><input id="190" type="checkbox"
                             onclick="seleccionaPrivilegio(this);"></td>
                        <td>Menú Inbursa</td>
                  </tr>
                  <tr class="X3">
                        <td><input id="191" type="checkbox"
                             onclick="selectAll(this,'menuInbursa');"></td>
                        <td>Consulta Inbursa</td>
                  </tr>
                  <tr class="X3">
                        <td></td>
                        <td>
                        <form action="" id="menuInbursa">
                        <table>
                             <tr class="X3">
                                   <td><input id="192" type="checkbox"
                                         onclick="seleccionaPrivilegio(this);"></td>
                                   <td>Consultar</td>
                             </tr>
                        </table>
                        </form>
                        </td>
                  </tr>
            </table>
            </div>
            </td>
      </tr>
<tr>
      <td width="550px" valign="top">
      <h4 class="grupoPrivilegios">Menú Soporte</h4>
      <div class="panel">
            <table>
                  <tr class="X3">
                        <td><input id="2" type="checkbox"
                             onclick="seleccionaPrivilegio(this);"></td>
                        <td>Menú Soporte</td>
                  </tr>
                  <tr class="X3">
                        <td><input id="13" type="checkbox"
                             onclick="selectAll(this,'menuSoportePuntitos');"></td>
                        <td>Consulta de Puntitos</td>
                  </tr>
                  <tr class="X3">
                        <td></td>
                        <td>
                        <form action="" id="menuSoportePuntitos">
                        <table>
                             <tr class="X3">
                                   <td><input id="38" type="checkbox"
                                         onclick="seleccionaPrivilegio(this);"></td>
                                   <td>Buscar</td>
                             </tr>
                             <tr class="X3">
                                   <td><input id="39" type="checkbox"
                                         onclick="seleccionaPrivilegio(this);"></td>
                                   <td>Cancelar</td>
                             </tr>
                        </table>
                        </form>
                        </td>
                  </tr>
                  <tr class="X3">
                        <td><input id="14" type="checkbox"
                             onclick="selectAll(this,'menuSoporteUsuarios');"></td>
                        <td>Usuarios</td>
                  </tr>
                  <tr class="X3">
                        <td></td>
                        <td>
                        <form action="" id="menuSoporteUsuarios">
                        <table>
                             <tr class="X3">
                                   <td><input id="40" type="checkbox"
                                         onclick="seleccionaPrivilegio(this);"></td>
                                   <td>Buscar</td>
                             </tr>
                             <tr class="X3">
                                   <td><input id="41" type="checkbox"
                                         onclick="seleccionaPrivilegio(this);"></td>
                                   <td>Agregar</td>
                             </tr>
                             <tr class="X3">
                                   <td><input id="42" type="checkbox"
                                         onclick="seleccionaPrivilegio(this);"></td>
                                   <td>Resetea</td>
                             </tr>
                             <tr class="X3">
                                   <td><input id="43" type="checkbox"
                                         onclick="seleccionaPrivilegio(this);"></td>
                                   <td>Actualizar</td>
                             </tr>
                             <tr class="X3">
                                   <td><input id="44" type="checkbox"
                                         onclick="seleccionaPrivilegio(this);"></td>
                                   <td>Eliminar</td>
                             </tr>
                             <tr class="X3">
                                   <td><input id="45" type="checkbox"
                                         onclick="seleccionaPrivilegio(this);"></td>
                                   <td>Limpiar</td>
                             </tr>
                        </table>
                        </form>
                        </td>
                  </tr>
                  <tr class="X3">
                        <td><input id="172" type="checkbox"
                             onclick="selectAll(this,'menuSoporteLineasRestringidas');"></td>
                        <td>Lineas Restringidas</td>
                  </tr>
                  <tr class="X3">
                        <td></td>
                        <td>
                        <form action="" id="menuSoporteLineasRestringidas">
                        <table>
                             <tr class="X3">
                                   <td><input id="173" type="checkbox"
                                         onclick="seleccionaPrivilegio(this);"></td>
                                   <td>Procesar</td>
                             </tr>
                             <tr class="X3">
                                   <td><input id="186" type="checkbox"
                                         onclick="seleccionaPrivilegio(this);"></td>
                                   <td>Descargar</td>
                             </tr>
                        </table>
                        </form>
                        </td>
                  </tr>
            </table>
            </div>
            </td>
            <td width="550px" valign="top">
            <h4 class="grupoPrivilegios">Menú Administración</h4>
            <div class="panel">
            <table id="menuAdmon">
                  <tr class="X3">
                        <td><input id="3" type="checkbox"
                             onclick="seleccionaPrivilegio(this);"></td>
                        <td>Menú Administración</td>
                  </tr>
                  <tr class="X3">
                        <td><input id="15" type="checkbox"
                             onclick="selectAll(this,'menuAdmonAvisos')"></td>
                        <td>Avisos</td>
                  </tr>
                  <tr class="X3">
                        <td></td>
                        <td>
                        <form action="" id="menuAdmonAvisos">
                        <table>
                             <tr class="X3">
                                   <td><input id="46" type="checkbox"
                                         onclick="seleccionaPrivilegio(this);"></td>
                                   <td>Consultar</td>
                             </tr>
                             <tr class="X3">
                                   <td><input id="47" type="checkbox"
                                         onclick="seleccionaPrivilegio(this);"></td>
                                   <td>Eliminar</td>
                             </tr>
                             <tr class="X3">
                                   <td><input id="48" type="checkbox"
                                         onclick="seleccionaPrivilegio(this);"></td>
                                   <td>Agregar</td>
                             </tr>
                        </table>
                        </form>
                        </td>
                  </tr>
                  <tr class="X3">
                        <td><input id="16" type="checkbox"
                             onclick="selectAll(this,'menuAdmonPtosVenta');"></td>
                        <td>Puntos de Venta</td>
                  </tr>
                  <tr class="X3">
                        <td></td>
                        <td>
                        <form action="" id="menuAdmonPtosVenta">
                        <table>
                             <tr class="X3">
                                   <td><input id="49" type="checkbox"
                                         onclick="seleccionaPrivilegio(this);"></td>
                                   <td>Consultar</td>
                             </tr>
                             <tr class="X3">
                                   <td><input id="50" type="checkbox"
                                         onclick="seleccionaPrivilegio(this);"></td>
                                   <td>Agregar</td>
                             </tr>
                             <tr class="X3">
                                   <td><input id="51" type="checkbox"
                                         onclick="seleccionaPrivilegio(this);"></td>
                                   <td>Actualizar</td>
                             </tr>
                             <tr class="X3">
                                   <td><input id="52" type="checkbox"
                                         onclick="seleccionaPrivilegio(this);"></td>
                                   <td>Eliminar</td>
                             </tr>
                        </table>
                        </form>
                        </td>
                  </tr>
                  <tr class="X3">
                        <td><input id="17" type="checkbox"
                             onclick="selectAll(this,'menuAdministracionPromociones');"></td>
                        <td>Promociones</td>
                  </tr>
                  <tr class="X3">
                        <td></td>
                        <td>
                        <form action="" id="menuAdministracionPromociones">
                        <table>
                             <tr class="X3">
                                   <td><input id="53" type="checkbox"
                                         onclick="selectAllFromTable(this,'menuAdmonPromocionesAltaDoctos');"></td>
                                   <td>Alta de documentos</td>
                             </tr>
                             <tr class="X3">
                                   <td></td>
                                   <td>
                                   <table id="menuAdmonPromocionesAltaDoctos">
                                         <tr class="X3">
                                               <td><input id="81" type="checkbox"
                                                     onclick="seleccionaPrivilegio(this);"></td>
                                               <td>Adjuntar</td>
                                         </tr>
                                         <tr class="X3">
                                               <td><input id="82" type="checkbox"
                                                     onclick="seleccionaPrivilegio(this);"></td>
                                               <td>Eliminar</td>
                                         </tr>
                                   </table>
                                   </td>
                             </tr>
                             <tr class="X3">
                                   <td><input id="54" type="checkbox"
                                         onclick="selectAllFromTable(this,'menuAdmonPromoConsultas');"></td>
                                   <td>Consultas</td>
                             </tr>
                             <tr class="X3">
                                   <td></td>
                                   <td>
                                   <table id="menuAdmonPromoConsultas">
                                         <tr class="X3">
                                               <td><input id="83" type="checkbox"
                                                     onclick="seleccionaPrivilegio(this);"></td>
                                               <td>Consulta</td>
                                         </tr>
                                         <tr class="X3">
                                               <td><input id="159" type="checkbox"
                                                     onclick="seleccionaPrivilegio(this);"></td>
                                               <td>Actualizar</td>
                                         </tr>
                                         <tr class="X3">
                                               <td><input id="84" type="checkbox"
                                                     onclick="seleccionaPrivilegio(this);"></td>
                                               <td>Exportar</td>
                                         </tr>
                                   </table>
                                   </td>
                             </tr>
                             <tr class="X3">
                                   <td><input id="55" type="checkbox"
                                         onclick="selectAllFromTable(this,'menuAdmonPromoProcesaCat');"></td>
                                   <td>Procesa Catálogos</td>
                             </tr>
                             <tr class="X3">
                                   <td></td>
                                   <td>
                                   <table id="menuAdmonPromoProcesaCat">
                                         <tr class="X3">
                                               <td><input id="85" type="checkbox"
                                                     onclick="seleccionaPrivilegio(this);"></td>
                                               <td>Procesar</td>
                                         </tr>
                                   </table>
                                   </td>
                             </tr>
                             <tr class="X3">
                                <td><input id="193" type="checkbox"
                                         onclick="selectAllFromTable(this,'cargaMasivaFTP');"></td>
                                <td>Carga Masiva FTP</td>
                             </tr>
                                   <tr class="X3">
                                      <td></td>
                                      <td>
                                      <table id="cargaMasivaFTP">
                                         <tr class="X3">
                                                  <td><input id="194" type="checkbox"
                                                           onclick="seleccionaPrivilegio(this);"></td>
                                                  <td>Enviar</td>
                                         </tr>
                                         </table>
                                   </td>
                             </tr>
                             <tr class="X3">
                                   <td><input id="157" type="checkbox"
                                         onclick="selectAllFromTable(this,'menuAdmonPromoProcesaCat');"></td>
                                   <td>Distribuidores</td>
                             </tr>
                             <tr class="X3">
                                   <td></td>
                                   <td>
                                   <table id="menuAdmonPromoProcesaCat">
                                         <tr class="X3">
                                               <td><input id="158" type="checkbox"
                                                     onclick="seleccionaPrivilegio(this);"></td>
                                               <td>Consulta</td>
                                         </tr>
                                   </table>
                                   </td>
                             </tr>
                             <tr class="X3">
                                   <td><input id="169" type="checkbox" onclick="selectAllFromTable(this,'menuAdmonPromoProcesaCat');"></td>
                                   <td>Reporte Membresías</td>
                             </tr>
                             <tr class="X3">
                                   <td></td>
                                   <td>
                                         <table id="menuAdmonPromoProcesaCat">
                                               <tr class="X3">
                                                     <td><input id="170" type="checkbox" onclick="seleccionaPrivilegio(this);"></td>
                                                     <td>Procesar</td>
                                               </tr>                                                            
                                         </table>
                                   </td>                                                                  
                             </tr>
                        </table>
                        </form>
                        </td>
                  </tr>
                  <tr class="X3">
                        <td><input id="166" type="checkbox"
                             onclick="selectAll(this,'menuAdministracionReportes');"></td>
                        <td>Reportes</td>
                  </tr>
                  <tr class="X3">
                        <td></td>
                        <td>
                        <form action="" id="menuAdministracionReportes">
                        <table>
                             <tr class="X3">
                                   <td><input id="167" type="checkbox"></td>
                                   <td>Exportar</td>
                             </tr>
                        </table>
                        </form>
                        </td>
                  </tr>
                  
                  
                  
                  
                  <!-- MÓDULO ASIGNACIÓN DE PUNTOS - JAPA 11/02/2013 Folio 121733 -->
                  <tr class="X3">
                        <td><input id="174" type="checkbox"
                             onclick="selectAll(this,'menuAdmonPuntos');"></td>
                        <td>Puntos</td>
                  </tr>
                  <tr class="X3">
                        <td></td>
                        <td>
                        <form action="" id="menuAdmonPuntos">
                        <table>
                             <tr class="X3">
                                   <td><input id="175" type="checkbox"
                                         onclick="seleccionaPrivilegio(this);"></td>
                                   <td>Asignacion</td>
                             </tr>
                             <tr class="X3">
                                   <td></td>
                                   <td>
                                   <table>
                                         <tr class="X3">
                                               <td><input id="176" type="checkbox"
                                                     onclick="selectAllFromTable(this,'menuAdmonPuntosPerfilesValidos');"></td>
                                               <td>Perfiles Validos</td>
                                         </tr>
                                         <tr class="X3">
                                               <td></td>
                                               <td>
                                               <table id="menuAdmonPuntosPerfilesValidos">
                                                     <tr class="X3">
                                                           <td><input id="177" type="checkbox" onclick="seleccionaPrivilegio(this);"></td>
                                                           <td>Consultar</td>
                                                     </tr>
                                                     <tr class="X3">
                                                           <td><input id="178" type="checkbox" onclick="seleccionaPrivilegio(this);"></td>
                                                           <td>Agregar</td>
                                                     </tr>
                        
                                                     <tr class="X3">
                                                           <td><input id="179" type="checkbox" onclick="seleccionaPrivilegio(this);"></td>
                                                           <td>Actualizar</td>
                                                     </tr>
                                                     <tr class="X3">
                                                           <td><input id="180" type="checkbox" onclick="seleccionaPrivilegio(this);"></td>
                                                           <td>Eliminar</td>
                                                     </tr>
                                               </table>
                                               </td>
                                         </tr>
                                         
                                         <tr class="X3">
                                               <td><input id="181" type="checkbox"
                                                     onclick="selectAllFromTable(this,'menuAdmonPuntosMotivosAsignacion');"></td>
                                               <td>Motivos de Asignación</td>
                                         </tr>
                                         <tr class="X3">
                                               <td></td>
                                               <td>
                                               <table id="menuAdmonPuntosMotivosAsignacion">
                                                     <tr class="X3">
                                                           <td><input id="182" type="checkbox" onclick="seleccionaPrivilegio(this);"></td>
                                                           <td>Consultar</td>
                                                     </tr>
                                                     <tr class="X3">
                                                           <td><input id="183" type="checkbox" onclick="seleccionaPrivilegio(this);"></td>
                                                           <td>Agregar</td>
                                                     </tr>
                        
                                                     <tr class="X3">
                                                           <td><input id="184" type="checkbox" onclick="seleccionaPrivilegio(this);"></td>
                                                           <td>Actualizar</td>
                                                     </tr>
                                                     <tr class="X3">
                                                           <td><input id="185" type="checkbox" onclick="seleccionaPrivilegio(this);"></td>
                                                           <td>Eliminar</td>
                                                     </tr>
                                               </table>
                                               </td>
                                         </tr>
                                         
                                         <tr class="X3">
                                               <td><input id="187" type="checkbox"
                                                     onclick="selectAllFromTable(this,'menuAdmonPuntosLineasPrueba');"></td>
                                               <td>Líneas de Prueba</td>
                                         </tr>
                                         <tr class="X3">
                                               <td></td>
                                               <td>
                                               <table id="menuAdmonPuntosLineasPrueba">
                                                     <tr class="X3">
                                                           <td><input id="188" type="checkbox" onclick="seleccionaPrivilegio(this);"></td>
                                                           <td>Agregar</td>
                                                     </tr>
                                                     <tr class="X3">
                                                           <td><input id="189" type="checkbox" onclick="seleccionaPrivilegio(this);"></td>
                                                           <td>Eliminar</td>
                                                     </tr>
                                               </table>
                                               </td>
                                         </tr>
                                         
                                   </table>
                                   </td>
                             </tr>
                        </table>
                        </form>
                        </td>
                  </tr>
                  <tr class="X3">
                        <td><input id="171" type="checkbox"
                             onclick="selectAll(this,'menuAdmonReportesFinanzas')"></td>
                        <td>Reportes Finanzas</td>
                  </tr>
                  <tr class="X3">
                        <td></td>
                        <td>
                        <form action="" id="menuAdmonReportesFinanzas">
                        <table>
                             <tr class="X3">
                                      <td><input id="195" type="checkbox"
                                               onclick="seleccionaPrivilegio(this);"></td>
                                      <td>Generar</td>
                             </tr>
                             <tr class="X3">
                                      <td><input id="196" type="checkbox"
                                               onclick="seleccionaPrivilegio(this);"></td>
                                      <td>Exportar</td>
                             </tr>
                        </table>
                        </form>
                        </td>
                  </tr>
            </table>
            </div>
            </td>
            <tr>
            <td width="550px" valign="top">
            <h4 class="grupoPrivilegios">Menú Seguridad</h4>
            <div class="panel">
            <table>
                  <tr class="X3">
                        <td><input id="4" type="checkbox"
                             onclick="seleccionaPrivilegio(this);"></td>
                        <td>Menú Seguridad</td>
                  </tr>
                  <tr class="X3">
                        <td><input id="18" type="checkbox"
                             onclick="selectAll(this,'menuSeguridadUsuarios');"></td>
                        <td>Usuarios</td>
                  </tr>
                  <tr class="X3">
                        <td></td>
                        <td>
                        <form id="menuSeguridadUsuarios">
                        <table>
                             <tr class="X3">
                                   <td><input id="56" type="checkbox"
                                         onclick="seleccionaPrivilegio(this);"></td>
                                   <td>Consultar</td>
                             </tr>
                             <tr class="X3">
                                   <td><input id="57" type="checkbox"
                                         onclick="seleccionaPrivilegio(this);"></td>
                                   <td>Exportar</td>
                             </tr>
                        </table>
                        </form>
                        </td>
                  </tr>
                  <tr class="X3">
                        <td><input id="19" type="checkbox"
                             onclick="selectAll(this,'menuSeguridadPerfiles');"></td>
                        <td>Perfiles</td>
                  </tr>
                  <tr class="X3">
                        <td></td>
                        <td>
                        <form id="menuSeguridadPerfiles">
                        <table>
                             <tr class="X3">
                                   <td><input id="58" type="checkbox"
                                         onclick="seleccionaPrivilegio(this);"></td>
                                   <td>Consultar</td>
                             </tr>
                             <tr class="X3">
                                   <td><input id="59" type="checkbox"
                                         onclick="seleccionaPrivilegio(this);"></td>
                                   <td>Exportar PDF</td>
                             </tr>
                             <tr class="X3">
                                   <td><input id="150" type="checkbox"
                                         onclick="seleccionaPrivilegio(this);"></td>
                                   <td>Exportar Excel</td>
                             </tr>
                        </table>
                        </form>
                        </td>
                  </tr>
                  <tr class="X3">
                        <td><input id="149" type="checkbox"
                             onclick="selectAll(this,'menuSeguridadPrivilegios');"></td>
                        <td>Privilegios</td>
                  </tr>
                  <tr class="X3">
                        <td></td>
                        <td>
                        <form id="menuSeguridadPrivilegios">
                        <table>
                             <tr class="X3">
                                   <td><input id="151" type="checkbox"
                                         onclick="seleccionaPrivilegio(this);"></td>
                                   <td>Consultar</td>
                             </tr>
                             <tr class="X3">
                                   <td><input id="152" type="checkbox"
                                         onclick="seleccionaPrivilegio(this);"></td>
                                   <td>Actualizar</td>
                             </tr>
                        </table>
                        </form>
                        </td>
                  </tr>
            </table>
            </div>
            </td>
      </tr>
</table>
</body>
</html>
