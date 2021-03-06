package com.claro.gml.persistence.model;
// Generated Dec 3, 2015 6:51:56 PM by Hibernate Tools 4.3.1.Final

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Catsplanes generated by hbm2java
 */
@Entity
@Table(name = "CATSPLANES")
public class Catsplanes implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private CatsplanesId id;

	public Catsplanes() {
	}

	public Catsplanes(CatsplanesId id) {
		this.id = id;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "cveplanes", column = @Column(name = "CVEPLANES", nullable = false, length = 30) ),
			@AttributeOverride(name = "descripcion", column = @Column(name = "DESCRIPCION", length = 50) ),
			@AttributeOverride(name = "activo", column = @Column(name = "ACTIVO", precision = 22, scale = 0) ) })
	public CatsplanesId getId() {
		return this.id;
	}

	public void setId(CatsplanesId id) {
		this.id = id;
	}

}
