/*     */ package com.claro.transfer;
/*     */ 
/*     */ import com.claro.transfer.service.ReservacionServiceTO;
/*     */ import com.claro.util.Utils;

/*     */ import java.math.BigDecimal;
/*     */ import java.text.ParseException;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Date;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ReservacionTO
/*     */   extends RedencionTO
/*     */ {
/*     */   private static final long serialVersionUID = 8621114546241946379L;
/*     */   private String motivo;
/*     */   private Date fechaExpiracion;
/*     */   
/*     */   public ReservacionTO() {}
/*     */   
/*     */   public ReservacionTO(ReservacionServiceTO reservacionServiceTO)
/*     */     throws ParseException
/*     */   {
/*  26 */     TelefonoSimpleTO telefonoSimpleTO = new TelefonoSimpleTO();
/*  27 */     telefonoSimpleTO.setCuenta(reservacionServiceTO.getCuenta());
/*  28 */     telefonoSimpleTO.setSecuencia(reservacionServiceTO.getSecuencia());
/*  29 */     telefonoSimpleTO.setLinea(reservacionServiceTO.getTelefono());
/*  30 */     telefonoSimpleTO.setRegion(reservacionServiceTO.getRegion());
/*  31 */     setTelefonoSimpleTO(telefonoSimpleTO);
/*     */     
/*  33 */     MobileTO mobileTO = new MobileTO();
/*  34 */     mobileTO.setEstCobranza(reservacionServiceTO.getEstatusCobranza());
/*  35 */     mobileTO.setMotivo(reservacionServiceTO.getMotivo());
/*  36 */     mobileTO.setPlanM2K(reservacionServiceTO.getPlanM2K());
/*  37 */     setMobileTO(mobileTO);
/*     */     
/*  39 */     PuntoVentaTO puntoVentaTO = new PuntoVentaTO();
/*  40 */     puntoVentaTO.setPtoVenta(reservacionServiceTO.getPuntoVenta());
/*     */     
/*  42 */     UsuarioTO usuarioTO = new UsuarioTO();
/*  43 */     usuarioTO.setIdUsuario(reservacionServiceTO.getUsuario());
/*  44 */     usuarioTO.setRegion(reservacionServiceTO.getRegion());
/*  45 */     setFuerzaVenta(reservacionServiceTO.getFuerzaVenta());
/*  46 */     usuarioTO.setPuntoVentaTO(puntoVentaTO);
/*  47 */     setUsuarioTO(usuarioTO);
/*     */     
/*  49 */     PuntosRedimidosTO puntosRedimidosTO = new PuntosRedimidosTO();
/*  50 */     puntosRedimidosTO.setPtosDisponibles(reservacionServiceTO.getPuntosDisponibles());
/*  51 */     setPuntosRedimidosTO(puntosRedimidosTO);
/*     */     
/*  53 */     ProductosTO productosTO = new ProductosTO();
/*     */     
/*  55 */     productosTO.setIdProducto(reservacionServiceTO.getIdProducto());
/*  56 */     productosTO.setDifPesos(reservacionServiceTO.getValorPesos());
/*  57 */     productosTO.setValorPuntos(reservacionServiceTO.getValorPuntos());
/*  58 */     productosTO.setPtosARedimir(reservacionServiceTO.getPuntosaRedimir());
/*  59 */     productosTO.setDescripcion(reservacionServiceTO.getDescripcion());
/*  60 */     productosTO.setMarca(reservacionServiceTO.getMarca());
/*  61 */     productosTO.setModelo(reservacionServiceTO.getModelo());
/*  62 */     productosTO.setTipoPromocion(reservacionServiceTO.getTipoPromocion());
/*  63 */     productosTO.setPrecioIva(new BigDecimal(reservacionServiceTO.getPrecioIVA()));
/*  64 */     productosTO.setPrecio(new BigDecimal(reservacionServiceTO.getPrecio()));
/*  65 */     productosTO.setBonosAltoValor(reservacionServiceTO.getBonoAltoValor());
/*  66 */     productosTO.setBonosRoext(reservacionServiceTO.getBonoRoext());
/*  67 */     productosTO.setDescuento(new BigDecimal(reservacionServiceTO.getDescuento()));
/*     */     
/*  69 */     productosTO.setAplicaPromocionGap(reservacionServiceTO.getAplicaPromocionGap());
/*  70 */     productosTO.setAplicaEP(reservacionServiceTO.getAplicaEP());
/*  71 */     productosTO.setBonoDescuentoGap(reservacionServiceTO.getBonoDescuentoGap());
/*  72 */     productosTO.setProductoM2KGap(reservacionServiceTO.getProductoM2KGap());
/*  73 */     productosTO.setNombrePromocionGap(reservacionServiceTO.getNombrePromocionGap());
/*  74 */     productosTO.setBonosGap(reservacionServiceTO.getBonosGap());
/*  75 */     productosTO.setIdPromocionGapCA(reservacionServiceTO.getIdPromocionGapCA());
/*  76 */     productosTO.setIdPromocionGap(reservacionServiceTO.getIdPromocionGap());
/*  77 */     productosTO.setVerPromocionGap(reservacionServiceTO.getVerPromocionGap());
/*     */     
/*  79 */     setProductosTO(productosTO);
/*  80 */     setComentario(reservacionServiceTO.getComentario());
/*  81 */     setPlanNuevo(reservacionServiceTO.getPlanNuevo());
/*  82 */     setTipoRedencion(reservacionServiceTO.getTipoRedencion());
/*  83 */     setFechaAdendumAnterior(Utils.DATEFORMATyyyy_MM_dd.parse(reservacionServiceTO.getFechaAddendumM2K()));
/*  84 */     setPlazoAnterior(reservacionServiceTO.getAddendumM2K());
/*  85 */     setPlazoNuevo(reservacionServiceTO.getAddendumNuevo());
/*  86 */     setFormaRedencion(reservacionServiceTO.getFormaRedencion());
/*  87 */     setPtsSobrantes(reservacionServiceTO.getPuntosSobrantes());
/*  88 */     setEstatus(reservacionServiceTO.getEstatus());
/*     */   }
/*     */   
/*     */   public Date getFechaExpiracion()
/*     */   {
/*  93 */     return this.fechaExpiracion;
/*     */   }
/*     */   
/*  96 */   public void setFechaExpiracion(Date fechaExpiracion) { this.fechaExpiracion = fechaExpiracion; }
/*     */   
/*     */   public String getMotivo() {
/*  99 */     return this.motivo;
/*     */   }
/*     */   
/* 102 */   public void setMotivo(String motivo) { this.motivo = motivo; }
/*     */ }


/* Location:              /opt/trabajo/claro/Guatemala/IS/CO/ClaroClub.ear!/ClaroClubEJBClient.jar!/com/claro/transfer/ReservacionTO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */