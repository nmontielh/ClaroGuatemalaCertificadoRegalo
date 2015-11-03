/*     */package com.claro.ws.service;

/*     */
/*     *//*     */
import java.util.ArrayList;
/*     */
import java.util.Iterator;
/*     */
import java.util.List;

import com.claro.ejb.CirculoAzulLocal;
/*     */
import com.claro.ejb.CirculoAzulLocalHome;
/*     */
import com.claro.exception.ClaroException;
import com.claro.transfer.certificados.ActivacionTarjetaCertificadoTO;
import com.claro.transfer.certificados.MensajeServiceTO;
import com.claro.transfer.certificados.MovimientoCertificadoTO;
import com.claro.transfer.certificados.TarjetaCertificadoTO;
/*     */
import com.claro.util.ServiceLocator;

/*     */
/*     */
/*     */public class ClaroService
/*     */{
	/*     */public String consultaSaldo(String numeroCertificado)
	/*     */throws ClaroException
	/*     */{
		/* 21 */TarjetaCertificadoTO tarjetaCertificadoTO = new TarjetaCertificadoTO();
		/*     */try
		/*     */{
			/* 24 */if ((numeroCertificado == null)
					|| (numeroCertificado.trim().equals(""))) {
				/* 25 */return "01|El numero de certificado es requerido";
				/*     */}
			/*     */
			/*     */
			/* 29 */CirculoAzulLocalHome circuloAzulLocalHome = (CirculoAzulLocalHome) ServiceLocator
					.getInstance().getLocalHome(ServiceLocator.ejbCirculoAzul);
			/* 30 */CirculoAzulLocal circuloAzulLocal = circuloAzulLocalHome
					.create();
			/*     */
			/* 32 */tarjetaCertificadoTO = circuloAzulLocal
					.consultaSaldoTarjetaCertificado(numeroCertificado);
			/*     */}
		/*     */catch (Exception e) {
			/* 35 */return "07|" + e.toString();
			/*     */}
		/* 37 */if (tarjetaCertificadoTO.getIdMensaje().equals("00")) {
			/* 38 */return
			/*     */
			/*     */
			/* 41 */tarjetaCertificadoTO.getNumeroTarjeta() + "|"
					+ tarjetaCertificadoTO.getNumeroCertificado() + "|"
					+ tarjetaCertificadoTO.getSaldo() + "|"
					+ tarjetaCertificadoTO.getFechaActivacion() + "|"
					+ tarjetaCertificadoTO.getFechaExpiracion() + "|"
					+ tarjetaCertificadoTO.getEstatus() + "|"
					+ tarjetaCertificadoTO.getIdMensaje() + "|"
					+ tarjetaCertificadoTO.getMensaje();
			/*     */}
		/* 43 */return tarjetaCertificadoTO.getIdMensaje() + "|"
				+ tarjetaCertificadoTO.getMensaje();
		/*     */}

	/*     */
	/*     */
	/*     */public String activaTarjetaCertificado(String numeroTarjeta,
			long montoCertificado, String idUsuario)
	/*     */throws ClaroException
	/*     */{
		/* 50 */ActivacionTarjetaCertificadoTO activacionTarjetaCertificadoTO = new ActivacionTarjetaCertificadoTO();
		/*     */
		/*     */try
		/*     */{
			/* 54 */if ((numeroTarjeta == null)
					|| (numeroTarjeta.trim().equals(""))) {
				/* 55 */return "01|El numero de certificado es requerido";
				/*     */}
			/* 57 */if (montoCertificado < 0L) {
				/* 58 */return "01|El monto del certificado debe ser mayor a 0";
				/*     */}
			/* 60 */if ((idUsuario == null) || (idUsuario.trim().equals(""))) {
				/* 61 */return "01|El usuario es requerido";
				/*     */}
			/*     */
			/*     */
			/* 65 */CirculoAzulLocalHome circuloAzulLocalHome = (CirculoAzulLocalHome) ServiceLocator
					.getInstance().getLocalHome(ServiceLocator.ejbCirculoAzul);
			/* 66 */CirculoAzulLocal circuloAzulLocal = circuloAzulLocalHome
					.create();
			/*     */
			/* 68 */activacionTarjetaCertificadoTO = circuloAzulLocal
					.activaTarjetaCertificado(numeroTarjeta, montoCertificado,
							idUsuario);
			/*     */}
		/*     */catch (Exception e)
		/*     */{
			/* 72 */return "07|" + e.toString();
			/*     */}
		/* 74 */if (activacionTarjetaCertificadoTO.getIdMensaje().equals("00")) {
			/* 75 */return
			/*     */
			/* 77 */activacionTarjetaCertificadoTO.getNumeroTarjeta() + "|"
					+ activacionTarjetaCertificadoTO.getNumeroCertificado()
					+ "|"
					+ activacionTarjetaCertificadoTO.getMontoCertificado()
					+ "|" + activacionTarjetaCertificadoTO.getEstatus() + "|"
					+ activacionTarjetaCertificadoTO.getFechaActivacion() + "|"
					+ activacionTarjetaCertificadoTO.getFechaExpiracion();
			/*     */}
		/* 79 */return activacionTarjetaCertificadoTO.getIdMensaje() + "|"
				+ activacionTarjetaCertificadoTO.getMensaje();
		/*     */}

	/*     */
	/*     */
	/*     */
	/*     */public String aplicaCertificado(
			MovimientoCertificadoTO movimientoCertificadoTO)
	/*     */throws ClaroException
	/*     */{
		/* 87 */MensajeServiceTO mensajeServiceTO = new MensajeServiceTO();
		/*     */
		/*     */try
		/*     */{
			/* 91 */if ((movimientoCertificadoTO.getNumeroCertificado() == null)
					|| (movimientoCertificadoTO.getNumeroCertificado().trim()
							.equals(""))) {
				/* 92 */return "01|El numero de certificado es requerido";
				/*     */}
			/*     */
			/*     */
			/* 96 */CirculoAzulLocalHome circuloAzulLocalHome = (CirculoAzulLocalHome) ServiceLocator
					.getInstance().getLocalHome(ServiceLocator.ejbCirculoAzul);
			/* 97 */CirculoAzulLocal circuloAzulLocal = circuloAzulLocalHome
					.create();
			/*     */
			/* 99 */mensajeServiceTO = circuloAzulLocal
					.aplicaCertificado(movimientoCertificadoTO);
			/*     */}
		/*     */catch (Exception e)
		/*     */{
			/* 103 */return "07|" + e.toString();
			/*     */}
		/* 105 */if (mensajeServiceTO.getId().equals("00")) {
			/* 106 */return mensajeServiceTO.getId() + "|"
					+ mensajeServiceTO.getMensaje() + "|"
					+ mensajeServiceTO.getFolio();
			/*     */}
		/* 108 */return mensajeServiceTO.getId() + "|"
				+ mensajeServiceTO.getMensaje();
		/*     */}

	/*     */
	/*     */public String cancelaTarjetaCertificado(String numeroCertificado,
			String idUsuario)
	/*     */throws ClaroException
	/*     */{
		/* 114 */MensajeServiceTO mensajeServiceTO = new MensajeServiceTO();
		/*     */
		/*     */try
		/*     */{
			/* 118 */if ((numeroCertificado == null)
					|| (numeroCertificado.trim().equals(""))) {
				/* 119 */return "01|El numero de certificado es requerido";
				/*     */}
			/* 121 */if ((idUsuario == null) || (idUsuario.trim().equals(""))) {
				/* 122 */return "01|El usuario es requerido";
				/*     */}
			/*     */
			/*     */
			/* 126 */CirculoAzulLocalHome circuloAzulLocalHome = (CirculoAzulLocalHome) ServiceLocator
					.getInstance().getLocalHome(ServiceLocator.ejbCirculoAzul);
			/* 127 */CirculoAzulLocal circuloAzulLocal = circuloAzulLocalHome
					.create();
			/*     */
			/* 129 */mensajeServiceTO = circuloAzulLocal
					.cancelaTarjetaCertificado(numeroCertificado, idUsuario);
			/*     */}
		/*     */catch (Exception e)
		/*     */{
			/* 133 */throw new ClaroException("07", e.toString());
			/*     */}
		/*     */
		/* 136 */return mensajeServiceTO.getId() + "|"
				+ mensajeServiceTO.getMensaje();
		/*     */}

	/*     */
	/*     */public String cancelaAplicaCertificado(String folio, String idUsuario,
			String idpuntoVta, String referencia) throws ClaroException
	/*     */{
		/* 141 */MensajeServiceTO mensajeServiceTO = new MensajeServiceTO();
		/*     */
		/*     */try
		/*     */{
			/* 145 */if ((folio == null) || (folio.trim().equals(""))) {
				/* 146 */return "01|El numero de folio es requerido";
				/*     */}
			/* 148 */if ((idUsuario == null) || (idUsuario.trim().equals(""))) {
				/* 149 */return "01|El usuario es requerido";
				/*     */}
			/* 151 */if ((idpuntoVta == null) || (idpuntoVta.trim().equals(""))) {
				/* 152 */return "01|El Punto de Venta es requerido";
				/*     */}
			/* 154 */if ((referencia == null) || (referencia.trim().equals(""))) {
				/* 155 */return "01|La descripcion del movimiento es requerida";
				/*     */}
			/*     */
			/*     */
			/* 159 */CirculoAzulLocalHome circuloAzulLocalHome = (CirculoAzulLocalHome) ServiceLocator
					.getInstance().getLocalHome(ServiceLocator.ejbCirculoAzul);
			/* 160 */CirculoAzulLocal circuloAzulLocal = circuloAzulLocalHome
					.create();
			/*     */
			/* 162 */mensajeServiceTO = circuloAzulLocal
					.cancelaAplicaCertificado(folio, idUsuario, idpuntoVta,
							referencia);
			/*     */}
		/*     */catch (Exception e)
		/*     */{
			/* 166 */throw new ClaroException("07", e.toString());
			/*     */}
		/*     */
		/* 169 */return mensajeServiceTO.getId() + "|"
				+ mensajeServiceTO.getMensaje();
		/*     */}

	/*     */
	/*     */public String consultaMovimientosTajetaCertificado(
			String numeroCertificado) throws ClaroException
	/*     */{
		/* 174 */List<MovimientoCertificadoTO> movimientos = new ArrayList();
		/* 175 */StringBuilder devuelve = new StringBuilder();
		/*     */try
		/*     */{
			/* 178 */if ((numeroCertificado == null)
					|| (numeroCertificado.trim().equals(""))) {
				/* 179 */return "01|El numero de certificado es requerido";
				/*     */}
			/*     */
			/*     */
			/* 183 */CirculoAzulLocalHome circuloAzulLocalHome = (CirculoAzulLocalHome) ServiceLocator
					.getInstance().getLocalHome(ServiceLocator.ejbCirculoAzul);
			/* 184 */CirculoAzulLocal circuloAzulLocal = circuloAzulLocalHome
					.create();
			/*     */
			/* 186 */movimientos = circuloAzulLocal
					.consultaMovimientosTajetaCertificado(numeroCertificado);
			/*     */
			/* 188 */if ((movimientos != null) && (!movimientos.isEmpty())) {
				/* 189 */Iterator<MovimientoCertificadoTO> iteraRegistros = movimientos
						.iterator();
				/* 190 */while (iteraRegistros.hasNext()) {
					/* 191 */MovimientoCertificadoTO movimientoCertificadoTO = new MovimientoCertificadoTO();
					/* 192 */movimientoCertificadoTO = (MovimientoCertificadoTO) iteraRegistros
							.next();
					/* 193 */devuelve.append(movimientoCertificadoTO
							.getNumeroTarjeta() + "|");
					/* 194 */devuelve.append(movimientoCertificadoTO
							.getNumeroCertificado() + "|");
					/* 195 */devuelve.append(movimientoCertificadoTO
							.getIdMotivo() + "|");
					/* 196 */devuelve.append(movimientoCertificadoTO
							.getIdUsuario() + "|");
					/* 197 */devuelve.append(movimientoCertificadoTO
							.getPuntoVenta() + "|");
					/* 198 */devuelve.append(movimientoCertificadoTO
							.getFechaOperacion() + "|");
					/* 199 */devuelve.append(movimientoCertificadoTO
							.getEstatus() + "|");
					/* 200 */devuelve.append(movimientoCertificadoTO
							.getValorAplicado() + "|");
					/* 201 */devuelve.append(movimientoCertificadoTO
							.getValorAnterior() + "|");
					/* 202 */devuelve.append(movimientoCertificadoTO
							.getValorRestante() + "|");
					/* 203 */devuelve.append(movimientoCertificadoTO
							.getReferencia() + "|");
					/*     */}
				/*     */}
			/*     */else {
				/* 207 */return "08|NO EXISTEN MOVIMIENTOS DEL CERTIFICADO";
				/*     */}
			/*     */}
		/*     */catch (Exception e) {
			/* 211 */throw new ClaroException("07", e.toString());
			/*     */}
		/* 213 */return devuelve.toString();
		/*     */}

	/*     */
	/*     */public String consultaTajeta(String numeroTarjeta,
			long montoCertificado) throws ClaroException
	/*     */{
		/* 218 */boolean existeTarjeta = false;
		/*     */try
		/*     */{
			/* 221 */if ((numeroTarjeta == null)
					|| (numeroTarjeta.trim().equals("")))
				/* 222 */return "01|El numero de certificado es requerido";
			/* 223 */if (montoCertificado < 0L) {
				/* 224 */return "01|El monto del certificado debe ser mayor a 0";
				/*     */}
			/*     */
			/*     */
			/* 228 */CirculoAzulLocalHome circuloAzulLocalHome = (CirculoAzulLocalHome) ServiceLocator
					.getInstance().getLocalHome(ServiceLocator.ejbCirculoAzul);
			/* 229 */CirculoAzulLocal circuloAzulLocal = circuloAzulLocalHome
					.create();
			/*     */
			/* 231 */existeTarjeta = circuloAzulLocal.consultaTajeta(
					numeroTarjeta, montoCertificado);
			/*     */}
		/*     */catch (Exception e) {
			/* 234 */return "07|" + e.toString();
			/*     */}
		// TODO se corrige para que compile, al parecer la decompilacion jodio
		// algo o estaba ya con error
		/* 236 */return existeTarjeta + "";
		/*     */}

	/*     */
	/*     */public String consultaSaldoCertificado(String numeroTarjeta)
			throws ClaroException
	/*     */{
		/* 241 */TarjetaCertificadoTO tarjetaCertificadoTO = new TarjetaCertificadoTO();
		/*     */try
		/*     */{
			/* 244 */if ((numeroTarjeta == null)
					|| (numeroTarjeta.trim().equals(""))) {
				/* 245 */return "01|El numero de tarjetal es requerido";
				/*     */}
			/*     */
			/*     */
			/* 249 */CirculoAzulLocalHome circuloAzulLocalHome = (CirculoAzulLocalHome) ServiceLocator
					.getInstance().getLocalHome(ServiceLocator.ejbCirculoAzul);
			/* 250 */CirculoAzulLocal circuloAzulLocal = circuloAzulLocalHome
					.create();
			/*     */
			/* 252 */tarjetaCertificadoTO = circuloAzulLocal
					.consultaSaldoCertificado(numeroTarjeta);
			/*     */}
		/*     */catch (Exception e) {
			/* 255 */return "07|" + e.toString();
			/*     */}
		/* 257 */if (tarjetaCertificadoTO.getIdMensaje().equals("00")) {
			/* 258 */return
			/*     */
			/*     */
			/* 261 */tarjetaCertificadoTO.getNumeroTarjeta() + "|"
					+ tarjetaCertificadoTO.getNumeroCertificado() + "|"
					+ tarjetaCertificadoTO.getSaldo() + "|"
					+ tarjetaCertificadoTO.getFechaActivacion() + "|"
					+ tarjetaCertificadoTO.getFechaExpiracion() + "|"
					+ tarjetaCertificadoTO.getEstatus() + "|"
					+ tarjetaCertificadoTO.getIdMensaje() + "|"
					+ tarjetaCertificadoTO.getMensaje();
			/*     */}
		/* 263 */return tarjetaCertificadoTO.getIdMensaje() + "|"
				+ tarjetaCertificadoTO.getMensaje();
		/*     */}

	/*     */
	/*     */public String consultaMovimientosCertificado(String numeroTarjeta)
	/*     */throws ClaroException
	/*     */{
		/* 269 */List<MovimientoCertificadoTO> movimientos = new ArrayList();
		/* 270 */StringBuilder devuelve = new StringBuilder();
		/*     */try
		/*     */{
			/* 273 */if ((numeroTarjeta == null)
					|| (numeroTarjeta.trim().equals(""))) {
				/* 274 */return "01|El numero de tarjeta es requerido";
				/*     */}
			/*     */
			/*     */
			/* 278 */CirculoAzulLocalHome circuloAzulLocalHome = (CirculoAzulLocalHome) ServiceLocator
					.getInstance().getLocalHome(ServiceLocator.ejbCirculoAzul);
			/* 279 */CirculoAzulLocal circuloAzulLocal = circuloAzulLocalHome
					.create();
			/*     */
			/* 281 */movimientos = circuloAzulLocal
					.consultaMovimientosCertificado(numeroTarjeta);
			/*     */
			/* 283 */if ((movimientos != null) && (!movimientos.isEmpty())) {
				/* 284 */Iterator<MovimientoCertificadoTO> iteraRegistros = movimientos
						.iterator();
				/* 285 */while (iteraRegistros.hasNext()) {
					/* 286 */MovimientoCertificadoTO movimientoCertificadoTO = new MovimientoCertificadoTO();
					/* 287 */movimientoCertificadoTO = (MovimientoCertificadoTO) iteraRegistros
							.next();
					/* 288 */devuelve.append(movimientoCertificadoTO
							.getNumeroTarjeta() + "|");
					/* 289 */devuelve.append(movimientoCertificadoTO
							.getNumeroCertificado() + "|");
					/* 290 */devuelve.append(movimientoCertificadoTO
							.getIdMotivo() + "|");
					/* 291 */devuelve.append(movimientoCertificadoTO
							.getIdUsuario() + "|");
					/* 292 */devuelve.append(movimientoCertificadoTO
							.getPuntoVenta() + "|");
					/* 293 */devuelve.append(movimientoCertificadoTO
							.getFechaOperacion() + "|");
					/* 294 */devuelve.append(movimientoCertificadoTO
							.getEstatus() + "|");
					/* 295 */devuelve.append(movimientoCertificadoTO
							.getValorAplicado() + "|");
					/* 296 */devuelve.append(movimientoCertificadoTO
							.getValorAnterior() + "|");
					/* 297 */devuelve.append(movimientoCertificadoTO
							.getValorRestante() + "|");
					/* 298 */devuelve.append(movimientoCertificadoTO
							.getReferencia() + "|");
					/*     */}
				/*     */}
			/*     */else {
				/* 302 */return "08|NO EXISTEN MOVIMIENTOS DE LA TARJETA";
				/*     */}
			/*     */}
		/*     */catch (Exception e) {
			/* 306 */throw new ClaroException("07", e.toString());
			/*     */}
		/* 308 */return devuelve.toString();
		/*     */}

	/*     */
	/*     */public String consultaTajetaVendida(String numeroTarjeta,
			long montoCertificado) throws ClaroException
	/*     */{
		/* 313 */String existeTarjeta = "";
		/*     */try
		/*     */{
			/* 316 */if ((numeroTarjeta == null)
					|| (numeroTarjeta.trim().equals("")))
				/* 317 */return "01|El numero de certificado es requerido";
			/* 318 */if (montoCertificado < 0L) {
				/* 319 */return "01|El monto del certificado debe ser mayor a 0";
				/*     */}
			/*     */
			/*     */
			/* 323 */CirculoAzulLocalHome circuloAzulLocalHome = (CirculoAzulLocalHome) ServiceLocator
					.getInstance().getLocalHome(ServiceLocator.ejbCirculoAzul);
			/* 324 */CirculoAzulLocal circuloAzulLocal = circuloAzulLocalHome
					.create();
			/*     */
			/* 326 */existeTarjeta = circuloAzulLocal.consultaTajetaVendida(
					numeroTarjeta, montoCertificado);
			/*     */}
		/*     */catch (Exception e) {
			/* 329 */return "07|" + existeTarjeta;
			/*     */}
		/* 331 */return existeTarjeta;
		/*     */}
	/*     */
}

/*
 * Location:
 * /opt/trabajo/claro/Guatemala/IS/CO/ClaroClub.ear!/ClaroClubWeb.war!/
 * WEB-INF/classes/com/claro/ws/service/ClaroService.class Java compiler
 * version: 6 (50.0) JD-Core Version: 0.7.1
 */