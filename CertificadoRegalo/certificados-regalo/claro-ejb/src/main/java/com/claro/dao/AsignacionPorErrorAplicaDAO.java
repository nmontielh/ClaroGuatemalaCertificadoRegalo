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
import org.apache.log4j.Logger;

public class AsignacionPorErrorAplicaDAO {
    protected final Logger logger = Logger.getLogger((String)"loggerCirculoAzul");
    protected final Logger error = Logger.getLogger((String)"loggerCirculoAzulError");
    private String schema_database;

    public AsignacionPorErrorAplicaDAO() {
        try {
            this.schema_database = ServiceLocator.getInstance().getVariable(ServiceLocator.schema_database);
        }
        catch (Exception e) {
            this.error.error((Object)"ReactivaDAO", (Throwable)e);
        }
    }

    public void AsignacionPorErrorAplic(AsignaPorErrorTO asignaPorErrorTO, String Cuenta, String Telefono, String numempleado) throws CAException {
        Connection conn = null;
        PreparedStatement statement = null;
        PreparedStatement updatepuntos = null;
        PreparedStatement insertamovimiento = null;
        ResultSet resultSet = null;
        try {
            try {
                conn = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                StringBuffer query = new StringBuffer();
                query.append("SELECT PUNTOSEXCEDENTES FROM ");
                query.append(this.schema_database).append("PTO_TBLTOTALES ");
                query.append("where CUENTA=? AND SECUENCIA=?");
                statement = conn.prepareStatement(query.toString());
                statement.setString(1, Cuenta);
                statement.setString(2, asignaPorErrorTO.getSecuencia());
                resultSet = statement.executeQuery();
                int puntosexc = 0;
                while (resultSet.next()) {
                    puntosexc = resultSet.getInt("PUNTOSEXCEDENTES");
                }
                StringBuffer queryupdate = new StringBuffer();
                queryupdate.append("UPDATE ").append(this.schema_database).append("PTO_TBLTOTALES ");
                queryupdate.append(" SET PUNTOSEXCEDENTES =? WHERE CUENTA=? AND SECUENCIA =? ");
                updatepuntos = conn.prepareStatement(queryupdate.toString());
                updatepuntos.setInt(1, puntosexc+=Integer.parseInt(asignaPorErrorTO.getPuntos()));
                updatepuntos.setString(2, Cuenta);
                updatepuntos.setString(3, asignaPorErrorTO.getSecuencia());
                updatepuntos.executeUpdate();
                String comentario = "ASIGNA: " + numempleado + " COMENT: PUNTOS ASIGNADOS POR CUENTA ELIMINADA ERRONEAMENTE";
                StringBuffer queryinsert = new StringBuffer();
                queryinsert.append(" INSERT INTO ").append(this.schema_database).append("PTO_TBLMSTRDETALLE ");
                queryinsert.append(" (CUENTA,SECUENCIA,LINEA,FECHAFAC,FECHAOPERACION,IDMOVTO,IDUSUARIO, ");
                queryinsert.append(" NUMPUNTOS,NUMPUNTOSEXC,TOTAJUSTES,IDBONOPROM,REFERENCIA) VALUES ");
                queryinsert.append(" (?,?,?,SYSDATE,SYSDATE, ");
                queryinsert.append(" 59,'VIBPT05',0,0,?,null,?) ");
                insertamovimiento = conn.prepareStatement(queryinsert.toString());
                insertamovimiento.setString(1, Cuenta);
                insertamovimiento.setString(2, asignaPorErrorTO.getSecuencia());
                insertamovimiento.setString(3, Telefono);
                insertamovimiento.setInt(4, Integer.parseInt(asignaPorErrorTO.getPuntos()));
                insertamovimiento.setString(5, comentario);
                insertamovimiento.executeUpdate();
            }
            catch (SQLException e) {
                this.error.info((Object)"AsignacionPorErrorAplicaDAO.AsignacionPorErrorAplic.SQLException:", (Throwable)e);
                throw new CAException(-1, "AsignacionPorErrorAplicaDAO.AsignacionPorErrorAplic.SQLException[" + e.toString() + "]", (Exception)e);
            }
            catch (Exception e) {
                this.error.info((Object)"AsignacionPorErrorAplicaDAO.AsignacionPorErrorAplic.Exception:", (Throwable)e);
                throw new CAException(-1, "AsignacionPorErrorAplicaDAO.AsignacionPorErrorAplic.Error[" + e.toString() + "]", e);
            }
        }
        finally {
            if (statement != null) {
                try {
                    statement.close();
                    statement = null;
                }
                catch (Exception var16_21) {}
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                    resultSet = null;
                }
                catch (Exception var16_22) {}
            }
            if (conn != null) {
                try {
                    conn.close();
                    conn = null;
                }
                catch (Exception var16_23) {}
            }
        }
    }

    public boolean VerificasiexisteAPTOE(String Cuenta, String Telefono) throws CAException {
        boolean bandera;
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        bandera = false;
        try {
            try {
                conn = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                StringBuffer query = new StringBuffer();
                query.append("SELECT IDMOVTO FROM ");
                query.append(this.schema_database).append("PTO_TBLMSTRDETALLE ");
                query.append("where IDMOVTO=59 AND CUENTA=? AND LINEA=?");
                statement = conn.prepareStatement(query.toString());
                statement.setString(1, Cuenta);
                statement.setString(2, Telefono);
                resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    bandera = true;
                }
            }
            catch (SQLException e) {
                this.error.info((Object)"AsignacionPorErrorAplicaDAO.VerificasiexisteAPTOE.SQLException:", (Throwable)e);
                throw new CAException(-1, "AsignacionPorErrorAplicaDAO.VerificasiexisteAPTOE.SQLException[" + e.toString() + "]", (Exception)e);
            }
            catch (Exception e) {
                this.error.info((Object)"AsignacionPorErrorAplicaDAO.VerificasiexisteAPTOE.Exception:", (Throwable)e);
                throw new CAException(-1, "AsignacionPorErrorAplicaDAO.VerificasiexisteAPTOE.Error[" + e.toString() + "]", e);
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
        return bandera;
    }
}

