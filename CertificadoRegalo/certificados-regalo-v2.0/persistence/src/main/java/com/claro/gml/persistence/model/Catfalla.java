package com.claro.gml.persistence.model;
// Generated Dec 3, 2015 6:51:56 PM by Hibernate Tools 4.3.1.Final

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Catfalla generated by hbm2java
 */
@Entity
@Table(name = "CATFALLA")
public class Catfalla implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private CatfallaId id;

	public Catfalla() {
	}

	public Catfalla(CatfallaId id) {
		this.id = id;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "idfalla", column = @Column(name = "IDFALLA", nullable = false, precision = 4, scale = 0) ),
			@AttributeOverride(name = "descripcion", column = @Column(name = "DESCRIPCION", length = 100) ),
			@AttributeOverride(name = "activo", column = @Column(name = "ACTIVO", precision = 2, scale = 0) ),
			@AttributeOverride(name = "cvemarca", column = @Column(name = "CVEMARCA", length = 30) ),
			@AttributeOverride(name = "fallaMarca", column = @Column(name = "FALLA_MARCA", length = 50) ) })
	public CatfallaId getId() {
		return this.id;
	}

	public void setId(CatfallaId id) {
		this.id = id;
	}

}
