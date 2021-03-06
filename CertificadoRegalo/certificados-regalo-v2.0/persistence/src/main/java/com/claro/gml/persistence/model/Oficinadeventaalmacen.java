package com.claro.gml.persistence.model;
// Generated Dec 3, 2015 6:51:56 PM by Hibernate Tools 4.3.1.Final

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Oficinadeventaalmacen generated by hbm2java
 */
@Entity
@Table(name = "OFICINADEVENTAALMACEN")
public class Oficinadeventaalmacen implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private OficinadeventaalmacenId id;

	public Oficinadeventaalmacen() {
	}

	public Oficinadeventaalmacen(OficinadeventaalmacenId id) {
		this.id = id;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "cveoficinadeventas", column = @Column(name = "CVEOFICINADEVENTAS", nullable = false, length = 4) ),
			@AttributeOverride(name = "cvealmacen", column = @Column(name = "CVEALMACEN", nullable = false, length = 4) ) })
	public OficinadeventaalmacenId getId() {
		return this.id;
	}

	public void setId(OficinadeventaalmacenId id) {
		this.id = id;
	}

}
