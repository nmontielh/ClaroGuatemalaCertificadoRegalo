package com.claro.gml.certificados.composite.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.claro.gml.certificados.services.CertificadosService;
import com.claro.gml.certificados.transforms.CertificadosTransform;
import com.claro.gml.certificados.utils.CertificadosUtils;
import com.claro.gml.certificados.utils.DateUtilsClaro;
import com.claro.gml.persistence.model.CcCtlusuarios;
import com.claro.gml.persistence.model.CcTblcertificado;
import com.claro.gml.persistence.model.CcTblmovimientocertificado;
import com.claro.gml.persistence.model.CcTbltarjetacertificado;
import com.claro.transfer.certificados.constants.AplicadoCatalog;
import com.claro.transfer.certificados.constants.ErrorCatalog;
import com.claro.transfer.certificados.constants.EstatusCatalog;
import com.claro.transfer.certificados.constants.SucessCatalog;
import com.claro.transfer.certificados.exception.BussinesException;
import com.claro.transfer.certificados.request.MovimientoCertificadoTO;
import com.claro.transfer.certificados.response.ActivaTarjeta;
import com.claro.transfer.certificados.response.AplicaTarjetaCertificado;
import com.claro.transfer.certificados.response.CancelaTarjetaCertificado;
import com.claro.transfer.certificados.response.ConsultaMovimientosCertificado;
import com.claro.transfer.certificados.response.ConsultaSaldoCertificado;
import com.claro.transfer.certificados.utils.StringOutputTransform;

@Service
public class CertificadosBussinesService {

	private Logger log = LoggerFactory.getLogger(CertificadosBussinesService.class);

	@Autowired
	private CertificadosService service;

	/**
	 * @see Metodo para activar tarjetas
	 * @param numeroTarjeta
	 * @param montoCertificado
	 * @param idUsuario
	 * @return
	 * @throws BussinesException
	 */
	public String activaTarjetaCertificado(String numeroTarjeta, long montoCertificado, String idUsuario)
			throws BussinesException {

		// Validamos los parametros de entrada, si hay alguna anomalia lanza una
		// exception
		CertificadosUtils.parametrosValidos(numeroTarjeta, montoCertificado, idUsuario);

		// Nos traemos el usuario para obtener la region
		// El codigo viejo lo consulta por numTarjeta pero no hay info en esta
		// tabla con este dato
		CcCtlusuarios usuario = service.findUsuario(idUsuario);

		// Valida la existencia de la region o lanza exception
		CertificadosUtils.regionExist(usuario);

		String region = usuario.getId().getRegion();

		// Nos traemos el certificado
		CcTblcertificado certificado = service.findCertificado(numeroTarjeta, montoCertificado, region);

		// Valida que el certificado sea valido, de lo contrario lanza exception
		CertificadosUtils.isCertificadoValido(numeroTarjeta, montoCertificado, certificado);

		String numeroCertificado = certificado.getNumcertificado();

		// Creamos la tarjeta
		ActivaTarjeta activacion = service.saveTarjetaCertificado(numeroCertificado, idUsuario, numeroTarjeta,
				montoCertificado);

		// Actualizamos el certificado
		service.updateCertificado(certificado, idUsuario);

		String resultado = StringOutputTransform.toString(activacion);

		return resultado;
	}

	/**
	 * @see metodo que se utiliza para cancelar la venta de un certificado
	 * @param numeroCertificado
	 * @param idUsuario
	 * @return
	 * @throws BussinesException
	 */
	public String cancelaTarjetaCertificado(String numeroCertificado, String idUsuario) throws BussinesException {

		// Validamos los parametros de entrada, si hay alguna anomalia lanza una
		// exception
		CertificadosUtils.parametrosValidos(numeroCertificado, idUsuario);

		// Consultamos la tarjeta certificado y el certificado
		CcTbltarjetacertificado tarjeta = service.findTarjetaCertificado(numeroCertificado);
		CcTblcertificado certificado = service.findCertificadoByNumCertificado(numeroCertificado);

		// Validamos los certificados, si no es valido manda una exception
		CertificadosUtils.validaCertificados(tarjeta, certificado);

		// TODO revisar si esta aplica, segun yo no
		/*
		 * CcTbltarjetacertificado tarjeta2 =
		 * service.findTarjetaCertificadoByNumTarjeta(numeroCertificado,
		 * tarjeta.getNumtarjeta());
		 */

		// cancelamos la tarjeta de certificado
		service.updateCanceladoTarjetaCertificados(idUsuario, tarjeta);

		// Cancelamos el certificado
		service.updateCertificado(idUsuario, certificado);

		// Generamos la respuesta para enviar como OK
		CancelaTarjetaCertificado cancela = new CancelaTarjetaCertificado(SucessCatalog.CANCELA_ACTIVACION_TARJETA);

		String resultado = StringOutputTransform.toString(cancela);

		return resultado;
	}

	/**
	 * @see Metodo para visualizar el estado actual de un certificado
	 * @param numeroCertificado
	 * @return
	 * @throws BussinesException
	 */
	public String consultaSaldoTarjetaCertificado(String numeroCertificado) throws BussinesException {

		if (numeroCertificado != null && !numeroCertificado.isEmpty())
			throw new BussinesException(ErrorCatalog.CERTIFICADO_NO_VALIDO);

		CcTbltarjetacertificado certificado = service.findTarjetaCertificado(numeroCertificado);

		if (certificado == null)
			throw new BussinesException(ErrorCatalog.CERTIFICADO_NO_VALIDO);

		// Transformador de certificado
		ConsultaSaldoCertificado saldo = CertificadosTransform.transform(certificado);

		String resultado = StringOutputTransform.toString(saldo);

		return resultado;
	}

	/**
	 * @see Metodo para listar los movimientos de los certificados
	 * @param numeroTarjeta
	 * @return
	 * @throws BussinesException
	 */
	public String consultaMovimientosCertificado(String numeroTarjeta) throws BussinesException {

		// Validamos que sea valido la tarjeta
		if (numeroTarjeta != null && !numeroTarjeta.isEmpty())
			throw new BussinesException(ErrorCatalog.NUM_TARJETA_REQUERIDO);

		// nos traemos el movto
		List<ConsultaMovimientosCertificado> resultado = service.findMovtoCert(numeroTarjeta);

		if (resultado.isEmpty())
			throw new BussinesException(ErrorCatalog.NO_EXISTEN_MOVTOS_CERT);

		// Generamos cadena de respuesta
		String respuesta = StringOutputTransform.toString(resultado);

		return respuesta;
	}

	/**
	 * @see se utiliza para usar el valor del certificado como medio de pago
	 * @param movimientoCertificadoTO
	 * @return
	 * @throws BussinesException
	 */
	public String aplicaCertificado(MovimientoCertificadoTO movimientoCertificadoTO) throws BussinesException {

		String numCertificado = movimientoCertificadoTO.getNumeroCertificado();

		CcTbltarjetacertificado tarjeta = service.findTarjetaCertificado(numCertificado);

		// Validamos que sea correcto el estatus de lo contrario lanzamos una
		// exception
		CertificadosUtils.validaCertificado(tarjeta);

		// Obtenemos las fechas
		Date fechaExpiracion = DateUtilsClaro.getDate(tarjeta.getFechaactivacion());
		Date hoy = DateUtilsClaro.now();

		// Obtenemos los saldos (Este code esta duplicado en el saveMovto)
		float saldo = tarjeta.getValorrestante().floatValue();
		float valorAplicado = movimientoCertificadoTO.getValorAplicado();
		float valorRestante = saldo - valorAplicado;

		String response = null;

		if (!DateUtilsClaro.isLessOrEqual(hoy, fechaExpiracion)) {
			service.updateTarjetaCertificado(tarjeta, movimientoCertificadoTO.getIdUsuario());
			ErrorCatalog error = ErrorCatalog.CERTIFICADO_EXPIRADO;
			CertificadosUtils.setMessage(error, DateUtilsClaro.toFormatString(fechaExpiracion, "ddMMyyyy"));

		} else if (CertificadosUtils.tieneSaldoSuficiente(saldo, valorAplicado)) {

			service.saveMovtoCertificado(tarjeta, movimientoCertificadoTO);

			service.updateTarjetaCertificado(tarjeta, valorRestante, movimientoCertificadoTO.getIdUsuario());

			SucessCatalog sucess = SucessCatalog.APLICA_CERT_EXITOSO;
			CertificadosUtils.setMessage(sucess, valorRestante);

			AplicaTarjetaCertificado result = new AplicaTarjetaCertificado(sucess);
			response = StringOutputTransform.toString(result);

		} else {
			ErrorCatalog error = ErrorCatalog.SALDO_INSUFICIENTE;
			CertificadosUtils.setMessage(error, saldo);
			throw new BussinesException(error);

		}

		return response;
	}

	/**
	 * @see cancela el momento de una trasaccion en especifico
	 * @param folio
	 * @param idUsuario
	 * @param idpuntoVta
	 * @param referencia
	 * @return
	 * @throws BussinesException
	 */
	public String cancelaAplicaCertificado(String folio, String idUsuario, String idpuntoVta, String referencia)
			throws BussinesException {

		// 1. Nos traemos el movto por el folio
		CcTblmovimientocertificado movto = service.findMovtoCertificadoByFolio(folio);

		if (movto == null)
			throw new BussinesException(ErrorCatalog.NO_EXISTEN_MOVTOS_FOLIO);

		// 2. Nos traemos la tarjeta certificado
		String numeroTarjeta = movto.getId().getNumtarjeta();
		CcTbltarjetacertificado tarjeta = service.findTarjetaCertificadoByNumTarjeta(numeroTarjeta);

		if (tarjeta == null)
			throw new BussinesException(ErrorCatalog.NO_EXISTE_INFO);

		// Obtenemos los dias entre la fecha operacion y hoy
		int dias = DateUtilsClaro.getDaysFromNow(movto.getId().getFechaoper());

		// Revisamos si tiene mas de un dia el movto
		if (dias > 1)
			throw new BussinesException(ErrorCatalog.MOVTO_MAS_1_DIA);

		// Si no esta activo
		if (!tarjeta.getEstatus().equals(EstatusCatalog.ACTIVO.getValue()))
			throw new BussinesException(ErrorCatalog.CERTIFICADO_NO_ACTIVO);

		if (movto.getEstatus().equals(AplicadoCatalog.MOVTO_CANCELADO.getValue()))
			throw new BussinesException(ErrorCatalog.MOVTO_CANCELADO);

		// Despues de todas las validaciones de negocio, realizamos la
		// cancelacion
		service.updateMovtoCertificadoCancelado(movto, idUsuario);

		// Calculamos el valor restante
		float saldoTarjeta = tarjeta.getValorrestante().floatValue();
		float saldoMovto = movto.getValoraplicado().floatValue();
		float valorRestante = saldoTarjeta + saldoMovto;

		// Actualizamos la tarjeta certificado
		service.updateTarjetaCertificado(tarjeta, valorRestante, idUsuario);

		// Creamos un movimientos
		service.saveMovtoCertificado(tarjeta, movto, folio, idUsuario, idpuntoVta, referencia, valorRestante);

		CancelaTarjetaCertificado cancela = new CancelaTarjetaCertificado(SucessCatalog.MOVTO_CANCEL_OK);

		String resultado = StringOutputTransform.toString(cancela);

		return resultado;
	}

}
