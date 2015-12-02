package com.claro.gml.certificados.facade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.claro.gml.certificados.services.CertificadosService;
import com.claro.transfer.certificados.exception.BussinesException;
import com.claro.transfer.certificados.request.MovimientoCertificadoTO;

@Service
public class CertificadosFacade {

	private Logger log = LoggerFactory.getLogger(CertificadosFacade.class);

	@Autowired
	private CertificadosService service;

	public String activaTarjetaCertificado(String numeroTarjeta, long montoCertificado, String idUsuario)
			throws BussinesException {

		return null;
	}

	public String cancelaTarjetaCertificado(String numeroCertificado, String idUsuario) throws BussinesException {

		return null;
	}

	public String consultaSaldoTarjetaCertificado(String numeroCertificado) throws BussinesException {

		return null;
	}

	public String consultaMovimientosCertificado(String numeroTarjeta) throws BussinesException {

		return null;
	}

	public String aplicaCertificado(MovimientoCertificadoTO movimientoCertificadoTO) throws BussinesException {

		return null;
	}

	public String cancelaAplicaCertificado(String folio, String idUsuario, String idpuntoVta, String referencia)
			throws BussinesException {
		
		return null;
	}

}
