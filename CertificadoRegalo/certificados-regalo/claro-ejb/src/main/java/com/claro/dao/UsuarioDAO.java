/*
 * Decompiled with CFR 0_102.
 * 
 * Could not load the following classes:
 *  com.claro.exception.CAException
 *  com.claro.transfer.PerfilTO
 *  com.claro.transfer.PrivilegioTO
 *  com.claro.transfer.PuntoVentaTO
 *  com.claro.transfer.UsuarioTO
 *  com.claro.util.Constantes
 *  com.claro.util.ServiceLocator
 *  org.apache.log4j.Logger
 */
package com.claro.dao;

import com.claro.dao.ConsultasDAO;
import com.claro.exception.CAException;
import com.claro.transfer.PerfilTO;
import com.claro.transfer.PrivilegioTO;
import com.claro.transfer.PuntoVentaTO;
import com.claro.transfer.UsuarioTO;
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
import java.util.Hashtable;

import org.apache.log4j.Logger;

public class UsuarioDAO {
    protected final Logger logger = Logger.getLogger((String)"loggerCirculoAzul");
    protected final Logger error = Logger.getLogger((String)"loggerCirculoAzulError");
    private String schema_database;

    public UsuarioDAO() {
        try {
            this.schema_database = ServiceLocator.getInstance().getVariable(ServiceLocator.schema_database);
        }
        catch (Exception e) {
            this.error.error((Object)"UsuarioDAO", (Throwable)e);
        }
    }

    public UsuarioTO consultaEmpleado(String numEmpleado, String sIP, String passActual, boolean validaPwd) throws CAException {
        UsuarioTO usuarioTO;
        if (numEmpleado == null) {
            throw new CAException(-1, "Debe ingresar un numero de empleado");
        }
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        StringBuffer sQuery = new StringBuffer();
        sQuery.append("SELECT A.IDUSUARIO, A.NOMBRE, A.PASSWDENC, A.IDPERFIL,P.IDPERFILN, P.NIVELAUT,P.IDPUESTO,P.DESCRIPCION,P.IDREGION,A.STATUS ");
        sQuery.append("FROM ").append(this.schema_database).append("PTO_CTLUSUARIOS A,").append(this.schema_database).append("PTO_CTLPERFILN P ");
        sQuery.append("WHERE A.NUMEMPLEADO = ? AND A.STATUS IN ('A','P') AND A.IDPERFILN=P.IDPERFILN");
        usuarioTO = new UsuarioTO();
        try {
            try {
                long inicioProceso = System.currentTimeMillis();
                this.logger.info((Object)("consultaEmpleado|Inicio Proceso|" + Constantes.DATEFORMTALog.format(new Date()) + "|" + inicioProceso));
                this.logger.info((Object)("consultaEmpleado|Antes de Conexion|" + Constantes.DATEFORMTALog.format(new Date()) + "|" + inicioProceso));
                connection = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                this.logger.info((Object)("consultaEmpleado|Despues de Conexion|" + Constantes.DATEFORMTALog.format(new Date()) + "|" + (System.currentTimeMillis() - inicioProceso)));
                long inicioConsulta = System.currentTimeMillis();
                this.logger.info((Object)("consultaEmpleado|InicioConsulta|" + Constantes.DATEFORMTALog.format(new Date()) + "|" + inicioConsulta));
                preparedStatement = connection.prepareStatement(sQuery.toString());
                preparedStatement.setString(1, numEmpleado);
                resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    usuarioTO.setNumEmpleado(numEmpleado);
                    usuarioTO.setIdUsuario(resultSet.getString(1));
                    usuarioTO.setNombre(resultSet.getString(2));
                    usuarioTO.setPassword(resultSet.getString(3));
                    usuarioTO.setStatus(resultSet.getString(10));
                    int idPerfiln = resultSet.getInt(5);
                    usuarioTO.setPuntoVentaTO(new ConsultasDAO().obtienePuntoVenta(sIP, connection));
                    if (validaPwd) {
                        if (!usuarioTO.getPassword().equals(passActual) && usuarioTO.getStatus().equals("A")) {
                            usuarioTO.agregaMensaje(-1, "El password no es valido para el usuario [" + numEmpleado + "]");
                        } else if (usuarioTO.getPassword().equals(passActual) && usuarioTO.getStatus().equals("A")) {
                            PerfilTO perfilTO = new PerfilTO();
                            perfilTO.setIdPerfilN(idPerfiln);
                            perfilTO.setNivelAutorizacion(resultSet.getInt(6));
                            perfilTO.setIdPuesto(resultSet.getString(7));
                            perfilTO.setDescripcion(resultSet.getString(8));
                            perfilTO.setRegion(resultSet.getInt(9));
                            PerfilTO perfiles = this.getPrivilegiosCirculoAzulEcac(idPerfiln);
                            perfilTO.setPrivilegiosCa(perfiles.getPrivilegiosCa());
                            perfilTO.setPrivilegiosEcac(perfiles.getPrivilegiosEcac());
                            usuarioTO.setPerfilTO(perfilTO);
                            usuarioTO.agregaMensaje(0, "Proceso Exitoso");
                        } else {
                            usuarioTO.agregaMensaje(-1, "Usuario [" + numEmpleado + "] dado de baja por no ingresar en un periodo mayor a 60 dias." + "<br>El usuario debe levantar SUA en Service Desk como Alta para su reactivaci\u00f3n.");
                        }
                    } else {
                        PerfilTO perfilTO = new PerfilTO();
                        perfilTO.setIdPerfilN(idPerfiln);
                        perfilTO.setNivelAutorizacion(resultSet.getInt(6));
                        perfilTO.setIdPuesto(resultSet.getString(7));
                        perfilTO.setDescripcion(resultSet.getString(8));
                        perfilTO.setRegion(resultSet.getInt(9));
                        PerfilTO perfiles = this.getPrivilegiosCirculoAzulEcac(idPerfiln);
                        perfilTO.setPrivilegiosCa(perfiles.getPrivilegiosCa());
                        perfilTO.setPrivilegiosEcac(perfiles.getPrivilegiosEcac());
                        usuarioTO.setPerfilTO(perfilTO);
                        usuarioTO.agregaMensaje(0, "Proceso Exitoso");
                    }
                } else {
                    usuarioTO.agregaMensaje(-1, "Usuario[" + numEmpleado + "] No registrado");
                }
                this.logger.info((Object)("consultaEmpleado|FinConsulta|" + Constantes.DATEFORMTALog.format(new Date()) + "|" + (System.currentTimeMillis() - inicioConsulta)));
                this.logger.info((Object)("consultaEmpleado|FinProceso|" + Constantes.DATEFORMTALog.format(new Date()) + "|" + (System.currentTimeMillis() - inicioProceso)));
            }
            catch (SQLException e) {
                this.error.info((Object)"SQLException.consultaEmpleado:", (Throwable)e);
                throw new CAException(-1, "SQLException.consulta[" + e.toString() + "]", (Exception)e);
            }
            catch (Exception e) {
                this.error.info((Object)"Exception.consultaEmpleado:", (Throwable)e);
                throw new CAException(-1, "UsuarioDAO.consulta[" + e.toString() + "]", e);
            }
        }
        finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                    resultSet = null;
                }
                catch (Exception var18_23) {}
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                    preparedStatement = null;
                }
                catch (Exception var18_24) {}
            }
            if (connection != null) {
                try {
                    connection.close();
                    connection = null;
                }
                catch (Exception var18_25) {}
            }
        }
        return usuarioTO;
    }

    public PerfilTO getPrivilegiosCirculoAzulEcac(int idPerfiln) throws CAException {
        PerfilTO perfilTO;
        perfilTO = new PerfilTO();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet resultSet = null;
        StringBuffer sQuery = new StringBuffer();
        try {
            try {
                sQuery = new StringBuffer();
                sQuery.append("SELECT PCA.IDPROCESO, PCS.NOMBREPROCESO, PCS.TIPO, PCA.ESTATUS ");
                sQuery.append("FROM ").append(this.schema_database).append("PTO_CTLPERFIL_CA PCA, ");
                sQuery.append(this.schema_database).append("PTO_CTLPROCESOS PCS ");
                sQuery.append("WHERE PCA.IDPERFILN=? AND PCA.IDPROCESO=PCS.IDPROCESO AND ");
                sQuery.append("PCA.ESTATUS='A' AND PCS.TIPO='C'");
                conn = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                stmt = conn.prepareStatement(sQuery.toString());
                stmt.setInt(1, idPerfiln);
                resultSet = stmt.executeQuery();
                Hashtable<String, PrivilegioTO> privilegiosCa = new Hashtable<String, PrivilegioTO>();
                while (resultSet.next()) {
                    PrivilegioTO privilegioTO = new PrivilegioTO();
                    privilegioTO.setIdProceso(resultSet.getInt(1));
                    privilegioTO.setNombre(resultSet.getString(2));
                    privilegioTO.setTipo(resultSet.getString(3));
                    privilegioTO.setEstatus(resultSet.getString(4));
                    privilegiosCa.put(String.valueOf(privilegioTO.getIdProceso()), privilegioTO);
                }
                perfilTO.setPrivilegiosCa(privilegiosCa);
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
                if (stmt != null) {
                    stmt.close();
                    stmt = null;
                }
                sQuery = new StringBuffer();
                sQuery.append("SELECT PCA.IDPROCESO, PCS.NOMBREPROCESO, PCS.TIPO, PCA.ESTATUS ");
                sQuery.append("FROM ").append(this.schema_database).append("PTO_CTLPERFIL_CA PCA, ");
                sQuery.append(this.schema_database).append("PTO_CTLPROCESOS PCS ");
                sQuery.append("WHERE PCA.IDPERFILN=? AND PCA.IDPROCESO=PCS.IDPROCESO AND ");
                sQuery.append("PCA.ESTATUS='A' AND PCS.TIPO='E'");
                stmt = conn.prepareStatement(sQuery.toString());
                stmt.setInt(1, idPerfiln);
                resultSet = stmt.executeQuery();
                Hashtable<String, PrivilegioTO> privilegiosEcac = new Hashtable<String, PrivilegioTO>();
                while (resultSet.next()) {
                    PrivilegioTO privilegioTO = new PrivilegioTO();
                    privilegioTO.setIdProceso(resultSet.getInt(1));
                    privilegioTO.setNombre(resultSet.getString(2));
                    privilegioTO.setTipo(resultSet.getString(3));
                    privilegioTO.setEstatus(resultSet.getString(4));
                    privilegiosEcac.put(String.valueOf(privilegioTO.getIdProceso()), privilegioTO);
                }
                perfilTO.setPrivilegiosEcac(privilegiosEcac);
            }
            catch (SQLException e) {
                this.error.info((Object)"Exception.getPrivilegiosCirculoAzulEcac:", (Throwable)e);
                throw new CAException(-1, "UsuarioDAO.getPrivilegiosCirculoAzulEcac[" + e.toString() + "]", (Exception)e);
            }
            catch (Exception e) {
                this.error.info((Object)"Exception.getPrivilegiosCirculoAzulEcac:", (Throwable)e);
                throw new CAException(-1, "UsuarioDAO.getPrivilegiosCirculoAzulEcac[" + e.toString() + "]", e);
            }
        }
        finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                    resultSet = null;
                }
                catch (Exception var11_16) {}
            }
            if (stmt != null) {
                try {
                    stmt.close();
                    stmt = null;
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
        return perfilTO;
    }

    public UsuarioTO validaUserPerfil(String lClave, String sIP) throws CAException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            if (lClave == null || lClave.equals(null) || lClave.equals("")) {
                throw new CAException(-1, "Debe ingresar su CLAVE de usuario.");
            }
            if (sIP == null || sIP.equals(null) || sIP.equals("")) {
                throw new CAException(-1, "Error al obtener la Direccion IP de la PC.");
            }
            String sQuery = "SELECT IDUSUARIO, NOMBRE, IDPERFIL FROM " + this.schema_database + "PTO_CTLUSUARIOS WHERE IDUSUARIO = ?  AND STATUS ='A' ";
            long inicioProceso = System.currentTimeMillis();
            this.logger.info((Object)("validaUserPerfil|Inicio Proceso|" + Constantes.DATEFORMTALog.format(new Date()) + "|" + inicioProceso));
            this.logger.info((Object)("validaUserPerfil|Antes de Conexion|" + Constantes.DATEFORMTALog.format(new Date()) + "|" + inicioProceso));
            connection = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
            this.logger.info((Object)("validaUserPerfil|Despues de Conexion|" + Constantes.DATEFORMTALog.format(new Date()) + "|" + (System.currentTimeMillis() - inicioProceso)));
            long inicioConsulta = System.currentTimeMillis();
            this.logger.info((Object)("validaUserPerfil|InicioConsulta|" + Constantes.DATEFORMTALog.format(new Date()) + "|" + inicioConsulta));
            connection = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
            preparedStatement = connection.prepareStatement(sQuery);
            preparedStatement.setString(1, lClave.toUpperCase());
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                UsuarioTO usuarioTO = new UsuarioTO();
                usuarioTO.setIdUsuario(resultSet.getString(1));
                usuarioTO.setNombre(resultSet.getString(2));
                usuarioTO.setPuntoVentaTO(new ConsultasDAO().obtienePuntoVenta(sIP, connection));
                this.logger.info((Object)("validaUserPerfil|FinConsulta|" + Constantes.DATEFORMTALog.format(new Date()) + "|" + (System.currentTimeMillis() - inicioConsulta)));
                this.logger.info((Object)("validaUserPerfil|FinProceso|" + Constantes.DATEFORMTALog.format(new Date()) + "|" + (System.currentTimeMillis() - inicioProceso)));
                UsuarioTO usuarioTO2 = usuarioTO;
                return usuarioTO2;
            }
            try {
                throw new CAException(-1, "La clave de Usuario no se encuentra en el Sistema de Puntos.");
            }
            catch (Exception e) {
                this.error.info((Object)"Exception.validaUserPerfil:", (Throwable)e);
                throw new CAException(-1, "UsuarioDAO.validaUserPerfil[" + e.toString() + "]", e);
            }
        } catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                    resultSet = null;
                }
                catch (Exception var14_13) {}
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                    preparedStatement = null;
                }
                catch (Exception var14_14) {}
            }
            if (connection != null) {
                try {
                    connection.close();
                    connection = null;
                }
                catch (Exception var14_15) {}
            }
        }
		return null;
    }

    public boolean agregaUsuario(UsuarioTO usuarioTO) throws Exception {
        boolean accion;
        accion = false;
        Connection conn = null;
        PreparedStatement prepStat = null;
        ResultSet resultSet = null;
        try {
            try {
                conn = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                StringBuffer query = new StringBuffer();
                query.append("SELECT IDUSUARIO FROM ").append(this.schema_database).append("PTO_CTLUSUARIOS ");
                query.append("where IDUSUARIO = ?");
                prepStat = conn.prepareStatement(query.toString());
                prepStat.setString(1, usuarioTO.getIdUsuario());
                resultSet = prepStat.executeQuery();
                boolean existeIdUsuario = false;
                if (resultSet.next()) {
                    existeIdUsuario = true;
                }
                if (existeIdUsuario) {
                    throw new Exception("El identificador de usuario ya existe. Elija otro.");
                }
                query = new StringBuffer();
                query.append("insert into ").append(this.schema_database).append("PTO_CTLUSUARIOS (NUMEMPLEADO,IDUSUARIO,NOMBRE,PASSWDENC,");
                query.append("STATUS,IDUSUARIOCAPTURA,FECHAUPDATE,SISTEMAADMIN,FECHAADMIN,IDPERFILN,IDPERFIL) ");
                query.append("values(?,?,?,?,?,?,?,?,?,?,?)");
                prepStat = conn.prepareStatement(query.toString());
                prepStat.setString(1, usuarioTO.getNumEmpleado());
                prepStat.setString(2, usuarioTO.getIdUsuario());
                prepStat.setString(3, usuarioTO.getNombre());
                prepStat.setString(4, usuarioTO.getPassword());
                prepStat.setString(5, usuarioTO.getStatus());
                prepStat.setString(6, usuarioTO.getIdUsuarioCaptura());
                prepStat.setTimestamp(7, usuarioTO.getFechaUpdate());
                prepStat.setString(8, usuarioTO.getSistemaAdmin());
                prepStat.setTimestamp(9, usuarioTO.getFechaAdmin());
                prepStat.setInt(10, usuarioTO.getPerfilTO().getIdPerfilN());
                prepStat.setString(11, usuarioTO.getPerfilTO().getIdPuesto());
                prepStat.executeUpdate();
            }
            catch (Exception ex) {
                throw new Exception("Usuario " + ex.toString());
            }
        }
        finally {
            if (prepStat != null) {
                try {
                    prepStat.close();
                    prepStat = null;
                }
                catch (Exception var9_13) {}
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                    resultSet = null;
                }
                catch (Exception var9_14) {}
            }
            if (conn != null) {
                try {
                    conn.close();
                    conn = null;
                }
                catch (Exception var9_15) {}
            }
        }
        return accion;
    }

    public boolean actualizaUsuario(UsuarioTO usuarioCA, UsuarioTO usuarioModificado, int perfilAnterior) throws Exception {
        boolean accion;
        accion = false;
        Connection conn = null;
        PreparedStatement prepStat = null;
        try {
            try {
                conn = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                StringBuffer query = new StringBuffer();
                if (usuarioModificado.getPassword() != null) {
                    query.append("update ").append(this.schema_database).append("PTO_CTLUSUARIOS set IDUSUARIO=?,NOMBRE=?,PASSWORD=?,");
                    query.append("STATUS=?,IDUSUARIOCAPTURA=?,FECHAUPDATE=?,IDPERFILN=?,IDPERFIL=?, ");
                    query.append("PASSWDENC=?,BANCAMBIAPASS=? ");
                    query.append("where NUMEMPLEADO=?");
                    prepStat = conn.prepareStatement(query.toString());
                    prepStat.setString(1, usuarioModificado.getIdUsuario());
                    prepStat.setString(2, usuarioModificado.getNombre());
                    prepStat.setString(3, null);
                    prepStat.setString(4, usuarioModificado.getStatus());
                    prepStat.setString(5, usuarioModificado.getIdUsuarioCaptura());
                    prepStat.setTimestamp(6, usuarioModificado.getFechaUpdate());
                    prepStat.setInt(7, usuarioModificado.getPerfilTO().getIdPerfilN());
                    prepStat.setString(8, usuarioModificado.getPerfilTO().getIdPuesto());
                    prepStat.setString(9, usuarioModificado.getPassword().trim());
                    prepStat.setString(10, "C");
                    prepStat.setString(11, usuarioModificado.getNumEmpleado());
                    prepStat.executeUpdate();
                    usuarioModificado.agregaMensaje(0, "Proceso Exitoso");
                } else {
                    query.append("update ").append(this.schema_database).append("PTO_CTLUSUARIOS set IDUSUARIO=?,NOMBRE=?,");
                    query.append("STATUS=?,IDUSUARIOCAPTURA=?,FECHAUPDATE=?,IDPERFILN=?,IDPERFIL=? ");
                    query.append("where NUMEMPLEADO=?");
                    prepStat = conn.prepareStatement(query.toString());
                    prepStat.setString(1, usuarioModificado.getIdUsuario());
                    prepStat.setString(2, usuarioModificado.getNombre());
                    prepStat.setString(3, usuarioModificado.getStatus());
                    prepStat.setString(4, usuarioModificado.getIdUsuarioCaptura());
                    prepStat.setTimestamp(5, usuarioModificado.getFechaUpdate());
                    prepStat.setInt(6, usuarioModificado.getPerfilTO().getIdPerfilN());
                    prepStat.setString(7, usuarioModificado.getPerfilTO().getIdPuesto());
                    prepStat.setString(8, usuarioModificado.getNumEmpleado());
                    prepStat.execute();
                    usuarioModificado.agregaMensaje(0, "Proceso Exitoso");
                }
                if (usuarioModificado.getPerfilTO().getIdPerfilN() != perfilAnterior) {
                    if (prepStat != null) {
                        prepStat.close();
                        prepStat = null;
                    }
                    query = new StringBuffer();
                    query.append("INSERT INTO ").append(this.schema_database).append("PTO_TBLHISTOCAMBIOPERFILUSR ");
                    query.append("VALUES(?,?,?,?,?)");
                    prepStat = conn.prepareStatement(query.toString());
                    prepStat.setString(1, usuarioModificado.getNumEmpleado());
                    prepStat.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
                    prepStat.setString(3, usuarioCA.getNumEmpleado());
                    prepStat.setInt(4, perfilAnterior);
                    prepStat.setInt(5, usuarioModificado.getPerfilTO().getIdPerfilN());
                    prepStat.execute();
                }
            }
            catch (Exception ex) {
                throw new Exception("Usuario " + ex.toString());
            }
        }
        finally {
            if (prepStat != null) {
                try {
                    prepStat.close();
                    prepStat = null;
                }
                catch (Exception var9_12) {}
            }
            if (conn != null) {
                try {
                    conn.close();
                    conn = null;
                }
                catch (Exception var9_13) {}
            }
        }
        return accion;
    }

    public boolean eliminaUsuario(UsuarioTO usuarioTO) throws Exception {
        boolean accion;
        accion = false;
        Connection conn = null;
        PreparedStatement prepStat = null;
        try {
            try {
                conn = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                StringBuffer query = new StringBuffer();
                query.append("update ").append(this.schema_database).append("PTO_CTLUSUARIOS set STATUS = 'I' ");
                query.append("where NUMEMPLEADO=?");
                prepStat = conn.prepareStatement(query.toString());
                prepStat.setString(1, usuarioTO.getNumEmpleado());
                prepStat.executeUpdate();
            }
            catch (Exception ex) {
                throw new Exception("Usuario " + ex.toString());
            }
        }
        finally {
            if (prepStat != null) {
                try {
                    prepStat.close();
                    prepStat = null;
                }
                catch (Exception var7_10) {}
            }
            if (conn != null) {
                try {
                    conn.close();
                    conn = null;
                }
                catch (Exception var7_11) {}
            }
        }
        return accion;
    }

    public UsuarioTO consultaUsuario(UsuarioTO usuarioTO) throws Exception {
        Connection conn = null;
        PreparedStatement prepStat = null;
        ResultSet resultSet = null;
        try {
            try {
                conn = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                StringBuffer query = new StringBuffer();
                query.append("SELECT A.IDUSUARIO,A.NOMBRE,A.PASSWORD,A.STATUS,");
                query.append("A.CONTADORPMP,P.IDPERFILN,P.IDPUESTO,P.DESCRIPCION,P.IDREGION,P.NIVELAUT ");
                query.append("FROM ").append(this.schema_database).append("PTO_CTLUSUARIOS A ");
                query.append("join ").append(this.schema_database).append("PTO_CTLPERFILN P on A.IDPERFILN=P.IDPERFILN ");
                query.append("WHERE A.NUMEMPLEADO = ?");
                prepStat = conn.prepareStatement(query.toString());
                prepStat.setString(1, usuarioTO.getNumEmpleado());
                resultSet = prepStat.executeQuery();
                if (resultSet.next()) {
                    usuarioTO.setNumEmpleado(usuarioTO.getNumEmpleado());
                    usuarioTO.setIdUsuario(resultSet.getString(1));
                    usuarioTO.setNombre(resultSet.getString(2));
                    usuarioTO.setPassword(resultSet.getString(3));
                    usuarioTO.setStatus(resultSet.getString(4));
                    usuarioTO.setContadorPMP(Integer.valueOf(resultSet.getInt(5)));
                    int idPerfiln = resultSet.getInt(6);
                    PerfilTO perfilTO = new PerfilTO();
                    perfilTO.setIdPerfilN(idPerfiln);
                    perfilTO.setIdPuesto(resultSet.getString(7));
                    perfilTO.setDescripcion(resultSet.getString(8));
                    perfilTO.setRegion(resultSet.getInt(9));
                    perfilTO.setNivelAutorizacion(resultSet.getInt(10));
                    usuarioTO.setPerfilTO(perfilTO);
                    usuarioTO.agregaMensaje(0, "Proceso Exitoso");
                } else {
                    usuarioTO.agregaMensaje(-1, "Usuario[" + usuarioTO.getNumEmpleado() + "] Inconsistente");
                }
            }
            catch (Exception e) {
                throw new Exception("Usuario consulta " + e.toString() + "]");
            }
        }
        finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                    resultSet = null;
                }
                catch (Exception var9_13) {}
            }
            if (prepStat != null) {
                try {
                    prepStat.close();
                    prepStat = null;
                }
                catch (Exception var9_14) {}
            }
            if (conn != null) {
                try {
                    conn.close();
                    conn = null;
                }
                catch (Exception var9_15) {}
            }
        }
        return usuarioTO;
    }

    public ArrayList<UsuarioTO> consultaUsuarios(UsuarioTO user, int max) throws CAException {
        ArrayList<UsuarioTO> lista;
        Connection conn = null;
        PreparedStatement prepStat = null;
        ResultSet resultSet = null;
        lista = new ArrayList<UsuarioTO>();
        try {
            try {
                conn = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                StringBuffer query = new StringBuffer();
                query.append("SELECT NUMEMPLEADO,IDUSUARIO,NOMBRE,IDPERFIL,STATUS, ");
                query.append("IDUSUARIOCAPTURA,FECHAUPDATE,SISTEMAADMIN,FECHAADMIN ");
                query.append("FROM ").append(this.schema_database).append("PTO_CTLUSUARIOS  ");
                query.append("WHERE  NUMEMPLEADO = NUMEMPLEADO ");
                if (user.getNumEmpleado() != null & !"".equals(user.getNumEmpleado())) {
                    query.append(" AND  NUMEMPLEADO = ?");
                }
                if (user.getIdUsuario() != null & !"".equals(user.getIdUsuario())) {
                    query.append(" AND  IDUSUARIO = ?");
                }
                if (user.getPerfil() != null & !"".equals(user.getPerfil())) {
                    query.append(" AND  IDPERFIL = ?");
                }
                if (user.getStatus() != null & !"".equals(user.getStatus())) {
                    query.append(" AND  STATUS = ?");
                }
                if (user.getFechaAdmin() != null & !"".equals(user.getFechaAdmin()) & user.getFechaUpdate() != null & !"".equals(user.getFechaUpdate())) {
                    query.append(" AND  (FECHAADMIN <= ? OR FECHAUPDATE <= ? )");
                }
                prepStat = conn.prepareStatement(query.toString());
                int cont = 1;
                if (user.getNumEmpleado() != null & !"".equals(user.getNumEmpleado())) {
                    prepStat.setString(cont++, user.getNumEmpleado());
                }
                if (user.getIdUsuario() != null & !"".equals(user.getIdUsuario())) {
                    prepStat.setString(cont++, user.getIdUsuario());
                }
                if (user.getPerfil() != null & !"".equals(user.getPerfil())) {
                    prepStat.setString(cont++, user.getPerfil());
                }
                if (user.getStatus() != null & !"".equals(user.getStatus())) {
                    prepStat.setString(cont++, user.getStatus());
                }
                resultSet = prepStat.executeQuery();
                if (max == 0) {
                    max = -1;
                }
                int contador = 0;
                while (resultSet.next()) {
                    if (contador != max) {
                        ++contador;
                        UsuarioTO userTemp = new UsuarioTO();
                        userTemp.setNumEmpleado(resultSet.getString("NUMEMPLEADO"));
                        userTemp.setIdUsuario(resultSet.getString("IDUSUARIO"));
                        userTemp.setNombre(resultSet.getString("NOMBRE"));
                        userTemp.setPerfil(resultSet.getString("IDPERFIL"));
                        userTemp.setStatus(resultSet.getString("STATUS"));
                        userTemp.setIdUsuarioCaptura(resultSet.getString("IDUSUARIOCAPTURA"));
                        userTemp.setFechaUpdate(resultSet.getTimestamp("FECHAUPDATE"));
                        userTemp.setFechaAdmin(resultSet.getTimestamp("FECHAADMIN"));
                        userTemp.setSistemaAdmin(resultSet.getString("SISTEMAADMIN"));
                        lista.add(userTemp);
                        continue;
                    } else {
                        break;
                    }
                }
            }
            catch (Exception e) {
                throw new CAException(0, "Error al obtener los usuarios " + e.toString() + "]");
            }
        }
        finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                    resultSet = null;
                }
                catch (Exception var12_16) {}
            }
            if (prepStat != null) {
                try {
                    prepStat.close();
                    prepStat = null;
                }
                catch (Exception var12_17) {}
            }
            if (conn != null) {
                try {
                    conn.close();
                    conn = null;
                }
                catch (Exception var12_18) {}
            }
        }
        return lista;
    }

    public boolean existeUsuario(UsuarioTO usuarioTO) throws Exception {
        boolean existe;
        Connection conn = null;
        PreparedStatement prepStat = null;
        ResultSet resultSet = null;
        existe = false;
        try {
            try {
                conn = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                StringBuffer query = new StringBuffer();
                query.append("SELECT IDUSUARIO ");
                query.append("FROM ").append(this.schema_database).append("PTO_CTLUSUARIOS ");
                query.append("WHERE NUMEMPLEADO = ?");
                prepStat = conn.prepareStatement(query.toString());
                prepStat.setString(1, usuarioTO.getNumEmpleado());
                resultSet = prepStat.executeQuery();
                if (resultSet.next()) {
                    existe = true;
                }
            }
            catch (Exception e) {
                throw new Exception("Error Usuario consulta " + e.toString() + "]");
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
        return existe;
    }

    public void fechaAcceso(UsuarioTO usuarioTO) throws Exception {
        Connection conn = null;
        PreparedStatement prepStat = null;
        try {
            try {
                conn = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                StringBuffer query = new StringBuffer();
                query.append("update ").append(this.schema_database).append("PTO_CTLUSUARIOS set FECHAACCESO= SYSDATE ");
                query.append("where NUMEMPLEADO=?");
                prepStat = conn.prepareStatement(query.toString());
                prepStat.setString(1, usuarioTO.getNumEmpleado());
                prepStat.executeUpdate();
            }
            catch (Exception ex) {
                throw new Exception("-1, Error al insertar la fecha de acceso " + ex.toString());
            }
        }
        finally {
            if (prepStat != null) {
                try {
                    prepStat.close();
                    prepStat = null;
                }
                catch (Exception var6_9) {}
            }
            if (conn != null) {
                try {
                    conn.close();
                    conn = null;
                }
                catch (Exception var6_10) {}
            }
        }
    }

    public boolean ValidaCambioPass(UsuarioTO usuarioTO) throws Exception {
        boolean CambioPass;
        Connection conn = null;
        PreparedStatement prepStat = null;
        ResultSet resultSet = null;
        CambioPass = false;
        try {
            try {
                conn = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                StringBuffer query = new StringBuffer();
                query.append("SELECT NUMEMPLEADO FROM  ").append(this.schema_database).append("PTO_CTLUSUARIOS WHERE ");
                query.append(" FECHAUPDATE < SYSDATE - 60 AND STATUS = 'A' AND NUMEMPLEADO=?");
                prepStat = conn.prepareStatement(query.toString());
                prepStat.setString(1, usuarioTO.getNumEmpleado());
                resultSet = prepStat.executeQuery();
                if (resultSet.next()) {
                    CambioPass = true;
                }
            }
            catch (Exception ex) {
                throw new Exception("-1, Error al consultar si el usuario modifico la contrase\u00f1a despues de 60 dias " + ex.toString() + "UsuarioDAO.ValidaCAmbioPass");
            }
        }
        finally {
            if (prepStat != null) {
                try {
                    prepStat.close();
                    prepStat = null;
                }
                catch (Exception var8_12) {}
            }
            if (conn != null) {
                try {
                    conn.close();
                    conn = null;
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
        }
        return CambioPass;
    }
}

