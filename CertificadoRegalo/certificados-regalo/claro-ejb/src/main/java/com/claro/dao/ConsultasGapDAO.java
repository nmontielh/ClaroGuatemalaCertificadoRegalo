/*
 * Decompiled with CFR 0_102.
 * 
 * Could not load the following classes:
 *  com.claro.exception.CAException
 *  com.claro.transfer.MensajeTO
 *  com.claro.transfer.MobileTO
 *  com.claro.transfer.ParametrosTO
 *  com.claro.transfer.PlanTO
 *  com.claro.transfer.TelefonoTO
 *  com.claro.transfer.gap.InfoPromocionGapTO
 *  com.claro.transfer.gap.PromocionCaTO
 *  com.claro.util.ServiceLocator
 *  org.apache.log4j.Logger
 */
package com.claro.dao;

import com.claro.exception.CAException;
import com.claro.transfer.MensajeTO;
import com.claro.transfer.MobileTO;
import com.claro.transfer.ParametrosTO;
import com.claro.transfer.PlanTO;
import com.claro.transfer.TelefonoTO;
import com.claro.transfer.gap.InfoPromocionGapTO;
import com.claro.transfer.gap.PromocionCaTO;
import com.claro.util.ServiceLocator;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

public class ConsultasGapDAO {
    protected final Logger logger = Logger.getLogger((String)"loggerCirculoAzul");
    protected final Logger error = Logger.getLogger((String)"loggerCirculoAzulError");
    private String schemaGap = null;

    public ConsultasGapDAO() {
        try {
            this.schemaGap = ServiceLocator.getInstance().getVariable(ServiceLocator.schemaGap_database);
        }
        catch (Exception e) {
            this.error.error((Object)("ConsultasGapDAO" + e.toString()));
            e.printStackTrace();
        }
    }

    public void consultaAnacrGap(MobileTO mobileTO, TelefonoTO telefonoTO, PlanTO planTO, ParametrosTO parametrosTO) throws CAException {
        StringBuffer stringBuffer = new StringBuffer();
        Connection connection = null;
        StringBuffer sRegion = new StringBuffer();
        Statement statement = null;
        ResultSet resultSet = null;
        String dwtag = "";
        try {
            telefonoTO.setSAnacr("0.9");
            String nCalificacion = "";
            if (!(planTO.getBanMixto() == null || planTO.getBanMixto().equals(""))) {
                dwtag = "S".equals(planTO.getBanMixto().trim()) ? "DWTAG_VALORACION_MIXTO" : "DWTAG_VALORACION_POSPAGO";
            } else {
                return;
            }
            try {
                sRegion.append("R0").append(String.valueOf(parametrosTO.getRegion()));
                stringBuffer.append(" SELECT NCALIFICACION FROM ").append(this.schemaGap).append(dwtag);
                stringBuffer.append(" WHERE NLINEAID= ").append(new BigInteger(mobileTO.getTelefono().trim()));
                stringBuffer.append(" AND SREGIONID= '").append(sRegion.toString()).append("'");
                connection = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcGap);
                statement = connection.createStatement();
                statement.setQueryTimeout(15);
                resultSet = statement.executeQuery(stringBuffer.toString());
                if (resultSet.next()) {
                    nCalificacion = resultSet.getBigDecimal("NCALIFICACION").toString();
                }
                if (!"".equals(nCalificacion)) {
                    if ("0".equals(nCalificacion)) {
                        telefonoTO.setSAnacr(new BigDecimal(nCalificacion).setScale(1).toString());
                    } else {
                        telefonoTO.setSAnacr(nCalificacion);
                    }
                }
            }
            catch (SQLException exception) {
                throw new CAException(-1, "ConsultasGapDAO.consultaAnacrGap:[" + exception.toString() + "]");
            }
            catch (Exception e) {
                throw new CAException(-1, "ConsultasGapDAO.consultaAnacrGap:[" + e.toString() + "]");
            }
        }
        finally {
            try {
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
            }
            catch (SQLException var13_14) {}
        }
    }

    public PromocionCaTO consultaPromocionCA(InfoPromocionGapTO infoPromocionGapTO) throws CAException {
        PromocionCaTO gapCaTO;
        StringBuffer stringBuffer = new StringBuffer();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        gapCaTO = null;
        stringBuffer.append(" SELECT idPromocionCA, versionPromocion, modelo, marca, idGrupoPlanNuevo, ");
        stringBuffer.append(" idGrupoPlanAnterior, plazoForzosoNuevo, plazoFzoAnterior, bonoDescuento, ");
        stringBuffer.append(" productoMobile, planDescuento, servicioAdicional, descuento,  ");
        stringBuffer.append(" modoSubsNuevo, modoSubsAnt ");
        stringBuffer.append(" FROM ").append(this.schemaGap).append("PROMOCIONCA ");
        stringBuffer.append(" WHERE idPromocion=? and versionPromocion=? ");
        try {
            try {
                connection = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcGap);
                preparedStatement = connection.prepareStatement(stringBuffer.toString());
                preparedStatement.setInt(1, Integer.parseInt(infoPromocionGapTO.getIdPromocion().trim()));
                preparedStatement.setInt(2, Integer.parseInt(infoPromocionGapTO.getVersionPromocion()));
                preparedStatement.setQueryTimeout(15);
                resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    gapCaTO = new PromocionCaTO();
                    gapCaTO.setIdPromocionCA(resultSet.getInt("idPromocionCA"));
                    gapCaTO.setIdPromocion(Integer.parseInt(infoPromocionGapTO.getIdPromocion().trim()));
                    gapCaTO.setVersionPromocion(resultSet.getInt("versionPromocion"));
                    gapCaTO.setModelo(resultSet.getString("modelo") != null ? resultSet.getString("modelo") : "");
                    gapCaTO.setMarca(resultSet.getString("marca") != null ? resultSet.getString("marca") : "");
                    gapCaTO.setIdGrupoPlanNuevo(resultSet.getInt("idGrupoPlanNuevo"));
                    gapCaTO.setIdGrupoPlanAnterior(resultSet.getInt("idGrupoPlanAnterior"));
                    gapCaTO.setPlazoFzoNuevo(resultSet.getInt("plazoForzosoNuevo"));
                    gapCaTO.setPlazoFzoAnterior(resultSet.getInt("plazoFzoAnterior"));
                    gapCaTO.setBonoDescuento(resultSet.getString("bonoDescuento") != null ? resultSet.getString("bonoDescuento") : "");
                    gapCaTO.setProductoM2K(resultSet.getString("productoMobile") != null ? resultSet.getString("productoMobile") : "");
                    gapCaTO.setCantidadDescuento(resultSet.getBigDecimal("descuento"));
                    gapCaTO.setModoSuscripcionNuevo(resultSet.getInt("modoSubsNuevo"));
                    gapCaTO.setModoSuscripcionAnterior(resultSet.getInt("modoSubsAnt"));
                }
            }
            catch (SQLException e) {
                throw new CAException(-1, "ConsultasGapDAO.consultaPromocionCA:[" + e.toString() + "]");
            }
            catch (Exception e) {
                throw new CAException(-1, "ConsultasGapDAO.consultaPromocionCA:[" + e.toString() + "]");
            }
        }
        finally {
            try {
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                    preparedStatement = null;
                }
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
            }
            catch (SQLException var9_11) {}
        }
        return gapCaTO;
    }

    public List<String> getPlanesByIdGrupoPlan(int idGrupoPlan, int idModoSubscripcion, int idRegion) throws CAException {
        ArrayList<String> planesList;
        planesList = null;
        StringBuffer stringBuffer = new StringBuffer();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        stringBuffer.append(" SELECT B.SPLANID ");
        stringBuffer.append(" FROM ").append(this.schemaGap).append("GRUPOPLAN A, ").append(this.schemaGap).append("PLANGRUPOPLAN B ");
        stringBuffer.append(" WHERE A.IDGRUPOPLAN=B.IDGRUPOPLAN and A.IDGRUPOPLAN=? ");
        stringBuffer.append(" AND A.IDMODOSUBSCRIPCION = B.IDMODOSUBSCRIPCION ");
        stringBuffer.append(" AND A.IDMODOSUBSCRIPCION=? ");
        stringBuffer.append(" and B.IDREGION=? ");
        try {
            try {
                connection = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcGap);
                preparedStatement = connection.prepareStatement(stringBuffer.toString());
                preparedStatement.setInt(1, idGrupoPlan);
                preparedStatement.setInt(2, idModoSubscripcion);
                preparedStatement.setInt(3, idRegion);
                preparedStatement.setQueryTimeout(15);
                resultSet = preparedStatement.executeQuery();
                planesList = new ArrayList<String>();
                while (resultSet.next()) {
                    planesList.add(resultSet.getString("SPLANID").trim());
                }
            }
            catch (SQLException e) {
                throw new CAException(-1, "ConsultasGapDAO.getPlanesByIdGrupoPlan:[" + e.toString() + "]");
            }
            catch (Exception e) {
                throw new CAException(-1, "ConsultasGapDAO.getPlanesByIdGrupoPlan:[" + e.toString() + "]");
            }
        }
        finally {
            try {
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                    preparedStatement = null;
                }
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
            }
            catch (SQLException var11_13) {}
        }
        return planesList;
    }

    public int validaPromocionOfrecida(int idPromocionGap, String telefono, int versionPromoGap) throws Exception {
        int status;
        status = 0;
        StringBuffer stringBuffer = new StringBuffer();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        stringBuffer.append(" SELECT IDESTADOOFRECIMIENTO FROM ").append(this.schemaGap).append("ESTADOPROMOCIONOFRECIDA");
        stringBuffer.append(" WHERE IDPROMOCION = ? AND LINEAID = ? AND VERSIONPROMOCION=? ");
        try {
            try {
                connection = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcGap);
                preparedStatement = connection.prepareStatement(stringBuffer.toString());
                preparedStatement.setInt(1, idPromocionGap);
                preparedStatement.setLong(2, new Long(telefono.trim()));
                preparedStatement.setInt(3, versionPromoGap);
                preparedStatement.setQueryTimeout(15);
                resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    status = resultSet.getInt("IDESTADOOFRECIMIENTO");
                }
            }
            catch (SQLException e) {
                throw new CAException(-1, "ConsultasGapDAO.validaPromocionOfrecida:[" + e.toString() + "]");
            }
            catch (Exception e) {
                throw new CAException(-1, "ConsultasGapDAO.validaPromocionOfrecida:[" + e.toString() + "]");
            }
        }
        finally {
            try {
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                    preparedStatement = null;
                }
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
            }
            catch (SQLException var11_13) {}
        }
        return status;
    }

    public MensajeTO insertaPromocionOfrecida(int idPromocionGap, int versionPromoGap, String telefono, long fechaTransaccion, String msg) throws Exception {
        MensajeTO mensajeTO;
        StringBuffer stringBuffer = new StringBuffer();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        mensajeTO = new MensajeTO();
        stringBuffer.append(" INSERT INTO ").append(this.schemaGap).append("ESTADOPROMOCIONOFRECIDA");
        stringBuffer.append(" (IDPROMOCION,VERSIONPROMOCION,LINEAID,IDESTADOOFRECIMIENTO,FECHAAPLICACION,ESTATUSSOCKET, MENSAJE)");
        stringBuffer.append(" VALUES (?,?,?,?,?,?,?)");
        try {
            try {
                connection = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcGap);
                connection.setAutoCommit(false);
                preparedStatement = connection.prepareStatement(stringBuffer.toString());
                preparedStatement.setInt(1, idPromocionGap);
                preparedStatement.setInt(2, versionPromoGap);
                preparedStatement.setLong(3, new Long(telefono.trim()));
                preparedStatement.setInt(4, 1);
                preparedStatement.setInt(5, Integer.parseInt(new Date(fechaTransaccion).toString().replaceAll("-", "")));
                preparedStatement.setInt(6, 5);
                preparedStatement.setString(7, msg);
                preparedStatement.setQueryTimeout(15);
                if (preparedStatement.executeUpdate() > 0) {
                    connection.commit();
                    mensajeTO.agregaMensaje(0, "Proceso Exitoso");
                } else {
                    connection.rollback();
                    mensajeTO.agregaMensaje(1, "No se actulizo el estado de la promocion en Gap");
                }
            }
            catch (SQLException e) {
                connection.rollback();
                throw new CAException(-1, "ConsultasGapDAO.insertaPromocionOfrecida:[" + e.toString() + "]");
            }
            catch (Exception e) {
                connection.rollback();
                throw new CAException(-1, "ConsultasGapDAO.insertaPromocionOfrecida:[" + e.toString() + "]");
            }
        }
        finally {
            try {
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                    preparedStatement = null;
                }
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
            }
            catch (SQLException var14_15) {}
        }
        return mensajeTO;
    }

    public MensajeTO actualizaPromocionOfrecida(int idPromocionGap, int versionPromoGap, String telefono, long fechaTransaccion, String msg) throws Exception {
        MensajeTO mensajeTO;
        StringBuffer stringBuffer = new StringBuffer();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        mensajeTO = new MensajeTO();
        stringBuffer.append(" UPDATE ").append(this.schemaGap).append("ESTADOPROMOCIONOFRECIDA");
        stringBuffer.append(" SET IDESTADOOFRECIMIENTO = ?,FECHAAPLICACION=?, MENSAJE=?");
        stringBuffer.append(" WHERE IDPROMOCION = ? AND LINEAID=? AND VERSIONPROMOCION=? ");
        try {
            try {
                connection = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcGap);
                preparedStatement = connection.prepareStatement(stringBuffer.toString());
                preparedStatement.setInt(1, 1);
                preparedStatement.setInt(2, Integer.parseInt(new Date(fechaTransaccion).toString().replaceAll("-", "")));
                preparedStatement.setString(3, msg);
                preparedStatement.setInt(4, idPromocionGap);
                preparedStatement.setLong(5, new Long(telefono.trim()));
                preparedStatement.setInt(6, versionPromoGap);
                preparedStatement.setQueryTimeout(15);
                if (preparedStatement.executeUpdate() > 0) {
                    connection.commit();
                    mensajeTO.agregaMensaje(0, "Proceso Exitoso");
                } else {
                    mensajeTO.agregaMensaje(1, "No se actulizo el estado de la promocion en Gap");
                }
            }
            catch (SQLException e) {
                connection.rollback();
                throw new CAException(-1, "ConsultasGapDAO.actualizaPromocionOfrecida:[" + e.toString() + "]");
            }
            catch (Exception e) {
                connection.rollback();
                throw new CAException(-1, "ConsultasGapDAO.actualizaPromocionOfrecida:[" + e.toString() + "]");
            }
        }
        finally {
            try {
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                    preparedStatement = null;
                }
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
            }
            catch (SQLException var14_15) {}
        }
        return mensajeTO;
    }
}

