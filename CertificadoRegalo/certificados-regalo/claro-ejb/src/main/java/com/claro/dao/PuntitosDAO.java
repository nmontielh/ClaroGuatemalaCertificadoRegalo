/*
 * Decompiled with CFR 0_102.
 * 
 * Could not load the following classes:
 *  com.claro.exception.CAException
 *  com.claro.transfer.ParametrosTO
 *  com.claro.transfer.PuntoVentaTO
 *  com.claro.transfer.PuntosTO
 *  com.claro.transfer.TelefonoTO
 *  com.claro.util.ServiceLocator
 *  org.apache.log4j.Logger
 */
package com.claro.dao;

import com.claro.exception.CAException;
import com.claro.transfer.ParametrosTO;
import com.claro.transfer.PuntoVentaTO;
import com.claro.transfer.PuntosTO;
import com.claro.transfer.TelefonoTO;
import com.claro.util.ServiceLocator;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import org.apache.log4j.Logger;

public class PuntitosDAO {
    protected final Logger logger = Logger.getLogger((String)"loggerCirculoAzul");
    protected final Logger error = Logger.getLogger((String)"loggerCirculoAzulError");
    private String schema_database;

    public PuntitosDAO() {
        try {
            this.schema_database = ServiceLocator.getInstance().getVariable(ServiceLocator.schema_database);
        }
        catch (Exception e) {
            this.error.error((Object)"PuntitosDAO", (Throwable)e);
        }
    }

    public TelefonoTO procedimientoGeneral(ParametrosTO parametrosTO) throws CAException {
        TelefonoTO telefonoTO;
        Connection connection = null;
        telefonoTO = null;
        try {
            connection = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
            telefonoTO = this.consultaDatosPuntos(parametrosTO, connection);
            if (telefonoTO.getIdMensaje() == 1) {
                TelefonoTO telefonoTO2 = telefonoTO;
                return telefonoTO2;
            }
            try {
                if (telefonoTO.getPuntosTO().getPtosStatus().trim().equals("R")) {
                    telefonoTO.agregaMensaje(2, "Los Puntos estan " + telefonoTO.getPuntosTO().getDescPtsReservados());
                } else if (telefonoTO.getPuntosTO().getPtosStatus().trim().equals("C")) {
                    telefonoTO.agregaMensaje(2, "Los puntos estan congelados");
                }
            }
            catch (Exception e) {
                throw new CAException(-1, "ConsultaDAO.procedimientoGeneral[" + e.toString() + "]", e);
            }
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
                catch (Exception var7_5) {}
            }
        }
        return telefonoTO;
    }

    private TelefonoTO consultaDatosPuntos(ParametrosTO parametrosTO, Connection connection) throws CAException {
        TelefonoTO telefonoTO;
        telefonoTO = new TelefonoTO();
        if (parametrosTO.getCuenta() == null && parametrosTO.getTelefono() == null) {
            throw new CAException(-1, "Debe especificar el telefono a consultar.");
        }
        String sBusqueda = null;
        StringBuffer query = new StringBuffer();
        if (!(parametrosTO.getCuenta() == null || parametrosTO.getTelefono() == null || parametrosTO.getCuenta().trim().equals("") || parametrosTO.getTelefono().trim().equals(""))) {
            sBusqueda = "a.Linea = '" + parametrosTO.getTelefono() + "' AND " + "a.Cuenta = '" + parametrosTO.getCuenta() + "' ";
        } else {
            if (parametrosTO.getCuenta() != null) {
                sBusqueda = "a.Cuenta = '" + parametrosTO.getCuenta() + "' ";
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
        query.append("        A.FECHAADD,A.ANACR ");
        query.append(" FROM ").append(this.schema_database).append("PTO_TBLLINEAS A, ").append(this.schema_database).append("PTO_TBLTOTALES B,");
        query.append(this.schema_database).append("PTO_CTLPLANESTARIFARIOS C, ").append(this.schema_database).append("PTO_CTLSEGMENTOS D ");
        query.append(" WHERE ").append(sBusqueda);
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
        query.append("   A.FECHAADD, A.ANACR");
        query.append(" ORDER BY A.FECHAADD DESC, A.FECHAALTA DESC ");
        Statement statement = null;
        Statement statementFvta = null;
        ResultSet resultSet = null;
        ResultSet resultSetFvta = null;
        PuntosTO oPuntos = null;
        try {
            try {
                statement = connection.createStatement(1004, 1007);
                resultSet = statement.executeQuery(query.toString());
                if (resultSet.next()) {
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
                    telefonoTO.setPuntosTO(oPuntos);
                    telefonoTO.agregaMensaje(0, "Proceso Exitoso");
                } else {
                    telefonoTO.agregaMensaje(-1, "Telefono no Encontrado");
                }
            }
            catch (SQLException e) {
                throw new CAException(-1, "SQLException.consultaDatosPuntos[" + e.toString() + "]", (Exception)e);
            }
            catch (Exception e) {
                throw new CAException(-1, "ConsultasDAO.consultaDatosPuntos[" + e.toString() + "]", e);
            }
        }
        finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                    resultSet = null;
                }
                catch (Exception var13_18) {}
            }
            if (resultSetFvta != null) {
                try {
                    resultSetFvta.close();
                    resultSetFvta = null;
                }
                catch (Exception var13_19) {}
            }
            if (statement != null) {
                try {
                    statement.close();
                    statement = null;
                }
                catch (Exception var13_20) {}
            }
            if (statementFvta != null) {
                try {
                    statementFvta.close();
                    statementFvta = null;
                }
                catch (Exception var13_21) {}
            }
        }
        return telefonoTO;
    }

    public ArrayList<TelefonoTO> consultaLinea(TelefonoTO telefonoTO) throws Exception {
        ArrayList<TelefonoTO> lineas;
        Connection oConn = null;
        PreparedStatement oStmt = null;
        ResultSet oRset = null;
        lineas = new ArrayList<TelefonoTO>();
        try {
            try {
                oConn = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                String where = "";
                where = telefonoTO.getTelefono() != null && !telefonoTO.getTelefono().equalsIgnoreCase("") ? "LINEA=? " : "CUENTA=?";
                StringBuffer query = new StringBuffer();
                query.append("SELECT LINEA,CUENTA,IDREGION,STATUSPUNTOS,SECUENCIA,");
                query.append("PLAN,CICLOFACT,STATUSTEL,ADDENDUM,FECHAADD,FECHAALTA ");
                query.append("FROM ").append(this.schema_database).append("PTO_TBLLINEAS WHERE ").append(where);
                oStmt = oConn.prepareStatement(query.toString());
                if (!(telefonoTO.getTelefono() == null || telefonoTO.getTelefono().equalsIgnoreCase(""))) {
                    oStmt.setString(1, telefonoTO.getTelefono());
                } else {
                    oStmt.setString(1, telefonoTO.getCuenta());
                }
                oRset = oStmt.executeQuery();
                while (oRset.next()) {
                    TelefonoTO telefono = new TelefonoTO();
                    PuntosTO puntosTO = new PuntosTO();
                    telefono.setTelefono(oRset.getString(1));
                    telefono.setCuenta(oRset.getString(2));
                    telefono.setRegion(oRset.getInt(3));
                    puntosTO.setEstatusPuntos(oRset.getString(4));
                    telefono.setSecuencia(oRset.getString(5));
                    telefono.setPlan(oRset.getString(6));
                    telefono.setCiclo(oRset.getString(7));
                    telefono.setNickName(oRset.getString(8));
                    telefono.setAddendum(oRset.getInt(9));
                    telefono.setFechaExpira(oRset.getTimestamp(10));
                    telefono.setFechaAltaTime(oRset.getTimestamp(11));
                    telefono.setPuntosTO(puntosTO);
                    lineas.add(telefono);
                }
            }
            catch (Exception e) {
                throw new Exception("Consulta Linea [" + e.toString() + "]");
            }
        }
        finally {
            if (oRset != null) {
                try {
                    oRset.close();
                    oRset = null;
                }
                catch (Exception var11_15) {}
            }
            if (oStmt != null) {
                try {
                    oStmt.close();
                    oStmt = null;
                }
                catch (Exception var11_16) {}
            }
            if (oConn != null) {
                try {
                    oConn.close();
                    oConn = null;
                }
                catch (Exception var11_17) {}
            }
        }
        return lineas;
    }

    public ArrayList<TelefonoTO> consultaReserva(TelefonoTO telefonoTO) throws Exception {
        ArrayList<TelefonoTO> lineas;
        Connection oConn = null;
        PreparedStatement oStmt = null;
        ResultSet oRset = null;
        lineas = new ArrayList<TelefonoTO>();
        try {
            try {
                oConn = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                StringBuffer query = new StringBuffer();
                query.append("SELECT FOLIO,CUENTA,SECUENCIA,IDREGION,");
                query.append("IDPLAN,STATUS,LINEA,DESCRIPCION,MARCA,MODELO,FECHAEXPIRA,");
                query.append("TIPOREDEN,TIPOPROM,FECHAULTMOD,FZAVTA ");
                query.append("FROM ").append(this.schema_database).append("PTO_TBLRESERVACIONES ");
                query.append("WHERE CUENTA=? AND LINEA=? and IDREGION=? ");
                query.append("order by FECHAEXPIRA desc");
                oStmt = oConn.prepareStatement(query.toString());
                oStmt.setString(1, telefonoTO.getCuenta());
                oStmt.setString(2, telefonoTO.getTelefono());
                oStmt.setInt(3, telefonoTO.getRegion());
                oRset = oStmt.executeQuery();
                while (oRset.next()) {
                    TelefonoTO telefono = new TelefonoTO();
                    PuntosTO puntosTO = new PuntosTO();
                    telefono.setFolio(oRset.getString(1));
                    telefono.setCuenta(oRset.getString(2));
                    telefono.setSecuencia(oRset.getString(3));
                    telefono.setRegion(oRset.getInt(4));
                    telefono.setPlan(oRset.getString(5));
                    puntosTO.setEstatusPuntos(oRset.getString(6));
                    telefono.setTelefono(oRset.getString(7));
                    telefono.setDescripcionPlan(oRset.getString(8));
                    telefono.setMarca(oRset.getString(9));
                    telefono.setModelo(oRset.getString(10));
                    telefono.setFechaExpira(oRset.getTimestamp(11));
                    telefono.setTipoRedencion(oRset.getString(12));
                    telefono.setTipoProm(oRset.getString(13));
                    telefono.setFechaAltaTime(oRset.getTimestamp(14));
                    telefono.setFzaVta(oRset.getString("FZAVTA"));
                    telefono.setPuntosTO(puntosTO);
                    lineas.add(telefono);
                }
            }
            catch (Exception e) {
                throw new Exception("Consulta Linea [" + e.toString() + "]");
            }
        }
        finally {
            if (oRset != null) {
                try {
                    oRset.close();
                    oRset = null;
                }
                catch (Exception var10_14) {}
            }
            if (oStmt != null) {
                try {
                    oStmt.close();
                    oStmt = null;
                }
                catch (Exception var10_15) {}
            }
            if (oConn != null) {
                try {
                    oConn.close();
                    oConn = null;
                }
                catch (Exception var10_16) {}
            }
        }
        return lineas;
    }

    public boolean cancelaReserva(TelefonoTO telefonoTO) throws Exception {
        boolean accion;
        Connection oConn = null;
        PreparedStatement oStmt = null;
        accion = false;
        StringBuffer cancelReservLinea = new StringBuffer();
        cancelReservLinea.append("UPDATE ").append(this.schema_database).append("PTO_TBLLINEAS SET STATUSPUNTOS=? ");
        cancelReservLinea.append("WHERE LINEA=? AND IDREGION=? AND CUENTA=? ");
        StringBuffer cancelReservacion = new StringBuffer();
        cancelReservacion.append("UPDATE ").append(this.schema_database).append("PTO_TBLRESERVACIONES SET STATUS=?,");
        cancelReservacion.append("USUARIOMOD=?,FECHAULTMOD=? WHERE FOLIO=? ");
        try {
            try {
                oConn = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                oConn.setAutoCommit(false);
                oStmt = oConn.prepareStatement(cancelReservLinea.toString());
                oStmt.setInt(1, 0);
                oStmt.setString(2, telefonoTO.getTelefono());
                oStmt.setInt(3, telefonoTO.getRegion());
                oStmt.setString(4, telefonoTO.getCuenta());
                oStmt.executeUpdate();
                oStmt = oConn.prepareStatement(cancelReservacion.toString());
                oStmt.setString(1, "C");
                oStmt.setString(2, telefonoTO.getIdUsuario());
                oStmt.setTimestamp(3, telefonoTO.getFechaAltaTime());
                oStmt.setString(4, telefonoTO.getFolio());
                oStmt.executeUpdate();
                oConn.commit();
                accion = true;
            }
            catch (Exception e) {
                oConn.rollback();
                throw new Exception("Linea [" + e.toString() + "]");
            }
        }
        finally {
            if (oStmt != null) {
                try {
                    oStmt.close();
                    oStmt = null;
                }
                catch (Exception var9_11) {}
            }
            if (oConn != null) {
                try {
                    oConn.close();
                    oConn = null;
                }
                catch (Exception var9_12) {}
            }
        }
        return accion;
    }

    public ArrayList<PuntoVentaTO> consultaPtosVta(PuntoVentaTO puntoVentaTO) throws Exception {
        ArrayList<PuntoVentaTO> puntosVenta;
        Connection oConn = null;
        PreparedStatement oStmt = null;
        ResultSet oRset = null;
        puntosVenta = new ArrayList<PuntoVentaTO>();
        try {
            try {
                oConn = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                String where = "";
                if (!((puntoVentaTO.getSegmentoIP() == null || puntoVentaTO.getSegmentoIP().equalsIgnoreCase("")) && (puntoVentaTO.getIdPuntoVta() == null || puntoVentaTO.getIdPuntoVta().equalsIgnoreCase("")) && puntoVentaTO.getIdRegion() <= 0)) {
                    where = " where ";
                }
                boolean ip = false;
                boolean vta = false;
                if (!(puntoVentaTO.getIdPuntoVta() == null || puntoVentaTO.getIdPuntoVta().equalsIgnoreCase(""))) {
                    where = String.valueOf(where) + " IDPUNTOVTA like '%" + puntoVentaTO.getIdPuntoVta() + "%' ";
                    ip = true;
                }
                if (!(puntoVentaTO.getSegmentoIP() == null || puntoVentaTO.getSegmentoIP().equalsIgnoreCase(""))) {
                    where = ip ? String.valueOf(where) + " and SEGMENTOIP=? " : String.valueOf(where) + " SEGMENTOIP=? ";
                    vta = true;
                }
                if (puntoVentaTO.getIdRegion() > 0) {
                    where = ip || vta ? String.valueOf(where) + " and IDREGION=? " : String.valueOf(where) + " IDREGION=? ";
                }
                StringBuffer query = new StringBuffer();
                query.append("SELECT IDPUNTOVTA,SEGMENTOIP,RANGOINF,RANGOSUP,IDREGION,");
                query.append("IVA_PORCENTAJE FROM ").append(this.schema_database).append("PTO_CTLPUNTOSVTA ").append(where);
                oStmt = oConn.prepareStatement(query.toString());
                int i = 0;
                if (!(puntoVentaTO.getSegmentoIP() == null || puntoVentaTO.getSegmentoIP().equalsIgnoreCase(""))) {
                    oStmt.setString(++i, puntoVentaTO.getSegmentoIP());
                }
                if (puntoVentaTO.getIdRegion() > 0) {
                    oStmt.setInt(++i, puntoVentaTO.getIdRegion());
                }
                oRset = oStmt.executeQuery();
                while (oRset.next()) {
                    PuntoVentaTO puntos = new PuntoVentaTO();
                    puntos.setIdPuntoVta(oRset.getString(1));
                    puntos.setSegmentoIP(oRset.getString(2));
                    puntos.setRangoInf(oRset.getInt(3));
                    puntos.setRangoSup(oRset.getInt(4));
                    puntos.setIdRegion(oRset.getInt(5));
                    puntos.setIvaProcentaje(oRset.getInt(6));
                    puntosVenta.add(puntos);
                }
            }
            catch (Exception e) {
                throw new Exception("Consulta Linea [" + e.toString() + "]");
            }
        }
        finally {
            if (oRset != null) {
                try {
                    oRset.close();
                    oRset = null;
                }
                catch (Exception var13_17) {}
            }
            if (oStmt != null) {
                try {
                    oStmt.close();
                    oStmt = null;
                }
                catch (Exception var13_18) {}
            }
            if (oConn != null) {
                try {
                    oConn.close();
                    oConn = null;
                }
                catch (Exception var13_19) {}
            }
        }
        return puntosVenta;
    }

    public PuntoVentaTO consultaPtoVta(Connection oConn, PuntoVentaTO puntoVentaTO) throws Exception {
        PuntoVentaTO puntoVenta;
        PreparedStatement oStmt = null;
        ResultSet oRset = null;
        puntoVenta = new PuntoVentaTO();
        try {
            try {
                StringBuffer query = new StringBuffer();
                query.append("SELECT IDPUNTOVTA,SEGMENTOIP,RANGOINF,RANGOSUP,IDREGION,");
                query.append("IVA_PORCENTAJE FROM ").append(this.schema_database).append("PTO_CTLPUNTOSVTA where IDPUNTOVTA=? ");
                oStmt = oConn.prepareStatement(query.toString());
                oStmt.setString(1, puntoVentaTO.getIdPuntoVta());
                oRset = oStmt.executeQuery();
                while (oRset.next()) {
                    puntoVenta.setIdPuntoVta(oRset.getString(1));
                }
            }
            catch (Exception e) {
                throw new Exception("Consulta Linea [" + e.toString() + "]");
            }
        }
        finally {
            if (oRset != null) {
                try {
                    oRset.close();
                    oRset = null;
                }
                catch (Exception var8_11) {}
            }
            if (oStmt != null) {
                try {
                    oStmt.close();
                    oStmt = null;
                }
                catch (Exception var8_12) {}
            }
        }
        return puntoVenta;
    }

    public boolean agregaPtoVta(PuntoVentaTO puntoVentaTO) throws Exception {
        boolean accion;
        Connection oConn = null;
        PreparedStatement oStmt = null;
        accion = false;
        try {
            try {
                oConn = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                PuntoVentaTO puntoVentaExiste = this.consultaPtoVta(oConn, puntoVentaTO);
                if (puntoVentaExiste.getIdPuntoVta() == null) {
                    StringBuffer query = new StringBuffer();
                    query.append("insert into ").append(this.schema_database).append("PTO_CTLPUNTOSVTA (IDPUNTOVTA,SEGMENTOIP,RANGOINF,");
                    query.append("RANGOSUP,IDREGION,IVA_PORCENTAJE) values (?,?,?,?,?,?) ");
                    oStmt = oConn.prepareStatement(query.toString());
                    oStmt.setString(1, puntoVentaTO.getIdPuntoVta());
                    oStmt.setString(2, puntoVentaTO.getSegmentoIP());
                    oStmt.setInt(3, puntoVentaTO.getRangoInf());
                    oStmt.setInt(4, puntoVentaTO.getRangoSup());
                    oStmt.setInt(5, puntoVentaTO.getIdRegion());
                    oStmt.setInt(6, puntoVentaTO.getIvaProcentaje());
                    oStmt.executeUpdate();
                    accion = true;
                }
            }
            catch (Exception e) {
                throw new Exception("Linea [" + e.toString() + "]");
            }
        }
        finally {
            if (oStmt != null) {
                try {
                    oStmt.close();
                    oStmt = null;
                }
                catch (Exception var8_11) {}
            }
            if (oConn != null) {
                try {
                    oConn.close();
                    oConn = null;
                }
                catch (Exception var8_12) {}
            }
        }
        return accion;
    }

    public boolean actualizaPtoVta(PuntoVentaTO puntoVentaTO) throws Exception {
        boolean accion;
        Connection oConn = null;
        PreparedStatement oStmt = null;
        accion = false;
        try {
            try {
                oConn = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                StringBuffer query = new StringBuffer();
                query.append("update ").append(this.schema_database).append("PTO_CTLPUNTOSVTA set SEGMENTOIP=?,RANGOINF=?,");
                query.append("RANGOSUP=?,IDREGION=?,IVA_PORCENTAJE=? where IDPUNTOVTA=? ");
                oStmt = oConn.prepareStatement(query.toString());
                oStmt.setString(1, puntoVentaTO.getSegmentoIP());
                oStmt.setInt(2, puntoVentaTO.getRangoInf());
                oStmt.setInt(3, puntoVentaTO.getRangoSup());
                oStmt.setInt(4, puntoVentaTO.getIdRegion());
                oStmt.setInt(5, puntoVentaTO.getIvaProcentaje());
                oStmt.setString(6, puntoVentaTO.getIdPuntoVta());
                oStmt.executeUpdate();
                accion = true;
            }
            catch (Exception e) {
                throw new Exception("Linea [" + e.toString() + "]");
            }
        }
        finally {
            if (oStmt != null) {
                try {
                    oStmt.close();
                    oStmt = null;
                }
                catch (Exception var7_10) {}
            }
            if (oConn != null) {
                try {
                    oConn.close();
                    oConn = null;
                }
                catch (Exception var7_11) {}
            }
        }
        return accion;
    }

    public boolean eliminaPtoVta(PuntoVentaTO puntoVentaTO) throws Exception {
        boolean accion;
        Connection oConn = null;
        PreparedStatement oStmt = null;
        accion = false;
        try {
            try {
                oConn = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                StringBuffer query = new StringBuffer();
                query.append("delete from ").append(this.schema_database).append("PTO_CTLPUNTOSVTA where IDPUNTOVTA=? ");
                oStmt = oConn.prepareStatement(query.toString());
                oStmt.setString(1, puntoVentaTO.getIdPuntoVta());
                oStmt.executeUpdate();
                accion = true;
            }
            catch (Exception e) {
                throw new Exception("Linea [" + e.toString() + "]");
            }
        }
        finally {
            if (oStmt != null) {
                try {
                    oStmt.close();
                    oStmt = null;
                }
                catch (Exception var7_10) {}
            }
            if (oConn != null) {
                try {
                    oConn.close();
                    oConn = null;
                }
                catch (Exception var7_11) {}
            }
        }
        return accion;
    }
}

