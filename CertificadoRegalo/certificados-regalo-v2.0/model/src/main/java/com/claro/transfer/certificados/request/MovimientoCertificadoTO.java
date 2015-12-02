/*
 * Decompiled with CFR 0_102.
 */
package com.claro.transfer.certificados.request;

import java.io.Serializable;

public class MovimientoCertificadoTO implements Serializable {

	private static final long serialVersionUID = -1333853279360001420L;

	private String numeroCertificado;
	private float valorAplicado;
	private String idUsuario;
	private String puntoVenta;
	private String referencia;

	public MovimientoCertificadoTO() {
		super();
	}

	public MovimientoCertificadoTO(String numeroCertificado, float valorAplicado, String idUsuario, String puntoVenta,
			String referencia) {
		super();
		this.numeroCertificado = numeroCertificado;
		this.valorAplicado = valorAplicado;
		this.idUsuario = idUsuario;
		this.puntoVenta = puntoVenta;
		this.referencia = referencia;
	}

	public String getNumeroCertificado() {
		return numeroCertificado;
	}

	public void setNumeroCertificado(String numeroCertificado) {
		this.numeroCertificado = numeroCertificado;
	}

	public float getValorAplicado() {
		return valorAplicado;
	}

	public void setValorAplicado(float valorAplicado) {
		this.valorAplicado = valorAplicado;
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getPuntoVenta() {
		return puntoVenta;
	}

	public void setPuntoVenta(String puntoVenta) {
		this.puntoVenta = puntoVenta;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	@Override
	public String toString() {
		return "MovimientoCertificadoTO [numeroCertificado=" + numeroCertificado + ", valorAplicado=" + valorAplicado
				+ ", idUsuario=" + idUsuario + ", puntoVenta=" + puntoVenta + ", referencia=" + referencia + "]";
	}

}
