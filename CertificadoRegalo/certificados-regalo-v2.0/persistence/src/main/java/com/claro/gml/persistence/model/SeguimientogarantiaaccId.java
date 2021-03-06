package com.claro.gml.persistence.model;
// Generated Dec 3, 2015 6:51:56 PM by Hibernate Tools 4.3.1.Final

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * SeguimientogarantiaaccId generated by hbm2java
 */
@Embeddable
public class SeguimientogarantiaaccId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private String foliogenerico;
	private String material;
	private String cveoficinadeventas;
	private Short piezas;
	private String modelo;
	private String componentes;
	private String comentarios;
	private Byte reemplazo;

	public SeguimientogarantiaaccId() {
	}

	public SeguimientogarantiaaccId(String foliogenerico, String material, String cveoficinadeventas) {
		this.foliogenerico = foliogenerico;
		this.material = material;
		this.cveoficinadeventas = cveoficinadeventas;
	}

	public SeguimientogarantiaaccId(String foliogenerico, String material, String cveoficinadeventas, Short piezas,
			String modelo, String componentes, String comentarios, Byte reemplazo) {
		this.foliogenerico = foliogenerico;
		this.material = material;
		this.cveoficinadeventas = cveoficinadeventas;
		this.piezas = piezas;
		this.modelo = modelo;
		this.componentes = componentes;
		this.comentarios = comentarios;
		this.reemplazo = reemplazo;
	}

	@Column(name = "FOLIOGENERICO", nullable = false, length = 17)
	public String getFoliogenerico() {
		return this.foliogenerico;
	}

	public void setFoliogenerico(String foliogenerico) {
		this.foliogenerico = foliogenerico;
	}

	@Column(name = "MATERIAL", nullable = false, length = 20)
	public String getMaterial() {
		return this.material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	@Column(name = "CVEOFICINADEVENTAS", nullable = false, length = 4)
	public String getCveoficinadeventas() {
		return this.cveoficinadeventas;
	}

	public void setCveoficinadeventas(String cveoficinadeventas) {
		this.cveoficinadeventas = cveoficinadeventas;
	}

	@Column(name = "PIEZAS", precision = 3, scale = 0)
	public Short getPiezas() {
		return this.piezas;
	}

	public void setPiezas(Short piezas) {
		this.piezas = piezas;
	}

	@Column(name = "MODELO", length = 100)
	public String getModelo() {
		return this.modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	@Column(name = "COMPONENTES")
	public String getComponentes() {
		return this.componentes;
	}

	public void setComponentes(String componentes) {
		this.componentes = componentes;
	}

	@Column(name = "COMENTARIOS")
	public String getComentarios() {
		return this.comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	@Column(name = "REEMPLAZO", precision = 2, scale = 0)
	public Byte getReemplazo() {
		return this.reemplazo;
	}

	public void setReemplazo(Byte reemplazo) {
		this.reemplazo = reemplazo;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof SeguimientogarantiaaccId))
			return false;
		SeguimientogarantiaaccId castOther = (SeguimientogarantiaaccId) other;

		return ((this.getFoliogenerico() == castOther.getFoliogenerico())
				|| (this.getFoliogenerico() != null && castOther.getFoliogenerico() != null
						&& this.getFoliogenerico().equals(castOther.getFoliogenerico())))
				&& ((this.getMaterial() == castOther.getMaterial()) || (this.getMaterial() != null
						&& castOther.getMaterial() != null && this.getMaterial().equals(castOther.getMaterial())))
				&& ((this.getCveoficinadeventas() == castOther.getCveoficinadeventas())
						|| (this.getCveoficinadeventas() != null && castOther.getCveoficinadeventas() != null
								&& this.getCveoficinadeventas().equals(castOther.getCveoficinadeventas())))
				&& ((this.getPiezas() == castOther.getPiezas()) || (this.getPiezas() != null
						&& castOther.getPiezas() != null && this.getPiezas().equals(castOther.getPiezas())))
				&& ((this.getModelo() == castOther.getModelo()) || (this.getModelo() != null
						&& castOther.getModelo() != null && this.getModelo().equals(castOther.getModelo())))
				&& ((this.getComponentes() == castOther.getComponentes())
						|| (this.getComponentes() != null && castOther.getComponentes() != null
								&& this.getComponentes().equals(castOther.getComponentes())))
				&& ((this.getComentarios() == castOther.getComentarios())
						|| (this.getComentarios() != null && castOther.getComentarios() != null
								&& this.getComentarios().equals(castOther.getComentarios())))
				&& ((this.getReemplazo() == castOther.getReemplazo()) || (this.getReemplazo() != null
						&& castOther.getReemplazo() != null && this.getReemplazo().equals(castOther.getReemplazo())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getFoliogenerico() == null ? 0 : this.getFoliogenerico().hashCode());
		result = 37 * result + (getMaterial() == null ? 0 : this.getMaterial().hashCode());
		result = 37 * result + (getCveoficinadeventas() == null ? 0 : this.getCveoficinadeventas().hashCode());
		result = 37 * result + (getPiezas() == null ? 0 : this.getPiezas().hashCode());
		result = 37 * result + (getModelo() == null ? 0 : this.getModelo().hashCode());
		result = 37 * result + (getComponentes() == null ? 0 : this.getComponentes().hashCode());
		result = 37 * result + (getComentarios() == null ? 0 : this.getComentarios().hashCode());
		result = 37 * result + (getReemplazo() == null ? 0 : this.getReemplazo().hashCode());
		return result;
	}

}
