/*     */ package com.claro.transfer.promociones;
/*     */ 
/*     */ import com.claro.transfer.MensajeTO;
/*     */ import java.io.Serializable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PromocionTO
/*     */   extends MensajeTO
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 8180465079737604973L;
/*     */   private String idProducto;
/*     */   private String idPromocion;
/*     */   private String idRegion;
/*     */   private String idGrupoPromocion;
/*     */   private String tipoPromocion;
/*     */   private String estatus;
/*     */   private String gpoPromocion;
/*     */   private String descuento;
/*     */   private String descuentoAltoValor;
/*     */   private String descripcion;
/*     */   private String tipoProducto;
/*     */   private String precioLista;
/*     */   private String precioActiva;
/*     */   private String marca;
/*     */   private String modelo;
/*     */   private String URL;
/*     */   private String tecnologia;
/*     */   private String banSISACT;
/*     */   private String addendum;
/*     */   private String fzaVta;
/*     */   private String valorPtos;
/*     */   private String indicador;
/*     */   private String idAreaPromocion;
/*     */   
/*     */   public PromocionTO() {}
/*     */   
/*     */   public PromocionTO(String[] promocion)
/*     */   {
/*  43 */     this.idProducto = promocion[0];
/*  44 */     this.idRegion = promocion[1];
/*  45 */     this.idGrupoPromocion = promocion[2];
/*  46 */     this.descripcion = promocion[3];
/*  47 */     this.tipoProducto = promocion[4];
/*  48 */     this.precioLista = promocion[5];
/*  49 */     this.precioActiva = promocion[6];
/*  50 */     this.marca = promocion[7];
/*  51 */     this.modelo = promocion[8];
/*  52 */     this.URL = promocion[9];
/*  53 */     this.tecnologia = promocion[10];
/*  54 */     this.estatus = promocion[11];
/*  55 */     this.banSISACT = promocion[12];
/*  56 */     this.addendum = promocion[13];
/*  57 */     this.fzaVta = promocion[14];
/*  58 */     if (((promocion[15] != null ? 1 : 0) & (promocion[15].trim().equals("") ? 0 : 1)) != 0)
/*  59 */       this.valorPtos = promocion[15];
/*  60 */     this.indicador = promocion[16];
/*     */     
/*  62 */     if ((promocion.length > 17) && (promocion[17] != null) && (!promocion[17].trim().equals("")))
/*  63 */       this.idAreaPromocion = promocion[17];
/*     */   }
/*     */   
/*     */   public String getIdPromocion() {
/*  67 */     return this.idPromocion;
/*     */   }
/*     */   
/*  70 */   public void setIdPromocion(String idPromocion) { this.idPromocion = idPromocion; }
/*     */   
/*     */   public String getValorPtos()
/*     */   {
/*  74 */     return this.valorPtos;
/*     */   }
/*     */   
/*  77 */   public String getDescripcion() { return this.descripcion; }
/*     */   
/*  79 */   public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
/*     */   
/*  81 */   public String getTipoProducto() { return this.tipoProducto; }
/*     */   
/*  83 */   public void setTipoProducto(String tipoProducto) { this.tipoProducto = tipoProducto; }
/*     */   
/*     */ 
/*  86 */   public String getMarca() { return this.marca; }
/*     */   
/*  88 */   public void setMarca(String marca) { this.marca = marca; }
/*     */   
/*  90 */   public String getModelo() { return this.modelo; }
/*     */   
/*  92 */   public void setModelo(String modelo) { this.modelo = modelo; }
/*     */   
/*  94 */   public String getURL() { return this.URL; }
/*     */   
/*  96 */   public void setURL(String url) { this.URL = url; }
/*     */   
/*  98 */   public String getTecnologia() { return this.tecnologia; }
/*     */   
/* 100 */   public void setTecnologia(String tecnologia) { this.tecnologia = tecnologia; }
/*     */   
/* 102 */   public String getBanSISACT() { return this.banSISACT; }
/*     */   
/* 104 */   public void setBanSISACT(String banSISACT) { this.banSISACT = banSISACT; }
/*     */   
/*     */ 
/* 107 */   public String getFzaVta() { return this.fzaVta; }
/*     */   
/* 109 */   public void setFzaVta(String fzaVta) { this.fzaVta = fzaVta; }
/*     */   
/*     */ 
/* 112 */   public String getTipoPromocion() { return this.tipoPromocion; }
/*     */   
/* 114 */   public void setTipoPromocion(String TipoPromocion) { this.tipoPromocion = TipoPromocion; }
/*     */   
/* 116 */   public String getEstatus() { return this.estatus; }
/*     */   
/* 118 */   public void setEstatus(String Estatus) { this.estatus = Estatus; }
/*     */   
/* 120 */   public String getGpoPromocion() { return this.gpoPromocion; }
/*     */   
/* 122 */   public void setGpoPromocion(String GpoPromocion) { this.gpoPromocion = GpoPromocion; }
/*     */   
/*     */   public String getLineaLog()
/*     */   {
/* 126 */     return 
/*     */     
/*     */ 
/*     */ 
/* 130 */       this.idProducto + "@" + this.idRegion + "@" + this.idGrupoPromocion + "@" + this.descripcion + "@" + this.tipoProducto + "@" + this.precioLista + "@" + this.precioActiva + "@" + this.marca + "@" + this.modelo + "@" + this.URL + "@" + this.tecnologia + "@" + this.estatus + "@" + this.banSISACT + "@" + this.addendum + "@" + this.fzaVta + "@" + this.valorPtos + "@" + this.indicador + "@" + this.descuento + "@" + this.descuentoAltoValor + "@" + this.tipoPromocion + "@" + this.gpoPromocion;
/*     */   }
/*     */   
/*     */   public String toString()
/*     */   {
/* 135 */     return 
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 142 */       this.idProducto + "," + this.idRegion + "," + this.idGrupoPromocion + "," + this.descripcion + "," + this.tipoProducto + "," + this.precioLista + "," + this.precioActiva + "," + this.marca + "," + this.modelo + "," + this.URL + "," + this.tecnologia + "," + this.estatus + "," + this.banSISACT + "," + this.addendum + "," + this.fzaVta + "," + this.valorPtos + "," + this.indicador + "," + this.idAreaPromocion;
/*     */   }
/*     */   
/*     */   public String getIdProducto() {
/* 146 */     return this.idProducto;
/*     */   }
/*     */   
/* 149 */   public void setIdProducto(String idProducto) { this.idProducto = idProducto; }
/*     */   
/*     */   public String getIdRegion()
/*     */   {
/* 153 */     return this.idRegion;
/*     */   }
/*     */   
/*     */   public void setIdRegion(String idRegion) {
/* 157 */     this.idRegion = idRegion;
/*     */   }
/*     */   
/*     */   public String getIdGrupoPromocion() {
/* 161 */     return this.idGrupoPromocion;
/*     */   }
/*     */   
/*     */   public void setIdGrupoPromocion(String idGrupoPromocion) {
/* 165 */     this.idGrupoPromocion = idGrupoPromocion;
/*     */   }
/*     */   
/*     */   public String getDescuento() {
/* 169 */     return this.descuento;
/*     */   }
/*     */   
/*     */   public void setDescuento(String descuento) {
/* 173 */     this.descuento = descuento;
/*     */   }
/*     */   
/*     */   public String getDescuentoAltoValor() {
/* 177 */     return this.descuentoAltoValor;
/*     */   }
/*     */   
/*     */   public void setDescuentoAltoValor(String descuentoAltoValor) {
/* 181 */     this.descuentoAltoValor = descuentoAltoValor;
/*     */   }
/*     */   
/*     */   public String getPrecioLista() {
/* 185 */     return this.precioLista;
/*     */   }
/*     */   
/*     */   public void setPrecioLista(String precioLista) {
/* 189 */     this.precioLista = precioLista;
/*     */   }
/*     */   
/*     */   public String getPrecioActiva() {
/* 193 */     return this.precioActiva;
/*     */   }
/*     */   
/*     */   public void setPrecioActiva(String precioActiva) {
/* 197 */     this.precioActiva = precioActiva;
/*     */   }
/*     */   
/*     */   public String getAddendum() {
/* 201 */     return this.addendum;
/*     */   }
/*     */   
/*     */   public void setAddendum(String addendum) {
/* 205 */     this.addendum = addendum;
/*     */   }
/*     */   
/*     */   public String getIndicador() {
/* 209 */     return this.indicador;
/*     */   }
/*     */   
/*     */   public void setIndicador(String indicador) {
/* 213 */     this.indicador = indicador;
/*     */   }
/*     */   
/*     */   public void setValorPtos(String valorPtos) {
/* 217 */     this.valorPtos = valorPtos;
/*     */   }
/*     */   
/*     */   public String getIdAreaPromocion() {
/* 221 */     return this.idAreaPromocion;
/*     */   }
/*     */   
/*     */   public void setIdAreaPromocion(String idAreaPromocion) {
/* 225 */     this.idAreaPromocion = idAreaPromocion;
/*     */   }
/*     */ }


/* Location:              /opt/trabajo/claro/Guatemala/IS/CO/ClaroClub.ear!/ClaroClubEJBClient.jar!/com/claro/transfer/promociones/PromocionTO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */