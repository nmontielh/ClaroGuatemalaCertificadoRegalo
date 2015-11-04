/*
 * Decompiled with CFR 0_102.
 * 
 * Could not load the following classes:
 *  com.claro.exception.CAException
 *  com.claro.transfer.PerfilTO
 *  com.claro.transfer.PrivilegioTO
 *  com.claro.util.Constantes
 *  com.claro.util.ServiceLocator
 *  org.apache.log4j.Logger
 */
package com.claro.dao;

import com.claro.dao.AsignacionDAO;
import com.claro.exception.CAException;
import com.claro.transfer.PerfilTO;
import com.claro.transfer.PrivilegioTO;
import com.claro.util.Constantes;
import com.claro.util.ServiceLocator;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;

public class SeguridadDAO {
    protected final Logger logger = Logger.getLogger((String)"loggerCirculoAzul");
    protected final Logger error = Logger.getLogger((String)"loggerCirculoAzulError");
    private String schema_database;

    public SeguridadDAO() {
        try {
            this.schema_database = ServiceLocator.getInstance().getVariable(ServiceLocator.schema_database);
        }
        catch (Exception e) {
            this.error.error((Object)"SeguridadDAO", (Throwable)e);
        }
    }

    public PerfilTO getPrivilegiosPerfil(String idPerfil) throws CAException {
        PerfilTO perfilTO;
        perfilTO = new PerfilTO();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rset = null;
        StringBuffer query = new StringBuffer();
        try {
            try {
                this.logger.info((Object)("consultaPrivilegios|Inicio Proceso|" + Constantes.DATEFORMTALog.format(new Date()) + "|" + System.currentTimeMillis()));
                query.append("SELECT PC.IDPROCESO, PC.NOMBREPROCESO, PC.DESCRIPCION, PC.TIPO ");
                query.append("FROM ").append(this.schema_database).append("PTO_CTLPERFIL_CA PE, ");
                query.append(this.schema_database).append("PTO_CTLPROCESOS PC,").append(this.schema_database).append("PTO_CTLPERFILN PF ");
                query.append("where PE.IDPERFILN=? and PF.IDPERFILN = PE.IDPERFILN and ");
                query.append("PE.IDPROCESO = PC.IDPROCESO and PC.IDPROCESO!=0 and PE.ESTATUS = 'A' and PC.TIPO = 'C' ");
                query.append("order by PC.IDPROCESO ");
                conn = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                stmt = conn.prepareStatement(query.toString());
                stmt.setString(1, idPerfil);
                rset = stmt.executeQuery();
                Hashtable<String, PrivilegioTO> privilegiosCa = new Hashtable<String, PrivilegioTO>();
                while (rset.next()) {
                    PrivilegioTO privilegioTO = new PrivilegioTO();
                    privilegioTO.setIdProceso(rset.getInt("IDPROCESO"));
                    privilegioTO.setNombre(rset.getString("NOMBREPROCESO"));
                    privilegioTO.setDescripcion(rset.getString("DESCRIPCION"));
                    privilegioTO.setTipo(rset.getString("TIPO"));
                    privilegiosCa.put(Integer.toString(privilegioTO.getIdProceso()), privilegioTO);
                }
                perfilTO.setPrivilegiosCa(privilegiosCa);
                if (rset != null) {
                    rset.close();
                    rset = null;
                }
                if (stmt != null) {
                    stmt.close();
                    rset = null;
                }
                query = new StringBuffer();
                query.append("SELECT PC.IDPROCESO, PC.NOMBREPROCESO, PC.DESCRIPCION, PC.TIPO ");
                query.append("FROM ").append(this.schema_database).append("PTO_CTLPERFIL_CA PE, ");
                query.append(this.schema_database).append("PTO_CTLPROCESOS PC,").append(this.schema_database).append("PTO_CTLPERFILN PF ");
                query.append("where PE.IDPERFILN=? and PF.IDPERFILN = PE.IDPERFILN and ");
                query.append("PE.IDPROCESO = PC.IDPROCESO and PC.IDPROCESO!=0 and PE.ESTATUS = 'A' and PC.TIPO = 'E' ");
                query.append("order by PC.IDPROCESO ");
                stmt = conn.prepareStatement(query.toString());
                stmt.setString(1, idPerfil);
                rset = stmt.executeQuery();
                Hashtable<String, PrivilegioTO> privilegiosEcac = new Hashtable<String, PrivilegioTO>();
                while (rset.next()) {
                    PrivilegioTO privilegioTO = new PrivilegioTO();
                    privilegioTO.setIdProceso(rset.getInt("IDPROCESO"));
                    privilegioTO.setNombre(rset.getString("NOMBREPROCESO"));
                    privilegioTO.setDescripcion(rset.getString("DESCRIPCION"));
                    privilegioTO.setTipo(rset.getString("TIPO"));
                    privilegiosEcac.put(Integer.toString(privilegioTO.getIdProceso()), privilegioTO);
                }
                perfilTO.setPrivilegiosEcac(privilegiosEcac);
                this.logger.info((Object)("consultaPrivilegios|Fin Proceso|" + Constantes.DATEFORMTALog.format(new Date()) + "|" + System.currentTimeMillis()));
            }
            catch (SQLException e) {
                this.error.info((Object)"SQLException.consultaPrivilegios:", (Throwable)e);
                throw new CAException(-1, "SQLException.consulta[" + e.toString() + "]", (Exception)e);
            }
            catch (Exception e) {
                this.error.info((Object)"Exception.consultaPrivilegios:", (Throwable)e);
                throw new CAException(-1, "Exception.consulta[" + e.toString() + "]", e);
            }
        }
        finally {
            if (rset != null) {
                try {
                    rset.close();
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
        return perfilTO;
    }

    public Map<String, PrivilegioTO> getprivilegiosCa() throws CAException {
        HashMap<String, PrivilegioTO> privilegiosCa;
        block29 : {
            Connection connection = null;
            Statement statement = null;
            ResultSet resultSet = null;
            privilegiosCa = null;
            try {
                try {
                    StringBuffer sQuery = new StringBuffer();
                    sQuery.append("SELECT IDPROCESO,NOMBREPROCESO,DESCRIPCION FROM ");
                    sQuery.append(this.schema_database).append("PTO_CTLPROCESOS ");
                    sQuery.append("order by IDPROCESO ");
                    connection = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                    statement = connection.createStatement();
                    resultSet = statement.executeQuery(sQuery.toString());
                    privilegiosCa = new HashMap<String, PrivilegioTO>();
                    while (resultSet.next()) {
                        PrivilegioTO privilegioTO = new PrivilegioTO();
                        privilegioTO.setIdProceso(resultSet.getInt(1));
                        privilegioTO.setNombre(resultSet.getString(2));
                        privilegioTO.setDescripcion(resultSet.getString(3));
                        privilegiosCa.put(String.valueOf(privilegioTO.getIdProceso()), privilegioTO);
                    }
                }
                catch (Exception e) {
                    this.error.error((Object)"CatalogoDAO.obtienePrivilegios:", (Throwable)e);
                    if (resultSet != null) {
                        try {
                            resultSet.close();
                            resultSet = null;
                        }
                        catch (Exception var8_8) {
                            // empty catch block
                        }
                    }
                    if (statement != null) {
                        try {
                            statement.close();
                            statement = null;
                        }
                        catch (Exception var8_9) {
                            // empty catch block
                        }
                    }
                    if (connection == null) break block29;
                    try {
                        connection.close();
                        connection = null;
                    }
                    catch (Exception var8_10) {}
                }
            }
            finally {
                if (resultSet != null) {
                    try {
                        resultSet.close();
                        resultSet = null;
                    }
                    catch (Exception var8_14) {}
                }
                if (statement != null) {
                    try {
                        statement.close();
                        statement = null;
                    }
                    catch (Exception var8_15) {}
                }
                if (connection != null) {
                    try {
                        connection.close();
                        connection = null;
                    }
                    catch (Exception var8_16) {}
                }
            }
        }
        return privilegiosCa;
    }

    public void actualizaPrivilegios(int idPerfil, List<PrivilegioTO> privilegios, String numEmpleado) throws CAException {
        this.actualizaPrivilegios(idPerfil, privilegios, numEmpleado, true);
    }

    public boolean actualizaPrivilegiosAsignacion(int idPerfil, int numMaxPtos, String numEmpleado) throws CAException {
        AsignacionDAO asignacion = new AsignacionDAO();
        int numPerfiles = asignacion.obtienePerfilesAsignacion(idPerfil, 0).size();
        if (numPerfiles == 0) {
            PrivilegioTO privilegioTO = null;
            ArrayList<PrivilegioTO> privilegios = new ArrayList<PrivilegioTO>(0);
            privilegioTO = new PrivilegioTO();
            privilegioTO.setIdProceso(25);
            privilegios.add(privilegioTO);
            privilegioTO = new PrivilegioTO();
            privilegioTO.setIdProceso(71);
            privilegios.add(privilegioTO);
            this.actualizaPrivilegios(idPerfil, privilegios, numEmpleado, false);
            this.insertaPerfilAsignacion(idPerfil, numMaxPtos);
            return true;
        }
        return false;
    }

    private void actualizaPrivilegios(int idPerfil, List<PrivilegioTO> privilegios, String numEmpleado, boolean esModSeguridad) throws CAException {
        block32 : {
            Connection conn = null;
            PreparedStatement stmt = null;
            boolean procesoAsignacion = false;
            StringBuffer query = new StringBuffer();
            query.append("insert into ").append(this.schema_database).append("PTO_CTLPERFIL_CA ");
            query.append("values(?,0,?,'A')");
            try {
                try {
                    conn = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                    conn.setAutoCommit(false);
                    try {
                        StringBuffer delete = new StringBuffer();
                        delete.append("delete from ").append(this.schema_database).append("PTO_CTLPERFIL_CA ");
                        delete.append("where IDPERFILN = ?");
                        if (!esModSeguridad) {
                            delete.append("and IDPROCESO IN (?,?) ");
                        }
                        stmt = conn.prepareStatement(delete.toString());
                        stmt.setInt(1, idPerfil);
                        if (!esModSeguridad) {
                            stmt.setInt(2, 25);
                            stmt.setInt(3, 71);
                        }
                        stmt.executeUpdate();
                        if (stmt != null) {
                            stmt.close();
                            stmt = null;
                        }
                        for (PrivilegioTO privilegio : privilegios) {
                            if (privilegio.getIdProceso() == 71) {
                                procesoAsignacion = true;
                            }
                            stmt = conn.prepareStatement(query.toString());
                            stmt.setInt(1, idPerfil);
                            stmt.setInt(2, privilegio.getIdProceso());
                            stmt.executeUpdate();
                            if (stmt == null) continue;
                            stmt.close();
                            stmt = null;
                        }
                        if (!procesoAsignacion) {
                            StringBuffer deletePerfilAsig = new StringBuffer();
                            deletePerfilAsig.append("delete from ").append(this.schema_database).append("PTO_CTLPERFILESASIGNACION ");
                            deletePerfilAsig.append("where IDPERFILN = ?");
                            stmt = conn.prepareStatement(deletePerfilAsig.toString());
                            stmt.setInt(1, idPerfil);
                            stmt.executeUpdate();
                            if (stmt != null) {
                                stmt.close();
                                stmt = null;
                            }
                        }
                    }
                    catch (SQLException e) {
                        conn.rollback();
                        this.error.info((Object)"Exception.actualizaPrivilegios:", (Throwable)e);
                    }
                    if (stmt != null) {
                        stmt.close();
                        stmt = null;
                    }
                    query = new StringBuffer();
                    query.append("INSERT INTO ").append(this.schema_database).append("PTO_TBLHISTOCAMBIOPERFPVL ");
                    query.append("VALUES(?,?,?)");
                    stmt = conn.prepareStatement(query.toString());
                    stmt.setString(1, numEmpleado);
                    stmt.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
                    stmt.setInt(3, idPerfil);
                    stmt.execute();
                    conn.commit();
                    conn.setAutoCommit(true);
                }
                catch (Exception e) {
                    this.error.info((Object)"Exception.actualizaPrivilegios:", (Throwable)e);
                    if (stmt != null) {
                        try {
                            stmt.close();
                        }
                        catch (SQLException var13_14) {
                            // empty catch block
                        }
                    }
                    if (conn == null) break block32;
                    try {
                        conn.close();
                    }
                    catch (SQLException var13_15) {}
                }
            }
            finally {
                if (stmt != null) {
                    try {
                        stmt.close();
                    }
                    catch (SQLException var13_18) {}
                }
                if (conn != null) {
                    try {
                        conn.close();
                    }
                    catch (SQLException var13_19) {}
                }
            }
        }
    }

    private void insertaPerfilAsignacion(int idPerfil, int numPtos) throws CAException {
        Connection conn = null;
        PreparedStatement stmt = null;
        StringBuffer query = new StringBuffer();
        query.append("INSERT INTO ").append(this.schema_database).append("PTO_CTLPERFILESASIGNACION ");
        query.append("VALUES(?,?)");
        try {
            try {
                conn = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                stmt = conn.prepareStatement(query.toString());
                stmt.setInt(1, idPerfil);
                stmt.setInt(2, numPtos);
                stmt.executeUpdate();
            }
            catch (Exception e) {
                this.error.info((Object)"SeguridadDAO.insertaPerfilAsignacion.Exception:", (Throwable)e);
                throw new CAException(-1, "SeguridadDAO.insertaPerfilAsignacion.Error[" + e.toString() + "]", e);
            }
        }
        finally {
            if (stmt != null) {
                try {
                    stmt.close();
                }
                catch (SQLException var8_10) {}
            }
            if (conn != null) {
                try {
                    conn.close();
                }
                catch (SQLException var8_11) {}
            }
        }
    }

    public boolean actualizaPerfilAsignacion(int idPerfil, int numMaxPtos, String numEmpleado) throws CAException {
        return this.actualizaPerfilAsignacion(idPerfil, numMaxPtos);
    }

    private boolean actualizaPerfilAsignacion(int idPerfil, int numPtos) throws CAException {
        boolean seActualizo;
        Connection conn = null;
        PreparedStatement stmt = null;
        seActualizo = false;
        StringBuffer query = new StringBuffer();
        query.append("UPDATE ").append(this.schema_database).append("PTO_CTLPERFILESASIGNACION ");
        query.append("SET MAX_PUNTOSASIGNAR = ? ");
        query.append("WHERE IDPERFILN = ? ");
        try {
            try {
                conn = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                stmt = conn.prepareStatement(query.toString());
                stmt.setInt(1, numPtos);
                stmt.setInt(2, idPerfil);
                if (stmt.executeUpdate() > 0) {
                    seActualizo = true;
                }
            }
            catch (Exception e) {
                this.error.info((Object)"SeguridadDAO.actualizaPerfilAsignacion.Exception:", (Throwable)e);
                throw new CAException(-1, "SeguridadDAO.actualizaPerfilAsignacion.Error[" + e.toString() + "]", e);
            }
        }
        finally {
            if (stmt != null) {
                try {
                    stmt.close();
                }
                catch (SQLException var9_11) {}
            }
            if (conn != null) {
                try {
                    conn.close();
                }
                catch (SQLException var9_12) {}
            }
        }
        return seActualizo;
    }

    public boolean eliminaPerfilAsignacion(int idPerfil) throws CAException {
        boolean seElimino;
        Connection conn = null;
        PreparedStatement stmt = null;
        seElimino = false;
        StringBuffer query = new StringBuffer();
        query.append("DELETE ").append(this.schema_database).append("PTO_CTLPERFILESASIGNACION ");
        query.append("WHERE IDPERFILN = ? ");
        try {
            try {
                conn = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                stmt = conn.prepareStatement(query.toString());
                stmt.setInt(1, idPerfil);
                if (stmt.executeUpdate() > 0) {
                    seElimino = true;
                }
            }
            catch (Exception e) {
                this.error.info((Object)"SeguridadDAO.actualizaPerfilAsignacion.Exception:", (Throwable)e);
                throw new CAException(-1, "SeguridadDAO.actualizaPerfilAsignacion.Error[" + e.toString() + "]", e);
            }
        }
        finally {
            if (stmt != null) {
                try {
                    stmt.close();
                }
                catch (SQLException var8_10) {}
            }
            if (conn != null) {
                try {
                    conn.close();
                }
                catch (SQLException var8_11) {}
            }
        }
        return seElimino;
    }
}

