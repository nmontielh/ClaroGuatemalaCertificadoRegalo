/*
 * Decompiled with CFR 0_102.
 * 
 * Could not load the following classes:
 *  com.claro.util.ServiceLocator
 *  org.apache.log4j.Logger
 */
package com.claro.promociones.dao;

import com.claro.util.ServiceLocator;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import org.apache.log4j.Logger;

public class ReplicaPromoDAO {
    private String schema_database;
    protected final Logger error = Logger.getLogger((String)"loggerCirculoAzulError");

    public ReplicaPromoDAO() {
        try {
            this.schema_database = ServiceLocator.getInstance().getVariable(ServiceLocator.schema_database);
        }
        catch (Exception e) {
            this.error.error((Object)("Error al obtener el esquema de la BD en ReplicaPromoDAO, " + e.getMessage()));
        }
    }

    public int insertaDetalleReplicaPromoFTPR1R8(String nombreArchivo, String operacion) throws Exception {
        int idArchivo;
        idArchivo = 0;
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            try {
                StringBuilder query = new StringBuilder();
                query.append("INSERT INTO ").append(this.schema_database).append("PTO_TBLREPLICAPROMOR1R8 ");
                query.append("VALUES(?,?,?,?,null,?) ");
                conn = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                stmt = conn.prepareStatement(query.toString());
                idArchivo = this.getConsecutivoDetalleReplicaPromoFTPR1R8(conn);
                stmt.setInt(1, idArchivo);
                stmt.setString(2, nombreArchivo);
                stmt.setString(3, operacion);
                stmt.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
                stmt.setString(5, "T");
                stmt.executeUpdate();
            }
            catch (Exception e) {
                this.error.error((Object)("Error al insertar detalle de replica promo FTP R1-R8, archivo: , " + nombreArchivo + ", error:" + e.getMessage()));
                throw new Exception(e.getMessage());
            }
        }
        finally {
            if (stmt != null) {
                try {
                    stmt.close();
                }
                catch (SQLException var8_11) {}
            }
            if (conn != null) {
                try {
                    conn.close();
                }
                catch (SQLException var8_12) {}
            }
        }
        return idArchivo;
    }

    public int verificaExisteDetalleReplicaPromoFTPR1R8(String nombreArchivo) throws Exception {
        int idArchivo;
        idArchivo = 0;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet resultSet = null;
        try {
            try {
                StringBuilder query = new StringBuilder();
                query.append("SELECT IDARCHIVO FROM ");
                query.append(this.schema_database).append("PTO_TBLREPLICAPROMOR1R8 ");
                query.append("WHERE NOMBRE_ARCHIVO = ?");
                conn = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                stmt = conn.prepareStatement(query.toString());
                stmt.setString(1, nombreArchivo);
                resultSet = stmt.executeQuery();
                while (resultSet.next()) {
                    idArchivo = resultSet.getInt("IDARCHIVO");
                }
            }
            catch (Exception e) {
                this.error.error((Object)("Error al verificar si existe detalle de replica promo FTP R1-R8, archivo: , " + nombreArchivo + ", error:" + e.getMessage()));
                throw new Exception(e.getMessage());
            }
        }
        finally {
            if (stmt != null) {
                try {
                    stmt.close();
                }
                catch (SQLException var8_12) {}
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                }
                catch (SQLException var8_13) {}
            }
            if (conn != null) {
                try {
                    conn.close();
                }
                catch (SQLException var8_14) {}
            }
        }
        return idArchivo;
    }

    public boolean actualizaDetalleReplicaPromoFTPR1R8(int idArchivo, String estatus, String operacion) throws Exception {
        boolean success;
        success = false;
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            try {
                StringBuilder query = new StringBuilder();
                query.append("UPDATE ").append(this.schema_database).append("PTO_TBLREPLICAPROMOR1R8 ");
                query.append("SET ");
                if (operacion != null) {
                    query.append("OPERACION=?, ");
                }
                query.append("INICIO_PROCESO=?, FIN_PROCESO=null, ESTATUS=? ");
                query.append("WHERE IDARCHIVO=? ");
                conn = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                stmt = conn.prepareStatement(query.toString());
                int idx = 0;
                if (operacion != null) {
                    stmt.setString(++idx, operacion);
                }
                stmt.setTimestamp(++idx, new Timestamp(System.currentTimeMillis()));
                stmt.setString(++idx, estatus);
                stmt.setInt(++idx, idArchivo);
                stmt.executeUpdate();
                success = true;
            }
            catch (Exception e) {
                this.error.error((Object)("Error al actualizar detalle de replica promo FTP R1-R8, idArchivo: , " + idArchivo + ", error:" + e.getMessage()));
                throw new Exception(e.getMessage());
            }
        }
        finally {
            if (stmt != null) {
                try {
                    stmt.close();
                }
                catch (SQLException var10_13) {}
            }
            if (conn != null) {
                try {
                    conn.close();
                }
                catch (SQLException var10_14) {}
            }
        }
        return success;
    }

    private int getConsecutivoDetalleReplicaPromoFTPR1R8(Connection conn) {
        int consecutivo;
        block21 : {
            PreparedStatement stmt = null;
            ResultSet resultSet = null;
            consecutivo = 0;
            try {
                try {
                    StringBuilder query = new StringBuilder();
                    query.append("SELECT MAX(IDARCHIVO) FROM ").append(this.schema_database).append("PTO_TBLREPLICAPROMOR1R8  ");
                    conn = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                    stmt = conn.prepareStatement(query.toString());
                    resultSet = stmt.executeQuery();
                    while (resultSet.next()) {
                        consecutivo = resultSet.getInt(1);
                    }
                    ++consecutivo;
                }
                catch (Exception e) {
                    this.error.error((Object)("Error al obtener consecutivo de detalle de replica promo FTP R1-R8, error:" + e.getMessage()));
                    if (stmt != null) {
                        try {
                            stmt.close();
                        }
                        catch (SQLException var7_7) {
                            // empty catch block
                        }
                    }
                    if (resultSet == null) break block21;
                    try {
                        resultSet.close();
                    }
                    catch (SQLException var7_8) {}
                }
            }
            finally {
                if (stmt != null) {
                    try {
                        stmt.close();
                    }
                    catch (SQLException var7_11) {}
                }
                if (resultSet != null) {
                    try {
                        resultSet.close();
                    }
                    catch (SQLException var7_12) {}
                }
            }
        }
        return consecutivo;
    }
}

