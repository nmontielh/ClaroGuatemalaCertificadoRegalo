/*    */ package com.claro.transfer;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CatalogoTO
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = -2876315591050173260L;
/*    */   private String idVariable;
/*    */   private String descripcion;
/*    */   private String valor;
/*    */   
/*    */   public String getIdVariable()
/*    */   {
/* 17 */     return this.idVariable;
/*    */   }
/*    */   
/* 20 */   public void setIdVariable(String idVariable) { this.idVariable = idVariable; }
/*    */   
/*    */   public String getValor() {
/* 23 */     return this.valor;
/*    */   }
/*    */   
/* 26 */   public void setValor(String valor) { this.valor = valor; }
/*    */   
/*    */   public static long getSerialVersionUID() {
/* 29 */     return -2876315591050173260L;
/*    */   }
/*    */   
/*    */   public String getDescripcion() {
/* 33 */     return this.descripcion;
/*    */   }
/*    */   
/* 36 */   public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
/*    */ }


/* Location:              /opt/trabajo/claro/Guatemala/IS/CO/ClaroClub.ear!/ClaroClubEJBClient.jar!/com/claro/transfer/CatalogoTO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */