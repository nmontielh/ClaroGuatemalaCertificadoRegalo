/*
 * Decompiled with CFR 0_102.
 * 
 * Could not load the following classes:
 *  com.claro.exception.CAException
 *  com.claro.transfer.inbursa.InbursaTO
 *  com.claro.util.ServiceLocator
 *  com.claro.util.Utils
 *  org.apache.log4j.Logger
 */
package com.claro.inbursa.dao;

import com.claro.exception.CAException;
import com.claro.transfer.inbursa.InbursaTO;
import com.claro.util.ServiceLocator;
import com.claro.util.Utils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

public class ConsultaInbursaDAO {
    protected final Logger error = Logger.getLogger((String)"loggerCirculoAzulError");
    protected final Logger logger = Logger.getLogger((String)"loggerCirculoAzul");
    private String schema_database;

    public ConsultaInbursaDAO() {
        try {
            this.schema_database = ServiceLocator.getInstance().getVariable(ServiceLocator.schema_database);
        }
        catch (Exception e) {
            this.error.error((Object)"ReportesCirculoAzulDAO", (Throwable)e);
        }
    }

    public ArrayList<InbursaTO> getMovimientosInbursa(String telefono) throws CAException {
        ArrayList<InbursaTO> inbursa;
        Connection cnx = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        inbursa = new ArrayList<InbursaTO>();
        try {
            try {
                cnx = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                StringBuffer query = new StringBuffer();
                query.append(" SELECT FOLIO,TELEFONO,REGION,MODALIDAD,TO_CHAR(FECHAOPER, 'dd/mm/yyyy') AS FECHAOPER,PRODUCTO,TIPOMOVIMIENTO,STATUS FROM ");
                query.append(this.schema_database).append("PTO_TBLBATCHINBURSA WHERE TELEFONO=? ORDER BY FECHAOPER DESC ");
                stmt = cnx.prepareStatement(query.toString());
                stmt.setLong(1, Long.parseLong(telefono));
                rs = stmt.executeQuery();
                while (rs.next()) {
                    InbursaTO inbursaTO = new InbursaTO();
                    inbursaTO.setFolioInbursa(rs.getString("FOLIO"));
                    inbursaTO.setTelefono(rs.getString("TELEFONO"));
                    inbursaTO.setRegion(rs.getString("REGION"));
                    inbursaTO.setFechaOper(rs.getString("FECHAOPER"));
                    inbursaTO.setProducto(rs.getString("PRODUCTO"));
                    if (inbursaTO.getProducto().equals("RTOTA")) {
                        inbursaTO.setDescripcion("RENTA INBURSA");
                    } else if (inbursaTO.getProducto().equals("C100N")) {
                        inbursaTO.setDescripcion("100 MIN INBURSA");
                    } else if (inbursaTO.getProducto().equals("RCAIN")) {
                        inbursaTO.setDescripcion("RENTA INBURSA");
                    } else if (inbursaTO.getProducto().equals("C101N")) {
                        inbursaTO.setDescripcion("25 MINUTOS INBURSA");
                    } else if (inbursaTO.getProducto().equals("C102N")) {
                        inbursaTO.setDescripcion("50 MINUTOS INBURSA");
                    } else if (inbursaTO.getProducto().equals("C103N")) {
                        inbursaTO.setDescripcion("75 MINUTOS INBURSA");
                    } else if (inbursaTO.getProducto().equals("C104N")) {
                        inbursaTO.setDescripcion("100 MINUTOS INBURSA");
                    }
                    inbursaTO.setTipoMovimiento(rs.getString("TIPOMOVIMIENTO"));
                    if (inbursaTO.getTipoMovimiento().equals("A")) {
                        inbursaTO.setTipoMovimiento("APLICADO");
                    } else if (inbursaTO.getTipoMovimiento().equals("B")) {
                        inbursaTO.setTipoMovimiento("BAJA");
                    } else if (inbursaTO.getTipoMovimiento().equals("N")) {
                        inbursaTO.setTipoMovimiento("NO APLICA");
                    }
                    inbursaTO.setModalidad(rs.getString("MODALIDAD"));
                    if (inbursaTO.getModalidad().equals("P")) {
                        inbursaTO.setModalidad("POSPAGO");
                    } else if (inbursaTO.getModalidad().equals("T")) {
                        inbursaTO.setModalidad("PREPAGO");
                    }
                    inbursaTO.setEstatus(rs.getString("STATUS"));
                    inbursa.add(inbursaTO);
                }
                InbursaTO inbursa1000 = this.obtieneLineaDesc1000(telefono, cnx);
                if (inbursa1000 != null) {
                    boolean reaccion = this.obtieneReaccionDesc1000(telefono, cnx);
                    if (reaccion) {
                        List<InbursaTO> inbursaBono = this.getInbursaDescuento1000(telefono, cnx);
                        if (inbursaBono != null) {
                            for (InbursaTO inbursaDescuento : inbursaBono) {
                                inbursa.add(inbursaDescuento);
                            }
                        }
                    } else {
                        inbursa.add(inbursa1000);
                    }
                }
            }
            catch (Exception e) {
                this.error.info((Object)"Exception.getMovimientosInbursa:", (Throwable)e);
                throw new CAException(-1, "ConsultaInbursaDAO.getMovimientosInbursa[" + e.toString() + "]", e);
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
        return inbursa;
    }

    public List<InbursaTO> getInbursaDescuento1000(String telefono, Connection conn) throws CAException {
        ArrayList<InbursaTO> descuentoInbursa;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        descuentoInbursa = new ArrayList<InbursaTO>();
        try {
            try {
                StringBuffer query = new StringBuffer();
                query.append(" SELECT R.FOLIO,R.IDREGION,R.LINEA,R.CUENTA,R.COMENTARIO,R.IDPRODUCTO,R.MARCA,R.MODELO, ");
                query.append(" R.VALORPTOS,R.DIFPESOS,R.TIPOREDEN,R.BONOINBURSA,R.BONOMARCA,R.BONOROEXT,R.BONOALTOVALOR, ");
                query.append(" TO_CHAR(R.FECHAOPER, 'dd/mm/yyyy') AS FECHAOPER,R.ESTATUS,R.IDPUNTOVTA, ");
                query.append(" I.FOLIO AS FOLIOFIN,I.DESCUENTOINBURSA+ I.DESCUENTOMARCA AS BONODESCUENTO, ");
                query.append(" I.FOLIOINBURSA  FROM ");
                query.append(this.schema_database).append("PTO_TBLREDENCION R, ").append(this.schema_database).append("PTO_TBLBONOINBURSA I");
                query.append(" WHERE R.LINEA=? AND R.CUENTA=I.CUENTA AND R.FOLIO=I.FOLIOREDENCION ");
                query.append(" ORDER BY R.FECHAOPER ");
                stmt = conn.prepareStatement(query.toString());
                stmt.setString(1, telefono);
                rs = stmt.executeQuery();
                while (rs.next()) {
                    InbursaTO inbursaTO = new InbursaTO();
                    inbursaTO.setFolioCA(rs.getString("FOLIO"));
                    inbursaTO.setRegion("R0" + rs.getString("IDREGION"));
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
                    inbursaTO.setFolioInbursa(rs.getString("FOLIOINBURSA"));
                    inbursaTO.setFechaOper(rs.getString("FECHAOPER"));
                    inbursaTO.setEstatus(rs.getString("ESTATUS"));
                    if (inbursaTO.getEstatus().equals("I")) {
                        inbursaTO.setEstatus("REDENCION CANCELADA");
                    } else {
                        inbursaTO.setEstatus("REDENCION REALIZADA");
                    }
                    inbursaTO.setPuntoVenta(rs.getString("IDPUNTOVTA"));
                    inbursaTO.setDescripcion("DESC. EQ INBURSA");
                    inbursaTO.setModalidad("POSPAGO");
                    inbursaTO.setTipoMovimiento("APLICADO");
                    descuentoInbursa.add(inbursaTO);
                }
            }
            catch (Exception e) {
                this.error.info((Object)"ConsultaInbursaDAO.getInbursaDescuento1000:", (Throwable)e);
                throw new CAException(-1, "ConsultaInbursaDAO.getInbursaDescuento1000[" + e.toString() + "]", e);
            }
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                    rs = null;
                }
                catch (Exception var9_12) {}
            }
            if (stmt != null) {
                try {
                    stmt.close();
                    stmt = null;
                }
                catch (Exception var9_13) {}
            }
        }
        return descuentoInbursa;
    }

    private InbursaTO obtieneLineaDesc1000(String telefono, Connection conn) throws CAException {
        InbursaTO inbursaBono;
        PreparedStatement stmt = null;
        ResultSet resultSet = null;
        inbursaBono = null;
        StringBuilder query = new StringBuilder();
        query.append("SELECT FOLIO,TELEFONO,REGION FROM ");
        query.append(this.schema_database).append("PTO_TBLINBURSAPROMO WHERE TELEFONO=? AND PRODUCTO='D1000' ");
        try {
            try {
                stmt = conn.prepareStatement(query.toString());
                stmt.setString(1, telefono);
                resultSet = stmt.executeQuery();
                if (resultSet.next()) {
                    inbursaBono = new InbursaTO();
                    inbursaBono.setFolioInbursa(resultSet.getString("FOLIO"));
                    inbursaBono.setTelefono(resultSet.getString("TELEFONO"));
                    inbursaBono.setRegion(resultSet.getString("REGION"));
                    inbursaBono.setDescripcion("DESC. EQ INBURSA");
                    inbursaBono.setModalidad("POSPAGO");
                }
            }
            catch (Exception e) {
                this.error.info((Object)"ConsultaInbursaDAO.obtieneLineaDesc1000:", (Throwable)e);
                throw new CAException(-1, "ConsultaInbursaDAO.obtieneLineaDesc1000[" + e.toString() + "]", e);
            }
        }
        finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                }
                catch (SQLException var9_11) {}
            }
            if (stmt != null) {
                try {
                    stmt.close();
                }
                catch (SQLException var9_12) {}
            }
        }
        return inbursaBono;
    }

    private boolean obtieneReaccionDesc1000(String telefono, Connection conn) throws CAException {
        boolean reaccion;
        PreparedStatement stmt = null;
        ResultSet resultSet = null;
        reaccion = false;
        StringBuilder query = new StringBuilder();
        query.append("SELECT LINEA FROM ");
        query.append(this.schema_database).append("PTO_TBLBONOINBURSA WHERE LINEA=?");
        try {
            try {
                stmt = conn.prepareStatement(query.toString());
                stmt.setString(1, telefono);
                resultSet = stmt.executeQuery();
                if (resultSet.next()) {
                    reaccion = true;
                }
            }
            catch (Exception e) {
                this.error.info((Object)"ConsultaInbursaDAO.obtieneReaccionDesc1000:", (Throwable)e);
                throw new CAException(-1, "ConsultaInbursaDAO.obtieneReaccionDesc1000[" + e.toString() + "]", e);
            }
        }
        finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                }
                catch (SQLException var9_11) {}
            }
            if (stmt != null) {
                try {
                    stmt.close();
                }
                catch (SQLException var9_12) {}
            }
        }
        return reaccion;
    }
}

