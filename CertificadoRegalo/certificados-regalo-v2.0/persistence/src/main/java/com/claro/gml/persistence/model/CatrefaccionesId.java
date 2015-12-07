package com.claro.gml.persistence.model;
// Generated Dec 3, 2015 6:51:56 PM by Hibernate Tools 4.3.1.Final

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * CatrefaccionesId generated by hbm2java
 */
@Embeddable
public class CatrefaccionesId implements java.io.Serializable {

	private int cveproducto;
	private String cveoficinadeventas;
	private BigDecimal precio;
	private Integer existencias;

	public CatrefaccionesId() {
	}

	public CatrefaccionesId(int cveproducto, String cveoficinadeventas) {
		this.cveproducto = cveproducto;
		this.cveoficinadeventas = cveoficinadeventas;
	}

	public CatrefaccionesId(int cveproducto, String cveoficinadeventas, BigDecimal precio, Integer existencias) {
		this.cveproducto = cveproducto;
		this.cveoficinadeventas = cveoficinadeventas;
		this.precio = precio;
		this.existencias = existencias;
	}

	@Column(name = "CVEPRODUCTO", nullable = false, precision = 6, scale = 0)
	public int getCveproducto() {
		return this.cveproducto;
	}

	public void setCveproducto(int cveproducto) {
		this.cveproducto = cveproducto;
	}

	@Column(name = "CVEOFICINADEVENTAS", nullable = false, length = 4)
	public String getCveoficinadeventas() {
		return this.cveoficinadeventas;
	}

	public void setCveoficinadeventas(String cveoficinadeventas) {
		this.cveoficinadeventas = cveoficinadeventas;
	}

	@Column(name = "PRECIO", precision = 6)
	public BigDecimal getPrecio() {
		return this.precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

	@Column(name = "EXISTENCIAS", precision = 6, scale = 0)
	public Integer getExistencias() {
		return this.existencias;
	}

	public void setExistencias(Integer existencias) {
		this.existencias = existencias;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof CatrefaccionesId))
			return false;
		CatrefaccionesId castOther = (CatrefaccionesId) other;

		return (this.getCveproducto() == castOther.getCveproducto())
				&& ((this.getCveoficinadeventas() == castOther.getCveoficinadeventas())
						|| (this.getCveoficinadeventas() != null && castOther.getCveoficinadeventas() != null
								&& this.getCveoficinadeventas().equals(castOther.getCveoficinadeventas())))
				&& ((this.getPrecio() == castOther.getPrecio()) || (this.getPrecio() != null
						&& castOther.getPrecio() != null && this.getPrecio().equals(castOther.getPrecio())))
				&& ((this.getExistencias() == castOther.getExistencias())
						|| (this.getExistencias() != null && castOther.getExistencias() != null
								&& this.getExistencias().equals(castOther.getExistencias())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getCveproducto();
		result = 37 * result + (getCveoficinadeventas() == null ? 0 : this.getCveoficinadeventas().hashCode());
		result = 37 * result + (getPrecio() == null ? 0 : this.getPrecio().hashCode());
		result = 37 * result + (getExistencias() == null ? 0 : this.getExistencias().hashCode());
		return result;
	}

}