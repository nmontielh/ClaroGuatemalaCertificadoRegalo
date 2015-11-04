/*
 * Decompiled with CFR 0_102.
 * 
 * Could not load the following classes:
 *  com.claro.exception.CAException
 *  com.claro.transfer.transpuntos.TransferenciaTO
 *  com.claro.util.ServiceLocator
 *  org.apache.log4j.Logger
 */
package com.claro.dao;

import com.claro.dao.ConsultaM2KDAO;
import com.claro.dao.ConsultasDAO;
import com.claro.exception.CAException;
import com.claro.transfer.transpuntos.TransferenciaTO;
import com.claro.util.ServiceLocator;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import org.apache.log4j.Logger;

public class TranasferenciaDAO {
    protected final Logger logger = Logger.getLogger((String)"loggerCirculoAzul");
    protected final Logger error = Logger.getLogger((String)"loggerCirculoAzulError");
    protected ConsultaM2KDAO m2kDAO = new ConsultaM2KDAO();
    protected ConsultasDAO consultasPuntosDAO = new ConsultasDAO();
    protected String schema_database;

    public TranasferenciaDAO() {
        try {
            this.schema_database = ServiceLocator.getInstance().getVariable(ServiceLocator.schema_database);
        }
        catch (Exception e) {
            this.error.error((Object)"TranasferenciaDAO", (Throwable)e);
        }
    }

    protected boolean guardarDetalleLinea(Connection _cnx, TransferenciaTO _transfTO, int _tipo, boolean esCancelacion) throws CAException {
        int rows;
        PreparedStatement ps = null;
        StringBuffer qry = new StringBuffer(255);
        String referencia = "";
        rows = 0;
        int ptsTransf = 0;
        int idMvto = 0;
        try {
            try {
                qry.append("INSERT INTO ").append(this.schema_database).append("PTO_TBLMSTRDETALLE");
                qry.append("(CUENTA, SECUENCIA, LINEA, FECHAFAC, FECHAOPERACION, IDMOVTO,");
                qry.append(" IDUSUARIO, NUMPUNTOS, NUMPUNTOSEXC, TOTAJUSTES, IDBONOPROM, REFERENCIA) ");
                qry.append("VALUES ").append("(?,?,?,?,?,?,?,?,?,?,?,?)");
                if (_tipo == 1) {
                    if (esCancelacion) {
                        ptsTransf = _transfTO.getPuntosTrasnferidos();
                        idMvto = 64;
                        referencia = "Cancel. Traspaso de ptos de la cta: " + _transfTO.getCuentaDestino();
                    } else {
                        ptsTransf = _transfTO.getPuntosTrasnferidos() * -1;
                        idMvto = 13;
                        referencia = "Traspaso de ptos a la cta: " + _transfTO.getCuentaDestino();
                    }
                    referencia = String.valueOf(referencia) + ", atendio: " + _transfTO.getIdUsuario() + " " + _transfTO.getComentario();
                    if (referencia.length() > 100) {
                        referencia = referencia.substring(0, 100);
                    }
                    ps = _cnx.prepareStatement(qry.toString());
                    ps.setString(1, _transfTO.getCuentaOrigen());
                    ps.setInt(2, _transfTO.getSecuenciaOrigen());
                    ps.setString(3, _transfTO.getTelefonoOrigen());
                    ps.setDate(4, new Date(System.currentTimeMillis()));
                    ps.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
                    ps.setInt(6, idMvto);
                    ps.setString(7, _transfTO.getIdUsuario());
                    ps.setInt(8, 0);
                    ps.setInt(9, 0);
                    ps.setInt(10, ptsTransf);
                    ps.setNull(11, 12);
                    ps.setString(12, referencia.toString());
                } else if (_tipo == 2) {
                    if (esCancelacion) {
                        ptsTransf = _transfTO.getPuntosTrasnferidos() * -1;
                        idMvto = 64;
                        referencia = "Cancel. Recepci\u00f3n de ptos de la cta: " + _transfTO.getCuentaOrigen();
                    } else {
                        ptsTransf = _transfTO.getPuntosTrasnferidos();
                        idMvto = 13;
                        referencia = "Recepci\u00f3n de ptos de la cta: " + _transfTO.getCuentaOrigen();
                    }
                    referencia = String.valueOf(referencia) + ", atendio: " + _transfTO.getIdUsuario() + " " + _transfTO.getComentario();
                    if (referencia.length() > 100) {
                        referencia = referencia.substring(0, 100);
                    }
                    ps = _cnx.prepareStatement(qry.toString());
                    ps.setString(1, _transfTO.getCuentaDestino());
                    ps.setInt(2, _transfTO.getSecuenciaDestino());
                    ps.setString(3, _transfTO.getTelefonoDestino());
                    ps.setDate(4, new Date(System.currentTimeMillis()));
                    ps.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
                    ps.setInt(6, idMvto);
                    ps.setString(7, _transfTO.getIdUsuario());
                    ps.setInt(8, 0);
                    ps.setInt(9, 0);
                    ps.setInt(10, ptsTransf);
                    ps.setNull(11, 12);
                    ps.setString(12, referencia.toString());
                }
                rows = ps.executeUpdate();
            }
            catch (SQLException se) {
                se.printStackTrace();
                this.error.info((Object)"Exception.guardarDetalleLinea:", (Throwable)se);
                throw new CAException(-1, "TranasferenciaDAO.guardarDetalleLinea[" + se.toString() + "]", (Exception)se);
            }
            catch (Exception e) {
                e.printStackTrace();
                this.error.info((Object)"Exception.guardarDetalleLinea:", (Throwable)e);
                throw new CAException(-1, "TranasferenciaDAO.guardarDetalleLinea[" + e.toString() + "]", e);
            }
        }
        finally {
            if (ps != null) {
                try {
                    ps.close();
                    ps = null;
                }
                catch (Exception var13_15) {}
            }
        }
        if (rows > 0) {
            return true;
        }
        return false;
    }

    protected String crearComentario(int _ptsTransf, String _ctaOrigen, String _ctaDestino, String _usuario, String _referencia, int _tipo, boolean esCancelacion) throws CAException {
        StringBuffer referencia = new StringBuffer(255);
        if (_tipo == 1) {
            referencia.append("CIR - ");
            if (esCancelacion) {
                referencia.append("CANCELA");
            }
            referencia.append(" TRASPASO DE [").append(_ptsTransf);
            referencia.append("] PUNTOS A LA CUENTA [").append(_ctaDestino);
            referencia.append("], ATENDIO [").append(_usuario);
            referencia.append("] ").append(_referencia);
        } else if (_tipo == 2) {
            referencia.append("CIR - ");
            if (esCancelacion) {
                referencia.append("CANCELA");
            }
            referencia.append(" RECEPCION DE [").append(_ptsTransf);
            referencia.append("] PUNTOS DE LA CUENTA [" + _ctaOrigen);
            referencia.append("], ATENDIO [").append(_usuario);
            referencia.append("] ").append(_referencia);
        }
        return referencia.toString();
    }
}

