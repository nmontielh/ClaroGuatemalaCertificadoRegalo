package com.claro.gml.persistence.model;
// Generated Dec 3, 2015 6:51:56 PM by Hibernate Tools 4.3.1.Final

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Catdiasfestivos generated by hbm2java
 */
@Entity
@Table(name = "CATDIASFESTIVOS")
public class Catdiasfestivos implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private CatdiasfestivosId id;

	public Catdiasfestivos() {
	}

	public Catdiasfestivos(CatdiasfestivosId id) {
		this.id = id;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "fecha", column = @Column(name = "FECHA", nullable = false, length = 7) ),
			@AttributeOverride(name = "activo", column = @Column(name = "ACTIVO", precision = 1, scale = 0) ),
			@AttributeOverride(name = "pais", column = @Column(name = "PAIS", length = 3) ) })
	public CatdiasfestivosId getId() {
		return this.id;
	}

	public void setId(CatdiasfestivosId id) {
		this.id = id;
	}

}
