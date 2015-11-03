/*     */ package com.claro.transfer;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.math.BigDecimal;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DescuentoTO
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private int aplicaDescuentoRoext;
/*     */   private int aplicaDescuentoAltoValor;
/*     */   private int aplicaDescuentoPromocion;
/*     */   private int aplicaProductoM2K;
/*     */   private int numBonosRoext;
/*     */   private int numBonosAltoValor;
/*     */   private int numBonosInbursa;
/*     */   private int numBonosGap;
/*     */   private BigDecimal bonoDescuentoAltoValor;
/*     */   private BigDecimal bonoDescuentoRoext;
/*     */   private BigDecimal bonoDescuentoPromocion;
/*     */   private BigDecimal descuentoUtilizado;
/*     */   private int aplicaDescuentoInbursa;
/*     */   private BigDecimal bonoDescuentoMarca;
/*     */   private BigDecimal bonoDescuentoInbursa;
/*     */   private Map<String, ProductosTO> descuentosInbursa;
/*     */   private BigDecimal descuentoInbursaRestante;
/*     */   private BigDecimal descuentoMarcaRestante;
/*     */   
/*     */   public BigDecimal getBonoDescuentoRoext()
/*     */   {
/*  36 */     return this.bonoDescuentoRoext;
/*     */   }
/*     */   
/*  39 */   public void setBonoDescuentoRoext(BigDecimal bonoDescuentoRoext) { this.bonoDescuentoRoext = bonoDescuentoRoext; }
/*     */   
/*     */   public BigDecimal getBonoDescuentoAltoValor() {
/*  42 */     return this.bonoDescuentoAltoValor;
/*     */   }
/*     */   
/*  45 */   public void setBonoDescuentoAltoValor(BigDecimal bonoDescuentoAltoValor) { this.bonoDescuentoAltoValor = bonoDescuentoAltoValor; }
/*     */   
/*     */   public int getNumBonosAltoValor() {
/*  48 */     return this.numBonosAltoValor;
/*     */   }
/*     */   
/*  51 */   public void setNumBonosAltoValor(int numBonosAltoValor) { this.numBonosAltoValor = numBonosAltoValor; }
/*     */   
/*     */ 
/*     */   public int getAplicaDescuentoRoext()
/*     */   {
/*  56 */     return this.aplicaDescuentoRoext;
/*     */   }
/*     */   
/*  59 */   public void setAplicaDescuentoRoext(int aplicaDescuentoRoext) { this.aplicaDescuentoRoext = aplicaDescuentoRoext; }
/*     */   
/*     */   public int getAplicaDescuentoAltoValor() {
/*  62 */     return this.aplicaDescuentoAltoValor;
/*     */   }
/*     */   
/*  65 */   public void setAplicaDescuentoAltoValor(int aplicaDescuentoAltoValor) { this.aplicaDescuentoAltoValor = aplicaDescuentoAltoValor; }
/*     */   
/*     */   public int getNumBonosRoext()
/*     */   {
/*  69 */     return this.numBonosRoext;
/*     */   }
/*     */   
/*  72 */   public void setNumBonosRoext(int numBonosRoext) { this.numBonosRoext = numBonosRoext; }
/*     */   
/*     */   public BigDecimal getDescuentoUtilizado() {
/*  75 */     return this.descuentoUtilizado;
/*     */   }
/*     */   
/*  78 */   public void setDescuentoUtilizado(BigDecimal descuentoUtilizado) { this.descuentoUtilizado = descuentoUtilizado; }
/*     */   
/*     */   public int getNumBonosGap() {
/*  81 */     return this.numBonosGap;
/*     */   }
/*     */   
/*  84 */   public void setNumBonosGap(int numBonosGap) { this.numBonosGap = numBonosGap; }
/*     */   
/*     */   public int getAplicaProductoM2K() {
/*  87 */     return this.aplicaProductoM2K;
/*     */   }
/*     */   
/*  90 */   public void setAplicaProductoM2K(int aplicaProductoM2K) { this.aplicaProductoM2K = aplicaProductoM2K; }
/*     */   
/*     */   public int getAplicaDescuentoPromocion() {
/*  93 */     return this.aplicaDescuentoPromocion;
/*     */   }
/*     */   
/*  96 */   public void setAplicaDescuentoPromocion(int aplicaDescuentoPromocion) { this.aplicaDescuentoPromocion = aplicaDescuentoPromocion; }
/*     */   
/*     */   public BigDecimal getBonoDescuentoPromocion() {
/*  99 */     return this.bonoDescuentoPromocion;
/*     */   }
/*     */   
/* 102 */   public void setBonoDescuentoPromocion(BigDecimal bonoDescuentoPromocion) { this.bonoDescuentoPromocion = bonoDescuentoPromocion; }
/*     */   
/*     */   public int getAplicaDescuentoInbursa() {
/* 105 */     return this.aplicaDescuentoInbursa;
/*     */   }
/*     */   
/* 108 */   public void setAplicaDescuentoInbursa(int aplicaDescuentoInbursa) { this.aplicaDescuentoInbursa = aplicaDescuentoInbursa; }
/*     */   
/*     */   public BigDecimal getBonoDescuentoMarca() {
/* 111 */     return this.bonoDescuentoMarca;
/*     */   }
/*     */   
/* 114 */   public void setBonoDescuentoMarca(BigDecimal bonoDescuentoMarca) { this.bonoDescuentoMarca = bonoDescuentoMarca; }
/*     */   
/*     */   public BigDecimal getBonoDescuentoInbursa() {
/* 117 */     return this.bonoDescuentoInbursa;
/*     */   }
/*     */   
/* 120 */   public void setBonoDescuentoInbursa(BigDecimal bonoDescuentoInbursa) { this.bonoDescuentoInbursa = bonoDescuentoInbursa; }
/*     */   
/*     */   public int getNumBonosInbursa() {
/* 123 */     return this.numBonosInbursa;
/*     */   }
/*     */   
/* 126 */   public void setNumBonosInbursa(int numBonosInbursa) { this.numBonosInbursa = numBonosInbursa; }
/*     */   
/*     */   public Map<String, ProductosTO> getDescuentosInbursa() {
/* 129 */     return this.descuentosInbursa;
/*     */   }
/*     */   
/* 132 */   public void setDescuentosInbursa(Map<String, ProductosTO> descuentosInbursa) { this.descuentosInbursa = descuentosInbursa; }
/*     */   
/*     */   public BigDecimal getDescuentoInbursaRestante() {
/* 135 */     return this.descuentoInbursaRestante;
/*     */   }
/*     */   
/* 138 */   public void setDescuentoInbursaRestante(BigDecimal descuentoInbursaRestante) { this.descuentoInbursaRestante = descuentoInbursaRestante; }
/*     */   
/*     */   public BigDecimal getDescuentoMarcaRestante() {
/* 141 */     return this.descuentoMarcaRestante;
/*     */   }
/*     */   
/* 144 */   public void setDescuentoMarcaRestante(BigDecimal descuentoMarcaRestante) { this.descuentoMarcaRestante = descuentoMarcaRestante; }
/*     */ }


/* Location:              /opt/trabajo/claro/Guatemala/IS/CO/ClaroClub.ear!/ClaroClubEJBClient.jar!/com/claro/transfer/DescuentoTO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */