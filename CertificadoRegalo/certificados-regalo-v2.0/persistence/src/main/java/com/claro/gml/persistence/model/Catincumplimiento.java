package com.claro.gml.persistence.model;
// Generated Dec 3, 2015 6:51:56 PM by Hibernate Tools 4.3.1.Final

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Catincumplimiento generated by hbm2java
 */
@Entity
@Table(name = "CATINCUMPLIMIENTO", schema = "CERTREG")
public class Catincumplimiento implements java.io.Serializable {

	private CatincumplimientoId id;

	public Catincumplimiento() {
	}

	public Catincumplimiento(CatincumplimientoId id) {
		this.id = id;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "idincumplimiento", column = @Column(name = "IDINCUMPLIMIENTO", nullable = false, precision = 2, scale = 0) ),
			@AttributeOverride(name = "descripcion", column = @Column(name = "DESCRIPCION", length = 60) ),
			@AttributeOverride(name = "activo", column = @Column(name = "ACTIVO", precision = 2, scale = 0) ) })
	public CatincumplimientoId getId() {
		return this.id;
	}

	public void setId(CatincumplimientoId id) {
		this.id = id;
	}

}