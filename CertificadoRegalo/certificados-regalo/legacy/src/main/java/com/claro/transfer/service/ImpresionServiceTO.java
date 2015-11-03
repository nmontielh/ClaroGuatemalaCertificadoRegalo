/*     */ package com.claro.transfer.service;
/*     */ 
/*     */ import com.claro.transfer.ImpresionTO;
/*     */ import com.claro.transfer.MensajeTO;
/*     */ import com.claro.util.Constantes;
/*     */ import java.io.Serializable;
/*     */ import java.text.SimpleDateFormat;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ImpresionServiceTO
/*     */   extends MensajeTO
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1815031627280032289L;
/*     */   private String idPuntoVenta;
/*     */   private String planAnterior;
/*     */   private String fechaPlazoAnterior;
/*     */   private String fechaOperacion;
/*     */   private String planNuevo;
/*     */   private String fechaPlazoSeguro;
/*     */   private String fechaFolio;
/*     */   private String tipoRedencion;
/*     */   private String formaRedencion;
/*     */   private String idProducto;
/*     */   private String descripcion;
/*     */   private String marca;
/*     */   private String modelo;
/*     */   private String precio;
/*     */   private String precioIva;
/*     */   private String comentario;
/*     */   private String esnImeiR;
/*     */   private String esnImeiP;
/*     */   private String iccid;
/*     */   private String folio;
/*     */   private String descuento;
/*     */   private String ptsDispRestantesCF;
/*     */   private String valorPuntosCF;
/*     */   private int addendumAnterior;
/*     */   private int puntosDisponibles;
/*     */   private int valorPuntos;
/*     */   private int puntosDispRestantes;
/*     */   private int addendumNuevo;
/*     */   private int puntosminimos;
/*     */   private int bonoProrrateo;
/*     */   private int puntosAcumulados;
/*     */   private int puntosAcumuladosRestantes;
/*     */   private int bonosAltoValor;
/*     */   private int bonosRoext;
/*     */   private int bonosGap;
/*     */   
/*     */   public ImpresionServiceTO(ImpresionTO impresionTO)
/*     */   {
/*  56 */     agregaMensaje(impresionTO.getIdMensaje(), impresionTO.getMensaje());
/*  57 */     this.idPuntoVenta = impresionTO.getIdPuntoVenta();
/*  58 */     this.planAnterior = impresionTO.getPlanAnt();
/*  59 */     this.addendumAnterior = impresionTO.getAddAnt();
/*  60 */     this.fechaPlazoAnterior = (impresionTO.getFechaPlazoAnt() == null ? "" : Constantes.DATEFORMATMM_dd_YYYY.format(impresionTO.getFechaPlazoAnt()));
/*  61 */     this.fechaOperacion = (impresionTO.getFechaOperacion() == null ? "" : Constantes.DATEFORMATMM_dd_YYYY.format(impresionTO.getFechaOperacion()));
/*  62 */     this.puntosDisponibles = impresionTO.getPtosDisp();
/*  63 */     this.valorPuntos = impresionTO.getValorPuntos();
/*  64 */     this.puntosDispRestantes = impresionTO.getPtsDispRestantes();
/*  65 */     this.planNuevo = impresionTO.getPlanNuevo();
/*  66 */     this.addendumNuevo = impresionTO.getAddNuevo();
/*  67 */     this.fechaPlazoSeguro = (impresionTO.getFechaPlazoSeg() == null ? "" : Constantes.DATEFORMATMM_dd_YYYY.format(impresionTO.getFechaPlazoSeg()));
/*  68 */     this.fechaFolio = (impresionTO.getFechaFolio() == null ? "" : Constantes.DATEFORMATMM_dd_YYYY.format(impresionTO.getFechaFolio()));
/*  69 */     this.tipoRedencion = impresionTO.getTipoReden();
/*  70 */     this.formaRedencion = impresionTO.getFormaReden();
/*  71 */     this.idProducto = impresionTO.getIdProducto();
/*  72 */     this.descripcion = impresionTO.getDescripcion();
/*  73 */     this.marca = impresionTO.getMarca();
/*  74 */     this.modelo = impresionTO.getModelo();
/*  75 */     this.precio = (impresionTO.getPrecioFormato() != null ? impresionTO.getPrecioFormato() : "");
/*  76 */     this.descuento = (impresionTO.getDescuentoFormato() != null ? impresionTO.getDescuentoFormato() : "");
/*  77 */     this.precioIva = (impresionTO.getPrecioIvaFormato() != null ? impresionTO.getPrecioIvaFormato() : "");
/*  78 */     this.comentario = impresionTO.getComentario();
/*  79 */     this.esnImeiR = impresionTO.getEsnImeiR();
/*  80 */     this.esnImeiP = impresionTO.getEsnImeiP();
/*  81 */     this.iccid = impresionTO.getIccid();
/*  82 */     this.puntosminimos = impresionTO.getPtsMinimos();
/*  83 */     this.bonoProrrateo = impresionTO.getBonoProrr();
/*  84 */     this.puntosAcumulados = impresionTO.getPtsAcum();
/*  85 */     this.puntosAcumuladosRestantes = impresionTO.getPtsAcumRes();
/*  86 */     this.folio = impresionTO.getFolio();
/*  87 */     this.ptsDispRestantesCF = impresionTO.getPtsDispRestantesCF();
/*  88 */     this.valorPuntosCF = impresionTO.getValorPuntosCF();
/*  89 */     this.bonosAltoValor = impresionTO.getBonosAltoValor();
/*  90 */     this.bonosRoext = impresionTO.getBonosRoext();
/*  91 */     this.bonosGap = impresionTO.getBonosGap();
/*     */   }
/*     */   
/*     */   public ImpresionServiceTO() {}
/*     */   
/*     */   public String getIdPuntoVenta()
/*     */   {
/*  98 */     return this.idPuntoVenta;
/*     */   }
/*     */   
/*     */   public String getPlanAnterior() {
/* 102 */     return this.planAnterior;
/*     */   }
/*     */   
/*     */   public String getFechaPlazoAnterior() {
/* 106 */     return this.fechaPlazoAnterior;
/*     */   }
/*     */   
/*     */   public String getFechaOperacion() {
/* 110 */     return this.fechaOperacion;
/*     */   }
/*     */   
/*     */   public String getPlanNuevo() {
/* 114 */     return this.planNuevo;
/*     */   }
/*     */   
/*     */   public String getFechaPlazoSeguro() {
/* 118 */     return this.fechaPlazoSeguro;
/*     */   }
/*     */   
/*     */   public String getFechaFolio() {
/* 122 */     return this.fechaFolio;
/*     */   }
/*     */   
/*     */   public String getTipoRedencion() {
/* 126 */     return this.tipoRedencion;
/*     */   }
/*     */   
/*     */   public String getFormaRedencion() {
/* 130 */     return this.formaRedencion;
/*     */   }
/*     */   
/*     */   public String getIdProducto() {
/* 134 */     return this.idProducto;
/*     */   }
/*     */   
/*     */   public String getDescripcion() {
/* 138 */     return this.descripcion;
/*     */   }
/*     */   
/*     */   public String getMarca() {
/* 142 */     return this.marca;
/*     */   }
/*     */   
/*     */   public String getModelo() {
/* 146 */     return this.modelo;
/*     */   }
/*     */   
/*     */   public String getPrecio() {
/* 150 */     return this.precio;
/*     */   }
/*     */   
/*     */   public String getPrecioIva() {
/* 154 */     return this.precioIva;
/*     */   }
/*     */   
/*     */   public String getComentario() {
/* 158 */     return this.comentario;
/*     */   }
/*     */   
/*     */   public String getEsnImeiR() {
/* 162 */     return this.esnImeiR;
/*     */   }
/*     */   
/*     */   public String getEsnImeiP() {
/* 166 */     return this.esnImeiP;
/*     */   }
/*     */   
/*     */   public String getIccid() {
/* 170 */     return this.iccid;
/*     */   }
/*     */   
/*     */   public String getFolio() {
/* 174 */     return this.folio;
/*     */   }
/*     */   
/*     */   public String getDescuento() {
/* 178 */     return this.descuento;
/*     */   }
/*     */   
/*     */   public int getAddendumAnterior() {
/* 182 */     return this.addendumAnterior;
/*     */   }
/*     */   
/*     */   public int getPuntosDisponibles() {
/* 186 */     return this.puntosDisponibles;
/*     */   }
/*     */   
/*     */   public int getValorPuntos() {
/* 190 */     return this.valorPuntos;
/*     */   }
/*     */   
/*     */   public int getPuntosDispRestantes() {
/* 194 */     return this.puntosDispRestantes;
/*     */   }
/*     */   
/*     */   public int getAddendumNuevo() {
/* 198 */     return this.addendumNuevo;
/*     */   }
/*     */   
/*     */   public int getPuntosminimos() {
/* 202 */     return this.puntosminimos;
/*     */   }
/*     */   
/*     */   public int getBonoProrrateo() {
/* 206 */     return this.bonoProrrateo;
/*     */   }
/*     */   
/*     */   public int getPuntosAcumulados() {
/* 210 */     return this.puntosAcumulados;
/*     */   }
/*     */   
/*     */   public int getPuntosAcumuladosRestantes() {
/* 214 */     return this.puntosAcumuladosRestantes;
/*     */   }
/*     */   
/*     */   public String getPtsDispRestantesCF() {
/* 218 */     return this.ptsDispRestantesCF;
/*     */   }
/*     */   
/*     */   public String getValorPuntosCF() {
/* 222 */     return this.valorPuntosCF;
/*     */   }
/*     */   
/*     */   public int getBonosAltoValor() {
/* 226 */     return this.bonosAltoValor;
/*     */   }
/*     */   
/*     */   public void setBonosAltoValor(int bonosAltoValor) {
/* 230 */     this.bonosAltoValor = bonosAltoValor;
/*     */   }
/*     */   
/*     */   public int getBonosRoext() {
/* 234 */     return this.bonosRoext;
/*     */   }
/*     */   
/*     */   public void setBonosRoext(int bonosRoext) {
/* 238 */     this.bonosRoext = bonosRoext;
/*     */   }
/*     */   
/*     */   public int getBonosGap() {
/* 242 */     return this.bonosGap;
/*     */   }
/*     */   
/*     */   public void setBonosGap(int bonosGap) {
/* 246 */     this.bonosGap = bonosGap;
/*     */   }
/*     */ }


/* Location:              /opt/trabajo/claro/Guatemala/IS/CO/ClaroClub.ear!/ClaroClubEJBClient.jar!/com/claro/transfer/service/ImpresionServiceTO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */