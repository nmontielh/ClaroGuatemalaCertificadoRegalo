/*    */ package com.claro.transfer;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.sql.Timestamp;
/*    */ 
/*    */ public class AvisosTO
/*    */   extends MensajeTO implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private int idAviso;
/*    */   private String idUsuario;
/*    */   private String descripcion;
/*    */   private Timestamp fechaAlta;
/*    */   private Timestamp fechaActivacion;
/*    */   private Timestamp fechaExpiracion;
/*    */   private String tipoAviso;
/*    */   private String tipoMsg;
/*    */   private String estatus;
/*    */   private Timestamp fechaModificacion;
/*    */   private String idUsuarioMod;
/*    */   private String formatoDescripcion;
/*    */   
/*    */   public int getIdAviso()
/*    */   {
/* 25 */     return this.idAviso;
/*    */   }
/*    */   
/* 28 */   public void setIdAviso(int idAviso) { this.idAviso = idAviso; }
/*    */   
/*    */   public String getIdUsuario() {
/* 31 */     return this.idUsuario;
/*    */   }
/*    */   
/* 34 */   public void setIdUsuario(String idUsuario) { this.idUsuario = idUsuario; }
/*    */   
/*    */   public String getDescripcion() {
/* 37 */     return this.descripcion;
/*    */   }
/*    */   
/* 40 */   public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
/*    */   
/*    */   public Timestamp getFechaAlta() {
/* 43 */     return this.fechaAlta;
/*    */   }
/*    */   
/* 46 */   public void setFechaAlta(Timestamp fechaAlta) { this.fechaAlta = fechaAlta; }
/*    */   
/*    */   public Timestamp getFechaActivacion() {
/* 49 */     return this.fechaActivacion;
/*    */   }
/*    */   
/* 52 */   public void setFechaActivacion(Timestamp fechaActivacion) { this.fechaActivacion = fechaActivacion; }
/*    */   
/*    */   public Timestamp getFechaExpiracion() {
/* 55 */     return this.fechaExpiracion;
/*    */   }
/*    */   
/* 58 */   public void setFechaExpiracion(Timestamp fechaExpiracion) { this.fechaExpiracion = fechaExpiracion; }
/*    */   
/*    */   public String getTipoAviso() {
/* 61 */     return this.tipoAviso;
/*    */   }
/*    */   
/* 64 */   public void setTipoAviso(String tipoAviso) { this.tipoAviso = tipoAviso; }
/*    */   
/*    */   public String getTipoMsg() {
/* 67 */     return this.tipoMsg;
/*    */   }
/*    */   
/* 70 */   public void setTipoMsg(String tipoMsg) { this.tipoMsg = tipoMsg; }
/*    */   
/*    */   public String getEstatus() {
/* 73 */     return this.estatus;
/*    */   }
/*    */   
/* 76 */   public void setEstatus(String estatus) { this.estatus = estatus; }
/*    */   
/*    */   public Timestamp getFechaModificacion() {
/* 79 */     return this.fechaModificacion;
/*    */   }
/*    */   
/* 82 */   public void setFechaModificacion(Timestamp fechaModificacion) { this.fechaModificacion = fechaModificacion; }
/*    */   
/*    */   public String getIdUsuarioMod() {
/* 85 */     return this.idUsuarioMod;
/*    */   }
/*    */   
/* 88 */   public void setIdUsuarioMod(String idUsuarioMod) { this.idUsuarioMod = idUsuarioMod; }
/*    */   
/*    */   public String getFormatoDescripcion() {
/* 91 */     return this.formatoDescripcion;
/*    */   }
/*    */   
/* 94 */   public void setFormatoDescripcion(String formatoDescripcion) { this.formatoDescripcion = formatoDescripcion; }
/*    */ }


/* Location:              /opt/trabajo/claro/Guatemala/IS/CO/ClaroClub.ear!/ClaroClubEJBClient.jar!/com/claro/transfer/AvisosTO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */