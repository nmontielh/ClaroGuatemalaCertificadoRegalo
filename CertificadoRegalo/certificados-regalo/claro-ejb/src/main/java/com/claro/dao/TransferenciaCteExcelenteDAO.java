/*
 * Decompiled with CFR 0_102.
 * 
 * Could not load the following classes:
 *  com.claro.exception.CAException
 *  com.claro.seguridad.SeguridadCaUtil
 *  com.claro.transfer.MensajeTO
 *  com.claro.transfer.MobileTO
 *  com.claro.transfer.ParametrosTO
 *  com.claro.transfer.PerfilTO
 *  com.claro.transfer.PuntosTO
 *  com.claro.transfer.TelefonoTO
 *  com.claro.transfer.transpuntos.TransferenciaTO
 *  com.claro.util.Constantes
 *  com.claro.util.ServiceLocator
 *  com.claro.util.Utils
 *  org.apache.log4j.Logger
 */
package com.claro.dao;

import com.claro.dao.ConsultaM2KDAO;
import com.claro.dao.ConsultasDAO;
import com.claro.dao.PuntosDAO;
import com.claro.dao.TranasferenciaDAO;
import com.claro.exception.CAException;
import com.claro.seguridad.SeguridadCaUtil;
import com.claro.transfer.MensajeTO;
import com.claro.transfer.MobileTO;
import com.claro.transfer.ParametrosTO;
import com.claro.transfer.PerfilTO;
import com.claro.transfer.PuntosTO;
import com.claro.transfer.TelefonoTO;
import com.claro.transfer.transpuntos.TransferenciaTO;
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
import org.apache.log4j.Logger;

