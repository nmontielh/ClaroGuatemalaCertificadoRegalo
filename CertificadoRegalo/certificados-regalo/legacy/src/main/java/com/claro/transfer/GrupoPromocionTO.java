/*    */ package com.claro.transfer;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ 
/*    */ public class GrupoPromocionTO
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = -814566303199471298L;
/*    */   private int idGrupoPromocion;
/*    */   private String tipoPromocion;
/*    */   private String estatus;
/*    */   private String grupoPromocion;
/*    */   private int descuento;
/*    */   private int descuentoValorAlto;
/*    */   
/*    */   public int getIdGrupoPromocion()
/*    */   {
/* 19 */     return this.idGrupoPromocion;
/*    */   }
/*    */   
/* 22 */   public void setIdGrupoPromocion(int idGrupoPromocion) { this.idGrupoPromocion = idGrupoPromocion; }
/*    */   
/*    */   public String getTipoPromocion() {
/* 25 */     return this.tipoPromocion;
/*    */   }
/*    */   
/* 28 */   public void setTipoPromocion(String tipoPromocion) { this.tipoPromocion = tipoPromocion; }
/*    */   
/*    */   public String getEstatus() {
/* 31 */     return this.estatus;
/*    */   }
/*    */   
/* 34 */   public void setEstatus(String estatus) { this.estatus = estatus; }
/*    */   
/*    */   public String getGrupoPromocion() {
/* 37 */     return this.grupoPromocion;
/*    */   }
/*    */   
/* 40 */   public void setGrupoPromocion(String grupoPromocion) { this.grupoPromocion = grupoPromocion; }
/*    */   
/*    */   public int getDescuentoValorAlto() {
/* 43 */     return this.descuentoValorAlto;
/*    */   }
/*    */   
/* 46 */   public void setDescuentoValorAlto(int descuentoValorAlto) { this.descuentoValorAlto = descuentoValorAlto; }
/*    */   
/*    */   public int getDescuento() {
/* 49 */     return this.descuento;
/*    */   }
/*    */   
/* 52 */   public void setDescuento(int descuento) { this.descuento = descuento; }
/*    */   
/*    */   public String getLineaLog() {
/* 55 */     StringBuffer buffer = new StringBuffer();
/* 56 */     buffer.append(this.idGrupoPromocion).append("@");
/* 57 */     buffer.append(this.tipoPromocion).append("@");
/* 58 */     buffer.append(this.estatus).append("@");
/* 59 */     buffer.append(this.grupoPromocion).append("@");
/* 60 */     buffer.append(this.descuento).append("@");
/* 61 */     buffer.append(this.descuentoValorAlto);
/* 62 */     return buffer.toString();
/*    */   }
/*    */ }


/* Location:              /opt/trabajo/claro/Guatemala/IS/CO/ClaroClub.ear!/ClaroClubEJBClient.jar!/com/claro/transfer/GrupoPromocionTO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */