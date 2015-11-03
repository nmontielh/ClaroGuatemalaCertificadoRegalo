/*     */ package com.claro.transfer.membresia;
/*     */ 
/*     */ import com.claro.transfer.MensajeTO;
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MembresiaTO
/*     */   extends MensajeTO
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -42109309820494988L;
/*     */   private String cuenta;
/*     */   private String nombreM2K;
/*     */   private String fechaAltaM2K;
/*     */   private String telefono;
/*     */   private String segmento;
/*     */   private String plan;
/*     */   private String descPlan;
/*     */   private String nombre;
/*     */   private String apPaterno;
/*     */   private String apMaterno;
/*     */   private String tipoCalle;
/*     */   private String calle;
/*     */   private String numExterior;
/*     */   private String numInterior;
/*     */   private String colonia;
/*     */   private String ciudad;
/*     */   private String estado;
/*     */   private String codigoPostal;
/*     */   private String pais;
/*     */   private String usuario;
/*     */   private String estatus;
/*     */   private String motivo;
/*     */   private int costo;
/*     */   private int secuencia;
/*     */   private int region;
/*     */   private Date fechaOperacion;
/*     */   
/*     */   public String getPais()
/*     */   {
/*  46 */     return this.pais;
/*     */   }
/*     */   
/*     */   public void setPais(String pais) {
/*  50 */     this.pais = pais;
/*     */   }
/*     */   
/*     */   public String getCuenta() {
/*  54 */     return this.cuenta;
/*     */   }
/*     */   
/*     */   public void setCuenta(String cuenta) {
/*  58 */     this.cuenta = cuenta;
/*     */   }
/*     */   
/*     */   public String getNombreM2K() {
/*  62 */     return this.nombreM2K;
/*     */   }
/*     */   
/*     */   public void setNombreM2K(String nombreM2K) {
/*  66 */     this.nombreM2K = nombreM2K;
/*     */   }
/*     */   
/*     */   public String getFechaAltaM2K() {
/*  70 */     return this.fechaAltaM2K;
/*     */   }
/*     */   
/*     */   public void setFechaAltaM2K(String fechaAltaM2K) {
/*  74 */     this.fechaAltaM2K = fechaAltaM2K;
/*     */   }
/*     */   
/*     */   public String getTelefono() {
/*  78 */     return this.telefono;
/*     */   }
/*     */   
/*     */   public void setTelefono(String telefono) {
/*  82 */     this.telefono = telefono;
/*     */   }
/*     */   
/*     */   public String getSegmento() {
/*  86 */     return this.segmento;
/*     */   }
/*     */   
/*     */   public void setSegmento(String segmento) {
/*  90 */     this.segmento = segmento;
/*     */   }
/*     */   
/*     */   public String getPlan() {
/*  94 */     return this.plan;
/*     */   }
/*     */   
/*     */   public void setPlan(String plan) {
/*  98 */     this.plan = plan;
/*     */   }
/*     */   
/*     */   public String getDescPlan() {
/* 102 */     return this.descPlan;
/*     */   }
/*     */   
/*     */   public void setDescPlan(String descPlan) {
/* 106 */     this.descPlan = descPlan;
/*     */   }
/*     */   
/*     */   public String getNombre() {
/* 110 */     return this.nombre;
/*     */   }
/*     */   
/*     */   public void setNombre(String nombre) {
/* 114 */     this.nombre = nombre;
/*     */   }
/*     */   
/*     */   public String getApPaterno() {
/* 118 */     return this.apPaterno;
/*     */   }
/*     */   
/*     */   public void setApPaterno(String apPaterno) {
/* 122 */     this.apPaterno = apPaterno;
/*     */   }
/*     */   
/*     */   public String getApMaterno() {
/* 126 */     return this.apMaterno;
/*     */   }
/*     */   
/*     */   public void setApMaterno(String apMaterno) {
/* 130 */     this.apMaterno = apMaterno;
/*     */   }
/*     */   
/*     */   public String getTipoCalle() {
/* 134 */     return this.tipoCalle;
/*     */   }
/*     */   
/*     */   public void setTipoCalle(String tipoCalle) {
/* 138 */     this.tipoCalle = tipoCalle;
/*     */   }
/*     */   
/*     */   public String getCalle() {
/* 142 */     return this.calle;
/*     */   }
/*     */   
/*     */   public void setCalle(String calle) {
/* 146 */     this.calle = calle;
/*     */   }
/*     */   
/*     */   public String getNumExterior() {
/* 150 */     return this.numExterior;
/*     */   }
/*     */   
/*     */   public void setNumExterior(String numExterior) {
/* 154 */     this.numExterior = numExterior;
/*     */   }
/*     */   
/*     */   public String getNumInterior() {
/* 158 */     return this.numInterior;
/*     */   }
/*     */   
/*     */   public void setNumInterior(String numInterior) {
/* 162 */     this.numInterior = numInterior;
/*     */   }
/*     */   
/*     */   public String getColonia() {
/* 166 */     return this.colonia;
/*     */   }
/*     */   
/*     */   public void setColonia(String colonia) {
/* 170 */     this.colonia = colonia;
/*     */   }
/*     */   
/*     */   public String getCiudad() {
/* 174 */     return this.ciudad;
/*     */   }
/*     */   
/*     */   public void setCiudad(String ciudad) {
/* 178 */     this.ciudad = ciudad;
/*     */   }
/*     */   
/*     */   public String getEstado() {
/* 182 */     return this.estado;
/*     */   }
/*     */   
/*     */   public void setEstado(String estado) {
/* 186 */     this.estado = estado;
/*     */   }
/*     */   
/*     */   public String getCodigoPostal() {
/* 190 */     return this.codigoPostal;
/*     */   }
/*     */   
/*     */   public void setCodigoPostal(String codigoPostal) {
/* 194 */     this.codigoPostal = codigoPostal;
/*     */   }
/*     */   
/*     */   public String getUsuario() {
/* 198 */     return this.usuario;
/*     */   }
/*     */   
/*     */   public void setUsuario(String usuario) {
/* 202 */     this.usuario = usuario;
/*     */   }
/*     */   
/*     */   public String getEstatus() {
/* 206 */     return this.estatus;
/*     */   }
/*     */   
/*     */   public void setEstatus(String estatus) {
/* 210 */     this.estatus = estatus;
/*     */   }
/*     */   
/*     */   public String getMotivo() {
/* 214 */     return this.motivo;
/*     */   }
/*     */   
/*     */   public void setMotivo(String motivo) {
/* 218 */     this.motivo = motivo;
/*     */   }
/*     */   
/*     */   public int getCosto() {
/* 222 */     return this.costo;
/*     */   }
/*     */   
/*     */   public void setCosto(int costo) {
/* 226 */     this.costo = costo;
/*     */   }
/*     */   
/*     */   public int getSecuencia() {
/* 230 */     return this.secuencia;
/*     */   }
/*     */   
/*     */   public void setSecuencia(int secuencia) {
/* 234 */     this.secuencia = secuencia;
/*     */   }
/*     */   
/*     */   public int getRegion() {
/* 238 */     return this.region;
/*     */   }
/*     */   
/*     */   public void setRegion(int region) {
/* 242 */     this.region = region;
/*     */   }
/*     */   
/*     */   public Date getFechaOperacion() {
/* 246 */     return this.fechaOperacion;
/*     */   }
/*     */   
/*     */   public void setFechaOperacion(Date fechaOperacion) {
/* 250 */     this.fechaOperacion = fechaOperacion;
/*     */   }
/*     */   
/*     */ 
/*     */   public String toString()
/*     */   {
/* 256 */     return 
/*     */     
/*     */ 
/* 259 */       this.apMaterno + "|" + this.apPaterno + "|" + this.calle + "|" + this.ciudad + "|" + this.codigoPostal + "|" + this.colonia + "|" + this.costo + "|" + this.cuenta + "|" + this.descPlan + "|" + this.estado + "|" + this.estatus + "|" + this.fechaAltaM2K + "|" + this.motivo + "|" + this.nombre + "|" + this.nombreM2K + "|" + this.numExterior + "|" + this.numInterior + "|" + this.pais + "|" + this.plan + "|" + this.region + "|" + this.secuencia + "|" + this.segmento + "|" + this.telefono + "|" + this.usuario + "|";
/*     */   }
/*     */ }


/* Location:              /opt/trabajo/claro/Guatemala/IS/CO/ClaroClub.ear!/ClaroClubEJBClient.jar!/com/claro/transfer/membresia/MembresiaTO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */