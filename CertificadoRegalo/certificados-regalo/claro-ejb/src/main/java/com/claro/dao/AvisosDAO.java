/*
 * Decompiled with CFR 0_102.
 * 
 * Could not load the following classes:
 *  com.claro.exception.CAException
 *  com.claro.transfer.AvisosTO
 *  com.claro.transfer.MensajeTO
 *  com.claro.util.Constantes
 *  com.claro.util.ServiceLocator
 *  org.apache.log4j.Logger
 */
package com.claro.dao;

import com.claro.exception.CAException;
import com.claro.transfer.AvisosTO;
import com.claro.transfer.MensajeTO;
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
import org.apache.log4j.Logger;

public class AvisosDAO {
    protected final Logger logger = Logger.getLogger((String)"loggerCirculoAzul");
    protected final Logger error = Logger.getLogger((String)"loggerCirculoAzulError");
    private String schema_database;

    public AvisosDAO() {
        try {
            this.schema_database = ServiceLocator.getInstance().getVariable(ServiceLocator.schema_database);
        }
        catch (Exception e) {
            this.error.error((Object)"AvisosDAO", (Throwable)e);
        }
    }

    public ArrayList<AvisosTO> historicoAvisos(String sFechaIni, String sFechaFin, String tipoAviso, String estatus) throws CAException {
        ArrayList<AvisosTO> historicoAvisosTO;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        Date dFechaIni = null;
        Date dFechaFin = null;
        StringBuffer query = new StringBuffer();
        historicoAvisosTO = new ArrayList<AvisosTO>();
        query.append(" SELECT IDAVISO,DESCRIPCION,FECHAALTA,IDUSUARIO,FECHA_INICIO, ");
        query.append(" FECHAFIN,IDUSUARIO_MOD, FECHA_MODIFICACION,TIPOAVISO,ESTATUS,TIPOMSG  ");
        query.append(" FROM  ").append(this.schema_database).append("PTO_CTLAVISOS");
        query.append(" WHERE ESTATUS = ? ");
        if (sFechaIni != null && sFechaFin != null) {
            query.append(" AND FECHAALTA BETWEEN ?  AND ?  ");
        }
        if (tipoAviso != null) {
            query.append(" AND TIPOAVISO = ? ");
        }
        query.append(" ORDER BY IDAVISO,FECHAALTA,IDUSUARIO,ESTATUS,TIPOAVISO DESC ");
        try {
            try {
                connection = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                statement = connection.prepareStatement(query.toString());
                if (estatus != null) {
                    statement.setString(1, estatus);
                } else {
                    statement.setString(1, "A");
                }
                if (sFechaIni != null && sFechaFin != null) {
                    sFechaFin = String.valueOf(sFechaFin) + " 23:59:59";
                    dFechaIni = Constantes.DATEFORMATMM_dd_YYYY.parse(sFechaIni);
                    dFechaFin = Constantes.DATEFORMATMM_dd_YYYY_HHmmss.parse(sFechaFin);
                }
                if (dFechaIni != null && dFechaFin != null) {
                    statement.setTimestamp(2, new Timestamp(dFechaIni.getTime()));
                    statement.setTimestamp(3, new Timestamp(dFechaFin.getTime()));
                }
                if (tipoAviso != null) {
                    statement.setString(4, tipoAviso);
                }
                resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    AvisosTO avisosTO = new AvisosTO();
                    avisosTO.setIdAviso(resultSet.getInt("IDAVISO"));
                    avisosTO.setIdUsuario(resultSet.getString("IDUSUARIO"));
                    avisosTO.setDescripcion(resultSet.getString("DESCRIPCION"));
                    avisosTO.setFechaAlta(resultSet.getTimestamp("FECHAALTA"));
                    avisosTO.setFechaActivacion(resultSet.getTimestamp("FECHA_INICIO"));
                    avisosTO.setFechaExpiracion(resultSet.getTimestamp("FECHAFIN"));
                    if (resultSet.getString("TIPOAVISO").equals("FP")) {
                        avisosTO.setTipoAviso("Falla en Puntos");
                    } else {
                        avisosTO.setTipoAviso("Aviso General");
                    }
                    if (resultSet.getString("ESTATUS").equals("A")) {
                        avisosTO.setEstatus("Activo");
                    } else {
                        avisosTO.setEstatus("Inactivo");
                    }
                    avisosTO.setFechaModificacion(resultSet.getTimestamp("FECHA_MODIFICACION"));
                    avisosTO.setIdUsuarioMod(resultSet.getString("IDUSUARIO_MOD"));
                    historicoAvisosTO.add(avisosTO);
                }
            }
            catch (SQLException e) {
                throw new CAException(-1, "SQLException.historicoAvisos", (Exception)e);
            }
            catch (NumberFormatException e) {
                throw new CAException(-1, "NumberFormatException.historicoAvisos", (Exception)e);
            }
            catch (Exception e) {
                throw new CAException(-1, "AvisosDAO.historicoAvisos", e);
            }
        }
        finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                    resultSet = null;
                }
                catch (Exception var14_20) {}
            }
            if (statement != null) {
                try {
                    statement.close();
                    statement = null;
                }
                catch (Exception var14_21) {}
            }
            if (connection != null) {
                try {
                    connection.close();
                    connection = null;
                }
                catch (Exception var14_22) {}
            }
        }
        return historicoAvisosTO;
    }

    public MensajeTO insertarAvisos(String sDescripcion, String sFechaFin, String sTipoAviso, String sUsuario, String sFechaInicio) throws CAException {
        MensajeTO mensajeTO;
        PreparedStatement statement = null;
        Connection connection = null;
        mensajeTO = new MensajeTO();
        Timestamp fechaAlta = new Timestamp(System.currentTimeMillis());
        Date dFechaIni = null;
        Date dFechaFin = null;
        int indice = 0;
        indice = this.maxIdAvisos();
        String queryInsert = "INSERT INTO " + this.schema_database + "PTO_CTLAVISOS (IDAVISO, DESCRIPCION, FECHAALTA, FECHAFIN, " + " TIPOAVISO, IDUSUARIO, FECHA_INICIO, IDUSUARIO_MOD, FECHA_MODIFICACION, ESTATUS, TIPOMSG )" + " VALUES (?,?,?,?,?,?,?,'',NULL,'A','C')";
        try {
            try {
                connection = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                statement = connection.prepareStatement(queryInsert.toString());
                dFechaIni = Constantes.DATEFORMATMM_dd_YYYY.parse(sFechaInicio);
                dFechaFin = Constantes.DATEFORMATMM_dd_YYYY.parse(sFechaFin);
                statement.setInt(1, indice);
                statement.setString(2, sDescripcion);
                statement.setTimestamp(3, fechaAlta);
                statement.setTimestamp(4, new Timestamp(dFechaFin.getTime()));
                statement.setString(5, sTipoAviso);
                statement.setString(6, sUsuario);
                statement.setTimestamp(7, new Timestamp(dFechaIni.getTime()));
                if (statement.executeUpdate() > 0) {
                    mensajeTO.agregaMensaje(0, "El registro se inserto exitosamente");
                } else {
                    mensajeTO.agregaMensaje(-1, "No se Inserto el Aviso");
                }
            }
            catch (SQLException e) {
                this.error.info((Object)"AvisosDAO.insertarAvisos.SQLException:", (Throwable)e);
                throw new CAException(-1, "AvisosDAO.insertaRedencion.SQLException[" + e.toString() + "]", (Exception)e);
            }
            catch (Exception e) {
                this.error.info((Object)"AvisosDAO.insertarAvisos.Exception:", (Throwable)e);
                throw new CAException(-1, "AvisosDAO.insertarAvisos.Error[" + e.toString() + "]", e);
            }
        }
        finally {
            if (statement != null) {
                try {
                    statement.close();
                    statement = null;
                }
                catch (Exception var16_19) {}
            }
            if (connection != null) {
                try {
                    connection.close();
                    connection = null;
                }
                catch (Exception var16_20) {}
            }
        }
        return mensajeTO;
    }

    public MensajeTO eliminarAvisos(String sUsuarioMod, int idAviso) throws CAException {
        MensajeTO mensajeTO;
        PreparedStatement statement = null;
        Connection connection = null;
        mensajeTO = new MensajeTO();
        Timestamp fechaMod = new Timestamp(System.currentTimeMillis());
        String query = "UPDATE " + this.schema_database + "PTO_CTLAVISOS SET ESTATUS =  'I' , IDUSUARIO_MOD=?, FECHA_MODIFICACION=? " + " WHERE IDAVISO = ? ";
        try {
            try {
                connection = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                statement = connection.prepareStatement(query);
                statement.setString(1, sUsuarioMod);
                statement.setTimestamp(2, fechaMod);
                statement.setInt(3, idAviso);
                if (statement.executeUpdate() > 0) {
                    mensajeTO.agregaMensaje(0, "El registro se elimino exitosamente");
                } else {
                    mensajeTO.agregaMensaje(1, "No se realizo la eliminacion del aviso");
                }
            }
            catch (SQLException e) {
                if (connection != null) {
                    try {
                        connection.rollback();
                    }
                    catch (Exception var9_10) {
                        // empty catch block
                    }
                }
                this.error.info((Object)"SQLException.eliminarAvisos:", (Throwable)e);
                throw new CAException(-1, "[eliminarAvisos] SQLError: " + e.toString() + "Actualizar Inf", (Exception)e);
            }
            catch (Exception e) {
                this.error.info((Object)"Exception.eliminarAvisos:", (Throwable)e);
                throw new CAException(-1, "[eliminarAvisos] Error: " + e.toString() + "Actualizar Inf", e);
            }
        }
        finally {
            if (connection == null) {
                try {
                    connection.close();
                    connection = null;
                }
                catch (Exception var11_13) {}
            }
        }
        return mensajeTO;
    }

    public ArrayList<AvisosTO> marquesinaAvisos(long fecha) throws CAException {
        ArrayList<AvisosTO> marquesina;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        StringBuffer query = new StringBuffer();
        marquesina = new ArrayList<AvisosTO>();
        query.append(" SELECT DESCRIPCION,TIPOAVISO ");
        query.append(" FROM  ").append(this.schema_database).append("PTO_CTLAVISOS");
        query.append(" WHERE ESTATUS = ? AND FECHAFIN >= ? ");
        query.append(" ORDER BY FECHAALTA ");
        try {
            try {
                connection = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                statement = connection.prepareStatement(query.toString());
                statement.setString(1, "A");
                statement.setTimestamp(2, new Timestamp(fecha));
                resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    AvisosTO avisosTO = new AvisosTO();
                    avisosTO.setDescripcion(resultSet.getString("DESCRIPCION"));
                    avisosTO.setTipoAviso(resultSet.getString("TIPOAVISO"));
                    marquesina.add(avisosTO);
                }
            }
            catch (SQLException e) {
                throw new CAException(-1, "SQLException.marquesinaAvisos", (Exception)e);
            }
            catch (NumberFormatException e) {
                throw new CAException(-1, "NumberFormatException.marquesinaAvisos", (Exception)e);
            }
            catch (Exception e) {
                throw new CAException(-1, "AvisosDAO.marquesinaAvisos", e);
            }
        }
        finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                    resultSet = null;
                }
                catch (Exception var10_15) {}
            }
            if (statement != null) {
                try {
                    statement.close();
                    statement = null;
                }
                catch (Exception var10_16) {}
            }
            if (connection != null) {
                try {
                    connection.close();
                    connection = null;
                }
                catch (Exception var10_17) {}
            }
        }
        return marquesina;
    }

    public int maxIdAvisos() throws CAException {
        int idnum;
        PreparedStatement statement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        String query = " SELECT MAX(IDAVISO) FROM " + this.schema_database + "PTO_CTLAVISOS ";
        try {
            try {
                connection = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                statement = connection.prepareStatement(query.toString());
                resultSet = statement.executeQuery();
                idnum = resultSet.next() ? resultSet.getInt(1) + 1 : 0;
            }
            catch (SQLException e) {
                throw new CAException(-1, "SQLException.maxIdAvisos", (Exception)e);
            }
            catch (NumberFormatException e) {
                throw new CAException(-1, "NumberFormatException.maxIdAvisos", (Exception)e);
            }
            catch (Exception e) {
                throw new CAException(-1, "AvisosDAO.maxIdAvisos", e);
            }
        }
        finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                    resultSet = null;
                }
                catch (Exception var8_13) {}
            }
            if (statement != null) {
                try {
                    statement.close();
                    statement = null;
                }
                catch (Exception var8_14) {}
            }
            if (connection != null) {
                try {
                    connection.close();
                    connection = null;
                }
                catch (Exception var8_15) {}
            }
        }
        return idnum;
    }
}

