package com.claro.gml.persistence.model;
// Generated Dec 3, 2015 6:51:56 PM by Hibernate Tools 4.3.1.Final

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * CatsalmacenId generated by hbm2java
 */
@Embeddable
public class CatsalmacenId implements java.io.Serializable {

	private String cvealmacen;
	private String descripcion;
	private BigDecimal activo;

	public CatsalmacenId() {
	}

	public CatsalmacenId(String cvealmacen) {
		this.cvealmacen = cvealmacen;
	}

	public CatsalmacenId(String cvealmacen, String descripcion, BigDecimal activo) {
		this.cvealmacen = cvealmacen;
		this.descripcion = descripcion;
		this.activo = activo;
	}

	@Column(name = "CVEALMACEN", nullable = false, length = 4)
	public String getCvealmacen() {
		return this.cvealmacen;
	}

	public void setCvealmacen(String cvealmacen) {
		this.cvealmacen = cvealmacen;
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
		if (!(other instanceof CatsalmacenId))
			return false;
		CatsalmacenId castOther = (CatsalmacenId) other;

		return ((this.getCvealmacen() == castOther.getCvealmacen()) || (this.getCvealmacen() != null
				&& castOther.getCvealmacen() != null && this.getCvealmacen().equals(castOther.getCvealmacen())))
				&& ((this.getDescripcion() == castOther.getDescripcion())
						|| (this.getDescripcion() != null && castOther.getDescripcion() != null
								&& this.getDescripcion().equals(castOther.getDescripcion())))
				&& ((this.getActivo() == castOther.getActivo()) || (this.getActivo() != null
						&& castOther.getActivo() != null && this.getActivo().equals(castOther.getActivo())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getCvealmacen() == null ? 0 : this.getCvealmacen().hashCode());
		result = 37 * result + (getDescripcion() == null ? 0 : this.getDescripcion().hashCode());
		result = 37 * result + (getActivo() == null ? 0 : this.getActivo().hashCode());
		return result;
	}

}