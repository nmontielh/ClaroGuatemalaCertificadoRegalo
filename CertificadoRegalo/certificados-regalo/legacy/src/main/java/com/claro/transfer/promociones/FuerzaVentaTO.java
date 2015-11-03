/*    */ package com.claro.transfer.promociones;
/*    */ 
/*    */ public class FuerzaVentaTO extends ErrorTO
/*    */ {
/*    */   private String idFuerzaVenta;
/*    */   private String planVisible;
/*    */   private String estatus;
/*    */   private String descripcion;
/*    */   
/*    */   public FuerzaVentaTO(String[] fza) {
/* 11 */     this.idFuerzaVenta = fza[0];
/* 12 */     this.planVisible = fza[1];
/* 13 */     this.estatus = fza[2];
/* 14 */     this.descripcion = fza[3];
/*    */   }
/*    */   
/*    */   public FuerzaVentaTO() {}
/*    */   
/*    */   public String getIdFuerzaVenta() {
/* 20 */     return this.idFuerzaVenta;
/*    */   }
/*    */   
/* 23 */   public void setIdFuerzaVenta(String idFuerzaVenta) { this.idFuerzaVenta = idFuerzaVenta; }
/*    */   
/*    */   public String getPlanVisible() {
/* 26 */     return this.planVisible;
/*    */   }
/*    */   
/* 29 */   public void setPlanVisible(String planVisible) { this.planVisible = planVisible; }
/*    */   
/*    */   public String getEstatus() {
/* 32 */     return this.estatus;
/*    */   }
/*    */   
/* 35 */   public void setEstatus(String estatus) { this.estatus = estatus; }
/*    */   
/*    */   public String getDescripcion() {
/* 38 */     return this.descripcion;
/*    */   }
/*    */   
/* 41 */   public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
/*    */   
/*    */ 
/*    */   public String toString()
/*    */   {
/* 46 */     return 
/*    */     
/*    */ 
/* 49 */       this.idFuerzaVenta + "," + this.planVisible + "," + this.estatus + "," + this.descripcion;
/*    */   }
/*    */ }


/* Location:              /opt/trabajo/claro/Guatemala/IS/CO/ClaroClub.ear!/ClaroClubEJBClient.jar!/com/claro/transfer/promociones/FuerzaVentaTO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */