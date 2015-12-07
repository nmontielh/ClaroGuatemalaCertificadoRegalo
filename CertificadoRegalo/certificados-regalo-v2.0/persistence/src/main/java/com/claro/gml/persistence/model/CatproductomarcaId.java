package com.claro.gml.persistence.model;
// Generated Dec 3, 2015 6:51:56 PM by Hibernate Tools 4.3.1.Final

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * CatproductomarcaId generated by hbm2java
 */
@Embeddable
public class CatproductomarcaId implements java.io.Serializable {

	private int cveproducto;
	private String cvemarca;
	private String cvemodelo;

	public CatproductomarcaId() {
	}

	public CatproductomarcaId(int cveproducto, String cvemarca, String cvemodelo) {
		this.cveproducto = cveproducto;
		this.cvemarca = cvemarca;
		this.cvemodelo = cvemodelo;
	}

	@Column(name = "CVEPRODUCTO", nullable = false, precision = 6, scale = 0)
	public int getCveproducto() {
		return this.cveproducto;
	}

	public void setCveproducto(int cveproducto) {
		this.cveproducto = cveproducto;
	}

	@Column(name = "CVEMARCA", nullable = false, length = 30)
	public String getCvemarca() {
		return this.cvemarca;
	}

	public void setCvemarca(String cvemarca) {
		this.cvemarca = cvemarca;
	}

	@Column(name = "CVEMODELO", nullable = false, length = 30)
	public String getCvemodelo() {
		return this.cvemodelo;
	}

	public void setCvemodelo(String cvemodelo) {
		this.cvemodelo = cvemodelo;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof CatproductomarcaId))
			return false;
		CatproductomarcaId castOther = (CatproductomarcaId) other;

		return (this.getCveproducto() == castOther.getCveproducto())
				&& ((this.getCvemarca() == castOther.getCvemarca()) || (this.getCvemarca() != null
						&& castOther.getCvemarca() != null && this.getCvemarca().equals(castOther.getCvemarca())))
				&& ((this.getCvemodelo() == castOther.getCvemodelo()) || (this.getCvemodelo() != null
						&& castOther.getCvemodelo() != null && this.getCvemodelo().equals(castOther.getCvemodelo())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getCveproducto();
		result = 37 * result + (getCvemarca() == null ? 0 : this.getCvemarca().hashCode());
		result = 37 * result + (getCvemodelo() == null ? 0 : this.getCvemodelo().hashCode());
		return result;
	}

}