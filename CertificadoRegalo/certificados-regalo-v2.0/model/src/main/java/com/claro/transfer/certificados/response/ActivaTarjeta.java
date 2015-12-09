
package com.claro.transfer.certificados.response;

import java.io.Serializable;

public class ActivaTarjeta implements Serializable {

	private static final long serialVersionUID = 1L;

	private String numeroCertificado;
	private String numeroTarjeta;
	private Long montoCertificado;
	private String fechaActivacion;
	private String fechaExpiracion;
	private String estatus;

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

	public Long getMontoCertificado() {
		return montoCertificado;
	}

	public void setMontoCertificado(Long montoCertificado) {
		this.montoCertificado = montoCertificado;
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

}
