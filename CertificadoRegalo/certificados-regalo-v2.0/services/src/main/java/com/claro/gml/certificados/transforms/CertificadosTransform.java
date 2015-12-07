package com.claro.gml.certificados.transforms;

import java.io.Serializable;
import java.math.BigDecimal;

import com.claro.gml.persistence.model.CcTblmovimientocertificado;
import com.claro.transfer.certificados.response.ConsultaMovimientosCertificado;

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

}
