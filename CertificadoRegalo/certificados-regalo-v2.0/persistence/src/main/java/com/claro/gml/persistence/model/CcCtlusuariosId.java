package com.claro.gml.persistence.model;
// Generated Dec 3, 2015 6:51:56 PM by Hibernate Tools 4.3.1.Final

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * CcCtlusuariosId generated by hbm2java
 */
@Embeddable
public class CcCtlusuariosId implements java.io.Serializable {

	private String usuario;
	private String region;
	private String moneda;

	public CcCtlusuariosId() {
	}

	public CcCtlusuariosId(String usuario, String region, String moneda) {
		this.usuario = usuario;
		this.region = region;
		this.moneda = moneda;
	}

	@Column(name = "USUARIO", nullable = false, length = 16)
	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	@Column(name = "REGION", nullable = false, length = 4)
	public String getRegion() {
		return this.region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	@Column(name = "MONEDA", nullable = false, length = 8)
	public String getMoneda() {
		return this.moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof CcCtlusuariosId))
			return false;
		CcCtlusuariosId castOther = (CcCtlusuariosId) other;

		return ((this.getUsuario() == castOther.getUsuario()) || (this.getUsuario() != null
				&& castOther.getUsuario() != null && this.getUsuario().equals(castOther.getUsuario())))
				&& ((this.getRegion() == castOther.getRegion()) || (this.getRegion() != null
						&& castOther.getRegion() != null && this.getRegion().equals(castOther.getRegion())))
				&& ((this.getMoneda() == castOther.getMoneda()) || (this.getMoneda() != null
						&& castOther.getMoneda() != null && this.getMoneda().equals(castOther.getMoneda())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getUsuario() == null ? 0 : this.getUsuario().hashCode());
		result = 37 * result + (getRegion() == null ? 0 : this.getRegion().hashCode());
		result = 37 * result + (getMoneda() == null ? 0 : this.getMoneda().hashCode());
		return result;
	}

}
