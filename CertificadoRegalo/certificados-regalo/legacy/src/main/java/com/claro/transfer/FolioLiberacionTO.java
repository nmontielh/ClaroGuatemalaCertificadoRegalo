/*    */ package com.claro.transfer;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class FolioLiberacionTO
/*    */   extends MensajeTO implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = -8627320171353134978L;
/*    */   private String folio;
/*    */   
/*    */   public String getFolio()
/*    */   {
/* 13 */     return this.folio;
/*    */   }
/*    */   
/*    */   public void setFolio(String folio) {
/* 17 */     this.folio = folio;
/*    */   }
/*    */ }


/* Location:              /opt/trabajo/claro/Guatemala/IS/CO/ClaroClub.ear!/ClaroClubEJBClient.jar!/com/claro/transfer/FolioLiberacionTO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */