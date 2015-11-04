/*
 * Decompiled with CFR 0_102.
 * 
 * Could not load the following classes:
 *  com.claro.exception.CAException
 *  com.claro.transfer.AreaPromocionTO
 *  com.claro.transfer.CteExcelenteTO
 *  com.claro.transfer.FuerzaVentasTO
 *  com.claro.transfer.MensajeTO
 *  com.claro.transfer.ParametrosTO
 *  com.claro.transfer.PerfilTO
 *  com.claro.transfer.PlanTO
 *  com.claro.transfer.ProductosSmsTO
 *  com.claro.transfer.PromocionTO
 *  com.claro.transfer.PuntosTO
 *  com.claro.transfer.TelefonoTO
 *  com.claro.transfer.UsuarioTO
 *  com.claro.transfer.promociones.GrupoTO
 *  com.claro.transfer.service.DocumentoTO
 *  com.claro.transfer.service.FileDataTO
 *  com.claro.util.SFtp
 *  com.claro.util.ServiceLocator
 *  net.sf.jasperreports.engine.JRExporterParameter
 *  net.sf.jasperreports.engine.JasperFillManager
 *  net.sf.jasperreports.engine.JasperPrint
 *  net.sf.jasperreports.engine.JasperRunManager
 *  net.sf.jasperreports.engine.export.JRCsvExporter
 *  net.sf.jasperreports.engine.export.JRCsvExporterParameter
 *  org.apache.log4j.Logger
 */
package com.claro.promociones.dao;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;

import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRCsvExporterParameter;

import org.apache.log4j.Logger;

import com.claro.dao.AsignacionDAO;
import com.claro.dao.ConsultasDAO;
import com.claro.dao.PuntosDAO;
import com.claro.dao.UsuarioDAO;
import com.claro.exception.CAException;
import com.claro.transfer.AreaPromocionTO;
import com.claro.transfer.CteExcelenteTO;
import com.claro.transfer.FuerzaVentasTO;
import com.claro.transfer.MensajeTO;
import com.claro.transfer.ParametrosTO;
import com.claro.transfer.PlanTO;
import com.claro.transfer.ProductosSmsTO;
import com.claro.transfer.PromocionTO;
import com.claro.transfer.PuntosTO;
import com.claro.transfer.TelefonoTO;
import com.claro.transfer.UsuarioTO;
import com.claro.transfer.promociones.GrupoTO;
import com.claro.transfer.service.DocumentoTO;
import com.claro.transfer.service.FileDataTO;
import com.claro.util.SFtp;
import com.claro.util.ServiceLocator;

public class PromocionesDAO {
    protected final Logger logger = Logger.getLogger((String)"loggerCirculoAzul");
    protected final Logger error = Logger.getLogger((String)"loggerCirculoAzulError");
    private String schema_database;

    public PromocionesDAO() {
        try {
            this.schema_database = ServiceLocator.getInstance().getVariable(ServiceLocator.schema_database);
        }
        catch (Exception e) {
            this.error.error((Object)"PromocionesDAO", (Throwable)e);
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     */
    public MensajeTO asignacionPuntos(ParametrosTO parametrosTO, int ptsAsignar, String ip) throws CAException {
        Connection connection = null;
        MensajeTO mensajeTO = new MensajeTO();
        try {
            ConsultasDAO consultasDAO = new ConsultasDAO();
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            UsuarioTO usuarioTO = usuarioDAO.consultaEmpleado(parametrosTO.getUsuariMovimiento(), ip, null, false);
            if (usuarioTO.getIdMensaje() != 0) {
                MensajeTO mensajeTO2 = usuarioTO.obtieneMensajeTO();
                return mensajeTO2;
            }
            TelefonoTO telefonoTO = consultasDAO.procedimientoGeneral(parametrosTO, usuarioTO.getPerfilTO(), null);
            if (telefonoTO.getIdMensaje() != 0) {
                MensajeTO mensajeTO3 = telefonoTO.obtieneMensajeTO();
                return mensajeTO3;
            }
            PuntosTO puntosTO = consultasDAO.obtienePuntos(parametrosTO.getCuenta(), parametrosTO.getSecuencia());
            if (puntosTO.getIdMensaje() != 0) {
                MensajeTO mensajeTO4 = puntosTO.obtieneMensajeTO();
                return mensajeTO4;
            }
            connection = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
            connection.setAutoCommit(false);
            long fechaTransaccion = System.currentTimeMillis();
            PuntosDAO puntosDAO = new PuntosDAO();
            AsignacionDAO asignacionDAO = new AsignacionDAO();
            String referencia = "POR PROMOCION-ASIGNA: " + parametrosTO.getUsuariMovimiento() + " COMENT: " + parametrosTO.getComentario();
            mensajeTO = puntosDAO.insertaDetalle(connection, fechaTransaccion, referencia, 15, ptsAsignar, null, parametrosTO.getCuenta(), parametrosTO.getSecuencia(), parametrosTO.getTelefono(), parametrosTO.getUsuariMovimiento());
            referencia = "POR PROMOCION-CIR- ASIGNA " + ptsAsignar + " PTOS. REALIZO: " + parametrosTO.getUsuariMovimiento() + " " + parametrosTO.getComentario();
            if (mensajeTO.getIdMensaje() == 0) {
                mensajeTO = puntosDAO.insertaComentarioTMP(connection, parametrosTO.getRegion(), telefonoTO.getCuenta(), parametrosTO.getUsuariMovimiento(), fechaTransaccion, referencia);
            }
            if (mensajeTO.getIdMensaje() == 0) {
                mensajeTO = asignacionDAO.actualizaTotalesAsignacion(connection, puntosTO.getPtsExcedentes() + ptsAsignar, parametrosTO.getCuenta(), parametrosTO.getSecuencia());
            }
            if (mensajeTO.getIdMensaje() == 0) {
                connection.commit();
                return mensajeTO;
            }
            connection.rollback();
            return mensajeTO;
        }
        catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                }
                catch (Exception usuarioDAO) {
                    // empty catch block
                }
            }
            this.error.info((Object)"SQLException.procesaAsignacion:", (Throwable)e);
            throw new CAException(-1, "[procesaAsignacion] SQLError: " + e.toString() + "Actualizar Inf", (Exception)e);
        }
        catch (Exception e) {
            if (connection != null) {
                try {
                    connection.rollback();
                }
                catch (Exception usuarioDAO) {
                    // empty catch block
                }
            }
            this.error.info((Object)"Exception.procesaAsignacion:", (Throwable)e);
            throw new CAException(-1, "[procesaAsignacion] Error: " + e.toString() + "Actualizar Inf", e);
        }
        finally {
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                    connection.close();
                    connection = null;
                }
                catch (Exception var18_16) {}
            }
        }
    }

    public void borraArchivo(DocumentoTO documentoTO) throws Exception {
        SFtp.eliminaArchivo((String)documentoTO.getNombre());
    }

    public void guardaArchivo(DocumentoTO documentoTO, FileDataTO fileDataTO) throws Exception {
        SFtp.guardaArchivoCirculoAzul((String)documentoTO.getNombre(), (InputStream)fileDataTO.getData());
    }

    public int insertaPromocion(DocumentoTO documentoTO) throws Exception {
        Connection oConn = null;
        PreparedStatement oStmt = null;
        ResultSet oRset = null;
        try {
            oConn = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
            StringBuffer query = new StringBuffer();
            query.append("INSERT INTO ").append(this.schema_database).append("PTO_CTLDOCPROMOCIONES(IDDOCUMENTO,NOMBRE,DOCUMENTO,STATUS,FECHAMOD,");
            query.append("IDUSERMOD) VALUES(?, ?, ?, ?, ?, ?)");
            oStmt = oConn.prepareStatement(query.toString());
            oStmt.setInt(1, documentoTO.getIdDocumento());
            oStmt.setString(2, documentoTO.getDescripcion());
            oStmt.setString(3, documentoTO.getNombre());
            oStmt.setString(4, documentoTO.getEstatus());
            oStmt.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
            oStmt.setString(6, documentoTO.getIdUsuario());
            int n = oStmt.executeUpdate();
            return n;
        }
        catch (Exception e) {
            throw e;
        }
        finally {
            if (oRset != null) {
                try {
                    oRset.close();
                    oRset = null;
                }
                catch (Exception var8_11) {}
            }
            if (oStmt != null) {
                try {
                    oStmt.close();
                    oStmt = null;
                }
                catch (Exception var8_12) {}
            }
            if (oConn != null) {
                try {
                    oConn.close();
                    oConn = null;
                }
                catch (Exception var8_13) {}
            }
        }
    }

    public void borrarPromocion(int idDocumento) throws Exception {
        Connection oConn = null;
        PreparedStatement oStmt = null;
        ResultSet oRset = null;
        try {
            try {
                oConn = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                StringBuffer query = new StringBuffer();
                query.append("update ").append(this.schema_database).append("PTO_CTLDOCPROMOCIONES set STATUS='I', FECHAMOD=? where IDDOCUMENTO=?");
                oStmt = oConn.prepareStatement(query.toString());
                oStmt.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
                oStmt.setInt(2, idDocumento);
                oStmt.executeUpdate();
            }
            catch (Exception e) {
                throw new Exception("DocumDAO.borrarDocumento Error: " + e);
            }
        }
        finally {
            if (oRset != null) {
                try {
                    oRset.close();
                    oRset = null;
                }
                catch (Exception var7_11) {}
            }
            if (oStmt != null) {
                try {
                    oStmt.close();
                    oStmt = null;
                }
                catch (Exception var7_12) {}
            }
            if (oConn != null) {
                try {
                    oConn.close();
                    oConn = null;
                }
                catch (Exception var7_13) {}
            }
        }
    }

    public int obtenIDDocumento() throws Exception {
        int idDocumento;
        block21 : {
            idDocumento = 0;
            Connection conn = null;
            PreparedStatement prepStat = null;
            ResultSet resultSet = null;
            try {
                try {
                    conn = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                    StringBuffer query = new StringBuffer();
                    query.append("select max(IDDOCUMENTO) from ").append(this.schema_database).append("PTO_CTLDOCPROMOCIONES");
                    prepStat = conn.prepareStatement(query.toString());
                    resultSet = prepStat.executeQuery();
                    if (resultSet.next()) {
                        idDocumento = resultSet.getInt(1);
                        break block21;
                    }
                    throw new Exception("Consulta IDDocumento ");
                }
                catch (Exception e) {
                    throw new Exception("Consulta IDDocumento [" + e + "]");
                }
            }
            finally {
                if (resultSet != null) {
                    try {
                        resultSet.close();
                        resultSet = null;
                    }
                    catch (Exception var7_11) {}
                }
                if (prepStat != null) {
                    try {
                        prepStat.close();
                        prepStat = null;
                    }
                    catch (Exception var7_12) {}
                }
                if (conn != null) {
                    try {
                        conn.close();
                        conn = null;
                    }
                    catch (Exception var7_13) {}
                }
            }
        }
        return ++idDocumento;
    }

    public ArrayList<DocumentoTO> obtenPromociones(String estatus) throws Exception {
        ArrayList<DocumentoTO> listaPromociones;
        listaPromociones = new ArrayList<DocumentoTO>();
        Connection conn = null;
        PreparedStatement prepStat = null;
        ResultSet resultSet = null;
        try {
            try {
                conn = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                StringBuffer query = new StringBuffer();
                query.append("select IDDOCUMENTO,NOMBRE,DOCUMENTO,STATUS,FECHAMOD,");
                query.append("IDUSERMOD from ").append(this.schema_database).append("PTO_CTLDOCPROMOCIONES where STATUS=? order by NOMBRE ");
                prepStat = conn.prepareStatement(query.toString());
                prepStat.setString(1, estatus);
                resultSet = prepStat.executeQuery();
                while (resultSet.next()) {
                    DocumentoTO documentoTO = new DocumentoTO();
                    documentoTO.setIdDocumento(resultSet.getInt(1));
                    documentoTO.setDescripcion(resultSet.getString(2));
                    documentoTO.setNombre(resultSet.getString(3));
                    documentoTO.setFechaCreacion(resultSet.getTimestamp(5));
                    documentoTO.setIdUsuario(resultSet.getString(6));
                    listaPromociones.add(documentoTO);
                }
            }
            catch (Exception e) {
                throw new Exception("Consulta IDDocumento [" + e + "]");
            }
        }
        finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                    resultSet = null;
                }
                catch (Exception var9_13) {}
            }
            if (prepStat != null) {
                try {
                    prepStat.close();
                    prepStat = null;
                }
                catch (Exception var9_14) {}
            }
            if (conn != null) {
                try {
                    conn.close();
                    conn = null;
                }
                catch (Exception var9_15) {}
            }
        }
        return listaPromociones;
    }

    public ArrayList<GrupoTO> consultaGpoPromo(GrupoTO grupoTO) throws Exception {
        ArrayList<GrupoTO> listaGposPromociones;
        Connection oConn = null;
        PreparedStatement oStmt = null;
        ResultSet oRset = null;
        listaGposPromociones = new ArrayList<GrupoTO>();
        try {
            try {
                oConn = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                StringBuffer query = new StringBuffer();
                query.append("SELECT IDGRUPOPROMOCION,TIPOPROMOCION,ESTATUS,");
                query.append("GRUPOPROMOCION,DESCUENTO,DESCUENTOALTOVALOR ");
                query.append("FROM ").append(this.schema_database).append("PTO_CTLGRUPOPROMOCION ");
                query.append("WHERE ESTATUS=? ");
                if (grupoTO.getIdGrupoPromocion() != null && grupoTO.getIdGrupoPromocion().trim().length() > 0) {
                    query.append("AND IDGRUPOPROMOCION=? ");
                }
                if (grupoTO.getTipoPromocion() != null && grupoTO.getTipoPromocion().trim().length() > 0) {
                    query.append("AND TIPOPROMOCION=? ");
                }
                query.append("order by IDGRUPOPROMOCION desc");
                oStmt = oConn.prepareStatement(query.toString());
                oStmt.setString(1, grupoTO.getEstatus());
                if (grupoTO.getIdGrupoPromocion() != null && grupoTO.getIdGrupoPromocion().trim().length() > 0) {
                    oStmt.setInt(2, Integer.valueOf(grupoTO.getIdGrupoPromocion()));
                    if (grupoTO.getTipoPromocion() != null && grupoTO.getTipoPromocion().trim().length() > 0) {
                        oStmt.setString(3, grupoTO.getTipoPromocion());
                    }
                } else if (grupoTO.getTipoPromocion() != null && grupoTO.getTipoPromocion().trim().length() > 0) {
                    oStmt.setString(2, grupoTO.getTipoPromocion());
                }
                oRset = oStmt.executeQuery();
                while (oRset.next()) {
                    GrupoTO grupoPromoTO = new GrupoTO();
                    grupoPromoTO.setIdGrupoPromocion(String.valueOf(oRset.getInt(1)));
                    grupoPromoTO.setTipoPromocion(oRset.getString(2));
                    grupoPromoTO.setEstatus(oRset.getString(3));
                    grupoPromoTO.setGrupoPromocion(oRset.getString(4));
                    grupoPromoTO.setDescuento(String.valueOf(oRset.getFloat(5)));
                    grupoPromoTO.setDescuentoValorAlto(String.valueOf(oRset.getInt(6)));
                    listaGposPromociones.add(grupoPromoTO);
                }
            }
            catch (Exception e) {
                throw new Exception("Consulta Linea [" + e.toString() + "]");
            }
        }
        finally {
            if (oRset != null) {
                try {
                    oRset.close();
                    oRset = null;
                }
                catch (Exception var9_13) {}
            }
            if (oStmt != null) {
                try {
                    oStmt.close();
                    oStmt = null;
                }
                catch (Exception var9_14) {}
            }
            if (oConn != null) {
                try {
                    oConn.close();
                    oConn = null;
                }
                catch (Exception var9_15) {}
            }
        }
        return listaGposPromociones;
    }

    public ArrayList<PromocionTO> consultaPromociones(PromocionTO promocionTO) throws Exception {
        ArrayList<PromocionTO> listaPromociones;
        Connection oConn = null;
        PreparedStatement oStmt = null;
        ResultSet oRset = null;
        listaPromociones = new ArrayList<PromocionTO>();
        PromocionTO promocionesTO = null;
        AreaPromocionTO areaPromoTO = null;
        try {
            try {
                oConn = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                StringBuffer query = new StringBuffer();
                query.append("SELECT A.IDPRODUCTO,A.IDREGION,A.IDGRUPOPROMOCION,A.DESCRIPCION,A.TIPOPRODUCTO,A.PRECIOLISTA,");
                query.append("A.PRECIOACTIVACION,A.MARCA,A.MODELO,A.URL,A.TECNOLOGIA,A.ESTATUS,A.BANSISACT,A.ADENDUM,A.FZAVENTAS,");
                query.append("A.VALORPUNTOS,A.INDICADOR,B.IDAREAPROMOCION, B.DESCRIPCION ");
                query.append("FROM ").append(this.schema_database).append("PTO_CTLPROMOCIONES A, ").append(this.schema_database).append("PTO_CTLAREASPROMOCION B ");
                query.append("WHERE A.IDAREAPROMOCION = B.IDAREAPROMOCION (+) ");
                query.append("AND A.ESTATUS=? AND A.IDREGION=? AND A.TIPOPRODUCTO=? ");
                if (promocionTO.getIdProducto() != null && promocionTO.getIdProducto().trim().length() > 0) {
                    query.append("AND A.IDPRODUCTO=? ");
                }
                if (promocionTO.getIdGrupoPromocion() != 0) {
                    query.append("AND A.IDGRUPOPROMOCION=?");
                }
                if (promocionTO.getAreaPromocion().getIdAreaPromocion() != 0) {
                    query.append("AND A.IDAREAPROMOCION=?");
                }
                oStmt = oConn.prepareStatement(query.toString());
                oStmt.setString(1, promocionTO.getEstatus());
                oStmt.setInt(2, promocionTO.getIdRegion());
                oStmt.setString(3, promocionTO.getTipoProducto());
                int i = 4;
                if (promocionTO.getIdProducto() != null && promocionTO.getIdProducto().trim().length() > 0) {
                    oStmt.setString(i, promocionTO.getIdProducto());
                    ++i;
                }
                if (promocionTO.getIdGrupoPromocion() != 0) {
                    oStmt.setInt(i, promocionTO.getIdGrupoPromocion());
                    ++i;
                }
                if (promocionTO.getAreaPromocion().getIdAreaPromocion() != 0) {
                    oStmt.setInt(i, promocionTO.getAreaPromocion().getIdAreaPromocion());
                }
                oRset = oStmt.executeQuery();
                while (oRset.next()) {
                    promocionesTO = new PromocionTO();
                    areaPromoTO = new AreaPromocionTO();
                    promocionesTO.setIdProducto(oRset.getString(1));
                    promocionesTO.setIdRegion(oRset.getInt(2));
                    promocionesTO.setIdGrupoPromocion(oRset.getInt(3));
                    promocionesTO.setDescripcion(oRset.getString(4));
                    promocionesTO.setTipoProducto(oRset.getString(5));
                    promocionesTO.setPrecioLista(oRset.getFloat(6));
                    promocionesTO.setPrecioActiva(oRset.getFloat(7));
                    promocionesTO.setMarca(oRset.getString(8));
                    promocionesTO.setModelo(oRset.getString(9));
                    promocionesTO.setURL(oRset.getString(10));
                    promocionesTO.setTecnologia(oRset.getString(11));
                    promocionesTO.setEstatus(oRset.getString(12));
                    promocionesTO.setBanSISACT(oRset.getString(13));
                    promocionesTO.setAddendum(oRset.getInt(14));
                    promocionesTO.setFzaVta(oRset.getString(15));
                    promocionesTO.setValorPtos(oRset.getInt(16));
                    promocionesTO.setIndicador(oRset.getInt(17));
                    areaPromoTO.setIdAreaPromocion(oRset.getInt(18));
                    areaPromoTO.setDescAreaPromocion(oRset.getString(19));
                    promocionesTO.setAreaPromocion(areaPromoTO);
                    listaPromociones.add(promocionesTO);
                }
            }
            catch (Exception e) {
                throw new Exception("Consulta Promociones[" + e.toString() + "]");
            }
        }
        finally {
            if (oRset != null) {
                try {
                    oRset.close();
                    oRset = null;
                }
                catch (Exception var11_15) {}
            }
            if (oStmt != null) {
                try {
                    oStmt.close();
                    oStmt = null;
                }
                catch (Exception var11_16) {}
            }
            if (oConn != null) {
                try {
                    oConn.close();
                    oConn = null;
                }
                catch (Exception var11_17) {}
            }
        }
        return listaPromociones;
    }

    public ArrayList<PlanTO> consultaPlanes(PlanTO planTO) throws Exception {
        ArrayList<PlanTO> listaPlanes;
        Connection oConn = null;
        PreparedStatement oStmt = null;
        ResultSet oRset = null;
        listaPlanes = new ArrayList<PlanTO>();
        try {
            try {
                oConn = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                StringBuffer query = new StringBuffer();
                query.append("SELECT IDPLAN,IDSEGMENTO,IDREGION,IDGRUPOPROMOCION,DESCRIPCION,TECNOLOGIA,BMIXTO,");
                query.append("MODALIDAD,BSISACT,ADENDUM,RENTA,BREDENCION,ESTATUS,BREDENCIONANTC ");
                query.append(",TIPO_PLAN ");
                query.append("FROM ").append(this.schema_database).append("PTO_CTLPLANESTARIFARIOS ");
                query.append("WHERE IDREGION=? ");
                if (!planTO.getEstatus().equals("T")) {
                    query.append("AND ESTATUS=? ");
                }
                if (planTO.getIdPlanNuevo() != null && planTO.getIdPlanNuevo().trim().length() > 0) {
                    query.append("AND IDPLAN =? ");
                }
                query.append("order by IDPLAN");
                oStmt = oConn.prepareStatement(query.toString());
                oStmt.setInt(1, planTO.getIdRegion());
                if (!planTO.getEstatus().equals("T")) {
                    oStmt.setString(2, planTO.getEstatus());
                }
                if (planTO.getIdPlanNuevo() != null && planTO.getIdPlanNuevo().trim().length() > 0) {
                    oStmt.setString(3, planTO.getIdPlanNuevo().trim());
                }
                oRset = oStmt.executeQuery();
                while (oRset.next()) {
                    PlanTO planesTO = new PlanTO();
                    planesTO.setIdPlanNuevo(oRset.getString(1));
                    planesTO.setSegmento(oRset.getInt(2));
                    planesTO.setIdRegion(oRset.getInt(3));
                    planesTO.setIdGrupoPromocion(oRset.getInt(4));
                    planesTO.setDescripcion(oRset.getString(5));
                    planesTO.setTecnologia(oRset.getString(6));
                    planesTO.setBanMixto(oRset.getString(7));
                    planesTO.setModalidad(oRset.getString(8));
                    planesTO.setBanSisact(oRset.getString(9));
                    planesTO.setAdendum(oRset.getInt(10));
                    planesTO.setRenta(oRset.getInt(11));
                    planesTO.setBanRedencion(oRset.getInt(12));
                    planesTO.setEstatus(oRset.getString(13));
                    planesTO.setBanRedencionAnct(oRset.getString(14));
                    planesTO.setTipoPlan(oRset.getString(15));
                    listaPlanes.add(planesTO);
                }
            }
            catch (Exception e) {
                throw new Exception("Consulta Linea [" + e.toString() + "]");
            }
        }
        finally {
            if (oRset != null) {
                try {
                    oRset.close();
                    oRset = null;
                }
                catch (Exception var9_13) {}
            }
            if (oStmt != null) {
                try {
                    oStmt.close();
                    oStmt = null;
                }
                catch (Exception var9_14) {}
            }
            if (oConn != null) {
                try {
                    oConn.close();
                    oConn = null;
                }
                catch (Exception var9_15) {}
            }
        }
        return listaPlanes;
    }

    public ArrayList<FuerzaVentasTO> consultaFuerzaVentas(FuerzaVentasTO fuerzaVentasTO) throws Exception {
        ArrayList<FuerzaVentasTO> listaFuerzasVentas;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        listaFuerzasVentas = new ArrayList<FuerzaVentasTO>();
        try {
            try {
                connection = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                StringBuffer query = new StringBuffer();
                query.append("SELECT FZAVENTAS, PLAN_VISIBLE, ESTATUS, DESCRIPCION ");
                query.append("FROM ").append(this.schema_database).append("PTO_CTLFZA_VENTAS ");
                query.append("WHERE ");
                if (fuerzaVentasTO.getIdFuerzaVenta() != null && fuerzaVentasTO.getIdFuerzaVenta().trim().length() > 0) {
                    query.append("FZAVENTAS=? ");
                    query.append("AND ESTATUS=? ");
                } else {
                    query.append("ESTATUS=? ");
                }
                preparedStatement = connection.prepareStatement(query.toString());
                if (fuerzaVentasTO.getIdFuerzaVenta() != null && fuerzaVentasTO.getIdFuerzaVenta().trim().length() > 0) {
                    preparedStatement.setString(1, fuerzaVentasTO.getIdFuerzaVenta());
                    preparedStatement.setString(2, fuerzaVentasTO.getEstatus());
                } else {
                    preparedStatement.setString(1, fuerzaVentasTO.getEstatus());
                }
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    FuerzaVentasTO fzaVentasTO = new FuerzaVentasTO();
                    fzaVentasTO.setIdFuerzaVenta(resultSet.getString(1));
                    fzaVentasTO.setPlanVisible(resultSet.getString(2));
                    fzaVentasTO.setEstatus(resultSet.getString(3));
                    fzaVentasTO.setDescripcion(resultSet.getString(4));
                    listaFuerzasVentas.add(fzaVentasTO);
                }
            }
            catch (Exception e) {
                throw new Exception("Consulta Fuerza Venta [" + e.toString() + "]");
            }
        }
        finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                    resultSet = null;
                }
                catch (Exception var9_13) {}
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                    preparedStatement = null;
                }
                catch (Exception var9_14) {}
            }
            if (connection != null) {
                try {
                    connection.close();
                    connection = null;
                }
                catch (Exception var9_15) {}
            }
        }
        return listaFuerzasVentas;
    }

    public byte[] generaReporte(InputStream archivo, HashMap<String, String> mapa) throws Exception {
        byte[] resultado;
        resultado = null;
        Connection oConn = null;
        try {
            try {
                oConn = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                resultado = JasperRunManager.runReportToPdf((InputStream)archivo, mapa, (Connection)oConn);
            }
            catch (Exception ex) {
                throw new Exception("Genera Reporte [" + ex.toString() + "]");
            }
        }
        finally {
            if (oConn != null) {
                try {
                    oConn.close();
                    oConn = null;
                }
                catch (Exception var7_8) {}
            }
        }
        return resultado;
    }

    public byte[] generaReporteCSV(InputStream archivo, HashMap<String, String> mapa) throws Exception {
        byte[] resultado;
        resultado = null;
        Connection oConn = null;
        try {
            try {
                oConn = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                JasperPrint jasperPrint = JasperFillManager.fillReport((InputStream)archivo, mapa, (Connection)oConn);
                JRCsvExporter exporter = new JRCsvExporter();
                exporter.setParameter(JRExporterParameter.JASPER_PRINT, (Object)jasperPrint);
                ByteArrayOutputStream csvReport = new ByteArrayOutputStream();
                exporter.setParameter(JRExporterParameter.JASPER_PRINT, (Object)jasperPrint);
                exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, (Object)csvReport);
                exporter.setParameter((JRExporterParameter)JRCsvExporterParameter.FIELD_DELIMITER, (Object)"\t");
                exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, (Object)"REPORTE.csv");
                exporter.exportReport();
                resultado = csvReport.toByteArray();
            }
            catch (Exception ex) {
                throw new Exception("Genera Reporte [" + ex.toString() + "]");
            }
        }
        finally {
            if (oConn != null) {
                try {
                    oConn.close();
                    oConn = null;
                }
                catch (Exception var9_11) {}
            }
        }
        return resultado;
    }

    public ArrayList<PromocionTO> consultaPromocionesDistribuidores(PromocionTO promocionTO, String idPlan, int idGpoPromocion) throws Exception {
        ArrayList<PromocionTO> listaPromociones;
        Connection oConn = null;
        PreparedStatement oStmt = null;
        ResultSet oRset = null;
        listaPromociones = new ArrayList<PromocionTO>();
        try {
            try {
                oConn = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                StringBuffer query = new StringBuffer();
                query.append("SELECT a.IdProducto, a.Descripcion , a.Marca, A.Modelo , TO_BINARY_FLOAT(a.PrecioLista) AS Preciolista, TO_BINARY_FLOAT(a.PrecioActivacion) as PrecioActivacion, c.TipoPromocion, a.Indicador, a.FZAVENTAS, b.IdPlan  ");
                query.append("FROM ").append(this.schema_database).append("PTO_CTLPROMOCIONES a, ").append(this.schema_database).append("PTO_CTLPLANESTARIFARIOS b , ").append(this.schema_database).append("PTO_CTLGRUPOPROMOCION c ");
                query.append("WHERE c.IDGRUPOPROMOCION = b.IDGRUPOPROMOCION   ");
                query.append("AND A.IDGRUPOPROMOCION = B.IDGRUPOPROMOCION ");
                query.append("AND a.adendum = b.adendum  ");
                query.append("AND b.IdRegion = a.IdRegion   ");
                query.append("AND a.Tecnologia = 'GSM' ");
                query.append("AND a.Bansisact = 'S'   ");
                query.append("AND a.estatus <> 'I' AND b.estatus <> 'I' ");
                query.append("AND a.IdRegion = ?  ");
                if (!(idPlan == null || idPlan.equals(""))) {
                    query.append("AND b.IdPlan = ? ");
                    query.append("AND c.IDGRUPOPROMOCION = ? ");
                }
                if (!(promocionTO.getMarca() == null || promocionTO.getMarca().equals(""))) {
                    query.append("AND a.Marca = ?  ");
                }
                if (!(promocionTO.getModelo() == null || promocionTO.getModelo().equals(""))) {
                    query.append("AND a.Modelo = ?  ");
                }
                if (!(promocionTO.getFzaVta() == null || promocionTO.getFzaVta().equals(""))) {
                    query.append("AND a.FZAVENTAS IN ('" + promocionTO.getFzaVta() + "','TODOS')  ");
                } else {
                    query.append("AND a.FZAVENTAS = 'TODOS'  ");
                }
                query.append("ORDER BY a.Marca, a.Modelo, a.PrecioLista , a.PrecioActivacion DESC, a.Tecnologia ");
                int i = 2;
                oStmt = oConn.prepareStatement(query.toString());
                oStmt.setInt(1, promocionTO.getIdRegion());
                if (!(idPlan == null || idPlan.equals(""))) {
                    oStmt.setString(i, idPlan);
                    oStmt.setInt(++i, idGpoPromocion);
                    ++i;
                }
                if (!(promocionTO.getMarca() == null || promocionTO.getMarca().equals(""))) {
                    oStmt.setString(i, promocionTO.getMarca());
                    ++i;
                }
                if (!(promocionTO.getModelo() == null || promocionTO.getModelo().equals(""))) {
                    oStmt.setString(i, promocionTO.getModelo());
                    ++i;
                }
                oRset = oStmt.executeQuery();
                while (oRset.next()) {
                    PromocionTO promocionesTO = new PromocionTO();
                    promocionesTO.setIdProducto(oRset.getString("IdProducto"));
                    promocionesTO.setDescripcion(oRset.getString("Descripcion"));
                    promocionesTO.setTipoPromocion(oRset.getString("TipoPromocion"));
                    promocionesTO.setPrecioLista(oRset.getFloat("PrecioLista"));
                    promocionesTO.setPrecioActiva(oRset.getFloat("PrecioActivacion"));
                    promocionesTO.setMarca(oRset.getString("Marca"));
                    promocionesTO.setModelo(oRset.getString("Modelo"));
                    promocionesTO.setIndicador(oRset.getInt("Indicador"));
                    promocionesTO.setFzaVta(oRset.getString("FZAVENTAS"));
                    promocionesTO.setTipoProducto(oRset.getString("IdPlan"));
                    listaPromociones.add(promocionesTO);
                }
            }
            catch (Exception e) {
                throw new Exception("consultaPromocionesDistribuidores [" + e.toString() + "]");
            }
        }
        finally {
            if (oRset != null) {
                try {
                    oRset.close();
                    oRset = null;
                }
                catch (Exception var12_16) {}
            }
            if (oStmt != null) {
                try {
                    oStmt.close();
                    oStmt = null;
                }
                catch (Exception var12_17) {}
            }
            if (oConn != null) {
                try {
                    oConn.close();
                    oConn = null;
                }
                catch (Exception var12_18) {}
            }
        }
        return listaPromociones;
    }

    public ArrayList<ProductosSmsTO> consultaPromocionesSms(ProductosSmsTO productosSmsTO) throws Exception {
        ArrayList<ProductosSmsTO> listaPromocionesSms;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        listaPromocionesSms = new ArrayList<ProductosSmsTO>();
        try {
            try {
                connection = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                StringBuffer query = new StringBuffer();
                query.append(" SELECT CLAVEM2K,CLAVESMS,IDPRODUCTO,DESCRIPCION,TIPOPRODUCTO,VALORPUNTOS ");
                query.append("FROM ").append(this.schema_database).append("PTO_CTLPROMOCIONES_SMS ");
                query.append("WHERE 1=1 ");
                if (productosSmsTO.getClaveSms() != null && productosSmsTO.getClaveSms().trim().length() > 0) {
                    query.append(" AND CLAVESMS= '" + productosSmsTO.getClaveSms().trim().toUpperCase() + "'");
                }
                if (productosSmsTO.getTipoProducto() != null && productosSmsTO.getTipoProducto().trim().length() > 0) {
                    query.append(" AND TIPOPRODUCTO =  '" + productosSmsTO.getTipoProducto().trim() + "'");
                }
                query.append(" order by TIPOPRODUCTO,IDPRODUCTO ");
                preparedStatement = connection.prepareStatement(query.toString());
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    ProductosSmsTO proSmsTO = new ProductosSmsTO();
                    proSmsTO.setClaveM2k(resultSet.getString(1));
                    proSmsTO.setClaveSms(resultSet.getString(2));
                    proSmsTO.setIdProducto(resultSet.getString(3));
                    proSmsTO.setDescripcion(resultSet.getString(4));
                    proSmsTO.setTipoProducto(resultSet.getString(5));
                    proSmsTO.setValorPuntos(resultSet.getInt(6));
                    listaPromocionesSms.add(proSmsTO);
                }
            }
            catch (Exception e) {
                throw new Exception("Consulta promociones Sms [" + e.toString() + "]");
            }
        }
        finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                    resultSet = null;
                }
                catch (Exception var9_13) {}
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                    preparedStatement = null;
                }
                catch (Exception var9_14) {}
            }
            if (connection != null) {
                try {
                    connection.close();
                    connection = null;
                }
                catch (Exception var9_15) {}
            }
        }
        return listaPromocionesSms;
    }

    public ArrayList<CteExcelenteTO> consultaClientExcelente(CteExcelenteTO cteExcelenteTO) throws Exception {
        ArrayList<CteExcelenteTO> listaCteExcelente;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        listaCteExcelente = new ArrayList<CteExcelenteTO>();
        try {
            try {
                connection = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                StringBuffer query = new StringBuffer();
                query.append("SELECT CUENTA, LINEA, IDREGION, ESTATUS ");
                query.append("FROM ").append(this.schema_database).append("PTO_TBLLINEASCTEEXC ");
                query.append("WHERE  ");
                if (cteExcelenteTO.getCuenta() != null && cteExcelenteTO.getCuenta().trim().length() > 0) {
                    query.append(" CUENTA=? ");
                    query.append(" AND IDREGION=? ");
                    query.append(" AND ESTATUS=? ");
                } else {
                    query.append(" IDREGION=? ");
                    query.append(" AND ESTATUS=? ");
                }
                preparedStatement = connection.prepareStatement(query.toString());
                if (cteExcelenteTO.getCuenta() != null && cteExcelenteTO.getCuenta().trim().length() > 0) {
                    preparedStatement.setString(1, cteExcelenteTO.getCuenta());
                    preparedStatement.setInt(2, cteExcelenteTO.getIdRegion());
                    preparedStatement.setString(3, cteExcelenteTO.getEstatus());
                } else {
                    preparedStatement.setInt(1, cteExcelenteTO.getIdRegion());
                    preparedStatement.setString(2, cteExcelenteTO.getEstatus());
                }
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    CteExcelenteTO excelenteTO = new CteExcelenteTO();
                    excelenteTO.setCuenta(resultSet.getString(1));
                    excelenteTO.setLinea(resultSet.getString(2));
                    excelenteTO.setIdRegion(Integer.valueOf(resultSet.getInt(3)));
                    excelenteTO.setEstatus(resultSet.getString(4));
                    listaCteExcelente.add(excelenteTO);
                }
            }
            catch (Exception e) {
                throw new Exception("Consulta Cuenta Cliente Excelente [" + e.toString() + "]");
            }
        }
        finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                    resultSet = null;
                }
                catch (Exception var9_13) {}
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                    preparedStatement = null;
                }
                catch (Exception var9_14) {}
            }
            if (connection != null) {
                try {
                    connection.close();
                    connection = null;
                }
                catch (Exception var9_15) {}
            }
        }
        return listaCteExcelente;
    }
}

