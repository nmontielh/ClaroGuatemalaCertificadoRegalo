/*
 * Decompiled with CFR 0_102.
 * 
 * Could not load the following classes:
 *  com.claro.exception.CAException
 *  com.claro.transfer.MensajeTO
 *  com.claro.transfer.ParametrosTO
 *  com.claro.util.Constantes
 *  com.claro.util.ServiceLocator
 *  org.apache.log4j.Logger
 */
package com.claro.dao;

import com.claro.dao.PuntosDAO;
import com.claro.exception.CAException;
import com.claro.transfer.MensajeTO;
import com.claro.transfer.ParametrosTO;
import com.claro.util.Constantes;
import com.claro.util.ServiceLocator;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.log4j.Logger;

public class RenunciaDAO {
    protected final Logger logger = Logger.getLogger((String)"loggerCirculoAzul");
    protected final Logger error = Logger.getLogger((String)"loggerCirculoAzulError");

    public MensajeTO reactivaPuntos(ParametrosTO parametrosTO) throws CAException {
        MensajeTO mensajeTO;
        mensajeTO = new MensajeTO();
        Connection connection = null;
        PuntosDAO puntosDAO = new PuntosDAO();
        try {
            try {
                long fechaTransaccion = System.currentTimeMillis();
                mensajeTO = puntosDAO.actualizaLinea(null, parametrosTO.getCuenta(), parametrosTO.getSecuencia(), "0");
                connection = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                if (mensajeTO.getIdMensaje() == 0) {
                    mensajeTO = puntosDAO.insertaDetalle(connection, fechaTransaccion, "Reactivacion de Puntos (" + Constantes.TIMEFORMAT.format(new Date(fechaTransaccion)) + ")", 48, 0, null, parametrosTO.getCuenta(), parametrosTO.getSecuencia(), parametrosTO.getTelefono(), parametrosTO.getUsuariMovimiento());
                }
            }
            catch (SQLException e) {
                this.error.info((Object)"SQLException.actualizaDatosM2K:", (Throwable)e);
                throw new CAException(-1, "[consultaDatos] SQLError: " + e.toString() + "Actualizar Inf", (Exception)e);
            }
            catch (Exception e) {
                this.error.info((Object)"Exception.actualizaDatosM2K:", (Throwable)e);
                throw new CAException(-1, "[consultaDatos] Error: " + e.toString() + "Actualizar Inf", e);
            }
        }
        finally {
            if (puntosDAO != null) {
                puntosDAO = null;
            }
            if (connection != null) {
                try {
                    connection.close();
                    connection = null;
                }
                catch (Exception var8_10) {}
            }
        }
        return mensajeTO;
    }

    public MensajeTO renunciaPuntos(ParametrosTO parametrosTO) throws CAException {
        MensajeTO mensajeTO;
        mensajeTO = new MensajeTO();
        Connection connection = null;
        PuntosDAO puntosDAO = new PuntosDAO();
        try {
            try {
                long fechaTransaccion = System.currentTimeMillis();
                mensajeTO = puntosDAO.actualizaLinea(null, parametrosTO.getCuenta(), parametrosTO.getSecuencia(), "C");
                connection = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                if (mensajeTO.getIdMensaje() == 0) {
                    mensajeTO = puntosDAO.insertaDetalle(connection, fechaTransaccion, "Renuncia de Puntos (" + Constantes.TIMEFORMAT.format(new Date(fechaTransaccion)) + ")", 49, 0, null, parametrosTO.getCuenta(), parametrosTO.getSecuencia(), parametrosTO.getTelefono(), parametrosTO.getUsuariMovimiento());
                }
            }
            catch (SQLException e) {
                this.error.info((Object)"SQLException.actualizaDatosM2K:", (Throwable)e);
                throw new CAException(-1, "[consultaDatos] SQLError: " + e.toString() + "Actualizar Inf", (Exception)e);
            }
            catch (Exception e) {
                this.error.info((Object)"Exception.actualizaDatosM2K:", (Throwable)e);
                throw new CAException(-1, "[consultaDatos] Error: " + e.toString() + "Actualizar Inf", e);
            }
        }
        finally {
            if (puntosDAO != null) {
                puntosDAO = null;
            }
            if (connection != null) {
                try {
                    connection.close();
                    connection = null;
                }
                catch (Exception var8_10) {}
            }
        }
        return mensajeTO;
    }
}

