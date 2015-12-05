package com.claro.gml.persistence.model;
// Generated Dec 3, 2015 6:51:56 PM by Hibernate Tools 4.3.1.Final

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Catdistribuidor generated by hbm2java
 */
@Entity
@Table(name = "CATDISTRIBUIDOR", schema = "CERTREG")
public class Catdistribuidor implements java.io.Serializable {

	private CatdistribuidorId id;

	public Catdistribuidor() {
	}

	public Catdistribuidor(CatdistribuidorId id) {
		this.id = id;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "iddistribuidor", column = @Column(name = "IDDISTRIBUIDOR", nullable = false, precision = 4, scale = 0) ),
			@AttributeOverride(name = "region", column = @Column(name = "REGION", nullable = false, length = 2) ),
			@AttributeOverride(name = "fuerzaventas", column = @Column(name = "FUERZAVENTAS", length = 30) ),
			@AttributeOverride(name = "razonsocial", column = @Column(name = "RAZONSOCIAL", length = 80) ),
			@AttributeOverride(name = "contacto", column = @Column(name = "CONTACTO", length = 60) ),
			@AttributeOverride(name = "direccion", column = @Column(name = "DIRECCION", length = 160) ),
			@AttributeOverride(name = "telefono", column = @Column(name = "TELEFONO", length = 16) ),
			@AttributeOverride(name = "ext", column = @Column(name = "EXT", length = 5) ),
			@AttributeOverride(name = "email", column = @Column(name = "EMAIL", length = 60) ),
			@AttributeOverride(name = "activo", column = @Column(name = "ACTIVO", precision = 2, scale = 0) ) })
	public CatdistribuidorId getId() {
		return this.id;
	}

	public void setId(CatdistribuidorId id) {
		this.id = id;
	}

}
