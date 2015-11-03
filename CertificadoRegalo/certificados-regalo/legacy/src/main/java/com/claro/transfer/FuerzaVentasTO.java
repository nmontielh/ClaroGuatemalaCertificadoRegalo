/*    */ package com.claro.transfer;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class FuerzaVentasTO
/*    */   extends MensajeTO
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = -859845217988431256L;
/*    */   private String idFuerzaVenta;
/*    */   private String planVisible;
/*    */   private String estatus;
/*    */   private String descripcion;
/*    */   
/*    */   public String getIdFuerzaVenta()
/*    */   {
/* 17 */     return this.idFuerzaVenta;
/*    */   }
/*    */   
/* 20 */   public void setIdFuerzaVenta(String idFuerzaVenta) { this.idFuerzaVenta = idFuerzaVenta; }
/*    */   
/*    */   public String getPlanVisible() {
/* 23 */     return this.planVisible;
/*    */   }
/*    */   
/* 26 */   public void setPlanVisible(String planVisible) { this.planVisible = planVisible; }
/*    */   
/*    */   public String getEstatus() {
/* 29 */     return this.estatus;
/*    */   }
/*    */   
/* 32 */   public void setEstatus(String estatus) { this.estatus = estatus; }
/*    */   
/*    */   public String getDescripcion() {
/* 35 */     return this.descripcion;
/*    */   }
/*    */   
/* 38 */   public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
/*    */ }


/* Location:              /opt/trabajo/claro/Guatemala/IS/CO/ClaroClub.ear!/ClaroClubEJBClient.jar!/com/claro/transfer/FuerzaVentasTO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */