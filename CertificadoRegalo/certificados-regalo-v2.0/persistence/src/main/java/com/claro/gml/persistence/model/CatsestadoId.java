package com.claro.gml.persistence.model;
// Generated Dec 3, 2015 6:51:56 PM by Hibernate Tools 4.3.1.Final

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * CatsestadoId generated by hbm2java
 */
@Embeddable
public class CatsestadoId implements java.io.Serializable {

	private String cveestado;
	private String cvepais;
	private String descripcion;
	private BigDecimal activo;
	private String cveestadom2k;

	public CatsestadoId() {
	}

	public CatsestadoId(String cveestado, String cvepais) {
		this.cveestado = cveestado;
		this.cvepais = cvepais;
	}

	public CatsestadoId(String cveestado, String cvepais, String descripcion, BigDecimal activo, String cveestadom2k) {
		this.cveestado = cveestado;
		this.cvepais = cvepais;
		this.descripcion = descripcion;
		this.activo = activo;
		this.cveestadom2k = cveestadom2k;
	}

	@Column(name = "CVEESTADO", nullable = false, length = 3)
	public String getCveestado() {
		return this.cveestado;
	}

	public void setCveestado(String cveestado) {
		this.cveestado = cveestado;
	}

	@Column(name = "CVEPAIS", nullable = false, length = 3)
	public String getCvepais() {
		return this.cvepais;
	}

	public void setCvepais(String cvepais) {
		this.cvepais = cvepais;
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

	@Column(name = "CVEESTADOM2K", length = 3)
	public String getCveestadom2k() {
		return this.cveestadom2k;
	}

	public void setCveestadom2k(String cveestadom2k) {
		this.cveestadom2k = cveestadom2k;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof CatsestadoId))
			return false;
		CatsestadoId castOther = (CatsestadoId) other;

		return ((this.getCveestado() == castOther.getCveestado()) || (this.getCveestado() != null
				&& castOther.getCveestado() != null && this.getCveestado().equals(castOther.getCveestado())))
				&& ((this.getCvepais() == castOther.getCvepais()) || (this.getCvepais() != null
						&& castOther.getCvepais() != null && this.getCvepais().equals(castOther.getCvepais())))
				&& ((this.getDescripcion() == castOther.getDescripcion())
						|| (this.getDescripcion() != null && castOther.getDescripcion() != null
								&& this.getDescripcion().equals(castOther.getDescripcion())))
				&& ((this.getActivo() == castOther.getActivo()) || (this.getActivo() != null
						&& castOther.getActivo() != null && this.getActivo().equals(castOther.getActivo())))
				&& ((this.getCveestadom2k() == castOther.getCveestadom2k())
						|| (this.getCveestadom2k() != null && castOther.getCveestadom2k() != null
								&& this.getCveestadom2k().equals(castOther.getCveestadom2k())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getCveestado() == null ? 0 : this.getCveestado().hashCode());
		result = 37 * result + (getCvepais() == null ? 0 : this.getCvepais().hashCode());
		result = 37 * result + (getDescripcion() == null ? 0 : this.getDescripcion().hashCode());
		result = 37 * result + (getActivo() == null ? 0 : this.getActivo().hashCode());
		result = 37 * result + (getCveestadom2k() == null ? 0 : this.getCveestadom2k().hashCode());
		return result;
	}

}