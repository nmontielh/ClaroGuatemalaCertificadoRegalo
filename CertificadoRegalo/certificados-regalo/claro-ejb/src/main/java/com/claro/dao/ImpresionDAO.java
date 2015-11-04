/*
 * Decompiled with CFR 0_102.
 * 
 * Could not load the following classes:
 *  com.claro.exception.CAException
 *  com.claro.transfer.AlianzasTO
 *  com.claro.transfer.ImpresionTO
 *  com.claro.util.Constantes
 *  com.claro.util.Impresion
 *  com.claro.util.ServiceLocator
 *  com.claro.util.Utils
 *  org.apache.log4j.Logger
 */
package com.claro.dao;

import com.claro.exception.CAException;
import com.claro.transfer.AlianzasTO;
import com.claro.transfer.ImpresionTO;
import com.claro.util.Constantes;
import com.claro.util.Impresion;
import com.claro.util.ServiceLocator;
import com.claro.util.Utils;

import java.io.PrintStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import org.apache.log4j.Logger;

public class ImpresionDAO {
    protected final Logger logger = Logger.getLogger((String)"loggerCirculoAzul");
    protected final Logger error = Logger.getLogger((String)"loggerCirculoAzulError");
    private String schema_database;

    public ImpresionDAO() {
        try {
            this.schema_database = ServiceLocator.getInstance().getVariable(ServiceLocator.schema_database);
        }
        catch (Exception e) {
            this.error.error((Object)"ImpresionDAO", (Throwable)e);
        }
    }

    public ImpresionTO datosConstanciaImpresion(String sTelefono, String cuenta, String fechaFolio, String folio, String fechaOperacion, String tipoReden) throws CAException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        StringBuffer query = new StringBuffer();
        query.append(" SELECT C.IDPUNTOVTA,D.PLANANT,D.ADDANT,D.FECPLAZOANT, ");
        query.append(" C.FECHAOPER,D.PUNTOSDISPO,C.VALORPTOS, ");
        query.append(" D.PTSDISPORES,D.PLANNVO,D.ADDNVO,D.FECPLAZOSUG, ");
        query.append(" D.FECHAFOLIO,D.TIPOREDEN,D.FORMARED,C.IDPRODUCTO, ");
        query.append(" C.DESCRIPCION,C.MARCA,C.MODELO,C.PRECIO,C.DESCUENTO, ");
        query.append(" C.PRECIOIVA,C.COMENTARIO,D.ESNIMEIR,D.ESNIMEIP,D.ICCID, ");
        query.append(" C.PTOSMIN , C.BONOPRORR , D.PUNTOSACUM, D.PTSACUMRES,C.FOLIO, ");
        query.append(" C.BONOROEXT , C.BONOALTOVALOR , C.BONOGAP,C.BONOINBURSA,C.BONOMARCA ");
        query.append("  FROM  ").append(this.schema_database).append("PTO_TBLLINEAS A,  ").append(this.schema_database).append("PTO_TBLTOTALES B, ").append(this.schema_database).append("PTO_TBLREDENCION C, ").append(this.schema_database).append("PTO_TBLCONSTANCIA D ");
        query.append(" WHERE ");
        if (sTelefono != null && sTelefono.trim().length() > 0) {
            query.append(" C.LINEA = ? ");
        } else if (cuenta != null && cuenta.trim().length() > 0) {
            query.append(" C.CUENTA = ? ");
        } else {
            query.append("  C.FOLIO = ? ");
        }
        if (folio != null) {
            query.append(" AND C.FOLIO = ? ");
        }
        if (fechaFolio != null) {
            query.append(" AND C.FECHAFOLIO = ? ");
        }
        if (fechaOperacion != null) {
            query.append(" AND C.FECHAOPER=? ");
        }
        if (tipoReden != null) {
            query.append(" AND C.TIPOREDEN=? ");
        }
        query.append(" AND A.CUENTA = B.CUENTA AND A.SECUENCIA = B.SECUENCIA ");
        query.append(" AND A.CUENTA = C.CUENTA AND A.SECUENCIA = C.SECUENCIA ");
        query.append(" AND C.CUENTA = D.CUENTA AND C.SECUENCIA = D.SECUENCIA ");
        query.append(" AND C.FECHAFOLIO = D.FECHAFOLIO ");
        query.append(" AND C.FECHAOPER = D.FECHAOPER ");
        query.append(" AND C.TIPOREDEN = D.TIPOREDEN ");
        query.append(" AND C.ESTATUS = 'A' AND D.ESTATUS = 'A' ");
        query.append(" GROUP BY C.IDPUNTOVTA,D.PLANANT,D.ADDANT,D.FECPLAZOANT, ");
        query.append(" C.FECHAOPER, D.PUNTOSDISPO,C.VALORPTOS,D.PTSDISPORES,D.PLANNVO,ADDNVO, ");
        query.append(" D.FECPLAZOSUG,D.FECHAFOLIO,D.TIPOREDEN,D.FORMARED,C.IDPRODUCTO, ");
        query.append(" C.DESCRIPCION,C.MARCA,C.MODELO,C.PRECIO,C.DESCUENTO, ");
        query.append(" C.PRECIOIVA,C.COMENTARIO,D.ESNIMEIR,D.ESNIMEIP,D.ICCID, ");
        query.append(" C.PTOSMIN , C.BONOPRORR , D.PUNTOSACUM, D.PTSACUMRES,C.FOLIO, ");
        query.append(" C.BONOROEXT , C.BONOALTOVALOR , C.BONOGAP,C.BONOINBURSA,C.BONOMARCA ");
        query.append(" ORDER BY D.FECPLAZOANT ");
        ImpresionTO impresionTO = new ImpresionTO();
        try {
            connection = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
            statement = connection.prepareStatement(query.toString());
            int contador = 1;
            if (sTelefono != null && sTelefono.trim().length() > 0) {
                statement.setString(contador++, sTelefono);
            } else if (cuenta != null && cuenta.trim().length() > 0) {
                statement.setString(contador++, cuenta);
            } else {
                statement.setString(contador++, folio);
            }
            if (folio != null) {
                statement.setString(contador++, folio);
            }
            if (fechaFolio != null) {
                statement.setTimestamp(contador++, new Timestamp(new Long(fechaFolio)));
            }
            if (fechaOperacion != null) {
                statement.setTimestamp(contador++, new Timestamp(new Long(fechaOperacion)));
            }
            if (tipoReden != null) {
                statement.setString(contador++, tipoReden);
            }
            if ((resultSet = statement.executeQuery()).next()) {
                impresionTO.setIdPuntoVenta(resultSet.getString("IDPUNTOVTA"));
                impresionTO.setPlanAnt(resultSet.getString("PLANANT"));
                impresionTO.setAddAnt(resultSet.getInt("ADDANT"));
                impresionTO.setFechaPlazoAnt((java.util.Date)resultSet.getDate("FECPLAZOANT"));
                impresionTO.setFechaOperacion((java.util.Date)resultSet.getDate("FECHAOPER"));
                impresionTO.setPtosDisp(resultSet.getInt("PUNTOSDISPO"));
                impresionTO.setValorPuntos(resultSet.getInt("VALORPTOS"));
                impresionTO.setPtsDispRestantes(resultSet.getInt("PTSDISPORES"));
                impresionTO.setPlanNuevo(resultSet.getString("PLANNVO"));
                impresionTO.setAddNuevo(resultSet.getInt("ADDNVO"));
                impresionTO.setFechaPlazoSeg((java.util.Date)resultSet.getDate("FECPLAZOSUG"));
                impresionTO.setFechaFolio(resultSet.getTimestamp("FECHAFOLIO"));
                impresionTO.setTipoReden(resultSet.getString("TIPOREDEN"));
                String formaReden = resultSet.getString("FORMARED");
                impresionTO.setIdProducto(resultSet.getString("IDPRODUCTO"));
                impresionTO.setDescripcion(resultSet.getString("DESCRIPCION"));
                impresionTO.setMarca(resultSet.getString("MARCA"));
                impresionTO.setModelo(resultSet.getString("MODELO"));
                impresionTO.setPrecio(resultSet.getBigDecimal("PRECIO"));
                impresionTO.setDescuento(resultSet.getBigDecimal("DESCUENTO"));
                impresionTO.setPrecioIva(resultSet.getBigDecimal("PRECIOIVA"));
                impresionTO.setComentario(resultSet.getString("COMENTARIO"));
                impresionTO.setEsnImeiR(resultSet.getString("ESNIMEIR"));
                impresionTO.setEsnImeiP(resultSet.getString("ESNIMEIP"));
                impresionTO.setIccid(resultSet.getString("ICCID"));
                impresionTO.setPtsMinimos(resultSet.getInt("PTOSMIN"));
                impresionTO.setBonoProrr(resultSet.getInt("BONOPRORR"));
                impresionTO.setPtsAcum(resultSet.getInt("PUNTOSACUM"));
                impresionTO.setPtsAcumRes(resultSet.getInt("PTSACUMRES"));
                impresionTO.setFolio(resultSet.getString("FOLIO"));
                impresionTO.setFormaReden(Impresion.getFormaRed((String)formaReden));
                impresionTO.setTipoRedenDB(Impresion.getTipoRed((String)impresionTO.getTipoReden()));
                impresionTO.setPrecioIvaFormato(Utils.setFormatoDecimalPrecio((String)resultSet.getString("PRECIOIVA")));
                impresionTO.setPrecioFormato(Utils.setFormatoDecimalPrecio((String)resultSet.getString("PRECIO")));
                impresionTO.setDescuentoFormato(Utils.setFormatoDecimalPrecio((String)resultSet.getString("DESCUENTO")));
                impresionTO.setBonosRoext(resultSet.getInt("BONOROEXT"));
                impresionTO.setBonosAltoValor(resultSet.getInt("BONOALTOVALOR"));
                impresionTO.setBonosGap(resultSet.getInt("BONOGAP"));
                impresionTO.setInbursaFormato(Utils.setFormatoDecimalPrecio((String)resultSet.getString("BONOINBURSA")));
                impresionTO.setMarcaFormato(Utils.setFormatoDecimalPrecio((String)resultSet.getString("BONOMARCA")));
                impresionTO.setPtsAcumResCF(Utils.setFormatoPtos((int)impresionTO.getPtsAcumRes()));
                impresionTO.setPtosDispCF(Utils.setFormatoPtos((int)impresionTO.getPtosDisp()));
                impresionTO.setPtsDispRestantesCF(Utils.setFormatoPtos((int)impresionTO.getPtsDispRestantes()));
                impresionTO.setValorPuntosCF(Utils.setFormatoPtos((int)impresionTO.getValorPuntos()));
                impresionTO.setPtsMinimosCF(Utils.setFormatoPtos((int)impresionTO.getPtsMinimos()));
                impresionTO.setPtsAcumCF(Utils.setFormatoPtos((int)impresionTO.getPtsAcum()));
                impresionTO.setPtsTransferidosCF(Utils.setFormatoPtos((int)impresionTO.getPtsTransferidos()));
                impresionTO.setPtsMillasCF(Utils.setFormatoPtos((int)impresionTO.getPtsMillas()));
                impresionTO.setPtsCanjeadoCF(Utils.setFormatoPtos((int)impresionTO.getPtsCanjeado()));
                impresionTO.agregaMensaje(0, "Proceso Exitoso");
                ImpresionTO impresionTO2 = impresionTO;
                return impresionTO2;
            }
            try {
                throw new CAException(1, "No existe ninguna redencion.");
            }
            catch (Exception e) {
                throw new CAException(-1, "ImpresionDAO.datosConstanciaImpresion[" + e.toString() + "]", e);
            }
        } catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                    resultSet = null;
                }
                catch (Exception var16_17) {}
            }
            if (statement != null) {
                try {
                    statement.close();
                    statement = null;
                }
                catch (Exception var16_18) {}
            }
            if (connection != null) {
                try {
                    connection.close();
                    connection = null;
                }
                catch (Exception var16_19) {}
            }
        }
		return impresionTO;
    }

    public ImpresionTO datosConstanciaBonoInbursa(String sTelefono, String cuenta) throws CAException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        StringBuffer query = new StringBuffer();
        query.append(" SELECT  FOLIO,LINEA,CUENTA,DESCUENTOINBURSA,DESCUENTOMARCA,FECHAIMPRESION,FECHAEXPIRACION,CAC,FOLIOREDENCION ");
        query.append("  FROM  ").append(this.schema_database).append("PTO_TBLBONOINBURSA ");
        query.append(" WHERE FECHAEXPIRACION IS NOT NULL AND LINEA = ? AND CUENTA = ? ");
        try {
            connection = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
            statement = connection.prepareStatement(query.toString());
            statement.setString(1, sTelefono);
            statement.setString(2, cuenta);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                ImpresionTO impresionTO = new ImpresionTO();
                impresionTO.setFolio(resultSet.getString("FOLIO"));
                impresionTO.setTelefono(resultSet.getString("LINEA"));
                impresionTO.setCuenta(resultSet.getString("CUENTA"));
                impresionTO.setInbursaFormato(Utils.setFormatoDecimal((String)resultSet.getString("DESCUENTOINBURSA")));
                impresionTO.setMarcaFormato(Utils.setFormatoDecimal((String)resultSet.getString("DESCUENTOMARCA")));
                impresionTO.setFechaOperacion((java.util.Date)resultSet.getDate("FECHAIMPRESION"));
                impresionTO.setFechaPlazoSeg((java.util.Date)resultSet.getDate("FECHAEXPIRACION"));
                impresionTO.setIdPuntoVenta(resultSet.getString("CAC"));
                impresionTO.setFolioRedencion(resultSet.getString("FOLIOREDENCION"));
                impresionTO.agregaMensaje(0, "Proceso Exitoso");
                ImpresionTO impresionTO2 = impresionTO;
                return impresionTO2;
            }
            try {
                throw new CAException(1, "No existe ninguna redencion.");
            }
            catch (Exception e) {
                throw new CAException(-1, "ImpresionDAO.datosConstanciaImpresion[" + e.toString() + "]", e);
            }
        } catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                    resultSet = null;
                }
                catch (Exception var10_11) {}
            }
            if (statement != null) {
                try {
                    statement.close();
                    statement = null;
                }
                catch (Exception var10_12) {}
            }
            if (connection != null) {
                try {
                    connection.close();
                    connection = null;
                }
                catch (Exception var10_13) {}
            }
        }
		return null;
    }

    public ArrayList<ImpresionTO> detalleRedenImp(String sTelefono, String cuenta, String sFecha) throws CAException {
        ArrayList<ImpresionTO> impresionDetalleRed;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        StringBuffer query = new StringBuffer();
        java.util.Date dFecha = null;
        impresionDetalleRed = new ArrayList<ImpresionTO>();
        query.append(" SELECT  R.FECHAFOLIO, R.TIPOREDEN, R.MARCA, R.MODELO, R.VALORPTOS,R.PRECIOIVA, R.IDUSUARIO, R.IDPRODUCTO, L.CUENTA,L.IDREGION,R.FOLIO ");
        query.append(" FROM  ").append(this.schema_database).append("PTO_TBLREDENCION R,");
        query.append(this.schema_database).append("PTO_TBLLINEAS L ");
        query.append(" WHERE ");
        if (sTelefono != null && sTelefono.length() > 0) {
            query.append(" R.LINEA = ? ");
        } else {
            query.append(" R.CUENTA = ? ");
        }
        query.append(" AND R.CUENTA=L.CUENTA  ");
        query.append(" AND FECHAOPER =  ? ");
        query.append(" AND R.ESTATUS='A'  ");
        query.append(" ORDER  BY R.FECHAFOLIO ");
        try {
            try {
                connection = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                statement = connection.prepareStatement(query.toString());
                if (sTelefono != null && sTelefono.length() > 0) {
                    statement.setString(1, sTelefono);
                } else {
                    statement.setString(1, cuenta);
                }
                if (sFecha != null) {
                    dFecha = Constantes.DATEFORMATdd_MM_YYYY_2.parse(sFecha);
                }
                if (dFecha != null) {
                    statement.setDate(2, new Date(dFecha.getTime()));
                }
                resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    ImpresionTO impresionTO = new ImpresionTO();
                    impresionTO.setFechaFolio(resultSet.getTimestamp("FECHAFOLIO"));
                    impresionTO.setTipoReden(resultSet.getString("TIPOREDEN"));
                    impresionTO.setMarca(resultSet.getString("MARCA"));
                    impresionTO.setModelo(resultSet.getString("MODELO"));
                    impresionTO.setValorPuntos(resultSet.getInt("VALORPTOS"));
                    impresionTO.setPrecioIva(resultSet.getBigDecimal("PRECIOIVA"));
                    impresionTO.setIdUsuario(resultSet.getString("IDUSUARIO"));
                    impresionTO.setIdProducto(resultSet.getString("IDPRODUCTO"));
                    impresionTO.setCuenta(resultSet.getString("CUENTA"));
                    impresionTO.setIdRegion(resultSet.getInt("IDREGION"));
                    impresionTO.setFolio(resultSet.getString("FOLIO"));
                    impresionTO.setPrecioIvaFormato(Utils.setFormatoDecimal((String)resultSet.getString("PRECIOIVA")));
                    impresionTO.setPrecioIvaFormato(Utils.setFormatoDecimalPrecio((String)impresionTO.getPrecioIvaFormato()));
                    impresionTO.setValorPuntosCF(Utils.setFormatoPtos((int)impresionTO.getValorPuntos()));
                    impresionDetalleRed.add(impresionTO);
                }
            }
            catch (SQLException e) {
                throw new CAException(-1, "SQLException.detalleRedenImp [" + e.toString() + "]", (Exception)e);
            }
            catch (Exception e) {
                throw new CAException(-1, "ImpresionDAO.detalleRedenImp[" + e.toString() + "]", e);
            }
        }
        finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                    resultSet = null;
                }
                catch (Exception var12_17) {}
            }
            if (statement != null) {
                try {
                    statement.close();
                    statement = null;
                }
                catch (Exception var12_18) {}
            }
            if (connection != null) {
                try {
                    connection.close();
                    connection = null;
                }
                catch (Exception var12_19) {}
            }
        }
        return impresionDetalleRed;
    }

    public ArrayList<ImpresionTO> detalleBonosInbursa(String sTelefono, String cuenta) throws CAException {
        ArrayList<ImpresionTO> impresionDetalleBonoInbursa;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        StringBuffer query = new StringBuffer();
        impresionDetalleBonoInbursa = new ArrayList<ImpresionTO>();
        query.append(" SELECT  FOLIO,LINEA,CUENTA,DESCUENTOINBURSA,DESCUENTOMARCA,FECHAIMPRESION,FECHAEXPIRACION,CAC,FOLIOREDENCION ");
        query.append(" FROM  ").append(this.schema_database).append("PTO_TBLBONOINBURSA ");
        query.append(" WHERE ");
        if (sTelefono != null && sTelefono.length() > 0) {
            query.append(" LINEA = ? ");
        } else {
            query.append(" CUENTA = ? ");
        }
        query.append(" AND FECHAEXPIRACION IS NOT NULL AND ( DESCUENTOINBURSA > 0 OR DESCUENTOMARCA > 0 ) ");
        try {
            try {
                connection = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                statement = connection.prepareStatement(query.toString());
                if (sTelefono != null && sTelefono.length() > 0) {
                    statement.setString(1, sTelefono);
                } else {
                    statement.setString(1, cuenta);
                }
                resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    ImpresionTO impresionTO = new ImpresionTO();
                    impresionTO.setFolio(resultSet.getString("FOLIO"));
                    impresionTO.setTelefono(resultSet.getString("LINEA"));
                    impresionTO.setCuenta(resultSet.getString("CUENTA"));
                    impresionTO.setInbursaFormato(Utils.setFormatoDecimal((String)resultSet.getString("DESCUENTOINBURSA")));
                    impresionTO.setMarcaFormato(Utils.setFormatoDecimal((String)resultSet.getString("DESCUENTOMARCA")));
                    impresionTO.setFechaOperacion((java.util.Date)resultSet.getDate("FECHAIMPRESION"));
                    impresionTO.setFechaPlazoSeg((java.util.Date)resultSet.getDate("FECHAEXPIRACION"));
                    impresionTO.setIdPuntoVenta(resultSet.getString("CAC"));
                    impresionTO.setFolioRedencion(resultSet.getString("FOLIOREDENCION"));
                    impresionDetalleBonoInbursa.add(impresionTO);
                }
            }
            catch (SQLException e) {
                throw new CAException(-1, "SQLException.detalleRedenImp [" + e.toString() + "]", (Exception)e);
            }
            catch (Exception e) {
                throw new CAException(-1, "ImpresionDAO.detalleRedenImp[" + e.toString() + "]", e);
            }
        }
        finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                    resultSet = null;
                }
                catch (Exception var10_15) {}
            }
            if (statement != null) {
                try {
                    statement.close();
                    statement = null;
                }
                catch (Exception var10_16) {}
            }
            if (connection != null) {
                try {
                    connection.close();
                    connection = null;
                }
                catch (Exception var10_17) {}
            }
        }
        return impresionDetalleBonoInbursa;
    }

    public ImpresionTO obtieneConstanciaAlianzas(String sFecha, String sCuenta, int iSecuencia, int tipoAlianza, String sFechaFolio, String sLinea) throws CAException {
        ImpresionTO impresionTO;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        StringBuffer query = new StringBuffer();
        java.util.Date dFecha = null;
        impresionTO = new ImpresionTO();
        query.append(" SELECT A.CUENTA, A.FECHAOPER, A.CTAALIANZA, A.PTRANSF, A.MILLAS, ");
        query.append(" A.COMENTARIOS, A.FOLIO, A.VALCUPONORIG, A.FECHAFOLIO, A.ARCHIVOSALIDA, ");
        query.append(" A.IDPUNTOVTA, A.PTSDISPORES ");
        query.append(" FROM  ").append(this.schema_database).append("PTO_TBLALIANZAS  A, ").append(this.schema_database).append("PTO_TBLLINEAS L");
        query.append(" WHERE ");
        if (sLinea == null) {
            query.append(" A.CUENTA = ? ");
        } else {
            query.append(" AND L.Linea = ? ");
        }
        query.append(" AND A.SECUENCIA = ? ");
        query.append(" AND A.STATUSTRANS NOT IN (0,1,2,3) ");
        query.append(" AND A.IDCUENTA = ? AND A.ESTATUS = 'A' ");
        if (sFecha != null) {
            query.append(" AND A.FECHAOPER = ? ");
        }
        query.append(" AND A.cuenta = L.CUENTA ");
        query.append(" ORDER BY FECHAOPER DESC ");
        try {
            try {
                connection = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                statement = connection.prepareStatement(query.toString());
                if (sLinea == null) {
                    statement.setString(1, sCuenta);
                } else {
                    statement.setString(1, sLinea);
                }
                statement.setInt(2, iSecuencia);
                statement.setInt(3, tipoAlianza);
                if (sFecha != null) {
                    dFecha = Constantes.DATEFORMATdd_MM_YYYY_2.parse(sFecha);
                    statement.setDate(4, new Date(dFecha.getTime()));
                }
                resultSet = statement.executeQuery();
                int contador = 0;
                ArrayList<AlianzasTO> alianzasTO = new ArrayList<AlianzasTO>();
                while (resultSet.next()) {
                    if (contador == 0) {
                        impresionTO.setCuenta(resultSet.getString("CUENTA"));
                        impresionTO.setFechaOperacion((java.util.Date)resultSet.getDate("FECHAOPER"));
                        impresionTO.setPtsDispRestantes(resultSet.getInt("PTSDISPORES"));
                        impresionTO.setPtsTransferidos(resultSet.getInt("PTRANSF"));
                        impresionTO.setIdPuntoVenta(resultSet.getString("IDPUNTOVTA"));
                        ++contador;
                    }
                    int ptsTranferidos = resultSet.getInt("PTRANSF");
                    int ptsMillas = resultSet.getInt("MILLAS");
                    impresionTO.setPtsCanjeado(impresionTO.getPtsCanjeado() + ptsTranferidos);
                    impresionTO.setPtsMillas(impresionTO.getPtsMillas() + ptsMillas);
                    AlianzasTO alianzaTO = new AlianzasTO();
                    alianzaTO.setCuenta(resultSet.getString("CUENTA"));
                    alianzaTO.setFechaOperacion(resultSet.getDate("FECHAOPER"));
                    alianzaTO.setCuentaAlianza(resultSet.getString("CTAALIANZA"));
                    alianzaTO.setPtsTransferidos(ptsTranferidos);
                    alianzaTO.setMillas(ptsMillas);
                    alianzaTO.setComentario(resultSet.getString("COMENTARIOS"));
                    alianzaTO.setFolio(resultSet.getString("FOLIO"));
                    alianzaTO.setValorCuponOrig(resultSet.getInt("VALCUPONORIG"));
                    alianzaTO.setFechaFolio(resultSet.getTimestamp("FECHAFOLIO"));
                    alianzaTO.setArchivoSalida(resultSet.getString("ARCHIVOSALIDA"));
                    alianzasTO.add(alianzaTO);
                }
                impresionTO.setPtsCanjeadoCF(Utils.setFormatoPtos((int)impresionTO.getPtsCanjeado()));
                impresionTO.setPtsMillasCF(Utils.setFormatoPtos((int)impresionTO.getPtsMillas()));
                impresionTO.setPtosDispCF(Utils.setFormatoPtos((int)impresionTO.getPtosDisp()));
                impresionTO.setAlianzas(alianzasTO);
            }
            catch (SQLException e) {
                throw new CAException(-1, "SQLException.obtieneConstanciaAlianzas [" + e.toString() + "]", (Exception)e);
            }
            catch (Exception e) {
                throw new CAException(-1, "ImpresionDAO.obtieneConstanciaAlianzas[" + e.toString() + "]", e);
            }
        }
        finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                    resultSet = null;
                }
                catch (Exception var19_24) {}
            }
            if (statement != null) {
                try {
                    statement.close();
                    statement = null;
                }
                catch (Exception var19_25) {}
            }
            if (connection != null) {
                try {
                    connection.close();
                    connection = null;
                }
                catch (Exception var19_26) {}
            }
        }
        return impresionTO;
    }

    public ArrayList<ImpresionTO> detalleImpresionAlianzas(String sFecha, String sCuenta, int iSecuencia, int tipoAlianza, String sFolio) throws CAException {
        ArrayList<ImpresionTO> impresionDetAlianza;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        StringBuffer query = new StringBuffer();
        impresionDetAlianza = new ArrayList<ImpresionTO>();
        query.append(" SELECT A.CUENTA, A.FECHAOPER, A.PTRANSF, A.VALCUPONORIG,  ");
        query.append(" A.COMENTARIOS, B.IDPRODUCTO, B.DESCRIPCION, A.MILLAS, A.IDREF, A.IDPUNTOVTA,C.LINEA ");
        query.append("  FROM  ").append(this.schema_database).append("PTO_TBLALIANZAS A,  ").append(this.schema_database).append("PTO_CTLPROMOCIONES B, ").append(this.schema_database).append("PTO_TBLLINEAS C  ");
        query.append(" WHERE A.CUENTA = C.CUENTA AND A.SECUENCIA = C.SECUENCIA AND  ");
        query.append(" C.IDREGION = B.IDREGION ");
        query.append(" AND A.CUENTA = ? AND A.SECUENCIA = ? AND A.IDCUENTA = ? ");
        query.append(" AND A.ARCHIVOSALIDA = B.IDPRODUCTO  ");
        if (tipoAlianza == 2) {
            query.append(" AND FOLIO = ? ");
        }
        query.append(" ORDER BY FECHAOPER DESC  ");
        try {
            try {
                connection = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                statement = connection.prepareStatement(query.toString());
                statement.setString(1, sCuenta);
                statement.setInt(2, iSecuencia);
                statement.setInt(3, tipoAlianza);
                if (tipoAlianza == 2) {
                    statement.setString(4, sFolio);
                }
                resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    ImpresionTO impresionTO = new ImpresionTO();
                    ImpresionTO tmp = new ImpresionTO();
                    tmp = this.datosImpresion(resultSet.getString("LINEA"), sFolio);
                    impresionTO.setCuenta(resultSet.getString("CUENTA"));
                    impresionTO.setFechaOperacion((java.util.Date)resultSet.getDate("FECHAOPER"));
                    impresionTO.setPtsTransferidos(resultSet.getInt("PTRANSF"));
                    impresionTO.setValorCuponOrig(resultSet.getInt("VALCUPONORIG"));
                    impresionTO.setComentario(resultSet.getString("COMENTARIOS"));
                    impresionTO.setIdProducto(resultSet.getString("IDPRODUCTO"));
                    impresionTO.setDescripcion(resultSet.getString("DESCRIPCION"));
                    impresionTO.setPtsMillas(resultSet.getInt("MILLAS"));
                    impresionTO.setIdReferencia(resultSet.getString("IDREF"));
                    impresionDetAlianza.add(impresionTO);
                }
            }
            catch (SQLException e) {
                throw new CAException(-1, "SQLException.detalleImpresionAlianzas [" + e.toString() + "]", (Exception)e);
            }
            catch (Exception e) {
                throw new CAException(-1, "ImpresionDAO.detalleImpresionAlianzas[" + e.toString() + "]", e);
            }
        }
        finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                    resultSet = null;
                }
                catch (Exception var14_19) {}
            }
            if (statement != null) {
                try {
                    statement.close();
                    statement = null;
                }
                catch (Exception var14_20) {}
            }
            if (connection != null) {
                try {
                    connection.close();
                    connection = null;
                }
                catch (Exception var14_21) {}
            }
        }
        return impresionDetAlianza;
    }

    public ImpresionTO datosImpresion(String sTelefono, String fechaFolio) throws CAException {
        ImpresionTO impresionTO;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        StringBuffer query = new StringBuffer();
        query.append(" SELECT C.IDPUNTOVTA, MAX(C.FECHAOPER) FECHAOPER,  ");
        query.append(" C.PUNTOSACUM, C.PUNTOSDISPO, C.PTRANSF, C.PTSACUMRES,  ");
        query.append(" C.PTSDISPORES,C.FECHAFOLIO, C.OPCION, C.CUENTA, C.SECUENCIA   ");
        query.append("  FROM  ").append(this.schema_database).append("PTO_TBLLINEAS A,  ").append(this.schema_database).append("PTO_TBLTOTALES B, ").append(this.schema_database).append("PTO_TBLALIANZAS  C ");
        query.append(" WHERE A.LINEA = ? ");
        query.append(" AND A.CUENTA = B.CUENTA AND A.SECUENCIA = B.SECUENCIA  ");
        query.append(" AND A.CUENTA = C.CUENTA AND A.SECUENCIA = C.SECUENCIA  ");
        query.append(" AND C.ESTATUS = 'A'\tAND C.FECHAFOLIO = ?  ");
        query.append(" GROUP BY C.IDPUNTOVTA, C.PUNTOSACUM, C.PUNTOSDISPO,  ");
        query.append(" C.PTRANSF, C.PTSACUMRES, C.PTSDISPORES, C.FECHAFOLIO,  ");
        query.append(" C.OPCION, C.CUENTA, C.SECUENCIA  ");
        query.append(" ORDER BY C.FECHAFOLIO ");
        impresionTO = new ImpresionTO();
        try {
            connection = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
            statement = connection.prepareStatement(query.toString());
            statement.setString(1, sTelefono);
            statement.setTimestamp(2, new Timestamp(new Long(fechaFolio)));
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                impresionTO.setIdPuntoVenta(resultSet.getString("IDPUNTOVTA"));
                impresionTO.setFechaOperacion((java.util.Date)resultSet.getDate("FECHAOPER"));
                impresionTO.setPtsAcum(resultSet.getInt("PUNTOSACUM"));
                impresionTO.setPtosDisp(resultSet.getInt("PUNTOSDISPO"));
                impresionTO.setPtsTransferidos(resultSet.getInt("PTRANSF"));
                impresionTO.setPtsAcumRes(resultSet.getInt("PTSACUMRES"));
                impresionTO.setPtsDispRestantes(resultSet.getInt("PTSDISPORES"));
                impresionTO.setFechaFolio(resultSet.getTimestamp("FECHAFOLIO"));
                impresionTO.setOpcion(resultSet.getInt("OPCION"));
                impresionTO.setCuenta(resultSet.getString("CUENTA"));
                impresionTO.setSecuencia(resultSet.getInt("SECUENCIA"));
                ImpresionTO impresionTO2 = impresionTO;
                return impresionTO2;
            }
            try {
                impresionTO.agregaMensaje(1, "No existe ninguna redencion para el telefono y la fecha indicados.");
            }
            catch (Exception e) {
                throw new CAException(-1, "ImpresionDAO.datosImpresion[" + e.toString() + "]", e);
            }
        } catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                    resultSet = null;
                }
                catch (Exception var11_9) {}
            }
            if (statement != null) {
                try {
                    statement.close();
                    statement = null;
                }
                catch (Exception var11_10) {}
            }
            if (connection != null) {
                try {
                    connection.close();
                    connection = null;
                }
                catch (Exception var11_11) {}
            }
        }
        return impresionTO;
    }

    public ArrayList<ImpresionTO> detalleEquipo(String sFecha, String sTipoRed, String sFechaFolio, String sCuenta, String sTelefono) throws CAException {
        ArrayList<ImpresionTO> impresionDetalleEquipo;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        StringBuffer query = new StringBuffer();
        java.util.Date dFecha = null;
        java.util.Date dFechaFolio = null;
        impresionDetalleEquipo = new ArrayList<ImpresionTO>();
        query.append(" SELECT DESCRIPCION, MARCA, MODELO, VALORPTOS, PRECIOIVA, ");
        query.append(" COMENTARIO, TIPOREDEN, FECHAFOLIO, IDPRODUCTO, IDUSUARIO, DESCUENTO,PRECIO ");
        query.append(" FROM  ").append(this.schema_database).append("PTO_TBLREDENCION ");
        query.append(" WHERE  CUENTA  = ? ");
        query.append(" AND LINEA = ? ");
        query.append(" AND FECHAOPER =  ? ");
        query.append(" AND ESTATUS='A'  ");
        query.append(" AND  TIPOREDEN = ? ");
        if (sFechaFolio != null) {
            query.append(" AND FECHAFOLIO = ? ");
        }
        try {
            try {
                connection = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                statement = connection.prepareStatement(query.toString());
                statement.setString(1, sCuenta);
                statement.setString(2, sTelefono);
                if (sFecha != null) {
                    dFecha = Constantes.DATEFORMATyyyy_MM_dd.parse(sFecha);
                }
                if (dFecha != null) {
                    statement.setDate(3, new Date(dFecha.getTime()));
                }
                statement.setString(4, sTipoRed);
                if (sFechaFolio != null) {
                    dFechaFolio = Constantes.DATEFORMTAyyyy_MM_ddHHmmss.parse(sFechaFolio);
                }
                if (dFechaFolio != null) {
                    statement.setDate(5, new Date(dFechaFolio.getTime()));
                }
                resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    ImpresionTO impresionTO = new ImpresionTO();
                    impresionTO.setDescripcion(resultSet.getString("DESCRIPCION"));
                    impresionTO.setModelo(resultSet.getString("MARCA"));
                    impresionTO.setMarca(resultSet.getString("MODELO"));
                    impresionTO.setValorPuntos(resultSet.getInt("VALORPTOS"));
                    impresionTO.setPrecioIvaFormato(Utils.setFormatoDecimal((String)resultSet.getString("PRECIOIVA")));
                    impresionTO.setComentario(resultSet.getString("COMENTARIO"));
                    impresionTO.setTipoReden(resultSet.getString("TIPOREDEN"));
                    impresionTO.setFechaFolio(resultSet.getTimestamp("FECHAFOLIO"));
                    impresionTO.setIdProducto(resultSet.getString("IDPRODUCTO"));
                    impresionTO.setIdUsuario(resultSet.getString("IDUSUARIO"));
                    impresionTO.setDescuentoFormato(Utils.setFormatoDecimal((String)resultSet.getString("DESCUENTO")));
                    impresionTO.setPrecioFormato(Utils.setFormatoDecimal((String)resultSet.getString("PRECIO")));
                    impresionDetalleEquipo.add(impresionTO);
                }
            }
            catch (SQLException e) {
                throw new CAException(-1, "SQLException.impresionDetalleEquipo [" + e.toString() + "]", (Exception)e);
            }
            catch (Exception e) {
                throw new CAException(-1, "ImpresionDAO.impresionDetalleEquipo[" + e.toString() + "]", e);
            }
        }
        finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                    resultSet = null;
                }
                catch (Exception var15_20) {}
            }
            if (statement != null) {
                try {
                    statement.close();
                    statement = null;
                }
                catch (Exception var15_21) {}
            }
            if (connection != null) {
                try {
                    connection.close();
                    connection = null;
                }
                catch (Exception var15_22) {}
            }
        }
        return impresionDetalleEquipo;
    }

    public ImpresionTO consultaCertificados(String lFecha, String cuenta, String sec) throws CAException {
        ImpresionTO impresionTO;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        StringBuffer query = new StringBuffer();
        ArrayList<AlianzasTO> alianzasArray = new ArrayList<AlianzasTO>();
        impresionTO = new ImpresionTO();
        query.append("SELECT a.Folio, a.Fechaoper, a.Ptransf, a.ValCuponOrig, a.Comentarios ");
        query.append("FROM ").append(this.schema_database).append("PTO_TBLALIANZAS a ");
        query.append("WHERE a.Cuenta = ?");
        query.append(" AND a.Secuencia = ? AND a.Idcuenta = 2 AND");
        query.append(" a.Estatus = 'A' AND A.Statustrans NOT IN (0,1,2,3) ");
        if (!(lFecha == null || "".equals(lFecha))) {
            query.append(" AND FechaOper = ?");
        }
        query.append("ORDER BY a.Fechaoper DESC");
        try {
            try {
                connection = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                statement = connection.prepareStatement(query.toString());
                statement.setString(1, cuenta);
                statement.setString(2, sec);
                if (!(lFecha == null || "".equals(lFecha))) {
                    Calendar fechaOperacion = Calendar.getInstance();
                    int dia = Integer.parseInt(lFecha.substring(0, lFecha.indexOf("/")));
                    int mes = Integer.parseInt(lFecha.substring(lFecha.indexOf("/") + 1, lFecha.lastIndexOf("/"))) - 1;
                    int anio = Integer.parseInt(lFecha.substring(lFecha.lastIndexOf("/") + 1, lFecha.length()));
                    fechaOperacion.set(5, dia);
                    fechaOperacion.set(2, mes);
                    fechaOperacion.set(1, anio);
                    System.out.println(fechaOperacion.getTime());
                    statement.setDate(3, new Date(fechaOperacion.getTimeInMillis()));
                }
                resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    AlianzasTO alianza = new AlianzasTO();
                    alianza.setFolio(resultSet.getString("Folio"));
                    alianza.setFechaOperacion(resultSet.getDate("Fechaoper"));
                    alianza.setPtsTransferidos(resultSet.getInt("Ptransf"));
                    alianza.setValorCuponOrig(resultSet.getInt("ValCuponOrig"));
                    alianza.setComentario(resultSet.getString("Comentarios"));
                    alianzasArray.add(alianza);
                }
                impresionTO.setAlianzas(alianzasArray);
            }
            catch (SQLException e) {
                throw new CAException(-1, "SQLException.consultaCertificados [" + e.toString() + "]", (Exception)e);
            }
            catch (Exception e) {
                throw new CAException(-1, "ImpresionDAO.consultaCertificados[" + e.toString() + "]", e);
            }
        }
        finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                    resultSet = null;
                }
                catch (Exception var15_20) {}
            }
            if (statement != null) {
                try {
                    statement.close();
                    statement = null;
                }
                catch (Exception var15_21) {}
            }
            if (connection != null) {
                try {
                    connection.close();
                    connection = null;
                }
                catch (Exception var15_22) {}
            }
        }
        return impresionTO;
    }

    public ImpresionTO obtieneCertificado(String folio, String cuenta, String sec) throws CAException {
        ImpresionTO impresionTO;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        StringBuffer query = new StringBuffer();
        ArrayList<AlianzasTO> alianzasArray = new ArrayList<AlianzasTO>();
        impresionTO = new ImpresionTO();
        query.append("SELECT a.Folio, a.Fechaoper, a.Ptransf, a.ValCuponOrig, ");
        query.append("a.Vigenciamax, b.NOMBRE,b.APATERNO, b.AMATERNO ");
        query.append("FROM ").append(this.schema_database).append("PTO_TBLALIANZAS a,");
        query.append(this.schema_database).append("PTO_TBLDETALLEALIANZA b ");
        query.append("WHERE a.Cuenta = ? AND a.Secuencia = ? AND a.Idcuenta = 2 AND ");
        query.append("a.Estatus = 'A' AND Statustrans NOT IN (0,1,2,3) AND a.Folio = ? ");
        query.append("AND a.CTAALIANZA = b.CTAALIANZA AND a.IDCUENTA = b.IDCUENTA ");
        query.append("AND b.STATUSALIANZA = 'A' AND a.CUENTA = b.CUENTA AND a.SECUENCIA = b.SECUENCIA ");
        try {
            try {
                connection = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                statement = connection.prepareStatement(query.toString());
                statement.setString(1, cuenta);
                statement.setString(2, sec);
                statement.setString(3, folio);
                resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    AlianzasTO alianza = new AlianzasTO();
                    alianza.setFolio(resultSet.getString("Folio"));
                    alianza.setFechaOperacion(resultSet.getDate("Fechaoper"));
                    alianza.setPtsTransferidos(resultSet.getInt("Ptransf"));
                    alianza.setValorCuponOrig(resultSet.getInt("ValCuponOrig"));
                    alianza.setVigenciaMax(resultSet.getDate("Vigenciamax"));
                    alianza.setNombre(resultSet.getString("NOMBRE"));
                    alianza.setAMaterno(resultSet.getString("AMATERNO"));
                    alianza.setAPaterno(resultSet.getString("APATERNO"));
                    alianzasArray.add(alianza);
                }
                impresionTO.setAlianzas(alianzasArray);
            }
            catch (SQLException e) {
                throw new CAException(-1, "SQLException.obtieneCertificado [" + e.toString() + "]", (Exception)e);
            }
            catch (Exception e) {
                throw new CAException(-1, "ImpresionDAO.obtieneCertificado[" + e.toString() + "]", e);
            }
        }
        finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                    resultSet = null;
                }
                catch (Exception var12_17) {}
            }
            if (statement != null) {
                try {
                    statement.close();
                    statement = null;
                }
                catch (Exception var12_18) {}
            }
            if (connection != null) {
                try {
                    connection.close();
                    connection = null;
                }
                catch (Exception var12_19) {}
            }
        }
        return impresionTO;
    }
}

