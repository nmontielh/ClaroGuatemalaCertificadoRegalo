/*    */ package com.claro.transfer;
/*    */ 
/*    */ import java.math.BigDecimal;
/*    */ 
/*    */ public class FacturaTO
/*    */ {
/*    */   private double monto;
/*    */   private BigDecimal fechaFactura;
/*    */   
/*    */   public double getMonto()
/*    */   {
/* 12 */     return this.monto;
/*    */   }
/*    */   
/* 15 */   public void setMonto(double monto) { this.monto = monto; }
/*    */   
/*    */   public BigDecimal getFechaFactura() {
/* 18 */     return this.fechaFactura;
/*    */   }
/*    */   
/* 21 */   public void setFechaFactura(BigDecimal fechaFactura) { this.fechaFactura = fechaFactura; }
/*    */ }


/* Location:              /opt/trabajo/claro/Guatemala/IS/CO/ClaroClub.ear!/ClaroClubEJBClient.jar!/com/claro/transfer/FacturaTO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */