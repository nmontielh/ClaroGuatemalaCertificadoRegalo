package com.claro.gml.persistence.model;
// Generated Dec 3, 2015 6:51:56 PM by Hibernate Tools 4.3.1.Final

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * DiasreparacionmarcaId generated by hbm2java
 */
@Embeddable
public class DiasreparacionmarcaId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private String cvemarca;
	private String cveoficinadeventas;
	private String cvemensajeria;
	private BigDecimal aplicagarantia;
	private String cveregion;
	private String diashabiles;
	private Long diasreparacion;

	public DiasreparacionmarcaId() {
	}

	public DiasreparacionmarcaId(String cvemarca, String cveoficinadeventas, String cvemensajeria,
			BigDecimal aplicagarantia, String cveregion, String diashabiles, Long diasreparacion) {
		this.cvemarca = cvemarca;
		this.cveoficinadeventas = cveoficinadeventas;
		this.cvemensajeria = cvemensajeria;
		this.aplicagarantia = aplicagarantia;
		this.cveregion = cveregion;
		this.diashabiles = diashabiles;
		this.diasreparacion = diasreparacion;
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

	@Column(name = "CVEMENSAJERIA", length = 2)
	public String getCvemensajeria() {
		return this.cvemensajeria;
	}

	public void setCvemensajeria(String cvemensajeria) {
		this.cvemensajeria = cvemensajeria;
	}

	@Column(name = "APLICAGARANTIA", precision = 22, scale = 0)
	public BigDecimal getAplicagarantia() {
		return this.aplicagarantia;
	}

	public void setAplicagarantia(BigDecimal aplicagarantia) {
		this.aplicagarantia = aplicagarantia;
	}

	@Column(name = "CVEREGION", length = 2)
	public String getCveregion() {
		return this.cveregion;
	}

	public void setCveregion(String cveregion) {
		this.cveregion = cveregion;
	}

	@Column(name = "DIASHABILES", length = 2)
	public String getDiashabiles() {
		return this.diashabiles;
	}

	public void setDiashabiles(String diashabiles) {
		this.diashabiles = diashabiles;
	}

	@Column(name = "DIASREPARACION", precision = 10, scale = 0)
	public Long getDiasreparacion() {
		return this.diasreparacion;
	}

	public void setDiasreparacion(Long diasreparacion) {
		this.diasreparacion = diasreparacion;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof DiasreparacionmarcaId))
			return false;
		DiasreparacionmarcaId castOther = (DiasreparacionmarcaId) other;

		return ((this.getCvemarca() == castOther.getCvemarca()) || (this.getCvemarca() != null
				&& castOther.getCvemarca() != null && this.getCvemarca().equals(castOther.getCvemarca())))
				&& ((this.getCveoficinadeventas() == castOther.getCveoficinadeventas())
						|| (this.getCveoficinadeventas() != null && castOther.getCveoficinadeventas() != null
								&& this.getCveoficinadeventas().equals(castOther.getCveoficinadeventas())))
				&& ((this.getCvemensajeria() == castOther.getCvemensajeria())
						|| (this.getCvemensajeria() != null && castOther.getCvemensajeria() != null
								&& this.getCvemensajeria().equals(castOther.getCvemensajeria())))
				&& ((this.getAplicagarantia() == castOther.getAplicagarantia())
						|| (this.getAplicagarantia() != null && castOther.getAplicagarantia() != null
								&& this.getAplicagarantia().equals(castOther.getAplicagarantia())))
				&& ((this.getCveregion() == castOther.getCveregion()) || (this.getCveregion() != null
						&& castOther.getCveregion() != null && this.getCveregion().equals(castOther.getCveregion())))
				&& ((this.getDiashabiles() == castOther.getDiashabiles())
						|| (this.getDiashabiles() != null && castOther.getDiashabiles() != null
								&& this.getDiashabiles().equals(castOther.getDiashabiles())))
				&& ((this.getDiasreparacion() == castOther.getDiasreparacion())
						|| (this.getDiasreparacion() != null && castOther.getDiasreparacion() != null
								&& this.getDiasreparacion().equals(castOther.getDiasreparacion())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getCvemarca() == null ? 0 : this.getCvemarca().hashCode());
		result = 37 * result + (getCveoficinadeventas() == null ? 0 : this.getCveoficinadeventas().hashCode());
		result = 37 * result + (getCvemensajeria() == null ? 0 : this.getCvemensajeria().hashCode());
		result = 37 * result + (getAplicagarantia() == null ? 0 : this.getAplicagarantia().hashCode());
		result = 37 * result + (getCveregion() == null ? 0 : this.getCveregion().hashCode());
		result = 37 * result + (getDiashabiles() == null ? 0 : this.getDiashabiles().hashCode());
		result = 37 * result + (getDiasreparacion() == null ? 0 : this.getDiasreparacion().hashCode());
		return result;
	}

}
