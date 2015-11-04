/*
 * Decompiled with CFR 0_102.
 * 
 * Could not load the following classes:
 *  com.claro.catalogo.Catalogo
 *  com.claro.exception.CAException
 *  com.claro.transfer.AlianzasTO
 *  com.claro.transfer.FactorTO
 *  com.claro.transfer.FolioLiberacionTO
 *  com.claro.transfer.MensajeTO
 *  com.claro.transfer.ParametrosTO
 *  com.claro.transfer.ProductosTO
 *  com.claro.transfer.PuntosRedimidosTO
 *  com.claro.transfer.PuntosTO
 *  com.claro.transfer.TelefonoSimpleTO
 *  com.claro.transfer.TelefonoTO
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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.ListIterator;

import org.apache.log4j.Logger;

import com.claro.catalogo.Catalogo;
import com.claro.exception.CAException;
import com.claro.redencion.sms.NotificaSMS;
import com.claro.transfer.AlianzasTO;
import com.claro.transfer.FactorTO;
import com.claro.transfer.FolioLiberacionTO;
import com.claro.transfer.MensajeTO;
import com.claro.transfer.ParametrosTO;
import com.claro.transfer.PuntosRedimidosTO;
import com.claro.transfer.PuntosTO;
import com.claro.transfer.TelefonoSimpleTO;
import com.claro.transfer.TelefonoTO;
import com.claro.util.Constantes;
import com.claro.util.ServiceLocator;
import com.claro.util.Utils;

public class AlianzasDAO {
	protected final Logger logger = Logger
			.getLogger((String) "loggerCirculoAzul");
	protected final Logger error = Logger
			.getLogger((String) "loggerCirculoAzulError");
	private String schema_database;

	public AlianzasDAO() {
		try {
			this.schema_database = ServiceLocator.getInstance().getVariable(
					ServiceLocator.schema_database);
		} catch (Exception e) {
			this.error.error((Object) "AlianzasDAO", (Throwable) e);
		}
	}

	public ArrayList<AlianzasTO> consultaCanjesAlianzas(String cuenta,
			String secuencia) throws CAException {
		ArrayList<AlianzasTO> canjesAlianzas;
		canjesAlianzas = new ArrayList<AlianzasTO>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		StringBuffer query = new StringBuffer();
		query.append("SELECT A.CUENTA, B.LINEA, A.FECHAOPER, A.CTAALIANZA,");
		query.append(" A.PTRANSF, A.MILLAS,A.USUARIO, A.COMENTARIOS, A.IDCUENTA, A.VALCUPONORIG");
		query.append(" FROM  ").append(this.schema_database)
				.append("PTO_TBLALIANZAS A,  ").append(this.schema_database)
				.append("PTO_TBLLINEAS B");
		query.append(" WHERE B.CUENTA = ? ").append(" AND A.SECUENCIA = ? ");
		query.append(" AND A.CUENTA = B.CUENTA AND A.SECUENCIA = B.SECUENCIA ");
		query.append(" AND STATUSTRANS IN (0,4,5) AND A.ESTATUS = 'A'");
		query.append(" ORDER BY A.FECHAOPER DESC");
		try {
			try {
				connection = ServiceLocator.getInstance().getConnection(
						ServiceLocator.jdbcCirculoAzul);
				preparedStatement = connection.prepareStatement(query
						.toString());
				preparedStatement.setString(1, cuenta);
				preparedStatement.setInt(2, Integer.parseInt(secuencia));
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					AlianzasTO alianzas = new AlianzasTO();
					TelefonoSimpleTO telefonoSimpleTO = new TelefonoSimpleTO();
					PuntosRedimidosTO puntosRedimidosTO = new PuntosRedimidosTO();
					telefonoSimpleTO.setCuenta(resultSet.getString("CUENTA"));
					telefonoSimpleTO.setLinea(resultSet.getString("LINEA"));
					alianzas.setFechaOperacion(resultSet.getDate("FECHAOPER"));
					alianzas.setCuentaAlianza(resultSet.getString("CTAALIANZA"));
					puntosRedimidosTO.setPtsCanjeados(resultSet
							.getInt("PTRANSF"));
					alianzas.setMillas(resultSet.getInt("MILLAS"));
					alianzas.setUsuario(resultSet.getString("USUARIO"));
					alianzas.setComentario(resultSet.getString("COMENTARIOS"));
					alianzas.setIdCuentaAlianza(resultSet.getInt("IDCUENTA"));
					alianzas.setValorCuponOrig(resultSet.getInt("VALCUPONORIG"));
					alianzas.setTelefonoSimpleTO(telefonoSimpleTO);
					alianzas.setPuntosRedimidosTO(puntosRedimidosTO);
					canjesAlianzas.add(alianzas);
				}
			} catch (SQLException e) {
				throw new CAException(-1,
						"SQLException.consultaCanjesAlianzas", (Exception) e);
			} catch (NumberFormatException e) {
				throw new CAException(-1,
						"NumberFormatException.consultaCanjesAlianzas",
						(Exception) e);
			} catch (Exception e) {
				throw new CAException(-1, "AlianzasDAO.consultaCanjesAlianzas",
						e);
			}
		} finally {
			if (resultSet != null) {
				try {
					resultSet.close();
					resultSet = null;
				} catch (Exception var12_18) {
				}
			}
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
					preparedStatement = null;
				} catch (Exception var12_19) {
				}
			}
			if (connection != null) {
				try {
					connection.close();
					connection = null;
				} catch (Exception var12_20) {
				}
			}
		}
		return canjesAlianzas;
	}

	public ArrayList<AlianzasTO> consultaConfirmaCanje(TelefonoTO telefonoTO,
			Connection connection, String usuario) throws CAException {
		try {
			ArrayList<AlianzasTO> alianzas = this.obtieneAliazasPendientes(
					telefonoTO.getCuenta(), telefonoTO.getSecuencia(), true,
					connection);
			if (alianzas != null && alianzas.size() > 0) {
				this.expiraCertificados(telefonoTO.getCuenta(),
						telefonoTO.getSecuencia(), telefonoTO.getTelefono(),
						alianzas, telefonoTO.getRegion(), connection, usuario);
			}
			return this.obtieneAliazasPendientes(telefonoTO.getCuenta(),
					telefonoTO.getSecuencia(), false, connection);
		} catch (Exception e) {
			throw new CAException(-1, "AlianzasDAO.consultaConfirmaCanje["
					+ e.toString() + "]", e);
		}
	}

	private ArrayList<AlianzasTO> obtieneAliazasPendientes(String cuenta,
			String secuencia, boolean bVigencia, Connection connection)
			throws CAException {
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String sQuery = " SELECT a.Folio, a.VigenciaMax ,a.FechaOper, a.PTransf, a.ValCuponOrig FROM "
				+ this.schema_database
				+ "PTO_TBLALIANZAS a, "
				+ this.schema_database
				+ "PTO_TBLLINEAS b "
				+ " WHERE a.Cuenta =? and a.Secuencia = ?"
				+ "\tand a.cuenta = b.cuenta and a.secuencia = b.secuencia and a.Idcuenta = 2 AND Statustrans = 4 "
				+ " \tand a.Estatus = 'A'";
		if (bVigencia) {
			sQuery = String.valueOf(sQuery) + " and ? > VigenciaMax ";
		}
		sQuery = String.valueOf(sQuery) + " ORDER BY a.fechaoper DESC";
		try {
			long inicioConsulta = System.currentTimeMillis();
			this.logger
					.info((Object) ("obtieneAliazasPendientes|InicioConsulta|"
							+ Constantes.DATEFORMTALog
									.format(new java.util.Date()) + "|" + inicioConsulta));
			statement = connection.prepareStatement(sQuery);
			statement.setString(1, cuenta);
			statement.setString(2, secuencia);
			if (bVigencia) {
				statement.setDate(3, new Date(System.currentTimeMillis()));
			}
			resultSet = statement.executeQuery();
			ArrayList<AlianzasTO> alianzas = new ArrayList<AlianzasTO>();
			while (resultSet.next()) {
				AlianzasTO alianzasTO = new AlianzasTO();
				alianzasTO.setFolio(resultSet.getString(1));
				alianzasTO.setVigenciaMax(resultSet.getDate(2));
				alianzasTO.setFechaOperacion(resultSet.getDate(3));
				alianzasTO.setPtsTransferidos(resultSet.getInt(4));
				alianzasTO.setValorCuponOrig(resultSet.getInt(5));
				alianzas.add(alianzasTO);
			}
			this.logger.info((Object) ("obtieneAliazasPendientes|FinConsulta|"
					+ Constantes.DATEFORMTALog.format(new java.util.Date())
					+ "|" + (System.currentTimeMillis() - inicioConsulta)));
			ArrayList<AlianzasTO> arrayList = alianzas;
			return arrayList;
		} catch (SQLException e) {
			this.error.info((Object) "SQLException.obtieneAliazasPendientes:",
					(Throwable) e);
			throw new CAException(-1, "SQLException.obtieneAliazasPendientes",
					(Exception) e);
		} catch (Exception e) {
			this.error.info((Object) "Exception.obtieneAliazasPendientes:",
					(Throwable) e);
			throw new CAException(-1, "AlianzasDAO.obtieneAliazasPendientes", e);
		} finally {
			if (resultSet != null) {
				try {
					resultSet.close();
					resultSet = null;
				} catch (Exception var14_16) {
				}
			}
			if (statement != null) {
				try {
					statement.close();
					statement = null;
				} catch (Exception var14_17) {
				}
			}
		}
	}

	private void expiraCertificados(String cuenta, String secuencia,
			String telefono, ArrayList<AlianzasTO> alianzas, int region,
			Connection connection, String usuario) throws CAException {
		PreparedStatement updateAlianza = null;
		PreparedStatement insertDetalle = null;
		PreparedStatement insertComentario = null;
		try {
			try {
				String queryUpdateAlianza = "UPDATE " + this.schema_database
						+ "PTO_TBLALIANZAS SET Statustrans = 2 "
						+ "WHERE Folio = ?";
				String queryInsertDetalle = "INSERT INTO "
						+ this.schema_database
						+ "PTO_TBLMSTRDETALLE (Cuenta, Secuencia, Linea, FechaFac, FechaOperacion, "
						+ "IdMovto, IdUsuario, NumPuntos, NumPuntosExc, TotAjustes, IdBonoProm, Referencia ) "
						+ "VALUES (?,?,?,?,?, 23, ?, 0, 0, 0, null,?)";
				String queryInsertComentario = "INSERT INTO "
						+ this.schema_database
						+ "PTO_TMP_COMNT(Region, Fecha, Cuenta, Usuario, Comentarios) "
						+ "VALUES(?,?,?,?,?)";
				long inicioConsulta = System.currentTimeMillis();
				this.logger.info((Object) ("expiraCertificados|InicioConsulta|"
						+ Constantes.DATEFORMTALog.format(new java.util.Date())
						+ "|" + inicioConsulta));
				connection.setAutoCommit(false);
				updateAlianza = connection.prepareStatement(queryUpdateAlianza);
				insertDetalle = connection.prepareStatement(queryInsertDetalle);
				insertComentario = connection
						.prepareStatement(queryInsertComentario);
				ListIterator<AlianzasTO> iterator = alianzas.listIterator();
				while (iterator.hasNext()) {
					Timestamp fechaTransaccion = new Timestamp(
							System.currentTimeMillis());
					AlianzasTO alianza = iterator.next();
					String comentarioInsertaDetalle = "CANCELA CUPON AMEX FOLIO "
							+ alianza.getFolio()
							+ " POR VIGENCIA. FECHA DE CADUC. "
							+ alianza.getVigenciaMax();
					String comentarioInsertComentario = "CANCELACION DE CERTIFICADO AMEX CON FOLIO "
							+ alianza.getFolio()
							+ " POR VIGENCIA. FECHA DE CADUCIDAD: "
							+ alianza.getVigenciaMax();
					updateAlianza.clearParameters();
					updateAlianza.setString(1, alianza.getFolio());
					updateAlianza.executeUpdate();
					insertDetalle.clearParameters();
					insertDetalle.setString(1, cuenta);
					insertDetalle.setInt(2, Integer.parseInt(secuencia));
					insertDetalle.setString(3, telefono);
					insertDetalle.setDate(4,
							new Date(fechaTransaccion.getTime()));
					insertDetalle.setDate(5,
							new Date(fechaTransaccion.getTime()));
					insertDetalle.setString(6, usuario);
					insertDetalle.setString(7, comentarioInsertaDetalle);
					insertDetalle.executeUpdate();
					insertComentario.clearParameters();
					insertComentario.setInt(1, region);
					insertComentario.setTimestamp(2, fechaTransaccion);
					insertComentario.setString(3, cuenta);
					insertComentario.setString(4, usuario);
					insertComentario.setString(5, comentarioInsertComentario);
					insertComentario.executeUpdate();
				}
				connection.commit();
				this.logger.info((Object) ("expiraCertificados|FinConsulta|"
						+ Constantes.DATEFORMTALog.format(new java.util.Date())
						+ "|" + (System.currentTimeMillis() - inicioConsulta)));
			} catch (SQLException e) {
				this.error.info((Object) "SQLException.expiraCertificados:",
						(Throwable) e);
				if (connection != null) {
					try {
						connection.rollback();
					} catch (Exception queryInsertDetalle) {
						// empty catch block
					}
				}
				throw new CAException(-1, "SQLException.expiraCertificados["
						+ e.toString() + "]", (Exception) e);
			} catch (Exception e) {
				this.error.info((Object) "SQLException.expiraCertificados:",
						(Throwable) e);
				throw new CAException(-1, "AlianzasDAO.expiraCertificados["
						+ e.toString() + "]", e);
			}
		} finally {
			if (updateAlianza != null) {
				try {
					updateAlianza.close();
					updateAlianza = null;
				} catch (Exception var22_28) {
				}
			}
			if (insertDetalle != null) {
				try {
					insertDetalle.close();
					insertDetalle = null;
				} catch (Exception var22_29) {
				}
			}
			if (insertComentario != null) {
				try {
					insertComentario.close();
					insertComentario = null;
				} catch (Exception var22_30) {
				}
			}
			if (connection != null) {
				try {
					connection.setAutoCommit(true);
				} catch (Exception var22_31) {
				}
			}
		}
	}

	public FactorTO consultaFactor(int idAlianza, int nPtsDisp)
			throws CAException {
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Connection connection = null;
		String sQuery = " SELECT idcuenta,Factor,fechaActiva,Status,Millasmin FROM "
				+ this.schema_database
				+ "PTO_TBLFACTORALIANZA "
				+ " WHERE Idcuenta=? AND Status='A' ORDER BY FechaActiva desc";
		try {
			connection = ServiceLocator.getInstance().getConnection(
					ServiceLocator.jdbcCirculoAzul);
			statement = connection.prepareStatement(sQuery);
			statement.setInt(1, idAlianza);
			resultSet = statement.executeQuery();
			if (resultSet.next()) {
				FactorTO factorTO = new FactorTO();
				factorTO.setIdcuenta(resultSet.getInt(1));
				factorTO.setFactor(resultSet.getInt(2));
				factorTO.setFechaActiva((java.util.Date) resultSet.getDate(3));
				factorTO.setEstatus(resultSet.getString(4));
				factorTO.setMillanMin(resultSet.getInt(5));
				FactorTO factorTO2 = factorTO;
				return factorTO2;
			}

		} catch (SQLException e) {
			throw new CAException(-1, "SQLException.consultaFactor["
					+ e.toString() + "]", (Exception) e);
		} catch (Exception e) {
			throw new CAException(-1, "AlianzasDAO.consultaFactor["
					+ e.toString() + "]", e);
		} finally {
			if (statement != null) {
				try {
					statement.close();
					statement = null;
				} catch (Exception var10_11) {
				}
			}
			if (resultSet != null) {
				try {
					resultSet.close();
					resultSet = null;
				} catch (Exception var10_12) {
				}
			}
			if (connection != null) {
				try {
					connection.close();
					connection = null;
				} catch (Exception var10_13) {
				}
			}
		}
		return null;
	}

	public ArrayList<AlianzasTO> consultaCuponesAmex(String sCuenta,
			int iSecuncia) throws CAException {
		ArrayList<AlianzasTO> cuponesAmex;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		StringBuffer query = new StringBuffer();
		query.append(" SELECT FOLIO, SECUENCIA, VALCUPONORIG, PTRANSF, VIGENCIAMAX, STATUSTRANS,FECHAOPER  ");
		query.append(" FROM ").append(this.schema_database)
				.append("PTO_TBLALIANZAS ");
		query.append(" WHERE CUENTA = ? AND SECUENCIA = ?  AND IDCUENTA = 2");
		query.append(" ORDER BY FECHAOPER DESC ");
		cuponesAmex = new ArrayList<AlianzasTO>();
		try {
			try {
				connection = ServiceLocator.getInstance().getConnection(
						ServiceLocator.jdbcCirculoAzul);
				statement = connection.prepareStatement(query.toString());
				statement.setString(1, sCuenta);
				statement.setInt(2, iSecuncia);
				resultSet = statement.executeQuery();
				while (resultSet.next()) {
					AlianzasTO alianzasTO = new AlianzasTO();
					alianzasTO.setFolio(resultSet.getString("FOLIO"));
					alianzasTO.setSecuencia(resultSet.getInt("SECUENCIA"));
					alianzasTO.setValorCuponOrig(resultSet
							.getInt("VALCUPONORIG"));
					alianzasTO.setPtsTransferidos(resultSet.getInt("PTRANSF"));
					alianzasTO.setVigenciaMax(resultSet.getDate("VIGENCIAMAX"));
					alianzasTO.setStatusTrans(resultSet.getInt("STATUSTRANS"));
					alianzasTO
							.setFechaOperacion(resultSet.getDate("FECHAOPER"));
					cuponesAmex.add(alianzasTO);
				}
			} catch (SQLException e) {
				throw new CAException(-1, "SQLException.consultaCuponesAmex ["
						+ e.toString() + "]", (Exception) e);
			} catch (Exception e) {
				throw new CAException(-1, "CatalogoDAO.consultaCuponesAmex["
						+ e.toString() + "]", e);
			}
		} finally {
			if (resultSet != null) {
				try {
					resultSet.close();
					resultSet = null;
				} catch (Exception var10_15) {
				}
			}
			if (statement != null) {
				try {
					statement.close();
					statement = null;
				} catch (Exception var10_16) {
				}
			}
			if (connection != null) {
				try {
					connection.close();
					connection = null;
				} catch (Exception var10_17) {
				}
			}
		}
		return cuponesAmex;
	}

	public MensajeTO actualizaPromoAlianza(String sCuentaAl, int iCveAl,
			String sCuenta, int iSecuencia, String sNombre, String sApePat,
			String sApeMat, String sUsrCve, String sTel) throws CAException {
		MensajeTO mensajeTO;
		mensajeTO = null;
		Connection connection = null;
		try {
			try {
				connection = ServiceLocator.getInstance().getConnection(
						ServiceLocator.jdbcCirculoAzul);
				String fechaTransaccion = Constantes.DATEFORMATyyyy_MM_dd
						.format(new java.util.Date());
				if (sCuentaAl.length() < 12) {
					sCuentaAl = Utils.anexarCeros((int) 12, (String) sCuentaAl);
				}
				if (iCveAl == 2) {
					sCuentaAl = "00" + sTel;
				}
				if ((mensajeTO = this.actualizaAlianza(connection,
						fechaTransaccion, sCuentaAl, iCveAl, sCuenta,
						iSecuencia, sNombre, sApePat, sApeMat, sUsrCve, sTel))
						.getIdMensaje() != 0) {
					connection.rollback();
				} else {
					connection.commit();
				}
			} catch (SQLException e) {
				this.error
						.info((Object) "Alianzas.actualizaPromoAlianza.SQLException:",
								(Throwable) e);
				throw new CAException(-1,
						"Alianzas.actualizaPromoAlianza.SQLException["
								+ e.toString() + "]", (Exception) e);
			} catch (Exception e) {
				this.error.info(
						(Object) "Alianzas.actualizaPromoAlianza.Exception:",
						(Throwable) e);
				throw new CAException(-1,
						"Alianzas.actualizaPromoAlianza.Error[" + e.toString()
								+ "]", e);
			}
		} finally {
			if (connection != null) {
				try {
					connection.close();
					connection = null;
				} catch (Exception var14_17) {
				}
			}
		}
		return mensajeTO;
	}

	public MensajeTO altaPromoAlianza(String sCuentaAl, int iCveAl,
			String sCuenta, int iSecuencia, String sNombre, String sApePat,
			String sApeMat, String sUsrCve, String sTel) throws CAException {
		MensajeTO mensajeTO;
		mensajeTO = new MensajeTO();
		Connection connection = null;
		try {
			try {
				String FechaActual = Constantes.DATEFORMATyyyy_MM_dd
						.format(new java.util.Date());
				if (sCuentaAl.length() < 12) {
					sCuentaAl = Utils.anexarCeros((int) 12, (String) sCuentaAl);
				}
				if (iCveAl == 2) {
					sCuentaAl = "00" + sTel;
				}
				CatalogoDAO catalogoDAO = new CatalogoDAO();
				String estatus = "A";
				ArrayList<AlianzasTO> alianzasArreglo = catalogoDAO
						.consultaAlianzas(iCveAl, sCuenta, iSecuencia, estatus,
								sCuentaAl);
				connection = ServiceLocator.getInstance().getConnection(
						ServiceLocator.jdbcCirculoAzul);
				mensajeTO = alianzasArreglo == null
						|| alianzasArreglo.isEmpty() ? this.altaAlianza(
						connection, FechaActual, sCuentaAl, iCveAl, sCuenta,
						iSecuencia, sNombre, sApePat, sApeMat, sUsrCve, sTel)
						: this.actualizaAlianza(connection, FechaActual,
								sCuentaAl, iCveAl, sCuenta, iSecuencia,
								sNombre, sApePat, sApeMat, sUsrCve, sTel);
			} catch (SQLException e) {
				this.error.info(
						(Object) "Alianzas.altaPromoAlianza.SQLException:",
						(Throwable) e);
				throw new CAException(-1,
						"Alianzas.altaPromoAlianza.SQLException["
								+ e.toString() + "]", (Exception) e);
			} catch (Exception e) {
				this.error.info(
						(Object) "Alianzas.altaPromoAlianza.Exception:",
						(Throwable) e);
				throw new CAException(-1, "Alianzas.altaPromoAlianza.Error["
						+ e.toString() + "]", e);
			}
		} finally {
			if (connection != null) {
				try {
					connection.close();
					connection = null;
				} catch (Exception var17_20) {
				}
			}
		}
		return mensajeTO;
	}

	private MensajeTO actualizaAlianza(Connection connection,
			String fechaTransaccion, String sCuentaAl, int iCveAl,
			String sCuenta, int iSecuencia, String sNombre, String sApePat,
			String sApeMat, String sUsrCve, String sTel) throws CAException {
		MensajeTO mensajeTO;
		Connection lConn = null;
		PreparedStatement statement = null;
		mensajeTO = null;
		try {
			try {
				lConn = connection == null ? ServiceLocator.getInstance()
						.getConnection(ServiceLocator.jdbcCirculoAzul)
						: connection;
				StringBuffer sUpdate = new StringBuffer();
				sUpdate.append(" UPDATE ").append(this.schema_database)
						.append("PTO_TBLDETALLEALIANZA ");
				sUpdate.append(" SET  ");
				sUpdate.append(" NOMBRE = ?, ");
				sUpdate.append(" APATERNO= ?, ");
				sUpdate.append(" AMATERNO = ? ");
				if (fechaTransaccion != null) {
					sUpdate.append(" ,STATUSALIANZA = 'A', ");
					sUpdate.append(" FECHAALTA = ?  ");
				}
				sUpdate.append(" WHERE CUENTA = ?");
				sUpdate.append(" AND SECUENCIA = ?");
				sUpdate.append(" AND IDCUENTA = ?");
				sUpdate.append(" AND CTAALIANZA = ? ");
				statement = lConn.prepareStatement(sUpdate.toString());
				statement.setString(1, sNombre);
				statement.setString(2, sApePat);
				statement.setString(3, sApeMat);
				if (fechaTransaccion != null) {
					statement.setDate(4, Date.valueOf(fechaTransaccion));
					statement.setString(5, sCuenta);
					statement.setInt(6, iSecuencia);
					statement.setInt(7, iCveAl);
					statement.setString(8, sCuentaAl);
				} else {
					statement.setString(4, sCuenta);
					statement.setInt(5, iSecuencia);
					statement.setInt(6, iCveAl);
					statement.setString(7, sCuentaAl);
				}
				mensajeTO = new MensajeTO();
				if (statement.executeUpdate() > 0) {
					mensajeTO.agregaMensaje(0, "Proceso Exitoso");
				} else {
					mensajeTO.agregaMensaje(-1,
							"No se Realizo la actualizacion de la Alianza");
				}
			} catch (SQLException e) {
				this.error
						.info((Object) "Alianzas.actualizaPromoAlianza.SQLException:",
								(Throwable) e);
				throw new CAException(-1,
						"Alianzas.actualizaPromoAlianza.SQLException["
								+ e.toString() + "]", (Exception) e);
			} catch (Exception e) {
				this.error.info(
						(Object) "Alianzas.actualizaPromoAlianza.Exception:",
						(Throwable) e);
				throw new CAException(-1,
						"Alianzas.actualizaPromoAlianza.SQLException["
								+ e.toString() + "]", e);
			}
		} finally {
			if (statement != null) {
				try {
					statement.close();
					statement = null;
				} catch (Exception var17_21) {
				}
			}
			if (connection == null && lConn != null) {
				try {
					lConn.close();
					lConn = null;
				} catch (Exception var17_22) {
				}
			}
		}
		return mensajeTO;
	}

	private MensajeTO altaAlianza(Connection connection,
			String fechaTransaccion, String sCuentaAl, int iCveAl,
			String sCuenta, int iSecuencia, String sNombre, String sApePat,
			String sApeMat, String sUsrCve, String sTel) throws CAException {
		MensajeTO mensajeTO;
		Connection lConn = null;
		PreparedStatement statement = null;
		mensajeTO = new MensajeTO();
		try {
			try {
				lConn = connection == null ? ServiceLocator.getInstance()
						.getConnection(ServiceLocator.jdbcCirculoAzul)
						: connection;
				StringBuffer sInsert = new StringBuffer();
				Object dFechaTransaccion = null;
				sInsert.append(" INSERT INTO ").append(this.schema_database)
						.append("PTO_TBLDETALLEALIANZA ");
				sInsert.append(" (CTAALIANZA,IDCUENTA, CUENTA, SECUENCIA, NOMBRE, APATERNO, AMATERNO, FECHAALTA, USUARIO, STATUSALIANZA) VALUES ");
				sInsert.append(" (?,?,?,?,?,?,?,?,?,?) ");
				statement = lConn.prepareStatement(sInsert.toString());
				statement.setString(1, sCuentaAl);
				statement.setInt(2, iCveAl);
				statement.setString(3, sCuenta);
				statement.setInt(4, iSecuencia);
				statement.setString(5, sNombre);
				statement.setString(6, sApePat);
				statement.setString(7, sApeMat);
				statement.setDate(8, Date.valueOf(fechaTransaccion));
				statement.setString(9, sUsrCve);
				statement.setString(10, "A");
				if (statement.executeUpdate() > 0) {
					mensajeTO.agregaMensaje(0, "Proceso Exitoso");
				} else {
					mensajeTO.agregaMensaje(-1, "No se Inserto la Alianza");
				}
			} catch (SQLException e) {
				this.error.info(
						(Object) "AlianzaDAO.AltaPromoAlianza.SQLException:",
						(Throwable) e);
				throw new CAException(-1,
						"AlianzaDAO.AltaPromoAlianza.SQLException["
								+ e.toString() + "]", (Exception) e);
			} catch (Exception e) {
				this.error.info(
						(Object) "AlianzaDAO.AltaPromoAlianza.Exception:",
						(Throwable) e);
				throw new CAException(-1, "AlianzaDAO.AltaPromoAlianza.Error["
						+ e.toString() + "]", e);
			}
		} finally {
			if (statement != null) {
				try {
					statement.close();
					statement = null;
				} catch (Exception var18_22) {
				}
			}
			if (connection == null && lConn != null) {
				try {
					lConn.close();
					lConn = null;
				} catch (Exception var18_23) {
				}
			}
		}
		return mensajeTO;
	}

	private MensajeTO inhabilitaAlianza(Connection connection,
			String sCuentaAl, int iCveAl) throws CAException {
		MensajeTO mensajeTO;
		Connection lConn = null;
		PreparedStatement statement = null;
		mensajeTO = new MensajeTO();
		try {
			try {
				lConn = connection == null ? ServiceLocator.getInstance()
						.getConnection(ServiceLocator.jdbcCirculoAzul)
						: connection;
				StringBuffer sUpdate = new StringBuffer();
				sUpdate.append(" UPDATE ").append(this.schema_database)
						.append("PTO_TBLDETALLEALIANZA ");
				sUpdate.append(" SET STATUSALIANZA = 'I'  ");
				sUpdate.append(" WHERE CTAALIANZA = ? ");
				sUpdate.append(" AND IDCUENTA = ? ");
				statement = lConn.prepareStatement(sUpdate.toString());
				statement.setString(1, sCuentaAl);
				statement.setInt(2, iCveAl);
				if (statement.executeUpdate() > 0) {
					mensajeTO.agregaMensaje(0, "Proceso Exitoso");
				} else {
					mensajeTO.agregaMensaje(-1,
							"No se Realizo la inhabilitacion de la Alianza");
				}
			} catch (SQLException e) {
				this.error.info(
						(Object) "Alianzas.inhabilitaAlianza.SQLException:",
						(Throwable) e);
				throw new CAException(-1,
						"Alianzas.inhabilitaAlianza.SQLException["
								+ e.toString() + "]", (Exception) e);
			} catch (Exception e) {
				this.error.info(
						(Object) "Alianzas.inhabilitaAlianza.Exception:",
						(Throwable) e);
				throw new CAException(-1,
						"Alianzas.inhabilitaAlianza.SQLException["
								+ e.toString() + "]", e);
			}
		} finally {
			if (statement != null) {
				try {
					statement.close();
					statement = null;
				} catch (Exception var9_13) {
				}
			}
			if (connection == null && lConn != null) {
				try {
					lConn.close();
					lConn = null;
				} catch (Exception var9_14) {
				}
			}
		}
		return mensajeTO;
	}

	public MensajeTO cancelaAlianza(String sCuenta, String sCuentaAl, int iCveAl)
			throws CAException {
		MensajeTO mensajeTO;
		mensajeTO = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		boolean valor = false;
		ArrayList<String> canjesPend = new ArrayList<String>();
		StringBuffer sQuery = new StringBuffer();
		sQuery.append(" SELECT DISTINCT CUENTA, STATUSTRANS ");
		sQuery.append("  FROM  ").append(this.schema_database)
				.append("PTO_TBLALIANZAS ");
		sQuery.append(" WHERE IDCUENTA = 1 AND CTAALIANZA = ? ");
		sQuery.append(" AND ESTATUS = 'A' AND STATUSTRANS IN (4, 5) ");
		try {
			try {
				connection = ServiceLocator.getInstance().getConnection(
						ServiceLocator.jdbcCirculoAzul);
				statement = connection.prepareStatement(sQuery.toString(),
						1004, 1007);
				statement.setString(1, sCuentaAl);
				resultSet = statement.executeQuery();
				if (resultSet.next()) {
					resultSet.beforeFirst();
					while (resultSet.next()) {
						if (resultSet.getString(1).trim()
								.equals(sCuenta.trim())
								&& resultSet.getString(2).equals("4")) {
							valor = true;
							break;
						}
						if (!resultSet.getString(2).equals("4"))
							continue;
						canjesPend.add(resultSet.getString(1));
					}
					mensajeTO = new MensajeTO();
					if (valor) {
						mensajeTO
								.agregaMensaje(
										2,
										"Canje pendiente. Favor de cancelar canje(s) antes de dar de baja la cuenta Alianza.");
					} else if (canjesPend.size() > 0) {
						if (canjesPend.size() == 1) {
							mensajeTO
									.agregaMensaje(
											2,
											"El usuario tiene canje(s) pendiente(s) con la cuenta Telcel "
													+ (String) canjesPend
															.get(0)
													+ ". "
													+ "Favor de cancelar estos canjes antes de dar de baja la cuenta Alianza.");
						} else {
							String sCad = "El usuario tiene canje(s) pendiente(s) con las cuentas de Telcel : ";
							for (int i = 0; i < canjesPend.size() - 1; ++i) {
								sCad = String.valueOf(sCad)
										+ (String) canjesPend.get(i) + ", ";
							}
							sCad = String.valueOf(sCad)
									+ (String) canjesPend
											.get(canjesPend.size() - 1)
									+ ". Favor de cancelar estos canjes"
									+ " antes de dar de baja la cuenta Alianza.";
							mensajeTO.agregaMensaje(2, sCad);
						}
					} else {
						mensajeTO
								.agregaMensaje(
										2,
										"La cuenta Alianza no puede ser cancelada, hasta que Mexicana confirme a Telcel el estatus de uno o varios canjes.");
					}
				} else {
					mensajeTO = this.inhabilitaAlianza(connection, sCuentaAl,
							iCveAl);
					if (mensajeTO.getIdMensaje() != 0) {
						connection.rollback();
					} else {
						connection.commit();
					}
				}
			} catch (SQLException e) {
				this.error.info(
						(Object) "Alianzas.cancelaAlianza.SQLException:",
						(Throwable) e);
				throw new CAException(-1,
						"Alianzas.cancelaAlianza.SQLException[" + e.toString()
								+ "]", (Exception) e);
			} catch (Exception e) {
				this.error.info((Object) "Alianzas.cancelaAlianza.Exception:",
						(Throwable) e);
				throw new CAException(-1, "Alianzas.cancelaAlianza.Error["
						+ e.toString() + "]", e);
			}
		} finally {
			if (connection != null) {
				try {
					connection.close();
					connection = null;
				} catch (Exception var14_17) {
				}
			}
		}
		return mensajeTO;
	}

	public MensajeTO actualizaDatos(String sCuentaAl, int iCveAl,
			String sCuenta, int iSecuencia, String sNombre, String sApePat,
			String sApeMat) throws Exception {
		MensajeTO mensajeTO;
		mensajeTO = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		StringBuffer sQuery = new StringBuffer();
		sQuery.append(" SELECT DISTINCT CUENTA, STATUSTRANS ");
		sQuery.append("  FROM  ").append(this.schema_database)
				.append("PTO_TBLALIANZAS ");
		sQuery.append(" WHERE IDCUENTA = 1 AND CTAALIANZA = ? ");
		sQuery.append(" AND ESTATUS = 'A' AND STATUSTRANS = 5 ");
		try {
			try {
				connection = ServiceLocator.getInstance().getConnection(
						ServiceLocator.jdbcCirculoAzul);
				statement = connection.prepareStatement(sQuery.toString());
				statement.setString(1, sCuentaAl);
				resultSet = statement.executeQuery();
				if (resultSet.next()) {
					mensajeTO
							.agregaMensaje(
									2,
									"No se puede actualizar la informacion de la cuenta alianza debido a que hay por lo menos un canje pendiente de validar en Frecuenta.");
				} else {
					mensajeTO = this.actualizaAlianza(connection, null,
							sCuentaAl, iCveAl, sCuenta, iSecuencia, sNombre,
							sApePat, sApeMat, null, null);
					if (mensajeTO.getIdMensaje() != 0) {
						connection.rollback();
					} else {
						connection.commit();
					}
				}
			} catch (SQLException e) {
				this.error.info(
						(Object) "Alianzas.actualizaDatos.SQLException:",
						(Throwable) e);
				throw new CAException(-1,
						"Alianzas.actualizaDatos.SQLException[" + e.toString()
								+ "]", (Exception) e);
			} catch (Exception e) {
				this.error.info((Object) "Alianzas.actualizaDatos.Exception:",
						(Throwable) e);
				throw new CAException(-1, "Alianzas.actualizaDatos.Error["
						+ e.toString() + "]", e);
			}
		} finally {
			if (connection != null) {
				try {
					connection.close();
					connection = null;
				} catch (Exception var15_17) {
				}
			}
		}
		return mensajeTO;
	}

	private MensajeTO actualizaConfCanjeAlianza(Connection connection,
			int lValViaje, String sFolio, int lNumAcomp, String lIdCanje)
			throws CAException {
		MensajeTO mensajeTO;
		Connection lConn = null;
		PreparedStatement statement = null;
		mensajeTO = new MensajeTO();
		try {
			try {
				lConn = connection == null ? ServiceLocator.getInstance()
						.getConnection(ServiceLocator.jdbcCirculoAzul)
						: connection;
				String queryUpdateAlianza = "UPDATE "
						+ this.schema_database
						+ "PTO_TBLALIANZAS SET Valordls = ?, Statustrans = 0, "
						+ " Millas = ValCuponOrig, Clavefactura = ?, NumeroCanje = ? "
						+ " WHERE Folio = ?";
				statement = lConn.prepareStatement(queryUpdateAlianza);
				statement.setInt(1, lValViaje);
				statement.setString(2, sFolio);
				statement.setInt(3, lNumAcomp);
				statement.setString(4, lIdCanje);
				if (statement.executeUpdate() > 0) {
					mensajeTO.agregaMensaje(0, "Proceso Exitoso");
				} else {
					mensajeTO.agregaMensaje(1,
							"No se actualizo la alianza de confirma canje");
				}
			} catch (SQLException e) {
				this.error.info(
						(Object) "SQLException.actualizaConfCanjeAlianza:",
						(Throwable) e);
				throw new CAException(-1, "[consultaDatos] SQLError: "
						+ e.toString() + "Actualizar Inf", (Exception) e);
			} catch (Exception e) {
				this.error.info(
						(Object) "Exception.actualizaConfCanjeAlianza:",
						(Throwable) e);
				throw new CAException(-1, "[consultaDatos] Error: "
						+ e.toString() + "Actualizar Inf", e);
			}
		} finally {
			if (statement != null) {
				try {
					statement.close();
					statement = null;
				} catch (Exception var11_15) {
				}
			}
			if (connection == null && lConn != null) {
				try {
					lConn.close();
					lConn = null;
				} catch (Exception var11_16) {
				}
			}
		}
		return mensajeTO;
	}

	private MensajeTO insertaDetalleConfCanje(Connection connection,
			long fechaTransaccion, String sCuenta, int isecuencia,
			String sTelefono, String sUsuario, String sFolio)
			throws CAException {
		MensajeTO mensajeTO;
		Connection lConn = null;
		PreparedStatement statement = null;
		mensajeTO = new MensajeTO();
		try {
			try {
				lConn = connection == null ? ServiceLocator.getInstance()
						.getConnection(ServiceLocator.jdbcCirculoAzul)
						: connection;
				String queryInsertDetalle = "INSERT INTO "
						+ this.schema_database
						+ "PTO_TBLMSTRDETALLE (Cuenta, Secuencia, Linea, FechaFac, FechaOperacion, "
						+ "IdMovto, IdUsuario, NumPuntos, NumPuntosExc, TotAjustes, IdBonoProm, Referencia ) "
						+ "VALUES (?,?,?,?,?, 22, ?, 0, 0, 0, null,?)";
				String mensaje = "LIBERA CERTIFICADO AMEX A PET. DE "
						+ sUsuario.toUpperCase() + ". FOLIO DE LIBERACION: "
						+ sFolio.trim();
				statement = lConn.prepareStatement(queryInsertDetalle);
				statement.setString(1, sCuenta);
				statement.setInt(2, isecuencia);
				statement.setString(3, sTelefono);
				statement.setDate(4, new Date(fechaTransaccion));
				statement.setDate(5, new Date(fechaTransaccion));
				statement.setString(6, sUsuario);
				statement.setString(7, mensaje);
				if (statement.executeUpdate() > 0) {
					mensajeTO.agregaMensaje(0, "Proceso Exitoso");
				} else {
					mensajeTO.agregaMensaje(1,
							"No se Inserto el detalle de confirma canje");
				}
			} catch (SQLException e) {
				this.error.info(
						(Object) "SQLException.insertaDetalleConfCanje:",
						(Throwable) e);
				throw new CAException(-1, "[consultaDatos] SQLError: "
						+ e.toString() + "Insertar Inf", (Exception) e);
			} catch (Exception e) {
				this.error.info((Object) "Exception.insertaDetalleConfCanje:",
						(Throwable) e);
				throw new CAException(-1, "[consultaDatos] Error: "
						+ e.toString() + "Insertar Inf", e);
			}
		} finally {
			if (statement != null) {
				try {
					statement.close();
					statement = null;
				} catch (Exception var15_18) {
				}
			}
			if (connection == null && lConn != null) {
				try {
					lConn.close();
					lConn = null;
				} catch (Exception var15_19) {
				}
			}
		}
		return mensajeTO;
	}

	private MensajeTO insertaComntConfCanje(Connection connection,
			long fechaTransaccion, String sCuenta, String lIdCanje,
			int iRegion, String sUsuario, String sFolio) throws CAException {
		MensajeTO mensajeTO;
		Connection lConn = null;
		PreparedStatement statement = null;
		mensajeTO = new MensajeTO();
		try {
			try {
				lConn = connection == null ? ServiceLocator.getInstance()
						.getConnection(ServiceLocator.jdbcCirculoAzul)
						: connection;
				String queryInsertComentario = "INSERT INTO "
						+ this.schema_database
						+ "PTO_TMP_COMNT(Region, Fecha, Cuenta, Usuario, Comentarios) "
						+ "VALUES(?,?,?,?,?)";
				String mensaje = "LIBERA CERTIFICADO AMEX CON FOLIO  "
						+ lIdCanje.trim() + " A PET. DE " + sFolio.trim();
				statement = lConn.prepareStatement(queryInsertComentario);
				statement.setInt(1, iRegion);
				statement.setTimestamp(2, new Timestamp(fechaTransaccion));
				statement.setString(3, sCuenta);
				statement.setString(4, sUsuario);
				statement.setString(5, mensaje);
				if (statement.executeUpdate() > 0) {
					mensajeTO.agregaMensaje(0, "Proceso Exitoso");
				} else {
					mensajeTO.agregaMensaje(1,
							"No se Inserto el comentario de confirma canje");
				}
			} catch (SQLException e) {
				this.error.info((Object) "SQLException.insertaComntConfCanje:",
						(Throwable) e);
				throw new CAException(-1, "[consultaDatos] SQLError: "
						+ e.toString() + "Insertar Inf", (Exception) e);
			} catch (Exception e) {
				this.error.info((Object) "Exception.insertaComntConfCanje:",
						(Throwable) e);
				throw new CAException(-1, "[consultaDatos] Error: "
						+ e.toString() + "Insertar Inf", e);
			}
		} finally {
			if (statement != null) {
				try {
					statement.close();
					statement = null;
				} catch (Exception var15_18) {
				}
			}
			if (connection == null && lConn != null) {
				try {
					lConn.close();
					lConn = null;
				} catch (Exception var15_19) {
				}
			}
		}
		return mensajeTO;
	}

	public MensajeTO confirmaCanje(int lValViaje,
			FolioLiberacionTO folioLiberacion, int lNumAcomp, String lIdCanje,
			String sCuenta, int isecuencia, String sTelefono, String sUsuario,
			int iRegion) throws CAException {
		MensajeTO mensajeTO;
		Connection connection = null;
		mensajeTO = new MensajeTO();
		try {
			try {
				connection = ServiceLocator.getInstance().getConnection(
						ServiceLocator.jdbcCirculoAzul);
				connection.setAutoCommit(false);
				long fechaTransaccion = System.currentTimeMillis();
				folioLiberacion.setFolio(Utils
						.generaFolioAlianza((String) sTelefono));
				mensajeTO = this.actualizaConfCanjeAlianza(connection,
						lValViaje, folioLiberacion.getFolio(), lNumAcomp,
						lIdCanje);
				if (mensajeTO.getIdMensaje() == 0) {
					mensajeTO = this.insertaDetalleConfCanje(connection,
							fechaTransaccion, sCuenta, isecuencia, sTelefono,
							sUsuario, folioLiberacion.getFolio());
				}
				if (mensajeTO.getIdMensaje() == 0) {
					mensajeTO = this.insertaComntConfCanje(connection,
							fechaTransaccion, sCuenta, lIdCanje, iRegion,
							sUsuario, folioLiberacion.getFolio());
				}
				if (mensajeTO.getIdMensaje() != 0) {
					connection.rollback();
				} else {
					connection.commit();
				}
			} catch (SQLException e) {
				this.error.info((Object) "SQLException.confirmaCanje:",
						(Throwable) e);
				if (connection != null) {
					try {
						connection.rollback();
					} catch (Exception var13_15) {
						// empty catch block
					}
				}
				throw new CAException(-1, "[consultaDatos] SQLError: "
						+ e.toString() + "Actualizar Inf", (Exception) e);
			} catch (Exception e) {
				this.error.info((Object) "Exception.confirmaCanje:",
						(Throwable) e);
				if (connection != null) {
					try {
						connection.rollback();
					} catch (Exception var13_16) {
						// empty catch block
					}
				}
				throw new CAException(-1, "[consultaDatos] Error: "
						+ e.toString() + "Actualizar Inf", e);
			}
		} finally {
			if (connection != null) {
				try {
					connection.setAutoCommit(true);
					connection.close();
					connection = null;
				} catch (Exception var15_19) {
				}
			}
		}
		return mensajeTO;
	}

	public int consultaPtosTransferir(String sIdViaje, int iRegion)
			throws Exception {
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Connection connection = null;
		int nPuntos = 0;
		String sQuery = " SELECT DISTINCT A.VALORPUNTOS FROM  "
				+ this.schema_database + "PTO_CTLPROMOCIONES "
				+ " WHERE IDPRODUCTO = ? AND A.ESTATUS = 'A' "
				+ " AND IDREGION = ? AND FZAVENTAS = 'TELCEL' ";
		try {
			connection = ServiceLocator.getInstance().getConnection(
					ServiceLocator.jdbcCirculoAzul);
			statement = connection.prepareStatement(sQuery);
			statement.setString(1, sIdViaje);
			statement.setInt(2, iRegion);
			resultSet = statement.executeQuery();
			nPuntos = resultSet.next() ? resultSet.getInt(1) : 0;
			int n = nPuntos;
			return n;
		} catch (SQLException e) {
			throw new CAException(-1, "SQLException.consultaPtosTransferir["
					+ e.toString() + "]", (Exception) e);
		} catch (Exception e) {
			throw new CAException(-1, "AlianzasDAO.consultaPtosTransferir["
					+ e.toString() + "]", e);
		} finally {
			if (statement != null) {
				try {
					statement.close();
					statement = null;
				} catch (Exception var11_12) {
				}
			}
			if (resultSet != null) {
				try {
					resultSet.close();
					resultSet = null;
				} catch (Exception var11_13) {
				}
			}
			if (connection != null) {
				try {
					connection.close();
					connection = null;
				} catch (Exception var11_14) {
				}
			}
		}
	}

	/*
	 * Enabled aggressive block sorting Enabled unnecessary exception pruning
	 */
	public MensajeTO realizaCancelacion(ParametrosTO parametrosTO,
			int estatusTrans) throws CAException {
		Connection connection = null;
		MensajeTO mensajeTO = new MensajeTO();
		try {
			ConsultasDAO consultasDAO = new ConsultasDAO();
			PuntosRedimidosTO redimidosTO = consultasDAO
					.consultaPuntosRedimAlianza(parametrosTO.getFolio());
			if (redimidosTO.getIdMensaje() != 0) {
				MensajeTO mensajeTO2 = mensajeTO = redimidosTO
						.obtieneMensajeTO();
				return mensajeTO2;
			}
			PuntosTO puntosTO = consultasDAO.obtienePuntos(
					parametrosTO.getCuenta(), parametrosTO.getSecuencia());
			if (puntosTO.getIdMensaje() != 0) {
				MensajeTO mensajeTO3 = mensajeTO = puntosTO.obtieneMensajeTO();
				return mensajeTO3;
			}
			connection = ServiceLocator.getInstance().getConnection(
					ServiceLocator.jdbcCirculoAzul);
			connection.setAutoCommit(false);
			long fechaTransaccion = System.currentTimeMillis();
			PuntosDAO puntosDAO = new PuntosDAO();
			String referencia = "Cancelacion de Canje Realizo:"
					+ parametrosTO.getUsuariMovimiento() + " -> "
					+ parametrosTO.getComentario();
			mensajeTO = puntosDAO.insertaDetalle(connection, fechaTransaccion,
					referencia, 20, redimidosTO.getPtsTransferidos(), null,
					parametrosTO.getCuenta(), parametrosTO.getSecuencia(),
					parametrosTO.getTelefono(),
					parametrosTO.getUsuariMovimiento());
			referencia = "CIR -CANCELA -" + redimidosTO.getPtsTransferidos()
					+ " PTOS. A PET. DE " + parametrosTO.getUsuariMovimiento()
					+ " " + parametrosTO.getComentario();
			if (mensajeTO.getIdMensaje() == 0) {
				mensajeTO = puntosDAO.insertaComentarioTMP(connection,
						parametrosTO.getRegion(), parametrosTO.getCuenta(),
						parametrosTO.getUsuariMovimiento(), fechaTransaccion,
						referencia);
			}
			if (mensajeTO.getIdMensaje() == 0) {
				redimidosTO.setPtsAcumulados(0);
				redimidosTO.setPtsExcedentes(puntosTO.getPtsExcedentes()
						+ redimidosTO.getPtsExcedentesRedimidos());
				redimidosTO.setPtsPorVencer(redimidosTO
						.getPtsPorVencerRedimidos()
						+ puntosTO.getPtsPorVencer());
				redimidosTO.setPtsPorVencer1(redimidosTO
						.getPtsPorVencer1Redimidos()
						+ puntosTO.getPtsPorVencer1());
				redimidosTO.setPtsPorVencer2(redimidosTO
						.getPtsPorVencer2Redimidos()
						+ puntosTO.getPtsPorVencer2());
				redimidosTO.setPtsTransferidos(puntosTO.getPtsTransferidos()
						- redimidosTO.getPtsTransferidos());
				redimidosTO.setPtsRenta(puntosTO.getPtsRenta()
						+ redimidosTO.getPtsRentaRedimidos());
				redimidosTO.setPtsPromocion(puntosTO.getPtsPromocion()
						+ redimidosTO.getPtsPromocionRedimidos());
				redimidosTO.setPtsAntiguedad(puntosTO.getPtsAntiguedad()
						+ redimidosTO.getPtsPorAntiguedadRedimidos());
				mensajeTO = puntosDAO.actualizaPuntos(redimidosTO, connection,
						null, parametrosTO.getCuenta(),
						parametrosTO.getSecuencia(), false);
			}
			if (mensajeTO.getIdMensaje() == 0) {
				AlianzasTO alianzasTO = new AlianzasTO();
				alianzasTO.setEstatus("I");
				alianzasTO.setUsuario(parametrosTO.getUsuariMovimiento());
				if (parametrosTO.getOpcion() == 2) {
					alianzasTO.setStatusTrans(1);
				} else {
					alianzasTO.setStatusTrans(estatusTrans);
				}
				alianzasTO.setFolio(parametrosTO.getFolio());
				mensajeTO = this.actualizaAlianza(connection, alianzasTO,
						fechaTransaccion);
			}
			if (mensajeTO.getIdMensaje() == 0) {
				connection.commit();
				return mensajeTO;
			}
			connection.rollback();
			return mensajeTO;
		} catch (SQLException e) {
			if (connection != null) {
				try {
					connection.rollback();
				} catch (Exception redimidosTO) {
					// empty catch block
				}
			}
			this.error.info((Object) "SQLException.realizaCancelacion:",
					(Throwable) e);
			throw new CAException(-1, "[realizaCancelacion] SQLError: "
					+ e.toString() + "Actualizar Inf", (Exception) e);
		} catch (Exception e) {
			this.error.info((Object) "Exception.realizaCancelacion:",
					(Throwable) e);
			throw new CAException(-1, "[realizaCancelacion] Error: "
					+ e.toString() + "Actualizar Inf", e);
		} finally {
			if (connection != null) {
				try {
					connection.setAutoCommit(true);
					connection.close();
					connection = null;
				} catch (Exception var15_12) {
				}
			}
		}
	}

	private MensajeTO actualizaAlianza(Connection connection,
			AlianzasTO alianzasTO, long fechaActualizacion) throws CAException {
		MensajeTO mensajeTO;
		Connection lConn = null;
		mensajeTO = new MensajeTO();
		PreparedStatement statement = null;
		try {
			try {
				String query = "UPDATE "
						+ this.schema_database
						+ "PTO_TBLALIANZAS "
						+ "SET Estatus = ?, FechaUpd = ?, IdUsuarioUpd = ?, Statustrans =? WHERE Folio = ?";
				lConn = connection != null ? connection : ServiceLocator
						.getInstance().getConnection(
								ServiceLocator.jdbcCirculoAzul);
				statement = lConn.prepareStatement(query);
				statement.setString(1, alianzasTO.getEstatus());
				statement.setDate(2, new Date(fechaActualizacion));
				statement.setString(3, alianzasTO.getUsuario());
				statement.setInt(4, alianzasTO.getStatusTrans());
				statement.setString(5, alianzasTO.getFolio());
				if (statement.executeUpdate() > 0) {
					mensajeTO.agregaMensaje(0, "Proceso Exitoso.");
				} else {
					mensajeTO.agregaMensaje(1,
							"No se realizo la actualizacion de la alianza");
				}
			} catch (SQLException e) {
				if (connection != null) {
					try {
						connection.rollback();
					} catch (Exception var9_10) {
						// empty catch block
					}
				}
				this.error.info((Object) "SQLException.actualizaAlianza:",
						(Throwable) e);
				throw new CAException(-1, "[realizaCancelacion] SQLError: "
						+ e.toString() + "Actualizar Inf", (Exception) e);
			} catch (Exception e) {
				this.error.info((Object) "Exception.actualizaAlianza:",
						(Throwable) e);
				throw new CAException(-1, "[realizaCancelacion] Error: "
						+ e.toString() + "Actualizar Inf", e);
			}
		} finally {
			if (lConn != null && connection == null) {
				try {
					lConn.close();
					lConn = null;
				} catch (Exception var11_13) {
				}
			}
		}
		return mensajeTO;
	}

	public MensajeTO realizaCanje(ParametrosTO parametrosTO,
			int millasPorTranferir, int valorPuntos, String descAlianza,
			String cuentaAlianza, int ptsTransferir, int valorCertificado,
			String puntoVenta, String numEmpleado, String dirIP,
			String idViaje, int valorPesos, String referencia, int opcion)
			throws CAException {
		MensajeTO mensajeTO;
		Connection connection = null;
		mensajeTO = new MensajeTO();
		String comentario = null;
		int movimiento = 0;
		try {
			ConsultasDAO consultasDAO = new ConsultasDAO();
			PuntosTO puntosTO = consultasDAO.obtienePuntos(
					parametrosTO.getCuenta(), parametrosTO.getSecuencia());
			if (puntosTO.getIdMensaje() != 0) {
				MensajeTO mensajeTO2 = puntosTO.obtieneMensajeTO();
				return mensajeTO2;
			}
			try {
				PuntosRedimidosTO redimidosTO = Utils.canjePuntos(
						(PuntosTO) puntosTO, (int) valorPuntos, (int) 7);
				redimidosTO.setPtsTransferidos(valorPuntos);
				PuntosDAO puntosDAO = new PuntosDAO();
				long fechaTransaccion = System.currentTimeMillis();
				String folio = Utils.generaFolio((String) parametrosTO
						.getTelefono());
				AlianzasTO alianzasTO = new AlianzasTO();
				alianzasTO.setFolio(folio);
				alianzasTO.setSecuencia(parametrosTO.getSecuencia());
				alianzasTO.setCuenta(parametrosTO.getCuenta());
				alianzasTO.setStatusTrans(4);
				alianzasTO.setUsuario(parametrosTO.getUsuariMovimiento());
				alianzasTO.setEstatus("A");
				alianzasTO.setCveAlianza(Integer.toString(parametrosTO
						.getOpcion()));
				alianzasTO.setPuntosRedimidosTO(redimidosTO);
				alianzasTO.setNumeroCanje(-1);
				if (parametrosTO.getOpcion() == 2) {
					opcion = -1;
					alianzasTO.setIdCuentaAlianza(2);
					alianzasTO.setCuentaAlianza(cuentaAlianza);
					alianzasTO.setComentario("Canjeados a:" + descAlianza + ","
							+ parametrosTO.getComentario());
					alianzasTO.setMillas(0);
					alianzasTO.setArchivoSalida(idViaje);
					alianzasTO.setValorCuponOrig(valorCertificado);
					Calendar calendar = Calendar.getInstance();
					calendar.setTimeInMillis(fechaTransaccion);
					calendar.add(1, 1);
					alianzasTO.setVigenciaMax(new Date(calendar
							.getTimeInMillis()));
					alianzasTO.setIdPuntoVenta(puntoVenta);
					movimiento = 19;
					alianzasTO.setNumeroCanje(-1);
				}
				connection = ServiceLocator.getInstance().getConnection(
						ServiceLocator.jdbcCirculoAzul);
				connection.setAutoCommit(false);
				mensajeTO = this.insertaAlianza(connection, alianzasTO,
						puntosTO, fechaTransaccion);
				if (mensajeTO.getIdMensaje() == 0) {
					comentario = "Canjeados a:" + descAlianza + "->"
							+ parametrosTO.getComentario() + ", atendio: "
							+ numEmpleado + " IP: " + dirIP;
					if (comentario.length() > 100) {
						comentario = comentario.substring(0, 100);
					}
					mensajeTO = puntosDAO.insertaDetalle(connection,
							fechaTransaccion, comentario, movimiento,
							ptsTransferir * -1, null, parametrosTO.getCuenta(),
							parametrosTO.getSecuencia(),
							parametrosTO.getTelefono(),
							parametrosTO.getUsuariMovimiento());
				}
				if (mensajeTO.getIdMensaje() == 0) {
					redimidosTO.setPtsTransferidos(puntosTO
							.getPtsTransferidos() + valorPuntos);
					mensajeTO = puntosDAO.actualizaPuntos(redimidosTO,
							connection, null, parametrosTO.getCuenta(),
							parametrosTO.getSecuencia(), false);
				}
				if (mensajeTO.getIdMensaje() == 0) {
					comentario = "CIR - CANJE DE -" + valorPuntos + " PTOS. A "
							+ "AMEX " + "A PET. DE "
							+ parametrosTO.getUsuariMovimiento() + " "
							+ parametrosTO.getComentario().toUpperCase();
					comentario = "PTO.VTA:" + puntoVenta + "-" + comentario;
					mensajeTO = puntosDAO.insertaComentarioTMP(connection,
							parametrosTO.getRegion(), parametrosTO.getCuenta(),
							parametrosTO.getUsuariMovimiento(),
							fechaTransaccion, comentario);
				}
				if (mensajeTO.getIdMensaje() == 0) {
					Catalogo properties = new Catalogo();
					properties.setTabla("propiedades");
					properties.cargaCatalogo();
					String linea = parametrosTO.getTelefono();
					SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy");
					String fechaHoy = sdf.format(new java.util.Date());
					String mensajeSMS = String.valueOf(fechaHoy)
							+ " Redencion de "
							+ ptsTransferir
							+ " Puntos por CERTIFICADO AMEX. Para cualquier aclaracion marca *111 desde tu Telcel.";
					NotificaSMS notificaSMS = new NotificaSMS();
					notificaSMS.enviaSMSRedencionCA(linea, mensajeSMS,
							properties);
					connection.commit();
				} else {
					connection.rollback();
				}
			} catch (SQLException e) {
				if (connection != null) {
					try {
						connection.rollback();
					} catch (Exception e1) {
						// empty catch block
					}
				}
				this.error.info((Object) "SQLException.realizaCanje:",
						(Throwable) e);
				throw new CAException(-1, "[realizaCanje] SQLError: "
						+ e.toString() + "Actualizar Inf", (Exception) e);
			} catch (Exception e) {
				if (connection != null) {
					try {
						connection.rollback();
					} catch (Exception e2) {
						// empty catch block
					}
				}
				this.error.info((Object) "Exception.realizaCanje:",
						(Throwable) e);
				throw new CAException(-1, "[realizaCanje] Error: "
						+ e.toString() + "Actualizar Inf", e);
			}
		} finally {
			if (connection != null) {
				try {
					connection.setAutoCommit(true);
					connection.close();
					connection = null;
				} catch (Exception var35_26) {
				}
			}
		}
		return mensajeTO;
	}

	private MensajeTO insertaAlianza(Connection connection,
			AlianzasTO alianzasTO, PuntosTO puntosTO, long fechaOperacion)
			throws CAException {
		MensajeTO mensajeTO;
		PreparedStatement statement = null;
		mensajeTO = new MensajeTO();
		try {
			try {
				String squery = "INSERT INTO "
						+ this.schema_database
						+ "PTO_TBLALIANZAS (Folio, CtaAlianza, IdCuenta, StatusTrans, Secuencia, "
						+ "Pacad, Pacad1,Pacad2, Prenta, Pexce, Ptransf, FechaOper, Cuenta, Comentarios, Usuario, Estatus, "
						+ "Millas, Pdisp, ArchivoSalida, NumeroCanje, IdPuntoVta,puntosantiguedad, puntospromocion,ValCuponOrig,"
						+ "Vigenciamax, idref,fechafolio,opcion, PtsDispoRes,Puntosdispo ) VALUES"
						+ "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,? )";
				statement = connection.prepareStatement(squery);
				statement.setString(1, alianzasTO.getFolio());
				statement.setString(2, alianzasTO.getCuentaAlianza());
				statement.setInt(3, alianzasTO.getIdCuentaAlianza());
				statement.setInt(4, alianzasTO.getStatusTrans());
				statement.setInt(5, alianzasTO.getSecuencia());
				statement.setInt(6, alianzasTO.getPuntosRedimidosTO()
						.getPtsPorVencerRedimidos());
				statement.setInt(7, alianzasTO.getPuntosRedimidosTO()
						.getPtsPorVencer1Redimidos());
				statement.setInt(8, alianzasTO.getPuntosRedimidosTO()
						.getPtsPorVencer2Redimidos());
				statement.setInt(9, alianzasTO.getPuntosRedimidosTO()
						.getPtsRentaRedimidos());
				statement.setInt(10, alianzasTO.getPuntosRedimidosTO()
						.getPtsExcedentesRedimidos());
				statement.setInt(11, alianzasTO.getPuntosRedimidosTO()
						.getPtsTransferidos());
				statement.setDate(12, new Date(fechaOperacion));
				statement.setString(13, alianzasTO.getCuenta());
				statement.setString(14, alianzasTO.getComentario());
				statement.setString(15, alianzasTO.getUsuario());
				statement.setString(16, alianzasTO.getEstatus());
				statement.setInt(17, alianzasTO.getMillas());
				statement.setInt(18, puntosTO.getPtosDisponibles());
				if (alianzasTO.getArchivoSalida() == null) {
					statement.setNull(19, 12);
				} else {
					statement.setString(19, alianzasTO.getArchivoSalida());
				}
				statement.setInt(20, alianzasTO.getNumeroCanje());
				statement.setString(21, alianzasTO.getIdPuntoVenta());
				statement.setInt(22, alianzasTO.getPuntosRedimidosTO()
						.getPtsPorAntiguedadRedimidos());
				statement.setInt(23, alianzasTO.getPuntosRedimidosTO()
						.getPtsPromocionRedimidos());
				if (alianzasTO.getValorCuponOrig() > 0) {
					statement.setInt(24, alianzasTO.getValorCuponOrig());
				} else {
					statement.setNull(24, 4);
				}
				if (alianzasTO.getVigenciaMax() == null) {
					statement.setNull(25, 91);
				} else {
					statement.setDate(25, alianzasTO.getVigenciaMax());
				}
				if (alianzasTO.getIdReferencia() == null) {
					statement.setNull(26, 12);
				} else {
					statement.setString(26, alianzasTO.getIdReferencia());
				}
				if (alianzasTO.getFechaFolio() == null) {
					statement.setNull(27, 93);
				} else {
					statement.setTimestamp(27, alianzasTO.getFechaFolio());
				}
				if (alianzasTO.getOpcion() == -1) {
					statement.setNull(28, 4);
				} else {
					statement.setInt(28, alianzasTO.getOpcion());
				}
				statement.setInt(29, alianzasTO.getPuntosRedimidosTO()
						.getPtosDisponibles());
				statement.setInt(30, puntosTO.getPtosDisponibles());
				if (statement.executeUpdate() > 0) {
					mensajeTO.agregaMensaje(0, "Proceso Exitoso");
				} else {
					mensajeTO.agregaMensaje(1, "No se inserto la alianza");
				}
			} catch (SQLException e) {
				this.error.info((Object) "SQLException.insertaAlianza:",
						(Throwable) e);
				throw new CAException(-1, "[insertaAlianza] SQLError: "
						+ e.toString() + "Insertar Inf", (Exception) e);
			} catch (Exception e) {
				this.error.info((Object) "Exception.insertaAlianza:",
						(Throwable) e);
				throw new CAException(-1, "[insertaAlianza] Error: "
						+ e.toString() + "Insertar Inf", e);
			}
		} finally {
			if (statement != null) {
				try {
					statement.close();
					statement = null;
				} catch (Exception var10_12) {
				}
			}
		}
		return mensajeTO;
	}

	public Hashtable<String, AlianzasTO> consultaInicialAlianzas(int iRegion,
			String sCuenta, int nPtsDisp, int iSecuencia) throws CAException {
		try {
			CatalogoDAO catalogoDAO = new CatalogoDAO();
			ArrayList<AlianzasTO> alianzas = catalogoDAO.consultaAlianzas(0,
					sCuenta, iSecuencia, "A", null);
			if (alianzas != null) {
				Iterator<AlianzasTO> iterator = alianzas.iterator();
				Hashtable<String, AlianzasTO> hashtable = new Hashtable<String, AlianzasTO>();
				while (iterator.hasNext()) {
					AlianzasTO alianzasTO = iterator.next();
					if (alianzasTO.getIdCuentaAlianza() != 2)
						continue;
					alianzasTO.setCupones(this.consultaCuponesAmex(sCuenta,
							iSecuencia));
					alianzasTO.setProductos(catalogoDAO.listaPromociones(
							iRegion, nPtsDisp, null, 1086, "TELCEL", "TELCEL",
							null, null));
					hashtable.put("AMEX", alianzasTO);
				}
				return hashtable;
			}
		} catch (Exception e) {
			throw new CAException(-1, "AlianzasDAO.consultaInicialAlianzas["
					+ e.toString() + "]", e);
		}
		return null;
	}

	public AlianzasTO consultaPromocionesAmex(int alianza, int iRegion,
			String linea, int iSecuencia, int ptosDisponibles)
			throws CAException {
		try {
			CatalogoDAO catalogoDAO = new CatalogoDAO();
			AlianzasTO alianzasTO = catalogoDAO.consultaAlianza(alianza, linea,
					iSecuencia, "A");
			if (alianzasTO != null && alianzasTO.getIdCuentaAlianza() == 2) {
				alianzasTO.setProductos(catalogoDAO.listaPromociones(iRegion,
						ptosDisponibles, null, 1086, "TELCEL", "TELCEL", null,
						null));
			}
			return alianzasTO;
		} catch (Exception e) {
			throw new CAException(-1, "AlianzasDAO.consultaInicialAlianzas["
					+ e.toString() + "]", e);
		}
	}

	public AlianzasTO consultaPromocionesTicketMaster(int region, int ptsDisp)
			throws CAException {
		AlianzasTO alianzasTO = new AlianzasTO();
		CatalogoDAO catalogoDAO = new CatalogoDAO();
		alianzasTO.setProductos(catalogoDAO.listaPromociones(region, ptsDisp,
				null, 1086, "TELCEL", "TELCEL", null, null));
		return alianzasTO;
	}
}
