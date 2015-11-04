/*
 * Decompiled with CFR 0_102.
 * 
 * Could not load the following classes:
 *  com.claro.util.ServiceLocator
 *  org.apache.log4j.Logger
 */
package com.claro.redencion.dao;

import com.claro.util.ServiceLocator;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import org.apache.log4j.Logger;

public class RedencionMulticotizadorDAO {
    protected final Logger logger = Logger.getLogger((String)"loggerCirculoAzul");
    protected final Logger error = Logger.getLogger((String)"loggerCirculoAzulError");
    private String schema_database;

    public RedencionMulticotizadorDAO() {
        try {
            this.schema_database = ServiceLocator.getInstance().getVariable(ServiceLocator.schema_database);
        }
        catch (Exception e) {
            this.error.error((Object)"RedencionMulticotizadorDAO", (Throwable)e);
        }
    }

    public String obtienePropiedad(String idVariable) throws Exception {
        String aplicaRedencionMult;
        aplicaRedencionMult = "";
        Connection conn = null;
        PreparedStatement prepStat = null;
        ResultSet resultSet = null;
        try {
            try {
                conn = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                StringBuffer query = new StringBuffer();
                query.append("SELECT valor FROM ").append(this.schema_database).append("PTO_CTLPROPIEDADES ");
                query.append("WHERE IDVARIABLE = ? ");
                prepStat = conn.prepareStatement(query.toString());
                prepStat.setString(1, idVariable);
                resultSet = prepStat.executeQuery();
                if (resultSet.next()) {
                    aplicaRedencionMult = resultSet.getString("valor");
                }
            }
            catch (Exception e) {
                throw new Exception("RedencionMulticotizadorDAO.aplicaRedencionMulticotizador [" + e + "]");
            }
        }
        finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                    resultSet = null;
                }
                catch (Exception var8_12) {}
            }
            if (prepStat != null) {
                try {
                    prepStat.close();
                    prepStat = null;
                }
                catch (Exception var8_13) {}
            }
            if (conn != null) {
                try {
                    conn.close();
                    conn = null;
                }
                catch (Exception var8_14) {}
            }
        }
        return aplicaRedencionMult;
    }
}

