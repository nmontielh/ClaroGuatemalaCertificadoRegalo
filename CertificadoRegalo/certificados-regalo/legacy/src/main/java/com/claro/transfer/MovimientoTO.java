/*     */ package com.claro.transfer;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.sql.Date;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MovimientoTO
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private String cuenta;
/*     */   private String secuencia;
/*     */   private String linea;
/*     */   private Date fechaOperacion;
/*     */   private Date facturacion;
/*     */   private String usuario;
/*     */   private String movimiento;
/*     */   private int numPuntos;
/*     */   private int numPuntosExc;
/*     */   private int totalAjustes;
/*     */   private String bonoPromocion;
/*     */   private String referencia;
/*     */   private Date fechaAdendum;
/*     */   
/*     */   public String getCuenta()
/*     */   {
/*  29 */     return this.cuenta;
/*     */   }
/*     */   
/*  32 */   public void setCuenta(String cuenta) { this.cuenta = cuenta; }
/*     */   
/*     */   public String getSecuencia() {
/*  35 */     return this.secuencia;
/*     */   }
/*     */   
/*  38 */   public void setSecuencia(String secuencia) { this.secuencia = secuencia; }
/*     */   
/*     */   public String getLinea() {
/*  41 */     return this.linea;
/*     */   }
/*     */   
/*  44 */   public void setLinea(String linea) { this.linea = linea; }
/*     */   
/*     */   public String getUsuario()
/*     */   {
/*  48 */     return this.usuario;
/*     */   }
/*     */   
/*  51 */   public void setUsuario(String usuario) { this.usuario = usuario; }
/*     */   
/*     */   public String getMovimiento() {
/*  54 */     return this.movimiento;
/*     */   }
/*     */   
/*  57 */   public void setMovimiento(String movimiento) { this.movimiento = movimiento; }
/*     */   
/*     */   public String getBonoPromocion()
/*     */   {
/*  61 */     return this.bonoPromocion;
/*     */   }
/*     */   
/*  64 */   public void setBonoPromocion(String bonoPromocion) { this.bonoPromocion = bonoPromocion; }
/*     */   
/*     */   public String getReferencia() {
/*  67 */     return this.referencia;
/*     */   }
/*     */   
/*  70 */   public void setReferencia(String referencia) { this.referencia = referencia; }
/*     */   
/*     */   public Date getFechaOperacion() {
/*  73 */     return this.fechaOperacion;
/*     */   }
/*     */   
/*  76 */   public void setFechaOperacion(Date fechaOperacion) { this.fechaOperacion = fechaOperacion; }
/*     */   
/*     */   public Date getFacturacion() {
/*  79 */     return this.facturacion;
/*     */   }
/*     */   
/*  82 */   public void setFacturacion(Date facturacion) { this.facturacion = facturacion; }
/*     */   
/*     */   public int getNumPuntos() {
/*  85 */     return this.numPuntos;
/*     */   }
/*     */   
/*  88 */   public void setNumPuntos(int numPuntos) { this.numPuntos = numPuntos; }
/*     */   
/*     */   public int getNumPuntosExc() {
/*  91 */     return this.numPuntosExc;
/*     */   }
/*     */   
/*  94 */   public void setNumPuntosExc(int numPuntosExc) { this.numPuntosExc = numPuntosExc; }
/*     */   
/*     */   public int getTotalAjustes() {
/*  97 */     return this.totalAjustes;
/*     */   }
/*     */   
/* 100 */   public void setTotalAjustes(int totalAjustes) { this.totalAjustes = totalAjustes; }
/*     */   
/*     */   public Date getFechaAdendum() {
/* 103 */     return this.fechaAdendum;
/*     */   }
/*     */   
/* 106 */   public void setFechaAdendum(Date fechaAdendum) { this.fechaAdendum = fechaAdendum; }
/*     */ }


/* Location:              /opt/trabajo/claro/Guatemala/IS/CO/ClaroClub.ear!/ClaroClubEJBClient.jar!/com/claro/transfer/MovimientoTO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */