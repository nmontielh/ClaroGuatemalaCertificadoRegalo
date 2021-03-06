package com.claro.gml.persistence.model;
// Generated Dec 3, 2015 6:51:56 PM by Hibernate Tools 4.3.1.Final

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * GrupopermisoId generated by hbm2java
 */
@Embeddable
public class GrupopermisoId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private BigDecimal idgrupo;
	private BigDecimal idpermiso;

	public GrupopermisoId() {
	}

	public GrupopermisoId(BigDecimal idgrupo, BigDecimal idpermiso) {
		this.idgrupo = idgrupo;
		this.idpermiso = idpermiso;
	}

	@Column(name = "IDGRUPO", nullable = false, precision = 22, scale = 0)
	public BigDecimal getIdgrupo() {
		return this.idgrupo;
	}

	public void setIdgrupo(BigDecimal idgrupo) {
		this.idgrupo = idgrupo;
	}

	@Column(name = "IDPERMISO", nullable = false, precision = 22, scale = 0)
	public BigDecimal getIdpermiso() {
		return this.idpermiso;
	}

	public void setIdpermiso(BigDecimal idpermiso) {
		this.idpermiso = idpermiso;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof GrupopermisoId))
			return false;
		GrupopermisoId castOther = (GrupopermisoId) other;

		return ((this.getIdgrupo() == castOther.getIdgrupo()) || (this.getIdgrupo() != null
				&& castOther.getIdgrupo() != null && this.getIdgrupo().equals(castOther.getIdgrupo())))
				&& ((this.getIdpermiso() == castOther.getIdpermiso()) || (this.getIdpermiso() != null
						&& castOther.getIdpermiso() != null && this.getIdpermiso().equals(castOther.getIdpermiso())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getIdgrupo() == null ? 0 : this.getIdgrupo().hashCode());
		result = 37 * result + (getIdpermiso() == null ? 0 : this.getIdpermiso().hashCode());
		return result;
	}

}
