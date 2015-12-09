package com.claro.gml.persistence.model;
// Generated Dec 3, 2015 6:51:56 PM by Hibernate Tools 4.3.1.Final

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * CatsplanesId generated by hbm2java
 */
@Embeddable
public class CatsplanesId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private String cveplanes;
	private String descripcion;
	private BigDecimal activo;

	public CatsplanesId() {
	}

	public CatsplanesId(String cveplanes) {
		this.cveplanes = cveplanes;
	}

	public CatsplanesId(String cveplanes, String descripcion, BigDecimal activo) {
		this.cveplanes = cveplanes;
		this.descripcion = descripcion;
		this.activo = activo;
	}

	@Column(name = "CVEPLANES", nullable = false, length = 30)
	public String getCveplanes() {
		return this.cveplanes;
	}

	public void setCveplanes(String cveplanes) {
		this.cveplanes = cveplanes;
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
		if (!(other instanceof CatsplanesId))
			return false;
		CatsplanesId castOther = (CatsplanesId) other;

		return ((this.getCveplanes() == castOther.getCveplanes()) || (this.getCveplanes() != null
				&& castOther.getCveplanes() != null && this.getCveplanes().equals(castOther.getCveplanes())))
				&& ((this.getDescripcion() == castOther.getDescripcion())
						|| (this.getDescripcion() != null && castOther.getDescripcion() != null
								&& this.getDescripcion().equals(castOther.getDescripcion())))
				&& ((this.getActivo() == castOther.getActivo()) || (this.getActivo() != null
						&& castOther.getActivo() != null && this.getActivo().equals(castOther.getActivo())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getCveplanes() == null ? 0 : this.getCveplanes().hashCode());
		result = 37 * result + (getDescripcion() == null ? 0 : this.getDescripcion().hashCode());
		result = 37 * result + (getActivo() == null ? 0 : this.getActivo().hashCode());
		return result;
	}

}
