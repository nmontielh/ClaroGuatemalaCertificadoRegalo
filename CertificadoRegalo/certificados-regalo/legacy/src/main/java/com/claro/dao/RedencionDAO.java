/*      */ package com.claro.dao;
/*      */ 
/*      */ import com.claro.catalogo.Catalogo;
/*      */ import com.claro.exception.CAException;
/*      */ import com.claro.redencion.sms.NotificaSMS;
/*      */ import com.claro.seguridad.SeguridadCaUtil;
/*      */ import com.claro.transfer.BeneficioTO;
/*      */ import com.claro.transfer.MensajeTO;
/*      */ import com.claro.transfer.MobileTO;
/*      */ import com.claro.transfer.ParametrosTO;
/*      */ import com.claro.transfer.ProductosTO;
/*      */ import com.claro.transfer.PuntoVentaTO;
/*      */ import com.claro.transfer.PuntosRedimidosTO;
/*      */ import com.claro.transfer.PuntosTO;
/*      */ import com.claro.transfer.RedencionTO;
/*      */ import com.claro.transfer.ReservacionTO;
/*      */ import com.claro.transfer.TelefonoSimpleTO;
/*      */ import com.claro.transfer.TelefonoTO;
/*      */ import com.claro.transfer.UsuarioTO;
/*      */ import com.claro.util.Constantes;
/*      */ import com.claro.util.Redencion;
/*      */ import com.claro.util.ServiceLocator;
/*      */ import com.claro.util.Utils;
/*      */ import com.telcel.crm.scrapy.beans.ErrorRow;
/*      */ import com.telcel.crm.scrapy.beans.beneficios.Cudi2TO;
/*      */ import com.telcel.crm.scrapy.beans.beneficios.CudisTO;
/*      */ import com.telcel.gscrm.dccrm.ws.ioh.AplicaBeneficioProxy;
/*      */ import java.math.BigDecimal;
/*      */ import java.sql.Connection;
/*      */ import java.sql.PreparedStatement;
/*      */ import java.sql.ResultSet;
/*      */ import java.sql.SQLException;
/*      */ import java.sql.Timestamp;
/*      */ import java.text.SimpleDateFormat;
/*      */ import java.util.Calendar;
/*      */ import java.util.Date;
/*      */ import org.apache.log4j.Logger;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class RedencionDAO
/*      */ {
/*   48 */   protected final Logger logger = Logger.getLogger("loggerCirculoAzul");
/*   49 */   protected final Logger error = Logger.getLogger("loggerCirculoAzulError");
/*      */   
/*      */   private String schema_database;
/*   52 */   private ConsultasGapDAO consultasGapDAO = null;
/*      */   
/*      */ 
/*      */   public RedencionDAO()
/*      */   {
/*      */     try
/*      */     {
/*   59 */       this.consultasGapDAO = new ConsultasGapDAO();
/*   60 */       this.schema_database = ServiceLocator.getInstance().getVariable(ServiceLocator.schema_database);
/*      */     } catch (Exception e) {
/*   62 */       this.error.error("RedencionDAO", e);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public RedencionTO aplicaredencion(ProductosTO productosTO, ParametrosTO parametrosTO, RedencionTO redencionTO, boolean isEcac, boolean isDistribuidores, String endPoint, String usuario, BeneficioTO beneficioTO, String endpointGap)
/*      */     throws CAException
/*      */   {
/*   70 */     Connection connection = null;
/*      */     
/*   72 */     MensajeTO mensajeTO = new MensajeTO();
/*   73 */     PuntosDAO puntosDAO = new PuntosDAO();
/*   74 */     ConsultasDAO consultasDAO = new ConsultasDAO();
/*      */     
/*   76 */     String rComent = "";
/*   77 */     String msgEP = "";
/*      */     
/*   79 */     int valorPuntos = 0;
/*      */     
/*      */     try
/*      */     {
/*   83 */       Catalogo properties = new Catalogo();
/*      */       
/*   85 */       properties.setTabla("propiedades");
/*   86 */       properties.cargaCatalogo();
/*      */       
/*      */ 
/*   89 */       String idTipoRedencion = "";
/*   90 */       String descTipoRedencion = "";
/*   91 */       String comentarioCompl = "";
/*      */       
/*   93 */       String descTipoRedencionSMS = "";
/*   94 */       String usuarioR04 = properties.getPropiedad("usuario.r04.redencion.mensajeSMS");
/*      */       
/*   96 */       if ((parametrosTO != null) && (parametrosTO.getTipoRed() != null))
/*   97 */         idTipoRedencion = parametrosTO.getTipoRed();
/*      */       Object localObject2;
/*   99 */       if (((isEcac) || (isDistribuidores)) && (productosTO == null))
/*      */       {
/*  101 */         ReservacionTO reservacionTO = consultasDAO.obtieneReservacion(parametrosTO.getFolio(), null, false);
/*      */         
/*  103 */         if (reservacionTO.getIdMensaje() != 0) {
/*  104 */           return reservacionTO;
/*      */         }
/*  106 */         productosTO = reservacionTO.getProductosTO();
/*  107 */         productosTO.setNumeroSerieP(parametrosTO.getNumeroSerieP());
/*  108 */         productosTO.setNumeroSerieT(parametrosTO.getNumeroSerieT());
/*  109 */         productosTO.setIccid(parametrosTO.getIccid());
/*  110 */         productosTO.setValorPuntosTmp(productosTO.getValorPuntos());
/*      */         
/*  112 */         parametrosTO.setCuenta(reservacionTO.getTelefonoSimpleTO().getCuenta());
/*  113 */         parametrosTO.setSecuencia(reservacionTO.getTelefonoSimpleTO().getSecuencia());
/*  114 */         parametrosTO.setTelefono(reservacionTO.getTelefonoSimpleTO().getLinea());
/*  115 */         parametrosTO.setPlanAnt(reservacionTO.getPlanAnterior());
/*  116 */         parametrosTO.setPlanNvo(reservacionTO.getPlanNuevo());
/*      */         
/*  118 */         redencionTO.setRegion(reservacionTO.getRegion());
/*      */         
/*  120 */         redencionTO.setAddendumNuevo(Integer.parseInt(reservacionTO.getPlazoNuevo() == null ? "0" : reservacionTO.getPlazoNuevo()));
/*  121 */         redencionTO.setAddendumAnterior(Integer.parseInt(reservacionTO.getPlazoAnterior() == null ? "0" : reservacionTO.getPlazoAnterior()));
/*  122 */         redencionTO.setFormaRedencion(reservacionTO.getFormaRedencion());
/*  123 */         redencionTO.setEstatus("A");
/*  124 */         redencionTO.setPtsSobrantes(reservacionTO.getPtsSobrantes());
/*  125 */         redencionTO.setFechaAdendumAnterior(reservacionTO.getFechaAdendumAnterior());
/*  126 */         redencionTO.setFechaAdendumNuevo(reservacionTO.getFechaAdendumNuevo());
/*  127 */         redencionTO.setComentario(reservacionTO.getComentario());
/*  128 */         redencionTO.setTipoRedencion(reservacionTO.getTipoRedencion());
/*  129 */         redencionTO.setFolio(parametrosTO.getFolio());
/*  130 */         redencionTO.setFuerzaVenta(reservacionTO.getFuerzaVenta());
/*      */         
/*  132 */         if (isEcac) {
/*  133 */           reservacionTO.getUsuarioTO().setIdUsuario(parametrosTO.getUsuariMovimiento());
/*      */         }
/*      */         
/*  136 */         redencionTO.setUsuarioTO(reservacionTO.getUsuarioTO());
/*  137 */         redencionTO.setDireccionIP(parametrosTO.getIpAddress());
/*      */         
/*  139 */         idTipoRedencion = reservacionTO.getTipoRedencion();
/*      */         
/*  141 */         if ((idTipoRedencion.equals("C")) || (idTipoRedencion.equals("S"))) {
/*  142 */           idTipoRedencion = "CON";
/*  143 */         } else if (idTipoRedencion.equals("G")) {
/*  144 */           idTipoRedencion = "T3G";
/*      */         } else {
/*  146 */           idTipoRedencion = "ACA";
/*      */         }
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  165 */       if (idTipoRedencion.equals("TAIR")) {
/*  166 */         descTipoRedencion = "TIEMPO AIRE";
/*  167 */         descTipoRedencionSMS = "PAQUETE DE MINUTOS";
/*  168 */       } else if (idTipoRedencion.equals("SIN")) {
/*  169 */         descTipoRedencion = "AMIGO KIT";
/*  170 */         descTipoRedencionSMS = "AMIGO KIT";
/*  171 */       } else if (idTipoRedencion.equals("ACA")) {
/*  172 */         descTipoRedencion = "AMIGO CHIP";
/*  173 */         descTipoRedencionSMS = "AMIGO CHIP";
/*  174 */       } else if (idTipoRedencion.equals("T3G")) {
/*  175 */         descTipoRedencion = "TARJETA 3G";
/*  176 */         descTipoRedencionSMS = "MODEM USB TELCEL";
/*  177 */       } else if (idTipoRedencion.equals("CAREG")) {
/*  178 */         descTipoRedencion = "CAREG";
/*  179 */         descTipoRedencionSMS = "CAREG";
/*  180 */       } else if (idTipoRedencion.equals("CON")) {
/*  181 */         descTipoRedencion = "CAMCA";
/*  182 */         descTipoRedencionSMS = "RENOVACION DE EQUIPO";
/*  183 */       } else if (idTipoRedencion.equals("RONLINE")) {
/*  184 */         descTipoRedencion = "VIA ONLINE";
/*  185 */         descTipoRedencionSMS = "PAQUETE SMS";
/*      */       }
/*      */       
/*  188 */       PuntosTO puntosTO = consultasDAO.obtienePuntos(parametrosTO.getCuenta(), parametrosTO.getSecuencia());
/*      */       
/*  190 */       if ((isEcac) || (isDistribuidores)) {
/*  191 */         puntosTO.setPtosDisponiblesTmp(puntosTO.getPtosDisponibles());
/*      */       } else {
/*  193 */         puntosTO.setPtosDisponiblesTmp(parametrosTO.getPuntosTotalesTmp());
/*      */       }
/*      */       
/*  196 */       puntosTO.setPtsRedimidos(puntosTO.getPtsRedimidos() + productosTO.getValorPuntos());
/*  197 */       productosTO.setValorPuntosTmp(productosTO.getValorPuntos());
/*  198 */       valorPuntos = productosTO.getValorPuntos();
/*  199 */       PuntosRedimidosTO puntosRedimidosTO = new PuntosRedimidosTO(puntosTO);
/*      */       
/*      */ 
/*  202 */       if (puntosRedimidosTO.getIdMensaje() == 1) {
/*  203 */         redencionTO.agregaMensaje(puntosRedimidosTO.getIdMensaje(), puntosRedimidosTO.getMensaje());
/*  204 */         return redencionTO;
/*      */       }
/*      */       
/*  207 */       if ((!isEcac) && (!isDistribuidores)) {
/*  208 */         if ((puntosRedimidosTO.getPtosStatus() != null) && (puntosRedimidosTO.getPtosStatus().equals("W"))) {
/*  209 */           redencionTO.agregaMensaje(1, "Lo sentimos los puntos estan reservados por otra operacion. Intentelo de nuevo");
/*  210 */           return redencionTO;
/*      */         }
/*  212 */         mensajeTO = puntosDAO.actualizaLinea(null, parametrosTO.getCuenta(), parametrosTO.getSecuencia(), "W");
/*      */       }
/*      */       
/*      */ 
/*  216 */       connection = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
/*  217 */       long fechaTransaccion = System.currentTimeMillis();
/*      */       
/*  219 */       connection.setAutoCommit(false);
/*      */       
/*      */ 
/*  222 */       if (productosTO.getValorPuntos() > puntosRedimidosTO.getPtosDisponibles()) {
/*  223 */         redencionTO.agregaMensaje(1, "Lo sentimos, pero el usuario no cuenta con los puntos suficientes para realizar la redencion de Puntos.");
/*      */         
/*  225 */         return redencionTO;
/*      */       }
/*  227 */       redencionTO.setPuntosRedimidosTO(puntosRedimidosTO);
/*  228 */       redencionTO.setProductosTO(productosTO);
/*      */       
/*      */ 
/*  231 */       Redencion.aplicaRedencion(redencionTO, 7);
/*  232 */       redencionTO.getPuntosRedimidosTO().setPtsExcedentes(redencionTO.getPuntosRedimidosTO().getPtsExcedentes() + redencionTO.getPtsSobrantes());
/*      */       
/*      */ 
/*  235 */       mensajeTO = puntosDAO.actualizaPuntos(puntosRedimidosTO, connection, redencionTO.getTipoRedencion(), parametrosTO.getCuenta(), parametrosTO.getSecuencia(), true);
/*      */       
/*      */ 
/*  238 */       if ((mensajeTO.getIdMensaje() == 1) || (mensajeTO.getIdMensaje() == -1)) {
/*  239 */         redencionTO.agregaMensaje(mensajeTO.getIdMensaje(), mensajeTO.getMensaje());
/*  240 */         connection.rollback();
/*  241 */         return redencionTO;
/*      */       }
/*      */       
/*  244 */       if ((isEcac) || (isDistribuidores)) {
/*  245 */         rComent = redencionTO.getFuerzaVenta() == null ? redencionTO.getFuerzaVenta() : redencionTO.getFuerzaVenta().trim();
/*  246 */         if (redencionTO.getUsuarioTO().getIdUsuario().equals(usuarioR04)) {
/*  247 */           rComent = "SR4";
/*      */         }
/*  249 */         if ((parametrosTO.getPlanAnt() != null) && (!parametrosTO.getPlanAnt().trim().equals("")))
/*  250 */           rComent = rComent + "- PLAN ANTERIOR:" + parametrosTO.getPlanAnt().trim();
/*  251 */         if ((parametrosTO.getPlanNvo() != null) && (!parametrosTO.getPlanNvo().trim().equals(""))) {
/*  252 */           rComent = rComent + " PLAN ACTUAL:" + parametrosTO.getPlanNvo().trim();
/*      */         }
/*  254 */         rComent = rComent + "  " + redencionTO.getComentario();
/*      */       }
/*      */       else {
/*  257 */         String sFolioRed = Utils.generaFolio(parametrosTO.getTelefono());
/*      */         
/*  259 */         if (sFolioRed.length() <= 0) {
/*  260 */           redencionTO.agregaMensaje(1, "El numero de folio no es valido.");
/*  261 */           return redencionTO;
/*      */         }
/*  263 */         redencionTO.setFolio(sFolioRed);
/*      */         
/*  265 */         if ("C".equals(redencionTO.getTipoRedencion())) {
/*  266 */           rComent = 
/*  267 */             " CIR-PLAN ANTERIOR: " + parametrosTO.getPlanAnt() + " PLAN ACTUAL: " + parametrosTO.getPlanNvo() + "  " + redencionTO.getComentario();
/*      */         } else {
/*  269 */           rComent = " CIR-" + redencionTO.getComentario();
/*      */         }
/*      */       }
/*  272 */       redencionTO.setComentario(rComent);
/*      */       
/*  274 */       mensajeTO = puntosDAO.insertaRedencion(connection, redencionTO, parametrosTO, fechaTransaccion);
/*      */       
/*  276 */       if (mensajeTO.getIdMensaje() == 0) {
/*  277 */         mensajeTO = puntosDAO.insertaConstancia(redencionTO, parametrosTO, connection, fechaTransaccion, "A");
/*      */       }
/*  279 */       if ((mensajeTO.getIdMensaje() == 0) && (
/*  280 */         (!redencionTO.getProductosTO().getDescuentoInbursaRestante().equals(new BigDecimal(0))) || 
/*  281 */         (!redencionTO.getProductosTO().getDescuentoMarcaRestante().equals(new BigDecimal(0))) || 
/*  282 */         (!redencionTO.getProductosTO().getDescuentoMarca().equals(new BigDecimal(0))) || 
/*  283 */         (!redencionTO.getProductosTO().getDescuentoInbursa().equals(new BigDecimal(0))))) {
/*  284 */         mensajeTO = puntosDAO.insertaBonoInbursa(redencionTO, parametrosTO, connection);
/*      */       }
/*  286 */       if (mensajeTO.getIdMensaje() == 0)
/*      */       {
/*  288 */         mensajeTO = puntosDAO.insertaDetalle(connection, fechaTransaccion, "Redencion Terminada " + redencionTO.getTipoRedencion() + ", atendio: " + redencionTO.getUsuarioTO().getNumEmpleado() + " IP: " + redencionTO.getDireccionIP(), 
/*  289 */           5, redencionTO.getProductosTO().getValorPuntosTmp() * -1, 
/*  290 */           null, parametrosTO.getCuenta(), parametrosTO.getSecuencia(), parametrosTO.getTelefono(), 
/*  291 */           redencionTO.getUsuarioTO().getIdUsuario());
/*      */       }
/*      */       
/*      */ 
/*  295 */       if (!idTipoRedencion.equals("RONLINE"))
/*      */       {
/*  297 */         if (!productosTO.getMarca().trim().equals(""))
/*  298 */           comentarioCompl = "MARCA: " + productosTO.getMarca().trim();
/*  299 */         if (!productosTO.getModelo().trim().equals("")) {
/*  300 */           comentarioCompl = comentarioCompl + " MODELO: " + productosTO.getModelo().trim();
/*      */         }
/*      */       }
/*  303 */       if (mensajeTO.getIdMensaje() == 0)
/*      */       {
/*  305 */         if ((isEcac) || (isDistribuidores)) {
/*  306 */           String fzaVenta = redencionTO.getFuerzaVenta();
/*  307 */           usuarioR04 = properties.getPropiedad("usuario.r04.redencion.mensajeSMS");
/*  308 */           if (redencionTO.getUsuarioTO().getIdUsuario().equals(usuarioR04)) {
/*  309 */             fzaVenta = "R04";
/*      */           }
/*  311 */           rComent = 
/*  312 */             fzaVenta + " -REDENCION " + descTipoRedencion + "-" + valorPuntos + " PTOS. A PET. DE " + redencionTO.getUsuarioTO().getIdUsuario() + " " + comentarioCompl + " " + rComent;
/*      */         } else {
/*  314 */           rComent = "CIR-REDENCION " + descTipoRedencion + "-" + redencionTO.getProductosTO().getValorPuntosTmp() + " PTOS. A PET. DE " + redencionTO.getUsuarioTO().getIdUsuario() + " " + comentarioCompl + " " + rComent;
/*      */         }
/*  316 */         rComent = "PTO.VTA:" + redencionTO.getUsuarioTO().getPuntoVentaTO().getPtoVenta() + "-" + rComent;
/*      */         
/*  318 */         if (rComent.length() > 250) {
/*  319 */           rComent = rComent.substring(0, 250);
/*      */         }
/*      */         
/*  322 */         mensajeTO = puntosDAO.insertaComentarioTMP(connection, redencionTO.getRegion(), 
/*  323 */           parametrosTO.getCuenta(), redencionTO.getUsuarioTO().getIdUsuario(), 
/*  324 */           fechaTransaccion, rComent);
/*      */       }
/*      */       
/*  327 */       if ((mensajeTO.getIdMensaje() == 0) && (redencionTO.getPtsSobrantes() > 0)) {
/*  328 */         redencionTO.setIdMovimiento(52);
/*      */         
/*  330 */         rComent = "Asigna Pts Sobrantes Bono Equipo";
/*  331 */         mensajeTO = puntosDAO.insertaDetalle(connection, fechaTransaccion, rComent, 
/*  332 */           52, redencionTO.getPtsSobrantes(), 
/*  333 */           null, parametrosTO.getCuenta(), parametrosTO.getSecuencia(), parametrosTO.getTelefono(), 
/*  334 */           redencionTO.getUsuarioTO().getIdUsuario());
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*  339 */       if ((!isEcac) && (!isDistribuidores) && 
/*  340 */         (mensajeTO.getIdMensaje() == 0))
/*      */       {
/*  342 */         if ((beneficioTO != null) && 
/*  343 */           (beneficioTO.getIdBeneficio() != null) && (!beneficioTO.getIdBeneficio().equals("")) && (
/*  344 */           (parametrosTO.getTipoRed().equals("CON")) || (parametrosTO.getTipoRed().equals("CAREG"))))
/*      */         {
/*  346 */           TelefonoTO telefonoTO = new TelefonoTO();
/*  347 */           telefonoTO.setCuenta(parametrosTO.getCuenta());
/*  348 */           telefonoTO.setSecuencia(String.valueOf(parametrosTO.getSecuencia()));
/*  349 */           telefonoTO.setTelefono(parametrosTO.getTelefono());
/*  350 */           telefonoTO.setRegion(parametrosTO.getRegion());
/*      */           
/*  352 */           beneficioTO.setFolio(redencionTO.getFolio());
/*      */           
/*  354 */           BeneficioDAO beneficioDAO = new BeneficioDAO();
/*  355 */           mensajeTO = beneficioDAO.guardaBeneficioSeleccionado(connection, telefonoTO, beneficioTO, redencionTO.getUsuarioTO().getIdUsuario());
/*      */         }
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*  361 */       if ((isEcac) || (isDistribuidores))
/*      */       {
/*  363 */         if (mensajeTO.getIdMensaje() == 0) {
/*  364 */           mensajeTO = puntosDAO.actualizaReservacion(connection, fechaTransaccion, redencionTO.getUsuarioTO().getIdUsuario(), "R", null, parametrosTO.getFolio());
/*      */         } else {
/*  366 */           connection.rollback();
/*  367 */           if (connection != null) try { connection.close();connection = null; } catch (Exception localException7) {}
/*  368 */           return redencionTO;
/*      */         }
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  434 */       if ((mensajeTO.getIdMensaje() == 0) && (
/*  435 */         (isEcac) || (isDistribuidores))) {
/*  436 */         mensajeTO = puntosDAO.actualizaLinea(null, parametrosTO.getCuenta(), parametrosTO.getSecuencia(), "0");
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*  441 */       if (mensajeTO.getIdMensaje() == 0) {
/*  442 */         if ((parametrosTO.getTipoRed() != null) && (!parametrosTO.getTipoRed().trim().equals("")) && ((parametrosTO.getTipoRed().equals("TAIR")) || (parametrosTO.getTipoRed().equals("RONLINE"))) && (!isEcac))
/*      */         {
/*  444 */           boolean bExitoso = false;
/*  445 */           String mensaje = "";
/*      */           
/*      */ 
/*  448 */           boolean aplicaCudi2 = new Boolean(properties.getPropiedad("APLICA.WS.CUDI2")).booleanValue();
/*  449 */           if (aplicaCudi2) {
/*  450 */             AplicaBeneficioProxy proxy = new AplicaBeneficioProxy();
/*  451 */             Cudi2TO cudisResponse = null;
/*      */             
/*  453 */             proxy.setEndpoint(endPoint);
/*      */             
/*  455 */             cudisResponse = proxy.actualizaPlanDescuentoRenta(parametrosTO.getCuenta(), parametrosTO.getTelefono(), redencionTO.getPlanDescuento(), "A", "", "N", parametrosTO.getRegion());
/*  456 */             if (cudisResponse.getErrores() != null) {
/*  457 */               ErrorRow[] errorRows = cudisResponse.getErrores();
/*      */               
/*  459 */               for (int i = 0; i < errorRows.length; i++) {
/*  460 */                 if ((errorRows[i].getCodigo() != null) && (
/*  461 */                   (errorRows[i].getCodigo().toString().trim().indexOf("AS201") != -1) || 
/*  462 */                   (errorRows[i].getCodigo().toString().trim().indexOf("PG010") != -1))) {
/*  463 */                   bExitoso = true;
/*      */                 } else {
/*  465 */                   mensaje = mensaje + errorRows[i].getCodigo() + "|" + errorRows[i].getMensaje() + "-";
/*      */                 }
/*      */               }
/*      */             }
/*      */           }
/*      */           else {
/*  471 */             AplicaBeneficioProxy proxy = new AplicaBeneficioProxy();
/*  472 */             CudisTO cudisResponse = null;
/*      */             
/*  474 */             proxy.setEndpoint(endPoint);
/*      */             
/*  476 */             cudisResponse = proxy.actualizaPlanDescuento(parametrosTO.getCuenta(), parametrosTO.getTelefono(), redencionTO.getPlanDescuento(), "A", "", "N", parametrosTO.getRegion());
/*  477 */             if (cudisResponse.getErrores() != null) {
/*  478 */               ErrorRow[] errorRows = cudisResponse.getErrores();
/*      */               
/*  480 */               for (int i = 0; i < errorRows.length; i++) {
/*  481 */                 if ((errorRows[i].getCodigo() != null) && (
/*  482 */                   (errorRows[i].getCodigo().toString().trim().indexOf("AS201") != -1) || 
/*  483 */                   (errorRows[i].getCodigo().toString().trim().indexOf("PG010") != -1))) {
/*  484 */                   bExitoso = true;
/*      */                 } else {
/*  486 */                   mensaje = mensaje + errorRows[i].getCodigo() + "|" + errorRows[i].getMensaje() + "-";
/*      */                 }
/*      */               }
/*      */             }
/*      */           }
/*      */           
/*      */ 
/*  493 */           if (!bExitoso) {
/*  494 */             redencionTO.agregaMensaje(-1, " ERROR:   " + mensaje + "]");
/*  495 */             if (connection != null) {
/*  496 */               connection.rollback();
/*  497 */               try { connection.close();connection = null;
/*      */               } catch (Exception localException9) {} }
/*  499 */             return redencionTO;
/*      */           }
/*      */         }
/*      */       } else {
/*  503 */         connection.rollback();
/*  504 */         if (connection != null) try { connection.close();connection = null; } catch (Exception localException11) {}
/*  505 */         return redencionTO;
/*      */       }
/*      */       
/*  508 */       if (mensajeTO.getIdMensaje() == 0) {
/*  509 */         String aplicaMensajeSMS = properties.getPropiedad("ws.aplica.redencion.mensajeSMS");
/*      */         
/*  511 */         if (new Boolean(aplicaMensajeSMS).booleanValue())
/*      */         {
/*  513 */           String linea = parametrosTO.getTelefono();
/*      */           
/*  515 */           SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy");
/*  516 */           String fechaHoy = sdf.format(new Date());
/*      */           
/*  518 */           String mensajeSMS = fechaHoy + " Redencion de " + redencionTO.getProductosTO().getValorPuntosTmp() + " Puntos por " + descTipoRedencionSMS + ". Para cualquier aclaracion marca *111 desde tu Telcel.";
/*  519 */           NotificaSMS notificaSMS = new NotificaSMS();
/*  520 */           notificaSMS.enviaSMSRedencionCA(linea, mensajeSMS, properties);
/*      */         }
/*      */         
/*  523 */         connection.commit();
/*      */       } else {
/*  525 */         connection.rollback();
/*  526 */         if (connection != null) try { connection.close();connection = null; } catch (Exception localException13) {}
/*  527 */         return redencionTO;
/*      */       }
/*      */       
/*  530 */       redencionTO.agregaMensaje(mensajeTO.getIdMensaje(), mensajeTO.getMensaje());
/*      */     }
/*      */     catch (SQLException e) {
/*  533 */       if (connection != null) try { connection.rollback(); } catch (Exception localException15) {}
/*  534 */       throw new CAException(-1, "RedencionDAO.aplicaredencion.SQLException[" + e.toString() + "]", e);
/*      */     } catch (Exception e) {
/*      */       try {
/*  537 */         if (connection != null) connection.rollback(); } catch (Exception localException16) {}
/*  538 */       throw new CAException(-1, "RedencionDAO.aplicaredencion.Error[" + e.toString() + "]", e);
/*      */     } finally {
/*  540 */       if (connection != null)
/*  541 */         try { connection.close();connection = null; } catch (Exception localException17) {}
/*  542 */       if ((!isEcac) && (!isDistribuidores)) {
/*  543 */         puntosDAO.actualizaLinea(null, parametrosTO.getCuenta(), parametrosTO.getSecuencia(), "0");
/*      */       }
/*      */     }
/*  540 */     if (connection != null)
/*  541 */       try { connection.close();connection = null; } catch (Exception localException18) {}
/*  542 */     if ((!isEcac) && (!isDistribuidores)) {
/*  543 */       puntosDAO.actualizaLinea(null, parametrosTO.getCuenta(), parametrosTO.getSecuencia(), "0");
/*      */     }
/*      */     
/*  546 */     return redencionTO;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public ReservacionTO realizaApartado(ReservacionTO reservacionTO, TelefonoTO telefonoTO, boolean isDistribuidores, String fzaVentas)
/*      */     throws CAException
/*      */   {
/*  555 */     Connection connection = null;
/*  556 */     PuntosDAO puntosDAO = new PuntosDAO();
/*      */     try
/*      */     {
/*  559 */       connection = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
/*  560 */       connection.setAutoCommit(false);
/*      */       
/*      */       ReservacionTO localReservacionTO;
/*  563 */       if (isDistribuidores)
/*      */       {
/*      */ 
/*  566 */         String estatusPuntos = getEstatusPuntos(reservacionTO.getTelefonoSimpleTO().getCuenta(), connection, reservacionTO.getTelefonoSimpleTO().getSecuencia());
/*      */         
/*      */ 
/*  569 */         if (Utils.getValEstatusTel(telefonoTO.getMobileTO().getStatus(), telefonoTO.getMobileTO().getMotivo())) {
/*  570 */           throw new CAException(-1, "El Estatus del telefono es Invalido -- " + telefonoTO.getMobileTO().getStatus() + 
/*  571 */             " -- Motivo: " + telefonoTO.getMobileTO().getMotivo());
/*      */         }
/*      */         
/*      */ 
/*  575 */         if (estatusPuntos.equals("R"))
/*      */         {
/*  577 */           EstatusPuntosDist(connection, reservacionTO.getTelefonoSimpleTO().getCuenta());
/*      */         }
/*      */         
/*      */ 
/*  581 */         if (estatusPuntos.equals("C")) {
/*  582 */           throw new CAException(-1, "Los puntos de la linea se encuentran congelados.");
/*      */         }
/*      */         
/*  585 */         if (!Utils.getValEstatusCobranza(reservacionTO.getMobileTO().getEstCobranza(), reservacionTO.getRegion(), "Reden", reservacionTO.getMobileTO().getMotivo()))
/*      */         {
/*  587 */           throw new CAException(-1, "El estatus de cobranza de la linea: " + reservacionTO.getEstatus() + 
/*  588 */             ", no es valido para la redencion de promociones.");
/*      */         }
/*      */         
/*      */ 
/*  592 */         if (reservacionTO.getPuntosRedimidosTO().getPtosTotalesTemp() < reservacionTO.getProductosTO().getPtosARedimir()) {
/*  593 */           throw new CAException(-1, "No se cuentan con los suficientes puntos para realizar la redencion.");
/*      */         }
/*      */         
/*  596 */         String claseCredito = telefonoTO.getMobileTO().getClaseCredit();
/*      */         
/*  598 */         if ((claseCredito.trim().equals("IM")) || (claseCredito.trim().equals("FO"))) {
/*  599 */           DistribuidoresDAO distribuidoresDAO = new DistribuidoresDAO();
/*  600 */           boolean fzaVentasAutorizada = distribuidoresDAO.validaFzaVentasImssFonacot(fzaVentas, claseCredito, connection);
/*      */           
/*  602 */           if (!fzaVentasAutorizada) {
/*  603 */             throw new CAException(-1, "La cuenta tiene una clase de credito " + 
/*  604 */               telefonoTO.getMobileTO().getClaseCredit().toUpperCase().trim() + " , por lo que solo podra realizar " + 
/*  605 */               " la redencion de puntos el Distribuidor Autorizado.");
/*      */           }
/*      */         }
/*      */       }
/*      */       else
/*      */       {
/*  611 */         if (!Utils.getValEstatusCobranza(reservacionTO.getMobileTO().getEstCobranza(), reservacionTO.getRegion(), "Reden", reservacionTO.getMobileTO().getMotivo())) {
/*  612 */           reservacionTO.agregaMensaje(1, "El estatus de cobranza de la linea: " + reservacionTO.getEstatus() + 
/*  613 */             ", no es valido para la redencion de promociones.");
/*  614 */           return reservacionTO;
/*      */         }
/*      */         
/*      */ 
/*  618 */         if (reservacionTO.getPuntosRedimidosTO().getPtosTotalesTemp() < reservacionTO.getProductosTO().getPtosARedimir()) {
/*  619 */           reservacionTO.agregaMensaje(1, "No se cuentan con los suficientes puntos para realizar la redencion.");
/*  620 */           return reservacionTO;
/*      */         }
/*      */       }
/*      */       
/*      */ 
/*  625 */       reservacionTO.setTipoRedencion(Redencion.validaTipoRedencion(reservacionTO.getTipoRedencion()));
/*      */       
/*  627 */       String sFolioReservacion = Utils.generaFolio(reservacionTO.getTelefonoSimpleTO().getLinea());
/*      */       
/*  629 */       if (sFolioReservacion.length() <= 0) {
/*  630 */         if (isDistribuidores) {
/*  631 */           throw new CAException(-1, "El numero de folio no es valido.");
/*      */         }
/*  633 */         reservacionTO.agregaMensaje(1, "El numero de folio no es valido.");
/*  634 */         return reservacionTO;
/*      */       }
/*      */       
/*      */ 
/*  638 */       long fechaTransaccion = System.currentTimeMillis();
/*      */       
/*  640 */       reservacionTO.setFolio(String.valueOf(sFolioReservacion));
/*  641 */       reservacionTO.setFolioHex(Long.toHexString(new Long(sFolioReservacion).longValue()));
/*      */       
/*  643 */       reservacionTO.setFechaExpiracion(Utils.fechaExpiracion(isDistribuidores));
/*  644 */       reservacionTO.setFechaAdendumNuevo(new Date(fechaTransaccion));
/*  645 */       reservacionTO.setFechaOperacion(new Date(fechaTransaccion));
/*      */       
/*      */ 
/*  648 */       MensajeTO mensajeTO = puntosDAO.actualizaLinea(connection, reservacionTO.getTelefonoSimpleTO().getCuenta(), reservacionTO.getTelefonoSimpleTO().getSecuencia(), "R");
/*      */       
/*      */ 
/*  651 */       if (mensajeTO.getIdMensaje() == 0) {
/*  652 */         mensajeTO = puntosDAO.insertReservacion(connection, reservacionTO, fechaTransaccion, isDistribuidores);
/*      */       }
/*      */       
/*  655 */       if (mensajeTO.getIdMensaje() == 0) {
/*  656 */         connection.commit();
/*      */       } else {
/*  658 */         connection.rollback();
/*      */       }
/*      */       
/*      */ 
/*  662 */       reservacionTO.agregaMensaje(mensajeTO.getIdMensaje(), mensajeTO.getMensaje());
/*      */ 
/*      */     }
/*      */     catch (SQLException e)
/*      */     {
/*  667 */       if (connection != null) try { connection.rollback(); } catch (Exception localException4) {}
/*  668 */       throw new CAException(-1, "RedencionDAO.realizaApartado.SQLException[" + e.toString() + "]", e);
/*      */     }
/*      */     catch (Exception e) {
/*  671 */       if (connection != null) try { connection.rollback(); } catch (Exception localException5) {}
/*  672 */       throw new CAException(-1, "RedencionDAO.realizaApartado.Error[" + e.toString() + "]", e);
/*      */     } finally {
/*  674 */       if (connection != null) try { connection.close();connection = null; } catch (Exception localException6) {} } if (connection != null) try { connection.close();connection = null;
/*      */       } catch (Exception localException7) {}
/*  676 */     return reservacionTO;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public MensajeTO cancelaApartado(ParametrosTO parametrosTO, UsuarioTO usuarioTO, boolean isDistribuidores)
/*      */     throws CAException
/*      */   {
/*  686 */     Connection connection = null;
/*  687 */     MensajeTO mensajeTO = new MensajeTO();
/*  688 */     String comentario = null;
/*  689 */     RedencionTO redencionTO = null;
/*      */     
/*  691 */     int totAjustes = 0;
/*      */     try
/*      */     {
/*  694 */       long fechaTransaccion = System.currentTimeMillis();
/*  695 */       boolean conRedencion = false;
/*  696 */       MensajeTO localMensajeTO1; if ((parametrosTO.getFolio() == null) || (parametrosTO.getFolio().trim().equals(""))) {
/*  697 */         mensajeTO.agregaMensaje(-1, "El numero de folio no es valido.");
/*  698 */         return mensajeTO;
/*      */       }
/*  700 */       if (isDistribuidores)
/*      */       {
/*  702 */         if ((!parametrosTO.getEstatus().equals("C")) && (!parametrosTO.getEstatus().equals("P")) && 
/*  703 */           (!parametrosTO.getEstatus().equals("A"))) {
/*  704 */           mensajeTO.agregaMensaje(-1, "El tipo de estatus " + parametrosTO.getEstatus() + " no es valido para la reservacion.");
/*  705 */           return mensajeTO;
/*      */         }
/*      */         
/*      */       }
/*  709 */       else if ((!parametrosTO.getEstatus().equals("C")) && (!parametrosTO.getEstatus().equals("P"))) {
/*  710 */         mensajeTO.agregaMensaje(-1, "El tipo de estatus " + parametrosTO.getEstatus() + " no es valido para la reservacion.");
/*  711 */         return mensajeTO;
/*      */       }
/*      */       
/*      */ 
/*  715 */       ConsultasDAO consultasDAO = new ConsultasDAO();
/*      */       
/*  717 */       ReservacionTO reservacionTO = consultasDAO.obtieneReservacion(parametrosTO.getFolio(), parametrosTO.getEstatus(), true);
/*      */       
/*  719 */       if ((isDistribuidores) && (!parametrosTO.getFzaVentas().equals(reservacionTO.getFuerzaVenta())))
/*      */       {
/*  721 */         mensajeTO.agregaMensaje(-1, "La fuerza de ventas que va a cancelar la reservacion es incorrecta.");
/*  722 */         return mensajeTO;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*  727 */       if ((reservacionTO.getIdMensaje() == 1) || (mensajeTO.getIdMensaje() == 1)) {
/*  728 */         mensajeTO.agregaMensaje(reservacionTO.getIdMensaje(), reservacionTO.getMensaje());
/*  729 */         return mensajeTO;
/*      */       }
/*  731 */       if ((!isDistribuidores) && 
/*  732 */         (!SeguridadCaUtil.getInstance().validaPerfilProcesoEcac(usuarioTO.getPerfilTO(), "154"))) {
/*  733 */         mensajeTO.agregaMensaje(1, "Su perfil no tiene los privilegios para realizar esta operacion.");
/*  734 */         return mensajeTO;
/*      */       }
/*      */       
/*      */ 
/*  738 */       if ((parametrosTO.getEstatus().equals("C")) && (!reservacionTO.getEstatus().equals(parametrosTO.getEstatus())))
/*      */       {
/*  740 */         redencionTO = consultasDAO.ultimaRedencion(reservacionTO.getTelefonoSimpleTO().getCuenta(), reservacionTO.getTelefonoSimpleTO().getSecuencia());
/*      */         
/*  742 */         if ((redencionTO.getFolio() != null) && (redencionTO.getFolio().equals(parametrosTO.getFolio()))) {
/*  743 */           if (!reservacionTO.getFolio().equals(redencionTO.getFolio())) {
/*  744 */             mensajeTO.agregaMensaje(1, "El folio ingresado no corresponde a la redencion mas actual con numero de folio " + 
/*  745 */               reservacionTO.getFolio() + 
/*  746 */               " y fecha de operacion " + Constantes.DATEFORMATdd_MM_YYYY.format(reservacionTO.getFechaOperacion()) + ".");
/*  747 */             return mensajeTO;
/*      */           }
/*      */           
/*      */ 
/*      */ 
/*  752 */           if (redencionTO.getFechaFolio() == null) {
/*  753 */             mensajeTO.agregaMensaje(1, "Debe eligir un registro para la Cancelacion.");
/*  754 */             return mensajeTO;
/*      */           }
/*      */           
/*      */ 
/*  758 */           if (Utils.calcularDiasEntreFechas(redencionTO.getFechaFolio().getTime(), fechaTransaccion) > 30L) {
/*  759 */             mensajeTO.agregaMensaje(1, "La Fecha de la Cancelacion no debe ser mayor 30 dias.");
/*  760 */             return mensajeTO;
/*      */           }
/*  762 */           conRedencion = true;
/*      */         }
/*  764 */       } else if ((parametrosTO.getEstatus().equals("C")) && (reservacionTO.getEstatus().equals(parametrosTO.getEstatus()))) {
/*  765 */         mensajeTO.agregaMensaje(0, "Este numero de folio ya fue cancelado previamente.");
/*  766 */         return mensajeTO;
/*      */       }
/*  768 */       PuntosDAO puntosDAO = new PuntosDAO();
/*      */       
/*  770 */       PuntosTO puntosTO = null;
/*  771 */       PuntosRedimidosTO puntosRedimidosTO = null;
/*      */       
/*      */ 
/*      */ 
/*  775 */       if (conRedencion)
/*      */       {
/*  777 */         if ((!isDistribuidores) && 
/*  778 */           (usuarioTO.getPerfilTO() != null) && 
/*  779 */           (!SeguridadCaUtil.getInstance().validaPerfilProcesoEcac(usuarioTO.getPerfilTO(), "155"))) {
/*  780 */           mensajeTO.agregaMensaje(1, "Su perfil no tiene los privilegios para la cancelacion de redenciones.");
/*  781 */           return mensajeTO;
/*      */         }
/*      */         
/*      */ 
/*      */ 
/*  786 */         puntosTO = consultasDAO.obtienePuntos(reservacionTO.getTelefonoSimpleTO().getCuenta(), reservacionTO.getTelefonoSimpleTO().getSecuencia());
/*      */         
/*  788 */         if (puntosTO.getIdMensaje() != 0) {
/*  789 */           return puntosTO.obtieneMensajeTO();
/*      */         }
/*  791 */         puntosRedimidosTO = consultasDAO.obtienePuntosRedimidos(reservacionTO.getTelefonoSimpleTO().getCuenta(), redencionTO.getFechaFolio());
/*      */         
/*  793 */         if (puntosRedimidosTO.getIdMensaje() != 0) {
/*  794 */           return puntosRedimidosTO.obtieneMensajeTO();
/*      */         }
/*  796 */         if (puntosTO.getPtsExcedentes() < puntosRedimidosTO.getPtsSobrantes1()) {
/*  797 */           mensajeTO.agregaMensaje(1, "No es posible realizar la cancelacion de puntos, ya que la linea no cuenta con los puntos sobrantes del bono.");
/*      */           
/*  799 */           return mensajeTO;
/*      */         }
/*      */       }
/*      */       
/*  803 */       connection = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
/*  804 */       connection.setAutoCommit(false);
/*      */       
/*      */ 
/*  807 */       if ((conRedencion) && (mensajeTO.getIdMensaje() == 0))
/*      */       {
/*  809 */         if (mensajeTO.getIdMensaje() == 0) {
/*  810 */           if (isDistribuidores) {
/*  811 */             comentario = "SISACT CANCELA:" + parametrosTO.getUsuariMovimiento();
/*      */           } else
/*  813 */             comentario = "E-CAC CANCELA:" + parametrosTO.getUsuariMovimiento() + " COMENT: " + parametrosTO.getComentario();
/*  814 */           mensajeTO = puntosDAO.insertaDetalle(connection, fechaTransaccion, comentario, 11, puntosRedimidosTO.getPtsRedimidos(), null, reservacionTO.getTelefonoSimpleTO().getCuenta(), reservacionTO.getTelefonoSimpleTO().getSecuencia(), reservacionTO.getTelefonoSimpleTO().getLinea(), parametrosTO.getUsuariMovimiento());
/*      */         }
/*      */         
/*  817 */         if (mensajeTO.getIdMensaje() == 0) {
/*  818 */           PuntosRedimidosTO redimidosTO = Utils.regresaPuntos(puntosTO, puntosRedimidosTO);
/*  819 */           mensajeTO = puntosDAO.actualizaPuntos(redimidosTO, connection, null, reservacionTO.getTelefonoSimpleTO().getCuenta(), reservacionTO.getTelefonoSimpleTO().getSecuencia(), true);
/*      */         }
/*      */         
/*  822 */         if (mensajeTO.getIdMensaje() == 0) {
/*  823 */           mensajeTO = puntosDAO.actualizaRedencion(connection, "I", redencionTO.getFechaFolio(), reservacionTO.getTelefonoSimpleTO().getCuenta(), new Timestamp(fechaTransaccion), parametrosTO.getUsuariMovimiento());
/*      */         }
/*      */         
/*  826 */         if (mensajeTO.getIdMensaje() == 0) {
/*  827 */           mensajeTO = puntosDAO.actualizaConstancia(connection, "I", null, null, null, redencionTO.getFechaFolio(), reservacionTO.getTelefonoSimpleTO().getCuenta(), reservacionTO.getTelefonoSimpleTO().getSecuencia());
/*      */         }
/*      */         
/*      */ 
/*  831 */         if ((mensajeTO.getIdMensaje() == 0) && ((puntosRedimidosTO.getPtsMinimos() > 0) || (puntosRedimidosTO.getBonoProrrateo() > 0)))
/*      */         {
/*  833 */           if (puntosRedimidosTO.getPtsMinimos() > 0) {
/*  834 */             totAjustes = puntosRedimidosTO.getPtsMinimos() * -1;
/*  835 */             comentario = "CANCELA PTOS. MINIMOS x CANC. DE REDENCION.";
/*  836 */           } else if (puntosRedimidosTO.getBonoProrrateo() > 0) {
/*  837 */             totAjustes = puntosRedimidosTO.getBonoProrrateo() * -1;
/*  838 */             comentario = "CANCELA PTOS. PRORRATEO x CANC. DE REDENCION";
/*      */           }
/*  840 */           mensajeTO = puntosDAO.insertaDetalle(connection, fechaTransaccion, comentario, 18, totAjustes, null, reservacionTO.getTelefonoSimpleTO().getCuenta(), reservacionTO.getTelefonoSimpleTO().getSecuencia(), reservacionTO.getTelefonoSimpleTO().getLinea(), parametrosTO.getUsuariMovimiento());
/*      */         }
/*      */         
/*  843 */         if ((mensajeTO.getIdMensaje() == 0) && (puntosRedimidosTO.getPtsSobrantes1() > 0)) {
/*  844 */           comentario = "Elimina Pts Sobrantes Bono Equipo.";
/*  845 */           totAjustes = puntosRedimidosTO.getPtsSobrantes1() * -1;
/*  846 */           mensajeTO = puntosDAO.insertaDetalle(connection, fechaTransaccion, comentario, 53, totAjustes, null, reservacionTO.getTelefonoSimpleTO().getCuenta(), reservacionTO.getTelefonoSimpleTO().getSecuencia(), reservacionTO.getTelefonoSimpleTO().getLinea(), parametrosTO.getUsuariMovimiento());
/*      */         }
/*      */         
/*  849 */         if (mensajeTO.getIdMensaje() == 0) {
/*  850 */           comentario = "CIR -CANCELA -" + puntosRedimidosTO.getPtsRedimidos() + "PTOS. A PET. DE " + parametrosTO.getUsuariMovimiento() + " " + parametrosTO.getComentario();
/*  851 */           mensajeTO = puntosDAO.insertaComentarioTMP(connection, reservacionTO.getTelefonoSimpleTO().getRegion(), reservacionTO.getTelefonoSimpleTO().getCuenta(), parametrosTO.getUsuariMovimiento(), fechaTransaccion, comentario);
/*      */         }
/*      */       }
/*      */       
/*  855 */       if (mensajeTO.getIdMensaje() == 0)
/*      */       {
/*  857 */         mensajeTO = puntosDAO.actualizaReservacion(connection, fechaTransaccion, parametrosTO.getUsuariMovimiento(), parametrosTO.getEstatus(), parametrosTO.getComentario(), parametrosTO.getFolio());
/*      */       }
/*      */       
/*  860 */       if (mensajeTO.getIdMensaje() == 0)
/*      */       {
/*  862 */         if ((mensajeTO.getIdMensaje() == 0) && ("C".equals(parametrosTO.getEstatus())))
/*      */         {
/*  864 */           mensajeTO = puntosDAO.actualizaLinea(connection, reservacionTO.getTelefonoSimpleTO().getCuenta(), reservacionTO.getTelefonoSimpleTO().getSecuencia(), "0");
/*      */         }
/*      */         
/*  867 */         if (mensajeTO.getIdMensaje() == 0) {
/*  868 */           connection.commit();
/*      */         } else {
/*  870 */           connection.rollback();
/*      */         }
/*      */       } else {
/*  873 */         connection.rollback();
/*      */       }
/*      */       
/*      */ 
/*      */     }
/*      */     catch (SQLException e)
/*      */     {
/*      */ 
/*  881 */       if (connection != null) try { connection.rollback(); } catch (Exception localException15) {}
/*  882 */       throw new CAException(-1, "RedencionDAO.cancelaApartado.SQLException[" + e.toString() + "]", e);
/*      */     } catch (Exception e) {
/*  884 */       throw new CAException(-1, "RedencionDAO.cancelaApartado.Error[" + e.toString() + "]", e);
/*      */     } finally {
/*  886 */       if (connection != null) try { connection.close();connection = null; } catch (Exception localException16) {} } if (connection != null) try { connection.close();connection = null;
/*      */       } catch (Exception localException17) {}
/*  888 */     return mensajeTO;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public MensajeTO cancelaRedencion(ParametrosTO parametrosTO, String fechaOperacion, int diasValidacion, UsuarioTO usuarioTO)
/*      */     throws CAException
/*      */   {
/*  897 */     MensajeTO mensajeTO = new MensajeTO();
/*  898 */     Connection connection = null;
/*      */     try
/*      */     {
/*  901 */       Calendar fechaInicial = Calendar.getInstance();
/*  902 */       Calendar fechaFinal = Calendar.getInstance();
/*  903 */       fechaInicial.setTime(new Date(new Long(fechaOperacion).longValue()));
/*      */       MensajeTO localMensajeTO1;
/*  905 */       if (usuarioTO.getPerfilTO() != null)
/*      */       {
/*  907 */         if ((!SeguridadCaUtil.getInstance().validaPerfilProcesoCa(usuarioTO.getPerfilTO(), "148")) || (diasValidacion == 0)) {
/*  908 */           mensajeTO.agregaMensaje(1, "Su perfil no tiene los privilegios para realizar esta operacion.");
/*  909 */           return mensajeTO;
/*      */         }
/*      */       }
/*      */       
/*  913 */       if ((diasValidacion != 0) && (Utils.calcularDiasEntreFechas(fechaInicial.getTimeInMillis(), fechaFinal.getTimeInMillis()) > diasValidacion)) {
/*  914 */         mensajeTO.agregaMensaje(1, "La Fecha de la Cancelacion no debe ser mayor a " + diasValidacion + " dias.");
/*  915 */         return mensajeTO;
/*      */       }
/*      */       
/*  918 */       ConsultasDAO consultasDAO = new ConsultasDAO();
/*      */       
/*  920 */       PuntosTO puntosTO = consultasDAO.obtienePuntos(parametrosTO.getCuenta(), parametrosTO.getSecuencia());
/*  921 */       if (puntosTO.getIdMensaje() != 0) {
/*  922 */         return puntosTO.obtieneMensajeTO();
/*      */       }
/*  924 */       PuntosRedimidosTO redimidosTO = consultasDAO.obtienePuntosRedimidos(parametrosTO.getCuenta(), new Timestamp(new Long(fechaOperacion).longValue()));
/*      */       
/*  926 */       if (redimidosTO.getIdMensaje() != 0) {
/*  927 */         return redimidosTO.obtieneMensajeTO();
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  935 */       connection = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
/*  936 */       connection.setAutoCommit(false);
/*  937 */       String referencia = "CANCELA:" + parametrosTO.getUsuariMovimiento() + " COMENT:" + parametrosTO.getComentario();
/*  938 */       PuntosDAO puntosDAO = new PuntosDAO();
/*  939 */       long fechaTransaccion = System.currentTimeMillis();
/*      */       
/*  941 */       mensajeTO = puntosDAO.insertaDetalle(connection, fechaTransaccion, referencia, 11, redimidosTO.getPtsRedimidos(), null, parametrosTO.getCuenta(), parametrosTO.getSecuencia(), parametrosTO.getTelefono(), parametrosTO.getUsuariMovimiento());
/*      */       
/*  943 */       redimidosTO.setPtsRenta(redimidosTO.getPtsRenta() + redimidosTO.getPtsRentaRedimidos());
/*  944 */       redimidosTO.setPtsPorVencer(redimidosTO.getPtsPorVencer() + redimidosTO.getPtsPorVencerRedimidos());
/*  945 */       redimidosTO.setPtsPorVencer1(redimidosTO.getPtsPorVencer1() + redimidosTO.getPtsPorVencer1Redimidos());
/*  946 */       redimidosTO.setPtsPorVencer2(redimidosTO.getPtsPorVencer2() + redimidosTO.getPtsPorVencer2Redimidos());
/*      */       
/*      */ 
/*  949 */       PuntosRedimidosTO puntosRedimidosTO = Utils.regresaPuntos(puntosTO, redimidosTO);
/*  950 */       if (puntosRedimidosTO.getPtsRedimidos() < 0) {
/*  951 */         puntosRedimidosTO.setPtsRedimidos(0);
/*      */       }
/*      */       
/*  954 */       if (mensajeTO.getIdMensaje() == 0) {
/*  955 */         mensajeTO = puntosDAO.actualizaPuntos(puntosRedimidosTO, connection, null, parametrosTO.getCuenta(), parametrosTO.getSecuencia(), true);
/*      */       }
/*      */       
/*  958 */       if (mensajeTO.getIdMensaje() == 0) {
/*  959 */         mensajeTO = puntosDAO.actualizaRedencion(connection, "I", new Timestamp(new Long(fechaOperacion).longValue()), parametrosTO.getCuenta(), new Timestamp(fechaTransaccion), parametrosTO.getUsuariMovimiento());
/*      */       }
/*  961 */       if (mensajeTO.getIdMensaje() == 0) {
/*  962 */         mensajeTO = puntosDAO.actualizaConstancia(connection, "I", null, null, null, new Timestamp(new Long(fechaOperacion).longValue()), parametrosTO.getCuenta(), parametrosTO.getSecuencia());
/*      */       }
/*  964 */       if (mensajeTO.getIdMensaje() == 0) {
/*  965 */         mensajeTO = puntosDAO.eliminaBonoDescuento(connection, parametrosTO.getCuenta(), parametrosTO.getFolio());
/*      */       }
/*      */       
/*  968 */       if ((mensajeTO.getIdMensaje() == 0) && (redimidosTO.getPtsMinimos() > 0)) {
/*  969 */         referencia = "CANCELA PTOS. MINIMOS x CANC. DE REDENCION";
/*  970 */         mensajeTO = puntosDAO.insertaDetalle(connection, fechaTransaccion, referencia, 18, redimidosTO.getPtsMinimos() * -1, null, parametrosTO.getCuenta(), parametrosTO.getSecuencia(), parametrosTO.getTelefono(), parametrosTO.getUsuariMovimiento());
/*  971 */       } else if ((mensajeTO.getIdMensaje() == 0) && (redimidosTO.getBonoProrrateo() > 0)) {
/*  972 */         referencia = "CANCELA PTOS. PRORRATEO x CANC. DE REDENCION";
/*  973 */         mensajeTO = puntosDAO.insertaDetalle(connection, fechaTransaccion, referencia, 18, redimidosTO.getBonoProrrateo() * -1, null, parametrosTO.getCuenta(), parametrosTO.getSecuencia(), parametrosTO.getTelefono(), parametrosTO.getUsuariMovimiento());
/*      */       }
/*  975 */       if ((mensajeTO.getIdMensaje() == 0) && (redimidosTO.getPtsSobrantes1() > 0)) {
/*  976 */         referencia = "Elimina Pts Sobrantes Bono Equipo";
/*  977 */         mensajeTO = puntosDAO.insertaDetalle(connection, fechaTransaccion, referencia, 53, redimidosTO.getPtsSobrantes1() * -1, null, parametrosTO.getCuenta(), parametrosTO.getSecuencia(), parametrosTO.getTelefono(), parametrosTO.getUsuariMovimiento());
/*      */       }
/*  979 */       referencia = "CIR -CANCELA -" + (redimidosTO.getPtsRedimidos() - redimidosTO.getPtsMinimos()) + "PTOS. A PET. DE " + parametrosTO.getUsuariMovimiento() + " " + parametrosTO.getComentario();
/*      */       
/*  981 */       mensajeTO = puntosDAO.insertaComentarioTMP(connection, parametrosTO.getRegion(), parametrosTO.getCuenta(), parametrosTO.getUsuariMovimiento(), fechaTransaccion, referencia);
/*      */       
/*  983 */       if (mensajeTO.getIdMensaje() == 0) connection.commit(); else {
/*  984 */         connection.rollback();
/*      */       }
/*      */     } catch (SQLException e) {
/*  987 */       if (connection != null) try { connection.rollback(); } catch (Exception localException5) {}
/*  988 */       throw new CAException(-1, "RedencionDAO.cancelaApartado.SQLException[" + e.toString() + "]", e);
/*      */     } catch (Exception e) {
/*  990 */       throw new CAException(-1, "RedencionDAO.cancelaApartado.Error[" + e.toString() + "]", e);
/*      */     } finally {
/*  992 */       if (connection != null) try { connection.setAutoCommit(true);connection.close();connection = null; } catch (Exception localException6) {} } if (connection != null) try { connection.setAutoCommit(true);connection.close();connection = null;
/*      */       } catch (Exception localException7) {}
/*  994 */     return mensajeTO;
/*      */   }
/*      */   
/*      */   /* Error */
/*      */   public boolean aplicaRedencionSisact(String folio, com.claro.transfer.service.TelefonoServiceTO telefonoTO, String claveSisact)
/*      */     throws CAException
/*      */   {
/*      */     // Byte code:
/*      */     //   0: aconst_null
/*      */     //   1: astore 4
/*      */     //   3: new 74	com/claro/dao/ConsultasDAO
/*      */     //   6: dup
/*      */     //   7: invokespecial 76	com/claro/dao/ConsultasDAO:<init>	()V
/*      */     //   10: astore 5
/*      */     //   12: aload_1
/*      */     //   13: bipush 16
/*      */     //   15: invokestatic 1146	java/lang/Long:parseLong	(Ljava/lang/String;I)J
/*      */     //   18: lstore 6
/*      */     //   20: invokestatic 36	com/claro/util/ServiceLocator:getInstance	()Lcom/claro/util/ServiceLocator;
/*      */     //   23: getstatic 382	com/claro/util/ServiceLocator:jdbcCirculoAzul	Ljava/lang/String;
/*      */     //   26: invokevirtual 385	com/claro/util/ServiceLocator:getConnection	(Ljava/lang/String;)Ljava/sql/Connection;
/*      */     //   29: astore 4
/*      */     //   31: aload 4
/*      */     //   33: iconst_0
/*      */     //   34: invokeinterface 395 2 0
/*      */     //   39: aload 5
/*      */     //   41: lload 6
/*      */     //   43: invokestatic 1150	java/lang/String:valueOf	(J)Ljava/lang/String;
/*      */     //   46: aconst_null
/*      */     //   47: iconst_0
/*      */     //   48: invokevirtual 105	com/claro/dao/ConsultasDAO:obtieneReservacion	(Ljava/lang/String;Ljava/lang/String;Z)Lcom/claro/transfer/ReservacionTO;
/*      */     //   51: astore 8
/*      */     //   53: aload 8
/*      */     //   55: invokevirtual 1152	com/claro/transfer/ReservacionTO:obtieneMensajeTO	()Lcom/claro/transfer/MensajeTO;
/*      */     //   58: astore 9
/*      */     //   60: aload 9
/*      */     //   62: ifnull +26 -> 88
/*      */     //   65: aload 9
/*      */     //   67: invokevirtual 432	com/claro/transfer/MensajeTO:getIdMensaje	()I
/*      */     //   70: iconst_1
/*      */     //   71: if_icmpne +17 -> 88
/*      */     //   74: new 66	com/claro/exception/CAException
/*      */     //   77: dup
/*      */     //   78: iconst_m1
/*      */     //   79: aload 9
/*      */     //   81: invokevirtual 433	com/claro/transfer/MensajeTO:getMensaje	()Ljava/lang/String;
/*      */     //   84: invokespecial 820	com/claro/exception/CAException:<init>	(ILjava/lang/String;)V
/*      */     //   87: athrow
/*      */     //   88: new 685	java/text/SimpleDateFormat
/*      */     //   91: dup
/*      */     //   92: ldc_w 1153
/*      */     //   95: new 1155	java/util/Locale
/*      */     //   98: dup
/*      */     //   99: ldc_w 1157
/*      */     //   102: ldc_w 1159
/*      */     //   105: invokespecial 1161	java/util/Locale:<init>	(Ljava/lang/String;Ljava/lang/String;)V
/*      */     //   108: invokespecial 1164	java/text/SimpleDateFormat:<init>	(Ljava/lang/String;Ljava/util/Locale;)V
/*      */     //   111: astore 10
/*      */     //   113: aload 10
/*      */     //   115: new 690	java/util/Date
/*      */     //   118: dup
/*      */     //   119: invokespecial 692	java/util/Date:<init>	()V
/*      */     //   122: invokevirtual 693	java/text/SimpleDateFormat:format	(Ljava/util/Date;)Ljava/lang/String;
/*      */     //   125: invokestatic 206	java/lang/Integer:parseInt	(Ljava/lang/String;)I
/*      */     //   128: istore 11
/*      */     //   130: aload 10
/*      */     //   132: aload 8
/*      */     //   134: invokevirtual 1167	com/claro/transfer/ReservacionTO:getFechaExpiracion	()Ljava/util/Date;
/*      */     //   137: invokevirtual 693	java/text/SimpleDateFormat:format	(Ljava/util/Date;)Ljava/lang/String;
/*      */     //   140: invokestatic 206	java/lang/Integer:parseInt	(Ljava/lang/String;)I
/*      */     //   143: istore 12
/*      */     //   145: iload 11
/*      */     //   147: iload 12
/*      */     //   149: if_icmple +50 -> 199
/*      */     //   152: aload 8
/*      */     //   154: invokevirtual 840	com/claro/transfer/ReservacionTO:getEstatus	()Ljava/lang/String;
/*      */     //   157: ldc -29
/*      */     //   159: invokevirtual 297	java/lang/String:equals	(Ljava/lang/Object;)Z
/*      */     //   162: ifeq +37 -> 199
/*      */     //   165: getstatic 1170	java/lang/System:out	Ljava/io/PrintStream;
/*      */     //   168: ldc_w 1174
/*      */     //   171: invokevirtual 1176	java/io/PrintStream:println	(Ljava/lang/String;)V
/*      */     //   174: aload 5
/*      */     //   176: lload 6
/*      */     //   178: invokestatic 1150	java/lang/String:valueOf	(J)Ljava/lang/String;
/*      */     //   181: aload_2
/*      */     //   182: invokevirtual 1181	com/claro/transfer/service/TelefonoServiceTO:getCuenta	()Ljava/lang/String;
/*      */     //   185: aload_2
/*      */     //   186: invokevirtual 1184	com/claro/transfer/service/TelefonoServiceTO:getSecuencia	()I
/*      */     //   189: aload 8
/*      */     //   191: invokevirtual 267	com/claro/transfer/ReservacionTO:getFuerzaVenta	()Ljava/lang/String;
/*      */     //   194: aload 4
/*      */     //   196: invokevirtual 1185	com/claro/dao/ConsultasDAO:cancelaReservacionSisact	(Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;Ljava/sql/Connection;)V
/*      */     //   199: aload 5
/*      */     //   201: aload 8
/*      */     //   203: aload_2
/*      */     //   204: invokevirtual 1189	com/claro/dao/ConsultasDAO:calculaPuntos	(Lcom/claro/transfer/ReservacionTO;Lcom/claro/transfer/service/TelefonoServiceTO;)Ljava/util/ArrayList;
/*      */     //   207: astore 13
/*      */     //   209: aload 5
/*      */     //   211: lload 6
/*      */     //   213: invokestatic 1150	java/lang/String:valueOf	(J)Ljava/lang/String;
/*      */     //   216: aload_3
/*      */     //   217: aload_2
/*      */     //   218: aload 8
/*      */     //   220: aload 13
/*      */     //   222: aload 4
/*      */     //   224: invokevirtual 1193	com/claro/dao/ConsultasDAO:actualizaRedencion	(Ljava/lang/String;Ljava/lang/String;Lcom/claro/transfer/service/TelefonoServiceTO;Lcom/claro/transfer/ReservacionTO;Ljava/util/ArrayList;Ljava/sql/Connection;)V
/*      */     //   227: aload 4
/*      */     //   229: invokeinterface 710 1 0
/*      */     //   234: goto +240 -> 474
/*      */     //   237: astore 8
/*      */     //   239: aload 4
/*      */     //   241: ifnull +15 -> 256
/*      */     //   244: aload 4
/*      */     //   246: invokeinterface 434 1 0
/*      */     //   251: goto +5 -> 256
/*      */     //   254: astore 9
/*      */     //   256: getstatic 1170	java/lang/System:out	Ljava/io/PrintStream;
/*      */     //   259: aload 8
/*      */     //   261: invokevirtual 715	java/sql/SQLException:toString	()Ljava/lang/String;
/*      */     //   264: invokevirtual 1176	java/io/PrintStream:println	(Ljava/lang/String;)V
/*      */     //   267: new 66	com/claro/exception/CAException
/*      */     //   270: dup
/*      */     //   271: iconst_m1
/*      */     //   272: new 450	java/lang/StringBuilder
/*      */     //   275: dup
/*      */     //   276: ldc_w 1196
/*      */     //   279: invokespecial 456	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
/*      */     //   282: aload 8
/*      */     //   284: invokevirtual 715	java/sql/SQLException:toString	()Ljava/lang/String;
/*      */     //   287: invokevirtual 460	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   290: ldc_w 681
/*      */     //   293: invokevirtual 460	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   296: invokevirtual 464	java/lang/StringBuilder:toString	()Ljava/lang/String;
/*      */     //   299: aload 8
/*      */     //   301: invokespecial 718	com/claro/exception/CAException:<init>	(ILjava/lang/String;Ljava/lang/Exception;)V
/*      */     //   304: athrow
/*      */     //   305: astore 8
/*      */     //   307: aload 4
/*      */     //   309: ifnull +15 -> 324
/*      */     //   312: aload 4
/*      */     //   314: invokeinterface 434 1 0
/*      */     //   319: goto +5 -> 324
/*      */     //   322: astore 9
/*      */     //   324: getstatic 1170	java/lang/System:out	Ljava/io/PrintStream;
/*      */     //   327: aload 8
/*      */     //   329: invokevirtual 1198	com/claro/exception/CAException:toString	()Ljava/lang/String;
/*      */     //   332: invokevirtual 1176	java/io/PrintStream:println	(Ljava/lang/String;)V
/*      */     //   335: new 66	com/claro/exception/CAException
/*      */     //   338: dup
/*      */     //   339: iconst_m1
/*      */     //   340: new 450	java/lang/StringBuilder
/*      */     //   343: dup
/*      */     //   344: ldc_w 1196
/*      */     //   347: invokespecial 456	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
/*      */     //   350: aload 8
/*      */     //   352: invokevirtual 1198	com/claro/exception/CAException:toString	()Ljava/lang/String;
/*      */     //   355: invokevirtual 460	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   358: ldc_w 681
/*      */     //   361: invokevirtual 460	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   364: invokevirtual 464	java/lang/StringBuilder:toString	()Ljava/lang/String;
/*      */     //   367: aload 8
/*      */     //   369: invokespecial 718	com/claro/exception/CAException:<init>	(ILjava/lang/String;Ljava/lang/Exception;)V
/*      */     //   372: athrow
/*      */     //   373: astore 8
/*      */     //   375: aload 4
/*      */     //   377: ifnull +15 -> 392
/*      */     //   380: aload 4
/*      */     //   382: invokeinterface 434 1 0
/*      */     //   387: goto +5 -> 392
/*      */     //   390: astore 9
/*      */     //   392: getstatic 1170	java/lang/System:out	Ljava/io/PrintStream;
/*      */     //   395: aload 8
/*      */     //   397: invokevirtual 723	java/lang/Exception:toString	()Ljava/lang/String;
/*      */     //   400: invokevirtual 1176	java/io/PrintStream:println	(Ljava/lang/String;)V
/*      */     //   403: new 66	com/claro/exception/CAException
/*      */     //   406: dup
/*      */     //   407: iconst_m1
/*      */     //   408: new 450	java/lang/StringBuilder
/*      */     //   411: dup
/*      */     //   412: ldc_w 1196
/*      */     //   415: invokespecial 456	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
/*      */     //   418: aload 8
/*      */     //   420: invokevirtual 723	java/lang/Exception:toString	()Ljava/lang/String;
/*      */     //   423: invokevirtual 460	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   426: ldc_w 681
/*      */     //   429: invokevirtual 460	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   432: invokevirtual 464	java/lang/StringBuilder:toString	()Ljava/lang/String;
/*      */     //   435: aload 8
/*      */     //   437: invokespecial 718	com/claro/exception/CAException:<init>	(ILjava/lang/String;Ljava/lang/Exception;)V
/*      */     //   440: athrow
/*      */     //   441: astore 14
/*      */     //   443: aload 4
/*      */     //   445: ifnull +26 -> 471
/*      */     //   448: aload 4
/*      */     //   450: iconst_1
/*      */     //   451: invokeinterface 395 2 0
/*      */     //   456: aload 4
/*      */     //   458: invokeinterface 115 1 0
/*      */     //   463: aconst_null
/*      */     //   464: astore 4
/*      */     //   466: goto +5 -> 471
/*      */     //   469: astore 15
/*      */     //   471: aload 14
/*      */     //   473: athrow
/*      */     //   474: aload 4
/*      */     //   476: ifnull +26 -> 502
/*      */     //   479: aload 4
/*      */     //   481: iconst_1
/*      */     //   482: invokeinterface 395 2 0
/*      */     //   487: aload 4
/*      */     //   489: invokeinterface 115 1 0
/*      */     //   494: aconst_null
/*      */     //   495: astore 4
/*      */     //   497: goto +5 -> 502
/*      */     //   500: astore 15
/*      */     //   502: iconst_1
/*      */     //   503: ireturn
/*      */     // Line number table:
/*      */     //   Java source line #1001	-> byte code offset #0
/*      */     //   Java source line #1002	-> byte code offset #3
/*      */     //   Java source line #1003	-> byte code offset #12
/*      */     //   Java source line #1005	-> byte code offset #20
/*      */     //   Java source line #1006	-> byte code offset #31
/*      */     //   Java source line #1008	-> byte code offset #39
/*      */     //   Java source line #1009	-> byte code offset #53
/*      */     //   Java source line #1010	-> byte code offset #60
/*      */     //   Java source line #1011	-> byte code offset #74
/*      */     //   Java source line #1026	-> byte code offset #88
/*      */     //   Java source line #1027	-> byte code offset #113
/*      */     //   Java source line #1028	-> byte code offset #130
/*      */     //   Java source line #1030	-> byte code offset #145
/*      */     //   Java source line #1031	-> byte code offset #165
/*      */     //   Java source line #1033	-> byte code offset #174
/*      */     //   Java source line #1036	-> byte code offset #199
/*      */     //   Java source line #1037	-> byte code offset #209
/*      */     //   Java source line #1039	-> byte code offset #227
/*      */     //   Java source line #1040	-> byte code offset #237
/*      */     //   Java source line #1041	-> byte code offset #239
/*      */     //   Java source line #1042	-> byte code offset #256
/*      */     //   Java source line #1043	-> byte code offset #267
/*      */     //   Java source line #1044	-> byte code offset #305
/*      */     //   Java source line #1045	-> byte code offset #307
/*      */     //   Java source line #1046	-> byte code offset #324
/*      */     //   Java source line #1047	-> byte code offset #335
/*      */     //   Java source line #1048	-> byte code offset #373
/*      */     //   Java source line #1049	-> byte code offset #375
/*      */     //   Java source line #1050	-> byte code offset #392
/*      */     //   Java source line #1051	-> byte code offset #403
/*      */     //   Java source line #1052	-> byte code offset #441
/*      */     //   Java source line #1053	-> byte code offset #443
/*      */     //   Java source line #1054	-> byte code offset #471
/*      */     //   Java source line #1053	-> byte code offset #474
/*      */     //   Java source line #1055	-> byte code offset #502
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	signature
/*      */     //   0	504	0	this	RedencionDAO
/*      */     //   0	504	1	folio	String
/*      */     //   0	504	2	telefonoTO	com.claro.transfer.service.TelefonoServiceTO
/*      */     //   0	504	3	claveSisact	String
/*      */     //   1	495	4	conexion	Connection
/*      */     //   10	200	5	consulta	ConsultasDAO
/*      */     //   18	194	6	folioDec	long
/*      */     //   51	168	8	reservacionTO	ReservacionTO
/*      */     //   237	63	8	e	SQLException
/*      */     //   305	63	8	e	CAException
/*      */     //   373	63	8	e	Exception
/*      */     //   58	22	9	mensajeTO	MensajeTO
/*      */     //   254	1	9	localException1	Exception
/*      */     //   322	1	9	localException2	Exception
/*      */     //   390	1	9	localException3	Exception
/*      */     //   111	20	10	sdf	SimpleDateFormat
/*      */     //   128	18	11	fechaAct	int
/*      */     //   143	5	12	fechaVenc	int
/*      */     //   207	14	13	puntosRedecionSisact	java.util.ArrayList<int[]>
/*      */     //   441	31	14	localObject	Object
/*      */     //   469	1	15	localException4	Exception
/*      */     //   500	1	15	localException5	Exception
/*      */     // Exception table:
/*      */     //   from	to	target	type
/*      */     //   20	234	237	java/sql/SQLException
/*      */     //   244	251	254	java/lang/Exception
/*      */     //   20	234	305	com/claro/exception/CAException
/*      */     //   312	319	322	java/lang/Exception
/*      */     //   20	234	373	java/lang/Exception
/*      */     //   380	387	390	java/lang/Exception
/*      */     //   20	441	441	finally
/*      */     //   448	466	469	java/lang/Exception
/*      */     //   479	497	500	java/lang/Exception
/*      */   }
/*      */   
/*      */   /* Error */
/*      */   private String getEstatusPuntos(String cuenta, Connection conn, int secuencia)
/*      */   {
/*      */     // Byte code:
/*      */     //   0: aconst_null
/*      */     //   1: astore 4
/*      */     //   3: aconst_null
/*      */     //   4: astore 5
/*      */     //   6: ldc 77
/*      */     //   8: astore 6
/*      */     //   10: new 1212	java/lang/StringBuffer
/*      */     //   13: dup
/*      */     //   14: invokespecial 1214	java/lang/StringBuffer:<init>	()V
/*      */     //   17: astore 7
/*      */     //   19: aload 7
/*      */     //   21: ldc_w 1215
/*      */     //   24: invokevirtual 1217	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   27: aload_0
/*      */     //   28: getfield 48	com/claro/dao/RedencionDAO:schema_database	Ljava/lang/String;
/*      */     //   31: invokevirtual 1217	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   34: ldc_w 1220
/*      */     //   37: invokevirtual 1217	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   40: pop
/*      */     //   41: aload 7
/*      */     //   43: aload_0
/*      */     //   44: getfield 48	com/claro/dao/RedencionDAO:schema_database	Ljava/lang/String;
/*      */     //   47: invokevirtual 1217	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   50: ldc_w 1222
/*      */     //   53: invokevirtual 1217	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   56: pop
/*      */     //   57: aload 7
/*      */     //   59: ldc_w 1224
/*      */     //   62: invokevirtual 1217	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   65: pop
/*      */     //   66: aload 7
/*      */     //   68: ldc_w 1226
/*      */     //   71: invokevirtual 1217	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*      */     //   74: pop
/*      */     //   75: aload_2
/*      */     //   76: aload 7
/*      */     //   78: invokevirtual 1228	java/lang/StringBuffer:toString	()Ljava/lang/String;
/*      */     //   81: invokeinterface 1229 2 0
/*      */     //   86: astore 5
/*      */     //   88: aload 5
/*      */     //   90: iconst_1
/*      */     //   91: aload_1
/*      */     //   92: invokeinterface 1233 3 0
/*      */     //   97: aload 5
/*      */     //   99: iconst_2
/*      */     //   100: iload_3
/*      */     //   101: invokeinterface 1238 3 0
/*      */     //   106: aload 5
/*      */     //   108: invokeinterface 1242 1 0
/*      */     //   113: astore 4
/*      */     //   115: aload_2
/*      */     //   116: aload 7
/*      */     //   118: invokevirtual 1228	java/lang/StringBuffer:toString	()Ljava/lang/String;
/*      */     //   121: invokeinterface 1229 2 0
/*      */     //   126: astore 5
/*      */     //   128: goto +15 -> 143
/*      */     //   131: aload 4
/*      */     //   133: ldc_w 1246
/*      */     //   136: invokeinterface 1248 2 0
/*      */     //   141: astore 6
/*      */     //   143: aload 4
/*      */     //   145: invokeinterface 1253 1 0
/*      */     //   150: ifne -19 -> 131
/*      */     //   153: goto +98 -> 251
/*      */     //   156: astore 8
/*      */     //   158: aload 8
/*      */     //   160: invokevirtual 1256	java/sql/SQLException:printStackTrace	()V
/*      */     //   163: aload 4
/*      */     //   165: ifnull +18 -> 183
/*      */     //   168: aload 4
/*      */     //   170: invokeinterface 1259 1 0
/*      */     //   175: aconst_null
/*      */     //   176: astore 4
/*      */     //   178: goto +5 -> 183
/*      */     //   181: astore 10
/*      */     //   183: aload 5
/*      */     //   185: ifnull +106 -> 291
/*      */     //   188: aload 5
/*      */     //   190: invokeinterface 1260 1 0
/*      */     //   195: aconst_null
/*      */     //   196: astore 5
/*      */     //   198: goto +93 -> 291
/*      */     //   201: astore 10
/*      */     //   203: goto +88 -> 291
/*      */     //   206: astore 9
/*      */     //   208: aload 4
/*      */     //   210: ifnull +18 -> 228
/*      */     //   213: aload 4
/*      */     //   215: invokeinterface 1259 1 0
/*      */     //   220: aconst_null
/*      */     //   221: astore 4
/*      */     //   223: goto +5 -> 228
/*      */     //   226: astore 10
/*      */     //   228: aload 5
/*      */     //   230: ifnull +18 -> 248
/*      */     //   233: aload 5
/*      */     //   235: invokeinterface 1260 1 0
/*      */     //   240: aconst_null
/*      */     //   241: astore 5
/*      */     //   243: goto +5 -> 248
/*      */     //   246: astore 10
/*      */     //   248: aload 9
/*      */     //   250: athrow
/*      */     //   251: aload 4
/*      */     //   253: ifnull +18 -> 271
/*      */     //   256: aload 4
/*      */     //   258: invokeinterface 1259 1 0
/*      */     //   263: aconst_null
/*      */     //   264: astore 4
/*      */     //   266: goto +5 -> 271
/*      */     //   269: astore 10
/*      */     //   271: aload 5
/*      */     //   273: ifnull +18 -> 291
/*      */     //   276: aload 5
/*      */     //   278: invokeinterface 1260 1 0
/*      */     //   283: aconst_null
/*      */     //   284: astore 5
/*      */     //   286: goto +5 -> 291
/*      */     //   289: astore 10
/*      */     //   291: aload 6
/*      */     //   293: areturn
/*      */     // Line number table:
/*      */     //   Java source line #1059	-> byte code offset #0
/*      */     //   Java source line #1060	-> byte code offset #3
/*      */     //   Java source line #1061	-> byte code offset #6
/*      */     //   Java source line #1063	-> byte code offset #10
/*      */     //   Java source line #1064	-> byte code offset #19
/*      */     //   Java source line #1065	-> byte code offset #41
/*      */     //   Java source line #1066	-> byte code offset #57
/*      */     //   Java source line #1067	-> byte code offset #66
/*      */     //   Java source line #1069	-> byte code offset #75
/*      */     //   Java source line #1070	-> byte code offset #88
/*      */     //   Java source line #1071	-> byte code offset #97
/*      */     //   Java source line #1074	-> byte code offset #106
/*      */     //   Java source line #1075	-> byte code offset #115
/*      */     //   Java source line #1077	-> byte code offset #128
/*      */     //   Java source line #1078	-> byte code offset #131
/*      */     //   Java source line #1077	-> byte code offset #143
/*      */     //   Java source line #1081	-> byte code offset #156
/*      */     //   Java source line #1083	-> byte code offset #158
/*      */     //   Java source line #1085	-> byte code offset #163
/*      */     //   Java source line #1086	-> byte code offset #183
/*      */     //   Java source line #1084	-> byte code offset #206
/*      */     //   Java source line #1085	-> byte code offset #208
/*      */     //   Java source line #1086	-> byte code offset #228
/*      */     //   Java source line #1087	-> byte code offset #248
/*      */     //   Java source line #1085	-> byte code offset #251
/*      */     //   Java source line #1086	-> byte code offset #271
/*      */     //   Java source line #1089	-> byte code offset #291
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	signature
/*      */     //   0	294	0	this	RedencionDAO
/*      */     //   0	294	1	cuenta	String
/*      */     //   0	294	2	conn	Connection
/*      */     //   0	294	3	secuencia	int
/*      */     //   1	264	4	resultSet	ResultSet
/*      */     //   4	281	5	stmt	PreparedStatement
/*      */     //   8	284	6	Status	String
/*      */     //   17	100	7	query	StringBuffer
/*      */     //   156	3	8	e	SQLException
/*      */     //   206	43	9	localObject	Object
/*      */     //   181	1	10	localException	Exception
/*      */     //   201	1	10	localException1	Exception
/*      */     //   226	1	10	localException2	Exception
/*      */     //   246	1	10	localException3	Exception
/*      */     //   269	1	10	localException4	Exception
/*      */     //   289	1	10	localException5	Exception
/*      */     // Exception table:
/*      */     //   from	to	target	type
/*      */     //   75	153	156	java/sql/SQLException
/*      */     //   168	178	181	java/lang/Exception
/*      */     //   188	198	201	java/lang/Exception
/*      */     //   75	163	206	finally
/*      */     //   213	223	226	java/lang/Exception
/*      */     //   233	243	246	java/lang/Exception
/*      */     //   256	266	269	java/lang/Exception
/*      */     //   276	286	289	java/lang/Exception
/*      */   }
/*      */   
/*      */   private void EstatusPuntosDist(Connection conn, String cuenta)
/*      */     throws CAException
/*      */   {
/* 1095 */     ResultSet resultSetFvta = null;
/* 1096 */     PreparedStatement statementFvta = null;
/* 1097 */     String distReserva = "";
/* 1098 */     Date fechaReservacion = null;
/*      */     
/*      */ 
/* 1101 */     StringBuffer query = new StringBuffer();
/* 1102 */     query.append(" SELECT B.FZAVTA, B.FECHAOPER ");
/* 1103 */     query.append("   FROM  ").append(this.schema_database).append("PTO_TBLLINEAS A,  ").append(this.schema_database).append("PTO_TBLRESERVACIONES B ");
/* 1104 */     query.append("  WHERE ").append("B.CUENTA = ? ");
/* 1105 */     query.append("   AND A.CUENTA = B.CUENTA AND A.STATUSPUNTOS='R' ");
/* 1106 */     query.append(" ORDER BY B.FECHAOPER DESC ");
/*      */     try
/*      */     {
/* 1109 */       statementFvta = conn.prepareStatement(query.toString());
/* 1110 */       statementFvta.setString(1, cuenta);
/*      */       
/* 1112 */       resultSetFvta = statementFvta.executeQuery();
/*      */       
/*      */ 
/* 1115 */       if (resultSetFvta.next()) {
/* 1116 */         distReserva = resultSetFvta.getString(1);
/* 1117 */         fechaReservacion = resultSetFvta.getDate(2);
/* 1118 */         throw new CAException(-1, "Los puntos estan reservados por el Distribuidor: " + distReserva + " el dia:" + Constantes.DATEFORMATdd_MM_YYYY.format(new Date(fechaReservacion.getTime())));
/*      */       }
/* 1120 */     } catch (SQLException e) { e = e;
/*      */       
/* 1122 */       e.printStackTrace();
/*      */       
/* 1124 */       if (resultSetFvta != null) try { resultSetFvta.close();resultSetFvta = null; } catch (Exception localException) {}
/* 1125 */       if (statementFvta == null) return; try { statementFvta.close();statementFvta = null;
/*      */       }
/*      */       catch (Exception localException1) {}
/*      */     }
/*      */     finally
/*      */     {
/* 1123 */       localObject = 
/*      */       
/* 1125 */         finally;
/* 1124 */       if (resultSetFvta != null) try { resultSetFvta.close();resultSetFvta = null; } catch (Exception localException2) {}
/* 1125 */       if (statementFvta != null) try { statementFvta.close();statementFvta = null; } catch (Exception localException3) {}
/* 1126 */       throw ((Throwable)localObject);
/*      */     }
/* 1124 */     if (resultSetFvta != null) try { resultSetFvta.close();resultSetFvta = null; } catch (Exception localException4) {}
/* 1125 */     if (statementFvta != null) try { statementFvta.close();statementFvta = null;
/*      */       }
/*      */       catch (Exception localException5) {}
/*      */   }
/*      */   
/*      */   /* Error */
/*      */   public java.util.Hashtable<String, Object> validaProductoDescuento(String telefono, int region, String cuenta, String endPoint, String usuario)
/*      */     throws CAException
/*      */   {
/*      */     // Byte code:
/*      */     //   0: aconst_null
/*      */     //   1: astore 6
/*      */     //   3: aconst_null
/*      */     //   4: astore 7
/*      */     //   6: aconst_null
/*      */     //   7: astore 8
/*      */     //   9: aconst_null
/*      */     //   10: astore 9
/*      */     //   12: new 68	com/claro/transfer/MensajeTO
/*      */     //   15: dup
/*      */     //   16: invokespecial 70	com/claro/transfer/MensajeTO:<init>	()V
/*      */     //   19: astore 10
/*      */     //   21: ldc 77
/*      */     //   23: astore 11
/*      */     //   25: ldc 77
/*      */     //   27: astore 12
/*      */     //   29: aconst_null
/*      */     //   30: checkcast 1305	[Lcom/telcel/crm/scrapy/beans/beneficios/PlanTO;
/*      */     //   33: astore 13
/*      */     //   35: new 635	com/telcel/gscrm/dccrm/ws/ioh/AplicaBeneficioProxy
/*      */     //   38: dup
/*      */     //   39: invokespecial 637	com/telcel/gscrm/dccrm/ws/ioh/AplicaBeneficioProxy:<init>	()V
/*      */     //   42: astore 14
/*      */     //   44: new 79	com/claro/catalogo/Catalogo
/*      */     //   47: dup
/*      */     //   48: invokespecial 81	com/claro/catalogo/Catalogo:<init>	()V
/*      */     //   51: astore 15
/*      */     //   53: aload 15
/*      */     //   55: ldc 82
/*      */     //   57: invokevirtual 84	com/claro/catalogo/Catalogo:setTabla	(Ljava/lang/String;)V
/*      */     //   60: aload 15
/*      */     //   62: invokevirtual 88	com/claro/catalogo/Catalogo:cargaCatalogo	()V
/*      */     //   65: new 626	java/lang/Boolean
/*      */     //   68: dup
/*      */     //   69: aload 15
/*      */     //   71: ldc_w 628
/*      */     //   74: invokevirtual 93	com/claro/catalogo/Catalogo:getPropiedad	(Ljava/lang/String;)Ljava/lang/String;
/*      */     //   77: invokespecial 630	java/lang/Boolean:<init>	(Ljava/lang/String;)V
/*      */     //   80: invokevirtual 631	java/lang/Boolean:booleanValue	()Z
/*      */     //   83: istore 16
/*      */     //   85: iload 16
/*      */     //   87: ifeq +38 -> 125
/*      */     //   90: aconst_null
/*      */     //   91: astore 17
/*      */     //   93: aload 14
/*      */     //   95: aload 4
/*      */     //   97: invokevirtual 638	com/telcel/gscrm/dccrm/ws/ioh/AplicaBeneficioProxy:setEndpoint	(Ljava/lang/String;)V
/*      */     //   100: aload 14
/*      */     //   102: aload_3
/*      */     //   103: aload_1
/*      */     //   104: iload_2
/*      */     //   105: invokevirtual 1307	com/telcel/gscrm/dccrm/ws/ioh/AplicaBeneficioProxy:consultaPlanDescuentoRenta	(Ljava/lang/String;Ljava/lang/String;I)Lcom/telcel/crm/scrapy/beans/beneficios/Cudi2TO;
/*      */     //   108: astore 17
/*      */     //   110: aload 17
/*      */     //   112: invokevirtual 1311	com/telcel/crm/scrapy/beans/beneficios/Cudi2TO:getPlanesDescuento	()[Lcom/telcel/crm/scrapy/beans/beneficios/PlanTO;
/*      */     //   115: astore 13
/*      */     //   117: ldc_w 1315
/*      */     //   120: astore 12
/*      */     //   122: goto +35 -> 157
/*      */     //   125: aconst_null
/*      */     //   126: astore 17
/*      */     //   128: aload 14
/*      */     //   130: aload 4
/*      */     //   132: invokevirtual 638	com/telcel/gscrm/dccrm/ws/ioh/AplicaBeneficioProxy:setEndpoint	(Ljava/lang/String;)V
/*      */     //   135: aload 14
/*      */     //   137: aload_3
/*      */     //   138: aload_1
/*      */     //   139: iload_2
/*      */     //   140: invokevirtual 1317	com/telcel/gscrm/dccrm/ws/ioh/AplicaBeneficioProxy:consultaPlanDescuento	(Ljava/lang/String;Ljava/lang/String;I)Lcom/telcel/crm/scrapy/beans/beneficios/CudisTO;
/*      */     //   143: astore 17
/*      */     //   145: aload 17
/*      */     //   147: invokevirtual 1321	com/telcel/crm/scrapy/beans/beneficios/CudisTO:getPlanesDescuento	()[Lcom/telcel/crm/scrapy/beans/beneficios/PlanTO;
/*      */     //   150: astore 13
/*      */     //   152: ldc_w 1322
/*      */     //   155: astore 12
/*      */     //   157: aload 13
/*      */     //   159: ifnull +259 -> 418
/*      */     //   162: aload 13
/*      */     //   164: arraylength
/*      */     //   165: ifle +253 -> 418
/*      */     //   168: aconst_null
/*      */     //   169: astore 17
/*      */     //   171: aload 10
/*      */     //   173: iconst_0
/*      */     //   174: ldc_w 1324
/*      */     //   177: invokevirtual 927	com/claro/transfer/MensajeTO:agregaMensaje	(ILjava/lang/String;)V
/*      */     //   180: invokestatic 36	com/claro/util/ServiceLocator:getInstance	()Lcom/claro/util/ServiceLocator;
/*      */     //   183: getstatic 382	com/claro/util/ServiceLocator:jdbcCirculoAzul	Ljava/lang/String;
/*      */     //   186: invokevirtual 385	com/claro/util/ServiceLocator:getConnection	(Ljava/lang/String;)Ljava/sql/Connection;
/*      */     //   189: astore 6
/*      */     //   191: new 450	java/lang/StringBuilder
/*      */     //   194: dup
/*      */     //   195: ldc_w 1326
/*      */     //   198: invokespecial 456	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
/*      */     //   201: aload_0
/*      */     //   202: getfield 48	com/claro/dao/RedencionDAO:schema_database	Ljava/lang/String;
/*      */     //   205: invokevirtual 460	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   208: ldc_w 1328
/*      */     //   211: invokevirtual 460	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   214: invokevirtual 464	java/lang/StringBuilder:toString	()Ljava/lang/String;
/*      */     //   217: astore 18
/*      */     //   219: aload 6
/*      */     //   221: aload 18
/*      */     //   223: invokeinterface 1229 2 0
/*      */     //   228: astore 7
/*      */     //   230: iconst_0
/*      */     //   231: istore 19
/*      */     //   233: goto +174 -> 407
/*      */     //   236: aload 13
/*      */     //   238: iload 19
/*      */     //   240: aaload
/*      */     //   241: astore 17
/*      */     //   243: aload 7
/*      */     //   245: invokeinterface 1330 1 0
/*      */     //   250: aload 7
/*      */     //   252: iconst_1
/*      */     //   253: aload 17
/*      */     //   255: invokevirtual 1333	com/telcel/crm/scrapy/beans/beneficios/PlanTO:getPlanDescuento	()Ljava/lang/String;
/*      */     //   258: invokeinterface 1233 3 0
/*      */     //   263: aload 7
/*      */     //   265: invokeinterface 1242 1 0
/*      */     //   270: astore 8
/*      */     //   272: aload 8
/*      */     //   274: invokeinterface 1253 1 0
/*      */     //   279: ifeq +105 -> 384
/*      */     //   282: aload 8
/*      */     //   284: iconst_1
/*      */     //   285: invokeinterface 1285 2 0
/*      */     //   290: astore 20
/*      */     //   292: aload 20
/*      */     //   294: ifnull +68 -> 362
/*      */     //   297: aload 20
/*      */     //   299: iconst_0
/*      */     //   300: iconst_2
/*      */     //   301: invokevirtual 580	java/lang/String:substring	(II)Ljava/lang/String;
/*      */     //   304: ldc_w 1336
/*      */     //   307: invokevirtual 297	java/lang/String:equals	(Ljava/lang/Object;)Z
/*      */     //   310: ifne +19 -> 329
/*      */     //   313: aload 20
/*      */     //   315: iconst_2
/*      */     //   316: iconst_2
/*      */     //   317: invokevirtual 580	java/lang/String:substring	(II)Ljava/lang/String;
/*      */     //   320: ldc_w 1336
/*      */     //   323: invokevirtual 297	java/lang/String:equals	(Ljava/lang/Object;)Z
/*      */     //   326: ifeq +36 -> 362
/*      */     //   329: aload 10
/*      */     //   331: iconst_1
/*      */     //   332: new 450	java/lang/StringBuilder
/*      */     //   335: dup
/*      */     //   336: ldc_w 1338
/*      */     //   339: invokespecial 456	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
/*      */     //   342: aload 12
/*      */     //   344: invokevirtual 460	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   347: ldc_w 1340
/*      */     //   350: invokevirtual 460	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   353: invokevirtual 464	java/lang/StringBuilder:toString	()Ljava/lang/String;
/*      */     //   356: invokevirtual 927	com/claro/transfer/MensajeTO:agregaMensaje	(ILjava/lang/String;)V
/*      */     //   359: goto +25 -> 384
/*      */     //   362: new 450	java/lang/StringBuilder
/*      */     //   365: dup
/*      */     //   366: aload 11
/*      */     //   368: invokestatic 452	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
/*      */     //   371: invokespecial 456	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
/*      */     //   374: aload 20
/*      */     //   376: invokevirtual 460	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   379: invokevirtual 464	java/lang/StringBuilder:toString	()Ljava/lang/String;
/*      */     //   382: astore 11
/*      */     //   384: aload 8
/*      */     //   386: ifnull +18 -> 404
/*      */     //   389: aload 8
/*      */     //   391: invokeinterface 1259 1 0
/*      */     //   396: aconst_null
/*      */     //   397: astore 8
/*      */     //   399: goto +5 -> 404
/*      */     //   402: astore 20
/*      */     //   404: iinc 19 1
/*      */     //   407: iload 19
/*      */     //   409: aload 13
/*      */     //   411: arraylength
/*      */     //   412: if_icmplt -176 -> 236
/*      */     //   415: goto +12 -> 427
/*      */     //   418: aload 10
/*      */     //   420: iconst_0
/*      */     //   421: ldc_w 1342
/*      */     //   424: invokevirtual 927	com/claro/transfer/MensajeTO:agregaMensaje	(ILjava/lang/String;)V
/*      */     //   427: new 1344	java/util/Hashtable
/*      */     //   430: dup
/*      */     //   431: invokespecial 1346	java/util/Hashtable:<init>	()V
/*      */     //   434: astore 9
/*      */     //   436: aload 9
/*      */     //   438: ldc_w 1347
/*      */     //   441: aload 11
/*      */     //   443: invokevirtual 1349	java/util/Hashtable:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
/*      */     //   446: pop
/*      */     //   447: aload 9
/*      */     //   449: ldc_w 1353
/*      */     //   452: aload 10
/*      */     //   454: invokevirtual 1349	java/util/Hashtable:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
/*      */     //   457: pop
/*      */     //   458: goto +148 -> 606
/*      */     //   461: astore 14
/*      */     //   463: new 66	com/claro/exception/CAException
/*      */     //   466: dup
/*      */     //   467: iconst_m1
/*      */     //   468: new 450	java/lang/StringBuilder
/*      */     //   471: dup
/*      */     //   472: ldc_w 1354
/*      */     //   475: invokespecial 456	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
/*      */     //   478: aload 14
/*      */     //   480: invokevirtual 715	java/sql/SQLException:toString	()Ljava/lang/String;
/*      */     //   483: invokevirtual 460	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   486: ldc_w 681
/*      */     //   489: invokevirtual 460	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   492: invokevirtual 464	java/lang/StringBuilder:toString	()Ljava/lang/String;
/*      */     //   495: aload 14
/*      */     //   497: invokespecial 718	com/claro/exception/CAException:<init>	(ILjava/lang/String;Ljava/lang/Exception;)V
/*      */     //   500: athrow
/*      */     //   501: astore 14
/*      */     //   503: new 66	com/claro/exception/CAException
/*      */     //   506: dup
/*      */     //   507: iconst_m1
/*      */     //   508: new 450	java/lang/StringBuilder
/*      */     //   511: dup
/*      */     //   512: ldc_w 1356
/*      */     //   515: invokespecial 456	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
/*      */     //   518: aload 14
/*      */     //   520: invokevirtual 723	java/lang/Exception:toString	()Ljava/lang/String;
/*      */     //   523: invokevirtual 460	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   526: ldc_w 681
/*      */     //   529: invokevirtual 460	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   532: invokevirtual 464	java/lang/StringBuilder:toString	()Ljava/lang/String;
/*      */     //   535: aload 14
/*      */     //   537: invokespecial 718	com/claro/exception/CAException:<init>	(ILjava/lang/String;Ljava/lang/Exception;)V
/*      */     //   540: athrow
/*      */     //   541: astore 21
/*      */     //   543: aload 8
/*      */     //   545: ifnull +18 -> 563
/*      */     //   548: aload 8
/*      */     //   550: invokeinterface 1259 1 0
/*      */     //   555: aconst_null
/*      */     //   556: astore 8
/*      */     //   558: goto +5 -> 563
/*      */     //   561: astore 22
/*      */     //   563: aload 7
/*      */     //   565: ifnull +18 -> 583
/*      */     //   568: aload 7
/*      */     //   570: invokeinterface 1260 1 0
/*      */     //   575: aconst_null
/*      */     //   576: astore 7
/*      */     //   578: goto +5 -> 583
/*      */     //   581: astore 22
/*      */     //   583: aload 6
/*      */     //   585: ifnull +18 -> 603
/*      */     //   588: aload 6
/*      */     //   590: invokeinterface 115 1 0
/*      */     //   595: aconst_null
/*      */     //   596: astore 6
/*      */     //   598: goto +5 -> 603
/*      */     //   601: astore 22
/*      */     //   603: aload 21
/*      */     //   605: athrow
/*      */     //   606: aload 8
/*      */     //   608: ifnull +18 -> 626
/*      */     //   611: aload 8
/*      */     //   613: invokeinterface 1259 1 0
/*      */     //   618: aconst_null
/*      */     //   619: astore 8
/*      */     //   621: goto +5 -> 626
/*      */     //   624: astore 22
/*      */     //   626: aload 7
/*      */     //   628: ifnull +18 -> 646
/*      */     //   631: aload 7
/*      */     //   633: invokeinterface 1260 1 0
/*      */     //   638: aconst_null
/*      */     //   639: astore 7
/*      */     //   641: goto +5 -> 646
/*      */     //   644: astore 22
/*      */     //   646: aload 6
/*      */     //   648: ifnull +18 -> 666
/*      */     //   651: aload 6
/*      */     //   653: invokeinterface 115 1 0
/*      */     //   658: aconst_null
/*      */     //   659: astore 6
/*      */     //   661: goto +5 -> 666
/*      */     //   664: astore 22
/*      */     //   666: aload 9
/*      */     //   668: areturn
/*      */     // Line number table:
/*      */     //   Java source line #1141	-> byte code offset #0
/*      */     //   Java source line #1142	-> byte code offset #3
/*      */     //   Java source line #1143	-> byte code offset #6
/*      */     //   Java source line #1145	-> byte code offset #9
/*      */     //   Java source line #1146	-> byte code offset #12
/*      */     //   Java source line #1148	-> byte code offset #21
/*      */     //   Java source line #1149	-> byte code offset #25
/*      */     //   Java source line #1150	-> byte code offset #29
/*      */     //   Java source line #1153	-> byte code offset #35
/*      */     //   Java source line #1156	-> byte code offset #44
/*      */     //   Java source line #1157	-> byte code offset #53
/*      */     //   Java source line #1158	-> byte code offset #60
/*      */     //   Java source line #1159	-> byte code offset #65
/*      */     //   Java source line #1160	-> byte code offset #85
/*      */     //   Java source line #1162	-> byte code offset #90
/*      */     //   Java source line #1164	-> byte code offset #93
/*      */     //   Java source line #1166	-> byte code offset #100
/*      */     //   Java source line #1167	-> byte code offset #110
/*      */     //   Java source line #1168	-> byte code offset #117
/*      */     //   Java source line #1170	-> byte code offset #125
/*      */     //   Java source line #1172	-> byte code offset #128
/*      */     //   Java source line #1174	-> byte code offset #135
/*      */     //   Java source line #1175	-> byte code offset #145
/*      */     //   Java source line #1176	-> byte code offset #152
/*      */     //   Java source line #1180	-> byte code offset #157
/*      */     //   Java source line #1182	-> byte code offset #168
/*      */     //   Java source line #1184	-> byte code offset #171
/*      */     //   Java source line #1186	-> byte code offset #180
/*      */     //   Java source line #1187	-> byte code offset #191
/*      */     //   Java source line #1188	-> byte code offset #219
/*      */     //   Java source line #1190	-> byte code offset #230
/*      */     //   Java source line #1191	-> byte code offset #236
/*      */     //   Java source line #1192	-> byte code offset #243
/*      */     //   Java source line #1193	-> byte code offset #250
/*      */     //   Java source line #1194	-> byte code offset #263
/*      */     //   Java source line #1196	-> byte code offset #272
/*      */     //   Java source line #1197	-> byte code offset #282
/*      */     //   Java source line #1198	-> byte code offset #292
/*      */     //   Java source line #1199	-> byte code offset #329
/*      */     //   Java source line #1201	-> byte code offset #362
/*      */     //   Java source line #1204	-> byte code offset #384
/*      */     //   Java source line #1190	-> byte code offset #404
/*      */     //   Java source line #1207	-> byte code offset #418
/*      */     //   Java source line #1209	-> byte code offset #427
/*      */     //   Java source line #1210	-> byte code offset #436
/*      */     //   Java source line #1211	-> byte code offset #447
/*      */     //   Java source line #1213	-> byte code offset #461
/*      */     //   Java source line #1214	-> byte code offset #463
/*      */     //   Java source line #1215	-> byte code offset #501
/*      */     //   Java source line #1216	-> byte code offset #503
/*      */     //   Java source line #1218	-> byte code offset #541
/*      */     //   Java source line #1219	-> byte code offset #543
/*      */     //   Java source line #1220	-> byte code offset #563
/*      */     //   Java source line #1221	-> byte code offset #583
/*      */     //   Java source line #1222	-> byte code offset #603
/*      */     //   Java source line #1219	-> byte code offset #606
/*      */     //   Java source line #1220	-> byte code offset #626
/*      */     //   Java source line #1221	-> byte code offset #646
/*      */     //   Java source line #1223	-> byte code offset #666
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	signature
/*      */     //   0	669	0	this	RedencionDAO
/*      */     //   0	669	1	telefono	String
/*      */     //   0	669	2	region	int
/*      */     //   0	669	3	cuenta	String
/*      */     //   0	669	4	endPoint	String
/*      */     //   0	669	5	usuario	String
/*      */     //   1	659	6	connection	Connection
/*      */     //   4	636	7	statement	PreparedStatement
/*      */     //   7	613	8	resultSet	ResultSet
/*      */     //   10	657	9	hashtable	java.util.Hashtable<String, Object>
/*      */     //   19	434	10	mensajeTO	MensajeTO
/*      */     //   23	419	11	productos	String
/*      */     //   27	316	12	cudis	String
/*      */     //   33	377	13	planes	com.telcel.crm.scrapy.beans.beneficios.PlanTO[]
/*      */     //   42	94	14	proxy	AplicaBeneficioProxy
/*      */     //   461	35	14	e	SQLException
/*      */     //   501	35	14	e	Exception
/*      */     //   51	19	15	properties	Catalogo
/*      */     //   83	3	16	aplicaCudi2	boolean
/*      */     //   91	20	17	cudisResponse	Cudi2TO
/*      */     //   126	20	17	cudisResponse	CudisTO
/*      */     //   169	85	17	planDescuentoTO	com.telcel.crm.scrapy.beans.beneficios.PlanTO
/*      */     //   217	5	18	sQuery	String
/*      */     //   231	177	19	i	int
/*      */     //   290	85	20	planDescuento	String
/*      */     //   402	1	20	localException1	Exception
/*      */     //   541	63	21	localObject	Object
/*      */     //   561	1	22	localException2	Exception
/*      */     //   581	1	22	localException3	Exception
/*      */     //   601	1	22	localException4	Exception
/*      */     //   624	1	22	localException5	Exception
/*      */     //   644	1	22	localException6	Exception
/*      */     //   664	1	22	localException7	Exception
/*      */     // Exception table:
/*      */     //   from	to	target	type
/*      */     //   389	399	402	java/lang/Exception
/*      */     //   35	458	461	java/sql/SQLException
/*      */     //   35	458	501	java/lang/Exception
/*      */     //   35	541	541	finally
/*      */     //   548	558	561	java/lang/Exception
/*      */     //   568	578	581	java/lang/Exception
/*      */     //   588	598	601	java/lang/Exception
/*      */     //   611	621	624	java/lang/Exception
/*      */     //   631	641	644	java/lang/Exception
/*      */     //   651	661	664	java/lang/Exception
/*      */   }
/*      */ }


/* Location:              /opt/trabajo/claro/Guatemala/IS/CO/ClaroClub.ear!/ClaroClubEJB.jar!/com/claro/dao/RedencionDAO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */