/*     */ package com.claro.transfer.gap;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ 
/*     */ public class InfoPromocionGapTO
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private String IdSecuencia;
/*     */   private String IdPromocion;
/*     */   private String versionPromocion;
/*     */   private String NombrePromocion;
/*     */   private String JustificacionPromocion;
/*     */   private String DescripcionPromocion;
/*     */   private String FechaInicio;
/*     */   private String FechaTermino;
/*     */   private String Familia;
/*     */   private String FechaAplicacionPromocion;
/*     */   private String TerminoStandBy;
/*     */   private String urlProducto;
/*     */   private String tecnologia;
/*     */   private String fzaVentaEntrega;
/*     */   private String aplicaEp;
/*     */   private String aplicaCA;
/*     */   
/*     */   public String getIdSecuencia()
/*     */   {
/*  29 */     return this.IdSecuencia;
/*     */   }
/*     */   
/*  32 */   public void setIdSecuencia(String idSecuencia) { this.IdSecuencia = idSecuencia; }
/*     */   
/*     */   public String getIdPromocion() {
/*  35 */     return this.IdPromocion;
/*     */   }
/*     */   
/*  38 */   public void setIdPromocion(String idPromocion) { this.IdPromocion = idPromocion; }
/*     */   
/*     */   public String getNombrePromocion() {
/*  41 */     return this.NombrePromocion;
/*     */   }
/*     */   
/*  44 */   public void setNombrePromocion(String nombrePromocion) { this.NombrePromocion = nombrePromocion; }
/*     */   
/*     */   public String getJustificacionPromocion() {
/*  47 */     return this.JustificacionPromocion;
/*     */   }
/*     */   
/*  50 */   public void setJustificacionPromocion(String justificacionPromocion) { this.JustificacionPromocion = justificacionPromocion; }
/*     */   
/*     */   public String getDescripcionPromocion() {
/*  53 */     return this.DescripcionPromocion;
/*     */   }
/*     */   
/*  56 */   public void setDescripcionPromocion(String descripcionPromocion) { this.DescripcionPromocion = descripcionPromocion; }
/*     */   
/*     */   public String getFechaInicio() {
/*  59 */     return this.FechaInicio;
/*     */   }
/*     */   
/*  62 */   public void setFechaInicio(String fechaInicio) { this.FechaInicio = fechaInicio; }
/*     */   
/*     */   public String getFechaTermino() {
/*  65 */     return this.FechaTermino;
/*     */   }
/*     */   
/*  68 */   public void setFechaTermino(String fechaTermino) { this.FechaTermino = fechaTermino; }
/*     */   
/*     */   public String getFamilia() {
/*  71 */     return this.Familia;
/*     */   }
/*     */   
/*  74 */   public void setFamilia(String familia) { this.Familia = familia; }
/*     */   
/*     */   public String getFechaAplicacionPromocion() {
/*  77 */     return this.FechaAplicacionPromocion;
/*     */   }
/*     */   
/*  80 */   public void setFechaAplicacionPromocion(String fechaAplicacionPromocion) { this.FechaAplicacionPromocion = fechaAplicacionPromocion; }
/*     */   
/*     */   public String getTerminoStandBy() {
/*  83 */     return this.TerminoStandBy;
/*     */   }
/*     */   
/*  86 */   public void setTerminoStandBy(String terminoStandBy) { this.TerminoStandBy = terminoStandBy; }
/*     */   
/*     */   public String getUrlProducto() {
/*  89 */     return this.urlProducto;
/*     */   }
/*     */   
/*  92 */   public void setUrlProducto(String urlProducto) { this.urlProducto = urlProducto; }
/*     */   
/*     */   public String getTecnologia() {
/*  95 */     return this.tecnologia;
/*     */   }
/*     */   
/*  98 */   public void setTecnologia(String tecnologia) { this.tecnologia = tecnologia; }
/*     */   
/*     */   public String getFzaVentaEntrega() {
/* 101 */     return this.fzaVentaEntrega;
/*     */   }
/*     */   
/* 104 */   public void setFzaVentaEntrega(String fzaVentaEntrega) { this.fzaVentaEntrega = fzaVentaEntrega; }
/*     */   
/*     */   public String getAplicaEp() {
/* 107 */     return this.aplicaEp;
/*     */   }
/*     */   
/* 110 */   public void setAplicaEp(String aplicaEp) { this.aplicaEp = aplicaEp; }
/*     */   
/*     */   public String getAplicaCA() {
/* 113 */     return this.aplicaCA;
/*     */   }
/*     */   
/* 116 */   public void setAplicaCA(String aplicaCA) { this.aplicaCA = aplicaCA; }
/*     */   
/*     */   public String getVersionPromocion() {
/* 119 */     return this.versionPromocion;
/*     */   }
/*     */   
/* 122 */   public void setVersionPromocion(String versionPromocion) { this.versionPromocion = versionPromocion; }
/*     */ }


/* Location:              /opt/trabajo/claro/Guatemala/IS/CO/ClaroClub.ear!/ClaroClubEJBClient.jar!/com/claro/transfer/gap/InfoPromocionGapTO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */