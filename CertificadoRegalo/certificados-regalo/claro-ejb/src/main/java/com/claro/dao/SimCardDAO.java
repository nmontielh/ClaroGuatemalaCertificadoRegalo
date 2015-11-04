/*
 * Decompiled with CFR 0_102.
 * 
 * Could not load the following classes:
 *  com.claro.exception.CAException
 *  com.claro.transfer.TelefonoTO
 *  com.claro.util.ServiceLocator
 *  org.apache.log4j.Logger
 */
package com.claro.dao;

import com.claro.dao.ConsultasDAO;
import com.claro.dao.PuntosDAO;
import com.claro.exception.CAException;
import com.claro.transfer.TelefonoTO;
import com.claro.util.ServiceLocator;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.log4j.Logger;

public class SimCardDAO {
    protected final Logger logger2 = Logger.getLogger((String)"loggerCirculoAzul");
    protected final Logger error = Logger.getLogger((String)"loggerCirculoAzulError");
    private String schema_database;
    private PuntosDAO puntosDAO = new PuntosDAO();
    private ConsultasDAO consultasDAO = new ConsultasDAO();

    public SimCardDAO() {
        try {
            this.schema_database = ServiceLocator.getInstance().getVariable(ServiceLocator.schema_database);
        }
        catch (Exception e) {
            this.error.error((Object)"SimCardDAO", (Throwable)e);
        }
    }

    public TelefonoTO consultaDatosSIM(String telefono) throws CAException {
        Connection connection = null;
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        StringBuffer query = new StringBuffer();
        query.append(" SELECT A.CUENTA, A.SECUENCIA, A.IDREGION, A.ADDENDUM, B.BBONO, A.FECHAADD");
        query.append(" FROM ").append(this.schema_database).append("PTO_TBLLINEAS A,");
        query.append(this.schema_database).append("PTO_TBLTOTALES B");
        query.append(" WHERE A.CUENTA = B.CUENTA AND A.SECUENCIA = B.SECUENCIA AND A.LINEA = ?");
        query.append(" AND STATUSTEL <> 'AN'");
        query.append(" GROUP BY A.CUENTA, A.SECUENCIA, A.IDREGION, A.ADDENDUM, B.BBONO, A.FECHAADD");
        query.append(" ORDER BY A.FECHAADD DESC");
        try {
            connection = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
            preparedStatement = connection.prepareStatement(query.toString());
            preparedStatement.setString(1, telefono);
            resultSet = preparedStatement.executeQuery();
            TelefonoTO telefonoTO = new TelefonoTO();
            if (resultSet.next()) {
                telefonoTO.setCuenta(resultSet.getString("CUENTA"));
                telefonoTO.setTelefono(telefono);
                telefonoTO.setSecuencia(resultSet.getString("SECUENCIA"));
                telefonoTO.setRegion(resultSet.getInt("IDREGION"));
            } else {
                telefonoTO.agregaMensaje(-1, "INVALIDO");
            }
            TelefonoTO telefonoTO2 = telefonoTO;
            return telefonoTO2;
        }
        catch (SQLException e) {
            throw new CAException(-1, "SQLException.consultaDatosSIM[" + e.toString() + "]", (Exception)e);
        }
        catch (Exception e) {
            throw new CAException(-1, "ConsultasDAO.consultaDatosSIM[" + e.toString() + "]", e);
        }
        finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
            }
            catch (Exception var9_13) {}
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                    preparedStatement = null;
                }
            }
            catch (Exception var9_14) {}
            try {
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            }
            catch (Exception var9_15) {}
        }
    }

    public String getPromoSIM(String estatus, String segmento, String tipoProd, int region) throws CAException {
        Connection connection = null;
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        TelefonoTO telefonoTO = new TelefonoTO();
        StringBuffer query = new StringBuffer();
        query.append(" SELECT A.DESCRIP FROM ").append(this.schema_database).append("PTO_CTLPRODUCTOS A");
        query.append(" WHERE A.IDREGION= ? AND A.STATUS= ? AND A.IDSEGMENTO = ? AND A.TIPOREG=?");
        query.append(" ORDER BY A.DESCRIP");
        try {
            telefonoTO.setPromocionSim("");
            connection = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
            preparedStatement = connection.prepareStatement(query.toString());
            preparedStatement.setInt(1, region);
            preparedStatement.setString(2, estatus);
            preparedStatement.setString(3, segmento);
            preparedStatement.setString(4, tipoProd);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                telefonoTO.setPromocionSim(resultSet.getString("DESCRIP"));
            }
            if ("".equals(telefonoTO.getPromocionSim())) {
                telefonoTO.setPromocionSim("NO HAY PROMOCIONES");
            }
            String string = telefonoTO.getPromocionSim();
            return string;
        }
        catch (SQLException e) {
            throw new CAException(-1, "SQLException.getPromoSIM[" + e.toString() + "]", (Exception)e);
        }
        catch (Exception e) {
            throw new CAException(-1, "ConsultasDAO.getPromoSIM[" + e.toString() + "]", e);
        }
        finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                    resultSet = null;
                }
            }
            catch (Exception var13_14) {}
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                    preparedStatement = null;
                }
            }
            catch (Exception var13_15) {}
            try {
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            }
            catch (Exception var13_16) {}
        }
    }
}

