/*    */ package com.claro.transfer.reportes;
/*    */ 
/*    */ public class PuntosVencerTO
/*    */ {
/*    */   private String cuenta;
/*    */   private int secuencia;
/*    */   private String linea;
/*    */   private int region;
/*    */   private String fechaCad;
/*    */   private int puntosaCad;
/*    */   private int puntosaCad1;
/*    */   private int puntosaCad2;
/*    */   private int puntosAntiguedad;
/*    */   private int puntosExcedentes;
/*    */   private int puntosPromocion;
/*    */   private int puntosRenta;
/*    */   
/*    */   public String getCuenta() {
/* 19 */     return this.cuenta;
/*    */   }
/*    */   
/* 22 */   public void setCuenta(String cuenta) { this.cuenta = cuenta; }
/*    */   
/*    */   public int getSecuencia() {
/* 25 */     return this.secuencia;
/*    */   }
/*    */   
/* 28 */   public void setSecuencia(int secuencia) { this.secuencia = secuencia; }
/*    */   
/*    */   public String getLinea() {
/* 31 */     return this.linea;
/*    */   }
/*    */   
/* 34 */   public void setLinea(String linea) { this.linea = linea; }
/*    */   
/*    */   public int getRegion() {
/* 37 */     return this.region;
/*    */   }
/*    */   
/* 40 */   public void setRegion(int region) { this.region = region; }
/*    */   
/*    */   public String getFechaCad() {
/* 43 */     return this.fechaCad;
/*    */   }
/*    */   
/* 46 */   public void setFechaCad(String fechaCad) { this.fechaCad = fechaCad; }
/*    */   
/*    */   public int getPuntosaCad() {
/* 49 */     return this.puntosaCad;
/*    */   }
/*    */   
/* 52 */   public void setPuntosaCad(int puntosaCad) { this.puntosaCad = puntosaCad; }
/*    */   
/*    */   public int getPuntosaCad1() {
/* 55 */     return this.puntosaCad1;
/*    */   }
/*    */   
/* 58 */   public void setPuntosaCad1(int puntosaCad1) { this.puntosaCad1 = puntosaCad1; }
/*    */   
/*    */   public int getPuntosaCad2() {
/* 61 */     return this.puntosaCad2;
/*    */   }
/*    */   
/* 64 */   public void setPuntosaCad2(int puntosaCad2) { this.puntosaCad2 = puntosaCad2; }
/*    */   
/*    */   public int getPuntosAntiguedad() {
/* 67 */     return this.puntosAntiguedad;
/*    */   }
/*    */   
/* 70 */   public void setPuntosAntiguedad(int puntosAntiguedad) { this.puntosAntiguedad = puntosAntiguedad; }
/*    */   
/*    */   public int getPuntosExcedentes() {
/* 73 */     return this.puntosExcedentes;
/*    */   }
/*    */   
/* 76 */   public void setPuntosExcedentes(int puntosExcedentes) { this.puntosExcedentes = puntosExcedentes; }
/*    */   
/*    */   public int getPuntosPromocion() {
/* 79 */     return this.puntosPromocion;
/*    */   }
/*    */   
/* 82 */   public void setPuntosPromocion(int puntosPromocion) { this.puntosPromocion = puntosPromocion; }
/*    */   
/*    */   public int getPuntosRenta() {
/* 85 */     return this.puntosRenta;
/*    */   }
/*    */   
/* 88 */   public void setPuntosRenta(int puntosRenta) { this.puntosRenta = puntosRenta; }
/*    */   
/*    */   public int getTotales()
/*    */   {
/* 92 */     return 
/* 93 */       this.puntosaCad + this.puntosaCad1 + this.puntosaCad2 + 
/* 94 */       this.puntosAntiguedad + this.puntosExcedentes + 
/* 95 */       this.puntosPromocion + this.puntosRenta;
/*    */   }
/*    */ }


/* Location:              /opt/trabajo/claro/Guatemala/IS/CO/ClaroClub.ear!/ClaroClubEJBClient.jar!/com/claro/transfer/reportes/PuntosVencerTO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */