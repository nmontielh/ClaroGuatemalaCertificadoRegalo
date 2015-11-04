/*
 * Decompiled with CFR 0_102.
 * 
 * Could not load the following classes:
 *  com.claro.transfer.promociones.GrupoTO
 *  com.claro.transfer.service.FileDataTO
 *  com.claro.util.ServiceLocator
 *  com.claro.util.Utils
 *  org.apache.log4j.Logger
 */
package com.claro.promociones.dao;

import com.claro.dao.ConsultaCuentasCorreoDAO;
import com.claro.transfer.promociones.GrupoTO;
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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;

public class AgregaGruposDAO
implements Runnable {
    protected final Logger error = Logger.getLogger((String)"loggerCirculoAzulError");
    protected final Logger logger = Logger.getLogger((String)"loggerCirculoAzul");
    private String schema_database;
    private String archivo;
    private int idRegion;
    private FileDataTO fileDataTO;

    public AgregaGruposDAO() {
        try {
            this.schema_database = ServiceLocator.getInstance().getVariable(ServiceLocator.schema_database);
        }
        catch (Exception e) {
            this.error.error((Object)"AgregaGruposDAO", (Throwable)e);
        }
    }

    public AgregaGruposDAO(FileDataTO fileDataTO, int idRegion) {
        try {
            this.schema_database = ServiceLocator.getInstance().getVariable(ServiceLocator.schema_database);
            this.fileDataTO = fileDataTO;
            this.idRegion = idRegion;
        }
        catch (Exception e) {
            this.error.error((Object)"AgregaGruposDAO", (Throwable)e);
        }
    }

    public boolean actualizaGpo(GrupoTO grupoTO) throws Exception {
        boolean actualiza;
        Connection conn = null;
        PreparedStatement statement = null;
        actualiza = false;
        try {
            try {
                conn = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                StringBuffer query = new StringBuffer();
                query.append("update ").append(this.schema_database).append("PTO_CTLGRUPOPROMOCION ");
                query.append("set ");
                query.append(grupoTO.getTipoPromocion() != null && grupoTO.getTipoPromocion().length() > 0 ? "TIPOPROMOCION=?" : "");
                query.append(grupoTO.getEstatus() != null && grupoTO.getEstatus().length() > 0 ? ",ESTATUS=?" : "");
                query.append(grupoTO.getGrupoPromocion() != null && grupoTO.getGrupoPromocion().length() > 0 ? ",GRUPOPROMOCION=?" : "");
                query.append(grupoTO.getDescuento() != null && grupoTO.getDescuento().length() > 0 ? ",DESCUENTO=?" : "");
                query.append(grupoTO.getDescuentoValorAlto() != null && grupoTO.getDescuentoValorAlto().length() > 0 ? ",DESCUENTOALTOVALOR=?" : "");
                query.append("where IDGRUPOPROMOCION = ?");
                statement = conn.prepareStatement(query.toString());
                int cont = 1;
                if (grupoTO.getTipoPromocion() != null && grupoTO.getTipoPromocion().length() > 0) {
                    statement.setString(cont++, grupoTO.getTipoPromocion());
                }
                if (grupoTO.getEstatus() != null && grupoTO.getEstatus().length() > 0) {
                    statement.setString(cont++, grupoTO.getEstatus());
                }
                if (grupoTO.getGrupoPromocion() != null && grupoTO.getGrupoPromocion().length() > 0) {
                    statement.setString(cont++, grupoTO.getGrupoPromocion());
                }
                if (grupoTO.getDescuento() != null && grupoTO.getDescuento().length() > 0) {
                    statement.setFloat(cont++, Float.parseFloat(grupoTO.getDescuento()));
                }
                if (grupoTO.getDescuentoValorAlto() != null && grupoTO.getDescuentoValorAlto().length() > 0) {
                    statement.setString(cont++, grupoTO.getDescuentoValorAlto());
                }
                statement.setInt(cont++, Integer.parseInt(grupoTO.getIdGrupoPromocion()));
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
            catch (Exception var8_11) {}
            if (conn != null) {
                try {
                    conn.close();
                    conn = null;
                }
                catch (Exception var8_12) {}
            }
        }
        return actualiza;
    }

    public boolean agregaGrupos() throws Exception {
        Connection connection = null;
        int movimientosBD = 0;
        InputStreamReader fileReader = null;
        BufferedReader reader = null;
        PrintWriter reporteInsert = null;
        PrintWriter reporteError = null;
        File insert = null;
        File error = null;
        InputStreamReader inputStreamReader = null;
        FileOutputStream insertOutput = null;
        FileOutputStream errorOutput = null;
        try {
            ConsultaCuentasCorreoDAO consultaCuentasCorreoDAO2 = new ConsultaCuentasCorreoDAO();
            Map<String, String> parametrosCorreo = consultaCuentasCorreoDAO2.obtieneCuentasCorreoCargaCatalogos(this.idRegion);
            inputStreamReader = new InputStreamReader(this.fileDataTO.getData());
            reader = new BufferedReader(inputStreamReader);
            if (reader == null) {
                Utils.enviaCorreo(parametrosCorreo, (String)"Error al procesar los grupos de promociones.", (String)"No se pudo procesar el archivo, verifique que haya sido previamente cargado.", (List)null);
                return false;
            }
            try {
                String linea = "";
                connection = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                connection.setAutoCommit(false);
                String logInserts = "gpoAgregadosR" + this.idRegion + ".txt";
                String logErrores = "gpoAgregadosErroresR" + this.idRegion + ".txt";
                insert = new File(logInserts);
                error = new File(logErrores);
                insertOutput = new FileOutputStream(insert);
                errorOutput = new FileOutputStream(error);
                reporteInsert = new PrintWriter(insertOutput);
                reporteError = new PrintWriter(errorOutput);
                while (linea != null) {
                    GrupoTO grupoTO;
                    block49 : {
                        linea = reader.readLine();
                        if (linea == null || linea.trim().equals("")) continue;
                        grupoTO = null;
                        try {
                            grupoTO = new GrupoTO(linea.split(","));
                        }
                        catch (Exception e) {
                            reporteError.println(String.valueOf(++movimientosBD) + ": Error al obtener linea del archivo [" + linea + "]" + e.getMessage());
                            if (movimientosBD <= 0) break block49;
                            connection.commit();
                        }
                    }
                    if (grupoTO == null) continue;
                    try {
                        GrupoTO grupoInsertado = this.insertar(grupoTO, connection);
                        if (grupoInsertado.getIdError() == 0) {
                            connection.commit();
                            reporteInsert.println(String.valueOf(++movimientosBD) + ": Se inserto[" + grupoInsertado.toString() + "]");
                            continue;
                        }
                        reporteError.println(String.valueOf(++movimientosBD) + ": No se inserto[" + grupoInsertado.toString() + "]idError:" + grupoInsertado.getIdError() + ",Mensaje:" + grupoInsertado.getMensaje());
                        continue;
                    }
                    catch (Exception e) {
                        reporteError.println(String.valueOf(++movimientosBD) + ": No se inserto[" + grupoTO.toString() + "]idError:" + grupoTO.getIdError() + ",Mensaje:" + grupoTO.getMensaje());
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
                Utils.enviaCorreo(parametrosCorreo, (String)"Carga grupos de promociones.", (String)"Los grupos de promociones fueron procesados.", archivosLog);
            }
            catch (Exception consultaCuentasCorreoDAO21) {}
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
            if (reader != null) {
                try {
                    reader.close();
                }
                catch (IOException var20_16) {}
            }
            if (fileReader != null) {
                try {
                    fileReader.close();
                }
                catch (IOException var20_17) {}
            }
            if (reporteInsert != null) {
                reporteInsert.close();
            }
            if (reporteError != null) {
                reporteError.close();
            }
            if (inputStreamReader != null) {
                inputStreamReader.close();
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
    private GrupoTO insertar(GrupoTO grupoTO, Connection conexion) {
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

    @Override
    public void run() {
        try {
            this.agregaGrupos();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

