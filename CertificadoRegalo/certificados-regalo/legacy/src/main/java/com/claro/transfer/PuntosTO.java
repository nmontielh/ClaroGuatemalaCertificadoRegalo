/*     */ package com.claro.transfer;
/*     */ 
/*     */ import com.claro.util.Constantes;
/*     */ import com.claro.util.Utils;

/*     */ import java.io.Serializable;
/*     */ import java.sql.Date;
/*     */ import java.text.SimpleDateFormat;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PuntosTO
/*     */   extends MensajeTO
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private String ptosStatus;
/*     */   private int ptsRedimidos;
/*     */   private int ptsTransferidos;
/*     */   private int ptsVencidos;
/*     */   private int ptsPorVencer;
/*     */   private int ptsPorVencer2;
/*     */   private int ptsPorVencer1;
/*     */   private int ptsRenta;
/*     */   private int ptsExcedentes;
/*     */   private int ptsPorAntiguedad;
/*     */   private int ptsPromocion;
/*     */   private int ptsSaldoAnt;
/*     */   private int ptsSubasta;
/*     */   private int ptosDisponibles;
/*     */   private int ptosDisponiblesTmp;
/*     */   private int ptsAntiguedad;
/*     */   private int ptsDisReserva;
/*     */   private int bonoEquipo;
/*     */   private int ptsTotales;
/*     */   private Date fecVencer;
/*     */   private Date fecVencer2;
/*     */   private Date fecVencer1;
/*     */   private Date fecVencerTmp;
/*     */   private Date fecVencer2Tmp;
/*     */   private Date fecVencer1Tmp;
/*     */   private Date fecVencidos;
/*     */   private Date fecReservacion;
/*     */   private Date fecFactura;
/*     */   private boolean bandVencer60Dias;
/*     */   private String fecReservacionCF;
/*     */   private String descPtsReservados;
/*     */   private String distribuidorReserva;
/*     */   private String ptsBonoProm;
/*     */   private String bBono;
/*     */   private String estatusPuntos;
/*     */   private String ptsAsignados;
/*     */   
/*     */   public String getEstatusPuntos()
/*     */   {
/*  62 */     return this.estatusPuntos;
/*     */   }
/*     */   
/*     */   public void setEstatusPuntos(String estatusPuntos) {
/*  66 */     this.estatusPuntos = estatusPuntos;
/*     */   }
/*     */   
/*     */   public String getBBono() {
/*  70 */     return this.bBono;
/*     */   }
/*     */   
/*     */   public void setBBono(String bono) {
/*  74 */     this.bBono = bono;
/*     */   }
/*     */   
/*     */   public String getPtosStatus() {
/*  78 */     return this.ptosStatus;
/*     */   }
/*     */   
/*     */   public void setPtosStatus(String ptosStatus) {
/*  82 */     this.ptosStatus = ptosStatus;
/*     */   }
/*     */   
/*     */   public int getPtsRedimidos() {
/*  86 */     return this.ptsRedimidos;
/*     */   }
/*     */   
/*     */   public void setPtsRedimidos(int ptsRedimidos) {
/*  90 */     this.ptsRedimidos = ptsRedimidos;
/*     */   }
/*     */   
/*     */   public int getPtsTransferidos() {
/*  94 */     return this.ptsTransferidos;
/*     */   }
/*     */   
/*     */   public void setPtsTransferidos(int ptsTransferidos) {
/*  98 */     this.ptsTransferidos = ptsTransferidos;
/*     */   }
/*     */   
/*     */   public int getPtsVencidos() {
/* 102 */     return this.ptsVencidos;
/*     */   }
/*     */   
/*     */   public void setPtsVencidos(int ptsVencidos) {
/* 106 */     this.ptsVencidos = ptsVencidos;
/*     */   }
/*     */   
/*     */   public int getPtsPorVencer() {
/* 110 */     return this.ptsPorVencer;
/*     */   }
/*     */   
/*     */   public void setPtsPorVencer(int ptsPorVencer) {
/* 114 */     this.ptsPorVencer = ptsPorVencer;
/*     */   }
/*     */   
/*     */   public int getPtsPorVencer2() {
/* 118 */     return this.ptsPorVencer2;
/*     */   }
/*     */   
/*     */   public void setPtsPorVencer2(int ptsPorVencer2) {
/* 122 */     this.ptsPorVencer2 = ptsPorVencer2;
/*     */   }
/*     */   
/*     */   public int getPtsPorVencer1() {
/* 126 */     return this.ptsPorVencer1;
/*     */   }
/*     */   
/*     */   public void setPtsPorVencer1(int ptsPorVencer1) {
/* 130 */     this.ptsPorVencer1 = ptsPorVencer1;
/*     */   }
/*     */   
/*     */   public int getPtsRenta() {
/* 134 */     return this.ptsRenta;
/*     */   }
/*     */   
/*     */   public void setPtsRenta(int ptsRenta) {
/* 138 */     this.ptsRenta = ptsRenta;
/*     */   }
/*     */   
/*     */   public int getPtsExcedentes() {
/* 142 */     return this.ptsExcedentes;
/*     */   }
/*     */   
/*     */   public void setPtsExcedentes(int ptsExcedentes) {
/* 146 */     this.ptsExcedentes = ptsExcedentes;
/*     */   }
/*     */   
/*     */   public int getPtsPorAntiguedad() {
/* 150 */     return this.ptsPorAntiguedad;
/*     */   }
/*     */   
/*     */   public void setPtsPorAntiguedad(int ptsPorAntiguedad) {
/* 154 */     this.ptsPorAntiguedad = ptsPorAntiguedad;
/*     */   }
/*     */   
/*     */   public int getPtsPromocion() {
/* 158 */     return this.ptsPromocion;
/*     */   }
/*     */   
/*     */   public void setPtsPromocion(int ptsPromocion) {
/* 162 */     this.ptsPromocion = ptsPromocion;
/*     */   }
/*     */   
/*     */   public int getPtsSaldoAnt() {
/* 166 */     return this.ptsSaldoAnt;
/*     */   }
/*     */   
/*     */   public void setPtsSaldoAnt(int ptsSaldoAnt) {
/* 170 */     this.ptsSaldoAnt = ptsSaldoAnt;
/*     */   }
/*     */   
/*     */   public int getPtsSubasta() {
/* 174 */     return this.ptsSubasta;
/*     */   }
/*     */   
/*     */   public void setPtsSubasta(int ptsSubasta) {
/* 178 */     this.ptsSubasta = ptsSubasta;
/*     */   }
/*     */   
/*     */   public int getBonoEquipo() {
/* 182 */     return this.bonoEquipo;
/*     */   }
/*     */   
/*     */   public void setBonoEquipo(int bonoEquipo) {
/* 186 */     this.bonoEquipo = bonoEquipo;
/*     */   }
/*     */   
/*     */   public int getPtosDisponibles() {
/* 190 */     this.ptosDisponibles = 
/* 191 */       (this.ptsExcedentes + this.ptsPorVencer + this.ptsPorVencer1 + this.ptsPorVencer2 + this.ptsRenta + this.ptsAntiguedad + this.ptsPromocion);
/* 192 */     return this.ptosDisponibles;
/*     */   }
/*     */   
/*     */   public void setPtosDisponibles(int ptosDisponibles) {
/* 196 */     this.ptosDisponibles = ptosDisponibles;
/*     */   }
/*     */   
/*     */   public int getPtosTotalesTemp() {
/* 200 */     return this.ptosDisponibles;
/*     */   }
/*     */   
/*     */   public Date getFecVencer() {
/* 204 */     setFecVencerTmp(this.fecVencer);
/* 205 */     if (this.ptsPorVencer == 0) {
/* 206 */       this.fecVencer = null;
/* 207 */       return this.fecVencer;
/*     */     }
/* 209 */     return this.fecVencer;
/*     */   }
/*     */   
/*     */   public void setFecVencer(Date fecVencer) {
/* 213 */     this.fecVencer = fecVencer;
/*     */   }
/*     */   
/*     */   public Date getFecVencer2() {
/* 217 */     setFecVencer2Tmp(this.fecVencer2);
/* 218 */     if (this.ptsPorVencer2 == 0) {
/* 219 */       this.fecVencer2 = null;
/* 220 */       return this.fecVencer2;
/*     */     }
/* 222 */     return this.fecVencer2;
/*     */   }
/*     */   
/*     */   public void setFecVencer2(Date fecVencer2) {
/* 226 */     this.fecVencer2 = fecVencer2;
/*     */   }
/*     */   
/*     */   public Date getFecVencer1() {
/* 230 */     setFecVencer1Tmp(this.fecVencer1);
/* 231 */     if (this.ptsPorVencer1 == 0) {
/* 232 */       this.fecVencer1 = null;
/* 233 */       return this.fecVencer1;
/*     */     }
/* 235 */     return this.fecVencer1;
/*     */   }
/*     */   
/*     */   public void setFecVencer1(Date fecVencer1) {
/* 239 */     this.fecVencer1 = fecVencer1;
/*     */   }
/*     */   
/*     */   public Date getFecVencidos() {
/* 243 */     return this.fecVencidos;
/*     */   }
/*     */   
/*     */   public void setFecVencidos(Date fecVencidos) {
/* 247 */     this.fecVencidos = fecVencidos;
/*     */   }
/*     */   
/*     */ 
/*     */   public String getPtsDisReservaCF_()
/*     */   {
/* 253 */     return Utils.setFormatoPtos(this.ptsDisReserva);
/*     */   }
/*     */   
/*     */   public int getPtsDisReserva() {
/* 257 */     return this.ptsDisReserva;
/*     */   }
/*     */   
/*     */   public void setPtsDisReserva(int ptsDisReserva) {
/* 261 */     this.ptsDisReserva = ptsDisReserva;
/*     */   }
/*     */   
/*     */   public String getPtsBonoProm() {
/* 265 */     return this.ptsBonoProm;
/*     */   }
/*     */   
/*     */   public void setPtsBonoProm(String ptsBonoProm) {
/* 269 */     this.ptsBonoProm = ptsBonoProm;
/*     */   }
/*     */   
/*     */   public int getPtsAntiguedad() {
/* 273 */     return this.ptsAntiguedad;
/*     */   }
/*     */   
/*     */   public void setPtsAntiguedad(int ptsAntiguedad) {
/* 277 */     this.ptsAntiguedad = ptsAntiguedad;
/*     */   }
/*     */   
/*     */   public boolean isBandVencer60Dias() {
/* 281 */     return this.bandVencer60Dias;
/*     */   }
/*     */   
/*     */   public void setBandVencer60Dias(boolean bandVencer60Dias) {
/* 285 */     this.bandVencer60Dias = bandVencer60Dias;
/*     */   }
/*     */   
/*     */   public String getPtsRedimidosCF() {
/* 289 */     return Utils.setFormatoPtos(this.ptsRedimidos);
/*     */   }
/*     */   
/*     */   public String getPtsTransferidosCF() {
/* 293 */     return Utils.setFormatoPtos(this.ptsTransferidos);
/*     */   }
/*     */   
/*     */   public String getPtsVencidosCF() {
/* 297 */     return Utils.setFormatoPtos(this.ptsVencidos);
/*     */   }
/*     */   
/*     */   public String getPtsPorVencerCF() {
/* 301 */     return Utils.setFormatoPtos(this.ptsPorVencer);
/*     */   }
/*     */   
/*     */   public String getPtsPorVencer2CF() {
/* 305 */     return Utils.setFormatoPtos(this.ptsPorVencer2);
/*     */   }
/*     */   
/*     */   public String getPtsPorVencer1CF() {
/* 309 */     return Utils.setFormatoPtos(this.ptsPorVencer1);
/*     */   }
/*     */   
/*     */   public String getPtsRentaCF() {
/* 313 */     return Utils.setFormatoPtos(this.ptsRenta);
/*     */   }
/*     */   
/*     */   public String getPtsExcedentesCF() {
/* 317 */     return Utils.setFormatoPtos(this.ptsExcedentes);
/*     */   }
/*     */   
/*     */   public String getPtsPorAntiguedadCF() {
/* 321 */     return Utils.setFormatoPtos(this.ptsPorAntiguedad);
/*     */   }
/*     */   
/*     */   public String getPtsPromocionCF() {
/* 325 */     return Utils.setFormatoPtos(this.ptsPromocion);
/*     */   }
/*     */   
/*     */   public String getPtsSaldoAntCF() {
/* 329 */     return Utils.setFormatoPtos(this.ptsSaldoAnt);
/*     */   }
/*     */   
/*     */   public String getPtsSubastaCF() {
/* 333 */     return Utils.setFormatoPtos(this.ptsSubasta);
/*     */   }
/*     */   
/*     */   public String getPtosDisponiblesCF() {
/* 337 */     return Utils.setFormatoPtos(getPtosDisponibles());
/*     */   }
/*     */   
/*     */   public String getPtsAntiguedadCF() {
/* 341 */     return Utils.setFormatoPtos(this.ptsAntiguedad);
/*     */   }
/*     */   
/*     */   public String getDescPtsReservados() {
/* 345 */     return this.descPtsReservados;
/*     */   }
/*     */   
/*     */   public void setDescPtsReservados(String descPtsReservados) {
/* 349 */     this.descPtsReservados = descPtsReservados;
/*     */   }
/*     */   
/*     */   public Date getFecReservacion() {
/* 353 */     return this.fecReservacion;
/*     */   }
/*     */   
/*     */   public void setFecReservacion(Date fecReservacion) {
/* 357 */     this.fecReservacion = fecReservacion;
/*     */   }
/*     */   
/*     */   public String getFecReservacionCF() {
/*     */     try {
/* 362 */       return Constantes.DATEFORMATdd_MM_YYYY.format(this.fecReservacion);
/*     */     } catch (Exception e) {}
/* 364 */     return this.fecReservacionCF;
/*     */   }
/*     */   
/*     */   public String getDistribuidorReserva()
/*     */   {
/* 369 */     return this.distribuidorReserva;
/*     */   }
/*     */   
/*     */   public void setDistribuidorReserva(String distribuidorReserva) {
/* 373 */     this.distribuidorReserva = distribuidorReserva;
/*     */   }
/*     */   
/*     */   public String getBonoEquipoCF() {
/* 377 */     return Utils.setFormatoPtos(this.bonoEquipo);
/*     */   }
/*     */   
/*     */   public int getPtosDisponiblesTmp() {
/* 381 */     return this.ptosDisponiblesTmp;
/*     */   }
/*     */   
/*     */   public void setPtosDisponiblesTmp(int ptosDisponiblesTmp) {
/* 385 */     this.ptosDisponiblesTmp = ptosDisponiblesTmp;
/*     */   }
/*     */   
/*     */   public Date getFecVencerTmp() {
/* 389 */     return this.fecVencerTmp;
/*     */   }
/*     */   
/*     */   public void setFecVencerTmp(Date fecVencerTmp) {
/* 393 */     this.fecVencerTmp = fecVencerTmp;
/*     */   }
/*     */   
/*     */   public Date getFecVencer2Tmp() {
/* 397 */     return this.fecVencer2Tmp;
/*     */   }
/*     */   
/*     */   public void setFecVencer2Tmp(Date fecVencer2Tmp) {
/* 401 */     this.fecVencer2Tmp = fecVencer2Tmp;
/*     */   }
/*     */   
/*     */   public Date getFecVencer1Tmp() {
/* 405 */     return this.fecVencer1Tmp;
/*     */   }
/*     */   
/*     */   public void setFecVencer1Tmp(Date fecVencer1Tmp) {
/* 409 */     this.fecVencer1Tmp = fecVencer1Tmp;
/*     */   }
/*     */   
/*     */   public Date getFecFactura() {
/* 413 */     return this.fecFactura;
/*     */   }
/*     */   
/*     */   public void setFecFactura(Date fecFactura) {
/* 417 */     this.fecFactura = fecFactura;
/*     */   }
/*     */   
/*     */   public int getPtsTotales() {
/* 421 */     this.ptsTotales = (this.ptsPorVencer + this.ptsPorVencer1 + this.ptsPorVencer2 + this.ptsPromocion + this.ptsAntiguedad + this.ptsExcedentes + this.ptsRenta);
/* 422 */     return this.ptsTotales;
/*     */   }
/*     */   
/*     */   public void setPtsTotales(int ptsTotales) {
/* 426 */     this.ptsTotales = ptsTotales;
/*     */   }
/*     */   
/*     */   public String getPtsAsignados() {
/* 430 */     return this.ptsAsignados;
/*     */   }
/*     */   
/*     */   public void setPtsAsignados(String ptsAsignados) {
/* 434 */     this.ptsAsignados = ptsAsignados;
/*     */   }
/*     */ }


/* Location:              /opt/trabajo/claro/Guatemala/IS/CO/ClaroClub.ear!/ClaroClubEJBClient.jar!/com/claro/transfer/PuntosTO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */