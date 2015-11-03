/*     */ package com.claro.transfer;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.math.BigDecimal;
/*     */ import java.sql.Timestamp;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
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
/*     */ public class ImpresionTO
/*     */   extends MensajeTO
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -2650842559951836887L;
/*     */   private String idPuntoVenta;
/*     */   private String planAnt;
/*     */   private String planNuevo;
/*     */   private String tipoReden;
/*     */   private String formaReden;
/*     */   private String idProducto;
/*     */   private String descripcion;
/*     */   private String comentario;
/*     */   private String marca;
/*     */   private String modelo;
/*     */   private String esnImeiR;
/*     */   private String esnImeiP;
/*     */   private String iccid;
/*     */   private String folio;
/*     */   private String idUsuario;
/*     */   private String telefono;
/*     */   private String cuenta;
/*     */   private String cuentaAlianza;
/*     */   private String archivoSalida;
/*     */   private String precioFormato;
/*     */   private String descuentoFormato;
/*     */   private String precioIvaFormato;
/*     */   private String tipoRedenDB;
/*     */   private String ptosDispCF;
/*     */   private String ptsDispRestantesCF;
/*     */   private String valorPuntosCF;
/*     */   private String ptsMinimosCF;
/*     */   private String ptsAcumCF;
/*     */   private String ptsAcumResCF;
/*     */   private String ptsTransferidosCF;
/*     */   private String ptsMillasCF;
/*     */   private String ptsCanjeadoCF;
/*     */   private String tipoAlianza;
/*     */   private String fechaFAlianza;
/*     */   private String idReferencia;
/*     */   private int addAnt;
/*     */   private int ptosDisp;
/*     */   private int valorPuntos;
/*     */   private int ptsDispRestantes;
/*     */   private int addNuevo;
/*     */   private int ptsMinimos;
/*     */   private int bonoProrr;
/*     */   private int ptsAcum;
/*     */   private int ptsAcumRes;
/*     */   private int idRegion;
/*     */   private int ptsTransferidos;
/*     */   private int valorCuponOrig;
/*     */   private int secuencia;
/*     */   private int opcion;
/*     */   private int ptsCanjeado;
/*     */   private int ptsMillas;
/*     */   private int bonosRoext;
/*     */   private int bonosAltoValor;
/*     */   private int bonosGap;
/*     */   private String inbursaFormato;
/*     */   private String marcaFormato;
/*     */   private String folioRedencion;
/*     */   private long lFechaFolio;
/*     */   private long fechaF;
/*     */   private Date fechaPlazoAnt;
/*     */   private Date fechaOperacion;
/*     */   private Date fechaPlazoSeg;
/*     */   private Timestamp fechaFolio;
/*     */   private BigDecimal precio;
/*     */   private BigDecimal descuento;
/*     */   private BigDecimal precioIva;
/*     */   private ArrayList<AlianzasTO> alianzas;
/*     */   
/*     */   public long getFechaF()
/*     */   {
/*  98 */     if (this.fechaFolio != null) {
/*  99 */       return this.fechaFolio.getTime();
/*     */     }
/* 101 */     return this.fechaF;
/*     */   }
/*     */   
/* 104 */   public void setFechaF(long fechaF) { this.fechaF = fechaF; }
/*     */   
/*     */   public long getLFechaFolio() {
/* 107 */     if (this.fechaFolio != null) return this.fechaFolio.getTime();
/* 108 */     return this.lFechaFolio;
/*     */   }
/*     */   
/* 111 */   public void setLFechaFolio(long fechaFolio) { this.lFechaFolio = fechaFolio; }
/*     */   
/*     */   public ArrayList<AlianzasTO> getAlianzas() {
/* 114 */     return this.alianzas;
/*     */   }
/*     */   
/* 117 */   public void setAlianzas(ArrayList<AlianzasTO> alianzas) { this.alianzas = alianzas; }
/*     */   
/*     */   public String getIdPuntoVenta() {
/* 120 */     return this.idPuntoVenta;
/*     */   }
/*     */   
/* 123 */   public void setIdPuntoVenta(String idPuntoVenta) { this.idPuntoVenta = idPuntoVenta; }
/*     */   
/*     */   public String getPlanAnt() {
/* 126 */     return this.planAnt;
/*     */   }
/*     */   
/* 129 */   public void setPlanAnt(String planAnt) { this.planAnt = planAnt; }
/*     */   
/*     */   public String getPlanNuevo() {
/* 132 */     return this.planNuevo;
/*     */   }
/*     */   
/* 135 */   public void setPlanNuevo(String planNuevo) { this.planNuevo = planNuevo; }
/*     */   
/*     */   public String getTipoReden() {
/* 138 */     return this.tipoReden;
/*     */   }
/*     */   
/* 141 */   public void setTipoReden(String tipoReden) { this.tipoReden = tipoReden; }
/*     */   
/*     */   public String getFormaReden() {
/* 144 */     return this.formaReden;
/*     */   }
/*     */   
/* 147 */   public void setFormaReden(String formaReden) { this.formaReden = formaReden; }
/*     */   
/*     */   public String getIdProducto() {
/* 150 */     return this.idProducto;
/*     */   }
/*     */   
/* 153 */   public void setIdProducto(String idProducto) { this.idProducto = idProducto; }
/*     */   
/*     */   public String getDescripcion() {
/* 156 */     return this.descripcion;
/*     */   }
/*     */   
/* 159 */   public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
/*     */   
/*     */   public String getComentario() {
/* 162 */     return this.comentario;
/*     */   }
/*     */   
/* 165 */   public void setComentario(String comentario) { this.comentario = comentario; }
/*     */   
/*     */   public String getMarca() {
/* 168 */     return this.marca;
/*     */   }
/*     */   
/* 171 */   public void setMarca(String marca) { this.marca = marca; }
/*     */   
/*     */   public String getModelo() {
/* 174 */     return this.modelo;
/*     */   }
/*     */   
/* 177 */   public void setModelo(String modelo) { this.modelo = modelo; }
/*     */   
/*     */   public String getEsnImeiR() {
/* 180 */     return this.esnImeiR;
/*     */   }
/*     */   
/* 183 */   public void setEsnImeiR(String esnImeiR) { this.esnImeiR = esnImeiR; }
/*     */   
/*     */   public String getEsnImeiP() {
/* 186 */     return this.esnImeiP;
/*     */   }
/*     */   
/* 189 */   public void setEsnImeiP(String esnImeiP) { this.esnImeiP = esnImeiP; }
/*     */   
/*     */   public String getIccid() {
/* 192 */     return this.iccid;
/*     */   }
/*     */   
/* 195 */   public void setIccid(String iccid) { this.iccid = iccid; }
/*     */   
/*     */   public String getFolio() {
/* 198 */     return this.folio;
/*     */   }
/*     */   
/* 201 */   public void setFolio(String folio) { this.folio = folio; }
/*     */   
/*     */   public int getAddAnt() {
/* 204 */     return this.addAnt;
/*     */   }
/*     */   
/* 207 */   public void setAddAnt(int addAnt) { this.addAnt = addAnt; }
/*     */   
/*     */   public int getPtosDisp() {
/* 210 */     return this.ptosDisp;
/*     */   }
/*     */   
/* 213 */   public void setPtosDisp(int ptosDisp) { this.ptosDisp = ptosDisp; }
/*     */   
/*     */   public int getValorPuntos() {
/* 216 */     return this.valorPuntos;
/*     */   }
/*     */   
/* 219 */   public void setValorPuntos(int valorPuntos) { this.valorPuntos = valorPuntos; }
/*     */   
/*     */   public int getPtsDispRestantes() {
/* 222 */     return this.ptsDispRestantes;
/*     */   }
/*     */   
/* 225 */   public void setPtsDispRestantes(int ptsDispRestantes) { this.ptsDispRestantes = ptsDispRestantes; }
/*     */   
/*     */   public int getAddNuevo() {
/* 228 */     return this.addNuevo;
/*     */   }
/*     */   
/* 231 */   public void setAddNuevo(int addNuevo) { this.addNuevo = addNuevo; }
/*     */   
/*     */   public int getPtsMinimos() {
/* 234 */     return this.ptsMinimos;
/*     */   }
/*     */   
/* 237 */   public void setPtsMinimos(int ptsMinimos) { this.ptsMinimos = ptsMinimos; }
/*     */   
/*     */   public int getBonoProrr() {
/* 240 */     return this.bonoProrr;
/*     */   }
/*     */   
/* 243 */   public void setBonoProrr(int bonoProrr) { this.bonoProrr = bonoProrr; }
/*     */   
/*     */   public int getPtsAcum() {
/* 246 */     return this.ptsAcum;
/*     */   }
/*     */   
/* 249 */   public void setPtsAcum(int ptsAcum) { this.ptsAcum = ptsAcum; }
/*     */   
/*     */   public int getPtsAcumRes() {
/* 252 */     return this.ptsAcumRes;
/*     */   }
/*     */   
/* 255 */   public void setPtsAcumRes(int ptsAcumRes) { this.ptsAcumRes = ptsAcumRes; }
/*     */   
/*     */   public Date getFechaPlazoAnt() {
/* 258 */     return this.fechaPlazoAnt;
/*     */   }
/*     */   
/* 261 */   public void setFechaPlazoAnt(Date fechaPlazoAnt) { this.fechaPlazoAnt = fechaPlazoAnt; }
/*     */   
/*     */   public Date getFechaOperacion() {
/* 264 */     return this.fechaOperacion;
/*     */   }
/*     */   
/* 267 */   public void setFechaOperacion(Date fechaOperacion) { this.fechaOperacion = fechaOperacion; }
/*     */   
/*     */   public Date getFechaPlazoSeg() {
/* 270 */     return this.fechaPlazoSeg;
/*     */   }
/*     */   
/* 273 */   public void setFechaPlazoSeg(Date fechaPlazoSeg) { this.fechaPlazoSeg = fechaPlazoSeg; }
/*     */   
/*     */   public Timestamp getFechaFolio() {
/* 276 */     return this.fechaFolio;
/*     */   }
/*     */   
/* 279 */   public void setFechaFolio(Timestamp fechaFolio) { this.fechaFolio = fechaFolio; }
/*     */   
/*     */   public BigDecimal getPrecio() {
/* 282 */     return this.precio;
/*     */   }
/*     */   
/* 285 */   public void setPrecio(BigDecimal precio) { this.precio = precio; }
/*     */   
/*     */   public BigDecimal getDescuento() {
/* 288 */     return this.descuento;
/*     */   }
/*     */   
/* 291 */   public void setDescuento(BigDecimal descuento) { this.descuento = descuento; }
/*     */   
/*     */   public BigDecimal getPrecioIva() {
/* 294 */     return this.precioIva;
/*     */   }
/*     */   
/* 297 */   public void setPrecioIva(BigDecimal precioIva) { this.precioIva = precioIva; }
/*     */   
/*     */   public int getSumaFormaRedencion()
/*     */   {
/* 301 */     return this.ptsMinimos + this.bonoProrr;
/*     */   }
/*     */   
/* 304 */   public String getIdUsuario() { return this.idUsuario; }
/*     */   
/*     */   public void setIdUsuario(String idUsuario) {
/* 307 */     this.idUsuario = idUsuario;
/*     */   }
/*     */   
/* 310 */   public int getIdRegion() { return this.idRegion; }
/*     */   
/*     */   public void setIdRegion(int idRegion) {
/* 313 */     this.idRegion = idRegion;
/*     */   }
/*     */   
/* 316 */   public String getCuenta() { return this.cuenta; }
/*     */   
/*     */   public void setCuenta(String cuenta) {
/* 319 */     this.cuenta = cuenta;
/*     */   }
/*     */   
/* 322 */   public int getPtsTransferidos() { return this.ptsTransferidos; }
/*     */   
/*     */   public void setPtsTransferidos(int ptsTransferidos) {
/* 325 */     this.ptsTransferidos = ptsTransferidos;
/*     */   }
/*     */   
/* 328 */   public int getValorCuponOrig() { return this.valorCuponOrig; }
/*     */   
/*     */   public void setValorCuponOrig(int valorCuponOrig) {
/* 331 */     this.valorCuponOrig = valorCuponOrig;
/*     */   }
/*     */   
/*     */   public String getCuentaAlianza() {
/* 335 */     return this.cuentaAlianza;
/*     */   }
/*     */   
/* 338 */   public void setCuentaAlianza(String cuentaAlianza) { this.cuentaAlianza = cuentaAlianza; }
/*     */   
/*     */   public String getArchivoSalida() {
/* 341 */     return this.archivoSalida;
/*     */   }
/*     */   
/* 344 */   public void setArchivoSalida(String archivoSalida) { this.archivoSalida = archivoSalida; }
/*     */   
/*     */   public int getPtsCanjeado() {
/* 347 */     return this.ptsCanjeado;
/*     */   }
/*     */   
/* 350 */   public void setPtsCanjeado(int ptsCanjeado) { this.ptsCanjeado = ptsCanjeado; }
/*     */   
/*     */   public int getPtsMillas() {
/* 353 */     return this.ptsMillas;
/*     */   }
/*     */   
/* 356 */   public void setPtsMillas(int ptsMillas) { this.ptsMillas = ptsMillas; }
/*     */   
/*     */   public String getPrecioFormato()
/*     */   {
/* 360 */     return this.precioFormato;
/*     */   }
/*     */   
/* 363 */   public void setPrecioFormato(String precioFormato) { this.precioFormato = precioFormato; }
/*     */   
/*     */   public String getDescuentoFormato() {
/* 366 */     return this.descuentoFormato;
/*     */   }
/*     */   
/* 369 */   public void setDescuentoFormato(String descuentoFormato) { this.descuentoFormato = descuentoFormato; }
/*     */   
/*     */   public String getPrecioIvaFormato() {
/* 372 */     return this.precioIvaFormato;
/*     */   }
/*     */   
/* 375 */   public void setPrecioIvaFormato(String precioIvaFormato) { this.precioIvaFormato = precioIvaFormato; }
/*     */   
/*     */   public String getTipoRedenDB() {
/* 378 */     return this.tipoRedenDB;
/*     */   }
/*     */   
/* 381 */   public void setTipoRedenDB(String tipoRedenDB) { this.tipoRedenDB = tipoRedenDB; }
/*     */   
/*     */   public int getSecuencia() {
/* 384 */     return this.secuencia;
/*     */   }
/*     */   
/* 387 */   public void setSecuencia(int secuencia) { this.secuencia = secuencia; }
/*     */   
/*     */   public int getOpcion() {
/* 390 */     return this.opcion;
/*     */   }
/*     */   
/* 393 */   public void setOpcion(int opcion) { this.opcion = opcion; }
/*     */   
/*     */   public String getPtosDispCF() {
/* 396 */     return this.ptosDispCF;
/*     */   }
/*     */   
/* 399 */   public void setPtosDispCF(String ptosDispCF) { this.ptosDispCF = ptosDispCF; }
/*     */   
/*     */   public String getPtsDispRestantesCF() {
/* 402 */     return this.ptsDispRestantesCF;
/*     */   }
/*     */   
/* 405 */   public void setPtsDispRestantesCF(String ptsDispRestantesCF) { this.ptsDispRestantesCF = ptsDispRestantesCF; }
/*     */   
/*     */   public String getValorPuntosCF() {
/* 408 */     return this.valorPuntosCF;
/*     */   }
/*     */   
/* 411 */   public void setValorPuntosCF(String valorPuntosCF) { this.valorPuntosCF = valorPuntosCF; }
/*     */   
/*     */   public String getPtsMinimosCF() {
/* 414 */     return this.ptsMinimosCF;
/*     */   }
/*     */   
/* 417 */   public void setPtsMinimosCF(String ptsMinimosCF) { this.ptsMinimosCF = ptsMinimosCF; }
/*     */   
/*     */   public String getPtsAcumCF() {
/* 420 */     return this.ptsAcumCF;
/*     */   }
/*     */   
/* 423 */   public void setPtsAcumCF(String ptsAcumCF) { this.ptsAcumCF = ptsAcumCF; }
/*     */   
/*     */   public String getPtsAcumResCF() {
/* 426 */     return this.ptsAcumResCF;
/*     */   }
/*     */   
/* 429 */   public void setPtsAcumResCF(String ptsAcumResCF) { this.ptsAcumResCF = ptsAcumResCF; }
/*     */   
/*     */   public String getPtsTransferidosCF() {
/* 432 */     return this.ptsTransferidosCF;
/*     */   }
/*     */   
/* 435 */   public void setPtsTransferidosCF(String ptsTransferidosCF) { this.ptsTransferidosCF = ptsTransferidosCF; }
/*     */   
/*     */   public String getPtsMillasCF() {
/* 438 */     return this.ptsMillasCF;
/*     */   }
/*     */   
/* 441 */   public void setPtsMillasCF(String ptsMillasCF) { this.ptsMillasCF = ptsMillasCF; }
/*     */   
/*     */   public String getPtsCanjeadoCF() {
/* 444 */     return this.ptsCanjeadoCF;
/*     */   }
/*     */   
/* 447 */   public void setPtsCanjeadoCF(String ptsCanjeadoCF) { this.ptsCanjeadoCF = ptsCanjeadoCF; }
/*     */   
/*     */   public static long getSerialVersionUID() {
/* 450 */     return -2650842559951836887L;
/*     */   }
/*     */   
/* 453 */   public String getTipoAlianza() { return this.tipoAlianza; }
/*     */   
/*     */   public void setTipoAlianza(String tipoAlianza) {
/* 456 */     this.tipoAlianza = tipoAlianza;
/*     */   }
/*     */   
/* 459 */   public String getFechaFAlianza() { return this.fechaFAlianza; }
/*     */   
/*     */   public void setFechaFAlianza(String fechaFAlianza) {
/* 462 */     this.fechaFAlianza = fechaFAlianza;
/*     */   }
/*     */   
/* 465 */   public String getIdReferencia() { return this.idReferencia; }
/*     */   
/*     */   public void setIdReferencia(String idReferencia) {
/* 468 */     this.idReferencia = idReferencia;
/*     */   }
/*     */   
/* 471 */   public int getBonosRoext() { return this.bonosRoext; }
/*     */   
/*     */   public void setBonosRoext(int bonosRoext) {
/* 474 */     this.bonosRoext = bonosRoext;
/*     */   }
/*     */   
/* 477 */   public int getBonosAltoValor() { return this.bonosAltoValor; }
/*     */   
/*     */   public void setBonosAltoValor(int bonosAltoValor) {
/* 480 */     this.bonosAltoValor = bonosAltoValor;
/*     */   }
/*     */   
/* 483 */   public int getBonosGap() { return this.bonosGap; }
/*     */   
/*     */   public void setBonosGap(int bonosGap) {
/* 486 */     this.bonosGap = bonosGap;
/*     */   }
/*     */   
/* 489 */   public String getInbursaFormato() { return this.inbursaFormato; }
/*     */   
/*     */   public void setInbursaFormato(String inbursaFormato) {
/* 492 */     this.inbursaFormato = inbursaFormato;
/*     */   }
/*     */   
/* 495 */   public String getMarcaFormato() { return this.marcaFormato; }
/*     */   
/*     */   public void setMarcaFormato(String marcaFormato) {
/* 498 */     this.marcaFormato = marcaFormato;
/*     */   }
/*     */   
/* 501 */   public String getTelefono() { return this.telefono; }
/*     */   
/*     */   public void setTelefono(String telefono) {
/* 504 */     this.telefono = telefono;
/*     */   }
/*     */   
/* 507 */   public String getFolioRedencion() { return this.folioRedencion; }
/*     */   
/*     */   public void setFolioRedencion(String folioRedencion) {
/* 510 */     this.folioRedencion = folioRedencion;
/*     */   }
/*     */ }


/* Location:              /opt/trabajo/claro/Guatemala/IS/CO/ClaroClub.ear!/ClaroClubEJBClient.jar!/com/claro/transfer/ImpresionTO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */