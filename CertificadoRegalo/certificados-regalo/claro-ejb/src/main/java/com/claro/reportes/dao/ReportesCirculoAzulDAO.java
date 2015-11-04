/*
 * Decompiled with CFR 0_102.
 * 
 * Could not load the following classes:
 *  com.claro.exception.CAException
 *  com.claro.transfer.inbursa.InbursaTO
 *  com.claro.transfer.reportes.ActivacionesReporteTO
 *  com.claro.transfer.reportes.AcumuladosTO
 *  com.claro.transfer.reportes.AlianzaAmexTO
 *  com.claro.transfer.reportes.AlianzaMexicanaTO
 *  com.claro.transfer.reportes.AltoValorTO
 *  com.claro.transfer.reportes.AmigoKitReporteTO
 *  com.claro.transfer.reportes.CertificadoLealtadTO
 *  com.claro.transfer.reportes.ComisionTO
 *  com.claro.transfer.reportes.PuntosVencerTO
 *  com.claro.transfer.reportes.RedencionDetalleTO
 *  com.claro.transfer.reportes.RedencionEntregaTO
 *  com.claro.transfer.reportes.RedencionLineaTO
 *  com.claro.transfer.reportes.RedencionReporteTO
 *  com.claro.transfer.reportes.RedencionesOnlineTO
 *  com.claro.transfer.reportes.Reportable
 *  com.claro.transfer.reportes.RetencionReporteTO
 *  com.claro.transfer.reportes.RoextTO
 *  com.claro.transfer.reportes.Tarjetas3GReporteTO
 *  com.claro.transfer.reportes.TiempoAireReporteTO
 *  com.claro.util.ServiceLocator
 *  com.claro.util.Utils
 *  org.apache.log4j.Logger
 */
package com.claro.reportes.dao;

import com.claro.exception.CAException;
import com.claro.transfer.inbursa.InbursaTO;
import com.claro.transfer.reportes.ActivacionesReporteTO;
import com.claro.transfer.reportes.AcumuladosTO;
import com.claro.transfer.reportes.AlianzaAmexTO;
import com.claro.transfer.reportes.AlianzaMexicanaTO;
import com.claro.transfer.reportes.AltoValorTO;
import com.claro.transfer.reportes.AmigoKitReporteTO;
import com.claro.transfer.reportes.CertificadoLealtadTO;
import com.claro.transfer.reportes.ComisionTO;
import com.claro.transfer.reportes.PuntosVencerTO;
import com.claro.transfer.reportes.RedencionDetalleTO;
import com.claro.transfer.reportes.RedencionEntregaTO;
import com.claro.transfer.reportes.RedencionLineaTO;
import com.claro.transfer.reportes.RedencionReporteTO;
import com.claro.transfer.reportes.RedencionesOnlineTO;
import com.claro.transfer.reportes.Reportable;
import com.claro.transfer.reportes.RetencionReporteTO;
import com.claro.transfer.reportes.RoextTO;
import com.claro.transfer.reportes.Tarjetas3GReporteTO;
import com.claro.transfer.reportes.TiempoAireReporteTO;
import com.claro.util.ServiceLocator;
import com.claro.util.Utils;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;

public class ReportesCirculoAzulDAO {
    protected final Logger error = Logger.getLogger((String)"loggerCirculoAzulError");
    protected final Logger logger = Logger.getLogger((String)"loggerCirculoAzul");
    private String schema_database;

    public ReportesCirculoAzulDAO() {
        try {
            this.schema_database = ServiceLocator.getInstance().getVariable(ServiceLocator.schema_database);
        }
        catch (Exception e) {
            this.error.error((Object)"ReportesCirculoAzulDAO", (Throwable)e);
        }
    }

    public List<CertificadoLealtadTO> getCertificadosLealtad(String fechaInicial, String fechaFinal, int idRegion) throws CAException {
        ArrayList<CertificadoLealtadTO> certificados;
        Connection cnx = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        certificados = new ArrayList<CertificadoLealtadTO>();
        try {
            try {
                cnx = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                StringBuffer query = new StringBuffer();
                query.append(" SELECT FOLIO,CUENTA,LINEA,IDUSUARIO, TO_CHAR(FECHAOPER,'YYYY-MM-DD') FECHAOPER, ");
                query.append("TO_CHAR(FCADUCA,'YYYY-MM-DD') FCADUCA , IDREGION,IDPUNTOVTA,SEGMENTO, ");
                query.append(" IDPLAN,MESESANTIG,PORCANTIG,VCERTIF,VCENTIFEXTRA,MOTIVO,ESTATUS ");
                query.append(" FROM ").append(this.schema_database).append("PTO_TBLCERTIFICADOS ");
                query.append(" WHERE FECHAOPER between to_timestamp(?,'DD/MM/YYYY') ");
                query.append(" AND to_timestamp(?,'DD/MM/YYYY') ");
                if (idRegion > 0) {
                    query.append(" AND IDREGION=").append(idRegion);
                }
                query.append(" ORDER BY FECHAOPER ");
                stmt = cnx.prepareStatement(query.toString());
                stmt.setString(1, fechaInicial);
                stmt.setString(2, fechaFinal);
                rs = stmt.executeQuery();
                while (rs.next()) {
                    CertificadoLealtadTO certificadoLealtadTO = new CertificadoLealtadTO();
                    certificadoLealtadTO.setFolio(rs.getString("FOLIO"));
                    certificadoLealtadTO.setCuenta(rs.getString("CUENTA"));
                    certificadoLealtadTO.setLinea(rs.getString("LINEA"));
                    certificadoLealtadTO.setIdUsuario(rs.getString("IDUSUARIO"));
                    certificadoLealtadTO.setFechaOperacion(rs.getString("FECHAOPER"));
                    certificadoLealtadTO.setFechaVencimiento(rs.getString("FCADUCA"));
                    certificadoLealtadTO.setIdRegion(rs.getString("IDREGION"));
                    certificadoLealtadTO.setIdPuntoVta(rs.getString("IDPUNTOVTA"));
                    certificadoLealtadTO.setSegmento(rs.getString("SEGMENTO"));
                    certificadoLealtadTO.setIdPlan(rs.getString("IDPLAN"));
                    certificadoLealtadTO.setMesesAntig(rs.getString("MESESANTIG"));
                    certificadoLealtadTO.setPorcAntig(rs.getString("PORCANTIG"));
                    certificadoLealtadTO.setCertif(rs.getString("VCERTIF"));
                    certificadoLealtadTO.setCertifExtra(rs.getString("VCENTIFEXTRA"));
                    certificadoLealtadTO.setMotivo(rs.getString("MOTIVO"));
                    certificadoLealtadTO.setEstatus(rs.getString("ESTATUS"));
                    if (certificadoLealtadTO.getIdPuntoVta().equals("VILLA COAPA")) {
                        certificadoLealtadTO.setIdPuntoVta("COAPA I");
                    } else if (certificadoLealtadTO.getIdPuntoVta().equals("VILLA COAPA 2")) {
                        certificadoLealtadTO.setIdPuntoVta("PLAZA COAPA");
                    } else if (certificadoLealtadTO.getIdPuntoVta().equals("SATELITE")) {
                        certificadoLealtadTO.setIdPuntoVta("MUNDO E");
                    }
                    certificados.add(certificadoLealtadTO);
                }
            }
            catch (Exception e) {
                this.error.info((Object)"Exception.getCertificadosLealtad:", (Throwable)e);
                throw new CAException(-1, "ReportesCirculoAzulDAO.getCertificadosLealtad[" + e.toString() + "]", e);
            }
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                    rs = null;
                }
                catch (Exception var11_15) {}
            }
            if (stmt != null) {
                try {
                    stmt.close();
                    stmt = null;
                }
                catch (Exception var11_16) {}
            }
            if (cnx != null) {
                try {
                    cnx.close();
                }
                catch (Exception var11_17) {}
            }
        }
        return certificados;
    }

    public List<RedencionReporteTO> getRedenciones(String fechaInicial, String fechaFinal, int idRegion) throws CAException {
        ArrayList<RedencionReporteTO> redenciones;
        Connection cnx = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        redenciones = new ArrayList<RedencionReporteTO>();
        try {
            try {
                cnx = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                StringBuffer query = new StringBuffer();
                query.append(" Select a.marca, a.modelo, a.IDPRODUCTO, a.TIPOREDEN, ");
                query.append(" a.ESTATUS, sum(a.valorptos), sum(a.difpesos), count(1) ");
                query.append(" from ").append(this.schema_database).append("pto_tblredencion a ");
                query.append(" where a.estatus = 'A' AND ");
                query.append(" a.FECHAOPER between to_timestamp(?,'DD/MM/YYYY') AND ");
                query.append(" to_timestamp(?,'DD/MM/YYYY') AND ");
                if (idRegion > 0) {
                    query.append(" a.IDREGION=").append(idRegion).append(" AND ");
                }
                query.append(" a.tiporeden  IN ('C','S') ");
                query.append(" group by a.marca, a.modelo, a.IDPRODUCTO, a.TIPOREDEN, a.ESTATUS ");
                query.append(" order by a.marca, a.modelo, a.IDPRODUCTO ");
                stmt = cnx.prepareStatement(query.toString());
                stmt.setString(1, fechaInicial);
                stmt.setString(2, fechaFinal);
                rs = stmt.executeQuery();
                while (rs.next()) {
                    RedencionReporteTO redencionReporteTO = new RedencionReporteTO();
                    redencionReporteTO.setMarca(rs.getString(1));
                    redencionReporteTO.setModelo(rs.getString(2));
                    redencionReporteTO.setIdProducto(rs.getString(3));
                    redencionReporteTO.setTipoRedencion(rs.getString(4));
                    redencionReporteTO.setEstatus(rs.getString(5));
                    redencionReporteTO.setTotalPuntos(rs.getString(6));
                    redencionReporteTO.setTotalPesos(rs.getString(7));
                    redencionReporteTO.setTotalEquipos(rs.getString(8));
                    redenciones.add(redencionReporteTO);
                }
            }
            catch (Exception e) {
                this.error.info((Object)"Exception.getRedenciones:", (Throwable)e);
                throw new CAException(-1, "ReportesCirculoAzulDAO.getRedenciones[" + e.toString() + "]", e);
            }
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                    rs = null;
                }
                catch (Exception var11_15) {}
            }
            if (stmt != null) {
                try {
                    stmt.close();
                    stmt = null;
                }
                catch (Exception var11_16) {}
            }
            if (cnx != null) {
                try {
                    cnx.close();
                }
                catch (Exception var11_17) {}
            }
        }
        return redenciones;
    }

    public List<RedencionDetalleTO> getRedencionesDetalle(String fechaInicial, String fechaFinal, int idRegion) throws CAException {
        ArrayList<RedencionDetalleTO> redenciones;
        Connection cnx = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        redenciones = new ArrayList<RedencionDetalleTO>();
        try {
            try {
                cnx = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                StringBuffer query = new StringBuffer();
                query.append("SELECT B.LINEA, B.PLAN, A.CUENTA, A.MARCA, A.MODELO, A.VALORPTOS, A.TIPOREDEN, A.IDPRODUCTO, A.IDREGION, ");
                query.append("A.IDPUNTOVTA, A.FECHAOPER, A.ESTATUS ");
                query.append("FROM ").append(this.schema_database).append("PTO_TBLREDENCION A, ");
                query.append(this.schema_database).append("PTO_TBLLINEAS B ");
                query.append(" where A.CUENTA = B.CUENTA AND A.FECHAOPER between to_timestamp(?,'DD/MM/YYYY') AND ");
                query.append(" to_timestamp(?,'DD/MM/YYYY')");
                if (idRegion > 0) {
                    query.append(" AND A.IDREGION=").append(idRegion);
                }
                stmt = cnx.prepareStatement(query.toString());
                stmt.setString(1, fechaInicial);
                stmt.setString(2, fechaFinal);
                rs = stmt.executeQuery();
                while (rs.next()) {
                    RedencionDetalleTO redencionDetalleTO = new RedencionDetalleTO();
                    redencionDetalleTO.setLinea(rs.getString("LINEA"));
                    redencionDetalleTO.setPlan(rs.getString("PLAN"));
                    redencionDetalleTO.setCuenta(rs.getString("CUENTA"));
                    redencionDetalleTO.setMarca(rs.getString("MARCA"));
                    redencionDetalleTO.setModelo(rs.getString("MODELO"));
                    redencionDetalleTO.setValorPuntos(String.valueOf(rs.getInt("VALORPTOS")));
                    redencionDetalleTO.setTipoRedencion(rs.getString("TIPOREDEN"));
                    redencionDetalleTO.setIdProducto(rs.getString("IDPRODUCTO"));
                    redencionDetalleTO.setIdRegion(String.valueOf(rs.getString("IDREGION")));
                    redencionDetalleTO.setIdPuntoVenta(rs.getString("IDPUNTOVTA"));
                    redencionDetalleTO.setFechaOperacion(String.valueOf(rs.getDate("FECHAOPER")));
                    redencionDetalleTO.setEstatus(rs.getString("ESTATUS"));
                    redenciones.add(redencionDetalleTO);
                }
            }
            catch (Exception e) {
                this.error.info((Object)"Exception.getRedencionesDetalle:", (Throwable)e);
                throw new CAException(-1, "ReportesCirculoAzulDAO.getRedencionesDetalle[" + e.toString() + "]", e);
            }
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                    rs = null;
                }
                catch (Exception var11_15) {}
            }
            if (stmt != null) {
                try {
                    stmt.close();
                    stmt = null;
                }
                catch (Exception var11_16) {}
            }
            if (cnx != null) {
                try {
                    cnx.close();
                }
                catch (Exception var11_17) {}
            }
        }
        return redenciones;
    }

    public List<RoextTO> getRoext(String fechaInicial, String fechaFinal, int idRegion) throws CAException {
        ArrayList<RoextTO> roext;
        Connection cnx = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        roext = new ArrayList<RoextTO>();
        try {
            try {
                cnx = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                StringBuffer query = new StringBuffer();
                query.append(" SELECT FOLIO,IDREGION,DESCUENTO,PRECIO,PRECIOIVA,");
                query.append(" TO_CHAR(FECHAOPER,'YYYY-MM-DD'),IDPRODUCTO,MARCA,MODELO,VALORPTOS,");
                query.append(" BONOROEXT,IDPUNTOVTA ");
                query.append(" FROM ").append(this.schema_database).append("PTO_TBLREDENCION ");
                query.append(" WHERE BONOROEXT <> 0 AND DESCUENTO > 0 AND ");
                query.append(" FECHAOPER between to_timestamp(?,'DD/MM/YYYY') AND ");
                query.append(" to_timestamp(?,'DD/MM/YYYY') AND ");
                if (idRegion > 0) {
                    query.append(" IDREGION=").append(idRegion).append(" AND ");
                }
                query.append(" ESTATUS ='A'");
                stmt = cnx.prepareStatement(query.toString());
                stmt.setString(1, fechaInicial);
                stmt.setString(2, fechaFinal);
                rs = stmt.executeQuery();
                while (rs.next()) {
                    RoextTO roextTO = new RoextTO();
                    roextTO.setFolio(rs.getString(1));
                    roextTO.setRegion(rs.getString(2));
                    roextTO.setDescuento(rs.getString(3));
                    roextTO.setPrecio(rs.getString(4));
                    roextTO.setPrecioIva(rs.getString(5));
                    roextTO.setFechaOperacion(rs.getString(6));
                    roextTO.setMaterial(rs.getString(7));
                    roextTO.setMarca(rs.getString(8));
                    roextTO.setModelo(rs.getString(9));
                    roextTO.setPuntos(rs.getString(10));
                    roextTO.setBonoRoext(rs.getString(11));
                    roextTO.setPuntoVenta(rs.getString(12));
                    roext.add(roextTO);
                }
            }
            catch (Exception e) {
                this.error.info((Object)"Exception.getRoext:", (Throwable)e);
                throw new CAException(-1, "ReportesCirculoAzulDAO.getRoext[" + e.toString() + "]", e);
            }
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                    rs = null;
                }
                catch (Exception var11_15) {}
            }
            if (stmt != null) {
                try {
                    stmt.close();
                    stmt = null;
                }
                catch (Exception var11_16) {}
            }
            if (cnx != null) {
                try {
                    cnx.close();
                }
                catch (Exception var11_17) {}
            }
        }
        return roext;
    }

    public List<ComisionTO> getComisiones(String fechaInicial, String fechaFinal, int idRegion) throws CAException {
        ArrayList<ComisionTO> comisiones;
        Connection cnx = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        comisiones = new ArrayList<ComisionTO>();
        try {
            try {
                cnx = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                StringBuffer query = new StringBuffer();
                query.append(" SELECT A.STATUS, A.FZAVTA, A.LINEA, A.CUENTA, A.IDPLAN, A.PLANNVO,");
                query.append(" D.SEGMENTO, A.FOLIO, TO_CHAR(B.FECHAOPER,'YYYY-MM-DD'), A.MARCA, A.MODELO, A.VALORPUNTOS,");
                query.append(" A.TIPOPROM, TO_CHAR(A.FECHAOPER,'YYYY-MM-DD'), A.PRECIO, A.PRECIOIVA ");
                query.append(" FROM ").append(this.schema_database).append("PTO_TBLRESERVACIONES a, ").append(this.schema_database).append("PTO_TBLREDENCION B, ");
                query.append(this.schema_database).append("PTO_CTLPLANESTARIFARIOS C, ").append(this.schema_database).append("PTO_CTLSEGMENTOS d ");
                query.append(" WHERE A.CUENTA = B.CUENTA ");
                query.append("   AND A.SECUENCIA = B.SECUENCIA ");
                query.append("   AND A.FOLIO = B.FOLIO ");
                query.append("   AND A.PLANNVO = C.IDPLAN ");
                query.append("   AND A.IDREGION = C.IDREGION ");
                query.append("   AND D.IDSEGMENTO = C.IDSEGMENTO ");
                query.append("   AND B.ESTATUS ='A' ");
                query.append("   AND A.STATUS = 'R' ");
                if (idRegion > 0) {
                    query.append(" AND A.IDREGION=").append(idRegion);
                }
                query.append("   AND A.IDUSUARIO = 'SISACT' ");
                query.append(" \t AND B.FECHAOPER between to_timestamp(?,'DD/MM/YYYY') AND ");
                query.append("   to_timestamp(?,'DD/MM/YYYY')");
                stmt = cnx.prepareStatement(query.toString());
                stmt.setString(1, fechaInicial);
                stmt.setString(2, fechaFinal);
                rs = stmt.executeQuery();
                while (rs.next()) {
                    ComisionTO comisionTO = new ComisionTO();
                    comisionTO.setEstatus(rs.getString(1));
                    comisionTO.setFuerzaVentas(rs.getString(2));
                    comisionTO.setLinea(rs.getString(3));
                    comisionTO.setCuenta(rs.getString(4));
                    comisionTO.setPlan(rs.getString(5));
                    comisionTO.setPlanNuevo(rs.getString(6));
                    comisionTO.setSegmento(rs.getString(7));
                    comisionTO.setFolio(rs.getString(8));
                    comisionTO.setFechaAdendum(rs.getString(9));
                    comisionTO.setMarca(rs.getString(10));
                    comisionTO.setModelo(rs.getString(11));
                    comisionTO.setPuntos(rs.getString(12));
                    comisionTO.setTipoPromocion(rs.getString(13));
                    comisionTO.setFechaReservacion(rs.getString(14));
                    comisionTO.setPrecio(rs.getString(15));
                    comisionTO.setPrecioIVA(rs.getString(16));
                    comisiones.add(comisionTO);
                }
            }
            catch (Exception e) {
                this.error.info((Object)"Exception.getComisiones:", (Throwable)e);
                throw new CAException(-1, "ReportesCirculoAzulDAO.getComisiones[" + e.toString() + "]", e);
            }
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                    rs = null;
                }
                catch (Exception var11_15) {}
            }
            if (stmt != null) {
                try {
                    stmt.close();
                    stmt = null;
                }
                catch (Exception var11_16) {}
            }
            if (cnx != null) {
                try {
                    cnx.close();
                }
                catch (Exception var11_17) {}
            }
        }
        return comisiones;
    }

    public List<RetencionReporteTO> getRetenciones(String fechaInicial, String fechaFinal, int idRegion) throws CAException {
        ArrayList<RetencionReporteTO> retenciones;
        Connection cnx = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        retenciones = new ArrayList<RetencionReporteTO>();
        try {
            try {
                cnx = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                StringBuffer query = new StringBuffer();
                query.append(" SELECT a.folio, a.vcertif, a.vcentifextra, sum(a.vcertif + a.vcentifextra), ");
                query.append(" to_char(a.fechaoper,'YYYY-MM-DD'), to_char(a.fcaduca,'YYYY-MM-DD'), ");
                query.append(" a.idUsuario, a.idpuntovta, a.estatus ");
                query.append(" FROM ").append(this.schema_database).append("PTO_TBLCERTIFICADOS a ");
                query.append(" WHERE a.FECHAOPER between to_timestamp(?,'DD/MM/YYYY') AND ");
                query.append(" to_timestamp(?,'DD/MM/YYYY')");
                if (idRegion > 0) {
                    query.append(" AND A.IDREGION=").append(idRegion);
                }
                query.append(" GROUP BY a.folio, a.vcertif, a.vcentifextra, a.fechaoper, a.fcaduca, a.idUsuario, a.idpuntovta, a.estatus ");
                query.append(" ORDER BY a.folio ");
                stmt = cnx.prepareStatement(query.toString());
                stmt.setString(1, fechaInicial);
                stmt.setString(2, fechaFinal);
                rs = stmt.executeQuery();
                while (rs.next()) {
                    RetencionReporteTO retencionReporteTO = new RetencionReporteTO();
                    retencionReporteTO.setFolio(rs.getString(1));
                    retencionReporteTO.setValorGeneral(rs.getString(2));
                    retencionReporteTO.setValorExtra(rs.getString(3));
                    retencionReporteTO.setTotal(rs.getString(4));
                    retencionReporteTO.setFechaOperacion(rs.getString(5));
                    retencionReporteTO.setFechaVencimiento(rs.getString(6));
                    retencionReporteTO.setUsuario(rs.getString(7));
                    retencionReporteTO.setPuntoVenta(rs.getString(8));
                    retencionReporteTO.setEstatus(rs.getString(9));
                    retenciones.add(retencionReporteTO);
                }
            }
            catch (Exception e) {
                this.error.info((Object)"Exception.getRetenciones:", (Throwable)e);
                throw new CAException(-1, "ReportesCirculoAzulDAO.getRetenciones[" + e.toString() + "]", e);
            }
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                    rs = null;
                }
                catch (Exception var11_15) {}
            }
            if (stmt != null) {
                try {
                    stmt.close();
                    stmt = null;
                }
                catch (Exception var11_16) {}
            }
            if (cnx != null) {
                try {
                    cnx.close();
                }
                catch (Exception var11_17) {}
            }
        }
        return retenciones;
    }

    public Map<String, List<Reportable>> getAlianzas(String fechaInicial, String fechaFinal, int idRegion) throws CAException {
        HashMap<String, List<Reportable>> alianzas;
        Connection cnx = null;
        alianzas = new HashMap<String, List<Reportable>>();
        List alianzasMexicana = new ArrayList();
        List alianzasAmex = new ArrayList();
        List redencionesEntrega = new ArrayList();
        List redencionesAmigoKit = new ArrayList();
        List redencionesTiempoAire = new ArrayList();
        List redencionesActivaciones = new ArrayList();
        List redencionesTarjetas3G = new ArrayList();
        try {
            try {
                cnx = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                alianzasMexicana = this.getRedencionesMexicana(fechaInicial, fechaFinal, idRegion, cnx);
                alianzas.put("MEXICANA", alianzasMexicana);
                alianzasAmex = this.getRedencionesAmericanExpress(fechaInicial, fechaFinal, idRegion, cnx);
                alianzas.put("AMEX", alianzasAmex);
                redencionesEntrega = this.getRedencionesEntrega(fechaInicial, fechaFinal, cnx);
                alianzas.put("REDENCIONES_ENTREGA", redencionesEntrega);
                redencionesAmigoKit = this.getRedencionesAmigoKit(fechaInicial, fechaFinal, idRegion, cnx);
                alianzas.put("REDENCIONES_AMIGOKIT", redencionesAmigoKit);
                redencionesTiempoAire = this.getRedencionesTiempoAire(fechaInicial, fechaFinal, idRegion, cnx);
                alianzas.put("REDENCIONES_TIEMPOAIRE", redencionesTiempoAire);
                redencionesActivaciones = this.getRedencionesActivaciones(fechaInicial, fechaFinal, idRegion, cnx);
                alianzas.put("REDENCIONES_ACTIVACIONES", redencionesActivaciones);
                redencionesTarjetas3G = this.getRedencionesTarjetas3G(fechaInicial, fechaFinal, idRegion, cnx);
                alianzas.put("REDENCIONES_TARJETAS_3G", redencionesTarjetas3G);
            }
            catch (Exception e) {
                this.error.info((Object)"Exception.getAlianzas:", (Throwable)e);
                throw new CAException(-1, "ReportesCirculoAzulDAO.getAlianzas[" + e.toString() + "]", e);
            }
        }
        finally {
            if (cnx != null) {
                try {
                    cnx.close();
                }
                catch (Exception var15_16) {}
            }
        }
        return alianzas;
    }

    public List<AcumuladosTO> getAcumulados(String fechaInicial, String fechaFinal, int idRegion) throws CAException {
        ArrayList<AcumuladosTO> acumulados;
        Connection cnx = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        acumulados = new ArrayList<AcumuladosTO>();
        try {
            try {
                cnx = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                StringBuffer query = new StringBuffer();
                query.append(" SELECT B.IDREGION, B.CICLOFACT, ");
                query.append(" SUM(A.PUNTOSACAD),  ");
                query.append(" SUM(A.PUNTOSACAD1), ");
                query.append(" SUM(A.PUNTOSACAD2), ");
                query.append(" SUM(A.PUNTOSRENTA), ");
                query.append(" SUM(A.PUNTOSEXCEDENTES), SUM(A.PUNTOSANTIGUEDAD) ");
                query.append(" FROM ").append(this.schema_database).append("PTO_TBLTOTALES A, ").append(this.schema_database).append("PTO_TBLLINEAS B ");
                query.append(" WHERE A.CUENTA = B.CUENTA AND A.SECUENCIA = B.SECUENCIA AND B.STATUSCARTA IN ('A','T','P')");
                query.append(" AND B.CICLOFACT NOT IN (20,24,31,60,61,97,99,98,32) ");
                if (idRegion > 0) {
                    query.append(" AND B.IDREGION=").append(idRegion);
                }
                query.append(" AND A.fechacad between to_timestamp(?,'DD/MM/YYYY') AND ");
                query.append(" to_timestamp(?,'DD/MM/YYYY')");
                query.append(" GROUP BY B.IDREGION, B.CICLOFACT ");
                query.append(" order by B.IDREGION, B.CICLOFACT ");
                stmt = cnx.prepareStatement(query.toString());
                stmt.setString(1, fechaInicial);
                stmt.setString(2, fechaFinal);
                rs = stmt.executeQuery();
                while (rs.next()) {
                    AcumuladosTO acumuladosTO = new AcumuladosTO();
                    acumuladosTO.setRegion(rs.getString(1));
                    acumuladosTO.setCiclo(rs.getString(2));
                    acumuladosTO.setPorVencer(rs.getString(3));
                    acumuladosTO.setPorVencer1(rs.getString(4));
                    acumuladosTO.setPorVencer2(rs.getString(5));
                    acumuladosTO.setRenta(rs.getString(6));
                    acumuladosTO.setExcedentes(rs.getString(7));
                    acumuladosTO.setAntiguedad(rs.getString(8));
                    acumulados.add(acumuladosTO);
                }
            }
            catch (Exception e) {
                this.error.info((Object)"Exception.getAcumulados:", (Throwable)e);
                throw new CAException(-1, "ReportesCirculoAzulDAO.getAcumulados[" + e.toString() + "]", e);
            }
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                    rs = null;
                }
                catch (Exception var11_15) {}
            }
            if (stmt != null) {
                try {
                    stmt.close();
                    stmt = null;
                }
                catch (Exception var11_16) {}
            }
            if (cnx != null) {
                try {
                    cnx.close();
                }
                catch (Exception var11_17) {}
            }
        }
        return acumulados;
    }

    public List<AltoValorTO> getAltoValor(String fechaInicial, String fechaFinal, int idRegion) throws CAException {
        ArrayList<AltoValorTO> altoValor;
        Connection cnx = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        altoValor = new ArrayList<AltoValorTO>();
        try {
            try {
                cnx = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                StringBuffer query = new StringBuffer();
                query.append(" SELECT FOLIO,IDREGION,LINEA,CUENTA,DESCUENTO,PRECIO,PRECIOIVA,");
                query.append(" TO_CHAR(FECHAOPER,'YYYY-MM-DD') AS FECHAOPER,IDPRODUCTO,MARCA,MODELO,VALORPTOS,");
                query.append(" BONOALTOVALOR,IDPUNTOVTA ");
                query.append(" FROM ").append(this.schema_database).append("PTO_TBLREDENCION  ");
                query.append(" WHERE BONOALTOVALOR > 0 AND DESCUENTO >= 0 ");
                query.append(" AND FECHAOPER between to_timestamp(?,'DD/MM/YYYY') AND ");
                query.append(" to_timestamp(?,'DD/MM/YYYY')");
                query.append(" AND ESTATUS ='A'");
                if (idRegion > 0) {
                    query.append(" AND IDREGION=").append(idRegion);
                }
                query.append(" ORDER BY FECHAOPER");
                stmt = cnx.prepareStatement(query.toString());
                stmt.setString(1, fechaInicial);
                stmt.setString(2, fechaFinal);
                rs = stmt.executeQuery();
                while (rs.next()) {
                    AltoValorTO altoValorTO = new AltoValorTO();
                    altoValorTO.setFolio(rs.getString(1));
                    altoValorTO.setRegion(rs.getString(2));
                    altoValorTO.setLinea(rs.getString(3));
                    altoValorTO.setCuenta(rs.getString(4));
                    altoValorTO.setDescuento(rs.getString(5));
                    altoValorTO.setPrecio(rs.getString(6));
                    altoValorTO.setPrecioIva(rs.getString(7));
                    altoValorTO.setFechaOperacion(rs.getString(8));
                    altoValorTO.setMaterial(rs.getString(9));
                    altoValorTO.setMarca(rs.getString(10));
                    altoValorTO.setModelo(rs.getString(11));
                    altoValorTO.setPuntos(rs.getString(12));
                    altoValorTO.setBonoAltoValor(rs.getString(13));
                    altoValorTO.setPuntoVenta(rs.getString(14));
                    altoValor.add(altoValorTO);
                }
            }
            catch (Exception e) {
                this.error.info((Object)"Exception.getAltoValor:", (Throwable)e);
                throw new CAException(-1, "ReportesCirculoAzulDAO.getAltoValor[" + e.toString() + "]", e);
            }
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                    rs = null;
                }
                catch (Exception var11_15) {}
            }
            if (stmt != null) {
                try {
                    stmt.close();
                    stmt = null;
                }
                catch (Exception var11_16) {}
            }
            if (cnx != null) {
                try {
                    cnx.close();
                }
                catch (Exception var11_17) {}
            }
        }
        return altoValor;
    }

    public List<PuntosVencerTO> getPuntosPorVencer(String fechaInicial, String fechaFinal, int idRegion) throws CAException {
        ArrayList<PuntosVencerTO> puntosPorVencer;
        Connection cnx = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        puntosPorVencer = new ArrayList<PuntosVencerTO>();
        try {
            try {
                cnx = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                ArrayList<Integer> ciclos = this.obtieneCiclosByDesc(idRegion, cnx);
                StringBuffer query = new StringBuffer();
                query.append("SELECT b.cuenta, b.secuencia, a.linea, b.puntosacad, ");
                query.append("to_char(b.fechacad,'YYYY-MM-DD') as fechacad, ");
                query.append("b.puntosacad1, to_char(b.fechacad1,'YYYY-MM-DD') as fechacad1, ");
                query.append("b.puntosacad2,  to_char(b.fechacad2,'YYYY-MM-DD') as fechacad2, ");
                query.append("b.puntosrenta, puntosexcedentes, puntosantiguedad, puntospromocion,IDREGION ");
                query.append("FROM ").append(this.schema_database).append("pto_tbllineas a, ").append(this.schema_database).append("pto_tbltotales b  ");
                query.append("WHERE a.cuenta = b.cuenta and a.secuencia = b.secuencia   ");
                if (idRegion > 0) {
                    query.append(" AND A.IDREGION=").append(idRegion).append(" ");
                }
                query.append("and a.statustel in ('AC','SU') ");
                query.append("and a.statuscarta in ('A','P','T') and ");
                query.append("a.ciclofact in (");
                for (Integer ciclo : ciclos) {
                    query.append(ciclo).append(",");
                }
                query.delete(query.length() - 1, query.length());
                query.append(")");
                query.append(" AND b.fechacad between to_timestamp(?,'DD/MM/YYYY') AND ");
                query.append(" to_timestamp(?,'DD/MM/YYYY')");
                query.append("order by fechacad, fechacad1, fechacad2 ");
                stmt = cnx.prepareStatement(query.toString());
                stmt.setString(1, fechaInicial);
                stmt.setString(2, fechaFinal);
                rs = stmt.executeQuery();
                while (rs.next()) {
                    PuntosVencerTO puntosVencerTO = new PuntosVencerTO();
                    puntosVencerTO.setCuenta(rs.getString("cuenta"));
                    puntosVencerTO.setSecuencia(rs.getInt("secuencia"));
                    puntosVencerTO.setLinea(rs.getString("linea"));
                    puntosVencerTO.setRegion(rs.getInt("IDREGION"));
                    puntosVencerTO.setFechaCad(rs.getString("fechacad"));
                    puntosVencerTO.setPuntosaCad(rs.getInt("puntosacad"));
                    puntosVencerTO.setPuntosaCad1(rs.getInt("puntosacad1"));
                    puntosVencerTO.setPuntosaCad2(rs.getInt("puntosacad2"));
                    puntosVencerTO.setPuntosAntiguedad(rs.getInt("puntosantiguedad"));
                    puntosVencerTO.setPuntosExcedentes(rs.getInt("puntosexcedentes"));
                    puntosVencerTO.setPuntosPromocion(rs.getInt("puntospromocion"));
                    puntosVencerTO.setPuntosRenta(rs.getInt("puntosrenta"));
                    puntosPorVencer.add(puntosVencerTO);
                }
            }
            catch (Exception e) {
                this.error.info((Object)"Exception.getPuntosPorVencer:", (Throwable)e);
                throw new CAException(-1, "ReportesCirculoAzulDAO.getPuntosPorVencer[" + e.toString() + "]", e);
            }
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                    rs = null;
                }
                catch (Exception var13_17) {}
            }
            if (stmt != null) {
                try {
                    stmt.close();
                    stmt = null;
                }
                catch (Exception var13_18) {}
            }
            if (cnx != null) {
                try {
                    cnx.close();
                }
                catch (Exception var13_19) {}
            }
        }
        return puntosPorVencer;
    }

    public List<RedencionLineaTO> getRedencionesLinea(String fechaInicial, String fechaFinal, List<String> lineas) throws CAException {
        ArrayList<RedencionLineaTO> redenciones;
        Connection cnx = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        redenciones = new ArrayList<RedencionLineaTO>();
        try {
            try {
                cnx = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                StringBuffer query = new StringBuffer();
                query.append("SELECT B.LINEA, A.CUENTA, A.MARCA, A.MODELO, A.PDISP, A.DIFPESOS, A.ESTATUS, A.TIPOREDEN, A.FECHAOPER ");
                query.append("FROM ").append(this.schema_database).append("PTO_TBLREDENCION A, ").append(this.schema_database).append("PTO_TBLLINEAS B ");
                query.append("where A.CUENTA = B.CUENTA AND A.LINEA IN (");
                for (String linea : lineas) {
                    query.append("'").append(linea).append("',");
                }
                query.delete(query.length() - 1, query.length());
                query.append(")");
                query.append(" AND A.FECHAOPER between to_timestamp(?,'DD/MM/YYYY') AND ");
                query.append(" to_timestamp(?,'DD/MM/YYYY')");
                stmt = cnx.prepareStatement(query.toString());
                stmt.setString(1, fechaInicial);
                stmt.setString(2, fechaFinal);
                rs = stmt.executeQuery();
                while (rs.next()) {
                    RedencionLineaTO redencionLineaTO = new RedencionLineaTO();
                    redencionLineaTO.setLinea(rs.getString("LINEA"));
                    redencionLineaTO.setCuenta(rs.getString("CUENTA"));
                    redencionLineaTO.setMarca(rs.getString("MARCA"));
                    redencionLineaTO.setModelo(rs.getString("MODELO"));
                    redencionLineaTO.setPuntosDisponibles(String.valueOf(rs.getInt("PDISP")));
                    redencionLineaTO.setDifPesos(String.valueOf(rs.getInt("DIFPESOS")));
                    redencionLineaTO.setEstatus(rs.getString("ESTATUS"));
                    redencionLineaTO.setTipoReden(rs.getString("TIPOREDEN"));
                    redencionLineaTO.setFechaOperacion(String.valueOf(rs.getDate("FECHAOPER")));
                    redenciones.add(redencionLineaTO);
                }
            }
            catch (Exception e) {
                this.error.info((Object)"Exception.getRedencionesLinea:", (Throwable)e);
                throw new CAException(-1, "ReportesCirculoAzulDAO.getRedencionesLinea[" + e.toString() + "]", e);
            }
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                    rs = null;
                }
                catch (Exception var12_16) {}
            }
            if (stmt != null) {
                try {
                    stmt.close();
                    stmt = null;
                }
                catch (Exception var12_17) {}
            }
            if (cnx != null) {
                try {
                    cnx.close();
                }
                catch (Exception var12_18) {}
            }
        }
        return redenciones;
    }

    private List<Reportable> getRedencionesMexicana(String fechaInicial, String fechaFinal, int idRegion, Connection cnx) throws Exception {
        ArrayList<Reportable> redencionesMexicana;
        redencionesMexicana = new ArrayList<Reportable>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        StringBuffer query = new StringBuffer();
        query.append(" select a.idregion,count(1), sum(b.Millas), sum(b.ptransf),to_char((sum(b.ptransf)/count(1)),'999999999999') promedio");
        query.append(" from ").append(this.schema_database).append("pto_tblalianzas b,").append(this.schema_database).append("pto_tbllineas a ");
        query.append(" where a.cuenta = b.cuenta and a.secuencia = b.secuencia and ");
        query.append(" b.estatus = 'A' and b.numerocanje <> -1 and   ");
        query.append(" FECHAOPER between to_timestamp(?,'DD/MM/YYYY') AND ");
        query.append(" to_timestamp(?,'DD/MM/YYYY') ");
        if (idRegion > 0) {
            query.append(" AND a.IDREGION=").append(idRegion);
        }
        query.append(" AND b.idcuenta = 1  ");
        query.append(" group by a.idregion ");
        query.append(" order by a.idregion ");
        try {
            stmt = cnx.prepareStatement(query.toString());
            stmt.setString(1, fechaInicial);
            stmt.setString(2, fechaFinal);
            rs = stmt.executeQuery();
            while (rs.next()) {
                AlianzaMexicanaTO alianzaMexicanaTO = new AlianzaMexicanaTO();
                alianzaMexicanaTO.setRegion(rs.getString(1));
                alianzaMexicanaTO.setNumCanjes(rs.getString(2));
                alianzaMexicanaTO.setMillas(rs.getString(3));
                alianzaMexicanaTO.setTotalPuntos(rs.getString(4));
                alianzaMexicanaTO.setPromedio(rs.getString(5));
                redencionesMexicana.add((Reportable)alianzaMexicanaTO);
            }
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                    rs = null;
                }
                catch (Exception var11_14) {}
            }
            if (stmt != null) {
                try {
                    stmt.close();
                    stmt = null;
                }
                catch (Exception var11_15) {}
            }
        }
        return redencionesMexicana;
    }

    private List<Reportable> getRedencionesAmericanExpress(String fechaInicial, String fechaFinal, int idRegion, Connection cnx) throws Exception {
        ArrayList<Reportable> redencionesAmex;
        redencionesAmex = new ArrayList<Reportable>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        StringBuffer query = new StringBuffer();
        query.append(" select a.idregion, count(1), sum(b.ptransf),statustrans ");
        query.append(" from ").append(this.schema_database).append("pto_tblalianzas b,").append(this.schema_database).append("pto_tbllineas a ");
        query.append(" where a.cuenta = b.cuenta and a.secuencia = b.secuencia and ");
        query.append(" b.estatus = 'A' and  ");
        query.append(" b.FECHAOPER between to_timestamp(?,'DD/MM/YYYY') AND ");
        query.append(" to_timestamp(?,'DD/MM/YYYY') AND ");
        query.append(" b.idcuenta = 2  ");
        if (idRegion > 0) {
            query.append(" AND a.IDREGION=").append(idRegion);
        }
        query.append(" group by a.idregion, statustrans ");
        query.append(" order by a.idregion, statustrans ");
        try {
            stmt = cnx.prepareStatement(query.toString());
            stmt.setString(1, fechaInicial);
            stmt.setString(2, fechaFinal);
            rs = stmt.executeQuery();
            while (rs.next()) {
                AlianzaAmexTO alianzaAmexTO = new AlianzaAmexTO();
                alianzaAmexTO.setRegion(rs.getString(1));
                alianzaAmexTO.setNumCanjes(rs.getString(2));
                alianzaAmexTO.setTotalPuntos(rs.getString(3));
                alianzaAmexTO.setEstatus(rs.getString(4));
                redencionesAmex.add((Reportable)alianzaAmexTO);
            }
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                    rs = null;
                }
                catch (Exception var11_14) {}
            }
            if (stmt != null) {
                try {
                    stmt.close();
                    stmt = null;
                }
                catch (Exception var11_15) {}
            }
        }
        return redencionesAmex;
    }

    private List<Reportable> getRedencionesEntrega(String fechaInicial, String fechaFinal, Connection cnx) throws Exception {
        ArrayList<Reportable> redencionesEntrega;
        redencionesEntrega = new ArrayList<Reportable>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        StringBuffer query = new StringBuffer();
        query.append(" select opcion, count(1) ");
        query.append(" from ").append(this.schema_database).append("pto_tblalianzas ");
        query.append(" where estatus = 'A' and  idcuenta = 3 and ");
        query.append(" FECHAOPER between to_timestamp(?,'DD/MM/YYYY') AND ");
        query.append(" to_timestamp(?,'DD/MM/YYYY') ");
        query.append(" group by opcion ");
        query.append(" order by opcion ");
        try {
            stmt = cnx.prepareStatement(query.toString());
            stmt.setString(1, fechaInicial);
            stmt.setString(2, fechaFinal);
            rs = stmt.executeQuery();
            while (rs.next()) {
                RedencionEntregaTO redencionEntregaTO = new RedencionEntregaTO();
                redencionEntregaTO.setMedioEntrega(rs.getString(1));
                redencionEntregaTO.setTotal(rs.getString(2));
                redencionesEntrega.add((Reportable)redencionEntregaTO);
            }
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                    rs = null;
                }
                catch (Exception var10_13) {}
            }
            if (stmt != null) {
                try {
                    stmt.close();
                    stmt = null;
                }
                catch (Exception var10_14) {}
            }
        }
        return redencionesEntrega;
    }

    private List<Reportable> getRedencionesAmigoKit(String fechaInicial, String fechaFinal, int idRegion, Connection cnx) throws Exception {
        ArrayList<Reportable> redencionesAmigoKit;
        redencionesAmigoKit = new ArrayList<Reportable>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        StringBuffer query = new StringBuffer();
        query.append(" Select b.idregion, count(1), sum(a.valorptos), sum(a.difpesos), ");
        query.append(" to_char((sum(a.valorptos)/count(1)),'999999999999') prom1, ");
        query.append(" to_char((sum(a.difpesos)/count(1)),'999999999999') prom2 ");
        query.append(" from ").append(this.schema_database).append("pto_tblredencion a, ").append(this.schema_database).append("pto_tbllineas b ");
        query.append(" where a.cuenta = b.cuenta and a.secuencia =b.secuencia and ");
        query.append(" a.tiporeden  = 'S' and a.estatus = 'A'  and ");
        query.append(" FECHAOPER between to_timestamp(?,'DD/MM/YYYY') AND ");
        query.append(" to_timestamp(?,'DD/MM/YYYY') ");
        if (idRegion > 0) {
            query.append(" AND b.IDREGION=").append(idRegion);
        }
        query.append(" group by b.idregion ");
        query.append(" order by b.idregion ");
        try {
            stmt = cnx.prepareStatement(query.toString());
            stmt.setString(1, fechaInicial);
            stmt.setString(2, fechaFinal);
            rs = stmt.executeQuery();
            while (rs.next()) {
                AmigoKitReporteTO amigoKitReporteTO = new AmigoKitReporteTO();
                amigoKitReporteTO.setRegion(rs.getString(1));
                amigoKitReporteTO.setNumCanjes(rs.getString(2));
                amigoKitReporteTO.setTotalPuntos(rs.getString(3));
                amigoKitReporteTO.setDiferenciaPesos(rs.getString(4));
                amigoKitReporteTO.setPromedioPuntos(rs.getString(5));
                amigoKitReporteTO.setPromedioPesos(rs.getString(6));
                redencionesAmigoKit.add((Reportable)amigoKitReporteTO);
            }
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                    rs = null;
                }
                catch (Exception var11_14) {}
            }
            if (stmt != null) {
                try {
                    stmt.close();
                    stmt = null;
                }
                catch (Exception var11_15) {}
            }
        }
        return redencionesAmigoKit;
    }

    private List<Reportable> getRedencionesTiempoAire(String fechaInicial, String fechaFinal, int idRegion, Connection cnx) throws Exception {
        ArrayList<Reportable> redencionesTiempoAire;
        redencionesTiempoAire = new ArrayList<Reportable>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        StringBuffer query = new StringBuffer();
        query.append(" Select b.idregion, a.idproducto, count(1), sum(a.valorptos)");
        query.append(" from ").append(this.schema_database).append("pto_tblredencion a, ").append(this.schema_database).append("pto_tbllineas b ");
        query.append(" where a.cuenta = b.cuenta and a.secuencia =b.secuencia and ");
        query.append(" a.tiporeden  = 'T' and a.estatus = 'A'  and ");
        query.append(" FECHAOPER between to_timestamp(?,'DD/MM/YYYY') AND ");
        query.append(" to_timestamp(?,'DD/MM/YYYY') ");
        if (idRegion > 0) {
            query.append(" AND b.IDREGION=").append(idRegion);
        }
        query.append(" group by b.idregion, a.idproducto ");
        query.append(" order by b.idregion, a.idproducto ");
        try {
            stmt = cnx.prepareStatement(query.toString());
            stmt.setString(1, fechaInicial);
            stmt.setString(2, fechaFinal);
            rs = stmt.executeQuery();
            while (rs.next()) {
                TiempoAireReporteTO tiempoAireReporteTO = new TiempoAireReporteTO();
                tiempoAireReporteTO.setRegion(rs.getString(1));
                tiempoAireReporteTO.setPaqueteTiempoAire(rs.getString(2));
                tiempoAireReporteTO.setTotalRegion(rs.getString(3));
                tiempoAireReporteTO.setTotalPuntos(rs.getString(4));
                redencionesTiempoAire.add((Reportable)tiempoAireReporteTO);
            }
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                    rs = null;
                }
                catch (Exception var11_14) {}
            }
            if (stmt != null) {
                try {
                    stmt.close();
                    stmt = null;
                }
                catch (Exception var11_15) {}
            }
        }
        return redencionesTiempoAire;
    }

    private List<Reportable> getRedencionesActivaciones(String fechaInicial, String fechaFinal, int idRegion, Connection cnx) throws Exception {
        ArrayList<Reportable> redencionesActivaciones;
        redencionesActivaciones = new ArrayList<Reportable>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        StringBuffer query = new StringBuffer();
        query.append(" Select b.idregion, a.idproducto, count(1), sum(a.valorptos)");
        query.append(" from ").append(this.schema_database).append("pto_tblredencion a, ").append(this.schema_database).append("pto_tbllineas b ");
        query.append(" where a.cuenta = b.cuenta and a.secuencia =b.secuencia and ");
        query.append(" a.tiporeden  = 'A' and a.estatus = 'A'  and ");
        query.append(" a.FECHAOPER between to_timestamp(?,'DD/MM/YYYY') AND ");
        query.append(" to_timestamp(?,'DD/MM/YYYY') ");
        if (idRegion > 0) {
            query.append(" AND b.IDREGION=").append(idRegion);
        }
        query.append(" group by b.idregion, a.idproducto ");
        query.append(" order by b.idregion, a.idproducto ");
        try {
            stmt = cnx.prepareStatement(query.toString());
            stmt.setString(1, fechaInicial);
            stmt.setString(2, fechaFinal);
            rs = stmt.executeQuery();
            while (rs.next()) {
                ActivacionesReporteTO activacionesReporteTO = new ActivacionesReporteTO();
                activacionesReporteTO.setRegion(rs.getString(1));
                activacionesReporteTO.setTecnologia(rs.getString(2));
                activacionesReporteTO.setTotalRegion(rs.getString(3));
                activacionesReporteTO.setTotalPuntos(rs.getString(4));
                redencionesActivaciones.add((Reportable)activacionesReporteTO);
            }
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                    rs = null;
                }
                catch (Exception var11_14) {}
            }
            if (stmt != null) {
                try {
                    stmt.close();
                    stmt = null;
                }
                catch (Exception var11_15) {}
            }
        }
        return redencionesActivaciones;
    }

    private List<Reportable> getRedencionesTarjetas3G(String fechaInicial, String fechaFinal, int idRegion, Connection cnx) throws Exception {
        ArrayList<Reportable> redencionesTarjetas3G;
        redencionesTarjetas3G = new ArrayList<Reportable>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        StringBuffer query = new StringBuffer();
        query.append(" Select b.idregion, a.idproducto, count(1),");
        query.append(" sum(a.valorptos), sum(a.difpesos), ");
        query.append(" to_char((sum(a.valorptos)/count(1)),'999999999999') prom1, ");
        query.append(" to_char((sum(a.difpesos)/count(1)),'999999999999') prom2 ");
        query.append(" from ").append(this.schema_database).append("pto_tblredencion a, ").append(this.schema_database).append("pto_tbllineas b ");
        query.append(" where a.cuenta = b.cuenta and a.secuencia =b.secuencia and ");
        query.append(" a.tiporeden  = 'G' and a.estatus = 'A'  and   ");
        query.append(" a.FECHAOPER between to_timestamp(?,'DD/MM/YYYY') AND ");
        query.append(" to_timestamp(?,'DD/MM/YYYY') ");
        if (idRegion > 0) {
            query.append(" AND b.IDREGION=").append(idRegion);
        }
        query.append(" group by b.idregion, a.idproducto ");
        query.append(" order by b.idregion, a.idproducto ");
        try {
            stmt = cnx.prepareStatement(query.toString());
            stmt.setString(1, fechaInicial);
            stmt.setString(2, fechaFinal);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Tarjetas3GReporteTO tarjetas3GReporteTO = new Tarjetas3GReporteTO();
                tarjetas3GReporteTO.setRegion(rs.getString(1));
                tarjetas3GReporteTO.setIdProducto(rs.getString(2));
                tarjetas3GReporteTO.setNumCanjes(rs.getString(3));
                tarjetas3GReporteTO.setTotalPuntos(rs.getString(4));
                tarjetas3GReporteTO.setDiferenciaPesos(rs.getString(5));
                tarjetas3GReporteTO.setPromedioPuntos(rs.getString(6));
                tarjetas3GReporteTO.setPromedioPesos(rs.getString(7));
                redencionesTarjetas3G.add((Reportable)tarjetas3GReporteTO);
            }
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                    rs = null;
                }
                catch (Exception var11_14) {}
            }
            if (stmt != null) {
                try {
                    stmt.close();
                    stmt = null;
                }
                catch (Exception var11_15) {}
            }
        }
        return redencionesTarjetas3G;
    }

    private ArrayList<Integer> obtieneCiclosByDesc(int region, Connection con) throws Exception {
        ArrayList<Integer> cList;
        cList = null;
        ResultSet resultSet = null;
        PreparedStatement stmt = null;
        StringBuilder query = new StringBuilder();
        query.append(" select ciclo from ").append(this.schema_database).append("PTO_CTLCICLOS");
        query.append(" where ");
        if (region > 0) {
            query.append("idRegion= ").append(region).append(" and descripcion like '%PUNTOS%'");
        } else {
            query.append(" descripcion like '%PUNTOS%'");
        }
        query.append(" order by ciclo asc");
        try {
            stmt = con.prepareStatement(query.toString());
            resultSet = stmt.executeQuery();
            cList = new ArrayList<Integer>();
            while (resultSet.next()) {
                cList.add(resultSet.getInt("ciclo"));
            }
        }
        finally {
            if (stmt != null) {
                try {
                    stmt.close();
                    stmt = null;
                }
                catch (SQLException var9_11) {}
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                    resultSet = null;
                }
                catch (SQLException var9_12) {}
            }
        }
        return cList;
    }

    public List<RedencionesOnlineTO> getRedencionesOnline(String fechaInicial, String fechaFinal, int idRegion) throws CAException {
        ArrayList<RedencionesOnlineTO> redencionesOnline;
        redencionesOnline = new ArrayList<RedencionesOnlineTO>();
        Connection cnx = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        StringBuffer query = new StringBuffer();
        query.append(" Select (CASE  WHEN a.ORIGENREDONLINE   = 1 THEN 'SERVICIO'");
        query.append(" WHEN a.ORIGENREDONLINE   = 2 THEN 'PORTAL' ");
        query.append(" WHEN a.ORIGENREDONLINE   = 3 THEN 'MY TELCEL' ");
        query.append(" ELSE 'DESCONOCIDO' END) Origen ");
        query.append(" , b.idregion, a.idproducto, count(1), sum(a.valorptos) ");
        query.append(" from ").append(this.schema_database).append("pto_tblredencion a, ").append(this.schema_database).append("pto_tbllineas b ");
        query.append(" where a.cuenta = b.cuenta and a.secuencia =b.secuencia and  ");
        query.append(" a.tiporeden  = 'O' and a.estatus = 'A'  and ");
        query.append(" a.FECHAOPER between to_timestamp(?,'DD/MM/YYYY') AND ");
        query.append(" to_timestamp(?,'DD/MM/YYYY') ");
        if (idRegion > 0) {
            query.append(" AND b.IDREGION=").append(idRegion);
        }
        query.append(" group by a.ORIGENREDONLINE,b.idregion, a.idproducto ");
        query.append(" order by a.ORIGENREDONLINE,b.idregion, a.idproducto ");
        try {
            try {
                cnx = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                stmt = cnx.prepareStatement(query.toString());
                stmt.setString(1, fechaInicial);
                stmt.setString(2, fechaFinal);
                rs = stmt.executeQuery();
                while (rs.next()) {
                    RedencionesOnlineTO redencionesOnlineTO = new RedencionesOnlineTO();
                    redencionesOnlineTO.setOrigen(rs.getString(1));
                    redencionesOnlineTO.setRegion(rs.getString(2));
                    redencionesOnlineTO.setPaquete(rs.getString(3));
                    redencionesOnlineTO.setTotalRegion(rs.getString(4));
                    redencionesOnlineTO.setTotalPuntos(rs.getString(5));
                    redencionesOnline.add(redencionesOnlineTO);
                }
            }
            catch (Exception e) {
                this.error.info((Object)"Exception.getRedencionesOnline:", (Throwable)e);
                throw new CAException(-1, "ReportesCirculoAzulDAO.getRedencionesOnline[" + e.toString() + "]", e);
            }
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                    rs = null;
                }
                catch (Exception var11_15) {}
            }
            if (stmt != null) {
                try {
                    stmt.close();
                    stmt = null;
                }
                catch (Exception var11_16) {}
            }
            if (cnx != null) {
                try {
                    cnx.close();
                }
                catch (Exception var11_17) {}
            }
        }
        return redencionesOnline;
    }

    public List<InbursaTO> getInbursaRentas(String fechaInicial, String fechaFinal, int idRegion) throws CAException {
        ArrayList<InbursaTO> productosInbursa;
        Connection cnx = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String fechaFinTimestamp = null;
        productosInbursa = new ArrayList<InbursaTO>();
        try {
            try {
                cnx = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                String ObtieneFecha = "SELECT to_timestamp('" + fechaFinal + "','DD/MM/YYYY')+1 FROM DUAL";
                stmt = cnx.prepareStatement(ObtieneFecha);
                rs = stmt.executeQuery();
                if (rs.next()) {
                    fechaFinTimestamp = String.valueOf(rs.getDate(1));
                }
                stmt = null;
                rs = null;
                StringBuffer query = new StringBuffer();
                query.append(" SELECT FOLIO,TELEFONO,CTATELCEL,REGION,MEDIO,PRODUCTO,TO_CHAR(FECHAOPER, 'dd/mm/yyyy') AS FECHAOPER, ");
                query.append(" TIPOMOVIMIENTO,STATUS FROM ").append(this.schema_database).append("PTO_TBLBATCHINBURSA ");
                query.append(" WHERE PRODUCTO IN ('RTOTA','RCAIN') AND FECHAOPER between to_timestamp(?,'DD/MM/YYYY') ");
                query.append(" AND to_timestamp(?,'YYYY-MM-DD') ");
                if (idRegion > 0) {
                    query.append(" AND REGION='R0").append(idRegion).append("'");
                }
                query.append(" ORDER BY FECHAOPER ");
                stmt = cnx.prepareStatement(query.toString());
                stmt.setString(1, fechaInicial);
                stmt.setString(2, fechaFinTimestamp);
                rs = stmt.executeQuery();
                while (rs.next()) {
                    InbursaTO inbursaTO = new InbursaTO();
                    inbursaTO.setFolioInbursa(rs.getString("FOLIO"));
                    inbursaTO.setTelefono(rs.getString("TELEFONO"));
                    inbursaTO.setCtaTelcel(rs.getString("CTATELCEL"));
                    inbursaTO.setRegion(rs.getString("REGION"));
                    inbursaTO.setMedio(rs.getString("MEDIO"));
                    inbursaTO.setProducto(rs.getString("PRODUCTO"));
                    if (inbursaTO.getProducto().equals("RTOTA")) {
                        inbursaTO.setDescripcion("RENTA INBURSA");
                    } else if (inbursaTO.getProducto().equals("RCAIN")) {
                        inbursaTO.setDescripcion("RENTA INBURSA");
                    }
                    inbursaTO.setFechaOper(rs.getString("FECHAOPER"));
                    inbursaTO.setTipoMovimiento(rs.getString("TIPOMOVIMIENTO"));
                    if (inbursaTO.getTipoMovimiento().equals("A")) {
                        inbursaTO.setTipoMovimiento("APLICADO");
                    } else if (inbursaTO.getTipoMovimiento().equals("B")) {
                        inbursaTO.setTipoMovimiento("BAJA");
                    } else if (inbursaTO.getTipoMovimiento().equals("N")) {
                        inbursaTO.setTipoMovimiento("NO APLICA");
                    }
                    inbursaTO.setEstatus(rs.getString("STATUS"));
                    productosInbursa.add(inbursaTO);
                }
            }
            catch (Exception e) {
                this.error.info((Object)"Exception.getInbursaRentas:", (Throwable)e);
                throw new CAException(-1, "ReportesCirculoAzulDAO.getInbursaRentas[" + e.toString() + "]", e);
            }
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                    rs = null;
                }
                catch (Exception var13_17) {}
            }
            if (stmt != null) {
                try {
                    stmt.close();
                    stmt = null;
                }
                catch (Exception var13_18) {}
            }
            if (cnx != null) {
                try {
                    cnx.close();
                }
                catch (Exception var13_19) {}
            }
        }
        return productosInbursa;
    }

    public List<InbursaTO> getInbursaMinutos(String fechaInicial, String fechaFinal, int idRegion) throws CAException {
        ArrayList<InbursaTO> productosInbursa;
        Connection cnx = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String fechaFinTimestamp = null;
        productosInbursa = new ArrayList<InbursaTO>();
        try {
            try {
                cnx = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                String ObtieneFecha = "SELECT to_timestamp('" + fechaFinal + "','DD/MM/YYYY')+1 FROM DUAL";
                stmt = cnx.prepareStatement(ObtieneFecha);
                rs = stmt.executeQuery();
                if (rs.next()) {
                    fechaFinTimestamp = String.valueOf(rs.getDate(1));
                }
                stmt = null;
                rs = null;
                StringBuffer query = new StringBuffer();
                query.append(" SELECT  FOLIO,TELEFONO,CTATELCEL,REGION,MEDIO,PRODUCTO,TO_CHAR(FECHAOPER, 'dd/mm/yyyy') AS FECHAOPER,");
                query.append(" TIPOMOVIMIENTO,STATUS FROM ").append(this.schema_database).append("PTO_TBLBATCHINBURSA ");
                query.append(" WHERE PRODUCTO ='C100N' AND FECHAOPER between to_timestamp(?,'DD/MM/YYYY') ");
                query.append(" AND to_timestamp(?,'YYYY-MM-DD') ");
                if (idRegion > 0) {
                    query.append(" AND REGION='R0").append(idRegion).append("'");
                }
                query.append(" ORDER BY FECHAOPER ");
                stmt = cnx.prepareStatement(query.toString());
                stmt.setString(1, fechaInicial);
                stmt.setString(2, fechaFinTimestamp);
                rs = stmt.executeQuery();
                while (rs.next()) {
                    InbursaTO inbursaTO = new InbursaTO();
                    inbursaTO.setFolioInbursa(rs.getString("FOLIO"));
                    inbursaTO.setTelefono(rs.getString("TELEFONO"));
                    inbursaTO.setCtaTelcel(rs.getString("CTATELCEL"));
                    inbursaTO.setRegion(rs.getString("REGION"));
                    inbursaTO.setMedio(rs.getString("MEDIO"));
                    inbursaTO.setProducto(rs.getString("PRODUCTO"));
                    inbursaTO.setDescripcion("100 MIN INBURSA");
                    inbursaTO.setFechaOper(rs.getString("FECHAOPER"));
                    inbursaTO.setTipoMovimiento(rs.getString("TIPOMOVIMIENTO"));
                    if (inbursaTO.getTipoMovimiento().equals("A")) {
                        inbursaTO.setTipoMovimiento("APLICADO");
                    } else if (inbursaTO.getTipoMovimiento().equals("B")) {
                        inbursaTO.setTipoMovimiento("BAJA");
                    } else if (inbursaTO.getTipoMovimiento().equals("N")) {
                        inbursaTO.setTipoMovimiento("NO APLICA");
                    }
                    inbursaTO.setEstatus(rs.getString("STATUS"));
                    productosInbursa.add(inbursaTO);
                }
            }
            catch (Exception e) {
                this.error.info((Object)"Exception.getInbursaMinutos:", (Throwable)e);
                throw new CAException(-1, "ReportesCirculoAzulDAO.getInbursaMinutos[" + e.toString() + "]", e);
            }
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                    rs = null;
                }
                catch (Exception var13_17) {}
            }
            if (stmt != null) {
                try {
                    stmt.close();
                    stmt = null;
                }
                catch (Exception var13_18) {}
            }
            if (cnx != null) {
                try {
                    cnx.close();
                }
                catch (Exception var13_19) {}
            }
        }
        return productosInbursa;
    }

    public List<InbursaTO> getInbursaDescuento1000(String fechaInicial, String fechaFinal, int idRegion) throws CAException {
        ArrayList<InbursaTO> descuentoInbursa;
        Connection cnx = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        descuentoInbursa = new ArrayList<InbursaTO>();
        try {
            try {
                cnx = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                StringBuffer query = new StringBuffer();
                query.append(" SELECT R.FOLIO,R.IDREGION,R.LINEA,R.CUENTA,R.COMENTARIO,R.IDPRODUCTO,R.MARCA,R.MODELO, ");
                query.append(" R.VALORPTOS,R.DIFPESOS,R.TIPOREDEN,R.BONOINBURSA,R.BONOMARCA,R.BONOROEXT,R.BONOALTOVALOR, ");
                query.append(" TO_CHAR(R.FECHAOPER, 'dd/mm/yyyy') AS FECHAOPER,R.ESTATUS,R.IDPUNTOVTA, ");
                query.append(" I.FOLIO AS FOLIOFIN,I.DESCUENTOINBURSA+ I.DESCUENTOMARCA AS BONODESCUENTO, ");
                query.append(" TO_CHAR(I.FECHAEXPIRACION, 'dd/mm/yyyy') AS FECHAEXPIRACIO,I.FOLIOINBURSA  FROM ");
                query.append(this.schema_database).append("PTO_TBLREDENCION R, ").append(this.schema_database).append("PTO_TBLBONOINBURSA I");
                query.append(" WHERE R.CUENTA=I.CUENTA AND R.FOLIO=I.FOLIOREDENCION AND ");
                query.append(" FECHAOPER BETWEEN TO_TIMESTAMP(?,'DD/MM/YYYY') ");
                query.append(" AND TO_TIMESTAMP(?,'DD/MM/YYYY') ");
                if (idRegion > 0) {
                    query.append(" AND R.IDREGION=").append(idRegion);
                }
                query.append(" ORDER BY R.FECHAOPER ");
                stmt = cnx.prepareStatement(query.toString());
                stmt.setString(1, fechaInicial);
                stmt.setString(2, fechaFinal);
                rs = stmt.executeQuery();
                while (rs.next()) {
                    InbursaTO inbursaTO = new InbursaTO();
                    inbursaTO.setFolioCA(rs.getString("FOLIO"));
                    inbursaTO.setRegion(rs.getString("IDREGION"));
                    inbursaTO.setTelefono(rs.getString("LINEA"));
                    inbursaTO.setCtaTelcel(rs.getString("CUENTA"));
                    inbursaTO.setComentario(rs.getString("COMENTARIO"));
                    inbursaTO.setProducto(rs.getString("IDPRODUCTO"));
                    inbursaTO.setMarca(rs.getString("MARCA"));
                    inbursaTO.setModelo(rs.getString("MODELO"));
                    inbursaTO.setValorPuntos(rs.getString("VALORPTOS"));
                    inbursaTO.setDifPesos(rs.getString("DIFPESOS"));
                    inbursaTO.setTipoRedencion(rs.getString("TIPOREDEN"));
                    inbursaTO.setBonoInbursa(rs.getString("BONOINBURSA"));
                    inbursaTO.setBonoMarca(rs.getString("BONOMARCA"));
                    inbursaTO.setBonoRoext(rs.getString("BONOROEXT"));
                    inbursaTO.setBonoAltoValor(rs.getString("BONOALTOVALOR"));
                    if (rs.getString("FOLIOFIN").equals("0")) {
                        inbursaTO.setFolioFinanzas("0");
                    } else {
                        inbursaTO.setFolioFinanzas(Utils.anexarCeros((int)8, (String)rs.getString("FOLIOFIN")));
                    }
                    inbursaTO.setBonoDescInbursa(rs.getString("BONODESCUENTO"));
                    inbursaTO.setFechaExpira(rs.getString("FECHAEXPIRACIO"));
                    inbursaTO.setFolioInbursa(rs.getString("FOLIOINBURSA"));
                    inbursaTO.setFechaOper(rs.getString("FECHAOPER"));
                    inbursaTO.setEstatus(rs.getString("ESTATUS"));
                    if (inbursaTO.getEstatus().equals("I")) {
                        inbursaTO.setEstatus("REDENCION CANCELADA");
                    } else {
                        inbursaTO.setEstatus("REDENCION REALIZADA");
                    }
                    inbursaTO.setPuntoVenta(rs.getString("IDPUNTOVTA"));
                    descuentoInbursa.add(inbursaTO);
                }
            }
            catch (Exception e) {
                this.error.info((Object)"Exception.getInbursaDescuento1000:", (Throwable)e);
                throw new CAException(-1, "ReportesCirculoAzulDAO.getInbursaDescuento1000[" + e.toString() + "]", e);
            }
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                    rs = null;
                }
                catch (Exception var11_15) {}
            }
            if (stmt != null) {
                try {
                    stmt.close();
                    stmt = null;
                }
                catch (Exception var11_16) {}
            }
            if (cnx != null) {
                try {
                    cnx.close();
                }
                catch (Exception var11_17) {}
            }
        }
        return descuentoInbursa;
    }

    public List<InbursaTO> getInbursaPaquetes(String fechaInicial, String fechaFinal, int idRegion) throws CAException {
        ArrayList<InbursaTO> paquetesInbursa;
        Connection cnx = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String fechaFinTimestamp = null;
        paquetesInbursa = new ArrayList<InbursaTO>();
        try {
            try {
                cnx = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                String ObtieneFecha = "SELECT to_timestamp('" + fechaFinal + "','DD/MM/YYYY')+1 FROM DUAL";
                stmt = cnx.prepareStatement(ObtieneFecha);
                rs = stmt.executeQuery();
                if (rs.next()) {
                    fechaFinTimestamp = String.valueOf(rs.getDate(1));
                }
                stmt = null;
                rs = null;
                StringBuffer query = new StringBuffer();
                query.append(" SELECT  TELEFONO,CTATELCEL,REGION,PRODUCTO,TO_CHAR(FECHAOPER, 'dd/mm/yyyy') AS FECHAOPER");
                query.append(" ,CODIGORESPUESTA,STATUS FROM ").append(this.schema_database).append("PTO_TBLBATCHINBURSA ");
                query.append(" WHERE PRODUCTO IN ('C101N','C102N','C103N','C104N') AND FECHAOPER between to_timestamp(?,'DD/MM/YYYY') ");
                query.append(" AND to_timestamp(?,'YYYY-MM-DD') ");
                if (idRegion > 0) {
                    query.append(" AND REGION='R0").append(idRegion).append("'");
                }
                query.append(" ORDER BY FECHAOPER ");
                stmt = cnx.prepareStatement(query.toString());
                stmt.setString(1, fechaInicial);
                stmt.setString(2, fechaFinTimestamp);
                rs = stmt.executeQuery();
                while (rs.next()) {
                    InbursaTO inbursaTO = new InbursaTO();
                    inbursaTO.setTelefono(rs.getString("TELEFONO"));
                    inbursaTO.setCtaTelcel(rs.getString("CTATELCEL"));
                    inbursaTO.setRegion(rs.getString("REGION"));
                    inbursaTO.setProducto(rs.getString("PRODUCTO"));
                    if (inbursaTO.getProducto().equals("C101N")) {
                        inbursaTO.setDescripcion("25 MINUTOS INBURSA");
                    } else if (inbursaTO.getProducto().equals("C102N")) {
                        inbursaTO.setDescripcion("50 MINUTOS INBURSA");
                    } else if (inbursaTO.getProducto().equals("C103N")) {
                        inbursaTO.setDescripcion("75 MINUTOS INBURSA");
                    } else if (inbursaTO.getProducto().equals("C104N")) {
                        inbursaTO.setDescripcion("100 MINUTOS INBURSA");
                    }
                    inbursaTO.setFechaOper(rs.getString("FECHAOPER"));
                    if (rs.getString("CODIGORESPUESTA").equals("00")) {
                        inbursaTO.setCodigoRespuesta("ACTIVA");
                    } else {
                        inbursaTO.setCodigoRespuesta("RECHAZADA");
                    }
                    inbursaTO.setEstatus(rs.getString("STATUS"));
                    paquetesInbursa.add(inbursaTO);
                }
            }
            catch (Exception e) {
                this.error.info((Object)"Exception.getInbursaPaquetes:", (Throwable)e);
                throw new CAException(-1, "ReportesCirculoAzulDAO.getInbursaPaquetes[" + e.toString() + "]", e);
            }
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                    rs = null;
                }
                catch (Exception var13_17) {}
            }
            if (stmt != null) {
                try {
                    stmt.close();
                    stmt = null;
                }
                catch (Exception var13_18) {}
            }
            if (cnx != null) {
                try {
                    cnx.close();
                }
                catch (Exception var13_19) {}
            }
        }
        return paquetesInbursa;
    }
}

