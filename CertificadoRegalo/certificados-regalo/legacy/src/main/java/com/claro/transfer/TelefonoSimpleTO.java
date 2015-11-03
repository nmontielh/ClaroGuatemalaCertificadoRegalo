/*    */ package com.claro.transfer;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TelefonoSimpleTO
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 5737837005783709525L;
/*    */   private String linea;
/*    */   private int secuencia;
/*    */   private int region;
/*    */   private String cuenta;
/*    */   
/*    */   public int getRegion()
/*    */   {
/* 20 */     return this.region;
/*    */   }
/*    */   
/* 23 */   public void setRegion(int region) { this.region = region; }
/*    */   
/*    */   public String getLinea() {
/* 26 */     return this.linea;
/*    */   }
/*    */   
/* 29 */   public void setLinea(String linea) { this.linea = linea; }
/*    */   
/*    */   public int getSecuencia() {
/* 32 */     return this.secuencia;
/*    */   }
/*    */   
/* 35 */   public void setSecuencia(int secuencia) { this.secuencia = secuencia; }
/*    */   
/*    */   public String getCuenta() {
/* 38 */     return this.cuenta;
/*    */   }
/*    */   
/* 41 */   public void setCuenta(String cuenta) { this.cuenta = cuenta; }
/*    */ }


/* Location:              /opt/trabajo/claro/Guatemala/IS/CO/ClaroClub.ear!/ClaroClubEJBClient.jar!/com/claro/transfer/TelefonoSimpleTO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */