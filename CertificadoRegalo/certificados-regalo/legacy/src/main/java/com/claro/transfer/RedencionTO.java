/*     */ package com.claro.transfer;
/*     */ 
/*     */ import com.claro.util.Utils;

/*     */ import java.io.Serializable;
/*     */ import java.sql.Timestamp;
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
/*     */ public class RedencionTO
/*     */   extends MensajeTO
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private PuntosRedimidosTO puntosRedimidosTO;
/*     */   private TelefonoSimpleTO telefonoSimpleTO;
/*     */   private ProductosTO productosTO;
/*     */   private LineasTO lineasTO;
/*     */   private UsuarioTO usuarioTO;
/*     */   private MobileTO mobileTO;
/*     */   private String folio;
/*     */   private String folioHex;
/*     */   private Date fechaOperacion;
/*     */   private Date fechaAdendumAnterior;
/*     */   private Date fechaAdendumNuevo;
/*     */   private Date fechaPlazoSeg;
/*     */   private String comentario;
/*     */   private String fechaAdendum;
/*     */   private String precioConFormato;
/*     */   private String tipoRedencion;
/*     */   private String estatus;
/*     */   private String idPlan;
/*     */   private String planNuevo;
/*     */   private String plazoNuevo;
/*     */   private String plazoAnterior;
/*     */   private String formaRedencion;
/*     */   private String fuerzaVenta;
/*     */   private String direccionIP;
/*     */   private Timestamp fechaFolio;
/*     */   private long fechaOperacionlong;
/*     */   private long fechaFoliolong;
/*     */   private int region;
/*     */   private int sobrantesBono;
/*     */   private int ptsSobrantes;
/*     */   private int puntosSobrantes1;
/*     */   private int addendumNuevo;
/*     */   private int idMovimiento;
/*     */   private String planAnterior;
/*     */   private int addendumAnterior;
/*     */   private int bonoProrrateo;
/*     */   private int ptsMinimos;
/*     */   private String planDescuento;
/*     */   private int tipoRedPromOnline;
/*     */   private int origenRedOnline;
/*     */   
/*     */   public long getFechaOperacionlong()
/*     */   {
/*  65 */     if (this.fechaOperacion != null) return this.fechaOperacion.getTime();
/*  66 */     return this.fechaOperacionlong;
/*     */   }
/*     */   
/*     */   public void setFechaOperacionlong(long fechaOperacionlong)
/*     */   {
/*  71 */     this.fechaOperacionlong = fechaOperacionlong;
/*     */   }
/*     */   
/*     */   public int getBonoProrrateo()
/*     */   {
/*  76 */     return this.bonoProrrateo;
/*     */   }
/*     */   
/*     */   public void setBonoProrrateo(int bonoProrrateo)
/*     */   {
/*  81 */     this.bonoProrrateo = bonoProrrateo;
/*     */   }
/*     */   
/*     */   public long getDiasTranscurridos()
/*     */   {
/*  86 */     return Utils.calcularDiasEntreFechas(this.fechaOperacion.getTime(), System.currentTimeMillis());
/*     */   }
/*     */   
/*     */   public int getIdMovimiento() {
/*  90 */     return this.idMovimiento;
/*     */   }
/*     */   
/*     */   public void setIdMovimiento(int idMovimiento)
/*     */   {
/*  95 */     this.idMovimiento = idMovimiento;
/*     */   }
/*     */   
/*     */   public int getAddendumNuevo()
/*     */   {
/* 100 */     return this.addendumNuevo;
/*     */   }
/*     */   
/*     */   public void setAddendumNuevo(int addendumNuevo)
/*     */   {
/* 105 */     this.addendumNuevo = addendumNuevo;
/*     */   }
/*     */   
/*     */   public int getPuntosSobrantes1()
/*     */   {
/* 110 */     return this.puntosSobrantes1;
/*     */   }
/*     */   
/*     */   public void setPuntosSobrantes1(int puntosSobrantes1)
/*     */   {
/* 115 */     this.puntosSobrantes1 = puntosSobrantes1;
/*     */   }
/*     */   
/*     */   public int getPtsSobrantes()
/*     */   {
/* 120 */     return this.ptsSobrantes;
/*     */   }
/*     */   
/*     */   public void setPtsSobrantes(int ptsSobrantes)
/*     */   {
/* 125 */     this.ptsSobrantes = ptsSobrantes;
/*     */   }
/*     */   
/*     */   public int getSobrantesBono()
/*     */   {
/* 130 */     return this.sobrantesBono;
/*     */   }
/*     */   
/*     */   public void setSobrantesBono(int sobrantesBono)
/*     */   {
/* 135 */     this.sobrantesBono = sobrantesBono;
/*     */   }
/*     */   
/*     */   public int getRegion() {
/* 139 */     return this.region;
/*     */   }
/*     */   
/*     */   public void setRegion(int region)
/*     */   {
/* 144 */     this.region = region;
/*     */   }
/*     */   
/*     */   public PuntosRedimidosTO getPuntosRedimidosTO() {
/* 148 */     return this.puntosRedimidosTO;
/*     */   }
/*     */   
/*     */   public void setPuntosRedimidosTO(PuntosRedimidosTO puntosRedimidosTO)
/*     */   {
/* 153 */     this.puntosRedimidosTO = puntosRedimidosTO;
/*     */   }
/*     */   
/*     */   public TelefonoSimpleTO getTelefonoSimpleTO()
/*     */   {
/* 158 */     return this.telefonoSimpleTO;
/*     */   }
/*     */   
/*     */   public void setTelefonoSimpleTO(TelefonoSimpleTO telefonoSimpleTO)
/*     */   {
/* 163 */     this.telefonoSimpleTO = telefonoSimpleTO;
/*     */   }
/*     */   
/*     */   public LineasTO getLineasTO()
/*     */   {
/* 168 */     return this.lineasTO;
/*     */   }
/*     */   
/*     */   public void setLineasTO(LineasTO lineasTO)
/*     */   {
/* 173 */     this.lineasTO = lineasTO;
/*     */   }
/*     */   
/*     */   public String getFolio()
/*     */   {
/* 178 */     return this.folio;
/*     */   }
/*     */   
/*     */   public void setFolio(String folio)
/*     */   {
/* 183 */     this.folio = folio;
/*     */   }
/*     */   
/*     */   public Date getFechaOperacion()
/*     */   {
/* 188 */     return this.fechaOperacion;
/*     */   }
/*     */   
/*     */   public void setFechaOperacion(Date fechaOperacion)
/*     */   {
/* 193 */     this.fechaOperacion = fechaOperacion;
/*     */   }
/*     */   
/*     */   public String getComentario() {
/* 197 */     return this.comentario;
/*     */   }
/*     */   
/*     */   public void setComentario(String comentario)
/*     */   {
/* 202 */     this.comentario = comentario;
/*     */   }
/*     */   
/*     */   public String getFechaAdendum()
/*     */   {
/* 207 */     return this.fechaAdendum;
/*     */   }
/*     */   
/*     */   public void setFechaAdendum(String fechaAdendum)
/*     */   {
/* 212 */     this.fechaAdendum = fechaAdendum;
/*     */   }
/*     */   
/*     */   public String getPrecioConFormato()
/*     */   {
/* 217 */     return this.precioConFormato;
/*     */   }
/*     */   
/*     */   public void setPrecioConFormato(String precioConFormato)
/*     */   {
/* 222 */     this.precioConFormato = precioConFormato;
/*     */   }
/*     */   
/*     */   public Timestamp getFechaFolio() {
/* 226 */     return this.fechaFolio;
/*     */   }
/*     */   
/*     */   public void setFechaFolio(Timestamp fechaFolio)
/*     */   {
/* 231 */     this.fechaFolio = fechaFolio;
/*     */   }
/*     */   
/*     */   public String getTipoRedencion()
/*     */   {
/* 236 */     return this.tipoRedencion;
/*     */   }
/*     */   
/*     */   public void setTipoRedencion(String tipoRedencion)
/*     */   {
/* 241 */     this.tipoRedencion = tipoRedencion;
/*     */   }
/*     */   
/*     */   public String getEstatus()
/*     */   {
/* 246 */     return this.estatus;
/*     */   }
/*     */   
/*     */   public UsuarioTO getUsuarioTO() {
/* 250 */     return this.usuarioTO;
/*     */   }
/*     */   
/*     */   public void setUsuarioTO(UsuarioTO usuarioTO)
/*     */   {
/* 255 */     this.usuarioTO = usuarioTO;
/*     */   }
/*     */   
/*     */   public void setEstatus(String estatus)
/*     */   {
/* 260 */     this.estatus = estatus;
/*     */   }
/*     */   
/*     */   public String getIdPlan() {
/* 264 */     return this.idPlan;
/*     */   }
/*     */   
/*     */   public void setIdPlan(String idPlan)
/*     */   {
/* 269 */     this.idPlan = idPlan;
/*     */   }
/*     */   
/*     */   public String getPlanNuevo()
/*     */   {
/* 274 */     return this.planNuevo;
/*     */   }
/*     */   
/*     */   public void setPlanNuevo(String planNuevo)
/*     */   {
/* 279 */     this.planNuevo = planNuevo;
/*     */   }
/*     */   
/*     */   public Date getFechaAdendumAnterior()
/*     */   {
/* 284 */     return this.fechaAdendumAnterior;
/*     */   }
/*     */   
/*     */   public void setFechaAdendumAnterior(Date fechaAdendumAnterior)
/*     */   {
/* 289 */     this.fechaAdendumAnterior = fechaAdendumAnterior;
/*     */   }
/*     */   
/*     */   public Date getFechaAdendumNuevo() {
/* 293 */     return this.fechaAdendumNuevo;
/*     */   }
/*     */   
/*     */   public void setFechaAdendumNuevo(Date fechaAdendumNuevo)
/*     */   {
/* 298 */     this.fechaAdendumNuevo = fechaAdendumNuevo;
/*     */   }
/*     */   
/*     */   public String getPlazoNuevo()
/*     */   {
/* 303 */     return this.plazoNuevo;
/*     */   }
/*     */   
/*     */   public void setPlazoNuevo(String plazoNuevo)
/*     */   {
/* 308 */     this.plazoNuevo = plazoNuevo;
/*     */   }
/*     */   
/*     */   public String getPlazoAnterior()
/*     */   {
/* 313 */     return this.plazoAnterior;
/*     */   }
/*     */   
/*     */   public void setPlazoAnterior(String plazoAnterior)
/*     */   {
/* 318 */     this.plazoAnterior = plazoAnterior;
/*     */   }
/*     */   
/*     */   public String getFormaRedencion()
/*     */   {
/* 323 */     return this.formaRedencion;
/*     */   }
/*     */   
/*     */   public void setFormaRedencion(String formaRedencion)
/*     */   {
/* 328 */     this.formaRedencion = formaRedencion;
/*     */   }
/*     */   
/*     */   public String getFuerzaVenta()
/*     */   {
/* 333 */     return this.fuerzaVenta;
/*     */   }
/*     */   
/*     */   public void setFuerzaVenta(String fuerzaVenta)
/*     */   {
/* 338 */     this.fuerzaVenta = fuerzaVenta;
/*     */   }
/*     */   
/*     */   public ProductosTO getProductosTO()
/*     */   {
/* 343 */     if (this.productosTO == null) this.productosTO = new ProductosTO();
/* 344 */     return this.productosTO;
/*     */   }
/*     */   
/*     */   public void setProductosTO(ProductosTO productosTO)
/*     */   {
/* 349 */     this.productosTO = productosTO;
/*     */   }
/*     */   
/*     */   public String toString()
/*     */   {
/* 354 */     return this.productosTO.getDescripcion() + "|" + this.productosTO.getMarca() + "|" + this.productosTO.getModelo();
/*     */   }
/*     */   
/*     */   public String getPlanAnterior()
/*     */   {
/* 359 */     return this.planAnterior;
/*     */   }
/*     */   
/*     */   public void setPlanAnterior(String planAnterior)
/*     */   {
/* 364 */     this.planAnterior = planAnterior;
/*     */   }
/*     */   
/*     */   public int getAddendumAnterior()
/*     */   {
/* 369 */     return this.addendumAnterior;
/*     */   }
/*     */   
/*     */   public void setAddendumAnterior(int addendumAnterior)
/*     */   {
/* 374 */     this.addendumAnterior = addendumAnterior;
/*     */   }
/*     */   
/*     */   public int getPtsMinimos()
/*     */   {
/* 379 */     return this.ptsMinimos;
/*     */   }
/*     */   
/*     */   public void setPtsMinimos(int ptsMinimos)
/*     */   {
/* 384 */     this.ptsMinimos = ptsMinimos;
/*     */   }
/*     */   
/*     */   public long getFechaFoliolong()
/*     */   {
/* 389 */     if (this.fechaFolio != null) return this.fechaFolio.getTime();
/* 390 */     return this.fechaFoliolong;
/*     */   }
/*     */   
/*     */   public Date getFechaPlazoSeg()
/*     */   {
/* 395 */     return this.fechaPlazoSeg;
/*     */   }
/*     */   
/*     */   public void setFechaPlazoSeg(Date fechaPlazoSeg)
/*     */   {
/* 400 */     this.fechaPlazoSeg = fechaPlazoSeg;
/*     */   }
/*     */   
/*     */   public MobileTO getMobileTO()
/*     */   {
/* 405 */     return this.mobileTO;
/*     */   }
/*     */   
/*     */   public void setMobileTO(MobileTO mobileTO)
/*     */   {
/* 410 */     this.mobileTO = mobileTO;
/*     */   }
/*     */   
/*     */   public String getFolioHex()
/*     */   {
/* 415 */     return this.folioHex;
/*     */   }
/*     */   
/*     */   public void setFolioHex(String folioHex)
/*     */   {
/* 420 */     this.folioHex = folioHex;
/*     */   }
/*     */   
/*     */   public String getDireccionIP()
/*     */   {
/* 425 */     return this.direccionIP;
/*     */   }
/*     */   
/*     */   public void setDireccionIP(String direccionIP)
/*     */   {
/* 430 */     this.direccionIP = direccionIP;
/*     */   }
/*     */   
/*     */   public String getPlanDescuento() {
/* 434 */     return this.planDescuento;
/*     */   }
/*     */   
/*     */   public void setPlanDescuento(String planDescuento) {
/* 438 */     this.planDescuento = planDescuento;
/*     */   }
/*     */   
/*     */   public int getTipoRedPromOnline() {
/* 442 */     return this.tipoRedPromOnline;
/*     */   }
/*     */   
/*     */   public void setTipoRedPromOnline(int tipoRedPromOnline) {
/* 446 */     this.tipoRedPromOnline = tipoRedPromOnline;
/*     */   }
/*     */   
/*     */   public int getOrigenRedOnline() {
/* 450 */     return this.origenRedOnline;
/*     */   }
/*     */   
/*     */   public void setOrigenRedOnline(int origenRedOnline) {
/* 454 */     this.origenRedOnline = origenRedOnline;
/*     */   }
/*     */ }


/* Location:              /opt/trabajo/claro/Guatemala/IS/CO/ClaroClub.ear!/ClaroClubEJBClient.jar!/com/claro/transfer/RedencionTO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */