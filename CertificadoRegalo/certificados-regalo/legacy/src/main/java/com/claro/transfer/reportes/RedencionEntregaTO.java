/*    */ package com.claro.transfer.reportes;
/*    */ 
/*    */ public class RedencionEntregaTO implements Reportable
/*    */ {
/*    */   private String medioEntrega;
/*    */   private String total;
/*    */   
/*    */   public String getMedioEntrega() {
/*  9 */     return this.medioEntrega;
/*    */   }
/*    */   
/* 12 */   public void setMedioEntrega(String medioEntrega) { this.medioEntrega = medioEntrega; }
/*    */   
/*    */   public String getTotal() {
/* 15 */     return this.total;
/*    */   }
/*    */   
/* 18 */   public void setTotal(String total) { this.total = total; }
/*    */ }


/* Location:              /opt/trabajo/claro/Guatemala/IS/CO/ClaroClub.ear!/ClaroClubEJBClient.jar!/com/claro/transfer/reportes/RedencionEntregaTO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */