/*    */ package com.claro.transfer;
/*    */ 
/*    */ import java.sql.Timestamp;
/*    */ 
/*    */ public class AsignaPorErrorTO
/*    */ {
/*    */   private String puntos;
/*    */   private Timestamp fechaOperacion;
/*    */   private String SM;
/*    */   private String secuencia;
/*    */   
/*    */   public String getPuntos() {
/* 13 */     return this.puntos;
/*    */   }
/*    */   
/* 16 */   public void setPuntos(String puntos) { this.puntos = puntos; }
/*    */   
/*    */   public Timestamp getFechaOperacion() {
/* 19 */     return this.fechaOperacion;
/*    */   }
/*    */   
/* 22 */   public void setFechaOperacion(Timestamp fechaOperacion) { this.fechaOperacion = fechaOperacion; }
/*    */   
/*    */   public String getSM() {
/* 25 */     return this.SM;
/*    */   }
/*    */   
/* 28 */   public void setSM(String sm) { this.SM = sm; }
/*    */   
/*    */   public String getSecuencia() {
/* 31 */     return this.secuencia;
/*    */   }
/*    */   
/* 34 */   public void setSecuencia(String secuencia) { this.secuencia = secuencia; }
/*    */ }


/* Location:              /opt/trabajo/claro/Guatemala/IS/CO/ClaroClub.ear!/ClaroClubEJBClient.jar!/com/claro/transfer/AsignaPorErrorTO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */