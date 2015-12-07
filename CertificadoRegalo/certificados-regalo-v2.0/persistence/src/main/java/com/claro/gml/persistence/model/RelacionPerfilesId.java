package com.claro.gml.persistence.model;
// Generated Dec 3, 2015 6:51:56 PM by Hibernate Tools 4.3.1.Final

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * RelacionPerfilesId generated by hbm2java
 */
@Embeddable
public class RelacionPerfilesId implements java.io.Serializable {

	private byte idRelacion;
	private String perfil;
	private Byte activo;
	private String clave;

	public RelacionPerfilesId() {
	}

	public RelacionPerfilesId(byte idRelacion) {
		this.idRelacion = idRelacion;
	}

	public RelacionPerfilesId(byte idRelacion, String perfil, Byte activo, String clave) {
		this.idRelacion = idRelacion;
		this.perfil = perfil;
		this.activo = activo;
		this.clave = clave;
	}

	@Column(name = "ID_RELACION", nullable = false, precision = 2, scale = 0)
	public byte getIdRelacion() {
		return this.idRelacion;
	}

	public void setIdRelacion(byte idRelacion) {
		this.idRelacion = idRelacion;
	}

	@Column(name = "PERFIL", length = 80)
	public String getPerfil() {
		return this.perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	@Column(name = "ACTIVO", precision = 2, scale = 0)
	public Byte getActivo() {
		return this.activo;
	}

	public void setActivo(Byte activo) {
		this.activo = activo;
	}

	@Column(name = "CLAVE", length = 15)
	public String getClave() {
		return this.clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof RelacionPerfilesId))
			return false;
		RelacionPerfilesId castOther = (RelacionPerfilesId) other;

		return (this.getIdRelacion() == castOther.getIdRelacion())
				&& ((this.getPerfil() == castOther.getPerfil()) || (this.getPerfil() != null
						&& castOther.getPerfil() != null && this.getPerfil().equals(castOther.getPerfil())))
				&& ((this.getActivo() == castOther.getActivo()) || (this.getActivo() != null
						&& castOther.getActivo() != null && this.getActivo().equals(castOther.getActivo())))
				&& ((this.getClave() == castOther.getClave()) || (this.getClave() != null
						&& castOther.getClave() != null && this.getClave().equals(castOther.getClave())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getIdRelacion();
		result = 37 * result + (getPerfil() == null ? 0 : this.getPerfil().hashCode());
		result = 37 * result + (getActivo() == null ? 0 : this.getActivo().hashCode());
		result = 37 * result + (getClave() == null ? 0 : this.getClave().hashCode());
		return result;
	}

}