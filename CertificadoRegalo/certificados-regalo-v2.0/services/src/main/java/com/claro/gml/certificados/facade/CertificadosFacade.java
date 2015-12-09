package com.claro.gml.certificados.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.claro.gml.certificados.composite.service.CertificadosBussinesService;
import com.claro.transfer.certificados.exception.BussinesException;
import com.claro.transfer.certificados.request.MovimientoCertificadoTO;

@Service
public class CertificadosFacade implements ICertificadosFacade {

	@Autowired
	private CertificadosBussinesService bussines;

	/* (non-Javadoc)
	 * @see com.claro.gml.certificados.facade.ICertificadosFacade#activaTarjetaCertificado(java.lang.String, long, java.lang.String)
	 */
	@Override
	public String activaTarjetaCertificado(String numeroTarjeta, long montoCertificado, String idUsuario)
			throws BussinesException {
		return bussines.activaTarjetaCertificado(numeroTarjeta, montoCertificado, idUsuario);
	}

	/* (non-Javadoc)
	 * @see com.claro.gml.certificados.facade.ICertificadosFacade#cancelaTarjetaCertificado(java.lang.String, java.lang.String)
	 */
	@Override
	public String cancelaTarjetaCertificado(String numeroCertificado, String idUsuario) throws BussinesException {
		return bussines.cancelaTarjetaCertificado(numeroCertificado, idUsuario);
	}

	/* (non-Javadoc)
	 * @see com.claro.gml.certificados.facade.ICertificadosFacade#consultaSaldoTarjetaCertificado(java.lang.String)
	 */
	@Override
	public String consultaSaldoTarjetaCertificado(String numeroCertificado) throws BussinesException {
		return bussines.consultaSaldoTarjetaCertificado(numeroCertificado);
	}

	/* (non-Javadoc)
	 * @see com.claro.gml.certificados.facade.ICertificadosFacade#consultaMovimientosCertificado(java.lang.String)
	 */
	@Override
	public String consultaMovimientosCertificado(String numeroTarjeta) throws BussinesException {
		return bussines.consultaMovimientosCertificado(numeroTarjeta);
	}

	/* (non-Javadoc)
	 * @see com.claro.gml.certificados.facade.ICertificadosFacade#aplicaCertificado(com.claro.transfer.certificados.request.MovimientoCertificadoTO)
	 */
	@Override
	public String aplicaCertificado(MovimientoCertificadoTO movimientoCertificadoTO) throws BussinesException {
		return bussines.aplicaCertificado(movimientoCertificadoTO);
	}

	/* (non-Javadoc)
	 * @see com.claro.gml.certificados.facade.ICertificadosFacade#cancelaAplicaCertificado(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public String cancelaAplicaCertificado(String folio, String idUsuario, String idpuntoVta, String referencia)
			throws BussinesException {
		return bussines.cancelaAplicaCertificado(folio, idUsuario, idpuntoVta, referencia);
	}

}
