/*    */ package com.claro.transfer.promociones;
/*    */ 
/*    */ public class CtesExcelentesTO extends ErrorTO
/*    */ {
/*    */   private String cuenta;
/*    */   private String linea;
/*    */   private String idRegion;
/*    */   private String estatus;
/*    */   
/*    */   public CtesExcelentesTO(String[] cteExc) {
/* 11 */     this.cuenta = cteExc[0];
/* 12 */     this.linea = cteExc[1];
/* 13 */     this.idRegion = cteExc[2];
/* 14 */     this.estatus = cteExc[3];
/*    */   }
/*    */   
/*    */   public CtesExcelentesTO() {}
/*    */   
/*    */   public String getCuenta() {
/* 20 */     return this.cuenta;
/*    */   }
/*    */   
/*    */   public void setCuenta(String cuenta) {
/* 24 */     this.cuenta = cuenta;
/*    */   }
/*    */   
/*    */   public String getLinea() {
/* 28 */     return this.linea;
/*    */   }
/*    */   
/*    */   public void setLinea(String linea) {
/* 32 */     this.linea = linea;
/*    */   }
/*    */   
/*    */   public String getIdRegion() {
/* 36 */     return this.idRegion;
/*    */   }
/*    */   
/*    */   public void setIdRegion(String idRegion) {
/* 40 */     this.idRegion = idRegion;
/*    */   }
/*    */   
/*    */   public String getEstatus() {
/* 44 */     return this.estatus;
/*    */   }
/*    */   
/*    */   public void setEstatus(String estatus) {
/* 48 */     this.estatus = estatus;
/*    */   }
/*    */   
/*    */   public String toString()
/*    */   {
/* 53 */     return 
/*    */     
/*    */ 
/* 56 */       this.cuenta + "," + this.linea + "," + this.idRegion + "," + this.estatus;
/*    */   }
/*    */ }


/* Location:              /opt/trabajo/claro/Guatemala/IS/CO/ClaroClub.ear!/ClaroClubEJBClient.jar!/com/claro/transfer/promociones/CtesExcelentesTO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */