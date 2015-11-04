/*
 * Decompiled with CFR 0_102.
 * 
 * Could not load the following classes:
 *  com.claro.util.ServiceLocator
 *  org.apache.log4j.Logger
 */
package com.claro.promociones.dao;

import com.claro.util.ServiceLocator;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import org.apache.log4j.Logger;

public class HorarioCargaPromocionesDAO {
    protected final Logger error = Logger.getLogger((String)"loggerCirculoAzulError");
    protected final Logger logger = Logger.getLogger((String)"loggerCirculoAzul");
    private String schema_database;

    public HorarioCargaPromocionesDAO() {
        try {
            this.schema_database = ServiceLocator.getInstance().getVariable(ServiceLocator.schema_database);
        }
        catch (Exception e) {
            this.error.error((Object)"HorarioCargaPromocionesDAO", (Throwable)e);
        }
    }

    public String consultaHorarioProductivoInicio() throws Exception {
        String horaIni;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        horaIni = null;
        try {
            try {
                connection = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("SELECT VALOR FROM ").append(this.schema_database).append("PTO_CTLPROPIEDADES ");
                stringBuffer.append("where IDVARIABLE = 'HORA.PRODUC.INICIO.CARGA.PROMO'");
                preparedStatement = connection.prepareStatement(stringBuffer.toString());
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    horaIni = resultSet.getString(1);
                }
            }
            catch (Exception e) {
                throw new Exception("Error al obtener la hora de inicio productiva para la carga de promociones: " + e.getMessage());
            }
        }
        finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                    resultSet = null;
                }
                catch (Exception var7_11) {}
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                    preparedStatement = null;
                }
                catch (Exception var7_12) {}
            }
            if (connection != null) {
                try {
                    connection.close();
                    connection = null;
                }
                catch (Exception var7_13) {}
            }
        }
        return horaIni;
    }

    public String consultaHorarioProductivoFin() throws Exception {
        String horaIni;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        horaIni = null;
        try {
            try {
                connection = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("SELECT VALOR FROM ").append(this.schema_database).append("PTO_CTLPROPIEDADES ");
                stringBuffer.append("where IDVARIABLE = 'HORA.PRODUC.FIN.CARGA.PROMO'");
                preparedStatement = connection.prepareStatement(stringBuffer.toString());
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    horaIni = resultSet.getString(1);
                }
            }
            catch (Exception e) {
                throw new Exception("Error al obtener la hora de fin productiva para la carga de promociones: " + e.getMessage());
            }
        }
        finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                    resultSet = null;
                }
                catch (Exception var7_11) {}
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                    preparedStatement = null;
                }
                catch (Exception var7_12) {}
            }
            if (connection != null) {
                try {
                    connection.close();
                    connection = null;
                }
                catch (Exception var7_13) {}
            }
        }
        return horaIni;
    }

    public String consultaLimiteRegistrosHorarioProductivo() throws Exception {
        String horaIni;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        horaIni = null;
        try {
            try {
                connection = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("SELECT VALOR FROM ").append(this.schema_database).append("PTO_CTLPROPIEDADES ");
                stringBuffer.append("where IDVARIABLE = 'LIMITE.REG.CARGA.PROMO.HORA.PRODUC'");
                preparedStatement = connection.prepareStatement(stringBuffer.toString());
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    horaIni = resultSet.getString(1);
                }
            }
            catch (Exception e) {
                throw new Exception("Error al obtener el limite de registros permitido en horario productivo para la carga de promociones: " + e.getMessage());
            }
        }
        finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                    resultSet = null;
                }
                catch (Exception var7_11) {}
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                    preparedStatement = null;
                }
                catch (Exception var7_12) {}
            }
            if (connection != null) {
                try {
                    connection.close();
                    connection = null;
                }
                catch (Exception var7_13) {}
            }
        }
        return horaIni;
    }
}

