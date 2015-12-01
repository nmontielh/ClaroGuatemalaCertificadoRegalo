package com.claro.ws.service.impl;

import com.claro.exception.ClaroException;
import com.claro.transfer.certificados.MovimientoCertificadoTO;
import com.claro.ws.service.ClaroService;

public class ClaroServiceImp implements ClaroService {

	@Override
	public String activaTarjetaCertificado(String numeroTarjeta, long montoCertificado, String idUsuario)
			throws ClaroException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String aplicaCertificado(MovimientoCertificadoTO movimientoCertificadoTO) throws ClaroException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String cancelaTarjetaCertificado(String numeroCertificado, String idUsuario) throws ClaroException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String cancelaAplicaCertificado(String folio, String idUsuario, String idpuntoVta, String referencia)
			throws ClaroException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String consultaSaldoCertificado(String numeroTarjeta) throws ClaroException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String consultaMovimientosCertificado(String numeroTarjeta) throws ClaroException {
		// TODO Auto-generated method stub
		return null;
	}

}
