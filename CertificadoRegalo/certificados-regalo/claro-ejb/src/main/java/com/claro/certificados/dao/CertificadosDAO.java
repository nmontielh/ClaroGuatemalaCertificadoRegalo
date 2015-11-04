/*
 * Decompiled with CFR 0_102.
 * 
 * Could not load the following classes:
 *  com.claro.exception.ClaroException
 *  com.claro.transfer.certificados.ActivacionTarjetaCertificadoTO
 *  com.claro.transfer.certificados.CancelaCertificadoTO
 *  com.claro.transfer.certificados.CancelaMovimientoTO
 *  com.claro.transfer.certificados.MensajeServiceTO
 *  com.claro.transfer.certificados.MovimientoCertificadoTO
 *  com.claro.transfer.certificados.TarjetaCertificadoTO
 *  com.claro.util.ServiceLocator
 *  com.claro.util.Utils
 *  org.apache.log4j.Logger
 */
package com.claro.certificados.dao;

import com.claro.exception.ClaroException;
import com.claro.transfer.certificados.ActivacionTarjetaCertificadoTO;
import com.claro.transfer.certificados.CancelaCertificadoTO;
import com.claro.transfer.certificados.CancelaMovimientoTO;
import com.claro.transfer.certificados.MensajeServiceTO;
import com.claro.transfer.certificados.MovimientoCertificadoTO;
import com.claro.transfer.certificados.TarjetaCertificadoTO;
import com.claro.util.ServiceLocator;
import com.claro.util.Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.log4j.Logger;

public class CertificadosDAO {
    protected final Logger logger = Logger.getLogger((String)"loggerCirculoAzul");
    protected final Logger error = Logger.getLogger((String)"loggerCirculoAzulError");
    private String schema_database;

    public CertificadosDAO() {
        try {
            this.schema_database = ServiceLocator.getInstance().getVariable(ServiceLocator.schema_database);
        }
        catch (Exception e) {
            this.error.error((Object)"CertificadosDAO", (Throwable)e);
        }
    }

