/*     */ package com.claro.transfer;
/*     */ 
/*     */ import com.claro.util.Utils;

/*     */ import java.io.Serializable;
/*     */ import java.sql.Timestamp;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FolioSAPTO
/*     */   extends MensajeTO
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -7692655089580911508L;
/*     */   private String esnimeir;
/*     */   private String esnimeip;
/*     */   private String sFechaFolio;
/*     */   private String folio;
/*     */   private String iccidant;
/*     */   private String iccidnvo;
/*     */   private String esnimeiant1;
/*     */   private String esnimeinvo1;
/*     */   private String esnimeiant2;
/*     */   private String esnimeinvo2;
/*     */   private String usuario;
/*     */   private String puntovta;
/*     */   private String cuenta;
/*     */   private String estatus;
/*     */   private Timestamp fechaFolio;
/*     */   private int diasTranscurridos;
/*     */   private int secuencia;
/*     */   
/*     */   public Timestamp getFechaFolio()
/*     */   {
/*  36 */     return this.fechaFolio;
/*     */   }
/*     */   
/*  39 */   public void setFechaFolio(Timestamp fechaFolio) { this.fechaFolio = fechaFolio; }
/*     */   
/*     */   public String getEstatus() {
/*  42 */     return this.estatus;
/*     */   }
/*     */   
/*  45 */   public void setEstatus(String estatus) { this.estatus = estatus; }
/*     */   
/*     */   public String getEsnimeir() {
/*  48 */     return this.esnimeir;
/*     */   }
/*     */   
/*  51 */   public void setEsnimeir(String esnimeir) { this.esnimeir = esnimeir; }
/*     */   
/*     */   public String getEsnimeip() {
/*  54 */     return this.esnimeip;
/*     */   }
/*     */   
/*  57 */   public void setEsnimeip(String esnimeip) { this.esnimeip = esnimeip; }
/*     */   
/*     */   public String getCuenta() {
/*  60 */     return this.cuenta;
/*     */   }
/*     */   
/*  63 */   public void setCuenta(String cuenta) { this.cuenta = cuenta; }
/*     */   
/*     */   public int getSecuencia() {
/*  66 */     return this.secuencia;
/*     */   }
/*     */   
/*  69 */   public void setSecuencia(int secuencia) { this.secuencia = secuencia; }
/*     */   
/*     */   public long getDiasTranscurridos()
/*     */   {
/*  73 */     if (this.fechaFolio != null)
/*  74 */       return Utils.calcularDiasEntreFechas(this.fechaFolio.getTime(), System.currentTimeMillis());
/*  75 */     return this.diasTranscurridos;
/*     */   }
/*     */   
/*  78 */   public void setDiasTranscurridos(int diasTranscurridos) { this.diasTranscurridos = diasTranscurridos; }
/*     */   
/*     */   public String getSFechaFolio() {
/*  81 */     return this.sFechaFolio;
/*     */   }
/*     */   
/*  84 */   public void setSFechaFolio(String fechaFolio) { this.sFechaFolio = fechaFolio; }
/*     */   
/*     */   public String getFolio() {
/*  87 */     return this.folio;
/*     */   }
/*     */   
/*  90 */   public void setFolio(String folio) { this.folio = folio; }
/*     */   
/*     */   public String getIccidant() {
/*  93 */     return this.iccidant;
/*     */   }
/*     */   
/*  96 */   public void setIccidant(String iccidant) { this.iccidant = iccidant; }
/*     */   
/*     */   public String getIccidnvo() {
/*  99 */     return this.iccidnvo;
/*     */   }
/*     */   
/* 102 */   public void setIccidnvo(String iccidnvo) { this.iccidnvo = iccidnvo; }
/*     */   
/*     */   public String getEsnimeiant1() {
/* 105 */     return this.esnimeiant1;
/*     */   }
/*     */   
/* 108 */   public void setEsnimeiant1(String esnimeiant1) { this.esnimeiant1 = esnimeiant1; }
/*     */   
/*     */   public String getEsnimeinvo1() {
/* 111 */     return this.esnimeinvo1;
/*     */   }
/*     */   
/* 114 */   public void setEsnimeinvo1(String esnimeinvo1) { this.esnimeinvo1 = esnimeinvo1; }
/*     */   
/*     */   public String getEsnimeiant2() {
/* 117 */     return this.esnimeiant2;
/*     */   }
/*     */   
/* 120 */   public void setEsnimeiant2(String esnimeiant2) { this.esnimeiant2 = esnimeiant2; }
/*     */   
/*     */   public String getEsnimeinvo2() {
/* 123 */     return this.esnimeinvo2;
/*     */   }
/*     */   
/* 126 */   public void setEsnimeinvo2(String esnimeinvo2) { this.esnimeinvo2 = esnimeinvo2; }
/*     */   
/*     */   public String getUsuario() {
/* 129 */     return this.usuario;
/*     */   }
/*     */   
/* 132 */   public void setUsuario(String usuario) { this.usuario = usuario; }
/*     */   
/*     */   public String getPuntovta() {
/* 135 */     return this.puntovta;
/*     */   }
/*     */   
/* 138 */   public void setPuntovta(String puntovta) { this.puntovta = puntovta; }
/*     */ }


/* Location:              /opt/trabajo/claro/Guatemala/IS/CO/ClaroClub.ear!/ClaroClubEJBClient.jar!/com/claro/transfer/FolioSAPTO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */