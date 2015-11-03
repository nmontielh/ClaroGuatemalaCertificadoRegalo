/*    */ package com.claro.transfer;


/*    */ 
/*    */ 
/*    */ public class ReferenciaTO
/*    */   extends MensajeTO
/*    */ {
/*    */   private static final long serialVersionUID = -1113323878109805556L;
/*    */   private String idReferencia;
/*    */   private int valor;
/*    */   private long fechaModificacion;
/*    */   private String usuario;
/*    */   
/*    */   public String getIdReferencia()
/*    */   {
/* 15 */     return this.idReferencia;
/*    */   }
/*    */   
/* 18 */   public void setIdReferencia(String idReferencia) { this.idReferencia = idReferencia; }
/*    */   
/*    */   public int getValor() {
/* 21 */     return this.valor;
/*    */   }
/*    */   
/* 24 */   public void setValor(int valor) { this.valor = valor; }
/*    */   
/*    */   public long getFechaModificacion() {
/* 27 */     return this.fechaModificacion;
/*    */   }
/*    */   
/* 30 */   public void setFechaModificacion(long fechaModificacion) { this.fechaModificacion = fechaModificacion; }
/*    */   
/*    */   public String getUsuario() {
/* 33 */     return this.usuario;
/*    */   }
/*    */   
/* 36 */   public void setUsuario(String usuario) { this.usuario = usuario; }
/*    */ }


/* Location:              /opt/trabajo/claro/Guatemala/IS/CO/ClaroClub.ear!/ClaroClubEJBClient.jar!/com/claro/transfer/ReferenciaTO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */