/*     */ package com.claro.dao;
/*     */ 
/*     */ import com.claro.exception.CAException;
/*     */ import com.claro.transfer.MensajeTO;
/*     */ import com.claro.transfer.MotivoTO;
/*     */ import com.claro.transfer.RetencionTO;
/*     */ import com.claro.transfer.TelefonoTO;
/*     */ import com.claro.transfer.UsuarioTO;
/*     */ import com.claro.util.ServiceLocator;
/*     */ import com.claro.util.Utils;
/*     */ import java.sql.Connection;
/*     */ import java.sql.PreparedStatement;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Calendar;
/*     */ import org.apache.log4j.Logger;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RetencionDAO
/*     */ {
/*  25 */   protected final Logger logger = Logger.getLogger("loggerCirculoAzul");
/*  26 */   protected final Logger error = Logger.getLogger("loggerCirculoAzulError");
/*     */   private String schema_database;
/*     */   
/*     */   public RetencionDAO()
/*     */   {
/*     */     try {
/*  32 */       this.schema_database = ServiceLocator.getInstance().getVariable(ServiceLocator.schema_database);
/*     */     } catch (Exception e) {
/*  34 */       this.error.error("RetencionDAO", e);
/*     */     }
/*     */   }
/*     */   
/*     */   public RetencionTO calculaValorCupon(Double dARPU, int iMeses, int iBajas, int iPcAutor)
/*     */     throws CAException
/*     */   {
/*  41 */     int iARPU = 0;
/*  42 */     int iPuntosARPU = 0;int iValorPARPU = 0;
/*  43 */     int iPuntosAntig = 0;int iValorPAntig = 0;
/*  44 */     int iPuntosCob = 0;int iValorPCob = 0;
/*  45 */     iARPU = (int)Math.floor(dARPU.doubleValue() + 0.5D);
/*  46 */     Connection conn = null;
/*  47 */     PreparedStatement stmt = null;
/*  48 */     ResultSet rset = null;
/*  49 */     String sQuery = "";
/*  50 */     RetencionTO retencionTO = new RetencionTO();
/*  51 */     MensajeTO mensajeTO = new MensajeTO();
/*     */     
/*     */     try
/*     */     {
/*  55 */       conn = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
/*  56 */       sQuery = " SELECT puntos, Porcentaje FROM " + this.schema_database + "PTO_CTLPARCERT " + 
/*  57 */         "  WHERE tipoparm = 'ARPU' AND Rango_Inf <= ? AND Rango_Sup >= ?";
/*  58 */       stmt = conn.prepareStatement(sQuery);
/*     */       
/*  60 */       stmt.setInt(1, iARPU);
/*  61 */       stmt.setInt(2, iARPU);
/*     */       
/*  63 */       rset = stmt.executeQuery();
/*  64 */       if (rset.next()) {
/*  65 */         iPuntosARPU = rset.getInt(1);
/*  66 */         iValorPARPU = rset.getInt(2);
/*     */       }
/*     */       
/*  69 */       retencionTO.setPorcARPU(iValorPARPU * iPuntosARPU / 100);
/*     */       
/*     */ 
/*  72 */       sQuery = " SELECT puntos, Porcentaje FROM " + this.schema_database + "PTO_CTLPARCERT " + 
/*  73 */         "  WHERE tipoparm = 'ANTIG' AND Rango_Inf <= ? AND Rango_Sup >= ?";
/*  74 */       stmt = conn.prepareStatement(sQuery);
/*     */       
/*  76 */       stmt.setInt(1, iMeses);
/*  77 */       stmt.setInt(2, iMeses);
/*     */       
/*  79 */       rset = stmt.executeQuery();
/*  80 */       if (rset.next()) {
/*  81 */         iPuntosAntig = rset.getInt(1);
/*  82 */         iValorPAntig = rset.getInt(2);
/*     */       }
/*  84 */       retencionTO.setPorcAntig(iPuntosAntig * iValorPAntig / 100);
/*     */       
/*     */ 
/*     */ 
/*  88 */       sQuery = " SELECT puntos, Porcentaje FROM " + this.schema_database + "PTO_CTLPARCERT " + 
/*  89 */         " WHERE tipoparm = 'BCOB' AND Rango_Inf <= ? AND Rango_Sup >= ?";
/*  90 */       stmt = conn.prepareStatement(sQuery);
/*     */       
/*  92 */       stmt.setInt(1, iBajas);
/*  93 */       stmt.setInt(2, iBajas);
/*     */       
/*  95 */       rset = stmt.executeQuery();
/*  96 */       if (rset.next()) {
/*  97 */         iPuntosCob = rset.getInt(1);
/*  98 */         iValorPCob = rset.getInt(2);
/*     */       }
/* 100 */       retencionTO.setPorcCob(iPuntosCob * iValorPCob / 100);
/*     */       
/*     */ 
/* 103 */       retencionTO.setValorCupon(500 * (retencionTO.getPorcARPU() + 
/* 104 */         retencionTO.getPorcAntig() + retencionTO.getPorcCob()) / 100);
/*     */       
/* 106 */       if (iPcAutor == 0) retencionTO.setVCentifextra("0"); else {
/* 107 */         retencionTO.setVCentifextra(String.valueOf(153 * iPcAutor / 100 * (retencionTO.getPorcARPU() + retencionTO.getPorcAntig() + retencionTO.getPorcCob()) / 100));
/*     */       }
/* 109 */       mensajeTO.setIdMensaje(0);
/* 110 */       retencionTO.setMensajeTO(mensajeTO);
/* 111 */       return retencionTO;
/*     */     } catch (Exception e) {
/*     */       RetencionTO localRetencionTO1;
/* 114 */       mensajeTO.setIdMensaje(1);
/* 115 */       mensajeTO.setMensaje("[calculaValorCupon] Error: " + e.getMessage());
/* 116 */       retencionTO.setMensajeTO(mensajeTO);
/* 117 */       return retencionTO;
/*     */     } finally {
/* 119 */       try { if (rset != null) { rset.close();rset = null; } } catch (Exception localException7) {}
/* 120 */       try { if (stmt != null) { stmt.close();stmt = null; } } catch (Exception localException8) {}
/* 121 */       try { if (conn != null) { conn.close();conn = null;
/*     */         }
/*     */       } catch (Exception localException9) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public MensajeTO consultaCertificado(String cuenta, String telefono) throws CAException {
/* 128 */     Connection connection = null;
/* 129 */     MensajeTO mensajeTO = null;
/* 130 */     PreparedStatement stmt = null;
/* 131 */     ResultSet rset = null;
/*     */     
/* 133 */     String sCondicion = "";
/* 134 */     if ((cuenta == null) || (cuenta.equals(""))) {
/* 135 */       sCondicion = " WHERE linea = ?";
/*     */     } else {
/* 137 */       sCondicion = " WHERE Cuenta = ?";
/*     */     }
/*     */     
/* 140 */     String sQuery = " SELECT Fechaoper, Idusuario, COMENTARIO1 FROM " + this.schema_database + "PTO_TBLCERTIFICADOS " + sCondicion + 
/* 141 */       "    AND ESTATUS = 'A'";
/*     */     try {
/* 143 */       connection = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
/* 144 */       stmt = connection.prepareStatement(sQuery);
/* 145 */       if ((cuenta == null) || (cuenta.equals(""))) {
/* 146 */         stmt.setString(1, telefono);
/*     */       } else {
/* 148 */         stmt.setString(1, cuenta);
/*     */       }
/* 150 */       rset = stmt.executeQuery();
/*     */       
/* 152 */       mensajeTO = new MensajeTO();
/* 153 */       Calendar hoy = Calendar.getInstance();
/*     */       MensajeTO localMensajeTO1;
/* 155 */       while (rset.next()) {
/* 156 */         Calendar fechaOperacion = Calendar.getInstance();
/*     */         
/* 158 */         java.sql.Date date = rset.getDate("FECHAOPER");
/*     */         
/* 160 */         if (date != null) {
/* 161 */           fechaOperacion.setTime(date);
/*     */           
/* 163 */           long dias = Utils.diferenciaEnDias(hoy, fechaOperacion);
/* 164 */           if (dias < 365L)
/*     */           {
/* 166 */             String fecha = String.valueOf(fechaOperacion.get(5)) + "-" + 
/* 167 */               String.valueOf(fechaOperacion.get(2) + 1) + "-" + 
/* 168 */               String.valueOf(fechaOperacion.get(1));
/* 169 */             String mensaje = "La linea tiene un Certificado de Lealtad generado el " + fecha + ", por lo que no puede ofrecerse nuevamente hasta cumplido un año.";
/*     */             
/* 171 */             mensajeTO.setIdMensaje(1);
/* 172 */             mensajeTO.setMensaje(mensaje);
/* 173 */             return mensajeTO;
/*     */           }
/*     */         }
/*     */       }
/* 177 */       mensajeTO.setIdMensaje(0);
/* 178 */       return mensajeTO;
/*     */     }
/*     */     catch (Exception e) {
/* 181 */       throw new CAException(1, e.getMessage());
/*     */     } finally {
/*     */       try {
/* 184 */         if (rset != null) { rset.close();rset = null; }
/* 185 */         if (stmt != null) { stmt.close();stmt = null; }
/* 186 */         if (connection != null) { connection.close();connection = null;
/*     */         }
/*     */       }
/*     */       catch (Exception localException3) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public MensajeTO validaVigenciaCertificado(String cuenta, String secuencia) {
/* 194 */     Connection conn = null;
/* 195 */     PreparedStatement stmt = null;
/* 196 */     ResultSet rset = null;
/* 197 */     String sQuery = null;
/* 198 */     MensajeTO mensajeTO = null;
/*     */     try
/*     */     {
/* 201 */       conn = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
/* 202 */       sQuery = " SELECT Fechaoper, Idusuario, COMENTARIO1 FROM " + this.schema_database + "PTO_TBLCERTIFICADOS " + 
/* 203 */         "  WHERE Cuenta = ? AND Secuencia = ?" + 
/* 204 */         " \t AND ESTATUS in ('A','L')";
/* 205 */       stmt = conn.prepareStatement(sQuery);
/* 206 */       stmt.setString(1, cuenta);
/* 207 */       stmt.setString(2, secuencia);
/* 208 */       rset = stmt.executeQuery();
/*     */       
/* 210 */       Calendar hoy = Calendar.getInstance();
/*     */       
/* 212 */       while (rset.next()) {
/* 213 */         Calendar fechaOperacion = Calendar.getInstance();
/* 214 */         java.sql.Date date = rset.getDate(1);
/*     */         
/* 216 */         if (date != null) {
/* 217 */           fechaOperacion.setTime(date);
/*     */           
/* 219 */           long dias = Utils.diferenciaEnDias(hoy, fechaOperacion);
/* 220 */           if (dias < 365L)
/*     */           {
/* 222 */             String fecha = String.valueOf(fechaOperacion.get(5)) + "-" + 
/* 223 */               String.valueOf(fechaOperacion.get(2) + 1) + "-" + 
/* 224 */               String.valueOf(fechaOperacion.get(1));
/* 225 */             String mensaje = "La linea tiene un Certificado de Lealtad generado el " + fecha + ", por lo que no puede ofrecerse nuevamente hasta cumplido un año.";
/*     */             
/* 227 */             mensajeTO = new MensajeTO();
/* 228 */             mensajeTO.setIdMensaje(1);
/* 229 */             mensajeTO.setMensaje(mensaje);
/* 230 */             return mensajeTO;
/*     */           }
/*     */         }
/*     */       }
/* 234 */       mensajeTO = new MensajeTO();
/* 235 */       mensajeTO.setIdMensaje(0);
/* 236 */       return mensajeTO;
/*     */     } catch (Exception e) { MensajeTO localMensajeTO1;
/* 238 */       mensajeTO = new MensajeTO();
/* 239 */       mensajeTO.setIdMensaje(1);
/* 240 */       mensajeTO.setMensaje(e.getMessage());
/* 241 */       return mensajeTO;
/*     */     } finally {
/* 243 */       try { if (rset != null) { rset.close();rset = null; } } catch (Exception localException10) {}
/* 244 */       try { if (stmt != null) { stmt.close();stmt = null; } } catch (Exception localException11) {}
/* 245 */       try { if (conn != null) { conn.close();conn = null;
/*     */         }
/*     */       }
/*     */       catch (Exception localException12) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public RetencionTO consultaRetencion(String sCta, String sSec) throws CAException {
/* 253 */     PreparedStatement stmt = null;
/* 254 */     ResultSet rset = null;
/* 255 */     Connection conn = null;
/* 256 */     RetencionTO retencionTO = null;
/*     */     
/* 258 */     String sQuery = "SELECT LINEA, CUENTA, SECUENCIA, FECHAOPER, FOLIO, VCERTIF, VCENTIFEXTRA, MOTIVO, FCADUCA, ESTATUS   FROM  " + 
/* 259 */       this.schema_database + "PTO_TBLCERTIFICADOS a  " + 
/* 260 */       " WHERE CUENTA=? AND SECUENCIA =? AND ESTATUS= 'A'" + 
/* 261 */       " ORDER BY FOLIO DESC ";
/*     */     try {
/* 263 */       conn = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
/* 264 */       stmt = conn.prepareStatement(sQuery);
/* 265 */       stmt.setString(1, sCta);
/* 266 */       stmt.setString(2, sSec);
/*     */       
/* 268 */       rset = stmt.executeQuery();
/* 269 */       retencionTO = new RetencionTO();
/* 270 */       RetencionTO localRetencionTO1; if (rset.next()) {
/* 271 */         retencionTO.setTelefono(rset.getString(1));
/* 272 */         retencionTO.setCuenta(rset.getString(2));
/* 273 */         retencionTO.setFechaOperacion(rset.getString(4));
/* 274 */         retencionTO.setFolio(rset.getString(5));
/* 275 */         retencionTO.setVCertif(rset.getString(6));
/* 276 */         retencionTO.setVCentifextra(rset.getString(7));
/* 277 */         retencionTO.setMotivo(rset.getString(8));
/* 278 */         retencionTO.setFechaCaduca(rset.getString(9));
/* 279 */         retencionTO.setEstatus(rset.getString(10));
/*     */         
/* 281 */         MensajeTO mensajeTO = new MensajeTO();
/* 282 */         mensajeTO.setIdMensaje(0);
/* 283 */         retencionTO.setMensajeTO(mensajeTO);
/*     */         
/* 285 */         return retencionTO;
/*     */       }
/* 287 */       MensajeTO mensajeTO = new MensajeTO();
/* 288 */       mensajeTO.setIdMensaje(1);
/* 289 */       mensajeTO.setMensaje("No existen Certificados de Lealtad generados para esta cuenta.");
/* 290 */       retencionTO.setMensajeTO(mensajeTO);
/*     */       
/* 292 */       return retencionTO;
/*     */     }
/*     */     catch (Exception e) {
/* 295 */       throw new CAException(0, "[Consulta Retencion]" + e.getMessage());
/*     */     } finally {
/*     */       try {
/* 298 */         if (rset != null) { rset.close();rset = null; }
/* 299 */         if (stmt != null) { stmt.close();stmt = null; }
/* 300 */         if (conn != null) { conn.close();conn = null;
/*     */         }
/*     */       }
/*     */       catch (Exception localException3) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public MensajeTO almacenaCertificado(TelefonoTO telefonoTO, UsuarioTO usuarioTO, RetencionTO retencionTO, String sNMeses, String sMotivo, String sComenta, String sComenta2, String sCuentaAnt, String sRegionAnt, String sMesesAnt, String sTipo) throws CAException
/*     */   {
/* 309 */     Connection conn = null;
/* 310 */     Connection puntosConnection = null;
/* 311 */     PreparedStatement stmt = null;
/* 312 */     ResultSet rset = null;
/* 313 */     MensajeTO mensajeTO = null;
/* 314 */     int iValorCert = 0;
/*     */     
/* 316 */     if (!sTipo.equals("CE")) iValorCert = retencionTO.getValorCupon(); else {
/* 317 */       iValorCert = retencionTO.getValorCupon() + Integer.parseInt(retencionTO.getVCentifextra());
/*     */     }
/*     */     
/*     */     try
/*     */     {
/* 322 */       mensajeTO = validaVigenciaCertificado(telefonoTO.getCuenta(), telefonoTO.getSecuencia());
/* 323 */       if (mensajeTO.getIdMensaje() != 0) {
/* 324 */         return mensajeTO;
/*     */       }
/*     */       
/*     */ 
/* 328 */       PuntosDAO puntosDAO = new PuntosDAO();
/* 329 */       puntosConnection = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
/*     */       
/* 331 */       puntosConnection.setAutoCommit(false);
/* 332 */       long fechaTransaccion = System.currentTimeMillis();
/* 333 */       String referencia = "'Se extiende certificado de Lealtad por $" + iValorCert + ".00 " + usuarioTO.getIdUsuario() + "'";
/*     */       try
/*     */       {
/* 336 */         mensajeTO = puntosDAO.insertaDetalle(puntosConnection, fechaTransaccion, referencia, 47, 0, 
/* 337 */           null, telefonoTO.getCuenta(), Integer.parseInt(telefonoTO.getSecuencia()), telefonoTO.getTelefono(), usuarioTO.getIdUsuario());
/*     */         
/* 339 */         if (mensajeTO.getIdMensaje() != 0) {
/* 340 */           puntosConnection.rollback();
/* 341 */           return mensajeTO;
/*     */         }
/*     */       } catch (Exception e) {
/* 344 */         mensajeTO.setIdMensaje(1);
/* 345 */         mensajeTO.setMensaje(e.getMessage());
/* 346 */         puntosConnection.rollback();
/* 347 */         return mensajeTO;
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 353 */       String comentario = "  'CIR - SE GENERA CERTIFICADO POR  " + iValorCert + " PESOS. POR RETENCION  A PET. DE " + usuarioTO.getIdUsuario() + "'";
/*     */       try
/*     */       {
/* 356 */         mensajeTO = puntosDAO.insertaComentarioTMP(puntosConnection, telefonoTO.getRegion(), telefonoTO.getCuenta(), usuarioTO.getIdUsuario(), 
/* 357 */           fechaTransaccion, comentario);
/*     */         
/* 359 */         if (mensajeTO.getIdMensaje() != 0) {
/* 360 */           puntosConnection.rollback();
/* 361 */           return mensajeTO;
/*     */         }
/*     */       } catch (Exception e) {
/* 364 */         mensajeTO.setIdMensaje(1);
/* 365 */         mensajeTO.setMensaje(e.getMessage());
/* 366 */         puntosConnection.rollback();
/* 367 */         return mensajeTO;
/*     */       }
/*     */       
/*     */ 
/* 371 */       mensajeTO = insertaCertificado(telefonoTO, usuarioTO, retencionTO, sNMeses, sMotivo, sComenta, 
/* 372 */         sComenta2, sCuentaAnt, sRegionAnt, sMesesAnt, sTipo, conn, fechaTransaccion);
/*     */       
/* 374 */       if (mensajeTO.getIdMensaje() != 0) {
/* 375 */         puntosConnection.rollback();
/* 376 */         return mensajeTO;
/*     */       }
/* 378 */       puntosConnection.commit();
/* 379 */       return mensajeTO;
/*     */     } catch (Exception e) {
/*     */       MensajeTO localMensajeTO1;
/* 382 */       mensajeTO.setIdMensaje(1);
/* 383 */       mensajeTO.setMensaje("[almacenaCertificado] Error: " + e.getMessage());
/* 384 */       try { puntosConnection.rollback();
/* 385 */       } catch (SQLException se) { mensajeTO.setIdMensaje(1);
/* 386 */         mensajeTO.setMensaje(e.getMessage());
/* 387 */         return mensajeTO;
/*     */       }
/* 389 */       return mensajeTO;
/*     */     }
/*     */     finally {
/* 392 */       if (rset != null) try { rset.close();rset = null; } catch (Exception localException28) {}
/* 393 */       if (stmt != null) try { stmt.close();stmt = null; } catch (Exception localException29) {}
/* 394 */       if (conn != null) try { conn.close();conn = null;
/*     */         }
/*     */         catch (Exception localException30) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public MensajeTO validaImpresion(String sFolio, java.util.Date dfechaM2K) throws CAException {
/* 401 */     Connection conn = null;
/* 402 */     PreparedStatement stmtCertificado = null;
/* 403 */     PreparedStatement stmtRetencion = null;
/* 404 */     ResultSet rsetCertificado = null;
/* 405 */     ResultSet rsetRedencion = null;
/* 406 */     MensajeTO mensajeTO = null;
/*     */     
/*     */ 
/*     */ 
/* 410 */     String sQueryCertificado = "select cuenta,secuencia,fechaoper from " + this.schema_database + "PTO_TBLCERTIFICADOS a " + 
/* 411 */       " where folio = ?";
/*     */     
/* 413 */     String sQueryRedencion = "SELECT b.fechaoper AS FECHAOPER  FROM  " + 
/* 414 */       this.schema_database + "PTO_TBLREDENCION b " + 
/* 415 */       " WHERE b.cuenta=? and b.secuencia=? and" + 
/* 416 */       " b.fechaoper =? and b.tiporeden = 'C' AND b.Estatus = 'A'";
/*     */     try
/*     */     {
/* 419 */       conn = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
/* 420 */       stmtCertificado = conn.prepareStatement(sQueryCertificado);
/*     */       
/* 422 */       stmtCertificado.setString(1, sFolio);
/*     */       
/* 424 */       rsetCertificado = stmtCertificado.executeQuery();
/* 425 */       boolean existeRetencion = false;
/* 426 */       mensajeTO = new MensajeTO();
/*     */       MensajeTO localMensajeTO1;
/* 428 */       while (rsetCertificado.next()) {
/* 429 */         String cuenta = rsetCertificado.getString("cuenta");
/* 430 */         String secuencia = rsetCertificado.getString("secuencia");
/* 431 */         java.sql.Date fechaoper = rsetCertificado.getDate("fechaoper");
/*     */         
/* 433 */         stmtRetencion = conn.prepareStatement(sQueryRedencion);
/* 434 */         stmtRetencion.setString(1, cuenta);
/* 435 */         stmtRetencion.setString(2, secuencia);
/* 436 */         stmtRetencion.setDate(3, fechaoper);
/*     */         
/* 438 */         rsetRedencion = stmtRetencion.executeQuery();
/*     */         
/* 440 */         if (rsetRedencion.next()) {
/* 441 */           existeRetencion = true;
/* 442 */           Calendar fechaOperacion = Calendar.getInstance();
/* 443 */           fechaOperacion.setTime(rsetRedencion.getDate(1));
/*     */           
/* 445 */           Calendar fechaHoy = Calendar.getInstance();
/*     */           
/* 447 */           Calendar fechaM2K = Calendar.getInstance();
/* 448 */           fechaM2K.setTime(dfechaM2K);
/*     */           
/* 450 */           if ((Utils.diferenciaEnDias(fechaHoy, fechaM2K) != 0L) || (Utils.diferenciaEnDias(fechaOperacion, fechaHoy) != 0L)) {
/* 451 */             mensajeTO.setIdMensaje(1);
/* 452 */             mensajeTO.setMensaje("Para poder imprimir el Certificado, las fechas de adendum en M2K y la redencion de Puntos deben coincidir con el dia actual.");
/* 453 */             return mensajeTO;
/*     */           }
/*     */         }
/*     */       }
/*     */       
/* 458 */       if (!existeRetencion) {
/* 459 */         mensajeTO.setIdMensaje(1);
/* 460 */         mensajeTO.setMensaje("Para poder imprimir el Certificado, debe existir una Redencion de Puntos con firma de adendum correspondiente a la fecha actual.");
/* 461 */         return mensajeTO;
/*     */       }
/* 463 */       mensajeTO.setIdMensaje(0);
/* 464 */       return mensajeTO;
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 468 */       throw new CAException(0, "[validaImpresion]" + e.getMessage());
/*     */     } finally {
/*     */       try {
/* 471 */         if (rsetCertificado != null) { rsetCertificado.close();rsetCertificado = null; }
/* 472 */         if (stmtCertificado != null) { stmtCertificado.close();stmtCertificado = null; }
/* 473 */         if (rsetRedencion != null) { rsetRedencion.close();rsetRedencion = null; }
/* 474 */         if (stmtRetencion != null) { stmtRetencion.close();stmtRetencion = null; }
/* 475 */         if (conn != null) { conn.close();conn = null;
/*     */         }
/* 477 */       } catch (Exception e) { throw new CAException(0, "[validaImpresion]" + e.getMessage());
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public MensajeTO cancelarCertificado(String sUsuario, String sModo, String sFolio, String sComenta) throws CAException
/*     */   {
/* 484 */     String sQuery = "";String sUpdate = "";
/* 485 */     Connection conn = null;
/* 486 */     PreparedStatement stmt = null;
/* 487 */     PreparedStatement stmt1 = null;
/* 488 */     ResultSet rset = null;
/* 489 */     long nDias = 0L;
/* 490 */     String sCta = "";String sTel = "";String sSec = "";
/* 491 */     MensajeTO mensajeTO = null;
/*     */     
/* 493 */     if (sModo.equals("1")) { sModo = "L";
/* 494 */     } else if (sModo.equals("0")) sModo = "C";
/*     */     try
/*     */     {
/* 497 */       conn = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
/* 498 */       sQuery = " SELECT a.fechaoper, CUENTA, SECUENCIA, LINEA   FROM " + 
/* 499 */         this.schema_database + "PTO_TBLCERTIFICADOS a " + 
/* 500 */         " WHERE FOLIO = ?";
/*     */       
/* 502 */       stmt = conn.prepareStatement(sQuery);
/* 503 */       stmt.setString(1, sFolio);
/*     */       
/* 505 */       rset = stmt.executeQuery();
/* 506 */       if (rset.next())
/*     */       {
/* 508 */         Calendar fechaHoy = Calendar.getInstance();
/* 509 */         Calendar fechaOperacion = Calendar.getInstance();
/* 510 */         fechaOperacion.setTime(rset.getDate(1));
/* 511 */         nDias = Utils.diferenciaEnDias(fechaHoy, fechaOperacion);
/* 512 */         sCta = rset.getString(2);
/* 513 */         sSec = rset.getString(3);
/* 514 */         sTel = rset.getString(4);
/*     */       }
/* 516 */       if (nDias <= 5L)
/*     */       {
/* 518 */         long fechaTransaccion = System.currentTimeMillis();
/*     */         
/* 520 */         sUpdate = " UPDATE " + this.schema_database + "PTO_TBLCERTIFICADOS " + 
/* 521 */           " SET Fechalib = ?," + 
/* 522 */           " IdUsuariolib = ?,Estatus = ?," + 
/* 523 */           " ComntLib = ?" + 
/* 524 */           " WHERE folio = ?";
/* 525 */         stmt1 = conn.prepareStatement(sUpdate);
/* 526 */         stmt1.setDate(1, new java.sql.Date(fechaTransaccion));
/* 527 */         stmt1.setString(2, sUsuario);
/* 528 */         stmt1.setString(3, sModo);
/* 529 */         stmt1.setString(4, sComenta);
/* 530 */         stmt1.setString(5, sFolio);
/*     */         
/* 532 */         stmt1.executeUpdate();
/*     */         
/*     */ 
/* 535 */         sUpdate = "INSERT INTO " + this.schema_database + "PTO_TBLMSTRDETALLE (Cuenta, Secuencia, Linea, FechaFac, FechaOperacion, IdMovto," + 
/* 536 */           " IdUsuario, NumPuntos, NumPuntosExc, TotAjustes, IdBonoProm, Referencia) " + 
/* 537 */           " VALUES(?,?,?,?,?, 50," + 
/* 538 */           "?,0,0,0,null,?)";
/*     */         
/* 540 */         stmt = conn.prepareStatement(sUpdate);
/*     */         
/*     */ 
/* 543 */         stmt.setString(1, sCta);
/* 544 */         stmt.setString(2, sSec);
/* 545 */         stmt.setString(3, sTel);
/* 546 */         stmt.setDate(4, new java.sql.Date(fechaTransaccion));
/* 547 */         stmt.setDate(5, new java.sql.Date(fechaTransaccion));
/* 548 */         stmt.setString(6, sUsuario);
/* 549 */         stmt.setString(7, "'Se cancela certificado de Lealtad folio: " + sFolio + "'");
/*     */         
/* 551 */         stmt.executeUpdate();
/* 552 */         mensajeTO = new MensajeTO();
/* 553 */         mensajeTO.setIdMensaje(0);
/* 554 */         mensajeTO.setMensaje("La cancelacion del Certificado de Lealtad, fue completada exitosamente.");
/* 555 */         return mensajeTO;
/*     */       }
/* 557 */       mensajeTO = new MensajeTO();
/* 558 */       mensajeTO.setIdMensaje(1);
/* 559 */       mensajeTO.setMensaje("No es posible cancelar el Certificado de Lealtad. El tiempo maximo para cancelar este tramite es de 5 dias.");
/* 560 */       return mensajeTO;
/*     */     }
/*     */     catch (Exception e) {
/*     */       MensajeTO localMensajeTO1;
/* 564 */       mensajeTO = new MensajeTO();
/* 565 */       mensajeTO.setIdMensaje(1);
/* 566 */       mensajeTO.setMensaje("Ocurrio un error al momento de actualzar el Certificado de Lealtad, por favor intente de nuevo.");
/*     */       
/* 568 */       return mensajeTO;
/*     */     } finally {
/*     */       try {
/* 571 */         if (stmt != null) { stmt.close();stmt = null; }
/* 572 */         if (stmt1 != null) { stmt1.close();stmt1 = null; }
/* 573 */         if (rset != null) { rset.close();rset = null; }
/* 574 */         if (conn != null) { conn.close();conn = null;
/*     */         }
/*     */       }
/*     */       catch (Exception localException4) {}
/*     */     }
/*     */   }
/*     */   
/*     */   /* Error */
/*     */   public MensajeTO insertaCertificado(TelefonoTO telefonoTO, UsuarioTO usuarioTO, RetencionTO retencionTO, String sNMeses, String sMotivo, String sComenta, String sComenta2, String sCuentaAnt, String sRegionAnt, String sMesesAnt, String sTipo, Connection connection, long fechaTransaccion)
/*     */     throws CAException
/*     */   {
/*     */     // Byte code:
/*     */     //   0: aconst_null
/*     */     //   1: astore 15
/*     */     //   3: aconst_null
/*     */     //   4: astore 16
/*     */     //   6: aconst_null
/*     */     //   7: astore 17
/*     */     //   9: aconst_null
/*     */     //   10: astore 18
/*     */     //   12: aload 12
/*     */     //   14: ifnull +10 -> 24
/*     */     //   17: aload 12
/*     */     //   19: astore 15
/*     */     //   21: goto +14 -> 35
/*     */     //   24: invokestatic 29	com/claro/util/ServiceLocator:getInstance	()Lcom/claro/util/ServiceLocator;
/*     */     //   27: getstatic 83	com/claro/util/ServiceLocator:jdbcCirculoAzul	Ljava/lang/String;
/*     */     //   30: invokevirtual 86	com/claro/util/ServiceLocator:getConnection	(Ljava/lang/String;)Ljava/sql/Connection;
/*     */     //   33: astore 15
/*     */     //   35: ldc 75
/*     */     //   37: astore 19
/*     */     //   39: ldc 75
/*     */     //   41: astore 20
/*     */     //   43: ldc 75
/*     */     //   45: astore 21
/*     */     //   47: aload_1
/*     */     //   48: invokevirtual 553	com/claro/transfer/TelefonoTO:getMobileTO	()Lcom/claro/transfer/MobileTO;
/*     */     //   51: invokevirtual 557	com/claro/transfer/MobileTO:getFecAddM2K	()Ljava/lang/String;
/*     */     //   54: ldc 75
/*     */     //   56: invokevirtual 221	java/lang/String:equals	(Ljava/lang/Object;)Z
/*     */     //   59: ifeq +11 -> 70
/*     */     //   62: ldc_w 562
/*     */     //   65: astore 20
/*     */     //   67: goto +12 -> 79
/*     */     //   70: aload_1
/*     */     //   71: invokevirtual 553	com/claro/transfer/TelefonoTO:getMobileTO	()Lcom/claro/transfer/MobileTO;
/*     */     //   74: invokevirtual 557	com/claro/transfer/MobileTO:getFecAddM2K	()Ljava/lang/String;
/*     */     //   77: astore 20
/*     */     //   79: aload_1
/*     */     //   80: invokevirtual 553	com/claro/transfer/TelefonoTO:getMobileTO	()Lcom/claro/transfer/MobileTO;
/*     */     //   83: invokevirtual 564	com/claro/transfer/MobileTO:getFecAltaUser	()Ljava/lang/String;
/*     */     //   86: ldc 75
/*     */     //   88: invokevirtual 221	java/lang/String:equals	(Ljava/lang/Object;)Z
/*     */     //   91: ifeq +11 -> 102
/*     */     //   94: ldc_w 562
/*     */     //   97: astore 21
/*     */     //   99: goto +12 -> 111
/*     */     //   102: aload_1
/*     */     //   103: invokevirtual 553	com/claro/transfer/TelefonoTO:getMobileTO	()Lcom/claro/transfer/MobileTO;
/*     */     //   106: invokevirtual 564	com/claro/transfer/MobileTO:getFecAltaUser	()Ljava/lang/String;
/*     */     //   109: astore 21
/*     */     //   111: aload 11
/*     */     //   113: ldc_w 345
/*     */     //   116: invokevirtual 221	java/lang/String:equals	(Ljava/lang/Object;)Z
/*     */     //   119: ifne +10 -> 129
/*     */     //   122: ldc -94
/*     */     //   124: astore 19
/*     */     //   126: goto +9 -> 135
/*     */     //   129: aload_3
/*     */     //   130: invokevirtual 350	com/claro/transfer/RetencionTO:getVCentifextra	()Ljava/lang/String;
/*     */     //   133: astore 19
/*     */     //   135: ldc 75
/*     */     //   137: astore 22
/*     */     //   139: new 90	java/lang/StringBuilder
/*     */     //   142: dup
/*     */     //   143: ldc_w 567
/*     */     //   146: invokespecial 94	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
/*     */     //   149: aload_0
/*     */     //   150: getfield 41	com/claro/dao/RetencionDAO:schema_database	Ljava/lang/String;
/*     */     //   153: invokevirtual 97	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   156: ldc_w 569
/*     */     //   159: invokevirtual 97	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   162: ldc_w 571
/*     */     //   165: invokevirtual 97	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   168: ldc_w 573
/*     */     //   171: invokevirtual 97	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   174: ldc_w 575
/*     */     //   177: invokevirtual 97	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   180: invokevirtual 105	java/lang/StringBuilder:toString	()Ljava/lang/String;
/*     */     //   183: astore 22
/*     */     //   185: aload 15
/*     */     //   187: aload 22
/*     */     //   189: invokeinterface 109 2 0
/*     */     //   194: astore 16
/*     */     //   196: aload 16
/*     */     //   198: iconst_1
/*     */     //   199: aload_1
/*     */     //   200: invokevirtual 359	com/claro/transfer/TelefonoTO:getCuenta	()Ljava/lang/String;
/*     */     //   203: invokeinterface 235 3 0
/*     */     //   208: aload_1
/*     */     //   209: invokevirtual 364	com/claro/transfer/TelefonoTO:getSecuencia	()Ljava/lang/String;
/*     */     //   212: ifnull +15 -> 227
/*     */     //   215: aload_1
/*     */     //   216: invokevirtual 364	com/claro/transfer/TelefonoTO:getSecuencia	()Ljava/lang/String;
/*     */     //   219: ldc 75
/*     */     //   221: invokevirtual 221	java/lang/String:equals	(Ljava/lang/Object;)Z
/*     */     //   224: ifeq +15 -> 239
/*     */     //   227: aload 16
/*     */     //   229: iconst_2
/*     */     //   230: iconst_0
/*     */     //   231: invokeinterface 115 3 0
/*     */     //   236: goto +18 -> 254
/*     */     //   239: aload 16
/*     */     //   241: iconst_2
/*     */     //   242: aload_1
/*     */     //   243: invokevirtual 364	com/claro/transfer/TelefonoTO:getSecuencia	()Ljava/lang/String;
/*     */     //   246: invokestatic 353	java/lang/Integer:parseInt	(Ljava/lang/String;)I
/*     */     //   249: invokeinterface 115 3 0
/*     */     //   254: aload 16
/*     */     //   256: iconst_3
/*     */     //   257: aload_1
/*     */     //   258: invokevirtual 399	com/claro/transfer/TelefonoTO:getTelefono	()Ljava/lang/String;
/*     */     //   261: invokeinterface 235 3 0
/*     */     //   266: aload 16
/*     */     //   268: iconst_4
/*     */     //   269: aload_2
/*     */     //   270: invokevirtual 392	com/claro/transfer/UsuarioTO:getIdUsuario	()Ljava/lang/String;
/*     */     //   273: invokeinterface 235 3 0
/*     */     //   278: aload 16
/*     */     //   280: iconst_5
/*     */     //   281: new 289	java/sql/Date
/*     */     //   284: dup
/*     */     //   285: lload 13
/*     */     //   287: invokespecial 523	java/sql/Date:<init>	(J)V
/*     */     //   290: invokeinterface 474 3 0
/*     */     //   295: aload 16
/*     */     //   297: bipush 6
/*     */     //   299: invokestatic 577	com/claro/util/Utils:generaFolio	()Ljava/lang/String;
/*     */     //   302: invokeinterface 235 3 0
/*     */     //   307: aload 16
/*     */     //   309: bipush 7
/*     */     //   311: aload_1
/*     */     //   312: invokevirtual 413	com/claro/transfer/TelefonoTO:getRegion	()I
/*     */     //   315: i2s
/*     */     //   316: invokeinterface 580 3 0
/*     */     //   321: aload 16
/*     */     //   323: bipush 8
/*     */     //   325: aload_2
/*     */     //   326: invokevirtual 584	com/claro/transfer/UsuarioTO:getPuntoVentaTO	()Lcom/claro/transfer/PuntoVentaTO;
/*     */     //   329: invokevirtual 588	com/claro/transfer/PuntoVentaTO:getPtoVenta	()Ljava/lang/String;
/*     */     //   332: invokeinterface 235 3 0
/*     */     //   337: aload 16
/*     */     //   339: bipush 9
/*     */     //   341: aload_1
/*     */     //   342: invokevirtual 593	com/claro/transfer/TelefonoTO:getSegmento	()Ljava/lang/String;
/*     */     //   345: invokeinterface 235 3 0
/*     */     //   350: aload 16
/*     */     //   352: bipush 10
/*     */     //   354: aload_1
/*     */     //   355: invokevirtual 596	com/claro/transfer/TelefonoTO:getPlan	()Ljava/lang/String;
/*     */     //   358: invokeinterface 235 3 0
/*     */     //   363: aload 20
/*     */     //   365: ifnonnull +16 -> 381
/*     */     //   368: aload 16
/*     */     //   370: bipush 11
/*     */     //   372: aconst_null
/*     */     //   373: invokeinterface 474 3 0
/*     */     //   378: goto +17 -> 395
/*     */     //   381: aload 16
/*     */     //   383: bipush 11
/*     */     //   385: aload 20
/*     */     //   387: invokestatic 599	java/sql/Date:valueOf	(Ljava/lang/String;)Ljava/sql/Date;
/*     */     //   390: invokeinterface 474 3 0
/*     */     //   395: aload 21
/*     */     //   397: ifnonnull +16 -> 413
/*     */     //   400: aload 16
/*     */     //   402: bipush 12
/*     */     //   404: aconst_null
/*     */     //   405: invokeinterface 474 3 0
/*     */     //   410: goto +17 -> 427
/*     */     //   413: aload 16
/*     */     //   415: bipush 12
/*     */     //   417: aload 21
/*     */     //   419: invokestatic 599	java/sql/Date:valueOf	(Ljava/lang/String;)Ljava/sql/Date;
/*     */     //   422: invokeinterface 474 3 0
/*     */     //   427: aload 4
/*     */     //   429: ifnull +13 -> 442
/*     */     //   432: aload 4
/*     */     //   434: ldc 75
/*     */     //   436: invokevirtual 221	java/lang/String:equals	(Ljava/lang/Object;)Z
/*     */     //   439: ifeq +16 -> 455
/*     */     //   442: aload 16
/*     */     //   444: bipush 13
/*     */     //   446: iconst_0
/*     */     //   447: invokeinterface 580 3 0
/*     */     //   452: goto +17 -> 469
/*     */     //   455: aload 16
/*     */     //   457: bipush 13
/*     */     //   459: aload 4
/*     */     //   461: invokestatic 601	java/lang/Short:parseShort	(Ljava/lang/String;)S
/*     */     //   464: invokeinterface 580 3 0
/*     */     //   469: aload 16
/*     */     //   471: bipush 14
/*     */     //   473: aload_3
/*     */     //   474: invokevirtual 607	com/claro/transfer/RetencionTO:getIIndAntig	()I
/*     */     //   477: i2s
/*     */     //   478: invokeinterface 580 3 0
/*     */     //   483: aload_1
/*     */     //   484: invokevirtual 553	com/claro/transfer/TelefonoTO:getMobileTO	()Lcom/claro/transfer/MobileTO;
/*     */     //   487: invokevirtual 610	com/claro/transfer/MobileTO:getFacturas	()Ljava/util/ArrayList;
/*     */     //   490: invokevirtual 614	java/util/ArrayList:iterator	()Ljava/util/Iterator;
/*     */     //   493: astore 23
/*     */     //   495: bipush 15
/*     */     //   497: istore 24
/*     */     //   499: goto +39 -> 538
/*     */     //   502: aload 23
/*     */     //   504: invokeinterface 620 1 0
/*     */     //   509: checkcast 625	com/claro/transfer/FacturaTO
/*     */     //   512: astore 25
/*     */     //   514: iload 24
/*     */     //   516: bipush 19
/*     */     //   518: if_icmpge +20 -> 538
/*     */     //   521: aload 16
/*     */     //   523: iload 24
/*     */     //   525: aload 25
/*     */     //   527: invokevirtual 627	com/claro/transfer/FacturaTO:getMonto	()D
/*     */     //   530: invokeinterface 630 4 0
/*     */     //   535: iinc 24 1
/*     */     //   538: aload 23
/*     */     //   540: invokeinterface 634 1 0
/*     */     //   545: ifne -43 -> 502
/*     */     //   548: aload 16
/*     */     //   550: bipush 19
/*     */     //   552: aload_1
/*     */     //   553: invokevirtual 553	com/claro/transfer/TelefonoTO:getMobileTO	()Lcom/claro/transfer/MobileTO;
/*     */     //   556: invokevirtual 637	com/claro/transfer/MobileTO:getPromedio	()D
/*     */     //   559: invokeinterface 630 4 0
/*     */     //   564: aload 16
/*     */     //   566: bipush 20
/*     */     //   568: aload_3
/*     */     //   569: invokevirtual 149	com/claro/transfer/RetencionTO:getPorcARPU	()I
/*     */     //   572: i2s
/*     */     //   573: invokeinterface 580 3 0
/*     */     //   578: aload 16
/*     */     //   580: bipush 21
/*     */     //   582: aload_1
/*     */     //   583: invokevirtual 553	com/claro/transfer/TelefonoTO:getMobileTO	()Lcom/claro/transfer/MobileTO;
/*     */     //   586: invokevirtual 640	com/claro/transfer/MobileTO:getNoBajas	()I
/*     */     //   589: i2s
/*     */     //   590: invokeinterface 580 3 0
/*     */     //   595: aload 16
/*     */     //   597: bipush 22
/*     */     //   599: aload_3
/*     */     //   600: invokevirtual 156	com/claro/transfer/RetencionTO:getPorcCob	()I
/*     */     //   603: i2s
/*     */     //   604: invokeinterface 580 3 0
/*     */     //   609: aload 16
/*     */     //   611: bipush 23
/*     */     //   613: aload_3
/*     */     //   614: invokevirtual 347	com/claro/transfer/RetencionTO:getValorCupon	()I
/*     */     //   617: invokeinterface 115 3 0
/*     */     //   622: aload 19
/*     */     //   624: ifnull +13 -> 637
/*     */     //   627: aload 19
/*     */     //   629: ldc 75
/*     */     //   631: invokevirtual 221	java/lang/String:equals	(Ljava/lang/Object;)Z
/*     */     //   634: ifeq +16 -> 650
/*     */     //   637: aload 16
/*     */     //   639: bipush 24
/*     */     //   641: iconst_0
/*     */     //   642: invokeinterface 115 3 0
/*     */     //   647: goto +17 -> 664
/*     */     //   650: aload 16
/*     */     //   652: bipush 24
/*     */     //   654: aload 19
/*     */     //   656: invokestatic 353	java/lang/Integer:parseInt	(Ljava/lang/String;)I
/*     */     //   659: invokeinterface 115 3 0
/*     */     //   664: aload 16
/*     */     //   666: bipush 25
/*     */     //   668: aload 5
/*     */     //   670: invokeinterface 235 3 0
/*     */     //   675: aload 16
/*     */     //   677: bipush 26
/*     */     //   679: aload 6
/*     */     //   681: invokeinterface 235 3 0
/*     */     //   686: aload 16
/*     */     //   688: bipush 27
/*     */     //   690: aload 7
/*     */     //   692: invokeinterface 235 3 0
/*     */     //   697: invokestatic 239	java/util/Calendar:getInstance	()Ljava/util/Calendar;
/*     */     //   700: astore 25
/*     */     //   702: aload 25
/*     */     //   704: bipush 6
/*     */     //   706: bipush 30
/*     */     //   708: invokevirtual 643	java/util/Calendar:add	(II)V
/*     */     //   711: aload 16
/*     */     //   713: bipush 28
/*     */     //   715: new 289	java/sql/Date
/*     */     //   718: dup
/*     */     //   719: aload 25
/*     */     //   721: invokevirtual 646	java/util/Calendar:getTimeInMillis	()J
/*     */     //   724: invokespecial 523	java/sql/Date:<init>	(J)V
/*     */     //   727: invokeinterface 474 3 0
/*     */     //   732: aload 16
/*     */     //   734: bipush 29
/*     */     //   736: aload 8
/*     */     //   738: invokeinterface 235 3 0
/*     */     //   743: aload 9
/*     */     //   745: ifnull +13 -> 758
/*     */     //   748: aload 9
/*     */     //   750: ldc 75
/*     */     //   752: invokevirtual 221	java/lang/String:equals	(Ljava/lang/Object;)Z
/*     */     //   755: ifeq +16 -> 771
/*     */     //   758: aload 16
/*     */     //   760: bipush 30
/*     */     //   762: iconst_0
/*     */     //   763: invokeinterface 580 3 0
/*     */     //   768: goto +17 -> 785
/*     */     //   771: aload 16
/*     */     //   773: bipush 30
/*     */     //   775: aload 9
/*     */     //   777: invokestatic 601	java/lang/Short:parseShort	(Ljava/lang/String;)S
/*     */     //   780: invokeinterface 580 3 0
/*     */     //   785: aload 10
/*     */     //   787: ifnull +13 -> 800
/*     */     //   790: aload 10
/*     */     //   792: ldc 75
/*     */     //   794: invokevirtual 221	java/lang/String:equals	(Ljava/lang/Object;)Z
/*     */     //   797: ifeq +16 -> 813
/*     */     //   800: aload 16
/*     */     //   802: bipush 31
/*     */     //   804: iconst_0
/*     */     //   805: invokeinterface 580 3 0
/*     */     //   810: goto +17 -> 827
/*     */     //   813: aload 16
/*     */     //   815: bipush 31
/*     */     //   817: aload 10
/*     */     //   819: invokestatic 601	java/lang/Short:parseShort	(Ljava/lang/String;)S
/*     */     //   822: invokeinterface 580 3 0
/*     */     //   827: aload 16
/*     */     //   829: invokeinterface 526 1 0
/*     */     //   834: pop
/*     */     //   835: new 80	com/claro/transfer/MensajeTO
/*     */     //   838: dup
/*     */     //   839: invokespecial 82	com/claro/transfer/MensajeTO:<init>	()V
/*     */     //   842: astore 18
/*     */     //   844: aload 18
/*     */     //   846: iconst_0
/*     */     //   847: invokevirtual 173	com/claro/transfer/MensajeTO:setIdMensaje	(I)V
/*     */     //   850: goto +172 -> 1022
/*     */     //   853: astore 19
/*     */     //   855: new 80	com/claro/transfer/MensajeTO
/*     */     //   858: dup
/*     */     //   859: invokespecial 82	com/claro/transfer/MensajeTO:<init>	()V
/*     */     //   862: astore 18
/*     */     //   864: aload 18
/*     */     //   866: iconst_1
/*     */     //   867: invokevirtual 173	com/claro/transfer/MensajeTO:setIdMensaje	(I)V
/*     */     //   870: aload 18
/*     */     //   872: aload 19
/*     */     //   874: invokevirtual 187	java/lang/Exception:getMessage	()Ljava/lang/String;
/*     */     //   877: invokevirtual 190	com/claro/transfer/MensajeTO:setMensaje	(Ljava/lang/String;)V
/*     */     //   880: aload 18
/*     */     //   882: astore 27
/*     */     //   884: aload 17
/*     */     //   886: ifnull +18 -> 904
/*     */     //   889: aload 17
/*     */     //   891: invokeinterface 180 1 0
/*     */     //   896: aconst_null
/*     */     //   897: astore 17
/*     */     //   899: goto +5 -> 904
/*     */     //   902: astore 28
/*     */     //   904: aload 16
/*     */     //   906: ifnull +18 -> 924
/*     */     //   909: aload 16
/*     */     //   911: invokeinterface 183 1 0
/*     */     //   916: aconst_null
/*     */     //   917: astore 16
/*     */     //   919: goto +5 -> 924
/*     */     //   922: astore 28
/*     */     //   924: aload 12
/*     */     //   926: ifnonnull +23 -> 949
/*     */     //   929: aload 15
/*     */     //   931: ifnull +18 -> 949
/*     */     //   934: aload 15
/*     */     //   936: invokeinterface 184 1 0
/*     */     //   941: aconst_null
/*     */     //   942: astore 15
/*     */     //   944: goto +5 -> 949
/*     */     //   947: astore 28
/*     */     //   949: aload 27
/*     */     //   951: areturn
/*     */     //   952: astore 26
/*     */     //   954: aload 17
/*     */     //   956: ifnull +18 -> 974
/*     */     //   959: aload 17
/*     */     //   961: invokeinterface 180 1 0
/*     */     //   966: aconst_null
/*     */     //   967: astore 17
/*     */     //   969: goto +5 -> 974
/*     */     //   972: astore 28
/*     */     //   974: aload 16
/*     */     //   976: ifnull +18 -> 994
/*     */     //   979: aload 16
/*     */     //   981: invokeinterface 183 1 0
/*     */     //   986: aconst_null
/*     */     //   987: astore 16
/*     */     //   989: goto +5 -> 994
/*     */     //   992: astore 28
/*     */     //   994: aload 12
/*     */     //   996: ifnonnull +23 -> 1019
/*     */     //   999: aload 15
/*     */     //   1001: ifnull +18 -> 1019
/*     */     //   1004: aload 15
/*     */     //   1006: invokeinterface 184 1 0
/*     */     //   1011: aconst_null
/*     */     //   1012: astore 15
/*     */     //   1014: goto +5 -> 1019
/*     */     //   1017: astore 28
/*     */     //   1019: aload 26
/*     */     //   1021: athrow
/*     */     //   1022: aload 17
/*     */     //   1024: ifnull +18 -> 1042
/*     */     //   1027: aload 17
/*     */     //   1029: invokeinterface 180 1 0
/*     */     //   1034: aconst_null
/*     */     //   1035: astore 17
/*     */     //   1037: goto +5 -> 1042
/*     */     //   1040: astore 28
/*     */     //   1042: aload 16
/*     */     //   1044: ifnull +18 -> 1062
/*     */     //   1047: aload 16
/*     */     //   1049: invokeinterface 183 1 0
/*     */     //   1054: aconst_null
/*     */     //   1055: astore 16
/*     */     //   1057: goto +5 -> 1062
/*     */     //   1060: astore 28
/*     */     //   1062: aload 12
/*     */     //   1064: ifnonnull +23 -> 1087
/*     */     //   1067: aload 15
/*     */     //   1069: ifnull +18 -> 1087
/*     */     //   1072: aload 15
/*     */     //   1074: invokeinterface 184 1 0
/*     */     //   1079: aconst_null
/*     */     //   1080: astore 15
/*     */     //   1082: goto +5 -> 1087
/*     */     //   1085: astore 28
/*     */     //   1087: aload 18
/*     */     //   1089: areturn
/*     */     // Line number table:
/*     */     //   Java source line #583	-> byte code offset #0
/*     */     //   Java source line #584	-> byte code offset #3
/*     */     //   Java source line #585	-> byte code offset #6
/*     */     //   Java source line #586	-> byte code offset #9
/*     */     //   Java source line #589	-> byte code offset #12
/*     */     //   Java source line #590	-> byte code offset #17
/*     */     //   Java source line #592	-> byte code offset #24
/*     */     //   Java source line #598	-> byte code offset #35
/*     */     //   Java source line #599	-> byte code offset #39
/*     */     //   Java source line #600	-> byte code offset #43
/*     */     //   Java source line #603	-> byte code offset #47
/*     */     //   Java source line #604	-> byte code offset #70
/*     */     //   Java source line #605	-> byte code offset #79
/*     */     //   Java source line #606	-> byte code offset #102
/*     */     //   Java source line #609	-> byte code offset #111
/*     */     //   Java source line #610	-> byte code offset #122
/*     */     //   Java source line #612	-> byte code offset #129
/*     */     //   Java source line #615	-> byte code offset #135
/*     */     //   Java source line #619	-> byte code offset #139
/*     */     //   Java source line #620	-> byte code offset #162
/*     */     //   Java source line #621	-> byte code offset #168
/*     */     //   Java source line #622	-> byte code offset #174
/*     */     //   Java source line #619	-> byte code offset #180
/*     */     //   Java source line #624	-> byte code offset #185
/*     */     //   Java source line #626	-> byte code offset #196
/*     */     //   Java source line #627	-> byte code offset #208
/*     */     //   Java source line #628	-> byte code offset #227
/*     */     //   Java source line #630	-> byte code offset #239
/*     */     //   Java source line #631	-> byte code offset #254
/*     */     //   Java source line #632	-> byte code offset #266
/*     */     //   Java source line #633	-> byte code offset #278
/*     */     //   Java source line #634	-> byte code offset #295
/*     */     //   Java source line #635	-> byte code offset #307
/*     */     //   Java source line #636	-> byte code offset #321
/*     */     //   Java source line #637	-> byte code offset #337
/*     */     //   Java source line #638	-> byte code offset #350
/*     */     //   Java source line #640	-> byte code offset #363
/*     */     //   Java source line #641	-> byte code offset #381
/*     */     //   Java source line #643	-> byte code offset #395
/*     */     //   Java source line #644	-> byte code offset #413
/*     */     //   Java source line #646	-> byte code offset #427
/*     */     //   Java source line #647	-> byte code offset #442
/*     */     //   Java source line #649	-> byte code offset #455
/*     */     //   Java source line #650	-> byte code offset #469
/*     */     //   Java source line #652	-> byte code offset #483
/*     */     //   Java source line #654	-> byte code offset #495
/*     */     //   Java source line #655	-> byte code offset #499
/*     */     //   Java source line #656	-> byte code offset #502
/*     */     //   Java source line #657	-> byte code offset #514
/*     */     //   Java source line #658	-> byte code offset #521
/*     */     //   Java source line #659	-> byte code offset #535
/*     */     //   Java source line #655	-> byte code offset #538
/*     */     //   Java source line #662	-> byte code offset #548
/*     */     //   Java source line #663	-> byte code offset #564
/*     */     //   Java source line #664	-> byte code offset #578
/*     */     //   Java source line #665	-> byte code offset #595
/*     */     //   Java source line #666	-> byte code offset #609
/*     */     //   Java source line #667	-> byte code offset #622
/*     */     //   Java source line #668	-> byte code offset #637
/*     */     //   Java source line #670	-> byte code offset #650
/*     */     //   Java source line #671	-> byte code offset #664
/*     */     //   Java source line #672	-> byte code offset #675
/*     */     //   Java source line #673	-> byte code offset #686
/*     */     //   Java source line #675	-> byte code offset #697
/*     */     //   Java source line #676	-> byte code offset #702
/*     */     //   Java source line #678	-> byte code offset #711
/*     */     //   Java source line #679	-> byte code offset #732
/*     */     //   Java source line #680	-> byte code offset #743
/*     */     //   Java source line #681	-> byte code offset #758
/*     */     //   Java source line #683	-> byte code offset #771
/*     */     //   Java source line #685	-> byte code offset #785
/*     */     //   Java source line #686	-> byte code offset #800
/*     */     //   Java source line #688	-> byte code offset #813
/*     */     //   Java source line #690	-> byte code offset #827
/*     */     //   Java source line #691	-> byte code offset #835
/*     */     //   Java source line #692	-> byte code offset #844
/*     */     //   Java source line #695	-> byte code offset #853
/*     */     //   Java source line #696	-> byte code offset #855
/*     */     //   Java source line #697	-> byte code offset #864
/*     */     //   Java source line #698	-> byte code offset #870
/*     */     //   Java source line #699	-> byte code offset #880
/*     */     //   Java source line #702	-> byte code offset #884
/*     */     //   Java source line #703	-> byte code offset #904
/*     */     //   Java source line #704	-> byte code offset #924
/*     */     //   Java source line #699	-> byte code offset #949
/*     */     //   Java source line #700	-> byte code offset #952
/*     */     //   Java source line #702	-> byte code offset #954
/*     */     //   Java source line #703	-> byte code offset #974
/*     */     //   Java source line #704	-> byte code offset #994
/*     */     //   Java source line #705	-> byte code offset #1019
/*     */     //   Java source line #702	-> byte code offset #1022
/*     */     //   Java source line #703	-> byte code offset #1042
/*     */     //   Java source line #704	-> byte code offset #1062
/*     */     //   Java source line #707	-> byte code offset #1087
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	signature
/*     */     //   0	1090	0	this	RetencionDAO
/*     */     //   0	1090	1	telefonoTO	TelefonoTO
/*     */     //   0	1090	2	usuarioTO	UsuarioTO
/*     */     //   0	1090	3	retencionTO	RetencionTO
/*     */     //   0	1090	4	sNMeses	String
/*     */     //   0	1090	5	sMotivo	String
/*     */     //   0	1090	6	sComenta	String
/*     */     //   0	1090	7	sComenta2	String
/*     */     //   0	1090	8	sCuentaAnt	String
/*     */     //   0	1090	9	sRegionAnt	String
/*     */     //   0	1090	10	sMesesAnt	String
/*     */     //   0	1090	11	sTipo	String
/*     */     //   0	1090	12	connection	Connection
/*     */     //   0	1090	13	fechaTransaccion	long
/*     */     //   1	1080	15	conn	Connection
/*     */     //   4	1052	16	stmt	PreparedStatement
/*     */     //   7	1029	17	rset	ResultSet
/*     */     //   10	1078	18	mensajeTO	MensajeTO
/*     */     //   37	618	19	sCuponExtra	String
/*     */     //   853	20	19	e	Exception
/*     */     //   41	345	20	sFechaAdd	String
/*     */     //   45	373	21	sFechaAlta	String
/*     */     //   137	51	22	sUpdate	String
/*     */     //   493	46	23	iterator	java.util.Iterator<com.claro.transfer.FacturaTO>
/*     */     //   497	39	24	numParam	int
/*     */     //   512	14	25	facturaTO	com.claro.transfer.FacturaTO
/*     */     //   700	20	25	hoy	Calendar
/*     */     //   952	68	26	localObject	Object
/*     */     //   882	68	27	localMensajeTO1	MensajeTO
/*     */     //   902	1	28	localException1	Exception
/*     */     //   922	1	28	localException2	Exception
/*     */     //   947	1	28	localException3	Exception
/*     */     //   972	1	28	localException4	Exception
/*     */     //   992	1	28	localException5	Exception
/*     */     //   1017	1	28	localException6	Exception
/*     */     //   1040	1	28	localException7	Exception
/*     */     //   1060	1	28	localException8	Exception
/*     */     //   1085	1	28	localException9	Exception
/*     */     // Exception table:
/*     */     //   from	to	target	type
/*     */     //   12	850	853	java/lang/Exception
/*     */     //   889	899	902	java/lang/Exception
/*     */     //   909	919	922	java/lang/Exception
/*     */     //   934	944	947	java/lang/Exception
/*     */     //   12	884	952	finally
/*     */     //   959	969	972	java/lang/Exception
/*     */     //   979	989	992	java/lang/Exception
/*     */     //   1004	1014	1017	java/lang/Exception
/*     */     //   1027	1037	1040	java/lang/Exception
/*     */     //   1047	1057	1060	java/lang/Exception
/*     */     //   1072	1082	1085	java/lang/Exception
/*     */   }
/*     */   
/*     */   public RetencionTO getMotivos()
/*     */     throws CAException
/*     */   {
/* 714 */     Connection conn = null;
/* 715 */     PreparedStatement stmt = null;
/* 716 */     ResultSet rset = null;
/* 717 */     RetencionTO retencionTO = null;
/* 718 */     ArrayList<MotivoTO> motivos = null;
/* 719 */     MensajeTO mensajeTO = null;
/*     */     try {
/* 721 */       conn = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
/* 722 */       String sSql = "SELECT IDMOTIVO, MOTIVO FROM " + 
/* 723 */         this.schema_database + "PTO_CTLRETENCION WHERE STATUS = 'A' ORDER BY IDMOTIVO";
/* 724 */       stmt = conn.prepareStatement(sSql);
/* 725 */       rset = stmt.executeQuery();
/*     */       
/* 727 */       motivos = new ArrayList();
/* 728 */       while (rset.next()) {
/* 729 */         MotivoTO motivoTO = new MotivoTO();
/* 730 */         motivoTO.setIdMotivo(String.valueOf(rset.getInt(1)));
/* 731 */         motivoTO.setDescripcion(rset.getString(2));
/* 732 */         motivos.add(motivoTO);
/*     */       }
/* 734 */       retencionTO = new RetencionTO();
/* 735 */       retencionTO.setMotivos(motivos);
/* 736 */       mensajeTO = new MensajeTO();
/* 737 */       mensajeTO.setIdMensaje(0);
/* 738 */       retencionTO.setMensajeTO(mensajeTO);
/*     */       
/* 740 */       return retencionTO;
/*     */     } catch (Exception e) { RetencionTO localRetencionTO1;
/* 742 */       retencionTO = new RetencionTO();
/* 743 */       mensajeTO = new MensajeTO();
/* 744 */       mensajeTO.setIdMensaje(1);
/* 745 */       mensajeTO.setMensaje("[getMotivos]" + e.getMessage());
/* 746 */       retencionTO.setMensajeTO(mensajeTO);
/* 747 */       return retencionTO;
/*     */     } finally {
/* 749 */       if (rset != null) try { rset.close();rset = null; } catch (Exception localException7) {}
/* 750 */       if (stmt != null) try { stmt.close();stmt = null; } catch (Exception localException8) {}
/* 751 */       if (conn != null) try { conn.close();conn = null;
/*     */         }
/*     */         catch (Exception localException9) {}
/*     */     }
/*     */   }
/*     */ }


/* Location:              /opt/trabajo/claro/Guatemala/IS/CO/ClaroClub.ear!/ClaroClubEJB.jar!/com/claro/dao/RetencionDAO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */