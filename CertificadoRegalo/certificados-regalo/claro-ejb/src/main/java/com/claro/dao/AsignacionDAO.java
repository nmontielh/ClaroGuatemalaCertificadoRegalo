/*
 * Decompiled with CFR 0_102.
 * 
 * Could not load the following classes:
 *  com.claro.exception.CAException
 *  com.claro.transfer.FolioLiberacionTO
 *  com.claro.transfer.MensajeTO
 *  com.claro.transfer.MotivoTO
 *  com.claro.transfer.ParametrosTO
 *  com.claro.transfer.PerfilTO
 *  com.claro.transfer.PuntosTO
 *  com.claro.transfer.TelefonoTO
 *  com.claro.util.Constantes
 *  com.claro.util.ServiceLocator
 *  com.claro.util.Utils
 *  org.apache.log4j.Logger
 */
package com.claro.dao;

import com.claro.dao.ConsultasDAO;
import com.claro.dao.PuntosDAO;
import com.claro.exception.CAException;
import com.claro.transfer.FolioLiberacionTO;
import com.claro.transfer.MensajeTO;
import com.claro.transfer.MotivoTO;
import com.claro.transfer.ParametrosTO;
import com.claro.transfer.PerfilTO;
import com.claro.transfer.PuntosTO;
import com.claro.transfer.TelefonoTO;
import com.claro.util.Constantes;
import com.claro.util.ServiceLocator;
import com.claro.util.Utils;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

public class AsignacionDAO {
    protected final Logger logger = Logger.getLogger((String)"loggerCirculoAzul");
    protected final Logger error = Logger.getLogger((String)"loggerCirculoAzulError");
    private String schema_database;

    public AsignacionDAO() {
        try {
            this.schema_database = ServiceLocator.getInstance().getVariable(ServiceLocator.schema_database);
        }
        catch (Exception e) {
            this.error.error((Object)"AsignacionDAO", (Throwable)e);
        }
    }

    public MensajeTO procesaAsignacion(ParametrosTO parametrosTO, String usuario, int accion, int ptsAsignar, String comentario, String motivoAsig) throws CAException {
        MensajeTO mensajeTO;
        Connection connection = null;
        mensajeTO = new MensajeTO();
        int inPuntos = ptsAsignar;
        int ptsExcedentes = 0;
        int[] puntosEliminar = new int[7];
        try {
            connection = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
            connection.setAutoCommit(false);
            long fechaTransaccion = System.currentTimeMillis();
            PuntosDAO puntosDAO = new PuntosDAO();
            ConsultasDAO consultasDAO = new ConsultasDAO();
            PuntosTO puntosTO = consultasDAO.obtienePuntos(parametrosTO.getCuenta(), parametrosTO.getSecuencia());
            if (puntosTO.getIdMensaje() != 0) {
                MensajeTO mensajeTO2 = puntosTO.obtieneMensajeTO();
                return mensajeTO2;
            }
            try {
                if (accion == Constantes.ASIGNAR_PUNTOS) {
                    String referencia = "ASIGNA: " + usuario + " MOTIVO: " + motivoAsig + " COMENT: " + comentario;
                    if (referencia.length() > 100) {
                        referencia = referencia.substring(0, 100);
                    }
                    mensajeTO = puntosDAO.insertaDetalle(connection, fechaTransaccion, referencia, 15, ptsAsignar, null, parametrosTO.getCuenta(), parametrosTO.getSecuencia(), parametrosTO.getTelefono(), usuario);
                    referencia = "CIR - ASIGNA " + ptsAsignar + " PTOS. REALIZO: " + usuario + " " + comentario;
                    if (mensajeTO.getIdMensaje() == 0) {
                        mensajeTO = puntosDAO.insertaComentarioTMP(connection, parametrosTO.getRegion(), parametrosTO.getCuenta(), usuario, fechaTransaccion, referencia);
                    }
                    if (mensajeTO.getIdMensaje() == 0) {
                        ptsExcedentes = puntosTO.getPtsExcedentes() + ptsAsignar;
                    }
                    mensajeTO = this.actualizaTotalesAsignacion(connection, ptsExcedentes, parametrosTO.getCuenta(), parametrosTO.getSecuencia());
                } else if (accion == Constantes.ELIMINAR_PUNTOS) {
                    if (puntosTO.getPtsTotales() < ptsAsignar) {
                        mensajeTO.agregaMensaje(-1, "La linea no cuenta con los suficientes puntos para eliminar.");
                    } else {
                        puntosEliminar[0] = puntosTO.getPtsPorVencer();
                        puntosEliminar[1] = puntosTO.getPtsPorVencer1();
                        puntosEliminar[2] = puntosTO.getPtsPorVencer2();
                        puntosEliminar[3] = puntosTO.getPtsPromocion();
                        puntosEliminar[4] = puntosTO.getPtsAntiguedad();
                        puntosEliminar[5] = puntosTO.getPtsExcedentes();
                        puntosEliminar[6] = puntosTO.getPtsRenta();
                        for (int i = 0; i < puntosEliminar.length; ++i) {
                            if (puntosEliminar[i] <= 0) continue;
                            if (puntosEliminar[i] < inPuntos) {
                                inPuntos-=puntosEliminar[i];
                                puntosEliminar[i] = 0;
                                continue;
                            }
                            puntosEliminar[i] = puntosEliminar[i] - inPuntos;
                            inPuntos = 0;
                            break;
                        }
                        String referencia = "ELIMINA: " + usuario + " COMENT: " + comentario;
                        int ptsAsignarneg = - ptsAsignar;
                        if (referencia.length() > 100) {
                            referencia = referencia.substring(0, 100);
                        }
                        mensajeTO = puntosDAO.insertaDetalle(connection, fechaTransaccion, referencia, 18, ptsAsignarneg, null, parametrosTO.getCuenta(), parametrosTO.getSecuencia(), parametrosTO.getTelefono(), usuario);
                        referencia = "CIR - ELIMINA " + ptsAsignar + " PTOS. REALIZO: " + usuario + " " + comentario;
                        if (mensajeTO.getIdMensaje() == 0) {
                            mensajeTO = puntosDAO.insertaComentarioTMP(connection, parametrosTO.getRegion(), parametrosTO.getCuenta(), usuario, fechaTransaccion, referencia);
                        }
                        if (mensajeTO.getIdMensaje() == 0) {
                            mensajeTO = this.actualizaTotalesEliminacion(connection, puntosEliminar, parametrosTO.getCuenta(), parametrosTO.getSecuencia());
                        }
                    }
                }
                if (mensajeTO.getIdMensaje() == 0) {
                    connection.commit();
                } else {
                    connection.rollback();
                }
            }
            catch (SQLException e) {
                if (connection != null) {
                    try {
                        connection.rollback();
                    }
                    catch (Exception var13_26) {
                        // empty catch block
                    }
                }
                this.error.info((Object)"SQLException.procesaAsignacion:", (Throwable)e);
                throw new CAException(-1, "[procesaAsignacion] SQLError: " + e.toString() + "Actualizar Inf", (Exception)e);
            }
            catch (Exception e) {
                if (connection != null) {
                    try {
                        connection.rollback();
                    }
                    catch (Exception var13_27) {
                        // empty catch block
                    }
                }
                this.error.info((Object)"Exception.procesaAsignacion:", (Throwable)e);
                throw new CAException(-1, "[procesaAsignacion] Error: " + e.toString() + "Actualizar Inf", e);
            }
        }catch(Exception e){
        	
        }
        finally {
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                    connection.close();
                    connection = null;
                }
                catch (Exception var21_19) {}
            }
        }
        return mensajeTO;
    }

    public MensajeTO actualizaTotalesAsignacion(Connection connection, int ptsExcedentes, String sCuenta, int iSecuencia) throws CAException {
        MensajeTO mensajeTO;
        Connection lConn = null;
        mensajeTO = new MensajeTO();
        PreparedStatement statement = null;
        try {
            try {
                String UpdateTotales = "UPDATE " + this.schema_database + "PTO_TBLTOTALES SET PuntosExcedentes =  ? " + " WHERE Cuenta = ? AND Secuencia = ? ";
                lConn = connection != null ? connection : ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                statement = lConn.prepareStatement(UpdateTotales);
                statement.setInt(1, ptsExcedentes);
                statement.setString(2, sCuenta);
                statement.setInt(3, iSecuencia);
                if (statement.executeUpdate() > 0) {
                    mensajeTO.agregaMensaje(0, "Proceso Exitoso.");
                } else {
                    mensajeTO.agregaMensaje(1, "No se realizo la actualizacion de los excedentes");
                }
            }
            catch (SQLException e) {
                if (connection != null) {
                    try {
                        connection.rollback();
                    }
                    catch (Exception var9_11) {
                        // empty catch block
                    }
                }
                this.error.info((Object)"SQLException.actualizaTotalesAsignacion:", (Throwable)e);
                throw new CAException(-1, "[actualizaTotalesAsignacion] SQLError: " + e.toString() + "Actualizar Inf", (Exception)e);
            }
            catch (Exception e) {
                this.error.info((Object)"Exception.actualizaTotalesAsignacion:", (Throwable)e);
                throw new CAException(-1, "[actualizaTotalesAsignacion] Error: " + e.toString() + "Actualizar Inf", e);
            }
        }
        finally {
            if (lConn != null && connection == null) {
                try {
                    lConn.close();
                    lConn = null;
                }
                catch (Exception var11_14) {}
            }
        }
        return mensajeTO;
    }

    private MensajeTO actualizaTotalesEliminacion(Connection connection, int[] puntosEliminar, String sCuenta, int iSecuencia) throws CAException {
        MensajeTO mensajeTO;
        Connection lConn = null;
        PreparedStatement statement = null;
        mensajeTO = null;
        try {
            try {
                lConn = connection == null ? ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul) : connection;
                StringBuffer UpdateTotales = new StringBuffer();
                UpdateTotales.append(" UPDATE ").append(this.schema_database).append("PTO_TBLTOTALES ");
                UpdateTotales.append(" SET  PuntosAcadDisp = ?");
                UpdateTotales.append(", PuntosAcad = ? ");
                UpdateTotales.append(", PuntosAcad1 = ? ");
                UpdateTotales.append(", PuntosAcad2 = ? ");
                UpdateTotales.append(", PuntosPromocion  = ? ");
                UpdateTotales.append(", PuntosAntiguedad = ? ");
                UpdateTotales.append(", PuntosExcedentes = ? ");
                UpdateTotales.append(", PuntosRenta = ? ");
                if (puntosEliminar[0] == 0) {
                    UpdateTotales.append(", FechaCad = null");
                }
                if (puntosEliminar[1] == 0) {
                    UpdateTotales.append(", FechaCad1 = null");
                }
                if (puntosEliminar[2] == 0) {
                    UpdateTotales.append(", FechaCad2 = null");
                }
                UpdateTotales.append(" WHERE  Cuenta = ? ");
                UpdateTotales.append(" AND  Secuencia = ?");
                statement = lConn.prepareStatement(UpdateTotales.toString());
                statement.setInt(1, puntosEliminar[0]);
                statement.setInt(2, puntosEliminar[0]);
                statement.setInt(3, puntosEliminar[1]);
                statement.setInt(4, puntosEliminar[2]);
                statement.setInt(5, puntosEliminar[3]);
                statement.setInt(6, puntosEliminar[4]);
                statement.setInt(7, puntosEliminar[5]);
                statement.setInt(8, puntosEliminar[6]);
                statement.setString(9, sCuenta);
                statement.setInt(10, iSecuencia);
                mensajeTO = new MensajeTO();
                if (statement.executeUpdate() > 0) {
                    mensajeTO.agregaMensaje(0, "Proceso Exitoso");
                } else {
                    mensajeTO.agregaMensaje(-1, "No se realizo la actualizacion de los excedentes");
                }
            }
            catch (SQLException e) {
                this.error.info((Object)"Asignacion.actualizaTotalesEliminacion.SQLException:", (Throwable)e);
                throw new CAException(-1, "Asignacion.actualizaTotalesEliminacion.SQLException[" + e.toString() + "]", (Exception)e);
            }
            catch (Exception e) {
                this.error.info((Object)"Asignacion.actualizaTotalesEliminacion.Exception:", (Throwable)e);
                throw new CAException(-1, "Asignacion.actualizaTotalesEliminacion.SQLException[" + e.toString() + "]", e);
            }
        }
        finally {
            if (statement != null) {
                try {
                    statement.close();
                    statement = null;
                }
                catch (Exception var10_14) {}
            }
            if (connection == null && lConn != null) {
                try {
                    lConn.close();
                    lConn = null;
                }
                catch (Exception var10_15) {}
            }
        }
        return mensajeTO;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     */
    public FolioLiberacionTO asignaPorAntiguedad(ParametrosTO parametrosTO, String lFecAltaM2K, String comentario, String usuario) throws CAException {
        MensajeTO mensajeTO = null;
        FolioLiberacionTO folioLiberacionTO = null;
        Connection connection = null;
        PreparedStatement statement = null;
        PreparedStatement statementTot = null;
        ResultSet resultSet = null;
        ResultSet resultSetTot = null;
        PuntosDAO puntosDAO = new PuntosDAO();
        long fechaTransaccion = System.currentTimeMillis();
        StringBuffer sQuery = new StringBuffer();
        sQuery.append(" SELECT FECHAOPERACION, IDUSUARIO, REFERENCIA ");
        sQuery.append("  FROM  ").append(this.schema_database).append("PTO_TBLMSTRDETALLE ");
        sQuery.append(" WHERE CUENTA = ? AND SECUENCIA = ? ");
        sQuery.append(" AND IDMOVTO = ?  AND IDUSUARIO <> 'VIBPT01'");
        try {
            mensajeTO = new MensajeTO();
            folioLiberacionTO = new FolioLiberacionTO();
            connection = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
            statement = connection.prepareStatement(sQuery.toString());
            statement.setString(1, parametrosTO.getCuenta());
            statement.setInt(2, parametrosTO.getSecuencia());
            statement.setInt(3, 24);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                folioLiberacionTO.agregaMensaje(-1, "Para esa cuenta ya hubo una asignacion de puntos por cambio de region.\tConsultela en el detalle de movimientos.");
                FolioLiberacionTO folioLiberacionTO2 = folioLiberacionTO;
                return folioLiberacionTO2;
            }
            if (!Utils.validaFechaM2K((String)lFecAltaM2K)) {
                folioLiberacionTO.agregaMensaje(-1, "Error al consultar la fecha de alta en M2K. Intente otra vez.");
                FolioLiberacionTO folioLiberacionTO3 = folioLiberacionTO;
                return folioLiberacionTO3;
            }
            mensajeTO = this.validaFechaAnt(connection, parametrosTO.getCuenta(), parametrosTO.getSecuencia(), lFecAltaM2K);
            if (mensajeTO.getIdMensaje() != 0) {
                folioLiberacionTO.agregaMensaje(-1, mensajeTO.getMensaje());
                FolioLiberacionTO folioLiberacionTO4 = folioLiberacionTO;
                return folioLiberacionTO4;
            }
            int nPtsAsignar = Utils.calculaPuntos((String)lFecAltaM2K);
            if (nPtsAsignar == -1) {
                folioLiberacionTO.agregaMensaje(-1, "Ocurrio un error durante el calculo de puntos a asignar por antig\u00fcedad.");
                FolioLiberacionTO folioLiberacionTO5 = folioLiberacionTO;
                return folioLiberacionTO5;
            }
            StringBuffer sQueryTot = new StringBuffer();
            sQueryTot.append(" SELECT TOTAJUSTES ");
            sQueryTot.append("  FROM  ").append(this.schema_database).append("PTO_TBLMSTRDETALLE ");
            sQueryTot.append(" WHERE  CUENTA = ? AND SECUENCIA = ? AND IDMOVTO = ? ");
            sQueryTot.append(" AND IDUSUARIO = ? AND REFERENCIA =? ");
            statementTot = connection.prepareStatement(sQueryTot.toString());
            statementTot.setString(1, parametrosTO.getCuenta());
            statementTot.setInt(2, parametrosTO.getSecuencia());
            statementTot.setInt(3, 24);
            statementTot.setString(4, "VIBPT01");
            statementTot.setString(5, "Asignacion de puntos por antiguedad - CARGA INICIAL");
            resultSetTot = statementTot.executeQuery();
            if (resultSetTot.next()) {
                nPtsAsignar-=resultSetTot.getInt(1);
            }
            if (nPtsAsignar == 0) {
                folioLiberacionTO.agregaMensaje(-1, "Ya le fue asignado a la cuenta " + parametrosTO.getCuenta() + " (sec. " + parametrosTO.getSecuencia() + ") el numero total de puntos que le corresponde por " + " concepto de antig\u00fcedad.");
                FolioLiberacionTO folioLiberacionTO6 = folioLiberacionTO;
                return folioLiberacionTO6;
            }
            mensajeTO = this.actualizaFechaAnt(connection, lFecAltaM2K, parametrosTO.getCuenta(), parametrosTO.getSecuencia());
            if (mensajeTO.getIdMensaje() == 0) {
                mensajeTO = this.actualizaTotales(connection, nPtsAsignar, parametrosTO.getCuenta(), parametrosTO.getSecuencia());
            }
            if (mensajeTO.getIdMensaje() == 0) {
                mensajeTO = puntosDAO.insertaDetalle(connection, fechaTransaccion, comentario, 24, nPtsAsignar, null, parametrosTO.getCuenta(), parametrosTO.getSecuencia(), parametrosTO.getTelefono(), usuario);
            }
            if (mensajeTO.getIdMensaje() == 0) {
                mensajeTO = puntosDAO.insertaComentarioTMP(connection, parametrosTO.getRegion(), parametrosTO.getCuenta(), usuario, fechaTransaccion, comentario);
            }
            if (mensajeTO.getIdMensaje() == 0) {
                connection.commit();
                return folioLiberacionTO;
            }
            connection.rollback();
            return folioLiberacionTO;
        }
        catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                }
                catch (Exception var18_59) {
                    // empty catch block
                }
            }
            this.error.info((Object)"SQLException.asignaPorAntiguedad:", (Throwable)e);
            throw new CAException(-1, "[asignaPorAntiguedad] SQLError: " + e.toString() + "Actualizar Inf", (Exception)e);
        }
        catch (Exception e) {
            this.error.info((Object)"Exception.asignaPorAntiguedad:", (Throwable)e);
            throw new CAException(-1, "[asignaPorAntiguedad] Error: " + e.toString() + "Actualizar Inf", e);
        }
        finally {
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                    connection.close();
                    connection = null;
                }
                catch (Exception var21_20) {}
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                    resultSet = null;
                }
                catch (Exception var21_21) {}
            }
            if (resultSetTot != null) {
                try {
                    resultSetTot.close();
                    resultSetTot = null;
                }
                catch (Exception var21_22) {}
            }
            if (statement != null) {
                try {
                    statement.close();
                    statement = null;
                }
                catch (Exception var21_23) {}
            }
            if (statementTot != null) {
                try {
                    statementTot.close();
                    statementTot = null;
                }
                catch (Exception var21_24) {}
            }
        }
    }

    private MensajeTO validaFechaAnt(Connection connection, String sCuenta, int iSecuencia, String lFecAltaM2K) throws CAException {
        MensajeTO mensajeTO = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        StringBuffer sQuery = new StringBuffer();
        sQuery.append(" SELECT FECHAANT ");
        sQuery.append("  FROM  ").append(this.schema_database).append("PTO_TBLLINEAS ");
        sQuery.append(" WHERE CUENTA = ? AND SECUENCIA = ? ");
        try {
            mensajeTO = new MensajeTO();
            connection = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
            statement = connection.prepareStatement(sQuery.toString());
            statement.setString(1, sCuenta);
            statement.setInt(2, iSecuencia);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                if (!(resultSet.getString(1) == null || resultSet.getString(1).equals(lFecAltaM2K))) {
                    ArrayList<String> lista = new ArrayList<String>();
                    lista.add(resultSet.getString(1));
                    lista.add(lFecAltaM2K);
                    int indMenor = Utils.comparaFechas(lista, (int)0, (int)1);
                    if (indMenor != 1) {
                        mensajeTO.agregaMensaje(2, "El tramite no procede porque la fecha de alta de la  cuenta proporcionada es mayor que la fecha de alta  de la cuenta vigente.");
                        return mensajeTO;
                    }
                }
                mensajeTO.agregaMensaje(0, "Proceso exitoso");
            }
        }
        catch (SQLException e) {
            this.error.info((Object)"Asignacion.validaFechaAnt.SQLException:", (Throwable)e);
            throw new CAException(-1, "Asignacion.validaFechaAnt.SQLException[" + e.toString() + "]", (Exception)e);
        }
        catch (Exception e) {
            this.error.info((Object)"Asignacion.validaFechaAnt.Exception:", (Throwable)e);
            throw new CAException(-1, "Asignacion.validaFechaAnt.Error[" + e.toString() + "]", e);
        }
        return mensajeTO;
    }

    private MensajeTO actualizaFechaAnt(Connection connection, String lFecAltaM2K, String sCuenta, int iSecuencia) throws CAException {
        MensajeTO mensajeTO;
        Connection lConn = null;
        PreparedStatement statement = null;
        mensajeTO = new MensajeTO();
        Date fechaAlta = null;
        try {
            try {
                lConn = connection == null ? ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul) : connection;
                String UpdateLineas = "UPDATE " + this.schema_database + "PTO_TBLLINEAS SET Fechaant =  ? " + " WHERE Cuenta = ? AND Secuencia = ? ";
                fechaAlta = new Date(Constantes.DATEFORMATyyyy_MM_dd.parse(lFecAltaM2K).getTime());
                statement = lConn.prepareStatement(UpdateLineas);
                statement.setDate(1, fechaAlta);
                statement.setString(2, sCuenta);
                statement.setInt(3, iSecuencia);
                if (statement.executeUpdate() > 0) {
                    mensajeTO.agregaMensaje(0, "Proceso Exitoso");
                } else {
                    mensajeTO.agregaMensaje(1, "No se realizo la actualizacion de la fecha");
                }
            }
            catch (SQLException e) {
                this.error.info((Object)"SQLException.actualizaFechaAnt:", (Throwable)e);
                throw new CAException(-1, "[actualizaFechaAnt] SQLError: " + e.toString() + "Actualizar Inf", (Exception)e);
            }
            catch (Exception e) {
                this.error.info((Object)"Exception.actualizaFechaAnt:", (Throwable)e);
                throw new CAException(-1, "[actualizaFechaAnt] Error: " + e.toString() + "Actualizar Inf", e);
            }
        }
        finally {
            if (statement != null) {
                try {
                    statement.close();
                    statement = null;
                }
                catch (Exception var11_14) {}
            }
        }
        return mensajeTO;
    }

    private MensajeTO actualizaTotales(Connection connection, int nPtsAsignar, String sCuenta, int iSecuencia) throws CAException {
        Connection lConn = null;
        MensajeTO mensajeTO = new MensajeTO();
        PreparedStatement statement = null;
        try {
            String UpdateTotales = "UPDATE " + this.schema_database + "PTO_TBLTOTALES " + " SET PUNTOSEXCEDENTES = PUNTOSEXCEDENTES + ?, " + " PUNTOSANTIG = PUNTOSANTIG + ? " + " WHERE CUENTA = ? AND SECUENCIA = ? ";
            lConn = connection != null ? connection : ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
            statement = lConn.prepareStatement(UpdateTotales);
            statement.setInt(1, nPtsAsignar);
            statement.setInt(2, nPtsAsignar);
            statement.setString(3, sCuenta);
            statement.setInt(4, iSecuencia);
            if (statement.executeUpdate() > 0) {
                mensajeTO.agregaMensaje(0, "Proceso Exitoso.");
            } else {
                mensajeTO.agregaMensaje(1, "No se realizo la actualizacion de los puntos");
            }
        }
        catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                }
                catch (Exception var9_11) {
                    // empty catch block
                }
            }
            this.error.info((Object)"SQLException.actualizaTotales:", (Throwable)e);
            throw new CAException(-1, "[actualizaTotales] SQLError: " + e.toString() + "Actualizar Inf", (Exception)e);
        }
        catch (Exception e) {
            this.error.info((Object)"Exception.actualizaTotales:", (Throwable)e);
            throw new CAException(-1, "[actualizaTotales] Error: " + e.toString() + "Actualizar Inf", e);
        }
        return mensajeTO;
    }

    public boolean validaLineaAsignacion(String Cuenta, String Telefono, int region) throws CAException {
        boolean bandera;
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        bandera = false;
        int numAsignaciones = 0;
        try {
            try {
                conn = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                StringBuffer query = new StringBuffer();
                query.append("SELECT * FROM ").append(this.schema_database).append("PTO_TBLLINEAS_PRUEBAS WHERE CUENTA = ?");
                statement = conn.prepareStatement(query.toString());
                statement.setString(1, Cuenta);
                resultSet = statement.executeQuery();
                boolean isLineaPruebas = false;
                if (resultSet.next()) {
                    isLineaPruebas = true;
                }
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (isLineaPruebas) {
                    bandera = true;
                } else {
                    query = new StringBuffer();
                    query.append("SELECT COUNT(CUENTA) AS ASIGNACIONES FROM ");
                    query.append(this.schema_database).append("PTO_TBLMSTRDETALLE ");
                    query.append("WHERE CUENTA = ? ");
                    query.append("AND LINEA = ? ");
                    query.append("AND IDMOVTO = ? ");
                    query.append("AND FECHAOPERACION >= add_months(sysdate,-12) ");
                    query.append("ORDER BY FECHAOPERACION DESC ");
                    statement = conn.prepareStatement(query.toString());
                    statement.setString(1, Cuenta);
                    statement.setString(2, Telefono);
                    statement.setInt(3, 15);
                    resultSet = statement.executeQuery();
                    if (resultSet.next()) {
                        numAsignaciones = resultSet.getInt("ASIGNACIONES");
                        if (region == 9) {
                            if (numAsignaciones < 1) {
                                bandera = true;
                            }
                        } else if (numAsignaciones < 3) {
                            bandera = true;
                        }
                    }
                }
            }
            catch (SQLException e) {
                this.error.info((Object)"AsignacionDAO.validaLineaAsignacion.SQLException:", (Throwable)e);
                throw new CAException(-1, "AsignacionDAO.validaLineaAsignacion.SQLException[" + e.toString() + "]", (Exception)e);
            }
            catch (Exception e) {
                this.error.info((Object)"AsignacionDAO.validaLineaAsignacion.Exception:", (Throwable)e);
                throw new CAException(-1, "AsignacionDAO.validaLineaAsignacion.Error[" + e.toString() + "]", e);
            }
        }
        finally {
            if (statement != null) {
                try {
                    statement.close();
                    statement = null;
                }
                catch (Exception var12_17) {}
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                    resultSet = null;
                }
                catch (Exception var12_18) {}
            }
            if (conn != null) {
                try {
                    conn.close();
                    conn = null;
                }
                catch (Exception var12_19) {}
            }
        }
        return bandera;
    }

    public int obtienePuntosMaxAsignar(int idPerfilN) throws CAException {
        int puntosMaximos;
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        puntosMaximos = 0;
        try {
            try {
                conn = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                StringBuffer query = new StringBuffer();
                query.append("SELECT MAX_PUNTOSASIGNAR FROM ");
                query.append(this.schema_database).append("PTO_CTLPERFILESASIGNACION ");
                query.append("WHERE IDPERFILN = ? ");
                statement = conn.prepareStatement(query.toString());
                statement.setInt(1, idPerfilN);
                resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    puntosMaximos = resultSet.getInt("MAX_PUNTOSASIGNAR");
                }
            }
            catch (SQLException e) {
                this.error.info((Object)"AsignacionDAO.obtienePuntosMaxAsignar.SQLException:", (Throwable)e);
                throw new CAException(-1, "AsignacionDAO.obtienePuntosMaxAsignar.SQLException[" + e.toString() + "]", (Exception)e);
            }
            catch (Exception e) {
                this.error.info((Object)"AsignacionDAO.obtienePuntosMaxAsignar.Exception:", (Throwable)e);
                throw new CAException(-1, "AsignacionDAO.obtienePuntosMaxAsignar.Error[" + e.toString() + "]", e);
            }
        }
        finally {
            if (statement != null) {
                try {
                    statement.close();
                    statement = null;
                }
                catch (Exception var8_13) {}
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                    resultSet = null;
                }
                catch (Exception var8_14) {}
            }
            if (conn != null) {
                try {
                    conn.close();
                    conn = null;
                }
                catch (Exception var8_15) {}
            }
        }
        return puntosMaximos;
    }

    public ArrayList<PerfilTO> obtienePerfilesValidos() throws CAException {
        ArrayList<PerfilTO> perfilesLst;
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        perfilesLst = new ArrayList<PerfilTO>(0);
        PerfilTO perfilTO = null;
        try {
            try {
                conn = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                StringBuffer query = new StringBuffer();
                query.append("SELECT DISTINCT P.IDPERFILN, P.IDPUESTO, P.DESCRIPCION, P.IDREGION FROM ");
                query.append(this.schema_database).append("PTO_CTLPERFILN P, ");
                query.append(this.schema_database).append("PTO_CTLPERFIL_CA PP ");
                query.append("WHERE PP.IDPROCESO = 71 ");
                query.append("AND P.IDPERFILN = PP.IDPERFILN ");
                query.append("AND P.IDPERFILN IN ( ");
                query.append("SELECT DISTINCT P.IDPERFILN FROM ");
                query.append(this.schema_database).append("PTO_CTLPERFILN P, ");
                query.append(this.schema_database).append("PTO_CTLPERFIL_CA PP ");
                query.append("WHERE PP.IDPROCESO = 25 ");
                query.append("AND P.IDPERFILN = PP.IDPERFILN ");
                query.append(") ");
                query.append("ORDER BY P.IDPUESTO ASC ");
                statement = conn.prepareStatement(query.toString());
                resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    perfilTO = new PerfilTO();
                    perfilTO.setIdPerfilN(resultSet.getInt("IDPERFILN"));
                    perfilTO.setIdPuesto(resultSet.getString("IDPUESTO"));
                    perfilTO.setDescripcion(resultSet.getString("DESCRIPCION"));
                    perfilTO.setRegion(resultSet.getInt("IDREGION"));
                    perfilesLst.add(perfilTO);
                }
            }
            catch (SQLException e) {
                this.error.info((Object)"AsignacionDAO.obtienePerfilesValidos.SQLException:", (Throwable)e);
                throw new CAException(-1, "AsignacionDAO.obtienePerfilesValidos.SQLException[" + e.toString() + "]", (Exception)e);
            }
            catch (Exception e) {
                this.error.info((Object)"AsignacionDAO.obtienePerfilesValidos.Exception:", (Throwable)e);
                throw new CAException(-1, "AsignacionDAO.obtienePerfilesValidos.Error[" + e.toString() + "]", e);
            }
        }
        finally {
            if (statement != null) {
                try {
                    statement.close();
                    statement = null;
                }
                catch (Exception var8_13) {}
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                    resultSet = null;
                }
                catch (Exception var8_14) {}
            }
            if (conn != null) {
                try {
                    conn.close();
                    conn = null;
                }
                catch (Exception var8_15) {}
            }
        }
        return perfilesLst;
    }

    public ArrayList<PerfilTO> obtienePerfilesAsignacion(int idPerfilN, int numPtosMaximo) throws CAException {
        ArrayList<PerfilTO> perfilesLst;
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        perfilesLst = new ArrayList<PerfilTO>(0);
        PerfilTO perfilTO = null;
        int numFiltro = 1;
        try {
            try {
                conn = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                StringBuffer query = new StringBuffer();
                query.append("SELECT PA.IDPERFILN, P.IDPUESTO, P.DESCRIPCION, PA.MAX_PUNTOSASIGNAR, P.IDREGION FROM ");
                query.append(this.schema_database).append("PTO_CTLPERFILESASIGNACION PA, ");
                query.append(this.schema_database).append("PTO_CTLPERFILN P ");
                query.append("WHERE PA.IDPERFILN = P.IDPERFILN ");
                if (idPerfilN != -1) {
                    query.append("AND PA.IDPERFILN = ? ");
                }
                if (numPtosMaximo != 0) {
                    query.append("AND PA.MAX_PUNTOSASIGNAR = ? ");
                }
                query.append("ORDER BY P.IDPUESTO ASC ");
                statement = conn.prepareStatement(query.toString());
                if (idPerfilN != -1) {
                    statement.setInt(numFiltro, idPerfilN);
                    ++numFiltro;
                }
                if (numPtosMaximo != 0) {
                    statement.setInt(numFiltro, numPtosMaximo);
                    ++numFiltro;
                }
                resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    perfilTO = new PerfilTO();
                    perfilTO.setIdPerfilN(resultSet.getInt("IDPERFILN"));
                    perfilTO.setIdPuesto(resultSet.getString("IDPUESTO"));
                    perfilTO.setDescripcion(resultSet.getString("DESCRIPCION"));
                    perfilTO.setRegion(resultSet.getInt("IDREGION"));
                    perfilTO.setNivelAutorizacion(resultSet.getInt("MAX_PUNTOSASIGNAR"));
                    perfilesLst.add(perfilTO);
                }
            }
            catch (SQLException e) {
                this.error.info((Object)"AsignacionDAO.obtienePerfilesAsignacion.SQLException:", (Throwable)e);
                throw new CAException(-1, "AsignacionDAO.obtienePerfilesAsignacion.SQLException[" + e.toString() + "]", (Exception)e);
            }
            catch (Exception e) {
                this.error.info((Object)"AsignacionDAO.obtienePerfilesAsignacion.Exception:", (Throwable)e);
                throw new CAException(-1, "AsignacionDAO.obtienePerfilesAsignacion.Error[" + e.toString() + "]", e);
            }
        }
        finally {
            if (statement != null) {
                try {
                    statement.close();
                    statement = null;
                }
                catch (Exception var11_16) {}
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                    resultSet = null;
                }
                catch (Exception var11_17) {}
            }
            if (conn != null) {
                try {
                    conn.close();
                    conn = null;
                }
                catch (Exception var11_18) {}
            }
        }
        return perfilesLst;
    }

    public ArrayList<MotivoTO> obtieneMotivosAsignacion(String idMotivo, String descripcion, int estatus) throws CAException {
        ArrayList<MotivoTO> motivosLst;
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        motivosLst = new ArrayList<MotivoTO>(0);
        MotivoTO motivoTO = null;
        int numFiltros = 1;
        try {
            try {
                conn = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                StringBuffer query = new StringBuffer();
                query.append("SELECT IDMOTIVO, DESCRIPCION, ESTATUS FROM ");
                query.append(this.schema_database).append("PTO_CTLMOTIVOSASIGNACION PA ");
                query.append("WHERE IDMOTIVO IS NOT NULL ");
                if (!(idMotivo == null || idMotivo.trim().equals(""))) {
                    query.append("AND IDMOTIVO = ? ");
                }
                if (!(descripcion == null || descripcion.trim().equals(""))) {
                    query.append("AND DESCRIPCION LIKE '%" + descripcion.toUpperCase() + "%' ");
                }
                if (estatus != -1) {
                    query.append("AND ESTATUS = ? ");
                }
                statement = conn.prepareStatement(query.toString());
                if (!(idMotivo == null || idMotivo.trim().equals(""))) {
                    statement.setString(numFiltros, idMotivo.toUpperCase());
                    ++numFiltros;
                }
                if (estatus != -1) {
                    statement.setInt(numFiltros, estatus);
                    ++numFiltros;
                }
                resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    motivoTO = new MotivoTO();
                    motivoTO.setIdMotivo(resultSet.getString("IDMOTIVO"));
                    motivoTO.setDescripcion(resultSet.getString("DESCRIPCION"));
                    motivoTO.setEstatus(resultSet.getString("ESTATUS").equals("1") ? "Activo" : "Inactivo");
                    motivosLst.add(motivoTO);
                }
            }
            catch (SQLException e) {
                this.error.info((Object)"AsignacionDAO.obtienePerfilesAsignacion.SQLException:", (Throwable)e);
                throw new CAException(-1, "AsignacionDAO.obtieneMotivosAsignacion.SQLException[" + e.toString() + "]", (Exception)e);
            }
            catch (Exception e) {
                this.error.info((Object)"AsignacionDAO.obtienePerfilesAsignacion.Exception:", (Throwable)e);
                throw new CAException(-1, "AsignacionDAO.obtieneMotivosAsignacion.Error[" + e.toString() + "]", e);
            }
        }
        finally {
            if (statement != null) {
                try {
                    statement.close();
                    statement = null;
                }
                catch (Exception var12_17) {}
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                    resultSet = null;
                }
                catch (Exception var12_18) {}
            }
            if (conn != null) {
                try {
                    conn.close();
                    conn = null;
                }
                catch (Exception var12_19) {}
            }
        }
        return motivosLst;
    }

    public boolean insertaMotivoAsignacion(String idMotivo, String descripcion, int estatus) throws CAException {
        Connection conn = null;
        PreparedStatement stmt = null;
        StringBuffer query = new StringBuffer();
        query.append("INSERT INTO ").append(this.schema_database).append("PTO_CTLMOTIVOSASIGNACION ");
        query.append("VALUES(?,?,?)");
        try {
            conn = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
            stmt = conn.prepareStatement(query.toString());
            stmt.setString(1, idMotivo.trim().toUpperCase());
            stmt.setString(2, descripcion.trim().toUpperCase());
            stmt.setInt(3, estatus);
            if (stmt.executeUpdate() > 0) {
                return true;
            }
            return false;
        }
        catch (SQLException e) {
            this.error.info((Object)"AsignacionDAO.insertaMotivoAsignacion.SQLException:", (Throwable)e);
            throw new CAException(-1, "AsignacionDAO.insertaMotivoAsignacion.Error[" + e.toString() + "]", (Exception)e);
        }
        catch (Exception e) {
            this.error.info((Object)"AsignacionDAO.insertaMotivoAsignacion.Exception:", (Throwable)e);
            throw new CAException(-1, "AsignacionDAO.insertaMotivoAsignacion.Error[" + e.toString() + "]", e);
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
    }

    public boolean actualizaMotivoAsignacion(String idMotivo, String descripcion, int estatus) throws CAException {
        boolean seActualizo;
        Connection conn = null;
        PreparedStatement stmt = null;
        seActualizo = false;
        StringBuffer query = new StringBuffer();
        query.append("UPDATE ").append(this.schema_database).append("PTO_CTLMOTIVOSASIGNACION ");
        query.append("SET DESCRIPCION = ? ");
        query.append(", ESTATUS = ? ");
        query.append("WHERE IDMOTIVO = ? ");
        try {
            try {
                conn = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                stmt = conn.prepareStatement(query.toString());
                stmt.setString(1, descripcion);
                stmt.setInt(2, estatus);
                stmt.setString(3, idMotivo);
                if (stmt.executeUpdate() > 0) {
                    seActualizo = true;
                }
            }
            catch (Exception e) {
                this.error.info((Object)"AsignacionDAO.actualizaMotivoAsignacion.Exception:", (Throwable)e);
                throw new CAException(-1, "AsignacionDAO.actualizaMotivoAsignacion.Error[" + e.toString() + "]", e);
            }
        }
        finally {
            if (stmt != null) {
                try {
                    stmt.close();
                }
                catch (SQLException var10_12) {}
            }
            if (conn != null) {
                try {
                    conn.close();
                }
                catch (SQLException var10_13) {}
            }
        }
        return seActualizo;
    }

    public boolean eliminaMotivoAsignacion(String idMotivo) throws CAException {
        boolean seElimino;
        Connection conn = null;
        PreparedStatement stmt = null;
        seElimino = false;
        StringBuffer query = new StringBuffer();
        query.append("DELETE ").append(this.schema_database).append("PTO_CTLMOTIVOSASIGNACION ");
        query.append("WHERE IDMOTIVO = ? ");
        try {
            try {
                conn = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                stmt = conn.prepareStatement(query.toString());
                stmt.setString(1, idMotivo);
                if (stmt.executeUpdate() > 0) {
                    seElimino = true;
                }
            }
            catch (Exception e) {
                this.error.info((Object)"AsignacionDAO.eliminaMotivoAsignacion.Exception:", (Throwable)e);
                throw new CAException(-1, "AsignacionDAO.eliminaMotivoAsignacion.Error[" + e.toString() + "]", e);
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

    public List<TelefonoTO> getLineasPrueba() throws CAException {
        ArrayList<TelefonoTO> lineasPrueba;
        lineasPrueba = new ArrayList<TelefonoTO>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet resultSet = null;
        try {
            try {
                conn = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                StringBuilder query = new StringBuilder();
                query.append("SELECT CUENTA, LINEA, IDREGION ");
                query.append("FROM ").append(this.schema_database).append("PTO_TBLLINEAS_PRUEBAS ");
                query.append("WHERE ESTATUS = 'A' ");
                query.append("ORDER BY FECHAOPERACION DESC ");
                stmt = conn.prepareStatement(query.toString());
                resultSet = stmt.executeQuery();
                while (resultSet.next()) {
                    TelefonoTO telefonoTO = new TelefonoTO();
                    telefonoTO.setCuenta(resultSet.getString("CUENTA"));
                    telefonoTO.setTelefono(resultSet.getString("LINEA"));
                    telefonoTO.setRegion(resultSet.getInt("IDREGION"));
                    lineasPrueba.add(telefonoTO);
                }
            }
            catch (Exception e) {
                this.error.info((Object)"AsignacionDAO.getLineasPrueba.Exception:", (Throwable)e);
                throw new CAException(-1, "AsignacionDAO.getLineasPrueba.Error[" + e.toString() + "]", e);
            }
        }
        finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                }
                catch (SQLException var8_12) {}
            }
            if (stmt != null) {
                try {
                    stmt.close();
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
        return lineasPrueba;
    }

    public MensajeTO insertaLineaPrueba(TelefonoTO telefonoTO, String numeroEmpleado) throws CAException {
        MensajeTO mensajeTO;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet resultSet = null;
        mensajeTO = new MensajeTO();
        try {
            try {
                StringBuilder query = new StringBuilder();
                query.append("SELECT COUNT(*) FROM ").append(this.schema_database).append("PTO_TBLLINEAS_PRUEBAS ");
                query.append("WHERE CUENTA = ? ");
                conn = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                stmt = conn.prepareStatement(query.toString());
                stmt.setString(1, telefonoTO.getCuenta());
                resultSet = stmt.executeQuery();
                int total = 0;
                if (resultSet.next()) {
                    total = resultSet.getInt(1);
                }
                if (resultSet != null) {
                    try {
                        resultSet.close();
                        resultSet = null;
                    }
                    catch (SQLException var9_10) {
                        // empty catch block
                    }
                }
                if (stmt != null) {
                    try {
                        stmt.close();
                        stmt = null;
                    }
                    catch (SQLException var9_11) {
                        // empty catch block
                    }
                }
                if (total == 0) {
                    query = new StringBuilder();
                    query.append("INSERT INTO ").append(this.schema_database).append("PTO_TBLLINEAS_PRUEBAS(CUENTA,LINEA,");
                    query.append("IDREGION,FECHAOPERACION,NUMEMPLEADO,ESTATUS) ");
                    query.append("values(?,?,?,?,?,?) ");
                    stmt = conn.prepareStatement(query.toString());
                    stmt.setString(1, telefonoTO.getCuenta());
                    stmt.setString(2, telefonoTO.getTelefono());
                    stmt.setInt(3, telefonoTO.getRegion());
                    stmt.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
                    stmt.setString(5, numeroEmpleado);
                    stmt.setString(6, "A");
                    stmt.executeUpdate();
                    mensajeTO.setIdMensaje(0);
                    mensajeTO.setMensaje("Se agrego satisfactoriamente la l\u00ednea de Pruebas: " + telefonoTO.getTelefono());
                } else {
                    mensajeTO.setIdMensaje(-1);
                    mensajeTO.setMensaje("Ya se encuentra dada de alta la l\u00ednea de Prueba: " + telefonoTO.getTelefono());
                }
            }
            catch (Exception e) {
                this.error.info((Object)"AsignacionDAO.insertaLineaPrueba.Exception:", (Throwable)e);
                throw new CAException(-1, "AsignacionDAO.insertaLineaPrueba.Error[" + e.toString() + "]", e);
            }
        }
        finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
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
        return mensajeTO;
    }

    public MensajeTO eliminaLineaPrueba(TelefonoTO telefonoTO) throws CAException {
        MensajeTO mensajeTO;
        Connection conn = null;
        PreparedStatement stmt = null;
        mensajeTO = new MensajeTO();
        try {
            try {
                StringBuilder query = new StringBuilder();
                query.append("DELETE FROM ").append(this.schema_database).append("PTO_TBLLINEAS_PRUEBAS WHERE CUENTA = ? ");
                conn = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                stmt = conn.prepareStatement(query.toString());
                stmt.setString(1, telefonoTO.getCuenta());
                stmt.executeUpdate();
                mensajeTO.setIdMensaje(0);
                mensajeTO.setMensaje("Se elimino satisfactoriamente la l\u00ednea de Pruebas: " + telefonoTO.getTelefono());
            }
            catch (Exception e) {
                this.error.info((Object)"AsignacionDAO.eliminaLineaPrueba.Exception:", (Throwable)e);
                throw new CAException(-1, "AsignacionDAO.eliminaLineaPrueba.Error[" + e.toString() + "]", e);
            }
        }
        finally {
            if (stmt != null) {
                try {
                    stmt.close();
                }
                catch (SQLException var7_10) {}
            }
            if (conn != null) {
                try {
                    conn.close();
                }
                catch (SQLException var7_11) {}
            }
        }
        return mensajeTO;
    }
}

