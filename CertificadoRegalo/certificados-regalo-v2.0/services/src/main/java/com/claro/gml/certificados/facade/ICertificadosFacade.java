package com.claro.gml.certificados.facade;

import com.claro.transfer.certificados.exception.BussinesException;
import com.claro.transfer.certificados.request.MovimientoCertificadoTO;

public interface ICertificadosFacade {

	String activaTarjetaCertificado(String numeroTarjeta, long montoCertificado, String idUsuario)
			throws BussinesException;

	String cancelaTarjetaCertificado(String numeroCertificado, String idUsuario) throws BussinesException;

	String consultaSaldoTarjetaCertificado(String numeroCertificado) throws BussinesException;

	String consultaMovimientosCertificado(String numeroTarjeta) throws BussinesException;

	String aplicaCertificado(MovimientoCertificadoTO movimientoCertificadoTO) throws BussinesException;

	String cancelaAplicaCertificado(String folio, String idUsuario, String idpuntoVta, String referencia)
			throws BussinesException;

}