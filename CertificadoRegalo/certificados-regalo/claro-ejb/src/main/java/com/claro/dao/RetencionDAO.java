/*
 * Decompiled with CFR 0_102.
 * 
 * Could not load the following classes:
 *  com.claro.exception.CAException
 *  com.claro.transfer.FacturaTO
 *  com.claro.transfer.MensajeTO
 *  com.claro.transfer.MobileTO
 *  com.claro.transfer.MotivoTO
 *  com.claro.transfer.PuntoVentaTO
 *  com.claro.transfer.RetencionTO
 *  com.claro.transfer.TelefonoTO
 *  com.claro.transfer.UsuarioTO
 *  com.claro.util.ServiceLocator
 *  com.claro.util.Utils
 *  org.apache.log4j.Logger
 */
package com.claro.dao;

import com.claro.exception.CAException;
import com.claro.transfer.FacturaTO;
import com.claro.transfer.MensajeTO;
import com.claro.transfer.MobileTO;
import com.claro.transfer.MotivoTO;
import com.claro.transfer.PuntoVentaTO;
import com.claro.transfer.RetencionTO;
import com.claro.transfer.TelefonoTO;
import com.claro.transfer.UsuarioTO;
import com.claro.util.ServiceLocator;
import com.claro.util.Utils;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import org.apache.log4j.Logger;

public class RetencionDAO {
    protected final Logger logger = Logger.getLogger((String)"loggerCirculoAzul");
    protected final Logger error = Logger.getLogger((String)"loggerCirculoAzulError");
    private String schema_database;

    public RetencionDAO() {
        try {
            this.schema_database = ServiceLocator.getInstance().getVariable(ServiceLocator.schema_database);
        }
        catch (Exception e) {
            this.error.error((Object)"RetencionDAO", (Throwable)e);
        }
    }

    public RetencionTO calculaValorCupon(Double dARPU, int iMeses, int iBajas, int iPcAutor) throws CAException {
        int iARPU = 0;
        int iPuntosARPU = 0;
        int iValorPARPU = 0;
        int iPuntosAntig = 0;
        int iValorPAntig = 0;
        int iPuntosCob = 0;
        int iValorPCob = 0;
        iARPU = (int)Math.floor(dARPU + 0.5);
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rset = null;
        String sQuery = "";
        RetencionTO retencionTO = new RetencionTO();
        MensajeTO mensajeTO = new MensajeTO();
        try {
            conn = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
            sQuery = " SELECT puntos, Porcentaje FROM " + this.schema_database + "PTO_CTLPARCERT " + "  WHERE tipoparm = 'ARPU' AND Rango_Inf <= ? AND Rango_Sup >= ?";
            stmt = conn.prepareStatement(sQuery);
            stmt.setInt(1, iARPU);
            stmt.setInt(2, iARPU);
            rset = stmt.executeQuery();
            if (rset.next()) {
                iPuntosARPU = rset.getInt(1);
                iValorPARPU = rset.getInt(2);
            }
            retencionTO.setPorcARPU(iValorPARPU * iPuntosARPU / 100);
            sQuery = " SELECT puntos, Porcentaje FROM " + this.schema_database + "PTO_CTLPARCERT " + "  WHERE tipoparm = 'ANTIG' AND Rango_Inf <= ? AND Rango_Sup >= ?";
            stmt = conn.prepareStatement(sQuery);
            stmt.setInt(1, iMeses);
            stmt.setInt(2, iMeses);
            rset = stmt.executeQuery();
            if (rset.next()) {
                iPuntosAntig = rset.getInt(1);
                iValorPAntig = rset.getInt(2);
            }
            retencionTO.setPorcAntig(iPuntosAntig * iValorPAntig / 100);
            sQuery = " SELECT puntos, Porcentaje FROM " + this.schema_database + "PTO_CTLPARCERT " + " WHERE tipoparm = 'BCOB' AND Rango_Inf <= ? AND Rango_Sup >= ?";
            stmt = conn.prepareStatement(sQuery);
            stmt.setInt(1, iBajas);
            stmt.setInt(2, iBajas);
            rset = stmt.executeQuery();
            if (rset.next()) {
                iPuntosCob = rset.getInt(1);
                iValorPCob = rset.getInt(2);
            }
            retencionTO.setPorcCob(iPuntosCob * iValorPCob / 100);
            retencionTO.setValorCupon(500 * (retencionTO.getPorcARPU() + retencionTO.getPorcAntig() + retencionTO.getPorcCob()) / 100);
            if (iPcAutor == 0) {
                retencionTO.setVCentifextra("0");
            } else {
                retencionTO.setVCentifextra(String.valueOf(153 * iPcAutor / 100 * (retencionTO.getPorcARPU() + retencionTO.getPorcAntig() + retencionTO.getPorcCob()) / 100));
            }
            mensajeTO.setIdMensaje(0);
            retencionTO.setMensajeTO(mensajeTO);
            RetencionTO retencionTO2 = retencionTO;
            return retencionTO2;
        }
        catch (Exception e) {
            mensajeTO.setIdMensaje(1);
            mensajeTO.setMensaje("[calculaValorCupon] Error: " + e.getMessage());
            retencionTO.setMensajeTO(mensajeTO);
            RetencionTO retencionTO3 = retencionTO;
            return retencionTO3;
        }
        finally {
            try {
                if (rset != null) {
                    rset.close();
                    rset = null;
                }
            }
            catch (Exception var21_23) {}
            try {
                if (stmt != null) {
                    stmt.close();
                    stmt = null;
                }
            }
            catch (Exception var21_24) {}
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            }
            catch (Exception var21_25) {}
        }
    }

    public MensajeTO consultaCertificado(String cuenta, String telefono) throws CAException {
        MensajeTO mensajeTO;
        Connection connection = null;
        MensajeTO mensajeTO2 = null;
        PreparedStatement stmt = null;
        ResultSet rset = null;
        String sCondicion = "";
        sCondicion = cuenta == null || cuenta.equals("") ? " WHERE linea = ?" : " WHERE Cuenta = ?";
        String sQuery = " SELECT Fechaoper, Idusuario, COMENTARIO1 FROM " + this.schema_database + "PTO_TBLCERTIFICADOS " + sCondicion + "    AND ESTATUS = 'A'";
        try {
            connection = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
            stmt = connection.prepareStatement(sQuery);
            if (cuenta == null || cuenta.equals("")) {
                stmt.setString(1, telefono);
            } else {
                stmt.setString(1, cuenta);
            }
            rset = stmt.executeQuery();
            mensajeTO2 = new MensajeTO();
            Calendar hoy = Calendar.getInstance();
            while (rset.next()) {
                Calendar fechaOperacion = Calendar.getInstance();
                Date date = rset.getDate("FECHAOPER");
                if (date == null) continue;
                fechaOperacion.setTime(date);
                long dias = Utils.diferenciaEnDias((Calendar)hoy, (Calendar)fechaOperacion);
                if (dias >= 365) continue;
                String fecha = String.valueOf(String.valueOf(fechaOperacion.get(5))) + "-" + String.valueOf(fechaOperacion.get(2) + 1) + "-" + String.valueOf(fechaOperacion.get(1));
                String mensaje = "La linea tiene un Certificado de Lealtad generado el " + fecha + ", por lo que no puede ofrecerse nuevamente hasta cumplido un a\u00f1o.";
                mensajeTO2.setIdMensaje(1);
                mensajeTO2.setMensaje(mensaje);
                MensajeTO mensajeTO3 = mensajeTO2;
                return mensajeTO3;
            }
            mensajeTO2.setIdMensaje(0);
            mensajeTO = mensajeTO2;
        }
        catch (Exception e) {
            throw new CAException(1, e.getMessage());
        }
        finally {
            try {
                if (rset != null) {
                    rset.close();
                    rset = null;
                }
                if (stmt != null) {
                    stmt.close();
                    stmt = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            }
            catch (Exception var18_20) {}
        }
        return mensajeTO;
    }

    public MensajeTO validaVigenciaCertificado(String cuenta, String secuencia) {
        MensajeTO mensajeTO;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rset = null;
        String sQuery = null;
        MensajeTO mensajeTO2 = null;
        try {
            conn = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
            sQuery = " SELECT Fechaoper, Idusuario, COMENTARIO1 FROM " + this.schema_database + "PTO_TBLCERTIFICADOS " + "  WHERE Cuenta = ? AND Secuencia = ?" + " \t AND ESTATUS in ('A','L')";
            stmt = conn.prepareStatement(sQuery);
            stmt.setString(1, cuenta);
            stmt.setString(2, secuencia);
            rset = stmt.executeQuery();
            Calendar hoy = Calendar.getInstance();
            while (rset.next()) {
                Calendar fechaOperacion = Calendar.getInstance();
                Date date = rset.getDate(1);
                if (date == null) continue;
                fechaOperacion.setTime(date);
                long dias = Utils.diferenciaEnDias((Calendar)hoy, (Calendar)fechaOperacion);
                if (dias >= 365) continue;
                String fecha = String.valueOf(String.valueOf(fechaOperacion.get(5))) + "-" + String.valueOf(fechaOperacion.get(2) + 1) + "-" + String.valueOf(fechaOperacion.get(1));
                String mensaje = "La linea tiene un Certificado de Lealtad generado el " + fecha + ", por lo que no puede ofrecerse nuevamente hasta cumplido un a\u00f1o.";
                mensajeTO2 = new MensajeTO();
                mensajeTO2.setIdMensaje(1);
                mensajeTO2.setMensaje(mensaje);
                MensajeTO mensajeTO3 = mensajeTO2;
                return mensajeTO3;
            }
            mensajeTO2 = new MensajeTO();
            mensajeTO2.setIdMensaje(0);
            mensajeTO = mensajeTO2;
        }
        catch (Exception e) {
            mensajeTO2 = new MensajeTO();
            mensajeTO2.setIdMensaje(1);
            mensajeTO2.setMensaje(e.getMessage());
            MensajeTO mensajeTO4 = mensajeTO2;
            return mensajeTO4;
        }
        finally {
            try {
                if (rset != null) {
                    rset.close();
                    rset = null;
                }
            }
            catch (Exception var17_24) {}
            try {
                if (stmt != null) {
                    stmt.close();
                    stmt = null;
                }
            }
            catch (Exception var17_25) {}
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            }
            catch (Exception var17_26) {}
        }
        return mensajeTO;
    }

    public RetencionTO consultaRetencion(String sCta, String sSec) throws CAException {
        RetencionTO retencionTO;
        PreparedStatement stmt = null;
        ResultSet rset = null;
        Connection conn = null;
        RetencionTO retencionTO2 = null;
        String sQuery = "SELECT LINEA, CUENTA, SECUENCIA, FECHAOPER, FOLIO, VCERTIF, VCENTIFEXTRA, MOTIVO, FCADUCA, ESTATUS   FROM  " + this.schema_database + "PTO_TBLCERTIFICADOS a  " + " WHERE CUENTA=? AND SECUENCIA =? AND ESTATUS= 'A'" + " ORDER BY FOLIO DESC ";
        try {
            conn = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
            stmt = conn.prepareStatement(sQuery);
            stmt.setString(1, sCta);
            stmt.setString(2, sSec);
            rset = stmt.executeQuery();
            retencionTO2 = new RetencionTO();
            if (rset.next()) {
                retencionTO2.setTelefono(rset.getString(1));
                retencionTO2.setCuenta(rset.getString(2));
                retencionTO2.setFechaOperacion(rset.getString(4));
                retencionTO2.setFolio(rset.getString(5));
                retencionTO2.setVCertif(rset.getString(6));
                retencionTO2.setVCentifextra(rset.getString(7));
                retencionTO2.setMotivo(rset.getString(8));
                retencionTO2.setFechaCaduca(rset.getString(9));
                retencionTO2.setEstatus(rset.getString(10));
                MensajeTO mensajeTO = new MensajeTO();
                mensajeTO.setIdMensaje(0);
                retencionTO2.setMensajeTO(mensajeTO);
                RetencionTO retencionTO3 = retencionTO2;
                return retencionTO3;
            }
            MensajeTO mensajeTO = new MensajeTO();
            mensajeTO.setIdMensaje(1);
            mensajeTO.setMensaje("No existen Certificados de Lealtad generados para esta cuenta.");
            retencionTO2.setMensajeTO(mensajeTO);
            retencionTO = retencionTO2;
        }
        catch (Exception e) {
            throw new CAException(0, "[Consulta Retencion]" + e.getMessage());
        }
        finally {
            try {
                if (rset != null) {
                    rset.close();
                    rset = null;
                }
                if (stmt != null) {
                    stmt.close();
                    stmt = null;
                }
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            }
            catch (Exception var11_15) {}
        }
        return retencionTO;
    }

    /*
     * Exception decompiling
     */
    public MensajeTO almacenaCertificado(TelefonoTO telefonoTO, UsuarioTO usuarioTO, RetencionTO retencionTO, String sNMeses, String sMotivo, String sComenta, String sComenta2, String sCuentaAnt, String sRegionAnt, String sMesesAnt, String sTipo) throws CAException {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [0[TRYBLOCK]], but top level block is 55[CATCHBLOCK]
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.processEndingBlocks(Op04StructuredStatement.java:392)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:444)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.createInitialStructuredBlock(Op03SimpleStatement.java:2802)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:787)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:214)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:159)
        // org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:91)
        // org.benf.cfr.reader.entities.Method.analyse(Method.java:353)
        // org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:731)
        // org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:663)
        // org.benf.cfr.reader.Main.doJar(Main.java:126)
        // org.benf.cfr.reader.Main.main(Main.java:178)
        throw new IllegalStateException("Decompilation failed");
    }

    public MensajeTO validaImpresion(String sFolio, java.util.Date dfechaM2K) throws CAException {
        Connection conn = null;
        PreparedStatement stmtCertificado = null;
        PreparedStatement stmtRetencion = null;
        ResultSet rsetCertificado = null;
        ResultSet rsetRedencion = null;
        MensajeTO mensajeTO = null;
        String sQueryCertificado = "select cuenta,secuencia,fechaoper from " + this.schema_database + "PTO_TBLCERTIFICADOS a " + " where folio = ?";
        String sQueryRedencion = "SELECT b.fechaoper AS FECHAOPER  FROM  " + this.schema_database + "PTO_TBLREDENCION b " + " WHERE b.cuenta=? and b.secuencia=? and" + " b.fechaoper =? and b.tiporeden = 'C' AND b.Estatus = 'A'";
        try {
            conn = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
            stmtCertificado = conn.prepareStatement(sQueryCertificado);
            stmtCertificado.setString(1, sFolio);
            rsetCertificado = stmtCertificado.executeQuery();
            boolean existeRetencion = false;
            mensajeTO = new MensajeTO();
            while (rsetCertificado.next()) {
                String cuenta = rsetCertificado.getString("cuenta");
                String secuencia = rsetCertificado.getString("secuencia");
                Date fechaoper = rsetCertificado.getDate("fechaoper");
                stmtRetencion = conn.prepareStatement(sQueryRedencion);
                stmtRetencion.setString(1, cuenta);
                stmtRetencion.setString(2, secuencia);
                stmtRetencion.setDate(3, fechaoper);
                rsetRedencion = stmtRetencion.executeQuery();
                if (!rsetRedencion.next()) continue;
                existeRetencion = true;
                Calendar fechaOperacion = Calendar.getInstance();
                fechaOperacion.setTime(rsetRedencion.getDate(1));
                Calendar fechaHoy = Calendar.getInstance();
                Calendar fechaM2K = Calendar.getInstance();
                fechaM2K.setTime(dfechaM2K);
                if (Utils.diferenciaEnDias((Calendar)fechaHoy, (Calendar)fechaM2K) == 0 && Utils.diferenciaEnDias((Calendar)fechaOperacion, (Calendar)fechaHoy) == 0) continue;
                mensajeTO.setIdMensaje(1);
                mensajeTO.setMensaje("Para poder imprimir el Certificado, las fechas de adendum en M2K y la redencion de Puntos deben coincidir con el dia actual.");
                MensajeTO mensajeTO2 = mensajeTO;
                return mensajeTO2;
            }
            if (!existeRetencion) {
                mensajeTO.setIdMensaje(1);
                mensajeTO.setMensaje("Para poder imprimir el Certificado, debe existir una Redencion de Puntos con firma de adendum correspondiente a la fecha actual.");
                MensajeTO mensajeTO3 = mensajeTO;
                return mensajeTO3;
            }
            mensajeTO.setIdMensaje(0);
            MensajeTO mensajeTO4 = mensajeTO;
            return mensajeTO4;
        }
        catch (Exception e) {
            throw new CAException(0, "[validaImpresion]" + e.getMessage());
        }
        finally {
            try {
                if (rsetCertificado != null) {
                    rsetCertificado.close();
                    rsetCertificado = null;
                }
                if (stmtCertificado != null) {
                    stmtCertificado.close();
                    stmtCertificado = null;
                }
                if (rsetRedencion != null) {
                    rsetRedencion.close();
                    rsetRedencion = null;
                }
                if (stmtRetencion != null) {
                    stmtRetencion.close();
                    stmtRetencion = null;
                }
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            }
            catch (Exception e) {
                throw new CAException(0, "[validaImpresion]" + e.getMessage());
            }
        }
    }

    public MensajeTO cancelarCertificado(String sUsuario, String sModo, String sFolio, String sComenta) throws CAException {
        MensajeTO mensajeTO;
        String sQuery = "";
        String sUpdate = "";
        Connection conn = null;
        PreparedStatement stmt = null;
        PreparedStatement stmt1 = null;
        ResultSet rset = null;
        long nDias = 0;
        String sCta = "";
        String sTel = "";
        String sSec = "";
        MensajeTO mensajeTO2 = null;
        if (sModo.equals("1")) {
            sModo = "L";
        } else if (sModo.equals("0")) {
            sModo = "C";
        }
        try {
            conn = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
            sQuery = " SELECT a.fechaoper, CUENTA, SECUENCIA, LINEA   FROM " + this.schema_database + "PTO_TBLCERTIFICADOS a " + " WHERE FOLIO = ?";
            stmt = conn.prepareStatement(sQuery);
            stmt.setString(1, sFolio);
            rset = stmt.executeQuery();
            if (rset.next()) {
                Calendar fechaHoy = Calendar.getInstance();
                Calendar fechaOperacion = Calendar.getInstance();
                fechaOperacion.setTime(rset.getDate(1));
                nDias = Utils.diferenciaEnDias((Calendar)fechaHoy, (Calendar)fechaOperacion);
                sCta = rset.getString(2);
                sSec = rset.getString(3);
                sTel = rset.getString(4);
            }
            if (nDias <= 5) {
                long fechaTransaccion = System.currentTimeMillis();
                sUpdate = " UPDATE " + this.schema_database + "PTO_TBLCERTIFICADOS " + " SET Fechalib = ?," + " IdUsuariolib = ?,Estatus = ?," + " ComntLib = ?" + " WHERE folio = ?";
                stmt1 = conn.prepareStatement(sUpdate);
                stmt1.setDate(1, new Date(fechaTransaccion));
                stmt1.setString(2, sUsuario);
                stmt1.setString(3, sModo);
                stmt1.setString(4, sComenta);
                stmt1.setString(5, sFolio);
                stmt1.executeUpdate();
                sUpdate = "INSERT INTO " + this.schema_database + "PTO_TBLMSTRDETALLE (Cuenta, Secuencia, Linea, FechaFac, FechaOperacion, IdMovto," + " IdUsuario, NumPuntos, NumPuntosExc, TotAjustes, IdBonoProm, Referencia) " + " VALUES(?,?,?,?,?, 50," + "?,0,0,0,null,?)";
                stmt = conn.prepareStatement(sUpdate);
                stmt.setString(1, sCta);
                stmt.setString(2, sSec);
                stmt.setString(3, sTel);
                stmt.setDate(4, new Date(fechaTransaccion));
                stmt.setDate(5, new Date(fechaTransaccion));
                stmt.setString(6, sUsuario);
                stmt.setString(7, "'Se cancela certificado de Lealtad folio: " + sFolio + "'");
                stmt.executeUpdate();
                mensajeTO2 = new MensajeTO();
                mensajeTO2.setIdMensaje(0);
                mensajeTO2.setMensaje("La cancelacion del Certificado de Lealtad, fue completada exitosamente.");
                MensajeTO mensajeTO3 = mensajeTO2;
                return mensajeTO3;
            }
            mensajeTO2 = new MensajeTO();
            mensajeTO2.setIdMensaje(1);
            mensajeTO2.setMensaje("No es posible cancelar el Certificado de Lealtad. El tiempo maximo para cancelar este tramite es de 5 dias.");
            mensajeTO = mensajeTO2;
        }
        catch (Exception e) {
            mensajeTO2 = new MensajeTO();
            mensajeTO2.setIdMensaje(1);
            mensajeTO2.setMensaje("Ocurrio un error al momento de actualzar el Certificado de Lealtad, por favor intente de nuevo.");
            MensajeTO mensajeTO4 = mensajeTO2;
            return mensajeTO4;
        }
        finally {
            try {
                if (stmt != null) {
                    stmt.close();
                    stmt = null;
                }
                if (stmt1 != null) {
                    stmt1.close();
                    stmt1 = null;
                }
                if (rset != null) {
                    rset.close();
                    rset = null;
                }
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            }
            catch (Exception var21_25) {}
        }
        return mensajeTO;
    }

    public MensajeTO insertaCertificado(TelefonoTO telefonoTO, UsuarioTO usuarioTO, RetencionTO retencionTO, String sNMeses, String sMotivo, String sComenta, String sComenta2, String sCuentaAnt, String sRegionAnt, String sMesesAnt, String sTipo, Connection connection, long fechaTransaccion) throws CAException {
        MensajeTO mensajeTO;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rset = null;
        mensajeTO = null;
        try {
            try {
                conn = connection != null ? connection : ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                String sCuponExtra = "";
                String sFechaAdd = "";
                String sFechaAlta = "";
                sFechaAdd = telefonoTO.getMobileTO().getFecAddM2K().equals("") ? "null" : telefonoTO.getMobileTO().getFecAddM2K();
                sFechaAlta = telefonoTO.getMobileTO().getFecAltaUser().equals("") ? "null" : telefonoTO.getMobileTO().getFecAltaUser();
                sCuponExtra = !sTipo.equals("CE") ? "0" : retencionTO.getVCentifextra();
                String sUpdate = "";
                sUpdate = " INSERT INTO " + this.schema_database + "PTO_TBLCERTIFICADOS (Cuenta, Secuencia, Linea, IdUsuario, FechaOper, Folio, IdRegion, IdPuntoVta, Segmento, IdPlan, FechaAdd, FechaAlta," + "  MesesAntig, PorcAntig, VFact1, VFact2, VFact3, VFact4, Promedio, PorcARPU, NoCancel, PorcCob, Vcertif, VcentifExtra, Motivo, Comentario1, Comentario2, FCaduca, Ctanterior," + "  idRegionant, nMesesAnt, Estatus, IdUsuarioLib, FechaLib, ComntLib) " + " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,'A','',null,'')";
                stmt = conn.prepareStatement(sUpdate);
                stmt.setString(1, telefonoTO.getCuenta());
                if (telefonoTO.getSecuencia() == null || telefonoTO.getSecuencia().equals("")) {
                    stmt.setInt(2, 0);
                } else {
                    stmt.setInt(2, Integer.parseInt(telefonoTO.getSecuencia()));
                }
                stmt.setString(3, telefonoTO.getTelefono());
                stmt.setString(4, usuarioTO.getIdUsuario());
                stmt.setDate(5, new Date(fechaTransaccion));
                stmt.setString(6, Utils.generaFolio());
                stmt.setShort(7, (short)telefonoTO.getRegion());
                stmt.setString(8, usuarioTO.getPuntoVentaTO().getPtoVenta());
                stmt.setString(9, telefonoTO.getSegmento());
                stmt.setString(10, telefonoTO.getPlan());
                if (sFechaAdd == null) {
                    stmt.setDate(11, null);
                } else {
                    stmt.setDate(11, Date.valueOf(sFechaAdd));
                }
                if (sFechaAlta == null) {
                    stmt.setDate(12, null);
                } else {
                    stmt.setDate(12, Date.valueOf(sFechaAlta));
                }
                if (sNMeses == null || sNMeses.equals("")) {
                    stmt.setShort(13, (short) 0);
                } else {
                    stmt.setShort(13, Short.parseShort(sNMeses));
                }
                stmt.setShort(14, (short)retencionTO.getIIndAntig());
                Iterator iterator = telefonoTO.getMobileTO().getFacturas().iterator();
                int numParam = 15;
                while (iterator.hasNext()) {
                    FacturaTO facturaTO = (FacturaTO)iterator.next();
                    if (numParam >= 19) continue;
                    stmt.setDouble(numParam, facturaTO.getMonto());
                    ++numParam;
                }
                stmt.setDouble(19, telefonoTO.getMobileTO().getPromedio());
                stmt.setShort(20, (short)retencionTO.getPorcARPU());
                stmt.setShort(21, (short)telefonoTO.getMobileTO().getNoBajas());
                stmt.setShort(22, (short)retencionTO.getPorcCob());
                stmt.setInt(23, retencionTO.getValorCupon());
                if (sCuponExtra == null || sCuponExtra.equals("")) {
                    stmt.setInt(24, 0);
                } else {
                    stmt.setInt(24, Integer.parseInt(sCuponExtra));
                }
                stmt.setString(25, sMotivo);
                stmt.setString(26, sComenta);
                stmt.setString(27, sComenta2);
                Calendar hoy = Calendar.getInstance();
                hoy.add(6, 30);
                stmt.setDate(28, new Date(hoy.getTimeInMillis()));
                stmt.setString(29, sCuentaAnt);
                if (sRegionAnt == null || sRegionAnt.equals("")) {
                    stmt.setShort(30, (short) 0);
                } else {
                    stmt.setShort(30, Short.parseShort(sRegionAnt));
                }
                if (sMesesAnt == null || sMesesAnt.equals("")) {
                    stmt.setShort(31, (short) 0);
                } else {
                    stmt.setShort(31, Short.parseShort(sMesesAnt));
                }
                stmt.executeUpdate();
                mensajeTO = new MensajeTO();
                mensajeTO.setIdMensaje(0);
            }
            catch (Exception e) {
                mensajeTO = new MensajeTO();
                mensajeTO.setIdMensaje(1);
                mensajeTO.setMensaje(e.getMessage());
                MensajeTO mensajeTO2 = mensajeTO;
                if (rset != null) {
                    try {
                        rset.close();
                        rset = null;
                    }
                    catch (Exception var28_27) {
                        // empty catch block
                    }
                }
                if (stmt != null) {
                    try {
                        stmt.close();
                        stmt = null;
                    }
                    catch (Exception var28_28) {
                        // empty catch block
                    }
                }
                if (connection == null && conn != null) {
                    try {
                        conn.close();
                        conn = null;
                    }
                    catch (Exception var28_29) {
                        // empty catch block
                    }
                }
                return mensajeTO2;
            }
        }
        finally {
            if (rset != null) {
                try {
                    rset.close();
                    rset = null;
                }
                catch (Exception var28_33) {}
            }
            if (stmt != null) {
                try {
                    stmt.close();
                    stmt = null;
                }
                catch (Exception var28_34) {}
            }
            if (connection == null && conn != null) {
                try {
                    conn.close();
                    conn = null;
                }
                catch (Exception var28_35) {}
            }
        }
        return mensajeTO;
    }

    public RetencionTO getMotivos() throws CAException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rset = null;
        RetencionTO retencionTO = null;
        ArrayList<MotivoTO> motivos = null;
        MensajeTO mensajeTO = null;
        try {
            conn = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
            String sSql = "SELECT IDMOTIVO, MOTIVO FROM " + this.schema_database + "PTO_CTLRETENCION WHERE STATUS = 'A' ORDER BY IDMOTIVO";
            stmt = conn.prepareStatement(sSql);
            rset = stmt.executeQuery();
            motivos = new ArrayList<MotivoTO>();
            while (rset.next()) {
                MotivoTO motivoTO = new MotivoTO();
                motivoTO.setIdMotivo(String.valueOf(rset.getInt(1)));
                motivoTO.setDescripcion(rset.getString(2));
                motivos.add(motivoTO);
            }
            retencionTO = new RetencionTO();
            retencionTO.setMotivos(motivos);
            mensajeTO = new MensajeTO();
            mensajeTO.setIdMensaje(0);
            retencionTO.setMensajeTO(mensajeTO);
            RetencionTO retencionTO2 = retencionTO;
            return retencionTO2;
        }
        catch (Exception e) {
            retencionTO = new RetencionTO();
            mensajeTO = new MensajeTO();
            mensajeTO.setIdMensaje(1);
            mensajeTO.setMensaje("[getMotivos]" + e.getMessage());
            retencionTO.setMensajeTO(mensajeTO);
            RetencionTO retencionTO3 = retencionTO;
            return retencionTO3;
        }
        finally {
            if (rset != null) {
                try {
                    rset.close();
                    rset = null;
                }
                catch (Exception var11_15) {}
            }
            if (stmt != null) {
                try {
                    stmt.close();
                    stmt = null;
                }
                catch (Exception var11_16) {}
            }
            if (conn != null) {
                try {
                    conn.close();
                    conn = null;
                }
                catch (Exception var11_17) {}
            }
        }
    }
}

