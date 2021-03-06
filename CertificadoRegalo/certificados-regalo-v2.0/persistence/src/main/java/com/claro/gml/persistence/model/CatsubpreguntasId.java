package com.claro.gml.persistence.model;
// Generated Dec 3, 2015 6:51:56 PM by Hibernate Tools 4.3.1.Final

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * CatsubpreguntasId generated by hbm2java
 */
@Embeddable
public class CatsubpreguntasId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private byte idpregunta;
	private byte idsubpregunta;
	private String descripcion;
	private Integer valormax;
	private Integer valormin;
	private Date fechaactiva;
	private Date fechaexpira;

	public CatsubpreguntasId() {
	}

	public CatsubpreguntasId(byte idpregunta, byte idsubpregunta) {
		this.idpregunta = idpregunta;
		this.idsubpregunta = idsubpregunta;
	}

	public CatsubpreguntasId(byte idpregunta, byte idsubpregunta, String descripcion, Integer valormax,
			Integer valormin, Date fechaactiva, Date fechaexpira) {
		this.idpregunta = idpregunta;
		this.idsubpregunta = idsubpregunta;
		this.descripcion = descripcion;
		this.valormax = valormax;
		this.valormin = valormin;
		this.fechaactiva = fechaactiva;
		this.fechaexpira = fechaexpira;
	}

	@Column(name = "IDPREGUNTA", nullable = false, precision = 2, scale = 0)
	public byte getIdpregunta() {
		return this.idpregunta;
	}

	public void setIdpregunta(byte idpregunta) {
		this.idpregunta = idpregunta;
	}

	@Column(name = "IDSUBPREGUNTA", nullable = false, precision = 2, scale = 0)
	public byte getIdsubpregunta() {
		return this.idsubpregunta;
	}

	public void setIdsubpregunta(byte idsubpregunta) {
		this.idsubpregunta = idsubpregunta;
	}

	@Column(name = "DESCRIPCION", length = 80)
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Column(name = "VALORMAX", precision = 6, scale = 0)
	public Integer getValormax() {
		return this.valormax;
	}

	public void setValormax(Integer valormax) {
		this.valormax = valormax;
	}

	@Column(name = "VALORMIN", precision = 6, scale = 0)
	public Integer getValormin() {
		return this.valormin;
	}

	public void setValormin(Integer valormin) {
		this.valormin = valormin;
	}

	@Column(name = "FECHAACTIVA", length = 7)
	public Date getFechaactiva() {
		return this.fechaactiva;
	}

	public void setFechaactiva(Date fechaactiva) {
		this.fechaactiva = fechaactiva;
	}

	@Column(name = "FECHAEXPIRA", length = 7)
	public Date getFechaexpira() {
		return this.fechaexpira;
	}

	public void setFechaexpira(Date fechaexpira) {
		this.fechaexpira = fechaexpira;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof CatsubpreguntasId))
			return false;
		CatsubpreguntasId castOther = (CatsubpreguntasId) other;

		return (this.getIdpregunta() == castOther.getIdpregunta())
				&& (this.getIdsubpregunta() == castOther.getIdsubpregunta())
				&& ((this.getDescripcion() == castOther.getDescripcion())
						|| (this.getDescripcion() != null && castOther.getDescripcion() != null
								&& this.getDescripcion().equals(castOther.getDescripcion())))
				&& ((this.getValormax() == castOther.getValormax()) || (this.getValormax() != null
						&& castOther.getValormax() != null && this.getValormax().equals(castOther.getValormax())))
				&& ((this.getValormin() == castOther.getValormin()) || (this.getValormin() != null
						&& castOther.getValormin() != null && this.getValormin().equals(castOther.getValormin())))
				&& ((this.getFechaactiva() == castOther.getFechaactiva())
						|| (this.getFechaactiva() != null && castOther.getFechaactiva() != null
								&& this.getFechaactiva().equals(castOther.getFechaactiva())))
				&& ((this.getFechaexpira() == castOther.getFechaexpira())
						|| (this.getFechaexpira() != null && castOther.getFechaexpira() != null
								&& this.getFechaexpira().equals(castOther.getFechaexpira())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getIdpregunta();
		result = 37 * result + this.getIdsubpregunta();
		result = 37 * result + (getDescripcion() == null ? 0 : this.getDescripcion().hashCode());
		result = 37 * result + (getValormax() == null ? 0 : this.getValormax().hashCode());
		result = 37 * result + (getValormin() == null ? 0 : this.getValormin().hashCode());
		result = 37 * result + (getFechaactiva() == null ? 0 : this.getFechaactiva().hashCode());
		result = 37 * result + (getFechaexpira() == null ? 0 : this.getFechaexpira().hashCode());
		return result;
	}

}
