package com.claro.gml.persistence.model;
// Generated Dec 3, 2015 6:51:56 PM by Hibernate Tools 4.3.1.Final

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * CatdiasfestivosId generated by hbm2java
 */
@Embeddable
public class CatdiasfestivosId implements java.io.Serializable {

	private Date fecha;
	private Boolean activo;
	private String pais;

	public CatdiasfestivosId() {
	}

	public CatdiasfestivosId(Date fecha) {
		this.fecha = fecha;
	}

	public CatdiasfestivosId(Date fecha, Boolean activo, String pais) {
		this.fecha = fecha;
		this.activo = activo;
		this.pais = pais;
	}

	@Column(name = "FECHA", nullable = false, length = 7)
	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	@Column(name = "ACTIVO", precision = 1, scale = 0)
	public Boolean getActivo() {
		return this.activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	@Column(name = "PAIS", length = 3)
	public String getPais() {
		return this.pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof CatdiasfestivosId))
			return false;
		CatdiasfestivosId castOther = (CatdiasfestivosId) other;

		return ((this.getFecha() == castOther.getFecha()) || (this.getFecha() != null && castOther.getFecha() != null
				&& this.getFecha().equals(castOther.getFecha())))
				&& ((this.getActivo() == castOther.getActivo()) || (this.getActivo() != null
						&& castOther.getActivo() != null && this.getActivo().equals(castOther.getActivo())))
				&& ((this.getPais() == castOther.getPais()) || (this.getPais() != null && castOther.getPais() != null
						&& this.getPais().equals(castOther.getPais())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getFecha() == null ? 0 : this.getFecha().hashCode());
		result = 37 * result + (getActivo() == null ? 0 : this.getActivo().hashCode());
		result = 37 * result + (getPais() == null ? 0 : this.getPais().hashCode());
		return result;
	}

}
