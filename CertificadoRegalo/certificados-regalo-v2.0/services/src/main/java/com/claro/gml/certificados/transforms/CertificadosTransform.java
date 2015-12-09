package com.claro.gml.certificados.transforms;

import java.io.Serializable;
import java.math.BigDecimal;

import com.claro.gml.persistence.model.CcTblmovimientocertificado;
import com.claro.gml.persistence.model.CcTbltarjetacertificado;
import com.claro.transfer.certificados.constants.SucessCatalog;
import com.claro.transfer.certificados.response.ConsultaMovimientosCertificado;
import com.claro.transfer.certificados.response.ConsultaSaldoCertificado;

public class CertificadosTransform {

	/**
	 * @see transformador de CcTblmovimientocertificado a
	 *      MovimientoCertificadoTO
	 * @param movto
	 * @return
	 */
	public static ConsultaMovimientosCertificado transform(CcTblmovimientocertificado movto) {

		// Se planeaba usar beanutils pero el nombre de los atributos no
		// corresponde

		String numeroCertificado = movto.getId().getNumcertificado();
		BigDecimal valorAplicado = movto.getValoraplicado();
		String idUsuario = movto.getIdusuario();
		String puntoVenta = movto.getId().getPuntovta();
		String referencia = movto.getReferencia();

		String numeroTarjeta = movto.getId().getNumtarjeta();
		String idMotivo = movto.getIdmovto();

		// TODO Cambiar este tipo
		Serializable fechaOperacion = movto.getId().getFechaoper();
		String estatus = movto.getEstatus();

		BigDecimal valorAnterior = movto.getValoranterior();
		BigDecimal valorRestante = movto.getValorrestante();

		ConsultaMovimientosCertificado newMovto = new ConsultaMovimientosCertificado();

		newMovto.setNumeroCertificado(numeroCertificado);
		newMovto.setValorAplicado(valorAplicado.floatValue());
		newMovto.setIdUsuario(idUsuario);
		newMovto.setPuntoVenta(puntoVenta);
		newMovto.setReferencia(referencia);
		newMovto.setNumeroTarjeta(numeroTarjeta);
		newMovto.setIdMotivo(idMotivo);

		// TODO cambiar este tipo
		newMovto.setFechaOperacion(fechaOperacion.toString());
		newMovto.setEstatus(estatus);
		newMovto.setValorAnterior(valorAnterior.floatValue());
		newMovto.setValorRestante(valorRestante.floatValue());

		return newMovto;
	}

	/**
	 * @see transform de CcTbltarjetacertificado a ConsultaSaldoCertificado
	 * @param saldo
	 * @return
	 */
	public static ConsultaSaldoCertificado transform(CcTbltarjetacertificado saldo) {

		ConsultaSaldoCertificado response = new ConsultaSaldoCertificado();

		String numeroTarjeta = saldo.getNumtarjeta();
		String numeroCertificado = saldo.getNumcertificado();
		float saldoTarjeta = saldo.getValorrestante().floatValue();
		String fechaActivacion = String.valueOf(saldo.getFechaactivacion());
		String fechaExpiracion = String.valueOf(saldo.getFechaexpiracion());
		String status = saldo.getEstatus();

		String idMensaje = SucessCatalog.PROCESO_EXITOSO.getCode();
		String message = SucessCatalog.PROCESO_EXITOSO.getMessage();

		response.setEstatus(status);
		response.setFechaActivacion(fechaActivacion);
		response.setFechaExpiracion(fechaExpiracion);
		response.setIdMensaje(Integer.valueOf(idMensaje));
		response.setMensaje(message);
		response.setNumeroCertificado(numeroCertificado);
		response.setNumeroTarjeta(numeroTarjeta);
		response.setSaldo(saldoTarjeta);

		return response;
	}

}
