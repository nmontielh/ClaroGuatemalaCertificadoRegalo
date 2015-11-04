/*
 * Decompiled with CFR 0_102.
 * 
 * Could not load the following classes:
 *  com.claro.ejb.CirculoAzulLocal
 *  com.claro.ejb.CirculoAzulLocalHome
 *  com.claro.exception.ClaroException
 *  com.claro.transfer.certificados.ActivacionTarjetaCertificadoTO
 *  com.claro.transfer.certificados.MensajeServiceTO
 *  com.claro.transfer.certificados.MovimientoCertificadoTO
 *  com.claro.transfer.certificados.TarjetaCertificadoTO
 *  com.claro.util.ServiceLocator
 *  javax.ejb.EJBLocalHome
 */
package com.claro.ws.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.claro.ejb.CirculoAzulLocal;
import com.claro.ejb.CirculoAzulLocalHome;
import com.claro.exception.ClaroException;
import com.claro.transfer.certificados.ActivacionTarjetaCertificadoTO;
import com.claro.transfer.certificados.MensajeServiceTO;
import com.claro.transfer.certificados.MovimientoCertificadoTO;
import com.claro.transfer.certificados.TarjetaCertificadoTO;
import com.claro.util.ServiceLocator;

public class ClaroService {
	public String consultaSaldo(String numeroCertificado) throws Exception {
		TarjetaCertificadoTO tarjetaCertificadoTO;
		block4: {
			tarjetaCertificadoTO = new TarjetaCertificadoTO();
			try {
				if (numeroCertificado != null
						&& !numeroCertificado.trim().equals(""))
					break block4;
				return "01|El numero de certificado es requerido";
			} catch (Exception e) {
				return "07|" + e.toString();
			}
		}
		CirculoAzulLocalHome circuloAzulLocalHome = (CirculoAzulLocalHome) ServiceLocator
				.getInstance().getLocalHome(ServiceLocator.ejbCirculoAzul);
		CirculoAzulLocal circuloAzulLocal = circuloAzulLocalHome.create();
		tarjetaCertificadoTO = circuloAzulLocal
				.consultaSaldoTarjetaCertificado(numeroCertificado);
		if (tarjetaCertificadoTO.getIdMensaje().equals("00")) {
			return String.valueOf(tarjetaCertificadoTO.getNumeroTarjeta())
					+ "|" + tarjetaCertificadoTO.getNumeroCertificado() + "|"
					+ tarjetaCertificadoTO.getSaldo() + "|"
					+ tarjetaCertificadoTO.getFechaActivacion() + "|"
					+ tarjetaCertificadoTO.getFechaExpiracion() + "|"
					+ tarjetaCertificadoTO.getEstatus() + "|"
					+ tarjetaCertificadoTO.getIdMensaje() + "|"
					+ tarjetaCertificadoTO.getMensaje();
		}
		return String.valueOf(tarjetaCertificadoTO.getIdMensaje()) + "|"
				+ tarjetaCertificadoTO.getMensaje();
	}

	public String activaTarjetaCertificado(String numeroTarjeta,
			long montoCertificado, String idUsuario) throws Exception {
		ActivacionTarjetaCertificadoTO activacionTarjetaCertificadoTO;
		block8: {
			block7: {
				block6: {
					activacionTarjetaCertificadoTO = new ActivacionTarjetaCertificadoTO();
					try {
						if (numeroTarjeta != null
								&& !numeroTarjeta.trim().equals(""))
							break block6;
						return "01|El numero de certificado es requerido";
					} catch (Exception e) {
						return "07|" + e.toString();
					}
				}
				if (montoCertificado >= 0)
					break block7;
				return "01|El monto del certificado debe ser mayor a 0";
			}
			if (idUsuario != null && !idUsuario.trim().equals(""))
				break block8;
			return "01|El usuario es requerido";
		}
		CirculoAzulLocalHome circuloAzulLocalHome = (CirculoAzulLocalHome) ServiceLocator
				.getInstance().getLocalHome(ServiceLocator.ejbCirculoAzul);
		CirculoAzulLocal circuloAzulLocal = circuloAzulLocalHome.create();
		activacionTarjetaCertificadoTO = circuloAzulLocal
				.activaTarjetaCertificado(numeroTarjeta, montoCertificado,
						idUsuario);
		if (activacionTarjetaCertificadoTO.getIdMensaje().equals("00")) {
			return String.valueOf(activacionTarjetaCertificadoTO
					.getNumeroTarjeta())
					+ "|"
					+ activacionTarjetaCertificadoTO.getNumeroCertificado()
					+ "|"
					+ activacionTarjetaCertificadoTO.getMontoCertificado()
					+ "|"
					+ activacionTarjetaCertificadoTO.getEstatus()
					+ "|"
					+ activacionTarjetaCertificadoTO.getFechaActivacion()
					+ "|"
					+ activacionTarjetaCertificadoTO.getFechaExpiracion();
		}
		return String.valueOf(activacionTarjetaCertificadoTO.getIdMensaje())
				+ "|" + activacionTarjetaCertificadoTO.getMensaje();
	}

	public String aplicaCertificado(
			MovimientoCertificadoTO movimientoCertificadoTO) throws Exception {
		MensajeServiceTO mensajeServiceTO;
		block4: {
			mensajeServiceTO = new MensajeServiceTO();
			try {
				if (movimientoCertificadoTO.getNumeroCertificado() != null
						&& !movimientoCertificadoTO.getNumeroCertificado()
								.trim().equals(""))
					break block4;
				return "01|El numero de certificado es requerido";
			} catch (Exception e) {
				return "07|" + e.toString();
			}
		}
		CirculoAzulLocalHome circuloAzulLocalHome = (CirculoAzulLocalHome) ServiceLocator
				.getInstance().getLocalHome(ServiceLocator.ejbCirculoAzul);
		CirculoAzulLocal circuloAzulLocal = circuloAzulLocalHome.create();
		mensajeServiceTO = circuloAzulLocal
				.aplicaCertificado(movimientoCertificadoTO);
		if (mensajeServiceTO.getId().equals("00")) {
			return String.valueOf(mensajeServiceTO.getId()) + "|"
					+ mensajeServiceTO.getMensaje() + "|"
					+ mensajeServiceTO.getFolio();
		}
		return String.valueOf(mensajeServiceTO.getId()) + "|"
				+ mensajeServiceTO.getMensaje();
	}

	public String cancelaTarjetaCertificado(String numeroCertificado,
			String idUsuario) throws Exception {
		MensajeServiceTO mensajeServiceTO;
		block5: {
			block4: {
				mensajeServiceTO = new MensajeServiceTO();
				try {
					if (numeroCertificado != null
							&& !numeroCertificado.trim().equals(""))
						break block4;
					return "01|El numero de certificado es requerido";
				} catch (Exception e) {
					throw new ClaroException("07", e.toString());
				}
			}
			if (idUsuario != null && !idUsuario.trim().equals(""))
				break block5;
			return "01|El usuario es requerido";
		}
		CirculoAzulLocalHome circuloAzulLocalHome = (CirculoAzulLocalHome) ServiceLocator
				.getInstance().getLocalHome(ServiceLocator.ejbCirculoAzul);
		CirculoAzulLocal circuloAzulLocal = circuloAzulLocalHome.create();
		mensajeServiceTO = circuloAzulLocal.cancelaTarjetaCertificado(
				numeroCertificado, idUsuario);
		return String.valueOf(mensajeServiceTO.getId()) + "|"
				+ mensajeServiceTO.getMensaje();
	}

	public String cancelaAplicaCertificado(String folio, String idUsuario,
			String idpuntoVta, String referencia) throws Exception {
		MensajeServiceTO mensajeServiceTO;
		block9: {
			block8: {
				block7: {
					block6: {
						mensajeServiceTO = new MensajeServiceTO();
						try {
							if (folio != null && !folio.trim().equals(""))
								break block6;
							return "01|El numero de folio es requerido";
						} catch (Exception e) {
							throw new ClaroException("07", e.toString());
						}
					}
					if (idUsuario != null && !idUsuario.trim().equals(""))
						break block7;
					return "01|El usuario es requerido";
				}
				if (idpuntoVta != null && !idpuntoVta.trim().equals(""))
					break block8;
				return "01|El Punto de Venta es requerido";
			}
			if (referencia != null && !referencia.trim().equals(""))
				break block9;
			return "01|La descripcion del movimiento es requerida";
		}
		CirculoAzulLocalHome circuloAzulLocalHome = (CirculoAzulLocalHome) ServiceLocator
				.getInstance().getLocalHome(ServiceLocator.ejbCirculoAzul);
		CirculoAzulLocal circuloAzulLocal = circuloAzulLocalHome.create();
		mensajeServiceTO = circuloAzulLocal.cancelaAplicaCertificado(folio,
				idUsuario, idpuntoVta, referencia);
		return String.valueOf(mensajeServiceTO.getId()) + "|"
				+ mensajeServiceTO.getMensaje();
	}

	public String consultaMovimientosTajetaCertificado(String numeroCertificado)
			throws ClaroException {
		StringBuilder devuelve;
		block6: {
			List movimientos;
			block5: {
				movimientos = new ArrayList();
				devuelve = new StringBuilder();
				if (numeroCertificado != null
						&& !numeroCertificado.trim().equals(""))
					break block5;
				return "01|El numero de certificado es requerido";
			}
			try {
				CirculoAzulLocalHome circuloAzulLocalHome = (CirculoAzulLocalHome) ServiceLocator
						.getInstance().getLocalHome(
								ServiceLocator.ejbCirculoAzul);
				CirculoAzulLocal circuloAzulLocal = circuloAzulLocalHome
						.create();
				movimientos = circuloAzulLocal
						.consultaMovimientosTajetaCertificado(numeroCertificado);
				if (!(movimientos == null || movimientos.isEmpty())) {
					Iterator iteraRegistros = movimientos.iterator();
					while (iteraRegistros.hasNext()) {
						MovimientoCertificadoTO movimientoCertificadoTO = new MovimientoCertificadoTO();
						movimientoCertificadoTO = (MovimientoCertificadoTO) iteraRegistros
								.next();
						devuelve.append(String.valueOf(movimientoCertificadoTO
								.getNumeroTarjeta()) + "|");
						devuelve.append(String.valueOf(movimientoCertificadoTO
								.getNumeroCertificado()) + "|");
						devuelve.append(String.valueOf(movimientoCertificadoTO
								.getIdMotivo()) + "|");
						devuelve.append(String.valueOf(movimientoCertificadoTO
								.getIdUsuario()) + "|");
						devuelve.append(String.valueOf(movimientoCertificadoTO
								.getPuntoVenta()) + "|");
						devuelve.append(String.valueOf(movimientoCertificadoTO
								.getFechaOperacion()) + "|");
						devuelve.append(String.valueOf(movimientoCertificadoTO
								.getEstatus()) + "|");
						devuelve.append(String.valueOf(movimientoCertificadoTO
								.getValorAplicado()) + "|");
						devuelve.append(String.valueOf(movimientoCertificadoTO
								.getValorAnterior()) + "|");
						devuelve.append(String.valueOf(movimientoCertificadoTO
								.getValorRestante()) + "|");
						devuelve.append(String.valueOf(movimientoCertificadoTO
								.getReferencia()) + "|");
					}
					break block6;
				}
				return "08|NO EXISTEN MOVIMIENTOS DEL CERTIFICADO";
			} catch (Exception e) {
				throw new ClaroException("07", e.toString());
			}
		}
		return devuelve.toString();
	}

	public String consultaTajeta(String numeroTarjeta, long montoCertificado)
			throws Exception {
		boolean existeTarjeta;
		block5: {
			block4: {
				existeTarjeta = false;
				try {
					if (numeroTarjeta != null
							&& !numeroTarjeta.trim().equals(""))
						break block4;
					return "01|El numero de certificado es requerido";
				} catch (Exception e) {
					return "07|" + e.toString();
				}
			}
			if (montoCertificado >= 0)
				break block5;
			return "01|El monto del certificado debe ser mayor a 0";
		}
		CirculoAzulLocalHome circuloAzulLocalHome = (CirculoAzulLocalHome) ServiceLocator
				.getInstance().getLocalHome(ServiceLocator.ejbCirculoAzul);
		CirculoAzulLocal circuloAzulLocal = circuloAzulLocalHome.create();
		existeTarjeta = circuloAzulLocal.consultaTajeta(numeroTarjeta,
				montoCertificado);
		return String.valueOf(existeTarjeta);
	}

	public String consultaSaldoCertificado(String numeroTarjeta)
			throws Exception {
		TarjetaCertificadoTO tarjetaCertificadoTO;
		block4: {
			tarjetaCertificadoTO = new TarjetaCertificadoTO();
			try {
				if (numeroTarjeta != null && !numeroTarjeta.trim().equals(""))
					break block4;
				return "01|El numero de tarjetal es requerido";
			} catch (Exception e) {
				return "07|" + e.toString();
			}
		}
		CirculoAzulLocalHome circuloAzulLocalHome = (CirculoAzulLocalHome) ServiceLocator
				.getInstance().getLocalHome(ServiceLocator.ejbCirculoAzul);
		CirculoAzulLocal circuloAzulLocal = circuloAzulLocalHome.create();
		tarjetaCertificadoTO = circuloAzulLocal
				.consultaSaldoCertificado(numeroTarjeta);
		if (tarjetaCertificadoTO.getIdMensaje().equals("00")) {
			return String.valueOf(tarjetaCertificadoTO.getNumeroTarjeta())
					+ "|" + tarjetaCertificadoTO.getNumeroCertificado() + "|"
					+ tarjetaCertificadoTO.getSaldo() + "|"
					+ tarjetaCertificadoTO.getFechaActivacion() + "|"
					+ tarjetaCertificadoTO.getFechaExpiracion() + "|"
					+ tarjetaCertificadoTO.getEstatus() + "|"
					+ tarjetaCertificadoTO.getIdMensaje() + "|"
					+ tarjetaCertificadoTO.getMensaje();
		}
		return String.valueOf(tarjetaCertificadoTO.getIdMensaje()) + "|"
				+ tarjetaCertificadoTO.getMensaje();
	}

	public String consultaMovimientosCertificado(String numeroTarjeta)
			throws ClaroException {
		StringBuilder devuelve;
		block6: {
			List movimientos;
			block5: {
				movimientos = new ArrayList();
				devuelve = new StringBuilder();
				if (numeroTarjeta != null && !numeroTarjeta.trim().equals(""))
					break block5;
				return "01|El numero de tarjeta es requerido";
			}
			try {
				CirculoAzulLocalHome circuloAzulLocalHome = (CirculoAzulLocalHome) ServiceLocator
						.getInstance().getLocalHome(
								ServiceLocator.ejbCirculoAzul);
				CirculoAzulLocal circuloAzulLocal = circuloAzulLocalHome
						.create();
				movimientos = circuloAzulLocal
						.consultaMovimientosCertificado(numeroTarjeta);
				if (!(movimientos == null || movimientos.isEmpty())) {
					Iterator iteraRegistros = movimientos.iterator();
					while (iteraRegistros.hasNext()) {
						MovimientoCertificadoTO movimientoCertificadoTO = new MovimientoCertificadoTO();
						movimientoCertificadoTO = (MovimientoCertificadoTO) iteraRegistros
								.next();
						devuelve.append(String.valueOf(movimientoCertificadoTO
								.getNumeroTarjeta()) + "|");
						devuelve.append(String.valueOf(movimientoCertificadoTO
								.getNumeroCertificado()) + "|");
						devuelve.append(String.valueOf(movimientoCertificadoTO
								.getIdMotivo()) + "|");
						devuelve.append(String.valueOf(movimientoCertificadoTO
								.getIdUsuario()) + "|");
						devuelve.append(String.valueOf(movimientoCertificadoTO
								.getPuntoVenta()) + "|");
						devuelve.append(String.valueOf(movimientoCertificadoTO
								.getFechaOperacion()) + "|");
						devuelve.append(String.valueOf(movimientoCertificadoTO
								.getEstatus()) + "|");
						devuelve.append(String.valueOf(movimientoCertificadoTO
								.getValorAplicado()) + "|");
						devuelve.append(String.valueOf(movimientoCertificadoTO
								.getValorAnterior()) + "|");
						devuelve.append(String.valueOf(movimientoCertificadoTO
								.getValorRestante()) + "|");
						devuelve.append(String.valueOf(movimientoCertificadoTO
								.getReferencia()) + "|");
					}
					break block6;
				}
				return "08|NO EXISTEN MOVIMIENTOS DE LA TARJETA";
			} catch (Exception e) {
				throw new ClaroException("07", e.toString());
			}
		}
		return devuelve.toString();
	}

	public String consultaTajetaVendida(String numeroTarjeta,
			long montoCertificado) throws Exception {
		String existeTarjeta;
		block5: {
			block4: {
				existeTarjeta = "";
				try {
					if (numeroTarjeta != null
							&& !numeroTarjeta.trim().equals(""))
						break block4;
					return "01|El numero de certificado es requerido";
				} catch (Exception e) {
					return "07|" + existeTarjeta;
				}
			}
			if (montoCertificado >= 0)
				break block5;
			return "01|El monto del certificado debe ser mayor a 0";
		}
		CirculoAzulLocalHome circuloAzulLocalHome = (CirculoAzulLocalHome) ServiceLocator
				.getInstance().getLocalHome(ServiceLocator.ejbCirculoAzul);
		CirculoAzulLocal circuloAzulLocal = circuloAzulLocalHome.create();
		existeTarjeta = circuloAzulLocal.consultaTajetaVendida(numeroTarjeta,
				montoCertificado);
		return existeTarjeta;
	}
}
