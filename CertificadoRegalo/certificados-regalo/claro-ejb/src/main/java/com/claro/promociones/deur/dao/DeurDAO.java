/*
 * Decompiled with CFR 0_102.
 * 
 * Could not load the following classes:
 *  com.claro.util.ServiceLocator
 *  org.apache.log4j.Logger
 */
package com.claro.promociones.deur.dao;

import com.claro.util.ServiceLocator;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import org.apache.log4j.Logger;

public class DeurDAO {
    private String schema_database;
    protected final Logger error = Logger.getLogger((String)"loggerCirculoAzulError");

    public DeurDAO() {
        try {
            this.schema_database = ServiceLocator.getInstance().getVariable(ServiceLocator.schema_database);
        }
        catch (Exception e) {
            this.error.error((Object)("Error al obtener el esquema de la BD en DeurDAO, " + e.getMessage()));
        }
    }

    public int obtieneConsecutivoIdArchivoReplicaPromoDeur() {
        int consecutivoIdArchivo;
        block29 : {
            consecutivoIdArchivo = 0;
            Connection conn = null;
            PreparedStatement stmt = null;
            ResultSet resultSet = null;
            StringBuilder query = new StringBuilder();
            query.append("SELECT MAX(IDARCHIVO) FROM ").append(this.schema_database).append("PTO_TBLREPLICAPROMO ");
            query.append("WHERE REGION_CARGA=? ");
            try {
                try {
                    conn = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                    stmt = conn.prepareStatement(query.toString());
                    stmt.setString(1, "DEUR");
                    resultSet = stmt.executeQuery();
                    if (resultSet.next()) {
                        consecutivoIdArchivo = resultSet.getInt(1);
                        ++consecutivoIdArchivo;
                    }
                }
                catch (Exception e) {
                    this.error.error((Object)("Error al consultar consecutivo de idArchivo para replicaPromoDeur, " + e.getMessage()));
                    if (conn != null) {
                        try {
                            conn.close();
                        }
                        catch (SQLException var8_7) {
                            // empty catch block
                        }
                    }
                    if (stmt != null) {
                        try {
                            stmt.close();
                        }
                        catch (SQLException var8_8) {
                            // empty catch block
                        }
                    }
                    if (resultSet == null) break block29;
                    try {
                        resultSet.close();
                    }
                    catch (SQLException var8_9) {}
                }
            }
            finally {
                if (conn != null) {
                    try {
                        conn.close();
                    }
                    catch (SQLException var8_13) {}
                }
                if (stmt != null) {
                    try {
                        stmt.close();
                    }
                    catch (SQLException var8_14) {}
                }
                if (resultSet != null) {
                    try {
                        resultSet.close();
                    }
                    catch (SQLException var8_15) {}
                }
            }
        }
        return consecutivoIdArchivo;
    }

    public boolean insertaDetalleReplicaPromoDeur(int idArchivo, String regionCarga, String nombreArchivo, String usuario, String estatus) {
        boolean isSuccess;
        block20 : {
            isSuccess = false;
            Connection conn = null;
            PreparedStatement stmt = null;
            StringBuilder query = new StringBuilder();
            query.append("INSERT INTO ").append(this.schema_database).append("PTO_TBLREPLICAPROMO(IDARCHIVO,REGION_CARGA,");
            query.append("NOMBRE_ARCHIVO,INICIO_PROCESO,USUARIO,ESTATUS) ");
            query.append("VALUES(?,?,?,?,?,?)");
            try {
                try {
                    conn = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                    stmt = conn.prepareStatement(query.toString());
                    stmt.setInt(1, idArchivo);
                    stmt.setString(2, regionCarga);
                    stmt.setString(3, nombreArchivo);
                    stmt.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
                    stmt.setString(5, usuario);
                    stmt.setString(6, estatus);
                    stmt.executeUpdate();
                    isSuccess = true;
                }
                catch (Exception e) {
                    this.error.error((Object)("Error al insertar detalle de replicaPromoDeur, " + e.getMessage()));
                    if (conn != null) {
                        try {
                            conn.close();
                        }
                        catch (SQLException var12_11) {
                            // empty catch block
                        }
                    }
                    if (stmt == null) break block20;
                    try {
                        stmt.close();
                    }
                    catch (SQLException var12_12) {}
                }
            }
            finally {
                if (conn != null) {
                    try {
                        conn.close();
                    }
                    catch (SQLException var12_15) {}
                }
                if (stmt != null) {
                    try {
                        stmt.close();
                    }
                    catch (SQLException var12_16) {}
                }
            }
        }
        return isSuccess;
    }

    public boolean actualizaDetalleReplicaPromoDeur(int idArchivo, String regionCarga, String nombreArchivo, long inicioProceso, long finProceso, String usuario, String estatus) {
        boolean isSuccess;
        block28 : {
            isSuccess = false;
            Connection conn = null;
            PreparedStatement stmt = null;
            StringBuilder query = new StringBuilder();
            query.append("UPDATE ").append(this.schema_database).append("PTO_TBLREPLICAPROMO ");
            query.append("SET ESTATUS=? ");
            if (nombreArchivo != null) {
                query.append(",NOMBRE_ARCHIVO=? ");
            }
            if (inicioProceso > 0) {
                query.append(",INICIO_PROCESO=? ");
            }
            if (finProceso > 0) {
                query.append(",FIN_PROCESO=? ");
            }
            if (usuario != null) {
                query.append(",USUARIO=? ");
            }
            query.append("WHERE IDARCHIVO=? and REGION_CARGA=? ");
            try {
                try {
                    conn = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                    int idx = 0;
                    stmt = conn.prepareStatement(query.toString());
                    stmt.setString(++idx, estatus);
                    if (nombreArchivo != null) {
                        stmt.setString(++idx, nombreArchivo);
                    }
                    if (inicioProceso > 0) {
                        stmt.setTimestamp(++idx, new Timestamp(inicioProceso));
                    }
                    if (finProceso > 0) {
                        stmt.setTimestamp(++idx, new Timestamp(finProceso));
                    }
                    if (usuario != null) {
                        stmt.setString(++idx, usuario);
                    }
                    stmt.setInt(++idx, idArchivo);
                    stmt.setString(++idx, regionCarga);
                    stmt.executeUpdate();
                    isSuccess = true;
                }
                catch (Exception e) {
                    this.error.error((Object)("Error al actualizar detalle de replicaPromoDeur, " + e.getMessage()));
                    if (conn != null) {
                        try {
                            conn.close();
                        }
                        catch (SQLException var16_14) {
                            // empty catch block
                        }
                    }
                    if (stmt == null) break block28;
                    try {
                        stmt.close();
                    }
                    catch (SQLException var16_15) {}
                }
            }
            finally {
                if (conn != null) {
                    try {
                        conn.close();
                    }
                    catch (SQLException var16_18) {}
                }
                if (stmt != null) {
                    try {
                        stmt.close();
                    }
                    catch (SQLException var16_19) {}
                }
            }
        }
        return isSuccess;
    }

    public int verificaExisteDetalleReplicaPromoDeur(String nombreArchivo, String regionCarga) {
        int idArchivo;
        block29 : {
            idArchivo = 0;
            Connection conn = null;
            PreparedStatement stmt = null;
            ResultSet resultSet = null;
            StringBuilder query = new StringBuilder();
            query.append("SELECT IDARCHIVO FROM ").append(this.schema_database).append("PTO_TBLREPLICAPROMO ");
            query.append("WHERE REGION_CARGA=? AND NOMBRE_ARCHIVO=? ");
            try {
                try {
                    conn = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                    stmt = conn.prepareStatement(query.toString());
                    stmt.setString(1, regionCarga);
                    stmt.setString(2, nombreArchivo);
                    resultSet = stmt.executeQuery();
                    if (resultSet.next()) {
                        idArchivo = resultSet.getInt("IDARCHIVO");
                    }
                }
                catch (Exception e) {
                    this.error.error((Object)("Error al verificar existencia de detalle de replicaPromoDeur, " + e.getMessage()));
                    if (conn != null) {
                        try {
                            conn.close();
                        }
                        catch (SQLException var10_9) {
                            // empty catch block
                        }
                    }
                    if (stmt != null) {
                        try {
                            stmt.close();
                        }
                        catch (SQLException var10_10) {
                            // empty catch block
                        }
                    }
                    if (resultSet == null) break block29;
                    try {
                        resultSet.close();
                    }
                    catch (SQLException var10_11) {}
                }
            }
            finally {
                if (conn != null) {
                    try {
                        conn.close();
                    }
                    catch (SQLException var10_15) {}
                }
                if (stmt != null) {
                    try {
                        stmt.close();
                    }
                    catch (SQLException var10_16) {}
                }
                if (resultSet != null) {
                    try {
                        resultSet.close();
                    }
                    catch (SQLException var10_17) {}
                }
            }
        }
        return idArchivo;
    }
}

