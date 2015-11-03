/*     */ package com.claro.transfer.service;
/*     */ 
/*     */ import com.claro.transfer.MensajeTO;
/*     */ import com.claro.transfer.PerfilTO;
/*     */ import com.claro.transfer.ProductosTO;
/*     */ import com.claro.transfer.PuntoVentaTO;
/*     */ import com.claro.transfer.ReservacionTO;
/*     */ import com.claro.transfer.TelefonoSimpleTO;
/*     */ import com.claro.transfer.UsuarioTO;
/*     */ import java.io.Serializable;
/*     */ import java.math.BigDecimal;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ReservacionServiceTO
/*     */   extends MensajeTO
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 5007688798391824448L;
/*     */   private String folio;
/*     */   private String idProducto;
/*     */   private String comentario;
/*     */   private String usuario;
/*     */   private String puntoVenta;
/*     */   private String descripcion;
/*     */   private String marca;
/*     */   private String modelo;
/*     */   private String tipoRedencion;
/*     */   private String formaRedencion;
/*     */   private String tipoPromocion;
/*     */   private String planNuevo;
/*     */   private String planM2K;
/*     */   private String precioIVA;
/*     */   private String precio;
/*     */   private String descuento;
/*     */   private String idPerfil;
/*     */   private String cuenta;
/*     */   private String telefono;
/*     */   private String fuerzaVenta;
/*     */   private String fechaAddendumM2K;
/*     */   private String fechaAltaUser;
/*     */   private String addendumM2K;
/*     */   private String addendumNuevo;
/*     */   private String estatus;
/*     */   private String estatusCobranza;
/*     */   private String fechaExpiracion;
/*     */   private String fechaOperacion;
/*     */   private String motivo;
/*     */   private int valorPesos;
/*     */   private int valorPuntos;
/*     */   private int bonoRoext;
/*     */   private int bonoAltoValor;
/*     */   private int secuencia;
/*     */   private int region;
/*     */   private int puntosSobrantes;
/*     */   private int puntosDisponibles;
/*     */   private long puntosaRedimir;
/*     */   private String aplicaPromocionGap;
/*     */   private String bonoDescuentoGap;
/*     */   private String productoM2KGap;
/*     */   private String nombrePromocionGap;
/*     */   private String aplicaEP;
/*     */   private int bonosGap;
/*     */   private int idPromocionGapCA;
/*     */   private int idPromocionGap;
/*     */   private int verPromocionGap;
/*     */   
/*     */   public ReservacionServiceTO() {}
/*     */   
/*     */   public ReservacionServiceTO(ReservacionTO _reservacionTO)
/*     */   {
/*  74 */     this.addendumM2K = _reservacionTO.getFechaAdendum();
/*  75 */     this.addendumNuevo = String.valueOf(_reservacionTO.getAddendumNuevo());
/*  76 */     this.bonoAltoValor = _reservacionTO.getProductosTO().getBonosAltoValor();
/*  77 */     this.bonoRoext = _reservacionTO.getProductosTO().getBonosRoext();
/*  78 */     this.comentario = _reservacionTO.getComentario();
/*  79 */     this.cuenta = _reservacionTO.getTelefonoSimpleTO().getCuenta();
/*  80 */     this.descripcion = _reservacionTO.getProductosTO().getDescripcion();
/*  81 */     this.descuento = String.valueOf(_reservacionTO.getProductosTO().getDescuento());
/*  82 */     this.estatus = _reservacionTO.getEstatus();
/*  83 */     this.fechaAddendumM2K = _reservacionTO.getPlazoAnterior();
/*  84 */     this.fechaExpiracion = String.valueOf(_reservacionTO.getFechaExpiracion());
/*  85 */     this.fechaOperacion = String.valueOf(_reservacionTO.getFechaOperacion());
/*  86 */     this.folio = _reservacionTO.getFolio();
/*  87 */     this.formaRedencion = _reservacionTO.getFormaRedencion();
/*  88 */     this.fuerzaVenta = _reservacionTO.getFuerzaVenta();
/*  89 */     this.idPerfil = _reservacionTO.getUsuarioTO().getPerfilTO().getIdPuesto();
/*  90 */     this.idProducto = _reservacionTO.getProductosTO().getIdProducto();
/*  91 */     this.marca = _reservacionTO.getProductosTO().getMarca();
/*  92 */     this.modelo = _reservacionTO.getProductosTO().getModelo();
/*  93 */     this.planNuevo = _reservacionTO.getPlanNuevo();
/*  94 */     this.precio = _reservacionTO.getProductosTO().getPrecio().toString();
/*  95 */     this.precioIVA = _reservacionTO.getProductosTO().getPrecioIva().toString();
/*  96 */     this.puntosSobrantes = _reservacionTO.getPtsSobrantes();
/*  97 */     this.puntoVenta = _reservacionTO.getUsuarioTO().getPuntoVentaTO().getPtoVenta();
/*  98 */     this.region = _reservacionTO.getRegion();
/*  99 */     this.secuencia = _reservacionTO.getTelefonoSimpleTO().getSecuencia();
/* 100 */     this.telefono = _reservacionTO.getTelefonoSimpleTO().getLinea();
/* 101 */     this.tipoPromocion = _reservacionTO.getProductosTO().getTipoPromocion();
/* 102 */     this.tipoRedencion = _reservacionTO.getTipoRedencion();
/* 103 */     this.usuario = _reservacionTO.getUsuarioTO().getIdUsuario();
/* 104 */     this.valorPesos = _reservacionTO.getProductosTO().getDifPesos();
/* 105 */     this.valorPuntos = _reservacionTO.getProductosTO().getValorPuntos();
/*     */     
/* 107 */     this.aplicaPromocionGap = _reservacionTO.getProductosTO().getAplicaPromocionGap();
/* 108 */     this.bonoDescuentoGap = _reservacionTO.getProductosTO().getBonoDescuentoGap();
/* 109 */     this.productoM2KGap = _reservacionTO.getProductosTO().getProductoM2KGap();
/* 110 */     this.nombrePromocionGap = _reservacionTO.getProductosTO().getNombrePromocionGap();
/* 111 */     this.bonosGap = _reservacionTO.getProductosTO().getBonosGap();
/* 112 */     this.idPromocionGapCA = _reservacionTO.getProductosTO().getIdPromocionGapCA();
/* 113 */     this.idPromocionGap = _reservacionTO.getProductosTO().getIdPromocionGap();
/* 114 */     this.verPromocionGap = _reservacionTO.getProductosTO().getVerPromocionGap();
/* 115 */     this.aplicaEP = _reservacionTO.getProductosTO().getAplicaEP();
/*     */   }
/*     */   
/*     */   public long getPuntosaRedimir() {
/* 119 */     return this.puntosaRedimir;
/*     */   }
/*     */   
/* 122 */   public void setPuntosaRedimir(long puntosaRedimir) { this.puntosaRedimir = puntosaRedimir; }
/*     */   
/*     */   public int getPuntosDisponibles() {
/* 125 */     return this.puntosDisponibles;
/*     */   }
/*     */   
/* 128 */   public void setPuntosDisponibles(int puntosDisponibles) { this.puntosDisponibles = puntosDisponibles; }
/*     */   
/*     */   public String getFechaOperacion() {
/* 131 */     return this.fechaOperacion;
/*     */   }
/*     */   
/* 134 */   public void setFechaOperacion(String fechaOperacion) { this.fechaOperacion = fechaOperacion; }
/*     */   
/*     */   public String getFechaExpiracion() {
/* 137 */     return this.fechaExpiracion;
/*     */   }
/*     */   
/* 140 */   public void setFechaExpiracion(String fechaExpiracion) { this.fechaExpiracion = fechaExpiracion; }
/*     */   
/*     */   public String getEstatus() {
/* 143 */     return this.estatus;
/*     */   }
/*     */   
/* 146 */   public void setEstatus(String estatus) { this.estatus = estatus; }
/*     */   
/*     */   public String getFolio() {
/* 149 */     return this.folio;
/*     */   }
/*     */   
/* 152 */   public void setFolio(String folio) { this.folio = folio; }
/*     */   
/*     */   public int getPuntosSobrantes() {
/* 155 */     return this.puntosSobrantes;
/*     */   }
/*     */   
/* 158 */   public void setPuntosSobrantes(int puntosSobrantes) { this.puntosSobrantes = puntosSobrantes; }
/*     */   
/*     */   public String getPrecio() {
/* 161 */     return this.precio;
/*     */   }
/*     */   
/* 164 */   public void setPrecio(String precio) { this.precio = precio; }
/*     */   
/*     */   public String getAddendumM2K() {
/* 167 */     return this.addendumM2K;
/*     */   }
/*     */   
/* 170 */   public void setAddendumM2K(String addendumM2K) { this.addendumM2K = addendumM2K; }
/*     */   
/*     */   public String getFechaAltaUser() {
/* 173 */     return this.fechaAltaUser;
/*     */   }
/*     */   
/* 176 */   public void setFechaAltaUser(String fechaAltaUser) { this.fechaAltaUser = fechaAltaUser; }
/*     */   
/*     */   public String getFechaAddendumM2K()
/*     */   {
/* 180 */     if ((this.fechaAddendumM2K == null) || (this.fechaAddendumM2K.trim().equals("")) || 
/* 181 */       (this.fechaAddendumM2K.trim().equals("00/00/00"))) {
/* 182 */       return this.fechaAltaUser;
/*     */     }
/* 184 */     return this.fechaAddendumM2K;
/*     */   }
/*     */   
/* 187 */   public void setFechaAddendumM2K(String fechaAddendumM2K) { this.fechaAddendumM2K = fechaAddendumM2K; }
/*     */   
/*     */   public String getFuerzaVenta()
/*     */   {
/* 191 */     return this.fuerzaVenta;
/*     */   }
/*     */   
/* 194 */   public void setFuerzaVenta(String fuerzaVenta) { this.fuerzaVenta = fuerzaVenta; }
/*     */   
/*     */   public int getRegion() {
/* 197 */     return this.region;
/*     */   }
/*     */   
/* 200 */   public void setRegion(int region) { this.region = region; }
/*     */   
/*     */   public String getIdProducto() {
/* 203 */     return this.idProducto;
/*     */   }
/*     */   
/* 206 */   public void setIdProducto(String idProducto) { this.idProducto = idProducto; }
/*     */   
/*     */   public String getComentario() {
/* 209 */     return this.comentario;
/*     */   }
/*     */   
/* 212 */   public void setComentario(String comentario) { this.comentario = comentario; }
/*     */   
/*     */   public String getUsuario() {
/* 215 */     return this.usuario;
/*     */   }
/*     */   
/* 218 */   public void setUsuario(String usuario) { this.usuario = usuario; }
/*     */   
/*     */   public String getPuntoVenta()
/*     */   {
/* 222 */     return this.puntoVenta;
/*     */   }
/*     */   
/* 225 */   public void setPuntoVenta(String puntoVenta) { this.puntoVenta = puntoVenta; }
/*     */   
/*     */   public String getDescripcion() {
/* 228 */     return this.descripcion;
/*     */   }
/*     */   
/* 231 */   public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
/*     */   
/*     */   public String getMarca() {
/* 234 */     return this.marca;
/*     */   }
/*     */   
/* 237 */   public void setMarca(String marca) { this.marca = marca; }
/*     */   
/*     */   public String getModelo() {
/* 240 */     return this.modelo;
/*     */   }
/*     */   
/* 243 */   public void setModelo(String modelo) { this.modelo = modelo; }
/*     */   
/*     */   public String getTipoRedencion() {
/* 246 */     return this.tipoRedencion;
/*     */   }
/*     */   
/* 249 */   public void setTipoRedencion(String tipoRedencion) { this.tipoRedencion = tipoRedencion; }
/*     */   
/*     */   public String getFormaRedencion() {
/* 252 */     return this.formaRedencion;
/*     */   }
/*     */   
/* 255 */   public void setFormaRedencion(String formaRedencion) { this.formaRedencion = formaRedencion; }
/*     */   
/*     */   public String getTipoPromocion() {
/* 258 */     return this.tipoPromocion;
/*     */   }
/*     */   
/* 261 */   public void setTipoPromocion(String tipoPromocion) { this.tipoPromocion = tipoPromocion; }
/*     */   
/*     */   public String getPlanNuevo() {
/* 264 */     return this.planNuevo;
/*     */   }
/*     */   
/* 267 */   public void setPlanNuevo(String planNuevo) { this.planNuevo = planNuevo; }
/*     */   
/*     */   public String getPrecioIVA() {
/* 270 */     return this.precioIVA;
/*     */   }
/*     */   
/* 273 */   public void setPrecioIVA(String precioIVA) { this.precioIVA = precioIVA; }
/*     */   
/*     */   public String getDescuento() {
/* 276 */     return this.descuento;
/*     */   }
/*     */   
/* 279 */   public void setDescuento(String descuento) { this.descuento = descuento; }
/*     */   
/*     */   public String getIdPerfil() {
/* 282 */     return this.idPerfil;
/*     */   }
/*     */   
/* 285 */   public void setIdPerfil(String idPerfil) { this.idPerfil = idPerfil; }
/*     */   
/*     */   public String getCuenta() {
/* 288 */     return this.cuenta;
/*     */   }
/*     */   
/* 291 */   public void setCuenta(String cuenta) { this.cuenta = cuenta; }
/*     */   
/*     */   public String getTelefono() {
/* 294 */     return this.telefono;
/*     */   }
/*     */   
/* 297 */   public void setTelefono(String telefono) { this.telefono = telefono; }
/*     */   
/*     */   public int getValorPesos() {
/* 300 */     return this.valorPesos;
/*     */   }
/*     */   
/* 303 */   public void setValorPesos(int valorPesos) { this.valorPesos = valorPesos; }
/*     */   
/*     */   public int getValorPuntos() {
/* 306 */     return this.valorPuntos;
/*     */   }
/*     */   
/* 309 */   public void setValorPuntos(int valorPuntos) { this.valorPuntos = valorPuntos; }
/*     */   
/*     */   public String getAddendumNuevo() {
/* 312 */     return this.addendumNuevo;
/*     */   }
/*     */   
/* 315 */   public void setAddendumNuevo(String addendumNuevo) { this.addendumNuevo = addendumNuevo; }
/*     */   
/*     */   public int getBonoRoext() {
/* 318 */     return this.bonoRoext;
/*     */   }
/*     */   
/* 321 */   public void setBonoRoext(int bonoRoext) { this.bonoRoext = bonoRoext; }
/*     */   
/*     */   public int getBonoAltoValor() {
/* 324 */     return this.bonoAltoValor;
/*     */   }
/*     */   
/* 327 */   public void setBonoAltoValor(int bonoAltoValor) { this.bonoAltoValor = bonoAltoValor; }
/*     */   
/*     */   public int getSecuencia() {
/* 330 */     return this.secuencia;
/*     */   }
/*     */   
/* 333 */   public void setSecuencia(int secuencia) { this.secuencia = secuencia; }
/*     */   
/*     */   public String getMotivo() {
/* 336 */     return this.motivo;
/*     */   }
/*     */   
/* 339 */   public void setMotivo(String motivo) { this.motivo = motivo; }
/*     */   
/*     */   public String getEstatusCobranza() {
/* 342 */     return this.estatusCobranza;
/*     */   }
/*     */   
/* 345 */   public void setEstatusCobranza(String estatusCobranza) { this.estatusCobranza = estatusCobranza; }
/*     */   
/*     */   public String getPlanM2K() {
/* 348 */     return this.planM2K;
/*     */   }
/*     */   
/* 351 */   public void setPlanM2K(String planM2K) { this.planM2K = planM2K; }
/*     */   
/*     */   public String getAplicaPromocionGap()
/*     */   {
/* 355 */     return this.aplicaPromocionGap;
/*     */   }
/*     */   
/*     */   public void setAplicaPromocionGap(String aplicaPromocionGap) {
/* 359 */     this.aplicaPromocionGap = aplicaPromocionGap;
/*     */   }
/*     */   
/*     */   public String getBonoDescuentoGap() {
/* 363 */     return this.bonoDescuentoGap;
/*     */   }
/*     */   
/*     */   public void setBonoDescuentoGap(String bonoDescuentoGap) {
/* 367 */     this.bonoDescuentoGap = bonoDescuentoGap;
/*     */   }
/*     */   
/*     */   public String getProductoM2KGap() {
/* 371 */     return this.productoM2KGap;
/*     */   }
/*     */   
/*     */   public void setProductoM2KGap(String productoM2KGap) {
/* 375 */     this.productoM2KGap = productoM2KGap;
/*     */   }
/*     */   
/*     */   public String getNombrePromocionGap() {
/* 379 */     return this.nombrePromocionGap;
/*     */   }
/*     */   
/*     */   public void setNombrePromocionGap(String nombrePromocionGap) {
/* 383 */     this.nombrePromocionGap = nombrePromocionGap;
/*     */   }
/*     */   
/*     */   public int getBonosGap() {
/* 387 */     return this.bonosGap;
/*     */   }
/*     */   
/*     */   public void setBonosGap(int bonosGap) {
/* 391 */     this.bonosGap = bonosGap;
/*     */   }
/*     */   
/*     */   public int getIdPromocionGapCA() {
/* 395 */     return this.idPromocionGapCA;
/*     */   }
/*     */   
/*     */   public void setIdPromocionGapCA(int idPromocionGapCA) {
/* 399 */     this.idPromocionGapCA = idPromocionGapCA;
/*     */   }
/*     */   
/*     */   public int getIdPromocionGap() {
/* 403 */     return this.idPromocionGap;
/*     */   }
/*     */   
/*     */   public void setIdPromocionGap(int idPromocionGap) {
/* 407 */     this.idPromocionGap = idPromocionGap;
/*     */   }
/*     */   
/*     */   public int getVerPromocionGap() {
/* 411 */     return this.verPromocionGap;
/*     */   }
/*     */   
/*     */   public void setVerPromocionGap(int verPromocionGap) {
/* 415 */     this.verPromocionGap = verPromocionGap;
/*     */   }
/*     */   
/*     */   public String getAplicaEP() {
/* 419 */     return this.aplicaEP;
/*     */   }
/*     */   
/*     */   public void setAplicaEP(String aplicaEP) {
/* 423 */     this.aplicaEP = aplicaEP;
/*     */   }
/*     */ }


/* Location:              /opt/trabajo/claro/Guatemala/IS/CO/ClaroClub.ear!/ClaroClubEJBClient.jar!/com/claro/transfer/service/ReservacionServiceTO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */