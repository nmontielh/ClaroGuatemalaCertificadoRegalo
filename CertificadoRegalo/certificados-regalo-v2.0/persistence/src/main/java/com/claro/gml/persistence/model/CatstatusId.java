package com.claro.gml.persistence.model;
// Generated Dec 3, 2015 6:51:56 PM by Hibernate Tools 4.3.1.Final

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * CatstatusId generated by hbm2java
 */
@Embeddable
public class CatstatusId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private String idstatus;
	private String descripcion;

	public CatstatusId() {
	}

	public CatstatusId(String idstatus) {
		this.idstatus = idstatus;
	}

	public CatstatusId(String idstatus, String descripcion) {
		this.idstatus = idstatus;
		this.descripcion = descripcion;
	}

	@Column(name = "IDSTATUS", nullable = false, length = 2)
	public String getIdstatus() {
		return this.idstatus;
	}

	public void setIdstatus(String idstatus) {
		this.idstatus = idstatus;
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
		if (!(other instanceof CatstatusId))
			return false;
		CatstatusId castOther = (CatstatusId) other;

		return ((this.getIdstatus() == castOther.getIdstatus()) || (this.getIdstatus() != null
				&& castOther.getIdstatus() != null && this.getIdstatus().equals(castOther.getIdstatus())))
				&& ((this.getDescripcion() == castOther.getDescripcion())
						|| (this.getDescripcion() != null && castOther.getDescripcion() != null
								&& this.getDescripcion().equals(castOther.getDescripcion())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getIdstatus() == null ? 0 : this.getIdstatus().hashCode());
		result = 37 * result + (getDescripcion() == null ? 0 : this.getDescripcion().hashCode());
		return result;
	}

}
