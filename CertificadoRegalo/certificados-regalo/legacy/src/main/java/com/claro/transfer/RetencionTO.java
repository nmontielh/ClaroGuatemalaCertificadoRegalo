/*     */ package com.claro.transfer;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RetencionTO
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -2876315591050173269L;
/*     */   private int PorcARPU;
/*     */   private int PorcAntig;
/*     */   private int PorcCob;
/*     */   private int ValorCupon;
/*     */   private int iValorCuponExtra;
/*     */   private String telefono;
/*     */   private String cuenta;
/*     */   private String fechaOperacion;
/*     */   private String folio;
/*     */   private String vCertif;
/*     */   private String vCentifextra;
/*     */   private String motivo;
/*     */   private String fechaCaduca;
/*     */   private String estatus;
/*     */   private MensajeTO mensajeTO;
/*  31 */   private int iIndAntig = 0;
/*     */   private ArrayList<MotivoTO> motivos;
/*     */   
/*     */   public int getIIndAntig() {
/*  35 */     return this.iIndAntig;
/*     */   }
/*     */   
/*  38 */   public void setIIndAntig(int indAntig) { this.iIndAntig = indAntig; }
/*     */   
/*     */   public int getPorcARPU() {
/*  41 */     return this.PorcARPU;
/*     */   }
/*     */   
/*  44 */   public void setPorcARPU(int porcARPU) { this.PorcARPU = porcARPU; }
/*     */   
/*     */   public int getPorcAntig() {
/*  47 */     return this.PorcAntig;
/*     */   }
/*     */   
/*  50 */   public void setPorcAntig(int porcAntig) { this.PorcAntig = porcAntig; }
/*     */   
/*     */   public int getPorcCob() {
/*  53 */     return this.PorcCob;
/*     */   }
/*     */   
/*  56 */   public void setPorcCob(int porcCob) { this.PorcCob = porcCob; }
/*     */   
/*     */   public int getValorCupon() {
/*  59 */     return this.ValorCupon;
/*     */   }
/*     */   
/*  62 */   public void setValorCupon(int valorCupon) { this.ValorCupon = valorCupon; }
/*     */   
/*     */   public int getIValorCuponExtra() {
/*  65 */     return this.iValorCuponExtra;
/*     */   }
/*     */   
/*  68 */   public void setIValorCuponExtra(int valorCuponExtra) { this.iValorCuponExtra = valorCuponExtra; }
/*     */   
/*     */   public String getTelefono() {
/*  71 */     return this.telefono;
/*     */   }
/*     */   
/*  74 */   public void setTelefono(String telefono) { this.telefono = telefono; }
/*     */   
/*     */   public String getCuenta() {
/*  77 */     return this.cuenta;
/*     */   }
/*     */   
/*  80 */   public void setCuenta(String cuenta) { this.cuenta = cuenta; }
/*     */   
/*     */   public String getFechaOperacion() {
/*  83 */     return this.fechaOperacion;
/*     */   }
/*     */   
/*  86 */   public void setFechaOperacion(String fechaOperacion) { this.fechaOperacion = fechaOperacion; }
/*     */   
/*     */   public String getFolio() {
/*  89 */     return this.folio;
/*     */   }
/*     */   
/*  92 */   public void setFolio(String folio) { this.folio = folio; }
/*     */   
/*     */   public String getVCertif() {
/*  95 */     return this.vCertif;
/*     */   }
/*     */   
/*  98 */   public void setVCertif(String certif) { this.vCertif = certif; }
/*     */   
/*     */   public String getVCentifextra() {
/* 101 */     return this.vCentifextra;
/*     */   }
/*     */   
/* 104 */   public void setVCentifextra(String centifextra) { this.vCentifextra = centifextra; }
/*     */   
/*     */   public String getMotivo() {
/* 107 */     return this.motivo;
/*     */   }
/*     */   
/* 110 */   public void setMotivo(String motivo) { this.motivo = motivo; }
/*     */   
/*     */   public String getFechaCaduca() {
/* 113 */     return this.fechaCaduca;
/*     */   }
/*     */   
/* 116 */   public void setFechaCaduca(String fechaCaduca) { this.fechaCaduca = fechaCaduca; }
/*     */   
/*     */   public String getEstatus() {
/* 119 */     return this.estatus;
/*     */   }
/*     */   
/* 122 */   public void setEstatus(String estatus) { this.estatus = estatus; }
/*     */   
/*     */   public MensajeTO getMensajeTO() {
/* 125 */     return this.mensajeTO;
/*     */   }
/*     */   
/* 128 */   public void setMensajeTO(MensajeTO mensajeTO) { this.mensajeTO = mensajeTO; }
/*     */   
/*     */   public ArrayList<MotivoTO> getMotivos() {
/* 131 */     return this.motivos;
/*     */   }
/*     */   
/* 134 */   public void setMotivos(ArrayList<MotivoTO> motivos) { this.motivos = motivos; }
/*     */ }


/* Location:              /opt/trabajo/claro/Guatemala/IS/CO/ClaroClub.ear!/ClaroClubEJBClient.jar!/com/claro/transfer/RetencionTO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */