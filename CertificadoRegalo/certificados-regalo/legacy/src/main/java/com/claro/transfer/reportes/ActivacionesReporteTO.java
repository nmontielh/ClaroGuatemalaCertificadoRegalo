/*    */ package com.claro.transfer.reportes;
/*    */ 
/*    */ public class ActivacionesReporteTO implements Reportable
/*    */ {
/*    */   private String region;
/*    */   private String tecnologia;
/*    */   private String totalRegion;
/*    */   private String totalPuntos;
/*    */   
/*    */   public String getRegion() {
/* 11 */     return this.region;
/*    */   }
/*    */   
/* 14 */   public void setRegion(String region) { this.region = region; }
/*    */   
/*    */   public String getTecnologia() {
/* 17 */     return this.tecnologia;
/*    */   }
/*    */   
/* 20 */   public void setTecnologia(String tecnologia) { this.tecnologia = tecnologia; }
/*    */   
/*    */   public String getTotalRegion() {
/* 23 */     return this.totalRegion;
/*    */   }
/*    */   
/* 26 */   public void setTotalRegion(String totalRegion) { this.totalRegion = totalRegion; }
/*    */   
/*    */   public String getTotalPuntos() {
/* 29 */     return this.totalPuntos;
/*    */   }
/*    */   
/* 32 */   public void setTotalPuntos(String totalPuntos) { this.totalPuntos = totalPuntos; }
/*    */ }


/* Location:              /opt/trabajo/claro/Guatemala/IS/CO/ClaroClub.ear!/ClaroClubEJBClient.jar!/com/claro/transfer/reportes/ActivacionesReporteTO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */