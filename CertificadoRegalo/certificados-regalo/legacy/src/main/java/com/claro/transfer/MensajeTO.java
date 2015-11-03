/*    */ package com.claro.transfer;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MensajeTO
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 8809627781369251976L;
/*    */   private int idMensaje;
/*    */   private String mensaje;
/*    */   
/*    */   public MensajeTO(int idMensaje, String mensaje)
/*    */   {
/* 21 */     this.idMensaje = idMensaje;
/* 22 */     this.mensaje = mensaje; }
/*    */   
/*    */   public MensajeTO() {}
/*    */   
/* 26 */   public int getIdMensaje() { return this.idMensaje; }
/*    */   
/*    */   public void setIdMensaje(int idMensaje) {
/* 29 */     this.idMensaje = idMensaje;
/*    */   }
/*    */   
/* 32 */   public String getMensaje() { return this.mensaje; }
/*    */   
/*    */   public void setMensaje(String mensaje) {
/* 35 */     this.mensaje = mensaje;
/*    */   }
/*    */   
/*    */   public void agregaMensaje(int idMensaje, String mensaje) {
/* 39 */     this.idMensaje = idMensaje;
/* 40 */     this.mensaje = mensaje;
/*    */   }
/*    */   
/* 43 */   public MensajeTO obtieneMensajeTO() { return new MensajeTO(this.idMensaje, this.mensaje); }
/*    */ }


/* Location:              /opt/trabajo/claro/Guatemala/IS/CO/ClaroClub.ear!/ClaroClubEJBClient.jar!/com/claro/transfer/MensajeTO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */