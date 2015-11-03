/*     */ package com.claro.transfer;
/*     */ 
/*     */ import com.claro.util.Utils;
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
/*     */ public class ProductosTO
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -5598682274188091814L;
/*     */   private BigDecimal precioBD;
/*     */   private long ptosARedimir;
/*     */   private int sobBonoEquipo;
/*     */   private String material;
/*     */   private String descripcion;
/*     */   private String marca;
/*     */   private String modelo;
/*     */   private String puntos;
/*     */   private String tipoPromocion;
/*     */   private String url;
/*     */   private String tecnologia;
/*     */   private String puntosSobrantes;
/*     */   private String indicador;
/*     */   private String aplicaPaqueteSMS;
/*     */   private String numeroSerieT;
/*     */   private String numeroSerieP;
/*     */   private String iccid;
/*     */   private BigDecimal precio;
/*     */   private BigDecimal precioIva;
/*     */   private String precioConFormato;
/*     */   private String precioIvaConFormato;
/*     */   private String descripcionDescuento;
/*     */   private BigDecimal descuento;
/*     */   private BigDecimal precioLista;
/*     */   private BigDecimal precioActivacion;
/*     */   private BigDecimal precioDescuento;
/*     */   private int bonosRoext;
/*     */   private int bonosAltoValor;
/*     */   private int bonosGap;
/*     */   private String fzaVentas;
/*     */   private String idProducto;
/*     */   private int valorPuntos;
/*     */   private int valorPuntosTmp;
/*     */   private Long valorMillas;
/*     */   private int difPesos;
/*     */   private String valorPuntosF;
/*     */   private String precioListaCF;
/*     */   private String precioActivacionCF;
/*     */   private String plan;
/*     */   private int bonosInbursa;
/*     */   private int bonosMarca;
/*  59 */   private BigDecimal descuentoInbursa = new BigDecimal(0);
/*  60 */   private BigDecimal descuentoMarca = new BigDecimal(0);
/*  61 */   private BigDecimal descuentoInbursaRestante = new BigDecimal(0);
/*  62 */   private BigDecimal descuentoMarcaRestante = new BigDecimal(0);
/*     */   
/*     */   private String aplicaPromocionGap;
/*     */   
/*     */   private int idPromocionGapCA;
/*     */   
/*     */   private int idPromocionGap;
/*     */   
/*     */   private int verPromocionGap;
/*     */   
/*     */   private String bonoDescuentoGap;
/*     */   
/*     */   private String productoM2KGap;
/*     */   
/*     */   private String nombrePromocionGap;
/*     */   
/*     */   private String aplicaEP;
/*     */   
/*     */   public String getPrecioActivacionCF()
/*     */   {
/*  82 */     if (this.precioActivacion != null) {
/*     */       try {
/*  84 */         this.precioActivacionCF = Utils.setFormatoDecimal(this.precioActivacion.toString());
/*     */       } catch (Exception e) {
/*  86 */         this.precioActivacionCF = this.precioActivacion.toString();
/*     */       }
/*     */     }
/*  89 */     return this.precioActivacionCF;
/*     */   }
/*     */   
/*  92 */   public String getPrecioListaCF() { if (this.precioLista != null) {
/*     */       try {
/*  94 */         this.precioListaCF = Utils.setFormatoDecimal(this.precioLista.toString());
/*     */       } catch (Exception e) {
/*  96 */         this.precioListaCF = this.precioLista.toString();
/*     */       }
/*     */     }
/*  99 */     return this.precioListaCF;
/*     */   }
/*     */   
/* 102 */   public int getValorPuntosTmp() { return this.valorPuntosTmp; }
/*     */   
/*     */ 
/* 105 */   public void setValorPuntosTmp(int valorPuntosTmp) { this.valorPuntosTmp = valorPuntosTmp; }
/*     */   
/*     */   public String getValorPuntosF() {
/* 108 */     this.valorPuntosF = Utils.setFormatoPtos(this.valorPuntos);
/* 109 */     return this.valorPuntosF;
/*     */   }
/*     */   
/* 112 */   public String getPrecioConFormato() { return this.precioConFormato; }
/*     */   
/*     */   public void setPrecioConFormato(String precioConFormato) {
/* 115 */     this.precioConFormato = precioConFormato;
/*     */   }
/*     */   
/* 118 */   public String getPrecioIvaConFormato() { return this.precioIvaConFormato; }
/*     */   
/*     */   public void setPrecioIvaConFormato(String precioIvaConFormato) {
/* 121 */     this.precioIvaConFormato = precioIvaConFormato;
/*     */   }
/*     */   
/* 124 */   public int getDifPesos() { return this.difPesos; }
/*     */   
/*     */   public void setDifPesos(int difPesos) {
/* 127 */     this.difPesos = difPesos;
/*     */   }
/*     */   
/* 130 */   public BigDecimal getPrecioLista() { return this.precioLista; }
/*     */   
/*     */   public void setPrecioLista(BigDecimal precioLista) {
/* 133 */     this.precioLista = precioLista;
/*     */   }
/*     */   
/* 136 */   public BigDecimal getPrecioActivacion() { return this.precioActivacion; }
/*     */   
/*     */   public void setPrecioActivacion(BigDecimal precioActivacion) {
/* 139 */     this.precioActivacion = precioActivacion;
/*     */   }
/*     */   
/* 142 */   public static long getSerialVersionUID() { return -5598682274188091814L; }
/*     */   
/*     */   public String getAplicaPaqueteSMS() {
/* 145 */     if (this.aplicaPaqueteSMS == null)
/* 146 */       return "0";
/* 147 */     return this.aplicaPaqueteSMS;
/*     */   }
/*     */   
/* 150 */   public void setAplicaPaqueteSMS(String aplicaPaqueteSMS) { this.aplicaPaqueteSMS = aplicaPaqueteSMS; }
/*     */   
/*     */   public String getMaterial()
/*     */   {
/* 154 */     return this.material;
/*     */   }
/*     */   
/* 157 */   public void setMaterial(String material) { this.material = material; }
/*     */   
/*     */   public String getDescripcion() {
/* 160 */     return this.descripcion;
/*     */   }
/*     */   
/* 163 */   public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
/*     */   
/*     */   public String getMarca() {
/* 166 */     return this.marca;
/*     */   }
/*     */   
/* 169 */   public void setMarca(String marca) { this.marca = marca; }
/*     */   
/*     */   public String getModelo() {
/* 172 */     return this.modelo;
/*     */   }
/*     */   
/* 175 */   public void setModelo(String modelo) { this.modelo = modelo; }
/*     */   
/*     */   public String getPuntos() {
/* 178 */     return this.puntos;
/*     */   }
/*     */   
/* 181 */   public void setPuntos(String puntos) { this.puntos = puntos; }
/*     */   
/*     */   public String getTipoPromocion()
/*     */   {
/* 185 */     return this.tipoPromocion;
/*     */   }
/*     */   
/* 188 */   public void setTipoPromocion(String tipoPromocion) { this.tipoPromocion = tipoPromocion; }
/*     */   
/*     */   public String getUrl()
/*     */   {
/* 192 */     return this.url;
/*     */   }
/*     */   
/* 195 */   public void setUrl(String url) { this.url = url; }
/*     */   
/*     */   public String getTecnologia() {
/* 198 */     return this.tecnologia;
/*     */   }
/*     */   
/* 201 */   public void setTecnologia(String tecnologia) { this.tecnologia = tecnologia; }
/*     */   
/*     */   public String getPuntosSobrantes() {
/* 204 */     return this.puntosSobrantes;
/*     */   }
/*     */   
/* 207 */   public void setPuntosSobrantes(String puntosSobrantes) { this.puntosSobrantes = puntosSobrantes; }
/*     */   
/*     */   public String getIndicador()
/*     */   {
/* 211 */     return this.indicador;
/*     */   }
/*     */   
/* 214 */   public void setIndicador(String indicador) { this.indicador = indicador; }
/*     */   
/*     */   public BigDecimal getPrecioBD() {
/* 217 */     return this.precioBD;
/*     */   }
/*     */   
/* 220 */   public void setPrecioBD(BigDecimal precioBD) { this.precioBD = precioBD; }
/*     */   
/*     */   public long getPtosARedimir() {
/* 223 */     return this.ptosARedimir;
/*     */   }
/*     */   
/* 226 */   public void setPtosARedimir(long ptosARedimir) { this.ptosARedimir = ptosARedimir; }
/*     */   
/*     */   public int getSobBonoEquipo() {
/* 229 */     return this.sobBonoEquipo;
/*     */   }
/*     */   
/* 232 */   public void setSobBonoEquipo(int sobBonoEquipo) { this.sobBonoEquipo = sobBonoEquipo; }
/*     */   
/*     */   public BigDecimal getPrecio()
/*     */   {
/* 236 */     return this.precio;
/*     */   }
/*     */   
/* 239 */   public void setPrecio(BigDecimal precio) { this.precio = precio; }
/*     */   
/*     */   public BigDecimal getPrecioIva() {
/* 242 */     return this.precioIva;
/*     */   }
/*     */   
/* 245 */   public void setPrecioIva(BigDecimal precioIva) { this.precioIva = precioIva; }
/*     */   
/*     */   public BigDecimal getDescuento() {
/* 248 */     return this.descuento;
/*     */   }
/*     */   
/* 251 */   public void setDescuento(BigDecimal descuento) { this.descuento = descuento; }
/*     */   
/*     */   public int getBonosRoext() {
/* 254 */     return this.bonosRoext;
/*     */   }
/*     */   
/* 257 */   public void setBonosRoext(int bonosRoext) { this.bonosRoext = bonosRoext; }
/*     */   
/*     */   public int getBonosAltoValor() {
/* 260 */     return this.bonosAltoValor;
/*     */   }
/*     */   
/* 263 */   public void setBonosAltoValor(int bonosAltoValor) { this.bonosAltoValor = bonosAltoValor; }
/*     */   
/*     */   public String getNumeroSerieT() {
/* 266 */     if (this.numeroSerieT == null) return "0";
/* 267 */     return this.numeroSerieT;
/*     */   }
/*     */   
/* 270 */   public void setNumeroSerieT(String numeroSerieT) { this.numeroSerieT = numeroSerieT; }
/*     */   
/*     */   public String getNumeroSerieP() {
/* 273 */     if (this.numeroSerieP == null) return "0";
/* 274 */     return this.numeroSerieP;
/*     */   }
/*     */   
/* 277 */   public void setNumeroSerieP(String numeroSerieP) { this.numeroSerieP = numeroSerieP; }
/*     */   
/*     */   public String getIccid() {
/* 280 */     if (this.iccid == null) return "0";
/* 281 */     return this.iccid;
/*     */   }
/*     */   
/* 284 */   public void setIccid(String iccid) { this.iccid = iccid; }
/*     */   
/*     */   public String getFzaVentas() {
/* 287 */     return this.fzaVentas;
/*     */   }
/*     */   
/* 290 */   public void setFzaVentas(String fzaVentas) { this.fzaVentas = fzaVentas; }
/*     */   
/*     */   public String getIdProducto() {
/* 293 */     return this.idProducto;
/*     */   }
/*     */   
/* 296 */   public void setIdProducto(String idProducto) { this.idProducto = idProducto; }
/*     */   
/*     */   public int getValorPuntos() {
/* 299 */     return this.valorPuntos;
/*     */   }
/*     */   
/* 302 */   public void setValorPuntos(int valorPuntos) { this.valorPuntos = valorPuntos; }
/*     */   
/*     */   public Long getValorMillas() {
/* 305 */     return this.valorMillas;
/*     */   }
/*     */   
/* 308 */   public void setValorMillas(Long valorMillas) { this.valorMillas = valorMillas; }
/*     */   
/*     */   public String getDescripcionDescuento() {
/* 311 */     return this.descripcionDescuento;
/*     */   }
/*     */   
/* 314 */   public void setDescripcionDescuento(String descripcionDescuento) { this.descripcionDescuento = descripcionDescuento; }
/*     */   
/*     */   public BigDecimal getPrecioDescuento() {
/* 317 */     return this.precioDescuento;
/*     */   }
/*     */   
/* 320 */   public void setPrecioDescuento(BigDecimal precioDescuento) { this.precioDescuento = precioDescuento; }
/*     */   
/*     */   public int getBonosGap() {
/* 323 */     return this.bonosGap;
/*     */   }
/*     */   
/* 326 */   public void setBonosGap(int bonosGap) { this.bonosGap = bonosGap; }
/*     */   
/*     */ 
/*     */   public String toString()
/*     */   {
/* 331 */     return 
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 350 */       "precioBD: " + this.precioBD + "ptosARedimir: " + "|ptosARedimir:" + this.ptosARedimir + "|" + "sobBonoEquipo: " + this.sobBonoEquipo + "material:" + this.material + "|" + "|ptosARedimir:" + this.descripcion + "|" + "|" + this.marca + "|" + "|modelo:" + this.modelo + "|" + "|puntos:" + this.puntos + "|" + "|tipoPromocion" + this.tipoPromocion + "|url: " + this.url + "|" + "|tecnologia:" + this.tecnologia + "|puntosSobrantes:" + this.puntosSobrantes + "|indicador:" + this.indicador + "|" + "|aplicaPaqueteSMS:" + this.aplicaPaqueteSMS + "|" + "|numeroSerieT:" + this.numeroSerieT + "|" + "|numeroSerieP:" + this.numeroSerieP + "|" + "|iccid:" + this.iccid + "|" + "|precio:" + this.precio + "|" + "|precioIva:" + this.precioIva + "|" + "|precioConFormato:" + this.precioConFormato + "|" + "|precioIvaConFormato:" + this.precioIvaConFormato + "|" + "|descripcionDescuento:" + this.descripcionDescuento + "|" + "|descuento: " + this.descuento + "|" + "|precioLista: " + this.precioLista + "|" + "|precioActivacion:" + this.precioActivacion + "|" + "|precioDescuento:" + this.precioDescuento + "|" + "|bonosRoext:" + this.bonosRoext + "|" + "|bonosAltoValor:" + this.bonosAltoValor + "|" + "|bonosGap:" + this.bonosGap + "|" + "|fzaVentas:" + this.fzaVentas + "|" + "|idProducto:" + this.idProducto + "|" + "|valorPuntos:" + this.valorPuntos + "|" + "|valorPuntosTmp:" + this.valorPuntosTmp + "|" + "|valorMillas: " + this.valorMillas + "|" + "|difPesos:" + this.difPesos + "|" + "|" + this.valorPuntosF + "|" + "|precioListaCF: " + this.precioListaCF + "|" + "|precioActivacionCF: " + this.precioActivacionCF + "|bonosInbursa: " + this.bonosInbursa + "|bonosMarca: " + this.bonosMarca;
/*     */   }
/*     */   
/* 353 */   public int getIdPromocionGapCA() { return this.idPromocionGapCA; }
/*     */   
/*     */   public void setIdPromocionGapCA(int idPromocionGapCA) {
/* 356 */     this.idPromocionGapCA = idPromocionGapCA;
/*     */   }
/*     */   
/* 359 */   public int getIdPromocionGap() { return this.idPromocionGap; }
/*     */   
/*     */   public void setIdPromocionGap(int idPromocionGap) {
/* 362 */     this.idPromocionGap = idPromocionGap;
/*     */   }
/*     */   
/* 365 */   public int getVerPromocionGap() { return this.verPromocionGap; }
/*     */   
/*     */   public void setVerPromocionGap(int verPromocionGap) {
/* 368 */     this.verPromocionGap = verPromocionGap;
/*     */   }
/*     */   
/* 371 */   public String getBonoDescuentoGap() { return this.bonoDescuentoGap; }
/*     */   
/*     */   public void setBonoDescuentoGap(String bonoDescuentoGap) {
/* 374 */     this.bonoDescuentoGap = bonoDescuentoGap;
/*     */   }
/*     */   
/* 377 */   public String getProductoM2KGap() { return this.productoM2KGap; }
/*     */   
/*     */   public void setProductoM2KGap(String productoM2KGap) {
/* 380 */     this.productoM2KGap = productoM2KGap;
/*     */   }
/*     */   
/* 383 */   public String getNombrePromocionGap() { return this.nombrePromocionGap; }
/*     */   
/*     */   public void setNombrePromocionGap(String nombrePromocionGap) {
/* 386 */     this.nombrePromocionGap = nombrePromocionGap;
/*     */   }
/*     */   
/* 389 */   public String getAplicaPromocionGap() { return this.aplicaPromocionGap; }
/*     */   
/*     */   public void setAplicaPromocionGap(String aplicaPromocionGap) {
/* 392 */     this.aplicaPromocionGap = aplicaPromocionGap;
/*     */   }
/*     */   
/* 395 */   public String getAplicaEP() { return this.aplicaEP; }
/*     */   
/*     */   public void setAplicaEP(String aplicaEP) {
/* 398 */     this.aplicaEP = aplicaEP;
/*     */   }
/*     */   
/* 401 */   public int getBonosInbursa() { return this.bonosInbursa; }
/*     */   
/*     */   public void setBonosInbursa(int bonosInbursa) {
/* 404 */     this.bonosInbursa = bonosInbursa;
/*     */   }
/*     */   
/* 407 */   public int getBonosMarca() { return this.bonosMarca; }
/*     */   
/*     */   public void setBonosMarca(int bonosMarca) {
/* 410 */     this.bonosMarca = bonosMarca;
/*     */   }
/*     */   
/* 413 */   public BigDecimal getDescuentoInbursa() { return this.descuentoInbursa; }
/*     */   
/*     */   public void setDescuentoInbursa(BigDecimal descuentoInbursa) {
/* 416 */     this.descuentoInbursa = descuentoInbursa;
/*     */   }
/*     */   
/* 419 */   public BigDecimal getDescuentoMarca() { return this.descuentoMarca; }
/*     */   
/*     */   public void setDescuentoMarca(BigDecimal descuentoMarca) {
/* 422 */     this.descuentoMarca = descuentoMarca;
/*     */   }
/*     */   
/* 425 */   public BigDecimal getDescuentoInbursaRestante() { return this.descuentoInbursaRestante; }
/*     */   
/*     */   public void setDescuentoInbursaRestante(BigDecimal descuentoInbursaRestante) {
/* 428 */     this.descuentoInbursaRestante = descuentoInbursaRestante;
/*     */   }
/*     */   
/* 431 */   public BigDecimal getDescuentoMarcaRestante() { return this.descuentoMarcaRestante; }
/*     */   
/*     */   public void setDescuentoMarcaRestante(BigDecimal descuentoMarcaRestante) {
/* 434 */     this.descuentoMarcaRestante = descuentoMarcaRestante;
/*     */   }
/*     */   
/* 437 */   public String getPlan() { return this.plan; }
/*     */   
/*     */   public void setPlan(String plan) {
/* 440 */     this.plan = plan;
/*     */   }
/*     */ }


/* Location:              /opt/trabajo/claro/Guatemala/IS/CO/ClaroClub.ear!/ClaroClubEJBClient.jar!/com/claro/transfer/ProductosTO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */