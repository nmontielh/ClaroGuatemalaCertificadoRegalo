package com.claro.gml.persistence.model;
// Generated Dec 3, 2015 6:51:56 PM by Hibernate Tools 4.3.1.Final

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * ComentariosId generated by hbm2java
 */
@Embeddable
public class ComentariosId implements java.io.Serializable {

	private String folio;
	private Date fecha;
	private String descripcion;
	private String usuariocrea;

	public ComentariosId() {
	}

	public ComentariosId(String folio, Date fecha) {
		this.folio = folio;
		this.fecha = fecha;
	}

	public ComentariosId(String folio, Date fecha, String descripcion, String usuariocrea) {
		this.folio = folio;
		this.fecha = fecha;
		this.descripcion = descripcion;
		this.usuariocrea = usuariocrea;
	}

	@Column(name = "FOLIO", nullable = false, length = 13)
	public String getFolio() {
		return this.folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}

	@Column(name = "FECHA", nullable = false, length = 7)
	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	@Column(name = "DESCRIPCION")
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Column(name = "USUARIOCREA", length = 7)
	public String getUsuariocrea() {
		return this.usuariocrea;
	}

	public void setUsuariocrea(String usuariocrea) {
		this.usuariocrea = usuariocrea;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ComentariosId))
			return false;
		ComentariosId castOther = (ComentariosId) other;

		return ((this.getFolio() == castOther.getFolio()) || (this.getFolio() != null && castOther.getFolio() != null
				&& this.getFolio().equals(castOther.getFolio())))
				&& ((this.getFecha() == castOther.getFecha()) || (this.getFecha() != null
						&& castOther.getFecha() != null && this.getFecha().equals(castOther.getFecha())))
				&& ((this.getDescripcion() == castOther.getDescripcion())
						|| (this.getDescripcion() != null && castOther.getDescripcion() != null
								&& this.getDescripcion().equals(castOther.getDescripcion())))
				&& ((this.getUsuariocrea() == castOther.getUsuariocrea())
						|| (this.getUsuariocrea() != null && castOther.getUsuariocrea() != null
								&& this.getUsuariocrea().equals(castOther.getUsuariocrea())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getFolio() == null ? 0 : this.getFolio().hashCode());
		result = 37 * result + (getFecha() == null ? 0 : this.getFecha().hashCode());
		result = 37 * result + (getDescripcion() == null ? 0 : this.getDescripcion().hashCode());
		result = 37 * result + (getUsuariocrea() == null ? 0 : this.getUsuariocrea().hashCode());
		return result;
	}

}