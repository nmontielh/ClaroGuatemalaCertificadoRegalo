package com.claro.dao;

import com.claro.catalogo.Catalogo;
import com.claro.dao.BeneficioDAO;
import com.claro.dao.ConsultasDAO;
import com.claro.dao.ConsultasGapDAO;
import com.claro.dao.DistribuidoresDAO;
import com.claro.dao.PuntosDAO;
import com.claro.exception.CAException;
import com.claro.redencion.sms.NotificaSMS;
import com.claro.seguridad.SeguridadCaUtil;
import com.claro.transfer.BeneficioTO;
import com.claro.transfer.MensajeTO;
import com.claro.transfer.MobileTO;
import com.claro.transfer.ParametrosTO;
import com.claro.transfer.PerfilTO;
import com.claro.transfer.ProductosTO;
import com.claro.transfer.PuntoVentaTO;
import com.claro.transfer.PuntosRedimidosTO;
import com.claro.transfer.PuntosTO;
import com.claro.transfer.RedencionTO;
import com.claro.transfer.ReservacionTO;
import com.claro.transfer.TelefonoSimpleTO;
import com.claro.transfer.TelefonoTO;
import com.claro.transfer.UsuarioTO;
import com.claro.transfer.service.TelefonoServiceTO;
import com.claro.util.Constantes;
import com.claro.util.Redencion;
import com.claro.util.ServiceLocator;
import com.claro.util.Utils;
import com.telcel.crm.scrapy.beans.ErrorRow;
import com.telcel.crm.scrapy.beans.beneficios.Cudi2TO;
import com.telcel.crm.scrapy.beans.beneficios.CudisTO;
import com.telcel.crm.scrapy.beans.beneficios.PlanTO;
import com.telcel.gscrm.dccrm.ws.ioh.AplicaBeneficioProxy;

import java.io.PrintStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.Locale;

import org.apache.log4j.Logger;

public class RedencionDAO {
    protected final Logger logger = Logger.getLogger((String)"loggerCirculoAzul");
    protected final Logger error = Logger.getLogger((String)"loggerCirculoAzulError");
    private String schema_database;
    private ConsultasGapDAO consultasGapDAO = null;

    public RedencionDAO() {
        try {
            this.consultasGapDAO = new ConsultasGapDAO();
            this.schema_database = ServiceLocator.getInstance().getVariable(ServiceLocator.schema_database);
        }
        catch (Exception e) {
            this.error.error((Object)"RedencionDAO", (Throwable)e);
        }
    }

    public RedencionTO aplicaredencion(ProductosTO productosTO, ParametrosTO parametrosTO, RedencionTO redencionTO, boolean isEcac, boolean isDistribuidores, String endPoint, String usuario, BeneficioTO beneficioTO, String endpointGap) throws CAException {
        Connection connection = null;
        MensajeTO mensajeTO = new MensajeTO();
        PuntosDAO puntosDAO = new PuntosDAO();
        ConsultasDAO consultasDAO = new ConsultasDAO();
        String rComent = "";
        String msgEP = "";
        int valorPuntos = 0;
        try {
            Catalogo properties = new Catalogo();
            properties.setTabla("propiedades");
            properties.cargaCatalogo();
            String idTipoRedencion = "";
            String descTipoRedencion = "";
            String comentarioCompl = "";
            String descTipoRedencionSMS = "";
            String usuarioR04 = properties.getPropiedad("usuario.r04.redencion.mensajeSMS");
            if (parametrosTO != null && parametrosTO.getTipoRed() != null) {
                idTipoRedencion = parametrosTO.getTipoRed();
            }
            if ((isEcac || isDistribuidores) && productosTO == null) {
                ReservacionTO reservacionTO = consultasDAO.obtieneReservacion(parametrosTO.getFolio(), null, false);
                if (reservacionTO.getIdMensaje() != 0) {
                    ReservacionTO reservacionTO2 = reservacionTO;
                    return reservacionTO2;
                }
                productosTO = reservacionTO.getProductosTO();
                productosTO.setNumeroSerieP(parametrosTO.getNumeroSerieP());
                productosTO.setNumeroSerieT(parametrosTO.getNumeroSerieT());
                productosTO.setIccid(parametrosTO.getIccid());
                productosTO.setValorPuntosTmp(productosTO.getValorPuntos());
                parametrosTO.setCuenta(reservacionTO.getTelefonoSimpleTO().getCuenta());
                parametrosTO.setSecuencia(reservacionTO.getTelefonoSimpleTO().getSecuencia());
                parametrosTO.setTelefono(reservacionTO.getTelefonoSimpleTO().getLinea());
                parametrosTO.setPlanAnt(reservacionTO.getPlanAnterior());
                parametrosTO.setPlanNvo(reservacionTO.getPlanNuevo());
                redencionTO.setRegion(reservacionTO.getRegion());
                redencionTO.setAddendumNuevo(Integer.parseInt(reservacionTO.getPlazoNuevo() == null ? "0" : reservacionTO.getPlazoNuevo()));
                redencionTO.setAddendumAnterior(Integer.parseInt(reservacionTO.getPlazoAnterior() == null ? "0" : reservacionTO.getPlazoAnterior()));
                redencionTO.setFormaRedencion(reservacionTO.getFormaRedencion());
                redencionTO.setEstatus("A");
                redencionTO.setPtsSobrantes(reservacionTO.getPtsSobrantes());
                redencionTO.setFechaAdendumAnterior(reservacionTO.getFechaAdendumAnterior());
                redencionTO.setFechaAdendumNuevo(reservacionTO.getFechaAdendumNuevo());
                redencionTO.setComentario(reservacionTO.getComentario());
                redencionTO.setTipoRedencion(reservacionTO.getTipoRedencion());
                redencionTO.setFolio(parametrosTO.getFolio());
                redencionTO.setFuerzaVenta(reservacionTO.getFuerzaVenta());
                if (isEcac) {
                    reservacionTO.getUsuarioTO().setIdUsuario(parametrosTO.getUsuariMovimiento());
                }
                redencionTO.setUsuarioTO(reservacionTO.getUsuarioTO());
                redencionTO.setDireccionIP(parametrosTO.getIpAddress());
                idTipoRedencion = reservacionTO.getTipoRedencion();
                idTipoRedencion = idTipoRedencion.equals("C") || idTipoRedencion.equals("S") ? "CON" : (idTipoRedencion.equals("G") ? "T3G" : "ACA");
            }
            if (idTipoRedencion.equals("TAIR")) {
                descTipoRedencion = "TIEMPO AIRE";
                descTipoRedencionSMS = "PAQUETE DE MINUTOS";
            } else if (idTipoRedencion.equals("SIN")) {
                descTipoRedencion = "AMIGO KIT";
                descTipoRedencionSMS = "AMIGO KIT";
            } else if (idTipoRedencion.equals("ACA")) {
                descTipoRedencion = "AMIGO CHIP";
                descTipoRedencionSMS = "AMIGO CHIP";
            } else if (idTipoRedencion.equals("T3G")) {
                descTipoRedencion = "TARJETA 3G";
                descTipoRedencionSMS = "MODEM USB TELCEL";
            } else if (idTipoRedencion.equals("CAREG")) {
                descTipoRedencion = "CAREG";
                descTipoRedencionSMS = "CAREG";
            } else if (idTipoRedencion.equals("CON")) {
                descTipoRedencion = "CAMCA";
                descTipoRedencionSMS = "RENOVACION DE EQUIPO";
            } else if (idTipoRedencion.equals("RONLINE")) {
                descTipoRedencion = "VIA ONLINE";
                descTipoRedencionSMS = "PAQUETE SMS";
            }
            PuntosTO puntosTO = consultasDAO.obtienePuntos(parametrosTO.getCuenta(), parametrosTO.getSecuencia());
            if (isEcac || isDistribuidores) {
                puntosTO.setPtosDisponiblesTmp(puntosTO.getPtosDisponibles());
            } else {
                puntosTO.setPtosDisponiblesTmp(parametrosTO.getPuntosTotalesTmp());
            }
            puntosTO.setPtsRedimidos(puntosTO.getPtsRedimidos() + productosTO.getValorPuntos());
            productosTO.setValorPuntosTmp(productosTO.getValorPuntos());
            valorPuntos = productosTO.getValorPuntos();
            PuntosRedimidosTO puntosRedimidosTO = new PuntosRedimidosTO(puntosTO);
            if (puntosRedimidosTO.getIdMensaje() == 1) {
                redencionTO.agregaMensaje(puntosRedimidosTO.getIdMensaje(), puntosRedimidosTO.getMensaje());
                RedencionTO redencionTO2 = redencionTO;
                return redencionTO2;
            }
            if (!(isEcac || isDistribuidores)) {
                if (puntosRedimidosTO.getPtosStatus() != null && puntosRedimidosTO.getPtosStatus().equals("W")) {
                    redencionTO.agregaMensaje(1, "Lo sentimos los puntos estan reservados por otra operacion. Intentelo de nuevo");
                    RedencionTO redencionTO3 = redencionTO;
                    return redencionTO3;
                }
                mensajeTO = puntosDAO.actualizaLinea(null, parametrosTO.getCuenta(), parametrosTO.getSecuencia(), "W");
            }
            connection = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
            long fechaTransaccion = System.currentTimeMillis();
            connection.setAutoCommit(false);
            if (productosTO.getValorPuntos() > puntosRedimidosTO.getPtosDisponibles()) {
                redencionTO.agregaMensaje(1, "Lo sentimos, pero el usuario no cuenta con los puntos suficientes para realizar la redencion de Puntos.");
                RedencionTO redencionTO4 = redencionTO;
                return redencionTO4;
            }
            redencionTO.setPuntosRedimidosTO(puntosRedimidosTO);
            redencionTO.setProductosTO(productosTO);
            Redencion.aplicaRedencion((RedencionTO)redencionTO, (int)7);
            redencionTO.getPuntosRedimidosTO().setPtsExcedentes(redencionTO.getPuntosRedimidosTO().getPtsExcedentes() + redencionTO.getPtsSobrantes());
            mensajeTO = puntosDAO.actualizaPuntos(puntosRedimidosTO, connection, redencionTO.getTipoRedencion(), parametrosTO.getCuenta(), parametrosTO.getSecuencia(), true);
            if (mensajeTO.getIdMensaje() == 1 || mensajeTO.getIdMensaje() == -1) {
                redencionTO.agregaMensaje(mensajeTO.getIdMensaje(), mensajeTO.getMensaje());
                connection.rollback();
                RedencionTO redencionTO5 = redencionTO;
                return redencionTO5;
            }
            if (isEcac || isDistribuidores) {
                String string = rComent = redencionTO.getFuerzaVenta() == null ? redencionTO.getFuerzaVenta() : redencionTO.getFuerzaVenta().trim();
                if (redencionTO.getUsuarioTO().getIdUsuario().equals(usuarioR04)) {
                    rComent = "SR4";
                }
                if (!(parametrosTO.getPlanAnt() == null || parametrosTO.getPlanAnt().trim().equals(""))) {
                    rComent = String.valueOf(rComent) + "- PLAN ANTERIOR:" + parametrosTO.getPlanAnt().trim();
                }
                if (!(parametrosTO.getPlanNvo() == null || parametrosTO.getPlanNvo().trim().equals(""))) {
                    rComent = String.valueOf(rComent) + " PLAN ACTUAL:" + parametrosTO.getPlanNvo().trim();
                }
                rComent = String.valueOf(rComent) + "  " + redencionTO.getComentario();
            } else {
                String sFolioRed = Utils.generaFolio((String)parametrosTO.getTelefono());
                if (sFolioRed.length() <= 0) {
                    redencionTO.agregaMensaje(1, "El numero de folio no es valido.");
                    RedencionTO redencionTO6 = redencionTO;
                    return redencionTO6;
                }
                redencionTO.setFolio(sFolioRed);
                rComent = "C".equals(redencionTO.getTipoRedencion()) ? " CIR-PLAN ANTERIOR: " + parametrosTO.getPlanAnt() + " PLAN ACTUAL: " + parametrosTO.getPlanNvo() + "  " + redencionTO.getComentario() : " CIR-" + redencionTO.getComentario();
            }
            redencionTO.setComentario(rComent);
            mensajeTO = puntosDAO.insertaRedencion(connection, redencionTO, parametrosTO, fechaTransaccion);
            if (mensajeTO.getIdMensaje() == 0) {
                mensajeTO = puntosDAO.insertaConstancia(redencionTO, parametrosTO, connection, fechaTransaccion, "A");
            }
            if (!(mensajeTO.getIdMensaje() != 0 || redencionTO.getProductosTO().getDescuentoInbursaRestante().equals(new BigDecimal(0)) && redencionTO.getProductosTO().getDescuentoMarcaRestante().equals(new BigDecimal(0)) && redencionTO.getProductosTO().getDescuentoMarca().equals(new BigDecimal(0)) && redencionTO.getProductosTO().getDescuentoInbursa().equals(new BigDecimal(0)))) {
                mensajeTO = puntosDAO.insertaBonoInbursa(redencionTO, parametrosTO, connection);
            }
            if (mensajeTO.getIdMensaje() == 0) {
                mensajeTO = puntosDAO.insertaDetalle(connection, fechaTransaccion, "Redencion Terminada " + redencionTO.getTipoRedencion() + ", atendio: " + redencionTO.getUsuarioTO().getNumEmpleado() + " IP: " + redencionTO.getDireccionIP(), 5, redencionTO.getProductosTO().getValorPuntosTmp() * -1, null, parametrosTO.getCuenta(), parametrosTO.getSecuencia(), parametrosTO.getTelefono(), redencionTO.getUsuarioTO().getIdUsuario());
            }
            if (!idTipoRedencion.equals("RONLINE")) {
                if (!productosTO.getMarca().trim().equals("")) {
                    comentarioCompl = "MARCA: " + productosTO.getMarca().trim();
                }
                if (!productosTO.getModelo().trim().equals("")) {
                    comentarioCompl = String.valueOf(comentarioCompl) + " MODELO: " + productosTO.getModelo().trim();
                }
            }
            if (mensajeTO.getIdMensaje() == 0) {
                if (isEcac || isDistribuidores) {
                    String fzaVenta = redencionTO.getFuerzaVenta();
                    usuarioR04 = properties.getPropiedad("usuario.r04.redencion.mensajeSMS");
                    if (redencionTO.getUsuarioTO().getIdUsuario().equals(usuarioR04)) {
                        fzaVenta = "R04";
                    }
                    rComent = String.valueOf(fzaVenta) + " -REDENCION " + descTipoRedencion + "-" + valorPuntos + " PTOS. A PET. DE " + redencionTO.getUsuarioTO().getIdUsuario() + " " + comentarioCompl + " " + rComent;
                } else {
                    rComent = "CIR-REDENCION " + descTipoRedencion + "-" + redencionTO.getProductosTO().getValorPuntosTmp() + " PTOS. A PET. DE " + redencionTO.getUsuarioTO().getIdUsuario() + " " + comentarioCompl + " " + rComent;
                }
                rComent = "PTO.VTA:" + redencionTO.getUsuarioTO().getPuntoVentaTO().getPtoVenta() + "-" + rComent;
                if (rComent.length() > 250) {
                    rComent = rComent.substring(0, 250);
                }
                mensajeTO = puntosDAO.insertaComentarioTMP(connection, redencionTO.getRegion(), parametrosTO.getCuenta(), redencionTO.getUsuarioTO().getIdUsuario(), fechaTransaccion, rComent);
            }
            if (mensajeTO.getIdMensaje() == 0 && redencionTO.getPtsSobrantes() > 0) {
                redencionTO.setIdMovimiento(52);
                rComent = "Asigna Pts Sobrantes Bono Equipo";
                mensajeTO = puntosDAO.insertaDetalle(connection, fechaTransaccion, rComent, 52, redencionTO.getPtsSobrantes(), null, parametrosTO.getCuenta(), parametrosTO.getSecuencia(), parametrosTO.getTelefono(), redencionTO.getUsuarioTO().getIdUsuario());
            }
            if (!(isEcac || isDistribuidores || mensajeTO.getIdMensaje() != 0 || beneficioTO == null || beneficioTO.getIdBeneficio() == null || beneficioTO.getIdBeneficio().equals("") || !parametrosTO.getTipoRed().equals("CON") && !parametrosTO.getTipoRed().equals("CAREG"))) {
                TelefonoTO telefonoTO = new TelefonoTO();
                telefonoTO.setCuenta(parametrosTO.getCuenta());
                telefonoTO.setSecuencia(String.valueOf(parametrosTO.getSecuencia()));
                telefonoTO.setTelefono(parametrosTO.getTelefono());
                telefonoTO.setRegion(parametrosTO.getRegion());
                beneficioTO.setFolio(redencionTO.getFolio());
                BeneficioDAO beneficioDAO = new BeneficioDAO();
                mensajeTO = beneficioDAO.guardaBeneficioSeleccionado(connection, telefonoTO, beneficioTO, redencionTO.getUsuarioTO().getIdUsuario());
            }
            if (isEcac || isDistribuidores) {
                if (mensajeTO.getIdMensaje() == 0) {
                    mensajeTO = puntosDAO.actualizaReservacion(connection, fechaTransaccion, redencionTO.getUsuarioTO().getIdUsuario(), "R", null, parametrosTO.getFolio());
                } else {
                    connection.rollback();
                    if (connection != null) {
                        try {
                            connection.close();
                            connection = null;
                        }
                        catch (Exception telefonoTO) {
                            // empty catch block
                        }
                    }
                    RedencionTO redencionTO7 = redencionTO;
                    return redencionTO7;
                }
            }
            if (mensajeTO.getIdMensaje() == 0 && (isEcac || isDistribuidores)) {
                mensajeTO = puntosDAO.actualizaLinea(null, parametrosTO.getCuenta(), parametrosTO.getSecuencia(), "0");
            }
            if (mensajeTO.getIdMensaje() == 0) {
                if (parametrosTO.getTipoRed() != null && !parametrosTO.getTipoRed().trim().equals("") && (parametrosTO.getTipoRed().equals("TAIR") || parametrosTO.getTipoRed().equals("RONLINE")) && !isEcac) {
                    AplicaBeneficioProxy proxy;
                    Cudi2TO cudisResponse = null;
                    CudisTO response;
                    ErrorRow[] errorRows;
                    boolean bExitoso = false;
                    String mensaje = "";
                    boolean aplicaCudi2 = new Boolean(properties.getPropiedad("APLICA.WS.CUDI2"));
                    if (aplicaCudi2) {
                        proxy = new AplicaBeneficioProxy();
                        cudisResponse = null;
                        proxy.setEndpoint(endPoint);
                        cudisResponse = proxy.actualizaPlanDescuentoRenta(parametrosTO.getCuenta(), parametrosTO.getTelefono(), redencionTO.getPlanDescuento(), "A", "", "N", parametrosTO.getRegion());
                        if (cudisResponse.getErrores() != null) {
                            errorRows = cudisResponse.getErrores();
                            for (int i = 0; i < errorRows.length; ++i) {
                                if (errorRows[i].getCodigo() != null && (errorRows[i].getCodigo().toString().trim().indexOf("AS201") != -1 || errorRows[i].getCodigo().toString().trim().indexOf("PG010") != -1)) {
                                    bExitoso = true;
                                    continue;
                                }
                                mensaje = String.valueOf(mensaje) + errorRows[i].getCodigo() + "|" + errorRows[i].getMensaje() + "-";
                            }
                        }
                    } else {
                        proxy = new AplicaBeneficioProxy();
                        response = null;
                        proxy.setEndpoint(endPoint);
                        response = proxy.actualizaPlanDescuento(parametrosTO.getCuenta(), parametrosTO.getTelefono(), redencionTO.getPlanDescuento(), "A", "", "N", parametrosTO.getRegion());
                        if (cudisResponse.getErrores() != null) {
                            errorRows = cudisResponse.getErrores();
                            for (int i = 0; i < errorRows.length; ++i) {
                                if (errorRows[i].getCodigo() != null && (errorRows[i].getCodigo().toString().trim().indexOf("AS201") != -1 || errorRows[i].getCodigo().toString().trim().indexOf("PG010") != -1)) {
                                    bExitoso = true;
                                    continue;
                                }
                                mensaje = String.valueOf(mensaje) + errorRows[i].getCodigo() + "|" + errorRows[i].getMensaje() + "-";
                            }
                        }
                    }
                    if (!bExitoso) {
                        redencionTO.agregaMensaje(-1, " ERROR:   " + mensaje + "]");
                        if (connection != null) {
                            connection.rollback();
                            try {
                                connection.close();
                                connection = null;
                            }
                            catch (Exception proxy1) {
                                // empty catch block
                            }
                        }
                        RedencionTO redencionTO8 = redencionTO;
                        return redencionTO8;
                    }
                }
            } else {
                connection.rollback();
                if (connection != null) {
                    try {
                        connection.close();
                        connection = null;
                    }
                    catch (Exception bExitoso) {
                        // empty catch block
                    }
                }
                RedencionTO redencionTO9 = redencionTO;
                return redencionTO9;
            }
            if (mensajeTO.getIdMensaje() == 0) {
                String aplicaMensajeSMS = properties.getPropiedad("ws.aplica.redencion.mensajeSMS");
                if (new Boolean(aplicaMensajeSMS).booleanValue()) {
                    String linea = parametrosTO.getTelefono();
                    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy");
                    String fechaHoy = sdf.format(new java.util.Date());
                    String mensajeSMS = String.valueOf(fechaHoy) + " Redencion de " + redencionTO.getProductosTO().getValorPuntosTmp() + " Puntos por " + descTipoRedencionSMS + ". Para cualquier aclaracion marca *111 desde tu Telcel.";
                    NotificaSMS notificaSMS = new NotificaSMS();
                    notificaSMS.enviaSMSRedencionCA(linea, mensajeSMS, properties);
                }
                connection.commit();
            } else {
                connection.rollback();
                if (connection != null) {
                    try {
                        connection.close();
                        connection = null;
                    }
                    catch (Exception aplicaMensajeSMS) {
                        // empty catch block
                    }
                }
                RedencionTO redencionTO10 = redencionTO;
                return redencionTO10;
            }
            try {
                redencionTO.agregaMensaje(mensajeTO.getIdMensaje(), mensajeTO.getMensaje());
            }
            catch (Exception e) {
                try {
                    if (connection != null) {
                        connection.rollback();
                    }
                }
                catch (Exception idTipoRedencion1) {
                    // empty catch block
                }
                throw new CAException(-1, "RedencionDAO.aplicaredencion.Error[" + e.toString() + "]", e);
            }
        } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        finally {
            if (connection != null) {
                try {
                    connection.close();
                    connection = null;
                }
                catch (Exception var36_38) {}
            }
            if (!(isEcac || isDistribuidores)) {
                puntosDAO.actualizaLinea(null, parametrosTO.getCuenta(), parametrosTO.getSecuencia(), "0");
            }
        }
        return redencionTO;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     */
    public ReservacionTO realizaApartado(ReservacionTO reservacionTO, TelefonoTO telefonoTO, boolean isDistribuidores, String fzaVentas) throws CAException {
        Connection connection = null;
        PuntosDAO puntosDAO = new PuntosDAO();
        try {
            connection = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
            connection.setAutoCommit(false);
            if (isDistribuidores) {
                DistribuidoresDAO distribuidoresDAO;
                boolean fzaVentasAutorizada;
                String estatusPuntos = this.getEstatusPuntos(reservacionTO.getTelefonoSimpleTO().getCuenta(), connection, reservacionTO.getTelefonoSimpleTO().getSecuencia());
                if (Utils.getValEstatusTel((String)telefonoTO.getMobileTO().getStatus(), (String)telefonoTO.getMobileTO().getMotivo())) {
                    throw new CAException(-1, "El Estatus del telefono es Invalido -- " + telefonoTO.getMobileTO().getStatus() + " -- Motivo: " + telefonoTO.getMobileTO().getMotivo());
                }
                if (estatusPuntos.equals("R")) {
                    this.EstatusPuntosDist(connection, reservacionTO.getTelefonoSimpleTO().getCuenta());
                }
                if (estatusPuntos.equals("C")) {
                    throw new CAException(-1, "Los puntos de la linea se encuentran congelados.");
                }
                if (!Utils.getValEstatusCobranza((String)reservacionTO.getMobileTO().getEstCobranza(), (int)reservacionTO.getRegion(), (String)"Reden", (String)reservacionTO.getMobileTO().getMotivo())) {
                    throw new CAException(-1, "El estatus de cobranza de la linea: " + reservacionTO.getEstatus() + ", no es valido para la redencion de promociones.");
                }
                if ((long)reservacionTO.getPuntosRedimidosTO().getPtosTotalesTemp() < reservacionTO.getProductosTO().getPtosARedimir()) {
                    throw new CAException(-1, "No se cuentan con los suficientes puntos para realizar la redencion.");
                }
                String claseCredito = telefonoTO.getMobileTO().getClaseCredit();
                if ((claseCredito.trim().equals("IM") || claseCredito.trim().equals("FO")) && !(fzaVentasAutorizada = (distribuidoresDAO = new DistribuidoresDAO()).validaFzaVentasImssFonacot(fzaVentas, claseCredito, connection))) {
                    throw new CAException(-1, "La cuenta tiene una clase de credito " + telefonoTO.getMobileTO().getClaseCredit().toUpperCase().trim() + " , por lo que solo podra realizar " + " la redencion de puntos el Distribuidor Autorizado.");
                }
            } else {
                if (!Utils.getValEstatusCobranza((String)reservacionTO.getMobileTO().getEstCobranza(), (int)reservacionTO.getRegion(), (String)"Reden", (String)reservacionTO.getMobileTO().getMotivo())) {
                    reservacionTO.agregaMensaje(1, "El estatus de cobranza de la linea: " + reservacionTO.getEstatus() + ", no es valido para la redencion de promociones.");
                    ReservacionTO reservacionTO2 = reservacionTO;
                    return reservacionTO2;
                }
                if ((long)reservacionTO.getPuntosRedimidosTO().getPtosTotalesTemp() < reservacionTO.getProductosTO().getPtosARedimir()) {
                    reservacionTO.agregaMensaje(1, "No se cuentan con los suficientes puntos para realizar la redencion.");
                    ReservacionTO reservacionTO3 = reservacionTO;
                    return reservacionTO3;
                }
            }
            reservacionTO.setTipoRedencion(Redencion.validaTipoRedencion((String)reservacionTO.getTipoRedencion()));
            String sFolioReservacion = Utils.generaFolio((String)reservacionTO.getTelefonoSimpleTO().getLinea());
            if (sFolioReservacion.length() <= 0) {
                if (isDistribuidores) {
                    throw new CAException(-1, "El numero de folio no es valido.");
                }
                reservacionTO.agregaMensaje(1, "El numero de folio no es valido.");
                ReservacionTO reservacionTO4 = reservacionTO;
                return reservacionTO4;
            }
            long fechaTransaccion = System.currentTimeMillis();
            reservacionTO.setFolio(String.valueOf(sFolioReservacion));
            reservacionTO.setFolioHex(Long.toHexString(new Long(sFolioReservacion)));
            reservacionTO.setFechaExpiracion(Utils.fechaExpiracion((boolean)isDistribuidores));
            reservacionTO.setFechaAdendumNuevo(new java.util.Date(fechaTransaccion));
            reservacionTO.setFechaOperacion(new java.util.Date(fechaTransaccion));
            MensajeTO mensajeTO = puntosDAO.actualizaLinea(connection, reservacionTO.getTelefonoSimpleTO().getCuenta(), reservacionTO.getTelefonoSimpleTO().getSecuencia(), "R");
            if (mensajeTO.getIdMensaje() == 0) {
                mensajeTO = puntosDAO.insertReservacion(connection, reservacionTO, fechaTransaccion, isDistribuidores);
            }
            if (mensajeTO.getIdMensaje() == 0) {
                connection.commit();
            } else {
                connection.rollback();
            }
            reservacionTO.agregaMensaje(mensajeTO.getIdMensaje(), mensajeTO.getMensaje());
            return reservacionTO;
        }
        catch (SQLException e) {
            if (connection == null) throw new CAException(-1, "RedencionDAO.realizaApartado.SQLException[" + e.toString() + "]", (Exception)e);
            try {
                connection.rollback();
                throw new CAException(-1, "RedencionDAO.realizaApartado.SQLException[" + e.toString() + "]", (Exception)e);
            }
            catch (Exception fechaTransaccion) {
                // empty catch block
            }
            throw new CAException(-1, "RedencionDAO.realizaApartado.SQLException[" + e.toString() + "]", (Exception)e);
        }
        catch (Exception e) {
            if (connection == null) throw new CAException(-1, "RedencionDAO.realizaApartado.Error[" + e.toString() + "]", e);
            try {
                connection.rollback();
                throw new CAException(-1, "RedencionDAO.realizaApartado.Error[" + e.toString() + "]", e);
            }
            catch (Exception fechaTransaccion) {
                // empty catch block
            }
            throw new CAException(-1, "RedencionDAO.realizaApartado.Error[" + e.toString() + "]", e);
        }
        finally {
            if (connection != null) {
                try {
                    connection.close();
                    connection = null;
                }
                catch (Exception var13_20) {}
            }
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     */
    public MensajeTO cancelaApartado(ParametrosTO parametrosTO, UsuarioTO usuarioTO, boolean isDistribuidores) throws CAException {
        Connection connection = null;
        MensajeTO mensajeTO = new MensajeTO();
        String comentario = null;
        RedencionTO redencionTO = null;
        int totAjustes = 0;
        try {
            long fechaTransaccion = System.currentTimeMillis();
            boolean conRedencion = false;
            if (parametrosTO.getFolio() == null || parametrosTO.getFolio().trim().equals("")) {
                mensajeTO.agregaMensaje(-1, "El numero de folio no es valido.");
                MensajeTO mensajeTO2 = mensajeTO;
                return mensajeTO2;
            }
            if (isDistribuidores) {
                if (!(parametrosTO.getEstatus().equals("C") || parametrosTO.getEstatus().equals("P") || parametrosTO.getEstatus().equals("A"))) {
                    mensajeTO.agregaMensaje(-1, "El tipo de estatus " + parametrosTO.getEstatus() + " no es valido para la reservacion.");
                    MensajeTO mensajeTO3 = mensajeTO;
                    return mensajeTO3;
                }
            } else if (!(parametrosTO.getEstatus().equals("C") || parametrosTO.getEstatus().equals("P"))) {
                mensajeTO.agregaMensaje(-1, "El tipo de estatus " + parametrosTO.getEstatus() + " no es valido para la reservacion.");
                MensajeTO mensajeTO4 = mensajeTO;
                return mensajeTO4;
            }
            ConsultasDAO consultasDAO = new ConsultasDAO();
            ReservacionTO reservacionTO = consultasDAO.obtieneReservacion(parametrosTO.getFolio(), parametrosTO.getEstatus(), true);
            if (isDistribuidores && !parametrosTO.getFzaVentas().equals(reservacionTO.getFuerzaVenta())) {
                mensajeTO.agregaMensaje(-1, "La fuerza de ventas que va a cancelar la reservacion es incorrecta.");
                MensajeTO mensajeTO5 = mensajeTO;
                return mensajeTO5;
            }
            if (reservacionTO.getIdMensaje() == 1 || mensajeTO.getIdMensaje() == 1) {
                mensajeTO.agregaMensaje(reservacionTO.getIdMensaje(), reservacionTO.getMensaje());
                MensajeTO mensajeTO6 = mensajeTO;
                return mensajeTO6;
            }
            if (!(isDistribuidores || SeguridadCaUtil.getInstance().validaPerfilProcesoEcac(usuarioTO.getPerfilTO(), "154"))) {
                mensajeTO.agregaMensaje(1, "Su perfil no tiene los privilegios para realizar esta operacion.");
                MensajeTO mensajeTO7 = mensajeTO;
                return mensajeTO7;
            }
            if (parametrosTO.getEstatus().equals("C") && !reservacionTO.getEstatus().equals(parametrosTO.getEstatus())) {
                redencionTO = consultasDAO.ultimaRedencion(reservacionTO.getTelefonoSimpleTO().getCuenta(), reservacionTO.getTelefonoSimpleTO().getSecuencia());
                if (redencionTO.getFolio() != null && redencionTO.getFolio().equals(parametrosTO.getFolio())) {
                    if (!reservacionTO.getFolio().equals(redencionTO.getFolio())) {
                        mensajeTO.agregaMensaje(1, "El folio ingresado no corresponde a la redencion mas actual con numero de folio " + reservacionTO.getFolio() + " y fecha de operacion " + Constantes.DATEFORMATdd_MM_YYYY.format(reservacionTO.getFechaOperacion()) + ".");
                        MensajeTO mensajeTO8 = mensajeTO;
                        return mensajeTO8;
                    }
                    if (redencionTO.getFechaFolio() == null) {
                        mensajeTO.agregaMensaje(1, "Debe eligir un registro para la Cancelacion.");
                        MensajeTO mensajeTO9 = mensajeTO;
                        return mensajeTO9;
                    }
                    if (Utils.calcularDiasEntreFechas((long)redencionTO.getFechaFolio().getTime(), (long)fechaTransaccion) > 30) {
                        mensajeTO.agregaMensaje(1, "La Fecha de la Cancelacion no debe ser mayor 30 dias.");
                        MensajeTO mensajeTO10 = mensajeTO;
                        return mensajeTO10;
                    }
                    conRedencion = true;
                }
            } else if (parametrosTO.getEstatus().equals("C") && reservacionTO.getEstatus().equals(parametrosTO.getEstatus())) {
                mensajeTO.agregaMensaje(0, "Este numero de folio ya fue cancelado previamente.");
                MensajeTO mensajeTO11 = mensajeTO;
                return mensajeTO11;
            }
            PuntosDAO puntosDAO = new PuntosDAO();
            PuntosTO puntosTO = null;
            PuntosRedimidosTO puntosRedimidosTO = null;
            if (conRedencion) {
                if (!(isDistribuidores || usuarioTO.getPerfilTO() == null || SeguridadCaUtil.getInstance().validaPerfilProcesoEcac(usuarioTO.getPerfilTO(), "155"))) {
                    mensajeTO.agregaMensaje(1, "Su perfil no tiene los privilegios para la cancelacion de redenciones.");
                    MensajeTO mensajeTO12 = mensajeTO;
                    return mensajeTO12;
                }
                puntosTO = consultasDAO.obtienePuntos(reservacionTO.getTelefonoSimpleTO().getCuenta(), reservacionTO.getTelefonoSimpleTO().getSecuencia());
                if (puntosTO.getIdMensaje() != 0) {
                    MensajeTO mensajeTO13 = puntosTO.obtieneMensajeTO();
                    return mensajeTO13;
                }
                puntosRedimidosTO = consultasDAO.obtienePuntosRedimidos(reservacionTO.getTelefonoSimpleTO().getCuenta(), redencionTO.getFechaFolio());
                if (puntosRedimidosTO.getIdMensaje() != 0) {
                    MensajeTO mensajeTO14 = puntosRedimidosTO.obtieneMensajeTO();
                    return mensajeTO14;
                }
                if (puntosTO.getPtsExcedentes() < puntosRedimidosTO.getPtsSobrantes1()) {
                    mensajeTO.agregaMensaje(1, "No es posible realizar la cancelacion de puntos, ya que la linea no cuenta con los puntos sobrantes del bono.");
                    MensajeTO mensajeTO15 = mensajeTO;
                    return mensajeTO15;
                }
            }
            connection = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
            connection.setAutoCommit(false);
            if (conRedencion && mensajeTO.getIdMensaje() == 0) {
                if (mensajeTO.getIdMensaje() == 0) {
                    comentario = isDistribuidores ? "SISACT CANCELA:" + parametrosTO.getUsuariMovimiento() : "E-CAC CANCELA:" + parametrosTO.getUsuariMovimiento() + " COMENT: " + parametrosTO.getComentario();
                    mensajeTO = puntosDAO.insertaDetalle(connection, fechaTransaccion, comentario, 11, puntosRedimidosTO.getPtsRedimidos(), null, reservacionTO.getTelefonoSimpleTO().getCuenta(), reservacionTO.getTelefonoSimpleTO().getSecuencia(), reservacionTO.getTelefonoSimpleTO().getLinea(), parametrosTO.getUsuariMovimiento());
                }
                if (mensajeTO.getIdMensaje() == 0) {
                    PuntosRedimidosTO redimidosTO = Utils.regresaPuntos((PuntosTO)puntosTO, (PuntosRedimidosTO)puntosRedimidosTO);
                    mensajeTO = puntosDAO.actualizaPuntos(redimidosTO, connection, null, reservacionTO.getTelefonoSimpleTO().getCuenta(), reservacionTO.getTelefonoSimpleTO().getSecuencia(), true);
                }
                if (mensajeTO.getIdMensaje() == 0) {
                    mensajeTO = puntosDAO.actualizaRedencion(connection, "I", redencionTO.getFechaFolio(), reservacionTO.getTelefonoSimpleTO().getCuenta(), new Timestamp(fechaTransaccion), parametrosTO.getUsuariMovimiento());
                }
                if (mensajeTO.getIdMensaje() == 0) {
                    mensajeTO = puntosDAO.actualizaConstancia(connection, "I", null, null, null, redencionTO.getFechaFolio(), reservacionTO.getTelefonoSimpleTO().getCuenta(), reservacionTO.getTelefonoSimpleTO().getSecuencia());
                }
                if (mensajeTO.getIdMensaje() == 0 && (puntosRedimidosTO.getPtsMinimos() > 0 || puntosRedimidosTO.getBonoProrrateo() > 0)) {
                    if (puntosRedimidosTO.getPtsMinimos() > 0) {
                        totAjustes = puntosRedimidosTO.getPtsMinimos() * -1;
                        comentario = "CANCELA PTOS. MINIMOS x CANC. DE REDENCION.";
                    } else if (puntosRedimidosTO.getBonoProrrateo() > 0) {
                        totAjustes = puntosRedimidosTO.getBonoProrrateo() * -1;
                        comentario = "CANCELA PTOS. PRORRATEO x CANC. DE REDENCION";
                    }
                    mensajeTO = puntosDAO.insertaDetalle(connection, fechaTransaccion, comentario, 18, totAjustes, null, reservacionTO.getTelefonoSimpleTO().getCuenta(), reservacionTO.getTelefonoSimpleTO().getSecuencia(), reservacionTO.getTelefonoSimpleTO().getLinea(), parametrosTO.getUsuariMovimiento());
                }
                if (mensajeTO.getIdMensaje() == 0 && puntosRedimidosTO.getPtsSobrantes1() > 0) {
                    comentario = "Elimina Pts Sobrantes Bono Equipo.";
                    totAjustes = puntosRedimidosTO.getPtsSobrantes1() * -1;
                    mensajeTO = puntosDAO.insertaDetalle(connection, fechaTransaccion, comentario, 53, totAjustes, null, reservacionTO.getTelefonoSimpleTO().getCuenta(), reservacionTO.getTelefonoSimpleTO().getSecuencia(), reservacionTO.getTelefonoSimpleTO().getLinea(), parametrosTO.getUsuariMovimiento());
                }
                if (mensajeTO.getIdMensaje() == 0) {
                    comentario = "CIR -CANCELA -" + puntosRedimidosTO.getPtsRedimidos() + "PTOS. A PET. DE " + parametrosTO.getUsuariMovimiento() + " " + parametrosTO.getComentario();
                    mensajeTO = puntosDAO.insertaComentarioTMP(connection, reservacionTO.getTelefonoSimpleTO().getRegion(), reservacionTO.getTelefonoSimpleTO().getCuenta(), parametrosTO.getUsuariMovimiento(), fechaTransaccion, comentario);
                }
            }
            if (mensajeTO.getIdMensaje() == 0) {
                mensajeTO = puntosDAO.actualizaReservacion(connection, fechaTransaccion, parametrosTO.getUsuariMovimiento(), parametrosTO.getEstatus(), parametrosTO.getComentario(), parametrosTO.getFolio());
            }
            if (mensajeTO.getIdMensaje() != 0) {
                connection.rollback();
                return mensajeTO;
            }
            if (mensajeTO.getIdMensaje() == 0 && "C".equals(parametrosTO.getEstatus())) {
                mensajeTO = puntosDAO.actualizaLinea(connection, reservacionTO.getTelefonoSimpleTO().getCuenta(), reservacionTO.getTelefonoSimpleTO().getSecuencia(), "0");
            }
            if (mensajeTO.getIdMensaje() == 0) {
                connection.commit();
                return mensajeTO;
            }
            connection.rollback();
            return mensajeTO;
        }
        catch (SQLException e) {
            if (connection == null) throw new CAException(-1, "RedencionDAO.cancelaApartado.SQLException[" + e.toString() + "]", (Exception)e);
            try {
                connection.rollback();
                throw new CAException(-1, "RedencionDAO.cancelaApartado.SQLException[" + e.toString() + "]", (Exception)e);
            }
            catch (Exception var10_49) {
                // empty catch block
            }
            throw new CAException(-1, "RedencionDAO.cancelaApartado.SQLException[" + e.toString() + "]", (Exception)e);
        }
        catch (Exception e) {
            throw new CAException(-1, "RedencionDAO.cancelaApartado.Error[" + e.toString() + "]", e);
        }
        finally {
            if (connection != null) {
                try {
                    connection.close();
                    connection = null;
                }
                catch (Exception var20_27) {}
            }
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     */
    public MensajeTO cancelaRedencion(ParametrosTO parametrosTO, String fechaOperacion, int diasValidacion, UsuarioTO usuarioTO) throws CAException {
        MensajeTO mensajeTO = new MensajeTO();
        Connection connection = null;
        try {
            Calendar fechaInicial = Calendar.getInstance();
            Calendar fechaFinal = Calendar.getInstance();
            fechaInicial.setTime(new java.util.Date(new Long(fechaOperacion)));
            if (!(usuarioTO.getPerfilTO() == null || SeguridadCaUtil.getInstance().validaPerfilProcesoCa(usuarioTO.getPerfilTO(), "148") && diasValidacion != 0)) {
                mensajeTO.agregaMensaje(1, "Su perfil no tiene los privilegios para realizar esta operacion.");
                MensajeTO mensajeTO2 = mensajeTO;
                return mensajeTO2;
            }
            if (diasValidacion != 0 && Utils.calcularDiasEntreFechas((long)fechaInicial.getTimeInMillis(), (long)fechaFinal.getTimeInMillis()) > (long)diasValidacion) {
                mensajeTO.agregaMensaje(1, "La Fecha de la Cancelacion no debe ser mayor a " + diasValidacion + " dias.");
                MensajeTO mensajeTO3 = mensajeTO;
                return mensajeTO3;
            }
            ConsultasDAO consultasDAO = new ConsultasDAO();
            PuntosTO puntosTO = consultasDAO.obtienePuntos(parametrosTO.getCuenta(), parametrosTO.getSecuencia());
            if (puntosTO.getIdMensaje() != 0) {
                MensajeTO mensajeTO4 = puntosTO.obtieneMensajeTO();
                return mensajeTO4;
            }
            PuntosRedimidosTO redimidosTO = consultasDAO.obtienePuntosRedimidos(parametrosTO.getCuenta(), new Timestamp(new Long(fechaOperacion)));
            if (redimidosTO.getIdMensaje() != 0) {
                MensajeTO mensajeTO5 = redimidosTO.obtieneMensajeTO();
                return mensajeTO5;
            }
            connection = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
            connection.setAutoCommit(false);
            String referencia = "CANCELA:" + parametrosTO.getUsuariMovimiento() + " COMENT:" + parametrosTO.getComentario();
            PuntosDAO puntosDAO = new PuntosDAO();
            long fechaTransaccion = System.currentTimeMillis();
            mensajeTO = puntosDAO.insertaDetalle(connection, fechaTransaccion, referencia, 11, redimidosTO.getPtsRedimidos(), null, parametrosTO.getCuenta(), parametrosTO.getSecuencia(), parametrosTO.getTelefono(), parametrosTO.getUsuariMovimiento());
            redimidosTO.setPtsRenta(redimidosTO.getPtsRenta() + redimidosTO.getPtsRentaRedimidos());
            redimidosTO.setPtsPorVencer(redimidosTO.getPtsPorVencer() + redimidosTO.getPtsPorVencerRedimidos());
            redimidosTO.setPtsPorVencer1(redimidosTO.getPtsPorVencer1() + redimidosTO.getPtsPorVencer1Redimidos());
            redimidosTO.setPtsPorVencer2(redimidosTO.getPtsPorVencer2() + redimidosTO.getPtsPorVencer2Redimidos());
            PuntosRedimidosTO puntosRedimidosTO = Utils.regresaPuntos((PuntosTO)puntosTO, (PuntosRedimidosTO)redimidosTO);
            if (puntosRedimidosTO.getPtsRedimidos() < 0) {
                puntosRedimidosTO.setPtsRedimidos(0);
            }
            if (mensajeTO.getIdMensaje() == 0) {
                mensajeTO = puntosDAO.actualizaPuntos(puntosRedimidosTO, connection, null, parametrosTO.getCuenta(), parametrosTO.getSecuencia(), true);
            }
            if (mensajeTO.getIdMensaje() == 0) {
                mensajeTO = puntosDAO.actualizaRedencion(connection, "I", new Timestamp(new Long(fechaOperacion)), parametrosTO.getCuenta(), new Timestamp(fechaTransaccion), parametrosTO.getUsuariMovimiento());
            }
            if (mensajeTO.getIdMensaje() == 0) {
                mensajeTO = puntosDAO.actualizaConstancia(connection, "I", null, null, null, new Timestamp(new Long(fechaOperacion)), parametrosTO.getCuenta(), parametrosTO.getSecuencia());
            }
            if (mensajeTO.getIdMensaje() == 0) {
                mensajeTO = puntosDAO.eliminaBonoDescuento(connection, parametrosTO.getCuenta(), parametrosTO.getFolio());
            }
            if (mensajeTO.getIdMensaje() == 0 && redimidosTO.getPtsMinimos() > 0) {
                referencia = "CANCELA PTOS. MINIMOS x CANC. DE REDENCION";
                mensajeTO = puntosDAO.insertaDetalle(connection, fechaTransaccion, referencia, 18, redimidosTO.getPtsMinimos() * -1, null, parametrosTO.getCuenta(), parametrosTO.getSecuencia(), parametrosTO.getTelefono(), parametrosTO.getUsuariMovimiento());
            } else if (mensajeTO.getIdMensaje() == 0 && redimidosTO.getBonoProrrateo() > 0) {
                referencia = "CANCELA PTOS. PRORRATEO x CANC. DE REDENCION";
                mensajeTO = puntosDAO.insertaDetalle(connection, fechaTransaccion, referencia, 18, redimidosTO.getBonoProrrateo() * -1, null, parametrosTO.getCuenta(), parametrosTO.getSecuencia(), parametrosTO.getTelefono(), parametrosTO.getUsuariMovimiento());
            }
            if (mensajeTO.getIdMensaje() == 0 && redimidosTO.getPtsSobrantes1() > 0) {
                referencia = "Elimina Pts Sobrantes Bono Equipo";
                mensajeTO = puntosDAO.insertaDetalle(connection, fechaTransaccion, referencia, 53, redimidosTO.getPtsSobrantes1() * -1, null, parametrosTO.getCuenta(), parametrosTO.getSecuencia(), parametrosTO.getTelefono(), parametrosTO.getUsuariMovimiento());
            }
            referencia = "CIR -CANCELA -" + (redimidosTO.getPtsRedimidos() - redimidosTO.getPtsMinimos()) + "PTOS. A PET. DE " + parametrosTO.getUsuariMovimiento() + " " + parametrosTO.getComentario();
            mensajeTO = puntosDAO.insertaComentarioTMP(connection, parametrosTO.getRegion(), parametrosTO.getCuenta(), parametrosTO.getUsuariMovimiento(), fechaTransaccion, referencia);
            if (mensajeTO.getIdMensaje() == 0) {
                connection.commit();
                return mensajeTO;
            }
            connection.rollback();
            return mensajeTO;
        }
        catch (SQLException e) {
            if (connection == null) throw new CAException(-1, "RedencionDAO.cancelaApartado.SQLException[" + e.toString() + "]", (Exception)e);
            try {
                connection.rollback();
                throw new CAException(-1, "RedencionDAO.cancelaApartado.SQLException[" + e.toString() + "]", (Exception)e);
            }
            catch (Exception fechaFinal) {
                // empty catch block
            }
            throw new CAException(-1, "RedencionDAO.cancelaApartado.SQLException[" + e.toString() + "]", (Exception)e);
        }
        catch (Exception e) {
            throw new CAException(-1, "RedencionDAO.cancelaApartado.Error[" + e.toString() + "]", e);
        }
        finally {
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                    connection.close();
                    connection = null;
                }
                catch (Exception var19_16) {}
            }
        }
    }

    public boolean aplicaRedencionSisact(String folio, TelefonoServiceTO telefonoTO, String claveSisact) throws CAException {
        Connection conexion = null;
        ConsultasDAO consulta = new ConsultasDAO();
        long folioDec = Long.parseLong(folio, 16);
        try {
            try {
                int fechaVenc;
                conexion = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                conexion.setAutoCommit(false);
                ReservacionTO reservacionTO = consulta.obtieneReservacion(String.valueOf(folioDec), null, false);
                MensajeTO mensajeTO = reservacionTO.obtieneMensajeTO();
                if (mensajeTO != null && mensajeTO.getIdMensaje() == 1) {
                    throw new CAException(-1, mensajeTO.getMensaje());
                }
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd", new Locale("en", "US"));
                int fechaAct = Integer.parseInt(sdf.format(new java.util.Date()));
                if (fechaAct > (fechaVenc = Integer.parseInt(sdf.format(reservacionTO.getFechaExpiracion()))) && reservacionTO.getEstatus().equals("A")) {
                    System.out.println("Si la reservacion ya expiro, el status es A y no existe otra reservacion, se libera el status de puntos en PTO_TBLLINEAS y se cancela reservacion.");
                    consulta.cancelaReservacionSisact(String.valueOf(folioDec), telefonoTO.getCuenta(), telefonoTO.getSecuencia(), reservacionTO.getFuerzaVenta(), conexion);
                }
                ArrayList<int[]> puntosRedecionSisact = consulta.calculaPuntos(reservacionTO, telefonoTO);
                consulta.actualizaRedencion(String.valueOf(folioDec), claveSisact, telefonoTO, reservacionTO, puntosRedecionSisact, conexion);
                conexion.commit();
            }
            catch (SQLException e) {
                if (conexion != null) {
                    try {
                        conexion.rollback();
                    }
                    catch (Exception mensajeTO) {
                        // empty catch block
                    }
                }
                System.out.println(e.toString());
                throw new CAException(-1, "RedencionDAO.aplicaRedencionSisact.Error[" + e.toString() + "]", (Exception)e);
            }
            catch (CAException e) {
                if (conexion != null) {
                    try {
                        conexion.rollback();
                    }
                    catch (Exception mensajeTO) {
                        // empty catch block
                    }
                }
                System.out.println(e.toString());
                throw new CAException(-1, "RedencionDAO.aplicaRedencionSisact.Error[" + e.toString() + "]", (Exception)e);
            }
            catch (Exception e) {
                if (conexion != null) {
                    try {
                        conexion.rollback();
                    }
                    catch (Exception mensajeTO) {
                        // empty catch block
                    }
                }
                System.out.println(e.toString());
                throw new CAException(-1, "RedencionDAO.aplicaRedencionSisact.Error[" + e.toString() + "]", e);
            }
        }
        finally {
            if (conexion != null) {
                try {
                    conexion.setAutoCommit(true);
                    conexion.close();
                    conexion = null;
                }
                catch (Exception var15_21) {}
            }
        }
        return true;
    }

    private String getEstatusPuntos(String cuenta, Connection conn, int secuencia) {
        String Status;
        block21 : {
            ResultSet resultSet = null;
            PreparedStatement stmt = null;
            Status = "";
            StringBuffer query = new StringBuffer();
            query.append("SELECT A.STATUSPUNTOS FROM ").append(this.schema_database).append("PTO_TBLLINEAS A, ");
            query.append(this.schema_database).append("PTO_TBLRESERVACIONES B WHERE A.CUENTA = ? AND A.SECUENCIA= ? ");
            query.append(" AND A.CUENTA = B.CUENTA AND A.SECUENCIA=B.SECUENCIA ");
            query.append(" ORDER BY B.FECHAOPER DESC ");
            try {
                try {
                    stmt = conn.prepareStatement(query.toString());
                    stmt.setString(1, cuenta);
                    stmt.setInt(2, secuencia);
                    resultSet = stmt.executeQuery();
                    stmt = conn.prepareStatement(query.toString());
                    while (resultSet.next()) {
                        Status = resultSet.getString("STATUSPUNTOS");
                    }
                }
                catch (SQLException e) {
                    e.printStackTrace();
                    if (resultSet != null) {
                        try {
                            resultSet.close();
                            resultSet = null;
                        }
                        catch (Exception var10_9) {
                            // empty catch block
                        }
                    }
                    if (stmt == null) break block21;
                    try {
                        stmt.close();
                        stmt = null;
                    }
                    catch (Exception var10_10) {}
                }
            }
            finally {
                if (resultSet != null) {
                    try {
                        resultSet.close();
                        resultSet = null;
                    }
                    catch (Exception var10_13) {}
                }
                if (stmt != null) {
                    try {
                        stmt.close();
                        stmt = null;
                    }
                    catch (Exception var10_14) {}
                }
            }
        }
        return Status;
    }

    private void EstatusPuntosDist(Connection conn, String cuenta) throws CAException {
        ResultSet resultSetFvta = null;
        PreparedStatement statementFvta = null;
        String distReserva = "";
        Date fechaReservacion = null;
        StringBuffer query = new StringBuffer();
        query.append(" SELECT B.FZAVTA, B.FECHAOPER ");
        query.append("   FROM  ").append(this.schema_database).append("PTO_TBLLINEAS A,  ").append(this.schema_database).append("PTO_TBLRESERVACIONES B ");
        query.append("  WHERE ").append("B.CUENTA = ? ");
        query.append("   AND A.CUENTA = B.CUENTA AND A.STATUSPUNTOS='R' ");
        query.append(" ORDER BY B.FECHAOPER DESC ");
        try {
            try {
                statementFvta = conn.prepareStatement(query.toString());
                statementFvta.setString(1, cuenta);
                resultSetFvta = statementFvta.executeQuery();
                if (resultSetFvta.next()) {
                    distReserva = resultSetFvta.getString(1);
                    fechaReservacion = resultSetFvta.getDate(2);
                    throw new CAException(-1, "Los puntos estan reservados por el Distribuidor: " + distReserva + " el dia:" + Constantes.DATEFORMATdd_MM_YYYY.format(new java.util.Date(fechaReservacion.getTime())));
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                if (resultSetFvta != null) {
                    try {
                        resultSetFvta.close();
                        resultSetFvta = null;
                    }
                    catch (Exception var10_9) {
                        // empty catch block
                    }
                }
                if (statementFvta != null) {
                    try {
                        statementFvta.close();
                        statementFvta = null;
                    }
                    catch (Exception var10_10) {}
                }
            }
        }
        finally {
            if (resultSetFvta != null) {
                try {
                    resultSetFvta.close();
                    resultSetFvta = null;
                }
                catch (Exception var10_13) {}
            }
            if (statementFvta != null) {
                try {
                    statementFvta.close();
                    statementFvta = null;
                }
                catch (Exception var10_14) {}
            }
        }
    }

    public Hashtable<String, Object> validaProductoDescuento(String telefono, int region, String cuenta, String endPoint, String usuario) throws CAException {
        Hashtable<String, Object> hashtable;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        hashtable = null;
        MensajeTO mensajeTO = new MensajeTO();
        String productos = "";
        String cudis = "";
        PlanTO[] planes = null;
        try {
            try {
            	com.telcel.crm.scrapy.beans.beneficios.Cudi2TO cudisResponse = null;
            	CudisTO response = null;
                AplicaBeneficioProxy proxy = new AplicaBeneficioProxy();
                Catalogo properties = new Catalogo();
                properties.setTabla("propiedades");
                properties.cargaCatalogo();
                boolean aplicaCudi2 = new Boolean(properties.getPropiedad("APLICA.WS.CUDI2"));
                if (aplicaCudi2) {
                    cudisResponse = null;
                    proxy.setEndpoint(endPoint);
                    cudisResponse = proxy.consultaPlanDescuentoRenta(cuenta, telefono, region);
                    planes = cudisResponse.getPlanesDescuento();
                    cudis = "CUDI2";
                } else {
                	response = null;
                    proxy.setEndpoint(endPoint);
                    response = proxy.consultaPlanDescuento(cuenta, telefono, region);
                    planes = cudisResponse.getPlanesDescuento();
                    cudis = "CUDIS";
                }
                if (planes != null && planes.length > 0) {
                    PlanTO planDescuentoTO = null;
                    mensajeTO.agregaMensaje(0, "Proceso Exitoso");
                    connection = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                    String sQuery = "SELECT TIPOPROD  FROM " + this.schema_database + "PTO_CTLPRODGRAL WHERE IDPROD =?";
                    statement = connection.prepareStatement(sQuery);
                    for (int i = 0; i < planes.length; ++i) {
                        planDescuentoTO = planes[i];
                        statement.clearParameters();
                        statement.setString(1, planDescuentoTO.getPlanDescuento());
                        resultSet = statement.executeQuery();
                        if (resultSet.next()) {
                            String planDescuento = resultSet.getString(1);
                            if (planDescuento != null && (planDescuento.substring(0, 2).equals("AM") || planDescuento.substring(2, 2).equals("AM"))) {
                                mensajeTO.agregaMensaje(1, " El cliente cuenta con otra promoci\u00f3n de T.A dada de alta(Minutos Dobles), favor de verificar la pantalla " + cudis + " desde Mobile");
                            } else {
                                productos = String.valueOf(productos) + planDescuento;
                            }
                        }
                        if (resultSet == null) continue;
                        try {
                            resultSet.close();
                            resultSet = null;
                            continue;
                        }
                        catch (Exception planDescuento) {
                            // empty catch block
                        }
                    }
                } else {
                    mensajeTO.agregaMensaje(0, "No se encontraron planes en M2K");
                }
                hashtable = new Hashtable<String, Object>();
                hashtable.put("planDescuento", productos);
                hashtable.put("mensaje", (Object)mensajeTO);
            }
            catch (SQLException e) {
                throw new CAException(-1, "RedencionDAO.validaProductoDescuento.SQLException[" + e.toString() + "]", (Exception)e);
            }
            catch (Exception e) {
                throw new CAException(-1, "RedencionDAO.validaProductoDescuento.Error[" + e.toString() + "]", e);
            }
        }
        finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                    resultSet = null;
                }
                catch (Exception var22_28) {}
            }
            if (statement != null) {
                try {
                    statement.close();
                    statement = null;
                }
                catch (Exception var22_29) {}
            }
            if (connection != null) {
                try {
                    connection.close();
                    connection = null;
                }
                catch (Exception var22_30) {}
            }
        }
        return hashtable;
    }
}

