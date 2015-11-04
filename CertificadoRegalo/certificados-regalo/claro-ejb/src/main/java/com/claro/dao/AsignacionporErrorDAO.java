/*
 * Decompiled with CFR 0_102.
 * 
 * Could not load the following classes:
 *  com.claro.exception.CAException
 *  com.claro.transfer.AsignaPorErrorTO
 *  com.claro.util.ServiceLocator
 *  org.apache.log4j.Logger
 */
package com.claro.dao;

import com.claro.exception.CAException;
import com.claro.transfer.AsignaPorErrorTO;
import com.claro.util.ServiceLocator;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import org.apache.log4j.Logger;

public class AsignacionporErrorDAO {
    protected final Logger logger = Logger.getLogger((String)"loggerCirculoAzul");
    protected final Logger error = Logger.getLogger((String)"loggerCirculoAzulError");
    private String schema_database;

    public AsignacionporErrorDAO() {
        try {
            this.schema_database = ServiceLocator.getInstance().getVariable(ServiceLocator.schema_database);
        }
        catch (Exception e) {
            this.error.error((Object)"ReactivaDAO", (Throwable)e);
        }
    }

    public AsignaPorErrorTO VerificaEPBAJ(String cuenta, String linea) throws CAException {
        AsignaPorErrorTO asignaPorErrorTO;
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        asignaPorErrorTO = null;
        try {
            try {
                conn = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                StringBuffer query = new StringBuffer();
                query.append("SELECT TOTAJUSTES, FECHAOPERACION,SECUENCIA FROM ");
                query.append(this.schema_database).append("PTO_TBLMSTRDETALLE ");
                query.append("where IDMOVTO=57 AND CUENTA=? AND LINEA=?");
                statement = conn.prepareStatement(query.toString());
                statement.setString(1, cuenta);
                statement.setString(2, linea);
                resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    asignaPorErrorTO = new AsignaPorErrorTO();
                    asignaPorErrorTO.setPuntos(resultSet.getString("TOTAJUSTES"));
                    asignaPorErrorTO.setFechaOperacion(resultSet.getTimestamp("FECHAOPERACION"));
                    asignaPorErrorTO.setSecuencia(resultSet.getString("SECUENCIA"));
                }
            }
            catch (SQLException e) {
                this.error.info((Object)"AsignacionporErrorDAO.VerificaEPBAJ.SQLException:", (Throwable)e);
                throw new CAException(-1, "AsignacionporErrorDAO.VerificaEPBAJ.SQLException[" + e.toString() + "]", (Exception)e);
            }
            catch (Exception e) {
                this.error.info((Object)"AsignacionporErrorDAO.VerificaEPBAJ.Exception:", (Throwable)e);
                throw new CAException(-1, "AsignacionporErrorDAO.VerificaEPBAJ.Error[" + e.toString() + "]", e);
            }
        }
        finally {
            if (statement != null) {
                try {
                    statement.close();
                    statement = null;
                }
                catch (Exception var9_14) {}
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                    resultSet = null;
                }
                catch (Exception var9_15) {}
            }
            if (conn != null) {
                try {
                    conn.close();
                    conn = null;
                }
                catch (Exception var9_16) {}
            }
        }
        return asignaPorErrorTO;
    }
}

