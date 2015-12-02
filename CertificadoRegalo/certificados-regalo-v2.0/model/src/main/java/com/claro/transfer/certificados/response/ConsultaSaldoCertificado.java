package com.claro.transfer.certificados.response;

import java.io.Serializable;

public class ConsultaSaldoCertificado implements Serializable {

	private static final long serialVersionUID = 1L;

	private String numeroTarjeta;
	private String numeroCertificado;
	private float saldo;
	private String fechaActivacion;
	private String fechaExpiracion;
	private String estatus;
	private int idMensaje;
	private String mensaje;

	public String getNumeroTarjeta() {
		return numeroTarjeta;
	}

	public void setNumeroTarjeta(String numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}

	public String getNumeroCertificado() {
		return numeroCertificado;
	}

	public void setNumeroCertificado(String numeroCertificado) {
		this.numeroCertificado = numeroCertificado;
	}

	public float getSaldo() {
		return saldo;
	}

	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}

	public String getFechaActivacion() {
		return fechaActivacion;
	}

	public void setFechaActivacion(String fechaActivacion) {
		this.fechaActivacion = fechaActivacion;
	}

	public String getFechaExpiracion() {
		return fechaExpiracion;
	}

	public void setFechaExpiracion(String fechaExpiracion) {
		this.fechaExpiracion = fechaExpiracion;
	}

	public String getEstatus() {
		return estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	public int getIdMensaje() {
		return idMensaje;
	}

	public void setIdMensaje(int idMensaje) {
		this.idMensaje = idMensaje;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

}