    public List<MovimientoCertificadoTO> consultaMovimientosTajetaCertificado(String numeroCertificado) throws ClaroException {
        ArrayList<MovimientoCertificadoTO> movimientos;
        block13 : {
            movimientos = new ArrayList<MovimientoCertificadoTO>();
            Connection conn = null;
            PreparedStatement stmt = null;
            ResultSet resultSet = null;
            try {
                try {
                    StringBuilder query = new StringBuilder();
                    query.append("SELECT NUMCERTIFICADO,NUMTARJETA,IDMOVTO,IDUSUARIO,PUNTOVTA, ");
                    query.append("FECHAOPER,ESTATUS,VALORAPLICADO,VALORANTERIOR,VALORRESTANTE,REFERENCIA ");
                    query.append("FROM  ").append(this.schema_database).append("CC_TBLMOVIMIENTOCERTIFICADO ");
                    query.append("WHERE NUMCERTIFICADO = ? ORDER BY FECHAOPER DESC ");
                    conn = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                    stmt = conn.prepareStatement(query.toString());
                    stmt.setString(1, numeroCertificado.trim());
                    resultSet = stmt.executeQuery();
                    while (resultSet.next()) {
                        MovimientoCertificadoTO movimientoCertificadoTO = new MovimientoCertificadoTO();
                        movimientoCertificadoTO.setNumeroCertificado(resultSet.getString("NUMCERTIFICADO").trim());
                        movimientoCertificadoTO.setNumeroTarjeta(resultSet.getString("NUMTARJETA").trim());
                        movimientoCertificadoTO.setIdMotivo(resultSet.getString("IDMOVTO").trim());
                        movimientoCertificadoTO.setIdUsuario(resultSet.getString("IDUSUARIO").trim());
                        movimientoCertificadoTO.setPuntoVenta(resultSet.getString("PUNTOVTA").trim());
                        movimientoCertificadoTO.setFechaOperacion(String.valueOf(resultSet.getTimestamp("FECHAOPER")));
                        movimientoCertificadoTO.setEstatus(resultSet.getString("ESTATUS").trim());
                        movimientoCertificadoTO.setValorAplicado(resultSet.getFloat("VALORAPLICADO"));
                        movimientoCertificadoTO.setValorAnterior(resultSet.getFloat("VALORANTERIOR"));
                        movimientoCertificadoTO.setValorRestante(resultSet.getFloat("VALORRESTANTE"));
                        movimientoCertificadoTO.setReferencia(resultSet.getString("REFERENCIA").trim());
                        movimientos.add(movimientoCertificadoTO);
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                    if (conn == null) break block13;
                    try {
                        conn.close();
                    }
                    catch (SQLException var9_9) {}
                }
            }
            finally {
                if (conn != null) {
                    try {
                        conn.close();
                    }
                    catch (SQLException var9_11) {}
                }
            }
        }
        return movimientos;
    }

    public TarjetaCertificadoTO consultaSaldoTarjetaCertificado(String numeroCertificado) throws ClaroException {
        TarjetaCertificadoTO tarjetaCertificadoTO;
        block29 : {
            Connection conn = null;
            PreparedStatement stmt = null;
            ResultSet resultSet = null;
            tarjetaCertificadoTO = new TarjetaCertificadoTO();
            try {
                try {
                    StringBuilder query = new StringBuilder();
                    query.append("SELECT NUMTARJETA, NUMCERTIFICADO, VALORRESTANTE, FECHAACTIVACION, FECHAEXPIRACION, ESTATUS ");
                    query.append("FROM  ").append(this.schema_database).append("CC_TBLTARJETACERTIFICADO ");
                    query.append("WHERE NUMCERTIFICADO = ? ");
                    conn = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                    stmt = conn.prepareStatement(query.toString());
                    stmt.setString(1, numeroCertificado.trim());
                    resultSet = stmt.executeQuery();
                    if (resultSet.next()) {
                        tarjetaCertificadoTO.setNumeroTarjeta(resultSet.getString("NUMTARJETA").trim());
                        tarjetaCertificadoTO.setNumeroCertificado(resultSet.getString("NUMCERTIFICADO").trim());
                        tarjetaCertificadoTO.setSaldo(resultSet.getFloat("VALORRESTANTE"));
                        tarjetaCertificadoTO.setFechaActivacion(String.valueOf(resultSet.getTimestamp("FECHAACTIVACION")));
                        tarjetaCertificadoTO.setFechaExpiracion(String.valueOf(resultSet.getTimestamp("FECHAEXPIRACION")));
                        tarjetaCertificadoTO.setEstatus(resultSet.getString("ESTATUS").trim());
                        tarjetaCertificadoTO.setIdMensaje("00");
                        tarjetaCertificadoTO.setMensaje("Proceso exitoso");
                        break block29;
                    }
                    tarjetaCertificadoTO.setIdMensaje("02");
                    tarjetaCertificadoTO.setMensaje("Certificado no v\u00e1lido.");
                }
                catch (Exception e) {
                    tarjetaCertificadoTO.setIdMensaje("07");
                    tarjetaCertificadoTO.setMensaje("Error: " + e.toString());
                    if (resultSet != null) {
                        try {
                            resultSet.close();
                        }
                        catch (SQLException var8_8) {
                            // empty catch block
                        }
                    }
                    if (stmt != null) {
                        try {
                            stmt.close();
                        }
                        catch (SQLException var8_9) {
                            // empty catch block
                        }
                    }
                    if (conn == null) break block29;
                    try {
                        conn.close();
                    }
                    catch (SQLException var8_10) {}
                }
            }
            finally {
                if (resultSet != null) {
                    try {
                        resultSet.close();
                    }
                    catch (SQLException var8_14) {}
                }
                if (stmt != null) {
                    try {
                        stmt.close();
                    }
                    catch (SQLException var8_15) {}
                }
                if (conn != null) {
                    try {
                        conn.close();
                    }
                    catch (SQLException var8_16) {}
                }
            }
        }
        return tarjetaCertificadoTO;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public ActivacionTarjetaCertificadoTO activaTarjetaCertificado(String numeroTarjeta, long montoCertificado, String idUsuario) throws ClaroException {
    	PreparedStatement stmt;
        Connection conn;
        ActivacionTarjetaCertificadoTO activacionTarjetaCertificadoTO;
        String region;
        ResultSet resultSet;
        StringBuilder query;
        block49 : {
            ActivacionTarjetaCertificadoTO activacionTarjetaCertificadoTO2;
            block50 : {
                activacionTarjetaCertificadoTO = new ActivacionTarjetaCertificadoTO();
                conn = null;
                stmt = null;
                resultSet = null;
                region = null;
                query = new StringBuilder();
                query.append("SELECT REGION FROM ");
                query.append(this.schema_database).append("CC_CTLUSUARIOS ");
                query.append("WHERE USUARIO = ? ");
                try {
					conn = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                try {
					conn.setAutoCommit(false);
					stmt = conn.prepareStatement(query.toString());
	                stmt.setString(1, numeroTarjeta);
	                resultSet = stmt.executeQuery();
	                if (resultSet.next()) {
	                    region = resultSet.getString("REGION");
	                }	            

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                if (region != null) break block49;
                activacionTarjetaCertificadoTO.setIdMensaje("03");
                activacionTarjetaCertificadoTO.setMensaje("No se pueden activar certificados ya que usuario generico no existe");
                activacionTarjetaCertificadoTO2 = activacionTarjetaCertificadoTO;
                if (resultSet == null) break block50;
                try {
                    resultSet.close();
                }
                catch (SQLException var20_13) {
                    // empty catch block
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                }
                catch (SQLException var20_14) {
                    // empty catch block
                }
            }
            if (conn == null) return activacionTarjetaCertificadoTO2;
            try {
                conn.setAutoCommit(true);
                conn.close();
                return activacionTarjetaCertificadoTO2;
            }
            catch (SQLException var20_15) {
                // empty catch block
            }
            return activacionTarjetaCertificadoTO2;
        }
        try {
            try {
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (stmt != null) {
                    stmt.close();
                    stmt = null;
                }
                query = new StringBuilder();
                query.append("SELECT NUMCERTIFICADO,APLICADO FROM ");
                query.append(this.schema_database).append("CC_TBLCERTIFICADO ");
                query.append("WHERE NUMTARJETA = ? AND VALOR = ? AND REGION= ?");
                stmt = conn.prepareStatement(query.toString());
                stmt.setString(1, numeroTarjeta);
                stmt.setFloat(2, montoCertificado);
                stmt.setString(3, region);
                String Certificado = null;
                String Estatus = null;
                resultSet = stmt.executeQuery();
                if (resultSet.next()) {
                    Certificado = resultSet.getString("NUMCERTIFICADO");
                    Estatus = resultSet.getString("APLICADO");
                }
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (stmt != null) {
                    stmt.close();
                    stmt = null;
                }
                if (Certificado != null) {
                    if (Estatus.trim().equals("N")) {
                        query = new StringBuilder();
                        query.append("INSERT INTO ");
                        query.append(this.schema_database).append("CC_TBLTARJETACERTIFICADO(NUMCERTIFICADO,NUMTARJETA, ");
                        query.append("VALORRESTANTE, FECHAACTIVACION, FECHAEXPIRACION, ESTATUS, IDUSUARIO) ");
                        query.append("VALUES(?,?,?,?,?,?,?) ");
                        stmt = conn.prepareStatement(query.toString());
                        stmt.setString(1, Certificado.trim());
                        stmt.setString(2, numeroTarjeta.trim());
                        stmt.setLong(3, montoCertificado);
                        GregorianCalendar fechaActivacion = new GregorianCalendar();
                        GregorianCalendar fechaExpiracion = new GregorianCalendar();
                        fechaExpiracion.add(2, 6);
                        stmt.setTimestamp(4, new Timestamp(fechaActivacion.getTimeInMillis()));
                        stmt.setTimestamp(5, new Timestamp(fechaExpiracion.getTimeInMillis()));
                        stmt.setString(6, "1");
                        stmt.setString(7, idUsuario.trim());
                        stmt.executeUpdate();
                        if (resultSet != null) {
                            resultSet.close();
                            resultSet = null;
                        }
                        if (stmt != null) {
                            stmt.close();
                            stmt = null;
                        }
                        activacionTarjetaCertificadoTO.setNumeroTarjeta(numeroTarjeta.trim());
                        activacionTarjetaCertificadoTO.setNumeroCertificado(Certificado.trim());
                        activacionTarjetaCertificadoTO.setMontoCertificado(montoCertificado);
                        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
                        String fechaAct = sdf.format(fechaActivacion.getTime());
                        String fechaExp = sdf.format(fechaExpiracion.getTime());
                        activacionTarjetaCertificadoTO.setFechaActivacion(fechaAct);
                        activacionTarjetaCertificadoTO.setFechaExpiracion(fechaExp);
                        activacionTarjetaCertificadoTO.setEstatus("1");
                        query = new StringBuilder();
                        query.append("UPDATE ").append(this.schema_database).append("CC_TBLCERTIFICADO ");
                        query.append(" SET APLICADO = ?, IDUSUARIO = ? ");
                        query.append(" WHERE NUMCERTIFICADO = ? AND NUMTARJETA = ?");
                        stmt = conn.prepareStatement(query.toString());
                        stmt.setString(1, "S");
                        stmt.setString(2, idUsuario.trim());
                        stmt.setString(3, Certificado.trim());
                        stmt.setString(4, numeroTarjeta.trim());
                        stmt.executeUpdate();
                        conn.commit();
                        activacionTarjetaCertificadoTO.setIdMensaje("00");
                        activacionTarjetaCertificadoTO.setMensaje("Se activo el certificado correctamente");
                        return activacionTarjetaCertificadoTO;
                    }
                    activacionTarjetaCertificadoTO.setIdMensaje("03");
                    activacionTarjetaCertificadoTO.setMensaje("La tarjeta: " + numeroTarjeta + " ya fue vendida");
                    return activacionTarjetaCertificadoTO;
                }
                activacionTarjetaCertificadoTO.setIdMensaje("03");
                activacionTarjetaCertificadoTO.setMensaje("No existe la tarjeta: " + numeroTarjeta + " a Activar o el monto: " + montoCertificado + " es invaldo.");
                return activacionTarjetaCertificadoTO;
            }
            catch (Exception e) {
                if (conn != null) {
                    try {
                        conn.rollback();
                    }
                    catch (SQLException e1) {
                        // empty catch block
                    }
                }
                activacionTarjetaCertificadoTO.setIdMensaje("07");
                activacionTarjetaCertificadoTO.setMensaje("Error: " + e.toString());
                if (resultSet != null) {
                    try {
                        resultSet.close();
                    }
                    catch (SQLException var20_16) {
                        // empty catch block
                    }
                }
                if (stmt != null) {
                    try {
                        stmt.close();
                    }
                    catch (SQLException var20_17) {
                        // empty catch block
                    }
                }
                if (conn == null) return activacionTarjetaCertificadoTO;
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                    return activacionTarjetaCertificadoTO;
                }
                catch (SQLException var20_18) {}
            }
            return activacionTarjetaCertificadoTO;
        }
        catch (Throwable var18_32) {}
        finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                }
                catch (SQLException var20_22) {}
            }
            if (stmt != null) {
                try {
                    stmt.close();
                }
                catch (SQLException var20_23) {}
            }
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                }
                catch (SQLException var20_24) {}
            }
        }/*
        {
            throw var18_32;
        }*/
		return activacionTarjetaCertificadoTO;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public MensajeServiceTO aplicaCertificado(MovimientoCertificadoTO movimientoCertificadoTO) throws ClaroException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet resultSet = null;
        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
        MensajeServiceTO mensajeServiceTO = new MensajeServiceTO();
        try {
            try {
                conn = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                conn.setAutoCommit(false);
                StringBuilder query = new StringBuilder();
                query.append("SELECT NUMCERTIFICADO, NUMTARJETA, VALORRESTANTE, FECHAACTIVACION, FECHAEXPIRACION, ESTATUS ");
                query.append("FROM ").append(this.schema_database).append("CC_TBLTARJETACERTIFICADO ");
                query.append("WHERE NUMCERTIFICADO = ? ");
                stmt = conn.prepareStatement(query.toString());
                stmt.setString(1, movimientoCertificadoTO.getNumeroCertificado().trim());
                resultSet = stmt.executeQuery();
                TarjetaCertificadoTO tarjetaCertificadoTO = null;
                if (resultSet.next()) {
                    tarjetaCertificadoTO = new TarjetaCertificadoTO();
                    tarjetaCertificadoTO.setNumeroCertificado(resultSet.getString("NUMCERTIFICADO").trim());
                    tarjetaCertificadoTO.setNumeroTarjeta(resultSet.getString("NUMTARJETA").trim());
                    tarjetaCertificadoTO.setSaldo(resultSet.getFloat("VALORRESTANTE"));
                    tarjetaCertificadoTO.setFechaActivacion(sdf.format(resultSet.getTimestamp("FECHAACTIVACION")));
                    tarjetaCertificadoTO.setFechaExpiracion(sdf.format(resultSet.getTimestamp("FECHAEXPIRACION")));
                    tarjetaCertificadoTO.setEstatus(resultSet.getString("ESTATUS").trim());
                }
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (stmt != null) {
                    stmt.close();
                    stmt = null;
                }
                if (tarjetaCertificadoTO != null) {
                    if (tarjetaCertificadoTO.getEstatus().equals("1".trim())) {
                        Date fechaExpiracion = sdf.parse(tarjetaCertificadoTO.getFechaExpiracion());
                        Date hoy = new Date();
                        if ((hoy = sdf.parse(sdf.format(hoy))).compareTo(fechaExpiracion) <= 0) {
                            if (tarjetaCertificadoTO.getSaldo() >= movimientoCertificadoTO.getValorAplicado()) {
                                query = new StringBuilder();
                                query.append("INSERT INTO ");
                                query.append(this.schema_database).append("CC_TBLMOVIMIENTOCERTIFICADO (FOLIO,NUMCERTIFICADO, ");
                                query.append("NUMTARJETA,IDMOVTO,IDUSUARIO,PUNTOVTA,FECHAOPER,ESTATUS,VALORAPLICADO, ");
                                query.append("VALORANTERIOR,VALORRESTANTE,REFERENCIA) ");
                                query.append("VALUES(?,?,?,?,?,?,?,?,?,?,?,?) ");
                                mensajeServiceTO.setFolio(this.getFolioMovimientoCertificado(tarjetaCertificadoTO.getNumeroCertificado().trim()));
                                stmt = conn.prepareStatement(query.toString());
                                stmt.setString(1, mensajeServiceTO.getFolio().trim());
                                stmt.setString(2, tarjetaCertificadoTO.getNumeroCertificado().trim());
                                stmt.setString(3, tarjetaCertificadoTO.getNumeroTarjeta().trim());
                                stmt.setString(4, "1");
                                stmt.setString(5, movimientoCertificadoTO.getIdUsuario().trim());
                                stmt.setString(6, movimientoCertificadoTO.getPuntoVenta().trim());
                                stmt.setTimestamp(7, new Timestamp(System.currentTimeMillis()));
                                stmt.setString(8, "A");
                                stmt.setFloat(9, movimientoCertificadoTO.getValorAplicado());
                                stmt.setFloat(10, tarjetaCertificadoTO.getSaldo());
                                float valorRestante = tarjetaCertificadoTO.getSaldo() - movimientoCertificadoTO.getValorAplicado();
                                stmt.setFloat(11, valorRestante);
                                stmt.setString(12, movimientoCertificadoTO.getReferencia().trim());
                                stmt.executeUpdate();
                                if (resultSet != null) {
                                    resultSet.close();
                                    resultSet = null;
                                }
                                if (stmt != null) {
                                    stmt.close();
                                    stmt = null;
                                }
                                query = new StringBuilder();
                                query.append("UPDATE ").append(this.schema_database).append("CC_TBLTARJETACERTIFICADO ");
                                query.append("SET VALORRESTANTE = ? ");
                                query.append("WHERE NUMTARJETA = ? AND NUMCERTIFICADO = ? ");
                                stmt = conn.prepareStatement(query.toString());
                                stmt.setFloat(1, valorRestante);
                                stmt.setString(2, tarjetaCertificadoTO.getNumeroTarjeta().trim());
                                stmt.setString(3, tarjetaCertificadoTO.getNumeroCertificado().trim());
                                stmt.executeUpdate();
                                conn.commit();
                                mensajeServiceTO.setId("00");
                                mensajeServiceTO.setMensaje("Se realizo satisfactoriamente la operaci\u00f3n. Saldo Restante: " + valorRestante);
                                return mensajeServiceTO;
                            } else {
                                mensajeServiceTO.setId("05");
                                mensajeServiceTO.setMensaje("No cuenta con saldo suficiente, saldo: " + tarjetaCertificadoTO.getSaldo());
                            }
                            return mensajeServiceTO;
                        } else {
                            if (resultSet != null) {
                                resultSet.close();
                                resultSet = null;
                            }
                            if (stmt != null) {
                                stmt.close();
                                stmt = null;
                            }
                            query = new StringBuilder();
                            query.append("UPDATE ").append(this.schema_database).append("CC_TBLTARJETACERTIFICADO ");
                            query.append("SET ESTATUS = ? ");
                            query.append("WHERE NUMTARJETA = ? AND NUMCERTIFICADO = ? ");
                            stmt = conn.prepareStatement(query.toString());
                            stmt.setString(1, "2".trim());
                            stmt.setString(2, tarjetaCertificadoTO.getNumeroTarjeta().trim());
                            stmt.setString(3, tarjetaCertificadoTO.getNumeroCertificado().trim());
                            stmt.executeUpdate();
                            conn.commit();
                            mensajeServiceTO.setId("06");
                            mensajeServiceTO.setMensaje("El certificado ha expirado, fecha de expiracion: " + sdf.format(fechaExpiracion));
                        }
                        return mensajeServiceTO;
                    } else {
                        if (tarjetaCertificadoTO.getEstatus().equals("2")) {
                            mensajeServiceTO.setId("2");
                            mensajeServiceTO.setMensaje("Error, ha caducado la vigencia del certificado.");
                        }
                        if (!tarjetaCertificadoTO.getEstatus().equals("3")) return mensajeServiceTO;
                        mensajeServiceTO.setId("3");
                        mensajeServiceTO.setMensaje("Error, el certificado esta cancelado.");
                    }
                    return mensajeServiceTO;
                }
                mensajeServiceTO.setId("01");
                mensajeServiceTO.setMensaje("No existe activacion para el certificado: " + movimientoCertificadoTO.getNumeroCertificado().trim());
                return mensajeServiceTO;
            }
            catch (Exception e) {
                if (conn != null) {
                    try {
                        conn.rollback();
                    }
                    catch (SQLException tarjetaCertificadoTO) {
                        // empty catch block
                    }
                }
                mensajeServiceTO.setId("07");
                mensajeServiceTO.setMensaje("Error: " + e.toString());
                if (resultSet != null) {
                    try {
                        resultSet.close();
                    }
                    catch (SQLException var13_14) {
                        // empty catch block
                    }
                }
                if (stmt != null) {
                    try {
                        stmt.close();
                    }
                    catch (SQLException var13_15) {
                        // empty catch block
                    }
                }
                if (conn == null) return mensajeServiceTO;
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                    return mensajeServiceTO;
                }
                catch (SQLException var13_16) {}
            }
            return mensajeServiceTO;
        }
        finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                }
                catch (SQLException var13_20) {}
            }
            if (stmt != null) {
                try {
                    stmt.close();
                }
                catch (SQLException var13_21) {}
            }
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                }
                catch (SQLException var13_22) {}
            }
        }
    }

    private String getFolioMovimientoCertificado(String numeroCertificado) {
        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyhhmmss");
        Date date = new Date();
        String folio = String.valueOf(numeroCertificado.substring(numeroCertificado.length() - 4, numeroCertificado.length())) + sdf.format(date);
        return folio.trim();
    }

    public MensajeServiceTO cancelaTarjetaCertificado(String numeroCertificado, String idUsuario) throws ClaroException {
        MensajeServiceTO mensajeServiceTO;
        block49 : {
            mensajeServiceTO = new MensajeServiceTO();
            Connection conn = null;
            PreparedStatement stmt = null;
            ResultSet resultSet = null;
            try {
                try {
                    conn = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                    conn.setAutoCommit(false);
                    StringBuilder query = new StringBuilder();
                    query.append("SELECT NUMCERTIFICADO, NUMTARJETA, VALORRESTANTE,ESTATUS ");
                    query.append(" FROM ").append(this.schema_database).append("CC_TBLTARJETACERTIFICADO ");
                    query.append(" WHERE NUMCERTIFICADO = ? ");
                    stmt = conn.prepareStatement(query.toString());
                    stmt.setString(1, numeroCertificado.trim());
                    resultSet = stmt.executeQuery();
                    CancelaCertificadoTO cancelaCertificadoTO = null;
                    if (resultSet.next()) {
                        cancelaCertificadoTO = new CancelaCertificadoTO();
                        cancelaCertificadoTO.setNumeroCertificado(resultSet.getString("NUMCERTIFICADO").trim());
                        cancelaCertificadoTO.setNumeroTarjeta(resultSet.getString("NUMTARJETA").trim());
                        cancelaCertificadoTO.setSaldo(resultSet.getFloat("VALORRESTANTE"));
                        cancelaCertificadoTO.setEstatusTarjeta(resultSet.getString("ESTATUS").trim());
                    } else {
                        cancelaCertificadoTO = null;
                    }
                    if (resultSet != null) {
                        resultSet.close();
                        resultSet = null;
                    }
                    if (stmt != null) {
                        stmt.close();
                        stmt = null;
                    }
                    if (cancelaCertificadoTO != null) {
                        query = new StringBuilder();
                        query.append("SELECT VALOR,APLICADO  ");
                        query.append(" FROM ").append(this.schema_database).append("CC_TBLCERTIFICADO ");
                        query.append(" WHERE NUMCERTIFICADO = ? ");
                        stmt = conn.prepareStatement(query.toString());
                        stmt.setString(1, cancelaCertificadoTO.getNumeroCertificado());
                        resultSet = stmt.executeQuery();
                        if (resultSet.next()) {
                            cancelaCertificadoTO.setSaldoinicial(resultSet.getFloat("VALOR"));
                            cancelaCertificadoTO.setEstatusCertificado(resultSet.getString("APLICADO").trim());
                        } else {
                            cancelaCertificadoTO = null;
                        }
                        if (resultSet != null) {
                            resultSet.close();
                            resultSet = null;
                        }
                        if (stmt != null) {
                            stmt.close();
                            stmt = null;
                        }
                    }
                    if (cancelaCertificadoTO != null) {
                        if (cancelaCertificadoTO.getSaldo() >= cancelaCertificadoTO.getSaldoinicial()) {
                            if (!cancelaCertificadoTO.getEstatusTarjeta().trim().equals("2".trim())) {
                                if (cancelaCertificadoTO.getEstatusCertificado().trim().equals("S".trim())) {
                                    query = new StringBuilder();
                                    query.append("UPDATE ").append(this.schema_database).append("CC_TBLTARJETACERTIFICADO SET ESTATUS= ?, IDUSUARIO = ?");
                                    query.append("WHERE NUMTARJETA = ? AND NUMCERTIFICADO = ? ");
                                    stmt = conn.prepareStatement(query.toString());
                                    stmt.setString(1, "3");
                                    stmt.setString(2, idUsuario);
                                    stmt.setString(3, cancelaCertificadoTO.getNumeroTarjeta().trim());
                                    stmt.setString(4, cancelaCertificadoTO.getNumeroCertificado().trim());
                                    stmt.executeUpdate();
                                    if (resultSet != null) {
                                        resultSet.close();
                                        resultSet = null;
                                    }
                                    if (stmt != null) {
                                        stmt.close();
                                        stmt = null;
                                    }
                                    query = new StringBuilder();
                                    query.append("UPDATE ").append(this.schema_database).append("CC_TBLCERTIFICADO ");
                                    query.append(" SET APLICADO= ? WHERE NUMCERTIFICADO = ? ");
                                    stmt = conn.prepareStatement(query.toString());
                                    stmt.setString(1, "C".trim());
                                    stmt.setString(2, cancelaCertificadoTO.getNumeroCertificado().trim());
                                    stmt.executeUpdate();
                                    mensajeServiceTO.setId("00".trim());
                                    mensajeServiceTO.setMensaje("Se cancelo el certificado: " + cancelaCertificadoTO.getNumeroTarjeta().trim());
                                    conn.commit();
                                } else {
                                    mensajeServiceTO.setId("01");
                                    mensajeServiceTO.setMensaje("No se puede cancelar el certificado por que no ha sido vendido");
                                }
                            } else {
                                mensajeServiceTO.setId("06");
                                mensajeServiceTO.setMensaje("No se puede cancelar el certificado ya que ha caducado");
                            }
                        } else {
                            mensajeServiceTO.setId("01");
                            mensajeServiceTO.setMensaje("El certificado " + cancelaCertificadoTO.getNumeroTarjeta().trim() + " ha sido utilizado y no puede ser cancelado");
                        }
                        break block49;
                    }
                    mensajeServiceTO.setId("02");
                    mensajeServiceTO.setMensaje("No existe activacion para el certificado: " + numeroCertificado);
                }
                catch (Exception e) {
                    if (conn != null) {
                        try {
                            conn.rollback();
                        }
                        catch (SQLException cancelaCertificadoTO) {
                            // empty catch block
                        }
                    }
                    mensajeServiceTO.setId("07");
                    mensajeServiceTO.setMensaje("Error: " + e.toString());
                    if (resultSet != null) {
                        try {
                            resultSet.close();
                        }
                        catch (SQLException var10_11) {
                            // empty catch block
                        }
                    }
                    if (stmt != null) {
                        try {
                            stmt.close();
                        }
                        catch (SQLException var10_12) {
                            // empty catch block
                        }
                    }
                    if (conn == null) break block49;
                    try {
                        conn.setAutoCommit(true);
                        conn.close();
                    }
                    catch (SQLException var10_13) {}
                }
            }
            finally {
                if (resultSet != null) {
                    try {
                        resultSet.close();
                    }
                    catch (SQLException var10_17) {}
                }
                if (stmt != null) {
                    try {
                        stmt.close();
                    }
                    catch (SQLException var10_18) {}
                }
                if (conn != null) {
                    try {
                        conn.setAutoCommit(true);
                        conn.close();
                    }
                    catch (SQLException var10_19) {}
                }
            }
        }
        return mensajeServiceTO;
    }

    public MensajeServiceTO cancelaAplicaCertificado(String folio, String idUsuario, String idpuntoVta, String referencia) throws ClaroException {
        MensajeServiceTO mensajeServiceTO;
        block50 : {
            mensajeServiceTO = new MensajeServiceTO();
            SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
            Connection conn = null;
            PreparedStatement stmt = null;
            ResultSet resultSet = null;
            try {
                try {
                    conn = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                    conn.setAutoCommit(false);
                    StringBuilder query = new StringBuilder();
                    query.append("SELECT NUMCERTIFICADO, NUMTARJETA, VALORAPLICADO, FECHAOPER,ESTATUS ");
                    query.append("FROM ").append(this.schema_database).append("CC_TBLMOVIMIENTOCERTIFICADO ");
                    query.append("WHERE FOLIO = ? ");
                    stmt = conn.prepareStatement(query.toString());
                    stmt.setString(1, folio.trim());
                    resultSet = stmt.executeQuery();
                    CancelaMovimientoTO cancelaMovimientoTO = null;
                    if (resultSet.next()) {
                        cancelaMovimientoTO = new CancelaMovimientoTO();
                        cancelaMovimientoTO.setNumeroCertificado(resultSet.getString("NUMCERTIFICADO"));
                        cancelaMovimientoTO.setNumeroTarjeta(resultSet.getString("NUMTARJETA"));
                        cancelaMovimientoTO.setSaldo(resultSet.getFloat("VALORAPLICADO"));
                        cancelaMovimientoTO.setFechaOperacion(sdf.format(resultSet.getTimestamp("FECHAOPER")));
                        cancelaMovimientoTO.setEstatusMovto(resultSet.getString("ESTATUS"));
                    }
                    if (resultSet != null) {
                        resultSet.close();
                        resultSet = null;
                    }
                    if (stmt != null) {
                        stmt.close();
                        stmt = null;
                    }
                    if (cancelaMovimientoTO != null) {
                        query = new StringBuilder();
                        query.append("SELECT NUMCERTIFICADO, NUMTARJETA, VALORRESTANTE, FECHAACTIVACION, FECHAEXPIRACION, ESTATUS ");
                        query.append("FROM ").append(this.schema_database).append("CC_TBLTARJETACERTIFICADO ");
                        query.append("WHERE NUMTARJETA = ? ");
                        stmt = conn.prepareStatement(query.toString());
                        stmt.setString(1, cancelaMovimientoTO.getNumeroTarjeta());
                        resultSet = stmt.executeQuery();
                        TarjetaCertificadoTO tarjetaCertificadoTO = null;
                        if (resultSet.next()) {
                            tarjetaCertificadoTO = new TarjetaCertificadoTO();
                            tarjetaCertificadoTO.setNumeroCertificado(resultSet.getString("NUMCERTIFICADO"));
                            tarjetaCertificadoTO.setNumeroTarjeta(resultSet.getString("NUMTARJETA"));
                            tarjetaCertificadoTO.setSaldo(resultSet.getFloat("VALORRESTANTE"));
                            tarjetaCertificadoTO.setFechaActivacion(sdf.format(resultSet.getTimestamp("FECHAACTIVACION")));
                            tarjetaCertificadoTO.setFechaExpiracion(sdf.format(resultSet.getTimestamp("FECHAEXPIRACION")));
                            tarjetaCertificadoTO.setEstatus(resultSet.getString("ESTATUS"));
                        }
                        if (resultSet != null) {
                            resultSet.close();
                            resultSet = null;
                        }
                        if (stmt != null) {
                            stmt.close();
                            stmt = null;
                        }
                        if (tarjetaCertificadoTO != null) {
                            if (Utils.diferenciaDiasCertificado((String)cancelaMovimientoTO.getFechaOperacion()) <= 0) {
                                if (tarjetaCertificadoTO.getEstatus().equals("1")) {
                                    if (!cancelaMovimientoTO.getEstatusMovto().equals("I")) {
                                        query = new StringBuilder();
                                        query.append("UPDATE ").append(this.schema_database).append("CC_TBLMOVIMIENTOCERTIFICADO ");
                                        query.append("SET ESTATUS = ? ");
                                        query.append("WHERE FOLIO=? ");
                                        stmt = conn.prepareStatement(query.toString());
                                        stmt.setString(1, "I");
                                        stmt.setString(2, folio);
                                        stmt.executeUpdate();
                                        if (resultSet != null) {
                                            resultSet.close();
                                            resultSet = null;
                                        }
                                        if (stmt != null) {
                                            stmt.close();
                                            stmt = null;
                                        }
                                        float valorRestante = tarjetaCertificadoTO.getSaldo() + cancelaMovimientoTO.getSaldo();
                                        query = new StringBuilder();
                                        query.append("UPDATE ").append(this.schema_database).append("CC_TBLTARJETACERTIFICADO ");
                                        query.append("SET VALORRESTANTE = ? ");
                                        query.append("WHERE NUMTARJETA = ? AND NUMCERTIFICADO = ? ");
                                        stmt = conn.prepareStatement(query.toString());
                                        stmt.setFloat(1, valorRestante);
                                        stmt.setString(2, tarjetaCertificadoTO.getNumeroTarjeta());
                                        stmt.setString(3, tarjetaCertificadoTO.getNumeroCertificado());
                                        stmt.executeUpdate();
                                        if (resultSet != null) {
                                            resultSet.close();
                                            resultSet = null;
                                        }
                                        if (stmt != null) {
                                            stmt.close();
                                            stmt = null;
                                        }
                                        query = new StringBuilder();
                                        query.append("INSERT INTO ");
                                        query.append(this.schema_database).append("CC_TBLMOVIMIENTOCERTIFICADO (FOLIO,NUMCERTIFICADO, ");
                                        query.append("NUMTARJETA,IDMOVTO,IDUSUARIO,PUNTOVTA,FECHAOPER,ESTATUS,VALORAPLICADO, ");
                                        query.append("VALORANTERIOR,VALORRESTANTE,REFERENCIA) ");
                                        query.append("VALUES(?,?,?,?,?,?,?,?,?,?,?,?) ");
                                        stmt = conn.prepareStatement(query.toString());
                                        stmt.setString(1, folio);
                                        stmt.setString(2, tarjetaCertificadoTO.getNumeroCertificado());
                                        stmt.setString(3, tarjetaCertificadoTO.getNumeroTarjeta());
                                        stmt.setString(4, "2");
                                        stmt.setString(5, idUsuario.trim());
                                        stmt.setString(6, idpuntoVta.trim());
                                        stmt.setTimestamp(7, new Timestamp(System.currentTimeMillis()));
                                        stmt.setString(8, "A");
                                        stmt.setFloat(9, cancelaMovimientoTO.getSaldo());
                                        stmt.setFloat(10, tarjetaCertificadoTO.getSaldo());
                                        stmt.setFloat(11, valorRestante);
                                        stmt.setString(12, referencia.trim());
                                        stmt.executeUpdate();
                                        conn.commit();
                                        mensajeServiceTO.setId("00");
                                        mensajeServiceTO.setMensaje("El movimiento fue cancelado exitosamente.");
                                    } else {
                                        mensajeServiceTO.setId("01");
                                        mensajeServiceTO.setMensaje("El movimiento ya fue cancelado.");
                                    }
                                } else {
                                    mensajeServiceTO.setId("01");
                                    mensajeServiceTO.setMensaje("El certificado tiene un estatus diferente a activo");
                                }
                            } else {
                                mensajeServiceTO.setId("01");
                                mensajeServiceTO.setMensaje("El movimiento tiene mas de 1 dia, no puede ser cancelado");
                            }
                        } else {
                            mensajeServiceTO.setId("02");
                            mensajeServiceTO.setMensaje("No existe informacion del certificado de regalo");
                        }
                        break block50;
                    }
                    mensajeServiceTO.setId("01");
                    mensajeServiceTO.setMensaje("No existen movimientos relacionados al folio");
                }
                catch (Exception e) {
                    if (conn != null) {
                        try {
                            conn.rollback();
                        }
                        catch (SQLException cancelaMovimientoTO) {
                            // empty catch block
                        }
                    }
                    mensajeServiceTO.setId("07");
                    mensajeServiceTO.setMensaje("Error: " + e.toString());
                    if (resultSet != null) {
                        try {
                            resultSet.close();
                        }
                        catch (SQLException var15_16) {
                            // empty catch block
                        }
                    }
                    if (stmt != null) {
                        try {
                            stmt.close();
                        }
                        catch (SQLException var15_17) {
                            // empty catch block
                        }
                    }
                    if (conn == null) break block50;
                    try {
                        conn.setAutoCommit(true);
                        conn.close();
                    }
                    catch (SQLException var15_18) {}
                }
            }
            finally {
                if (resultSet != null) {
                    try {
                        resultSet.close();
                    }
                    catch (SQLException var15_22) {}
                }
                if (stmt != null) {
                    try {
                        stmt.close();
                    }
                    catch (SQLException var15_23) {}
                }
                if (conn != null) {
                    try {
                        conn.setAutoCommit(true);
                        conn.close();
                    }
                    catch (SQLException var15_24) {}
                }
            }
        }
        return mensajeServiceTO;
    }

    public boolean consultaTajeta(String numeroTarjeta, long montoCertificado) throws ClaroException {
        boolean existe;
        block29 : {
            Connection conn = null;
            PreparedStatement stmt = null;
            ResultSet resultSet = null;
            existe = false;
            TarjetaCertificadoTO tarjetaCertificadoTO = new TarjetaCertificadoTO();
            try {
                try {
                    StringBuilder query = new StringBuilder();
                    query.append("SELECT NUMTARJETA,VALOR FROM ");
                    query.append(this.schema_database).append("CC_TBLCERTIFICADO ");
                    query.append("WHERE NUMTARJETA = ? AND VALOR = ?");
                    conn = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                    stmt = conn.prepareStatement(query.toString());
                    stmt.setString(1, numeroTarjeta);
                    stmt.setFloat(2, montoCertificado);
                    resultSet = stmt.executeQuery();
                    if (resultSet.next()) {
                        existe = true;
                    }
                }
                catch (Exception e) {
                    tarjetaCertificadoTO.setIdMensaje("07");
                    tarjetaCertificadoTO.setMensaje("Error: " + e.toString());
                    if (resultSet != null) {
                        try {
                            resultSet.close();
                        }
                        catch (SQLException var11_10) {
                            // empty catch block
                        }
                    }
                    if (stmt != null) {
                        try {
                            stmt.close();
                        }
                        catch (SQLException var11_11) {
                            // empty catch block
                        }
                    }
                    if (conn == null) break block29;
                    try {
                        conn.close();
                    }
                    catch (SQLException var11_12) {}
                }
            }
            finally {
                if (resultSet != null) {
                    try {
                        resultSet.close();
                    }
                    catch (SQLException var11_16) {}
                }
                if (stmt != null) {
                    try {
                        stmt.close();
                    }
                    catch (SQLException var11_17) {}
                }
                if (conn != null) {
                    try {
                        conn.close();
                    }
                    catch (SQLException var11_18) {}
                }
            }
        }
        return existe;
    }

    public TarjetaCertificadoTO consultaSaldoCertificado(String numeroTarjeta) throws ClaroException {
        TarjetaCertificadoTO tarjetaCertificadoTO;
        block29 : {
            Connection conn = null;
            PreparedStatement stmt = null;
            ResultSet resultSet = null;
            tarjetaCertificadoTO = new TarjetaCertificadoTO();
            try {
                try {
                    StringBuilder query = new StringBuilder();
                    query.append("SELECT NUMTARJETA, NUMCERTIFICADO, VALORRESTANTE, FECHAACTIVACION, FECHAEXPIRACION, ESTATUS ");
                    query.append("FROM  ").append(this.schema_database).append("CC_TBLTARJETACERTIFICADO ");
                    query.append("WHERE NUMTARJETA = ? ");
                    conn = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                    stmt = conn.prepareStatement(query.toString());
                    stmt.setString(1, numeroTarjeta.trim());
                    resultSet = stmt.executeQuery();
                    if (resultSet.next()) {
                        tarjetaCertificadoTO.setNumeroTarjeta(resultSet.getString("NUMTARJETA").trim());
                        tarjetaCertificadoTO.setNumeroCertificado(resultSet.getString("NUMCERTIFICADO").trim());
                        tarjetaCertificadoTO.setSaldo(resultSet.getFloat("VALORRESTANTE"));
                        tarjetaCertificadoTO.setFechaActivacion(String.valueOf(resultSet.getTimestamp("FECHAACTIVACION")));
                        tarjetaCertificadoTO.setFechaExpiracion(String.valueOf(resultSet.getTimestamp("FECHAEXPIRACION")));
                        tarjetaCertificadoTO.setEstatus(resultSet.getString("ESTATUS").trim());
                        tarjetaCertificadoTO.setIdMensaje("00");
                        tarjetaCertificadoTO.setMensaje("Proceso exitoso");
                        break block29;
                    }
                    tarjetaCertificadoTO.setIdMensaje("02");
                    tarjetaCertificadoTO.setMensaje("Tarjeta no v\u00e1lida.");
                }
                catch (Exception e) {
                    tarjetaCertificadoTO.setIdMensaje("07");
                    tarjetaCertificadoTO.setMensaje("Error: " + e.toString());
                    if (resultSet != null) {
                        try {
                            resultSet.close();
                        }
                        catch (SQLException var8_8) {
                            // empty catch block
                        }
                    }
                    if (stmt != null) {
                        try {
                            stmt.close();
                        }
                        catch (SQLException var8_9) {
                            // empty catch block
                        }
                    }
                    if (conn == null) break block29;
                    try {
                        conn.close();
                    }
                    catch (SQLException var8_10) {}
                }
            }
            finally {
                if (resultSet != null) {
                    try {
                        resultSet.close();
                    }
                    catch (SQLException var8_14) {}
                }
                if (stmt != null) {
                    try {
                        stmt.close();
                    }
                    catch (SQLException var8_15) {}
                }
                if (conn != null) {
                    try {
                        conn.close();
                    }
                    catch (SQLException var8_16) {}
                }
            }
        }
        return tarjetaCertificadoTO;
    }

    public List<MovimientoCertificadoTO> consultaMovimientosCertificado(String numeroTarjeta) throws ClaroException {
        ArrayList<MovimientoCertificadoTO> movimientos;
        block13 : {
            movimientos = new ArrayList<MovimientoCertificadoTO>();
            Connection conn = null;
            PreparedStatement stmt = null;
            ResultSet resultSet = null;
            try {
                try {
                    StringBuilder query = new StringBuilder();
                    query.append("SELECT NUMCERTIFICADO,NUMTARJETA,IDMOVTO,IDUSUARIO,PUNTOVTA, ");
                    query.append("FECHAOPER,ESTATUS,VALORAPLICADO,VALORANTERIOR,VALORRESTANTE,REFERENCIA ");
                    query.append("FROM  ").append(this.schema_database).append("CC_TBLMOVIMIENTOCERTIFICADO ");
                    query.append("WHERE NUMTARJETA = ? ORDER BY FECHAOPER DESC ");
                    conn = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                    stmt = conn.prepareStatement(query.toString());
                    stmt.setString(1, numeroTarjeta.trim());
                    resultSet = stmt.executeQuery();
                    while (resultSet.next()) {
                        MovimientoCertificadoTO movimientoCertificadoTO = new MovimientoCertificadoTO();
                        movimientoCertificadoTO.setNumeroCertificado(resultSet.getString("NUMCERTIFICADO").trim());
                        movimientoCertificadoTO.setNumeroTarjeta(resultSet.getString("NUMTARJETA").trim());
                        movimientoCertificadoTO.setIdMotivo(resultSet.getString("IDMOVTO").trim());
                        movimientoCertificadoTO.setIdUsuario(resultSet.getString("IDUSUARIO").trim());
                        movimientoCertificadoTO.setPuntoVenta(resultSet.getString("PUNTOVTA").trim());
                        movimientoCertificadoTO.setFechaOperacion(String.valueOf(resultSet.getTimestamp("FECHAOPER")));
                        movimientoCertificadoTO.setEstatus(resultSet.getString("ESTATUS").trim());
                        movimientoCertificadoTO.setValorAplicado(resultSet.getFloat("VALORAPLICADO"));
                        movimientoCertificadoTO.setValorAnterior(resultSet.getFloat("VALORANTERIOR"));
                        movimientoCertificadoTO.setValorRestante(resultSet.getFloat("VALORRESTANTE"));
                        movimientoCertificadoTO.setReferencia(resultSet.getString("REFERENCIA").trim());
                        movimientos.add(movimientoCertificadoTO);
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                    if (conn == null) break block13;
                    try {
                        conn.close();
                    }
                    catch (SQLException var9_9) {}
                }
            }
            finally {
                if (conn != null) {
                    try {
                        conn.close();
                    }
                    catch (SQLException var9_11) {}
                }
            }
        }
        return movimientos;
    }

    public String consultaTajetaVendida(String numeroTarjeta, long montoCertificado) throws ClaroException {
        String existe;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet resultSet = null;
        existe = "";
        try {
            try {
                StringBuilder query = new StringBuilder();
                query.append("SELECT APLICADO FROM ");
                query.append(this.schema_database).append("CC_TBLCERTIFICADO ");
                query.append("WHERE NUMTARJETA = ? AND VALOR = ?");
                conn = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                stmt = conn.prepareStatement(query.toString());
                stmt.setString(1, numeroTarjeta);
                stmt.setFloat(2, montoCertificado);
                resultSet = stmt.executeQuery();
                existe = resultSet.next() ? (resultSet.getString(1).equals("N") ? "LA TARJETA NO HA SIDO VENDIDO" : (resultSet.getString(1).equals("S") ? "LA TARJETA HA SIDO VENDIDO" : "LA TARJETA HA SIDO CANCELADA")) : "02|LA TARJETA INGRESADA NO EXISTE";
            }
            catch (Exception e) {
                String string = "Error: " + e.toString();
                if (resultSet != null) {
                    try {
                        resultSet.close();
                    }
                    catch (SQLException var11_10) {
                        // empty catch block
                    }
                }
                if (stmt != null) {
                    try {
                        stmt.close();
                    }
                    catch (SQLException var11_11) {
                        // empty catch block
                    }
                }
                if (conn != null) {
                    try {
                        conn.close();
                    }
                    catch (SQLException var11_12) {
                        // empty catch block
                    }
                }
                return string;
            }
        }
        finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                }
                catch (SQLException var11_16) {}
            }
            if (stmt != null) {
                try {
                    stmt.close();
                }
                catch (SQLException var11_17) {}
            }
            if (conn != null) {
                try {
                    conn.close();
                }
                catch (SQLException var11_18) {}
            }
        }
        return existe;
    }
}

