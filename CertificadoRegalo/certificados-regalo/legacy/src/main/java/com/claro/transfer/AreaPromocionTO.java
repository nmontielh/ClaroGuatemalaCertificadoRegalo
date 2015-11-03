/*    */ package com.claro.transfer;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class AreaPromocionTO implements Serializable {
/*    */   private static final long serialVersionUID = -1470664204782238908L;
/*    */   private int idAreaPromocion;
/*    */   private String descAreaPromocion;
/*    */   private String estatus;
/*    */   
/*    */   public int getIdAreaPromocion() {
/* 12 */     return this.idAreaPromocion;
/*    */   }
/*    */   
/* 15 */   public void setIdAreaPromocion(int idAreaPromocion) { this.idAreaPromocion = idAreaPromocion; }
/*    */   
/*    */   public String getDescAreaPromocion() {
/* 18 */     return this.descAreaPromocion;
/*    */   }
/*    */   
/* 21 */   public void setDescAreaPromocion(String descAreaPromocion) { this.descAreaPromocion = descAreaPromocion; }
/*    */   
/*    */   public String getEstatus() {
/* 24 */     return this.estatus;
/*    */   }
/*    */   
/* 27 */   public void setEstatus(String estatus) { this.estatus = estatus; }
/*    */ }


/* Location:              /opt/trabajo/claro/Guatemala/IS/CO/ClaroClub.ear!/ClaroClubEJBClient.jar!/com/claro/transfer/AreaPromocionTO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */