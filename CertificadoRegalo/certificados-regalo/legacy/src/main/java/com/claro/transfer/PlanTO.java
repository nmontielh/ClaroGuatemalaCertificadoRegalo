/*     */ package com.claro.transfer;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public class PlanTO
/*     */   extends MensajeTO
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -8964901768875452603L;
/*     */   private String idPlanNuevo;
/*     */   private int segmento;
/*     */   private int idRegion;
/*     */   private int idGrupoPromocion;
/*     */   private String descripcion;
/*     */   private String tecnologia;
/*     */   private String banMixto;
/*     */   private String modalidad;
/*     */   private String banSisact;
/*     */   private int adendum;
/*     */   private int renta;
/*     */   private int banRedencion;
/*     */   private String estatus;
/*     */   private String banRedencionAnct;
/*     */   private String descSegmento;
/*     */   private int adendumNvo;
/*     */   private String tipoPromocion;
/*     */   private int ptosMinimos;
/*     */   private String tipoPlan;
/*     */   private int bonoXSegmento;
/*     */   
/*     */   public PlanTO() {}
/*     */   
/*     */   public PlanTO(String[] columnas)
/*     */   {
/*  35 */     this.idPlanNuevo = columnas[0];
/*  36 */     this.segmento = Integer.parseInt(columnas[1]);
/*  37 */     this.idRegion = Integer.parseInt(columnas[2]);
/*  38 */     this.idGrupoPromocion = Integer.parseInt(columnas[3]);
/*  39 */     this.descripcion = columnas[4];
/*  40 */     this.tecnologia = columnas[5];
/*  41 */     this.banMixto = columnas[6];
/*  42 */     this.modalidad = columnas[7];
/*  43 */     this.banSisact = columnas[8];
/*  44 */     if (columnas[9] != null)
/*  45 */       this.adendum = Integer.parseInt(columnas[9]);
/*  46 */     if (columnas[10] != null)
/*  47 */       this.renta = Integer.parseInt(columnas[10]);
/*  48 */     if (columnas[11] != null)
/*  49 */       this.banRedencion = Integer.parseInt(columnas[11]);
/*  50 */     this.estatus = columnas[12];
/*  51 */     this.banRedencionAnct = columnas[13];
/*  52 */     this.tipoPlan = columnas[14];
/*     */   }
/*     */   
/*     */   public String getDescSegmento() {
/*  56 */     return this.descSegmento;
/*     */   }
/*     */   
/*  59 */   public void setDescSegmento(String descSegmento) { this.descSegmento = descSegmento; }
/*     */   
/*     */   public int getPtosMinimos() {
/*  62 */     return this.ptosMinimos;
/*     */   }
/*     */   
/*  65 */   public void setPtosMinimos(int ptosMinimos) { this.ptosMinimos = ptosMinimos; }
/*     */   
/*     */   public String getTipoPlan() {
/*  68 */     return this.tipoPlan;
/*     */   }
/*     */   
/*  71 */   public void setTipoPlan(String tipoPlan) { this.tipoPlan = tipoPlan; }
/*     */   
/*     */   public int getBonoXSegmento() {
/*  74 */     return this.bonoXSegmento;
/*     */   }
/*     */   
/*  77 */   public void setBonoXSegmento(int bonoXSegmento) { this.bonoXSegmento = bonoXSegmento; }
/*     */   
/*     */   public String getTipoPromocion() {
/*  80 */     return this.tipoPromocion;
/*     */   }
/*     */   
/*  83 */   public void setTipoPromocion(String tipoPromocion) { this.tipoPromocion = tipoPromocion; }
/*     */   
/*     */   public int getIdGrupoPromocion() {
/*  86 */     return this.idGrupoPromocion;
/*     */   }
/*     */   
/*  89 */   public void setIdGrupoPromocion(int idGrupoPromocion) { this.idGrupoPromocion = idGrupoPromocion; }
/*     */   
/*     */   public int getSegmento() {
/*  92 */     return this.segmento;
/*     */   }
/*     */   
/*  95 */   public void setSegmento(int segmento) { this.segmento = segmento; }
/*     */   
/*     */   public int getIdRegion() {
/*  98 */     return this.idRegion;
/*     */   }
/*     */   
/* 101 */   public void setIdRegion(int idRegion) { this.idRegion = idRegion; }
/*     */   
/*     */   public int getBanRedencion() {
/* 104 */     return this.banRedencion;
/*     */   }
/*     */   
/* 107 */   public void setBanRedencion(int redencion) { this.banRedencion = redencion; }
/*     */   
/*     */   public int getAdendum() {
/* 110 */     return this.adendum;
/*     */   }
/*     */   
/* 113 */   public void setAdendum(int adendum) { this.adendum = adendum; }
/*     */   
/*     */   public int getRenta() {
/* 116 */     return this.renta;
/*     */   }
/*     */   
/* 119 */   public void setRenta(int renta) { this.renta = renta; }
/*     */   
/*     */   public String getIdPlanNuevo() {
/* 122 */     return this.idPlanNuevo;
/*     */   }
/*     */   
/* 125 */   public void setIdPlanNuevo(String idPlanNuevo) { this.idPlanNuevo = idPlanNuevo; }
/*     */   
/*     */   public String getDescripcion() {
/* 128 */     return this.descripcion;
/*     */   }
/*     */   
/* 131 */   public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
/*     */   
/*     */   public String getTecnologia() {
/* 134 */     return this.tecnologia;
/*     */   }
/*     */   
/* 137 */   public void setTecnologia(String tecnologia) { this.tecnologia = tecnologia; }
/*     */   
/*     */   public String getBanMixto() {
/* 140 */     return this.banMixto;
/*     */   }
/*     */   
/* 143 */   public void setBanMixto(String mixto) { this.banMixto = mixto; }
/*     */   
/*     */   public String getModalidad() {
/* 146 */     return this.modalidad;
/*     */   }
/*     */   
/* 149 */   public void setModalidad(String modalidad) { this.modalidad = modalidad; }
/*     */   
/*     */   public String getBanSisact() {
/* 152 */     return this.banSisact;
/*     */   }
/*     */   
/* 155 */   public void setBanSisact(String sisact) { this.banSisact = sisact; }
/*     */   
/*     */   public String getEstatus() {
/* 158 */     return this.estatus;
/*     */   }
/*     */   
/* 161 */   public void setEstatus(String estatus) { this.estatus = estatus; }
/*     */   
/*     */   public String getBanRedencionAnct() {
/* 164 */     return this.banRedencionAnct;
/*     */   }
/*     */   
/* 167 */   public void setBanRedencionAnct(String redencionAnct) { this.banRedencionAnct = redencionAnct; }
/*     */   
/*     */   public int getAdendumNvo() {
/* 170 */     return this.adendumNvo;
/*     */   }
/*     */   
/* 173 */   public void setAdendumNvo(int adendumNvo) { this.adendumNvo = adendumNvo; }
/*     */   
/*     */ 
/*     */   public String toString()
/*     */   {
/* 178 */     StringBuffer buffer = new StringBuffer();
/* 179 */     buffer.append(this.idPlanNuevo).append(",").append(this.segmento).append(",");
/* 180 */     buffer.append(this.idRegion).append(",").append(this.idGrupoPromocion).append(",");
/* 181 */     buffer.append(this.descripcion).append(",").append(this.tecnologia).append(",");
/* 182 */     buffer.append(this.banMixto).append(",").append(this.modalidad).append(",");
/* 183 */     buffer.append(this.banSisact).append(",").append(this.adendum).append(",");
/* 184 */     buffer.append(this.renta).append(",").append(this.banRedencion).append(",");
/* 185 */     buffer.append(this.estatus).append(",").append(this.banRedencionAnct).append(",");
/* 186 */     buffer.append(this.tipoPlan);
/* 187 */     return buffer.toString();
/*     */   }
/*     */   
/*     */   public String getLineaLog() {
/* 191 */     StringBuffer linea = new StringBuffer();
/* 192 */     linea.append(this.idPlanNuevo).append("@").append(this.descripcion).append("@");
/* 193 */     linea.append(this.segmento).append("@").append(this.idRegion).append("@");
/* 194 */     linea.append(this.ptosMinimos).append("@").append(this.tecnologia).append("@");
/* 195 */     linea.append(this.banMixto).append("@").append(this.estatus).append("@");
/* 196 */     linea.append(this.modalidad).append("@").append(this.banSisact).append("@");
/* 197 */     linea.append(this.renta).append("@").append(this.adendum).append("@");
/* 198 */     linea.append(this.tipoPlan).append("@").append(this.idGrupoPromocion).append("@");
/* 199 */     linea.append(this.banRedencion).append("@");
/* 200 */     if (this.banRedencionAnct != null) {
/* 201 */       linea.append(this.banRedencionAnct);
/*     */     } else {
/* 203 */       linea.append(" ");
/*     */     }
/* 205 */     linea.append("@").append(this.tipoPlan).append("@");
/*     */     
/* 207 */     return linea.toString();
/*     */   }
/*     */ }


/* Location:              /opt/trabajo/claro/Guatemala/IS/CO/ClaroClub.ear!/ClaroClubEJBClient.jar!/com/claro/transfer/PlanTO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */