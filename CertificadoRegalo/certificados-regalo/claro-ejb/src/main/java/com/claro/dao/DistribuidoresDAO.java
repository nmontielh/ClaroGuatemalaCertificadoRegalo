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
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.log4j.Logger;

public class DistribuidoresDAO {
    protected final Logger error = Logger.getLogger((String)"loggerCirculoAzulError");
    protected final Logger logger = Logger.getLogger((String)"loggerCirculoAzul");
    private String schema_database;

    public DistribuidoresDAO() {
        try {
            this.schema_database = ServiceLocator.getInstance().getVariable(ServiceLocator.schema_database);
        }
        catch (Exception e) {
            this.error.error((Object)"DistribuidoresDAO", (Throwable)e);
        }
    }

    public boolean validaFzaVentasImssFonacot(String fuerzaVentas, String claseCredito, Connection conexion) {
        boolean fzaVentasValida;
        block22 : {
        	PreparedStatement stmt = null;
            StringBuffer query = new StringBuffer();
            ResultSet resultSet = null;
            fzaVentasValida = false;
            try {
                try {
                    if (claseCredito.equals("FO") || claseCredito.equals("IM")) {
                        query.append("SELECT FZAVENTAS FROM ").append(this.schema_database).append("PTO_CTLFZA_VENTAS ");
                        query.append("where FZAVENTAS=? and PLAN_VISIBLE = 'IMFO' and ESTATUS = 'A' ");
                    }
                    stmt = conexion.prepareStatement(query.toString());
                    stmt.setString(1, fuerzaVentas);
                    resultSet = stmt.executeQuery();
                    while (resultSet.next()) {
                        fzaVentasValida = true;
                    }
                }
                catch (SQLException e) {
                    this.error.error((Object)("validaFzaVentasImssFonacot.Excepcion: " + e));
                    if (stmt != null) {
                        try {
                            stmt.close();
                        }
                        catch (SQLException var10_9) {
                            // empty catch block
                        }
                    }
                    if (resultSet == null) break block22;
                    try {
                        resultSet.close();
                    }
                    catch (SQLException var10_10) {}
                }
            }
            finally {
                if (stmt != null) {
                    try {
                        stmt.close();
                    }
                    catch (SQLException var10_13) {}
                }
                if (resultSet != null) {
                    try {
                        resultSet.close();
                    }
                    catch (SQLException var10_14) {}
                }
            }
        }
        return fzaVentasValida;
    }
}

