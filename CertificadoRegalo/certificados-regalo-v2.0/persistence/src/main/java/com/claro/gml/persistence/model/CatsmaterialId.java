package com.claro.gml.persistence.model;
// Generated Dec 3, 2015 6:51:56 PM by Hibernate Tools 4.3.1.Final

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * CatsmaterialId generated by hbm2java
 */
@Embeddable
public class CatsmaterialId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private String cvematerial;
	private String cvemarca;
	private String cvetipoproducto;
	private String denominacion;
	private String modelo;
	private String cvetipomaterial;
	private String descripcion;
	private Boolean requiereserie;
	private Boolean requierealmacen;
	private Boolean activo;
	private String tiporotacion;

	public CatsmaterialId() {
	}

	public CatsmaterialId(String cvematerial) {
		this.cvematerial = cvematerial;
	}

	public CatsmaterialId(String cvematerial, String cvemarca, String cvetipoproducto, String denominacion,
			String modelo, String cvetipomaterial, String descripcion, Boolean requiereserie, Boolean requierealmacen,
			Boolean activo, String tiporotacion) {
		this.cvematerial = cvematerial;
		this.cvemarca = cvemarca;
		this.cvetipoproducto = cvetipoproducto;
		this.denominacion = denominacion;
		this.modelo = modelo;
		this.cvetipomaterial = cvetipomaterial;
		this.descripcion = descripcion;
		this.requiereserie = requiereserie;
		this.requierealmacen = requierealmacen;
		this.activo = activo;
		this.tiporotacion = tiporotacion;
	}

	@Column(name = "CVEMATERIAL", nullable = false, length = 18)
	public String getCvematerial() {
		return this.cvematerial;
	}

	public void setCvematerial(String cvematerial) {
		this.cvematerial = cvematerial;
	}

	@Column(name = "CVEMARCA", length = 30)
	public String getCvemarca() {
		return this.cvemarca;
	}

	public void setCvemarca(String cvemarca) {
		this.cvemarca = cvemarca;
	}

	@Column(name = "CVETIPOPRODUCTO", length = 2)
	public String getCvetipoproducto() {
		return this.cvetipoproducto;
	}

	public void setCvetipoproducto(String cvetipoproducto) {
		this.cvetipoproducto = cvetipoproducto;
	}

	@Column(name = "DENOMINACION", length = 30)
	public String getDenominacion() {
		return this.denominacion;
	}

	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}

	@Column(name = "MODELO", length = 100)
	public String getModelo() {
		return this.modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	@Column(name = "CVETIPOMATERIAL", length = 9)
	public String getCvetipomaterial() {
		return this.cvetipomaterial;
	}

	public void setCvetipomaterial(String cvetipomaterial) {
		this.cvetipomaterial = cvetipomaterial;
	}

	@Column(name = "DESCRIPCION", length = 50)
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Column(name = "REQUIERESERIE", precision = 1, scale = 0)
	public Boolean getRequiereserie() {
		return this.requiereserie;
	}

	public void setRequiereserie(Boolean requiereserie) {
		this.requiereserie = requiereserie;
	}

	@Column(name = "REQUIEREALMACEN", precision = 1, scale = 0)
	public Boolean getRequierealmacen() {
		return this.requierealmacen;
	}

	public void setRequierealmacen(Boolean requierealmacen) {
		this.requierealmacen = requierealmacen;
	}

	@Column(name = "ACTIVO", precision = 1, scale = 0)
	public Boolean getActivo() {
		return this.activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	@Column(name = "TIPOROTACION", length = 15)
	public String getTiporotacion() {
		return this.tiporotacion;
	}

	public void setTiporotacion(String tiporotacion) {
		this.tiporotacion = tiporotacion;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof CatsmaterialId))
			return false;
		CatsmaterialId castOther = (CatsmaterialId) other;

		return ((this.getCvematerial() == castOther.getCvematerial()) || (this.getCvematerial() != null
				&& castOther.getCvematerial() != null && this.getCvematerial().equals(castOther.getCvematerial())))
				&& ((this.getCvemarca() == castOther.getCvemarca()) || (this.getCvemarca() != null
						&& castOther.getCvemarca() != null && this.getCvemarca().equals(castOther.getCvemarca())))
				&& ((this.getCvetipoproducto() == castOther.getCvetipoproducto())
						|| (this.getCvetipoproducto() != null && castOther.getCvetipoproducto() != null
								&& this.getCvetipoproducto().equals(castOther.getCvetipoproducto())))
				&& ((this.getDenominacion() == castOther.getDenominacion())
						|| (this.getDenominacion() != null && castOther.getDenominacion() != null
								&& this.getDenominacion().equals(castOther.getDenominacion())))
				&& ((this.getModelo() == castOther.getModelo()) || (this.getModelo() != null
						&& castOther.getModelo() != null && this.getModelo().equals(castOther.getModelo())))
				&& ((this.getCvetipomaterial() == castOther.getCvetipomaterial())
						|| (this.getCvetipomaterial() != null && castOther.getCvetipomaterial() != null
								&& this.getCvetipomaterial().equals(castOther.getCvetipomaterial())))
				&& ((this.getDescripcion() == castOther.getDescripcion())
						|| (this.getDescripcion() != null && castOther.getDescripcion() != null
								&& this.getDescripcion().equals(castOther.getDescripcion())))
				&& ((this.getRequiereserie() == castOther.getRequiereserie())
						|| (this.getRequiereserie() != null && castOther.getRequiereserie() != null
								&& this.getRequiereserie().equals(castOther.getRequiereserie())))
				&& ((this.getRequierealmacen() == castOther.getRequierealmacen())
						|| (this.getRequierealmacen() != null && castOther.getRequierealmacen() != null
								&& this.getRequierealmacen().equals(castOther.getRequierealmacen())))
				&& ((this.getActivo() == castOther.getActivo()) || (this.getActivo() != null
						&& castOther.getActivo() != null && this.getActivo().equals(castOther.getActivo())))
				&& ((this.getTiporotacion() == castOther.getTiporotacion())
						|| (this.getTiporotacion() != null && castOther.getTiporotacion() != null
								&& this.getTiporotacion().equals(castOther.getTiporotacion())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getCvematerial() == null ? 0 : this.getCvematerial().hashCode());
		result = 37 * result + (getCvemarca() == null ? 0 : this.getCvemarca().hashCode());
		result = 37 * result + (getCvetipoproducto() == null ? 0 : this.getCvetipoproducto().hashCode());
		result = 37 * result + (getDenominacion() == null ? 0 : this.getDenominacion().hashCode());
		result = 37 * result + (getModelo() == null ? 0 : this.getModelo().hashCode());
		result = 37 * result + (getCvetipomaterial() == null ? 0 : this.getCvetipomaterial().hashCode());
		result = 37 * result + (getDescripcion() == null ? 0 : this.getDescripcion().hashCode());
		result = 37 * result + (getRequiereserie() == null ? 0 : this.getRequiereserie().hashCode());
		result = 37 * result + (getRequierealmacen() == null ? 0 : this.getRequierealmacen().hashCode());
		result = 37 * result + (getActivo() == null ? 0 : this.getActivo().hashCode());
		result = 37 * result + (getTiporotacion() == null ? 0 : this.getTiporotacion().hashCode());
		return result;
	}

}
