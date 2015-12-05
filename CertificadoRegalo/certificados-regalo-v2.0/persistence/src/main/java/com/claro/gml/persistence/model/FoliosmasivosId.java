package com.claro.gml.persistence.model;
// Generated Dec 3, 2015 6:51:56 PM by Hibernate Tools 4.3.1.Final

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * FoliosmasivosId generated by hbm2java
 */
@Embeddable
public class FoliosmasivosId implements java.io.Serializable {

	private String foliomasivo;
	private String region;
	private String cveoficinadeventas;
	private Short numequipos;
	private Date fechaalta;
	private String usuarioalta;
	private Boolean indicador;

	public FoliosmasivosId() {
	}

	public FoliosmasivosId(String foliomasivo) {
		this.foliomasivo = foliomasivo;
	}

	public FoliosmasivosId(String foliomasivo, String region, String cveoficinadeventas, Short numequipos,
			Date fechaalta, String usuarioalta, Boolean indicador) {
		this.foliomasivo = foliomasivo;
		this.region = region;
		this.cveoficinadeventas = cveoficinadeventas;
		this.numequipos = numequipos;
		this.fechaalta = fechaalta;
		this.usuarioalta = usuarioalta;
		this.indicador = indicador;
	}

	@Column(name = "FOLIOMASIVO", nullable = false, length = 16)
	public String getFoliomasivo() {
		return this.foliomasivo;
	}

	public void setFoliomasivo(String foliomasivo) {
		this.foliomasivo = foliomasivo;
	}

	@Column(name = "REGION", length = 2)
	public String getRegion() {
		return this.region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	@Column(name = "CVEOFICINADEVENTAS", length = 4)
	public String getCveoficinadeventas() {
		return this.cveoficinadeventas;
	}

	public void setCveoficinadeventas(String cveoficinadeventas) {
		this.cveoficinadeventas = cveoficinadeventas;
	}

	@Column(name = "NUMEQUIPOS", precision = 4, scale = 0)
	public Short getNumequipos() {
		return this.numequipos;
	}

	public void setNumequipos(Short numequipos) {
		this.numequipos = numequipos;
	}

	@Column(name = "FECHAALTA", length = 7)
	public Date getFechaalta() {
		return this.fechaalta;
	}

	public void setFechaalta(Date fechaalta) {
		this.fechaalta = fechaalta;
	}

	@Column(name = "USUARIOALTA", length = 7)
	public String getUsuarioalta() {
		return this.usuarioalta;
	}

	public void setUsuarioalta(String usuarioalta) {
		this.usuarioalta = usuarioalta;
	}

	@Column(name = "INDICADOR", precision = 1, scale = 0)
	public Boolean getIndicador() {
		return this.indicador;
	}

	public void setIndicador(Boolean indicador) {
		this.indicador = indicador;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof FoliosmasivosId))
			return false;
		FoliosmasivosId castOther = (FoliosmasivosId) other;

		return ((this.getFoliomasivo() == castOther.getFoliomasivo()) || (this.getFoliomasivo() != null
				&& castOther.getFoliomasivo() != null && this.getFoliomasivo().equals(castOther.getFoliomasivo())))
				&& ((this.getRegion() == castOther.getRegion()) || (this.getRegion() != null
						&& castOther.getRegion() != null && this.getRegion().equals(castOther.getRegion())))
				&& ((this.getCveoficinadeventas() == castOther.getCveoficinadeventas())
						|| (this.getCveoficinadeventas() != null && castOther.getCveoficinadeventas() != null
								&& this.getCveoficinadeventas().equals(castOther.getCveoficinadeventas())))
				&& ((this.getNumequipos() == castOther.getNumequipos()) || (this.getNumequipos() != null
						&& castOther.getNumequipos() != null && this.getNumequipos().equals(castOther.getNumequipos())))
				&& ((this.getFechaalta() == castOther.getFechaalta()) || (this.getFechaalta() != null
						&& castOther.getFechaalta() != null && this.getFechaalta().equals(castOther.getFechaalta())))
				&& ((this.getUsuarioalta() == castOther.getUsuarioalta())
						|| (this.getUsuarioalta() != null && castOther.getUsuarioalta() != null
								&& this.getUsuarioalta().equals(castOther.getUsuarioalta())))
				&& ((this.getIndicador() == castOther.getIndicador()) || (this.getIndicador() != null
						&& castOther.getIndicador() != null && this.getIndicador().equals(castOther.getIndicador())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getFoliomasivo() == null ? 0 : this.getFoliomasivo().hashCode());
		result = 37 * result + (getRegion() == null ? 0 : this.getRegion().hashCode());
		result = 37 * result + (getCveoficinadeventas() == null ? 0 : this.getCveoficinadeventas().hashCode());
		result = 37 * result + (getNumequipos() == null ? 0 : this.getNumequipos().hashCode());
		result = 37 * result + (getFechaalta() == null ? 0 : this.getFechaalta().hashCode());
		result = 37 * result + (getUsuarioalta() == null ? 0 : this.getUsuarioalta().hashCode());
		result = 37 * result + (getIndicador() == null ? 0 : this.getIndicador().hashCode());
		return result;
	}

}
