package com.claro.transfer.certificados.response;

import java.io.Serializable;

public class ConsultaMovimientosCertificado implements Serializable {

	private static final long serialVersionUID = 1L;

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

	public String getNumeroCertificado() {
		return numeroCertificado;
	}

	public void setNumeroCertificado(String numeroCertificado) {
		this.numeroCertificado = numeroCertificado;
	}

	public String getNumeroTarjeta() {
		return numeroTarjeta;
	}

	public void setNumeroTarjeta(String numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}

	public String getIdMotivo() {
		return idMotivo;
	}

	public void setIdMotivo(String idMotivo) {
		this.idMotivo = idMotivo;
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

	public String getFechaOperacion() {
		return fechaOperacion;
	}

	public void setFechaOperacion(String fechaOperacion) {
		this.fechaOperacion = fechaOperacion;
	}

	public String getEstatus() {
		return estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	public float getValorAplicado() {
		return valorAplicado;
	}

	public void setValorAplicado(float valorAplicado) {
		this.valorAplicado = valorAplicado;
	}

	public float getValorAnterior() {
		return valorAnterior;
	}

	public void setValorAnterior(float valorAnterior) {
		this.valorAnterior = valorAnterior;
	}

	public float getValorRestante() {
		return valorRestante;
	}

	public void setValorRestante(float valorRestante) {
		this.valorRestante = valorRestante;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

}
