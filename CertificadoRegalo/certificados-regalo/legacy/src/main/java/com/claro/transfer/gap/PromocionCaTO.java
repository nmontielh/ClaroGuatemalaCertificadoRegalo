/*     */ package com.claro.transfer.gap;
/*     */ 
/*     */ import com.telcel.gscrm.dccrm.admin.promo.ws.CatalogoTO;
/*     */ import java.io.Serializable;
/*     */ import java.math.BigDecimal;
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
/*     */ 
/*     */ public class PromocionCaTO
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -8008442086123602365L;
/*     */   private int idPromocionCA;
/*     */   private int idPromocion;
/*     */   private int versionPromocion;
/*     */   private String aplicaPromoGap;
/*     */   private String bonoDescuento;
/*     */   private String productoM2K;
/*     */   private String nombrePromocion;
/*     */   private String aplicaEp;
/*     */   private int modoSuscripcionNuevo;
/*     */   private int modoSuscripcionAnterior;
/*     */   private String modelo;
/*     */   private String marca;
/*     */   private int idGrupoPlanNuevo;
/*     */   private int idGrupoPlanAnterior;
/*     */   private int plazoFzoNuevo;
/*     */   private int plazoFzoAnterior;
/*     */   private BigDecimal cantidadDescuento;
/*     */   private String idCampania;
/*     */   private String idOferta;
/*     */   private CatalogoTO[] equipos;
/*     */   
/*     */   public int getIdPromocionCA()
/*     */   {
/*  44 */     return this.idPromocionCA;
/*     */   }
/*     */   
/*  47 */   public void setIdPromocionCA(int idPromocionCA) { this.idPromocionCA = idPromocionCA; }
/*     */   
/*     */   public int getIdPromocion() {
/*  50 */     return this.idPromocion;
/*     */   }
/*     */   
/*  53 */   public void setIdPromocion(int idPromocion) { this.idPromocion = idPromocion; }
/*     */   
/*     */   public int getVersionPromocion() {
/*  56 */     return this.versionPromocion;
/*     */   }
/*     */   
/*  59 */   public void setVersionPromocion(int versionPromocion) { this.versionPromocion = versionPromocion; }
/*     */   
/*     */   public String getModelo() {
/*  62 */     return this.modelo;
/*     */   }
/*     */   
/*  65 */   public void setModelo(String modelo) { this.modelo = modelo; }
/*     */   
/*     */   public String getMarca() {
/*  68 */     return this.marca;
/*     */   }
/*     */   
/*  71 */   public void setMarca(String marca) { this.marca = marca; }
/*     */   
/*     */   public int getIdGrupoPlanNuevo() {
/*  74 */     return this.idGrupoPlanNuevo;
/*     */   }
/*     */   
/*  77 */   public void setIdGrupoPlanNuevo(int idGrupoPlanNuevo) { this.idGrupoPlanNuevo = idGrupoPlanNuevo; }
/*     */   
/*     */   public int getIdGrupoPlanAnterior() {
/*  80 */     return this.idGrupoPlanAnterior;
/*     */   }
/*     */   
/*  83 */   public void setIdGrupoPlanAnterior(int idGrupoPlanAnterior) { this.idGrupoPlanAnterior = idGrupoPlanAnterior; }
/*     */   
/*     */   public int getPlazoFzoNuevo() {
/*  86 */     return this.plazoFzoNuevo;
/*     */   }
/*     */   
/*  89 */   public void setPlazoFzoNuevo(int plazoFzoNuevo) { this.plazoFzoNuevo = plazoFzoNuevo; }
/*     */   
/*     */   public int getPlazoFzoAnterior() {
/*  92 */     return this.plazoFzoAnterior;
/*     */   }
/*     */   
/*  95 */   public void setPlazoFzoAnterior(int plazoFzoAnterior) { this.plazoFzoAnterior = plazoFzoAnterior; }
/*     */   
/*     */   public String getBonoDescuento()
/*     */   {
/*  99 */     return this.bonoDescuento;
/*     */   }
/*     */   
/* 102 */   public void setBonoDescuento(String bonoDescuento) { this.bonoDescuento = bonoDescuento; }
/*     */   
/*     */   public String getProductoM2K() {
/* 105 */     return this.productoM2K;
/*     */   }
/*     */   
/* 108 */   public void setProductoM2K(String productoM2K) { this.productoM2K = productoM2K; }
/*     */   
/*     */ 
/*     */   public String toString()
/*     */   {
/* 113 */     return 
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
/*     */ 
/*     */ 
/*     */ 
/* 127 */       "\nidPromocionCA: " + this.idPromocionCA + "\nidPromocion: " + this.idPromocion + "\nversionPromocion: " + this.versionPromocion + "\nmodelo: " + this.modelo + "\nmarca: " + this.marca + "\nnmodelo: " + this.modelo + "\nidGrupoPlanNuevo: " + this.idGrupoPlanNuevo + "\nidGrupoPlanAnterior: " + this.idGrupoPlanAnterior + "\nplazoFzoAnterior: " + this.plazoFzoAnterior + "\nbonoDescuento: " + this.bonoDescuento + "\ncantidadDescuento: " + this.cantidadDescuento + "\nmodoSuscripcionNuevo: " + this.modoSuscripcionNuevo + "\nmodoSuscripcionAnterior: " + this.modoSuscripcionAnterior + "\naplicaPromoGap: " + this.aplicaPromoGap + "\nnombrePromocion: " + this.nombrePromocion;
/*     */   }
/*     */   
/* 130 */   public BigDecimal getCantidadDescuento() { return this.cantidadDescuento; }
/*     */   
/*     */   public void setCantidadDescuento(BigDecimal cantidadDescuento) {
/* 133 */     this.cantidadDescuento = cantidadDescuento;
/*     */   }
/*     */   
/* 136 */   public int getModoSuscripcionNuevo() { return this.modoSuscripcionNuevo; }
/*     */   
/*     */   public void setModoSuscripcionNuevo(int modoSuscripcionNuevo) {
/* 139 */     this.modoSuscripcionNuevo = modoSuscripcionNuevo;
/*     */   }
/*     */   
/* 142 */   public int getModoSuscripcionAnterior() { return this.modoSuscripcionAnterior; }
/*     */   
/*     */   public void setModoSuscripcionAnterior(int modoSuscripcionAnterior) {
/* 145 */     this.modoSuscripcionAnterior = modoSuscripcionAnterior;
/*     */   }
/*     */   
/* 148 */   public String getAplicaPromoGap() { return this.aplicaPromoGap; }
/*     */   
/*     */   public void setAplicaPromoGap(String aplicaPromoGap) {
/* 151 */     this.aplicaPromoGap = aplicaPromoGap;
/*     */   }
/*     */   
/* 154 */   public String getNombrePromocion() { return this.nombrePromocion; }
/*     */   
/*     */   public void setNombrePromocion(String nombrePromocion) {
/* 157 */     this.nombrePromocion = nombrePromocion;
/*     */   }
/*     */   
/* 160 */   public String getAplicaEp() { return this.aplicaEp; }
/*     */   
/*     */   public void setAplicaEp(String aplicaEp) {
/* 163 */     this.aplicaEp = aplicaEp;
/*     */   }
/*     */   
/* 166 */   public String getIdCampania() { return this.idCampania; }
/*     */   
/*     */   public void setIdCampania(String idCampania) {
/* 169 */     this.idCampania = idCampania;
/*     */   }
/*     */   
/* 172 */   public String getIdOferta() { return this.idOferta; }
/*     */   
/*     */   public void setIdOferta(String idOferta) {
/* 175 */     this.idOferta = idOferta;
/*     */   }
/*     */   
/* 178 */   public CatalogoTO[] getEquipos() { return this.equipos; }
/*     */   
/*     */   public void setEquipos(CatalogoTO[] equipos) {
/* 181 */     this.equipos = equipos;
/*     */   }
/*     */ }


/* Location:              /opt/trabajo/claro/Guatemala/IS/CO/ClaroClub.ear!/ClaroClubEJBClient.jar!/com/claro/transfer/gap/PromocionCaTO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */