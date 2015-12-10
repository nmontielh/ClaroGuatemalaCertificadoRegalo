package com.claro.gml.persistence.model;
// Generated Dec 3, 2015 6:51:56 PM by Hibernate Tools 4.3.1.Final

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Catatipoventa generated by hbm2java
 */
@Entity
@Table(name = "CATATIPOVENTA")
public class Catatipoventa implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private CatatipoventaId id;

	public Catatipoventa() {
	}

	public Catatipoventa(CatatipoventaId id) {
		this.id = id;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "cvetipoventa", column = @Column(name = "CVETIPOVENTA", nullable = false, length = 3) ),
			@AttributeOverride(name = "idregiontventa", column = @Column(name = "IDREGIONTVENTA", nullable = false, precision = 22, scale = 0) ),
			@AttributeOverride(name = "descripcion", column = @Column(name = "DESCRIPCION", length = 50) ) })
	public CatatipoventaId getId() {
		return this.id;
	}

	public void setId(CatatipoventaId id) {
		this.id = id;
	}

}
