/*
 * Decompiled with CFR 0_102.
 * 
 * Could not load the following classes:
 *  com.claro.exception.CAException
 *  com.claro.transfer.TelefonoTO
 *  com.claro.util.ServiceLocator
 *  org.apache.log4j.Logger
 */
package com.claro.dao;

import com.claro.exception.CAException;
import com.claro.transfer.TelefonoTO;
import com.claro.util.ServiceLocator;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

public class LineasRestringidasDAO {
    protected final Logger logger = Logger.getLogger((String)"loggerCirculoAzul");
    protected final Logger error = Logger.getLogger((String)"loggerCirculoAzulError");
    private String schema_database;

    public LineasRestringidasDAO() {
        try {
            this.schema_database = ServiceLocator.getInstance().getVariable(ServiceLocator.schema_database);
        }
        catch (Exception e) {
            this.error.error((Object)"LineasRestringidasDAO", (Throwable)e);
        }
    }

    public boolean isLineaBloqueada(String cuenta, String telefono) throws CAException {
        boolean isLineaBloqueada;
        isLineaBloqueada = false;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet resultSet = null;
        StringBuilder query = new StringBuilder();
        try {
            try {
                conn = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                if (telefono == null || telefono.trim().equals("") || cuenta == null || cuenta.trim().equals("")) {
                    if (telefono == null || telefono.trim().equals("")) {
                        query.append("SELECT CUENTA, LINEA FROM ").append(this.schema_database).append("PTO_TBLLINEAS where CUENTA=? ");
                        stmt = conn.prepareStatement(query.toString());
                        stmt.setString(1, cuenta);
                    } else {
                        query.append("SELECT CUENTA, LINEA FROM ").append(this.schema_database).append("PTO_TBLLINEAS where LINEA=? ");
                        stmt = conn.prepareStatement(query.toString());
                        stmt.setString(1, telefono);
                    }
                    resultSet = stmt.executeQuery();
                    while (resultSet.next()) {
                        cuenta = resultSet.getString("CUENTA");
                        telefono = resultSet.getString("LINEA");
                    }
                    if (stmt != null) {
                        stmt.close();
                        stmt = null;
                    }
                    if (resultSet != null) {
                        resultSet.close();
                        resultSet = null;
                    }
                }
                query = new StringBuilder();
                query.append("SELECT MODSUBASTA FROM ").append(this.schema_database).append("PTO_TBLLINEAS where CUENTA=? AND LINEA=? ");
                stmt = conn.prepareStatement(query.toString());
                stmt.setString(1, cuenta);
                stmt.setString(2, telefono);
                resultSet = stmt.executeQuery();
                while (resultSet.next()) {
                    int lineaBloqueada = resultSet.getInt("MODSUBASTA");
                    if (lineaBloqueada != 1) continue;
                    isLineaBloqueada = true;
                }
            }
            catch (Exception e) {
                throw new CAException(-1, "LineasRestringidasDAO.isLineaBloqueada[" + e.toString() + "]", e);
            }
        }
        finally {
            if (stmt != null) {
                try {
                    stmt.close();
                    stmt = null;
                }
                catch (Exception var10_14) {}
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                    stmt = null;
                }
                catch (Exception var10_15) {}
            }
            if (conn != null) {
                try {
                    conn.close();
                    stmt = null;
                }
                catch (Exception var10_16) {}
            }
        }
        return isLineaBloqueada;
    }

    public void cargaLineasRestringidas(List<TelefonoTO> lineas) throws CAException {
        Connection conn = null;
        ResultSet resultSet = null;
        PreparedStatement stmt = null;
        try {
            try {
                conn = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                StringBuilder queryBusqueda = new StringBuilder();
                queryBusqueda.append("SELECT CUENTA, LINEA FROM ").append(this.schema_database).append("PTO_TBLLINEAS ");
                queryBusqueda.append("WHERE CUENTA = ? AND LINEA = ?");
                StringBuilder update = new StringBuilder();
                update.append("UPDATE ").append(this.schema_database).append("PTO_TBLLINEAS ");
                update.append("SET MODSUBASTA = 1 ");
                update.append("WHERE CUENTA = ? AND LINEA = ? ");
                StringBuilder insertaLinea = new StringBuilder();
                insertaLinea.append("INSERT INTO ").append(this.schema_database).append("PTO_TBLLINEAS ");
                insertaLinea.append("VALUES (?,1,'',?,9,'','AC',0,0,null,null,null,null,'0','P',1,null,null,null,null)");
                StringBuilder insertaTotales = new StringBuilder();
                insertaTotales.append("INSERT INTO ").append(this.schema_database).append("PTO_TBLTOTALES ");
                insertaTotales.append("VALUES (?,1,null,0,0,0,0,0,0,0,0,0,0,0,null,null,0,null,0,null,null,null,0,0,0,0,0,0,0,0,0,null)");
                StringBuilder buscaTotales = new StringBuilder();
                buscaTotales.append("SELECT CUENTA FROM ").append(this.schema_database).append("PTO_TBLTOTALES where CUENTA = ? ");
                for (TelefonoTO telefonoTO : lineas) {
                    stmt = conn.prepareStatement(queryBusqueda.toString());
                    stmt.setString(1, telefonoTO.getCuenta());
                    stmt.setString(2, telefonoTO.getTelefono());
                    resultSet = stmt.executeQuery();
                    boolean existeLinea = false;
                    while (resultSet.next()) {
                        existeLinea = true;
                    }
                    if (resultSet != null) {
                        resultSet.close();
                        resultSet = null;
                    }
                    if (stmt != null) {
                        stmt.close();
                        stmt = null;
                    }
                    if (existeLinea) {
                        stmt = conn.prepareStatement(update.toString());
                        stmt.setString(1, telefonoTO.getCuenta());
                        stmt.setString(2, telefonoTO.getTelefono());
                        stmt.executeUpdate();
                        continue;
                    }
                    boolean existeTotales = false;
                    stmt = conn.prepareStatement(buscaTotales.toString());
                    stmt.setString(1, telefonoTO.getCuenta());
                    resultSet = stmt.executeQuery();
                    if (resultSet.next()) {
                        existeTotales = true;
                    }
                    if (resultSet != null) {
                        resultSet.close();
                        resultSet = null;
                    }
                    if (stmt != null) {
                        stmt.close();
                        stmt = null;
                    }
                    if (!existeLinea) {
                        stmt = conn.prepareStatement(insertaLinea.toString());
                        stmt.setString(1, telefonoTO.getCuenta().trim());
                        stmt.setString(2, telefonoTO.getTelefono().trim());
                        stmt.executeUpdate();
                        if (stmt != null) {
                            stmt.close();
                            stmt = null;
                        }
                    }
                    if (existeTotales) continue;
                    stmt = conn.prepareStatement(insertaTotales.toString().trim());
                    stmt.setString(1, telefonoTO.getCuenta().trim());
                    stmt.executeUpdate();
                    if (stmt == null) continue;
                    stmt.close();
                    stmt = null;
                }
            }
            catch (Exception e) {
                throw new CAException(-1, "LineasRestringidasDAO.cargaLineasRestringidas[" + e.toString() + "]", e);
            }
        }
        finally {
            if (stmt != null) {
                try {
                    stmt.close();
                    stmt = null;
                }
                catch (Exception var15_19) {}
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                    stmt = null;
                }
                catch (Exception var15_20) {}
            }
            if (conn != null) {
                try {
                    conn.close();
                    stmt = null;
                }
                catch (Exception var15_21) {}
            }
        }
    }

    public List<TelefonoTO> generaReporteLineasRestringidas() throws CAException {
        ArrayList<TelefonoTO> lineas;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet resultSet = null;
        lineas = new ArrayList<TelefonoTO>();
        StringBuilder query = new StringBuilder();
        try {
            try {
                conn = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                query.append("SELECT CUENTA, LINEA, IDREGION, PLAN FROM ").append(this.schema_database).append("PTO_TBLLINEAS where MODSUBASTA=1 ");
                stmt = conn.prepareStatement(query.toString());
                resultSet = stmt.executeQuery();
                while (resultSet.next()) {
                    TelefonoTO telefonoTO = new TelefonoTO();
                    telefonoTO.setCuenta(resultSet.getString("CUENTA"));
                    telefonoTO.setTelefono(resultSet.getString("LINEA"));
                    telefonoTO.setRegion(resultSet.getInt("IDREGION"));
                    telefonoTO.setPlan(resultSet.getString("PLAN"));
                    lineas.add(telefonoTO);
                }
            }
            catch (Exception e) {
                throw new CAException(-1, "LineasRestringidasDAO.generaReporte[" + e.toString() + "]", e);
            }
        }
        finally {
            if (stmt != null) {
                try {
                    stmt.close();
                    stmt = null;
                }
                catch (Exception var8_12) {}
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                    stmt = null;
                }
                catch (Exception var8_13) {}
            }
            if (conn != null) {
                try {
                    conn.close();
                    stmt = null;
                }
                catch (Exception var8_14) {}
            }
        }
        return lineas;
    }
}

