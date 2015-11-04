/*
 * Decompiled with CFR 0_102.
 * 
 * Could not load the following classes:
 *  com.claro.transfer.promociones.PromocionTO
 *  com.claro.transfer.service.FileDataTO
 *  com.claro.util.ServiceLocator
 *  com.claro.util.Utils
 *  org.apache.log4j.Logger
 */
package com.claro.promociones.dao;

import com.claro.dao.ConsultaCuentasCorreoDAO;
import com.claro.transfer.promociones.PromocionTO;
import com.claro.transfer.service.FileDataTO;
import com.claro.util.ServiceLocator;
import com.claro.util.Utils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;

public class ActualizaPromocionesDAO
implements Runnable {
    protected final Logger error = Logger.getLogger((String)"loggerCirculoAzulError");
    protected final Logger logger = Logger.getLogger((String)"loggerCirculoAzul");
    private String archivo;
    private int idRegion;
    private String schema_database;
    private FileDataTO fileDataTO;

    public ActualizaPromocionesDAO(FileDataTO fileDataTO, int idRegion) {
        try {
            this.fileDataTO = fileDataTO;
            this.idRegion = idRegion;
            this.schema_database = ServiceLocator.getInstance().getVariable(ServiceLocator.schema_database);
        }
        catch (Exception e) {
            this.error.error((Object)"ActualizaPromocionesDAO", (Throwable)e);
        }
    }

    public boolean actualizaPromociones(String nombreArchivo, int idRegion) throws Exception {
        Connection connection = null;
        int movimientosBD = 0;
        InputStreamReader inputStreamReader = null;
        InputStreamReader fileReader = null;
        BufferedReader reader = null;
        PrintWriter reporteInsert = null;
        PrintWriter reporteError = null;
        File insert = null;
        File error = null;
        FileOutputStream insertOutput = null;
        FileOutputStream errorOutput = null;
        try {
            inputStreamReader = new InputStreamReader(this.fileDataTO.getData());
            reader = new BufferedReader(inputStreamReader);
            ConsultaCuentasCorreoDAO consultaCuentasCorreoDAO = new ConsultaCuentasCorreoDAO();
            Map<String, String> parametrosCorreo = consultaCuentasCorreoDAO.obtieneCuentasCorreoCargaCatalogos(this.idRegion);
            if (reader == null) {
                Utils.enviaCorreo(parametrosCorreo, (String)"Error al procesar promociones.", (String)"No se pudo procesar el archivo, verifique que haya sido previamente cargado.", (List)null);
                return false;
            }
            try {
                String linea = "";
                String logInserts = "promoActualizadasR" + idRegion + ".txt";
                String logErrores = "promoActualizadasErroresR" + idRegion + ".txt";
                insert = new File(logInserts);
                error = new File(logErrores);
                insertOutput = new FileOutputStream(insert);
                errorOutput = new FileOutputStream(error);
                reporteInsert = new PrintWriter(insertOutput);
                reporteError = new PrintWriter(errorOutput);
                connection = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                connection.setAutoCommit(false);
                while (linea != null) {
                    PromocionTO promocionTO;
                    block57 : {
                        linea = reader.readLine();
                        if (linea == null || linea.trim().equals("")) continue;
                        promocionTO = null;
                        try {
                            promocionTO = new PromocionTO(linea.split(","));
                        }
                        catch (Exception e) {
                            reporteError.println(String.valueOf(++movimientosBD) + ": Error al obtener linea del archivo [" + linea + "]" + e.getMessage());
                            if (movimientosBD <= 0) break block57;
                            connection.commit();
                        }
                    }
                    if (promocionTO == null) continue;
                    try {
                        PromocionTO promoActualizada = this.actualizar(promocionTO, connection);
                        if (promoActualizada.getIdMensaje() == 0) {
                            connection.commit();
                            reporteInsert.println(String.valueOf(++movimientosBD) + ": Se actualizo[" + promoActualizada.toString() + "]");
                            continue;
                        }
                        reporteError.println(String.valueOf(++movimientosBD) + ": No se actualizo[" + promoActualizada.toString() + "]idError:" + promoActualizada.getIdMensaje() + ",Mensaje:" + promoActualizada.getMensaje());
                        continue;
                    }
                    catch (Exception e) {
                        reporteError.println(String.valueOf(++movimientosBD) + ": No se actualizo[" + promocionTO.toString() + "]idError:" + promocionTO.getIdMensaje() + ",Mensaje:" + promocionTO.getMensaje());
                    }
                }
                if (movimientosBD > 0) {
                    connection.commit();
                }
                reporteInsert.flush();
                reporteError.flush();
                ArrayList<String> archivosLog = new ArrayList<String>();
                archivosLog.add(logInserts);
                archivosLog.add(logErrores);
                Utils.enviaCorreo(parametrosCorreo, (String)"Actualizacion de promociones.", (String)"Las promociones fueron procesadas.", archivosLog);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        finally {
            if (connection != null) {
                try {
                    if (movimientosBD > 0) {
                        connection.commit();
                    }
                    connection.setAutoCommit(true);
                    connection.close();
                    connection = null;
                }
                catch (Exception var22_17) {}
            }
            if (reader != null) {
                try {
                    reader.close();
                }
                catch (IOException var22_18) {}
            }
            if (inputStreamReader != null) {
                try {
                    inputStreamReader.close();
                }
                catch (IOException var22_19) {}
            }
            if (fileReader != null) {
                try {
                    fileReader.close();
                }
                catch (IOException var22_20) {}
            }
            if (reporteInsert != null) {
                reporteInsert.close();
            }
            if (reporteError != null) {
                reporteError.close();
            }
            if (insertOutput != null) {
                insertOutput.close();
            }
            if (errorOutput != null) {
                errorOutput.close();
            }
            if (insert != null) {
                insert.delete();
            }
            if (error != null) {
                error.delete();
            }
        }
        return true;
    }

    private PromocionTO actualizar(PromocionTO promocionTO, Connection conexion) {
        block24 : {
            StringBuffer sbQuery = new StringBuffer();
            sbQuery.append("UPDATE ").append(this.schema_database).append("PTO_CTLPROMOCIONES SET IDPRODUCTO = IDPRODUCTO");
            sbQuery.append(promocionTO.getDescripcion() != null && promocionTO.getDescripcion().length() > 0 ? " ,DESCRIPCION=?" : "");
            sbQuery.append(promocionTO.getTipoProducto() != null && promocionTO.getTipoProducto().length() > 0 ? " ,TIPOPRODUCTO=?" : "");
            sbQuery.append(promocionTO.getPrecioLista() != null && promocionTO.getPrecioLista().length() > 0 ? " ,PRECIOLISTA=?" : "");
            sbQuery.append(promocionTO.getPrecioActiva() != null && promocionTO.getPrecioActiva().length() > 0 ? " ,PRECIOACTIVACION=?" : "");
            sbQuery.append(promocionTO.getMarca() != null && promocionTO.getMarca().length() > 0 ? " ,MARCA=?" : "");
            sbQuery.append(promocionTO.getModelo() != null && promocionTO.getModelo().length() > 0 ? " ,MODELO=?" : "");
            sbQuery.append(promocionTO.getURL() != null && promocionTO.getURL().length() > 0 ? " ,URL=?" : "");
            sbQuery.append(promocionTO.getTecnologia() != null && promocionTO.getTecnologia().length() > 0 ? " ,TECNOLOGIA=?" : "");
            sbQuery.append(promocionTO.getEstatus() != null && promocionTO.getEstatus().length() > 0 ? " ,ESTATUS=?" : "");
            sbQuery.append(promocionTO.getBanSISACT() != null && promocionTO.getBanSISACT().length() > 0 ? " ,BANSISACT=?" : "");
            sbQuery.append(promocionTO.getValorPtos() != null && promocionTO.getValorPtos().length() > 0 && !promocionTO.getValorPtos().equals("-1") ? " ,VALORPUNTOS=?" : "");
            sbQuery.append(" WHERE ");
            sbQuery.append(" IDPRODUCTO=?");
            sbQuery.append(" AND IDREGION=?");
            sbQuery.append(" AND IDGRUPOPROMOCION=?");
            sbQuery.append(" AND FZAVENTAS=? ");
            sbQuery.append(" AND INDICADOR=? ");
            sbQuery.append(" AND ADENDUM=? ");
            PreparedStatement statement = null;
            try {
                try {
                    int contador = 1;
                    statement = conexion.prepareStatement(sbQuery.toString());
                    if (promocionTO.getDescripcion() != null && promocionTO.getDescripcion().length() > 0) {
                        statement.setString(contador++, promocionTO.getDescripcion());
                    }
                    if (promocionTO.getTipoProducto() != null && promocionTO.getTipoProducto().length() > 0) {
                        statement.setString(contador++, promocionTO.getTipoProducto());
                    }
                    if (promocionTO.getPrecioLista() != null && promocionTO.getPrecioLista().length() > 0) {
                        statement.setFloat(contador++, Float.parseFloat(promocionTO.getPrecioLista()));
                    }
                    if (promocionTO.getPrecioActiva() != null && promocionTO.getPrecioActiva().length() > 0) {
                        statement.setFloat(contador++, Float.parseFloat(promocionTO.getPrecioActiva()));
                    }
                    if (promocionTO.getMarca() != null && promocionTO.getMarca().length() > 0) {
                        statement.setString(contador++, promocionTO.getMarca());
                    }
                    if (promocionTO.getModelo() != null && promocionTO.getModelo().length() > 0) {
                        statement.setString(contador++, promocionTO.getModelo());
                    }
                    if (promocionTO.getURL() != null && promocionTO.getURL().length() > 0) {
                        statement.setString(contador++, promocionTO.getURL());
                    }
                    if (promocionTO.getTecnologia() != null && promocionTO.getTecnologia().length() > 0) {
                        statement.setString(contador++, promocionTO.getTecnologia());
                    }
                    if (promocionTO.getEstatus() != null && promocionTO.getEstatus().length() > 0) {
                        statement.setString(contador++, promocionTO.getEstatus());
                    }
                    if (promocionTO.getBanSISACT() != null && promocionTO.getBanSISACT().length() > 0) {
                        statement.setString(contador++, promocionTO.getBanSISACT());
                    }
                    if (!(promocionTO.getValorPtos() == null || promocionTO.getValorPtos().length() <= 0 || promocionTO.getValorPtos().equals("-1"))) {
                        statement.setInt(contador++, Integer.parseInt(promocionTO.getValorPtos()));
                    }
                    statement.setString(contador++, promocionTO.getIdProducto());
                    statement.setInt(contador++, Integer.parseInt(promocionTO.getIdRegion()));
                    statement.setInt(contador++, Integer.parseInt(promocionTO.getIdGrupoPromocion()));
                    statement.setString(contador++, promocionTO.getFzaVta());
                    statement.setInt(contador++, Integer.parseInt(promocionTO.getIndicador()));
                    statement.setInt(contador++, Integer.parseInt(promocionTO.getAddendum()));
                    if (statement.executeUpdate() == 1) {
                        promocionTO.setIdMensaje(0);
                        promocionTO.setMensaje("proceso exitoso.");
                        break block24;
                    }
                    promocionTO.setIdMensaje(-1);
                    promocionTO.setMensaje("Error al actualizar la promocion[No Existe la promocion]");
                }
                catch (SQLException sqlE) {
                    promocionTO.setIdMensaje(-1);
                    promocionTO.setMensaje("Error al actualizar la promocion[" + sqlE.toString() + "]");
                    if (statement == null) break block24;
                    try {
                        statement.close();
                        statement = null;
                    }
                    catch (Exception var7_7) {}
                }
            }
            finally {
                if (statement != null) {
                    try {
                        statement.close();
                        statement = null;
                    }
                    catch (Exception var7_9) {}
                }
            }
        }
        return promocionTO;
    }

    @Override
    public void run() {
        try {
            this.actualizaPromociones(this.archivo, this.idRegion);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

