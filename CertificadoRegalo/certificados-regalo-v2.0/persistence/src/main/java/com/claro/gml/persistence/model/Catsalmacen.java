package com.claro.gml.persistence.model;
// Generated Dec 3, 2015 6:51:56 PM by Hibernate Tools 4.3.1.Final

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Catsalmacen generated by hbm2java
 */
@Entity
@Table(name = "CATSALMACEN")
public class Catsalmacen implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private CatsalmacenId id;

	public Catsalmacen() {
	}

	public Catsalmacen(CatsalmacenId id) {
		this.id = id;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "cvealmacen", column = @Column(name = "CVEALMACEN", nullable = false, length = 4) ),
			@AttributeOverride(name = "descripcion", column = @Column(name = "DESCRIPCION", length = 50) ),
			@AttributeOverride(name = "activo", column = @Column(name = "ACTIVO", precision = 22, scale = 0) ) })
	public CatsalmacenId getId() {
		return this.id;
	}

	public void setId(CatsalmacenId id) {
		this.id = id;
	}

}
