/*     */ package com.claro.transfer;
/*     */ 
/*     */ import com.claro.util.Redencion;
/*     */ import java.io.Serializable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ParametrosTO
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 2094039818724429749L;
/*     */   private String folio;
/*     */   private String telefono;
/*     */   private String cuenta;
/*     */   private String planNvo;
/*     */   private String planAnt;
/*     */   private String tipoRed;
/*     */   private String formaRed;
/*     */   private String mesCareg;
/*     */   private String addCareg;
/*     */   private String usuariMovimiento;
/*     */   private String comentario;
/*     */   private String descFormaRed;
/*     */   private String descTipoRed;
/*     */   private String estatus;
/*     */   private String bRedencionAnct;
/*     */   private String numeroSerieT;
/*     */   private String numeroSerieP;
/*     */   private String iccid;
/*     */   private String ipAddress;
/*     */   private int opcion;
/*     */   private int region;
/*     */   private int puntosTotalesTmp;
/*     */   private int secuencia;
/*     */   private boolean consultaGeneral;
/*     */   private String fzaVentas;
/*     */   
/*     */   public boolean isConsultaGeneral()
/*     */   {
/*  46 */     return this.consultaGeneral;
/*     */   }
/*     */   
/*     */   public void setConsultaGeneral(boolean consultaGeneral) {
/*  50 */     this.consultaGeneral = consultaGeneral;
/*     */   }
/*     */   
/*     */   public String getNumeroSerieT() {
/*  54 */     return this.numeroSerieT;
/*     */   }
/*     */   
/*     */   public void setNumeroSerieT(String numeroSerieT) {
/*  58 */     this.numeroSerieT = numeroSerieT;
/*     */   }
/*     */   
/*     */   public String getNumeroSerieP() {
/*  62 */     return this.numeroSerieP;
/*     */   }
/*     */   
/*     */   public void setNumeroSerieP(String numeroSerieP) {
/*  66 */     this.numeroSerieP = numeroSerieP;
/*     */   }
/*     */   
/*     */   public String getIccid() {
/*  70 */     return this.iccid;
/*     */   }
/*     */   
/*     */   public void setIccid(String iccid) {
/*  74 */     this.iccid = iccid;
/*     */   }
/*     */   
/*     */   public int getOpcion() {
/*  78 */     return this.opcion;
/*     */   }
/*     */   
/*     */   public void setOpcion(int opcion) {
/*  82 */     this.opcion = opcion;
/*     */   }
/*     */   
/*     */   public String getBRedencionAnct() {
/*  86 */     return this.bRedencionAnct;
/*     */   }
/*     */   
/*     */   public void setBRedencionAnct(String redencionAnct) {
/*  90 */     this.bRedencionAnct = redencionAnct;
/*     */   }
/*     */   
/*     */   public String getEstatus() {
/*  94 */     return this.estatus;
/*     */   }
/*     */   
/*     */   public void setEstatus(String estatus) {
/*  98 */     this.estatus = estatus;
/*     */   }
/*     */   
/*     */   public String getDescTipoRed() {
/* 102 */     return this.descTipoRed;
/*     */   }
/*     */   
/*     */   public void setDescTipoRed(String descTipoRed) {
/* 106 */     this.descTipoRed = descTipoRed;
/*     */   }
/*     */   
/*     */   public String getDescTipoRed(String tipoRed) {
/* 110 */     return Redencion.getDescTipoRed(tipoRed);
/*     */   }
/*     */   
/*     */   public String getDescFormaRed(String formaRed) {
/* 114 */     return Redencion.getDescFormaRed(formaRed);
/*     */   }
/*     */   
/*     */   public String getDescFormaRed() {
/* 118 */     return this.descFormaRed;
/*     */   }
/*     */   
/*     */   public void setDescFormaRed(String descFormaRed) {
/* 122 */     this.descFormaRed = descFormaRed;
/*     */   }
/*     */   
/*     */   public String getComentario() {
/* 126 */     return this.comentario;
/*     */   }
/*     */   
/* 129 */   public void setComentario(String comentario) { this.comentario = comentario; }
/*     */   
/*     */   public String getUsuariMovimiento() {
/* 132 */     return this.usuariMovimiento;
/*     */   }
/*     */   
/* 135 */   public void setUsuariMovimiento(String usuariMovimiento) { this.usuariMovimiento = usuariMovimiento; }
/*     */   
/*     */   public String getFormaRed() {
/* 138 */     return this.formaRed;
/*     */   }
/*     */   
/* 141 */   public void setFormaRed(String formaRed) { this.formaRed = formaRed; }
/*     */   
/*     */   public String getTelefono()
/*     */   {
/* 145 */     if ((this.telefono != null) && (!this.telefono.trim().equals("")))
/* 146 */       return this.telefono.trim();
/* 147 */     return null;
/*     */   }
/*     */   
/* 150 */   public void setTelefono(String telefono) { this.telefono = telefono; }
/*     */   
/*     */   public String getCuenta() {
/* 153 */     if ((this.cuenta != null) && (!this.cuenta.trim().equals("")))
/* 154 */       return this.cuenta.trim();
/* 155 */     return null;
/*     */   }
/*     */   
/* 158 */   public void setCuenta(String cuenta) { this.cuenta = cuenta; }
/*     */   
/*     */   public String getPlanNvo() {
/* 161 */     return this.planNvo;
/*     */   }
/*     */   
/* 164 */   public void setPlanNvo(String planNvo) { this.planNvo = planNvo; }
/*     */   
/*     */   public String getTipoRed() {
/* 167 */     return this.tipoRed;
/*     */   }
/*     */   
/* 170 */   public void setTipoRed(String tipoRed) { this.tipoRed = tipoRed; }
/*     */   
/*     */ 
/* 173 */   public String getMesCareg() { return this.mesCareg; }
/*     */   
/*     */   public void setMesCareg(String mesCareg) {
/* 176 */     if ((mesCareg == null) || (mesCareg.trim().equals(""))) this.mesCareg = "0"; else
/* 177 */       this.mesCareg = mesCareg;
/*     */   }
/*     */   
/* 180 */   public String getAddCareg() { return this.addCareg; }
/*     */   
/*     */   public void setAddCareg(String addCareg) {
/* 183 */     if ((addCareg == null) || (addCareg.trim().equals(""))) this.addCareg = "0"; else
/* 184 */       this.addCareg = addCareg;
/*     */   }
/*     */   
/*     */   public int getRegion() {
/* 188 */     return this.region;
/*     */   }
/*     */   
/* 191 */   public void setRegion(int region) { this.region = region; }
/*     */   
/*     */   public String getFolio() {
/* 194 */     return this.folio;
/*     */   }
/*     */   
/* 197 */   public void setFolio(String folio) { this.folio = folio; }
/*     */   
/*     */   public int getSecuencia() {
/* 200 */     return this.secuencia;
/*     */   }
/*     */   
/* 203 */   public void setSecuencia(int secuencia) { this.secuencia = secuencia; }
/*     */   
/*     */   public String getPlanAnt()
/*     */   {
/* 207 */     return this.planAnt;
/*     */   }
/*     */   
/* 210 */   public void setPlanAnt(String planAnt) { this.planAnt = planAnt; }
/*     */   
/*     */   public int getPuntosTotalesTmp()
/*     */   {
/* 214 */     return this.puntosTotalesTmp;
/*     */   }
/*     */   
/*     */   public void setPuntosTotalesTmp(int puntosTotalesTmp) {
/* 218 */     this.puntosTotalesTmp = puntosTotalesTmp;
/*     */   }
/*     */   
/*     */   public String getIpAddress() {
/* 222 */     return this.ipAddress;
/*     */   }
/*     */   
/*     */   public void setIpAddress(String ipAddress) {
/* 226 */     this.ipAddress = ipAddress;
/*     */   }
/*     */   
/* 229 */   public String getFzaVentas() { return this.fzaVentas; }
/*     */   
/*     */   public void setFzaVentas(String fzaVentas) {
/* 232 */     this.fzaVentas = fzaVentas;
/*     */   }
/*     */ }


/* Location:              /opt/trabajo/claro/Guatemala/IS/CO/ClaroClub.ear!/ClaroClubEJBClient.jar!/com/claro/transfer/ParametrosTO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */