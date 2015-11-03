/*    */ package com.claro.transfer.promociones;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class GrupoTO
/*    */   extends ErrorTO
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 4855634203558484800L;
/*    */   private String idGrupoPromocion;
/*    */   private String tipoPromocion;
/*    */   private String estatus;
/*    */   private String grupoPromocion;
/*    */   private String descuento;
/*    */   private String descuentoValorAlto;
/*    */   
/*    */   public GrupoTO() {}
/*    */   
/*    */   public GrupoTO(String[] grupo)
/*    */   {
/* 21 */     this.idGrupoPromocion = grupo[0];
/* 22 */     this.tipoPromocion = grupo[1];
/* 23 */     this.estatus = grupo[2];
/* 24 */     this.grupoPromocion = grupo[3];
/* 25 */     this.descuento = grupo[4];
/* 26 */     this.descuentoValorAlto = grupo[5];
/*    */   }
/*    */   
/*    */   public String getEstatus() {
/* 30 */     return this.estatus;
/*    */   }
/*    */   
/*    */   public void setEstatus(String estatus) {
/* 34 */     this.estatus = estatus;
/*    */   }
/*    */   
/*    */   public String getIdGrupoPromocion() {
/* 38 */     return this.idGrupoPromocion;
/*    */   }
/*    */   
/*    */   public void setIdGrupoPromocion(String idGrupoPromocion) {
/* 42 */     this.idGrupoPromocion = idGrupoPromocion;
/*    */   }
/*    */   
/*    */   public String getDescuento() {
/* 46 */     return this.descuento;
/*    */   }
/*    */   
/*    */   public void setDescuento(String descuento) {
/* 50 */     this.descuento = descuento;
/*    */   }
/*    */   
/*    */   public String getTipoPromocion() {
/* 54 */     return this.tipoPromocion;
/*    */   }
/*    */   
/*    */   public void setTipoPromocion(String tipoPromocion) {
/* 58 */     this.tipoPromocion = tipoPromocion;
/*    */   }
/*    */   
/*    */   public String getGrupoPromocion() {
/* 62 */     return this.grupoPromocion;
/*    */   }
/*    */   
/*    */   public void setGrupoPromocion(String grupoPromocion) {
/* 66 */     this.grupoPromocion = grupoPromocion;
/*    */   }
/*    */   
/*    */   public String getDescuentoValorAlto() {
/* 70 */     return this.descuentoValorAlto;
/*    */   }
/*    */   
/*    */   public void setDescuentoValorAlto(String descuentoValorAlto) {
/* 74 */     this.descuentoValorAlto = descuentoValorAlto;
/*    */   }
/*    */   
/*    */   public String toString()
/*    */   {
/* 79 */     return 
/*    */     
/*    */ 
/* 82 */       this.idGrupoPromocion + "," + this.tipoPromocion + "," + this.estatus + "," + this.grupoPromocion + "," + this.descuento + "," + this.descuentoValorAlto;
/*    */   }
/*    */   
/*    */   public String getLineaLog() {
/* 86 */     StringBuffer buffer = new StringBuffer();
/* 87 */     buffer.append(this.idGrupoPromocion).append("@");
/* 88 */     buffer.append(this.tipoPromocion).append("@");
/* 89 */     buffer.append(this.estatus).append("@");
/* 90 */     buffer.append(this.grupoPromocion).append("@");
/* 91 */     buffer.append(this.descuento).append("@");
/* 92 */     buffer.append(this.descuentoValorAlto);
/* 93 */     return buffer.toString();
/*    */   }
/*    */ }


/* Location:              /opt/trabajo/claro/Guatemala/IS/CO/ClaroClub.ear!/ClaroClubEJBClient.jar!/com/claro/transfer/promociones/GrupoTO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */