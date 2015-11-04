/*
 * Decompiled with CFR 0_102.
 * 
 * Could not load the following classes:
 *  com.claro.exception.CAException
 *  com.claro.transfer.FacturaTO
 *  com.claro.transfer.MensajeTO
 *  com.claro.transfer.MobileTO
 *  com.claro.transfer.ParametrosTO
 *  com.claro.transfer.membresia.MembresiaTO
 *  com.claro.transfer.service.FileDataTO
 *  com.claro.util.Constantes
 *  com.claro.util.ServiceLocator
 *  com.claro.util.Utils
 *  org.apache.log4j.Logger
 */
package com.claro.dao;

import com.claro.exception.CAException;
import com.claro.transfer.FacturaTO;
import com.claro.transfer.MensajeTO;
import com.claro.transfer.MobileTO;
import com.claro.transfer.ParametrosTO;
import com.claro.transfer.membresia.MembresiaTO;
import com.claro.transfer.service.FileDataTO;
import com.claro.util.Constantes;
import com.claro.util.ServiceLocator;
import com.claro.util.Utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Logger;

public class ConsultaM2KDAO {
    protected final Logger logger = Logger.getLogger((String)"loggerCirculoAzul");
    protected final Logger error = Logger.getLogger((String)"loggerCirculoAzulError");

    private MobileTO consultaMobile(ParametrosTO parametrosTO, Connection connection) throws CAException {
        MobileTO mobileTO;
        StringBuffer query = new StringBuffer();
        String mertelM2K = parametrosTO.getRegion() == 9 ? "mobprc" : "mobp" + parametrosTO.getRegion() + "c";
        query.append("SELECT RET_ACCT_NUM,PHON_NUM_STAT_CD,GSM_IND,");
        query.append("       PHON_NUM_REAS_CD,RET_USER_SEQ_NUM, EFF_DT, ");
        query.append("       CASE WHEN substr(char(EFF_DT),2,1) = '1' THEN ");
        query.append("       substr(char(EFF_DT),7,2)||'/'||substr(char(EFF_DT),5,2)||'/'||");
        query.append("       substr(char(EFF_DT),3,2) ELSE ");
        query.append("       substr(char(EFF_DT),7,2)||'/'||substr(char(EFF_DT),5,2)||'/'||");
        query.append("       substr(char(EFF_DT),3,2) END,ESN_NUM, IMEI_NUM, ICCID_NUM,MOBL_PHON_NUM ");
        query.append("  FROM ").append(mertelM2K).append(".TSAC_PHON_NUM WHERE ");
        if (parametrosTO.getTelefono() != null) {
            query.append("  MOBL_PHON_NUM =").append(parametrosTO.getTelefono());
        } else {
            query.append("  RET_ACCT_NUM =").append(parametrosTO.getCuenta());
        }
        query.append("       AND EFF_DT = (SELECT MAX(EFF_DT) FROM ");
        query.append(mertelM2K).append(".TSAC_PHON_NUM");
        if (parametrosTO.getTelefono() != null) {
            query.append("       WHERE MOBL_PHON_NUM = ").append(parametrosTO.getTelefono()).append(") ");
            query.append(" ORDER BY RET_USER_SEQ_NUM DESC ");
        } else {
            query.append("       WHERE RET_ACCT_NUM = ").append(parametrosTO.getCuenta()).append(") ");
            query.append("ORDER BY RET_USER_SEQ_NUM DESC, PHON_NUM_STAT_CD ASC");
        }
        Statement statement = null;
        ResultSet resultSet = null;
        mobileTO = new MobileTO();
        try {
            try {
                long inicioConsulta = System.currentTimeMillis();
                this.logger.info((Object)("consultaMobile|InicioConsulta|" + Constantes.DATEFORMTALog.format(new Date()) + "|" + inicioConsulta));
                statement = connection.createStatement();
                resultSet = statement.executeQuery(query.toString());
                if (resultSet.next()) {
                    mobileTO = new MobileTO();
                    mobileTO.setCuenta(resultSet.getString("RET_ACCT_NUM"));
                    mobileTO.setStatus(resultSet.getString("PHON_NUM_STAT_CD"));
                    mobileTO.setTelefono(resultSet.getString("MOBL_PHON_NUM"));
                    mobileTO.setIdTecnologia(resultSet.getString("GSM_IND"));
                    mobileTO.setMotivo(resultSet.getString("PHON_NUM_REAS_CD"));
                    mobileTO.setSecuencia(resultSet.getString("RET_USER_SEQ_NUM"));
                    mobileTO.setFecEfectiva(resultSet.getString("EFF_DT"));
                    mobileTO.setFechaSuspension(Utils.formatFecha((String)resultSet.getString(7)));
                    mobileTO.setEsn(resultSet.getString("ESN_NUM"));
                    mobileTO.setImei(resultSet.getString("IMEI_NUM"));
                    mobileTO.setIccid(resultSet.getString("ICCID_NUM"));
                    mobileTO.agregaMensaje(0, "Proceso Exitoso");
                } else {
                    mobileTO.agregaMensaje(-1, "El telefono no existe o no esta dado de alta en la region.");
                }
                if (mobileTO.getCuenta() == null) {
                    mobileTO.agregaMensaje(-1, "No se encontro cuenta relacionada a la linea  en M2K.");
                }
                this.logger.info((Object)("consultaMobile|FinConsulta|" + Constantes.DATEFORMTALog.format(new Date()) + "|" + (System.currentTimeMillis() - inicioConsulta)));
            }
            catch (SQLException e) {
                this.error.info((Object)"SQLException.consultaMobile:", (Throwable)e);
                throw new CAException(-2, "SQLException.consultaMobile[" + e.toString() + "]", (Exception)e);
            }
            catch (Exception e) {
                this.error.info((Object)"Exception.consultaMobile:", (Throwable)e);
                throw new CAException(-3, "ConsultaM2KDAO.consultaMobile[" + e.toString() + "]", e);
            }
        }
        finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                    resultSet = null;
                }
                catch (Exception var11_14) {}
            }
            if (statement != null) {
                try {
                    statement.close();
                    statement = null;
                }
                catch (Exception var11_15) {}
            }
        }
        return mobileTO;
    }

    public MobileTO consultaDatosM2K(ParametrosTO parametrosTO) throws CAException {
        MobileTO mobileTO;
        Connection connection = null;
        mobileTO = null;
        try {
            long inicioProceso = System.currentTimeMillis();
            this.logger.info((Object)("consultaDatosM2K|Inicio Proceso|" + Constantes.DATEFORMTALog.format(new Date()) + "|" + inicioProceso));
            this.logger.info((Object)("consultaDatosM2K|Antes de Conexion|" + Constantes.DATEFORMTALog.format(new Date()) + "|" + inicioProceso));
            connection = parametrosTO.getRegion() == 4 || parametrosTO.getRegion() == 5 || parametrosTO.getRegion() == 9 ? ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcMobile459) : ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcMobile);
            this.logger.info((Object)("consultaDatosM2K|Despues de Conexion|" + Constantes.DATEFORMTALog.format(new Date()) + "|" + (System.currentTimeMillis() - inicioProceso)));
            mobileTO = this.consultaMobile(parametrosTO, connection);
            if (mobileTO.getIdMensaje() == -1) {
                MobileTO mobileTO2 = mobileTO;
                return mobileTO2;
            }
            try {
                this.obtieneDatosM2K(parametrosTO.getRegion(), mobileTO, connection);
                this.logger.info((Object)("consultaDatosM2K|FinProceso|" + Constantes.DATEFORMTALog.format(new Date()) + "|" + (System.currentTimeMillis() - inicioProceso)));
            }
            catch (Exception e) {
                this.error.info((Object)"Exception.consultaDatosM2K:", (Throwable)e);
                throw new CAException(-1, "ConsultaM2KDAO.consultaDatosM2K[" + e.toString() + "]", e);
            }
        } catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        finally {
            if (connection != null) {
                try {
                    connection.close();
                    connection = null;
                }
                catch (Exception var8_8) {}
            }
        }
        return mobileTO;
    }

    private void obtieneDatosM2K(int region, MobileTO mobileTO, Connection connection) throws CAException {
        String mercadoM2K = region == 4 ? "mobpmn" : (region == 9 ? "mobpmm" : "mobp" + region + "m");
        StringBuffer query1 = new StringBuffer();
        query1.append("SELECT T.BILL_CYC_ID,T.COL_STAT_CD,T.CR_CLASS_CD,");
        query1.append("       CASE WHEN substr(char(T.ORIG_SET_UP_DT),2,1)='1' THEN ");
        query1.append("       substr(char(T.ORIG_SET_UP_DT),7,2)||'/'||");
        query1.append("       substr(char(T.ORIG_SET_UP_DT),5,2)||'/'||");
        query1.append("       substr(char(T.ORIG_SET_UP_DT),3,2) ELSE ");
        query1.append("       substr(char(T.ORIG_SET_UP_DT),7,2)||'/'||");
        query1.append("       substr(char(T.ORIG_SET_UP_DT),5,2)||'/'||");
        query1.append("       substr(char(T.ORIG_SET_UP_DT),3,2) END,");
        query1.append("       B.FST_NAME_TXT, B.LST_NAME_TXT, UP.PRICE_PLAN_ID,");
        query1.append("       U.SERV_CNTCT_LEN, ");
        query1.append("       CASE WHEN substr(char(U.SERV_CNTCT_STRT_DT),2,1) = '1' THEN ");
        query1.append("       substr(char(U.SERV_CNTCT_STRT_DT),7,2)||'/'||");
        query1.append("       substr(char(U.SERV_CNTCT_STRT_DT),5,2)||'/'||");
        query1.append("       substr(char(U.SERV_CNTCT_STRT_DT),3,2) ELSE ");
        query1.append("       substr(char(U.SERV_CNTCT_STRT_DT),7,2)||'/'||");
        query1.append("       substr(char(U.SERV_CNTCT_STRT_DT),5,2)||'/'||");
        query1.append("       substr(char(U.SERV_CNTCT_STRT_DT),3,2) END,");
        query1.append("       CASE WHEN substr(char(UP.EFF_DT),2,1) = '1' THEN ");
        query1.append("       substr(char(UP.EFF_DT),7,2)||'/'||");
        query1.append("       substr(char(UP.EFF_DT),5,2)||'/'||");
        query1.append("       substr(char(UP.EFF_DT),3,2) ELSE ");
        query1.append("       substr(char(UP.EFF_DT),7,2)||'/'||");
        query1.append("       substr(char(UP.EFF_DT),5,2)||'/'||");
        query1.append("       substr(char(UP.EFF_DT),3,2) END , T.CONS_ACCT_NUM, U.EQUIP_MAKE_TXT, U.EQUIP_MODEL_TXT, ");
        query1.append("\t\t  B.CONT_PHON_NUM, B.CONT_PHON_EXT, B.ALT_PHON_NUM, B.ALT_PHON_EXT, B.E_MAIL_ADDR , T.SOC_SEC_NUM_TXT\t");
        query1.append(" FROM ").append(mercadoM2K).append(".TCUST T JOIN ");
        query1.append(mercadoM2K).append(".TCUST_ADDR B on ");
        query1.append("     B.ACCT_NUM = T.ACCT_NUM JOIN ").append(mercadoM2K).append(".TUSER U on ");
        query1.append("     U.ACCT_NUM = T.ACCT_NUM JOIN ").append(mercadoM2K).append(".TUSER_PRICING UP on ");
        query1.append("     UP.ACCT_NUM = T.ACCT_NUM ");
        query1.append(" \tWHERE T.MKT_ID = 'R0").append(region).append("'");
        query1.append("     AND T.ACCT_NUM = ").append(mobileTO.getCuenta());
        query1.append("     AND B.MKT_ID = T.MKT_ID AND B.ADDR_TYP_CD = 'CU' ");
        query1.append("     AND U.MKT_ID = T.MKT_ID AND U.USER_SEQ_NUM = ").append(mobileTO.getSecuencia());
        query1.append("     AND UP.MKT_ID = T.MKT_ID AND UP.USER_SEQ_NUM = U.USER_SEQ_NUM ");
        query1.append("     AND UP.EFF_DT = (SELECT max(EFF_DT) ");
        query1.append("                        FROM ").append(mercadoM2K).append(".TUSER_PRICING");
        query1.append("                       WHERE MKT_ID = 'R0").append(region).append("'");
        query1.append("                             AND ACCT_NUM =  ").append(mobileTO.getCuenta());
        query1.append("                             AND USER_SEQ_NUM =").append(mobileTO.getSecuencia()).append(")");
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            try {
                long inicioConsulta = System.currentTimeMillis();
                this.logger.info((Object)("obtieneDatosM2K|InicioConsulta|" + Constantes.DATEFORMTALog.format(new Date()) + "|" + inicioConsulta));
                statement = connection.createStatement();
                resultSet = statement.executeQuery(query1.toString());
                if (resultSet.next()) {
                    String lAddM2K = resultSet.getString("SERV_CNTCT_LEN");
                    mobileTO.setCiclo(resultSet.getString("BILL_CYC_ID"));
                    mobileTO.setPlanM2K(resultSet.getString("PRICE_PLAN_ID"));
                    mobileTO.setEstCobranza(resultSet.getString("COL_STAT_CD"));
                    mobileTO.setLastName(resultSet.getString("LST_NAME_TXT"));
                    mobileTO.setFirstName(resultSet.getString("FST_NAME_TXT"));
                    mobileTO.setTelContacto1(resultSet.getString("CONT_PHON_NUM"));
                    mobileTO.setTelContacto2(resultSet.getString("ALT_PHON_NUM"));
                    mobileTO.setExtContacto1(resultSet.getString("CONT_PHON_EXT"));
                    mobileTO.setExtContacto2(resultSet.getString("ALT_PHON_EXT"));
                    mobileTO.setCuentaCorreo(resultSet.getString("E_MAIL_ADDR"));
                    mobileTO.setFecEfectiva(Utils.formatFecha((String)resultSet.getString(10)));
                    String sFecAltaUser = resultSet.getString(4);
                    if (sFecAltaUser == null || sFecAltaUser.equals("2000-00-00")) {
                        mobileTO.setFecAltaUser(mobileTO.getFecEfectiva());
                    } else {
                        mobileTO.setFecAltaUser(Utils.formatFecha((String)sFecAltaUser));
                    }
                    String sFecAddM2K = resultSet.getString(9);
                    if (sFecAddM2K == null || sFecAddM2K.equals("00/00/00")) {
                        mobileTO.setFecAddM2K(mobileTO.getFecAltaUser());
                    } else {
                        mobileTO.setFecAddM2K(Utils.formatFecha((String)sFecAddM2K));
                    }
                    mobileTO.setClaseCredit(resultSet.getString("CR_CLASS_CD"));
                    if (lAddM2K.trim().equals("")) {
                        if (region == 9) {
                            mobileTO.setAddM2K("012");
                        } else {
                            mobileTO.setAddM2K("018");
                        }
                    } else {
                        mobileTO.setAddM2K(lAddM2K);
                    }
                    mobileTO.setCuentaPadre(resultSet.getString(11));
                    mobileTO.setMarca(resultSet.getString("EQUIP_MAKE_TXT"));
                    mobileTO.setModelo(resultSet.getString("EQUIP_MODEL_TXT"));
                    mobileTO.setRfc(resultSet.getString("SOC_SEC_NUM_TXT") != null ? resultSet.getString("SOC_SEC_NUM_TXT").trim() : null);
                    mobileTO.agregaMensaje(0, "Proceso Exitoso.");
                } else {
                    mobileTO.agregaMensaje(-1, "No se pudieron obtener los datos de la Linea en M2K.");
                }
                this.logger.info((Object)("obtieneDatosM2K|FinConsulta|" + Constantes.DATEFORMTALog.format(new Date()) + "|" + (System.currentTimeMillis() - inicioConsulta)));
            }
            catch (SQLException e) {
                this.error.info((Object)"SQLException.obtieneDatosM2K:", (Throwable)e);
                throw new CAException(-1, "SQLException.obtieneDatosM2K[" + e.toString() + "]", (Exception)e);
            }
            catch (Exception e) {
                this.error.info((Object)"Exception.obtieneDatosM2K:", (Throwable)e);
                throw new CAException(-1, "ConsultaM2KDAO.obtieneDatosM2K[" + e.toString() + "]", e);
            }
        }
        finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                    resultSet = null;
                }
                catch (Exception var14_17) {}
            }
            if (statement != null) {
                try {
                    statement.close();
                    statement = null;
                }
                catch (Exception var14_18) {}
            }
        }
    }

    public void obtenPromedioFacturaciones(MobileTO mobileTO, int region, String cuenta) throws CAException {
        String mercadoM2K = region == 4 ? "mobpmn" : (region == 9 ? "mobpmm" : "mobp" + region + "m");
        String mertelM2K = region == 9 ? "mobprc" : "mobp" + region + "c";
        StringBuffer query1 = new StringBuffer();
        query1.append(" SELECT MKT_ID,ACCT_NUM,BILL_DT FROM ").append(mercadoM2K).append(".TIMG_USER_HDR");
        query1.append("  WHERE ACCT_NUM =").append(cuenta).append(" AND MKT_ID = 'R0").append(region).append("'");
        query1.append("  AND  SAMP_BILL_IND = 'N'  ");
        query1.append("  AND USER_SEQ_NUM   =  ").append(mobileTO.getSecuencia());
        query1.append("  ORDER By 3 desc ");
        query1.append("  FETCH FIRST 6 rows only ");
        StringBuffer query2 = new StringBuffer();
        query2.append("SELECT  TOT_MON_SERV_AMT  - MON_SERV_DISC_AMT    + OPT_FEAT_TOT_AMT     -     ");
        query2.append(" OPT_FEAT_DISC_AMT    +  OCC_TOT_AMT  +  USAG_CHRG_TOT_AMT    -  USAG_DISC_AMT        +    ");
        query2.append(" ROM_CHRG_TOT_AMT     +   TOLL_CHARG_TOT_AMT   +  VM_AIR_TOT_AMT       -    VM_TOLL_TOT_AMT   ");
        query2.append(" FROM ").append(mercadoM2K).append(".TIMG_USER_HDR  ");
        query2.append(" WHERE MKT_ID   = ? ");
        query2.append(" AND  SAMP_BILL_IND = 'N'  ");
        query2.append(" AND ACCT_NUM       = ?");
        query2.append(" AND BILL_DT        = ?");
        query2.append(" AND USER_SEQ_NUM   = ? ");
        StringBuffer query3 = new StringBuffer();
        query3.append(" SELECT count(*) ");
        query3.append(" FROM ").append(mertelM2K).append(".TSAC_PHON_NUM  ");
        query3.append(" WHERE RET_ACCT_NUM = ? ");
        query3.append(" AND PHON_NUM_REAS_CD = 'CLIMO'  ");
        query3.append(" AND eff_dt > int(substr(char(current date - 5 day,iso),1,1)|| substr(char(current date - 1 year,iso),3,2)|| substr(char(current date - 1 year,iso),6,2)|| substr(char(current date - 1 year,iso),9,2) ) ");
        PreparedStatement statementSelectFecFac = null;
        PreparedStatement statementFactura = null;
        PreparedStatement statementBajaClimo = null;
        ResultSet resultSetFecFac = null;
        ResultSet resultSetFactura = null;
        ResultSet resultSetBajaClimo = null;
        Connection connection = null;
        int iNoFactura = 1;
        int iCount = 0;
        try {
            try {
                connection = region == 4 || region == 5 || region == 9 ? ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcMobile459) : ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcMobile);
                statementSelectFecFac = connection.prepareStatement(query1.toString());
                statementFactura = connection.prepareStatement(query2.toString());
                statementBajaClimo = connection.prepareStatement(query3.toString());
                resultSetFecFac = statementSelectFecFac.executeQuery();
                ArrayList<FacturaTO> facturas = new ArrayList<FacturaTO>();
                int i = 0;
                while (resultSetFecFac.next()) {
                    ++i;
                    String sRegion = resultSetFecFac.getString(1);
                    BigDecimal cuentaBig = resultSetFecFac.getBigDecimal(2);
                    BigDecimal fechaFac = resultSetFecFac.getBigDecimal(3);
                    statementFactura.clearParameters();
                    statementFactura.setString(1, sRegion);
                    statementFactura.setBigDecimal(2, cuentaBig);
                    statementFactura.setBigDecimal(3, fechaFac);
                    statementFactura.setInt(4, mobileTO.getSecuencia() != null ? Integer.parseInt(mobileTO.getSecuencia()) : 1);
                    resultSetFactura = statementFactura.executeQuery();
                    if (resultSetFactura.next()) {
                        FacturaTO facturaTO = new FacturaTO();
                        facturaTO.setMonto(resultSetFactura.getDouble(1));
                        facturaTO.setFechaFactura(fechaFac);
                        facturas.add(facturaTO);
                    }
                    if (resultSetFactura != null) {
                        try {
                            resultSetFactura.close();
                            resultSetFactura = null;
                        }
                        catch (Exception facturaTO) {
                            // empty catch block
                        }
                    }
                    ++iNoFactura;
                    ++iCount;
                }
                if (iCount == 0) {
                    mobileTO.agregaMensaje(-1, "No existe en M2K region " + region + " la cuenta " + cuenta);
                }
                mobileTO.setFacturas(facturas);
                mobileTO.setSPromFacturaAV(Double.toString(mobileTO.getPromedio()));
                statementBajaClimo.clearParameters();
                statementBajaClimo.setString(1, cuenta);
                resultSetBajaClimo = statementBajaClimo.executeQuery();
                if (resultSetBajaClimo.next()) {
                    mobileTO.setNoBajas(resultSetBajaClimo.getInt(1));
                }
                if (resultSetBajaClimo != null) {
                    try {
                        resultSetBajaClimo.close();
                        resultSetBajaClimo = null;
                    }
                    catch (Exception sRegion) {}
                }
            }
            catch (SQLException e) {
                throw new CAException(-1, "SQLException.obtenPromedioFacturaciones[" + e.toString() + "]", (Exception)e);
            }
            catch (Exception e) {
                throw new CAException(-1, "ConsultaM2KDAO.obtenPromedioFacturaciones[" + e.toString() + "]", e);
            }
        }
        finally {
            if (resultSetFactura != null) {
                try {
                    resultSetFactura.close();
                    resultSetFactura = null;
                }
                catch (Exception var25_34) {}
            }
            if (resultSetFecFac != null) {
                try {
                    resultSetFecFac.close();
                    resultSetFecFac = null;
                }
                catch (Exception var25_35) {}
            }
            if (statementFactura != null) {
                try {
                    statementFactura.close();
                    statementFactura = null;
                }
                catch (Exception var25_36) {}
            }
            if (statementSelectFecFac != null) {
                try {
                    statementSelectFecFac.close();
                    statementSelectFecFac = null;
                }
                catch (Exception var25_37) {}
            }
            if (connection != null) {
                try {
                    connection.close();
                    connection = null;
                }
                catch (Exception var25_38) {}
            }
        }
    }

    public MobileTO consultaPorCuenta(String lCta, int nReg) throws CAException {
        MobileTO mobileTO;
        Connection connection = null;
        mobileTO = null;
        Statement stmt = null;
        ResultSet rset = null;
        String sQuery = null;
        String mercadoM2K = nReg == 4 ? "mobpmn" : (nReg == 9 ? "mobpmm" : "mobp" + nReg + "m");
        if (nReg == 9) {
            String mertelM2K = "mobprc";
        } else {
            String mertelM2K = "mobp" + nReg + "c";
        }
        sQuery = "SELECT  case when substr(char(T.ORIG_SET_UP_DT),2,1)='1' then  Substr(char(T.ORIG_SET_UP_DT),7,2)||'/'||substr(char(T.ORIG_SET_UP_DT),5,2)||'/'|| substr(char(T.ORIG_SET_UP_DT),3,2) ELSE  substr(char(T.ORIG_SET_UP_DT),7,2)||'/'||substr(char(T.ORIG_SET_UP_DT),5,2)||'/'|| substr(char(T.ORIG_SET_UP_DT),3,2) end, T.ACCT_STAT_CD, T.COL_STAT_CD  FROM " + mercadoM2K + ".TCUST T " + " JOIN " + mercadoM2K + ".TUSER_PRICING UP on UP.ACCT_NUM = T.ACCT_NUM" + " WHERE T.MKT_ID = 'R0" + nReg + "' AND T.ACCT_NUM =" + lCta + " AND UP.MKT_ID = T.MKT_ID" + " AND UP.EFF_DT = (SELECT max(EFF_DT) FROM " + mercadoM2K + ".TUSER_PRICING" + " WHERE MKT_ID = 'R0" + nReg + "'" + " AND ACCT_NUM =" + lCta + ")";
        try {
            try {
                connection = nReg == 4 || nReg == 5 || nReg == 9 ? ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcMobile459) : ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcMobile);
                stmt = connection.createStatement();
                rset = stmt.executeQuery(sQuery);
                mobileTO = new MobileTO();
                if (rset.next()) {
                    mobileTO.setStatus(rset.getString("ACCT_STAT_CD"));
                    mobileTO.setEstCobranza(rset.getString("COL_STAT_CD"));
                    mobileTO.setFecAltaUser(Utils.formatFecha((String)rset.getString(1)));
                } else {
                    mobileTO.setMensaje("La cuenta no existe.");
                }
            }
            catch (SQLException e) {
                throw new CAException(0, "[M2K] Error al obtener datos de cuenta: " + lCta + " - " + e.getMessage());
            }
            catch (Exception e) {
                throw new CAException(0, "[M2K] Error: " + lCta + " - " + e.getMessage());
            }
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
            catch (Exception var12_15) {}
        }
        return mobileTO;
    }

    public void obtenFacturaciones(MobileTO mobileTO, int region, String cuenta, int numFacturas) throws CAException {
        String mercadoM2K = region == 4 ? "mobpmn" : (region == 9 ? "mobpmm" : "mobp" + region + "m");
        String mertelM2K = region == 9 ? "mobprc" : "mobp" + region + "c";
        StringBuffer query1 = new StringBuffer();
        query1.append(" SELECT MKT_ID,ACCT_NUM,BILL_DT FROM ").append(mercadoM2K).append(".TIMG_USER_HDR");
        query1.append("  WHERE ACCT_NUM =").append(cuenta).append(" AND MKT_ID = 'R0").append(region).append("'");
        query1.append("  AND  SAMP_BILL_IND = 'N'  ");
        query1.append("  AND USER_SEQ_NUM   =  ").append(mobileTO.getSecuencia());
        query1.append("  ORDER By 3 desc ");
        String rowsOnly = " FETCH FIRST " + numFacturas + " rows only ";
        query1.append(rowsOnly);
        StringBuffer query2 = new StringBuffer();
        query2.append("SELECT  TOT_MON_SERV_AMT  - MON_SERV_DISC_AMT    + OPT_FEAT_TOT_AMT     -     ");
        query2.append(" OPT_FEAT_DISC_AMT    +  OCC_TOT_AMT  +  USAG_CHRG_TOT_AMT    -  USAG_DISC_AMT        +    ");
        query2.append(" ROM_CHRG_TOT_AMT     +   TOLL_CHARG_TOT_AMT   +  VM_AIR_TOT_AMT       -    VM_TOLL_TOT_AMT   ");
        query2.append(" FROM ").append(mercadoM2K).append(".TIMG_USER_HDR  ");
        query2.append(" WHERE MKT_ID   = ? ");
        query2.append(" AND  SAMP_BILL_IND = 'N'  ");
        query2.append(" AND ACCT_NUM       = ?");
        query2.append(" AND BILL_DT        = ?");
        query2.append(" AND USER_SEQ_NUM   = ? ");
        StringBuffer query3 = new StringBuffer();
        query3.append(" SELECT count(*) ");
        query3.append(" FROM ").append(mertelM2K).append(".TSAC_PHON_NUM  ");
        query3.append(" WHERE RET_ACCT_NUM = ? ");
        query3.append(" AND PHON_NUM_REAS_CD = 'CLIMO'  ");
        query3.append(" AND eff_dt > int(substr(char(current date - 5 day,iso),1,1)|| substr(char(current date - 1 year,iso),3,2)|| substr(char(current date - 1 year,iso),6,2)|| substr(char(current date - 1 year,iso),9,2) ) ");
        PreparedStatement statementSelectFecFac = null;
        PreparedStatement statementFactura = null;
        PreparedStatement statementBajaClimo = null;
        ResultSet resultSetFecFac = null;
        ResultSet resultSetFactura = null;
        ResultSet resultSetBajaClimo = null;
        Connection connection = null;
        int iNoFactura = 1;
        int iCount = 0;
        try {
            try {
                connection = region == 4 || region == 5 || region == 9 ? ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcMobile459) : ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcMobile);
                statementSelectFecFac = connection.prepareStatement(query1.toString());
                statementFactura = connection.prepareStatement(query2.toString());
                statementBajaClimo = connection.prepareStatement(query3.toString());
                resultSetFecFac = statementSelectFecFac.executeQuery();
                ArrayList<FacturaTO> facturas = new ArrayList<FacturaTO>();
                int i = 0;
                while (resultSetFecFac.next()) {
                    ++i;
                    String sRegion = resultSetFecFac.getString(1);
                    BigDecimal cuentaBig = resultSetFecFac.getBigDecimal(2);
                    BigDecimal fechaFac = resultSetFecFac.getBigDecimal(3);
                    statementFactura.clearParameters();
                    statementFactura.setString(1, sRegion);
                    statementFactura.setBigDecimal(2, cuentaBig);
                    statementFactura.setBigDecimal(3, fechaFac);
                    statementFactura.setInt(4, mobileTO.getSecuencia() != null ? Integer.parseInt(mobileTO.getSecuencia()) : 1);
                    resultSetFactura = statementFactura.executeQuery();
                    if (resultSetFactura.next()) {
                        FacturaTO facturaTO = new FacturaTO();
                        facturaTO.setMonto(resultSetFactura.getDouble(1));
                        facturaTO.setFechaFactura(fechaFac);
                        facturas.add(facturaTO);
                    }
                    if (resultSetFactura != null) {
                        try {
                            resultSetFactura.close();
                            resultSetFactura = null;
                        }
                        catch (Exception facturaTO) {
                            // empty catch block
                        }
                    }
                    ++iNoFactura;
                    ++iCount;
                }
                if (iCount == 0) {
                    mobileTO.agregaMensaje(-1, "No existe en M2K region " + region + " la cuenta " + cuenta);
                }
                mobileTO.setFacturas(facturas);
                mobileTO.setSPromFacturaAV(Double.toString(mobileTO.getPromedio()));
                statementBajaClimo.clearParameters();
                statementBajaClimo.setString(1, cuenta);
                resultSetBajaClimo = statementBajaClimo.executeQuery();
                if (resultSetBajaClimo.next()) {
                    mobileTO.setNoBajas(resultSetBajaClimo.getInt(1));
                }
                if (resultSetBajaClimo != null) {
                    try {
                        resultSetBajaClimo.close();
                        resultSetBajaClimo = null;
                    }
                    catch (Exception sRegion) {}
                }
            }
            catch (SQLException e) {
                throw new CAException(-1, "SQLException.obtenPromedioFacturaciones[" + e.toString() + "]", (Exception)e);
            }
            catch (Exception e) {
                throw new CAException(-1, "ConsultaM2KDAO.obtenPromedioFacturaciones[" + e.toString() + "]", e);
            }
        }
        finally {
            if (resultSetFactura != null) {
                try {
                    resultSetFactura.close();
                    resultSetFactura = null;
                }
                catch (Exception var27_36) {}
            }
            if (resultSetFecFac != null) {
                try {
                    resultSetFecFac.close();
                    resultSetFecFac = null;
                }
                catch (Exception var27_37) {}
            }
            if (statementFactura != null) {
                try {
                    statementFactura.close();
                    statementFactura = null;
                }
                catch (Exception var27_38) {}
            }
            if (statementSelectFecFac != null) {
                try {
                    statementSelectFecFac.close();
                    statementSelectFecFac = null;
                }
                catch (Exception var27_39) {}
            }
            if (connection != null) {
                try {
                    connection.close();
                    connection = null;
                }
                catch (Exception var27_40) {}
            }
        }
    }

    public MensajeTO consultaTipoPlan(String idPlan, int nReg) throws CAException {
        MensajeTO mensajeTO;
        Connection connection = null;
        Statement stmt = null;
        ResultSet rset = null;
        String sQuery = null;
        mensajeTO = null;
        String mercadoM2K = nReg == 4 ? "mobpmn" : (nReg == 9 ? "mobpmm" : "mobp" + nReg + "m");
        if (nReg == 9) {
            String mertelM2K = "mobprc";
        } else {
            String mertelM2K = "mobp" + nReg + "c";
        }
        sQuery = "SELECT *  FROM " + mercadoM2K + ".TPRICE_PLAN_HDR " + " WHERE MKT_ID = 'R0" + nReg + "' AND PRICE_PLAN_ID  = '" + idPlan + "' AND PRICE_PLAN_TYP_CD = 'PF' ";
        try {
            try {
                connection = nReg == 4 || nReg == 5 || nReg == 9 ? ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcMobile459) : ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcMobile);
                stmt = connection.createStatement();
                rset = stmt.executeQuery(sQuery);
                mensajeTO = new MensajeTO();
                if (rset.next()) {
                    mensajeTO.agregaMensaje(2, "El plan no es compatible para redimir por tiempo aire.");
                }
            }
            catch (SQLException e) {
                throw new CAException(0, "[M2K] Error al obtener el tipo de plan - " + e.getMessage());
            }
            catch (Exception e) {
                throw new CAException(0, "[M2K] Error: " + idPlan + " - " + e.getMessage());
            }
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
            catch (Exception var12_15) {}
        }
        return mensajeTO;
    }

    public ArrayList<MembresiaTO> consultaDatosM2KMembresias(ParametrosTO parametrosTO, FileDataTO fileDataTO) throws CAException {
        ArrayList<MembresiaTO> arrayMembresia;
        Connection connection = null;
        MobileTO mobileTO = null;
        arrayMembresia = null;
        try {
            try {
                long inicioProceso = System.currentTimeMillis();
                this.logger.info((Object)("consultaDatosM2KMembresias|Inicio Proceso|" + Constantes.DATEFORMTALog.format(new Date()) + "|" + inicioProceso));
                this.logger.info((Object)("consultaDatosM2KMembresias|Antes de Conexion|" + Constantes.DATEFORMTALog.format(new Date()) + "|" + inicioProceso));
                connection = parametrosTO.getRegion() == 4 || parametrosTO.getRegion() == 5 || parametrosTO.getRegion() == 9 ? ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcMobile459) : ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcMobile);
                this.logger.info((Object)("consultaDatosM2K|Despues de Conexion|" + Constantes.DATEFORMTALog.format(new Date()) + "|" + (System.currentTimeMillis() - inicioProceso)));
                arrayMembresia = new ArrayList<MembresiaTO>();
                InputStreamReader inputStreamReader = null;
                BufferedReader reader = null;
                inputStreamReader = new InputStreamReader(fileDataTO.getData());
                reader = new BufferedReader(inputStreamReader);
                String telefono = "";
                while ((telefono = reader.readLine()) != null) {
                    if (telefono.equals("")) continue;
                    MembresiaTO membresiaTO = new MembresiaTO();
                    parametrosTO.setTelefono(telefono.replace((CharSequence)",", (CharSequence)"").trim());
                    mobileTO = this.consultaMobile(parametrosTO, connection);
                    if (mobileTO.getIdMensaje() == 0) {
                        this.obtieneDatosCtesMembresiaM2K(parametrosTO.getRegion(), mobileTO, connection);
                    }
                    if (mobileTO.getIdMensaje() != 0) continue;
                    membresiaTO.setTelefono(parametrosTO.getTelefono());
                    membresiaTO.setCuenta(mobileTO.getCuenta());
                    membresiaTO.setSecuencia(Integer.parseInt(mobileTO.getSecuencia()));
                    membresiaTO.setNombre(mobileTO.getNombre());
                    membresiaTO.setApPaterno(mobileTO.getFirstName());
                    membresiaTO.setApMaterno(mobileTO.getLastName());
                    arrayMembresia.add(membresiaTO);
                }
                this.logger.info((Object)("consultaDatosM2KMembresias|FinProceso|" + Constantes.DATEFORMTALog.format(new Date()) + "|" + (System.currentTimeMillis() - inicioProceso)));
            }
            catch (SQLException e) {
                this.error.info((Object)"SQLException.consultaDatosM2KMembresias:", (Throwable)e);
                throw new CAException(-1, "SQLException.consultaDatosM2KMembresias[" + e.toString() + "]", (Exception)e);
            }
            catch (Exception e) {
                this.error.info((Object)"Exception.consultaDatosM2KMembresias:", (Throwable)e);
                throw new CAException(-1, "ConsultaM2KDAO.consultaDatosM2KMembresias[" + e.toString() + "]", e);
            }
        }
        finally {
            if (connection != null) {
                try {
                    connection.close();
                    connection = null;
                }
                catch (Exception var13_15) {}
            }
        }
        return arrayMembresia;
    }

    private void obtieneDatosCtesMembresiaM2K(int region, MobileTO mobileTO, Connection connection) throws CAException {
        String mercadoM2K = region == 4 ? "mobpmn" : (region == 9 ? "mobpmm" : "mobp" + region + "m");
        StringBuffer query1 = new StringBuffer();
        query1.append("SELECT A.FST_NAME_TXT,A.LST_NAME_TXT ");
        query1.append(" FROM ").append(mercadoM2K).append(".TCUST_ADDR A, ");
        query1.append(mercadoM2K).append(".TCUST B ");
        query1.append(" \tWHERE A.MKT_ID = 'R0").append(region).append("'");
        query1.append("     AND A.ACCT_NUM = ").append(mobileTO.getCuenta());
        query1.append("     AND A.ADDR_TYP_CD = 'CU' ");
        query1.append("     AND B.BILL_CYC_ID NOT IN ('20','31','32','60','61','97','98','99')  ");
        query1.append("     AND B.COL_STAT_CD IN ('BH','BN','ST')  ");
        query1.append("     AND B.CR_CLASS_CD NOT IN ('AR')   ");
        query1.append("     AND B.MKT_ID = A.MKT_ID   ");
        query1.append("     AND B.ACCT_NUM = A.ACCT_NUM   ");
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            try {
                long inicioConsulta = System.currentTimeMillis();
                this.logger.info((Object)("obtieneDatosCtesMembresiaM2K|InicioConsulta|" + Constantes.DATEFORMTALog.format(new Date()) + "|" + inicioConsulta));
                statement = connection.createStatement();
                resultSet = statement.executeQuery(query1.toString());
                if (resultSet.next()) {
                    mobileTO.setLastName(resultSet.getString("LST_NAME_TXT"));
                    mobileTO.setFirstName(resultSet.getString("FST_NAME_TXT"));
                    mobileTO.agregaMensaje(0, "Proceso Exitoso.");
                } else {
                    mobileTO.agregaMensaje(-1, "No se pudieron obtener los datos del cliente de la L\u00ednea en M2K.");
                }
                this.logger.info((Object)("obtieneDatosCtesMembresiaM2K|FinConsulta|" + Constantes.DATEFORMTALog.format(new Date()) + "|" + (System.currentTimeMillis() - inicioConsulta)));
            }
            catch (SQLException e) {
                this.error.info((Object)"SQLException.obtieneDatosCtesMembresiaM2K:", (Throwable)e);
                throw new CAException(-1, "SQLException.obtieneDatosCtesMembresiaM2K[" + e.toString() + "]", (Exception)e);
            }
            catch (Exception e) {
                this.error.info((Object)"Exception.obtieneDatosCtesMembresiaM2K:", (Throwable)e);
                throw new CAException(-1, "ConsultaM2KDAO.obtieneDatosCtesMembresiaM2K[" + e.toString() + "]", e);
            }
        }
        finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                    resultSet = null;
                }
                catch (Exception var11_14) {}
            }
            if (statement != null) {
                try {
                    statement.close();
                    statement = null;
                }
                catch (Exception var11_15) {}
            }
        }
    }
}

