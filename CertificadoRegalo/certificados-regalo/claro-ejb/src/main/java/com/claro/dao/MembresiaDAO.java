/*
 * Decompiled with CFR 0_102.
 * 
 * Could not load the following classes:
 *  com.claro.exception.CAException
 *  com.claro.transfer.MensajeTO
 *  com.claro.transfer.membresia.MembresiaTO
 *  com.claro.util.ServiceLocator
 *  com.claro.util.Utils
 *  org.apache.log4j.Logger
 */
package com.claro.dao;

import com.claro.dao.ConsultaCuentasCorreoDAO;
import com.claro.dao.PuntosDAO;
import com.claro.exception.CAException;
import com.claro.transfer.MensajeTO;
import com.claro.transfer.membresia.MembresiaTO;
import com.claro.util.ServiceLocator;
import com.claro.util.Utils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.apache.log4j.Logger;

public class MembresiaDAO {
    protected final Logger logger = Logger.getLogger((String)"loggerCirculoAzul");
    protected final Logger error = Logger.getLogger((String)"loggerCirculoAzulError");
    private String schema_database;

    public MembresiaDAO() {
        try {
            this.schema_database = ServiceLocator.getInstance().getVariable(ServiceLocator.schema_database);
        }
        catch (Exception e) {
            this.error.error((Object)"PuntosDAO", (Throwable)e);
        }
    }

    private MensajeTO insertaReposiciones(Connection connection, long fechaTransaccion, MembresiaTO membresiaTO) throws CAException {
        MensajeTO mensajeTO;
        Connection lConn = null;
        PreparedStatement statement = null;
        mensajeTO = new MensajeTO();
        try {
            try {
                lConn = connection == null ? ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul) : connection;
                String insert = "INSERT INTO " + this.schema_database + "PTO_TBLREPOSICIONES (Cuenta, Secuencia, NombreM2K, FecAltaM2K, Region, Telefono," + " Segmento, Plan, DescPlan, Nombre, ApePaterno, ApeMaterno, TipoCalle, Calle, NumExterior, NumInterior," + " Colonia, Ciudad, Estado, CodigoPostal, Usuario, FechaOper, Estatus, Motivo, costo) VALUES (" + "?,?,?,?,?,?,?,?,?,?," + "?,?,?,?,?,?,?,?,?,?," + "?,?,?,?,?)";
                statement = lConn.prepareStatement(insert);
                statement.setString(1, membresiaTO.getCuenta());
                statement.setInt(2, membresiaTO.getSecuencia());
                statement.setString(3, membresiaTO.getNombreM2K());
                statement.setString(4, membresiaTO.getFechaAltaM2K());
                statement.setInt(5, membresiaTO.getRegion());
                statement.setString(6, membresiaTO.getTelefono());
                statement.setString(7, membresiaTO.getSegmento());
                statement.setString(8, membresiaTO.getPlan());
                statement.setString(9, membresiaTO.getDescPlan());
                statement.setString(10, membresiaTO.getNombre());
                statement.setString(11, membresiaTO.getApPaterno());
                statement.setString(12, membresiaTO.getApMaterno());
                statement.setString(13, membresiaTO.getTipoCalle());
                statement.setString(14, membresiaTO.getCalle());
                statement.setString(15, membresiaTO.getNumExterior());
                statement.setString(16, membresiaTO.getNumInterior());
                statement.setString(17, membresiaTO.getColonia());
                statement.setString(18, membresiaTO.getCiudad());
                statement.setString(19, membresiaTO.getEstado());
                statement.setString(20, membresiaTO.getCodigoPostal());
                statement.setString(21, membresiaTO.getUsuario());
                statement.setDate(22, new Date(fechaTransaccion));
                statement.setString(23, membresiaTO.getEstatus());
                statement.setString(24, membresiaTO.getMotivo());
                statement.setInt(25, membresiaTO.getCosto());
                if (statement.executeUpdate() > 0) {
                    mensajeTO.agregaMensaje(0, "Proceso Exitoso");
                } else {
                    mensajeTO.agregaMensaje(1, "No se inserta la reposicion");
                }
            }
            catch (SQLException e) {
                this.error.info((Object)"SQLException.actualizaDatosM2K:", (Throwable)e);
                throw new CAException(-1, "[consultaDatos] SQLError: " + e.toString() + "Actualizar Inf", (Exception)e);
            }
            catch (Exception e) {
                this.error.info((Object)"Exception.actualizaDatosM2K:", (Throwable)e);
                throw new CAException(-1, "[consultaDatos] Error: " + e.toString() + "Actualizar Inf", e);
            }
        }
        finally {
            if (statement != null) {
                try {
                    statement.close();
                    statement = null;
                }
                catch (Exception var10_13) {}
            }
            if (connection == null && lConn != null) {
                try {
                    lConn.close();
                    lConn = null;
                }
                catch (Exception var10_14) {}
            }
        }
        return mensajeTO;
    }

    public MensajeTO agregarMembresia(MembresiaTO membresiaTO) throws CAException {
        MensajeTO mensajeTO;
        Connection connection = null;
        mensajeTO = new MensajeTO();
        try {
            try {
                PuntosDAO puntosDAO = new PuntosDAO();
                connection = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                connection.setAutoCommit(false);
                long fechaTransaccion = System.currentTimeMillis();
                membresiaTO.setEstatus("A");
                mensajeTO = this.insertaReposiciones(connection, fechaTransaccion, membresiaTO);
                if (mensajeTO.getIdMensaje() == 0) {
                    mensajeTO = puntosDAO.insertaComentarioTMP(connection, membresiaTO.getRegion(), membresiaTO.getCuenta(), membresiaTO.getUsuario(), fechaTransaccion, "REPOSICION DE MEMBRESIA REALIZO " + membresiaTO.getUsuario() + ".MOTIVO:" + membresiaTO.getMotivo() + ".");
                }
                if (mensajeTO.getIdMensaje() == 0) {
                    String referencia = "Reposicion de tarjeta de puntos realizo " + membresiaTO.getUsuario() + ". Motivo:" + membresiaTO.getMotivo() + " " + (membresiaTO.getCosto() == 1 ? "-Con Costo-" : "-Sin Costo-");
                    mensajeTO = puntosDAO.insertaDetalle(connection, fechaTransaccion, referencia, 37, 0, null, membresiaTO.getCuenta(), membresiaTO.getSecuencia(), membresiaTO.getTelefono(), membresiaTO.getUsuario());
                }
                if (mensajeTO.getIdMensaje() != 0) {
                    connection.rollback();
                } else {
                    connection.commit();
                }
            }
            catch (SQLException e) {
                this.error.info((Object)"SQLException.actualizaDatosM2K:", (Throwable)e);
                if (connection != null) {
                    try {
                        connection.rollback();
                    }
                    catch (Exception fechaTransaccion) {
                        // empty catch block
                    }
                }
                throw new CAException(-1, "[consultaDatos] SQLError: " + e.toString() + "Actualizar Inf", (Exception)e);
            }
            catch (Exception e) {
                this.error.info((Object)"Exception.actualizaDatosM2K:", (Throwable)e);
                if (connection != null) {
                    try {
                        connection.rollback();
                    }
                    catch (Exception fechaTransaccion) {
                        // empty catch block
                    }
                }
                throw new CAException(-1, "[consultaDatos] Error: " + e.toString() + "Actualizar Inf", e);
            }
        }
        finally {
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                    connection.close();
                    connection = null;
                }
                catch (Exception var9_13) {}
            }
        }
        return mensajeTO;
    }

    public MembresiaTO consultaMembresia(String cuenta, int secuencia, java.util.Date fecha) throws CAException {
        MembresiaTO membresiaTO;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        membresiaTO = new MembresiaTO();
        try {
            try {
                String sQuery = "SELECT CUENTA,SECUENCIA,FECHAOPER,NOMBREM2K,FECALTAM2K,REGION,TELEFONO,SEGMENTO,PLAN,DESCPLAN,NOMBRE,APEPATERNO,APEMATERNO,TIPOCALLE,CALLE,NUMEXTERIOR,NUMINTERIOR,COLONIA,CIUDAD,ESTADO,CODIGOPOSTAL,USUARIO,ESTATUS,MOTIVO,COSTO FROM " + this.schema_database + "PTO_TBLREPOSICIONES" + " WHERE CUENTA=? AND SECUENCIA=? AND FECHAOPER=? ";
                connection = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                statement = connection.prepareStatement(sQuery);
                statement.setString(1, cuenta);
                statement.setInt(2, secuencia);
                if (fecha == null) {
                    statement.setDate(3, new Date(System.currentTimeMillis()));
                } else {
                    statement.setDate(3, new Date(fecha.getTime()));
                }
                resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    membresiaTO.setCuenta(resultSet.getString(1));
                    membresiaTO.setSecuencia(resultSet.getInt(2));
                    membresiaTO.setFechaOperacion((java.util.Date)resultSet.getDate(3));
                    membresiaTO.setNombreM2K(resultSet.getString(4));
                    membresiaTO.setFechaAltaM2K(resultSet.getString(5));
                    membresiaTO.setRegion(resultSet.getInt(6));
                    membresiaTO.setTelefono(resultSet.getString(7));
                    membresiaTO.setSegmento(resultSet.getString(8));
                    membresiaTO.setPlan(resultSet.getString(9));
                    membresiaTO.setDescPlan(resultSet.getString(10));
                    membresiaTO.setNombre(resultSet.getString(11));
                    membresiaTO.setApPaterno(resultSet.getString(12));
                    membresiaTO.setApMaterno(resultSet.getString(13));
                    membresiaTO.setTipoCalle(resultSet.getString(14));
                    membresiaTO.setCalle(resultSet.getString(15));
                    membresiaTO.setNumExterior(resultSet.getString(16));
                    membresiaTO.setNumInterior(resultSet.getString(17));
                    membresiaTO.setColonia(resultSet.getString(18));
                    membresiaTO.setCiudad(resultSet.getString(19));
                    membresiaTO.setEstado(resultSet.getString(20));
                    membresiaTO.setCodigoPostal(resultSet.getString(21));
                    membresiaTO.setUsuario(resultSet.getString(22));
                    membresiaTO.setEstatus(resultSet.getString(23));
                    membresiaTO.setMotivo(resultSet.getString(24));
                    membresiaTO.setCosto(resultSet.getInt(25));
                    membresiaTO.agregaMensaje(0, "Proceso Exitoso");
                } else {
                    membresiaTO.agregaMensaje(1, "NO SE ENCONTRO LA MEMBRESIA");
                }
            }
            catch (SQLException e) {
                this.error.info((Object)"SQLException.consultaMembresia:", (Throwable)e);
                throw new CAException(-1, "[consultaDatos] SQLError: " + e.toString() + "Actualizar Inf", (Exception)e);
            }
            catch (Exception e) {
                this.error.info((Object)"Exception.consultaMembresia:", (Throwable)e);
                throw new CAException(-1, "[consultaMembresia] Error: " + e.toString() + "Actualizar Inf", e);
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
        return membresiaTO;
    }

    public ArrayList<MembresiaTO> depuracionMembresia(ArrayList<MembresiaTO> arregloMembresiaTO) throws CAException {
        ArrayList<MembresiaTO> arregloMembresias;
        Connection connection = null;
        PreparedStatement statement = null;
        arregloMembresias = new ArrayList<MembresiaTO>();
        try {
            try {
                ListIterator<MembresiaTO> iterator = arregloMembresiaTO.listIterator();
                connection = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                while (iterator.hasNext()) {
                    MembresiaTO membresiaTO = iterator.next();
                    String sQuery = " SELECT A.CUENTA, A.SECUENCIA, A.PLAN  FROM " + this.schema_database + "PTO_TBLLINEAS A, " + this.schema_database + "PTO_TBLTOTALES B, " + this.schema_database + "PTO_CTLPLANESTARIFARIOS C, " + this.schema_database + "PTO_CTLSEGMENTOS D  " + " WHERE a.Cuenta = ? AND a.secuencia = ? " + " AND B.CUENTA = A.CUENTA AND B.SECUENCIA = A.SECUENCIA " + " AND C.IDPLAN = A.PLAN AND C.IDSEGMENTO = D.IDSEGMENTO " + " AND C.IDREGION = A.IDREGION ";
                    statement = connection.prepareStatement(sQuery);
                    statement.setString(1, membresiaTO.getCuenta());
                    statement.setInt(2, membresiaTO.getSecuencia());
                    ResultSet resultSet = statement.executeQuery();
                    if (resultSet.next()) {
                        membresiaTO.setPlan(resultSet.getString(3));
                        arregloMembresias.add(membresiaTO);
                        membresiaTO.agregaMensaje(0, "Proceso Exitoso");
                    } else {
                        membresiaTO.agregaMensaje(1, "NO SE ENCONTRO LA CUENTA/PLAN EN CIRCULO AZUL");
                    }
                    resultSet.close();
                    resultSet = null;
                }
            }
            catch (SQLException e) {
                this.error.info((Object)"SQLException.depuracionMembresia:", (Throwable)e);
                throw new CAException(-1, "[depuracionMembresia] SQLError: " + e.toString() + "Consultar Inf", (Exception)e);
            }
            catch (Exception e) {
                this.error.info((Object)"Exception.depuracionMembresia:", (Throwable)e);
                throw new CAException(-1, "[depuracionMembresia] Error: " + e.toString() + "Consultar Inf", e);
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
            if (connection != null) {
                try {
                    connection.close();
                    connection = null;
                }
                catch (Exception var10_15) {}
            }
        }
        return arregloMembresias;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public boolean listaMembresia(ArrayList<MembresiaTO> membresias, int idRegion) throws CAException {
        FileOutputStream f1;
        ArrayList membresiasDepuradas;
        boolean valor;
        String logInserts;
        Map<String, String> parametrosCorreo;
        block16 : {
            logInserts = "MembresiasR" + idRegion + ".csv";
            f1 = null;
            valor = false;
            try {
				f1 = new FileOutputStream(logInserts);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            ConsultaCuentasCorreoDAO consultaCuentasCorreoDAO = new ConsultaCuentasCorreoDAO();
            parametrosCorreo = consultaCuentasCorreoDAO.obtieneCuentasCorreoCargaCatalogos(8);
            membresiasDepuradas = new ArrayList();
            membresiasDepuradas = this.depuracionMembresia(membresias);
            if (membresiasDepuradas.size() > 0) break block16;
            Utils.enviaCorreo(parametrosCorreo, (String)"Error al procesar la lista de membresias.", (String)"No hay datos a procesar.", (List)null);
            if (f1 == null) return false;
            try {
                f1.close();
                return false;
            }
            catch (IOException var13_10) {
                // empty catch block
            }
            return false;
        }
        try {
            try {
                PrintStream p1 = new PrintStream(f1);
                ListIterator iterator = membresiasDepuradas.listIterator();
                while (iterator.hasNext()) {
                    MembresiaTO membresiaTO = (MembresiaTO)iterator.next();
                    p1.println(String.valueOf(membresiaTO.getTelefono()) + "," + membresiaTO.getCuenta() + "," + membresiaTO.getApPaterno().trim() + " " + membresiaTO.getApMaterno().trim());
                }
                p1.flush();
                ArrayList<String> archivosLog = new ArrayList<String>();
                archivosLog.add(logInserts);
                Utils.enviaCorreo(parametrosCorreo, (String)"lista de membresias.", (String)"Se genera lista de membresias.", archivosLog);
                valor = true;
                return valor;
            }
            catch (Exception consultaCuentasCorreoDAO) {
                if (f1 == null) return valor;
                try {
                    f1.close();
                    return valor;
                }
                catch (IOException var13_11) {}
            }
            return valor;
        }
        catch (Throwable var12_17) {}
        finally {
            if (f1 != null) {
                try {
                    f1.close();
                }
                catch (IOException var13_13) {}
            }
        }
        {
           ;// throw var12_17;
        }
		return valor;
    }
}

