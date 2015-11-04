/*
 * Decompiled with CFR 0_102.
 * 
 * Could not load the following classes:
 *  com.claro.transfer.promociones.ProductosSmsTO
 *  com.claro.transfer.service.FileDataTO
 *  com.claro.util.ServiceLocator
 *  com.claro.util.Utils
 *  org.apache.log4j.Logger
 */
package com.claro.promociones.dao;

import com.claro.dao.ConsultaCuentasCorreoDAO;
import com.claro.transfer.promociones.ProductosSmsTO;
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

public class EliminaPromocionesSmsDAO
implements Runnable {
    protected final Logger error = Logger.getLogger((String)"loggerCirculoAzulError");
    protected final Logger logger = Logger.getLogger((String)"loggerCirculoAzul");
    private String archivo;
    private String schema_database;
    private FileDataTO fileDataTO;

    public EliminaPromocionesSmsDAO() {
        try {
            this.schema_database = ServiceLocator.getInstance().getVariable(ServiceLocator.schema_database);
        }
        catch (Exception e) {
            this.error.error((Object)"EliminaPromocionesSmsDAO", (Throwable)e);
        }
    }

    public EliminaPromocionesSmsDAO(FileDataTO fileDataTO) {
        try {
            this.fileDataTO = fileDataTO;
            this.schema_database = ServiceLocator.getInstance().getVariable(ServiceLocator.schema_database);
        }
        catch (Exception e) {
            this.error.error((Object)"EliminaPromocionesDAO", (Throwable)e);
        }
    }

    public boolean eliminaPromocionesSms() throws Exception {
        Connection connection = null;
        int movimientosBD = 0;
        InputStreamReader inputStreamReader = null;
        InputStreamReader fileReader = null;
        BufferedReader reader = null;
        PrintWriter reporteDelete = null;
        PrintWriter reporteError = null;
        ArrayList<String> archivosLog = new ArrayList<String>();
        File delete = null;
        File error = null;
        FileOutputStream deleteOutput = null;
        FileOutputStream errorOutput = null;
        try {
            inputStreamReader = new InputStreamReader(this.fileDataTO.getData());
            reader = new BufferedReader(inputStreamReader);
            ConsultaCuentasCorreoDAO consultaCuentasCorreoDAO = new ConsultaCuentasCorreoDAO();
            Map<String, String> parametrosCorreo = consultaCuentasCorreoDAO.obtieneCuentasCorreoCargaCatalogos(0);
            if (reader == null) {
                Utils.enviaCorreo(parametrosCorreo, (String)"Error al eliminar promociones sms.", (String)"No se pudo procesar el archivo, verifique que haya sido previamente cargado.", (List)null);
                return false;
            }
            try {
                String linea = "";
                String logEliminados = "promoEliminadasSms.txt";
                String logErroresEliminados = "promoEliminadasErroresSms.txt";
                delete = new File(logEliminados);
                error = new File(logErroresEliminados);
                deleteOutput = new FileOutputStream(delete);
                errorOutput = new FileOutputStream(error);
                reporteDelete = new PrintWriter(deleteOutput);
                reporteError = new PrintWriter(errorOutput);
                connection = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                connection.setAutoCommit(false);
                while (linea != null) {
                    linea = reader.readLine();
                    if (linea == null || linea.trim().equals("")) continue;
                    ProductosSmsTO productosSmsTO = null;
                    try {
                        productosSmsTO = new ProductosSmsTO(linea.split(","));
                    }
                    catch (Exception e) {
                        reporteError.println(String.valueOf(++movimientosBD) + ": Error al obtener linea del archivo [" + linea + "]" + e.getMessage());
                    }
                    if (productosSmsTO == null) continue;
                    try {
                        ProductosSmsTO promoEliminada = this.eliminar(productosSmsTO, connection);
                        if (promoEliminada.getIdError() == 0) {
                            connection.commit();
                            reporteDelete.println(String.valueOf(++movimientosBD) + ": Se elimino[" + promoEliminada.toString() + "]");
                        }
                        if (promoEliminada.getIdError() == -1) {
                            connection.commit();
                            reporteDelete.println(String.valueOf(++movimientosBD) + ": Se elimino[" + promoEliminada.toString() + "]");
                        }
                        if (promoEliminada.getIdError() != -2) continue;
                        reporteError.println(String.valueOf(++movimientosBD) + ": No se elimino[" + productosSmsTO.toString() + "]idError:" + productosSmsTO.getIdError() + ",Mensaje:" + productosSmsTO.getMensaje());
                        continue;
                    }
                    catch (Exception e) {
                        reporteError.println(String.valueOf(++movimientosBD) + ": No se elimino[" + productosSmsTO.toString() + "]idError:" + productosSmsTO.getIdError() + ",Mensaje:" + productosSmsTO.getMensaje());
                    }
                }
                if (movimientosBD > 0) {
                    connection.commit();
                }
                reporteDelete.flush();
                reporteError.flush();
                archivosLog.add(logEliminados);
                archivosLog.add(logErroresEliminados);
                Utils.enviaCorreo(parametrosCorreo, (String)"Eliminar promociones sms", (String)"Las promociones sms fueron procesadas", archivosLog);
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
                catch (Exception var21_16) {}
            }
            if (reader != null) {
                try {
                    reader.close();
                }
                catch (IOException var21_17) {}
            }
            if (inputStreamReader != null) {
                try {
                    inputStreamReader.close();
                }
                catch (IOException var21_18) {}
            }
            if (fileReader != null) {
                try {
                    fileReader.close();
                }
                catch (IOException var21_19) {}
            }
            if (reporteDelete != null) {
                reporteDelete.close();
            }
            if (reporteError != null) {
                reporteError.close();
            }
            if (deleteOutput != null) {
                deleteOutput.close();
            }
            if (errorOutput != null) {
                errorOutput.close();
            }
            if (delete != null) {
                delete.delete();
            }
            if (error != null) {
                error.delete();
            }
        }
        return true;
    }

    private ProductosSmsTO eliminar(ProductosSmsTO productosSmsTO, Connection connection) throws Exception {
        block13 : {
            StringBuffer sbQuery = new StringBuffer();
            sbQuery.append("DELETE FROM ").append(this.schema_database).append("PTO_CTLPROMOCIONES_SMS ");
            sbQuery.append(" WHERE CLAVEM2K = ?");
            sbQuery.append(" AND CLAVESMS = ? ");
            PreparedStatement preparedStatement = null;
            try {
                try {
                    int contador = 1;
                    preparedStatement = connection.prepareStatement(sbQuery.toString());
                    preparedStatement.setString(contador++, productosSmsTO.getClaveM2k());
                    preparedStatement.setString(contador++, productosSmsTO.getClaveSms());
                    if (preparedStatement.executeUpdate() > 0) {
                        productosSmsTO.setAgregaIdMensaje(0, "Proceso Exitoso");
                        break block13;
                    }
                    productosSmsTO.setAgregaIdMensaje(-1, "No se elimino el registro");
                }
                catch (SQLException sqlE) {
                    productosSmsTO.setIdError(-2);
                    productosSmsTO.setMensaje("EXC_Al ejecutar el delete: " + sqlE.toString() + "]");
                    if (preparedStatement == null) break block13;
                    try {
                        preparedStatement.close();
                        preparedStatement = null;
                    }
                    catch (Exception var7_7) {}
                }
            }
            finally {
                if (preparedStatement != null) {
                    try {
                        preparedStatement.close();
                        preparedStatement = null;
                    }
                    catch (Exception var7_9) {}
                }
            }
        }
        return productosSmsTO;
    }

    @Override
    public void run() {
        try {
            this.eliminaPromocionesSms();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

