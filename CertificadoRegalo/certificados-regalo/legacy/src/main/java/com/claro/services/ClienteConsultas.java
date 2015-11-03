/*    */ package com.claro.services;
/*    */ 
/*    */ import com.claro.exception.CAException;
/*    */ import com.claro.services.acr.ParserXML;
/*    */ import com.telcel.gscrm.dccrm.wsm2k.ws.CRMWebServiceM2KProxy;
/*    */ import java.rmi.RemoteException;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ClienteConsultas
/*    */ {
/*    */   public int consultaRegionPorLinea(String endpoint, String telefono)
/*    */     throws CAException
/*    */   {
/* 18 */     ParserXML xml = new ParserXML();
/* 19 */     String xmlInput = "";
/*    */     try
/*    */     {
/* 22 */       xmlInput = xml.construyeMensajePeticionLinea(telefono);
/*    */       
/*    */ 
/* 25 */       CRMWebServiceM2KProxy serviceProxy = new CRMWebServiceM2KProxy();
/* 26 */       serviceProxy.setEndpoint(endpoint);
/* 27 */       String respuesta = serviceProxy.consultaRegionOrigen(xmlInput);
/* 28 */       return xml.parseaRespuesta(respuesta);
/*    */     }
/*    */     catch (RemoteException e) {
/* 31 */       throw new CAException(-1, e.toString());
/*    */     } catch (Exception e) {
/* 33 */       throw new CAException(-1, e.toString());
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public int consultaRegionPorCuenta(String endpoint, String cuenta)
/*    */     throws CAException
/*    */   {
/* 43 */     ParserXML xml = new ParserXML();
/*    */     
/* 45 */     String xmlInput = "";
/*    */     try
/*    */     {
/* 48 */       xmlInput = xml.construyeMensajePeticionCuenta(cuenta);
/*    */       
/*    */ 
/* 51 */       CRMWebServiceM2KProxy serviceProxy = new CRMWebServiceM2KProxy();
/* 52 */       serviceProxy.setEndpoint(endpoint);
/* 53 */       String respuesta = serviceProxy.consultaRegionOrigenPorCuenta(xmlInput);
/* 54 */       return xml.parseaRespuesta(respuesta);
/*    */     }
/*    */     catch (RemoteException e) {
/* 57 */       throw new CAException(-1, e.toString());
/*    */     } catch (Exception e) {
/* 59 */       throw new CAException(-1, e.toString());
/*    */     }
/*    */   }
/*    */ }


/* Location:              /opt/trabajo/claro/Guatemala/IS/CO/ClaroClub.ear!/ClaroClubEJB.jar!/com/claro/services/ClienteConsultas.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */