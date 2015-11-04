/*
 * Decompiled with CFR 0_102.
 * 
 * Could not load the following classes:
 *  com.claro.catalogo.Catalogo
 *  com.claro.exception.CAException
 *  com.claro.transfer.AlianzasTO
 *  com.claro.transfer.CatalogoTO
 *  com.claro.transfer.CicloFacturacionTO
 *  com.claro.transfer.CuentaPadreTO
 *  com.claro.transfer.MensajeTO
 *  com.claro.transfer.MobileTO
 *  com.claro.transfer.MovimientoTO
 *  com.claro.transfer.ParametrosTO
 *  com.claro.transfer.PerfilTO
 *  com.claro.transfer.PlanTO
 *  com.claro.transfer.ProductosTO
 *  com.claro.transfer.PuntoVentaTO
 *  com.claro.transfer.PuntosRedimidosTO
 *  com.claro.transfer.PuntosTO
 *  com.claro.transfer.RedencionTO
 *  com.claro.transfer.ReservacionTO
 *  com.claro.transfer.TelefonoSimpleTO
 *  com.claro.transfer.TelefonoTO
 *  com.claro.transfer.UsuarioTO
 *  com.claro.transfer.service.ReservacionServiceTO
 *  com.claro.transfer.service.TelefonoServiceTO
 *  com.claro.util.Constantes
 *  com.claro.util.Redencion
 *  com.claro.util.ServiceLocator
 *  com.claro.util.Utils
 *  org.apache.log4j.Logger
 */
package com.claro.dao;

import com.claro.catalogo.Catalogo;
import com.claro.dao.AlianzasDAO;
import com.claro.dao.CatalogoDAO;
import com.claro.dao.ConsultaM2KDAO;
import com.claro.dao.ConsultasGapDAO;
import com.claro.dao.DistribuidoresDAO;
import com.claro.dao.PuntosDAO;
import com.claro.exception.CAException;
import com.claro.services.ClienteConsultas;
import com.claro.transfer.AlianzasTO;
import com.claro.transfer.CatalogoTO;
import com.claro.transfer.CicloFacturacionTO;
import com.claro.transfer.CuentaPadreTO;
import com.claro.transfer.MensajeTO;
import com.claro.transfer.MobileTO;
import com.claro.transfer.MovimientoTO;
import com.claro.transfer.ParametrosTO;
import com.claro.transfer.PerfilTO;
import com.claro.transfer.PlanTO;
import com.claro.transfer.ProductosTO;
import com.claro.transfer.PuntoVentaTO;
import com.claro.transfer.PuntosRedimidosTO;
import com.claro.transfer.PuntosTO;
import com.claro.transfer.RedencionTO;
import com.claro.transfer.ReservacionTO;
import com.claro.transfer.TelefonoSimpleTO;
import com.claro.transfer.TelefonoTO;
import com.claro.transfer.UsuarioTO;
import com.claro.transfer.service.ReservacionServiceTO;
import com.claro.transfer.service.TelefonoServiceTO;
import com.claro.util.Constantes;
import com.claro.util.Redencion;
import com.claro.util.ServiceLocator;
import com.claro.util.Utils;

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
import java.util.GregorianCalendar;

import org.apache.log4j.Logger;

public class ConsultasDAO {
    protected final Logger logger = Logger.getLogger((String)"loggerCirculoAzul");
    protected final Logger loggerPuntos = Logger.getLogger((String)"loggerConsultaPuntos");
    protected final Logger error = Logger.getLogger((String)"loggerCirculoAzulError");
    private ConsultaM2KDAO consultaM2KDAO = new ConsultaM2KDAO();
    private PuntosDAO puntosDAO = new PuntosDAO();
    private ConsultasGapDAO consultasGapDAO = new ConsultasGapDAO();
    private String schema_database;

    public ConsultasDAO() {
        try {
            this.schema_database = ServiceLocator.getInstance().getVariable(ServiceLocator.schema_database);
        }
        catch (Exception e) {
            this.error.error((Object)"ConsultasDAO", (Throwable)e);
        }
    }

    public ArrayList<RedencionTO> detalleRedencion(String cuenta, int secuencia, String fecha) throws CAException {
        ArrayList<RedencionTO> redenciones;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        long fechaOperacion = 0;
        java.util.Date fechaOper = null;
        String busqueda = "";
        if (fecha != null) {
            if (fecha.startsWith("m")) {
                throw new CAException(-1, "El formato de fecha no es correcto.");
            }
            if (!fecha.trim().equals("")) {
                busqueda = " AND A.FECHAOPER = ? ";
            }
        }
        StringBuffer query = new StringBuffer();
        query.append(" SELECT A.CUENTA, C.LINEA, A.FECHAOPER, A.IDPRODUCTO,");
        query.append(" A.MARCA, A.MODELO, A.VALORPTOS, A.PRECIOIVA,");
        query.append(" A.IDUSUARIO, A.COMENTARIO, C.FECHAADD, A.FECHAFOLIO,");
        query.append(" A.PRECIO, A.DESCUENTO, A.FOLIO, A.TIPOREDEN,A.FECHAOPER,A.DESCRIPCION ");
        query.append(" FROM  ").append(this.schema_database).append("PTO_TBLREDENCION A,  ").append(this.schema_database).append("PTO_TBLLINEAS C ");
        query.append(" WHERE C.CUENTA = ? ");
        query.append(" AND A.SECUENCIA = ? ");
        query.append(" AND A.CUENTA = C.CUENTA AND A.SECUENCIA = C.SECUENCIA");
        query.append(" AND A.IDREGION = C.IDREGION AND ESTATUS = 'A'");
        query.append(busqueda);
        query.append(" ORDER BY C.FECHAADD DESC, A.FECHAFOLIO DESC, A.IDPRODUCTO");
        redenciones = new ArrayList<RedencionTO>();
        try {
            try {
                connection = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                preparedStatement = connection.prepareStatement(query.toString());
                preparedStatement.setString(1, cuenta);
                preparedStatement.setInt(2, secuencia);
                if (!busqueda.trim().equals("")) {
                    fechaOper = Constantes.DATEFORMATyyyy_MM_dd.parse(fecha);
                    fechaOperacion = fechaOper.getTime();
                    preparedStatement.setDate(3, new Date(fechaOperacion));
                }
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    RedencionTO redencionTO = new RedencionTO();
                    TelefonoSimpleTO telefonoSimpleTO = new TelefonoSimpleTO();
                    telefonoSimpleTO.setCuenta(resultSet.getString("CUENTA"));
                    telefonoSimpleTO.setLinea(resultSet.getString("LINEA"));
                    redencionTO.setTelefonoSimpleTO(telefonoSimpleTO);
                    PuntosRedimidosTO puntosRedimidosTO = new PuntosRedimidosTO();
                    puntosRedimidosTO.setPtsTotaltes(resultSet.getInt("VALORPTOS"));
                    puntosRedimidosTO.setPtsTotaltesconFormato(Utils.setFormatoPtos((int)resultSet.getInt("VALORPTOS")));
                    redencionTO.setPuntosRedimidosTO(puntosRedimidosTO);
                    redencionTO.setFechaOperacion((java.util.Date)resultSet.getDate("FECHAOPER"));
                    ProductosTO productosTO = new ProductosTO();
                    productosTO.setMaterial(resultSet.getString("IDPRODUCTO"));
                    productosTO.setMarca(resultSet.getString("MARCA"));
                    productosTO.setModelo(resultSet.getString("MODELO"));
                    productosTO.setPrecioIva(resultSet.getBigDecimal("PRECIOIVA"));
                    productosTO.setPrecio(resultSet.getBigDecimal("PRECIO"));
                    productosTO.setDescuento(resultSet.getBigDecimal("DESCUENTO"));
                    productosTO.setPrecioConFormato(Utils.setFormatoDecimal((String)resultSet.getString("PRECIO")));
                    productosTO.setPrecioIvaConFormato(Utils.setFormatoDecimal((String)resultSet.getString("PRECIOIVA")));
                    productosTO.setDescripcion(resultSet.getString("DESCRIPCION"));
                    productosTO.setIdProducto(resultSet.getString("IDPRODUCTO"));
                    UsuarioTO usuarioTO = new UsuarioTO();
                    usuarioTO.setIdUsuario(resultSet.getString("IDUSUARIO"));
                    usuarioTO.setPuntoVentaTO(new PuntoVentaTO());
                    redencionTO.setComentario(resultSet.getString("COMENTARIO"));
                    redencionTO.setFechaAdendum(resultSet.getString("FECHAADD"));
                    redencionTO.setFechaFolio(resultSet.getTimestamp("FECHAFOLIO"));
                    redencionTO.setTipoRedencion(resultSet.getString("TIPOREDEN"));
                    redencionTO.setPrecioConFormato(Utils.setFormatoDecimal((String)resultSet.getString("PRECIO")));
                    redencionTO.setFolio(resultSet.getString("FOLIO"));
                    redencionTO.setFechaOperacion((java.util.Date)resultSet.getDate("FECHAOPER"));
                    redencionTO.setUsuarioTO(usuarioTO);
                    redencionTO.setProductosTO(productosTO);
                    redencionTO.setTelefonoSimpleTO(telefonoSimpleTO);
                    redencionTO.setPuntosRedimidosTO(puntosRedimidosTO);
                    redenciones.add(redencionTO);
                }
            }
            catch (SQLException e) {
                throw new CAException(-1, "SQLException.detalleRedencion[" + e.toString() + "]", (Exception)e);
            }
            catch (Exception e) {
                throw new CAException(-1, "ConsultasDAO.detalleRedencion[" + e.toString() + "]", e);
            }
        }
        finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                    resultSet = null;
                }
                catch (Exception var19_23) {}
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                    preparedStatement = null;
                }
                catch (Exception var19_24) {}
            }
            if (connection != null) {
                try {
                    connection.close();
                    connection = null;
                }
                catch (Exception var19_25) {}
            }
        }
        return redenciones;
    }

    public ArrayList<MovimientoTO> detalleMovimientos(String cuenta, String secuencia) throws CAException {
        ArrayList<MovimientoTO> movimietos;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        StringBuffer query = new StringBuffer();
        query.append("SELECT A.CUENTA, A.SECUENCIA, A.LINEA, B.FECHAOPERACION, ");
        query.append("       B.FECHAFAC, B.IDUSUARIO, C.TIPOMOVTO, B.NUMPUNTOS,");
        query.append("       B.NUMPUNTOSEXC, B.TOTAJUSTES, B.IDBONOPROM, ");
        query.append("       B.REFERENCIA, A.FECHAADD ");
        query.append("  FROM  ").append(this.schema_database).append("PTO_TBLLINEAS A,  ").append(this.schema_database).append("PTO_TBLMSTRDETALLE B,");
        query.append("        ").append(this.schema_database).append("PTO_CTLTIPOMOVTO C ");
        query.append(" WHERE A.CUENTA = ?");
        query.append("       AND A.SECUENCIA = ?");
        query.append("       AND B.FECHAOPERACION >= add_months(sysdate,-24) ");
        query.append("       AND B.CUENTA = A.CUENTA ");
        query.append("       AND B.SECUENCIA = A.SECUENCIA AND C.IDMOVTO=B.IDMOVTO");
        query.append(" ORDER BY 13 DESC, 4, 5");
        movimietos = new ArrayList<MovimientoTO>();
        try {
            try {
                connection = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                statement = connection.prepareStatement(query.toString());
                statement.setString(1, cuenta);
                statement.setString(2, secuencia);
                resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    MovimientoTO movimientoTO = new MovimientoTO();
                    movimientoTO.setCuenta(resultSet.getString("CUENTA"));
                    movimientoTO.setSecuencia(resultSet.getString("SECUENCIA"));
                    movimientoTO.setLinea(resultSet.getString("LINEA"));
                    movimientoTO.setFechaOperacion(resultSet.getDate("FECHAOPERACION"));
                    movimientoTO.setFacturacion(resultSet.getDate("FECHAFAC"));
                    movimientoTO.setUsuario(resultSet.getString("IDUSUARIO"));
                    movimientoTO.setMovimiento(resultSet.getString("TIPOMOVTO"));
                    movimientoTO.setNumPuntos(resultSet.getInt("NUMPUNTOS"));
                    movimientoTO.setNumPuntosExc(resultSet.getInt("NUMPUNTOSEXC"));
                    movimientoTO.setTotalAjustes(resultSet.getInt("TOTAJUSTES"));
                    movimientoTO.setBonoPromocion(resultSet.getString("IDBONOPROM"));
                    movimientoTO.setReferencia(resultSet.getString("REFERENCIA"));
                    movimientoTO.setFechaAdendum(resultSet.getDate("FECHAADD"));
                    movimietos.add(movimientoTO);
                }
            }
            catch (SQLException e) {
                throw new CAException(-1, "SQLException.detalleMovimientos[" + e.toString() + "]", (Exception)e);
            }
            catch (Exception e) {
                throw new CAException(-1, "ConsultasDAO.detalleMovimientos[" + e.toString() + "]", e);
            }
        }
        finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                    resultSet = null;
                }
                catch (Exception var10_15) {}
            }
            if (statement != null) {
                try {
                    statement.close();
                    statement = null;
                }
                catch (Exception var10_16) {}
            }
            if (connection != null) {
                try {
                    connection.close();
                    connection = null;
                }
                catch (Exception var10_17) {}
            }
        }
        return movimietos;
    }

    public PuntoVentaTO obtienePuntoVenta(String sIP, Connection connection) throws CAException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection lConn = null;
        try {
            lConn = connection != null ? connection : ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
            String sQuery = "SELECT IDPUNTOVTA, IVA_PORCENTAJE, IDREGION, TIPOPUNTOVTA FROM " + this.schema_database + "PTO_CTLPUNTOSVTA " + " WHERE SEGMENTOIP = ? AND RANGOINF <= ? AND RANGOSUP >=?";
            int nPos = sIP.lastIndexOf(".");
            String sRango = sIP.substring(nPos + 1);
            preparedStatement = lConn.prepareStatement(sQuery);
            preparedStatement.setString(1, sIP.substring(0, nPos));
            preparedStatement.setString(2, sRango);
            preparedStatement.setString(3, sRango);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                PuntoVentaTO puntoVentaTO = new PuntoVentaTO();
                puntoVentaTO.setPtoVenta(resultSet.getString(1));
                puntoVentaTO.setPorcentajeIva(resultSet.getString(2));
                puntoVentaTO.setIdRegion(resultSet.getInt(3));
                puntoVentaTO.setTipoPuntovta(resultSet.getString(4));
                PuntoVentaTO puntoVentaTO2 = puntoVentaTO;
                return puntoVentaTO2;
            }
            try {
                throw new CAException(-1, "El punto de venta no se encuentra dado de alta en el sistema de puntos.");
            }
            catch (Exception e) {
                throw new CAException(-1, "ConsultasDAO.obtienePuntoVenta[" + e.toString() + "]", e);
            }
        } catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                    resultSet = null;
                }
                catch (Exception var12_13) {}
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                    preparedStatement = null;
                }
                catch (Exception var12_14) {}
            }
            if (connection == null && lConn != null) {
                try {
                    lConn.close();
                    lConn = null;
                }
                catch (Exception var12_15) {}
            }
        }
		return null;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     */
    public TelefonoTO procedimientoGeneral(ParametrosTO parametrosTO, PerfilTO perfilTO, String fzaVentas) throws CAException {
        Connection connection = null;
        TelefonoTO telefonoTO = new TelefonoTO();
        CatalogoDAO catalogoDAO = new CatalogoDAO();
        PlanTO planTO = null;
        telefonoTO.setAplicaRedencion(true);
        boolean lineaNueva = false;
        try {
            MobileTO mobileTO;
            telefonoTO.agregaMensaje(0, "Proceso Exitoso");
            long inicioProceso = System.currentTimeMillis();
            this.logger.info((Object)("procedimientoGeneral|Inicio Proceso|" + Constantes.DATEFORMTALog.format(new java.util.Date()) + "|" + inicioProceso));
            if (parametrosTO.getCuenta() == null && parametrosTO.getTelefono() == null) {
                telefonoTO.agregaMensaje(1, "Debe especificar el telefono a consultar.");
                TelefonoTO telefonoTO2 = telefonoTO;
                return telefonoTO2;
            }
            if (parametrosTO.getRegion() == 0) {
                Catalogo properties = new Catalogo();
                properties.setTabla("propiedades");
                properties.cargaCatalogo();
                lineaNueva = true;
                ClienteConsultas consulta = new ClienteConsultas();
                if (parametrosTO.getTelefono() != null) {
                    parametrosTO.setRegion(consulta.consultaRegionPorLinea(properties.getPropiedad("endpoint.consultas.m2k.crm"), parametrosTO.getTelefono()));
                } else {
                    parametrosTO.setRegion(consulta.consultaRegionPorCuenta(properties.getPropiedad("endpoint.consultas.m2k.crm"), parametrosTO.getCuenta()));
                }
                telefonoTO.setRegion(parametrosTO.getRegion());
            }
            if ((mobileTO = this.consultaM2KDAO.consultaDatosM2K(parametrosTO)).getIdMensaje() == -1) {
                telefonoTO.agregaMensaje(mobileTO.getIdMensaje(), mobileTO.getMensaje());
                TelefonoTO telefonoTO3 = telefonoTO;
                return telefonoTO3;
            }
            if (parametrosTO.getMesCareg() != null) {
                mobileTO.setMesesCareg(parametrosTO.getMesCareg());
            } else {
                mobileTO.setMesesCareg("0");
            }
            if (parametrosTO.getAddCareg() != null) {
                mobileTO.setAddCareg(parametrosTO.getAddCareg());
            } else {
                mobileTO.setAddCareg("0");
            }
            try {
                planTO = catalogoDAO.consultaPlan(mobileTO.getPlanM2K(), parametrosTO.getRegion());
                if (planTO != null && planTO.getIdMensaje() == 0 && fzaVentas == null) {
                    this.consultasGapDAO.consultaAnacrGap(mobileTO, telefonoTO, planTO, parametrosTO);
                } else {
                    telefonoTO.setSAnacr("0.9");
                }
            }
            catch (CAException exception) {
                this.error.info((Object)"Exception.procedimientoGeneral:", (Throwable)exception);
            }
            long tiempoConexionCA = System.currentTimeMillis();
            connection = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
            if (lineaNueva) {
                this.validaDatosLineaM2K(parametrosTO, mobileTO, connection, telefonoTO);
            } else {
                this.puntosDAO.actualizaDatosLineaM2K(mobileTO, connection, telefonoTO);
            }
            if (mobileTO.getIdMensaje() == -1) {
                telefonoTO.agregaMensaje(mobileTO.getIdMensaje(), mobileTO.getMensaje());
                TelefonoTO telefonoTO4 = telefonoTO;
                return telefonoTO4;
            }
            if (parametrosTO.getTipoRed() != null) {
                telefonoTO.setTipoRedencion(parametrosTO.getTipoRed());
            }
            telefonoTO.setMobileTO(mobileTO);
            this.consultaDatosPuntos(parametrosTO, telefonoTO, mobileTO, connection);
            if (telefonoTO.getIdMensaje() != 0) {
                TelefonoTO telefonoTO5 = telefonoTO;
                return telefonoTO5;
            }
            if (!(parametrosTO.getTipoRed() != null && (parametrosTO.getTipoRed().trim().equals("PTS") || parametrosTO.getTipoRed().trim().equals("CAN") || parametrosTO.getTipoRed().trim().equals("IMP")))) {
                if (Utils.getValEstatusTel((String)telefonoTO.getMobileTO().getStatus(), (String)telefonoTO.getMobileTO().getMotivo())) {
                    telefonoTO.setAplicaRedencion(false);
                    telefonoTO.agregaMensaje(2, "El Estatus del telefono es Invalido -- " + telefonoTO.getMobileTO().getStatus() + " -- Motivo: " + telefonoTO.getMobileTO().getMotivo());
                } else if (!Utils.getValEstatusCobranza((String)telefonoTO.getMobileTO().getEstCobranza(), (int)telefonoTO.getRegion(), (String)"Reden", (String)telefonoTO.getMobileTO().getMotivo(), (String)null)) {
                    telefonoTO.setAplicaRedencion(false);
                    telefonoTO.agregaMensaje(2, "Estatus de cobranza invalido para realizar tramites.  " + telefonoTO.getMobileTO().getEstCobranza());
                } else if (telefonoTO.getPuntosTO().getPtosStatus().equals("R")) {
                    telefonoTO.setAplicaRedencion(false);
                    telefonoTO.agregaMensaje(2, "Los Puntos estan " + telefonoTO.getPuntosTO().getDescPtsReservados());
                } else if (telefonoTO.getPuntosTO().getPtosStatus().trim().equals("C")) {
                    telefonoTO.setAplicaRedencion(false);
                    telefonoTO.agregaMensaje(2, "Los puntos de la linea se encuentran congelados");
                } else if (!(fzaVentas != null || Utils.ValidaCredito((String)telefonoTO.getMobileTO().getClaseCredit(), (int)telefonoTO.getRegion(), (PerfilTO)perfilTO))) {
                    telefonoTO.setAplicaRedencion(false);
                    telefonoTO.agregaMensaje(2, "El perfil del usuario no esta autorizado para realizar el tramite (Clase de credito AR).");
                } else {
                    boolean fzaVentasAutorizada = false;
                    String claseCredito = telefonoTO.getMobileTO().getClaseCredit();
                    if (fzaVentas != null) {
                        DistribuidoresDAO distribuidoresDAO;
                        if ((claseCredito.trim().equals("IM") || claseCredito.trim().equals("FO")) && !(fzaVentasAutorizada = (distribuidoresDAO = new DistribuidoresDAO()).validaFzaVentasImssFonacot(fzaVentas, telefonoTO.getMobileTO().getClaseCredit(), connection))) {
                            throw new CAException(-1, "La cuenta tiene una clase de credito " + telefonoTO.getMobileTO().getClaseCredit().toUpperCase().trim() + " , por lo que solo podra realizar " + " la redencion de puntos el Distribuidor Autorizado.");
                        }
                    } else if (claseCredito != null && !"".equals(claseCredito) && telefonoTO.getRegion() == 9 && (claseCredito.trim().equals("IM") || claseCredito.trim().equals("FO"))) {
                        telefonoTO.setAplicaRedencion(false);
                        telefonoTO.agregaMensaje(2, "La cuenta tiene una clase de credito " + telefonoTO.getMobileTO().getClaseCredit().toUpperCase().trim() + " , por lo que solo podra realizar " + " la redencion de puntos el Distribuidor Autorizado.");
                    }
                }
                if (parametrosTO.getTipoRed() == null && fzaVentas == null) {
                    AlianzasDAO consultaAmex = new AlianzasDAO();
                    consultaAmex.consultaConfirmaCanje(telefonoTO, connection, parametrosTO.getUsuariMovimiento());
                }
            }
            this.logger.info((Object)("procedimientoGeneral|FinProceso|" + Constantes.DATEFORMTALog.format(new java.util.Date()) + "|" + (System.currentTimeMillis() - inicioProceso)));
            return telefonoTO;
        }
        catch (Exception e) {
            e.printStackTrace();
            this.error.info((Object)"Exception.procedimientoGeneral:", (Throwable)e);
            throw new CAException(-1, "ConsultaDAO.procedimientoGeneral[" + e.toString() + "]", e);
        }
        finally {
            if (connection != null) {
                try {
                    connection.close();
                    connection = null;
                }
                catch (Exception var19_15) {}
            }
        }
    }

    private void validaDatosLineaM2K(ParametrosTO parametrosTO, MobileTO mobileTO, Connection connection, TelefonoTO telefonoTO) throws CAException {
        block18 : {
            try {
                try {
                    connection.setAutoCommit(false);
                    this.puntosDAO.insertaDatosLineaM2K(mobileTO, connection, telefonoTO);
                    this.puntosDAO.insertaTotales(mobileTO, connection);
                    this.consultaDatosPuntos(parametrosTO, telefonoTO, mobileTO, connection);
                    if (telefonoTO.getIdMensaje() == 0) {
                        connection.commit();
                        break block18;
                    }
                    throw new CAException(-1, "La linea no participa en Circulo Azul");
                }
                catch (SQLException e) {
                    if (connection != null) {
                        try {
                            connection.rollback();
                        }
                        catch (Exception var6_7) {
                            // empty catch block
                        }
                    }
                    throw new CAException(-1, e.toString());
                }
                catch (Exception e) {
                    if (connection != null) {
                        try {
                            connection.rollback();
                        }
                        catch (Exception var6_8) {
                            // empty catch block
                        }
                    }
                    throw new CAException(-1, e.toString());
                }
            }
            finally {
                if (connection != null) {
                    try {
                        connection.setAutoCommit(true);
                    }
                    catch (Exception var8_11) {}
                }
            }
        }
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Lifted jumps to return sites
     */
    public void consultaDatosPuntos(ParametrosTO parametrosTO, TelefonoTO telefonoTO, MobileTO mobileTO, Connection connection) throws CAException {
        Object sBusqueda = null;
        StringBuffer query = new StringBuffer();
        CicloFacturacionTO cicloFacturacionTO = null;
        if (!(mobileTO.getCuenta() == null || parametrosTO.getTelefono() == null || mobileTO.getCuenta().trim().equals("") || parametrosTO.getTelefono().trim().equals(""))) {
            sBusqueda = "a.Linea = '" + parametrosTO.getTelefono() + "' AND " + "a.Cuenta = '" + mobileTO.getCuenta() + "' ";
        } else {
            if (mobileTO.getCuenta() != null) {
                sBusqueda = "a.Cuenta = '" + mobileTO.getCuenta() + "' ";
            }
            if (parametrosTO.getTelefono() != null) {
                sBusqueda = "a.Linea = '" + parametrosTO.getTelefono() + "' ";
            }
        }
        query.append(" SELECT A.CUENTA, A.SECUENCIA, A.CTAPADRE, A.LINEA, ");
        query.append("        A.FECHAALTA, A.SISTEMA, A.STATUSPUNTOS,A.IDREGION,");
        query.append("        A.CICLOFACT,A.MODSUBASTA, A.PLAN, A.ADDENDUM,");
        query.append("        B.FECHAFAC, B.PUNTOSREDIM, B.PUNTOSTRANSF, ");
        query.append("        B.PUNTOSCADUC, B.PUNTOSACAD,B.PUNTOSACAD1, ");
        query.append("        B.PUNTOSACAD2, B.PUNTOSRENTA, B.PUNTOSEXCEDENTES,");
        query.append("        B.PUNTOSANTIGUEDAD, B.PUNTOSPROMOCION,B.FECHACAD,");
        query.append("        B.FECHACAD2,B.FECHACAD1,B.FECHACADU, B.SALDOANT,");
        query.append("        B.PUNTOSANTIG, C.TECNOLOGIA,");
        query.append("        B.BONOEQUIPO, C.RENTA,C.IDSEGMENTO,D.SEGMENTO,");
        query.append("        A.FECHAADD,A.ANACR,C.DESCRIPCION, C.BMIXTO, C.MODALIDAD ");
        query.append(" FROM  ").append(this.schema_database).append("PTO_TBLLINEAS A,  ").append(this.schema_database).append("PTO_TBLTOTALES B,");
        query.append(this.schema_database).append("PTO_CTLPLANESTARIFARIOS C,  ").append(this.schema_database).append("PTO_CTLSEGMENTOS D ");
        query.append(" WHERE ").append(sBusqueda);
        query.append("   AND C.ESTATUS = 'A'  ");
        query.append("   AND B.CUENTA = A.CUENTA AND B.SECUENCIA = A.SECUENCIA ");
        query.append("   AND C.IDPLAN = A.PLAN AND C.IDSEGMENTO = D.IDSEGMENTO ");
        query.append("   AND C.IDREGION = A.IDREGION ");
        query.append(" GROUP BY A.CUENTA, A.SECUENCIA, A.CTAPADRE, A.LINEA, ");
        query.append("   A.FECHAALTA, A.SISTEMA, A.STATUSPUNTOS, A.IDREGION, ");
        query.append("   A.CICLOFACT,A.MODSUBASTA, A.PLAN,A.ADDENDUM, ");
        query.append("   B.FECHAFAC, B.PUNTOSREDIM, B.PUNTOSTRANSF, ");
        query.append("   B.PUNTOSCADUC, B.PUNTOSACAD, B.PUNTOSACAD1,");
        query.append("   B.PUNTOSACAD2, B.PUNTOSRENTA, B.PUNTOSEXCEDENTES,");
        query.append("   B.PUNTOSANTIGUEDAD, B.PUNTOSPROMOCION, B.FECHACAD, ");
        query.append("   B.FECHACAD2, B.FECHACAD1, B.FECHACADU, B.SALDOANT, ");
        query.append("   B.PUNTOSANTIG, C.TECNOLOGIA, ");
        query.append("   B.BONOEQUIPO, C.RENTA,C.IDSEGMENTO, D.SEGMENTO, ");
        query.append("   A.FECHAADD, A.ANACR,C.DESCRIPCION,  C.BMIXTO, C.MODALIDAD ");
        query.append(" ORDER BY A.FECHAADD DESC, A.FECHAALTA DESC ");
        PreparedStatement statement = null;
        PreparedStatement statementFvta = null;
        ResultSet resultSet = null;
        ResultSet resultSetFvta = null;
        PuntosTO oPuntos = null;
        try {
            long inicioConsulta = System.currentTimeMillis();
            this.logger.info((Object)("consultaDatosPuntos|InicioConsulta|" + Constantes.DATEFORMTALog.format(new java.util.Date()) + "|" + inicioConsulta));
            statement = (PreparedStatement) connection.createStatement(1004, 1007);
            resultSet = statement.executeQuery(query.toString());
            if (!resultSet.next())// ** GOTO lbl156
            telefonoTO.setCuenta(resultSet.getString("CUENTA").trim());
            telefonoTO.setSecuencia(resultSet.getString("SECUENCIA"));
            telefonoTO.setCtaPadre(resultSet.getString("CTAPADRE").trim());
            telefonoTO.setTelefono(resultSet.getString("LINEA"));
            telefonoTO.setFechaAlta(resultSet.getDate("FECHAALTA"));
            telefonoTO.setSistema(resultSet.getString("SISTEMA"));
            telefonoTO.setRegion(resultSet.getInt("IDREGION"));
            telefonoTO.setCiclo(resultSet.getString("CICLOFACT"));
            telefonoTO.setPlan(resultSet.getString("PLAN"));
            telefonoTO.setAddendum(resultSet.getInt("ADDENDUM"));
            telefonoTO.setFecFactura(resultSet.getDate("FECHAFAC"));
            telefonoTO.setTecnologia(resultSet.getString("TECNOLOGIA"));
            telefonoTO.setBonoEquipo(resultSet.getInt("BONOEQUIPO"));
            telefonoTO.setRenta(resultSet.getInt("RENTA"));
            telefonoTO.setIdSegmento(resultSet.getInt("IDSEGMENTO"));
            telefonoTO.setSegmento(resultSet.getString("SEGMENTO"));
            telefonoTO.setSAnacr(resultSet.getString("ANACR"));
            telefonoTO.setBanSubasta(resultSet.getInt("MODSUBASTA"));
            telefonoTO.setDescripcionPlan(resultSet.getString("DESCRIPCION"));
            if (telefonoTO.getPlanTO() == null) {
                PlanTO planTO = new PlanTO();
                planTO.setBanMixto(resultSet.getString("BMIXTO"));
                planTO.setModalidad(resultSet.getString("MODALIDAD"));
                telefonoTO.setPlanTO(planTO);
            }
            if ((cicloFacturacionTO = this.consultaCicloFac(Integer.parseInt(telefonoTO.getCiclo() != null ? telefonoTO.getCiclo().trim() : "0"), telefonoTO.getRegion())) != null && cicloFacturacionTO.getDescripcion() != null && "CORPORATIVO".equals(cicloFacturacionTO.getDescripcion().trim())) {
                telefonoTO.agregaMensaje(-1, "Cuenta Corporativa, no participa en Puntos.");
                return;
            }
            oPuntos = new PuntosTO();
            oPuntos.setPtosStatus(resultSet.getString("STATUSPUNTOS"));
            oPuntos.setPtsRedimidos(resultSet.getInt("PUNTOSREDIM"));
            oPuntos.setPtsTransferidos(resultSet.getInt("PUNTOSTRANSF"));
            oPuntos.setPtsVencidos(resultSet.getInt("PUNTOSCADUC"));
            oPuntos.setPtsPorVencer(resultSet.getInt("PUNTOSACAD"));
            oPuntos.setPtsPorVencer1(resultSet.getInt("PUNTOSACAD1"));
            oPuntos.setPtsPorVencer2(resultSet.getInt("PUNTOSACAD2"));
            oPuntos.setPtsRenta(resultSet.getInt("PUNTOSRENTA"));
            oPuntos.setPtsExcedentes(resultSet.getInt("PUNTOSEXCEDENTES"));
            oPuntos.setPtsAntiguedad(resultSet.getInt("PUNTOSANTIGUEDAD"));
            oPuntos.setPtsPromocion(resultSet.getInt("PUNTOSPROMOCION"));
            oPuntos.setFecVencer(resultSet.getDate("FECHACAD"));
            oPuntos.setFecVencer2(resultSet.getDate("FECHACAD2"));
            oPuntos.setFecVencer1(resultSet.getDate("FECHACAD1"));
            oPuntos.setFecVencidos(resultSet.getDate("FECHACADU"));
            oPuntos.setPtsSaldoAnt(resultSet.getInt("SALDOANT"));
            oPuntos.setBonoEquipo(resultSet.getInt("BONOEQUIPO"));
            if (telefonoTO.getSAnacr() != null && telefonoTO.getSAnacr().equals("0.0")) {
                this.consultaM2KDAO.obtenPromedioFacturaciones(mobileTO, telefonoTO.getRegion(), telefonoTO.getCuenta());
            }
            if (mobileTO.getIdMensaje() != 0) {
                telefonoTO.agregaMensaje(mobileTO.getIdMensaje(), mobileTO.getMensaje());
                return;
            }
            if (parametrosTO.getTipoRed() == null) {
                String sFechaVenc = "";
                sFechaVenc = resultSet.getDate(24) != null ? resultSet.getDate(24).toString() : null;
                String sFechaActual = Constantes.DATEFORMATyyyy_MM_dd.format(new java.util.Date());
                if (sFechaVenc != null && resultSet.getInt("PUNTOSACAD") != 0) {
                    if (sFechaActual.equals(sFechaVenc)) {
                        oPuntos.setBandVencer60Dias(true);
                    } else {
                        ArrayList<String> vecFechas = new ArrayList<String>();
                        vecFechas.add(sFechaActual);
                        vecFechas.add(sFechaVenc);
                        int indMenor = Utils.comparaFechas(vecFechas, (int)0, (int)1);
                        if (indMenor != -1 && indMenor == 0) {
                            int difDias = 0;
                            difDias = (int) Utils.diferenciaEnDias((Calendar)new GregorianCalendar(Integer.parseInt(sFechaActual.substring(0, 4)), Integer.parseInt(sFechaActual.substring(5, 7)) - 1, Integer.parseInt(sFechaActual.substring(8, 10))), (Calendar)new GregorianCalendar(Integer.parseInt(sFechaVenc.substring(0, 4)), Integer.parseInt(sFechaVenc.substring(5, 7)) - 1, Integer.parseInt(sFechaVenc.substring(8, 10))));
                            if (difDias >= -60 && difDias <= 60) {
                                oPuntos.setBandVencer60Dias(true);
                               // ** GOTO lbl158
                            } else {
                                //** GOTO lbl158
                            }
                        } else {
                            //** GOTO lbl158
                        }
                    }
                } else {
                    //** GOTO lbl158
                }
            } else {
                if (parametrosTO.getTipoRed() != null && (parametrosTO.getTipoRed().equals("CON") || parametrosTO.getTipoRed().equals("CAREG"))) {
                    if (parametrosTO.getPlanNvo() == null || "".equals(parametrosTO.getPlanNvo())) {
                        if (!parametrosTO.isConsultaGeneral()) {
                            telefonoTO.agregaMensaje(1, "Es necesario capturar el plan en el que desea renovar el contrato.");
                            return;
                        }
                        parametrosTO.setPlanNvo(telefonoTO.getPlan());
                    }
                    this.datosPlan(parametrosTO.getPlanNvo(), parametrosTO.getRegion(), connection, telefonoTO);
                    if (telefonoTO.getIdMensaje() != 0) {
                        return;
                    }
                    parametrosTO.setBRedencionAnct(telefonoTO.getPlanTO().getBanRedencionAnct());
                    telefonoTO.setIdGrupo(String.valueOf(telefonoTO.getPlanTO().getIdGrupoPromocion()));
                }
                Redencion.validaRedencion((ParametrosTO)parametrosTO, (TelefonoTO)telefonoTO, (MobileTO)mobileTO);
                telefonoTO.setFormaRedencion(parametrosTO.getFormaRed());
                if (telefonoTO.getIdMensaje() != 0) {
                    return;
                }
                if (parametrosTO.getTipoRed().equals("CON") || parametrosTO.getTipoRed().equals("CAREG")) {
                    Redencion.validaPlan((ParametrosTO)parametrosTO, (TelefonoTO)telefonoTO);
                }
                if (telefonoTO.getIdMensaje() != 0) {
                    return;
                }
                if (parametrosTO.getTipoRed().trim().equals("ACA")) {
                    if (parametrosTO.getRegion() == 9) {
                        telefonoTO.setIdGrupo(String.valueOf(1178));
                    } else {
                        telefonoTO.setIdGrupo(String.valueOf(1084));
                    }
                }
                if (parametrosTO.getTipoRed().trim().equals("SIN")) {
                    if (parametrosTO.getRegion() == 9) {
                        telefonoTO.setIdGrupo(String.valueOf(1048));
                    } else {
                        telefonoTO.setIdGrupo(String.valueOf(1082));
                    }
                }
                if (parametrosTO.getTipoRed().trim().equals("T3G")) {
                    if (parametrosTO.getRegion() == 9) {
                        telefonoTO.setIdGrupo(String.valueOf(1347));
                    } else {
                        telefonoTO.setIdGrupo(String.valueOf(1346));
                    }
                } else {
                   // ** GOTO lbl158
                }
            }
            //** GOTO lbl158
lbl156: // 1 sources:
            telefonoTO.agregaMensaje(-1, "La Cuenta/Telefono no existe o el estatus no es valido.");
            return;
//lbl158: // 12 sources:
	/*
            if (oPuntos.getPtosStatus().trim().equals("R")) {
                StringBuffer sql = new StringBuffer();
                sql.append(" SELECT B.FZAVTA, B.FECHAOPER ");
                sql.append("   FROM  ").append(this.schema_database).append("PTO_TBLLINEAS A,  ").append(this.schema_database).append("PTO_TBLRESERVACIONES B ");
                sql.append("  WHERE ").append(sBusqueda);
                sql.append("   AND A.CUENTA = B.CUENTA AND A.SECUENCIA = B.SECUENCIA ");
                sql.append("   AND A.IDREGION = B.IDREGION AND A.STATUSPUNTOS='R' ");
                sql.append(" ORDER BY B.FECHAOPER DESC ");
                statementFvta = (PreparedStatement) connection.createStatement(1004, 1007);
                resultSetFvta = statementFvta.executeQuery(sql.toString());
                if (resultSetFvta.next()) {
                    String distReserva = resultSetFvta.getString(1);
                    Date fechaReservacion = resultSetFvta.getDate(2);
                    oPuntos.setDescPtsReservados("Reservados por el Distribuidor: " + distReserva + " el dia:" + Constantes.DATEFORMATdd_MM_YYYY.format(new java.util.Date(fechaReservacion.getTime())));
                    oPuntos.setDistribuidorReserva(distReserva);
                    oPuntos.setFecReservacion(fechaReservacion);
                }
            }*/
            //telefonoTO.setPuntosTO(oPuntos);
            //this.logger.info((Object)("consultaDatosPuntos|FinConsulta|" + Constantes.DATEFORMTALog.format(new java.util.Date()) + "|" + (System.currentTimeMillis() - inicioConsulta)));
           // return;
        }
        catch (SQLException e) {
            throw new CAException(-1, "SQLException.consultaDatosPuntos[" + e.toString() + "]", (Exception)e);
        }
        catch (Exception e) {
            throw new CAException(-1, "ConsultasDAO.consultaDatosPuntos[" + e.toString() + "]", e);
        }
        finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                    resultSet = null;
                }
                catch (Exception var22_17) {}
            }
            if (resultSetFvta != null) {
                try {
                    resultSetFvta.close();
                    resultSetFvta = null;
                }
                catch (Exception var22_18) {}
            }
            if (statement != null) {
                try {
                    statement.close();
                    statement = null;
                }
                catch (Exception var22_19) {}
            }
            if (statementFvta != null) {
                try {
                    statementFvta.close();
                    statementFvta = null;
                }
                catch (Exception var22_20) {}
            }
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public void datosPlan(String sPlan, int region, Connection connection, TelefonoTO telefonoTO) throws CAException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        StringBuffer query = new StringBuffer();
        Connection lConn = null;
        PlanTO planTO = new PlanTO();
        telefonoTO.setPlanTO(planTO);
        query.append(" SELECT B.TIPOPROMOCION, BREDENCION, A.IDGRUPOPROMOCION,");
        query.append(" A.ADENDUM, A.BREDENCIONANTC, A.IDSEGMENTO , A.TIPO_PLAN ");
        query.append(" FROM  ").append(this.schema_database).append("PTO_CTLPLANESTARIFARIOS A,");
        query.append("       ").append(this.schema_database).append("PTO_CTLGRUPOPROMOCION B ");
        query.append(" WHERE A.IDGRUPOPROMOCION = B.IDGRUPOPROMOCION ");
        query.append(" AND A.IDPLAN = ?");
        query.append(" AND A.IDREGION =?");
        query.append(" AND A.ESTATUS = 'A' AND B.ESTATUS = 'A' ");
        try {
            lConn = connection == null ? ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul) : connection;
            statement = lConn.prepareStatement(query.toString());
            statement.setString(1, sPlan);
            statement.setInt(2, region);
            resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                telefonoTO.agregaMensaje(1, "El Plan no se encuentra dado de alta en la base de datos.");
                do {
                    return;
                    //break;
                } while (true);
            }
            planTO.setTipoPromocion(resultSet.getString(1));
            planTO.setBanRedencion(resultSet.getInt(2));
            planTO.setIdGrupoPromocion(resultSet.getInt(3));
            planTO.setAdendumNvo(resultSet.getInt(4));
            planTO.setBanRedencionAnct(resultSet.getString(5));
            planTO.setIdPlanNuevo(sPlan);
            planTO.setSegmento(resultSet.getInt("IDSEGMENTO"));
            planTO.setTipoPlan(resultSet.getString(7));
            telefonoTO.setPlanTO(planTO);
            if (telefonoTO.getPlanTO().getBanRedencion() == 0) {
                telefonoTO.agregaMensaje(1, "La clave del plan capturado es obsoleta, favor de ingresar otra \nclave de plan que se comercialice para continuar.");
                return;
            }
            if (telefonoTO.getPlanTO().getAdendumNvo() == 0) {
                telefonoTO.agregaMensaje(1, "El plan capturado tiene Adendum 0, para realizar una Redencion con renovacion de Adendum seleccione un Plan con adendum valido.");
                return;
            }
            if (telefonoTO.getPlanTO().getTipoPlan() == null || telefonoTO.getPlanTO().getTipoPlan().trim().equals("MASIVO")) return;
            String tipoPlan = telefonoTO.getPlanTO().getTipoPlan().trim();
            if ("IMFO".equals(tipoPlan)) {
                telefonoTO.agregaMensaje(1, "No se puede redimir por el plan indicado, el plan es exclusivo para lineas IMSS-FONACOT.");
                return;
            }
            if (!"LIV".equals(tipoPlan)) return;
            telefonoTO.agregaMensaje(1, "No se puede redimir por el plan indicado, el plan es exclusivo para lineas LIVERPOOL.");
        }
        catch (SQLException e) {
            throw new CAException(-1, "SQLException.datosPlan[" + e.toString() + "]", (Exception)e);
        }
        catch (Exception e) {
            throw new CAException(-1, "ConsultasDAO.datosPlan[" + e.toString() + "]", e);
        }
        finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                    resultSet = null;
                }
                catch (Exception var12_16) {}
            }
            if (statement != null) {
                try {
                    statement.close();
                    statement = null;
                }
                catch (Exception var12_17) {}
            }
            if (connection == null && lConn != null) {
                try {
                    lConn.close();
                    lConn = null;
                }
                catch (Exception var12_18) {}
            }
        }
        return;
    }

    public ArrayList<CuentaPadreTO> consultaPadre(String cuenta) throws CAException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        StringBuffer query = new StringBuffer();
        query.append(" SELECT CUENTA, LINEA FROM  ").append(this.schema_database).append("PTO_TBLLINEAS WHERE CTAPADRE = ?");
        try {
            long inicioProceso = System.currentTimeMillis();
            this.logger.info((Object)("consultaPadre|Inicio Proceso|" + Constantes.DATEFORMTALog.format(new java.util.Date()) + "|" + inicioProceso));
            this.logger.info((Object)("consultaPadre|Antes de Conexion|" + Constantes.DATEFORMTALog.format(new java.util.Date()) + "|" + inicioProceso));
            connection = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
            this.logger.info((Object)("consultaPadre|Despues de Conexion|" + Constantes.DATEFORMTALog.format(new java.util.Date()) + "|" + (System.currentTimeMillis() - inicioProceso)));
            long inicioConsulta = System.currentTimeMillis();
            this.logger.info((Object)("consultaPadre|InicioConsulta|" + Constantes.DATEFORMTALog.format(new java.util.Date()) + "|" + inicioConsulta));
            preparedStatement = connection.prepareStatement(query.toString());
            preparedStatement.setString(1, cuenta);
            resultSet = preparedStatement.executeQuery();
            ArrayList<CuentaPadreTO> cuentasHijas = new ArrayList<CuentaPadreTO>();
            while (resultSet.next()) {
                CuentaPadreTO cuentaPadreTO = new CuentaPadreTO();
                cuentaPadreTO.setCuenta(resultSet.getString("CUENTA"));
                cuentaPadreTO.setLinea(resultSet.getString("LINEA"));
                cuentaPadreTO.setCuentaPadre(cuenta);
                cuentasHijas.add(cuentaPadreTO);
            }
            this.logger.info((Object)("consultaPadre|FinConsulta|" + Constantes.DATEFORMTALog.format(new java.util.Date()) + "|" + (System.currentTimeMillis() - inicioConsulta)));
            this.logger.info((Object)("consultaPadre|FinProceso|" + Constantes.DATEFORMTALog.format(new java.util.Date()) + "|" + (System.currentTimeMillis() - inicioProceso)));
            ArrayList<CuentaPadreTO> arrayList = cuentasHijas;
            return arrayList;
        }
        catch (SQLException e) {
            this.error.info((Object)"SQLException.consultaPadre:", (Throwable)e);
            throw new CAException(-1, "SQLException.consultaPadre[" + e.toString() + "]", (Exception)e);
        }
        catch (Exception e) {
            this.error.info((Object)"Exception.consultaPadre:", (Throwable)e);
            throw new CAException(-1, "ConsultasDAO.consultaPadre[" + e.toString() + "]", e);
        }
        finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                    resultSet = null;
                }
                catch (Exception var14_16) {}
            }
            if (preparedStatement != null) {
                try {
                	preparedStatement.close();
                	preparedStatement = null;
                }
                catch (Exception var14_17) {}
            }
            if (connection != null) {
                try {
                    connection.close();
                    connection = null;
                }
                catch (Exception var14_18) {}
            }
        }
    }

    public String consultaPrecioEquipo(String plan, int region, String marca, String modelo, String estatus) throws CAException {
        String precio;
        block23 : {
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;
            Connection connection = null;
            StringBuffer sQuery = new StringBuffer();
            precio = "";
            try {
                try {
                    if (plan != null && marca != null && modelo != null) {
                        sQuery.append(" SELECT PRECIOACTIVACION");
                        sQuery.append(" FROM  ").append(this.schema_database).append("PTO_CTLPROMOCIONES A ,  ").append(this.schema_database).append("PTO_CTLGRUPOPROMOCION B,");
                        sQuery.append(this.schema_database).append("PTO_CTLPLANESTARIFARIOS C");
                        sQuery.append(" WHERE A.IDGRUPOPROMOCION = B.IDGRUPOPROMOCION");
                        sQuery.append(" AND B.IDGRUPOPROMOCION = C.IDGRUPOPROMOCION");
                        sQuery.append(" AND C.IDPLAN = ? ");
                        sQuery.append(" AND A.ADENDUM = C.ADENDUM AND A.IDREGION = C.IDREGION");
                        sQuery.append(" AND A.IDREGION =? ");
                        sQuery.append(" AND A.MARCA =? ");
                        sQuery.append(" AND A.MODELO =?");
                        sQuery.append(" AND A.ESTATUS = ?");
                        connection = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                        preparedStatement = connection.prepareStatement(sQuery.toString());
                        preparedStatement.setString(1, plan);
                        preparedStatement.setInt(2, region);
                        preparedStatement.setString(3, marca);
                        preparedStatement.setString(4, modelo);
                        preparedStatement.setString(5, estatus);
                        resultSet = preparedStatement.executeQuery();
                        if (resultSet.next()) {
                            precio = resultSet.getBigDecimal("PRECIOACTIVACION").toString();
                            break block23;
                        }
                        throw new CAException(-1, "No existen promociones que correspondan a los datos enviados");
                    }
                    throw new CAException(-1, "Los datos de consulta son nulos.");
                }
                catch (SQLException e) {
                    throw new CAException(-1, "SQLException.consultaPrecioEquipo[" + e.toString() + "]", (Exception)e);
                }
                catch (Exception e) {
                    throw new CAException(-1, "ConsultasDAO.consultaPrecioEquipo[" + e.toString() + "]", e);
                }
            }
            finally {
                if (resultSet != null) {
                    try {
                        resultSet.close();
                        resultSet = null;
                    }
                    catch (Exception var13_17) {}
                }
                if (preparedStatement != null) {
                    try {
                        preparedStatement.close();
                        preparedStatement = null;
                    }
                    catch (Exception var13_18) {}
                }
                if (connection != null) {
                    try {
                        connection.close();
                        connection = null;
                    }
                    catch (Exception var13_19) {}
                }
            }
        }
        return precio;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public int consultaRegion(String lTelefono) throws CAException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        StringBuffer sQuery = new StringBuffer();
        try {
            if (lTelefono == null) throw new CAException(-1, "Debe especificar el telfono a consultar.");
            sQuery.append(" SELECT A.IDREGION ");
            sQuery.append("  FROM  ").append(this.schema_database).append("PTO_TBLLINEAS A ");
            sQuery.append(" WHERE A.LINEA = ? ");
            sQuery.append(" GROUP BY A.LINEA, A.CUENTA, A.SECUENCIA, A.IDREGION, A.FECHAADD ");
            sQuery.append(" ORDER BY A.FECHAADD DESC");
            connection = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
            preparedStatement = connection.prepareStatement(sQuery.toString(), 1004, 1007);
            preparedStatement.setString(1, lTelefono);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int n = resultSet.getInt("IDREGION");
                return n;
            }
            try {
                throw new CAException(-1, "La linea no se encuentra dada de alta en el sistema de Puntos.");
            }
            catch (Exception e) {
                throw new CAException(-1, "ConsultasDAO.consultaRegion[" + e.toString() + "]", e);
            }
        } catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                    resultSet = null;
                }
                catch (Exception var9_7) {}
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                    preparedStatement = null;
                }
                catch (Exception var9_8) {}
            }
            if (connection != null) {
                try {
                    connection.close();
                    connection = null;
                }
                catch (Exception var9_9) {}
            }
        }
		return 0;
    }

    public ArrayList<RedencionTO> consultaFolioSAP(String folio, String tipoRedencion, String estatus, int secuencia, String cuenta, int region, boolean consultaM2K) throws CAException {
        ArrayList<RedencionTO> folios;
        String query = "SELECT a.folio, a.fechaoper, a.descripcion, b.esnimeir,b.esnimeip,b.iccid, a.TipoProm, c.tecnologia,a.tiporeden,a.marca, a.modelo,a.valorptos, a.difpesos,a.linea,a.cuenta,a.IDPUNTOVTA,a.FECHAFOLIO,b.PLANNVO,b.ADDNVO,b.FECPLAZOSUG,a.descripcion,a.IDREGION,a.precio,a.estatus,u.IDUSUARIO,u.nombre,a.precioiva,a.descuento,a.BONOROEXT,a.BONOALTOVALOR, a.IDPRODUCTO, a.BONOGAP  FROM " + this.schema_database + "PTO_TBLREDENCION a, " + this.schema_database + "PTO_TBLCONSTANCIA b, " + this.schema_database + "PTO_CTLPLANESTARIFARIOS c, " + this.schema_database + "PTO_TBLLINEAS d," + this.schema_database + "PTO_CTLUSUARIOS u " + " WHERE a.fechafolio = b.fechafolio  and a.IDUSUARIO=u.IDUSUARIO and " + "       a.cuenta = b.cuenta and a.secuencia = b.secuencia and " + "       a.cuenta = d.cuenta and a.secuencia = d.secuencia and a.idregion = c.idregion and  d.plan = c.idplan ";
        if (estatus != null) {
            query = String.valueOf(query) + " and a.estatus =? ";
        }
        if (cuenta != null) {
            query = String.valueOf(query) + " and a.Cuenta =? ";
        }
        if (secuencia != 0) {
            query = String.valueOf(query) + " and a.Secuencia =? ";
        }
        if (folio != null) {
            query = String.valueOf(query) + " and a.folio =? ";
        }
        if (tipoRedencion != null) {
            query = String.valueOf(query) + " and a.tiporeden in (?)";
        }
        query = String.valueOf(query) + " ORDER BY a.tiporeden, a.fechafolio desc ";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        folios = new ArrayList<RedencionTO>();
        try {
            try {
                connection = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                statement = connection.prepareStatement(query);
                int nIndice = 0;
                if (estatus != null) {
                    statement.setString(++nIndice, estatus);
                }
                if (cuenta != null) {
                    statement.setString(++nIndice, cuenta);
                }
                if (secuencia != 0) {
                    statement.setInt(++nIndice, secuencia);
                }
                if (folio != null) {
                    statement.setString(++nIndice, folio);
                }
                if (tipoRedencion != null) {
                    statement.setString(++nIndice, tipoRedencion);
                }
                resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    RedencionTO redencionTO = new RedencionTO();
                    ProductosTO productosTO = new ProductosTO();
                    redencionTO.setFolio(resultSet.getString(1));
                    redencionTO.setFechaOperacion((java.util.Date)resultSet.getDate(2));
                    productosTO.setDescripcion(resultSet.getString(3));
                    productosTO.setNumeroSerieT(resultSet.getString(4));
                    productosTO.setNumeroSerieP(resultSet.getString(5));
                    productosTO.setIccid(resultSet.getString(6));
                    productosTO.setTipoPromocion(resultSet.getString(7));
                    productosTO.setTecnologia(resultSet.getString(8));
                    redencionTO.setTipoRedencion(resultSet.getString(9));
                    productosTO.setMarca(resultSet.getString(10));
                    productosTO.setModelo(resultSet.getString(11));
                    productosTO.setValorPuntos(resultSet.getInt(12));
                    productosTO.setDifPesos(resultSet.getInt(13));
                    productosTO.setDescripcion(resultSet.getString(21));
                    productosTO.setPrecio(resultSet.getBigDecimal(23));
                    productosTO.setPrecioIva(resultSet.getBigDecimal(27));
                    productosTO.setDescuento(resultSet.getBigDecimal(28));
                    productosTO.setBonosRoext(resultSet.getInt(29));
                    productosTO.setBonosAltoValor(resultSet.getInt(30));
                    productosTO.setIdProducto(resultSet.getString(31));
                    productosTO.setBonosGap(resultSet.getInt(32));
                    TelefonoSimpleTO telefonoSimpleTO = new TelefonoSimpleTO();
                    telefonoSimpleTO.setLinea(resultSet.getString(14));
                    telefonoSimpleTO.setCuenta(resultSet.getString(15));
                    PuntoVentaTO puntoVentaTO = new PuntoVentaTO();
                    puntoVentaTO.setPtoVenta(resultSet.getString(16));
                    redencionTO.setFechaFolio(resultSet.getTimestamp(17));
                    redencionTO.setPlanNuevo(resultSet.getString(18));
                    redencionTO.setAddendumNuevo(resultSet.getInt(19));
                    redencionTO.setFechaPlazoSeg((java.util.Date)resultSet.getDate(20));
                    redencionTO.setRegion(resultSet.getInt(22));
                    redencionTO.setEstatus(resultSet.getString(24));
                    UsuarioTO usuarioTO = new UsuarioTO();
                    usuarioTO.setIdUsuario(resultSet.getString(25));
                    usuarioTO.setNombre(resultSet.getString(26));
                    usuarioTO.setPuntoVentaTO(puntoVentaTO);
                    redencionTO.setUsuarioTO(usuarioTO);
                    redencionTO.setTelefonoSimpleTO(telefonoSimpleTO);
                    redencionTO.setProductosTO(productosTO);
                    if (consultaM2K) {
                        ParametrosTO parametrosTO = new ParametrosTO();
                        parametrosTO.setRegion(region);
                        parametrosTO.setTelefono(telefonoSimpleTO.getLinea());
                        try {
                            redencionTO.setMobileTO(this.consultaM2KDAO.consultaDatosM2K(parametrosTO));
                        }
                        catch (Exception e) {
                            throw new CAException(1, "***************************");
                        }
                    }
                    redencionTO.agregaMensaje(0, "Proceso Exitoso.");
                    folios.add(redencionTO);
                }
            }
            catch (SQLException e) {
                throw new CAException(-1, "SQLException.consultaFolioSAP[" + e.toString() + "]", (Exception)e);
            }
            catch (Exception e) {
                throw new CAException(-1, "ConsultasDAO.consultaFolioSAP[" + e.toString() + "]", e);
            }
        }
        finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                    resultSet = null;
                }
                catch (Exception var22_27) {}
            }
            if (statement != null) {
                try {
                    statement.close();
                    statement = null;
                }
                catch (Exception var22_28) {}
            }
            if (connection != null) {
                try {
                    connection.close();
                    connection = null;
                }
                catch (Exception var22_29) {}
            }
        }
        return folios;
    }

    public ReservacionTO obtieneReservacion(String folio, String estatus, boolean cancelaApartado) throws CAException {
        ReservacionTO reservacionTO;
        block32 : {
            Connection connection = null;
            PreparedStatement statement = null;
            ResultSet resultSet = null;
            reservacionTO = new ReservacionTO();
            try {
                try {
                    reservacionTO.agregaMensaje(0, "Proceso Exitoso");
                    StringBuffer query1 = new StringBuffer(" SELECT R.FOLIO,R.CUENTA,R.SECUENCIA,R.IDPRODUCTO,");
                    query1.append("R.IDPUNTOVTA,R.IDREGION,R.VALORPUNTOS,R.DIFERENCIAPESOS,");
                    query1.append("R.IDPLAN,R.COMENTARIO,R.PLANNVO,R.DESCRIPCION,R.MARCA,");
                    query1.append("R.MODELO,R.TIPOREDEN,R.ADDEANT,R.TIPOPROM,");
                    query1.append("R.ADDNVO,R.PLAZOANT,R.FZAVTA,");
                    query1.append("R.PLAZONVO,R.FORMARED,R.PRECIO,R.PRECIOIVA,R.LINEA, ");
                    query1.append("R.SOBRANTESBONO,R.FZAVTA,R.STATUS,R.BONOROEXT,R.DESCUENTO,R.BONOALTOVALOR,");
                    query1.append("P.IDPUESTO,");
                    query1.append("R.IDUSUARIO,R.FECHAOPER,R.FECHAEXPIRA, ");
                    query1.append("R.APLICAPROMOGAP,R.BONOGAP,R.IDPROMOCIONGAP,R.IDPROMOCA,R.VERPROMOGAP,R.APLICAEP, R.PTOSMIN ");
                    query1.append(", U.NUMEMPLEADO ");
                    query1.append(" FROM ").append(this.schema_database).append("PTO_TBLRESERVACIONES R ");
                    query1.append(",").append(this.schema_database).append("PTO_CTLUSUARIOS U");
                    query1.append(",").append(this.schema_database).append("PTO_CTLPERFILN P ");
                    query1.append(" WHERE R.IDUSUARIO = U.IDUSUARIO AND U.IDPERFILN=P.IDPERFILN AND R.FOLIO = ? ");
                    if (estatus != null && estatus.equals("C")) {
                        query1.append(" AND  R.STATUS IN ('A','P','R','C')");
                    } else if (estatus != null && estatus.equals("P")) {
                        query1.append(" AND  R.STATUS IN ('A')");
                    }
                    connection = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                    statement = connection.prepareStatement(query1.toString());
                    statement.setString(1, folio);
                    resultSet = statement.executeQuery();
                    if (resultSet.next()) {
                        String statusTmp = resultSet.getString("STATUS");
                        reservacionTO.setFolio(resultSet.getString("FOLIO"));
                        ProductosTO productosTO = new ProductosTO();
                        PuntoVentaTO puntoVentaTO = new PuntoVentaTO();
                        TelefonoSimpleTO telefonoSimpleTO = new TelefonoSimpleTO();
                        telefonoSimpleTO.setCuenta(resultSet.getString("CUENTA"));
                        telefonoSimpleTO.setSecuencia(resultSet.getInt("SECUENCIA"));
                        telefonoSimpleTO.setLinea(resultSet.getString("LINEA"));
                        telefonoSimpleTO.setRegion(resultSet.getInt("IDREGION"));
                        reservacionTO.setTelefonoSimpleTO(telefonoSimpleTO);
                        productosTO.setIdProducto(resultSet.getString("IDPRODUCTO"));
                        puntoVentaTO.setPtoVenta(resultSet.getString("IDPUNTOVTA"));
                        reservacionTO.setRegion(resultSet.getInt("IDREGION"));
                        productosTO.setValorPuntos(resultSet.getInt("VALORPUNTOS"));
                        productosTO.setDifPesos(resultSet.getInt("DIFERENCIAPESOS"));
                        reservacionTO.setPlanAnterior(resultSet.getString("IDPLAN"));
                        reservacionTO.setComentario(resultSet.getString("COMENTARIO"));
                        reservacionTO.setPlanNuevo(resultSet.getString("PLANNVO"));
                        productosTO.setDescripcion(resultSet.getString("DESCRIPCION"));
                        productosTO.setMarca(resultSet.getString("MARCA"));
                        productosTO.setModelo(resultSet.getString("MODELO"));
                        reservacionTO.setTipoRedencion(resultSet.getString("TIPOREDEN"));
                        reservacionTO.setFechaAdendumAnterior((java.util.Date)resultSet.getDate("ADDEANT"));
                        productosTO.setTipoPromocion(resultSet.getString("TIPOPROM"));
                        reservacionTO.setFechaAdendumNuevo((java.util.Date)resultSet.getDate("ADDNVO"));
                        reservacionTO.setPlazoAnterior(resultSet.getString("PLAZOANT"));
                        reservacionTO.setPlazoNuevo(resultSet.getString("PLAZONVO"));
                        reservacionTO.setFormaRedencion(resultSet.getString("FORMARED"));
                        productosTO.setPrecio(resultSet.getBigDecimal("PRECIO"));
                        productosTO.setPrecioIva(resultSet.getBigDecimal("PRECIOIVA"));
                        reservacionTO.setSobrantesBono(resultSet.getInt("SOBRANTESBONO"));
                        reservacionTO.setFuerzaVenta(resultSet.getString("FZAVTA"));
                        productosTO.setBonosRoext(resultSet.getInt("BONOROEXT"));
                        productosTO.setDescuento(resultSet.getBigDecimal("DESCUENTO"));
                        productosTO.setBonosAltoValor(resultSet.getInt("BONOALTOVALOR"));
                        productosTO.setBonosGap(resultSet.getInt("BONOGAP"));
                        UsuarioTO usuarioTO = new UsuarioTO();
                        usuarioTO.getPerfilTO().setIdPuesto(resultSet.getString("IDPUESTO"));
                        usuarioTO.setIdUsuario(resultSet.getString("IDUSUARIO"));
                        usuarioTO.setNumEmpleado(resultSet.getString("NUMEMPLEADO"));
                        reservacionTO.setFechaOperacion((java.util.Date)resultSet.getDate("FECHAOPER"));
                        reservacionTO.setFechaExpiracion((java.util.Date)resultSet.getDate("FECHAEXPIRA"));
                        reservacionTO.setEstatus(statusTmp);
                        reservacionTO.setFuerzaVenta(resultSet.getString("FZAVTA"));
                        productosTO.setAplicaPromocionGap(resultSet.getString("APLICAPROMOGAP") != null ? resultSet.getString("APLICAPROMOGAP") : "");
                        productosTO.setIdPromocionGap(resultSet.getInt("IDPROMOCIONGAP"));
                        productosTO.setIdPromocionGapCA(resultSet.getInt("IDPROMOCA"));
                        productosTO.setVerPromocionGap(resultSet.getInt("VERPROMOGAP"));
                        productosTO.setAplicaEP(resultSet.getString("APLICAEP"));
                        reservacionTO.setPtsMinimos(resultSet.getInt("PTOSMIN"));
                        if (productosTO.getAplicaPromocionGap() != null && "SI".equals(productosTO.getAplicaPromocionGap())) {
                            if (productosTO.getBonosGap() != 0) {
                                productosTO.setBonoDescuentoGap("SI");
                            } else {
                                productosTO.setProductoM2KGap("SI");
                            }
                        }
                        if (!cancelaApartado) {
                            if (statusTmp.equals("R")) {
                                reservacionTO.agregaMensaje(1, "R2. El folio de la reservacion ya fue aplicado.");
                            } else if (statusTmp.equals("C")) {
                                reservacionTO.agregaMensaje(1, "R3. El folio de la reservacion ya fue cancelado.");
                            }
                        }
                        reservacionTO.setProductosTO(productosTO);
                        reservacionTO.setUsuarioTO(usuarioTO);
                        reservacionTO.getUsuarioTO().setPuntoVentaTO(puntoVentaTO);
                        break block32;
                    }
                    throw new CAException(1, "R1. El folio de la reservacion no se encuentra.");
                }
                catch (SQLException e) {
                    throw new CAException(-1, "RedencionDAO.obtieneReservacion.SQLException[" + e.toString() + "]", (Exception)e);
                }
                catch (Exception e) {
                    throw new CAException(-1, "RedencionDAO.obtieneReservacion.Error[" + e.toString() + "]", e);
                }
            }
            finally {
                if (resultSet != null) {
                    try {
                        resultSet.close();
                        resultSet = null;
                    }
                    catch (Exception var15_20) {}
                }
                if (statement != null) {
                    try {
                        statement.close();
                        statement = null;
                    }
                    catch (Exception var15_21) {}
                }
                if (connection != null) {
                    try {
                        connection.close();
                        connection = null;
                    }
                    catch (Exception var15_22) {}
                }
            }
        }
        return reservacionTO;
    }

    public RedencionTO ultimaRedencion(String cuenta, int secuencia) throws CAException {
        RedencionTO redencionTO;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        StringBuffer query = new StringBuffer();
        query.append("SELECT FECHAFOLIO,FOLIO FROM ").append(this.schema_database).append("PTO_TBLREDENCION ");
        query.append("WHERE FECHAFOLIO=( ");
        query.append("SELECT max(A.FECHAFOLIO)");
        query.append("FROM  ").append(this.schema_database).append("PTO_TBLREDENCION A,");
        query.append(this.schema_database).append("PTO_TBLLINEAS C ");
        query.append("WHERE C.CUENTA = ? ");
        query.append("AND A.SECUENCIA =? ");
        query.append("AND A.CUENTA = C.CUENTA AND A.SECUENCIA = C.SECUENCIA ");
        query.append("AND A.IDREGION = C.IDREGION AND ESTATUS = 'A'");
        query.append(")");
        redencionTO = new RedencionTO();
        try {
            try {
                connection = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                preparedStatement = connection.prepareStatement(query.toString());
                preparedStatement.setString(1, cuenta);
                preparedStatement.setInt(2, secuencia);
                resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    redencionTO.setFechaFolio(resultSet.getTimestamp(1));
                    redencionTO.setFolio(resultSet.getString(2));
                    redencionTO.agregaMensaje(0, "Proceso Exitoso.");
                } else {
                    redencionTO.agregaMensaje(1, "Proceso Exitoso.");
                }
            }
            catch (SQLException e) {
                throw new CAException(-1, "SQLException.ultimaRedencion[" + e.toString() + "]", (Exception)e);
            }
            catch (Exception e) {
                throw new CAException(-1, "ConsultasDAO.ultimaRedencion[" + e.toString() + "]", e);
            }
        }
        finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                    resultSet = null;
                }
                catch (Exception var10_14) {}
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                    preparedStatement = null;
                }
                catch (Exception var10_15) {}
            }
            if (connection != null) {
                try {
                    connection.close();
                    connection = null;
                }
                catch (Exception var10_16) {}
            }
        }
        return redencionTO;
    }

    public ArrayList<ReservacionServiceTO> consultaFolioDistribuidores(String telefono, String cuenta, String fzaVenta) throws CAException {
        ArrayList<ReservacionServiceTO> folios;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        folios = new ArrayList<ReservacionServiceTO>();
        StringBuffer query = new StringBuffer();
        query.append("SELECT FOLIO, LINEA, CUENTA, TO_CHAR(FECHAOPER,'YYYY-MM-DD'), TO_CHAR(FECHAEXPIRA,'YYYY-MM-DD'), STATUS, FZAVTA ");
        query.append("FROM ").append(this.schema_database).append("PTO_TBLRESERVACIONES ");
        query.append("WHERE CUENTA = ? AND LINEA = ? ");
        query.append("AND FZAVTA = ?");
        try {
            try {
                connection = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                preparedStatement = connection.prepareStatement(query.toString());
                preparedStatement.setString(1, cuenta);
                preparedStatement.setString(2, telefono);
                preparedStatement.setString(3, fzaVenta);
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    ReservacionServiceTO reservacion = new ReservacionServiceTO();
                    reservacion.setFolio(Long.toHexString(resultSet.getLong(1)));
                    reservacion.setTelefono(resultSet.getString(2));
                    reservacion.setCuenta(resultSet.getString(3));
                    reservacion.setFechaOperacion(resultSet.getString(4));
                    reservacion.setFechaExpiracion(resultSet.getString(5));
                    reservacion.setEstatus(resultSet.getString(6));
                    reservacion.setFuerzaVenta(resultSet.getString(7));
                    folios.add(reservacion);
                }
            }
            catch (SQLException e) {
                throw new CAException(-1, "SQLException.ultimaRedencion[" + e.toString() + "]", (Exception)e);
            }
            catch (Exception e) {
                throw new CAException(-1, "ConsultasDAO.ultimaRedencion[" + e.toString() + "]", e);
            }
        }
        finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                    resultSet = null;
                }
                catch (Exception var11_16) {}
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                    preparedStatement = null;
                }
                catch (Exception var11_17) {}
            }
            if (connection != null) {
                try {
                    connection.close();
                    connection = null;
                }
                catch (Exception var11_18) {}
            }
        }
        return folios;
    }

    public PuntosTO obtienePuntos(String cuenta, int secuencia) throws CAException {
        PuntosTO puntosTO;
        Connection connection = null;
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        puntosTO = new PuntosTO();
        try {
            try {
                connection = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                StringBuffer query = new StringBuffer("SELECT A.PUNTOSRENTA, A.PUNTOSEXCEDENTES, A.PUNTOSACAD2, A.PUNTOSACAD1, A.PUNTOSACAD,");
                query.append(" A.PUNTOSREDIM, A.PUNTOSPROMOCION, A.PUNTOSANTIGUEDAD, A.BBONO, A.BONOEQUIPO, B.STATUSPUNTOS, ");
                query.append(" A.FECHACAD, A.FECHACAD1, A.FECHACAD2, PUNTOSTRANSF, A.PUNTOSCADUC, A.FECHAFAC ");
                query.append(" FROM ").append(this.schema_database).append("PTO_TBLTOTALES A, ");
                query.append(this.schema_database).append("PTO_TBLLINEAS B ");
                query.append(" WHERE A.CUENTA = ? AND  A.SECUENCIA =? ");
                query.append(" AND A.CUENTA=B.CUENTA  AND A.SECUENCIA = B.SECUENCIA ");
                preparedStatement = connection.prepareStatement(query.toString());
                preparedStatement.setString(1, cuenta);
                preparedStatement.setInt(2, secuencia);
                resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    puntosTO.setPtsRenta(resultSet.getInt(1));
                    puntosTO.setPtsExcedentes(resultSet.getInt(2));
                    puntosTO.setPtsPorVencer2(resultSet.getInt(3));
                    puntosTO.setPtsPorVencer1(resultSet.getInt(4));
                    puntosTO.setPtsPorVencer(resultSet.getInt(5));
                    puntosTO.setPtsRedimidos(resultSet.getInt(6));
                    puntosTO.setPtsPromocion(resultSet.getInt(7));
                    puntosTO.setPtsAntiguedad(resultSet.getInt(8));
                    puntosTO.setBBono(resultSet.getString(9));
                    puntosTO.setBonoEquipo(resultSet.getInt(10));
                    puntosTO.setEstatusPuntos(resultSet.getString(11));
                    puntosTO.setFecVencer(resultSet.getDate(12));
                    puntosTO.setFecVencer1(resultSet.getDate(13));
                    puntosTO.setFecVencer2(resultSet.getDate(14));
                    puntosTO.setPtsTransferidos(resultSet.getInt(15));
                    puntosTO.setPtsVencidos(resultSet.getInt(16));
                    puntosTO.setFecFactura(resultSet.getDate(17));
                    puntosTO.agregaMensaje(0, "Proceso Exitoso");
                } else {
                    puntosTO.agregaMensaje(1, "No existe un registro de puntos totales para el telefono y la cuenta.");
                }
            }
            catch (SQLException e) {
                throw new CAException(-1, "SQLException.obtienePuntos[" + e.toString() + "]", (Exception)e);
            }
            catch (Exception e) {
                throw new CAException(-1, "ConsultasDAO.obtienePuntos[" + e.toString() + "]", e);
            }
        }
        finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                    resultSet = null;
                }
                catch (Exception var9_14) {}
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                    preparedStatement = null;
                }
                catch (Exception var9_15) {}
            }
            if (connection != null) {
                try {
                    connection.close();
                    connection = null;
                }
                catch (Exception var9_16) {}
            }
        }
        return puntosTO;
    }

    public PuntosRedimidosTO obtienePuntosRedimidos(String cuenta, Timestamp fechaFolio) throws CAException {
        PuntosRedimidosTO puntosRedimidosTO;
        puntosRedimidosTO = new PuntosRedimidosTO();
        Connection connection = null;
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        try {
            try {
                StringBuffer query = new StringBuffer("SELECT PRENTA, PRENTADISP, PEXCED, PACAD2, PACAD2DISP, FCAD2,");
                query.append("PACAD1, PACAD1DISP, FACAD1, PACAD,  PACADDISP,  FACAD,");
                query.append("PUNTOSPROMOCION, PUNTOSANTIGUEDAD,VALORPTOS, PSOBR, PSOBR1,");
                query.append("PTOSMIN, BONOPRORR ");
                query.append("FROM ").append(this.schema_database).append("PTO_TBLREDENCION ");
                query.append("WHERE CUENTA = ? ");
                query.append("AND FECHAFOLIO = ? ");
                query.append("AND  ESTATUS = 'A'");
                connection = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                preparedStatement = connection.prepareStatement(query.toString());
                preparedStatement.setString(1, cuenta);
                preparedStatement.setTimestamp(2, fechaFolio);
                resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    puntosRedimidosTO.setPtsRenta(resultSet.getInt(1));
                    puntosRedimidosTO.setPtsRentaRedimidos(resultSet.getInt(2));
                    puntosRedimidosTO.setPtsExcedentesRedimidos(resultSet.getInt(3));
                    puntosRedimidosTO.setPtsPorVencer2(resultSet.getInt(4));
                    puntosRedimidosTO.setPtsPorVencer2Redimidos(resultSet.getInt(5));
                    puntosRedimidosTO.setFecVencer2(resultSet.getDate(6));
                    puntosRedimidosTO.setPtsPorVencer1(resultSet.getInt(7));
                    puntosRedimidosTO.setPtsPorVencer1Redimidos(resultSet.getInt(8));
                    puntosRedimidosTO.setFecVencer1(resultSet.getDate(9));
                    puntosRedimidosTO.setPtsPorVencer(resultSet.getInt(10));
                    puntosRedimidosTO.setPtsPorVencerRedimidos(resultSet.getInt(11));
                    puntosRedimidosTO.setFecVencer(resultSet.getDate(12));
                    puntosRedimidosTO.setPtsPromocionRedimidos(resultSet.getInt(13));
                    puntosRedimidosTO.setPtsPorAntiguedadRedimidos(resultSet.getInt(14));
                    puntosRedimidosTO.setPtsRedimidos(resultSet.getInt(15));
                    puntosRedimidosTO.setPtsSobrantes(resultSet.getInt(16));
                    puntosRedimidosTO.setPtsSobrantes1(resultSet.getInt(17));
                    puntosRedimidosTO.setPtsMinimos(resultSet.getInt(18));
                    puntosRedimidosTO.setBonoProrrateo(resultSet.getInt(19));
                    puntosRedimidosTO.agregaMensaje(0, "Proceso Exitoso.");
                } else {
                    puntosRedimidosTO.agregaMensaje(1, "No existe un registro de la redencion seleccionada.");
                }
            }
            catch (SQLException e) {
                throw new CAException(-1, "SQLException.obtienePuntosRedimidos[" + e.toString() + "]", (Exception)e);
            }
            catch (Exception e) {
                throw new CAException(-1, "ConsultasDAO.obtienePuntosRedimidos[" + e.toString() + "]", e);
            }
        }
        finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                    resultSet = null;
                }
                catch (Exception var9_14) {}
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                    preparedStatement = null;
                }
                catch (Exception var9_15) {}
            }
            if (connection != null) {
                try {
                    connection.close();
                    connection = null;
                }
                catch (Exception var9_16) {}
            }
        }
        return puntosRedimidosTO;
    }

    public ArrayList<AlianzasTO> consultaCancelaCanje(String telefono, String cuentaAlianza, int opcion) throws CAException {
        ArrayList<AlianzasTO> alianzas;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        alianzas = new ArrayList<AlianzasTO>();
        try {
            try {
                String sQuery = " SELECT a.folio , a.FechaOper, a.Ptransf, a.ctaalianza,A.STATUSTRANS ";
                sQuery = String.valueOf(sQuery) + " FROM " + this.schema_database + "PTO_TBLALIANZAS a, " + this.schema_database + "PTO_TBLLINEAS b " + " WHERE b.Linea = ? AND a.Idcuenta = ? ";
                if (opcion == 1 && cuentaAlianza != null) {
                    sQuery = String.valueOf(sQuery) + "AND a.ctaalianza =? ";
                }
                sQuery = String.valueOf(sQuery) + " AND a.cuenta = b.cuenta AND a.secuencia = b.secuencia AND Statustrans = 4 AND a.Estatus = 'A' ORDER BY a.FechaOper desc,a.FOLIO desc ";
                connection = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                preparedStatement = connection.prepareStatement(sQuery);
                preparedStatement.setString(1, telefono);
                preparedStatement.setInt(2, opcion);
                if (opcion == 1 && cuentaAlianza != null) {
                    preparedStatement.setString(3, cuentaAlianza);
                }
                resultSet = preparedStatement.executeQuery();
                long diaActual = System.currentTimeMillis();
                while (resultSet.next()) {
                    AlianzasTO alianzasTO = new AlianzasTO();
                    alianzasTO.setFolio(resultSet.getString(1));
                    alianzasTO.setFechaOperacion(resultSet.getDate(2));
                    alianzasTO.setPtsTransferidos(resultSet.getInt(3));
                    alianzasTO.setCuentaAlianza(resultSet.getString(4));
                    alianzasTO.setStatusTrans(resultSet.getInt(5));
                    if (opcion == 2) {
                        long dias = Utils.calcularDiasEntreFechas((long)alianzasTO.getFechaOperacion().getTime(), (long)diaActual);
                        if (dias > 30) continue;
                        alianzas.add(alianzasTO);
                        continue;
                    }
                    alianzas.add(alianzasTO);
                }
            }
            catch (SQLException e) {
                throw new CAException(-1, "SQLException.obtienePuntosRedimidos[" + e.toString() + "]", (Exception)e);
            }
            catch (Exception e) {
                throw new CAException(-1, "ConsultasDAO.obtienePuntosRedimidos[" + e.toString() + "]", e);
            }
        }
        finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                    resultSet = null;
                }
                catch (Exception var15_18) {}
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                    preparedStatement = null;
                }
                catch (Exception var15_19) {}
            }
            if (connection != null) {
                try {
                    connection.close();
                    connection = null;
                }
                catch (Exception var15_20) {}
            }
        }
        return alianzas;
    }

    public PuntosRedimidosTO consultaPuntosRedimAlianza(String folio) throws CAException {
        PuntosRedimidosTO redimidosTO;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        redimidosTO = new PuntosRedimidosTO();
        try {
            try {
                String sQuery = " SELECT PRenta, PAcad, PAcad1,  PAcad2, PExce,PuntosAntiguedad,PuntosPromocion,PTransf FROM " + this.schema_database + "PTO_TBLALIANZAS WHERE  Folio =?";
                connection = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                statement = connection.prepareStatement(sQuery);
                statement.setString(1, folio);
                resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    redimidosTO.setPtsRentaRedimidos(resultSet.getInt(1));
                    redimidosTO.setPtsPorVencerRedimidos(resultSet.getInt(2));
                    redimidosTO.setPtsPorVencer1Redimidos(resultSet.getInt(3));
                    redimidosTO.setPtsPorVencer2Redimidos(resultSet.getInt(4));
                    redimidosTO.setPtsExcedentesRedimidos(resultSet.getInt(5));
                    redimidosTO.setPtsPorAntiguedadRedimidos(resultSet.getInt(6));
                    redimidosTO.setPtsPromocionRedimidos(resultSet.getInt(7));
                    redimidosTO.setPtsTransferidos(resultSet.getInt(8));
                    redimidosTO.agregaMensaje(0, "Proceso Exitoso.");
                } else {
                    redimidosTO.agregaMensaje(1, "Folio del canje no es valido, por lo que no se consiguio informacion de la tabla de Alianzas");
                }
            }
            catch (SQLException e) {
                throw new CAException(-1, "SQLException.consultaPuntosRedimAlianza[" + e.toString() + "]", (Exception)e);
            }
            catch (Exception e) {
                throw new CAException(-1, "ConsultasDAO.consultaPuntosRedimAlianza[" + e.toString() + "]", e);
            }
        }
        finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                    resultSet = null;
                }
                catch (Exception var8_13) {}
            }
            if (statement != null) {
                try {
                    statement.close();
                    statement = null;
                }
                catch (Exception var8_14) {}
            }
            if (connection != null) {
                try {
                    connection.close();
                    connection = null;
                }
                catch (Exception var8_15) {}
            }
        }
        return redimidosTO;
    }

    public CicloFacturacionTO consultaCicloFac(int ciclo, int region) throws CAException {
        CicloFacturacionTO cicloFacTO;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        StringBuffer query = new StringBuffer();
        Connection lConn = null;
        cicloFacTO = null;
        query.append(" SELECT CICLO, IDREGION, FECHACORTE,DESCRIPCION ");
        query.append(" FROM  ").append(this.schema_database).append("PTO_CTLCICLOS");
        query.append(" WHERE CICLO=? AND IDREGION=? ");
        try {
            try {
                lConn = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                preparedStatement = lConn.prepareStatement(query.toString());
                preparedStatement.setInt(1, ciclo);
                preparedStatement.setInt(2, region != 9 ? 1 : region);
                resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    cicloFacTO = new CicloFacturacionTO();
                    cicloFacTO.setCicloFac(resultSet.getInt("CICLO"));
                    cicloFacTO.setIdRegion(resultSet.getInt("IDREGION"));
                    cicloFacTO.setFechaCorte((java.util.Date)resultSet.getDate("FECHACORTE"));
                    cicloFacTO.setDescripcion(resultSet.getString("DESCRIPCION") != null ? resultSet.getString("DESCRIPCION") : "");
                }
            }
            catch (SQLException e) {
                throw new CAException(-1, "SQLException.consultaCicloFac[" + e.toString() + "]", (Exception)e);
            }
            catch (Exception e) {
                throw new CAException(-1, "ConsultasDAO.consultaCicloFac[" + e.toString() + "]", e);
            }
        }
        finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                    resultSet = null;
                }
                catch (Exception var10_14) {}
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                    preparedStatement = null;
                }
                catch (Exception var10_15) {}
            }
            if (lConn != null) {
                try {
                    lConn.close();
                    lConn = null;
                }
                catch (Exception var10_16) {}
            }
        }
        return cicloFacTO;
    }

    public ArrayList<PlanTO> planesRedencionByRegion(int region) throws CAException {
        return this.planesRedencion(region, null, null, null, null, null, null, false);
    }

    public ArrayList<PlanTO> planesSisact(int region, String tecnologia, String modalidad, String mixto, String banSisact, String tipoPlan, String fzaVenta) throws CAException {
        return this.planesRedencion(region, tecnologia, modalidad, mixto, banSisact, tipoPlan, fzaVenta, true);
    }

    public ArrayList<PlanTO> planesRedencion(int region, String tecnologia, String modalidad, String mixto, String banSisact, String tipoPlan, String fzaVenta, boolean esSisact) throws CAException {
        ArrayList<PlanTO> planes;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        StringBuffer query = new StringBuffer();
        Connection lConn = null;
        planes = new ArrayList<PlanTO>();
        PlanTO planTO = null;
        query.append(" SELECT ");
        query.append(" DISTINCT A.IDPLAN, A.DESCRIPCION, A.IDGRUPOPROMOCION, A.ADENDUM, A.RENTA, C.SEGMENTO, A.TECNOLOGIA, A.IDREGION, A.BREDENCIONANTC, B.TIPOPROMOCION ");
        query.append(" FROM  ").append(this.schema_database).append("PTO_CTLPLANESTARIFARIOS A,");
        query.append("       ").append(this.schema_database).append("PTO_CTLGRUPOPROMOCION B, ");
        query.append("       ").append(this.schema_database).append("PTO_CTLSEGMENTOS C ");
        query.append(" WHERE A.ESTATUS = 'A' AND B.ESTATUS = 'A' ");
        if (region == 10) {
            query.append(" AND A.IDREGION IN (1,2,3,4,5,6,7,8)");
        } else {
            query.append(" AND A.IDREGION =?");
        }
        query.append(" AND A.BREDENCION != 0 ");
        query.append(" AND A.ADENDUM != 0 ");
        if (esSisact) {
            query.append(" AND A.TECNOLOGIA = ? ");
            query.append(" AND A.MODALIDAD = ? ");
            query.append(" AND A.BMIXTO = ? ");
            query.append(" AND A.BSISACT = ? ");
            query.append(" AND (A.TIPO_PLAN = ? ");
            query.append(" \t\tOR ( A.TIPO_PLAN = ( ");
            query.append(" \t\t\tSELECT f.PLAN_VISIBLE FROM ").append(this.schema_database).append("PTO_CTLFZA_VENTAS f ");
            query.append(" \t\t\tWHERE f.PLAN_VISIBLE = A.TIPO_PLAN AND f.FZAVENTAS = ? ");
            query.append(" \t\t\tAND f.ESTATUS = A.ESTATUS ) ");
            query.append(" \t\t) ");
            query.append(" ) ");
        } else {
            query.append(" AND A.TIPO_PLAN NOT IN ('IMFO','LIV') ");
        }
        query.append(" AND A.IDGRUPOPROMOCION = B.IDGRUPOPROMOCION ");
        query.append(" AND A.IDSEGMENTO = C.IDSEGMENTO ");
        query.append(" AND A.IDREGION = C.IDREGION ");
        query.append(" ORDER BY ASCII(SUBSTR(A.IDPLAN,1,1)), ASCII(SUBSTR(A.IDPLAN,2,1)), ASCII(SUBSTR(A.IDPLAN,3,1)), ASCII(SUBSTR(A.IDPLAN,4,1)), ASCII(SUBSTR(A.IDPLAN,5,1)) ASC ");
        try {
            try {
                lConn = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                statement = lConn.prepareStatement(query.toString());
                if (region != 10) {
                    statement.setInt(1, region);
                }
                if (esSisact) {
                    statement.setString(2, tecnologia);
                    statement.setString(3, modalidad);
                    statement.setString(4, mixto);
                    statement.setString(5, "S");
                    statement.setString(6, "MASIVO");
                    statement.setString(7, fzaVenta);
                }
                resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    planTO = new PlanTO();
                    planTO.setIdPlanNuevo(resultSet.getString("IDPLAN"));
                    planTO.setDescSegmento(resultSet.getString("SEGMENTO"));
                    planTO.setIdGrupoPromocion(resultSet.getInt("IDGRUPOPROMOCION"));
                    planTO.setTecnologia(resultSet.getString("TECNOLOGIA"));
                    planTO.setAdendumNvo(resultSet.getInt("ADENDUM"));
                    if (esSisact) {
                        planTO.setDescripcion(resultSet.getString(2));
                    } else {
                        planTO.setDescripcion(String.valueOf(resultSet.getString(1)) + " - " + resultSet.getString(2) + " $" + resultSet.getString(5));
                    }
                    planTO.setIdRegion(resultSet.getInt("IDREGION"));
                    planTO.setBanRedencionAnct(resultSet.getString("BREDENCIONANTC"));
                    planTO.setTipoPromocion(resultSet.getString("TIPOPROMOCION"));
                    planes.add(planTO);
                }
            }
            catch (SQLException e) {
                throw new CAException(-1, "SQLException.planesRedencion[" + e.toString() + "]", (Exception)e);
            }
            catch (Exception e) {
                throw new CAException(-1, "ConsultasDAO.planesRedencion[" + e.toString() + "]", e);
            }
        }
        finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                    resultSet = null;
                }
                catch (Exception var17_21) {}
            }
            if (statement != null) {
                try {
                    statement.close();
                    statement = null;
                }
                catch (Exception var17_22) {}
            }
            if (lConn != null) {
                try {
                    lConn.close();
                    lConn = null;
                }
                catch (Exception var17_23) {}
            }
        }
        return planes;
    }

    public int consultaRegionLinea(String lTelefono, String cuenta) throws CAException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        StringBuffer sQuery = new StringBuffer();
        String consulta = "";
        try {
            consulta = lTelefono != null && lTelefono.trim().length() > 0 ? " A.LINEA = ? " : " A.CUENTA = ? ";
            if (lTelefono != null || cuenta != null) {
                sQuery.append(" SELECT A.IDREGION ");
                sQuery.append("  FROM  ").append(this.schema_database).append("PTO_TBLLINEAS A ");
                sQuery.append(" WHERE " + consulta);
                sQuery.append(" GROUP BY A.LINEA, A.CUENTA, A.SECUENCIA, A.IDREGION, A.FECHAADD ");
                sQuery.append(" ORDER BY A.FECHAADD DESC");
                connection = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                preparedStatement = connection.prepareStatement(sQuery.toString(), 1004, 1007);
                if (lTelefono != null && lTelefono.trim().length() > 0) {
                    preparedStatement.setString(1, lTelefono);
                } else {
                    preparedStatement.setString(1, cuenta);
                }
                resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    int n = resultSet.getInt("IDREGION");
                    return n;
                }
                return 0;
            }
            try {
                throw new CAException(-1, "Debe especificar el telfono/cuenta a consultar.");
            }
            catch (Exception e) {
                throw new CAException(-1, "ConsultasDAO.consultaRegionLinea[" + e.toString() + "]", e);
            }
        } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                    resultSet = null;
                }
                catch (Exception var11_9) {}
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                    preparedStatement = null;
                }
                catch (Exception var11_10) {}
            }
            if (connection != null) {
                try {
                    connection.close();
                    connection = null;
                }
                catch (Exception var11_11) {}
            }
        }
		return 0;
    }

    public ArrayList<CatalogoTO> consultaAreasPromocion() throws CAException {
        ArrayList<CatalogoTO> areasPromos;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        StringBuffer query = new StringBuffer();
        query.append(" SELECT IDAREAPROMOCION, DESCRIPCION ");
        query.append(" FROM  ").append(this.schema_database).append("PTO_CTLAREASPROMOCION ");
        query.append(" WHERE ESTATUS = ? ");
        query.append(" ORDER BY IDAREAPROMOCION ");
        areasPromos = new ArrayList<CatalogoTO>(0);
        CatalogoTO areaPromoTO = null;
        try {
            try {
                connection = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                preparedStatement = connection.prepareStatement(query.toString());
                preparedStatement.setInt(1, 1);
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    areaPromoTO = new CatalogoTO();
                    areaPromoTO.setIdVariable(resultSet.getString("IDAREAPROMOCION"));
                    areaPromoTO.setDescripcion(resultSet.getString("DESCRIPCION"));
                    areasPromos.add(areaPromoTO);
                }
            }
            catch (SQLException e) {
                throw new CAException(-1, "SQLException.consultaAreasPromocion[" + e.toString() + "]", (Exception)e);
            }
            catch (Exception e) {
                throw new CAException(-1, "ConsultasDAO.consultaAreasPromocion[" + e.toString() + "]", e);
            }
        }
        finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                    resultSet = null;
                }
                catch (Exception var9_13) {}
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                    preparedStatement = null;
                }
                catch (Exception var9_14) {}
            }
            if (connection != null) {
                try {
                    connection.close();
                    connection = null;
                }
                catch (Exception var9_15) {}
            }
        }
        return areasPromos;
    }

    public void cancelaReservacionSisact(String folio, String cuenta, int secuencia, String fzaVenta, Connection conexion) throws CAException {
    	PreparedStatement pStmt1 = null;
        PreparedStatement pStmt2 = null;
        ResultSet rset1 = null;
        StringBuffer query = null;
        MensajeTO mensajeTO = null;
        mensajeTO = this.puntosDAO.actualizaReservacion(conexion, System.currentTimeMillis(), "SISACT", "C", null, folio, false);
        if (mensajeTO.getIdMensaje() != 0) {
            throw new CAException(-1, "ConsultasDAO.cancelaReservacionSisact[" + mensajeTO.getMensaje() + "]");
        }
        System.out.println("Se actualiza el Status en PTO_TBLRESERVACIONES");
        try {
            try {
                query = new StringBuffer();
                query = new StringBuffer();
                query.append("SELECT Folio ");
                query.append("FROM ").append(this.schema_database).append("PTO_TBLRESERVACIONES ");
                query.append("WHERE Cuenta = ? ");
                query.append("AND Status IN ('A','P') ");
                query.append("AND Folio != ? ");
                pStmt1 = conexion.prepareStatement(query.toString());
                pStmt1.setString(1, cuenta);
                pStmt1.setString(2, folio);
                rset1 = pStmt1.executeQuery();
                if (rset1.next()) {
                    System.out.println("No se puede liberar status de puntos, existe otra reservacion: " + Long.toHexString(Long.parseLong(rset1.getString(1))).toUpperCase());
                } else {
                    System.out.println("Se libera el status de puntos por vigencia, no existe otra reservacion.");
                    mensajeTO = this.puntosDAO.actualizaLinea(conexion, cuenta, secuencia, "0");
                    if (mensajeTO.getIdMensaje() != 0) {
                        throw new CAException(-1, "ConsultasDAO.cancelaReservacionSisact[" + mensajeTO.getMensaje() + "]");
                    }
                }
                throw new CAException(-1, "La fecha de vigencia de la reservacion ha expirado.");
            }
            catch (SQLException e) {
                throw new CAException(-1, "ConsultasDAO.cancelaReservacionSisact[" + e.toString() + "]");
            }
            catch (Exception e) {
                throw new CAException(-1, "ConsultasDAO.cancelaReservacionSisact[" + e.toString() + "]");
            }
        }
        catch (Throwable var12_13) {
            try {
                if (rset1 != null) {
                    rset1.close();
                    rset1 = null;
                }
                if (pStmt1 != null) {
                    pStmt1.close();
                    pStmt1 = null;
                }
                if (pStmt2 != null) {
                    pStmt2.close();
                    pStmt2 = null;
                }
            }
            catch (Exception var13_14) {
                // empty catch block
            }
            //throw var12_13;
        }
    }

    public void actualizaRedencion(String folio, String claveSisact, TelefonoServiceTO telefonoTO, ReservacionTO reservacionTO, ArrayList<int[]> puntosRedecionSisact, Connection conexion) throws CAException {
        Statement pStmt = null;
        Statement stmt = null;
        ResultSet rset = null;
        String gbTipoRed = reservacionTO.getTipoRedencion();
        long fechaTransaccion = System.currentTimeMillis();
        int[] puntosAConsumir = puntosRedecionSisact.get(0);
        try {
            try {
                stmt = conexion.createStatement();
                ParametrosTO parametrosTO = new ParametrosTO();
                parametrosTO.setCuenta(telefonoTO.getCuenta());
                parametrosTO.setTelefono(telefonoTO.getTelefono());
                parametrosTO.setSecuencia(telefonoTO.getSecuencia());
                parametrosTO.setPlanNvo(reservacionTO.getPlanNuevo());
                parametrosTO.setPlanAnt(telefonoTO.getPlanM2K());
                PuntosRedimidosTO puntosRedimidosTO = new PuntosRedimidosTO();
                puntosRedimidosTO.setPtsExcedentes(telefonoTO.getPtsExcedentes() + reservacionTO.getSobrantesBono());
                puntosRedimidosTO.setPtsRedimidos(reservacionTO.getProductosTO().getValorPuntos());
                puntosRedimidosTO.setPtsRentaRedimidos(puntosAConsumir[6]);
                puntosRedimidosTO.setPtsExcedentesRedimidos(puntosAConsumir[5]);
                if (!(telefonoTO.getFecVencer2() == null || telefonoTO.getFecVencer2().equals(""))) {
                    puntosRedimidosTO.setFecVencer2Tmp(new Date(Utils.DATEFORMATdd_MM_YYYY.parse(telefonoTO.getFecVencer2()).getTime()));
                    puntosRedimidosTO.setPtsPorVencer2Redimidos(puntosAConsumir[2]);
                }
                if (!(telefonoTO.getFecVencer1() == null || telefonoTO.getFecVencer1().equals(""))) {
                    puntosRedimidosTO.setFecVencer1Tmp(new Date(Utils.DATEFORMATdd_MM_YYYY.parse(telefonoTO.getFecVencer1()).getTime()));
                    puntosRedimidosTO.setPtsPorVencer1Redimidos(puntosAConsumir[1]);
                }
                if (!(telefonoTO.getFecVencer() == null || telefonoTO.getFecVencer().equals(""))) {
                    puntosRedimidosTO.setFecVencerTmp(new Date(Utils.DATEFORMATdd_MM_YYYY.parse(telefonoTO.getFecVencer()).getTime()));
                    puntosRedimidosTO.setPtsPorVencerRedimidos(puntosAConsumir[0]);
                }
                puntosRedimidosTO.setPtsPromocionRedimidos(puntosAConsumir[3]);
                puntosRedimidosTO.setPtsPorAntiguedadRedimidos(puntosAConsumir[4]);
                reservacionTO.getProductosTO().setValorPuntosTmp(reservacionTO.getProductosTO().getValorPuntos());
                puntosRedimidosTO.setBonoEquipo(telefonoTO.getBonoEquipo());
                reservacionTO.setPuntosRedimidosTO(puntosRedimidosTO);
                MensajeTO mensajeTO = this.puntosDAO.insertaRedencion(conexion, (RedencionTO)reservacionTO, parametrosTO, fechaTransaccion);
                if (mensajeTO.getIdMensaje() != 0) {
                    throw new CAException(-1, "ConsultasDAO.actualizaRedencion[" + mensajeTO.getMensaje() + "]");
                }
                int totAjustes = reservacionTO.getProductosTO().getValorPuntosTmp() * -1;
                mensajeTO = this.puntosDAO.insertaDetalle(conexion, fechaTransaccion, "Redencion Terminada", 5, totAjustes, null, telefonoTO.getCuenta(), telefonoTO.getSecuencia(), telefonoTO.getTelefono(), claveSisact);
                if (mensajeTO.getIdMensaje() != 0) {
                    throw new CAException(-1, "ConsultasDAO.actualizaRedencion[" + mensajeTO.getMensaje() + "]");
                }
                if (reservacionTO.getSobrantesBono() != 0 && (mensajeTO = this.puntosDAO.insertaDetalle(conexion, fechaTransaccion, "Asigna Pts Sobrantes Bono Equipo", 52, reservacionTO.getSobrantesBono(), null, telefonoTO.getCuenta(), telefonoTO.getSecuencia(), telefonoTO.getTelefono(), claveSisact)).getIdMensaje() != 0) {
                    throw new CAException(-1, "ConsultasDAO.actualizaRedencion[" + mensajeTO.getMensaje() + "]");
                }
                mensajeTO = this.puntosDAO.actualizaPuntos(puntosRedimidosTO, conexion, gbTipoRed, telefonoTO.getCuenta(), telefonoTO.getSecuencia(), true);
                if (mensajeTO.getIdMensaje() != 0) {
                    throw new CAException(-1, "ConsultasDAO.actualizaRedencion[" + mensajeTO.getMensaje() + "]");
                }
                mensajeTO = this.puntosDAO.actualizaReservacion(conexion, fechaTransaccion, claveSisact, "R", null, folio, false);
                if (mensajeTO.getIdMensaje() != 0) {
                    throw new CAException(-1, "ConsultasDAO.actualizaRedencion[" + mensajeTO.getMensaje() + "]");
                }
                mensajeTO = this.puntosDAO.actualizaLinea(conexion, telefonoTO.getCuenta(), telefonoTO.getSecuencia(), "0");
                if (mensajeTO.getIdMensaje() != 0) {
                    throw new CAException(-1, "ConsultasDAO.actualizaRedencion[" + mensajeTO.getMensaje() + "]");
                }
                this.puntosDAO.insertaConstancia((RedencionTO)reservacionTO, parametrosTO, conexion, fechaTransaccion, "A");
            }
            catch (Exception e) {
                throw new CAException(-1, "ConsultasDAO.actualizaRedencion[" + e.toString() + "]");
            }
        }
        finally {
            if (rset != null) {
                try {
                    rset.close();
                    rset = null;
                }
                catch (Exception var19_22) {}
            }
            if (stmt != null) {
                try {
                    stmt.close();
                    stmt = null;
                }
                catch (Exception var19_23) {}
            }
            if (pStmt != null) {
                try {
                    pStmt.close();
                    pStmt = null;
                }
                catch (Exception var19_24) {}
            }
        }
    }

    public ArrayList<int[]> calculaPuntos(ReservacionTO reservacionTO, TelefonoServiceTO telefonoTO) {
        ArrayList<int[]> puntosRedecionSisact = new ArrayList<int[]>(0);
        int[] puntosConsumidos = new int[9];
        int[] puntosAConsumir = new int[9];
        int gbSaldoAnt = telefonoTO.getPtsDisponibles();
        int gbPtosMin = reservacionTO.getPtsMinimos();
        int variableLocal = gbSaldoAnt + gbPtosMin;
        int dispRest = variableLocal - reservacionTO.getProductosTO().getValorPuntos();
        telefonoTO.setPtsDisponiblesCF(String.valueOf(dispRest));
        System.out.println("Puntos DispRest :" + telefonoTO.getPtsDisponiblesCF());
        puntosConsumidos[0] = telefonoTO.getPtsPorVencer();
        puntosConsumidos[1] = telefonoTO.getPtsPorVencer1();
        puntosConsumidos[2] = telefonoTO.getPtsPorVencer2();
        puntosConsumidos[3] = telefonoTO.getPtsPromocion();
        puntosConsumidos[4] = telefonoTO.getPtsAntiguedad();
        puntosConsumidos[5] = telefonoTO.getPtsExcedentes();
        puntosConsumidos[6] = telefonoTO.getPtsRenta();
        int inPuntos = reservacionTO.getProductosTO().getValorPuntos();
        for (int i = 0; i <= 6; ++i) {
            if (puntosConsumidos[i] > 0) {
                if (puntosConsumidos[i] <= inPuntos) {
                    inPuntos-=puntosConsumidos[i];
                    puntosAConsumir[i] = puntosConsumidos[i];
                    puntosConsumidos[i] = 0;
                    continue;
                }
                puntosConsumidos[i] = puntosConsumidos[i] - inPuntos;
                puntosAConsumir[i] = inPuntos;
                inPuntos = 0;
                break;
            }
            puntosConsumidos[i] = 0;
        }
        puntosRedecionSisact.add(0, puntosAConsumir);
        puntosRedecionSisact.add(1, puntosConsumidos);
        System.out.println("<<< PUNTOS REDIMIDOS >>> " + reservacionTO.getProductosTO().getValorPuntos());
        return puntosRedecionSisact;
    }
}

