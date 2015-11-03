/*    */ package com.claro.services.gap;
/*    */ 
/*    */ import com.claro.exception.CAException;
/*    */ import com.telcel.sds.gccrm.dccrm.ws.gap.cliente.ConsultaPromocionesGap;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ClientePromocionesGap
/*    */ {
/*    */   public String consultaValoracionLinea(String endpoint, String xmlInput)
/*    */     throws CAException
/*    */   {
/* 23 */     String xmlOutput = "";
/* 24 */     ConsultaPromocionesGap consultaPromocionesGap = new ConsultaPromocionesGap();
/*    */     try {
/* 26 */       xmlOutput = consultaPromocionesGap.consultaInfoValoracionPromociones(endpoint, xmlInput);
/*    */     } catch (Exception e) {
/* 28 */       throw new CAException(-1, e.toString());
/*    */     }
/* 30 */     return xmlOutput;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public String aceptaPromocionCirculoAzul(String endpoint, String xmlMsg)
/*    */     throws CAException
/*    */   {
/* 39 */     String respuesta = "";
/*    */     try {
/* 41 */       ConsultaPromocionesGap consultaPromocionesGap = new ConsultaPromocionesGap();
/* 42 */       respuesta = consultaPromocionesGap.aceptaPromocionCirculoAzul(endpoint, xmlMsg);
/*    */     } catch (Exception e) {
/* 44 */       throw new CAException(-1, e.toString());
/*    */     }
/* 46 */     return respuesta;
/*    */   }
/*    */ }


/* Location:              /opt/trabajo/claro/Guatemala/IS/CO/ClaroClub.ear!/ClaroClubEJB.jar!/com/claro/services/gap/ClientePromocionesGap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */