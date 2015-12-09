package com.claro.transfer.certificados.utils;

import java.util.List;

import com.claro.transfer.certificados.constants.ErrorCatalog;
import com.claro.transfer.certificados.constants.SucessCatalog;
import com.claro.transfer.certificados.response.ActivaTarjeta;
import com.claro.transfer.certificados.response.AplicaTarjetaCertificado;
import com.claro.transfer.certificados.response.CancelaTarjetaCertificado;
import com.claro.transfer.certificados.response.ConsultaMovimientosCertificado;
import com.claro.transfer.certificados.response.ConsultaSaldoCertificado;

public class StringOutputTransform {

	private static String SEPARATOR = "|";

	public static String toString(ActivaTarjeta tarjeta) {

		StringBuilder builder = new StringBuilder();

		builder.append(value(tarjeta.getNumeroTarjeta()));
		builder.append(SEPARATOR);
		builder.append(value(tarjeta.getNumeroCertificado()));
		builder.append(SEPARATOR);
		builder.append(value(tarjeta.getMontoCertificado()));
		builder.append(SEPARATOR);
		builder.append(value(tarjeta.getEstatus()));
		builder.append(SEPARATOR);
		builder.append(value(tarjeta.getFechaActivacion()));
		builder.append(SEPARATOR);
		builder.append(value(tarjeta.getFechaExpiracion()));

		return builder.toString();
	}

	public static String toString(ErrorCatalog error) {
		StringBuilder builder = new StringBuilder();

		builder.append(value(error.getCode()));
		builder.append(SEPARATOR);
		builder.append(value(error.getMessage()));

		return builder.toString();

	}

	public static String toString(SucessCatalog sucess) {
		StringBuilder builder = new StringBuilder();

		builder.append(value(sucess.getCode()));
		builder.append(SEPARATOR);
		builder.append(value(sucess.getMessage()));

		return builder.toString();

	}

	public static String toString(CancelaTarjetaCertificado tarjeta) {
		StringBuilder builder = new StringBuilder();

		builder.append(value(tarjeta.getIdMensaje()));
		builder.append(SEPARATOR);
		builder.append(value(tarjeta.getMensaje()));

		return builder.toString();
	}

	public static String toString(AplicaTarjetaCertificado tarjeta) {
		StringBuilder builder = new StringBuilder();

		builder.append(value(tarjeta.getIdMensaje()));
		builder.append(SEPARATOR);
		builder.append(value(tarjeta.getMensaje()));

		return builder.toString();
	}
	
	
	
	
	public static String toString(ConsultaSaldoCertificado saldo) {

		StringBuilder builder = new StringBuilder();

		builder.append(value(saldo.getNumeroTarjeta()));
		builder.append(SEPARATOR);
		builder.append(value(saldo.getNumeroCertificado()));
		builder.append(SEPARATOR);
		builder.append(value(saldo.getSaldo()));
		builder.append(SEPARATOR);
		builder.append(value(saldo.getFechaActivacion()));
		builder.append(SEPARATOR);
		builder.append(value(saldo.getFechaExpiracion()));
		builder.append(SEPARATOR);
		builder.append(value(saldo.getEstatus()));
		builder.append(SEPARATOR);
		builder.append(value(saldo.getIdMensaje()));
		builder.append(SEPARATOR);
		builder.append(value(saldo.getMensaje()));

		return builder.toString();

	}

	/**
	 * @see Obtiene la representacion en cadena de un arreglo de
	 *      ConsultaMovimientosCertificado
	 * @param movtos
	 * @return
	 */
	public static String toString(List<ConsultaMovimientosCertificado> movtos) {

		StringBuffer buffer = new StringBuffer();

		for (ConsultaMovimientosCertificado movto : movtos) {
			String result = toString(movto);
			buffer.append(result);
		}

		return buffer.toString();
	}

	/**
	 * @see Obtiene la representacion en cadena separado por | del registro de
	 *      ConsultaMovimientosCertificado
	 * @param movto
	 * @return
	 */
	public static String toString(ConsultaMovimientosCertificado movto) {

		StringBuilder builder = new StringBuilder();

		builder.append(value(movto.getNumeroTarjeta()));
		builder.append(SEPARATOR);
		builder.append(value(movto.getNumeroCertificado()));
		builder.append(SEPARATOR);
		builder.append(value(movto.getIdMotivo()));
		builder.append(SEPARATOR);
		builder.append(value(movto.getIdUsuario()));
		builder.append(SEPARATOR);
		builder.append(value(movto.getPuntoVenta()));
		builder.append(SEPARATOR);
		builder.append(value(movto.getFechaOperacion()));
		builder.append(SEPARATOR);
		builder.append(value(movto.getEstatus()));
		builder.append(SEPARATOR);
		builder.append(value(movto.getValorAplicado()));
		builder.append(SEPARATOR);
		builder.append(value(movto.getValorAnterior()));
		builder.append(SEPARATOR);
		builder.append(value(movto.getValorRestante()));
		builder.append(SEPARATOR);
		builder.append(value(movto.getReferencia()));
		builder.append(SEPARATOR);

		return builder.toString();

	}

	public static String value(Object input) {
		return (input == null) ? "" : input.toString();
	}

}
