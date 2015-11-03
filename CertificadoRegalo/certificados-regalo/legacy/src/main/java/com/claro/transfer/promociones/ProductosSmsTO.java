/*    */ package com.claro.transfer.promociones;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class ProductosSmsTO
/*    */   extends ErrorTO
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
/*    */   public ProductosSmsTO() {}
/*    */   
/*    */   public ProductosSmsTO(String[] productosSms)
/*    */   {
/* 22 */     this.claveM2k = productosSms[0];
/* 23 */     this.claveSms = productosSms[1];
/* 24 */     this.idProducto = productosSms[2];
/* 25 */     this.descripcion = productosSms[3];
/* 26 */     this.tipoProducto = productosSms[4];
/* 27 */     this.estatus = productosSms[5];
/* 28 */     this.valorPuntos = Integer.parseInt(productosSms[6]);
/*    */   }
/*    */   
/*    */   public String getLineaLog()
/*    */   {
/* 33 */     return 
/* 34 */       this.claveM2k + "@" + this.claveSms + "@" + this.idProducto + "@" + this.descripcion + "@" + this.tipoProducto + "@" + this.estatus + "@" + this.valorPuntos;
/*    */   }
/*    */   
/*    */   public String toString()
/*    */   {
/* 39 */     return 
/*    */     
/*    */ 
/* 42 */       this.claveM2k + "," + this.claveSms + "," + this.idProducto + "," + this.descripcion + "," + this.tipoProducto + "," + this.estatus + "," + this.valorPuntos;
/*    */   }
/*    */   
/*    */   public String getClaveM2k() {
/* 46 */     return this.claveM2k;
/*    */   }
/*    */   
/*    */   public void setClaveM2k(String claveM2k) {
/* 50 */     this.claveM2k = claveM2k;
/*    */   }
/*    */   
/*    */   public String getClaveSms() {
/* 54 */     return this.claveSms;
/*    */   }
/*    */   
/*    */   public void setClaveSms(String claveSms) {
/* 58 */     this.claveSms = claveSms;
/*    */   }
/*    */   
/*    */   public String getIdProducto() {
/* 62 */     return this.idProducto;
/*    */   }
/*    */   
/*    */   public void setIdProducto(String idProducto) {
/* 66 */     this.idProducto = idProducto;
/*    */   }
/*    */   
/*    */   public String getDescripcion() {
/* 70 */     return this.descripcion;
/*    */   }
/*    */   
/*    */   public void setDescripcion(String descripcion) {
/* 74 */     this.descripcion = descripcion;
/*    */   }
/*    */   
/*    */   public String getTipoProducto() {
/* 78 */     return this.tipoProducto;
/*    */   }
/*    */   
/*    */   public void setTipoProducto(String tipoProducto) {
/* 82 */     this.tipoProducto = tipoProducto;
/*    */   }
/*    */   
/*    */   public String getEstatus() {
/* 86 */     return this.estatus;
/*    */   }
/*    */   
/*    */   public void setEstatus(String estatus) {
/* 90 */     this.estatus = estatus;
/*    */   }
/*    */   
/*    */   public int getValorPuntos() {
/* 94 */     return this.valorPuntos;
/*    */   }
/*    */   
/*    */   public void setValorPuntos(int valorPuntos) {
/* 98 */     this.valorPuntos = valorPuntos;
/*    */   }
/*    */ }


/* Location:              /opt/trabajo/claro/Guatemala/IS/CO/ClaroClub.ear!/ClaroClubEJBClient.jar!/com/claro/transfer/promociones/ProductosSmsTO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */