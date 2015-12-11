package com.claro.gml.certificados.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.claro.gml.persistence.model.CcCtlusuarios;
import com.claro.gml.persistence.model.CcTblcertificado;
import com.claro.gml.persistence.model.CcTbltarjetacertificado;
import com.claro.transfer.certificados.constants.AplicadoCatalog;
import com.claro.transfer.certificados.constants.ErrorCatalog;
import com.claro.transfer.certificados.constants.EstatusCatalog;
import com.claro.transfer.certificados.constants.SucessCatalog;
import com.claro.transfer.certificados.exception.BussinesException;

public class CertificadosUtils {

	private static Logger logger = LoggerFactory.getLogger(CertificadosUtils.class);

	/**
	 * @see Metodo que valida si el certificado es valido (no aplicado)
	 * @param certificado
	 * @param estatus
	 * @return
	 */
	@Deprecated
	public static boolean isValid(String certificado, String estatus) {
		return (certificado != null) && (estatus.equals(AplicadoCatalog.NO));
	}

	/**
	 * @see valida que sea una region valida y exista, de lo contrario lanza una
	 *      exception
	 * @param user
	 * @throws BussinesException
	 */
	public static void regionExist(CcCtlusuarios user) throws BussinesException {

		logger.debug(" user  [{}]", user);

		if (user == null || user.getId() == null)
			throw new BussinesException(ErrorCatalog.NO_EXISTE_USUARIO);

		logger.debug("user->getid  [{}]", user.getId());

		String region = (user != null && user.getId() != null) ? user.getId().getRegion() : null;

		logger.debug("region [{}]", region);

		if (region == null)
			throw new BussinesException(ErrorCatalog.NO_EXISTE_USUARIO);

	}

	/**
	 * @see Valida que sea un certiticado valido o lanza exception ya definida
	 * @param numeroTarjeta
	 * @param montoCertificado
	 * @param certificado
	 * @throws BussinesException
	 */
	public static void isCertificadoValido(String numeroTarjeta, Long montoCertificado, CcTblcertificado certificado)
			throws BussinesException {

		final String tarjeta_token = "XXXXXXXXXXXXXXXX";
		final String monto_token = "YYY";

		if (certificado == null) {

			ErrorCatalog error = ErrorCatalog.NO_EXISTE_TARJETA;

			// Modificamos el msg a enviar
			String text = error.getMessage();

			text = StringUtils.replace(text, tarjeta_token, numeroTarjeta);
			text = StringUtils.replace(text, monto_token, String.valueOf(montoCertificado));

			error.setMessage(text);

			throw new BussinesException(error);
		} else if (!certificado.getAplicado().equals(AplicadoCatalog.NO.getValue())) {

			logger.debug("Aplicado [{}]", certificado.getAplicado());
			ErrorCatalog error = ErrorCatalog.TARJETA_VENDIDA;
			String text = error.getMessage();
			text = StringUtils.replace(text, tarjeta_token, numeroTarjeta);
			error.setMessage(text);

			throw new BussinesException(error);
		}

	}

	/**
	 * @see valida los parametros de entrada, si no son validos lanza una
	 *      exception
	 * @param numeroTarjeta
	 * @param montoCertificado
	 * @param idUsuario
	 * @throws BussinesException
	 */
	public static void parametrosValidos(String numeroTarjeta, long montoCertificado, String idUsuario)
			throws BussinesException {

		// Si el monto no es mayor a 0
		if (!(montoCertificado > 0))
			throw new BussinesException(ErrorCatalog.MONTO_MAYOR_0);
		// Si no s eenvio la tarjeta
		if (numeroTarjeta == null || numeroTarjeta.isEmpty())
			throw new BussinesException(ErrorCatalog.NUM_TARJETA_REQUERIDO);
		// El usuario es valido
		if (idUsuario == null || idUsuario.isEmpty())
			throw new BussinesException(ErrorCatalog.USUARIO_REQUERIDO);

	}

	/**
	 * @see valida los parametros de entrada
	 * @param numeroCertificado
	 * @param idUsuario
	 * @throws BussinesException
	 */
	public static void parametrosValidos(String numeroCertificado, String idUsuario) throws BussinesException {

		// Si no s eenvio la tarjeta
		if (numeroCertificado == null || numeroCertificado.isEmpty())
			throw new BussinesException(ErrorCatalog.NUM_CERTIFICADO_REQUERIDO);
		// El usuario es valido
		if (idUsuario == null || idUsuario.isEmpty())
			throw new BussinesException(ErrorCatalog.USUARIO_REQUERIDO);

	}

	/**
	 * @see Cambiamos el mensaje de error sustituyendo el valor de la exception
	 * @param exception
	 * @param error
	 */
	public static void setError(Exception exception, ErrorCatalog error) {

		final String error_token = "EEEEEEEEEE";

		String messageException = exception.getMessage();
		String messageError = error.getMessage();

		messageError = StringUtils.replace(messageError, error_token, messageException);

		error.setMessage(messageError);

	}

	/**
	 * @see Sustituye el valor del toket para los saldos
	 * @param sucess
	 * @param saldo
	 */
	public static void setMessage(ErrorCatalog error, float saldo) {

		final String saldo_token = "SSSSSRRRRR";
		String message = error.getMessage();

		message = StringUtils.replace(message, saldo_token, String.valueOf(saldo));

		error.setMessage(message);

	}

	/**
	 * @see Sustituye el valor del token para las fechas
	 * @param sucess
	 * @param saldo
	 */
	public static void setMessage(ErrorCatalog error, String fecha) {

		final String saldo_token = "FECHAEXP";
		String message = error.getMessage();

		message = StringUtils.replace(message, saldo_token, String.valueOf(fecha));

		error.setMessage(message);

	}

	/**
	 * @see Sustituye el valor del tiket para los saldos
	 * @param sucess
	 * @param saldo
	 */
	public static void setMessage(SucessCatalog sucess, float saldo) {

		final String saldo_token = "SSSSSRRRRR";
		String message = sucess.getMessage();

		message = StringUtils.replace(message, saldo_token, String.valueOf(saldo));

		sucess.setMessage(message);

	}

	/**
	 * @see valida que la tarjeta certificado y el certificado sean validos para
	 *      proceder a las actualizaciones
	 * @param tarjeta
	 * @param certificado
	 * @throws BussinesException
	 */
	public static void validaCertificados(CcTbltarjetacertificado tarjeta, CcTblcertificado certificado)
			throws BussinesException {

		// Si alguno es nulo no se puede hacer validacion
		if (tarjeta == null || certificado == null)
			throw new BussinesException(ErrorCatalog.NO_EXISTE_ACTIVACION_CERTIFICADO);

		float saldo = tarjeta.getValorrestante().floatValue();
		float saldoInicial = certificado.getValor().floatValue();
		String statusCertificado = certificado.getAplicado();
		String statusTarjeta = tarjeta.getEstatus();

		if (!(saldo >= saldoInicial))
			throw new BussinesException(ErrorCatalog.CERTIFICADO_UTILIZADO);

		if (statusTarjeta.trim().equals(EstatusCatalog.CADUCADO.getValue()))
			throw new BussinesException(ErrorCatalog.CERTIFICADO_CADUCADO);

		if (statusCertificado.trim().equals(AplicadoCatalog.SI.getValue()))
			throw new BussinesException(ErrorCatalog.CERTIFICADO_UTILIZADO);

	}

	/**
	 * @see Valida que el certificado sea valido para realizar operaciones, las
	 *      validaciones on que exista, este expirado o concelado
	 * @param certificado
	 * @throws BussinesException
	 */
	public static void validaCertificado(CcTbltarjetacertificado certificado) throws BussinesException {

		if (certificado == null)
			throw new BussinesException(ErrorCatalog.NO_EXISTE_ACTIVACION_TARJETA);

		String status = certificado.getEstatus();

		if (status.equals(EstatusCatalog.CADUCADO.getValue()))
			throw new BussinesException(ErrorCatalog.CERTIFICADO_EXPIRADO);

		if (status.equals(EstatusCatalog.CANCELADO.getValue()))
			throw new BussinesException(ErrorCatalog.CERTIFICADO_CANCELADO);

		// Esto es redundante aunque se coloca por si aparece un valor diferente
		// a 1,2,3
		if (!status.equals(EstatusCatalog.ACTIVO.getValue()))
			throw new BussinesException(ErrorCatalog.CERTIFICADO_CANCELADO);

	}

	/**
	 * @see condicion para revisar si tiene saldo suficiente
	 * @param saldo
	 * @param valor
	 * @return
	 */
	public static boolean tieneSaldoSuficiente(float saldo, float valor) {
		return saldo >= valor;
	}

	/**
	 * @see Obtiene el folio del movto con base al numero de certificado, este
	 *      metodo es legado al codigo del refactor
	 * @param numeroCertificado
	 * @return
	 */
	public static String getFolioMovimiento(String numeroCertificado) {
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyhhmmss");
		Date date = new Date();
		String folio = String
				.valueOf(numeroCertificado.substring(numeroCertificado.length() - 4, numeroCertificado.length()))
				+ sdf.format(date);
		return folio.trim();
	}

}
