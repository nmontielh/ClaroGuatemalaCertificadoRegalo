package com.claro.ws.service.impl;

import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.claro.exception.ClaroServiceException;
import com.claro.exception.ExceptionUtils;
import com.claro.gml.certificados.facade.CertificadosFacade;
import com.claro.transfer.certificados.exception.BussinesException;
import com.claro.transfer.certificados.request.MovimientoCertificadoTO;
import com.claro.ws.service.ICertificadosEndpoint;

@WebService(name = "CertificadosService", serviceName = "CertificadosWebService", portName = "CertificadosServicePort", endpointInterface = "com.claro.ws.service.ICertificadosEndpoint")
public class CertificadosEndpoint extends SpringBeanAutowiringSupport implements ICertificadosEndpoint {

	private static Logger log = LoggerFactory.getLogger(CertificadosEndpoint.class);

	@Autowired
	private CertificadosFacade facade;

	@Override
	public String activaTarjetaCertificado(String numeroTarjeta, long montoCertificado, String idUsuario)
			throws ClaroServiceException {

		log.debug("Request : tarjeta [{}] monto :[{}] idUsuario :[{}]", numeroTarjeta, montoCertificado, idUsuario);

		String resultado = null;

		try {
			resultado = facade.activaTarjetaCertificado(numeroTarjeta, montoCertificado, idUsuario);
		} catch (BussinesException e) {

			ExceptionUtils.propagateBussinesException(e);

		} catch (Throwable ex) {
			ExceptionUtils.propagateUncheckedException(ex);
		}
		return resultado;
	}

	@Override
	public String cancelaTarjetaCertificado(String numeroCertificado, String idUsuario) throws ClaroServiceException {

		log.debug(" Request : certificado : [{}] idUsuario : [{}]", numeroCertificado, idUsuario);
		String resultado = null;
		try {
			resultado = facade.cancelaTarjetaCertificado(numeroCertificado, idUsuario);
		} catch (BussinesException e) {

			ExceptionUtils.propagateBussinesException(e);

		} catch (Throwable ex) {
			ExceptionUtils.propagateUncheckedException(ex);
		}
		return resultado;
	}

	@Override
	public String consultaSaldoTarjetaCertificado(String numeroCertificado) throws ClaroServiceException {

		log.debug(" Request : numeroCertificado [{}]", numeroCertificado);

		String resultado = null;
		try {
			resultado = facade.consultaSaldoTarjetaCertificado(numeroCertificado);
		} catch (BussinesException e) {

			ExceptionUtils.propagateBussinesException(e);

		} catch (Throwable ex) {
			ExceptionUtils.propagateUncheckedException(ex);
		}
		return resultado;
	}

	@Override
	public String consultaMovimientosCertificado(String numeroTarjeta) throws ClaroServiceException {

		log.debug(" Request : numeroTarjeta [{}]", numeroTarjeta);

		String resultado = null;
		try {
			resultado = facade.consultaMovimientosCertificado(numeroTarjeta);
		} catch (BussinesException e) {

			ExceptionUtils.propagateBussinesException(e);

		} catch (Throwable ex) {
			ExceptionUtils.propagateUncheckedException(ex);
		}
		return resultado;

	}

	@Override
	public String aplicaCertificado(MovimientoCertificadoTO movimientoCertificadoTO) throws ClaroServiceException {
		log.debug(" Request : movto [{}]", movimientoCertificadoTO);

		String resultado = null;
		try {
			resultado = facade.aplicaCertificado(movimientoCertificadoTO);
		} catch (BussinesException e) {

			ExceptionUtils.propagateBussinesException(e);

		} catch (Throwable ex) {
			ExceptionUtils.propagateUncheckedException(ex);
		}
		return resultado;
	}

	@Override
	public String cancelaAplicaCertificado(String folio, String idUsuario, String idpuntoVta, String referencia)
			throws ClaroServiceException {
		log.debug(" Request : folio [{}] idUsuario [{}] idPtoVenta [{}] referencia [{}]", folio, idUsuario, idpuntoVta,
				referencia);

		String resultado = null;
		try {
			resultado = facade.cancelaAplicaCertificado(folio, idUsuario, idpuntoVta, referencia);
		} catch (BussinesException e) {

			ExceptionUtils.propagateBussinesException(e);

		} catch (Throwable ex) {
			ExceptionUtils.propagateUncheckedException(ex);
		}
		return resultado;
	}

}
