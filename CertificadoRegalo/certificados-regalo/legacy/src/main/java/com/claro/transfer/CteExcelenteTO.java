/*    */ package com.claro.transfer;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.Date;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CteExcelenteTO
/*    */   extends MensajeTO
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private Integer ID;
/*    */   private String cuenta;
/*    */   private String linea;
/*    */   private Integer idRegion;
/*    */   private String accion;
/*    */   private String idUsuario;
/*    */   private Date fecha;
/*    */   private String estatus;
/*    */   
/*    */   public Integer getID()
/*    */   {
/* 26 */     return this.ID;
/*    */   }
/*    */   
/* 29 */   public void setID(Integer id) { this.ID = id; }
/*    */   
/*    */   public String getCuenta() {
/* 32 */     return this.cuenta;
/*    */   }
/*    */   
/* 35 */   public void setCuenta(String cuenta) { this.cuenta = cuenta; }
/*    */   
/*    */   public String getLinea() {
/* 38 */     return this.linea;
/*    */   }
/*    */   
/* 41 */   public void setLinea(String linea) { this.linea = linea; }
/*    */   
/*    */   public Integer getIdRegion() {
/* 44 */     return this.idRegion;
/*    */   }
/*    */   
/* 47 */   public void setIdRegion(Integer idRegion) { this.idRegion = idRegion; }
/*    */   
/*    */   public String getAccion() {
/* 50 */     return this.accion;
/*    */   }
/*    */   
/* 53 */   public void setAccion(String accion) { this.accion = accion; }
/*    */   
/*    */   public String getIdUsuario() {
/* 56 */     return this.idUsuario;
/*    */   }
/*    */   
/* 59 */   public void setIdUsuario(String idUsuario) { this.idUsuario = idUsuario; }
/*    */   
/*    */   public Date getFecha() {
/* 62 */     return this.fecha;
/*    */   }
/*    */   
/* 65 */   public void setFecha(Date fecha) { this.fecha = fecha; }
/*    */   
/*    */   public String getEstatus() {
/* 68 */     return this.estatus;
/*    */   }
/*    */   
/* 71 */   public void setEstatus(String estatus) { this.estatus = estatus; }
/*    */ }


/* Location:              /opt/trabajo/claro/Guatemala/IS/CO/ClaroClub.ear!/ClaroClubEJBClient.jar!/com/claro/transfer/CteExcelenteTO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */