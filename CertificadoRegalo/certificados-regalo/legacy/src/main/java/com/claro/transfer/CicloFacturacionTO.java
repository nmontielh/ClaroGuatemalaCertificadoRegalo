/*    */ package com.claro.transfer;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.Date;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CicloFacturacionTO
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = -8959872228626683654L;
/*    */   private int cicloFac;
/*    */   private int idRegion;
/*    */   private Date fechaCorte;
/*    */   private String descripcion;
/*    */   
/*    */   public int getCicloFac()
/*    */   {
/* 19 */     return this.cicloFac;
/*    */   }
/*    */   
/* 22 */   public void setCicloFac(int cicloFac) { this.cicloFac = cicloFac; }
/*    */   
/*    */   public int getIdRegion() {
/* 25 */     return this.idRegion;
/*    */   }
/*    */   
/* 28 */   public void setIdRegion(int idRegion) { this.idRegion = idRegion; }
/*    */   
/*    */   public Date getFechaCorte() {
/* 31 */     return this.fechaCorte;
/*    */   }
/*    */   
/* 34 */   public void setFechaCorte(Date fechaCorte) { this.fechaCorte = fechaCorte; }
/*    */   
/*    */   public String getDescripcion() {
/* 37 */     return this.descripcion;
/*    */   }
/*    */   
/* 40 */   public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
/*    */ }


/* Location:              /opt/trabajo/claro/Guatemala/IS/CO/ClaroClub.ear!/ClaroClubEJBClient.jar!/com/claro/transfer/CicloFacturacionTO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */