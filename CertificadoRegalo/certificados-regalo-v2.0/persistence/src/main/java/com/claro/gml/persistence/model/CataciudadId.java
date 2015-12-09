package com.claro.gml.persistence.model;
// Generated Dec 3, 2015 6:51:56 PM by Hibernate Tools 4.3.1.Final

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * CataciudadId generated by hbm2java
 */
@Embeddable
public class CataciudadId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private String cveciudad;
	private BigDecimal idregionciudad;
	private String descripcion;

	public CataciudadId() {
	}

	public CataciudadId(String cveciudad, BigDecimal idregionciudad) {
		this.cveciudad = cveciudad;
		this.idregionciudad = idregionciudad;
	}

	public CataciudadId(String cveciudad, BigDecimal idregionciudad, String descripcion) {
		this.cveciudad = cveciudad;
		this.idregionciudad = idregionciudad;
		this.descripcion = descripcion;
	}

	@Column(name = "CVECIUDAD", nullable = false, length = 3)
	public String getCveciudad() {
		return this.cveciudad;
	}

	public void setCveciudad(String cveciudad) {
		this.cveciudad = cveciudad;
	}

	@Column(name = "IDREGIONCIUDAD", nullable = false, precision = 22, scale = 0)
	public BigDecimal getIdregionciudad() {
		return this.idregionciudad;
	}

	public void setIdregionciudad(BigDecimal idregionciudad) {
		this.idregionciudad = idregionciudad;
	}

	@Column(name = "DESCRIPCION", length = 50)
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof CataciudadId))
			return false;
		CataciudadId castOther = (CataciudadId) other;

		return ((this.getCveciudad() == castOther.getCveciudad()) || (this.getCveciudad() != null
				&& castOther.getCveciudad() != null && this.getCveciudad().equals(castOther.getCveciudad())))
				&& ((this.getIdregionciudad() == castOther.getIdregionciudad())
						|| (this.getIdregionciudad() != null && castOther.getIdregionciudad() != null
								&& this.getIdregionciudad().equals(castOther.getIdregionciudad())))
				&& ((this.getDescripcion() == castOther.getDescripcion())
						|| (this.getDescripcion() != null && castOther.getDescripcion() != null
								&& this.getDescripcion().equals(castOther.getDescripcion())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getCveciudad() == null ? 0 : this.getCveciudad().hashCode());
		result = 37 * result + (getIdregionciudad() == null ? 0 : this.getIdregionciudad().hashCode());
		result = 37 * result + (getDescripcion() == null ? 0 : this.getDescripcion().hashCode());
		return result;
	}

}
