/*
 * Decompiled with CFR 0_102.
 * 
 * Could not load the following classes:
 *  com.claro.transfer.promociones.PlanTO
 *  com.claro.transfer.service.FileDataTO
 *  com.claro.util.ServiceLocator
 *  com.claro.util.Utils
 *  org.apache.log4j.Logger
 */
package com.claro.promociones.dao;

import com.claro.dao.ConsultaCuentasCorreoDAO;
import com.claro.transfer.promociones.PlanTO;
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

public class AgregaPlanesDAO
implements Runnable {
    protected final Logger error = Logger.getLogger((String)"loggerCirculoAzulError");
    protected final Logger logger = Logger.getLogger((String)"loggerCirculoAzul");
    private String schema_database;
    private String archivo;
    private int idRegion;
    private FileDataTO fileDataTO;

    public AgregaPlanesDAO(FileDataTO fileDataTO, int idRegion) {
        try {
            this.schema_database = ServiceLocator.getInstance().getVariable(ServiceLocator.schema_database);
            this.fileDataTO = fileDataTO;
            this.idRegion = idRegion;
        }
        catch (Exception e) {
            this.error.error((Object)"AgregaPlanesDAO", (Throwable)e);
        }
    }

    public AgregaPlanesDAO() {
        try {
            this.schema_database = ServiceLocator.getInstance().getVariable(ServiceLocator.schema_database);
        }
        catch (Exception e) {
            this.error.error((Object)"AgregaPlanesDAO", (Throwable)e);
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

    public boolean actualizaPlan(PlanTO planTO) throws Exception {
        boolean actualiza;
        actualiza = false;
        Connection conn = null;
        PreparedStatement statement = null;
        try {
            try {
                conn = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                StringBuffer query = new StringBuffer();
                query.append("UPDATE ").append(this.schema_database).append("PTO_CTLPLANESTARIFARIOS SET ");
                query.append(planTO.getIdGrupoPromocion() != null ? " IDGRUPOPROMOCION =?" : "");
                query.append(planTO.getDescripcion() != null ? " ,DESCRIPCION=?" : "");
                query.append(planTO.getTecnologia() != null ? " ,TECNOLOGIA=?" : "");
                query.append(planTO.getBanMixto() != null ? " ,BMIXTO=?" : "");
                query.append(planTO.getModalidad() != null ? " ,MODALIDAD=?" : "");
                query.append(planTO.getBanSisact() != null ? " ,BSISACT=?" : "");
                query.append(planTO.getAdendum() != null ? " ,ADENDUM=?" : "");
                query.append(planTO.getRenta() != null ? " ,RENTA=?" : "");
                query.append(planTO.getBanRedencion() != null ? " ,BREDENCION=?" : "");
                query.append(planTO.getEstatus() != null ? " ,ESTATUS=?" : "");
                query.append(planTO.getBanRedencionAnct() != null ? " ,BREDENCIONANTC=?" : "");
                query.append(planTO.getTipoPlan() != null ? " ,TIPO_PLAN=? " : "");
                query.append(" WHERE ");
                query.append(" IDPLAN=? AND");
                query.append(" IDSEGMENTO=? AND");
                query.append(" IDREGION=? ");
                statement = conn.prepareStatement(query.toString());
                int contador = 1;
                if (planTO.getIdGrupoPromocion() != null) {
                    statement.setInt(contador++, Integer.parseInt(planTO.getIdGrupoPromocion()));
                }
                if (planTO.getDescripcion() != null) {
                    statement.setString(contador++, planTO.getDescripcion());
                }
                if (planTO.getTecnologia() != null) {
                    statement.setString(contador++, planTO.getTecnologia());
                }
                if (planTO.getBanMixto() != null) {
                    statement.setString(contador++, planTO.getBanMixto());
                }
                if (planTO.getModalidad() != null) {
                    statement.setString(contador++, planTO.getModalidad());
                }
                if (planTO.getBanSisact() != null) {
                    statement.setString(contador++, planTO.getBanSisact());
                }
                if (planTO.getAdendum() != null) {
                    statement.setInt(contador++, Integer.parseInt(planTO.getAdendum()));
                }
                if (planTO.getRenta() != null) {
                    statement.setInt(contador++, Integer.parseInt(planTO.getRenta()));
                }
                if (planTO.getBanRedencion() != null) {
                    statement.setInt(contador++, Integer.parseInt(planTO.getBanRedencion()));
                }
                if (planTO.getEstatus() != null) {
                    statement.setString(contador++, planTO.getEstatus());
                }
                if (planTO.getBanRedencionAnct() != null) {
                    statement.setString(contador++, planTO.getBanRedencionAnct());
                }
                if (planTO.getTipoPlan() != null) {
                    statement.setString(contador++, planTO.getTipoPlan());
                }
                statement.setString(contador++, planTO.getIdPlanNuevo());
                statement.setInt(contador++, Integer.parseInt(planTO.getSegmento()));
                statement.setInt(contador++, Integer.parseInt(planTO.getIdRegion()));
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

    public boolean agregaPlanes() throws Exception {
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
                Utils.enviaCorreo(parametrosCorreo, (String)"Error al procesar los planes", (String)"No se pudo procesar el archivo, verifique que haya sido previamente cargado", (List)null);
                return false;
            }
            try {
                String logInserts = "planesAgregadosR" + this.idRegion + ".txt";
                String logErrores = "planesAgregadosErroresR" + this.idRegion + ".txt";
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
                    PlanTO planTO = null;
                    try {
                        planTO = new PlanTO(linea.split(","));
                    }
                    catch (Exception e) {
                        reporteError.println(String.valueOf(++movimientosBD) + ": Error al obtener linea del archivo [" + linea + "]" + e.getMessage());
                    }
                    if (planTO == null) continue;
                    try {
                        PlanTO planInsertado = this.insertar(planTO, connection);
                        if (planInsertado.getIdMensaje() == 0) {
                            connection.commit();
                            reporteInsert.println(String.valueOf(++movimientosBD) + ": Se inserto[" + planInsertado.toString() + "]idError:" + planInsertado.getIdMensaje() + ",Mensaje:" + planInsertado.getMensaje());
                            continue;
                        }
                        reporteError.println(String.valueOf(++movimientosBD) + ": No se inserto[" + planInsertado.toString() + "]idError:" + planInsertado.getIdMensaje() + ",Mensaje:" + planInsertado.getMensaje());
                        continue;
                    }
                    catch (Exception e) {
                        reporteError.println(String.valueOf(++movimientosBD) + ": No se inserto[" + planTO.toString() + "]idError:" + planTO.getIdMensaje() + ",Mensaje:" + planTO.getMensaje());
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
                Utils.enviaCorreo(parametrosCorreo, (String)"Carga planes.", (String)"Los planes fueron procesados.", archivosLog);
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
            if (reader != null) {
                try {
                    reader.close();
                }
                catch (IOException var20_16) {}
            }
            if (inputStreamReader != null) {
                try {
                    inputStreamReader.close();
                }
                catch (IOException var20_17) {}
            }
            if (fileReader != null) {
                try {
                    fileReader.close();
                }
                catch (IOException var20_18) {}
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
    private PlanTO insertar(PlanTO planTO, Connection conexion) {
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
            this.agregaPlanes();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

