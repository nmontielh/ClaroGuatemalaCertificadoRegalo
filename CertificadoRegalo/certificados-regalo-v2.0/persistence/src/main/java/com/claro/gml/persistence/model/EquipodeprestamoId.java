package com.claro.gml.persistence.model;
// Generated Dec 3, 2015 6:51:56 PM by Hibernate Tools 4.3.1.Final

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * EquipodeprestamoId generated by hbm2java
 */
@Embeddable
public class EquipodeprestamoId implements java.io.Serializable {

	private int idequipodeprestamo;
	private Integer idestatusprestamo;
	private String cvemarca;
	private String cvemodelo;
	private Short plataforma;
	private BigDecimal costo;
	private String esnimei;
	private Short tipotel;
	private String cveoficinadeventas;
	private Short idantena;
	private Short idcarcaza;
	private Short iddisplay;
	private Short idteclado;
	private String descedofisico;
	private Date fechaalta;
	private String usuariocrea;
	private String accesorios;

	public EquipodeprestamoId() {
	}

	public EquipodeprestamoId(int idequipodeprestamo) {
		this.idequipodeprestamo = idequipodeprestamo;
	}

	public EquipodeprestamoId(int idequipodeprestamo, Integer idestatusprestamo, String cvemarca, String cvemodelo,
			Short plataforma, BigDecimal costo, String esnimei, Short tipotel, String cveoficinadeventas,
			Short idantena, Short idcarcaza, Short iddisplay, Short idteclado, String descedofisico, Date fechaalta,
			String usuariocrea, String accesorios) {
		this.idequipodeprestamo = idequipodeprestamo;
		this.idestatusprestamo = idestatusprestamo;
		this.cvemarca = cvemarca;
		this.cvemodelo = cvemodelo;
		this.plataforma = plataforma;
		this.costo = costo;
		this.esnimei = esnimei;
		this.tipotel = tipotel;
		this.cveoficinadeventas = cveoficinadeventas;
		this.idantena = idantena;
		this.idcarcaza = idcarcaza;
		this.iddisplay = iddisplay;
		this.idteclado = idteclado;
		this.descedofisico = descedofisico;
		this.fechaalta = fechaalta;
		this.usuariocrea = usuariocrea;
		this.accesorios = accesorios;
	}

	@Column(name = "IDEQUIPODEPRESTAMO", nullable = false, precision = 6, scale = 0)
	public int getIdequipodeprestamo() {
		return this.idequipodeprestamo;
	}

	public void setIdequipodeprestamo(int idequipodeprestamo) {
		this.idequipodeprestamo = idequipodeprestamo;
	}

	@Column(name = "IDESTATUSPRESTAMO", precision = 6, scale = 0)
	public Integer getIdestatusprestamo() {
		return this.idestatusprestamo;
	}

	public void setIdestatusprestamo(Integer idestatusprestamo) {
		this.idestatusprestamo = idestatusprestamo;
	}

	@Column(name = "CVEMARCA", length = 30)
	public String getCvemarca() {
		return this.cvemarca;
	}

	public void setCvemarca(String cvemarca) {
		this.cvemarca = cvemarca;
	}

	@Column(name = "CVEMODELO", length = 30)
	public String getCvemodelo() {
		return this.cvemodelo;
	}

	public void setCvemodelo(String cvemodelo) {
		this.cvemodelo = cvemodelo;
	}

	@Column(name = "PLATAFORMA", precision = 3, scale = 0)
	public Short getPlataforma() {
		return this.plataforma;
	}

	public void setPlataforma(Short plataforma) {
		this.plataforma = plataforma;
	}

	@Column(name = "COSTO", precision = 6)
	public BigDecimal getCosto() {
		return this.costo;
	}

	public void setCosto(BigDecimal costo) {
		this.costo = costo;
	}

	@Column(name = "ESNIMEI", length = 15)
	public String getEsnimei() {
		return this.esnimei;
	}

	public void setEsnimei(String esnimei) {
		this.esnimei = esnimei;
	}

	@Column(name = "TIPOTEL", precision = 3, scale = 0)
	public Short getTipotel() {
		return this.tipotel;
	}

	public void setTipotel(Short tipotel) {
		this.tipotel = tipotel;
	}

	@Column(name = "CVEOFICINADEVENTAS", length = 4)
	public String getCveoficinadeventas() {
		return this.cveoficinadeventas;
	}

	public void setCveoficinadeventas(String cveoficinadeventas) {
		this.cveoficinadeventas = cveoficinadeventas;
	}

	@Column(name = "IDANTENA", precision = 3, scale = 0)
	public Short getIdantena() {
		return this.idantena;
	}

	public void setIdantena(Short idantena) {
		this.idantena = idantena;
	}

	@Column(name = "IDCARCAZA", precision = 3, scale = 0)
	public Short getIdcarcaza() {
		return this.idcarcaza;
	}

	public void setIdcarcaza(Short idcarcaza) {
		this.idcarcaza = idcarcaza;
	}

	@Column(name = "IDDISPLAY", precision = 3, scale = 0)
	public Short getIddisplay() {
		return this.iddisplay;
	}

	public void setIddisplay(Short iddisplay) {
		this.iddisplay = iddisplay;
	}

	@Column(name = "IDTECLADO", precision = 3, scale = 0)
	public Short getIdteclado() {
		return this.idteclado;
	}

	public void setIdteclado(Short idteclado) {
		this.idteclado = idteclado;
	}

	@Column(name = "DESCEDOFISICO", length = 100)
	public String getDescedofisico() {
		return this.descedofisico;
	}

	public void setDescedofisico(String descedofisico) {
		this.descedofisico = descedofisico;
	}

	@Column(name = "FECHAALTA", length = 7)
	public Date getFechaalta() {
		return this.fechaalta;
	}

	public void setFechaalta(Date fechaalta) {
		this.fechaalta = fechaalta;
	}

	@Column(name = "USUARIOCREA", length = 7)
	public String getUsuariocrea() {
		return this.usuariocrea;
	}

	public void setUsuariocrea(String usuariocrea) {
		this.usuariocrea = usuariocrea;
	}

	@Column(name = "ACCESORIOS", length = 100)
	public String getAccesorios() {
		return this.accesorios;
	}

	public void setAccesorios(String accesorios) {
		this.accesorios = accesorios;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof EquipodeprestamoId))
			return false;
		EquipodeprestamoId castOther = (EquipodeprestamoId) other;

		return (this.getIdequipodeprestamo() == castOther.getIdequipodeprestamo())
				&& ((this.getIdestatusprestamo() == castOther.getIdestatusprestamo())
						|| (this.getIdestatusprestamo() != null && castOther.getIdestatusprestamo() != null
								&& this.getIdestatusprestamo().equals(castOther.getIdestatusprestamo())))
				&& ((this.getCvemarca() == castOther.getCvemarca()) || (this.getCvemarca() != null
						&& castOther.getCvemarca() != null && this.getCvemarca().equals(castOther.getCvemarca())))
				&& ((this.getCvemodelo() == castOther.getCvemodelo()) || (this.getCvemodelo() != null
						&& castOther.getCvemodelo() != null && this.getCvemodelo().equals(castOther.getCvemodelo())))
				&& ((this.getPlataforma() == castOther.getPlataforma()) || (this.getPlataforma() != null
						&& castOther.getPlataforma() != null && this.getPlataforma().equals(castOther.getPlataforma())))
				&& ((this.getCosto() == castOther.getCosto()) || (this.getCosto() != null
						&& castOther.getCosto() != null && this.getCosto().equals(castOther.getCosto())))
				&& ((this.getEsnimei() == castOther.getEsnimei()) || (this.getEsnimei() != null
						&& castOther.getEsnimei() != null && this.getEsnimei().equals(castOther.getEsnimei())))
				&& ((this.getTipotel() == castOther.getTipotel()) || (this.getTipotel() != null
						&& castOther.getTipotel() != null && this.getTipotel().equals(castOther.getTipotel())))
				&& ((this.getCveoficinadeventas() == castOther.getCveoficinadeventas())
						|| (this.getCveoficinadeventas() != null && castOther.getCveoficinadeventas() != null
								&& this.getCveoficinadeventas().equals(castOther.getCveoficinadeventas())))
				&& ((this.getIdantena() == castOther.getIdantena()) || (this.getIdantena() != null
						&& castOther.getIdantena() != null && this.getIdantena().equals(castOther.getIdantena())))
				&& ((this.getIdcarcaza() == castOther.getIdcarcaza()) || (this.getIdcarcaza() != null
						&& castOther.getIdcarcaza() != null && this.getIdcarcaza().equals(castOther.getIdcarcaza())))
				&& ((this.getIddisplay() == castOther.getIddisplay()) || (this.getIddisplay() != null
						&& castOther.getIddisplay() != null && this.getIddisplay().equals(castOther.getIddisplay())))
				&& ((this.getIdteclado() == castOther.getIdteclado()) || (this.getIdteclado() != null
						&& castOther.getIdteclado() != null && this.getIdteclado().equals(castOther.getIdteclado())))
				&& ((this.getDescedofisico() == castOther.getDescedofisico())
						|| (this.getDescedofisico() != null && castOther.getDescedofisico() != null
								&& this.getDescedofisico().equals(castOther.getDescedofisico())))
				&& ((this.getFechaalta() == castOther.getFechaalta()) || (this.getFechaalta() != null
						&& castOther.getFechaalta() != null && this.getFechaalta().equals(castOther.getFechaalta())))
				&& ((this.getUsuariocrea() == castOther.getUsuariocrea())
						|| (this.getUsuariocrea() != null && castOther.getUsuariocrea() != null
								&& this.getUsuariocrea().equals(castOther.getUsuariocrea())))
				&& ((this.getAccesorios() == castOther.getAccesorios())
						|| (this.getAccesorios() != null && castOther.getAccesorios() != null
								&& this.getAccesorios().equals(castOther.getAccesorios())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getIdequipodeprestamo();
		result = 37 * result + (getIdestatusprestamo() == null ? 0 : this.getIdestatusprestamo().hashCode());
		result = 37 * result + (getCvemarca() == null ? 0 : this.getCvemarca().hashCode());
		result = 37 * result + (getCvemodelo() == null ? 0 : this.getCvemodelo().hashCode());
		result = 37 * result + (getPlataforma() == null ? 0 : this.getPlataforma().hashCode());
		result = 37 * result + (getCosto() == null ? 0 : this.getCosto().hashCode());
		result = 37 * result + (getEsnimei() == null ? 0 : this.getEsnimei().hashCode());
		result = 37 * result + (getTipotel() == null ? 0 : this.getTipotel().hashCode());
		result = 37 * result + (getCveoficinadeventas() == null ? 0 : this.getCveoficinadeventas().hashCode());
		result = 37 * result + (getIdantena() == null ? 0 : this.getIdantena().hashCode());
		result = 37 * result + (getIdcarcaza() == null ? 0 : this.getIdcarcaza().hashCode());
		result = 37 * result + (getIddisplay() == null ? 0 : this.getIddisplay().hashCode());
		result = 37 * result + (getIdteclado() == null ? 0 : this.getIdteclado().hashCode());
		result = 37 * result + (getDescedofisico() == null ? 0 : this.getDescedofisico().hashCode());
		result = 37 * result + (getFechaalta() == null ? 0 : this.getFechaalta().hashCode());
		result = 37 * result + (getUsuariocrea() == null ? 0 : this.getUsuariocrea().hashCode());
		result = 37 * result + (getAccesorios() == null ? 0 : this.getAccesorios().hashCode());
		return result;
	}

}
