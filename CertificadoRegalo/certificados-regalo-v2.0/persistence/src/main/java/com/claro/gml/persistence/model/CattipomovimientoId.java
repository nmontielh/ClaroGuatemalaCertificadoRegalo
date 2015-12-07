package com.claro.gml.persistence.model;
// Generated Dec 3, 2015 6:51:56 PM by Hibernate Tools 4.3.1.Final

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * CattipomovimientoId generated by hbm2java
 */
@Embeddable
public class CattipomovimientoId implements java.io.Serializable {

	private int idtipomovimiento;
	private String descripcion;

	public CattipomovimientoId() {
	}

	public CattipomovimientoId(int idtipomovimiento) {
		this.idtipomovimiento = idtipomovimiento;
	}

	public CattipomovimientoId(int idtipomovimiento, String descripcion) {
		this.idtipomovimiento = idtipomovimiento;
		this.descripcion = descripcion;
	}

	@Column(name = "IDTIPOMOVIMIENTO", nullable = false, precision = 6, scale = 0)
	public int getIdtipomovimiento() {
		return this.idtipomovimiento;
	}

	public void setIdtipomovimiento(int idtipomovimiento) {
		this.idtipomovimiento = idtipomovimiento;
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
		if (!(other instanceof CattipomovimientoId))
			return false;
		CattipomovimientoId castOther = (CattipomovimientoId) other;

		return (this.getIdtipomovimiento() == castOther.getIdtipomovimiento())
				&& ((this.getDescripcion() == castOther.getDescripcion())
						|| (this.getDescripcion() != null && castOther.getDescripcion() != null
								&& this.getDescripcion().equals(castOther.getDescripcion())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getIdtipomovimiento();
		result = 37 * result + (getDescripcion() == null ? 0 : this.getDescripcion().hashCode());
		return result;
	}

}