package com.claro.gml.persistence.model;
// Generated Dec 3, 2015 6:51:56 PM by Hibernate Tools 4.3.1.Final

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * PtoCtlperfilCa generated by hbm2java
 */
@Entity
@Table(name = "PTO_CTLPERFIL_CA", schema = "CERTREG")
public class PtoCtlperfilCa implements java.io.Serializable {

	private PtoCtlperfilCaId id;
	private PtoCtlpantallaCa ptoCtlpantallaCa;
	private PtoCtlperfiln ptoCtlperfiln;
	private Character estatus;

	public PtoCtlperfilCa() {
	}

	public PtoCtlperfilCa(PtoCtlperfilCaId id, PtoCtlpantallaCa ptoCtlpantallaCa, PtoCtlperfiln ptoCtlperfiln) {
		this.id = id;
		this.ptoCtlpantallaCa = ptoCtlpantallaCa;
		this.ptoCtlperfiln = ptoCtlperfiln;
	}

	public PtoCtlperfilCa(PtoCtlperfilCaId id, PtoCtlpantallaCa ptoCtlpantallaCa, PtoCtlperfiln ptoCtlperfiln,
			Character estatus) {
		this.id = id;
		this.ptoCtlpantallaCa = ptoCtlpantallaCa;
		this.ptoCtlperfiln = ptoCtlperfiln;
		this.estatus = estatus;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "idperfiln", column = @Column(name = "IDPERFILN", nullable = false, precision = 22, scale = 0) ),
			@AttributeOverride(name = "idpantalla", column = @Column(name = "IDPANTALLA", nullable = false, precision = 22, scale = 0) ),
			@AttributeOverride(name = "idproceso", column = @Column(name = "IDPROCESO", nullable = false, precision = 22, scale = 0) ) })
	public PtoCtlperfilCaId getId() {
		return this.id;
	}

	public void setId(PtoCtlperfilCaId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IDPANTALLA", nullable = false, insertable = false, updatable = false)
	public PtoCtlpantallaCa getPtoCtlpantallaCa() {
		return this.ptoCtlpantallaCa;
	}

	public void setPtoCtlpantallaCa(PtoCtlpantallaCa ptoCtlpantallaCa) {
		this.ptoCtlpantallaCa = ptoCtlpantallaCa;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IDPERFILN", nullable = false, insertable = false, updatable = false)
	public PtoCtlperfiln getPtoCtlperfiln() {
		return this.ptoCtlperfiln;
	}

	public void setPtoCtlperfiln(PtoCtlperfiln ptoCtlperfiln) {
		this.ptoCtlperfiln = ptoCtlperfiln;
	}

	@Column(name = "ESTATUS", length = 1)
	public Character getEstatus() {
		return this.estatus;
	}

	public void setEstatus(Character estatus) {
		this.estatus = estatus;
	}

}