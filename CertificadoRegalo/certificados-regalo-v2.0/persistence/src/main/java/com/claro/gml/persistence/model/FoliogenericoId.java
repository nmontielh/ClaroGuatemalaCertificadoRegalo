package com.claro.gml.persistence.model;
// Generated Dec 3, 2015 6:51:56 PM by Hibernate Tools 4.3.1.Final

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * FoliogenericoId generated by hbm2java
 */
@Embeddable
public class FoliogenericoId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private String foliogenerico;
	private String folio;
	private String tipofolio;
	private String cveoficinadeventas;
	private Date fechaalta;
	private String usuarioalta;
	private String comentario;

	public FoliogenericoId() {
	}

	public FoliogenericoId(String foliogenerico, String folio, String tipofolio) {
		this.foliogenerico = foliogenerico;
		this.folio = folio;
		this.tipofolio = tipofolio;
	}

	public FoliogenericoId(String foliogenerico, String folio, String tipofolio, String cveoficinadeventas,
			Date fechaalta, String usuarioalta, String comentario) {
		this.foliogenerico = foliogenerico;
		this.folio = folio;
		this.tipofolio = tipofolio;
		this.cveoficinadeventas = cveoficinadeventas;
		this.fechaalta = fechaalta;
		this.usuarioalta = usuarioalta;
		this.comentario = comentario;
	}

	@Column(name = "FOLIOGENERICO", nullable = false, length = 17)
	public String getFoliogenerico() {
		return this.foliogenerico;
	}

	public void setFoliogenerico(String foliogenerico) {
		this.foliogenerico = foliogenerico;
	}

	@Column(name = "FOLIO", nullable = false, length = 13)
	public String getFolio() {
		return this.folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}

	@Column(name = "TIPOFOLIO", nullable = false, length = 30)
	public String getTipofolio() {
		return this.tipofolio;
	}

	public void setTipofolio(String tipofolio) {
		this.tipofolio = tipofolio;
	}

	@Column(name = "CVEOFICINADEVENTAS", length = 4)
	public String getCveoficinadeventas() {
		return this.cveoficinadeventas;
	}

	public void setCveoficinadeventas(String cveoficinadeventas) {
		this.cveoficinadeventas = cveoficinadeventas;
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

	@Column(name = "COMENTARIO")
	public String getComentario() {
		return this.comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof FoliogenericoId))
			return false;
		FoliogenericoId castOther = (FoliogenericoId) other;

		return ((this.getFoliogenerico() == castOther.getFoliogenerico())
				|| (this.getFoliogenerico() != null && castOther.getFoliogenerico() != null
						&& this.getFoliogenerico().equals(castOther.getFoliogenerico())))
				&& ((this.getFolio() == castOther.getFolio()) || (this.getFolio() != null
						&& castOther.getFolio() != null && this.getFolio().equals(castOther.getFolio())))
				&& ((this.getTipofolio() == castOther.getTipofolio()) || (this.getTipofolio() != null
						&& castOther.getTipofolio() != null && this.getTipofolio().equals(castOther.getTipofolio())))
				&& ((this.getCveoficinadeventas() == castOther.getCveoficinadeventas())
						|| (this.getCveoficinadeventas() != null && castOther.getCveoficinadeventas() != null
								&& this.getCveoficinadeventas().equals(castOther.getCveoficinadeventas())))
				&& ((this.getFechaalta() == castOther.getFechaalta()) || (this.getFechaalta() != null
						&& castOther.getFechaalta() != null && this.getFechaalta().equals(castOther.getFechaalta())))
				&& ((this.getUsuarioalta() == castOther.getUsuarioalta())
						|| (this.getUsuarioalta() != null && castOther.getUsuarioalta() != null
								&& this.getUsuarioalta().equals(castOther.getUsuarioalta())))
				&& ((this.getComentario() == castOther.getComentario())
						|| (this.getComentario() != null && castOther.getComentario() != null
								&& this.getComentario().equals(castOther.getComentario())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getFoliogenerico() == null ? 0 : this.getFoliogenerico().hashCode());
		result = 37 * result + (getFolio() == null ? 0 : this.getFolio().hashCode());
		result = 37 * result + (getTipofolio() == null ? 0 : this.getTipofolio().hashCode());
		result = 37 * result + (getCveoficinadeventas() == null ? 0 : this.getCveoficinadeventas().hashCode());
		result = 37 * result + (getFechaalta() == null ? 0 : this.getFechaalta().hashCode());
		result = 37 * result + (getUsuarioalta() == null ? 0 : this.getUsuarioalta().hashCode());
		result = 37 * result + (getComentario() == null ? 0 : this.getComentario().hashCode());
		return result;
	}

}