public class TransferenciaCteExcelenteDAO
extends TranasferenciaDAO {
    public TransferenciaTO transferirPuntosCteExc(TransferenciaTO _transfTO) throws CAException {
        Connection cnx = null;
        PuntosTO puntosOrigenTO = new PuntosTO();
        int ptsDispOrigen = 0;
        int puntosTrasnferidos = 0;
        PuntosDAO ptsDAO = new PuntosDAO();
        MensajeTO msgTO = new MensajeTO();
        PuntosTO ptsTO = null;
        try {
            try {
                long inicioProceso = System.currentTimeMillis();
                this.logger.info((Object)("transferirPorCteExcelente|Inicio|" + Constantes.DATEFORMTALog.format(new java.util.Date()) + "|" + inicioProceso));
                puntosOrigenTO = this.consultasPuntosDAO.obtienePuntos(_transfTO.getCuentaOrigen(), _transfTO.getSecuenciaOrigen());
                if (puntosOrigenTO.getIdMensaje() != 0) {
                    throw new CAException(-1, "SE PRODUJO UN ERROR AL CONSULTAR LOS PUNTOS DE LA LINEA [" + _transfTO.getTelefonoOrigen() + "]");
                }
                ptsDispOrigen = puntosOrigenTO.getPtosDisponibles();
                _transfTO.setPtosDisponiblesOrigen(ptsDispOrigen);
                puntosTrasnferidos = _transfTO.getPuntosTrasnferidos();
                TelefonoTO phoneDestinoTO = this.validaInfoTransferenciaCteExc(_transfTO);
                if (phoneDestinoTO.getIdMensaje() == -1) {
                    throw new CAException(-1, String.valueOf(phoneDestinoTO.getMensaje()) + "  [LINEA DESTINO]");
                }
                cnx = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                cnx.setAutoCommit(false);
                _transfTO.setPuntosOrigenTO(puntosOrigenTO);
                if (!this.existeLinea(cnx, _transfTO.getCuentaDestino())) {
                    this.crearLineaPuntos(cnx, phoneDestinoTO, _transfTO.getRegionDestino());
                } else {
                    PuntosDAO puntosDAO = new PuntosDAO();
                    puntosDAO.actualizaDatosLineaM2K(phoneDestinoTO.getMobileTO(), cnx, phoneDestinoTO);
                }
                this.actualizaInfoPuntosOrigen(puntosTrasnferidos, ptsDispOrigen, puntosOrigenTO);
                if (!this.existeTotales(cnx, _transfTO.getCuentaDestino())) {
                    this.creaTotalesLinea(cnx, _transfTO.getPuntosTrasnferidos(), _transfTO.getCuentaDestino(), phoneDestinoTO.getMobileTO().getSecuencia());
                } else {
                    this.actualizaTotalesDestino(cnx, _transfTO.getPuntosTrasnferidos(), _transfTO.getCuentaDestino(), phoneDestinoTO.getMobileTO().getSecuencia());
                }
                ptsTO = this.consultasPuntosDAO.obtienePuntos(_transfTO.getCuentaDestino(), Integer.parseInt(phoneDestinoTO.getMobileTO().getSecuencia()));
                ptsTO.setPtosStatus(ptsTO.getEstatusPuntos());
                phoneDestinoTO.setPuntosTO(ptsTO);
                phoneDestinoTO.getPuntosTO().setPtsExcedentes(phoneDestinoTO.getPuntosTO().getPtsExcedentes() + _transfTO.getPuntosTrasnferidos());
                if (!this.actualizaTotalesOrigen(cnx, _transfTO.getCuentaOrigen(), _transfTO.getSecuenciaOrigen(), puntosOrigenTO)) {
                    cnx.rollback();
                    throw new CAException(-1, "NO FUE POSIBLE ACTUALIZAR LOS TOTALES DE LA LINEA ORIGEN [" + _transfTO.getTelefonoOrigen() + "]");
                }
                if (!this.guardarDetalleLinea(cnx, _transfTO, phoneDestinoTO, 1)) {
                    throw new CAException(-1, "NO SE PUDO SALVAR EL DETALLE DE LA LINEA ORIGEN [" + _transfTO.getTelefonoOrigen() + "]");
                }
                String comntOrigen = this.crearComentario(puntosTrasnferidos, "", _transfTO.getCuentaDestino(), _transfTO.getIdUsuario(), _transfTO.getComentario(), 1);
                msgTO = ptsDAO.insertaComentarioTMP(cnx, _transfTO.getRegionOrigen(), _transfTO.getCuentaLineaOrigen(), _transfTO.getIdUsuario(), System.currentTimeMillis(), comntOrigen);
                if (msgTO.getIdMensaje() != 0) {
                    cnx.rollback();
                    throw new CAException(-1, "NO SE PUDO SALVAR EL COMENTARIO DE LA LINEA ORIGEN.");
                }
                if (!this.guardarDetalleLinea(cnx, _transfTO, phoneDestinoTO, 2)) {
                    throw new CAException(-1, "NO SE PUDO SALVAR EL DETALLE DE LA LINEA DESTINO [" + _transfTO.getTelefonoDestino() + "]");
                }
                String comntDestino = this.crearComentario(puntosTrasnferidos, _transfTO.getCuentaOrigen(), "", _transfTO.getIdUsuario(), _transfTO.getComentario(), 2);
                msgTO = ptsDAO.insertaComentarioTMP(cnx, _transfTO.getRegionDestino(), _transfTO.getCuentaDestino(), _transfTO.getIdUsuario(), System.currentTimeMillis(), comntDestino);
                if (msgTO.getIdMensaje() != 0) {
                    throw new CAException(-1, "NO SE PUDO SALVAR EL COMENTARIO DE LA LINEA DESTINO.");
                }
                _transfTO.setPuntosOrigenTO(puntosOrigenTO);
                _transfTO.setTelefonoTO(phoneDestinoTO);
                cnx.commit();
                this.logger.info((Object)("TransferenciaCteExcelenteDAO|Fin|" + Constantes.DATEFORMTALog.format(new java.util.Date()) + "|" + (System.currentTimeMillis() - inicioProceso)));
            }
            catch (SQLException se) {
                if (cnx != null) {
                    try {
                        cnx.rollback();
                    }
                    catch (Exception var10_15) {
                        // empty catch block
                    }
                }
                se.printStackTrace();
                this.error.info((Object)"Exception.transferirPuntosCteExc:", (Throwable)se);
                throw new CAException(-1, "TransferenciaCteExcelenteDAO.transferirPuntosCteExc[" + se.toString() + "]", (Exception)se);
            }
            catch (Exception e) {
                if (cnx != null) {
                    try {
                        cnx.rollback();
                    }
                    catch (Exception var10_16) {
                        // empty catch block
                    }
                }
                e.printStackTrace();
                this.error.info((Object)"Exception.transferirPuntosCteExc:", (Throwable)e);
                throw new CAException(-1, "TransferenciaCteExcelenteDAO.transferirPuntosCteExc[" + e.toString() + "]", e);
            }
        }
        finally {
            if (cnx != null) {
                try {
                    cnx.setAutoCommit(true);
                    cnx.close();
                    cnx = null;
                }
                catch (Exception var15_19) {}
            }
        }
        return _transfTO;
    }

    private TelefonoTO validaInfoTransferenciaCteExc(TransferenciaTO _transfTO) throws CAException {
        Connection cnx = null;
        MobileTO mobileDestinoTO = null;
        TelefonoTO phoneDestinoTO = new TelefonoTO();
        ParametrosTO paramsTO = null;
        try {
            paramsTO = new ParametrosTO();
            paramsTO.setTelefono(_transfTO.getTelefonoDestino());
            paramsTO.setCuenta(_transfTO.getCuentaDestino());
            paramsTO.setRegion(_transfTO.getRegionDestino());
            mobileDestinoTO = this.m2kDAO.consultaDatosM2K(paramsTO);
            if (mobileDestinoTO.getIdMensaje() == -1) {
                phoneDestinoTO.agregaMensaje(mobileDestinoTO.getIdMensaje(), mobileDestinoTO.getMensaje());
                TelefonoTO telefonoTO = phoneDestinoTO;
                return telefonoTO;
            }
            try {
                cnx = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                phoneDestinoTO.setMobileTO(mobileDestinoTO);
                phoneDestinoTO.setSAnacr("0.9");
                phoneDestinoTO.setEstatusCarta(phoneDestinoTO.getMobileTO().getStatus());
                phoneDestinoTO.setTecnologia(phoneDestinoTO.getMobileTO().getTecnologia());
                if (_transfTO.getRegionOrigen() == 9 || _transfTO.getRegionDestino() == 9) {
                    throw new CAException(-1, "LA TRANSFERENCIA NO APLICA A R9");
                }
                if (_transfTO.getCuentaOrigen().equals(_transfTO.getCuentaDestino()) || _transfTO.getTelefonoOrigen().equals(_transfTO.getTelefonoDestino())) {
                    throw new CAException(-1, "LA CUENTA Y/O TELEFONO SON IGUALES.");
                }
                if (_transfTO.getPtosDisponiblesOrigen() <= 0) {
                    throw new CAException(-1, "NO HAY PUNTOS EN LA LINEA ORIGEN PARA LA TRANSFERENCIA.");
                }
                if (_transfTO.getPuntosTrasnferidos() > _transfTO.getPtosDisponiblesOrigen()) {
                    throw new CAException(-1, "LOS PUNTOS A TRANSFERIR SON MAYORES A LOS DISPONIBLES.");
                }
                if (!this.lineaClienteExcelente(cnx, _transfTO.getTelefonoOrigen(), _transfTO.getRegionOrigen(), _transfTO.getCuentaOrigen())) {
                    throw new CAException(-1, "LA LINEA ORIGEN NO ES CLIENTE EXCELENTE.");
                }
                if (!phoneDestinoTO.getMobileTO().getStatus().equals("AC")) {
                    throw new CAException(-1, "LA LINEA DESTINO DEBE ESTAR ACTIVA.");
                }
                if (!_transfTO.getCuentaOrigen().equals(_transfTO.getCuentaLineaOrigen())) {
                    throw new CAException(-1, "CUENTA ORIGEN INCONSISTENTE EN M2K Y PUNTOS.");
                }
                if (!(mobileDestinoTO.getCuenta().equals(_transfTO.getCuentaDestino()) && mobileDestinoTO.getTelefono().equals(_transfTO.getTelefonoDestino()))) {
                    throw new CAException(-1, "CUENTA Y/O TELEFONO DESTINO INCONSISTENTE EN M2K.");
                }
                if (!_transfTO.getRfcOrigen().equals(mobileDestinoTO.getRfc())) {
                    throw new CAException(-1, "LAS LINEAS NO PERTENECEN AL MISMO CLIENTE.");
                }
                if (!(Utils.diferenciaDiasTransf((String)phoneDestinoTO.getMobileTO().getFecAltaUser()) <= 90 || SeguridadCaUtil.getInstance().validaPerfilProcesoCa(_transfTO.getPerfilTO(), "162"))) {
                    throw new CAException(-1, "LA FECHA PARA REALIZAR LA TRANSFERENCIA YA EXPIR\u00d3.");
                }
                if (this.validaTraspasoCteExc(cnx, _transfTO.getCuentaOrigen())) {
                    throw new CAException(-1, "YA CUENTA CON UNA TRANSFERENCIA PREVIA.");
                }
                if (!(_transfTO.getRegionOrigen() == _transfTO.getRegionDestino() || SeguridadCaUtil.getInstance().validaPerfilProcesoCa(_transfTO.getPerfilTO(), "162"))) {
                    throw new CAException(-1, " SU PERFIL NO TIENE LOS PRIVILEGIOS PARA REALIZAR LA TRANSFERENCIA ENTRE REGIONALES.");
                }
            }
            catch (SQLException se) {
                se.printStackTrace();
                this.error.info((Object)"Exception.validaInfoTransferenciaCteExc:", (Throwable)se);
                throw new CAException(-1, "TransferenciaCteExcelenteDAO.validaInfoTransferenciaCteExc[" + se.toString() + "]", (Exception)se);
            }
            catch (Exception e) {
                e.printStackTrace();
                this.error.info((Object)"Exception.validaInfoTransferenciaCteExc:", (Throwable)e);
                throw new CAException(-1, "TransferenciaCteExcelenteDAO.validaInfoTransferenciaCteExc[" + e.toString() + "]", e);
            }
        }
        finally {
            if (cnx != null) {
                try {
                    cnx.close();
                    cnx = null;
                }
                catch (Exception var9_7) {}
            }
        }
        return phoneDestinoTO;
    }

    private boolean actualizaTotalesDestino(Connection _cnx, int puntosTransferidos, String _ctaDest, String _secDest) throws CAException {
        int rows;
        PreparedStatement ps = null;
        StringBuffer qry = new StringBuffer();
        rows = 0;
        try {
            try {
                qry.append("UPDATE " + this.schema_database + "PTO_TBLTOTALES ");
                qry.append(" SET PUNTOSEXCEDENTES  = (PUNTOSEXCEDENTES + ?)");
                qry.append(" WHERE  CUENTA = ?");
                qry.append(" AND SECUENCIA = ?");
                ps = _cnx.prepareStatement(qry.toString());
                ps.setInt(1, puntosTransferidos);
                ps.setString(2, _ctaDest);
                ps.setString(3, _secDest);
                rows = ps.executeUpdate();
            }
            catch (SQLException se) {
                se.printStackTrace();
                this.error.info((Object)"Exception.actualizaTotalesDestino:", (Throwable)se);
                throw new CAException(-1, "TransferenciaCteExcelenteDAO.actualizaTotalesDestino[" + se.toString() + "]", (Exception)se);
            }
            catch (Exception e) {
                e.printStackTrace();
                this.error.info((Object)"Exception.actualizaTotalesDestino:", (Throwable)e);
                throw new CAException(-1, "TransferenciaCteExcelenteDAO.actualizaTotalesDestino[" + e.toString() + "]", e);
            }
        }
        finally {
            if (ps != null) {
                try {
                    ps.close();
                    ps = null;
                }
                catch (Exception var10_12) {}
            }
        }
        if (rows > 0) {
            return true;
        }
        return false;
    }

    private boolean actualizaTotalesOrigen(Connection _cnx, String _ctaOrigen, int _secuenciaOrigen, PuntosTO _puntosTO) throws CAException {
        int rows;
        PreparedStatement ps = null;
        rows = 0;
        try {
            try {
                ps = _cnx.prepareStatement("UPDATE " + this.schema_database + "PTO_TBLTOTALES " + "  \tSET PUNTOSACAD  = ?, " + "\t\tPUNTOSACAD1 = ?, " + "\t\tPUNTOSACAD2 = ?, " + "\t\tPUNTOSPROMOCION = ?, " + "\t\tPUNTOSANTIGUEDAD = ?, " + "\t\tPUNTOSEXCEDENTES = ?, " + "\t\tPUNTOSRENTA = ? " + " WHERE CUENTA = ? " + "\tAND SECUENCIA = ?");
                ps.setInt(1, _puntosTO.getPtsPorVencer());
                ps.setInt(2, _puntosTO.getPtsPorVencer1());
                ps.setInt(3, _puntosTO.getPtsPorVencer2());
                ps.setInt(4, _puntosTO.getPtsPromocion());
                ps.setInt(5, _puntosTO.getPtsAntiguedad());
                ps.setInt(6, _puntosTO.getPtsExcedentes());
                ps.setInt(7, _puntosTO.getPtsRenta());
                ps.setString(8, _ctaOrigen);
                ps.setInt(9, _secuenciaOrigen);
                rows = ps.executeUpdate();
            }
            catch (SQLException se) {
                se.printStackTrace();
                this.error.info((Object)"Exception.actualizaTotalesOrigen:", (Throwable)se);
                throw new CAException(-1, "TransferenciaCteExcelenteDAO.actualizaTotalesOrigen[" + se.toString() + "]", (Exception)se);
            }
            catch (Exception e) {
                e.printStackTrace();
                this.error.info((Object)"Exception.actualizaTotalesOrigen:", (Throwable)e);
                throw new CAException(-1, "TransferenciaCteExcelenteDAO.actualizaTotalesOrigen[" + e.toString() + "]", e);
            }
        }
        finally {
            if (ps != null) {
                try {
                    ps.close();
                    ps = null;
                }
                catch (Exception var9_11) {}
            }
        }
        if (rows > 0) {
            return true;
        }
        return false;
    }

    protected boolean guardarDetalleLinea(Connection _cnx, TransferenciaTO _transfTO, TelefonoTO _telefonoTO, int _tipo) throws CAException {
        int rows;
        PreparedStatement ps = null;
        StringBuffer referencia = new StringBuffer(255);
        StringBuffer qry = new StringBuffer(255);
        rows = 0;
        int ptsTransf = 0;
        try {
            try {
                qry.append("INSERT INTO ").append(this.schema_database).append("PTO_TBLMSTRDETALLE");
                qry.append("(CUENTA, SECUENCIA, LINEA, FECHAFAC, FECHAOPERACION, IDMOVTO,");
                qry.append(" IDUSUARIO, NUMPUNTOS, NUMPUNTOSEXC, TOTAJUSTES, IDBONOPROM, REFERENCIA) ");
                qry.append("VALUES ").append("(?,?,?,?,?,?,?,?,?,?,?,?)");
                if (_tipo == 1) {
                    referencia.append("Traspaso de ptos a la cuenta: ").append(_transfTO.getCuentaDestino());
                    referencia.append(", atendio: ").append(_transfTO.getIdUsuario());
                    referencia.append(" ").append(_transfTO.getComentario());
                    ptsTransf = _transfTO.getPuntosTrasnferidos() * -1;
                    ps = _cnx.prepareStatement(qry.toString());
                    ps.setString(1, _transfTO.getCuentaOrigen());
                    ps.setInt(2, _transfTO.getSecuenciaOrigen());
                    ps.setString(3, _transfTO.getTelefonoOrigen());
                    ps.setDate(4, new Date(System.currentTimeMillis()));
                    ps.setDate(5, new Date(System.currentTimeMillis()));
                    ps.setInt(6, 56);
                    ps.setString(7, _transfTO.getIdUsuario());
                    ps.setInt(8, 0);
                    ps.setInt(9, 0);
                    ps.setInt(10, ptsTransf);
                    ps.setNull(11, 12);
                    ps.setString(12, referencia.toString());
                } else if (_tipo == 2) {
                    referencia.append("Recepcion de ptos de la cuenta: ").append(_transfTO.getCuentaOrigen());
                    referencia.append(", atendio: ").append(_transfTO.getIdUsuario());
                    referencia.append(" ").append(_transfTO.getComentario());
                    ps = _cnx.prepareStatement(qry.toString());
                    ps.setString(1, _transfTO.getCuentaDestino());
                    ps.setString(2, _telefonoTO.getMobileTO().getSecuencia());
                    ps.setString(3, _transfTO.getTelefonoDestino());
                    ps.setDate(4, new Date(System.currentTimeMillis()));
                    ps.setDate(5, new Date(System.currentTimeMillis()));
                    ps.setInt(6, 56);
                    ps.setString(7, _transfTO.getIdUsuario());
                    ps.setInt(8, 0);
                    ps.setInt(9, 0);
                    ps.setInt(10, _transfTO.getPuntosTrasnferidos());
                    ps.setNull(11, 12);
                    ps.setString(12, referencia.toString());
                }
                rows = ps.executeUpdate();
            }
            catch (SQLException se) {
                se.printStackTrace();
                this.error.info((Object)"Exception.guardarDetalleLinea:", (Throwable)se);
                throw new CAException(-1, "TransferenciaCteExcelenteDAO.guardarDetalleLinea[" + se.toString() + "]", (Exception)se);
            }
            catch (Exception e) {
                e.printStackTrace();
                this.error.info((Object)"Exception.guardarDetalleLinea:", (Throwable)e);
                throw new CAException(-1, "TransferenciaCteExcelenteDAO.guardarDetalleLinea[" + e.toString() + "]", e);
            }
        }
        finally {
            if (ps != null) {
                try {
                    ps.close();
                    ps = null;
                }
                catch (Exception var12_14) {}
            }
        }
        if (rows > 0) {
            return true;
        }
        return false;
    }

    protected String crearComentario(int _ptsTransf, String _ctaOrigen, String _ctaDestino, String _usuario, String _referencia, int _tipo) throws CAException {
        StringBuffer referencia = new StringBuffer(255);
        if (_tipo == 1) {
            referencia.append("CIR - TRASPASO DE [").append(_ptsTransf);
            referencia.append("] PUNTOS A LA CUENTA [").append(_ctaDestino);
            referencia.append("], ATENDIO [").append(_usuario);
            referencia.append("] ").append(_referencia);
        } else if (_tipo == 2) {
            referencia.append("CIR - RECEPCION DE [").append(_ptsTransf);
            referencia.append("] PUNTOS DE LA CUENTA [" + _ctaOrigen);
            referencia.append("], ATENDIO [").append(_usuario);
            referencia.append("] ").append(_referencia);
        }
        return referencia.toString();
    }

    public MensajeTO insertaComentarioTMP(Connection lConn, int region, String cuenta, String usuario, long fechaTransaccion, String comentario) throws CAException {
        MensajeTO mensajeTO;
        Connection connection = null;
        PreparedStatement statement = null;
        mensajeTO = new MensajeTO();
        String sCadenaInsert = "INSERT INTO " + this.schema_database + "PTO_TMP_COMNT  (REGION,FECHA,CUENTA,USUARIO,COMENTARIOS)" + " VALUES(?,?,?,?,?)";
        try {
            try {
                connection = lConn != null && lConn != null && !lConn.isClosed() ? lConn : ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                statement = connection.prepareStatement(sCadenaInsert);
                statement.setInt(1, region);
                statement.setTimestamp(2, new Timestamp(fechaTransaccion));
                statement.setString(3, cuenta);
                statement.setString(4, usuario);
                statement.setString(5, comentario);
                if (statement.executeUpdate() > 0) {
                    mensajeTO.agregaMensaje(0, "Proceso Exitoso");
                } else {
                    mensajeTO.agregaMensaje(1, "Error al insertar Comentario");
                }
            }
            catch (SQLException e) {
                throw new CAException(-1, "PuntosDAO.insertaComentario[" + e.toString() + "]", (Exception)e);
            }
            catch (Exception e) {
                throw new CAException(-1, "PuntosDAO.insertaComentario[" + e.toString() + "]", e);
            }
        }
        finally {
            if (statement != null) {
                try {
                    statement.close();
                    statement = null;
                }
                catch (Exception var14_15) {}
            }
        }
        return mensajeTO;
    }

    private boolean lineaClienteExcelente(Connection _cnx, String _lineaOrigen, int _region, String _cuenta) throws CAException {
        boolean existe;
        PreparedStatement ps = null;
        ResultSet rs = null;
        existe = false;
        try {
            try {
                ps = _cnx.prepareStatement("SELECT LINEA   FROM " + this.schema_database + "PTO_TBLLINEASCTEEXC " + " WHERE CUENTA = ?" + "   AND LINEA = ?" + "   AND IDREGION = ?");
                ps.setString(1, _cuenta);
                ps.setString(2, _lineaOrigen);
                ps.setInt(3, _region);
                rs = ps.executeQuery();
                if (rs.next()) {
                    existe = true;
                }
            }
            catch (SQLException se) {
                se.printStackTrace();
                this.error.info((Object)"Exception.validaClienteExcelente:", (Throwable)se);
                throw new CAException(-1, "TransferenciaCteExcelenteDAO.validaClienteExcelente[" + se.toString() + "]", (Exception)se);
            }
            catch (Exception e) {
                e.printStackTrace();
                this.error.info((Object)"Exception.validaClienteExcelente:", (Throwable)e);
                throw new CAException(-1, "TransferenciaCteExcelenteDAO.validaClienteExcelente[" + e.toString() + "]", e);
            }
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                    rs = null;
                }
                catch (Exception var10_13) {}
            }
            if (ps != null) {
                try {
                    ps.close();
                    ps = null;
                }
                catch (Exception var10_14) {}
            }
        }
        return existe;
    }

    private boolean validaTraspasoCteExc(Connection _cnx, String _ctaOrigen) throws CAException {
        boolean bandera;
        PreparedStatement ps = null;
        ResultSet rs = null;
        bandera = false;
        try {
            try {
                ps = _cnx.prepareStatement("SELECT max(FECHAOPERACION) FROM " + this.schema_database + "PTO_TBLMSTRDETALLE " + " WHERE IDMOVTO = 56  AND CUENTA = ?");
                ps.setString(1, _ctaOrigen);
                rs = ps.executeQuery();
                if (rs.next() && rs.getString(1) != null && Utils.diferenciaDiasTransf((String)rs.getString(1)) <= 180) {
                    bandera = true;
                }
            }
            catch (SQLException se) {
                se.printStackTrace();
                this.error.info((Object)"Exception.validaTraspasoCteExc:", (Throwable)se);
                throw new CAException(-1, "TransferenciaCteExcelenteDAO.validaTraspasoCteExc[" + se.toString() + "]", (Exception)se);
            }
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                    rs = null;
                }
                catch (Exception var8_10) {}
            }
            if (ps != null) {
                try {
                    ps.close();
                    ps = null;
                }
                catch (Exception var8_11) {}
            }
        }
        return bandera;
    }

    private PuntosTO recalculaPuntosDestino(Connection _cnx, String _ctaDestino, String _secDest) throws CAException {
        PuntosTO ptsTO;
        block17 : {
            PreparedStatement ps = null;
            ResultSet rs = null;
            ptsTO = null;
            try {
                try {
                    ps = _cnx.prepareStatement("SELECT PUNTOSANTIGUEDAD, PUNTOSPROMOCION, PUNTOSREDIM, PUNTOSTRANSF,        PUNTOSACAD, FECHACAD, PUNTOSACAD1, BONOEQUIPO, FECHACAD1,        PUNTOSACAD2, FECHACAD2, PUNTOSRENTA, PUNTOSEXCEDENTES,        PUNTOSCADUC, FECHACADU,        SUM(b.PUNTOSEXCEDENTES + b.PUNTOSACAD + b.PUNTOSACAD1 +            b.PUNTOSACAD2 + b.PUNTOSRENTA + b.PUNTOSANTIGUEDAD +            b.PUNTOSPROMOCION) Puntos_Disponibles   FROM " + this.schema_database + "PTO_TBLTOTALES b " + " WHERE CUENTA = ? " + "   AND SECUENCIA = ? " + " GROUP BY PUNTOSANTIGUEDAD, PUNTOSPROMOCION, PUNTOSREDIM, PUNTOSTRANSF," + " \t\t   PUNTOSACAD, FECHACAD, PUNTOSACAD1, BONOEQUIPO, FECHACAD1," + " \t\t   PUNTOSACAD2, FECHACAD2, PUNTOSRENTA, PUNTOSEXCEDENTES,PUNTOSCADUC," + " \t\t   FECHACADU,PUNTOSPROMOCION");
                    ps.setString(1, _ctaDestino);
                    ps.setString(2, _secDest);
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        ptsTO = new PuntosTO();
                        ptsTO.setPtsRedimidos(rs.getInt("PuntosRedim"));
                        ptsTO.setPtsTransferidos(rs.getInt("PuntosTransf"));
                        ptsTO.setPtsPorVencer(rs.getInt("PuntosAcad"));
                        ptsTO.setFecVencer(rs.getDate("FechaCad"));
                        ptsTO.setPtsPorVencer1(rs.getInt("PuntosAcad1"));
                        ptsTO.setFecVencer1(rs.getDate("FechaCad1"));
                        ptsTO.setPtsPorVencer2(rs.getInt("PuntosAcad2"));
                        ptsTO.setFecVencer2(rs.getDate("FechaCad2"));
                        ptsTO.setPtsRenta(rs.getInt("PuntosRenta"));
                        ptsTO.setPtsExcedentes(rs.getInt("PuntosExcedentes"));
                        ptsTO.setPtsVencidos(rs.getInt("PuntosCaduc"));
                        ptsTO.setFecVencidos(rs.getDate("FechaCadu"));
                        ptsTO.setPtsAntiguedad(rs.getInt("PuntosAntiguedad"));
                        ptsTO.setPtsPromocion(rs.getInt("PuntosPromocion"));
                        ptsTO.setPtosDisponibles(rs.getInt("Puntos_Disponibles"));
                        ptsTO.setPtosDisponiblesTmp(rs.getInt("Puntos_Disponibles"));
                        ptsTO.setBonoEquipo(rs.getInt("BonoEquipo"));
                        break block17;
                    }
                    throw new CAException(-1, "NO SE ENCONTRO LA CUENTA [" + _ctaDestino + "] EN TOTALES.");
                }
                catch (SQLException se) {
                    se.printStackTrace();
                    this.error.info((Object)"Exception.recalculaPuntosDestino:", (Throwable)se);
                    throw new CAException(-1, "TransferenciaCaregDAO.recalculaPuntosDestino[" + se.toString() + "]", (Exception)se);
                }
                catch (Exception e) {
                    e.printStackTrace();
                    this.error.info((Object)"Exception.recalculaPuntosDestino:", (Throwable)e);
                    throw new CAException(-1, "TransferenciaCaregDAO.recalculaPuntosDestino[" + e.toString() + "]", e);
                }
            }
            finally {
                if (rs != null) {
                    try {
                        rs.close();
                        rs = null;
                    }
                    catch (Exception var9_12) {}
                }
                if (ps != null) {
                    try {
                        ps.close();
                        ps = null;
                    }
                    catch (Exception var9_13) {}
                }
            }
        }
        return ptsTO;
    }

    private void actualizaInfoPuntosOrigen(int puntosTrasnferidos, int ptsDispOrigen, PuntosTO puntosOrigenTO) throws CAException {
        int[] arrTiposPuntos = new int[7];
        if (puntosTrasnferidos < ptsDispOrigen) {
            arrTiposPuntos[0] = puntosOrigenTO.getPtsPorVencer();
            arrTiposPuntos[1] = puntosOrigenTO.getPtsPorVencer1();
            arrTiposPuntos[2] = puntosOrigenTO.getPtsPorVencer2();
            arrTiposPuntos[3] = puntosOrigenTO.getPtsPromocion();
            arrTiposPuntos[4] = puntosOrigenTO.getPtsAntiguedad();
            arrTiposPuntos[5] = puntosOrigenTO.getPtsExcedentes();
            arrTiposPuntos[6] = puntosOrigenTO.getPtsRenta();
            for (int i = 0; i < 7; ++i) {
                if (arrTiposPuntos[i] <= 0) continue;
                if (arrTiposPuntos[i] < puntosTrasnferidos) {
                    puntosTrasnferidos-=arrTiposPuntos[i];
                    arrTiposPuntos[i] = 0;
                    continue;
                }
                arrTiposPuntos[i] = arrTiposPuntos[i] - puntosTrasnferidos;
                puntosTrasnferidos = 0;
                break;
            }
            puntosOrigenTO.setPtsPorVencer(arrTiposPuntos[0]);
            puntosOrigenTO.setPtsPorVencer1(arrTiposPuntos[1]);
            puntosOrigenTO.setPtsPorVencer2(arrTiposPuntos[2]);
            puntosOrigenTO.setPtsPromocion(arrTiposPuntos[3]);
            puntosOrigenTO.setPtsAntiguedad(arrTiposPuntos[4]);
            puntosOrigenTO.setPtsExcedentes(arrTiposPuntos[5]);
            puntosOrigenTO.setPtsRenta(arrTiposPuntos[6]);
        } else {
            puntosOrigenTO.setPtsPorVencer(0);
            puntosOrigenTO.setPtsPorVencer1(0);
            puntosOrigenTO.setPtsPorVencer2(0);
            puntosOrigenTO.setPtsPromocion(0);
            puntosOrigenTO.setPtsAntiguedad(0);
            puntosOrigenTO.setPtsExcedentes(0);
            puntosOrigenTO.setPtsRenta(0);
        }
    }

    private boolean existeLinea(Connection cnx, String _cuentaDest) throws CAException {
        boolean existe;
        PreparedStatement ps = null;
        ResultSet rs = null;
        existe = false;
        try {
            try {
                ps = cnx.prepareStatement(" SELECT CUENTA FROM " + this.schema_database + "PTO_TBLLINEAS " + "  WHERE CUENTA = ?");
                ps.setString(1, _cuentaDest);
                rs = ps.executeQuery();
                if (rs.next()) {
                    existe = true;
                }
            }
            catch (SQLException se) {
                se.printStackTrace();
                this.error.info((Object)"Exception.existeLinea:", (Throwable)se);
                throw new CAException(-1, "TransferenciaCteExcelenteDAO.existeLinea[" + se.toString() + "]", (Exception)se);
            }
            catch (Exception e) {
                e.printStackTrace();
                this.error.info((Object)"Exception.existeLinea:", (Throwable)e);
                throw new CAException(-1, "TransferenciaCteExcelenteDAO.existeLinea[" + e.toString() + "]", e);
            }
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                    rs = null;
                }
                catch (Exception var8_11) {}
            }
            if (ps != null) {
                try {
                    ps.close();
                    ps = null;
                }
                catch (Exception var8_12) {}
            }
        }
        return existe;
    }

    private boolean existeTotales(Connection cnx, String _cuentaDest) throws CAException {
        boolean existe;
        PreparedStatement ps = null;
        ResultSet rs = null;
        existe = false;
        try {
            try {
                ps = cnx.prepareStatement(" SELECT CUENTA FROM " + this.schema_database + "PTO_TBLTOTALES " + "  WHERE CUENTA = ?");
                ps.setString(1, _cuentaDest);
                rs = ps.executeQuery();
                if (rs.next()) {
                    existe = true;
                }
            }
            catch (SQLException se) {
                se.printStackTrace();
                this.error.info((Object)"Exception.existeLinea:", (Throwable)se);
                throw new CAException(-1, "TransferenciaCteExcelenteDAO.existeLinea[" + se.toString() + "]", (Exception)se);
            }
            catch (Exception e) {
                e.printStackTrace();
                this.error.info((Object)"Exception.existeLinea:", (Throwable)e);
                throw new CAException(-1, "TransferenciaCteExcelenteDAO.existeLinea[" + e.toString() + "]", e);
            }
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                    rs = null;
                }
                catch (Exception var8_11) {}
            }
            if (ps != null) {
                try {
                    ps.close();
                    ps = null;
                }
                catch (Exception var8_12) {}
            }
        }
        return existe;
    }

    private boolean crearLineaPuntos(Connection cnx, TelefonoTO telefonoDestinoTO, int regionDestino) throws CAException {
        int rows;
        PreparedStatement ps = null;
        rows = 0;
        try {
            try {
                MobileTO m2kTO = telefonoDestinoTO.getMobileTO();
                ps = cnx.prepareStatement("INSERT INTO " + this.schema_database + "PTO_TBLLINEAS " + "(CUENTA, SECUENCIA, CTAPADRE, LINEA, IDREGION, PLAN, STATUSTEL, " + " CICLOFACT, ADDENDUM, FECHAADD, FECHAALTA, FECHAANT, SISTEMA," + " STATUSPUNTOS, STATUSCARTA, MODSUBASTA)" + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                ps.setString(1, m2kTO.getCuenta());
                ps.setString(2, m2kTO.getSecuencia());
                ps.setString(3, m2kTO.getCuentaPadre());
                ps.setString(4, m2kTO.getTelefono());
                ps.setInt(5, regionDestino);
                ps.setString(6, m2kTO.getPlanM2K());
                ps.setString(7, m2kTO.getStatus());
                ps.setString(8, m2kTO.getCiclo());
                ps.setString(9, m2kTO.getAddM2K());
                ps.setDate(10, new Date(new Long(Constantes.DATEFORMATyyyy_MM_dd.parse(m2kTO.getFecAddM2K()).getTime())));
                ps.setDate(11, new Date(System.currentTimeMillis()));
                ps.setDate(12, new Date(new Long(Constantes.DATEFORMATyyyy_MM_dd.parse(m2kTO.getFecAltaUser()).getTime())));
                ps.setString(13, "M2K");
                ps.setInt(14, 0);
                ps.setString(15, "T");
                ps.setInt(16, 0);
                rows = ps.executeUpdate();
                m2kTO = null;
            }
            catch (SQLException se) {
                se.printStackTrace();
                this.error.info((Object)"Exception.crearLineaPuntos:", (Throwable)se);
                throw new CAException(-1, "TransferenciaCteExcelenteDAO.crearLineaPuntos[" + se.toString() + "]", (Exception)se);
            }
            catch (Exception e) {
                e.printStackTrace();
                this.error.info((Object)"Exception.crearLineaPuntos:", (Throwable)e);
                throw new CAException(-1, "TransferenciaCteExcelenteDAO.crearLineaPuntos[" + e.toString() + "]", e);
            }
        }
        finally {
            if (ps != null) {
                try {
                    ps.close();
                    ps = null;
                }
                catch (Exception var8_11) {}
            }
        }
        if (rows > 0) {
            return true;
        }
        return false;
    }

    private boolean creaTotalesLinea(Connection _cnx, int ptsExc, String _ctaDest, String _secDest) throws CAException {
        int rows;
        PreparedStatement ps = null;
        rows = 0;
        try {
            try {
                ps = _cnx.prepareStatement("INSERT INTO " + this.schema_database + "PTO_TBLTOTALES(Cuenta, Secuencia, Fechafac, Puntosacum," + " Puntosredim, Puntostransf, Puntosalianzas, Puntoscaduc, Puntosacad, Puntosacad2," + " Puntosporbono, Puntosbonoant, Puntosrenta, Puntosexcedentes, Fechacad," + " Fechacad2, Puntosacad1, Fechacad1, Puntosprom, Fechacadu, Bbono, Saldoant," + " PuntosAntig, PuntosSubasta, PuntosAntiguedad, PuntosPromocion, BonoEquipo)" + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?," + "\t\t?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                ps.setString(1, _ctaDest);
                ps.setInt(2, Integer.parseInt(_secDest));
                ps.setDate(3, new Date(System.currentTimeMillis()));
                ps.setInt(4, 0);
                ps.setInt(5, 0);
                ps.setInt(6, 0);
                ps.setInt(7, 0);
                ps.setInt(8, 0);
                ps.setInt(9, 0);
                ps.setInt(10, 0);
                ps.setInt(11, 0);
                ps.setInt(12, 0);
                ps.setInt(13, 0);
                ps.setInt(14, ptsExc);
                ps.setDate(15, null);
                ps.setDate(16, null);
                ps.setInt(17, 0);
                ps.setDate(18, null);
                ps.setString(19, "0");
                ps.setDate(20, null);
                ps.setInt(21, 0);
                ps.setInt(22, 0);
                ps.setInt(23, 0);
                ps.setInt(24, 0);
                ps.setInt(25, 0);
                ps.setInt(26, 0);
                ps.setInt(27, 0);
                rows = ps.executeUpdate();
            }
            catch (SQLException se) {
                se.printStackTrace();
                this.error.info((Object)"Exception.creaTotalesLinea:", (Throwable)se);
                throw new CAException(-1, "TransferenciaCteExcelenteDAO.creaTotalesLinea[" + se.toString() + "]", (Exception)se);
            }
            catch (Exception e) {
                e.printStackTrace();
                this.error.info((Object)"Exception.creaTotalesLinea:", (Throwable)e);
                throw new CAException(-1, "TransferenciaCteExcelenteDAO.creaTotalesLinea[" + e.toString() + "]", e);
            }
        }
        finally {
            if (ps != null) {
                try {
                    ps.close();
                    ps = null;
                }
                catch (Exception var9_11) {}
            }
        }
        if (rows > 0) {
            return true;
        }
        return false;
    }
}

