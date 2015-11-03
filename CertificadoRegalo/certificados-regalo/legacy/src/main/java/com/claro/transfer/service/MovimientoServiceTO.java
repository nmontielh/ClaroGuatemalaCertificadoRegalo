/*     */ package com.claro.transfer.service;
/*     */ 
/*     */ import com.claro.transfer.MovimientoTO;
/*     */ import com.claro.util.Utils;
/*     */ import java.io.Serializable;
/*     */ import java.text.SimpleDateFormat;
/*     */ 
/*     */ 
/*     */ public class MovimientoServiceTO
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private String cuenta;
/*     */   private String secuencia;
/*     */   private String linea;
/*     */   private String fechaOperacion;
/*     */   private String facturacion;
/*     */   private String usuario;
/*     */   private String movimiento;
/*     */   private int numPuntos;
/*     */   private int numPuntosExc;
/*     */   private int totalAjustes;
/*     */   private String bonoPromocion;
/*     */   private String referencia;
/*     */   private String fechaAdendum;
/*     */   
/*     */   public MovimientoServiceTO() {}
/*     */   
/*     */   public MovimientoServiceTO(MovimientoTO movimientoTO)
/*     */   {
/*  31 */     this.cuenta = movimientoTO.getCuenta();
/*  32 */     this.secuencia = movimientoTO.getSecuencia();
/*  33 */     this.linea = movimientoTO.getLinea();
/*  34 */     this.fechaOperacion = Utils.DATEFORMATdd_MM_YYYY.format(movimientoTO.getFechaOperacion());
/*  35 */     this.facturacion = Utils.DATEFORMATdd_MM_YYYY.format(movimientoTO.getFacturacion());
/*  36 */     this.usuario = movimientoTO.getUsuario();
/*  37 */     this.movimiento = movimientoTO.getMovimiento();
/*  38 */     this.numPuntos = movimientoTO.getNumPuntos();
/*  39 */     this.numPuntosExc = movimientoTO.getNumPuntosExc();
/*  40 */     this.totalAjustes = movimientoTO.getTotalAjustes();
/*  41 */     this.bonoPromocion = movimientoTO.getBonoPromocion();
/*  42 */     this.referencia = movimientoTO.getReferencia();
/*  43 */     this.fechaAdendum = Utils.DATEFORMATdd_MM_YYYY.format(movimientoTO.getFechaAdendum());
/*     */   }
/*     */   
/*     */   public String getCuenta() {
/*  47 */     return this.cuenta;
/*     */   }
/*     */   
/*     */   public void setCuenta(String cuenta) {
/*  51 */     this.cuenta = cuenta;
/*     */   }
/*     */   
/*     */   public String getSecuencia() {
/*  55 */     return this.secuencia;
/*     */   }
/*     */   
/*     */   public void setSecuencia(String secuencia) {
/*  59 */     this.secuencia = secuencia;
/*     */   }
/*     */   
/*     */   public String getLinea() {
/*  63 */     return this.linea;
/*     */   }
/*     */   
/*     */   public void setLinea(String linea) {
/*  67 */     this.linea = linea;
/*     */   }
/*     */   
/*     */   public String getFechaOperacion() {
/*  71 */     return this.fechaOperacion;
/*     */   }
/*     */   
/*     */   public void setFechaOperacion(String fechaOperacion) {
/*  75 */     this.fechaOperacion = fechaOperacion;
/*     */   }
/*     */   
/*     */   public String getFacturacion() {
/*  79 */     return this.facturacion;
/*     */   }
/*     */   
/*     */   public void setFacturacion(String facturacion) {
/*  83 */     this.facturacion = facturacion;
/*     */   }
/*     */   
/*     */   public String getUsuario() {
/*  87 */     return this.usuario;
/*     */   }
/*     */   
/*     */   public void setUsuario(String usuario) {
/*  91 */     this.usuario = usuario;
/*     */   }
/*     */   
/*     */   public String getMovimiento() {
/*  95 */     return this.movimiento;
/*     */   }
/*     */   
/*     */   public void setMovimiento(String movimiento) {
/*  99 */     this.movimiento = movimiento;
/*     */   }
/*     */   
/*     */ 
/*     */   public String getBonoPromocion()
/*     */   {
/* 105 */     return this.bonoPromocion;
/*     */   }
/*     */   
/*     */   public void setBonoPromocion(String bonoPromocion) {
/* 109 */     this.bonoPromocion = bonoPromocion;
/*     */   }
/*     */   
/*     */   public String getReferencia() {
/* 113 */     return this.referencia;
/*     */   }
/*     */   
/*     */   public void setReferencia(String referencia) {
/* 117 */     this.referencia = referencia;
/*     */   }
/*     */   
/*     */   public String getFechaAdendum() {
/* 121 */     return this.fechaAdendum;
/*     */   }
/*     */   
/*     */   public void setFechaAdendum(String fechaAdendum) {
/* 125 */     this.fechaAdendum = fechaAdendum;
/*     */   }
/*     */   
/*     */   public int getNumPuntos() {
/* 129 */     return this.numPuntos;
/*     */   }
/*     */   
/*     */   public void setNumPuntos(int numPuntos) {
/* 133 */     this.numPuntos = numPuntos;
/*     */   }
/*     */   
/*     */   public int getNumPuntosExc() {
/* 137 */     return this.numPuntosExc;
/*     */   }
/*     */   
/*     */   public void setNumPuntosExc(int numPuntosExc) {
/* 141 */     this.numPuntosExc = numPuntosExc;
/*     */   }
/*     */   
/*     */   public int getTotalAjustes() {
/* 145 */     return this.totalAjustes;
/*     */   }
/*     */   
/*     */   public void setTotalAjustes(int totalAjustes) {
/* 149 */     this.totalAjustes = totalAjustes;
/*     */   }
/*     */ }


/* Location:              /opt/trabajo/claro/Guatemala/IS/CO/ClaroClub.ear!/ClaroClubEJBClient.jar!/com/claro/transfer/service/MovimientoServiceTO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */