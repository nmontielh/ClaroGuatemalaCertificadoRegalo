/*
 * Decompiled with CFR 0_102.
 * 
 * Could not load the following classes:
 *  com.claro.exception.CAException
 *  com.claro.transfer.FolioSAPTO
 *  com.claro.transfer.MensajeTO
 *  com.claro.transfer.MobileTO
 *  com.claro.transfer.ParametrosTO
 *  com.claro.transfer.ProductosTO
 *  com.claro.transfer.PuntoVentaTO
 *  com.claro.transfer.PuntosRedimidosTO
 *  com.claro.transfer.RedencionTO
 *  com.claro.transfer.ReservacionTO
 *  com.claro.transfer.TelefonoSimpleTO
 *  com.claro.transfer.TelefonoTO
 *  com.claro.transfer.UsuarioTO
 *  com.claro.util.Constantes
 *  com.claro.util.ServiceLocator
 *  org.apache.log4j.Logger
 */
package com.claro.dao;

import com.claro.exception.CAException;
import com.claro.transfer.FolioSAPTO;
import com.claro.transfer.MensajeTO;
import com.claro.transfer.MobileTO;
import com.claro.transfer.ParametrosTO;
import com.claro.transfer.ProductosTO;
import com.claro.transfer.PuntoVentaTO;
import com.claro.transfer.PuntosRedimidosTO;
import com.claro.transfer.RedencionTO;
import com.claro.transfer.ReservacionTO;
import com.claro.transfer.TelefonoSimpleTO;
import com.claro.transfer.TelefonoTO;
import com.claro.transfer.UsuarioTO;
import com.claro.util.Constantes;
import com.claro.util.ServiceLocator;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import org.apache.log4j.Logger;

public class PuntosDAO {
    protected final Logger logger = Logger.getLogger((String)"loggerCirculoAzul");
    protected final Logger error = Logger.getLogger((String)"loggerCirculoAzulError");
    private String schema_database;

    public PuntosDAO() {
        try {
            this.schema_database = ServiceLocator.getInstance().getVariable(ServiceLocator.schema_database);
        }
        catch (Exception e) {
            this.error.error((Object)"PuntosDAO", (Throwable)e);
        }
    }

    public void actualizaDatosLineaM2K(MobileTO mobileTO, Connection connection, TelefonoTO telefonoTO) throws CAException {
        PreparedStatement statement = null;
        java.util.Date fecForzoso = null;
        try {
            try {
                fecForzoso = "".equals(mobileTO.getFecAddM2K()) ? mobileTO.getFechaEfectiva() : mobileTO.getFechaAddendum();
                StringBuffer sQuery = new StringBuffer();
                sQuery.append("UPDATE ").append(this.schema_database).append("PTO_TBLLINEAS ");
                sQuery.append("SET ciclofact =?,");
                sQuery.append("Plan =?,");
                sQuery.append("Addendum =?,");
                sQuery.append("Fechaadd = ?,");
                sQuery.append("statustel=?,");
                sQuery.append("ctapadre =?,");
                sQuery.append("linea  =?,");
                sQuery.append("anacr  =?,");
                sQuery.append("statuscarta  =?,");
                sQuery.append("fechaant  =?");
                sQuery.append(" WHERE Cuenta =? AND Secuencia =?");
                long inicioConsulta = System.currentTimeMillis();
                this.logger.info((Object)("actualizaDatosM2K|InicioConsulta|" + Constantes.DATEFORMTALog.format(new java.util.Date()) + "|" + inicioConsulta));
                statement = connection.prepareStatement(sQuery.toString());
                statement.setInt(1, Integer.valueOf(mobileTO.getCiclo()));
                statement.setString(2, mobileTO.getPlanM2K());
                statement.setInt(3, Integer.valueOf(mobileTO.getAddM2K()));
                statement.setDate(4, new Date(fecForzoso.getTime()));
                statement.setString(5, mobileTO.getStatus());
                statement.setString(6, mobileTO.getCuentaPadre());
                statement.setString(7, mobileTO.getTelefono());
                statement.setString(8, telefonoTO.getSAnacr());
                if (!(mobileTO.getStatus() == null || "".equals(mobileTO.getStatus()))) {
                    statement.setString(9, telefonoTO.getEstatusCarta(mobileTO.getStatus()));
                } else {
                    statement.setNull(9, 12);
                }
                statement.setDate(10, new Date(mobileTO.getFechaAltaUser().getTime()));
                statement.setString(11, mobileTO.getCuenta());
                statement.setInt(12, Integer.parseInt(mobileTO.getSecuencia()));
                int iresul = statement.executeUpdate();
                if (iresul == 0) {
                    mobileTO.agregaMensaje(-1, "Cuenta aun no cargada en Puntos, Favor de esperar hasta su corte de Facturacion o validar si el plan participa en Circulo Azul");
                }
                this.logger.info((Object)("actualizaDatosM2K|FinConsulta|" + Constantes.DATEFORMTALog.format(new java.util.Date()) + "|" + (System.currentTimeMillis() - inicioConsulta)));
            }
            catch (SQLException e) {
                this.error.info((Object)"SQLException.actualizaDatosM2K:", (Throwable)e);
                throw new CAException(-1, "[consultaDatos] SQLError: " + e.toString() + "Actualizar Inf", (Exception)e);
            }
            catch (Exception e) {
                this.error.info((Object)"Exception.actualizaDatosM2K:", (Throwable)e);
                throw new CAException(-1, "[consultaDatos] Error: " + e.toString() + "Actualizar Inf", e);
            }
        }
        finally {
            if (statement != null) {
                try {
                    statement.close();
                    statement = null;
                }
                catch (Exception var11_13) {}
            }
        }
    }

    public void insertaDatosLineaM2K(MobileTO mobileTO, Connection connection, TelefonoTO telefonoTO) throws CAException {
        PreparedStatement statement = null;
        java.util.Date fecForzoso = null;
        try {
            try {
                fecForzoso = "".equals(mobileTO.getFecAddM2K()) ? mobileTO.getFechaEfectiva() : mobileTO.getFechaAddendum();
                StringBuffer sQuery = new StringBuffer();
                sQuery.append("INSERT INTO ").append(this.schema_database).append("PTO_TBLLINEAS ");
                sQuery.append("(CICLOFACT,PLAN,ADDENDUM,FECHAADD,STATUSTEL,CTAPADRE,");
                sQuery.append("LINEA,ANACR,STATUSCARTA,FECHAANT,CUENTA,SECUENCIA,IDREGION,FECHAALTA,STATUSPUNTOS,SISTEMA) ");
                sQuery.append("VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,CURRENT TIMESTAMP,0,'M2K') ");
                long InicioInsercion = System.currentTimeMillis();
                this.logger.info((Object)("insertaDatosLineaM2K|InicioInsercion|" + Constantes.DATEFORMTALog.format(new java.util.Date()) + "|" + InicioInsercion));
                statement = connection.prepareStatement(sQuery.toString());
                statement.setInt(1, Integer.valueOf(mobileTO.getCiclo()));
                statement.setString(2, mobileTO.getPlanM2K());
                statement.setInt(3, Integer.valueOf(mobileTO.getAddM2K()));
                statement.setDate(4, new Date(fecForzoso.getTime()));
                statement.setString(5, mobileTO.getStatus());
                statement.setString(6, mobileTO.getCuentaPadre());
                statement.setString(7, mobileTO.getTelefono());
                statement.setString(8, telefonoTO.getSAnacr());
                if (!(mobileTO.getStatus() == null || "".equals(mobileTO.getStatus()))) {
                    statement.setString(9, telefonoTO.getEstatusCarta(mobileTO.getStatus()));
                } else {
                    statement.setNull(9, 12);
                }
                statement.setDate(10, new Date(mobileTO.getFechaAltaUser().getTime()));
                statement.setString(11, mobileTO.getCuenta());
                statement.setInt(12, Integer.parseInt(mobileTO.getSecuencia()));
                statement.setInt(13, telefonoTO.getRegion());
                int iresul = statement.executeUpdate();
                if (iresul == 0) {
                    mobileTO.agregaMensaje(-1, "Error al insertar registro en PTO_TBLLINEAS");
                }
                this.logger.info((Object)("insertaDatosLineaM2K|FinInsercion|" + Constantes.DATEFORMTALog.format(new java.util.Date()) + "|" + (System.currentTimeMillis() - InicioInsercion)));
            }
            catch (SQLException e) {
                this.error.info((Object)"SQLException.actualizaDatosM2K:", (Throwable)e);
                throw new CAException(-1, "[insertaDatosLineaM2K] SQLError: " + e.toString() + "Inserta Inf", (Exception)e);
            }
            catch (Exception e) {
                this.error.info((Object)"Exception.actualizaDatosM2K:", (Throwable)e);
                throw new CAException(-1, "[insertaDatosLineaM2K] Error: " + e.toString() + "Inserta Inf", e);
            }
        }
        finally {
            if (statement != null) {
                try {
                    statement.close();
                    statement = null;
                }
                catch (Exception var11_13) {}
            }
        }
    }

    public void insertaTotales(MobileTO mobileTO, Connection connection) throws CAException {
        PreparedStatement statement = null;
        try {
            try {
                StringBuffer sQuery = new StringBuffer();
                sQuery.append("INSERT INTO ").append(this.schema_database).append("PTO_TBLTOTALES ");
                sQuery.append("(CUENTA,SECUENCIA,FECHAFAC,PUNTOSACUM,PUNTOSREDIM,PUNTOSTRANSF,PUNTOSALIANZAS,");
                sQuery.append("PUNTOSCADUC,PUNTOSACAD,PUNTOSACAD2,PUNTOSPORBONO,PUNTOSBONOANT,PUNTOSRENTA,PUNTOSEXCEDENTES,");
                sQuery.append("FECHACAD,FECHACAD2,PUNTOSACAD1,FECHACAD1,PUNTOSPROM,FECHACADU,BBONO,SALDOANT,PUNTOSACADDISP,");
                sQuery.append("PUNTOSACAD2DISP,PUNTOSACAD1DISP,PUNTOSRENTADISP,PUNTOSANTIG,PUNTOSSUBASTA,PUNTOSANTIGUEDAD,");
                sQuery.append("PUNTOSPROMOCION,BONOEQUIPO,FECHAASIGNACADU) ");
                sQuery.append("VALUES (?,?,null,0,0,0,0,0,0,0,0,0,0,0,null,null,0,null,0,null,'0',0,0,0,0,0,0,0,0,0,0,null) ");
                long InicioInsercion = System.currentTimeMillis();
                this.logger.info((Object)("insertaTotales|InicioInsercion|" + Constantes.DATEFORMTALog.format(new java.util.Date()) + "|" + InicioInsercion));
                statement = connection.prepareStatement(sQuery.toString());
                statement.setString(1, mobileTO.getCuenta());
                statement.setInt(2, Integer.parseInt(mobileTO.getSecuencia()));
                int iresul = statement.executeUpdate();
                if (iresul == 0) {
                    mobileTO.agregaMensaje(-1, "Error al insertar registro en PTO_TBLTOTALES");
                }
                this.logger.info((Object)("insertaTotales|FinInsercion|" + Constantes.DATEFORMTALog.format(new java.util.Date()) + "|" + (System.currentTimeMillis() - InicioInsercion)));
            }
            catch (SQLException e) {
                this.error.info((Object)"SQLException.actualizaDatosM2K:", (Throwable)e);
                throw new CAException(-1, "[insertaTotales] SQLError: " + e.toString() + "Inserta Inf", (Exception)e);
            }
            catch (Exception e) {
                this.error.info((Object)"Exception.actualizaDatosM2K:", (Throwable)e);
                throw new CAException(-1, "[insertaTotales] Error: " + e.toString() + "Inserta Inf", e);
            }
        }
        finally {
            if (statement != null) {
                try {
                    statement.close();
                    statement = null;
                }
                catch (Exception var9_11) {}
            }
        }
    }

    public MensajeTO actualizaPuntos(PuntosRedimidosTO puntosRedimidosTO, Connection connection, String tipoRedencion, String cuenta, int secuencia, boolean actualizaRedim) throws CAException {
        MensajeTO mensajeTO;
        PreparedStatement statement = null;
        try {
            StringBuffer update = new StringBuffer("UPDATE ").append(this.schema_database).append("PTO_TBLTOTALES ");
            update.append(" SET PUNTOSACAD =? ");
            update.append(",PUNTOSACAD1  = ?,PUNTOSACAD2 = ? ");
            update.append(",PUNTOSPROMOCION =?,PUNTOSANTIGUEDAD = ? ");
            update.append(",PUNTOSEXCEDENTES =?,PUNTOSRENTA = ? ");
            update.append(",BBONO = ?,BONOEQUIPO = ?,PUNTOSACUM=?,PUNTOSTRANSF=? ");
            if (puntosRedimidosTO.getPtsPorVencer() == 0) {
                update.append(",FECHACAD = ? ");
            } else if (puntosRedimidosTO.getFecVencer() != null) {
                update.append(",FECHACAD = ? ");
            }
            if (puntosRedimidosTO.getPtsPorVencer1() == 0) {
                update.append(",FECHACAD1 = ? ");
            } else if (puntosRedimidosTO.getFecVencer1() != null) {
                update.append(",FECHACAD1 = ? ");
            }
            if (puntosRedimidosTO.getPtsPorVencer2() == 0) {
                update.append(",FECHACAD2 = ? ");
            } else if (puntosRedimidosTO.getFecVencer2() != null) {
                update.append(",FECHACAD2 = ? ");
            }
            if (actualizaRedim) {
                update.append(",PUNTOSREDIM = ? ");
            }
            update.append(" WHERE CUENTA = ?  AND SECUENCIA =? ");
            statement = connection.prepareStatement(update.toString());
            statement.setInt(1, puntosRedimidosTO.getPtsPorVencer());
            statement.setInt(2, puntosRedimidosTO.getPtsPorVencer1());
            statement.setInt(3, puntosRedimidosTO.getPtsPorVencer2());
            statement.setInt(4, puntosRedimidosTO.getPtsPromocion());
            statement.setInt(5, puntosRedimidosTO.getPtsAntiguedad());
            statement.setInt(6, puntosRedimidosTO.getPtsExcedentes());
            statement.setInt(7, puntosRedimidosTO.getPtsRenta());
            if (tipoRedencion != null && tipoRedencion.trim().equals("C")) {
                statement.setString(8, "0");
                statement.setInt(9, 0);
            } else {
                statement.setString(8, puntosRedimidosTO.getBBono());
                statement.setInt(9, puntosRedimidosTO.getBonoEquipo());
            }
            statement.setInt(10, puntosRedimidosTO.getPtsAcumulados());
            statement.setInt(11, puntosRedimidosTO.getPtsTransferidos());
            int contador = 11;
            if (puntosRedimidosTO.getPtsPorVencer() == 0) {
                statement.setNull(++contador, 91);
            } else if (puntosRedimidosTO.getFecVencer() != null) {
                statement.setDate(++contador, puntosRedimidosTO.getFecVencer());
            }
            if (puntosRedimidosTO.getPtsPorVencer1() == 0) {
                statement.setNull(++contador, 91);
            } else if (puntosRedimidosTO.getFecVencer1() != null) {
                statement.setDate(++contador, puntosRedimidosTO.getFecVencer1());
            }
            if (puntosRedimidosTO.getPtsPorVencer2() == 0) {
                statement.setNull(++contador, 91);
            } else if (puntosRedimidosTO.getFecVencer2() != null) {
                statement.setDate(++contador, puntosRedimidosTO.getFecVencer2());
            }
            if (actualizaRedim) {
                statement.setInt(++contador, puntosRedimidosTO.getPtsRedimidos());
            }
            statement.setString(++contador, cuenta);
            statement.setInt(++contador, secuencia);
            if (statement.executeUpdate() > 0) {
                MensajeTO mensajeTO2 = new MensajeTO(0, "PROCESO EXITOSO");
                return mensajeTO2;
            }
            mensajeTO = new MensajeTO(1, "NO SE ACTUALIZO EL REGISTRO PARA LA CUENTA[" + cuenta + "]");
        }
        catch (SQLException e) {
            this.error.info((Object)"PuntosDAO.actualizaPuntos.SQLException:", (Throwable)e);
            throw new CAException(-1, "PuntosDAO.actualizaPuntos.SQLException[" + e.toString() + "]", (Exception)e);
        }
        catch (Exception e) {
            this.error.info((Object)"PuntosDAO.actualizaPuntos.Exception:", (Throwable)e);
            throw new CAException(-1, "PuntosDAO.actualizaPuntos.Error[" + e.toString() + "]", e);
        }
        finally {
            if (statement != null) {
                try {
                    statement.close();
                    statement = null;
                }
                catch (Exception var12_16) {}
            }
        }
        return mensajeTO;
    }

    public MensajeTO insertaRedencion(Connection connection, RedencionTO redencionTO, ParametrosTO parametrosTO, long fechaTransaccion) throws CAException {
        MensajeTO mensajeTO;
        PreparedStatement statement = null;
        mensajeTO = new MensajeTO();
        try {
            try {
                StringBuffer inserta = new StringBuffer("INSERT INTO ").append(this.schema_database).append("PTO_TBLREDENCION (CUENTA,SECUENCIA,");
                inserta.append("IDUSUARIO,FECHAOPER,IDPRODUCTO,VALORPTOS,DIFPESOS,");
                inserta.append("IDPUNTOVTA,IDREGION,COMENTARIO,ESTATUS,FECHAFOLIO,");
                inserta.append("LINEA,PRENTA,PEXCED,PACAD2,FCAD2,PACAD1,FACAD1,");
                inserta.append("PACAD,FACAD,DESCRIPCION,MARCA,MODELO,PDISP,");
                inserta.append("TIPOREDEN,ADDEANT,TIPOPROM,FOLIO,PRECIO,PRECIOIVA,");
                inserta.append("PUNTOSPROMOCION,PUNTOSANTIGUEDAD,PSOBR,PSOBR1,BONOROEXT,DESCUENTO,BONOALTOVALOR, ");
                inserta.append("APLICAPROMOGAP,BONOGAP,IDPROMOCIONGAP,IDPROMOCA,VERPROMOGAP,TIPOREDPROMOLINE,ORIGENREDONLINE, ");
                inserta.append("BONOINBURSA,BONOMARCA )");
                inserta.append(" VALUES (");
                inserta.append("?,?,?,?,?,?,?,?,?,?,");
                inserta.append("?,?,?,?,?,?,?,?,?,?,");
                inserta.append("?,?,?,?,?,?,?,?,?,?,");
                inserta.append("?,?,?,?,?,?,?,?,?,?,");
                inserta.append("?,?,?,?,?,?,?)");
                statement = connection.prepareStatement(inserta.toString());
                statement.setString(1, parametrosTO.getCuenta());
                statement.setInt(2, parametrosTO.getSecuencia());
                statement.setString(3, redencionTO.getUsuarioTO().getIdUsuario());
                statement.setDate(4, new Date(fechaTransaccion));
                statement.setString(5, redencionTO.getProductosTO().getIdProducto());
                statement.setInt(6, redencionTO.getProductosTO().getValorPuntosTmp());
                statement.setInt(7, redencionTO.getProductosTO().getDifPesos());
                statement.setString(8, redencionTO.getUsuarioTO().getPuntoVentaTO().getPtoVenta());
                statement.setInt(9, redencionTO.getRegion());
                statement.setString(10, redencionTO.getComentario());
                statement.setString(11, redencionTO.getEstatus());
                statement.setTimestamp(12, new Timestamp(fechaTransaccion));
                statement.setString(13, parametrosTO.getTelefono());
                statement.setInt(14, redencionTO.getPuntosRedimidosTO().getPtsRentaRedimidos());
                statement.setInt(15, redencionTO.getPuntosRedimidosTO().getPtsExcedentesRedimidos());
                statement.setInt(16, redencionTO.getPuntosRedimidosTO().getPtsPorVencer2Redimidos());
                statement.setDate(17, redencionTO.getPuntosRedimidosTO().getFecVencer2Tmp());
                statement.setInt(18, redencionTO.getPuntosRedimidosTO().getPtsPorVencer1Redimidos());
                statement.setDate(19, redencionTO.getPuntosRedimidosTO().getFecVencer1Tmp());
                statement.setInt(20, redencionTO.getPuntosRedimidosTO().getPtsPorVencerRedimidos());
                statement.setDate(21, redencionTO.getPuntosRedimidosTO().getFecVencerTmp());
                statement.setString(22, redencionTO.getProductosTO().getDescripcion());
                statement.setString(23, redencionTO.getProductosTO().getMarca());
                statement.setString(24, redencionTO.getProductosTO().getModelo());
                statement.setInt(25, redencionTO.getPuntosRedimidosTO().getPtosDisponiblesTmp());
                statement.setString(26, redencionTO.getTipoRedencion());
                statement.setDate(27, new Date(redencionTO.getFechaAdendumAnterior().getTime()));
                statement.setString(28, redencionTO.getProductosTO().getTipoPromocion());
                statement.setString(29, redencionTO.getFolio());
                statement.setBigDecimal(30, redencionTO.getProductosTO().getPrecio());
                statement.setBigDecimal(31, redencionTO.getProductosTO().getPrecioIva());
                statement.setInt(32, redencionTO.getPuntosRedimidosTO().getPtsPromocionRedimidos());
                statement.setInt(33, redencionTO.getPuntosRedimidosTO().getPtsPorAntiguedadRedimidos());
                if (redencionTO.getTipoRedencion() != null && redencionTO.getTipoRedencion().trim().equals("C")) {
                    statement.setInt(34, redencionTO.getPuntosRedimidosTO().getBonoEquipo());
                    statement.setInt(35, redencionTO.getPtsSobrantes());
                } else {
                    statement.setInt(34, 0);
                    statement.setInt(35, 0);
                }
                statement.setInt(36, redencionTO.getProductosTO().getBonosRoext());
                statement.setBigDecimal(37, redencionTO.getProductosTO().getDescuento());
                statement.setInt(38, redencionTO.getProductosTO().getBonosAltoValor());
                statement.setString(39, redencionTO.getProductosTO().getAplicaPromocionGap() != null ? redencionTO.getProductosTO().getAplicaPromocionGap() : "");
                statement.setInt(40, redencionTO.getProductosTO().getBonosGap());
                statement.setInt(41, redencionTO.getProductosTO().getIdPromocionGap());
                statement.setInt(42, redencionTO.getProductosTO().getIdPromocionGapCA());
                statement.setInt(43, redencionTO.getProductosTO().getVerPromocionGap());
                statement.setInt(44, redencionTO.getTipoRedPromOnline());
                statement.setInt(45, redencionTO.getOrigenRedOnline());
                statement.setBigDecimal(46, redencionTO.getProductosTO().getDescuentoInbursa());
                statement.setBigDecimal(47, redencionTO.getProductosTO().getDescuentoMarca());
                if (statement.executeUpdate() > 0) {
                    mensajeTO.agregaMensaje(0, "Proceso Exitoso");
                } else {
                    mensajeTO.agregaMensaje(1, "No se Inserto la Redencion");
                }
            }
            catch (SQLException e) {
                this.error.info((Object)"PuntosDAO.insertaRedencion.SQLException:", (Throwable)e);
                throw new CAException(-1, "PuntosDAO.insertaRedencion.SQLException[" + e.toString() + "]", (Exception)e);
            }
            catch (Exception e) {
                this.error.info((Object)"PuntosDAO.insertaRedencion.Exception:", (Throwable)e);
                throw new CAException(-1, "PuntosDAO.insertaRedencion.Error[" + e.toString() + "]", e);
            }
        }
        finally {
            if (statement != null) {
                try {
                    statement.close();
                    statement = null;
                }
                catch (Exception var10_12) {}
            }
        }
        return mensajeTO;
    }

    public MensajeTO insertaConstancia(RedencionTO redencionTO, ParametrosTO parametrosTO, Connection connection, long fechaTransaccion, String estatus) throws CAException {
        MensajeTO mensajeTO;
        PreparedStatement statement = null;
        mensajeTO = new MensajeTO();
        try {
            try {
                StringBuffer inserta = new StringBuffer("INSERT INTO ").append(this.schema_database).append("PTO_TBLCONSTANCIA(FECHAFOLIO,");
                inserta.append("CUENTA,SECUENCIA,LINEA,FECHAOPER,IDPRODUCTO,TIPOREDEN,");
                inserta.append("PLANNVO,PLANANT,ADDNVO,ADDANT,FECPLAZOSUG,FECPLAZOANT,");
                inserta.append("PUNTOSDISPO,PTSDISPORES,ESTATUS,FORMARED,ESNIMEIR,ESNIMEIP,ICCID)");
                inserta.append("VALUES (");
                inserta.append("?,?,?,?,?,?,?,?,?,?,");
                inserta.append("?,?,?,?,?,?,?,?,?,?");
                inserta.append(")");
                statement = connection.prepareStatement(inserta.toString());
                statement.setTimestamp(1, new Timestamp(fechaTransaccion));
                statement.setString(2, parametrosTO.getCuenta());
                statement.setInt(3, parametrosTO.getSecuencia());
                statement.setString(4, parametrosTO.getTelefono());
                statement.setDate(5, new Date(fechaTransaccion));
                statement.setString(6, redencionTO.getProductosTO().getIdProducto());
                statement.setString(7, redencionTO.getTipoRedencion());
                statement.setString(8, parametrosTO.getPlanNvo());
                statement.setString(9, parametrosTO.getPlanAnt());
                statement.setInt(10, redencionTO.getAddendumNuevo());
                statement.setInt(11, redencionTO.getAddendumAnterior());
                statement.setDate(12, new Date(fechaTransaccion));
                statement.setDate(13, new Date(redencionTO.getFechaAdendumAnterior().getTime()));
                statement.setInt(14, redencionTO.getPuntosRedimidosTO().getPtosDisponiblesTmp());
                statement.setInt(15, redencionTO.getPuntosRedimidosTO().getPtosDisponibles());
                statement.setString(16, estatus);
                statement.setString(17, redencionTO.getFormaRedencion());
                statement.setString(18, redencionTO.getProductosTO().getNumeroSerieT());
                statement.setString(19, redencionTO.getProductosTO().getNumeroSerieP());
                statement.setString(20, redencionTO.getProductosTO().getIccid());
                if (statement.executeUpdate() > 0) {
                    mensajeTO.agregaMensaje(0, "Proceso Exitoso");
                } else {
                    mensajeTO.agregaMensaje(1, "No se Inserto la Redencion");
                }
            }
            catch (SQLException e) {
                this.error.info((Object)"PuntosDAO.insertaRedencion.SQLException:", (Throwable)e);
                throw new CAException(-1, "PuntosDAO.insertaRedencion.SQLException[" + e.toString() + "]", (Exception)e);
            }
            catch (Exception e) {
                this.error.info((Object)"PuntosDAO.insertaRedencion.Exception:", (Throwable)e);
                throw new CAException(-1, "PuntosDAO.insertaRedencion.Error[" + e.toString() + "]", e);
            }
        }
        finally {
            if (statement != null) {
                try {
                    statement.close();
                    statement = null;
                }
                catch (Exception var11_13) {}
            }
        }
        return mensajeTO;
    }

    public MensajeTO insertaBonoInbursa(RedencionTO redencionTO, ParametrosTO parametrosTO, Connection connection) throws CAException {
        MensajeTO mensajeTO;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        mensajeTO = new MensajeTO();
        try {
            try {
                StringBuffer busca = new StringBuffer("SELECT FOLIO FROM ").append(this.schema_database).append("PTO_TBLINBURSAPROMO ");
                busca.append(" WHERE TELEFONO = ? ");
                statement = connection.prepareStatement(busca.toString());
                statement.setString(1, parametrosTO.getTelefono());
                resultSet = statement.executeQuery();
                String folio = null;
                if (resultSet.next()) {
                    folio = resultSet.getString("FOLIO");
                }
                GregorianCalendar fecha = new GregorianCalendar();
                StringBuffer inserta = new StringBuffer("INSERT INTO ").append(this.schema_database).append("PTO_TBLBONOINBURSA(");
                inserta.append("FOLIO,FOLIOINBURSA,LINEA,CUENTA,DESCUENTOINBURSA,DESCUENTOMARCA,FECHAIMPRESION,FECHAEXPIRACION,CAC,FOLIOREDENCION)");
                inserta.append("VALUES (");
                if (redencionTO.getProductosTO().getDescuentoInbursaRestante().compareTo(new BigDecimal(0)) == 0 && redencionTO.getProductosTO().getDescuentoMarcaRestante().compareTo(new BigDecimal(0)) == 0) {
                    inserta.append("0,?,?,?,?,?,?,?,?,?)");
                } else {
                    inserta.append("(SELECT MAX(FOLIO)+1 FROM ").append(this.schema_database).append("PTO_TBLBONOINBURSA),?,?,?,?,?,?,?,?,?)");
                }
                statement = connection.prepareStatement(inserta.toString());
                statement.setString(1, folio);
                statement.setString(2, parametrosTO.getTelefono());
                statement.setString(3, parametrosTO.getCuenta());
                statement.setBigDecimal(4, redencionTO.getProductosTO().getDescuentoInbursaRestante().setScale(2, RoundingMode.HALF_UP));
                statement.setBigDecimal(5, redencionTO.getProductosTO().getDescuentoMarcaRestante().setScale(2, RoundingMode.HALF_UP));
                statement.setDate(6, new Date(fecha.getTimeInMillis()));
                if (redencionTO.getProductosTO().getDescuentoInbursaRestante().compareTo(new BigDecimal(0)) == 0 && redencionTO.getProductosTO().getDescuentoMarcaRestante().compareTo(new BigDecimal(0)) == 0) {
                    statement.setDate(7, null);
                } else {
                    fecha.add(5, 30);
                    statement.setDate(7, new Date(fecha.getTimeInMillis()));
                }
                statement.setString(8, redencionTO.getUsuarioTO().getPuntoVentaTO().getPtoVenta());
                statement.setString(9, redencionTO.getFolio());
                if (statement.executeUpdate() > 0) {
                    mensajeTO.agregaMensaje(0, "Proceso Exitoso");
                } else {
                    mensajeTO.agregaMensaje(1, "No se Inserto el Bono Inbursa");
                }
            }
            catch (SQLException e) {
                this.error.info((Object)"PuntosDAO.insertaBonoInbursa.SQLException:", (Throwable)e);
                throw new CAException(-1, "PuntosDAO.insertaBonoInbursa.SQLException[" + e.toString() + "]", (Exception)e);
            }
            catch (Exception e) {
                this.error.info((Object)"PuntosDAO.insertaBonoInbursa.Exception:", (Throwable)e);
                throw new CAException(-1, "PuntosDAO.insertaBonoInbursa.Error[" + e.toString() + "]", e);
            }
        }
        finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                    resultSet = null;
                }
                catch (Exception var12_16) {}
            }
            if (statement != null) {
                try {
                    statement.close();
                    statement = null;
                }
                catch (Exception var12_17) {}
            }
        }
        return mensajeTO;
    }

    public MensajeTO eliminaBonoDescuento(Connection connection, String cuenta, String folio) throws CAException {
        MensajeTO mensajeTO;
        PreparedStatement statement = null;
        mensajeTO = new MensajeTO();
        mensajeTO.agregaMensaje(0, "Proceso Exitoso");
        boolean bonoInbursa = false;
        try {
            try {
                StringBuffer busca = new StringBuffer("SELECT LINEA FROM ").append(this.schema_database).append("PTO_TBLBONOINBURSA ");
                busca.append(" WHERE CUENTA = ? AND FOLIOREDENCION = ?");
                statement = connection.prepareStatement(busca.toString());
                statement.setString(1, cuenta);
                statement.setString(2, folio);
                if (statement.executeUpdate() > 0) {
                    bonoInbursa = true;
                }
                if (bonoInbursa) {
                    StringBuffer elimina = new StringBuffer("UPDATE ").append(this.schema_database).append("PTO_TBLBONOINBURSA ");
                    elimina.append(" SET FECHAEXPIRACION = NULL WHERE CUENTA = ? AND FOLIOREDENCION = ?");
                    statement = connection.prepareStatement(elimina.toString());
                    statement.setString(1, cuenta);
                    statement.setString(2, folio);
                    if (statement.executeUpdate() > 0) {
                        mensajeTO.agregaMensaje(0, "Proceso Exitoso");
                    } else {
                        mensajeTO.agregaMensaje(1, "No se elimino el Bono Inbursa");
                    }
                }
            }
            catch (SQLException e) {
                this.error.info((Object)"PuntosDAO.insertaBonoInbursa.SQLException:", (Throwable)e);
                throw new CAException(-1, "PuntosDAO.eliminaBonoDescuento.SQLException[" + e.toString() + "]", (Exception)e);
            }
            catch (Exception e) {
                this.error.info((Object)"PuntosDAO.insertaBonoInbursa.Exception:", (Throwable)e);
                throw new CAException(-1, "PuntosDAO.eliminaBonoDescuento.Error[" + e.toString() + "]", e);
            }
        }
        finally {
            if (statement != null) {
                try {
                    statement.close();
                    statement = null;
                }
                catch (Exception var10_13) {}
            }
        }
        return mensajeTO;
    }

    public MensajeTO insertaDetalle(Connection connection, long fechaTransaccion, String referencia, int movimiento, int totAjustes, String idBonoPromo, String cuenta, int secuencia, String telefono, String usuario) throws CAException {
        MensajeTO mensajeTO;
        Connection lConn = null;
        PreparedStatement statement = null;
        mensajeTO = new MensajeTO();
        try {
            try {
                lConn = connection != null && !connection.isClosed() ? connection : (connection != null && connection.isClosed() ? ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul) : ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul));
                StringBuffer sInsert = new StringBuffer("INSERT INTO ").append(this.schema_database).append("PTO_TBLMSTRDETALLE");
                sInsert.append("(cuenta,secuencia,linea,fechafac,fechaoperacion,idmovto,");
                sInsert.append("idusuario,numpuntos,numpuntosexc,totajustes,");
                sInsert.append("idbonoprom,referencia) VALUES");
                sInsert.append("(?,?,?,?,?,?");
                sInsert.append(",?,?,?,?,?,?)");
                statement = lConn.prepareStatement(sInsert.toString());
                statement.setString(1, cuenta);
                statement.setInt(2, secuencia);
                statement.setString(3, telefono);
                statement.setDate(4, new Date(fechaTransaccion));
                statement.setTimestamp(5, new Timestamp(fechaTransaccion));
                statement.setInt(6, movimiento);
                statement.setString(7, usuario);
                statement.setInt(8, 0);
                statement.setInt(9, 0);
                statement.setInt(10, totAjustes);
                if (idBonoPromo != null) {
                    statement.setString(11, idBonoPromo);
                } else {
                    statement.setNull(11, 12);
                }
                if (referencia != null) {
                    statement.setString(12, referencia);
                } else {
                    statement.setNull(12, 12);
                }
                if (statement.executeUpdate() > 0) {
                    mensajeTO.agregaMensaje(0, "Proceso Exitoso");
                } else {
                    mensajeTO.agregaMensaje(-1, "No se Inserto el Detalle de la Redencion");
                }
            }
            catch (SQLException e) {
                this.error.info((Object)"PuntosDAO.insertaDetalle.SQLException:", (Throwable)e);
                throw new CAException(-1, "PuntosDAO.insertaRedencion.SQLException[" + e.toString() + "]", (Exception)e);
            }
            catch (Exception e) {
                this.error.info((Object)"PuntosDAO.insertaDetalle.Exception:", (Throwable)e);
                throw new CAException(-1, "PuntosDAO.insertaRedencion.Error[" + e.toString() + "]", e);
            }
        }
        finally {
            if (statement != null) {
                try {
                    statement.close();
                    statement = null;
                }
                catch (Exception var17_19) {}
            }
        }
        return mensajeTO;
    }

    public MensajeTO insertaComentarioTMP(Connection lConn, int region, String cuenta, String usuario, long fechaTransaccion, String comentario) throws CAException {
        MensajeTO mensajeTO;
        Connection connection = null;
        PreparedStatement statement = null;
        mensajeTO = new MensajeTO();
        String sCadenaInsert = "INSERT INTO " + this.schema_database + "PTO_TMP_COMNT  (REGION,FECHA,CUENTA,USUARIO,COMENTARIOS)" + " VALUES(?,?,?,?,?)";
        try {
            try {
                connection = lConn != null && lConn != null && !lConn.isClosed() ? lConn : ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                statement = connection.prepareStatement(sCadenaInsert);
                statement.setInt(1, region);
                statement.setTimestamp(2, new Timestamp(fechaTransaccion));
                statement.setString(3, cuenta);
                statement.setString(4, usuario);
                statement.setString(5, comentario);
                if (statement.executeUpdate() > 0) {
                    mensajeTO.agregaMensaje(0, "Proceso Exitoso");
                } else {
                    mensajeTO.agregaMensaje(1, "Error al insertar Comentario");
                }
            }
            catch (SQLException e) {
                throw new CAException(-1, "PuntosDAO.insertaComentario[" + e.toString() + "]", (Exception)e);
            }
            catch (Exception e) {
                throw new CAException(-1, "PuntosDAO.insertaComentario[" + e.toString() + "]", e);
            }
        }
        finally {
            if (statement != null) {
                try {
                    statement.close();
                    statement = null;
                }
                catch (Exception var14_15) {}
            }
        }
        return mensajeTO;
    }

    public MensajeTO actualizaLinea(Connection connection, String cuenta, int secuencia, String estatus) throws CAException {
        MensajeTO mensajeTO;
        PreparedStatement statement = null;
        StringBuffer queryUpd = new StringBuffer();
        queryUpd.append(" UPDATE ").append(this.schema_database).append("PTO_TBLLINEAS ");
        queryUpd.append(" SET STATUSPUNTOS =? ");
        queryUpd.append(" WHERE CUENTA =?");
        queryUpd.append(" AND SECUENCIA = ?");
        mensajeTO = new MensajeTO();
        boolean cierraConeccion = false;
        try {
            try {
                if (connection == null || connection.isClosed()) {
                    connection = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                    cierraConeccion = true;
                }
                statement = connection.prepareStatement(queryUpd.toString());
                statement.setString(1, estatus);
                statement.setString(2, cuenta);
                statement.setInt(3, secuencia);
                if (statement.executeUpdate() > 0) {
                    mensajeTO.agregaMensaje(0, "Proceso Exitoso");
                } else {
                    mensajeTO.agregaMensaje(1, "Error al actualizar es Status de los puntos.");
                }
            }
            catch (SQLException e) {
                throw new CAException(-1, "PuntosDAO.cambiaStatusPuntos[" + e.getStackTrace() + "]", (Exception)e);
            }
            catch (Exception e) {
                throw new CAException(-1, "PuntosDAO.cambiaStatusPuntos[" + e.getStackTrace() + "]", e);
            }
        }
        finally {
            if (statement != null) {
                try {
                    statement.close();
                    statement = null;
                }
                catch (Exception var11_14) {}
            }
            if (cierraConeccion) {
                try {
                    if (connection != null) {
                        connection.close();
                    }
                    connection = null;
                }
                catch (Exception var11_15) {}
            }
        }
        return mensajeTO;
    }

    public MensajeTO insertReservacion(Connection connection, ReservacionTO reservacionTO, long fechaTransaccion, boolean isDistribuidores) throws CAException {
        MensajeTO mensajeTO;
        mensajeTO = new MensajeTO();
        PreparedStatement statement = null;
        try {
            try {
                if (isDistribuidores) {
                    StringBuffer sInserta = new StringBuffer("INSERT INTO ").append(this.schema_database).append("PTO_TBLRESERVACIONES(folio,cuenta,");
                    sInserta.append("  secuencia,idusuario,fechaoper,idproducto,ValorPuntos,");
                    sInserta.append("  DiferenciaPesos,Idpuntovta,IdRegion,FzaVta,IdPlan,");
                    sInserta.append("  Comentario,FechaExpira,Plannvo,Status,Linea,");
                    sInserta.append("  Descripcion,Marca,Modelo,TipoReden,Addeant,Tipoprom,");
                    sInserta.append("  Addnvo,plazoant,plazonvo,formared, precio, precioIVA,");
                    sInserta.append("  sobrantesbono,bonoroext,descuento,bonoaltovalor,");
                    sInserta.append(" APLICAPROMOGAP,BONOGAP,IDPROMOCIONGAP,IDPROMOCA,VERPROMOGAP,APLICAEP,TECNOLOGIA )");
                    sInserta.append("  VALUES(");
                    sInserta.append("?,?,?,?,?,?,?,?,?,?,");
                    sInserta.append("?,?,?,?,?,?,?,?,?,?,");
                    sInserta.append("?,?,?,?,?,?,?,?,?,?,");
                    sInserta.append("?,?,?,?,");
                    sInserta.append("?,?,?,?,?,?");
                    sInserta.append(")");
                    if (connection == null || connection.isClosed()) {
                        connection = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                    }
                    statement = connection.prepareStatement(sInserta.toString());
                    statement.setString(1, reservacionTO.getFolio());
                    statement.setString(2, reservacionTO.getTelefonoSimpleTO().getCuenta());
                    statement.setInt(3, reservacionTO.getTelefonoSimpleTO().getSecuencia());
                    statement.setString(4, reservacionTO.getUsuarioTO().getIdUsuario());
                    statement.setDate(5, new Date(fechaTransaccion));
                    statement.setString(6, reservacionTO.getProductosTO().getIdProducto());
                    statement.setInt(7, reservacionTO.getProductosTO().getValorPuntos());
                    statement.setInt(8, reservacionTO.getProductosTO().getDifPesos());
                    statement.setString(9, reservacionTO.getUsuarioTO().getPuntoVentaTO().getPtoVenta());
                    statement.setInt(10, reservacionTO.getTelefonoSimpleTO().getRegion());
                    statement.setString(11, reservacionTO.getFuerzaVenta());
                    statement.setString(12, reservacionTO.getMobileTO().getPlanM2K());
                    statement.setString(13, reservacionTO.getComentario());
                    statement.setDate(14, new Date(reservacionTO.getFechaExpiracion().getTime()));
                    statement.setString(15, reservacionTO.getPlanNuevo());
                    statement.setString(16, reservacionTO.getEstatus());
                    statement.setString(17, reservacionTO.getTelefonoSimpleTO().getLinea());
                    statement.setString(18, reservacionTO.getProductosTO().getDescripcion());
                    statement.setString(19, reservacionTO.getProductosTO().getMarca());
                    statement.setString(20, reservacionTO.getProductosTO().getModelo());
                    statement.setString(21, reservacionTO.getTipoRedencion());
                    statement.setDate(22, new Date(reservacionTO.getFechaAdendumAnterior().getTime()));
                    statement.setString(23, reservacionTO.getProductosTO().getTipoPromocion());
                    statement.setDate(24, new Date(reservacionTO.getFechaAdendumNuevo().getTime()));
                    statement.setString(25, reservacionTO.getPlazoAnterior());
                    statement.setString(26, reservacionTO.getPlazoNuevo());
                    statement.setString(27, reservacionTO.getFormaRedencion());
                    statement.setBigDecimal(28, reservacionTO.getProductosTO().getPrecio());
                    statement.setBigDecimal(29, reservacionTO.getProductosTO().getPrecioIva());
                    statement.setInt(30, reservacionTO.getPtsSobrantes());
                    statement.setInt(31, reservacionTO.getProductosTO().getBonosRoext());
                    statement.setBigDecimal(32, reservacionTO.getProductosTO().getDescuento());
                    statement.setInt(33, reservacionTO.getProductosTO().getBonosAltoValor());
                    statement.setString(34, reservacionTO.getProductosTO().getAplicaPromocionGap() != null ? reservacionTO.getProductosTO().getAplicaPromocionGap() : "");
                    statement.setInt(35, reservacionTO.getProductosTO().getBonosGap());
                    statement.setInt(36, reservacionTO.getProductosTO().getIdPromocionGap());
                    statement.setInt(37, reservacionTO.getProductosTO().getIdPromocionGapCA());
                    statement.setInt(38, reservacionTO.getProductosTO().getVerPromocionGap());
                    statement.setString(39, reservacionTO.getProductosTO().getAplicaEP());
                    statement.setString(40, reservacionTO.getProductosTO().getTecnologia());
                } else {
                    StringBuffer sInserta = new StringBuffer("INSERT INTO ").append(this.schema_database).append("PTO_TBLRESERVACIONES(folio,cuenta,");
                    sInserta.append("  secuencia,idusuario,fechaoper,idproducto,ValorPuntos,");
                    sInserta.append("  DiferenciaPesos,Idpuntovta,IdRegion,FzaVta,IdPlan,");
                    sInserta.append("  Comentario,FechaExpira,Plannvo,Status,Linea,");
                    sInserta.append("  Descripcion,Marca,Modelo,TipoReden,Addeant,Tipoprom,");
                    sInserta.append("  Addnvo,plazoant,plazonvo,formared, precio, precioIVA,");
                    sInserta.append("  sobrantesbono,bonoroext,descuento,bonoaltovalor,");
                    sInserta.append(" APLICAPROMOGAP,BONOGAP,IDPROMOCIONGAP,IDPROMOCA,VERPROMOGAP,APLICAEP )");
                    sInserta.append("  VALUES(");
                    sInserta.append("?,?,?,?,?,?,?,?,?,?,");
                    sInserta.append("?,?,?,?,?,?,?,?,?,?,");
                    sInserta.append("?,?,?,?,?,?,?,?,?,?,");
                    sInserta.append("?,?,?,");
                    sInserta.append("?,?,?,?,?,?");
                    sInserta.append(")");
                    if (connection == null || connection.isClosed()) {
                        connection = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                    }
                    statement = connection.prepareStatement(sInserta.toString());
                    statement.setString(1, reservacionTO.getFolio());
                    statement.setString(2, reservacionTO.getTelefonoSimpleTO().getCuenta());
                    statement.setInt(3, reservacionTO.getTelefonoSimpleTO().getSecuencia());
                    statement.setString(4, reservacionTO.getUsuarioTO().getIdUsuario());
                    statement.setDate(5, new Date(fechaTransaccion));
                    statement.setString(6, reservacionTO.getProductosTO().getIdProducto());
                    statement.setInt(7, reservacionTO.getProductosTO().getValorPuntos());
                    statement.setInt(8, reservacionTO.getProductosTO().getDifPesos());
                    statement.setString(9, reservacionTO.getUsuarioTO().getPuntoVentaTO().getPtoVenta());
                    statement.setInt(10, reservacionTO.getTelefonoSimpleTO().getRegion());
                    statement.setString(11, reservacionTO.getFuerzaVenta());
                    statement.setString(12, reservacionTO.getMobileTO().getPlanM2K());
                    statement.setString(13, reservacionTO.getComentario());
                    statement.setDate(14, new Date(reservacionTO.getFechaExpiracion().getTime()));
                    statement.setString(15, reservacionTO.getPlanNuevo());
                    statement.setString(16, reservacionTO.getEstatus());
                    statement.setString(17, reservacionTO.getTelefonoSimpleTO().getLinea());
                    statement.setString(18, reservacionTO.getProductosTO().getDescripcion());
                    statement.setString(19, reservacionTO.getProductosTO().getMarca());
                    statement.setString(20, reservacionTO.getProductosTO().getModelo());
                    statement.setString(21, reservacionTO.getTipoRedencion());
                    statement.setDate(22, new Date(reservacionTO.getFechaAdendumAnterior().getTime()));
                    statement.setString(23, reservacionTO.getProductosTO().getTipoPromocion());
                    statement.setDate(24, new Date(reservacionTO.getFechaAdendumNuevo().getTime()));
                    statement.setString(25, reservacionTO.getPlazoAnterior());
                    statement.setString(26, reservacionTO.getPlazoNuevo());
                    statement.setString(27, reservacionTO.getFormaRedencion());
                    statement.setBigDecimal(28, reservacionTO.getProductosTO().getPrecio());
                    statement.setBigDecimal(29, reservacionTO.getProductosTO().getPrecioIva());
                    statement.setInt(30, reservacionTO.getPtsSobrantes());
                    statement.setInt(31, reservacionTO.getProductosTO().getBonosRoext());
                    statement.setBigDecimal(32, reservacionTO.getProductosTO().getDescuento());
                    statement.setInt(33, reservacionTO.getProductosTO().getBonosAltoValor());
                    statement.setString(34, reservacionTO.getProductosTO().getAplicaPromocionGap() != null ? reservacionTO.getProductosTO().getAplicaPromocionGap() : "");
                    statement.setInt(35, reservacionTO.getProductosTO().getBonosGap());
                    statement.setInt(36, reservacionTO.getProductosTO().getIdPromocionGap());
                    statement.setInt(37, reservacionTO.getProductosTO().getIdPromocionGapCA());
                    statement.setInt(38, reservacionTO.getProductosTO().getVerPromocionGap());
                    statement.setString(39, reservacionTO.getProductosTO().getAplicaEP());
                }
                if (statement.executeUpdate() > 0) {
                    mensajeTO.agregaMensaje(0, "Proceso Exitoso");
                } else {
                    mensajeTO.agregaMensaje(1, "Error al insertar la reservacion");
                }
            }
            catch (SQLException e) {
                if (e.toString().contains((CharSequence)"ORA-00001")) {
                    throw new CAException(-1, "PuntosDAO.insertReservacion[ya existe una reservacion para esta linea.]", (Exception)e);
                }
                throw new CAException(-1, "PuntosDAO.insertReservacion[" + e.toString() + "]", (Exception)e);
            }
            catch (Exception e) {
                throw new CAException(-1, "PuntosDAO.insertReservacion[" + e.toString() + "]", e);
            }
        }
        finally {
            if (statement != null) {
                try {
                    statement.close();
                    statement = null;
                }
                catch (Exception var10_13) {}
            }
        }
        return mensajeTO;
    }

    public MensajeTO actualizaConstancia(Connection connection, String estatus, String esnimeir, String esnimeip, String iccid, Timestamp fechfolio, String cuenta, int secuencia) throws CAException {
        MensajeTO mensajeTO;
        Connection lConn = null;
        PreparedStatement statement = null;
        String sUpdate = "UPDATE  " + this.schema_database + "PTO_TBLCONSTANCIA " + " SET ESTATUS=? ";
        if (esnimeir != null) {
            sUpdate = String.valueOf(sUpdate) + ",esnimeir = ?";
        }
        if (esnimeip != null) {
            sUpdate = String.valueOf(sUpdate) + ",esnimeip =?";
        }
        if (iccid != null) {
            sUpdate = String.valueOf(sUpdate) + ",iccid =? ";
        }
        sUpdate = String.valueOf(sUpdate) + " WHERE fechafolio =? AND cuenta = ? and Secuencia = ?";
        mensajeTO = new MensajeTO();
        try {
            try {
                lConn = connection == null ? ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul) : connection;
                statement = lConn.prepareStatement(sUpdate);
                int contador = 0;
                statement.setString(++contador, estatus);
                if (esnimeir != null) {
                    statement.setString(++contador, esnimeir);
                }
                if (esnimeip != null) {
                    statement.setString(++contador, esnimeip);
                }
                if (iccid != null) {
                    statement.setString(++contador, iccid);
                }
                statement.setTimestamp(++contador, fechfolio);
                statement.setString(++contador, cuenta);
                statement.setInt(++contador, secuencia);
                if (statement.executeUpdate() > 0) {
                    mensajeTO.agregaMensaje(0, "Proceso Exitoso");
                } else {
                    mensajeTO.agregaMensaje(1, "No se realizo la actualizacion de la constancia");
                }
            }
            catch (SQLException e) {
                throw new CAException(-1, "PuntosDAO.insertReservacion[" + e.toString() + "]", (Exception)e);
            }
            catch (Exception e) {
                throw new CAException(-1, "PuntosDAO.insertReservacion[" + e.toString() + "]", e);
            }
        }
        finally {
            if (statement != null) {
                try {
                    statement.close();
                    statement = null;
                }
                catch (Exception var15_19) {}
            }
            if (lConn == null && connection != null) {
                try {
                    connection.close();
                    connection = null;
                }
                catch (Exception var15_20) {}
            }
        }
        return mensajeTO;
    }

    public MensajeTO insertaHistoricoFolios(FolioSAPTO folioSAPTO, Connection connection, long fechaTransaccion) throws CAException {
        MensajeTO mensajeTO;
        mensajeTO = new MensajeTO();
        Connection lConn = null;
        PreparedStatement statement = null;
        try {
            try {
                lConn = connection == null ? ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul) : connection;
                String sInserta = "INSERT INTO " + this.schema_database + "PTO_TBLHISTORICOFOLIOS (fechamod, folio,iccidant,iccidnvo,esnimeiant1,esnimeiant2,esnimeinvo1,esnimeinvo2,usuario,puntovta)" + " VALUES (?,?,?,?,?,?,?,?,?,?)";
                statement = lConn.prepareStatement(sInserta);
                statement.setTimestamp(1, new Timestamp(fechaTransaccion));
                statement.setString(2, folioSAPTO.getFolio());
                if (folioSAPTO.getIccidant() != null) {
                    statement.setString(3, folioSAPTO.getIccidant());
                } else {
                    statement.setNull(3, 12);
                }
                if (folioSAPTO.getIccidnvo() != null) {
                    statement.setString(4, folioSAPTO.getIccidnvo());
                } else {
                    statement.setNull(4, 12);
                }
                if (folioSAPTO.getEsnimeiant1() != null) {
                    statement.setString(5, folioSAPTO.getEsnimeiant1());
                } else {
                    statement.setNull(5, 12);
                }
                if (folioSAPTO.getEsnimeiant2() != null) {
                    statement.setString(6, folioSAPTO.getEsnimeiant2());
                } else {
                    statement.setNull(6, 12);
                }
                if (folioSAPTO.getEsnimeinvo1() != null) {
                    statement.setString(7, folioSAPTO.getEsnimeinvo1());
                } else {
                    statement.setNull(7, 12);
                }
                if (folioSAPTO.getEsnimeinvo2() != null) {
                    statement.setString(8, folioSAPTO.getEsnimeinvo2());
                } else {
                    statement.setNull(8, 12);
                }
                if (folioSAPTO.getUsuario() != null) {
                    statement.setString(9, folioSAPTO.getUsuario());
                } else {
                    statement.setNull(9, 12);
                }
                if (folioSAPTO.getPuntovta() != null) {
                    statement.setString(10, folioSAPTO.getPuntovta());
                } else {
                    statement.setNull(10, 12);
                }
                if (statement.executeUpdate() > 0) {
                    mensajeTO.agregaMensaje(0, "Proceso Exitoso");
                } else {
                    mensajeTO.agregaMensaje(1, "No se insert el Folio.");
                }
            }
            catch (SQLException e) {
                throw new CAException(-1, "PuntosDAO.insertReservacion[" + e.toString() + "]", (Exception)e);
            }
            catch (Exception e) {
                throw new CAException(-1, "PuntosDAO.insertReservacion[" + e.toString() + "]", e);
            }
        }
        finally {
            if (statement != null) {
                try {
                    statement.close();
                    statement = null;
                }
                catch (Exception var10_13) {}
            }
            if (lConn == null && connection != null) {
                try {
                    connection.close();
                    connection = null;
                }
                catch (Exception var10_14) {}
            }
        }
        return mensajeTO;
    }

    public MensajeTO actualizaFolioSAP(FolioSAPTO folioSAPTO) throws CAException {
        MensajeTO mensajeTO;
        Connection connection = null;
        mensajeTO = new MensajeTO();
        try {
            try {
                long fechaTransaccion = System.currentTimeMillis();
                connection = ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul);
                connection.setAutoCommit(false);
                if (folioSAPTO.getDiasTranscurridos() <= 5) {
                    mensajeTO = this.actualizaConstancia(connection, folioSAPTO.getEstatus(), folioSAPTO.getEsnimeinvo1(), folioSAPTO.getEsnimeinvo2(), folioSAPTO.getIccidnvo(), folioSAPTO.getFechaFolio(), folioSAPTO.getCuenta(), folioSAPTO.getSecuencia());
                    if (mensajeTO.getIdMensaje() == 0) {
                        mensajeTO = this.insertaHistoricoFolios(folioSAPTO, connection, fechaTransaccion);
                    }
                } else {
                    mensajeTO.agregaMensaje(1, "Lo sentimos pero ya pasaron mas de cuatro dias de haberse realizado el tramite.");
                }
                connection.commit();
            }
            catch (SQLException e) {
                if (connection != null) {
                    try {
                        connection.rollback();
                    }
                    catch (Exception var5_7) {
                        // empty catch block
                    }
                }
                throw new CAException(-1, "PuntosDAO.insertReservacion[" + e.toString() + "]", (Exception)e);
            }
            catch (Exception e) {
                throw new CAException(-1, "PuntosDAO.insertReservacion[" + e.toString() + "]", e);
            }
        }
        finally {
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                    connection.close();
                    connection = null;
                }
                catch (Exception var7_10) {}
            }
        }
        return mensajeTO;
    }

    public MensajeTO actualizaReservacion(Connection connection, long fechaTransaccion, String usuario, String estatus, String comentario, String folio) throws CAException {
        return this.actualizaReservacion(connection, fechaTransaccion, usuario, estatus, comentario, folio, true);
    }

    public MensajeTO actualizaReservacion(Connection connection, long fechaTransaccion, String usuario, String estatus, String comentario, String folio, boolean cerrarConexion) throws CAException {
        MensajeTO mensajeTO;
        mensajeTO = new MensajeTO();
        Connection lConn = null;
        PreparedStatement statement = null;
        try {
            try {
                lConn = connection == null ? ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul) : connection;
                StringBuffer sUpdate = new StringBuffer("UPDATE ").append(this.schema_database).append("PTO_TBLRESERVACIONES ");
                sUpdate.append(" SET FechaUltMod=?,UsuarioMod =?");
                if (estatus != null) {
                    sUpdate.append(",Status =?");
                }
                if (comentario != null) {
                    sUpdate.append(",comentario =?");
                }
                sUpdate.append(" WHERE Folio =?");
                statement = lConn.prepareStatement(sUpdate.toString());
                int contador = 1;
                statement.setTimestamp(contador++, new Timestamp(fechaTransaccion));
                statement.setString(contador++, usuario);
                if (estatus != null) {
                    statement.setString(contador++, estatus);
                }
                if (comentario != null) {
                    statement.setString(contador++, comentario);
                }
                statement.setString(contador++, folio);
                if (statement.executeUpdate() > 0) {
                    mensajeTO.agregaMensaje(0, "Proceso Exitoso");
                } else {
                    mensajeTO.agregaMensaje(1, "No existe una reservacion para actualizar");
                }
            }
            catch (SQLException e) {
                throw new CAException(-1, "PuntosDAO.actualizaReservacion[" + e.toString() + "]", (Exception)e);
            }
            catch (Exception e) {
                throw new CAException(-1, "PuntosDAO.actualizaReservacion[" + e.toString() + "]", e);
            }
        }
        finally {
            if (statement != null) {
                try {
                    statement.close();
                    statement = null;
                }
                catch (Exception var15_18) {}
            }
            if (cerrarConexion && connection == null && lConn != null) {
                try {
                    lConn.close();
                    lConn = null;
                }
                catch (Exception var15_19) {}
            }
        }
        return mensajeTO;
    }

    public MensajeTO actualizaRedencion(Connection connection, String estatus, Timestamp fechaFolio, String cuenta, Timestamp fechaActualizacion, String usuarioActualizacion) throws CAException {
        MensajeTO mensajeTO;
        mensajeTO = new MensajeTO();
        Connection lConn = null;
        PreparedStatement statement = null;
        try {
            try {
                lConn = connection == null ? ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul) : connection;
                StringBuffer update = new StringBuffer("UPDATE ").append(this.schema_database).append("PTO_TBLREDENCION");
                update.append("   SET ESTATUS = ?");
                if (fechaActualizacion != null) {
                    update.append(",FechaUpd=? ");
                }
                if (usuarioActualizacion != null) {
                    update.append(",IdUsuarioUpd=? ");
                }
                update.append(" WHERE FECHAFOLIO= ? AND CUENTA = ?");
                statement = lConn.prepareStatement(update.toString());
                statement.setString(1, estatus);
                int contador = 2;
                if (fechaActualizacion != null) {
                    statement.setTimestamp(contador++, fechaActualizacion);
                }
                if (usuarioActualizacion != null) {
                    statement.setString(contador++, usuarioActualizacion);
                }
                statement.setTimestamp(contador++, fechaFolio);
                statement.setString(contador++, cuenta);
                if (statement.executeUpdate() > 0) {
                    mensajeTO.agregaMensaje(0, "Proceso Exitoso");
                } else {
                    mensajeTO.agregaMensaje(1, "No existe la reservacion a actualizar");
                }
            }
            catch (SQLException e) {
                throw new CAException(-1, "PuntosDAO.actualizaRedencion[" + e.toString() + "]", (Exception)e);
            }
            catch (Exception e) {
                throw new CAException(-1, "PuntosDAO.actualizaRedencion[" + e.toString() + "]", e);
            }
        }
        finally {
            if (statement != null) {
                try {
                    statement.close();
                    statement = null;
                }
                catch (Exception var13_17) {}
            }
            if (connection == null && lConn != null) {
                try {
                    lConn.close();
                    lConn = null;
                }
                catch (Exception var13_18) {}
            }
        }
        return mensajeTO;
    }

    public MensajeTO actualizaMsgEP(Connection connection, String folio, String cuenta, int secuencia, String rspEP) throws CAException {
        MensajeTO mensajeTO;
        mensajeTO = new MensajeTO();
        Connection lConn = null;
        PreparedStatement statement = null;
        try {
            try {
                lConn = connection == null ? ServiceLocator.getInstance().getConnection(ServiceLocator.jdbcCirculoAzul) : connection;
                StringBuffer update = new StringBuffer("UPDATE ").append(this.schema_database).append("PTO_TBLREDENCION");
                update.append(" SET MSGEP = ?");
                update.append(" WHERE CUENTA = ? AND SECUENCIA= ? AND FOLIO=? ");
                statement = lConn.prepareStatement(update.toString());
                statement.setString(1, rspEP);
                statement.setString(2, cuenta);
                statement.setInt(3, secuencia);
                statement.setString(4, folio);
                if (statement.executeUpdate() > 0) {
                    mensajeTO.agregaMensaje(0, "Proceso Exitoso");
                } else {
                    mensajeTO.agregaMensaje(1, "No existe la redencion a actualizar");
                }
            }
            catch (SQLException e) {
                throw new CAException(-1, "PuntosDAO.actualizaMsgEP[" + e.toString() + "]", (Exception)e);
            }
            catch (Exception e) {
                throw new CAException(-1, "PuntosDAO.actualizaMsgEP[" + e.toString() + "]", e);
            }
        }
        finally {
            if (statement != null) {
                try {
                    statement.close();
                    statement = null;
                }
                catch (Exception var11_15) {}
            }
            if (connection == null && lConn != null) {
                try {
                    lConn.close();
                    lConn = null;
                }
                catch (Exception var11_16) {}
            }
        }
        return mensajeTO;
    }
}

