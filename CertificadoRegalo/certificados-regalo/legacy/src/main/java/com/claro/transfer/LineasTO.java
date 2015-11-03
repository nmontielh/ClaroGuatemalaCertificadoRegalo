/*     */ package com.claro.transfer;
/*     */ 
/*     */ import java.sql.Date;
/*     */ 
/*     */ public class LineasTO
/*     */ {
/*     */   private String cuenta;
/*     */   private Integer secuencia;
/*     */   private String ctaPadre;
/*     */   private String linea;
/*     */   private Integer idRegion;
/*     */   private String plan;
/*     */   private String estatusTel;
/*     */   private Integer cicloFact;
/*     */   private Integer addendum;
/*     */   private Date fechaAdd;
/*     */   private Date fechaAlta;
/*     */   private Date fechaAnt;
/*     */   private String sistema;
/*     */   private String estatusPuntos;
/*     */   private String estatusCarta;
/*     */   private String anacr;
/*     */   private CuentaPadreTO cuentaPadre;
/*     */   
/*     */   public String getCuenta() {
/*  26 */     return this.cuenta;
/*     */   }
/*     */   
/*     */   public void setCuenta(String cuenta) {
/*  30 */     this.cuenta = cuenta;
/*     */   }
/*     */   
/*     */   public Integer getSecuencia() {
/*  34 */     return this.secuencia;
/*     */   }
/*     */   
/*     */   public void setSecuencia(Integer secuencia) {
/*  38 */     this.secuencia = secuencia;
/*     */   }
/*     */   
/*     */   public String getCtaPadre() {
/*  42 */     return this.ctaPadre;
/*     */   }
/*     */   
/*     */   public void setCtaPadre(String ctaPadre) {
/*  46 */     this.ctaPadre = ctaPadre;
/*     */   }
/*     */   
/*     */   public String getLinea() {
/*  50 */     return this.linea;
/*     */   }
/*     */   
/*     */   public void setLinea(String linea) {
/*  54 */     this.linea = linea;
/*     */   }
/*     */   
/*     */   public Integer getIdRegion() {
/*  58 */     return this.idRegion;
/*     */   }
/*     */   
/*     */   public void setIdRegion(Integer idRegion) {
/*  62 */     this.idRegion = idRegion;
/*     */   }
/*     */   
/*     */   public String getPlan() {
/*  66 */     return this.plan;
/*     */   }
/*     */   
/*     */   public void setPlan(String plan) {
/*  70 */     this.plan = plan;
/*     */   }
/*     */   
/*     */   public String getEstatusTel() {
/*  74 */     return this.estatusTel;
/*     */   }
/*     */   
/*     */   public void setEstatusTel(String estatusTel) {
/*  78 */     this.estatusTel = estatusTel;
/*     */   }
/*     */   
/*     */   public Integer getCicloFact() {
/*  82 */     return this.cicloFact;
/*     */   }
/*     */   
/*     */   public void setCicloFact(Integer cicloFact) {
/*  86 */     this.cicloFact = cicloFact;
/*     */   }
/*     */   
/*     */   public Integer getAddendum() {
/*  90 */     return this.addendum;
/*     */   }
/*     */   
/*     */   public void setAddendum(Integer addendum) {
/*  94 */     this.addendum = addendum;
/*     */   }
/*     */   
/*     */   public Date getFechaAdd() {
/*  98 */     return this.fechaAdd;
/*     */   }
/*     */   
/*     */   public void setFechaAdd(Date fechaAdd) {
/* 102 */     this.fechaAdd = fechaAdd;
/*     */   }
/*     */   
/*     */   public Date getFechaAlta() {
/* 106 */     return this.fechaAlta;
/*     */   }
/*     */   
/*     */   public void setFechaAlta(Date fechaAlta) {
/* 110 */     this.fechaAlta = fechaAlta;
/*     */   }
/*     */   
/*     */   public Date getFechaAnt() {
/* 114 */     return this.fechaAnt;
/*     */   }
/*     */   
/*     */   public void setFechaAnt(Date fechaAnt) {
/* 118 */     this.fechaAnt = fechaAnt;
/*     */   }
/*     */   
/*     */   public String getSistema() {
/* 122 */     return this.sistema;
/*     */   }
/*     */   
/*     */   public void setSistema(String sistema) {
/* 126 */     this.sistema = sistema;
/*     */   }
/*     */   
/*     */   public String getEstatusPuntos() {
/* 130 */     return this.estatusPuntos;
/*     */   }
/*     */   
/*     */   public void setEstatusPuntos(String estatusPuntos) {
/* 134 */     this.estatusPuntos = estatusPuntos;
/*     */   }
/*     */   
/*     */   public String getEstatusCarta() {
/* 138 */     return this.estatusCarta;
/*     */   }
/*     */   
/*     */   public void setEstatusCarta(String estatusCarta) {
/* 142 */     this.estatusCarta = estatusCarta;
/*     */   }
/*     */   
/*     */   public String getAnacr() {
/* 146 */     return this.anacr;
/*     */   }
/*     */   
/*     */   public void setAnacr(String anacr) {
/* 150 */     this.anacr = anacr;
/*     */   }
/*     */   
/*     */   public CuentaPadreTO getCuentaPadre() {
/* 154 */     return this.cuentaPadre;
/*     */   }
/*     */   
/*     */   public void setCuentaPadre(CuentaPadreTO cuentaPadre) {
/* 158 */     this.cuentaPadre = cuentaPadre;
/*     */   }
/*     */ }


/* Location:              /opt/trabajo/claro/Guatemala/IS/CO/ClaroClub.ear!/ClaroClubEJBClient.jar!/com/claro/transfer/LineasTO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */