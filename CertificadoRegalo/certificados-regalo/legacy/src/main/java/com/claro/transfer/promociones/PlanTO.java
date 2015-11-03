/*     */ package com.claro.transfer.promociones;
/*     */ 
/*     */ import com.claro.transfer.MensajeTO;
/*     */ import java.io.Serializable;
/*     */ 
/*     */ 
/*     */ public class PlanTO
/*     */   extends MensajeTO
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 3111318816788434250L;
/*     */   private String idPlanNuevo;
/*     */   private String segmento;
/*     */   private String idRegion;
/*     */   private String idGrupoPromocion;
/*     */   private String descripcion;
/*     */   private String tecnologia;
/*     */   private String banMixto;
/*     */   private String modalidad;
/*     */   private String banSisact;
/*     */   private String adendum;
/*     */   private String renta;
/*     */   private String banRedencion;
/*     */   private String estatus;
/*     */   private String banRedencionAnct;
/*     */   private String descSegmento;
/*     */   private String adendumNvo;
/*     */   private String tipoPromocion;
/*     */   private String ptosMinimos;
/*     */   private String tipoPlan;
/*     */   private String bonoXSegmento;
/*     */   
/*     */   public PlanTO() {}
/*     */   
/*     */   public PlanTO(String[] columnas)
/*     */   {
/*  37 */     this.idPlanNuevo = columnas[0];
/*  38 */     this.segmento = columnas[1];
/*  39 */     this.idRegion = columnas[2];
/*  40 */     this.idGrupoPromocion = columnas[3];
/*  41 */     this.descripcion = columnas[4];
/*  42 */     this.tecnologia = columnas[5];
/*  43 */     this.banMixto = columnas[6];
/*  44 */     this.modalidad = columnas[7];
/*  45 */     this.banSisact = columnas[8];
/*  46 */     this.adendum = columnas[9];
/*  47 */     this.renta = columnas[10];
/*  48 */     this.banRedencion = columnas[11];
/*  49 */     this.estatus = columnas[12];
/*  50 */     this.banRedencionAnct = columnas[13];
/*  51 */     this.tipoPlan = columnas[14];
/*     */   }
/*     */   
/*     */   public String getDescSegmento() {
/*  55 */     return this.descSegmento;
/*     */   }
/*     */   
/*  58 */   public void setDescSegmento(String descSegmento) { this.descSegmento = descSegmento; }
/*     */   
/*     */   public String getTipoPlan()
/*     */   {
/*  62 */     return this.tipoPlan;
/*     */   }
/*     */   
/*  65 */   public void setTipoPlan(String tipoPlan) { this.tipoPlan = tipoPlan; }
/*     */   
/*     */   public String getTipoPromocion()
/*     */   {
/*  69 */     return this.tipoPromocion;
/*     */   }
/*     */   
/*  72 */   public void setTipoPromocion(String tipoPromocion) { this.tipoPromocion = tipoPromocion; }
/*     */   
/*     */   public String getIdPlanNuevo() {
/*  75 */     return this.idPlanNuevo;
/*     */   }
/*     */   
/*  78 */   public void setIdPlanNuevo(String idPlanNuevo) { this.idPlanNuevo = idPlanNuevo; }
/*     */   
/*     */   public String getDescripcion() {
/*  81 */     return this.descripcion;
/*     */   }
/*     */   
/*  84 */   public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
/*     */   
/*     */   public String getTecnologia() {
/*  87 */     return this.tecnologia;
/*     */   }
/*     */   
/*  90 */   public void setTecnologia(String tecnologia) { this.tecnologia = tecnologia; }
/*     */   
/*     */   public String getBanMixto() {
/*  93 */     return this.banMixto;
/*     */   }
/*     */   
/*  96 */   public void setBanMixto(String mixto) { this.banMixto = mixto; }
/*     */   
/*     */   public String getModalidad() {
/*  99 */     return this.modalidad;
/*     */   }
/*     */   
/* 102 */   public void setModalidad(String modalidad) { this.modalidad = modalidad; }
/*     */   
/*     */   public String getBanSisact() {
/* 105 */     return this.banSisact;
/*     */   }
/*     */   
/* 108 */   public void setBanSisact(String sisact) { this.banSisact = sisact; }
/*     */   
/*     */   public String getEstatus() {
/* 111 */     return this.estatus;
/*     */   }
/*     */   
/* 114 */   public void setEstatus(String estatus) { this.estatus = estatus; }
/*     */   
/*     */   public String getBanRedencionAnct() {
/* 117 */     return this.banRedencionAnct;
/*     */   }
/*     */   
/* 120 */   public void setBanRedencionAnct(String redencionAnct) { this.banRedencionAnct = redencionAnct; }
/*     */   
/*     */ 
/*     */   public String getSegmento()
/*     */   {
/* 125 */     return this.segmento;
/*     */   }
/*     */   
/*     */   public void setSegmento(String segmento) {
/* 129 */     this.segmento = segmento;
/*     */   }
/*     */   
/*     */   public String getIdRegion() {
/* 133 */     return this.idRegion;
/*     */   }
/*     */   
/*     */   public void setIdRegion(String idRegion) {
/* 137 */     this.idRegion = idRegion;
/*     */   }
/*     */   
/*     */   public String getIdGrupoPromocion() {
/* 141 */     return this.idGrupoPromocion;
/*     */   }
/*     */   
/*     */   public void setIdGrupoPromocion(String idGrupoPromocion) {
/* 145 */     this.idGrupoPromocion = idGrupoPromocion;
/*     */   }
/*     */   
/*     */   public String getAdendum() {
/* 149 */     return this.adendum;
/*     */   }
/*     */   
/*     */   public void setAdendum(String adendum) {
/* 153 */     this.adendum = adendum;
/*     */   }
/*     */   
/*     */   public String getRenta() {
/* 157 */     return this.renta;
/*     */   }
/*     */   
/*     */   public void setRenta(String renta) {
/* 161 */     this.renta = renta;
/*     */   }
/*     */   
/*     */   public String getBanRedencion() {
/* 165 */     return this.banRedencion;
/*     */   }
/*     */   
/*     */   public void setBanRedencion(String banRedencion) {
/* 169 */     this.banRedencion = banRedencion;
/*     */   }
/*     */   
/*     */   public String getAdendumNvo() {
/* 173 */     return this.adendumNvo;
/*     */   }
/*     */   
/*     */   public void setAdendumNvo(String adendumNvo) {
/* 177 */     this.adendumNvo = adendumNvo;
/*     */   }
/*     */   
/*     */   public String getPtosMinimos() {
/* 181 */     return this.ptosMinimos;
/*     */   }
/*     */   
/*     */   public void setPtosMinimos(String ptosMinimos) {
/* 185 */     this.ptosMinimos = ptosMinimos;
/*     */   }
/*     */   
/*     */   public String getBonoXSegmento() {
/* 189 */     return this.bonoXSegmento;
/*     */   }
/*     */   
/*     */   public void setBonoXSegmento(String bonoXSegmento) {
/* 193 */     this.bonoXSegmento = bonoXSegmento;
/*     */   }
/*     */   
/*     */   public String toString()
/*     */   {
/* 198 */     StringBuffer buffer = new StringBuffer();
/* 199 */     buffer.append(this.idPlanNuevo).append(",").append(this.segmento).append(",");
/* 200 */     buffer.append(this.idRegion).append(",").append(this.idGrupoPromocion).append(",");
/* 201 */     buffer.append(this.descripcion).append(",").append(this.tecnologia).append(",");
/* 202 */     buffer.append(this.banMixto).append(",").append(this.modalidad).append(",");
/* 203 */     buffer.append(this.banSisact).append(",").append(this.adendum).append(",");
/* 204 */     buffer.append(this.renta).append(",").append(this.banRedencion).append(",");
/* 205 */     buffer.append(this.estatus).append(",").append(this.banRedencionAnct).append(",");
/* 206 */     buffer.append(this.tipoPlan);
/* 207 */     return buffer.toString();
/*     */   }
/*     */   
/*     */   public String getLineaLog() {
/* 211 */     StringBuffer linea = new StringBuffer();
/* 212 */     linea.append(this.idPlanNuevo).append("@").append(this.descripcion).append("@");
/* 213 */     linea.append(this.segmento).append("@").append(this.idRegion).append("@");
/* 214 */     linea.append(this.ptosMinimos).append("@").append(this.tecnologia).append("@");
/* 215 */     linea.append(this.banMixto).append("@").append(this.estatus).append("@");
/* 216 */     linea.append(this.modalidad).append("@").append(this.banSisact).append("@");
/* 217 */     linea.append(this.renta).append("@").append(this.adendum).append("@");
/* 218 */     linea.append(this.tipoPlan).append("@").append(this.idGrupoPromocion).append("@");
/* 219 */     linea.append(this.banRedencion).append("@");
/* 220 */     if (this.banRedencionAnct != null) {
/* 221 */       linea.append(this.banRedencionAnct);
/*     */     } else {
/* 223 */       linea.append(" ");
/*     */     }
/* 225 */     linea.append("@").append(this.tipoPlan).append("@");
/*     */     
/* 227 */     return linea.toString();
/*     */   }
/*     */ }


/* Location:              /opt/trabajo/claro/Guatemala/IS/CO/ClaroClub.ear!/ClaroClubEJBClient.jar!/com/claro/transfer/promociones/PlanTO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */