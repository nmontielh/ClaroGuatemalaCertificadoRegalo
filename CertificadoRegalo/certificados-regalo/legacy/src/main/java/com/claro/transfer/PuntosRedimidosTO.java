/*     */ package com.claro.transfer;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ 
/*     */ public class PuntosRedimidosTO
/*     */   extends PuntosTO
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 7060777587156048745L;
/*     */   private int ptsPorVencerRedimidos;
/*     */   private int ptsPorVencer1Redimidos;
/*     */   private int ptsPorVencer2Redimidos;
/*     */   private int ptsRentaRedimidos;
/*     */   private int ptsExcedentesRedimidos;
/*     */   private int ptsPorAntiguedadRedimidos;
/*     */   private int ptsPromocionRedimidos;
/*     */   private int ptsCanjeados;
/*     */   private int ptsAcumulados;
/*     */   private int ptsTotaltes;
/*     */   private int ptsAcumRestantes;
/*     */   private int ptsSobrantes;
/*     */   private int ptsSobrantes1;
/*     */   private int ptsMinimos;
/*     */   private String ptsTotaltesconFormato;
/*     */   private int bonoProrrateo;
/*     */   
/*     */   public int getBonoProrrateo()
/*     */   {
/*  30 */     return this.bonoProrrateo;
/*     */   }
/*     */   
/*     */   public void setBonoProrrateo(int bonoProrrateo) {
/*  34 */     this.bonoProrrateo = bonoProrrateo;
/*     */   }
/*     */   
/*     */   public String getPtsTotaltesconFormato() {
/*  38 */     return this.ptsTotaltesconFormato;
/*     */   }
/*     */   
/*     */   public void setPtsTotaltesconFormato(String ptsTotaltesconFormato) {
/*  42 */     this.ptsTotaltesconFormato = ptsTotaltesconFormato;
/*     */   }
/*     */   
/*     */   public PuntosRedimidosTO(PuntosTO puntosTO) {
/*  46 */     setPtsPorVencer(puntosTO.getPtsPorVencer());
/*  47 */     setPtsPorVencer1(puntosTO.getPtsPorVencer1());
/*  48 */     setPtsPorVencer2(puntosTO.getPtsPorVencer2());
/*  49 */     setPtsPromocion(puntosTO.getPtsPromocion());
/*  50 */     setPtsAntiguedad(puntosTO.getPtsAntiguedad());
/*  51 */     setPtsExcedentes(puntosTO.getPtsExcedentes());
/*  52 */     setPtsRenta(puntosTO.getPtsRenta());
/*  53 */     setFecVencer(puntosTO.getFecVencer());
/*  54 */     setFecVencerTmp(puntosTO.getFecVencerTmp());
/*  55 */     setFecVencer1(puntosTO.getFecVencer1());
/*  56 */     setFecVencer1Tmp(puntosTO.getFecVencer1Tmp());
/*  57 */     setFecVencer2(puntosTO.getFecVencer2());
/*  58 */     setFecVencer2Tmp(puntosTO.getFecVencer2Tmp());
/*  59 */     setPtsRedimidos(puntosTO.getPtsRedimidos());
/*  60 */     setPtosDisponiblesTmp(puntosTO.getPtosDisponiblesTmp());
/*  61 */     setBonoEquipo(puntosTO.getBonoEquipo());
/*  62 */     setIdMensaje(puntosTO.getIdMensaje());
/*  63 */     setMensaje(puntosTO.getMensaje());
/*     */   }
/*     */   
/*     */   public int getPtsSobrantes() {
/*  67 */     return this.ptsSobrantes;
/*     */   }
/*     */   
/*     */   public void setPtsSobrantes(int ptsSobrantes) {
/*  71 */     this.ptsSobrantes = ptsSobrantes;
/*     */   }
/*     */   
/*     */   public int getPtsSobrantes1() {
/*  75 */     return this.ptsSobrantes1;
/*     */   }
/*     */   
/*     */   public void setPtsSobrantes1(int ptsSobrantes1) {
/*  79 */     this.ptsSobrantes1 = ptsSobrantes1;
/*     */   }
/*     */   
/*     */   public int getPtsMinimos() {
/*  83 */     return this.ptsMinimos;
/*     */   }
/*     */   
/*     */   public void setPtsMinimos(int ptsMinimos) {
/*  87 */     this.ptsMinimos = ptsMinimos;
/*     */   }
/*     */   
/*     */ 
/*     */   public PuntosRedimidosTO() {}
/*     */   
/*     */   public int getPtsCanjeados()
/*     */   {
/*  95 */     return this.ptsCanjeados;
/*     */   }
/*     */   
/*  98 */   public void setPtsCanjeados(int ptsCanjeados) { this.ptsCanjeados = ptsCanjeados; }
/*     */   
/*     */   public int getPtsAcumulados() {
/* 101 */     return this.ptsAcumulados;
/*     */   }
/*     */   
/* 104 */   public void setPtsAcumulados(int ptsAcumulados) { this.ptsAcumulados = ptsAcumulados; }
/*     */   
/*     */   public int getPtsTotaltes() {
/* 107 */     return this.ptsTotaltes;
/*     */   }
/*     */   
/* 110 */   public void setPtsTotaltes(int ptsTotaltes) { this.ptsTotaltes = ptsTotaltes; }
/*     */   
/*     */   public int getPtsAcumRestantes() {
/* 113 */     return this.ptsAcumRestantes;
/*     */   }
/*     */   
/* 116 */   public void setPtsAcumRestantes(int ptsAcumRestantes) { this.ptsAcumRestantes = ptsAcumRestantes; }
/*     */   
/*     */   public int getPtsPorVencerRedimidos() {
/* 119 */     return this.ptsPorVencerRedimidos;
/*     */   }
/*     */   
/* 122 */   public void setPtsPorVencerRedimidos(int ptsPorVencerRedimidos) { this.ptsPorVencerRedimidos = ptsPorVencerRedimidos; }
/*     */   
/*     */   public int getPtsPorVencer1Redimidos() {
/* 125 */     return this.ptsPorVencer1Redimidos;
/*     */   }
/*     */   
/* 128 */   public void setPtsPorVencer1Redimidos(int ptsPorVencer1Redimidos) { this.ptsPorVencer1Redimidos = ptsPorVencer1Redimidos; }
/*     */   
/*     */   public int getPtsPorVencer2Redimidos() {
/* 131 */     return this.ptsPorVencer2Redimidos;
/*     */   }
/*     */   
/* 134 */   public void setPtsPorVencer2Redimidos(int ptsPorVencer2Redimidos) { this.ptsPorVencer2Redimidos = ptsPorVencer2Redimidos; }
/*     */   
/*     */   public int getPtsRentaRedimidos() {
/* 137 */     return this.ptsRentaRedimidos;
/*     */   }
/*     */   
/* 140 */   public void setPtsRentaRedimidos(int ptsRentaRedimidos) { this.ptsRentaRedimidos = ptsRentaRedimidos; }
/*     */   
/*     */   public int getPtsExcedentesRedimidos() {
/* 143 */     return this.ptsExcedentesRedimidos;
/*     */   }
/*     */   
/* 146 */   public void setPtsExcedentesRedimidos(int ptsExcedentesRedimidos) { this.ptsExcedentesRedimidos = ptsExcedentesRedimidos; }
/*     */   
/*     */   public int getPtsPorAntiguedadRedimidos() {
/* 149 */     return this.ptsPorAntiguedadRedimidos;
/*     */   }
/*     */   
/* 152 */   public void setPtsPorAntiguedadRedimidos(int ptsPorAntiguedadRedimidos) { this.ptsPorAntiguedadRedimidos = ptsPorAntiguedadRedimidos; }
/*     */   
/*     */   public int getPtsPromocionRedimidos() {
/* 155 */     return this.ptsPromocionRedimidos;
/*     */   }
/*     */   
/* 158 */   public void setPtsPromocionRedimidos(int ptsPromocionRedimidos) { this.ptsPromocionRedimidos = ptsPromocionRedimidos; }
/*     */   
/*     */   public int getTotalRentaRedimidos()
/*     */   {
/* 162 */     return getPtsRenta() + this.ptsRentaRedimidos;
/*     */   }
/*     */   
/*     */   public int getTotalRedimidosPorVencer2() {
/* 166 */     return getPtsPorVencer2() + this.ptsPorVencer2Redimidos;
/*     */   }
/*     */   
/*     */   public int getTotalRedimidosPorVencer1() {
/* 170 */     return getPtsPorVencer1() + this.ptsPorVencer1Redimidos;
/*     */   }
/*     */   
/*     */   public int getTotalRedimidosPorVencer() {
/* 174 */     return getPtsPorVencer() + this.ptsPorVencerRedimidos;
/*     */   }
/*     */ }


/* Location:              /opt/trabajo/claro/Guatemala/IS/CO/ClaroClub.ear!/ClaroClubEJBClient.jar!/com/claro/transfer/PuntosRedimidosTO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */