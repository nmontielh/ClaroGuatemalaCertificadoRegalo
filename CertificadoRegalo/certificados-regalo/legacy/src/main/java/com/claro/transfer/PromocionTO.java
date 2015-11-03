/*     */ package com.claro.transfer;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public class PromocionTO
/*     */   extends MensajeTO implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -4669325907897942635L;
/*     */   private String idProducto;
/*     */   private String idPromocion;
/*     */   private int idRegion;
/*     */   private int idGrupoPromocion;
/*     */   private String tipoPromocion;
/*     */   private String estatus;
/*     */   private String gpoPromocion;
/*     */   private int descuento;
/*     */   private int descuentoAltoValor;
/*     */   private String descripcion;
/*     */   private String tipoProducto;
/*     */   private float precioLista;
/*     */   private float precioActiva;
/*     */   private String marca;
/*     */   private String modelo;
/*     */   private String URL;
/*     */   private String tecnologia;
/*     */   private String banSISACT;
/*     */   private int addendum;
/*     */   private String fzaVta;
/*     */   private int valorPtos;
/*     */   private int indicador;
/*     */   private AreaPromocionTO areaPromocion;
/*     */   
/*     */   public PromocionTO() {}
/*     */   
/*     */   public PromocionTO(String[] promocion)
/*     */   {
/*  37 */     this.idProducto = promocion[0];
/*  38 */     this.idRegion = Integer.parseInt(promocion[1]);
/*  39 */     this.idGrupoPromocion = Integer.parseInt(promocion[2]);
/*  40 */     this.descripcion = promocion[3];
/*  41 */     this.tipoProducto = promocion[4];
/*  42 */     this.precioLista = Float.valueOf(promocion[5]).floatValue();
/*  43 */     this.precioActiva = Float.valueOf(promocion[6]).floatValue();
/*  44 */     this.marca = promocion[7];
/*  45 */     this.modelo = promocion[8];
/*  46 */     this.URL = promocion[9];
/*  47 */     this.tecnologia = promocion[10];
/*  48 */     this.estatus = promocion[11];
/*  49 */     this.banSISACT = promocion[12];
/*  50 */     this.addendum = Integer.parseInt(promocion[13]);
/*  51 */     this.fzaVta = promocion[14];
/*  52 */     if (((promocion[15] != null ? 1 : 0) & (promocion[15].trim().equals("") ? 0 : 1)) != 0)
/*  53 */       this.valorPtos = Integer.parseInt(promocion[15]);
/*  54 */     this.indicador = Integer.parseInt(promocion[16]);
/*     */   }
/*     */   
/*     */   public String getIdPromocion() {
/*  58 */     return this.idPromocion;
/*     */   }
/*     */   
/*  61 */   public void setIdPromocion(String idPromocion) { this.idPromocion = idPromocion; }
/*     */   
/*     */ 
/*  64 */   public int getValorPtos() { return this.valorPtos; }
/*     */   
/*  66 */   public void setValorPtos(int valorPtos) { this.valorPtos = valorPtos; }
/*     */   
/*  68 */   public int getIndicador() { return this.indicador; }
/*     */   
/*  70 */   public void setIndicador(int indicador) { this.indicador = indicador; }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  76 */   public int getIdRegion() { return this.idRegion; }
/*     */   
/*  78 */   public void setIdRegion(int idRegion) { this.idRegion = idRegion; }
/*     */   
/*  80 */   public String getDescripcion() { return this.descripcion; }
/*     */   
/*  82 */   public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
/*     */   
/*  84 */   public String getTipoProducto() { return this.tipoProducto; }
/*     */   
/*  86 */   public void setTipoProducto(String tipoProducto) { this.tipoProducto = tipoProducto; }
/*     */   
/*  88 */   public float getPrecioLista() { return this.precioLista; }
/*     */   
/*  90 */   public void setPrecioLista(float precioLista) { this.precioLista = precioLista; }
/*     */   
/*  92 */   public float getPrecioActiva() { return this.precioActiva; }
/*     */   
/*  94 */   public void setPrecioActiva(float precioActiva) { this.precioActiva = precioActiva; }
/*     */   
/*  96 */   public String getMarca() { return this.marca; }
/*     */   
/*  98 */   public void setMarca(String marca) { this.marca = marca; }
/*     */   
/* 100 */   public String getModelo() { return this.modelo; }
/*     */   
/* 102 */   public void setModelo(String modelo) { this.modelo = modelo; }
/*     */   
/* 104 */   public String getURL() { return this.URL; }
/*     */   
/* 106 */   public void setURL(String url) { this.URL = url; }
/*     */   
/* 108 */   public String getTecnologia() { return this.tecnologia; }
/*     */   
/* 110 */   public void setTecnologia(String tecnologia) { this.tecnologia = tecnologia; }
/*     */   
/* 112 */   public String getBanSISACT() { return this.banSISACT; }
/*     */   
/* 114 */   public void setBanSISACT(String banSISACT) { this.banSISACT = banSISACT; }
/*     */   
/* 116 */   public int getAddendum() { return this.addendum; }
/*     */   
/* 118 */   public void setAddendum(int addendum) { this.addendum = addendum; }
/*     */   
/* 120 */   public String getFzaVta() { return this.fzaVta; }
/*     */   
/* 122 */   public void setFzaVta(String fzaVta) { this.fzaVta = fzaVta; }
/*     */   
/* 124 */   public static long getSerialVersionUID() { return -4669325907897942635L; }
/* 125 */   public int getIdGrupoPromocion() { return this.idGrupoPromocion; }
/*     */   
/* 127 */   public void setIdGrupoPromocion(int IdGrupoPromocion) { this.idGrupoPromocion = IdGrupoPromocion; }
/*     */   
/* 129 */   public String getTipoPromocion() { return this.tipoPromocion; }
/*     */   
/* 131 */   public void setTipoPromocion(String TipoPromocion) { this.tipoPromocion = TipoPromocion; }
/*     */   
/* 133 */   public String getEstatus() { return this.estatus; }
/*     */   
/* 135 */   public void setEstatus(String Estatus) { this.estatus = Estatus; }
/*     */   
/* 137 */   public String getGpoPromocion() { return this.gpoPromocion; }
/*     */   
/* 139 */   public void setGpoPromocion(String GpoPromocion) { this.gpoPromocion = GpoPromocion; }
/*     */   
/* 141 */   public int getDescuento() { return this.descuento; }
/*     */   
/* 143 */   public void setDescuento(int Descuento) { this.descuento = Descuento; }
/*     */   
/* 145 */   public int getDescuentoAltoValor() { return this.descuentoAltoValor; }
/*     */   
/* 147 */   public void setDescuentoAltoValor(int DescuentoAltoValor) { this.descuentoAltoValor = DescuentoAltoValor; }
/*     */   
/*     */ 
/*     */   public String getLineaLog()
/*     */   {
/* 152 */     return 
/*     */     
/*     */ 
/*     */ 
/* 156 */       this.idProducto + "@" + this.idRegion + "@" + this.idGrupoPromocion + "@" + this.descripcion + "@" + this.tipoProducto + "@" + this.precioLista + "@" + this.precioActiva + "@" + this.marca + "@" + this.modelo + "@" + this.URL + "@" + this.tecnologia + "@" + this.estatus + "@" + this.banSISACT + "@" + this.addendum + "@" + this.fzaVta + "@" + this.valorPtos + "@" + this.indicador + "@" + this.descuento + "@" + this.descuentoAltoValor + "@" + this.tipoPromocion + "@" + this.gpoPromocion;
/*     */   }
/*     */   
/*     */   public String toString()
/*     */   {
/* 161 */     return 
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 168 */       this.idProducto + "," + this.idRegion + "," + this.idGrupoPromocion + "," + this.descripcion + "," + this.tipoProducto + "," + this.precioLista + "," + this.precioActiva + "," + this.marca + "," + this.modelo + "," + this.URL + "," + this.tecnologia + "," + this.estatus + "," + this.banSISACT + "," + this.addendum + "," + this.fzaVta + "," + this.valorPtos + "," + this.indicador;
/*     */   }
/*     */   
/*     */   public String getIdProducto() {
/* 172 */     return this.idProducto;
/*     */   }
/*     */   
/* 175 */   public void setIdProducto(String idProducto) { this.idProducto = idProducto; }
/*     */   
/*     */   public AreaPromocionTO getAreaPromocion()
/*     */   {
/* 179 */     return this.areaPromocion;
/*     */   }
/*     */   
/*     */   public void setAreaPromocion(AreaPromocionTO areaPromocion) {
/* 183 */     this.areaPromocion = areaPromocion;
/*     */   }
/*     */ }


/* Location:              /opt/trabajo/claro/Guatemala/IS/CO/ClaroClub.ear!/ClaroClubEJBClient.jar!/com/claro/transfer/PromocionTO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */