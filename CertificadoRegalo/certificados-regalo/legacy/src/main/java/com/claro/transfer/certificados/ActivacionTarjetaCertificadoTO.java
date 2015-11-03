/*    */ package com.claro.transfer.certificados;
/*    */ 
/*    */ public class ActivacionTarjetaCertificadoTO
/*    */ {
/*    */   private String numeroCertificado;
/*    */   private String numeroTarjeta;
/*    */   private long montoCertificado;
/*    */   private String fechaActivacion;
/*    */   private String fechaExpiracion;
/*    */   private String estatus;
/*    */   private String idMensaje;
/*    */   private String mensaje;
/*    */   
/*    */   public String getNumeroCertificado()
/*    */   {
/* 16 */     return this.numeroCertificado;
/*    */   }
/*    */   
/* 19 */   public void setNumeroCertificado(String numeroCertificado) { this.numeroCertificado = numeroCertificado; }
/*    */   
/*    */   public String getNumeroTarjeta() {
/* 22 */     return this.numeroTarjeta;
/*    */   }
/*    */   
/* 25 */   public void setNumeroTarjeta(String numeroTarjeta) { this.numeroTarjeta = numeroTarjeta; }
/*    */   
/*    */   public long getMontoCertificado() {
/* 28 */     return this.montoCertificado;
/*    */   }
/*    */   
/* 31 */   public void setMontoCertificado(long montoCertificado) { this.montoCertificado = montoCertificado; }
/*    */   
/*    */   public String getFechaActivacion() {
/* 34 */     return this.fechaActivacion;
/*    */   }
/*    */   
/* 37 */   public void setFechaActivacion(String fechaActivacion) { this.fechaActivacion = fechaActivacion; }
/*    */   
/*    */   public String getFechaExpiracion() {
/* 40 */     return this.fechaExpiracion;
/*    */   }
/*    */   
/* 43 */   public void setFechaExpiracion(String fechaExpiracion) { this.fechaExpiracion = fechaExpiracion; }
/*    */   
/*    */   public String getEstatus() {
/* 46 */     return this.estatus;
/*    */   }
/*    */   
/* 49 */   public void setEstatus(String estatus) { this.estatus = estatus; }
/*    */   
/*    */   public String getIdMensaje() {
/* 52 */     return this.idMensaje;
/*    */   }
/*    */   
/* 55 */   public void setIdMensaje(String idMensaje) { this.idMensaje = idMensaje; }
/*    */   
/*    */   public String getMensaje() {
/* 58 */     return this.mensaje;
/*    */   }
/*    */   
/* 61 */   public void setMensaje(String mensaje) { this.mensaje = mensaje; }
/*    */ }


/* Location:              /opt/trabajo/claro/Guatemala/IS/CO/ClaroClub.ear!/ClaroClubEJBClient.jar!/com/claro/transfer/certificados/ActivacionTarjetaCertificadoTO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */