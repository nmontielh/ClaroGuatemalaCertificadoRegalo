package com.claro.gml.persistence.model;
// Generated Dec 3, 2015 6:51:56 PM by Hibernate Tools 4.3.1.Final

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Cattipomovimiento generated by hbm2java
 */
@Entity
@Table(name = "CATTIPOMOVIMIENTO", schema = "CERTREG")
public class Cattipomovimiento implements java.io.Serializable {

	private CattipomovimientoId id;

	public Cattipomovimiento() {
	}

	public Cattipomovimiento(CattipomovimientoId id) {
		this.id = id;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "idtipomovimiento", column = @Column(name = "IDTIPOMOVIMIENTO", nullable = false, precision = 6, scale = 0) ),
			@AttributeOverride(name = "descripcion", column = @Column(name = "DESCRIPCION", length = 50) ) })
	public CattipomovimientoId getId() {
		return this.id;
	}

	public void setId(CattipomovimientoId id) {
		this.id = id;
	}

}