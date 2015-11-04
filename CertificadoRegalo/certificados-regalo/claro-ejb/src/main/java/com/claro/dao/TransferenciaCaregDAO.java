/*
 * Decompiled with CFR 0_102.
 * 
 * Could not load the following classes:
 *  com.claro.exception.CAException
 *  com.claro.transfer.MensajeTO
 *  com.claro.transfer.MobileTO
 *  com.claro.transfer.ParametrosTO
 *  com.claro.transfer.PuntosTO
 *  com.claro.transfer.TelefonoTO
 *  com.claro.transfer.transpuntos.TransferenciaTO
 *  com.claro.util.Constantes
 *  com.claro.util.ServiceLocator
 *  com.claro.util.Utils
 *  org.apache.log4j.Logger
 */
package com.claro.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.claro.exception.CAException;
import com.claro.transfer.MensajeTO;
import com.claro.transfer.MobileTO;
import com.claro.transfer.ParametrosTO;
import com.claro.transfer.PuntosTO;
import com.claro.transfer.TelefonoTO;
import com.claro.transfer.transpuntos.TransferenciaTO;
import com.claro.util.Constantes;
import com.claro.util.ServiceLocator;
import com.claro.util.Utils;

public class TransferenciaCaregDAO extends TranasferenciaDAO {

	public TransferenciaTO transferirPuntosCareg(TransferenciaTO _transfTO)
			throws CAException {
		Connection cnx = null;
		PuntosTO puntosOrigenTO = new PuntosTO();
		PuntosDAO ptsDAO = new PuntosDAO();
		MensajeTO msgTO = new MensajeTO();
		int ptsDispOrigen = 0;
		try {
			try {
				long inicioProceso = System.currentTimeMillis();
				this.logger.info((Object) ("transferirPorRegion|Inicio|"
						+ Constantes.DATEFORMTALog.format(new java.util.Date())
						+ "|" + inicioProceso));
				puntosOrigenTO = this.consultasPuntosDAO.obtienePuntos(
						_transfTO.getCuentaOrigen(),
						_transfTO.getSecuenciaOrigen());
				ptsDispOrigen = puntosOrigenTO.getPtosDisponibles();
				_transfTO.setPuntosTrasnferidos(ptsDispOrigen);
				_transfTO.setPtosDisponiblesOrigen(ptsDispOrigen);
				_transfTO.setPuntosOrigenTO(puntosOrigenTO);
				cnx = ServiceLocator.getInstance().getConnection(
						ServiceLocator.jdbcCirculoAzul);
				cnx.setAutoCommit(false);
				TelefonoTO phoneDestinoTO = this.validaInfoTransferenciaRegion(
						cnx, _transfTO);
				if (phoneDestinoTO.getIdMensaje() == 0) {
					if (!this.actualizaTotalesDestino(cnx, _transfTO)) {
						throw new CAException(-1,
								"NO FUE POSIBLE ACTUALIZAR LOS TOTALESDE LA LINEA DESTINO ["
										+ _transfTO.getTelefonoDestino() + "]");
					}
					if (!this.guardarDetalleLinea(cnx, _transfTO, 2, false)) {
						throw new CAException(-1,
								"NO SE PUDO SALVAR EL DETALLE DE LA LINEA DESTINO ["
										+ _transfTO.getTelefonoDestino() + "]");
					}
					String comntDestino = this.crearComentario(ptsDispOrigen,
							_transfTO.getCuentaOrigen(), "",
							_transfTO.getIdUsuario(),
							_transfTO.getComentario(), 2, false);
					msgTO = ptsDAO.insertaComentarioTMP(cnx,
							_transfTO.getRegionDestino(),
							_transfTO.getCuentaDestino(),
							_transfTO.getIdUsuario(),
							System.currentTimeMillis(), comntDestino);
					if (msgTO.getIdMensaje() != 0) {
						throw new CAException(-1,
								"NO SE PUDO SALVAR EL SEGUNDO COMENTARIO.");
					}
					if (!this.actualizaTotalesOrigenPorRegion(cnx,
							_transfTO.getPuntosOrigenTO(),
							_transfTO.getCuentaOrigen(),
							_transfTO.getSecuenciaOrigen())) {
						throw new CAException(-1,
								"NO FUE POSIBLE ACTUALIZAR LOS TOTALES DE LA LINEA ORIGEN ["
										+ _transfTO.getTelefonoOrigen() + "]");
					}
					if (!this.guardarDetalleLinea(cnx, _transfTO, 1, false)) {
						throw new CAException(-1,
								"NO SE PUDO SALVAR EL DETALLE DE LA LINEA ORIGEN ["
										+ _transfTO.getTelefonoOrigen() + "]");
					}
					String comntOrigen = this.crearComentario(ptsDispOrigen,
							"", _transfTO.getCuentaDestino(),
							_transfTO.getIdUsuario(),
							_transfTO.getComentario(), 1, false);
					msgTO = ptsDAO.insertaComentarioTMP(cnx,
							_transfTO.getRegionOrigen(),
							_transfTO.getCuentaLineaOrigen(),
							_transfTO.getIdUsuario(),
							System.currentTimeMillis(), comntOrigen);
					if (msgTO.getIdMensaje() != 0) {
						throw new CAException(-1,
								"NO SE PUDO SALVAR EL PRIMER COMENTARIO.");
					}
					if (!this.registraHistoricoTransferncia(cnx, _transfTO)) {
						throw new CAException(-1,
								"NO SE PUDO REGISTRAR EL HISTORICO DE LA TRANSFERENCIA.");
					}
				}
				cnx.commit();
				this.logger.info((Object) ("transferirPuntosCareg|Fin|"
						+ Constantes.DATEFORMTALog.format(new java.util.Date())
						+ "|" + (System.currentTimeMillis() - inicioProceso)));
			} catch (SQLException se) {
				if (cnx != null) {
					try {
						cnx.rollback();
					} catch (Exception var8_13) {
						// empty catch block
					}
				}
				se.printStackTrace();
				this.error.info((Object) "Exception.transferirPorRegion:",
						(Throwable) se);
				throw new CAException(-1,
						"TransferenciaCaregDAO.transferirPorRegion["
								+ se.toString() + "]", (Exception) se);
			} catch (Exception e) {
				if (cnx != null) {
					try {
						cnx.rollback();
					} catch (Exception var8_14) {
						// empty catch block
					}
				}
				e.printStackTrace();
				this.error.info((Object) "Exception.transferirPorRegion:",
						(Throwable) e);
				throw new CAException(-1,
						"TransferenciaCaregDAO.transferirPorRegion["
								+ e.toString() + "]", e);
			}
		} finally {
			if (cnx != null) {
				try {
					cnx.setAutoCommit(true);
					cnx.close();
					cnx = null;
				} catch (Exception var13_17) {
				}
			}
		}
		return _transfTO;
	}

	public TransferenciaTO cancelaTransferenciaCareg(TransferenciaTO _transfTO)
			throws CAException {
		Connection cnx = null;
		PuntosDAO ptsDAO = new PuntosDAO();
		MensajeTO msgTO = new MensajeTO();
		int ptsDispOrigen = 0;
		try {
			try {
				long inicioProceso = System.currentTimeMillis();
				this.logger.info((Object) ("transferirPorRegion|Inicio|"
						+ Constantes.DATEFORMTALog.format(new java.util.Date())
						+ "|" + inicioProceso));
				TelefonoTO telefonoDestino = this.cuentaDestinoCancelacion(
						_transfTO.getCuentaOrigen(),
						_transfTO.getSecuenciaOrigen());
				_transfTO.setTelefonoTO(telefonoDestino);
				ptsDispOrigen = telefonoDestino.getPuntosTO()
						.getPtosDisponibles();
				_transfTO.setPuntosTrasnferidos(ptsDispOrigen);
				_transfTO.setPtosDisponiblesOrigen(ptsDispOrigen);
				_transfTO.setPuntosOrigenTO(telefonoDestino.getPuntosTO());
				cnx = ServiceLocator.getInstance().getConnection(
						ServiceLocator.jdbcCirculoAzul);
				cnx.setAutoCommit(false);
				this.validaCancelacionTransferencia(cnx, _transfTO);
				if (!this.cancelaTotales(cnx, _transfTO, true)) {
					throw new CAException(-1,
							"NO FUE POSIBLE ACTUALIZAR LOS TOTALESDE LA LINEA DESTINO ["
									+ _transfTO.getTelefonoDestino() + "]");
				}
				if (!this.guardarDetalleLinea(cnx, _transfTO, 2, true)) {
					throw new CAException(-1,
							"NO SE PUDO SALVAR EL DETALLE DE LA LINEA DESTINO ["
									+ _transfTO.getTelefonoDestino() + "]");
				}
				String comntDestino = this.crearComentario(ptsDispOrigen,
						_transfTO.getCuentaOrigen(), "",
						_transfTO.getIdUsuario(), _transfTO.getComentario(), 2,
						true);
				msgTO = ptsDAO.insertaComentarioTMP(cnx,
						_transfTO.getRegionDestino(),
						_transfTO.getCuentaDestino(), _transfTO.getIdUsuario(),
						System.currentTimeMillis(), comntDestino);
				if (msgTO.getIdMensaje() != 0) {
					throw new CAException(-1,
							"NO SE PUDO SALVAR EL SEGUNDO COMENTARIO.");
				}
				if (!this.cancelaTotales(cnx, _transfTO, false)) {
					throw new CAException(-1,
							"NO FUE POSIBLE ACTUALIZAR LOS TOTALES DE LA LINEA ORIGEN ["
									+ _transfTO.getTelefonoOrigen() + "]");
				}
				if (!this.guardarDetalleLinea(cnx, _transfTO, 1, true)) {
					throw new CAException(-1,
							"NO SE PUDO SALVAR EL DETALLE DE LA LINEA ORIGEN ["
									+ _transfTO.getTelefonoOrigen() + "]");
				}
				String comntOrigen = this.crearComentario(ptsDispOrigen, "",
						_transfTO.getCuentaDestino(), _transfTO.getIdUsuario(),
						_transfTO.getComentario(), 1, true);
				msgTO = ptsDAO.insertaComentarioTMP(cnx,
						_transfTO.getRegionOrigen(),
						_transfTO.getCuentaLineaOrigen(),
						_transfTO.getIdUsuario(), System.currentTimeMillis(),
						comntOrigen);
				if (msgTO.getIdMensaje() != 0) {
					throw new CAException(-1,
							"NO SE PUDO SALVAR EL PRIMER COMENTARIO.");
				}
				if (!this.borraHistoricoTransferencia(cnx, _transfTO)) {
					throw new CAException(-1,
							"NO SE PUDO BORRAR EL REGISTRO DE HISTORICO DE LA TRANSFERENCIA.");
				}
				cnx.commit();
				this.logger.info((Object) ("transferirPuntosCareg|Fin|"
						+ Constantes.DATEFORMTALog.format(new java.util.Date())
						+ "|" + (System.currentTimeMillis() - inicioProceso)));
			} catch (SQLException se) {
				if (cnx != null) {
					try {
						cnx.rollback();
					} catch (Exception var7_12) {
						// empty catch block
					}
				}
				se.printStackTrace();
				this.error.info((Object) "Exception.transferirPorRegion:",
						(Throwable) se);
				throw new CAException(-1,
						"TransferenciaCaregDAO.transferirPorRegion["
								+ se.toString() + "]", (Exception) se);
			} catch (Exception e) {
				if (cnx != null) {
					try {
						cnx.rollback();
					} catch (Exception var7_13) {
						// empty catch block
					}
				}
				e.printStackTrace();
				this.error.info((Object) "Exception.transferirPorRegion:",
						(Throwable) e);
				throw new CAException(-1,
						"TransferenciaCaregDAO.transferirPorRegion["
								+ e.toString() + "]", e);
			}
		} finally {
			if (cnx != null) {
				try {
					cnx.setAutoCommit(true);
					cnx.close();
					cnx = null;
				} catch (Exception var12_16) {
				}
			}
		}
		return _transfTO;
	}

	private TelefonoTO validaInfoTransferenciaRegion(Connection cnx,
			TransferenciaTO _transfTO) throws CAException {
		MobileTO mobileDestinoTO = null;
		TelefonoTO phoneDestinoTO = new TelefonoTO();
		ParametrosTO paramsTO = null;
		try {
			paramsTO = new ParametrosTO();
			paramsTO.setTelefono(_transfTO.getTelefonoDestino());
			paramsTO.setCuenta(_transfTO.getCuentaDestino());
			paramsTO.setRegion(_transfTO.getRegionDestino());
			if (_transfTO.getRegionOrigen() == _transfTO.getRegionDestino()) {
				throw new CAException(-1,
						"CUENTA ORIGEN Y DESTINO PERTENECEN A LA MISMA REGION.");
			}
			mobileDestinoTO = this.m2kDAO.consultaDatosM2K(paramsTO);
			_transfTO.setSecuenciaDestino(Integer.parseInt(mobileDestinoTO
					.getSecuencia()));
			if (mobileDestinoTO.getIdMensaje() == -1) {
				throw new CAException(-1, mobileDestinoTO.getMensaje()
						.toUpperCase());
			}
			this.consultasPuntosDAO.consultaDatosPuntos(paramsTO,
					phoneDestinoTO, mobileDestinoTO, cnx);
			phoneDestinoTO.setMobileTO(mobileDestinoTO);
			if (phoneDestinoTO.getIdMensaje() == -1
					&& phoneDestinoTO.getMensaje().equals(
							"Cuenta Corporativa, no participa en Puntos.")) {
				throw new CAException(-1,
						"CUENTA DESTINO CORPORATIVA, NO PARTICIPA EN PUNTOS.");
			}
			if (!_transfTO.getCuentaOrigen().equals(
					_transfTO.getCuentaLineaOrigen())) {
				throw new CAException(-1,
						"CUENTA ORIGEN INCONSISTENTE EN M2K Y PUNTOS.");
			}
			if (!(mobileDestinoTO.getCuenta().equals(
					_transfTO.getCuentaDestino()) && mobileDestinoTO
					.getTelefono().equals(_transfTO.getTelefonoDestino()))) {
				throw new CAException(-1,
						"LA CUENTA Y/O TELEFONO DESTINO ESTA INCORRECTO Y/O INCONSISTENTE EN M2K.");
			}
			if (_transfTO.getPtosDisponiblesOrigen() <= 0) {
				throw new CAException(-1,
						"NO HAY PUNTOS EN LA LINEA ORIGEN PARA LA TRANSFERENCIA.");
			}
			if (!_transfTO.getEstatusLineaOrigen().equals("AN")) {
				throw new CAException(-1,
						"LA LINEA ORIGEN DEBE ESTAR CANCELADA.");
			}
			if (Utils.diferenciaDiasTransf((String) mobileDestinoTO
					.getFecAltaUser()) > 60) {
				throw new CAException(-1,
						"LA TRANSFERENCIA SOLO APLICA LOS PRIMEROS 60 DIAS DE ACTIVADA LA CUENTA.");
			}
			if (!_transfTO.getRfcOrigen().equals(mobileDestinoTO.getRfc())) {
				throw new CAException(-1,
						"FAVOR DE VALIDAR QUE EL RFC DE LA LINEA ORIGEN Y DESTINO SEA EL MISMO.");
			}
			if (this.existeRedencion(cnx, _transfTO.getTelefonoDestino(),
					_transfTO.getCuentaDestino())) {
				throw new CAException(
						-1,
						"NO SE PUEDE REALIZAR LA TRANSFERENCIA DE PUNTOS<BR>PORQUE EXISTE UNA REDENCION PARA LA LINEA ["
								+ _transfTO.getTelefonoDestino() + "]");
			}
			if (this.existeRecepcionPuntos(cnx, _transfTO.getCuentaDestino(),
					phoneDestinoTO.getMobileTO().getSecuencia())) {
				throw new CAException(
						-1,
						"NO SE PUEDE REALIZAR LA TRANSFERENCIA DE PUNTOS PORQUE<BR>LA LINEA DESTINO YA TIENE UNA RECEPCION DE PUNTOS PREVIA.");
			}
			_transfTO.setTelefonoTO(phoneDestinoTO);
			if (phoneDestinoTO.getIdMensaje() == -1) {
				phoneDestinoTO.setPuntosTO(new PuntosTO());
				this.sincronizaEnPuntos(cnx, _transfTO, true);
			}
			phoneDestinoTO.setIdMensaje(0);
			phoneDestinoTO.setMensaje("");
		} catch (Exception e) {
			e.printStackTrace();
			this.error.info(
					(Object) "Exception.validaInfoTransferenciaRegion:",
					(Throwable) e);
			throw new CAException(-1,
					"TransferenciaCaregDAO.validaInfoTransferenciaRegion["
							+ e.toString() + "]", e);
		}
		return phoneDestinoTO;
	}

	private void validaCancelacionTransferencia(Connection cnx,
			TransferenciaTO _transfTO) throws CAException {
		ParametrosTO paramsTO = null;
		try {
			paramsTO = new ParametrosTO();
			paramsTO.setTelefono(_transfTO.getTelefonoDestino());
			paramsTO.setCuenta(_transfTO.getCuentaDestino());
			paramsTO.setRegion(_transfTO.getRegionDestino());
			if (_transfTO.getPtosDisponiblesOrigen() <= 0) {
				throw new CAException(-1,
						"NO HAY PUNTOS EN LA LINEA ORIGEN PARA LA TRANSFERENCIA.");
			}
			SimpleDateFormat format = Constantes.DATEFORMATyyyy_MM_dd;
			String fechaAltaUser = format.format(_transfTO.getTelefonoTO()
					.getFechaAlta());
			if (Utils.diferenciaDiasTransf((String) fechaAltaUser) > 60) {
				throw new CAException(-1,
						"LA TRANSFERENCIA SOLO APLICA LOS PRIMEROS 60 DIAS DE ACTIVADA LA CUENTA.");
			}
			if (this.existeRedencion(cnx, _transfTO.getTelefonoDestino(),
					_transfTO.getCuentaDestino())) {
				throw new CAException(
						-1,
						"NO SE PUEDE REALIZAR LA TRANSFERENCIA DE PUNTOS<BR>PORQUE EXISTE UNA REDENCION PARA LA LINEA ["
								+ _transfTO.getTelefonoDestino() + "]");
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.error.info(
					(Object) "Exception.validaInfoTransferenciaRegion:",
					(Throwable) e);
			throw new CAException(-1,
					"TransferenciaCaregDAO.validaInfoTransferenciaRegion["
							+ e.toString() + "]", e);
		}
	}

	private boolean existeRedencion(Connection _cnx, String _telefDestino,
			String _ctaDestino) throws CAException {
		boolean existe;
		PreparedStatement ps = null;
		ResultSet rs = null;
		existe = false;
		try {
			try {
				ps = _cnx.prepareStatement("SELECT IDREGION   FROM "
						+ this.schema_database + "PTO_TBLREDENCION "
						+ " WHERE CUENTA = ? " + "\tAND LINEA = ? "
						+ "\tAND ESTATUS = ?");
				ps.setString(1, _ctaDestino);
				ps.setString(2, _telefDestino);
				ps.setString(3, "A");
				rs = ps.executeQuery();
				if (rs.next()) {
					existe = true;
				}
			} catch (SQLException se) {
				se.printStackTrace();
				this.error.info((Object) "Exception.existeRedencion:",
						(Throwable) se);
				throw new CAException(-1,
						"TransferenciaCaregDAO.existeRedencion["
								+ se.toString() + "]", (Exception) se);
			} catch (Exception e) {
				e.printStackTrace();
				this.error.info((Object) "Exception.existeRedencion:",
						(Throwable) e);
				throw new CAException(-1,
						"TransferenciaCaregDAO.existeRedencion[" + e.toString()
								+ "]", e);
			}
		} finally {
			if (rs != null) {
				try {
					rs.close();
					rs = null;
				} catch (Exception var9_12) {
				}
			}
			if (ps != null) {
				try {
					ps.close();
					ps = null;
				} catch (Exception var9_13) {
				}
			}
		}
		return existe;
	}

	private boolean existeRecepcionPuntos(Connection _cnx, String _ctaDestino,
			String _secuenciaDestino) throws CAException {
		boolean existe;
		PreparedStatement ps = null;
		ResultSet rs = null;
		existe = false;
		String like = "Recep%";
		try {
			try {
				ps = _cnx.prepareStatement("SELECT * FROM "
						+ this.schema_database + "PTO_TBLMSTRDETALLE "
						+ " WHERE CUENTA = ? " + "   AND SECUENCIA = ? "
						+ "   AND IDMOVTO = ? " + "\tAND REFERENCIA LIKE ?");
				ps.setString(1, _ctaDestino);
				ps.setString(2, _secuenciaDestino);
				ps.setInt(3, 13);
				ps.setString(4, like);
				rs = ps.executeQuery();
				if (rs.next()) {
					existe = true;
				}
			} catch (SQLException se) {
				se.printStackTrace();
				this.error.info((Object) "Exception.existeRecepcionPuntos:",
						(Throwable) se);
				throw new CAException(-1,
						"TransferenciaCaregDAO.existeRecepcionPuntos["
								+ se.toString() + "]", (Exception) se);
			} catch (Exception e) {
				e.printStackTrace();
				this.error.info((Object) "Exception.existeRecepcionPuntos:",
						(Throwable) e);
				throw new CAException(-1,
						"TransferenciaCaregDAO.existeRecepcionPuntos["
								+ e.toString() + "]", e);
			}
		} finally {
			if (rs != null) {
				try {
					rs.close();
					rs = null;
				} catch (Exception var10_13) {
				}
			}
			if (ps != null) {
				try {
					ps.close();
					ps = null;
				} catch (Exception var10_14) {
				}
			}
		}
		return existe;
	}

	private boolean actualizaTotalesDestino(Connection _cnx,
			TransferenciaTO _transfTO) throws CAException {
		int rows;
		PreparedStatement ps = null;
		StringBuffer qry = new StringBuffer();
		PuntosTO ptsOrigen = _transfTO.getPuntosOrigenTO();
		TelefonoTO phoneDestinoTO = _transfTO.getTelefonoTO();
		String _ctaDest = _transfTO.getCuentaDestino();
		String _secDest = phoneDestinoTO.getMobileTO().getSecuencia();
		rows = 0;
		try {
			try {
				qry.append("UPDATE " + this.schema_database + "PTO_TBLTOTALES ");
				qry.append("   SET ");
				qry.append(" PUNTOSRENTA       = (PUNTOSRENTA + ?) ");
				qry.append(" ,PUNTOSACAD       = (PUNTOSACAD + ?)");
				qry.append(" ,PUNTOSACAD1      = (PUNTOSACAD1 + ?)");
				qry.append(" ,PUNTOSACAD2      = (PUNTOSACAD2 + ?)");
				qry.append(" ,PUNTOSANTIGUEDAD = (PUNTOSANTIGUEDAD + ?)");
				qry.append(" ,PUNTOSPROMOCION  = (PUNTOSPROMOCION + ?)");
				qry.append(" ,PUNTOSTRANSF     = (PUNTOSTRANSF + ?)");
				qry.append(" ,PUNTOSCADUC      = (PUNTOSCADUC + ?)");
				qry.append(" ,PUNTOSREDIM      = (PUNTOSREDIM + ?)");
				qry.append(" ,BonoEquipo      = (BonoEquipo + ?)");
				qry.append(" ,PUNTOSEXCEDENTES  = (PUNTOSEXCEDENTES + ?)");
				qry.append(", FECHACAD = ?");
				qry.append(", FECHACAD1 = ?");
				qry.append(", FECHACAD2 = ?");
				qry.append(", FECHACADU  = ?");
				qry.append(", PUNTOSPROM = ?");
				qry.append(", SALDOANT   = ?");
				qry.append(", FECHAFAC   = ?");
				qry.append(" WHERE  CUENTA = ?");
				qry.append(" AND SECUENCIA = ?");
				ps = _cnx.prepareStatement(qry.toString());
				ps.setInt(1, ptsOrigen.getPtsRenta());
				ps.setInt(2, ptsOrigen.getPtsPorVencer());
				ps.setInt(3, ptsOrigen.getPtsPorVencer1());
				ps.setInt(4, ptsOrigen.getPtsPorVencer2());
				ps.setInt(5, ptsOrigen.getPtsAntiguedad());
				ps.setInt(6, ptsOrigen.getPtsPromocion());
				ps.setInt(7, ptsOrigen.getPtsTransferidos());
				ps.setInt(8, ptsOrigen.getPtsVencidos());
				ps.setInt(9, ptsOrigen.getPtsRedimidos());
				ps.setInt(10, ptsOrigen.getBonoEquipo());
				ps.setInt(11, ptsOrigen.getPtsExcedentes());
				if (ptsOrigen.getFecVencer() != null) {
					ps.setDate(12, new Date(ptsOrigen.getFecVencer().getTime()));
				} else {
					ps.setNull(12, 91);
				}
				if (ptsOrigen.getFecVencer1() != null) {
					ps.setDate(13,
							new Date(ptsOrigen.getFecVencer1().getTime()));
				} else {
					ps.setNull(13, 91);
				}
				if (ptsOrigen.getFecVencer2() != null) {
					ps.setDate(14,
							new Date(ptsOrigen.getFecVencer2().getTime()));
				} else {
					ps.setNull(14, 91);
				}
				if (ptsOrigen.getFecVencidos() != null) {
					ps.setDate(15, new Date(ptsOrigen.getFecVencidos()
							.getTime()));
				} else {
					ps.setNull(15, 91);
				}
				ps.setString(16, ptsOrigen.getPtsBonoProm());
				ps.setInt(17, ptsOrigen.getPtsSaldoAnt());
				if (ptsOrigen.getFecFactura() != null) {
					ps.setDate(18,
							new Date(ptsOrigen.getFecFactura().getTime()));
				} else {
					ps.setNull(18, 91);
				}
				ps.setString(19, _ctaDest);
				ps.setString(20, _secDest);
				phoneDestinoTO.getPuntosTO().setPtsRedimidos(
						ptsOrigen.getPtsRedimidos());
				phoneDestinoTO.getPuntosTO().setPtsTransferidos(
						ptsOrigen.getPtsTransferidos());
				phoneDestinoTO.getPuntosTO().setPtsPorVencer(
						ptsOrigen.getPtsPorVencer());
				phoneDestinoTO.getPuntosTO().setFecVencer(
						ptsOrigen.getFecVencer());
				phoneDestinoTO.getPuntosTO().setPtsPorVencer1(
						ptsOrigen.getPtsPorVencer1());
				phoneDestinoTO.getPuntosTO().setFecVencer1(
						ptsOrigen.getFecVencer1());
				phoneDestinoTO.getPuntosTO().setPtsPorVencer2(
						ptsOrigen.getPtsPorVencer2());
				phoneDestinoTO.getPuntosTO().setFecVencer2(
						ptsOrigen.getFecVencer2());
				phoneDestinoTO.getPuntosTO().setPtsRenta(
						ptsOrigen.getPtsRenta());
				phoneDestinoTO.getPuntosTO().setPtsExcedentes(
						ptsOrigen.getPtsExcedentes());
				phoneDestinoTO.getPuntosTO().setPtsVencidos(
						ptsOrigen.getPtsVencidos());
				phoneDestinoTO.getPuntosTO().setFecVencidos(
						ptsOrigen.getFecVencidos());
				phoneDestinoTO.getPuntosTO().setPtsAntiguedad(
						ptsOrigen.getPtsAntiguedad());
				phoneDestinoTO.getPuntosTO().setPtsPromocion(
						ptsOrigen.getPtsPromocion());
				phoneDestinoTO.getPuntosTO().setPtosStatus(
						ptsOrigen.getPtosStatus());
				phoneDestinoTO.getPuntosTO().setPtosDisponibles(
						ptsOrigen.getPtosDisponibles());
				phoneDestinoTO.getPuntosTO().setBonoEquipo(
						ptsOrigen.getBonoEquipo());
				phoneDestinoTO.getPuntosTO().setFecFactura(
						ptsOrigen.getFecFactura());
				rows = ps.executeUpdate();
			} catch (SQLException se) {
				se.printStackTrace();
				this.error.info((Object) "Exception.actualizaTotalesDestino:",
						(Throwable) se);
				throw new CAException(-1,
						"TransferenciaCaregDAO.actualizaTotalesDestino["
								+ se.toString() + "]", (Exception) se);
			} catch (Exception e) {
				e.printStackTrace();
				this.error.info((Object) "Exception.actualizaTotalesDestino:",
						(Throwable) e);
				throw new CAException(-1,
						"TransferenciaCaregDAO.actualizaTotalesDestino["
								+ e.toString() + "]", e);
			}
		} finally {
			if (ps != null) {
				try {
					ps.close();
					ps = null;
				} catch (Exception var12_14) {
				}
			}
		}
		if (rows > 0) {
			return true;
		}
		return false;
	}

	private boolean actualizaTotalesOrigenPorRegion(Connection _cnx,
			PuntosTO puntosOrigenTO, String _ctaOrigen, int _secuenciaOrigen)
			throws CAException {
		int rows;
		rows = 0;
		PreparedStatement ps = null;
		try {
			try {
				ps = _cnx
						.prepareStatement(" UPDATE "
								+ this.schema_database
								+ "PTO_TBLTOTALES "
								+ " \t SET PUNTOSACUM = ?, PUNTOSRENTA = ?, PUNTOSEXCEDENTES = ?, PUNTOSREDIM = ?,"
								+ "    \t PUNTOSTRANSF = ?, PUNTOSALIANZAS = ?, PUNTOSCADUC = ?, PUNTOSACAD = ?,"
								+ "    \t PUNTOSACAD1 = ?, PUNTOSACAD2 = ?, PUNTOSPORBONO = ?, PUNTOSBONOANT = ?,"
								+ "    \t FECHACAD = ?, FECHACAD1 = ?, FECHACAD2 = ?, FECHACADU = ?,"
								+ "    \t PUNTOSANTIGUEDAD = ?, PUNTOSPROMOCION = ?, BONOEQUIPO = ?,"
								+ "    \t PUNTOSPROM = ?, SALDOANT = ?, PUNTOSANTIG = ? "
								+ "  WHERE CUENTA = ?" + "\t AND SECUENCIA = ?");
				ps.setInt(1, 0);
				ps.setInt(2, 0);
				ps.setInt(3, 0);
				ps.setInt(4, 0);
				ps.setInt(5, 0);
				ps.setInt(6, 0);
				ps.setInt(7, 0);
				ps.setInt(8, 0);
				ps.setInt(9, 0);
				ps.setInt(10, 0);
				ps.setInt(11, 0);
				ps.setInt(12, 0);
				ps.setNull(13, 91);
				ps.setNull(14, 91);
				ps.setNull(15, 91);
				ps.setNull(16, 91);
				ps.setInt(17, 0);
				ps.setInt(18, 0);
				ps.setInt(19, 0);
				ps.setInt(20, 0);
				ps.setInt(21, 0);
				ps.setInt(22, 0);
				ps.setString(23, _ctaOrigen);
				ps.setInt(24, _secuenciaOrigen);
				rows = ps.executeUpdate();
			} catch (SQLException se) {
				se.printStackTrace();
				this.error.info(
						(Object) "Exception.actualizaTotalesPorRegion:",
						(Throwable) se);
				throw new CAException(-1,
						"TransferenciaCaregDAO.actualizaTotalesPorRegion["
								+ se.toString() + "]", (Exception) se);
			} catch (Exception e) {
				e.printStackTrace();
				this.error.info(
						(Object) "Exception.actualizaTotalesPorRegion:",
						(Throwable) e);
				throw new CAException(-1,
						"TransferenciaCaregDAO.actualizaTotalesPorRegion["
								+ e.toString() + "]", e);
			}
		} finally {
			if (ps != null) {
				try {
					ps.close();
					ps = null;
				} catch (Exception var8_10) {
				}
			}
		}
		if (rows > 0) {
			return true;
		}
		return false;
	}

	private void sincronizaEnPuntos(Connection _cnx, TransferenciaTO _transfTO,
			boolean _sincroniza) throws CAException {
		try {
			if (!(this.existeLinea(_cnx, _transfTO.getCuentaDestino()) || this
					.crearLineaPuntos(_cnx, _transfTO))) {
				throw new CAException(-1,
						"NO FUE POSIBLE CREAR EL REGISTRO EN LINEAS");
			}
			if (!(this.existeTotales(_cnx, _transfTO.getCuentaDestino(),
					_transfTO.getTelefonoTO().getMobileTO().getSecuencia()) || this
					.creaTotalesLinea(_cnx, _transfTO.getCuentaDestino(),
							_transfTO.getTelefonoTO().getMobileTO()
									.getSecuencia()))) {
				throw new CAException(-1,
						"NO FUE POSIBLE CREAR EL REGISTRO EN TOTALES");
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.error.info((Object) "Exception.sincronizaEnPuntos:",
					(Throwable) e);
			throw new CAException(-1,
					"TransferenciaCaregDAO.sincronizaEnPuntos[" + e.toString()
							+ "]", e);
		}
	}

	private boolean existeLinea(Connection _cnx, String _cuentaDest)
			throws CAException {
		boolean existe;
		PreparedStatement ps = null;
		ResultSet rs = null;
		existe = false;
		try {
			try {
				ps = _cnx.prepareStatement(" SELECT CUENTA FROM "
						+ this.schema_database + "PTO_TBLLINEAS "
						+ "  WHERE CUENTA = ?");
				ps.setString(1, _cuentaDest);
				rs = ps.executeQuery();
				if (rs.next()) {
					existe = true;
				}
			} catch (SQLException se) {
				se.printStackTrace();
				this.error.info((Object) "Exception.existeLinea:",
						(Throwable) se);
				throw new CAException(-1, "TransferenciaCaregDAO.existeLinea["
						+ se.toString() + "]", (Exception) se);
			} catch (Exception e) {
				e.printStackTrace();
				this.error.info((Object) "Exception.existeLinea:",
						(Throwable) e);
				throw new CAException(-1, "TransferenciaCaregDAO.existeLinea["
						+ e.toString() + "]", e);
			}
		} finally {
			if (rs != null) {
				try {
					rs.close();
					rs = null;
				} catch (Exception var8_11) {
				}
			}
			if (ps != null) {
				try {
					ps.close();
					ps = null;
				} catch (Exception var8_12) {
				}
			}
		}
		return existe;
	}

	private boolean crearLineaPuntos(Connection _cnx, TransferenciaTO _transfTO)
			throws CAException {
		int rows;
		PreparedStatement ps = null;
		MobileTO m2kTO = null;
		rows = 0;
		try {
			try {
				m2kTO = _transfTO.getTelefonoTO().getMobileTO();
				ps = _cnx
						.prepareStatement("INSERT INTO "
								+ this.schema_database
								+ "PTO_TBLLINEAS "
								+ "(CUENTA, SECUENCIA, CTAPADRE, LINEA, IDREGION, PLAN, STATUSTEL, "
								+ " CICLOFACT, ADDENDUM, FECHAADD, FECHAALTA, FECHAANT, SISTEMA,"
								+ " STATUSPUNTOS, STATUSCARTA, MODSUBASTA, anacr)"
								+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?   ,?)");
				ps.setString(1, m2kTO.getCuenta());
				ps.setString(2, m2kTO.getSecuencia());
				ps.setString(3, m2kTO.getCuentaPadre());
				ps.setString(4, m2kTO.getTelefono());
				ps.setInt(5, _transfTO.getRegionDestino());
				ps.setString(6, m2kTO.getPlanM2K());
				ps.setString(7, m2kTO.getStatus());
				ps.setString(8, m2kTO.getCiclo());
				ps.setInt(9, Integer.valueOf(m2kTO.getAddM2K()));
				ps.setDate(10, new Date(_transfTO.getTelefonoTO().getMobileTO()
						.getFechaEfectiva().getTime()));
				ps.setDate(11, new Date(System.currentTimeMillis()));
				ps.setDate(12, new Date(m2kTO.getFechaAltaUser().getTime()));
				ps.setString(13, "M2K");
				ps.setInt(14, 0);
				ps.setString(15, "P");
				ps.setInt(16, 0);
				ps.setString(17, _transfTO.getTelefonoTO().getSAnacr());
				rows = ps.executeUpdate();
			} catch (SQLException se) {
				se.printStackTrace();
				this.error.info((Object) "Exception.crearLineaPuntos:",
						(Throwable) se);
				throw new CAException(-1,
						"TransferenciaCaregDAO.crearLineaPuntos["
								+ se.toString() + "]", (Exception) se);
			} catch (Exception e) {
				e.printStackTrace();
				this.error.info((Object) "Exception.crearLineaPuntos:",
						(Throwable) e);
				throw new CAException(-1,
						"TransferenciaCaregDAO.crearLineaPuntos["
								+ e.toString() + "]", e);
			}
		} finally {
			m2kTO = null;
			if (ps != null) {
				try {
					ps.close();
					ps = null;
				} catch (Exception var8_10) {
				}
			}
		}
		if (rows > 0) {
			return true;
		}
		return false;
	}

	private boolean creaTotalesLinea(Connection _cnx, String _ctaDest,
			String _secDest) throws CAException {
		int rows;
		PreparedStatement ps = null;
		rows = 0;
		try {
			try {
				ps = _cnx
						.prepareStatement("INSERT INTO "
								+ this.schema_database
								+ "PTO_TBLTOTALES(Cuenta, Secuencia, Fechafac, Puntosacum,"
								+ " Puntosredim, Puntostransf, Puntosalianzas, Puntoscaduc, Puntosacad, Puntosacad2,"
								+ " Puntosporbono, Puntosbonoant, Puntosrenta, Puntosexcedentes, Fechacad,"
								+ " Fechacad2, Puntosacad1, Fechacad1, Puntosprom, Fechacadu, Bbono, Saldoant,"
								+ " PuntosAntig, PuntosSubasta, PuntosAntiguedad, PuntosPromocion, BonoEquipo,"
								+ " PUNTOSACADDISP, PUNTOSACAD2DISP, PUNTOSACAD1DISP, PUNTOSRENTADISP)"
								+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,"
								+ "\t\t?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
								+ "\t\t?,?,?,?)");
				ps.setString(1, _ctaDest);
				ps.setInt(2, Integer.parseInt(_secDest));
				ps.setDate(3, new Date(System.currentTimeMillis()));
				ps.setInt(4, 0);
				ps.setInt(5, 0);
				ps.setInt(6, 0);
				ps.setInt(7, 0);
				ps.setInt(8, 0);
				ps.setInt(9, 0);
				ps.setInt(10, 0);
				ps.setInt(11, 0);
				ps.setInt(12, 0);
				ps.setInt(13, 0);
				ps.setInt(14, 0);
				ps.setNull(15, 91);
				ps.setNull(16, 91);
				ps.setInt(17, 0);
				ps.setNull(18, 91);
				ps.setString(19, "0");
				ps.setNull(20, 91);
				ps.setInt(21, 0);
				ps.setInt(22, 0);
				ps.setInt(23, 0);
				ps.setInt(24, 0);
				ps.setInt(25, 0);
				ps.setInt(26, 0);
				ps.setInt(27, 0);
				ps.setInt(28, 0);
				ps.setInt(29, 0);
				ps.setInt(30, 0);
				ps.setInt(31, 0);
				rows = ps.executeUpdate();
			} catch (SQLException se) {
				se.printStackTrace();
				this.error.info((Object) "Exception.creaTotalesLinea:",
						(Throwable) se);
				throw new CAException(-1,
						"TransferenciaCaregDAO.creaTotalesLinea["
								+ se.toString() + "]", (Exception) se);
			} catch (Exception e) {
				e.printStackTrace();
				this.error.info((Object) "Exception.creaTotalesLinea:",
						(Throwable) e);
				throw new CAException(-1,
						"TransferenciaCaregDAO.creaTotalesLinea["
								+ e.toString() + "]", e);
			}
		} finally {
			if (ps != null) {
				try {
					ps.close();
					ps = null;
				} catch (Exception var8_10) {
				}
			}
		}
		if (rows > 0) {
			return true;
		}
		return false;
	}

	private boolean registraHistoricoTransferncia(Connection _cnx,
			TransferenciaTO _transfTO) throws CAException {
		int rows;
		PreparedStatement ps = null;
		rows = 0;
		try {
			try {
				ps = _cnx
						.prepareStatement("INSERT INTO "
								+ this.schema_database
								+ "PTO_TBLHISTORICOTRANSF "
								+ "(CUENTA_DEST, SECUENCIA_DEST, LINEA_DEST, FECHAOPERACION,"
								+ " CUENTA, SECUENCIA, LINEA, FECHAANT,"
								+ " PUNTOSRENTA, PUNTOSACAD, PUNTOSACAD1,PUNTOSACAD2, "
								+ " PUNTOSANTIGUEDAD, PUNTOSPROMOCION, PUNTOSTRANSF, PUNTOSCADUC, "
								+ " PUNTOSREDIM, PUNTOSEXCEDENTES) "
								+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
				MobileTO m2kTO = _transfTO.getTelefonoTO().getMobileTO();
				PuntosTO ptsOrigen = _transfTO.getPuntosOrigenTO();
				ps.setString(1, _transfTO.getCuentaDestino());
				ps.setString(2, m2kTO.getSecuencia());
				ps.setString(3, _transfTO.getTelefonoDestino());
				ps.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
				ps.setString(5, _transfTO.getCuentaOrigen());
				ps.setInt(6, _transfTO.getSecuenciaOrigen());
				ps.setString(7, _transfTO.getTelefonoOrigen());
				ps.setDate(8, new Date(m2kTO.getFechaAltaUser().getTime()));
				ps.setInt(9, ptsOrigen.getPtsRenta());
				ps.setInt(10, ptsOrigen.getPtsPorVencer());
				ps.setInt(11, ptsOrigen.getPtsPorVencer1());
				ps.setInt(12, ptsOrigen.getPtsPorVencer2());
				ps.setInt(13, ptsOrigen.getPtsAntiguedad());
				ps.setInt(14, ptsOrigen.getPtsPromocion());
				ps.setInt(15, ptsOrigen.getPtsTransferidos());
				ps.setInt(16, ptsOrigen.getPtsVencidos());
				ps.setInt(17, ptsOrigen.getPtsRedimidos());
				ps.setInt(18, ptsOrigen.getPtsExcedentes());
				rows = ps.executeUpdate();
				ptsOrigen.setPtosDisponibles(0);
				ptsOrigen.setPtsRedimidos(0);
				ptsOrigen.setPtsTransferidos(0);
				ptsOrigen.setPtsVencidos(0);
				ptsOrigen.setPtsPorVencer(0);
				ptsOrigen.setFecVencer(null);
				ptsOrigen.setPtsPorVencer1(0);
				ptsOrigen.setFecVencer1(null);
				ptsOrigen.setPtsPorVencer2(0);
				ptsOrigen.setFecVencer2(null);
				ptsOrigen.setFecFactura(null);
				ptsOrigen.setPtsRenta(0);
				ptsOrigen.setPtsExcedentes(0);
				ptsOrigen.setPtosDisponiblesTmp(0);
				ptsOrigen.setPtsAntiguedad(0);
				ptsOrigen.setPtsPorAntiguedad(0);
			} catch (SQLException se) {
				se.printStackTrace();
				this.error.info(
						(Object) "Exception.registraHistoricoTransferncia:",
						(Throwable) se);
				throw new CAException(-1,
						"TransferenciaCaregDAO.registraHistoricoTransferncia["
								+ se.toString() + "]", (Exception) se);
			} catch (Exception e) {
				e.printStackTrace();
				this.error.info(
						(Object) "Exception.registraHistoricoTransferncia:",
						(Throwable) e);
				throw new CAException(-1,
						"TransferenciaCaregDAO.registraHistoricoTransferncia["
								+ e.toString() + "]", e);
			}
		} finally {
			if (ps != null) {
				try {
					ps.close();
					ps = null;
				} catch (Exception var8_11) {
				}
			}
		}
		if (rows > 0) {
			return true;
		}
		return false;
	}

	private void asignaPuntosPorAntiguedad(Connection _cnx, String _ctaDest,
			String _secuenDest, String _telefonoDest, int regionDest,
			String fechaAltaM2K) throws CAException {
		MensajeTO msgTO = null;
		PuntosDAO ptsDao = null;
		Calendar today = Calendar.getInstance();
		Calendar fechaAntig = Calendar.getInstance();
		java.util.Date fechaAlta = null;
		String ref = "";
		String comnt = "";
		long fechaOper = System.currentTimeMillis();
		int aniosAntig = 0;
		int ptosAntig = 0;
		try {
			ptsDao = new PuntosDAO();
			fechaAlta = new java.util.Date(Constantes.DATEFORMATyyyy_MM_dd
					.parse(fechaAltaM2K).getTime());
			fechaAntig.setTime(fechaAlta);
			aniosAntig = today.get(1) - fechaAntig.get(1);
			ptosAntig = Utils.obtienePuntosPorAntiguedad((int) aniosAntig);
			if (!this.actualizaPtsAntiguedad(_cnx, ptosAntig, _ctaDest,
					_secuenDest)) {
				throw new CAException(-1,
						"NO FUE POSIBLE ASIGNAR LOS PUNTOS POR ANTIG\u00dcEDAD<BR>A LA CUENTA ["
								+ _ctaDest + "]");
			}
			ref = "ASIGNA puntos por antiguedad VIBPT01 por cambio de region.";
			msgTO = ptsDao.insertaDetalle(_cnx, fechaOper, ref, 24, ptosAntig,
					"", _ctaDest, Integer.parseInt(_secuenDest), _telefonoDest,
					"VIBPT01");
			if (msgTO.getIdMensaje() != 0) {
				throw new CAException(-1,
						"NO FUE POSIBLE GUARDAR EL DETALLE DE LA CUENTA ["
								+ _ctaDest + "]");
			}
			comnt = "CIR - ASIGNA " + ptosAntig
					+ " PTOS. POR ANTIG. A PET. DE VIBPT01 : CAMBIO DE REGION.";
			msgTO = ptsDao.insertaComentarioTMP(_cnx, regionDest, _ctaDest,
					"VIBPT01", fechaOper, comnt);
			if (msgTO.getIdMensaje() != 0) {
				throw new CAException(-1,
						"NO FUE POSIBLE GUARDAR EL COMENTARIO PARA LA CUENTA ["
								+ _ctaDest + "]");
			}
		} catch (Exception e) {
			throw new CAException(-1,
					"TransferenciaCaregDAO.asignaPuntosPorAntiguedad["
							+ e.toString() + "]", e);
		}
	}

	private boolean actualizaPtsAntiguedad(Connection _cnx, int _ptsAntig,
			String _ctaDest, String _secuenciaDest) throws CAException {
		int rows;
		PreparedStatement ps = null;
		rows = 0;
		try {
			try {
				ps = _cnx.prepareStatement("UPDATE " + this.schema_database
						+ "PTO_TBLTOTALES "
						+ "\tSET PUNTOSEXCEDENTES = PUNTOSEXCEDENTES + ? "
						+ " \t\t,PUNTOSANTIGUEDAD = ? " + " WHERE CUENTA = ? "
						+ "\tAND SECUENCIA = ?");
				ps.setInt(1, _ptsAntig);
				ps.setInt(2, _ptsAntig);
				ps.setString(3, _ctaDest);
				ps.setString(4, _secuenciaDest);
				rows = ps.executeUpdate();
			} catch (SQLException se) {
				se.printStackTrace();
				this.error.info((Object) "Exception.actualizaPtsAntiguedad:",
						(Throwable) se);
				throw new CAException(-1,
						"TransferenciaCaregDAO.actualizaPtsAntiguedad["
								+ se.toString() + "]", (Exception) se);
			} catch (Exception e) {
				e.printStackTrace();
				this.error.info((Object) "Exception.actualizaPtsAntiguedad:",
						(Throwable) e);
				throw new CAException(-1,
						"TransferenciaCaregDAO.actualizaPtsAntiguedad["
								+ e.toString() + "]", e);
			}
		} finally {
			if (ps != null) {
				try {
					ps.close();
					ps = null;
				} catch (Exception var9_11) {
				}
			}
		}
		if (rows > 0) {
			return true;
		}
		return false;
	}

	private boolean existeTotales(Connection _cnx, String _cuentaDest,
			String _secDest) throws CAException {
		boolean existe;
		PreparedStatement ps = null;
		ResultSet rs = null;
		existe = false;
		try {
			try {
				ps = _cnx.prepareStatement(" SELECT CUENTA FROM "
						+ this.schema_database + "PTO_TBLTOTALES "
						+ "  WHERE CUENTA = ?" + "  AND SECUENCIA = ?");
				ps.setString(1, _cuentaDest);
				ps.setString(2, _secDest);
				rs = ps.executeQuery();
				if (rs.next()) {
					existe = true;
				}
			} catch (SQLException se) {
				se.printStackTrace();
				this.error.info((Object) "Exception.existeTotales:",
						(Throwable) se);
				throw new CAException(-1,
						"TransferenciaCaregDAO.existeTotales[" + se.toString()
								+ "]", (Exception) se);
			} catch (Exception e) {
				e.printStackTrace();
				this.error.info((Object) "Exception.existeTotales:",
						(Throwable) e);
				throw new CAException(-1,
						"TransferenciaCaregDAO.existeTotales[" + e.toString()
								+ "]", e);
			}
		} finally {
			if (rs != null) {
				try {
					rs.close();
					rs = null;
				} catch (Exception var9_12) {
				}
			}
			if (ps != null) {
				try {
					ps.close();
					ps = null;
				} catch (Exception var9_13) {
				}
			}
		}
		return existe;
	}

	public TelefonoTO cuentaDestinoCancelacion(String cuentaOrigen,
			int secuenciaOrigen) throws CAException {
		TelefonoTO telefonoDestino;
		Connection cnx = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		telefonoDestino = null;
		PuntosTO puntosDestino = null;
		try {
			try {
				cnx = ServiceLocator.getInstance().getConnection(
						ServiceLocator.jdbcCirculoAzul);
				ps = cnx.prepareStatement(" SELECT H.CUENTA_DEST, H.SECUENCIA_DEST, H.LINEA_DEST, H.FECHAOPERACION,  H.PUNTOSRENTA, H.PUNTOSACAD, H.PUNTOSACAD1, H.PUNTOSACAD2, H.PUNTOSANTIGUEDAD,  H.PUNTOSPROMOCION, H.PUNTOSTRANSF, H.PUNTOSCADUC, H.PUNTOSREDIM, H.PUNTOSEXCEDENTES,  (T.PUNTOSRENTA + T.PUNTOSACAD + T.PUNTOSACAD1 + T.PUNTOSACAD2 + T.PUNTOSANTIGUEDAD +   T.PUNTOSPROMOCION + T.PUNTOSTRANSF + T.PUNTOSCADUC + T.PUNTOSEXCEDENTES) AS TOTALES,  T.FECHAFAC, T.FECHACAD, T.FECHACAD1, T.FECHACAD2,  L.IDREGION, L.FECHAANT  FROM "
						+ this.schema_database
						+ "PTO_TBLHISTORICOTRANSF H, "
						+ this.schema_database
						+ "PTO_TBLTOTALES T, "
						+ this.schema_database
						+ "PTO_TBLLINEAS L "
						+ " WHERE H.CUENTA = ? "
						+ " AND H.SECUENCIA = ? "
						+ " AND H.CUENTA_DEST = T.CUENTA "
						+ " AND H.SECUENCIA_DEST = T.SECUENCIA "
						+ " AND H.CUENTA_DEST = L.CUENTA "
						+ " AND H.SECUENCIA_DEST = L.SECUENCIA "
						+ " AND H.LINEA_DEST = L.LINEA ");
				ps.setString(1, cuentaOrigen);
				ps.setInt(2, secuenciaOrigen);
				rs = ps.executeQuery();
				if (rs.next()) {
					telefonoDestino = new TelefonoTO();
					puntosDestino = new PuntosTO();
					telefonoDestino.setCuenta(rs.getString("CUENTA_DEST"));
					telefonoDestino
							.setSecuencia(rs.getString("SECUENCIA_DEST"));
					telefonoDestino.setTelefono(rs.getString("LINEA_DEST"));
					telefonoDestino.setRegion(rs.getInt("IDREGION"));
					telefonoDestino.setFechaAlta(rs.getDate("FECHAANT"));
					puntosDestino.setPtsRenta(rs.getInt("PUNTOSRENTA"));
					puntosDestino.setPtsPorVencer(rs.getInt("PUNTOSACAD"));
					puntosDestino.setPtsPorVencer1(rs.getInt("PUNTOSACAD1"));
					puntosDestino.setPtsPorVencer2(rs.getInt("PUNTOSACAD2"));
					puntosDestino.setPtsAntiguedad(rs
							.getInt("PUNTOSANTIGUEDAD"));
					puntosDestino.setPtsPromocion(rs.getInt("PUNTOSPROMOCION"));
					puntosDestino.setPtsTransferidos(rs.getInt("PUNTOSTRANSF"));
					puntosDestino.setPtsVencidos(rs.getInt("PUNTOSCADUC"));
					puntosDestino.setPtsRedimidos(rs.getInt("PUNTOSREDIM"));
					puntosDestino.setPtsExcedentes(rs
							.getInt("PUNTOSEXCEDENTES"));
					puntosDestino.setFecFactura(rs.getDate("FECHAFAC"));
					puntosDestino.setFecVencer(rs.getDate("FECHACAD"));
					puntosDestino.setFecVencer1(rs.getDate("FECHACAD1"));
					puntosDestino.setFecVencer2(rs.getDate("FECHACAD2"));
					puntosDestino.setPtosDisponiblesTmp(rs.getInt("TOTALES"));
					telefonoDestino.setPuntosTO(puntosDestino);
				}
			} catch (SQLException se) {
				se.printStackTrace();
				this.error.info((Object) "Exception.existeTotales:",
						(Throwable) se);
				throw new CAException(-1,
						"TransferenciaCaregDAO.transferenciaCuentaDestino["
								+ se.toString() + "]", (Exception) se);
			} catch (Exception e) {
				if (cnx != null) {
					try {
						cnx.rollback();
					} catch (Exception var9_10) {
						// empty catch block
					}
				}
				this.error.info(
						(Object) "Exception.consultaCuentasCancelacion:",
						(Throwable) e);
				throw new CAException(-1,
						"TransferenciaCaregDAO.consultaCuentasCancelacion["
								+ e.toString() + "]", e);
			}
		} finally {
			if (rs != null) {
				try {
					rs.close();
					rs = null;
				} catch (Exception var11_15) {
				}
			}
			if (ps != null) {
				try {
					ps.close();
					ps = null;
				} catch (Exception var11_16) {
				}
			}
			if (cnx != null) {
				try {
					cnx.setAutoCommit(true);
					cnx.close();
					cnx = null;
				} catch (Exception var11_17) {
				}
			}
		}
		return telefonoDestino;
	}

	private boolean cancelaTotales(Connection _cnx, TransferenciaTO _transfTO,
			boolean esLineaDestino) throws CAException {
		int rows;
		PreparedStatement ps = null;
		StringBuffer qry = new StringBuffer();
		PuntosTO ptsOrigen = _transfTO.getPuntosOrigenTO();
		TelefonoTO phoneDestinoTO = _transfTO.getTelefonoTO();
		String _ctaDest = _transfTO.getCuentaOrigen();
		String _secDest = String.valueOf(_transfTO.getSecuenciaOrigen());
		char signo = '+';
		if (esLineaDestino) {
			signo = '-';
			_ctaDest = _transfTO.getCuentaDestino();
			_secDest = String.valueOf(_transfTO.getSecuenciaDestino());
		}
		rows = 0;
		try {
			try {
				qry.append("UPDATE " + this.schema_database + "PTO_TBLTOTALES ");
				qry.append("   SET ");
				qry.append(" PUNTOSRENTA       = (PUNTOSRENTA " + signo
						+ " ?) ");
				qry.append(" ,PUNTOSACAD       = (PUNTOSACAD " + signo + " ?)");
				qry.append(" ,PUNTOSACAD1      = (PUNTOSACAD1 " + signo + " ?)");
				qry.append(" ,PUNTOSACAD2      = (PUNTOSACAD2 " + signo + " ?)");
				qry.append(" ,PUNTOSANTIGUEDAD = (PUNTOSANTIGUEDAD " + signo
						+ " ?)");
				qry.append(" ,PUNTOSPROMOCION  = (PUNTOSPROMOCION " + signo
						+ " ?)");
				qry.append(" ,PUNTOSTRANSF     = (PUNTOSTRANSF " + signo
						+ " ?)");
				qry.append(" ,PUNTOSCADUC      = (PUNTOSCADUC " + signo + " ?)");
				qry.append(" ,PUNTOSREDIM      = (PUNTOSREDIM " + signo + " ?)");
				qry.append(" ,BonoEquipo      = (BonoEquipo " + signo + " ?)");
				qry.append(" ,PUNTOSEXCEDENTES  = (PUNTOSEXCEDENTES " + signo
						+ " ?)");
				qry.append(", FECHACAD = ?");
				qry.append(", FECHACAD1 = ?");
				qry.append(", FECHACAD2 = ?");
				qry.append(", FECHACADU  = ?");
				qry.append(", PUNTOSPROM = ?");
				qry.append(", SALDOANT   = ?");
				qry.append(", FECHAFAC   = ?");
				qry.append(" WHERE  CUENTA = ?");
				qry.append(" AND SECUENCIA = ?");
				ps = _cnx.prepareStatement(qry.toString());
				ps.setInt(1, ptsOrigen.getPtsRenta());
				ps.setInt(2, ptsOrigen.getPtsPorVencer());
				ps.setInt(3, ptsOrigen.getPtsPorVencer1());
				ps.setInt(4, ptsOrigen.getPtsPorVencer2());
				ps.setInt(5, ptsOrigen.getPtsAntiguedad());
				ps.setInt(6, ptsOrigen.getPtsPromocion());
				ps.setInt(7, ptsOrigen.getPtsTransferidos());
				ps.setInt(8, ptsOrigen.getPtsVencidos());
				ps.setInt(9, ptsOrigen.getPtsRedimidos());
				ps.setInt(10, ptsOrigen.getBonoEquipo());
				ps.setInt(11, ptsOrigen.getPtsExcedentes());
				if (ptsOrigen.getFecVencer() != null) {
					ps.setDate(12, new Date(ptsOrigen.getFecVencer().getTime()));
				} else {
					ps.setNull(12, 91);
				}
				if (ptsOrigen.getFecVencer1() != null) {
					ps.setDate(13,
							new Date(ptsOrigen.getFecVencer1().getTime()));
				} else {
					ps.setNull(13, 91);
				}
				if (ptsOrigen.getFecVencer2() != null) {
					ps.setDate(14,
							new Date(ptsOrigen.getFecVencer2().getTime()));
				} else {
					ps.setNull(14, 91);
				}
				if (ptsOrigen.getFecVencidos() != null) {
					ps.setDate(15, new Date(ptsOrigen.getFecVencidos()
							.getTime()));
				} else {
					ps.setNull(15, 91);
				}
				ps.setString(16, ptsOrigen.getPtsBonoProm());
				ps.setInt(17, ptsOrigen.getPtsSaldoAnt());
				if (ptsOrigen.getFecFactura() != null) {
					ps.setDate(18,
							new Date(ptsOrigen.getFecFactura().getTime()));
				} else {
					ps.setNull(18, 91);
				}
				ps.setString(19, _ctaDest);
				ps.setString(20, _secDest);
				phoneDestinoTO.getPuntosTO().setPtsRedimidos(
						ptsOrigen.getPtsRedimidos());
				phoneDestinoTO.getPuntosTO().setPtsTransferidos(
						ptsOrigen.getPtsTransferidos());
				phoneDestinoTO.getPuntosTO().setPtsPorVencer(
						ptsOrigen.getPtsPorVencer());
				phoneDestinoTO.getPuntosTO().setFecVencer(
						ptsOrigen.getFecVencer());
				phoneDestinoTO.getPuntosTO().setPtsPorVencer1(
						ptsOrigen.getPtsPorVencer1());
				phoneDestinoTO.getPuntosTO().setFecVencer1(
						ptsOrigen.getFecVencer1());
				phoneDestinoTO.getPuntosTO().setPtsPorVencer2(
						ptsOrigen.getPtsPorVencer2());
				phoneDestinoTO.getPuntosTO().setFecVencer2(
						ptsOrigen.getFecVencer2());
				phoneDestinoTO.getPuntosTO().setPtsRenta(
						ptsOrigen.getPtsRenta());
				phoneDestinoTO.getPuntosTO().setPtsExcedentes(
						ptsOrigen.getPtsExcedentes());
				phoneDestinoTO.getPuntosTO().setPtsVencidos(
						ptsOrigen.getPtsVencidos());
				phoneDestinoTO.getPuntosTO().setFecVencidos(
						ptsOrigen.getFecVencidos());
				phoneDestinoTO.getPuntosTO().setPtsAntiguedad(
						ptsOrigen.getPtsAntiguedad());
				phoneDestinoTO.getPuntosTO().setPtsPromocion(
						ptsOrigen.getPtsPromocion());
				phoneDestinoTO.getPuntosTO().setPtosStatus(
						ptsOrigen.getPtosStatus());
				phoneDestinoTO.getPuntosTO().setPtosDisponibles(
						ptsOrigen.getPtosDisponibles());
				phoneDestinoTO.getPuntosTO().setBonoEquipo(
						ptsOrigen.getBonoEquipo());
				phoneDestinoTO.getPuntosTO().setFecFactura(
						ptsOrigen.getFecFactura());
				rows = ps.executeUpdate();
			} catch (SQLException se) {
				se.printStackTrace();
				this.error.info((Object) "Exception.actualizaTotalesDestino:",
						(Throwable) se);
				throw new CAException(-1,
						"TransferenciaCaregDAO.actualizaTotalesDestino["
								+ se.toString() + "]", (Exception) se);
			} catch (Exception e) {
				e.printStackTrace();
				this.error.info((Object) "Exception.actualizaTotalesDestino:",
						(Throwable) e);
				throw new CAException(-1,
						"TransferenciaCaregDAO.actualizaTotalesDestino["
								+ e.toString() + "]", e);
			}
		} finally {
			if (ps != null) {
				try {
					ps.close();
					ps = null;
				} catch (Exception var14_16) {
				}
			}
		}
		if (rows > 0) {
			return true;
		}
		return false;
	}

	private boolean borraHistoricoTransferencia(Connection cnx,
			TransferenciaTO trasnfTO) throws CAException {
		int rows;
		PreparedStatement ps = null;
		rows = 0;
		try {
			try {
				ps = cnx.prepareStatement("DELETE " + this.schema_database
						+ "PTO_TBLHISTORICOTRANSF " + " WHERE CUENTA = ? "
						+ " AND LINEA = ? " + " AND SECUENCIA = ?"
						+ " AND CUENTA_DEST = ? " + " AND LINEA_DEST = ? "
						+ " AND SECUENCIA_DEST = ?");
				ps.setString(1, trasnfTO.getCuentaOrigen());
				ps.setString(2, trasnfTO.getTelefonoOrigen());
				ps.setInt(3, trasnfTO.getSecuenciaOrigen());
				ps.setString(4, trasnfTO.getCuentaDestino());
				ps.setString(5, trasnfTO.getTelefonoDestino());
				ps.setInt(6, trasnfTO.getSecuenciaDestino());
				rows = ps.executeUpdate();
			} catch (SQLException se) {
				se.printStackTrace();
				this.error.info((Object) "Exception.actualizaPtsAntiguedad:",
						(Throwable) se);
				throw new CAException(-1,
						"TransferenciaCaregDAO.borraHistoricoTransferencia["
								+ se.toString() + "]", (Exception) se);
			} catch (Exception e) {
				e.printStackTrace();
				this.error.info((Object) "Exception.actualizaPtsAntiguedad:",
						(Throwable) e);
				throw new CAException(-1,
						"TransferenciaCaregDAO.borraHistoricoTransferencia["
								+ e.toString() + "]", e);
			}
		} finally {
			if (ps != null) {
				try {
					ps.close();
					ps = null;
				} catch (Exception var7_9) {
				}
			}
		}
		if (rows > 0) {
			return true;
		}
		return false;
	}
}
