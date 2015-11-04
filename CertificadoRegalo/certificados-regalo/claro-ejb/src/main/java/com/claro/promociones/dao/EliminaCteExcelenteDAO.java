/*
 * Decompiled with CFR 0_102.
 * 
 * Could not load the following classes:
 *  com.claro.transfer.promociones.CtesExcelentesTO
 *  com.claro.transfer.service.FileDataTO
 *  com.claro.util.ServiceLocator
 *  com.claro.util.Utils
 *  org.apache.log4j.Logger
 */
package com.claro.promociones.dao;

import com.claro.dao.ConsultaCuentasCorreoDAO;
import com.claro.transfer.promociones.CtesExcelentesTO;
import com.claro.transfer.service.FileDataTO;
import com.claro.util.ServiceLocator;
import com.claro.util.Utils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;

public class EliminaCteExcelenteDAO
implements Runnable {
    protected final Logger error = Logger.getLogger((String)"loggerCirculoAzulError");
    protected final Logger logger = Logger.getLogger((String)"loggerCirculoAzul");
    private String archivo;
    private int idRegion;
    private String schema_database;
    private FileDataTO fileDataTO;

    public EliminaCteExcelenteDAO() {
        try {
            this.schema_database = ServiceLocator.getInstance().getVariable(ServiceLocator.schema_database);
        }
        catch (Exception e) {
            this.error.error((Object)"EliminaCteExcelenteDAO", (Throwable)e);
        }
    }

    public EliminaCteExcelenteDAO(FileDataTO fileDataTO, int idRegion) {
        try {
            this.fileDataTO = fileDataTO;
            this.idRegion = idRegion;
            this.schema_database = ServiceLocator.getInstance().getVariable(ServiceLocator.schema_database);
        }
        catch (Exception e) {
            this.error.error((Object)"EliminaCteExcelenteDAO", (Throwable)e);
        }
    }

    public boolean eliminaCteExcelente() throws Exception {
        Connection connection = null;
        int movimientosBD = 0;
        PrintWriter reporteElimina = null;
        PrintWriter reporteErrorElimina = null;
        InputStreamReader inputStreamReader = null;
        InputStreamReader fileReader = null;
        BufferedReader reader = null;
        File elimina = null;
        File errorElimina = null;
        FileOutputStream eliminaOutput = null;
        FileOutputStream errorOutput = null;
        try {
            inputStreamReader = new InputStreamReader(this.fileDataTO.getData());
            reader = new BufferedReader(inputStreamReader);
            ConsultaCuentasCorreoDAO consultaCuentasCorreoDAO = new ConsultaCuentasCorreoDAO();
            Map<String, String> parametrosCorreo = consultaCuentasCorreoDAO.obtieneCuentasCorreoCargaCatalogos(this.idRegion);
            if (reader == null) {
                Utils.enviaCorreo(parametrosCorreo, (String)"Error al eliminar las lineas de cliente excelente.", (String)"No se pudo procesar el archivo, verifique que haya sido previamente cargado.", (List)null);
                return false;
            }
            try {
                String linea = "";
                String logEliminados = "cteExcelenteEliminadosR" + this.idRegion + ".txt";
                String logErroresEliminados = "cteExcelenteEliminadosErroresR" + this.idRegion + ".txt";
                elimina = new File(logEliminados);
                errorElimina = new File(logErroresEliminados);
                eliminaOutput = new FileOutputStream(elimina);
                errorOutput = new FileOutputStream(errorElimina);
                reporteElimina = new PrintWriter(eliminaOutput);
                reporteErrorElimina = new PrintWriter(errorOutput);
                connection = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                connection.setAutoCommit(false);
                while (linea != null) {
                    linea = reader.readLine();
                    if (linea == null || linea.trim().equals("")) continue;
                    CtesExcelentesTO ctesExcelentesTO = null;
                    try {
                        ctesExcelentesTO = new CtesExcelentesTO(linea.split(","));
                    }
                    catch (Exception e) {
                        reporteErrorElimina.println(String.valueOf(++movimientosBD) + ": Error al obtener linea del archivo [" + linea + "]" + e.getMessage());
                    }
                    if (ctesExcelentesTO == null) continue;
                    try {
                        CtesExcelentesTO ctesExcelentesEliminada = this.eliminar(ctesExcelentesTO, connection);
                        if (ctesExcelentesEliminada.getIdError() == 0) {
                            connection.commit();
                            reporteElimina.println(String.valueOf(++movimientosBD) + ": Se elimino[" + ctesExcelentesEliminada.toString() + "]");
                        }
                        if (ctesExcelentesEliminada.getIdError() == -1) {
                            connection.commit();
                            reporteElimina.println(String.valueOf(++movimientosBD) + ": Se elimino[" + ctesExcelentesEliminada.toString() + "]");
                        }
                        if (ctesExcelentesEliminada.getIdError() != -2) continue;
                        reporteErrorElimina.println(String.valueOf(++movimientosBD) + ": No se elimino[" + ctesExcelentesTO.toString() + "]idError:" + ctesExcelentesTO.getIdError() + ",Mensaje:" + ctesExcelentesTO.getMensaje());
                        continue;
                    }
                    catch (Exception e) {
                        reporteErrorElimina.println(String.valueOf(++movimientosBD) + ": No se elimino[" + ctesExcelentesTO.toString() + "]idError:" + ctesExcelentesTO.getIdError() + ",Mensaje:" + ctesExcelentesTO.getMensaje());
                    }
                }
                if (movimientosBD > 0) {
                    connection.commit();
                }
                reporteElimina.flush();
                reporteErrorElimina.flush();
                ArrayList<String> archivosLog = new ArrayList<String>();
                archivosLog.add(logEliminados);
                archivosLog.add(logErroresEliminados);
                Utils.enviaCorreo(parametrosCorreo, (String)"Eliminar lineas de cliente excelente.", (String)"Las lineas de cliente excelente fueron procesadas.", archivosLog);
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
                catch (Exception var20_15) {}
            }
            if (reporteElimina != null) {
                reporteElimina.close();
            }
            if (reporteErrorElimina != null) {
                reporteErrorElimina.close();
            }
            if (reader != null) {
                reader.close();
            }
            if (inputStreamReader != null) {
                inputStreamReader.close();
            }
            if (fileReader != null) {
                fileReader.close();
            }
            if (eliminaOutput != null) {
                eliminaOutput.close();
            }
            if (errorOutput != null) {
                errorOutput.close();
            }
            if (elimina != null) {
                elimina.delete();
            }
            if (errorElimina != null) {
                errorElimina.delete();
            }
        }
        return true;
    }

    private CtesExcelentesTO eliminar(CtesExcelentesTO ctesExcelentesTO, Connection connection) throws Exception {
        CtesExcelentesTO ctesExcelentesEliminada;
        block13 : {
            StringBuffer sbQuery = new StringBuffer();
            PreparedStatement preparedStatement = null;
            ctesExcelentesEliminada = null;
            sbQuery.append("UPDATE ").append(this.schema_database).append("PTO_TBLLINEASCTEEXC ");
            sbQuery.append("set ESTATUS=? ");
            sbQuery.append("where CUENTA=? ");
            try {
                try {
                    preparedStatement = connection.prepareStatement(sbQuery.toString());
                    preparedStatement.setString(1, "I");
                    preparedStatement.setString(2, ctesExcelentesTO.getCuenta());
                    ctesExcelentesEliminada = new CtesExcelentesTO();
                    ctesExcelentesEliminada.setCuenta(ctesExcelentesTO.getCuenta());
                    ctesExcelentesEliminada.setLinea(ctesExcelentesTO.getLinea());
                    ctesExcelentesEliminada.setIdRegion(ctesExcelentesTO.getIdRegion());
                    ctesExcelentesEliminada.setEstatus("I");
                    if (preparedStatement.executeUpdate() > 0) {
                        ctesExcelentesEliminada.setAgregaIdMensaje(0, "Proceso Exitoso");
                        break block13;
                    }
                    ctesExcelentesEliminada.setAgregaIdMensaje(-1, "No se elimino el registro");
                }
                catch (SQLException sqlE) {
                    ctesExcelentesEliminada.setAgregaIdMensaje(-2, "EXC_Al ejecutar el delete: " + sqlE.toString() + "]");
                    if (preparedStatement == null) break block13;
                    try {
                        preparedStatement.close();
                        preparedStatement = null;
                    }
                    catch (Exception var8_7) {}
                }
            }
            finally {
                if (preparedStatement != null) {
                    try {
                        preparedStatement.close();
                        preparedStatement = null;
                    }
                    catch (Exception var8_9) {}
                }
            }
        }
        return ctesExcelentesEliminada;
    }

    @Override
    public void run() {
        try {
            this.eliminaCteExcelente();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

