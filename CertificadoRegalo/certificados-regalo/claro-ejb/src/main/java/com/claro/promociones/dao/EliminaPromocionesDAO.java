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

public class EliminaPromocionesDAO
implements Runnable {
    protected final Logger error = Logger.getLogger((String)"loggerCirculoAzulError");
    protected final Logger logger = Logger.getLogger((String)"loggerCirculoAzul");
    private int idRegion;
    private String schema_database;
    private FileDataTO fileDataTO;

    public EliminaPromocionesDAO() {
        try {
            this.schema_database = ServiceLocator.getInstance().getVariable(ServiceLocator.schema_database);
        }
        catch (Exception e) {
            this.error.error((Object)"ProcesaPromocionesDAO", (Throwable)e);
        }
    }

    public EliminaPromocionesDAO(FileDataTO fileDataTO, int idRegion) {
        try {
            this.fileDataTO = fileDataTO;
            this.idRegion = idRegion;
            this.schema_database = ServiceLocator.getInstance().getVariable(ServiceLocator.schema_database);
        }
        catch (Exception e) {
            this.error.error((Object)"ProcesaPromocionesDAO", (Throwable)e);
        }
    }

    public boolean eliminaPromociones() throws Exception {
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
            Map<String, String> parametrosCorreo = consultaCuentasCorreoDAO.obtieneCuentasCorreoCargaCatalogos(this.idRegion);
            if (reader == null) {
                Utils.enviaCorreo(parametrosCorreo, (String)"Error al eliminar promociones.", (String)"No se pudo procesar el archivo, verifique que haya sido previamente cargado.", (List)null);
                return false;
            }
            try {
                String linea = "";
                String logEliminados = "promoEliminadasR" + this.idRegion + ".txt";
                String logErroresEliminados = "promoEliminadasErroresR" + this.idRegion + ".txt";
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
                    PromocionTO promocionTO = null;
                    try {
                        promocionTO = new PromocionTO(linea.split(","));
                    }
                    catch (Exception e) {
                        reporteError.println(String.valueOf(++movimientosBD) + ": Error al obtener linea del archivo [" + linea + "]" + e.getMessage());
                    }
                    if (promocionTO == null) continue;
                    try {
                        PromocionTO promoEliminada = this.eliminar(promocionTO, connection);
                        if (promoEliminada.getIdMensaje() == 0) {
                            connection.commit();
                            reporteDelete.println(String.valueOf(++movimientosBD) + ": Se elimino[" + promoEliminada.toString() + "]");
                        }
                        if (promoEliminada.getIdMensaje() == -1) {
                            connection.commit();
                            reporteDelete.println(String.valueOf(++movimientosBD) + ": Se elimino[" + promoEliminada.toString() + "]");
                        }
                        if (promoEliminada.getIdMensaje() != -2) continue;
                        reporteError.println(String.valueOf(++movimientosBD) + ": No se elimino[" + promocionTO.toString() + "]idError:" + promocionTO.getIdMensaje() + ",Mensaje:" + promocionTO.getMensaje());
                        continue;
                    }
                    catch (Exception e) {
                        reporteError.println(String.valueOf(++movimientosBD) + ": No se elimino[" + promocionTO.toString() + "]idError:" + promocionTO.getIdMensaje() + ",Mensaje:" + promocionTO.getMensaje());
                    }
                }
                if (movimientosBD > 0) {
                    connection.commit();
                }
                reporteDelete.flush();
                reporteError.flush();
                archivosLog.add(logEliminados);
                archivosLog.add(logErroresEliminados);
                Utils.enviaCorreo(parametrosCorreo, (String)"Eliminar promociones", (String)"Las promociones fueron procesadas", archivosLog);
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

    private PromocionTO eliminar(PromocionTO promocionTO, Connection connection) throws Exception {
        block13 : {
            StringBuffer sbQuery = new StringBuffer();
            sbQuery.append("DELETE FROM ").append(this.schema_database).append("PTO_CTLPROMOCIONES ");
            sbQuery.append(" WHERE IDPRODUCTO=IDPRODUCTO");
            sbQuery.append(" AND IDPRODUCTO = ? ");
            sbQuery.append(" AND IDREGION = ? ");
            sbQuery.append(" AND IDGRUPOPROMOCION = ? ");
            sbQuery.append(" AND ADENDUM = ? ");
            sbQuery.append(" AND FZAVENTAS = ? ");
            sbQuery.append(" AND INDICADOR = ? ");
            PreparedStatement preparedStatement = null;
            try {
                try {
                    int contador = 1;
                    preparedStatement = connection.prepareStatement(sbQuery.toString());
                    preparedStatement.setString(contador++, promocionTO.getIdProducto());
                    preparedStatement.setInt(contador++, Integer.parseInt(promocionTO.getIdRegion()));
                    preparedStatement.setInt(contador++, Integer.parseInt(promocionTO.getIdGrupoPromocion()));
                    preparedStatement.setInt(contador++, Integer.parseInt(promocionTO.getAddendum()));
                    preparedStatement.setString(contador++, promocionTO.getFzaVta());
                    preparedStatement.setInt(contador++, Integer.parseInt(promocionTO.getIndicador()));
                    if (preparedStatement.executeUpdate() > 0) {
                        promocionTO.setIdMensaje(0);
                        promocionTO.setMensaje("Proceso Exitoso");
                        break block13;
                    }
                    promocionTO.setIdMensaje(-1);
                    promocionTO.setMensaje("No se elimino el registro");
                }
                catch (SQLException sqlE) {
                    promocionTO.setIdMensaje(-2);
                    promocionTO.setMensaje("EXC_Al ejecutar el delete: " + sqlE.toString() + "]");
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
        return promocionTO;
    }

    @Override
    public void run() {
        try {
            this.eliminaPromociones();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

