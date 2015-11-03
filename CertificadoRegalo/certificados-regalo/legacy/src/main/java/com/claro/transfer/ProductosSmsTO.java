/*    */ package com.claro.transfer;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ProductosSmsTO
/*    */   extends MensajeTO
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String claveM2k;
/*    */   private String claveSms;
/*    */   private String idProducto;
/*    */   private String descripcion;
/*    */   private String tipoProducto;
/*    */   private String estatus;
/*    */   private int valorPuntos;
/*    */   
/*    */   public String getClaveM2k()
/*    */   {
/* 24 */     return this.claveM2k;
/*    */   }
/*    */   
/* 27 */   public void setClaveM2k(String claveM2k) { this.claveM2k = claveM2k; }
/*    */   
/*    */   public String getClaveSms() {
/* 30 */     return this.claveSms;
/*    */   }
/*    */   
/* 33 */   public void setClaveSms(String claveSms) { this.claveSms = claveSms; }
/*    */   
/*    */   public String getIdProducto() {
/* 36 */     return this.idProducto;
/*    */   }
/*    */   
/* 39 */   public void setIdProducto(String idProducto) { this.idProducto = idProducto; }
/*    */   
/*    */   public String getDescripcion() {
/* 42 */     return this.descripcion;
/*    */   }
/*    */   
/* 45 */   public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
/*    */   
/*    */   public String getTipoProducto() {
/* 48 */     return this.tipoProducto;
/*    */   }
/*    */   
/* 51 */   public void setTipoProducto(String tipoProducto) { this.tipoProducto = tipoProducto; }
/*    */   
/*    */   public String getEstatus() {
/* 54 */     return this.estatus;
/*    */   }
/*    */   
/* 57 */   public void setEstatus(String estatus) { this.estatus = estatus; }
/*    */   
/*    */   public int getValorPuntos() {
/* 60 */     return this.valorPuntos;
/*    */   }
/*    */   
/* 63 */   public void setValorPuntos(int valorPuntos) { this.valorPuntos = valorPuntos; }
/*    */ }


/* Location:              /opt/trabajo/claro/Guatemala/IS/CO/ClaroClub.ear!/ClaroClubEJBClient.jar!/com/claro/transfer/ProductosSmsTO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */