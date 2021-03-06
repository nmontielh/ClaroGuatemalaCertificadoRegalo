package com.claro.gml.persistence.model;
// Generated Dec 3, 2015 6:51:56 PM by Hibernate Tools 4.3.1.Final

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * RelacionmarcascsaId generated by hbm2java
 */
@Embeddable
public class RelacionmarcascsaId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private String cvemarca;
	private String cveoficinadeventas;
	private Boolean vigente;

	public RelacionmarcascsaId() {
	}

	public RelacionmarcascsaId(String cvemarca, String cveoficinadeventas, Boolean vigente) {
		this.cvemarca = cvemarca;
		this.cveoficinadeventas = cveoficinadeventas;
		this.vigente = vigente;
	}

	@Column(name = "CVEMARCA", length = 30)
	public String getCvemarca() {
		return this.cvemarca;
	}

	public void setCvemarca(String cvemarca) {
		this.cvemarca = cvemarca;
	}

	@Column(name = "CVEOFICINADEVENTAS", length = 4)
	public String getCveoficinadeventas() {
		return this.cveoficinadeventas;
	}

	public void setCveoficinadeventas(String cveoficinadeventas) {
		this.cveoficinadeventas = cveoficinadeventas;
	}

	@Column(name = "VIGENTE", precision = 1, scale = 0)
	public Boolean getVigente() {
		return this.vigente;
	}

	public void setVigente(Boolean vigente) {
		this.vigente = vigente;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof RelacionmarcascsaId))
			return false;
		RelacionmarcascsaId castOther = (RelacionmarcascsaId) other;

		return ((this.getCvemarca() == castOther.getCvemarca()) || (this.getCvemarca() != null
				&& castOther.getCvemarca() != null && this.getCvemarca().equals(castOther.getCvemarca())))
				&& ((this.getCveoficinadeventas() == castOther.getCveoficinadeventas())
						|| (this.getCveoficinadeventas() != null && castOther.getCveoficinadeventas() != null
								&& this.getCveoficinadeventas().equals(castOther.getCveoficinadeventas())))
				&& ((this.getVigente() == castOther.getVigente()) || (this.getVigente() != null
						&& castOther.getVigente() != null && this.getVigente().equals(castOther.getVigente())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getCvemarca() == null ? 0 : this.getCvemarca().hashCode());
		result = 37 * result + (getCveoficinadeventas() == null ? 0 : this.getCveoficinadeventas().hashCode());
		result = 37 * result + (getVigente() == null ? 0 : this.getVigente().hashCode());
		return result;
	}

}
