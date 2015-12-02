
package com.claro.ws.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import com.claro.exception.ClaroServiceException;
import com.claro.transfer.certificados.request.MovimientoCertificadoTO;

@WebService(targetNamespace = "http://service.ws.claro.com")
public interface ICertificadosEndpoint {

	/**
	 * @see Método que se utiliza cuando se realiza el quemado del certificado,
	 *      se activa el certificado y se le asigna una vigencia de 6 meses a
	 *      partir de la fecha de venta
	 * @param numeroTarjeta
	 *            : Numero de la tarjeta a activar
	 * @param montoCertificado
	 *            : Valor del certificado a activar
	 * @param idUsuario
	 *            : Usuario que hace la petición de activar la tarjeta
	 * @return String (NumeroTarjeta | NumeroCertificado | MontoCertificado |
	 *         Estatus | FechaActivacion |getFechaExpiracion)
	 * @throws ClaroServiceException
	 */
	@WebMethod
	@WebResult(name = "activaTarjetaCertificadoReturn")
	public String activaTarjetaCertificado(@WebParam(name = "numeroTarjeta") String numeroTarjeta,
			@WebParam(name = "montoCertificado") long montoCertificado, @WebParam(name = "idUsuario") String idUsuario)
					throws ClaroServiceException;

	/**
	 * @see Metodo que se utiliza para cancelar la venta de un certificado, una
	 *      vez que en cancelado no puede ser reciclado.
	 * @param numeroCertificado
	 *            : Numero del certificado
	 * @param idUsuario
	 *            : Numero de empleado que realiza la acción
	 * @return String (dMensaje | mensaje de error)
	 * @throws ClaroServiceException
	 */
	@WebMethod
	@WebResult(name = "cancelaTarjetaCertificadoReturn")
	public String cancelaTarjetaCertificado(@WebParam(name = "numeroCertificado") String numeroCertificado,
			@WebParam(name = "idUsuario") String idUsuario) throws ClaroServiceException;

	/**
	 * 
	 * @param numeroTarjeta
	 * @return String con el siguiente formato NumeroTarjeta | NumeroCertificado
	 *         | IdMotivo | IdUsuario | PuntoVenta | FechaOperacion | Estatus |
	 *         ValorAplicado | ValorAnterior | ValorRestante | Referencia |
	 * @throws ClaroServiceException
	 */
	@WebMethod
	@WebResult(name = "consultaSaldoCertificadoReturn")
	public String consultaSaldoTarjetaCertificado(@WebParam(name = "numeroCertificado") String numeroCertificado)
			throws ClaroServiceException;

	/**
	 * 
	 * @param numeroTarjeta
	 * @return
	 * @throws ClaroServiceException
	 */
	@WebMethod
	@WebResult(name = "consultaMovimientosCertificadoReturn")
	public String consultaMovimientosCertificado(@WebParam(name = "numeroTarjeta") String numeroTarjeta)
			throws ClaroServiceException;

	/**
	 * 
	 * @param movimientoCertificadoTO
	 * @return
	 * @throws ClaroServiceException
	 */
	@WebMethod
	@WebResult(name = "aplicaCertificadoReturn")
	public String aplicaCertificado(
			@WebParam(name = "movimientoCertificadoTO") MovimientoCertificadoTO movimientoCertificadoTO)
					throws ClaroServiceException;

	/**
	 * 
	 * @param folio
	 * @param idUsuario
	 * @param idpuntoVta
	 * @param referencia
	 * @return
	 * @throws ClaroServiceException
	 */
	@WebMethod
	@WebResult(name = "cancelaAplicaCertificadoReturn")
	public String cancelaAplicaCertificado(@WebParam(name = "folio") String folio,
			@WebParam(name = "idUsuario") String idUsuario, @WebParam(name = "idpuntoVta") String idpuntoVta,
			@WebParam(name = "referencia") String referencia) throws ClaroServiceException;

}
