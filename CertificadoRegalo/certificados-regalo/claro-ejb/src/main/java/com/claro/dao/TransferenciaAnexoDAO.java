/*
 * Decompiled with CFR 0_102.
 * 
 * Could not load the following classes:
 *  com.claro.exception.CAException
 *  com.claro.transfer.MensajeTO
 *  com.claro.transfer.MobileTO
 *  com.claro.transfer.ParametrosTO
 *  com.claro.transfer.PuntosTO
 *  com.claro.transfer.TelefonoTO
 *  com.claro.transfer.transpuntos.TransferenciaTO
 *  com.claro.util.Constantes
 *  com.claro.util.ServiceLocator
 *  org.apache.log4j.Logger
 */
package com.claro.dao;

import com.claro.dao.ConsultaM2KDAO;
import com.claro.dao.ConsultasDAO;
import com.claro.dao.PuntosDAO;
import com.claro.dao.TranasferenciaDAO;
import com.claro.exception.CAException;
import com.claro.transfer.MensajeTO;
import com.claro.transfer.MobileTO;
import com.claro.transfer.ParametrosTO;
import com.claro.transfer.PuntosTO;
import com.claro.transfer.TelefonoTO;
import com.claro.transfer.transpuntos.TransferenciaTO;
import com.claro.util.Constantes;
import com.claro.util.ServiceLocator;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.log4j.Logger;

public class TransferenciaAnexoDAO
extends TranasferenciaDAO {
    public TransferenciaTO transferirPuntosAnexo(TransferenciaTO _transfTO) throws CAException {
        Connection cnx = null;
        PuntosTO puntosOrigenTO = new PuntosTO();
        MensajeTO msgTO = new MensajeTO();
        PuntosDAO ptsDAO = new PuntosDAO();
        int[] arrTiposPuntos = new int[7];
        int puntosTrasnferidos = 0;
        try {
            try {
                long inicioProceso = System.currentTimeMillis();
                this.logger.info((Object)("transferirPuntosAnexo|Inicio|" + Constantes.DATEFORMTALog.format(new Date()) + "|" + inicioProceso));
                puntosOrigenTO = this.consultasPuntosDAO.obtienePuntos(_transfTO.getCuentaOrigen(), _transfTO.getSecuenciaOrigen());
                if (puntosOrigenTO.getIdMensaje() != 0) {
                    throw new CAException(-1, "SE PRODUJO UN ERROR AL CONSULTAR LOS PUNTOS DE LA LINEA [" + _transfTO.getTelefonoOrigen() + "]");
                }
                puntosTrasnferidos = _transfTO.getPuntosTrasnferidos();
                _transfTO.setPtosDisponiblesOrigen(puntosOrigenTO.getPtosDisponibles());
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
                if (puntosTrasnferidos != 0) {
                    puntosTrasnferidos-=_transfTO.getPuntosTrasnferidos();
                }
                this.actualizaInfoPuntos(arrTiposPuntos, puntosOrigenTO);
                TelefonoTO phoneDestinoTO = this.validaInfoTransferenciaAnexo(_transfTO);
                _transfTO.setSecuenciaDestino(Integer.parseInt(phoneDestinoTO.getMobileTO().getSecuencia()));
                cnx = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                cnx.setAutoCommit(false);
                if (!this.actualizaTotalesOrigenAnexo(cnx, puntosOrigenTO, _transfTO.getCuentaOrigen(), _transfTO.getSecuenciaOrigen())) {
                    cnx.rollback();
                    throw new CAException(-1, "NO ACTUALIZO LOS DATOS DE LA LINEA ORIGEN [" + _transfTO.getTelefonoOrigen() + "]");
                }
                if (!this.transferir(cnx, phoneDestinoTO.getPuntosTO().getPtsExcedentes(), _transfTO.getPuntosTrasnferidos(), phoneDestinoTO.getCuenta(), phoneDestinoTO.getSecuencia())) {
                    cnx.rollback();
                    throw new CAException(-1, "NO TRANSFIRIO LOS PUNTOS DE LA LINEA ORIGEN [" + _transfTO.getTelefonoOrigen() + "]");
                }
                phoneDestinoTO.getPuntosTO().setPtsExcedentes(phoneDestinoTO.getPuntosTO().getPtsExcedentes() + _transfTO.getPuntosTrasnferidos());
                if (!this.guardarDetalleLinea(cnx, _transfTO, 1, false)) {
                    cnx.rollback();
                    throw new CAException(-1, "NO SE PUDO SALVAR EL DETALLE DE LA LINEA ORIGEN [" + _transfTO.getTelefonoOrigen() + "]");
                }
                String comntOrigen = this.crearComentario(_transfTO.getPuntosTrasnferidos(), "", _transfTO.getCuentaDestino(), _transfTO.getIdUsuario(), _transfTO.getComentario(), 1, false);
                msgTO = ptsDAO.insertaComentarioTMP(cnx, _transfTO.getRegionOrigen(), _transfTO.getCuentaLineaOrigen(), _transfTO.getIdUsuario(), System.currentTimeMillis(), comntOrigen);
                if (msgTO.getIdMensaje() != 0) {
                    cnx.rollback();
                    throw new CAException(-1, "NO SE PUDO SALVAR EL PRIMER COMENTARIO.");
                }
                if (!this.guardarDetalleLinea(cnx, _transfTO, 2, false)) {
                    cnx.rollback();
                    throw new CAException(-1, "NO SE PUDO SALVAR EL DETALLE DE LA LINEA DESTINO [" + _transfTO.getTelefonoDestino() + "]");
                }
                String comntDestino = this.crearComentario(_transfTO.getPuntosTrasnferidos(), _transfTO.getCuentaOrigen(), "", _transfTO.getIdUsuario(), _transfTO.getComentario(), 2, false);
                msgTO = ptsDAO.insertaComentarioTMP(cnx, phoneDestinoTO.getRegion(), phoneDestinoTO.getCuenta(), _transfTO.getIdUsuario(), System.currentTimeMillis(), comntDestino);
                if (msgTO.getIdMensaje() != 0) {
                    cnx.rollback();
                    throw new CAException(-1, "NO SE PUDO SALVAR EL SEGUNDO COMENTARIO.");
                }
                _transfTO.setPuntosOrigenTO(puntosOrigenTO);
                _transfTO.setTelefonoTO(phoneDestinoTO);
                cnx.commit();
                this.logger.info((Object)("transferirPuntosAnexo|Fin|" + Constantes.DATEFORMTALog.format(new Date()) + "|" + (System.currentTimeMillis() - inicioProceso)));
            }
            catch (SQLException se) {
                if (cnx != null) {
                    try {
                        cnx.rollback();
                    }
                    catch (Exception var9_15) {
                        // empty catch block
                    }
                }
                se.printStackTrace();
                this.error.info((Object)"Exception.transferirPorPlanAnexo:", (Throwable)se);
                throw new CAException(-1, "TransferenciaAnexoDAO.transferirPorPlanAnexo[" + se.toString() + "]", (Exception)se);
            }
            catch (Exception e) {
                if (cnx != null) {
                    try {
                        cnx.rollback();
                    }
                    catch (Exception var9_16) {
                        // empty catch block
                    }
                }
                e.printStackTrace();
                this.error.info((Object)"Exception.transferirPorPlanAnexo:", (Throwable)e);
                throw new CAException(-1, "TransferenciaAnexoDAO.transferirPorPlanAnexo[" + e.toString() + "]", e);
            }
        }
        finally {
            if (cnx != null) {
                try {
                    cnx.setAutoCommit(true);
                    cnx.close();
                    cnx = null;
                }
                catch (Exception var14_19) {}
            }
        }
        return _transfTO;
    }

    private boolean validaPlan(Connection _cnx, String _idPlan, int _region) throws CAException {
        boolean existe;
        PreparedStatement ps = null;
        ResultSet rs = null;
        existe = false;
        try {
            try {
                ps = _cnx.prepareStatement("SELECT G.TIPOPROMOCION   FROM " + this.schema_database + "PTO_CTLPLANESTARIFARIOS P, " + "\t\t" + this.schema_database + "PTO_CTLGRUPOPROMOCION G" + " WHERE P.IDPLAN = ?" + "   AND P.IDREGION = ?" + "   AND P.IDGRUPOPROMOCION = G.IDGRUPOPROMOCION");
                ps.setString(1, _idPlan);
                ps.setInt(2, _region);
                rs = ps.executeQuery();
                if (rs.next()) {
                    existe = true;
                }
            }
            catch (SQLException se) {
                se.printStackTrace();
                this.error.info((Object)"Exception.obtieneInfoPlan:", (Throwable)se);
                throw new CAException(-1, "TransferenciaAnexoDAO.obtieneInfoPlan[" + se.toString() + "]", (Exception)se);
            }
            catch (Exception e) {
                e.printStackTrace();
                this.error.info((Object)"Exception.obtieneInfoPlan:", (Throwable)e);
                throw new CAException(-1, "TransferenciaAnexoDAO.obtieneInfoPlan[" + e.toString() + "]", e);
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
        return existe;
    }

    private TelefonoTO validaInfoTransferenciaAnexo(TransferenciaTO _transfTO) throws CAException {
        Connection cnx = null;
        MobileTO mobileDestinoTO = null;
        TelefonoTO phoneDestinoTO = new TelefonoTO();
        ParametrosTO paramsTO = null;
        try {
            try {
                paramsTO = new ParametrosTO();
                paramsTO.setTelefono(_transfTO.getTelefonoDestino());
                paramsTO.setCuenta(_transfTO.getCuentaDestino());
                paramsTO.setRegion(_transfTO.getRegionDestino());
                mobileDestinoTO = this.m2kDAO.consultaDatosM2K(paramsTO);
                cnx = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                this.consultasPuntosDAO.consultaDatosPuntos(paramsTO, phoneDestinoTO, mobileDestinoTO, cnx);
                phoneDestinoTO.setMobileTO(mobileDestinoTO);
                if (_transfTO.getPuntosTrasnferidos() > _transfTO.getPtosDisponiblesOrigen()) {
                    throw new CAException(-1, "LOS PUNTOS A TRANSFERIR SON MAYORES A LOS DISPONIBLES.");
                }
                if (!mobileDestinoTO.getCuentaPadre().equals(_transfTO.getCuentaPadreOrigen())) {
                    throw new CAException(-1, "LAS CUENTAS NO ESTAN CONSOLIDADAS.");
                }
                if (!this.validaPlan(cnx, mobileDestinoTO.getPlanM2K(), _transfTO.getRegionDestino())) {
                    throw new CAException(-1, "EL PLAN DE LA LINEA DESTINO NO ES PLAN ANEXO.");
                }
                if (!_transfTO.getCuentaOrigen().equals(_transfTO.getCuentaLineaOrigen())) {
                    throw new CAException(-1, "CUENTA ORIGEN INCONSISTENTE EN M2K Y PUNTOS.");
                }
                if (!_transfTO.getEstatusLineaOrigen().equals("AC")) {
                    throw new CAException(-1, "NO SE PUEDE REALIZAR LA TRANSFERENCIA SI LA LINEA ORIGEN ESTA INACTIVA.");
                }
                if (_transfTO.getPtosDisponiblesOrigen() <= 0) {
                    throw new CAException(-1, "CUENTA ORIGEN INCONSISTENTE EN CANTIDAD DE PUNTOS.");
                }
                if (!(mobileDestinoTO.getCuenta().equals(_transfTO.getCuentaDestino()) && mobileDestinoTO.getTelefono().equals(_transfTO.getTelefonoDestino()))) {
                    throw new CAException(-1, "CUENTA Y/O TELEFONO DESTINO INCONSISTENTE EN M2K.");
                }
            }
            catch (SQLException se) {
                se.printStackTrace();
                this.error.info((Object)"Exception.validaInfoTransferenciaAnexo:", (Throwable)se);
                throw new CAException(-1, "TransferenciaAnexoDAO.validaInfoTransferenciaAnexo[" + se.toString() + "]", (Exception)se);
            }
            catch (Exception e) {
                e.printStackTrace();
                this.error.info((Object)"Exception.validaInfoTransferenciaAnexo:", (Throwable)e);
                throw new CAException(-1, "TransferenciaAnexoDAO.validaInfoTransferenciaAnexo[" + e.toString() + "]", e);
            }
        }
        finally {
            if (cnx != null) {
                try {
                    cnx.close();
                    cnx = null;
                }
                catch (Exception var8_10) {}
            }
        }
        return phoneDestinoTO;
    }

    private void actualizaInfoPuntos(int[] _arrTiposPuntos, PuntosTO puntosOrigenTO) throws CAException {
        puntosOrigenTO.setPtsPorVencer(_arrTiposPuntos[0]);
        puntosOrigenTO.setPtsPorVencer1(_arrTiposPuntos[1]);
        puntosOrigenTO.setPtsPorVencer2(_arrTiposPuntos[2]);
        puntosOrigenTO.setPtsPromocion(_arrTiposPuntos[3]);
        puntosOrigenTO.setPtsAntiguedad(_arrTiposPuntos[4]);
        puntosOrigenTO.setPtsExcedentes(_arrTiposPuntos[5]);
        puntosOrigenTO.setPtsRenta(_arrTiposPuntos[6]);
    }

    private boolean actualizaTotalesOrigenAnexo(Connection _cnx, PuntosTO _puntosTO, String _cuenta, int _secuencia) throws CAException {
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
                ps.setString(8, _cuenta);
                ps.setInt(9, _secuencia);
                rows = ps.executeUpdate();
            }
            catch (SQLException se) {
                se.printStackTrace();
                this.error.info((Object)"Exception.actualizaTotalesOrigen:", (Throwable)se);
                throw new CAException(-1, "TransferenciaAnexoDAO.actualizaTotalesOrigen[" + se.toString() + "]", (Exception)se);
            }
            catch (Exception e) {
                e.printStackTrace();
                this.error.info((Object)"Exception.actualizaTotalesOrigen:", (Throwable)e);
                throw new CAException(-1, "TransferenciaAnexoDAO.actualizaTotalesOrigen[" + e.toString() + "]", e);
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

    private boolean transferir(Connection _cnx, int _ptsExcedentes, int _ptsTransf, String _cuenta, String _secuencia) throws CAException {
        int rows;
        PreparedStatement ps = null;
        rows = 0;
        int sumaPts = 0;
        try {
            try {
                sumaPts = _ptsExcedentes + _ptsTransf;
                ps = _cnx.prepareStatement("UPDATE " + this.schema_database + "PTO_TBLTOTALES " + "\tSET PUNTOSEXCEDENTES = ?" + " WHERE CUENTA = ?" + "\tAND SECUENCIA = ?");
                ps.setInt(1, sumaPts);
                ps.setString(2, _cuenta);
                ps.setString(3, _secuencia);
                rows = ps.executeUpdate();
            }
            catch (SQLException se) {
                se.printStackTrace();
                this.error.info((Object)"Exception.transferir:", (Throwable)se);
                throw new CAException(-1, "TransferenciaAnexoDAO.transferir[" + se.toString() + "]", (Exception)se);
            }
            catch (Exception e) {
                e.printStackTrace();
                this.error.info((Object)"Exception.transferir:", (Throwable)e);
                throw new CAException(-1, "TransferenciaAnexoDAO.transferir[" + e.toString() + "]", e);
            }
        }
        finally {
            if (ps != null) {
                try {
                    ps.close();
                    ps = null;
                }
                catch (Exception var11_13) {}
            }
        }
        if (rows > 0) {
            return true;
        }
        return false;
    }
}

