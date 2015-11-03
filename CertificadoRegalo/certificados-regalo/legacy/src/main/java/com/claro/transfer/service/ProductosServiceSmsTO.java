/*    */ package com.claro.transfer.service;
/*    */ 
/*    */ import com.claro.transfer.ProductosSmsTO;
/*    */ import java.io.Serializable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ProductosServiceSmsTO
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
/*    */   public ProductosServiceSmsTO() {}
/*    */   
/*    */   public ProductosServiceSmsTO(ProductosSmsTO productosSmsTO)
/*    */   {
/* 26 */     this.claveM2k = productosSmsTO.getClaveM2k();
/* 27 */     this.claveSms = productosSmsTO.getClaveSms();
/* 28 */     this.idProducto = productosSmsTO.getIdProducto();
/* 29 */     this.descripcion = productosSmsTO.getDescripcion();
/* 30 */     this.tipoProducto = productosSmsTO.getTipoProducto();
/* 31 */     this.estatus = productosSmsTO.getEstatus();
/* 32 */     this.valorPuntos = productosSmsTO.getValorPuntos();
/*    */   }
/*    */   
/*    */   public String getClaveM2k()
/*    */   {
/* 37 */     return this.claveM2k;
/*    */   }
/*    */   
/* 40 */   public void setClaveM2k(String claveM2k) { this.claveM2k = claveM2k; }
/*    */   
/*    */   public String getClaveSms() {
/* 43 */     return this.claveSms;
/*    */   }
/*    */   
/* 46 */   public void setClaveSms(String claveSms) { this.claveSms = claveSms; }
/*    */   
/*    */   public String getIdProducto() {
/* 49 */     return this.idProducto;
/*    */   }
/*    */   
/* 52 */   public void setIdProducto(String idProducto) { this.idProducto = idProducto; }
/*    */   
/*    */   public String getDescripcion() {
/* 55 */     return this.descripcion;
/*    */   }
/*    */   
/* 58 */   public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
/*    */   
/*    */   public String getTipoProducto() {
/* 61 */     return this.tipoProducto;
/*    */   }
/*    */   
/* 64 */   public void setTipoProducto(String tipoProducto) { this.tipoProducto = tipoProducto; }
/*    */   
/*    */   public String getEstatus() {
/* 67 */     return this.estatus;
/*    */   }
/*    */   
/* 70 */   public void setEstatus(String estatus) { this.estatus = estatus; }
/*    */   
/*    */   public int getValorPuntos() {
/* 73 */     return this.valorPuntos;
/*    */   }
/*    */   
/* 76 */   public void setValorPuntos(int valorPuntos) { this.valorPuntos = valorPuntos; }
/*    */ }


/* Location:              /opt/trabajo/claro/Guatemala/IS/CO/ClaroClub.ear!/ClaroClubEJBClient.jar!/com/claro/transfer/service/ProductosServiceSmsTO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */