package com.claro.gml.persistence.model;
// Generated Dec 3, 2015 6:51:56 PM by Hibernate Tools 4.3.1.Final

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Catambito generated by hbm2java
 */
@Entity
@Table(name = "CATAMBITO")
public class Catambito implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private CatambitoId id;

	public Catambito() {
	}

	public Catambito(CatambitoId id) {
		this.id = id;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "idambito", column = @Column(name = "IDAMBITO", nullable = false, precision = 22, scale = 0) ),
			@AttributeOverride(name = "descripcion", column = @Column(name = "DESCRIPCION", length = 50) ),
			@AttributeOverride(name = "activo", column = @Column(name = "ACTIVO", precision = 22, scale = 0) ),
			@AttributeOverride(name = "idarea", column = @Column(name = "IDAREA", precision = 2, scale = 0) ) })
	public CatambitoId getId() {
		return this.id;
	}

	public void setId(CatambitoId id) {
		this.id = id;
	}

}
