/*
 * Decompiled with CFR 0_102.
 * 
 * Could not load the following classes:
 *  com.claro.transfer.promociones.PromocionTO
 *  com.claro.transfer.service.DocumentoTO
 *  com.claro.transfer.service.FileDataTO
 *  com.claro.util.ComunicaFTP
 *  com.claro.util.ServiceLocator
 *  com.claro.util.Utils
 *  org.apache.log4j.Logger
 */
package com.claro.promociones.dao;

import com.claro.dao.ConsultaCuentasCorreoDAO;
import com.claro.transfer.promociones.PromocionTO;
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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;

public class AgregaPromocionesDAO
implements Runnable {
    protected final Logger error = Logger.getLogger((String)"loggerCirculoAzulError");
    protected final Logger logger = Logger.getLogger((String)"loggerCirculoAzul");
    private String archivo;
    private int idRegion;
    private String schema_database;
    private String usuario;
    private FileDataTO fileDataTO;

    public AgregaPromocionesDAO() {
        try {
            this.schema_database = ServiceLocator.getInstance().getVariable(ServiceLocator.schema_database);
        }
        catch (Exception e) {
            this.error.error((Object)"ProcesaPromocionesDAO", (Throwable)e);
        }
    }

    public AgregaPromocionesDAO(FileDataTO fileDataTO, int idRegion, String usuario) {
        try {
            this.fileDataTO = fileDataTO;
            this.idRegion = idRegion;
            this.schema_database = ServiceLocator.getInstance().getVariable(ServiceLocator.schema_database);
            this.usuario = usuario;
        }
        catch (Exception e) {
            this.error.error((Object)"ProcesaPromocionesDAO", (Throwable)e);
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

    public boolean insertaPromocion(Connection conn, PromocionTO promocionTO) throws Exception {
        boolean actual;
        actual = false;
        PreparedStatement stAct = null;
        try {
            try {
                StringBuffer insertaPromocion = new StringBuffer();
                insertaPromocion.append("insert into ").append(this.schema_database).append("PTO_CTLPROMOCIONES(IDPRODUCTO,");
                insertaPromocion.append("IDREGION,IDGRUPOPROMOCION,DESCRIPCION,TIPOPRODUCTO,PRECIOLISTA,");
                insertaPromocion.append("PRECIOACTIVACION,MARCA,MODELO,URL,TECNOLOGIA,ESTATUS,BANSISACT,");
                insertaPromocion.append("ADENDUM,FZAVENTAS,VALORPUNTOS,INDICADOR) ");
                insertaPromocion.append("values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                stAct = conn.prepareStatement(insertaPromocion.toString());
                stAct.setString(1, promocionTO.getIdProducto());
                stAct.setInt(2, Integer.parseInt(promocionTO.getIdRegion()));
                stAct.setInt(3, Integer.parseInt(promocionTO.getIdGrupoPromocion()));
                stAct.setString(4, promocionTO.getDescripcion());
                stAct.setString(5, promocionTO.getTipoProducto());
                if (promocionTO.getPrecioLista() != null) {
                    stAct.setFloat(6, Float.parseFloat(promocionTO.getPrecioLista()));
                } else {
                    stAct.setFloat(6, 0.0f);
                }
                if (promocionTO.getPrecioActiva() != null) {
                    stAct.setFloat(7, Float.parseFloat(promocionTO.getPrecioActiva()));
                } else {
                    stAct.setFloat(7, 0.0f);
                }
                stAct.setString(8, promocionTO.getMarca());
                stAct.setString(9, promocionTO.getModelo());
                stAct.setString(10, promocionTO.getURL());
                stAct.setString(11, promocionTO.getTecnologia());
                stAct.setString(12, promocionTO.getEstatus());
                stAct.setString(13, promocionTO.getBanSISACT());
                stAct.setInt(14, Integer.parseInt(promocionTO.getAddendum()));
                stAct.setString(15, promocionTO.getFzaVta());
                if (promocionTO.getValorPtos() != null) {
                    stAct.setInt(16, Integer.parseInt(promocionTO.getValorPtos()));
                } else {
                    stAct.setInt(16, 0);
                }
                if (promocionTO.getIndicador() != null) {
                    stAct.setInt(17, Integer.parseInt(promocionTO.getIndicador()));
                } else {
                    stAct.setInt(17, 0);
                }
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

    public boolean actualizaPromocion(PromocionTO promocionTO) throws Exception {
        boolean actualiza;
        actualiza = false;
        Connection conn = null;
        PreparedStatement statement = null;
        try {
            try {
                conn = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                StringBuffer query = new StringBuffer();
                query.append("UPDATE ").append(this.schema_database).append("PTO_CTLPROMOCIONES SET IDPRODUCTO = IDPRODUCTO");
                query.append(promocionTO.getDescripcion() != null ? " ,DESCRIPCION=?" : "");
                query.append(promocionTO.getTipoProducto() != null ? " ,TIPOPRODUCTO=?" : "");
                query.append(promocionTO.getPrecioLista() != null ? " ,PRECIOLISTA=?" : "");
                query.append(promocionTO.getPrecioActiva() != null ? " ,PRECIOACTIVACION=?" : "");
                query.append(promocionTO.getMarca() != null ? " ,MARCA=?" : "");
                query.append(promocionTO.getModelo() != null ? " ,MODELO=?" : "");
                query.append(promocionTO.getURL() != null ? " ,URL=?" : "");
                query.append(promocionTO.getTecnologia() != null ? " ,TECNOLOGIA=?" : "");
                query.append(promocionTO.getEstatus() != null ? " ,ESTATUS=?" : "");
                query.append(promocionTO.getBanSISACT() != null ? " ,BANSISACT=?" : "");
                query.append(promocionTO.getValorPtos() != null & !promocionTO.getValorPtos().equals("-1") ? " ,VALORPUNTOS=?" : "");
                query.append(" WHERE ");
                query.append(" IDPRODUCTO=?");
                query.append(" AND IDREGION=?");
                query.append(" AND IDGRUPOPROMOCION=?");
                query.append(" AND FZAVENTAS=? ");
                query.append(" AND INDICADOR=? ");
                query.append(" AND ADENDUM=? ");
                statement = conn.prepareStatement(query.toString());
                int contador = 1;
                if (promocionTO.getDescripcion() != null) {
                    statement.setString(contador++, promocionTO.getDescripcion());
                }
                if (promocionTO.getTipoProducto() != null) {
                    statement.setString(contador++, promocionTO.getTipoProducto());
                }
                if (promocionTO.getPrecioLista() != null) {
                    statement.setFloat(contador++, Float.parseFloat(promocionTO.getPrecioLista()));
                }
                if (promocionTO.getPrecioActiva() != null) {
                    statement.setFloat(contador++, Float.parseFloat(promocionTO.getPrecioActiva()));
                }
                if (promocionTO.getMarca() != null) {
                    statement.setString(contador++, promocionTO.getMarca());
                }
                if (promocionTO.getModelo() != null) {
                    statement.setString(contador++, promocionTO.getModelo());
                }
                if (promocionTO.getURL() != null) {
                    statement.setString(contador++, promocionTO.getURL());
                }
                if (promocionTO.getTecnologia() != null) {
                    statement.setString(contador++, promocionTO.getTecnologia());
                }
                if (promocionTO.getEstatus() != null) {
                    statement.setString(contador++, promocionTO.getEstatus());
                }
                if (promocionTO.getBanSISACT() != null) {
                    statement.setString(contador++, promocionTO.getBanSISACT());
                }
                if (promocionTO.getValorPtos() != null & !promocionTO.getValorPtos().equals("-1")) {
                    statement.setInt(contador++, Integer.parseInt(promocionTO.getValorPtos()));
                }
                statement.setString(contador++, promocionTO.getIdProducto());
                statement.setInt(contador++, Integer.parseInt(promocionTO.getIdRegion()));
                statement.setInt(contador++, Integer.parseInt(promocionTO.getIdGrupoPromocion()));
                statement.setString(contador++, promocionTO.getFzaVta());
                statement.setInt(contador++, Integer.parseInt(promocionTO.getIndicador()));
                statement.setInt(contador++, Integer.parseInt(promocionTO.getAddendum()));
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

    public boolean agregaPromociones() throws Exception {
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
                String logInserts = "promoAgregadasR" + this.idRegion + ".txt";
                String logErrores = "promoAgregadasErroresR" + this.idRegion + ".txt";
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
                    PromocionTO promocionTO = null;
                    try {
                        promocionTO = new PromocionTO(linea.split(","));
                    }
                    catch (Exception e) {
                        reporteError.println(String.valueOf(++movimientosBD) + ": Error al obtener linea del archivo [" + linea + "]" + e.getMessage());
                    }
                    if (promocionTO == null) continue;
                    try {
                        PromocionTO promoInsertada = this.insertar(promocionTO, connection);
                        if (promoInsertada.getIdMensaje() == 0) {
                            connection.commit();
                            reporteInsert.println(String.valueOf(++movimientosBD) + ": Se inserto[" + promoInsertada.toString() + "]");
                            continue;
                        }
                        reporteError.println(String.valueOf(++movimientosBD) + ": No se inserto[" + promoInsertada.toString() + "]idError:" + promoInsertada.getIdMensaje() + ",Mensaje:" + promoInsertada.getMensaje());
                        continue;
                    }
                    catch (Exception e) {
                        reporteError.println(String.valueOf(++movimientosBD) + ": No se inserto[" + promocionTO.toString() + "]idError:" + promocionTO.getIdMensaje() + ",Mensaje:" + promocionTO.getMensaje());
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
                Utils.enviaCorreo(parametrosCorreo, (String)"Carga promociones.", (String)"Las promociones fueron procesadas.", archivosLog);
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
    private PromocionTO insertar(PromocionTO promocionTO, Connection conexion) {
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
            this.agregaPromociones();
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

