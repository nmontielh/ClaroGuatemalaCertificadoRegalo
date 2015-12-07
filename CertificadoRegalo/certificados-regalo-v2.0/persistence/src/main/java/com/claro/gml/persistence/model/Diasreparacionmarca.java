package com.claro.gml.persistence.model;
// Generated Dec 3, 2015 6:51:56 PM by Hibernate Tools 4.3.1.Final

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Diasreparacionmarca generated by hbm2java
 */
@Entity
@Table(name = "DIASREPARACIONMARCA", schema = "CERTREG")
public class Diasreparacionmarca implements java.io.Serializable {

	private DiasreparacionmarcaId id;

	public Diasreparacionmarca() {
	}

	public Diasreparacionmarca(DiasreparacionmarcaId id) {
		this.id = id;
	}

	@EmbeddedId

	@AttributeOverrides({ @AttributeOverride(name = "cvemarca", column = @Column(name = "CVEMARCA", length = 30) ),
			@AttributeOverride(name = "cveoficinadeventas", column = @Column(name = "CVEOFICINADEVENTAS", length = 4) ),
			@AttributeOverride(name = "cvemensajeria", column = @Column(name = "CVEMENSAJERIA", length = 2) ),
			@AttributeOverride(name = "aplicagarantia", column = @Column(name = "APLICAGARANTIA", precision = 22, scale = 0) ),
			@AttributeOverride(name = "cveregion", column = @Column(name = "CVEREGION", length = 2) ),
			@AttributeOverride(name = "diashabiles", column = @Column(name = "DIASHABILES", length = 2) ),
			@AttributeOverride(name = "diasreparacion", column = @Column(name = "DIASREPARACION", precision = 10, scale = 0) ) })
	public DiasreparacionmarcaId getId() {
		return this.id;
	}

	public void setId(DiasreparacionmarcaId id) {
		this.id = id;
	}

}