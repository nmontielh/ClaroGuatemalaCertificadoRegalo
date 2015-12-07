package com.claro.gml.persistence.model;
// Generated Dec 3, 2015 6:51:56 PM by Hibernate Tools 4.3.1.Final

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * CatmotivocitaId generated by hbm2java
 */
@Embeddable
public class CatmotivocitaId implements java.io.Serializable {

	private BigDecimal idmotivocita;
	private String descripcion;
	private BigDecimal activo;

	public CatmotivocitaId() {
	}

	public CatmotivocitaId(BigDecimal idmotivocita) {
		this.idmotivocita = idmotivocita;
	}

	public CatmotivocitaId(BigDecimal idmotivocita, String descripcion, BigDecimal activo) {
		this.idmotivocita = idmotivocita;
		this.descripcion = descripcion;
		this.activo = activo;
	}

	@Column(name = "IDMOTIVOCITA", nullable = false, precision = 22, scale = 0)
	public BigDecimal getIdmotivocita() {
		return this.idmotivocita;
	}

	public void setIdmotivocita(BigDecimal idmotivocita) {
		this.idmotivocita = idmotivocita;
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
		if (!(other instanceof CatmotivocitaId))
			return false;
		CatmotivocitaId castOther = (CatmotivocitaId) other;

		return ((this.getIdmotivocita() == castOther.getIdmotivocita()) || (this.getIdmotivocita() != null
				&& castOther.getIdmotivocita() != null && this.getIdmotivocita().equals(castOther.getIdmotivocita())))
				&& ((this.getDescripcion() == castOther.getDescripcion())
						|| (this.getDescripcion() != null && castOther.getDescripcion() != null
								&& this.getDescripcion().equals(castOther.getDescripcion())))
				&& ((this.getActivo() == castOther.getActivo()) || (this.getActivo() != null
						&& castOther.getActivo() != null && this.getActivo().equals(castOther.getActivo())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getIdmotivocita() == null ? 0 : this.getIdmotivocita().hashCode());
		result = 37 * result + (getDescripcion() == null ? 0 : this.getDescripcion().hashCode());
		result = 37 * result + (getActivo() == null ? 0 : this.getActivo().hashCode());
		return result;
	}

}