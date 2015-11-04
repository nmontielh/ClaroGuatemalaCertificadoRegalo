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

public class EliminaPlanesDAO
implements Runnable {
    protected final Logger error = Logger.getLogger((String)"loggerCirculoAzulError");
    protected final Logger logger = Logger.getLogger((String)"loggerCirculoAzul");
    private String archivo;
    private int idRegion;
    private String schema_database;
    private FileDataTO fileDataTO;

    public EliminaPlanesDAO() {
        try {
            this.schema_database = ServiceLocator.getInstance().getVariable(ServiceLocator.schema_database);
        }
        catch (Exception e) {
            this.error.error((Object)"ProcesaPromocionesDAO", (Throwable)e);
        }
    }

    public EliminaPlanesDAO(FileDataTO fileDataTO, int idRegion) {
        try {
            this.fileDataTO = fileDataTO;
            this.idRegion = idRegion;
            this.schema_database = ServiceLocator.getInstance().getVariable(ServiceLocator.schema_database);
        }
        catch (Exception e) {
            this.error.error((Object)"ProcesaPromocionesDAO", (Throwable)e);
        }
    }

    public boolean eliminaPlanes() throws Exception {
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
                Utils.enviaCorreo(parametrosCorreo, (String)"Error al eliminar planes.", (String)"No se pudo procesar el archivo, verifique que haya sido previamente cargado.", (List)null);
                return false;
            }
            try {
                String linea = "";
                String logEliminados = "planesEliminadosR" + this.idRegion + ".txt";
                String logErroresEliminados = "planesEliminadosErroresR" + this.idRegion + ".txt";
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
                    PlanTO planTO = null;
                    try {
                        planTO = new PlanTO(linea.split(","));
                    }
                    catch (Exception e) {
                        reporteErrorElimina.println(String.valueOf(++movimientosBD) + ": Error al obtener linea del archivo [" + linea + "]" + e.getMessage());
                    }
                    if (planTO == null) continue;
                    try {
                        PlanTO planEliminado = this.eliminar(planTO, connection);
                        if (planEliminado.getIdMensaje() == 0) {
                            connection.commit();
                            reporteElimina.println(String.valueOf(++movimientosBD) + ": Se elimino[" + planEliminado.toString() + "]");
                        }
                        if (planEliminado.getIdMensaje() == -1) {
                            connection.commit();
                            reporteElimina.println(String.valueOf(++movimientosBD) + ": Se elimino[" + planEliminado.toString() + "]");
                        }
                        if (planEliminado.getIdMensaje() != -2) continue;
                        reporteErrorElimina.println(String.valueOf(++movimientosBD) + ": No se elimino[" + planTO.toString() + "]idError:" + planTO.getIdMensaje() + ",Mensaje:" + planTO.getMensaje());
                        continue;
                    }
                    catch (Exception e) {
                        reporteErrorElimina.println(String.valueOf(++movimientosBD) + ": No se elimino[" + planTO.toString() + "]idError:" + planTO.getIdMensaje() + ",Mensaje:" + planTO.getMensaje());
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
                Utils.enviaCorreo(parametrosCorreo, (String)"Eliminar planes.", (String)"Los planes fueron procesados", archivosLog);
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
            if (inputStreamReader != null) {
                inputStreamReader.close();
            }
            if (reader != null) {
                reader.close();
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

    private PlanTO eliminar(PlanTO planTO, Connection connection) throws Exception {
        PlanTO planEliminado;
        block13 : {
            StringBuffer sbQuery = new StringBuffer();
            PreparedStatement preparedStatement = null;
            planEliminado = null;
            sbQuery.append("DELETE FROM ").append(this.schema_database).append("PTO_CTLPLANESTARIFARIOS ");
            sbQuery.append("where IDPLAN=? and IDREGION=? and IDSEGMENTO=?");
            try {
                try {
                    preparedStatement = connection.prepareStatement(sbQuery.toString());
                    preparedStatement.setString(1, planTO.getIdPlanNuevo());
                    preparedStatement.setInt(2, Integer.parseInt(planTO.getIdRegion()));
                    preparedStatement.setInt(3, Integer.parseInt(planTO.getSegmento()));
                    planEliminado = new PlanTO();
                    planEliminado.setIdPlanNuevo(planTO.getIdPlanNuevo());
                    planEliminado.setSegmento(planTO.getSegmento());
                    planEliminado.setIdRegion(planTO.getIdRegion());
                    planEliminado.setIdGrupoPromocion(planTO.getIdGrupoPromocion());
                    planEliminado.setDescripcion(planTO.getDescripcion());
                    planEliminado.setTecnologia(planTO.getTecnologia());
                    planEliminado.setBanMixto(planTO.getBanMixto());
                    planEliminado.setModalidad(planTO.getModalidad());
                    planEliminado.setBanSisact(planTO.getBanSisact());
                    planEliminado.setAdendum(planTO.getAdendum());
                    planEliminado.setRenta(planTO.getRenta());
                    planEliminado.setBanRedencion(planTO.getBanRedencion());
                    planEliminado.setEstatus(planTO.getEstatus());
                    planEliminado.setBanRedencionAnct(planTO.getBanRedencionAnct());
                    planEliminado.setTipoPlan(planTO.getTipoPlan());
                    if (preparedStatement.executeUpdate() > 0) {
                        planEliminado.setIdMensaje(0);
                        planEliminado.setMensaje("Proceso Exitoso");
                        break block13;
                    }
                    planEliminado.setIdMensaje(-1);
                    planEliminado.setMensaje("No se elimino el registro");
                }
                catch (SQLException sqlE) {
                    planEliminado.setIdMensaje(-2);
                    planEliminado.setMensaje("EXC_Al ejecutar el delete: " + sqlE.toString() + "]");
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
        return planEliminado;
    }

    @Override
    public void run() {
        try {
            this.eliminaPlanes();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

