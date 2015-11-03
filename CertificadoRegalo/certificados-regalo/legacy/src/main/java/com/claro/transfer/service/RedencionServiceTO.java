/*     */ package com.claro.transfer.service;
/*     */ 
/*     */ import com.claro.transfer.MobileTO;
/*     */ import com.claro.transfer.ProductosTO;
/*     */ import com.claro.transfer.PuntoVentaTO;
/*     */ import com.claro.transfer.RedencionTO;
/*     */ import com.claro.transfer.TelefonoSimpleTO;
/*     */ import com.claro.transfer.UsuarioTO;
/*     */ import com.claro.util.Utils;
/*     */ import java.math.BigDecimal;
/*     */ import java.text.SimpleDateFormat;
/*     */ 
/*     */ public class RedencionServiceTO implements java.io.Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -3765146311453053615L;
/*     */   private String cuenta;
/*     */   private String linea;
/*     */   private String fechaOperacion;
/*     */   private String material;
/*     */   private String marca;
/*     */   private String modelo;
/*     */   private String descripcion;
/*     */   private String esnimeiR;
/*     */   private String esnimeiP;
/*     */   private String iccid;
/*     */   private String precio;
/*     */   private String precioIva;
/*     */   private String precioDescuento;
/*     */   private String descuento;
/*     */   private String descripcionDescuento;
/*     */   private String usuario;
/*     */   private String nombreUsuario;
/*     */   private String comentario;
/*     */   private String fechaAdendum;
/*     */   private String fechaFolio;
/*     */   private String folio;
/*     */   private String puntoVenta;
/*     */   private String tipoRedencion;
/*     */   private String planNuevo;
/*     */   private String fechaPlazoSeguro;
/*     */   private String estatus;
/*     */   private String fechaAddendumM2K;
/*     */   private String firstName;
/*     */   private String lastName;
/*     */   private String nombreCliente;
/*     */   private int addendumNuevo;
/*     */   private int ptsTotales;
/*     */   private int region;
/*     */   private int valorPuntos;
/*     */   
/*     */   public RedencionServiceTO(RedencionTO redencionTO)
/*     */   {
/*  53 */     this.cuenta = redencionTO.getTelefonoSimpleTO().getCuenta();
/*  54 */     this.linea = redencionTO.getTelefonoSimpleTO().getLinea();
/*  55 */     this.fechaOperacion = Utils.DATEFORMATdd_MM_YYYY.format(redencionTO.getFechaOperacion());
/*  56 */     this.material = redencionTO.getProductosTO().getIdProducto();
/*  57 */     this.marca = ((redencionTO.getProductosTO().getMarca() == null) || ("null".equals(redencionTO.getProductosTO().getMarca())) ? "" : redencionTO.getProductosTO().getMarca());
/*  58 */     this.modelo = ((redencionTO.getProductosTO().getModelo() == null) || ("null".equals(redencionTO.getProductosTO().getModelo())) ? "" : redencionTO.getProductosTO().getModelo());
/*  59 */     this.ptsTotales = (redencionTO.getPuntosRedimidosTO() != null ? redencionTO.getPuntosRedimidosTO().getPtsTotaltes() : 0);
/*  60 */     this.descuento = redencionTO.getProductosTO().getDescuento().toString();
/*  61 */     this.descripcionDescuento = redencionTO.getProductosTO().getDescripcionDescuento();
/*  62 */     this.precio = redencionTO.getProductosTO().getPrecio().toString();
/*  63 */     this.precioIva = redencionTO.getProductosTO().getPrecioIva().toString();
/*  64 */     this.usuario = redencionTO.getUsuarioTO().getIdUsuario();
/*  65 */     this.precioDescuento = (redencionTO.getProductosTO().getPrecioDescuento() != null ? redencionTO.getProductosTO().getPrecioDescuento().toString() : "");
/*  66 */     this.comentario = redencionTO.getComentario();
/*  67 */     this.fechaAdendum = redencionTO.getFechaAdendum();
/*  68 */     this.fechaFolio = redencionTO.getFechaFolio().toString();
/*  69 */     this.folio = redencionTO.getFolio();
/*  70 */     this.puntoVenta = (redencionTO.getUsuarioTO().getPuntoVentaTO().getPtoVenta() == null ? "" : redencionTO.getUsuarioTO().getPuntoVentaTO().getPtoVenta());
/*  71 */     this.tipoRedencion = redencionTO.getTipoRedencion();
/*  72 */     this.planNuevo = ((redencionTO.getPlanNuevo() == null) || ("null".equals(redencionTO.getPlanNuevo())) ? "" : redencionTO.getPlanNuevo());
/*  73 */     this.addendumNuevo = redencionTO.getAddendumNuevo();
/*  74 */     this.fechaPlazoSeguro = (redencionTO.getFechaPlazoSeg() == null ? "" : Utils.DATEFORMATyyyy_MM_dd.format(redencionTO.getFechaPlazoSeg()));
/*  75 */     this.descripcion = redencionTO.getProductosTO().getDescripcion();
/*  76 */     this.region = redencionTO.getRegion();
/*  77 */     this.estatus = redencionTO.getEstatus();
/*  78 */     this.nombreUsuario = redencionTO.getUsuarioTO().getNombre();
/*  79 */     this.fechaAddendumM2K = (redencionTO.getMobileTO() == null ? "" : redencionTO.getMobileTO().getFecAddM2K());
/*  80 */     this.firstName = (redencionTO.getMobileTO() == null ? "" : redencionTO.getMobileTO().getFirstName());
/*  81 */     this.lastName = (redencionTO.getMobileTO() == null ? "" : redencionTO.getMobileTO().getLastName());
/*  82 */     this.nombreCliente = (redencionTO.getMobileTO().getLastName() + " " + redencionTO.getMobileTO().getFirstName());
/*  83 */     this.valorPuntos = redencionTO.getProductosTO().getValorPuntos();
/*     */     
/*  85 */     this.esnimeiR = ((redencionTO.getProductosTO().getNumeroSerieT() == null) || 
/*  86 */       ("null".equals(redencionTO.getProductosTO().getNumeroSerieT())) || 
/*  87 */       ("0".equals(redencionTO.getProductosTO().getNumeroSerieT())) ? "" : redencionTO.getProductosTO().getNumeroSerieT());
/*     */     
/*  89 */     this.esnimeiP = ((redencionTO.getProductosTO().getNumeroSerieP() == null) || 
/*  90 */       ("null".equals(redencionTO.getProductosTO().getNumeroSerieP())) || 
/*  91 */       ("0".equals(redencionTO.getProductosTO().getNumeroSerieP())) ? "" : redencionTO.getProductosTO().getNumeroSerieP());
/*     */     
/*  93 */     this.iccid = ((redencionTO.getProductosTO().getIccid() == null) || 
/*  94 */       ("null".equals(redencionTO.getProductosTO().getIccid())) || 
/*  95 */       ("0".equals(redencionTO.getProductosTO().getIccid())) ? "" : redencionTO.getProductosTO().getIccid());
/*     */   }
/*     */   
/*     */   public RedencionServiceTO() {}
/*     */   
/* 100 */   public int getPtsTotales() { return this.ptsTotales; }
/*     */   
/*     */   public void setPtsTotales(int ptsTotales) {
/* 103 */     this.ptsTotales = ptsTotales;
/*     */   }
/*     */   
/* 106 */   public String getCuenta() { return this.cuenta; }
/*     */   
/*     */   public void setCuenta(String cuenta) {
/* 109 */     this.cuenta = cuenta;
/*     */   }
/*     */   
/* 112 */   public String getLinea() { return this.linea; }
/*     */   
/*     */   public void setLinea(String linea) {
/* 115 */     this.linea = linea;
/*     */   }
/*     */   
/* 118 */   public String getFechaOperacion() { return this.fechaOperacion; }
/*     */   
/*     */   public void setFechaOperacion(String fechaOperacion) {
/* 121 */     this.fechaOperacion = fechaOperacion;
/*     */   }
/*     */   
/* 124 */   public String getMaterial() { return this.material; }
/*     */   
/*     */   public void setMaterial(String material) {
/* 127 */     this.material = material;
/*     */   }
/*     */   
/* 130 */   public String getMarca() { return this.marca; }
/*     */   
/*     */   public void setMarca(String marca) {
/* 133 */     this.marca = marca;
/*     */   }
/*     */   
/* 136 */   public String getModelo() { return this.modelo; }
/*     */   
/*     */   public void setModelo(String modelo) {
/* 139 */     this.modelo = modelo;
/*     */   }
/*     */   
/* 142 */   public String getPrecio() { return this.precio; }
/*     */   
/*     */   public void setPrecio(String precio) {
/* 145 */     this.precio = precio;
/*     */   }
/*     */   
/* 148 */   public String getDescuento() { return this.descuento; }
/*     */   
/*     */   public void setDescuento(String descuento) {
/* 151 */     this.descuento = descuento;
/*     */   }
/*     */   
/* 154 */   public String getPrecioIva() { return this.precioIva; }
/*     */   
/*     */   public void setPrecioIva(String precioIva) {
/* 157 */     this.precioIva = precioIva;
/*     */   }
/*     */   
/* 160 */   public String getUsuario() { return this.usuario; }
/*     */   
/*     */   public void setUsuario(String usuario) {
/* 163 */     this.usuario = usuario;
/*     */   }
/*     */   
/* 166 */   public String getComentario() { return this.comentario; }
/*     */   
/*     */   public void setComentario(String comentario) {
/* 169 */     this.comentario = comentario;
/*     */   }
/*     */   
/* 172 */   public String getFechaAdendum() { return this.fechaAdendum; }
/*     */   
/*     */   public void setFechaAdendum(String fechaAdendum) {
/* 175 */     this.fechaAdendum = fechaAdendum;
/*     */   }
/*     */   
/* 178 */   public String getFechaFolio() { return this.fechaFolio; }
/*     */   
/*     */   public void setFechaFolio(String fechaFolio) {
/* 181 */     this.fechaFolio = fechaFolio;
/*     */   }
/*     */   
/* 184 */   public String getFolio() { return this.folio; }
/*     */   
/*     */   public void setFolio(String folio) {
/* 187 */     this.folio = folio;
/*     */   }
/*     */   
/*     */ 
/*     */   public String toString()
/*     */   {
/* 193 */     return 
/*     */     
/*     */ 
/* 196 */       this.cuenta + "|" + this.linea + "|" + this.fechaOperacion + "|" + this.material + "|" + this.marca + "|" + this.modelo + "|" + this.ptsTotales + "|" + this.precio + "|" + this.descuento + "|" + this.precioIva + "|" + this.usuario + "|" + this.comentario + "|" + this.fechaAdendum + "|" + this.fechaFolio + "|" + this.folio + "|" + this.puntoVenta + "|" + this.tipoRedencion + "|" + this.addendumNuevo + "|" + this.fechaPlazoSeguro + "|" + this.descripcion + "|" + this.region + "|" + this.esnimeiR + "|" + this.esnimeiP + "|" + this.nombreUsuario + this.iccid + "|" + getFechaAddendumM2K();
/*     */   }
/*     */   
/* 199 */   public String getPuntoVenta() { return this.puntoVenta; }
/*     */   
/*     */   public void setPuntoVenta(String puntoVenta) {
/* 202 */     this.puntoVenta = puntoVenta;
/*     */   }
/*     */   
/* 205 */   public String getTipoRedencion() { return this.tipoRedencion; }
/*     */   
/*     */   public void setTipoRedencion(String tipoRedencion) {
/* 208 */     this.tipoRedencion = tipoRedencion;
/*     */   }
/*     */   
/* 211 */   public String getPlanNuevo() { return this.planNuevo; }
/*     */   
/*     */   public void setPlanNuevo(String planNuevo) {
/* 214 */     this.planNuevo = planNuevo;
/*     */   }
/*     */   
/* 217 */   public int getAddendumNuevo() { return this.addendumNuevo; }
/*     */   
/*     */   public void setAddendumNuevo(int addendumNuevo) {
/* 220 */     this.addendumNuevo = addendumNuevo;
/*     */   }
/*     */   
/* 223 */   public String getFechaPlazoSeguro() { return this.fechaPlazoSeguro; }
/*     */   
/*     */   public void setFechaPlazoSeguro(String fechaPlazoSeguro) {
/* 226 */     this.fechaPlazoSeguro = fechaPlazoSeguro;
/*     */   }
/*     */   
/* 229 */   public String getDescripcion() { return this.descripcion; }
/*     */   
/*     */   public void setDescripcion(String descripcion) {
/* 232 */     this.descripcion = descripcion;
/*     */   }
/*     */   
/* 235 */   public int getRegion() { return this.region; }
/*     */   
/*     */   public void setRegion(int region) {
/* 238 */     this.region = region;
/*     */   }
/*     */   
/* 241 */   public String getEsnimeiR() { return this.esnimeiR; }
/*     */   
/*     */   public void setEsnimeiR(String esnimeiR) {
/* 244 */     this.esnimeiR = esnimeiR;
/*     */   }
/*     */   
/* 247 */   public String getEsnimeiP() { return this.esnimeiP; }
/*     */   
/*     */   public void setEsnimeiP(String esnimeiP) {
/* 250 */     this.esnimeiP = esnimeiP;
/*     */   }
/*     */   
/* 253 */   public String getEstatus() { return this.estatus; }
/*     */   
/*     */   public void setEstatus(String estatus) {
/* 256 */     this.estatus = estatus;
/*     */   }
/*     */   
/* 259 */   public String getNombreUsuario() { return this.nombreUsuario; }
/*     */   
/*     */   public void setNombreUsuario(String nombreUsuario) {
/* 262 */     this.nombreUsuario = nombreUsuario;
/*     */   }
/*     */   
/* 265 */   public String getIccid() { return this.iccid; }
/*     */   
/*     */   public void setIccid(String iccid) {
/* 268 */     this.iccid = iccid;
/*     */   }
/*     */   
/* 271 */   public String getFechaAddendumM2K() { return this.fechaAddendumM2K; }
/*     */   
/*     */   public void setFechaAddendumM2K(String fechaAddencumM2K) {
/* 274 */     this.fechaAddendumM2K = fechaAddencumM2K;
/*     */   }
/*     */   
/* 277 */   public int getValorPuntos() { return this.valorPuntos; }
/*     */   
/*     */   public void setValorPuntos(int valorPuntos) {
/* 280 */     this.valorPuntos = valorPuntos;
/*     */   }
/*     */   
/* 283 */   public String getFirstName() { return this.firstName; }
/*     */   
/*     */   public void setFirstName(String firstName) {
/* 286 */     this.firstName = firstName;
/*     */   }
/*     */   
/* 289 */   public String getLastName() { return this.lastName; }
/*     */   
/*     */   public void setLastName(String lastName) {
/* 292 */     this.lastName = lastName;
/*     */   }
/*     */   
/* 295 */   public String getNombreCliente() { return this.nombreCliente; }
/*     */   
/*     */   public void setNombreCliente(String nombreCliente) {
/* 298 */     this.nombreCliente = nombreCliente;
/*     */   }
/*     */   
/* 301 */   public String getPrecioDescuento() { return this.precioDescuento; }
/*     */   
/*     */   public void setPrecioDescuento(String precioDescuento) {
/* 304 */     this.precioDescuento = precioDescuento;
/*     */   }
/*     */   
/* 307 */   public String getDescripcionDescuento() { return this.descripcionDescuento; }
/*     */   
/*     */   public void setDescripcionDescuento(String descripcionDescuento) {
/* 310 */     this.descripcionDescuento = descripcionDescuento;
/*     */   }
/*     */ }


/* Location:              /opt/trabajo/claro/Guatemala/IS/CO/ClaroClub.ear!/ClaroClubEJBClient.jar!/com/claro/transfer/service/RedencionServiceTO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */