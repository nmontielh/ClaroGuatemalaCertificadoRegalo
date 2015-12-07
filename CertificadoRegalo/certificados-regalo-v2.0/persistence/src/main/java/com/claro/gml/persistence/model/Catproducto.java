package com.claro.gml.persistence.model;
// Generated Dec 3, 2015 6:51:56 PM by Hibernate Tools 4.3.1.Final

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Catproducto generated by hbm2java
 */
@Entity
@Table(name = "CATPRODUCTO", schema = "CERTREG")
public class Catproducto implements java.io.Serializable {

	private CatproductoId id;

	public Catproducto() {
	}

	public Catproducto(CatproductoId id) {
		this.id = id;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "cveproducto", column = @Column(name = "CVEPRODUCTO", nullable = false, precision = 6, scale = 0) ),
			@AttributeOverride(name = "descripcion", column = @Column(name = "DESCRIPCION", length = 100) ),
			@AttributeOverride(name = "activo", column = @Column(name = "ACTIVO", precision = 2, scale = 0) ) })
	public CatproductoId getId() {
		return this.id;
	}

	public void setId(CatproductoId id) {
		this.id = id;
	}

}