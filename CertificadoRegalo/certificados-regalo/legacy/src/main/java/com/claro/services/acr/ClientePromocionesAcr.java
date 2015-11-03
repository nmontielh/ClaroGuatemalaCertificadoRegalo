/*    */ package com.claro.services.acr;
/*    */ 
/*    */ import com.claro.exception.CAException;
/*    */ import com.claro.transfer.gap.PromocionCaTO;
/*    */ import com.telcel.gscrm.dccrm.admin.promo.ws.AcrValidacionTO;
/*    */ import com.telcel.gscrm.dccrm.admin.promo.ws.AcrWebServiceProxy;
/*    */ import com.telcel.promociones.business.AplicaPromocionProxy;
/*    */ import com.telcel.promociones.to.PromAceptRech;
/*    */ import java.math.BigDecimal;
/*    */ import java.rmi.RemoteException;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ClientePromocionesAcr
/*    */ {
/*    */   public PromocionCaTO consultaPromocionACR(String endpoint, String telefono, int region, String marca, String modelo, String plan)
/*    */     throws CAException
/*    */   {
/* 24 */     PromocionCaTO promocionTO = null;
/* 25 */     ParserXML xml = new ParserXML();
/*    */     
/* 27 */     String xmlInput = "";
/*    */     try
/*    */     {
/* 30 */       xmlInput = xml.construyeMensaje(telefono, region, marca, modelo, plan);
/*    */       
/*    */ 
/* 33 */       AcrWebServiceProxy serviceProxy = new AcrWebServiceProxy();
/* 34 */       serviceProxy.setEndpoint(endpoint);
/* 35 */       AcrValidacionTO promocionAcrTO = serviceProxy.validaPromocionCirculoAzul(xmlInput);
/* 36 */       if (promocionAcrTO != null) {
/* 37 */         promocionTO = new PromocionCaTO();
/*    */         
/* 39 */         promocionTO.setIdCampania(promocionAcrTO.getIdCampania());
/* 40 */         promocionTO.setIdOferta(promocionAcrTO.getIdOferta());
/* 41 */         promocionTO.setNombrePromocion(promocionAcrTO.getNombre());
/* 42 */         promocionTO.setIdPromocionCA(promocionAcrTO.getIdConfiguracion());
/* 43 */         promocionTO.setCantidadDescuento(new BigDecimal(promocionAcrTO.getMontoPuntos()));
/* 44 */         promocionTO.setEquipos(promocionAcrTO.getEquipos());
/*    */       } else {
/* 46 */         throw new CAException(-1, "ClientePromocionesAcr.consultaPromocionACR:[No se han encontrado promociones disponibles]");
/*    */       }
/*    */     } catch (RemoteException e) {
/* 49 */       throw new CAException(-1, e.toString());
/*    */     } catch (Exception e) {
/* 51 */       throw new CAException(-1, e.toString());
/*    */     }
/* 53 */     return promocionTO;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public String notificaPromocionSiebel(String endPointConsulta, String endPointAplica, String telefono, int region, String marca, String modelo, String planNuevo, String usuario, String ip)
/*    */     throws CAException
/*    */   {
/* 65 */     ClientePromocionesAcr promosACR = new ClientePromocionesAcr();
/* 66 */     String xmlRespuesta = "";
/*    */     try {
/* 68 */       PromocionCaTO gapCaTO = promosACR.consultaPromocionACR(endPointConsulta, telefono, region, marca, modelo, planNuevo);
/*    */       
/*    */ 
/*    */ 
/* 72 */       PromAceptRech promocionTO = new PromAceptRech();
/* 73 */       promocionTO.setAplicaEP("");
/* 74 */       promocionTO.setCodigoMensaje("PromocionAceptada");
/* 75 */       promocionTO.setIdCampania(gapCaTO.getIdCampania());
/* 76 */       promocionTO.setIdOferta(gapCaTO.getIdOferta());
/* 77 */       promocionTO.setIdUsuario(usuario);
/* 78 */       promocionTO.setIp(ip);
/* 79 */       promocionTO.setNumeroTelefonico(telefono);
/* 80 */       promocionTO.setRazonRechazo("");
/*    */       
/*    */ 
/* 83 */       AplicaPromocionProxy serviceProxy = new AplicaPromocionProxy();
/* 84 */       serviceProxy.setEndpoint(endPointAplica);
/*    */       
/* 86 */       xmlRespuesta = serviceProxy.promocionAceptadaRechazada(promocionTO);
/*    */     } catch (RemoteException e) {
/* 88 */       throw new CAException(-1, e.toString());
/*    */     } catch (Exception e) {
/* 90 */       throw new CAException(-1, e.toString());
/*    */     }
/* 92 */     return xmlRespuesta;
/*    */   }
/*    */ }


/* Location:              /opt/trabajo/claro/Guatemala/IS/CO/ClaroClub.ear!/ClaroClubEJB.jar!/com/claro/services/acr/ClientePromocionesAcr.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */