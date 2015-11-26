/*
 * Decompiled with CFR 0_102.
 */
package com.claro.transfer.certificados;

public class MovimientoCertificadoTO {
	private String numeroCertificado;
	private String numeroTarjeta;
	private String idMotivo;
	private String idUsuario;
	private String puntoVenta;
	private String fechaOperacion;
	private String estatus;
	private float valorAplicado;
	private float valorAnterior;
	private float valorRestante;
	private String referencia;
	private String idMensaje;
	private String mensaje;

	public String getNumeroCertificado() {
		return this.numeroCertificado;
	}

	public void setNumeroCertificado(String numeroCertificado) {
		this.numeroCertificado = numeroCertificado;
	}

	public String getNumeroTarjeta() {
		return this.numeroTarjeta;
	}

	public void setNumeroTarjeta(String numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}

	public String getIdMotivo() {
		return this.idMotivo;
	}

	public void setIdMotivo(String idMotivo) {
		this.idMotivo = idMotivo;
	}

	public String getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getPuntoVenta() {
		return this.puntoVenta;
	}

	public void setPuntoVenta(String puntoVenta) {
		this.puntoVenta = puntoVenta;
	}

	public String getFechaOperacion() {
		return this.fechaOperacion;
	}

	public void setFechaOperacion(String fechaOperacion) {
		this.fechaOperacion = fechaOperacion;
	}

	public String getEstatus() {
		return this.estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	public float getValorAplicado() {
		return this.valorAplicado;
	}

	public void setValorAplicado(float valorAplicado) {
		this.valorAplicado = valorAplicado;
	}

	public float getValorAnterior() {
		return this.valorAnterior;
	}

	public void setValorAnterior(float valorAnterior) {
		this.valorAnterior = valorAnterior;
	}

	public float getValorRestante() {
		return this.valorRestante;
	}

	public void setValorRestante(float valorRestante) {
		this.valorRestante = valorRestante;
	}

	public String getReferencia() {
		return this.referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getIdMensaje() {
		return this.idMensaje;
	}

	public void setIdMensaje(String idMensaje) {
		this.idMensaje = idMensaje;
	}

	public String getMensaje() {
		return this.mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
}
