/*
 * Decompiled with CFR 0_102.
 * 
 * Could not load the following classes:
 *  com.claro.exception.CAException
 *  com.claro.transfer.BeneficioTO
 *  com.claro.transfer.MensajeTO
 *  com.claro.transfer.TelefonoTO
 *  com.claro.util.ServiceLocator
 *  org.apache.log4j.Logger
 */
package com.claro.dao;

import com.claro.exception.CAException;
import com.claro.transfer.BeneficioTO;
import com.claro.transfer.MensajeTO;
import com.claro.transfer.TelefonoTO;
import com.claro.util.ServiceLocator;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import org.apache.log4j.Logger;

public class BeneficioDAO {
    protected final Logger logger = Logger.getLogger((String)"loggerCirculoAzul");
    protected final Logger error = Logger.getLogger((String)"loggerCirculoAzulError");
    private String schema_database;

    public BeneficioDAO() {
        try {
            this.schema_database = ServiceLocator.getInstance().getVariable(ServiceLocator.schema_database);
        }
        catch (Exception e) {
            this.error.error((Object)"RedencionDAO", (Throwable)e);
        }
    }

    public MensajeTO guardaBeneficioSeleccionado(Connection connection, TelefonoTO telefonoTO, BeneficioTO beneficioTO, String idUsuario) throws CAException {
    	PreparedStatement statement = null;
        MensajeTO mensajeTO = new MensajeTO();
        try {
            if (connection.isClosed()) {
                connection = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
            }
            String query = "INSERT INTO " + this.schema_database + "PTO_TBLHISTOPROM (CUENTA, SECUENCIA, TELEFONO, " + " FOLIO, FECHAMOVIMIENTO, USUARIO, IDBENEFICIO, IDGPOBENEF, IDMOTIVO, IDREGION) " + " VALUES(?,?,?,?,?,?,?,?,?,?)";
            statement = connection.prepareStatement(query);
            Timestamp fechaMovimiento = new Timestamp(System.currentTimeMillis());
            statement.setString(1, telefonoTO.getCuenta());
            statement.setInt(2, Integer.parseInt(telefonoTO.getSecuencia()));
            statement.setString(3, telefonoTO.getTelefono());
            statement.setString(4, beneficioTO.getFolio());
            statement.setTimestamp(5, fechaMovimiento);
            statement.setString(6, idUsuario);
            if (beneficioTO.getIdGpoBeneficio() != null && Integer.valueOf(beneficioTO.getIdGpoBeneficio().trim()) != 0) {
                statement.setString(7, "");
                statement.setInt(8, Integer.parseInt(beneficioTO.getIdGpoBeneficio()));
                statement.setInt(9, Integer.parseInt(beneficioTO.getIdMotivo()));
                statement.setInt(10, telefonoTO.getRegion());
            } else {
                statement.setString(7, beneficioTO.getIdBeneficio());
                statement.setInt(8, 0);
                statement.setInt(9, Integer.parseInt(beneficioTO.getIdMotivo()));
                statement.setInt(10, telefonoTO.getRegion());
            }
            if (statement.executeUpdate() > 0) {
                mensajeTO.agregaMensaje(0, "Proceso Exitoso");
            } else {
                mensajeTO.agregaMensaje(-1, "No se inserto el beneficio");
            }
            MensajeTO mensajeTO2 = mensajeTO;
            return mensajeTO2;
        }
        catch (Exception e) {
            throw new CAException(-1, "CatalogoDAO.guardaBeneficioSeleccionado[" + e.toString() + "]");
        }
        finally {
            if (statement != null) {
                try {
                    statement.close();
                    statement = null;
                }
                catch (Exception var11_12) {}
            }
        }
    }
}

