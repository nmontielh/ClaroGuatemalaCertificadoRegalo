/*
 * Decompiled with CFR 0_102.
 * 
 * Could not load the following classes:
 *  com.claro.exception.CAException
 *  com.claro.transfer.MensajeTO
 *  com.claro.util.Constantes
 *  com.claro.util.ServiceLocator
 *  com.claro.util.Utils
 *  org.apache.log4j.Logger
 */
package com.claro.dao;

import com.claro.exception.CAException;
import com.claro.transfer.MensajeTO;
import com.claro.util.Constantes;
import com.claro.util.ServiceLocator;
import com.claro.util.Utils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.log4j.Logger;

public class AdministracionDAO {
    protected final Logger logger = Logger.getLogger((String)"loggerCirculoAzul");
    protected final Logger error = Logger.getLogger((String)"loggerCirculoAzulError");
    private String schema_database;

    public AdministracionDAO() {
        try {
            this.schema_database = ServiceLocator.getInstance().getVariable(ServiceLocator.schema_database);
        }
        catch (Exception e) {
            this.error.error((Object)"AdministracionDAO", (Throwable)e);
        }
    }

    public MensajeTO actualizarPassword(String _usuario, String _passActual, String _passNuevo, String _passConfirm) throws CAException {
        MensajeTO oMensaje;
        Connection cnx = null;
        PreparedStatement ps = null;
        oMensaje = null;
        String qryUpdatePass = "UPDATE " + this.schema_database + "PTO_CTLUSUARIOS " + "\tSET PASSWDENC = ? , BANCAMBIAPASS='A', FECHAUPDATE=SYSDATE" + " WHERE idusuario = ?" + "   AND STATUS='A'";
        int rows = 0;
        try {
            try {
                long inicioProceso = System.currentTimeMillis();
                this.logger.info((Object)("actualizarPassword|Inicio Proceso|" + Constantes.DATEFORMTALog.format(new Date()) + "|" + inicioProceso));
                if (_passNuevo == null || _passNuevo.length() <= 0) {
                    throw new CAException(-1, "Debe indicar el password NUEVO.");
                }
                if (_passConfirm == null || _passConfirm.length() <= 0) {
                    throw new CAException(-1, "Debe indicar la CONFIRMACION del password.");
                }
                if (!_passNuevo.equals(_passConfirm)) {
                    throw new CAException(-1, "El NUEVO password y la CONFIRMACION deben ser iguales.");
                }
                this.logger.info((Object)("actualizarPassword|Antes de Conexion|" + Constantes.DATEFORMTALog.format(new Date()) + "|" + inicioProceso));
                cnx = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                this.logger.info((Object)("actualizarPassword|Despues de Conexion|" + Constantes.DATEFORMTALog.format(new Date()) + "|" + (System.currentTimeMillis() - inicioProceso)));
                ps = cnx.prepareStatement(qryUpdatePass);
                ps.setString(1, Utils.getMD5Encrypted((String)_passNuevo.trim()));
                ps.setString(2, _usuario.toUpperCase());
                rows = ps.executeUpdate();
                oMensaje = rows > 0 ? new MensajeTO(0, "El cambio de password fue completado exitosamente.") : new MensajeTO(-1, "No se actualizo ningun registro, <br>verifique que su password ACTUAL sea el correcto.");
                this.logger.info((Object)("actualizarPassword|FinProceso|" + Constantes.DATEFORMTALog.format(new Date()) + "|" + (System.currentTimeMillis() - inicioProceso)));
            }
            catch (SQLException e) {
                this.error.info((Object)"SQLException.actualizarPassword:", (Throwable)e);
                oMensaje = new MensajeTO(-1, "SQLException.actualizarPassword[" + e.toString() + "]");
                throw new CAException(-1, "SQLException.actualizarPassword[" + e.toString() + "]", (Exception)e);
            }
            catch (Exception e) {
                this.error.info((Object)"Exception.actualizarPassword:", (Throwable)e);
                oMensaje = new MensajeTO(-1, "Exception.actualizarPassword[" + e.toString() + "]");
                throw new CAException(-1, "AdministracionDAO.actualizarPassword[" + e.toString() + "]", e);
            }
        }
        finally {
            if (ps != null) {
                try {
                    ps.close();
                }
                catch (Exception var13_16) {}
            }
            if (cnx != null) {
                try {
                    cnx.close();
                    cnx = null;
                }
                catch (Exception var13_17) {}
            }
        }
        return oMensaje;
    }
}

