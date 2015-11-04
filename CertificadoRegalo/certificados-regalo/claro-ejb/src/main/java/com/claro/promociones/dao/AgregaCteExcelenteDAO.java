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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;

public class AgregaCteExcelenteDAO
implements Runnable {
    protected final Logger error = Logger.getLogger((String)"loggerCirculoAzulError");
    protected final Logger logger = Logger.getLogger((String)"loggerCirculoAzul");
    private String schema_database;
    private String archivo;
    private int idRegion;
    private FileDataTO fileDataTO;

    public AgregaCteExcelenteDAO(FileDataTO fileDataTO, int idRegion) {
        this.fileDataTO = fileDataTO;
        this.idRegion = idRegion;
        try {
            this.schema_database = ServiceLocator.getInstance().getVariable(ServiceLocator.schema_database);
        }
        catch (Exception e) {
            this.error.error((Object)"AgregaCteExcelenteDAO", (Throwable)e);
        }
    }

    public AgregaCteExcelenteDAO() {
        try {
            this.schema_database = ServiceLocator.getInstance().getVariable(ServiceLocator.schema_database);
        }
        catch (Exception e) {
            this.error.error((Object)"AgregaCteExcelenteDAO", (Throwable)e);
        }
    }

    public boolean actualizaCteExcelente(CtesExcelentesTO ctesExcelentesTO) throws Exception {
        boolean actualiza;
        actualiza = false;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            try {
                connection = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                StringBuffer query = new StringBuffer();
                query.append("update ").append(this.schema_database).append("PTO_TBLLINEASCTEEXC ");
                query.append("set LINEA=?,IDREGION=? ,ESTATUS=? ");
                query.append("where CUENTA =?");
                statement = connection.prepareStatement(query.toString());
                statement.setString(1, ctesExcelentesTO.getLinea());
                statement.setString(2, ctesExcelentesTO.getIdRegion());
                statement.setString(3, ctesExcelentesTO.getEstatus());
                statement.setString(4, ctesExcelentesTO.getCuenta());
                if (statement.executeUpdate() == 1) {
                    actualiza = true;
                }
            }
            catch (Exception ex) {
                throw new Exception(". " + ex);
            }
        }
        finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            }
            catch (Exception var7_10) {}
            if (connection != null) {
                try {
                    connection.close();
                    connection = null;
                }
                catch (Exception var7_11) {}
            }
        }
        return actualiza;
    }

    /*
     * Exception decompiling
     */
    public CtesExcelentesTO actualiza(CtesExcelentesTO ctesExcelentesTO, Connection conexion) throws Exception {
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

    public boolean agregaCteExcelente() throws Exception {
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
                Utils.enviaCorreo(parametrosCorreo, (String)"Error al procesar las lineas de cliente excelente.", (String)"No se pudo procesar el archivo, verifique que haya sido previamente cargado.", (List)null);
                return false;
            }
            try {
                String linea = "";
                connection = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                connection.setAutoCommit(false);
                String logInserts = "cteExcelenteAgregadosR" + this.idRegion + ".txt";
                String logErrores = "cteExcelenteAgregadosErroresR" + this.idRegion + ".txt";
                insert = new File(logInserts);
                error = new File(logErrores);
                insertOutput = new FileOutputStream(insert);
                errorOutput = new FileOutputStream(error);
                reporteInsert = new PrintWriter(insertOutput);
                reporteError = new PrintWriter(errorOutput);
                while (linea != null) {
                    CtesExcelentesTO ctesExcelentesTO;
                    block57 : {
                        linea = reader.readLine();
                        if (linea == null || linea.trim().equals("")) continue;
                        ctesExcelentesTO = null;
                        try {
                            ctesExcelentesTO = new CtesExcelentesTO(linea.split(","));
                        }
                        catch (Exception e) {
                            reporteError.println(String.valueOf(++movimientosBD) + ": Error al obtener linea del archivo [" + linea + "]" + e.getMessage());
                            if (movimientosBD <= 0) break block57;
                            connection.commit();
                        }
                    }
                    if (ctesExcelentesTO == null) continue;
                    CtesExcelentesTO ctesExcelentesInsertado = null;
                    try {
                        ctesExcelentesInsertado = this.existeCta(ctesExcelentesTO, connection) ? this.actualiza(ctesExcelentesTO, connection) : this.insertar(ctesExcelentesTO, connection);
                        if (ctesExcelentesInsertado.getIdError() == 0) {
                            connection.commit();
                            reporteInsert.println(String.valueOf(++movimientosBD) + ": Se inserto[" + ctesExcelentesInsertado.toString() + "]");
                            continue;
                        }
                        reporteError.println(String.valueOf(++movimientosBD) + ": No se inserto[" + ctesExcelentesInsertado.toString() + "]idError:" + ctesExcelentesInsertado.getIdError() + ",Mensaje:" + ctesExcelentesInsertado.getMensaje());
                        continue;
                    }
                    catch (Exception e) {
                        reporteError.println(String.valueOf(++movimientosBD) + ": No se inserto[" + ctesExcelentesTO.toString() + "]idError:" + ctesExcelentesTO.getIdError() + ",Mensaje:" + ctesExcelentesTO.getMensaje());
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
                Utils.enviaCorreo(parametrosCorreo, (String)"Carga lineas cliente excelente.", (String)"Las lineas de cliente excelente fueron procesadas.", archivosLog);
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
                catch (Exception var21_15) {}
            }
            if (reader != null) {
                try {
                    reader.close();
                }
                catch (IOException var21_16) {}
            }
            if (inputStreamReader != null) {
                try {
                    inputStreamReader.close();
                }
                catch (IOException var21_17) {}
            }
            if (fileReader != null) {
                try {
                    fileReader.close();
                }
                catch (IOException var21_18) {}
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
    private CtesExcelentesTO insertar(CtesExcelentesTO ctesExcelentesTO, Connection conexion) {
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

    public boolean existeCta(CtesExcelentesTO ctesExcelentesTO, Connection conexion) throws Exception {
        boolean existe;
        existe = false;
        PreparedStatement prepStat = null;
        ResultSet resultSet = null;
        try {
            try {
                StringBuffer query = new StringBuffer();
                query.append("SELECT CUENTA ");
                query.append("FROM ").append(this.schema_database).append("PTO_TBLLINEASCTEEXC ");
                query.append("WHERE CUENTA=?");
                prepStat = conexion.prepareStatement(query.toString());
                prepStat.setString(1, ctesExcelentesTO.getCuenta());
                resultSet = prepStat.executeQuery();
                if (resultSet.next()) {
                    existe = true;
                }
            }
            catch (Exception e) {
                throw new Exception("Error existeCta consulta " + e.toString() + "]");
            }
        }
        finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                    resultSet = null;
                }
                catch (Exception var8_11) {}
            }
            if (prepStat != null) {
                try {
                    prepStat.close();
                    prepStat = null;
                }
                catch (Exception var8_12) {}
            }
        }
        return existe;
    }

    @Override
    public void run() {
        try {
            this.agregaCteExcelente();
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

