package com.claro.gml.persistence.model;
// Generated Dec 3, 2015 6:51:56 PM by Hibernate Tools 4.3.1.Final

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Cataciudad generated by hbm2java
 */
@Entity
@Table(name = "CATACIUDAD")
public class Cataciudad implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private CataciudadId id;

	public Cataciudad() {
	}

	public Cataciudad(CataciudadId id) {
		this.id = id;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "cveciudad", column = @Column(name = "CVECIUDAD", nullable = false, length = 3) ),
			@AttributeOverride(name = "idregionciudad", column = @Column(name = "IDREGIONCIUDAD", nullable = false, precision = 22, scale = 0) ),
			@AttributeOverride(name = "descripcion", column = @Column(name = "DESCRIPCION", length = 50) ) })
	public CataciudadId getId() {
		return this.id;
	}

	public void setId(CataciudadId id) {
		this.id = id;
	}

}
