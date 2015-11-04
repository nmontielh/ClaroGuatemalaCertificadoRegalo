/*
 * Decompiled with CFR 0_102.
 * 
 * Could not load the following classes:
 *  com.claro.transfer.promociones.ProductosSmsTO
 *  com.claro.transfer.service.DocumentoTO
 *  com.claro.transfer.service.FileDataTO
 *  com.claro.util.ComunicaFTP
 *  com.claro.util.ServiceLocator
 *  com.claro.util.Utils
 *  org.apache.log4j.Logger
 */
package com.claro.promociones.dao;

import com.claro.dao.ConsultaCuentasCorreoDAO;
import com.claro.transfer.promociones.ProductosSmsTO;
import com.claro.transfer.service.DocumentoTO;
import com.claro.transfer.service.FileDataTO;
import com.claro.util.ComunicaFTP;
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

public class AgregaPromocionesSmsDAO
implements Runnable {
    protected final Logger error = Logger.getLogger((String)"loggerCirculoAzulError");
    protected final Logger logger = Logger.getLogger((String)"loggerCirculoAzul");
    private String archivo;
    private String schema_database;
    private FileDataTO fileDataTO;

    public AgregaPromocionesSmsDAO() {
        try {
            this.schema_database = ServiceLocator.getInstance().getVariable(ServiceLocator.schema_database);
        }
        catch (Exception e) {
            this.error.error((Object)"ProcesaPromocionesSmsDAO", (Throwable)e);
        }
    }

    public AgregaPromocionesSmsDAO(FileDataTO fileDataTO) {
        try {
            this.fileDataTO = fileDataTO;
            this.schema_database = ServiceLocator.getInstance().getVariable(ServiceLocator.schema_database);
        }
        catch (Exception e) {
            this.error.error((Object)"ProcesaPromocionesSmsDAO", (Throwable)e);
        }
    }

    public void guardaArchivo(DocumentoTO documentoTO, FileDataTO fileDataTO) throws Exception {
        try {
            ComunicaFTP comunicaFTP = new ComunicaFTP();
            comunicaFTP.guardaArchivo(documentoTO.getNombre(), fileDataTO.getData());
        }
        catch (Exception ex) {
            throw new Exception("Servicio de Archivos: Error al grabar archivo: " + ex);
        }
    }

    public boolean insertaPromocionSms(Connection conn, ProductosSmsTO productosSmsTO) throws Exception {
        boolean actual;
        actual = false;
        PreparedStatement stAct = null;
        try {
            try {
                StringBuffer insertaPromocion = new StringBuffer();
                insertaPromocion.append("insert into ").append(this.schema_database).append("PTO_CTLPROMOCIONES_SMS(CLAVEM2K,");
                insertaPromocion.append("CLAVESMS,IDPRODUCTO,DESCRIPCION,TIPOPRODUCTO,ESTATUS,VALORPUNTOS) ");
                insertaPromocion.append("values (?,?,?,?,?,?,?)");
                stAct = conn.prepareStatement(insertaPromocion.toString());
                stAct.setString(1, productosSmsTO.getClaveM2k());
                stAct.setString(2, productosSmsTO.getClaveSms());
                stAct.setString(3, productosSmsTO.getIdProducto());
                stAct.setString(4, productosSmsTO.getDescripcion());
                stAct.setString(5, productosSmsTO.getTipoProducto());
                stAct.setString(6, productosSmsTO.getEstatus());
                stAct.setInt(7, productosSmsTO.getValorPuntos());
                stAct.executeUpdate();
            }
            catch (Exception ex) {
                throw new Exception(". " + ex);
            }
        }
        finally {
            try {
                if (stAct != null) {
                    stAct.close();
                }
            }
            catch (Exception var7_9) {}
        }
        return actual;
    }

    public boolean actualizaPromocionSms(ProductosSmsTO productosSmsTO) throws Exception {
        boolean actualiza;
        actualiza = false;
        Connection conn = null;
        PreparedStatement statement = null;
        try {
            try {
                conn = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                StringBuffer query = new StringBuffer();
                query.append("UPDATE ").append(this.schema_database).append("PTO_CTLPROMOCIONES_SMS SET IDPRODUCTO = ?");
                query.append(productosSmsTO.getDescripcion() != null ? " ,DESCRIPCION=?" : "");
                query.append(productosSmsTO.getTipoProducto() != null ? " ,TIPOPRODUCTO=?" : "");
                query.append(productosSmsTO.getEstatus() != null ? " ,ESTATUS=?" : "");
                query.append(" ,VALORPUNTOS=?");
                query.append(" WHERE ");
                query.append(" CLAVEM2K=?");
                query.append(" AND CLAVESMS=?");
                statement = conn.prepareStatement(query.toString());
                int contador = 1;
                if (productosSmsTO.getIdProducto() != null) {
                    statement.setString(contador++, productosSmsTO.getIdProducto());
                }
                if (productosSmsTO.getDescripcion() != null) {
                    statement.setString(contador++, productosSmsTO.getDescripcion());
                }
                if (productosSmsTO.getTipoProducto() != null) {
                    statement.setString(contador++, productosSmsTO.getTipoProducto());
                }
                if (productosSmsTO.getEstatus() != null) {
                    statement.setString(contador++, productosSmsTO.getEstatus());
                }
                if (productosSmsTO.getValorPuntos() > 0) {
                    statement.setInt(contador++, productosSmsTO.getValorPuntos());
                }
                statement.setString(contador++, productosSmsTO.getClaveM2k());
                statement.setString(contador++, productosSmsTO.getClaveSms());
                if (statement.executeUpdate() == 1) {
                    actualiza = true;
                }
            }
            catch (Exception ex) {
                throw new Exception(ex.getMessage());
            }
        }
        finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            }
            catch (Exception var8_11) {}
            try {
                if (conn != null) {
                    conn.close();
                }
            }
            catch (Exception var8_12) {}
        }
        return actualiza;
    }

    public boolean agregaPromocionesSms() throws Exception {
        InputStreamReader inputStreamReader = null;
        Connection connection = null;
        int movimientosBD = 0;
        BufferedReader reader = null;
        PrintWriter reporteInsert = null;
        PrintWriter reporteError = null;
        InputStreamReader fileReader = null;
        FileOutputStream insertOutput = null;
        FileOutputStream errorOutput = null;
        File insert = null;
        File error = null;
        int idRegion = 0;
        try {
            inputStreamReader = new InputStreamReader(this.fileDataTO.getData());
            reader = new BufferedReader(inputStreamReader);
            ConsultaCuentasCorreoDAO consultaCuentasCorreoDAO = new ConsultaCuentasCorreoDAO();
            Map<String, String> parametrosCorreo = consultaCuentasCorreoDAO.obtieneCuentasCorreoCargaCatalogos(idRegion);
            if (reader == null) {
                Utils.enviaCorreo(parametrosCorreo, (String)"Error al procesar promociones Sms.", (String)"No se pudo procesar el archivo, verifique que haya sido previamente cargado.", (List)null);
                return false;
            }
            try {
                String linea = "";
                String logInserts = "promoSmsAgregadas.txt";
                String logErrores = "promoSmsAgregadasErrores.txt";
                insert = new File(logInserts);
                error = new File(logErrores);
                insertOutput = new FileOutputStream(insert);
                errorOutput = new FileOutputStream(error);
                reporteInsert = new PrintWriter(insertOutput);
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
                        reporteError.println(String.valueOf(++movimientosBD) + ": Error al obtener la promocion sms del archivo [" + linea + "]" + e.getMessage());
                    }
                    if (productosSmsTO == null) continue;
                    ProductosSmsTO productosSmsTOInsertado = null;
                    try {
                        productosSmsTOInsertado = this.existeClave(productosSmsTO, connection) ? this.actualiza(productosSmsTO, connection) : this.insertar(productosSmsTO, connection);
                        if (productosSmsTOInsertado.getIdError() == 0) {
                            connection.commit();
                            reporteInsert.println(String.valueOf(++movimientosBD) + ": Se inserto[" + productosSmsTOInsertado.toString() + "]");
                            continue;
                        }
                        reporteError.println(String.valueOf(++movimientosBD) + ": No se inserto[" + productosSmsTOInsertado.toString() + "]idError:" + productosSmsTOInsertado.getIdError() + ",Mensaje:" + productosSmsTOInsertado.getMensaje());
                        continue;
                    }
                    catch (Exception e) {
                        reporteError.println(String.valueOf(++movimientosBD) + ": No se inserto[" + productosSmsTO.toString() + "]idError:" + productosSmsTO.getIdError() + ",Mensaje:" + productosSmsTO.getMensaje());
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
                Utils.enviaCorreo(parametrosCorreo, (String)"Carga promociones sms.", (String)"Las promociones sms fueron procesadas.", archivosLog);
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
                catch (Exception var22_16) {}
            }
            if (reader != null) {
                try {
                    reader.close();
                }
                catch (IOException var22_17) {}
            }
            if (fileReader != null) {
                try {
                    fileReader.close();
                }
                catch (IOException var22_18) {}
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
    private ProductosSmsTO insertar(ProductosSmsTO productosSmsTO, Connection conexion) {
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

    public boolean existeClave(ProductosSmsTO productosSmsTO, Connection conexion) throws Exception {
        boolean existe;
        existe = false;
        PreparedStatement prepStat = null;
        ResultSet resultSet = null;
        try {
            try {
                StringBuffer query = new StringBuffer();
                query.append("SELECT CLAVESMS ");
                query.append("FROM ").append(this.schema_database).append("PTO_CTLPROMOCIONES_SMS ");
                query.append("WHERE CLAVEM2K =? and CLAVESMS =?");
                prepStat = conexion.prepareStatement(query.toString());
                prepStat.setString(1, productosSmsTO.getClaveM2k());
                prepStat.setString(2, productosSmsTO.getClaveSms());
                resultSet = prepStat.executeQuery();
                if (resultSet.next()) {
                    existe = true;
                }
            }
            catch (Exception e) {
                throw new Exception("Error existeClave consulta " + e.toString() + "]");
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

    /*
     * Exception decompiling
     */
    public ProductosSmsTO actualiza(ProductosSmsTO productosSmsTO, Connection conexion) throws Exception {
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
            this.agregaPromocionesSms();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

