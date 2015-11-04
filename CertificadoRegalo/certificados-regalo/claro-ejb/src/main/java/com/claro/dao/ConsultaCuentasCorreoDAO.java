/*
 * Decompiled with CFR 0_102.
 * 
 * Could not load the following classes:
 *  com.claro.util.ServiceLocator
 *  org.apache.log4j.Logger
 */
package com.claro.dao;

import com.claro.util.ServiceLocator;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;

public class ConsultaCuentasCorreoDAO {
    protected final Logger error = Logger.getLogger((String)"loggerCirculoAzulError");
    protected final Logger logger = Logger.getLogger((String)"loggerCirculoAzul");
    private String schema_database;

    public ConsultaCuentasCorreoDAO() {
        try {
            this.schema_database = ServiceLocator.getInstance().getVariable(ServiceLocator.schema_database);
        }
        catch (Exception e) {
            this.error.error((Object)"ConsultaCuentasCorreoDAO", (Throwable)e);
        }
    }

    public Map<String, String> obtieneCuentasCorreo(int idReporte) {
        HashMap<String, String> parametrosCorreo;
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        parametrosCorreo = null;
        try {
            try {
                conn = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                StringBuffer query = new StringBuffer();
                query.append("SELECT EMISOR,MAILTO,MAILTO1 FROM ");
                query.append(this.schema_database).append("PTO_CTLREPORTES ");
                query.append("where ID=?");
                statement = conn.prepareStatement(query.toString());
                statement.setInt(1, idReporte);
                resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    parametrosCorreo = new HashMap<String, String>();
                    parametrosCorreo.put("emisor", resultSet.getString("EMISOR"));
                    parametrosCorreo.put("mailTo", resultSet.getString("MAILTO"));
                    parametrosCorreo.put("mailTo1", resultSet.getString("MAILTO1"));
                }
            }
            catch (Exception query) {
                try {
                    if (resultSet != null) {
                        resultSet.close();
                    }
                }
                catch (Exception var8_8) {
                    // empty catch block
                }
                try {
                    if (statement != null) {
                        statement.close();
                    }
                }
                catch (Exception var8_9) {
                    // empty catch block
                }
                try {
                    if (conn != null) {
                        conn.close();
                    }
                }
                catch (Exception var8_10) {}
            }
        }
        finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            }
            catch (Exception var8_14) {}
            try {
                if (statement != null) {
                    statement.close();
                }
            }
            catch (Exception var8_15) {}
            try {
                if (conn != null) {
                    conn.close();
                }
            }
            catch (Exception var8_16) {}
        }
        return parametrosCorreo;
    }

    public Map<String, String> obtieneCuentasCorreoCargaCatalogos(int idRegion) {
        HashMap<String, String> parametrosCorreo;
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        parametrosCorreo = null;
        try {
            try {
                conn = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                StringBuffer query = new StringBuffer();
                query.append("SELECT EMISOR,MAILTO,MAILTO1 FROM ");
                query.append(this.schema_database).append("PTO_CTLREPORTES ");
                query.append("where ID=?");
                statement = conn.prepareStatement(query.toString());
                if (idRegion == 9) {
                    statement.setInt(1, 2001);
                } else {
                    statement.setInt(1, 2000);
                }
                resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    parametrosCorreo = new HashMap<String, String>();
                    parametrosCorreo.put("emisor", resultSet.getString("EMISOR"));
                    parametrosCorreo.put("mailTo", resultSet.getString("MAILTO"));
                    parametrosCorreo.put("mailTo1", resultSet.getString("MAILTO1"));
                }
            }
            catch (Exception query) {
                try {
                    if (resultSet != null) {
                        resultSet.close();
                    }
                }
                catch (Exception var8_8) {
                    // empty catch block
                }
                try {
                    if (statement != null) {
                        statement.close();
                    }
                }
                catch (Exception var8_9) {
                    // empty catch block
                }
                try {
                    if (conn != null) {
                        conn.close();
                    }
                }
                catch (Exception var8_10) {}
            }
        }
        finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            }
            catch (Exception var8_14) {}
            try {
                if (statement != null) {
                    statement.close();
                }
            }
            catch (Exception var8_15) {}
            try {
                if (conn != null) {
                    conn.close();
                }
            }
            catch (Exception var8_16) {}
        }
        return parametrosCorreo;
    }
}

