/*     */ package com.claro.transfer;
/*     */ 
/*     */ import com.claro.util.Utils;

/*     */ import java.io.Serializable;
/*     */ import java.sql.Date;
/*     */ import java.sql.Timestamp;
/*     */ import java.util.ArrayList;
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
/*     */ public class AlianzasTO
/*     */   extends MensajeTO
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private String folio;
/*     */   private String cuentaAlianza;
/*     */   private int idCuentaAlianza;
/*     */   private int statusTrans;
/*     */   private int millas;
/*     */   private int numeroCanje;
/*     */   private int valorDls;
/*     */   private int valorCuponOrig;
/*     */   private int opcion;
/*     */   private Date fechaOperacion;
/*     */   private Date fechaUpd;
/*     */   private Date vigenciaMax;
/*     */   private String comentario;
/*     */   private String usuario;
/*     */   private String estatus;
/*     */   private String archivoSalida;
/*     */   private String claveFactura;
/*     */   private String idPuntoVenta;
/*     */   private String idReferencia;
/*     */   private String idUsuarioUpd;
/*     */   private String factor;
/*     */   private String millasMin;
/*     */   private String cuenta;
/*     */   private int secuencia;
/*     */   private String nombre;
/*     */   private String aPaterno;
/*     */   private String aMaterno;
/*     */   private String apPaterno;
/*     */   private String apMaterno;
/*     */   private String cLinea;
/*     */   private String cveAlianza;
/*     */   private String mensajeEstatusUltimoCanje;
/*     */   private String statusAlianza;
/*     */   private Integer porcAntig;
/*     */   private Integer porcArpu;
/*     */   private Integer porcCob;
/*     */   private Integer vCertif;
/*     */   private Integer vCentifExtra;
/*     */   private Timestamp fechaToper;
/*     */   private int ptsTransferidos;
/*     */   private String ptsTransferidosCF;
/*     */   private Timestamp fechaFolio;
/*     */   private PuntosRedimidosTO puntosRedimidosTO;
/*     */   private TelefonoSimpleTO telefonoSimpleTO;
/*     */   private ArrayList<AlianzasTO> cupones;
/*     */   private ArrayList<ProductosTO> productos;
/*     */   
/*     */   public ArrayList<AlianzasTO> getCupones()
/*     */   {
/*  72 */     return this.cupones;
/*     */   }
/*     */   
/*  75 */   public void setCupones(ArrayList<AlianzasTO> cupones) { this.cupones = cupones; }
/*     */   
/*     */   public ArrayList<ProductosTO> getProductos() {
/*  78 */     return this.productos;
/*     */   }
/*     */   
/*  81 */   public void setProductos(ArrayList<ProductosTO> productos) { this.productos = productos; }
/*     */   
/*     */   public String getFolio() {
/*  84 */     return this.folio;
/*     */   }
/*     */   
/*  87 */   public void setFolio(String folio) { this.folio = folio; }
/*     */   
/*     */   public String getCuentaAlianza() {
/*  90 */     return this.cuentaAlianza;
/*     */   }
/*     */   
/*  93 */   public void setCuentaAlianza(String cuentaAlianza) { this.cuentaAlianza = cuentaAlianza; }
/*     */   
/*     */   public int getIdCuentaAlianza() {
/*  96 */     return this.idCuentaAlianza;
/*     */   }
/*     */   
/*  99 */   public void setIdCuentaAlianza(int idCuentaAlianza) { this.idCuentaAlianza = idCuentaAlianza; }
/*     */   
/*     */   public int getStatusTrans() {
/* 102 */     return this.statusTrans;
/*     */   }
/*     */   
/* 105 */   public void setStatusTrans(int statusTrans) { this.statusTrans = statusTrans; }
/*     */   
/*     */   public int getMillas() {
/* 108 */     return this.millas;
/*     */   }
/*     */   
/* 111 */   public void setMillas(int millas) { this.millas = millas; }
/*     */   
/*     */   public int getNumeroCanje() {
/* 114 */     return this.numeroCanje;
/*     */   }
/*     */   
/* 117 */   public void setNumeroCanje(int numeroCanje) { this.numeroCanje = numeroCanje; }
/*     */   
/*     */   public int getValorDls() {
/* 120 */     return this.valorDls;
/*     */   }
/*     */   
/* 123 */   public void setValorDls(int valorDls) { this.valorDls = valorDls; }
/*     */   
/*     */   public int getValorCuponOrig() {
/* 126 */     return this.valorCuponOrig;
/*     */   }
/*     */   
/* 129 */   public void setValorCuponOrig(int valorCuponOrig) { this.valorCuponOrig = valorCuponOrig; }
/*     */   
/*     */   public int getOpcion() {
/* 132 */     return this.opcion;
/*     */   }
/*     */   
/* 135 */   public void setOpcion(int opcion) { this.opcion = opcion; }
/*     */   
/*     */   public Date getFechaOperacion() {
/* 138 */     return this.fechaOperacion;
/*     */   }
/*     */   
/* 141 */   public void setFechaOperacion(Date fechaOperacion) { this.fechaOperacion = fechaOperacion; }
/*     */   
/*     */   public Date getFechaUpd() {
/* 144 */     return this.fechaUpd;
/*     */   }
/*     */   
/* 147 */   public void setFechaUpd(Date fechaUpd) { this.fechaUpd = fechaUpd; }
/*     */   
/*     */   public Date getVigenciaMax() {
/* 150 */     return this.vigenciaMax;
/*     */   }
/*     */   
/* 153 */   public void setVigenciaMax(Date vigenciaMax) { this.vigenciaMax = vigenciaMax; }
/*     */   
/*     */   public String getComentario() {
/* 156 */     return this.comentario;
/*     */   }
/*     */   
/* 159 */   public void setComentario(String comentario) { this.comentario = comentario; }
/*     */   
/*     */   public String getUsuario() {
/* 162 */     return this.usuario;
/*     */   }
/*     */   
/* 165 */   public void setUsuario(String usuario) { this.usuario = usuario; }
/*     */   
/*     */   public String getEstatus() {
/* 168 */     return this.estatus;
/*     */   }
/*     */   
/* 171 */   public void setEstatus(String estatus) { this.estatus = estatus; }
/*     */   
/*     */   public String getArchivoSalida() {
/* 174 */     return this.archivoSalida;
/*     */   }
/*     */   
/* 177 */   public void setArchivoSalida(String archivoSalida) { this.archivoSalida = archivoSalida; }
/*     */   
/*     */   public String getClaveFactura() {
/* 180 */     return this.claveFactura;
/*     */   }
/*     */   
/* 183 */   public void setClaveFactura(String claveFactura) { this.claveFactura = claveFactura; }
/*     */   
/*     */   public String getIdPuntoVenta() {
/* 186 */     return this.idPuntoVenta;
/*     */   }
/*     */   
/* 189 */   public void setIdPuntoVenta(String idPuntoVenta) { this.idPuntoVenta = idPuntoVenta; }
/*     */   
/*     */   public String getIdReferencia() {
/* 192 */     return this.idReferencia;
/*     */   }
/*     */   
/* 195 */   public void setIdReferencia(String idReferencia) { this.idReferencia = idReferencia; }
/*     */   
/*     */   public String getIdUsuarioUpd() {
/* 198 */     return this.idUsuarioUpd;
/*     */   }
/*     */   
/* 201 */   public void setIdUsuarioUpd(String idUsuarioUpd) { this.idUsuarioUpd = idUsuarioUpd; }
/*     */   
/*     */   public Timestamp getFechaFolio() {
/* 204 */     return this.fechaFolio;
/*     */   }
/*     */   
/* 207 */   public void setFechaFolio(Timestamp fechaFolio) { this.fechaFolio = fechaFolio; }
/*     */   
/*     */   public PuntosRedimidosTO getPuntosRedimidosTO() {
/* 210 */     return this.puntosRedimidosTO;
/*     */   }
/*     */   
/* 213 */   public void setPuntosRedimidosTO(PuntosRedimidosTO puntosRedimidosTO) { this.puntosRedimidosTO = puntosRedimidosTO; }
/*     */   
/*     */   public TelefonoSimpleTO getTelefonoSimpleTO() {
/* 216 */     return this.telefonoSimpleTO;
/*     */   }
/*     */   
/* 219 */   public void setTelefonoSimpleTO(TelefonoSimpleTO telefonoSimpleTO) { this.telefonoSimpleTO = telefonoSimpleTO; }
/*     */   
/*     */   public String getCuenta() {
/* 222 */     return this.cuenta;
/*     */   }
/*     */   
/* 225 */   public void setCuenta(String cuenta) { this.cuenta = cuenta; }
/*     */   
/*     */   public int getSecuencia() {
/* 228 */     return this.secuencia;
/*     */   }
/*     */   
/* 231 */   public void setSecuencia(int secuencia) { this.secuencia = secuencia; }
/*     */   
/*     */   public String getNombre() {
/* 234 */     return this.nombre;
/*     */   }
/*     */   
/* 237 */   public void setNombre(String nombre) { this.nombre = nombre; }
/*     */   
/*     */   public String getAPaterno() {
/* 240 */     return this.aPaterno;
/*     */   }
/*     */   
/* 243 */   public void setAPaterno(String paterno) { this.aPaterno = paterno; }
/*     */   
/*     */   public String getAMaterno() {
/* 246 */     return this.aMaterno;
/*     */   }
/*     */   
/* 249 */   public void setAMaterno(String materno) { this.aMaterno = materno; }
/*     */   
/*     */   public String getCLinea() {
/* 252 */     return this.cLinea;
/*     */   }
/*     */   
/* 255 */   public void setCLinea(String linea) { this.cLinea = linea; }
/*     */   
/*     */   public String getCveAlianza() {
/* 258 */     return this.cveAlianza;
/*     */   }
/*     */   
/* 261 */   public void setCveAlianza(String cveAlianza) { this.cveAlianza = cveAlianza; }
/*     */   
/*     */   public Integer getPorcAntig() {
/* 264 */     return this.porcAntig;
/*     */   }
/*     */   
/* 267 */   public void setPorcAntig(Integer porcAntig) { this.porcAntig = porcAntig; }
/*     */   
/*     */   public Integer getPorcArpu() {
/* 270 */     return this.porcArpu;
/*     */   }
/*     */   
/* 273 */   public void setPorcArpu(Integer porcArpu) { this.porcArpu = porcArpu; }
/*     */   
/*     */   public Integer getPorcCob() {
/* 276 */     return this.porcCob;
/*     */   }
/*     */   
/* 279 */   public void setPorcCob(Integer porcCob) { this.porcCob = porcCob; }
/*     */   
/*     */   public Integer getVCertif() {
/* 282 */     return this.vCertif;
/*     */   }
/*     */   
/* 285 */   public void setVCertif(Integer certif) { this.vCertif = certif; }
/*     */   
/*     */   public Integer getVCentifExtra() {
/* 288 */     return this.vCentifExtra;
/*     */   }
/*     */   
/* 291 */   public void setVCentifExtra(Integer centifExtra) { this.vCentifExtra = centifExtra; }
/*     */   
/*     */   public Timestamp getFechaToper() {
/* 294 */     return this.fechaToper;
/*     */   }
/*     */   
/* 297 */   public void setFechaToper(Timestamp fechaToper) { this.fechaToper = fechaToper; }
/*     */   
/*     */   public int getPtsTransferidos() {
/* 300 */     return this.ptsTransferidos;
/*     */   }
/*     */   
/* 303 */   public void setPtsTransferidos(int ptsTransferidos) { this.ptsTransferidos = ptsTransferidos; }
/*     */   
/*     */   public String getMensajeEstatusUltimoCanje() {
/* 306 */     return this.mensajeEstatusUltimoCanje;
/*     */   }
/*     */   
/* 309 */   public void setMensajeEstatusUltimoCanje(String mensajeEstatusUltimoCanje) { this.mensajeEstatusUltimoCanje = mensajeEstatusUltimoCanje; }
/*     */   
/*     */   public String getStatusAlianza() {
/* 312 */     return this.statusAlianza;
/*     */   }
/*     */   
/* 315 */   public void setStatusAlianza(String statusAlianza) { this.statusAlianza = statusAlianza; }
/*     */   
/*     */   public String getFactor() {
/* 318 */     return this.factor;
/*     */   }
/*     */   
/* 321 */   public void setFactor(String factor) { this.factor = factor; }
/*     */   
/*     */   public String getMillasMin() {
/* 324 */     return this.millasMin;
/*     */   }
/*     */   
/* 327 */   public void setMillasMin(String millasMin) { this.millasMin = millasMin; }
/*     */   
/*     */   public String getPtsTransferidosCF() {
/* 330 */     if (this.ptsTransferidos > 0) this.ptsTransferidosCF = Utils.setFormatoPtos(this.ptsTransferidos);
/* 331 */     return this.ptsTransferidosCF;
/*     */   }
/*     */   
/* 334 */   public String getApPaterno() { this.apPaterno = this.aPaterno;
/* 335 */     return this.apPaterno;
/*     */   }
/*     */   
/* 338 */   public void setApPaterno(String apPaterno) { this.apPaterno = apPaterno; }
/*     */   
/*     */   public String getApMaterno() {
/* 341 */     this.apMaterno = this.aMaterno;
/* 342 */     return this.apMaterno;
/*     */   }
/*     */   
/* 345 */   public void setApMaterno(String apMaterno) { this.apMaterno = this.aMaterno; }
/*     */ }


/* Location:              /opt/trabajo/claro/Guatemala/IS/CO/ClaroClub.ear!/ClaroClubEJBClient.jar!/com/claro/transfer/AlianzasTO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */