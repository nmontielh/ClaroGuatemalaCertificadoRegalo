package com.claro.gml.persistence.model;
// Generated Dec 3, 2015 6:51:56 PM by Hibernate Tools 4.3.1.Final

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * CatsmotivoId generated by hbm2java
 */
@Embeddable
public class CatsmotivoId implements java.io.Serializable {

	private String cvemotivo;
	private String descripcion;
	private BigDecimal activo;

	public CatsmotivoId() {
	}

	public CatsmotivoId(String cvemotivo) {
		this.cvemotivo = cvemotivo;
	}

	public CatsmotivoId(String cvemotivo, String descripcion, BigDecimal activo) {
		this.cvemotivo = cvemotivo;
		this.descripcion = descripcion;
		this.activo = activo;
	}

	@Column(name = "CVEMOTIVO", nullable = false, length = 3)
	public String getCvemotivo() {
		return this.cvemotivo;
	}

	public void setCvemotivo(String cvemotivo) {
		this.cvemotivo = cvemotivo;
	}

	@Column(name = "DESCRIPCION", length = 50)
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Column(name = "ACTIVO", precision = 22, scale = 0)
	public BigDecimal getActivo() {
		return this.activo;
	}

	public void setActivo(BigDecimal activo) {
		this.activo = activo;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof CatsmotivoId))
			return false;
		CatsmotivoId castOther = (CatsmotivoId) other;

		return ((this.getCvemotivo() == castOther.getCvemotivo()) || (this.getCvemotivo() != null
				&& castOther.getCvemotivo() != null && this.getCvemotivo().equals(castOther.getCvemotivo())))
				&& ((this.getDescripcion() == castOther.getDescripcion())
						|| (this.getDescripcion() != null && castOther.getDescripcion() != null
								&& this.getDescripcion().equals(castOther.getDescripcion())))
				&& ((this.getActivo() == castOther.getActivo()) || (this.getActivo() != null
						&& castOther.getActivo() != null && this.getActivo().equals(castOther.getActivo())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getCvemotivo() == null ? 0 : this.getCvemotivo().hashCode());
		result = 37 * result + (getDescripcion() == null ? 0 : this.getDescripcion().hashCode());
		result = 37 * result + (getActivo() == null ? 0 : this.getActivo().hashCode());
		return result;
	}

}
