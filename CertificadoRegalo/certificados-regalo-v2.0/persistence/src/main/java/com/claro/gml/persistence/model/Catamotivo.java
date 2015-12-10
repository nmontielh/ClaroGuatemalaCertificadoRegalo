package com.claro.gml.persistence.model;
// Generated Dec 3, 2015 6:51:56 PM by Hibernate Tools 4.3.1.Final

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Catamotivo generated by hbm2java
 */
@Entity
@Table(name = "CATAMOTIVO")
public class Catamotivo implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private CatamotivoId id;

	public Catamotivo() {
	}

	public Catamotivo(CatamotivoId id) {
		this.id = id;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "cvemotivo", column = @Column(name = "CVEMOTIVO", nullable = false, length = 5) ),
			@AttributeOverride(name = "descripcion", column = @Column(name = "DESCRIPCION", length = 30) ),
			@AttributeOverride(name = "activo", column = @Column(name = "ACTIVO", precision = 22, scale = 0) ) })
	public CatamotivoId getId() {
		return this.id;
	}

	public void setId(CatamotivoId id) {
		this.id = id;
	}

}
