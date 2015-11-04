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
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;

public class ActualizaCteExcelenteDAO
implements Runnable {
    protected final Logger error = Logger.getLogger((String)"loggerCirculoAzulError");
    protected final Logger logger = Logger.getLogger((String)"loggerCirculoAzul");
    private String archivo;
    private int idRegion;
    private String schema_database;
    private FileDataTO fileDataTO;

    public ActualizaCteExcelenteDAO(FileDataTO fileDataTO, int idRegion) {
        try {
            this.fileDataTO = fileDataTO;
            this.idRegion = idRegion;
            this.schema_database = ServiceLocator.getInstance().getVariable(ServiceLocator.schema_database);
        }
        catch (Exception e) {
            this.error.error((Object)"ActualizaCteExcelenteDAO", (Throwable)e);
        }
    }

    public boolean actualizaCteExcelente(String nombreArchivo, int idRegion) throws Exception {
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
            ConsultaCuentasCorreoDAO consultaCuentasCorreoDAO = new ConsultaCuentasCorreoDAO();
            Map<String, String> parametrosCorreo = consultaCuentasCorreoDAO.obtieneCuentasCorreoCargaCatalogos(this.idRegion);
            inputStreamReader = new InputStreamReader(this.fileDataTO.getData());
            reader = new BufferedReader(inputStreamReader);
            if (reader == null) {
                Utils.enviaCorreo(parametrosCorreo, (String)"Error al procesar las lineas de cliente excelente.", (String)"No se pudo procesar el archivo, verifique que haya sido previamente cargado.", (List)null);
                return false;
            }
            try {
                String logInserts = "cteExcelenteActualizadosR" + idRegion + ".txt";
                String logErrores = "cteExcelenteActualizadosErroresR" + idRegion + ".txt";
                insert = new File(logInserts);
                error = new File(logErrores);
                insertOutput = new FileOutputStream(insert);
                errorOutput = new FileOutputStream(error);
                reporteInsert = new PrintWriter(insertOutput);
                reporteError = new PrintWriter(errorOutput);
                String linea = "";
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
                        reporteError.println(String.valueOf(++movimientosBD) + ": Error al obtener linea del archivo [" + linea + "]" + e.getMessage());
                    }
                    if (ctesExcelentesTO == null) continue;
                    try {
                        CtesExcelentesTO CtesExcelentesActualizada = this.actualizar(ctesExcelentesTO, connection);
                        if (CtesExcelentesActualizada.getIdError() == 0) {
                            connection.commit();
                            reporteInsert.println(String.valueOf(++movimientosBD) + ": Se actualizo[" + CtesExcelentesActualizada.toString() + "]");
                            continue;
                        }
                        reporteError.println(String.valueOf(++movimientosBD) + ": No se actualizo[" + CtesExcelentesActualizada.toString() + "]idError:" + CtesExcelentesActualizada.getIdError() + ",Mensaje:" + CtesExcelentesActualizada.getMensaje());
                        continue;
                    }
                    catch (Exception e) {
                        reporteError.println(String.valueOf(++movimientosBD) + ": No se actualizo[" + ctesExcelentesTO.toString() + "]idError:" + ctesExcelentesTO.getIdError() + ",Mensaje:" + ctesExcelentesTO.getMensaje());
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
                Utils.enviaCorreo(parametrosCorreo, (String)"Actualizacion de lineas cliente excelente.", (String)"Las lineas de cliente excelente fueron procesadas.", archivosLog);
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

    /*
     * Exception decompiling
     */
    private CtesExcelentesTO actualizar(CtesExcelentesTO ctesExcelentesTO, Connection connection) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [0[TRYBLOCK]], but top level block is 9[CATCHBLOCK]
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.processEndingBlocks(Op04StructuredStatement.java:392)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:444)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.createInitialStructuredBlock(Op03SimpleStatement.java:2802)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:787)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:214)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:159)
        // org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:91)
        // org.benf.cfr.reader.entities.Method.analyse(Method.java:353)
        // org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:731)
        // org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:663)
        // org.benf.cfr.reader.Main.doJar(Main.java:126)
        // org.benf.cfr.reader.Main.main(Main.java:178)
        throw new IllegalStateException("Decompilation failed");
    }

    @Override
    public void run() {
        try {
            this.actualizaCteExcelente(this.archivo, this.idRegion);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getArchivo() {
        return this.archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    public int getIdRegion() {
        return this.idRegion;
    }

    public void setIdRegion(int idRegion) {
        this.idRegion = idRegion;
    }
}

