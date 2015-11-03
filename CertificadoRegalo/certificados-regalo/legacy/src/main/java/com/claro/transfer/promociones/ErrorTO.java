/*    */ package com.claro.transfer.promociones;
/*    */ 
/*    */ public class ErrorTO
/*    */ {
/*    */   private int idError;
/*    */   private String mensaje;
/*    */   
/*    */   public int getIdError() {
/*  9 */     return this.idError;
/*    */   }
/*    */   
/* 12 */   public void setIdError(int idError) { this.idError = idError; }
/*    */   
/*    */   public String getMensaje() {
/* 15 */     return this.mensaje;
/*    */   }
/*    */   
/* 18 */   public void setMensaje(String mensaje) { this.mensaje = mensaje; }
/*    */   
/*    */   public void setAgregaIdMensaje(int idError, String mensaje)
/*    */   {
/* 22 */     this.mensaje = mensaje;
/* 23 */     this.idError = idError;
/*    */   }
/*    */ }


/* Location:              /opt/trabajo/claro/Guatemala/IS/CO/ClaroClub.ear!/ClaroClubEJBClient.jar!/com/claro/transfer/promociones/ErrorTO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */