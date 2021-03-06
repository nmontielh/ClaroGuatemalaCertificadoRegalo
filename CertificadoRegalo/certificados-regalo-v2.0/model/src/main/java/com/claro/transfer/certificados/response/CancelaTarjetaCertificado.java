package com.claro.transfer.certificados.response;

import java.io.Serializable;

import com.claro.transfer.certificados.constants.SucessCatalog;

public class CancelaTarjetaCertificado implements Serializable {

	private static final long serialVersionUID = 1L;
	private String idMensaje;
	private String mensaje;

	public CancelaTarjetaCertificado() {
		super();

	}

	public CancelaTarjetaCertificado(SucessCatalog sucess) {
		super();
		this.idMensaje = sucess.getCode();
		this.mensaje = sucess.getMessage();
	}
	
	
	public CancelaTarjetaCertificado(String idMensaje, String mensaje) {
		super();
		this.idMensaje = idMensaje;
		this.mensaje = mensaje;
	}

	public String getIdMensaje() {
		return idMensaje;
	}

	public void setIdMensaje(String idMensaje) {
		this.idMensaje = idMensaje;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

}
