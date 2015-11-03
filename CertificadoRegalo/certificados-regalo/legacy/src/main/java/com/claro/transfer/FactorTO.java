/*    */ package com.claro.transfer;
/*    */ 
/*    */ import java.util.Date;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FactorTO
/*    */   extends MensajeTO
/*    */ {
/*    */   private static final long serialVersionUID = -4971336931931456206L;
/*    */   private int idcuenta;
/*    */   private int factor;
/*    */   private Date fechaActiva;
/*    */   private String estatus;
/*    */   private int millanMin;
/*    */   private int millasPosibles;
/*    */   
/*    */   public int getMillasPosibles(int ptsDisponibles)
/*    */   {
/* 20 */     int nPtosDisp = ptsDisponibles / this.millanMin * this.millanMin;
/*    */     
/*    */ 
/* 23 */     if ((nPtosDisp > 0) && (this.factor > 0))
/*    */     {
/* 25 */       int millas = (int)Math.floor(nPtosDisp / this.factor + 0.5D);
/*    */       
/* 27 */       return millas;
/*    */     }
/* 29 */     return this.millasPosibles;
/*    */   }
/*    */   
/*    */   public int getIdcuenta() {
/* 33 */     return this.idcuenta;
/*    */   }
/*    */   
/* 36 */   public void setIdcuenta(int idcuenta) { this.idcuenta = idcuenta; }
/*    */   
/*    */   public int getFactor() {
/* 39 */     return this.factor;
/*    */   }
/*    */   
/* 42 */   public void setFactor(int factor) { this.factor = factor; }
/*    */   
/*    */   public Date getFechaActiva() {
/* 45 */     return this.fechaActiva;
/*    */   }
/*    */   
/* 48 */   public void setFechaActiva(Date fechaActiva) { this.fechaActiva = fechaActiva; }
/*    */   
/*    */   public String getEstatus() {
/* 51 */     return this.estatus;
/*    */   }
/*    */   
/* 54 */   public void setEstatus(String estatus) { this.estatus = estatus; }
/*    */   
/*    */   public int getMillanMin() {
/* 57 */     return this.millanMin;
/*    */   }
/*    */   
/* 60 */   public void setMillanMin(int millanMin) { this.millanMin = millanMin; }
/*    */ }


/* Location:              /opt/trabajo/claro/Guatemala/IS/CO/ClaroClub.ear!/ClaroClubEJBClient.jar!/com/claro/transfer/FactorTO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */